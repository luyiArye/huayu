//重新加载用户作品
function reLoadProduction(operationType){
	productionPage=1;
	$("#roomContent").empty();
	loadProduct(operationType);
	statistics();
}

//作品页数
var productionPage=1;
//加载画语作品
function loadProduct(operationType){
	//显示loading图片
	$("#loadingArticleMsg").css({visibility: "visible"});
	//关闭ajax开关
	ajaxkey=false;
	
	$.huayu.ajax({
		type: "GET",
		url: contextPath_+"/production/load/",
		data: "createdBy="+$("#userId").val()+"&page="+productionPage+"&pageSize=5&operationType="+(operationType || ""),
		success: function(data){
			if(data.exCode){
				//加载数据出现异常
				return ;
			}
			
			$(document).unbind("scroll").bind("scroll", function(){
		        if(getDataCheck() && ajaxkey) {
		        	loadProduct(operationType);
		        };
		    });
			
			if(data.data && data.data.length>0){
				//增加页数
				productionPage++;
				var productList=data.data;
				for(var i=0;i<productList.length;i++){
					var productHtml='<article class="panel panel-default archive">'+
										'<div class="panel-body">'+
											'<div class="entry-header page-header">'+
												'<h3 class="entry-title h4">'+
													'<a href="'+contextPath_+'/production/details/'+productList[i].id+'" itemprop="url">'+
														'<span itemprop="name">'+productList[i].title+'</span>'+
													'</a>'+
												'</h3>'+
												'<div class="entry-meta">'+
													'<a href="'+contextPath_+'/user/room/'+productList[i].createdByUser.id+'" itemprop="author" title="'+productList[i].createdByUser.userAccount+'">'+
														'<span class="glyphicon glyphicon-user"></span>'+
														productList[i].createdByUser.userName+
													'</a>'+
													'<span class="glyphicon glyphicon-calendar"></span>'+
													'<time class="entry-date" title="发布于 '+new Date(productList[i].createdDate).format("yyyy年MM月dd日 hh:mm:ss")+'">'
														+new Date(productList[i].createdDate).format("yyyy年MM月dd日")+
													'</time>'+
													'<a href="'+contextPath_+'/production/details/'+productList[i].id+'#comments" itemprop="discussionUrl" itemscope="" itemtype="">'+
														'<span class="glyphicon glyphicon-comment"></span>'+
														'<span itemprop="interactionCount">'+productList[i].commentCount+'</span>'+
													'</a>'+
													'<span class="glyphicon glyphicon-eye-open"></span>'+productList[i].browseCount+' 次浏览'+
												'</div>'+
											'</div>'+
											'<div class="entry-content" itemprop="description">'+
												'<p>'+productList[i].content+'</p>'+
											'</div>'+
											'<div id="productImages'+productList[i].id+'" class="entry-content productListCarousel">'+
											'</div>'+
										'</div>'+
				 					'</article>';
					
					$("#roomContent").append(productHtml);
					
					//加载作品图片轮播
					loadProductionImages(productList[i].id, "productImages"+productList[i].id);
				}
			}
			else{
				//没有任何数据
			}
		},
		complete: function(xmlHttpRequest){
			//启用ajax开关
			ajaxkey=true;
			//隐藏loading图片
			$("#loadingArticleMsg").css({visibility: "hidden"});
		}
	});
}

//加载作品图片轮播
function loadProductionImages(productionId, containerId){
	$.huayu.ajax({
		type: "GET",
		url: contextPath_+"/pr/load/"+productionId,
		success: function(data){
			if(data.exCode){
				//加载数据出现异常
				return ;
			}
			
			if(data.data && data.data.length>0){
				var imagesHtml='<div class="carousel slide" id="carousel_'+productionId+'" data-ride="carousel">';
				var imagesOls='<ol class="carousel-indicators">';
				var imagesItems='<div class="carousel-inner">';
				
				var imageList=data.data;
				for(var i=0;i<imageList.length;i++){
					if(i==0){
						imagesOls+='<li data-slide-to="'+i+'" data-target="#carousel_'+productionId+'" class="active"></li>';
						imagesItems+='<div class="item active">';
						imagesItems+='<img sourceSrc="" src="'+contextPath_+'/file/read/'+imageList[i].resourceId+'">';
					}
					else{
						imagesOls+='<li data-slide-to="'+i+'" data-target="#carousel_'+productionId+'"></li>';
						imagesItems+='<div class="item">';
						imagesItems+='<img src="'+contextPath_+'/images/loading.gif" sourceSrc="'+contextPath_+'/file/read/'+imageList[i].resourceId+'">';
					}
					
					if(imageList[i].description && $.trim(imageList[i].description).length>0){
						imagesItems+='<div class="carousel-caption" contenteditable="true">'+
										'<p>'+imageList[i].description+'</p>'+
									'</div>';
					}
					
					imagesItems+='</div>';
				}
				
				imagesHtml+=imagesOls+
							'</ol>'+
							imagesItems+
							'</div>'+
							'<a data-slide="prev" href="#carousel_'+productionId+'" class="left carousel-control"><span class="glyphicon glyphicon-chevron-left"></span></a>'+
							'<a data-slide="next" href="#carousel_'+productionId+'" class="right carousel-control"><span class="glyphicon glyphicon-chevron-right"></span></a>'+
							'</div>';

				$("#"+containerId).html(imagesHtml);

				$("#carousel_"+productionId).carousel({
					pause: "hover"
				});
				$("#carousel_"+productionId).on('slid.bs.carousel', function(){
					var currentImg=$(this).find("div.carousel-inner div.active img");
					var sourceSrc=currentImg.attr("sourceSrc");
					if(sourceSrc.length>0){
						currentImg.attr("src", sourceSrc);
						currentImg.attr("sourceSrc", "");
					}
				});
			}
		}
	});
}

//滚动ajax控制
var ajaxkey=true;
//判断是否滚动到最低端
function getDataCheck(){
	var lastArticle=$("#roomContent article:last");
	var lastArticleHeight=lastArticle.offset().top/*+Math.floor(lastArticle.outerHeight()/2)*/;
	var documentHeight = $(window).height();
	var scrollTop = $(document).scrollTop();
	
	return lastArticleHeight<documentHeight+scrollTop;
}

//统计用户相关数量
function statistics(){
	$.huayu.ajax({
		type: "GET",
		url: contextPath_+"/user/statistics/"+$("#userId").val(),
		success: function(data){
			if(data.data){
				for(var ct in data.data){
					$("#"+ct).text(data.data[ct]);
				}
			}
		}
	});
}

$(function(){
	loadProduct();
	statistics();
	
	$("#statisticsTypeUl li").click(function(){
		$("#statisticsTypeUl li").removeClass("active");
		$(this).addClass("active");
	});
	
	$("#roomHeadIcoImg").attr("src", contextPath_+"/user/headIco/1/"+$("#userId").val());
});