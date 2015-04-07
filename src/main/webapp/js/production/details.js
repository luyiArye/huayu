var productId=0;
var faceImages;

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

/**
 * 加载作品评论
 * @param page
 */
function loadProcuctionComment(page){
	$("#productComments").html("<img src='"+contextPath_+"/images/loading.gif' />");
	$.huayu.ajax({
		type: "GET",
		url: contextPath_+"/pc/load/"+page,
		data: "productionId="+productId+"&totalCount="+$("#pcTotalCount").val(),
		success: function(data){
			var pcData=data.data;
			
			var commentsHtml='';
			if(pcData && pcData.length>0){
				var pc;
				for(var i=0;i<pcData.length;i++){
					pc=pcData[i];
					
					commentsHtml+='<li class="comment byuser even thread-even depth-1 list-group-item top" id="comment_'+pc.id+'">'+
										'<a class="comment-author" href="'+contextPath_+'/user/room/'+pc.commentUser.id+'" data-instant="">'+
											'<img src="'+contextPath_+'/user/headIco/1/'+pc.commentUser.id+'" class="avatar" width="50" height="50" style="display: block;">'+
										'</a>'+
										'<div class="comment-body">'+
											'<cite class="fn">'+
												'<a href="'+contextPath_+'/user/room/'+pc.commentUser.id+'">'+pc.commentUser.userName+'</a>'+
											'</cite>'+
											'<div class="comment-content"><p>'+
												replaceCommentFaceTag(pc.content)+
											'</p></div>'+
											'<div class="comment-meta">'+
												'<time title="'+new Date().format("yyyy年MM月dd日 hh:mm:ss")+'">'+new Date().format("yyyy年MM月dd日 hh:mm:ss")+'</time>'+
											'</div>'+
										'</div>'+
									'</li>';
				}
			}
			
			$("#productComments").html(commentsHtml);
			
			$("#commentTotalCount").text(0);
			$("#totalCommentCount").text(0);
			if((data.tc || 0)>0){
				$("#commentTotalCount").text(data.tc || 0);
				$("#totalCommentCount").text(data.tc || 0);
				//初始化分页标签
				$("#pcTotalCount").val(data.tc || 0);
				$('#commentPageation').twbsPagination({
			        totalPages: data.tp || 1,
			        visiblePages: 5,
			        startPage: page,
			        first: '第一页',
			        prev: '上一页',
			        next: '下一页',
			        last: '最后一页',
			        onPageClick: function(event, page) {
			        	loadProcuctionComment(page);
			        }
			    });
			}
		}
	});
}

//替换评论中的表情标记为表情图片
function replaceCommentFaceTag(comment){
	if(faceImages && comment.match(/(\[.*\]+)/mg)){
		for(var i=0;i<faceImages.length;i++){
			comment=comment.replace(new RegExp("\\["+faceImages[i]+"\\]", "gm"), "<img src='"+contextPath_+"/images/faces/"+faceImages[i]+".gif' />");
		}
	}
	
	return comment;
}

//加载表情图片
function loadFaceImages(){
	$.huayu.ajax({
		type: "GET",
		url: contextPath_+"/loadFaces",
		success: function(data){
			faceImages=data.data;
			//加载作品评论
			loadProcuctionComment(1);
			
			if(faceImages && faceImages.length>0){
				var faceLis='';
				for(var i=0;i<faceImages.length;i++){
					faceLis+='<li title="'+faceImages[i]+'"><img src="'+contextPath_+'/images/faces/'+faceImages[i]+'.gif" alt="'+faceImages[i]+'" width="22" height="22" /></li>';
				}
				$("#faceImagesUl").html(faceLis);
			}
		}
	});
}

function clickHideFaces(){
	if($(this).parents("#faceImagesDiv").length<=0 && $(this).parents("#facesButton").length<=0){
		$("#faceImagesDiv").fadeOut(100);
		$(document).unbind("click", clickHideFaces);
	}
}

