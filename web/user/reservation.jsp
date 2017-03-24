<%@ page import="com.nju.hostelworld.model.Hostel" %>
<%@ page import="com.nju.hostelworld.model.Plan" %>
<%@ page import="com.nju.hostelworld.model.Reservation" %>
<%@ page import="com.nju.hostelworld.model.Room" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nju.hostelworld.util.Time" %><%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/12
  Time: 下午5:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="user-head.jsp" flush="true"></jsp:include>
    <link href="<%=request.getContextPath()%>/static/css/plan.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/static/css/reservation.css" rel="stylesheet">
    <title>user reservation</title>
</head>
<body id="page-top" class="index">
<jsp:include page="user.jsp" flush="true"></jsp:include>
<%
    List<Reservation> reservations = (List<Reservation>) session.getAttribute("VIPReservations");
%>
<section id="content">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>reservation</h2>
                <hr>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">reservation list</h3>
                    </div>
                    <div class="panel-body">
                        <%
                            if (reservations.size() == 0) {
                        %>
                        You have no reservations at this time
                        <%
                        } else {
                        %>
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>hostel name</th>
                                <th>hostel address</th>
                                <th>hostel phone</th>
                                <th>time</th>
                                <th>days</th>
                                <th>roomNo.</th>
                                <th>price</th>
                                <th>button</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Reservation reservation : reservations) {
                                    Plan plan = reservation.getPlan();
                                    Hostel hostel = plan.getHostel();
                                    Room room = plan.getRoom();
                                    String date = Time.dateFormat(reservation.getReserveTime());
                                    String isUse = reservation.getIsUse();
                            %>
                            <tr>
                                <td><%=hostel.getHname()%>
                                </td>
                                <td><%=hostel.getAddress()%>
                                </td>
                                <td><%=hostel.getPhone()%>
                                </td>
                                <td><%=date%>
                                </td>
                                <td><%=reservation.getDays()%>
                                </td>
                                <td><%=room.getRoomNumber()%>
                                </td>
                                <td><%=plan.getPrice()%>
                                </td>
                                <td>
                                    <%
                                        if (isUse.equals("false")) {
                                    %>
                                    <a href="<%=request.getContextPath()%>/user-cancelReservation?reid=<%=reservation.getReid()%>">
                                        <button class="btn btn-info">cancel</button>
                                    </a>
                                    <%
                                        }
                                    %>
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
                    </div>
                </div>
            </div>
        </div>

        <%
            List<Plan> hostelPlans = (List<Plan>) session.getAttribute("hostelPlans");
            if (hostelPlans == null) {
        %>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">hostel list</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <%
                                List<Hostel> hostels = (List<Hostel>) session.getAttribute("hostels");
                                for (int i = 0; i < hostels.size(); i++) {
                                    int k = i % 6 + 1;
                                    Hostel hostel = hostels.get(i);
                            %>
                            <div class="col-lg-4 content-item">
                                <div class="row">
                                    <div class="col-lg-10 col-lg-offset-1">
                                        <a href="javascript:void(0)" class="more_detail content-link" data-
                                           toggle="modal">
                                            <div class="caption">
                                                <div class="caption-content">
                                                    <i class="fa fa-hand-o-right fa-3x"></i>
                                                </div>
                                            </div>
                                            <div>
                                                <img class="event_img img-rounded"
                                                     src="<%=request.getContextPath()%>/static/images/user/hostel<%=k%>.png">
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col-lg-10 col-lg-offset-1">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <h4><%=hostel.getHname()%>
                                                </h4>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-1 room_icon">
                                                <img width="16" height="16"
                                                     src="<%=request.getContextPath()%>/static/images/user/address.png">
                                            </div>
                                            <div class="col-lg-10 address_div">
                                                <span class="activity_address"><%=hostel.getAddress()%></span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-1 price_icon">
                                                <img width="16" height="16"
                                                     src="<%=request.getContextPath()%>/static/images/user/phone.png">
                                            </div>
                                            <div class="col-lg-10 time_div">
                                                <span class="activity_time"><%=hostel.getPhone()%></span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <h5>
                                                </h5>
                                            </div>

                                            <div class="col-lg-2 col-lg-offset-5">
                                                <a href="user-showPlan?hid=<%=hostel.getHid()%>">
                                                    <button type="button" class="btn btn-info">choose</button>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
        }
//            显示客栈对应的计划
        else {
        %>
        <div id="reservation_form" class="row" style="display: none;">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">reservation form</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">

                            <form name="reservation_form" class="form-horizontal" method="post"
                                  action="user-book">

                                <input type="hidden" name="pid" id="pid">

                                <div class="form-group">
                                    <label for="roomNo" class="col-lg-3 control-label">roomNo.</label>

                                    <div class="col-lg-5">
                                        <input type="text" name="roomNo" class="form-control input_data"
                                               id="roomNo" readonly="readonly">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="price" class="col-lg-3 control-label">price</label>

                                    <div class="col-lg-5">
                                        <input type="text" name="price" class="form-control input_data"
                                               id="price" placeholder="please input room number" readonly="readonly">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="roomNo" class="col-lg-3 control-label">time</label>
                                    <div class="col-lg-5">
                                        <div class="row">

                                            <div class="col-lg-1" style="width: 24%">
                                                <select name="year" id="year" class="form-control">
                                                    <option selected="selected">2017</option>
                                                    <option>2018</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-1 time_detail" style="width:2%">year</div>

                                            <div class="col-lg-1" style="width: 20%">
                                                <select name="month" id="month" class="form-control">
                                                    <%
                                                        for (int i = 1; i < 13; i++) {
                                                    %>
                                                    <option
                                                                <%
                                                    if (i==Time.getMonth()){
                                                        %>selected="selected"<%
                                                        }
                                                    %>
                                                    ><%=i%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-1 time_detail_month" style="width:2%">month</div>

                                            <div class="col-lg-1" style="width: 20%">
                                                <select name="day" id="day" class="form-control">
                                                    <%
                                                        for (int i = 1; i <= 31; i++) {
                                                    %>
                                                    <option
                                                            <%
                                                                if (i == Time.getday()) {
                                                            %>
                                                            selected="selected"
                                                            <%
                                                                }
                                                            %>
                                                    ><%=i%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-lg-1 time_detail" style="width:2%">day</div>
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <p class="form-control-static text-danger" id="date_error"></p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="days" class="col-lg-3 control-label">days</label>

                                    <div class="col-lg-5">
                                        <input type="text" name="days" class="form-control input_data"
                                               id="days" placeholder="please input the number of nights">
                                    </div>
                                    <div class="col-lg-4">
                                        <p class="form-control-static text-danger" id="days_error"></p>
                                    </div>
                                </div>

                                <div class="form-group" id="reservation_btn">
                                    <div class="col-lg-offset-5 col-lg-2">
                                        <button onclick="submitReservation();" type="button" class="btn btn-info">
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


        <div id="hostelPlan_div" class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">hostel list</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">

                            <%
                                for (int j = 0; j < hostelPlans.size(); j++) {
                                    Plan hostelPlan = hostelPlans.get(j);
                                    Room room = hostelPlan.getRoom();
                                    int k = j % 6 + 1;
                            %>

                            <div class="col-lg-4 content-item">
                                <div class="row">
                                    <div class="col-lg-10 col-lg-offset-1">
                                        <a href="javascript:void(0)" class="more_detail content-link" data-
                                           toggle="modal">
                                            <div class="caption">
                                                <div class="caption-content">
                                                    <i class="fa fa-hand-o-right fa-3x"></i>
                                                </div>
                                            </div>
                                            <div>
                                                <img class="event_img img-rounded"
                                                     src="<%=request.getContextPath()%>/static/images/plan/room<%=k%>.png">
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col-lg-10 col-lg-offset-1">
                                        <div class="row">
                                            <div class="col-lg-1 room_icon">
                                                <img width="16" height="16"
                                                     src="<%=request.getContextPath()%>/static/images/plan/room.png">
                                            </div>
                                            <div class="col-lg-10 address_div">
                                                <span class="activity_address"><%=room.getRoomNumber()%></span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-1 price_icon">
                                                <img width="16" height="16"
                                                     src="<%=request.getContextPath()%>/static/images/plan/price.png">
                                            </div>
                                            <div class="col-lg-10 time_div">
                                                <span class="activity_time"><%=hostelPlan.getPrice()%></span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-2 col-lg-offset-9">
                                                <button type="button" class="btn btn-info"
                                                        onclick="book(this);">book
                                                </button>
                                            </div>
                                        </div>
                                        <input type="hidden" value="<%=hostelPlan.getPid()%>">
                                        <input type="hidden" value="<%=hostelPlan.getPrice()%>">
                                        <input type="hidden" value="<%=room.getRoomNumber()%>">
                                    </div>
                                </div>
                            </div>
                            <%

                                }

                            %>
                        </div>
                        <div class="row">
                            <div class="col-lg-2 col-lg-offset-5">
                                <a href="<%=request.getContextPath()%>/user-showReservation">
                                    <button type="button" class="btn btn-warning">back
                                    </button>
                                </a>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>

    </div>
</section>
<% if (request.getAttribute("fail") != null) {
    if (request.getAttribute("fail").equals("conflict")) {
%>
<script>
    alert("conflict with other times");
</script>
<%
    }
    if (request.getAttribute("fail").equals("frozen")) {
%>
<script>
    alert("your account has been frozen, please first renew");
</script>
<%
        }
    }
%>
<jsp:include page="user-tail.jsp" flush="true"></jsp:include>
<script src="<%=request.getContextPath()%>/static/js/reservation.js"></script>
</body>
</html>
