<%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/10
  Time: 下午5:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.nju.hostelworld.model.*" %>
<%@ page import="com.nju.hostelworld.util.Time" %>
<html>
<head>
    <jsp:include page="hostel-head.jsp" flush="true"></jsp:include>
    <link href="<%=request.getContextPath()%>/static/css/register.css" rel="stylesheet">
    <title>hostel register</title>
</head>
<body id="page-top" class="index">
<jsp:include page="hostel.jsp" flush="true"></jsp:include>
<%
    List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservation");
    VIP vip = (VIP) request.getAttribute("loginVIP");
%>
<section id="content">
    <div class="container">

        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>Registration</h2>
                <hr>
            </div>
        </div>

        <%
            if (reservations == null) {
        %>

        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        vip
                    </h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12" id="info_div">
                            <form name="" class="form-horizontal" method="post"
                                  action="register-validateVIP">

                                <input type="hidden" name="hid" value="<c:out value="${hostel.hid}"/>">

                                <div class="form-group">
                                    <label for="vid" class="col-lg-3 control-label">id</label>

                                    <div class="col-lg-5">
                                        <input type="text" name="vid" class="form-control input_data"
                                               id="vid" placeholder="please input room number">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="password" class="col-lg-3 control-label">password</label>

                                    <div class="col-lg-5">
                                        <input type="password" name="password" class="form-control input_data"
                                               id="password"
                                               placeholder="please input price">
                                    </div>
                                </div>

                                <div class="form-group" id="check_btn_div">
                                    <div class="col-lg-offset-5 col-lg-2">
                                        <button type="submit" class="btn btn-info">submit</button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
        } else {
            int reservationSize = reservations.size();
        %>

        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        vip order form
                    </h3>
                </div>
                <div class="panel-body">
                    <%
                        if (reservationSize == 0) {
                    %>
                    <h3>
                        you don't have reservations
                    </h3>
                    <%
                    } else {
                    %>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>time</th>
                            <th>days</th>
                            <th>roomNo</th>
                            <th>button</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (Reservation reservation : reservations) {
                                Plan plan = reservation.getPlan();
                                Room room = plan.getRoom();
                                Date date = reservation.getReserveTime();
                                String time = Time.dateFormat(date);
                        %>
                        <tr>
                            <td><%=time%>
                            </td>
                            <td><%=reservation.getDays()%>
                            </td>
                            <td><%=room.getRoomNumber()%>
                            </td>
                            <td>
                                <a href="<%=request.getContextPath()%>/register-registerForReservation?reid=<%=reservation.getReid()%>">
                                    <button class="btn btn-info">generate</button>
                                </a>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                    <%
                        }
                    %>
                    <div class="form-group">
                        <div class="col-lg-offset-5 col-lg-2">
                            <a href="<%=request.getContextPath()%>/register-show">
                                <button type="button" class="btn btn-info">back</button>
                            </a>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <%

            }
        %>

        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        registration form
                    </h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12" id="registration_div">
                            <form name="register_register_form" class="form-horizontal" method="post"
                                  action="register-register">

                                <input type="hidden" name="hid" value="<c:out value="${hostel.hid}"/>">
                                <input type="hidden" name="isVIP" value="<%=vip==null?0:1%>">
                                <input type="hidden" name="uid" value="<%=vip==null?null:vip.getUser().getUid()%>">

                                <div class="form-group">
                                    <label for="vid" class="col-lg-3 control-label">name</label>

                                    <div class="col-lg-5">
                                        <input type="text" name="name" class="form-control input_data"
                                               id="name" placeholder="please input your name"
                                            <%
                                            if (vip!=null){
                                                %>
                                               value="<%=vip.getUser().getUname()%>"
                                               readonly="readonly"
                                            <%
                                            }
                                            %>
                                        >
                                    </div>
                                    <div class="col-lg-4">
                                        <p class="form-control-static text-danger" id="name_error"></p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="IdCardNo" class="col-lg-3 control-label">IdCardNo</label>

                                    <div class="col-lg-5">
                                        <input type="text" name="IdCardNo" class="form-control input_data"
                                               id="IdCardNo"
                                               placeholder="please input your IdCard number"
                                            <%
                                            if (vip!=null){
                                                %>
                                               value="<%=vip.getUser().getIdCardNumber()%>"
                                               readonly="readonly"
                                            <%
                                            }
                                            %>
                                        >
                                    </div>
                                    <div class="col-lg-4">
                                        <p class="form-control-static text-danger" id="IdCardNo_error"></p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-3 control-label">gender</label>

                                    <div class="col-lg-5">
                                        <label class="radio-inline label-color">
                                            <input type="radio" name="gender" value="男"
                                                <%
                                                if (vip!=null){
                                                    if (vip.getUser().getGender().equals("男")){
                                                        %>
                                                   checked disabled
                                                <%
                                                    }else{
                                                        %>
                                                   disabled
                                                <%
                                                    }
                                                }
                                                else {
                                                    %>
                                                   checked
                                                <%
                                                }
                                                %>
                                            >man
                                        </label>
                                        <label class="radio-inline label-color">
                                            <input type="radio" name="gender" value="女"
                                                <%
                                                if (vip!=null){
                                                    if (vip.getUser().getGender().equals("女")){
                                                        %>
                                                   checked disabled
                                                <%
                                                    }
                                                }
                                                %>
                                            >women
                                        </label>
                                    </div>
                                </div>
                                <%
                                    List<Plan> showPlans = (List<Plan>) session.getAttribute("showPlans");
                                %>
                                <div class="form-group">
                                    <label for="roomNumber" class="col-lg-3 control-label">room No.</label>

                                    <div class="col-lg-2">
                                        <select id="roomNumber" class="form-control" name="roomNo">
                                            <%
                                                for (Plan p : showPlans) {
                                            %>
                                            <option value="<%=p.getPid()%>">
                                                <%=p.getRoom().getRoomNumber()%> / ￥<%=p.getPrice()%>
                                            </option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="days" class="col-lg-3 control-label">days</label>

                                    <div class="col-lg-5">
                                        <input type="text" name="days" class="form-control input_data"
                                               id="days"
                                               placeholder="please input days">
                                    </div>
                                    <div class="col-lg-4">
                                        <p class="form-control-static text-danger" id="days_error"></p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="addition" class="col-lg-3 control-label">addition</label>

                                    <div class="col-lg-5">
                                        <textarea id="addition" name="addition" class="form-control" rows="3"
                                                  placeholder="please input additional infomation"></textarea>
                                    </div>
                                    <div class="col-lg-4">
                                        <p class="form-control-static text-danger" id="addition_error"></p>
                                    </div>
                                </div>

                                <%
                                    if (vip != null) {
                                %>
                                <div class="form-group">
                                    <label for="addition" class="col-lg-3 control-label">way</label>

                                    <div class="col-lg-5">
                                        <select class="state_select form-control" name="way">
                                            <option value="会员卡">VIP card payment</option>
                                            <option value="非会员卡">cash payment (%95)</option>
                                        </select>
                                    </div>
                                </div>
                                <%
                                    }
                                %>

                                <div class="form-group" id="">
                                    <div class="col-lg-offset-5 col-lg-2">
                                        <button onclick="submitRegister();" type="button" class="btn btn-info">submit
                                        </button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
            List<Trade> trades = (List<Trade>) session.getAttribute("trades");
        %>
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        registration list
                    </h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <%
                            for (Trade trade : trades) {
                                Plan plan = trade.getPlan();
                                User user = trade.getUser();
                                String gender = user.getGender().equals("男") ? "man" : "woman";
                                String date1 = Time.dateFormat(trade.getDate());
                                String date2 = Time.dateFormat(Time.getDateAfterDay(trade.getDate(), trade.getDays()));
                                String state = trade.getState();
                                String way = trade.getWay().equals("非会员卡") ? "cash" : "vip-card";
                        %>
                        <div class="col-lg-4">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <%
                                        if (user.getIsVIP().equals("true")) {
                                    %>
                                    <span class="badge">VIP</span>
                                    <%
                                        }
                                    %>
                                    <span
                                            class="span_prompt">name </span><%=user.getUname()%>
                                </li>
                                <li class="list-group-item"><span
                                        class="span_prompt">IdCardNo </span><%=user.getIdCardNumber()%>
                                </li>
                                <li class="list-group-item"><span class="span_prompt">gender </span><%=gender%>
                                </li>
                                <li class="list-group-item"><span
                                        class="span_prompt">roomNo </span><%=plan.getRoom().getRoomNumber()%>
                                </li>
                                <li class="list-group-item"><span class="span_prompt">arrival date </span><%=date1%>
                                </li>
                                <li class="list-group-item"><span class="span_prompt">departure date </span><%=date2%>
                                </li>
                                <li class="list-group-item"><span class="span_prompt">pay way </span><%=way%>
                                </li>
                                <li class="list-group-item"><span class="span_prompt">cost </span><%=trade.getFee()%>
                                </li>
                                <li class="list-group-item"><span
                                        class="span_prompt">addition </span><%=trade.getAddition().equals("") ? "none" : trade.getAddition()%>
                                </li>
                                <li class="list-group-item">
                                    <select onchange="selectState(<%=trade.getTid()%>, this);"
                                            class="state_select form-control" name="roomNo">
                                        <option
                                                <%
                                                    if (state.equals("入店")) {
                                                %>
                                                selected="selected"
                                                <%
                                                    }
                                                %>
                                                value="in">check in
                                        </option>
                                        <option
                                                <%
                                                    if (state.equals("离店")) {
                                                %>
                                                selected="selected"
                                                <%
                                                    }
                                                %>
                                                value="out">check out
                                        </option>
                                    </select>
                                </li>
                            </ul>
                        </div>
                        <%
                            }

                        %>
                    </div>

                </div>
            </div>
        </div>

    </div>
</section>
<% if (request.getAttribute("fail") != null && request.getAttribute("fail").equals("validateFail")) {
%>
<script>
    alert("verification failed");
</script>
<%
    }
%>

<% if (request.getAttribute("regFail") != null) {
    if (request.getAttribute("regFail").equals("regFail")) {
%>
<script>
    alert("conflict with the member's reservation time");
</script>
<%
} else if (request.getAttribute("regFail").equals("Expired")) {
%>
<script>
    alert("Expired");
</script>
<%
} else if (request.getAttribute("regFail").equals("NotYet")) {
%>
<script>
    alert("NotYet");
</script>
<%
} else if (request.getAttribute("regFail").equals("InsufficientBalance")) {
%>
<script>
    alert("insufficient balance");
</script>
<%
} else if (request.getAttribute("regFail").equals("Conflict")) {
%>
<script>
    alert("insufficient balance");
</script>
<%
    }
%>
<%
    }
%>
<jsp:include page="hostel-tail.jsp" flush="true"></jsp:include>
<script src="<%=request.getContextPath()%>/static/js/register.js"></script>
</body>
</html>
