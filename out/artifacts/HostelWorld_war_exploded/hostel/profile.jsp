<%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/16
  Time: 下午1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="hostel-head.jsp" flush="true"></jsp:include>
    <link href="<%=request.getContextPath()%>/static/css/plan.css" rel="stylesheet">
    <title>hostel plan</title>
</head>
<body id="page-top" class="index">
<jsp:include page="hostel.jsp" flush="true"></jsp:include>

<section id="content">
    <div class="container">

        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>hostel profile</h2>
                <hr>
            </div>
        </div>

        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        hostel information
                    </h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-1 col-lg-offset-1">
                            <div class="btn-group-vertical">
                                <button id="data_btn" type="button" class="btn btn-default active">basic data
                                </button>
                                <button id="pwd_btn" type="button" class="btn btn-default">reset password</button>
                            </div>
                        </div>
                        <div class="col-lg-10" id="info_div">
                            <form name="modifyData_form" class="form-horizontal" method="post"
                                  action="">
                                <div class="form-group">
                                    <label for="name" class="col-lg-2 control-label">name</label>

                                    <div class="col-lg-5">
                                        <input readonly="true"
                                               value="<c:out value="${hostel.hname}"/>"
                                               type="text" name="name" class="form-control input_data"
                                               id="name">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="address" class="col-lg-2 control-label">address</label>

                                    <div class="col-lg-5">
                                        <input readonly="readonly"
                                               value="<c:out value="${hostel.address}"/>"
                                               type="text" name="address" class="form-control input_data"
                                               id="address">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="phone" class="col-lg-2 control-label">contact phone</label>

                                    <div class="col-lg-5">
                                        <input value="<c:out value="${hostel.phone}"/>"
                                               type="text" name="phone"
                                               class="form-control input_data" id="phone">
                                    </div>
                                    <div class="col-lg-1">
                                        <button onclick="updateHostelPhone(<c:out value='${hostel.hid}'/>,<c:out value="${hostel.phone}"/>);" type="button"
                                                class="btn btn-info">update
                                        </button>
                                    </div>
                                    <div class="col-lg-4">
                                        <p id="phone_error" class="form-control-static text-danger"></p>
                                    </div>
                                </div>

                            </form>
                        </div>

                        <div style="display: none" class="col-lg-10" id="password_div">
                            <form name="password_form" class="form-horizontal" method="post"
                                  action="hostel-updatePassword?hid=<c:out value='${hostel.hid}'/>">

                                <div class="form-group">
                                    <label for="old_password" class="col-lg-2 control-label">old password</label>

                                    <div class="col-lg-5">
                                        <input type="password" name="oldPWD" class="form-control"
                                               id="old_password"
                                               placeholder="please input your old password">
                                    </div>
                                    <div class="col-lg-5">
                                        <p class="form-control-static text-danger"></p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="new_password" class="col-lg-2 control-label">new password</label>

                                    <div class="col-lg-5">
                                        <input type="password" name="newPWD" class="form-control"
                                               id="new_password"
                                               placeholder="please input your new password">
                                    </div>
                                    <div class="col-lg-5">
                                        <p id="new_pwd_prompt" class="form-control-static text-danger"></p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="again_password" class="col-lg-2 control-label">new again</label>

                                    <div class="col-lg-5">
                                        <input type="password" name="newAgain" class="form-control"
                                               id="again_password"
                                               placeholder="please input your new password again">
                                    </div>
                                    <div class="col-lg-5">
                                        <p id="again_pwd_prompt" class="form-control-static text-danger"></p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-lg-offset-4 col-lg-2">
                                        <button type="button" class="btn btn-primary" onclick="confirmPwd();">
                                            submit
                                        </button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<% if (request.getAttribute("fail") != null) {
    if (request.getAttribute("fail").equals("different")) {
%>
<script>
    alert("old password wrong");
</script>
<%
        }
    }
%>
<jsp:include page="hostel-tail.jsp" flush="true"></jsp:include>
<script src="<%=request.getContextPath()%>/static/js/data.js"></script>

</body>
</html>
