<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--页面顶部 --%>
<header id="masthead" itemscope="" itemtype="" style="height: 146px;">
	<div class="container header-content">
		<div class="row">
			<div class="col-lg-8 col-md-7 col-sm-6 col-xs-12">
				<div class="header-logo">
					<a href="${contextPath }" rel="home">
						<h3 style="border: none;">画语</h3>
					</a>
				</div>
			</div>
			<div class="col-lg-4 col-md-5 col-sm-6 col-xs-12">
				<div class="header-profile">
					<ul name="_headNoLoginInfo" class="user-profile clearfix" style="display: none;">
						<li class="date">今天是 <span id="_headDateSpan"></span></li>
						<li class="login clearfix">
							<span>你好，请<a href="${contextPath }/user/login" style="display: inline; float: none;" data-toggle="modal">登录</a>！</span>
							<span><a href="${contextPath }/user/register" style="display: inline; float: none;">注册>></a></span>
						</li>
					</ul>
					<a name="_headLoginInfo" href="" class="thumbnail avatar" style="display: none;">
						<img id="_loginUserHeadImage" src="${contextPath }/images/user_head.png" class="avatar" width="54" height="54" title="" />
					</a>
					<ul name="_headLoginInfo" class="user-profile clearfix" style="display: none;">
						<li class="clearfix">
							<a id="_headLoginUserName" href="javascript:;" class="name" title=""></a>，你好！
							<a href="${contextPath }/user/loginOut">登出 »</a>
						</li>
						<li class="tabs">
							<a href="http://www.dmeng.net/author/q780c84e6f0c28cf3ca70d3eeac2a9131/?tab=comment" title="查看我的评论">评论</a>
							<a href="http://www.dmeng.net/author/q780c84e6f0c28cf3ca70d3eeac2a9131/?tab=like" title="查看我的赞">赞</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>	
	<div class="navbar navbar-default navbar-static-top" role="banner">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".header-navbar-collapse">
					<span class="sr-only">切换菜单</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
			<nav id="navbar" class="navbar-collapse header-navbar-collapse collapse" role="navigation" itemscope="" itemtype="" aria-expanded="false" style="height: 1px;">
				<ul class="nav navbar-nav">
					<li class="active">
						<a href="${contextPath }" itemprop="url">首页</a>
					</li>
					<li class="dropdown">
						<a href="${contextPath }" data-toggle="dropdown" class="dropdown-toggle" itemprop="url">
							<span class="glyphicon glyphicon-book"></span> 画 <span class="caret"></span>
						</a>
						<ul id="huayu_hua_dropdownMenu" class="dropdown-menu" role="menu">
						</ul>
					</li>
					<li>
						<a href="${contextPath }/production/publish" itemprop="url">
							<span class="glyphicon glyphicon-th-large"></span> 发表
						</a>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a>关于</a>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</header>
<%--右侧固定工具栏 --%>
<div class="btn-group-vertical" id="floatButton">
	<button type="button" class="btn btn-default" id="goTop" title="去顶部">
		<span class="glyphicon glyphicon-arrow-up"></span>
	</button>
	<button type="button" class="btn btn-default" id="refresh" title="刷新">
		<span class="glyphicon glyphicon-repeat"></span>
	</button>
</div>
<%-- 登录弹出框 --%>
<div class="modal fade" id="loginModelDiv_" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  	<div class="modal-dialog" style="width: 600px;">
    	<div class="modal-content">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="myModalLabel">登录</h4>
      		</div>
      		<div class="modal-body">
        		<form id="loginFormModel" class="form-horizontal" action="${contextPath }/user/checkLogin">
  					<div class="form-group">
    					<label for="userAccountModel" class="col-sm-2 control-label">邮箱</label>
   						<div class="col-sm-10">
     						<input name="userAccount" type="text" class="form-control" id="userAccountModel" placeholder="邮箱" data-widget="autocomplete" data-items="8" data-suggestions='["@qq.com","@126.com","@163.com","@hotmail.com","@sina.com","@139.com","@yeah.net"]' />
   						</div>
  					</div>
  					<div class="form-group">
    					<label for="userPasswordModel" class="col-sm-2 control-label">密码</label>
   						<div class="col-sm-10">
    						<input name="userPassword" type="password" class="form-control" id="userPasswordModel" placeholder="密码">
   						</div>
  					</div>
  					<div id="vcodeRowModel" class="form-group" style="display: none;">
    					<label for="vCode_Model" class="col-sm-2 control-label">验证码</label>
   						<div class="col-sm-10">
    						<input name="vCode_" style="width: 100px; display: inline; padding: 6px 12px;" type="text" class="form-control" id="vCode_Model" placeholder="验证码" maxlength="5" />
    						<img src="" class="code-img" id="vCode_imageModel" title="点击图片刷新验证码">
   						</div>
  					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<div class="checkbox">
						        <label>
						          	<input name="rememberMe" type="checkbox" value="1" /> 记住我
						        </label>
						        <label style="float: right;">
						         	<a href="javascript:;" onclick="javascript:window.open('${contextPath }/forgetPwd/page');">忘记密码？</a>
						        </label>
      						</div>
    					</div>
  					</div>
		      		<div class="modal-footer">
		        		<button type="submit" class="btn btn-primary" data-loading-text="登录中...">登录</button>
		        		<button type="button" class="btn btn-default" onclick="javascript:window.open('${contextPath }/user/register');">注册</button>
		      		</div>
				</form>
      		</div>
    	</div>
  	</div>
</div>