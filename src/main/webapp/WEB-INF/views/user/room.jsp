<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../commons/resources.jsp"></jsp:include>
	<script type="text/javascript" src="${contextPath }/js/user/room.js"></script>
	<style type="text/css">.active{font-weight: bolder;background-color: #F3F3F3;}</style>
</head>
<body>
	<jsp:include page="../commons/header.jsp"></jsp:include>
	<div id="main" class="container">
		<div class="row">
			<div id="content" class="col-lg-8 col-md-8 archive">
				<div class="panel panel-default panel-archive">
					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="media page-header panel-archive-title">
							<a class="pull-left">
								<img id="roomHeadIcoImg" src="${contextPath }/images/user_head.png" class="avatar" width="80" height="80" style="display: inline;" />
								<input type="hidden" id="userId" value="<c:out value='${user.id }'></c:out>" />
							</a>
			  				<div class="media-body text-muted">
								<h1 class="h4 media-heading user-display-name">
									<span itemprop="name">
										<a href="" rel="external"><c:out value='${user.userName }'></c:out></a>
									</span>
								</h1>
								<p class="small user-register-time"><fmt:formatDate value='${user.createdDate }' pattern='yyyy年MM月dd日 HH:mm:ss' /><span>注册</span></p>
								<p class="small user-register-time">
									<c:forEach var="userRole" items="${user.userRoleList }">
										<span style="font-weight: bolder; margin-left: 0px;">${userRole.roleName }</span>
									</c:forEach>
								</p> 
							</div>
						</div>
						<ul id="statisticsTypeUl" class="author-tab clearfix">
							<li class="active">
								<a href="javascript:;" onclick="reLoadProduction()">作品(<span id="productionCount">0</span>)</a>
							</li>
							<%-- <li>
								<a href="javascript:;">评论(<span id="commentCount">0</span>)</a>
							</li>--%>
							<li>
								<a href="javascript:;" onclick="reLoadProduction(1)">赞(<span id="praiseCount">0</span>)</a>
							</li>
							<li>
								<a href="javascript:;" onclick="reLoadProduction(2)">踩(<span id="trampleCount">0</span>)</a>
							</li>
						</ul>
						<div id="roomContent">
						</div>
						<div id="loadingArticleMsg" style="text-align: center;">
							<img alt="" src="${contextPath }/images/loading.gif">
						</div>
					</div>
				</div>
			 </div>
			<jsp:include page="../commons/right.jsp"></jsp:include>
		</div>
	</div>
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>