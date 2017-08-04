<!DOCTYPE html>
<html lang="zh-cn">
<head>
<#include "/base/head_meta.ftl"/>
    <title>后台管理中心</title>  
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
  </div>
  <#--<div class="head-l"><a class="button button-little bg-green" href="" target="_blank"><span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="login.html"><span class="icon-power-off"></span> 退出登录</a> </div>-->
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>基本设置</h2>
  <ul style="display:block">
    <li><a href="/appVersion/list" target="right"><span class="icon-caret-right"></span>app版本管理</a></li>
    <#--<li><a href="subject.html" target="right"><span class="icon-caret-right"></span>banner管理</a></li>-->
    <#--<li><a href="fee.html" target="right"><span class="icon-caret-right"></span>名师推荐管理</a></li>-->
    <#--<li><a href="statistics.html" target="right"><span class="icon-caret-right"></span>教育头条</a></li>-->
  </ul>   
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><a href="/" class="icon-home"> 首页</a></li>
  <#--<li><a href="/" target="right" class="icon-home"> 首页</a></li>-->
  <#--<li><a href="##" id="a_leader_txt">网站信息</a></li>-->
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="/appVersion/list" name="right" width="100%" height="100%"></iframe>
</div>
<#--<div style="text-align:center;">
<p>来源:<a href="http://www.mycodes.net/" target="_blank">源码之家</a></p>
</div>-->
</body>
</html>