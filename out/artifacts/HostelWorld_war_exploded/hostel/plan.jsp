<%@ page import="com.nju.hostelworld.model.Plan" %>
<%@ page import="com.nju.hostelworld.model.Room" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nju.hostelworld.model.VO.ReservedPlan" %><%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/9
  Time: 下午2:11
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
<%
    List<ReservedPlan> plans = (List<ReservedPlan>) session.getAttribute("plans");
    int planSize = plans.size();
%>
<section id="content">
    <div class="container">

        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>hostel plan</h2>
                <hr>
            </div>
        </div>

        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        Plan release
                    </h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12" id="info_div">
                            <form name="release_plan_form" class="form-horizontal" method="post"
                                  action="plan-release">

                                <input type="hidden" name="hostel.hid" value="<c:out value="${hostel.hid}"/>">

                                <div class="form-group">
                                    <label for="roomNumber" class="col-lg-3 control-label">room No.</label>

                                    <div class="col-lg-5">
                                        <input type="text" name="roomNumber" class="form-control input_data"
                                               id="roomNumber" placeholder="please input room number">
                                    </div>
                                    <div class="col-lg-4">
                                        <p class="form-control-static text-danger" id="roomNumber_error"></p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="price" class="col-lg-3 control-label">price</label>

                                    <div class="col-lg-5">
                                        <input type="text" name="price" class="form-control input_data" id="price"
                                               placeholder="please input price">
                                    </div>
                                    <div class="col-lg-4">
                                        <p class="form-control-static text-danger" id="price_error"></p>
                                    </div>
                                </div>

                                <div class="form-group" id="check_btn_div">
                                    <div class="col-lg-offset-5 col-lg-2">
                                        <button id="release_btn" type="button" class="btn btn-info"
                                                onclick="releasePlan();">release
                                        </button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        plan list
                    </h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <%
                            for (int i = 0; i < planSize; i++) {
                                Plan plan = plans.get(i).getPlan();
                                Room room = plan.getRoom();
                                int k = i % 6 + 1;
                                String reserved = plans.get(i).isReserved() ? "reserved" : "unreserved";
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
                                            <span class="activity_time"><%=plan.getPrice()%></span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4">
                                            <h5><%=reserved%>
                                            </h5>
                                        </div>
                                        <%
                                            if (!plans.get(i).isReserved()) {
                                        %>

                                        <div class="col-lg-2 col-lg-offset-5">
                                            <button type="button" class="btn btn-info"
                                                    onclick="deletePlan(<%=plan.getPid()%> , <c:out value="${hostel.hid}"/>);">delete
                                            </button>
                                        </div>
                                        <%
                                            }
                                        %>
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
</section>
<% if (request.getAttribute("fail") != null && request.getAttribute("fail").equals("repeat")) {
%>
<script>
    alert("the room number repeats");
</script>
<%
    }
%>
<jsp:include page="hostel-tail.jsp" flush="true"></jsp:include>
<script src="<%=request.getContextPath()%>/static/js/plan.js"></script>
</body>
</html>
