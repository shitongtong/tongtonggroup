<%--
  Created by IntelliJ IDEA.
  User: stt
  Date: 2016/9/4
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>qqq</title>
</head>
<body>
<input type="button" onclick="toUrl()" />
<script type="text/javascript">
    function toUrl() {
        setCookie("com_cookie", "db0c0890", 10); //设置cookie
        location.href = "h.html"; //跳转到指定链接
    }

    //设置cookie
    function setCookie(name, value, expiredays) {
        var ExpireDate = new Date();
        ExpireDate.setTime(ExpireDate.getTime() + (expiredays * 60 * 1000));
        document.cookie = name + "=" + escape(value) + ((expiredays == null) ? "" : "; expires=" + ExpireDate.toGMTString());
    }

    //获取cookie
    function getCookie(name) {
        var strCookie = document.cookie;
        var arrCookie = strCookie.split(";");
        for (var i = 0; i < arrCookie.length; i++) {
            var arr = arrCookie[i].split("=");
            if (arr[0] == name) return arr[1];
        }
        return null;
    }
</script>
</body>
</html>
