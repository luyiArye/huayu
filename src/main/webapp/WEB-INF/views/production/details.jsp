<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../commons/resources.jsp"></jsp:include>
	<script type="text/javascript" src="${contextPath }/plugin/pagination/jquery.twbsPagination.min.js"></script>
	<script type="text/javascript" src="${contextPath }/js/production/details.js"></script>
</head>
<body>
	<jsp:include page="../commons/header.jsp"></jsp:include>
	<div id="main" class="container">
		<div class="row">
			<article id="content" class="col-lg-8 col-md-8 single" data-post-id="2230" role="article" itemscope="">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="entry-header page-header">
							<h1 class="entry-title h3" itemprop="name">
								<c:out value="${production.title }"></c:out>
							</h1>
							<div class="entry-meta">
								<div class="entry-set-font">
									<span id="set-font-small" class="disabled">A<sup>-</sup></span>
									<span id="set-font-big" class="">A<sup>+</sup></span>
								</div>
								<a href="${contextPath }/user/room/<c:out value='${production.createdByUser.id }'></c:out>" itemprop="author" title="<c:out value='${production.createdByUser.userAccount }'></c:out>">
									<span class="glyphicon glyphicon-user"></span>
									<c:out value="${production.createdByUser.userName }"></c:out>
								</a>
								<span class="glyphicon glyphicon-calendar"></span>
								<time class="entry-date" title="发布于 <fmt:formatDate value='${production.createdDate }' pattern='yyyy年MM月dd日 HH:mm:ss' />">
									<fmt:formatDate value="${production.createdDate }" pattern="yyyy年MM月dd日" />
								</time>
								<a href="${contextPath }/production/details/<c:out value='${production.id }'></c:out>#comments">
									<span class="glyphicon glyphicon-comment"></span>
									<span itemprop="interactionCount" id="totalCommentCount">0</span>
								</a>
								<span class="glyphicon glyphicon-eye-open"></span>
								<span data-num-views="true" name="productionOperationLabel" operationType="3"><c:out value="${production.browseCount }"></c:out></span> 次浏览
							</div>
						</div>
						<div class="entry-content" itemprop="articleBody" data-no-instant="" style="font-size: 14px; line-height: 24px;">
							<p>
								<c:out value="${production.content }"></c:out>
							</p>
						</div>
						<div class="entry-content" itemprop="articleBody" data-no-instant="" style="font-size: 14px; line-height: 24px;">
							<p>
								<div id="productImageDiv" class="entry-content productListCarousel">
								</div>
								<script type="text/javascript">
									productId=<c:out value="${production.id }"></c:out>;
									$(function(){
										loadProductionImages(<c:out value="${production.id }"></c:out>, "productImageDiv");
									});
								</script>
							</p>
						</div>
					</div>
					<div class="entry-footer clearfix" role="toolbar">
						<%-- <div class="bd-share">
							<div class="bdsharebuttonbox bdshare-button-style1-24">
								<a class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
								<a class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
								<a class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
								<a class="bds_more" data-cmd="more"></a>
							</div>
							<script>
								var share_excerpt = '【多梦网络2015新年计划通告】嗯，童鞋们新年好！春节过去了，新的一年开始了，让我们继续WordPress探索之旅，继续学习WordPress主题制作以及WordPress定制开发，做有意义的东西。以下是多梦网络2015新年计划。DMENG...';var share_pic = '';var share_url = 'http://www.dmeng.net/happy-2015.html?fid=0';var wkey = '2713487958';var qkey = '101138431';window._bd_share_main = false;window._bd_share_config = { common : { bdText : share_excerpt,bdDesc : share_excerpt,bdUrl : share_url, bdPic : share_pic, bdSnsKey : {'tsina':wkey, 'tqq':qkey,'qzone':qkey} }, share : [{ 'bdStyle' : 1, 'bdSize' : 24 }] };
							</script>
						</div>--%>
						<div id="operationBar_" class="btn-group vote-group" data-votes-up="<c:out value='${production.praiseCount }'></c:out>" data-votes-down="<c:out value='${production.trampleCount }'></c:out>">
							<a href="javascript:;" onclick="praise()" class="btn btn-default up">
								<span class="glyphicon glyphicon-thumbs-up"></span>
								<span class="votes" name="poCountSpan_" operationType="praiseCount"><c:out value="${production.praiseCount }"></c:out></span>
							</a>
							<a href="javascript:;" onclick="trample()" class="btn btn-default down">
								<span class="glyphicon glyphicon-thumbs-down"></span>
								<span class="votes" name="poCountSpan_" operationType="trampleCount"><c:out value="${production.trampleCount }"></c:out></span>
							</a>
						</div>
						<span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb">
							<a href="http://www.dmeng.net/" title="多梦网络" itemprop="url">
								<span itemprop="title">多梦网络</span>
							</a>
						</span> 
						› 
						<span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb">
							<a href="http://www.dmeng.net/category/news/" title="网站动态" itemprop="url">
								<span itemprop="title">网站动态</span>
							</a>
						</span> 
						› 
						<span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb">
							<a href="http://www.dmeng.net/happy-2015.html" title="多梦网络2015新年计划通告" itemprop="url">
								<span itemprop="title">多梦网络2015新年计划通告</span>
							</a>
						</span>
					</div>
					<div class="panel-footer profile clearfix" itemprop="publisher" itemscope="" itemtype="http://schema.org/Organization">
						<a class="author-avatar" href="${contextPath }/user/room/<c:out value='${production.createdByUser.id }'></c:out>">
							<img src="${contextPath }/user/headIco/1/${production.createdByUser.id }" width="50" height="50" style="display: inline;">
						</a>
						<div class="author-description">
							<div class="author-name"> 
								作者 : 
								<span itemprop="name">
									<a href="${contextPath }/user/room/<c:out value='${production.createdByUser.id }'></c:out>"><c:out value="${production.createdByUser.userName }"></c:out></a>
								</span>
							</div>
						</div>
					</div>
				</div>
				<!-- <nav class="pager" role="navigation" itemscope="" itemtype="http://schema.org/SiteNavigationElement">
					<li class="previous">
						<a href="http://www.dmeng.net/recruit-new-homeschooling-users.html" rel="prev">
							<span class="text-muted">上一篇：</span>
							<span itemprop="name">多梦自学实验班成员招募</span>
						</a>
					</li>
					<li class="next">
						<a href="http://www.dmeng.net/recruit-new-homeschooling-users.html" rel="prev">
							<span class="text-muted">下一篇：</span>
							<span itemprop="name">多梦自学实验班成员招募</span>
						</a>
					</li>
				</nav> -->
				
				<div class="panel panel-default" id="comments" data-no-instant="">
					<div class="list-group" id="respond">
						<h4 class="list-group-item">
							发表评论 
							<small id="cancel-comment-reply">
								<a rel="nofollow" id="cancel-comment-reply-link" href="/happy-2015.html#respond" style="display:none;">点击这里取消回复。</a>
							</small>
						</h4>
						<p id="noLoginCommentPmpt" class="list-group-item" style="display: none;">
							要发表评论，您必须先
							<a id="commentLoginLink" href="javascript:;">登录</a>。
						</p>
						<form action="${contextPath }/pc/save" method="post" id="commentForm" class="form-horizontal list-group-item" role="form" style="display: none;">
							<div id="comment-user" data-user-id="2532">
								<p class="logged-in-help">
									以
									<a id="commentLoginUser" href=""></a>
									登录。
									<a href="${contextPath }/user/loginOut">登出 »</a>
								</p>
							</div>
							<div class="form-group" style="margin: 0px;">
								<input type="hidden" name="productionId" value="${production.id }" />
								<textarea class="form-control" rows="3" name="content" id="commentContent" required=""></textarea>
							</div>
							<div id="comment-action" class="btn-toolbar clearfix" role="toolbar">
								<div class="btn-group">
									<span id="facesButton" class="btn btn-default look-toggle" onclick="triggerFaces(event)">
										<span class="glyphicon glyphicon-eye-open"></span> 
										表情
									</span>
									<div class="popover fade bottom in" role="tooltip" id="faceImagesDiv" style="top: 38px; left: -51.421875px; display: none;">
										<div class="arrow" style="left: 38.8646288209607%;"></div>
										<div class="popover-content" id="looks" style="width: 220px;">
											<ul id="faceImagesUl" class="clearfix" targetText="commentContent">
											</ul>
										</div>
									</div>
									<div class="btn-group">
										<button class="btn btn-default" name="submit" type="submit" id="commentsubmit" data-loading-text="提交中...">发表评论</button>
									</div>
								</div>
							</div>
						</form>
					</div>
					<ul class="list-group commentlist">
						<li class="list-group-item respond-title">
							<span id="commentTotalCount"></span>
							则回应给
							“<c:out value="${production.title }"></c:out>”
						</li>
						<ul id="productComments" style="padding: 0px;">
							<%-- <li class="comment byuser even thread-even depth-1 list-group-item top" id="comment-7280">
								<a class="comment-author" href="" data-instant="">
									<img src="" class="avatar" width="50" height="50" style="display: block;">
								</a>
								<div class="comment-body">
									<cite class="fn">
										<a href="" target="_blank">xinyang</a>
									</cite>
									<div class="comment-content">
										<p>期待新年变化<img class="look" src="http://s.duomeng.me/look/qinqin.gif" data-original="http://s.duomeng.me/look/qinqin.gif" width="22" height="22" style="display: inline;"></p>
									</div>
									<div class="comment-meta">
										<time datetime="2015-03-06" title="2015年3月6日 12:57">2015-03-06 12:57</time><a rel="nofollow" class="comment-reply-login" href="http://www.dmeng.net/wp-login.php?redirect_to=http%3A%2F%2Fwww.dmeng.net%2Fhappy-2015.html"><span class="glyphicon glyphicon-transfer"></span>登录以回复</a><a class="pm" href="http://www.dmeng.net/author/q9b78c83d14e6583105dd484bf944eda4/?tab=message" title="私信" target="_blank"><span class="glyphicon glyphicon-share-alt"></span>私信</a>	
									</div>
								</div>
							</li> --%>
							<%-- <li class="comment byuser comment-author-renkangtai even thread-even depth-1 list-group-item parent top" id="comment-7228" data-comment-id="7228">
								<a class="comment-author" href="http://www.dmeng.net/author/renkangtai/" data-instant=""><img src="http://q.qlogo.cn/qqapp/101138431/33A5EECAF18EF28DF96C8F5DC690DD8E/100" data-original="http://q.qlogo.cn/qqapp/101138431/33A5EECAF18EF28DF96C8F5DC690DD8E/100" class="avatar" width="50" height="50" style="display: block;"></a>
								<div class="comment-body">
									<cite class="fn">
										<a href="http://www.epmap.cn" rel="external nofollow" target="_blank">任Uncle</a>
									</cite>
									<div class="comment-content">
										<p>表示支持，希望手机版能早点出来。我的网站已接入微信，但浏览时用的其他的手机主题，很不方便<img class="look" src="http://s.duomeng.me/look/hehe.gif" data-original="http://s.duomeng.me/look/hehe.gif" width="22" height="22" style="display: inline;"></p>
									</div>
									<div class="comment-meta">
										<time datetime="2015-03-03" title="2015年3月3日 13:23">2015-03-03 13:23</time><a rel="nofollow" class="comment-reply-login" href="http://www.dmeng.net/wp-login.php?redirect_to=http%3A%2F%2Fwww.dmeng.net%2Fhappy-2015.html"><span class="glyphicon glyphicon-transfer"></span>登录以回复</a><a class="pm" href="http://www.dmeng.net/author/renkangtai/?tab=message" title="私信" target="_blank"><span class="glyphicon glyphicon-share-alt"></span>私信</a>
									</div>
								</div>
							</li> --%>
							<%-- <ul class="children">
								<li class="comment byuser comment-author-q87b3f28009ac9f7a8a520b52ebb62e4a odd alt depth-2 list-group-item parent" id="comment-7237" data-comment-id="7237">
									<a class="comment-author" href="http://www.dmeng.net/author/q87b3f28009ac9f7a8a520b52ebb62e4a/" data-instant=""><img src="http://q.qlogo.cn/qqapp/101138431/87B3F28009AC9F7A8A520B52EBB62E4A/100" data-original="http://q.qlogo.cn/qqapp/101138431/87B3F28009AC9F7A8A520B52EBB62E4A/100" class="avatar" width="30" height="30" style="display: block;"></a>
									<div class="comment-body">
										<div class="comment-content">
											<p>自适应主题...</p>
										</div>
										<div class="comment-meta">
											<cite class="fn"><a href="http://icun.me" rel="external nofollow" target="_blank">小二</a></cite><time datetime="2015-03-03" title="2015年3月3日 17:46">2015-03-03 17:46</time><a rel="nofollow" class="comment-reply-login" href="http://www.dmeng.net/wp-login.php?redirect_to=http%3A%2F%2Fwww.dmeng.net%2Fhappy-2015.html"><span class="glyphicon glyphicon-transfer"></span>登录以回复</a><a class="pm" href="http://www.dmeng.net/author/q87b3f28009ac9f7a8a520b52ebb62e4a/?tab=message" title="私信" target="_blank"><span class="glyphicon glyphicon-share-alt"></span>私信</a>
										</div>
									</div>
								</li>
								<ul class="children">
									<li class="comment byuser comment-author-renkangtai even depth-3 list-group-item" id="comment-7238" data-comment-id="7238">
										<a class="comment-author" href="http://www.dmeng.net/author/renkangtai/" data-instant=""><img src="http://s.duomeng.me/grey.png" data-original="http://q.qlogo.cn/qqapp/101138431/33A5EECAF18EF28DF96C8F5DC690DD8E/100" class="avatar" width="30" height="30"></a>
										<div class="comment-body">
											<div class="comment-content">
												<p>自适应总是不完美的</p>
											</div>
											<div class="comment-meta">
												<cite class="fn"><a href="http://www.epmap.cn" rel="external nofollow" target="_blank">任Uncle</a></cite>
												<time datetime="2015-03-03" title="2015年3月3日 18:54">2015-03-03 18:54</time>
												<a rel="nofollow" class="comment-reply-login" href="http://www.dmeng.net/wp-login.php?redirect_to=http%3A%2F%2Fwww.dmeng.net%2Fhappy-2015.html"><span class="glyphicon glyphicon-transfer"></span>登录以回复</a>
												<a class="pm" href="http://www.dmeng.net/author/renkangtai/?tab=message" title="私信" target="_blank"><span class="glyphicon glyphicon-share-alt"></span>私信</a>
											</div>
										</div>
									</li>
								</ul>
							</ul> --%>
						</ul>
						<li class="list-group-item text-center">
							<input type="hidden" id="pcTotalCount" value="0" />
							<ul id="commentPageation"></ul>
						</li>
					</ul>
				</div>
			</article>
			<jsp:include page="../commons/right.jsp"></jsp:include>
		</div>
	</div>
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>