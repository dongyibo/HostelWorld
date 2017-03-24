<%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/17
  Time: 下午3:47
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/9
  Time: 上午11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" style="background: #505D6E url(/HostelWorld/static/images/home/bg4.jpeg) no-repeat center center fixed;width: 100%">
<head>
    <jsp:include page="home-head.jsp" flush="true"></jsp:include>
    <title>pending...</title>
</head>
<body>
<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3 form-box">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h3 class="panel-title">pending...</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12 pending-panel">
                                Your hostel's id is <span style="color: red"><c:out value="${hid}"/></span>, and
                                your shop of the application will be approved by the  manager of HostelWorld,
                                the approval will be notified after the call
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
