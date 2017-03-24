<%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/17
  Time: 下午2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.nju.hostelworld.util.Time" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nju.hostelworld.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="hostel-head.jsp" flush="true"></jsp:include>
    <link href="<%=request.getContextPath()%>/static/css/statistic.css" rel="stylesheet">
    <title>hostel data</title>
</head>
<body id="page-top" class="index">
<jsp:include page="hostel.jsp" flush="true"></jsp:include>
<%
    List<Reservation> reservations = (List<Reservation>) session.getAttribute("allVIPReservations");
    List<Trade> trades = (List<Trade>) session.getAttribute("allTrades");
%>
<script type="text/javascript">
    window.hid =<%=((Hostel)session.getAttribute("hostel")).getHid()%>;
</script>
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
                        <h3 class="panel-title">reservation list</h3>
                    </div>
                    <div class="panel-body">
                        <%
                            if (reservations == null || reservations.size() == 0) {
                        %>
                        no reservations
                        <%
                        } else {
                        %>

                        <table class="table table-condensed">
                            <thead>
                            <tr>
                                <th>id</th>
                                <th>vip-name</th>
                                <th>vip-IDCardNo.</th>
                                <th>vip-gender</th>
                                <th>vip-phone</th>
                                <th>date</th>
                                <th>days</th>
                                <th>roomNo.</th>
                                <th>price/day</th>
                                <th>used</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Reservation reservation : reservations) {
                                    VIP vip = reservation.getVip();
                                    User user = vip.getUser();
                                    Plan plan = reservation.getPlan();
                                    Room room = plan.getRoom();
                                    String isUse = reservation.getIsUse().equals("true") ? "yes" : "no";
                                    String gender = user.getGender().equals("男") ? "man" : "woman";
                            %>
                            <tr>
                                <td><%=reservation.getReid()%>
                                </td>
                                <td><%=user.getUname()%>
                                </td>
                                <td><%=user.getIdCardNumber()%>
                                </td>
                                <td><%=gender%>
                                </td>
                                <td><%=vip.getPhone()%>
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
                        %>

                    </div>
                </div>
            </div>
        </div>

        <%
            if (trades == null) {
        %>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">income chart</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-1 col-lg-offset-8">
                                <h5>detail</h5>
                            </div>
                            <div class="col-lg-2" style="width: 10%">
                                <select id="month_data" class="form-control"
                                        onchange="selectMonth(<c:out value="${hostel.hid}"/>)">
                                    <option value="none"></option>
                                    <option>2017-01</option>
                                    <option>2017-02</option>
                                    <option>2017-03</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12" id="income_chart">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-4 col-lg-offset-8">
                                <h4>our total income is ￥ <c:out value="${hostel.income}"/></h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
        } else {
        %>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">trades list</h3>
                    </div>
                    <div class="panel-body">
                        <%
                            if (trades.size() == 0) {
                        %>
                        no trades
                        <%
                        } else {
                        %>

                        <table class="table table-condensed">
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
                        %>
                        <div class="row">
                            <div class="col-lg-1 col-lg-offset-5">
                                <a href="hostel-showAllData">
                                    <button class="btn btn-info" type="button">
                                        back
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

<jsp:include page="hostel-tail.jsp" flush="true"></jsp:include>
<script src="<%=request.getContextPath()%>/static/js/statistics.js"></script>

</body>
</html>
