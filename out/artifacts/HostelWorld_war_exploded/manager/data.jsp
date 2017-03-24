<%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/18
  Time: 上午10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.nju.hostelworld.model.*" %>
<%@ page import="com.nju.hostelworld.util.Time" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../user/user-head.jsp" flush="true"></jsp:include>
    <title>manager account</title>
</head>
<body id="page-top" class="index">
<jsp:include page="manager.jsp" flush="true"></jsp:include>
<link href="<%=request.getContextPath()%>/static/css/statistic.css" rel="stylesheet">

<%
    List<Hostel> hostels = (List<Hostel>) request.getAttribute("hostelData");
    List<Trade> trades = (List<Trade>) request.getAttribute("hostelTrades");
    List<VIP> vips = (List<VIP>) request.getAttribute("vips");

    List<Reservation> vipReservations = (List<Reservation>) request.getAttribute("vipReservations");
    List<Trade> vipTrades = (List<Trade>) request.getAttribute("vipTrades");

    Double finance = (Double) request.getAttribute("finance");
%>
<section id="content">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>Statistical data</h2>
                <hr>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">data list</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-3" style="width: 19%">
                                <ol class="breadcrumb">
                                    <li><a href="manager-showHostel">Hostel</a></li>
                                    <li><a href="manager-showVIP">VIP</a></li>
                                    <li><a href="manager-showFinance">Finance</a></li>
                                </ol>
                            </div>
                        </div>

                        <%
                            if (hostels != null) {
                        %>
                        <table class="table table-condensed">
                            <caption>hostel list</caption>
                            <thead>
                            <tr>
                                <th>id</th>
                                <th>name</th>
                                <th>address</th>
                                <th>phone</th>
                                <th>button</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Hostel hostel : hostels) {
                            %>
                            <tr>
                                <td><%=hostel.getHid()%>
                                </td>
                                <td><%=hostel.getHname()%>
                                </td>
                                <td><%=hostel.getAddress()%>
                                </td>
                                <td><%=hostel.getPhone()%>
                                </td>
                                <td>
                                    <a href="manager-viewHostelData?hid=<%=hostel.getHid()%>">
                                        <button type="button" class="btn btn-info">view data</button>
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
                            if (trades != null && trades.size() != 0) {
                        %>

                        <table class="table table-condensed">
                            <caption>hostel check-in view</caption>
                            <thead>
                            <tr>
                                <th>id</th>
                                <th>user-name</th>
                                <th>user-IDCardNo.</th>
                                <th>user-gender</th>
                                <th>date</th>
                                <th>days</th>
                                <th>roomNo.</th>
                                <th>cost</th>
                                <th>pay way</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Trade trade : trades) {
                                    User user = trade.getUser();
                                    Plan plan = trade.getPlan();
                                    Room room = plan.getRoom();
                                    String gender = user.getGender().equals("男") ? "man" : "woman";
                                    String way = trade.getWay().equals("非会员卡") ? "cash" : "vip-card";
                            %>
                            <tr>
                                <td><%=trade.getTid()%>
                                </td>
                                <td><%=user.getUname()%>
                                </td>
                                <td><%=user.getIdCardNumber()%>
                                </td>
                                <td><%=gender%>
                                </td>
                                <td><%=Time.dateFormat(trade.getDate())%>
                                </td>
                                <td><%=trade.getDays()%>
                                </td>
                                <td><%=room.getRoomNumber()%>
                                </td>
                                <td><%=trade.getFee()%>
                                </td>
                                <td><%=way%>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                        <%
                            }
                            if (vips != null && vips.size() != 0) {
                        %>

                        <table class="table table-condensed">
                            <caption>VIP list</caption>
                            <thead>
                            <tr>
                                <th>id</th>
                                <th>name</th>
                                <th>IDCardNo.</th>
                                <th>gender</th>
                                <th>phone</th>
                                <th>consumption</th>
                                <th>deadline</th>
                                <th>button</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (VIP vip : vips) {
                                    User user = vip.getUser();
                                    String gender = user.getGender().equals("男") ? "man" : "woman";
                            %>
                            <tr>
                                <td><%=vip.getVid()%>
                                </td>
                                <td><%=user.getUname()%>
                                </td>
                                <td><%=user.getIdCardNumber()%>
                                </td>
                                <td><%=gender%>
                                </td>
                                <td><%=vip.getPhone()%>
                                </td>
                                <td><%=vip.getConsumption()%>
                                </td>
                                <td><%=vip.getDeadline().toLocaleString()%>
                                </td>
                                <td>
                                    <a href="manager-viewVIPData?vid=<%=vip.getVid()%>">
                                        <button type="button" class="btn btn-info">view data</button>
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
                            if (vipReservations != null && vipReservations.size() != 0) {
                        %>
                        <table class="table table-condensed">
                            <caption>reservation list</caption>
                            <thead>
                            <tr>
                                <th>id</th>
                                <th>hostel-name</th>
                                <th>hostel-address</th>
                                <th>hostel-phone</th>
                                <th>date</th>
                                <th>days</th>
                                <th>roomNo.</th>
                                <th>price/day</th>
                                <th>used</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Reservation reservation : vipReservations) {
                                    VIP vip = reservation.getVip();
                                    Plan plan = reservation.getPlan();
                                    Room room = plan.getRoom();
                                    Hostel hostel = plan.getHostel();
                                    String isUse = reservation.getIsUse().equals("true") ? "yes" : "no";
                            %>
                            <tr>
                                <td><%=reservation.getReid()%>
                                </td>
                                <td><%=hostel.getHname()%>
                                </td>
                                <td><%=hostel.getAddress()%>
                                </td>
                                <td><%=hostel.getPhone()%>
                                </td>
                                <td><%=Time.dateFormat(reservation.getReserveTime())%>
                                </td>
                                <td><%=reservation.getDays()%>
                                </td>
                                <td><%=room.getRoomNumber()%>
                                </td>
                                <td><%=plan.getPrice()%>
                                </td>
                                <td><%=isUse%>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                        <%
                            }
                            if (vipTrades != null && vipTrades.size() != 0) {
                        %>
                        <table class="table table-condensed">
                            <caption>trade list</caption>
                            <thead>
                            <tr>
                                <th>id</th>
                                <th>hostel-name</th>
                                <th>hostel-address</th>
                                <th>hostel-phone</th>
                                <th>date</th>
                                <th>days</th>
                                <th>roomNo.</th>
                                <th>cost</th>
                                <th>pay way</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Trade trade : vipTrades) {
                                    Plan plan = trade.getPlan();
                                    Room room = plan.getRoom();
                                    Hostel hostel = plan.getHostel();
                                    String way = trade.getWay().equals("非会员卡") ? "cash" : "vip-card";
                            %>
                            <tr>
                                <td><%=trade.getTid()%>
                                </td>
                                <td><%=hostel.getHname()%>
                                </td>
                                <td><%=hostel.getAddress()%>
                                </td>
                                <td><%=hostel.getPhone()%>
                                </td>
                                <td><%=Time.dateFormat(trade.getDate())%>
                                </td>
                                <td><%=trade.getDays()%>
                                </td>
                                <td><%=room.getRoomNumber()%>
                                </td>
                                <td><%=trade.getFee()%>
                                </td>
                                <td><%=way%>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>

                        <%
                            }
                            if (finance != null) {

                        %>

                        <div class="row">
                            <div class="col-lg-12" id="income_all_chart">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-lg-offset-4">
                                <h5>hostel's total income is ￥ <%=finance%></h5>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-lg-offset-4">
                                <h5>VIP pay the amount is ￥ <c:out value="${manager.income}"/></h5>
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

<jsp:include page="../user/user-tail.jsp" flush="true"></jsp:include>
<script src="<%=request.getContextPath()%>/static/js/manager.js"></script>
</body>
</html>
