<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>首页</title>
	
	<script type="text/javascript" src="${contextPath }/plugin/jquery-2.1.3.min.js"></script>
	
	<link rel="stylesheet" href="${contextPath }/plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${contextPath }/plugin/jquery-upload/css/demo.css" rel="stylesheet" />
	<link rel="stylesheet" href="${contextPath }/plugin/jquery-upload/css/uploader.css" rel="stylesheet" />
	<script type="text/javascript" src="${contextPath }/plugin/jquery-upload/js/dmuploader.js"></script>
	<script type="text/javascript" src="${contextPath }/plugin/jquery-upload/js/demo-preview.js"></script>
	<script type="text/javascript" src="${contextPath }/plugin/jquery-upload/js/jquery-migrate-1.2.1.min.js"></script>
</head>
<body>
	<div id="drag-and-drop-zone" class="uploader">
  		<div>Drag &amp; Drop Images Here</div>
  		<div class="or">-or-</div>
  		<div class="browser">
    		<label>
      			<span>Click to open the file Browser</span>
      			<input type="file" name="files[]"  accept="image/*" multiple="multiple" title='Click to add Images'>
    		</label>
  		</div>
	</div>
	<img id="resultImg_" src="" />
	<div class="col-md-6">
   		<div class="panel panel-default">
       		<div class="panel-heading">
              	<h3 class="panel-title">Uploads</h3>
            </div>
            <div class="panel-body demo-panel-files" id='demo-files'>
              	<span class="demo-note">No Files have been selected/droped yet...</span>
            </div>
    	</div>
  	</div>
	
	<script type="text/javascript">
	 $('#drag-and-drop-zone').dmUploader({
	        url: '${contextPath }/file/upload/1',
	        dataType: 'json',
	        allowedTypes: 'image/*',
	        onInit: function(){
	          //$.danidemo.addLog('#demo-debug', 'default', 'Plugin initialized correctly');
	        },
	        onBeforeUpload: function(id){
	          //$.danidemo.addLog('#demo-debug', 'default', 'Starting the upload of #' + id);

	          //$.danidemo.updateFileStatus(id, 'default', 'Uploading...');
	        },
	        onNewFile: function(id, file){
	          //$.danidemo.addFile('#demo-files', id, file);

	          /*** Begins Image preview loader ***/
	          //if (typeof FileReader !== "undefined"){
	            
	            //var reader = new FileReader();

	            // Last image added
	            //var img = $('#demo-files').find('.demo-image-preview').eq(0);

	            //reader.onload = function (e) {
	              //img.attr('src', e.target.result);
	            //}

	            //reader.readAsDataURL(file);

	          //} else {
	            // Hide/Remove all Images if FileReader isn't supported
	            //$('#demo-files').find('.demo-image-preview').remove();
	          //}
	          /*** Ends Image preview loader ***/

	        },
	        onComplete: function(){
	          //$.danidemo.addLog('#demo-debug', 'default', 'All pending tranfers completed');
	        },
	        onUploadProgress: function(id, percent){
	          //var percentStr = percent + '%';

	          //$.danidemo.updateFileProgress(id, percentStr);
	        },
	        onUploadSuccess: function(id, data){
	        	$("#resultImg_").attr("src", "${contextPath }/file/read/"+data.data[0].id);
	        	
	          //$.danidemo.addLog('#demo-debug', 'success', 'Upload of file #' + id + ' completed');

	          //$.danidemo.addLog('#demo-debug', 'info', 'Server Response for file #' + id + ': ' + JSON.stringify(data));

	          //$.danidemo.updateFileStatus(id, 'success', 'Upload Complete');

	          //$.danidemo.updateFileProgress(id, '100%');
	        },
	        onUploadError: function(id, message){
	          //$.danidemo.updateFileStatus(id, 'error', message);

	          //$.danidemo.addLog('#demo-debug', 'error', 'Failed to Upload file #' + id + ': ' + message);
	        },
	        onFileTypeError: function(file){
	          //$.danidemo.addLog('#demo-debug', 'error', 'File \'' + file.name + '\' cannot be added: must be an image');
	        },
	        onFileSizeError: function(file){
	          //$.danidemo.addLog('#demo-debug', 'error', 'File \'' + file.name + '\' cannot be added: size excess limit');
	        },
	        onFallbackMode: function(message){
	          //$.danidemo.addLog('#demo-debug', 'info', 'Browser not supported(do something else here!): ' + message);
	        },
	        onFileCountError: function(files){
	        	
	        }
	      });
	</script>
</body>
</html>