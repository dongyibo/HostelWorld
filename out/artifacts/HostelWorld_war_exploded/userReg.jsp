<%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/8
  Time: 下午1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" style="background: #505D6E url(/HostelWorld/static/images/home/bg4.jpeg) no-repeat center center fixed;width: 100%">
<head>
    <jsp:include page="home-head.jsp" flush="true"></jsp:include>
    <title>user register</title>
</head>
<body>
<section id="register">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3 form-box">
                <div class="form-top" style="background: #f8f8f8">
                    <div id="head-text" class="form-top-left">
                        <h2>Welcome to HostelWorld！</h2>
                        <h4 style="color: #323232">You'll pay ￥1000 to activate the VIP for 1 year</h4>
                    </div>
                    <div id="error-panel" class="alert alert-danger form-top-left" style="margin-top: 10px">
                        <ul id="error-list">
                        </ul>
                    </div>
                    <div class="form-top-right">
                        <img height="80" width="80" src="static/images/home/user.png">
                    </div>
                </div>
                <div class="form-bottom" style="background-image: url(static/images/home/panel.jpg)">
                    <form name="register_form" role="form" action="user-reg" method="post"
                          class="form-horizontal">
                        <p id="username_repeat" class="form-control-static text-danger"></p>
                        <div class="form-group">
                            <label for="form-name" class="col-lg-2 control-label label-color">name</label>
                            <div class="col-lg-10">
                                <input value="" type="text" name="name"
                                       placeholder="please input your name" class="form-username form-control" id="form-name">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-lg-2 label-color" for="form-password">password</label>
                            <div class="col-lg-10">
                                <input value="" type="password" name="password"
                                       placeholder="please input your password (6~20)" class="form-password form-control"
                                       id="form-password">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-lg-2 label-color" for="form-phone">phone</label>
                            <div class="col-lg-10">
                                <input value="" type="text" name="phone"
                                       placeholder="please input your phone number" class="form-phone form-control" id="form-phone">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-lg-2 label-color" for="form-idCardNo">IdCardNo</label>
                            <div class="col-lg-10">
                                <input value="" type="text" name="idCardNo"
                                       placeholder="please input your phone number" class="form-phone form-control" id="form-idCardNo">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-lg-2 label-color">gender</label>
                            <div class="col-lg-10">
                                <label class="radio-inline label-color">
                                    <input type="radio" name="gender" value="男" checked="checked">man
                                </label>
                                <label class="radio-inline label-color">
                                    <input type="radio" name="gender" value="女">women
                                </label>
                            </div>
                        </div>
                        <button onclick="submitRegister();" type="button" class="btn">register</button>
                        <div class="col-lg-12" style="margin-top: 10px">
                            <a style="color: black;font-weight: 800" href="index.jsp">back home</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="home-tail.jsp" flush="true"></jsp:include>
</body>
</html>
