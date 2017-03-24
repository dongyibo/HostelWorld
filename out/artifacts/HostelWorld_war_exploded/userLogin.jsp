<%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/8
  Time: 上午9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"
      style="background: #505D6E url(/HostelWorld/static/images/home/bg4.jpeg) no-repeat center center fixed;width: 100%">
<head>
    <jsp:include page="home-head.jsp" flush="true"></jsp:include>
    <title>user login</title>
</head>
<body>

<section id="login">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3 form-box">
                <div class="form-top" style="background: #f8f7e2">
                    <div class="form-top-left">
                        <h2>Hostel World</h2>
                        <h4 style="color: #323232">please input your id & password ~</h4>
                    </div>
                    <div class="form-top-right">
                        <img height="80" width="80" src="static/images/home/key.png">
                    </div>
                </div>
                <div class="form-bottom" style="background-image: url(static/images/home/panel3.png)">
                    <form role="form" action="user-login" method="post" class="login-form">
                        <div class="form-group">
                            <label class="sr-only" for="login-username">Username</label>
                            <input type="text" name="vid" placeholder="id"
                                   class="form-username form-control" id="login-username">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="login-password">Password</label>
                            <input type="password" name="password" placeholder="password"
                                   class="form-password form-control" id="login-password">
                        </div>
                        <button type="submit" class="btn">login</button>
                        <div class="form-group">
                            <div class="col-lg-12" style="margin-top: 10px">
                                <a style="color: gray" href="userReg.jsp">Do not have an account? <span
                                        style="font-weight: 800;color: red">register</span> now!</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <% if (request.getAttribute("login") != null && request.getAttribute("login").equals("fail")) {
    %>
    <script>
        alert("login failed");
    </script>
    <%
        }
    %>
</section>
</body>
</html>
