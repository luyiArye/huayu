//初始化板块下拉列表
function initCategorySel(categorys){
	if(categorys){
		var categorysOptions='';
		for(var i=0;i<categorys.length;i++){
			categorysOptions+='<option value="'+categorys[i].id+'">'+categorys[i].categoryName+'</option>';
		}
		$("#categoryIdSel").html(categorysOptions);
	}
}

//提交话语作品
function submitProduction(){
	$.huayu.ajax({
		type: "POST",
		url: contextPath_+"/production/save",
		//data: $('#publishForm').serialize(),
		formId: "publishForm",
		success: function(data){
			
		}
	});
	
}

$(function(){
	//加载板块下拉列表
	if(HuayuCategorys_){
		initCategorySel(HuayuCategorys_);
	}
	else{
		//加载栏目信息
		$.huayu.ajax({
			url: contextPath_+"/category/load",
			type: "GET",
			dataType: "json",
			success: function(data){
				initCategorySel(data);
			}
		});
	}
	
	$('#publishForm').bootstrapValidator({
		message: '录入数据存在错误.',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			title: {
    	 		message: '标题错误.',
    	 		validators: {
    	 			notEmpty: {
    	 				message: '标题不能为空.'
    	 			},
    	 			stringLength: {
                        min: 1,
                        max: 30,
                        message: '标题最大长度不能超过30.'
                    }
    	 		}
    	 	},
    	 	content: {
    	 		message: '描述错误.',
    	 		validators: {
    	 			stringLength: {
                        min: 0,
                        max: 100,
                        message: '描述最大长度不能超过100.'
                    }
    	 		}
    	 	}
		}
	}).on('success.form.bv', function(e) {
		$.huayu.hideError("publishForm");
		if($("#huayuImageFilesUl>li").length<=0){
			$.huayu.showError("publishForm", "至少上传一张画语作品！");
			return false;
		}
		
		//设置作品序号
		$("#huayuImageFilesUl>li").each(function(i){
			$(this).find("[name='productionResources.seqNo']").val(i+1);
			
			//设置元素name属性
			$(this).find("[name='productionResources.seqNo']").attr("name", "productionResources["+i+"].seqNo");
			$(this).find("[name='productionResources.resourceId']").attr("name", "productionResources["+i+"].resourceId");
			$(this).find("[name='productionResources.description']").attr("name", "productionResources["+i+"].description");
		});
		$(this).find(":submit").button('loading');
		//submitProduction();
		return true;
	});
	
	//绑定上传事件
	$('#huayuImageDragUploadZone').dmUploader({
        url: contextPath_+'/file/upload/1',
        dataType: 'json',
        allowedTypes: 'image/*',
        maxFileSize: 5000*1024,
        onInit: function(){
        },
        onBeforeUpload: function(id){
        },
        onNewFile: function(id, file){
        	var newFileLi = '<li id="huayuImageFilesLi_'+id+'" class="panel panel-default">'+
        						'<table style="width:100%;"><tr><td style="width: 110px;">'+
	        						'<div class="upImageDiv">'+
	        							'<img id="huayuImage_'+id+'" src="..." alt="..." class="img-rounded">'+
	        						'</div>'+
        						'</td><td style="vertical-align: top; padding-left: 5px;">'+
        							'<label for="description_'+id+'">作品概述</label>'+
        							'<input type="hidden" name="productionResources.seqNo" id="seqNo_'+id+'" />'+
	        						'<input type="hidden" name="productionResources.resourceId" id="resourceId_'+id+'" />'+
	        						'<input class="form-control" name="productionResources.description" id="description_'+id+'" />'+
	        						'<label>上传状态: <label id="uploadStausLabel_'+id+'">等待上传...</label></label>'+
        						'</td></tr></table>'+
        				 	'</li>';
        	$("#huayuImageFilesUl").append(newFileLi);
        	//$.danidemo.addFile('#demo-files', id, file);

        	/*** Begins Image preview loader ***/
        	var img = $('#huayuImage_'+id);
        	if (typeof FileReader !== "undefined"){
        		var reader = new FileReader();
        		// Last image added
        		reader.onload = function (e) {
        			img.attr('src', e.target.result);
        		}
        		reader.readAsDataURL(file);
        	} 
        	else {
        		img.attr('src', contextPath_+"/images/loading.gif");
        	}
        	/*** Ends Image preview loader ***/
        	
        	//添加拖拽事件
        	$("#huayuImageFilesUl").sortable();
            $("#huayuImageFilesUl").disableSelection();
        },
        onComplete: function(){
        },
        onUploadProgress: function(id, percent){
        	$("#uploadStausLabel_"+id).text(percent + '%');
        },
        onUploadSuccess: function(id, data){
        	if(data && data.data && data.data.length>0){
        		$("#resourceId_"+id).val(data.data[0].id);
        	}
        	$("#uploadStausLabel_"+id).text("上传成功！");
        },
        onUploadError: function(id, message){
        	alert(message);
        },
        onFileTypeError: function(file){
        	alert("只能上传图片文件！");
        },
        onFileSizeError: function(file){
        	alert("文件大小错误！");
        },
        onFallbackMode: function(message){
        },
        onFileCountError: function(files){
        }
	});
});