function triggerFaces(event){
	event=event || window.event;
	event.stopPropagation();
	if($("#faceImagesDiv:visible").length>0){
		$("#faceImagesDiv").fadeOut(100);
		$(document).unbind("click", clickHideFaces);
		$("#faceImagesUl li").unbind("click");
	}
	else{
		$("#faceImagesDiv").fadeIn(100);
		$(document).unbind("click", clickHideFaces).bind("click", clickHideFaces);

		//点击表情图片
		$("#faceImagesUl li").unbind("click").bind("click", function(){
			var targetText=$("#faceImagesUl").attr("targetText");
			
			$("#"+targetText).focus();
			var faceTag=$(this).attr("title");
			if(faceTag){
				$("#"+targetText).val($("#"+targetText).val()+"["+faceTag+"]");
			}

			$('#commentForm').
			bootstrapValidator('updateStatus', 'content', 'NOT_VALIDATED')
			.bootstrapValidator('validateField', "content");

			return false;
		});
	}
}

//判断是否登录
function commentCheckLogin(loginUser){
	//刷新头部登录信息
	_refreshHeadLoginInfo(loginUser);
	if(loginUser && loginUser.id){
		$("#noLoginCommentPmpt").hide();
		$("#commentForm").show();
		
		$("#commentLoginUser").text(loginUser.userName);
		
		$('#commentForm').bootstrapValidator({
			message: '录入数据存在错误.',
			feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				content: {
	    	 		message: '评论内容错误.',
	    	 		validators: {
	    	 			notEmpty: {
	    	 				message: '评论内容不能为空.'
	    	 			},
	    	 			stringLength: {
	                        min: 5,
	                        max: 260,
	                        message: '评论内容的长度必须介于5到260之间.'
	                    }
	    	 		}
	    	 	}
	      	}
		}).on('success.form.bv', function(e) {
			//提交评论
			saveComment();
			
			return false;
		});
	}
	else{
		$("#commentForm").hide();
		$("#noLoginCommentPmpt").show();
	}
}

//保存评论内容
function saveComment(loginUser){
	if(loginUser){
		commentCheckLogin(loginUser);
	}
	
	$("#commentForm :submit").button('loading');
	
	$.huayu.ajax({
		formId: "commentForm",
		success: function(data){
			$('#commentForm').data("bootstrapValidator").resetForm(false);
			$("#commentContent").val("");
		
			$.huayu.ajax({
				url: contextPath_+"/pc/commentTotal",
				type: "GET",
				data: "productionId="+productId,
				success: function(data){
					var pc=data.data;
					
					if(pc){
						//将评论总数设置为0，使加载评论时重新查询总记录数
						$("#pcTotalCount").val(pc.totalCount);
						//将分页标签替换为一个新的容器以便于再次初始化分页标签
						$("#commentPageation").replaceWith('<ul id="commentPageation"></ul>');
						
						loadProcuctionComment(pc.totalPage);
					}
				}
			});
		},
		noLoginCallback: function(data){
			//登录超时，弹出登录窗登录
			_showLoginModel(saveComment);
		},
		exception: function(data){
		}
	});
}

//赞
function praise(loginUser){
	if(loginUser){
		commentCheckLogin(loginUser);
	}
	
	$.huayu.ajax({
		type: "POST",
		url: contextPath_+"/po/praise/"+productId,
		success: function(data){
			poStatistic();
		},
		noLoginCallback: function(data){
			//登录超时，弹出登录窗登录
			_showLoginModel(praise);
		},
		exception: function(data){
		}
	});
}

//踩
function trample(loginUser){
	if(loginUser){
		commentCheckLogin(loginUser);
	}
	
	$.huayu.ajax({
		type: "POST",
		url: contextPath_+"/po/trample/"+productId,
		success: function(data){
			poStatistic();
		},
		noLoginCallback: function(data){
			//登录超时，弹出登录窗登录
			_showLoginModel(praise);
		},
		exception: function(data){
		}
	});
}

//作品操作统计
function poStatistic(){
	$.huayu.ajax({
		type: "GET",
		url: contextPath_+"/production/statisticsPOCount/"+productId,
		success: function(data){
			var productionOperation=data.data;
			
			if(productionOperation){
				$("span[name='poCountSpan_'][operationType]").each(function(i){
					$(this).text(productionOperation[$(this).attr("operationType")] || "0");
				});
				
				//赞操作
				$("#operationBar_").attr("data-votes-up", productionOperation.praiseCount);
				//踩操作
				$("#operationBar_").attr("data-votes-down", productionOperation.trampleCount);
			}
		}
	});
}

$(function(){
	//加载表情图片
	loadFaceImages();
	$("#commentLoginLink").unbind("click").bind("click", function(){
		_showLoginModel(commentCheckLogin);
	});
	
	//加载作品操作统计
	//poStatistic();
	
	//校验登录
	$.huayu.checkLogin(commentCheckLogin);
});