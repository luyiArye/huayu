<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="commons/resources.jsp"></jsp:include>
	<script type="text/javascript" src="${contextPath }/js/index.js"></script>
</head>
<body>
	<jsp:include page="commons/header.jsp"></jsp:include>
	<input type="hidden" id="categoryId" value="${categoryId }" />
	<div id="main" class="container">
		<div class="row">
			<div id="content" class="col-lg-8 col-md-8 archive" role="main" itemscope="" itemprop="mainContentOfPage" itemtype="">
				<div class="panel panel-default panel-archive" role="main">
					<div id="mainPanelDiv" class="panel-body" style="padding-bottom: 0px;">
						<h5 class="page-header panel-archive-title">
							<a href="/huayu">
								<span class="glyphicon glyphicon-book"></span> 画
							</a>
							<label id="navigationLable"></label>
						</h5>
					</div>
					<div id="loadingArticleMsg" style="text-align: center;">
						<img alt="" src="${contextPath }/images/loading.gif">
					</div>
				</div>
			</div>
			<jsp:include page="commons/right.jsp"></jsp:include>
		</div>
	</div>
	<jsp:include page="commons/footer.jsp"></jsp:include>
</body>
</html>