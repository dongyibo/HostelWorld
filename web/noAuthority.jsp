<%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/3/1
  Time: 下午12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" style="background: #505D6E url(/HostelWorld/static/images/home/bg4.jpeg) no-repeat center center fixed;width: 100%">
<head>
    <jsp:include page="home-head.jsp" flush="true"></jsp:include>
    <title>noAuthority</title>
</head>
<body>
<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3 form-box">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h3 class="panel-title">warning</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12 pending-panel">
                                <h1>sorry,you have no authority</h1>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <a class="pending-back-home" href="index.jsp">back home</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
