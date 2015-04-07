<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../commons/resources.jsp"></jsp:include>
	<link rel="stylesheet" href="${contextPath }/plugin/jquery-upload/css/uploader.css" rel="stylesheet" />
	<script type="text/javascript" src="${contextPath }/plugin/jquery-upload/js/dmuploader.js"></script>
	<%-- <script type="text/javascript" src="${contextPath }/plugin/jquery-upload/js/demo-preview.js"></script> --%>
	<%-- <script type="text/javascript" src="${contextPath }/plugin/jquery-upload/js/jquery-migrate-1.2.1.min.js"></script --%>
	<script type="text/javascript" src="${contextPath }/plugin/jquery-ui/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${contextPath }/js/production/publish.js"></script>
</head>
<body>
	<jsp:include page="../commons/header.jsp"></jsp:include>
	<div id="main" class="container">
   		<div class="modal-header">
       		<h4 class="modal-title" id="myModalLabel">发表作品</h4>
   		</div>
   		<form id="publishForm" name="publishForm" method="post" class="form-horizontal" action="${contextPath }/production/save">
      		<div class="modal-body">
	      		<div class="form-group">
					<label for="title" class="col-sm-2 control-label">标题</label>
					<div class="col-sm-10">
						<input id="title" name="title" type="text" class="form-control" placeholder="标题" />
					</div>
				</div>
				<div class="form-group">
					<label for="categoryIdSel" class="col-sm-2 control-label">板块</label>
					<div class="col-sm-10">
						<select id="categoryIdSel" name="categoryId" class="form-control">
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="content" class="col-sm-2 control-label">描述</label>
					<div class="col-sm-10">
						<textarea id="content" name="content" class="form-control" rows="3" placeholder="描述"></textarea>
					</div>
				</div>
				<div class="form-group">
					<div id="huayuImageDragUploadZone" class="uploader">
				  		<div>将画语作品拖拽到此处</div>
				  		<div class="or">-或者-</div>
				  		<div class="browser">
				    		<label>
				      			<span>点击选择画语作品</span>
				      			<input type="file" name="files[]"  accept="image/*" multiple="multiple" title='点击选择画语作品'>
				    		</label>
				  		</div>
					</div>
				</div>
				<div class="form-group">
					<div class="panel panel-default">
		            	<div class="panel-heading">
		              		<h3 class="panel-title">作品上传列表</h3>
		            	</div>
			            <div class="panel-body demo-panel-files" id="huayuImageFiles" file-counter="2" style="padding: 10px;">
			            	<ul class="sortableUl" id="huayuImageFilesUl">
			            	</ul>
			          	</div>
					</div>
				</div>
				<div id="bottomError" class="alert alert-danger" role="alert" style="display: none;">
				  	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				  	<span class="sr-only">Error:</span>
				  	<label id="bottomErrorLabel"></label>
				</div>
				<div class="modal-footer">
	        		<button type="submit" class="btn btn-primary" data-loading-text="提交中...">提交</button>
	      		</div>
      		</div>
      	</form>
	</div>
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>