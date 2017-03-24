<%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/17
  Time: 下午1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nju.hostelworld.util.Time" %>
<%@ page import="com.nju.hostelworld.model.*" %>
<html>
<head>
    <jsp:include page="user-head.jsp" flush="true"></jsp:include>
    <title>user data</title>
</head>
<body id="page-top" class="index">
<jsp:include page="user.jsp" flush="true"></jsp:include>
<%
    List<Reservation> reservations = (List<Reservation>) session.getAttribute("allReservations");
    List<Trade> trades = (List<Trade>) session.getAttribute("allTrades");
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
                                for (Reservation reservation : reservations) {
                                    Plan plan = reservation.getPlan();
                                    Hostel hostel = plan.getHostel();
                                    Room room = plan.getRoom();
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
                        %>

                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">trade list</h3>
                    </div>
                    <div class="panel-body">
                        <%
                            if (trades == null || trades.size() == 0) {
                        %>
                        no trades
                        <%
                        } else {
                            double sumCost = 0;
                        %>

                        <table class="table table-condensed">
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
                                <th>way</th>
                                <th>inUse</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Trade trade : trades) {
                                    Plan plan = trade.getPlan();
                                    Hostel hostel = plan.getHostel();
                                    Room room = plan.getRoom();
                                    String inUse = trade.getState().equals("入店") ? "yes" : "no";
                                    String way = trade.getWay().equals("非会员卡") ? "cash" : "vip-card";
                                    sumCost += trade.getFee();
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
                                <td><%=inUse%>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                        <div class="row">
                            <div class="col-lg-4 col-lg-offset-8">
                                <h4>You have already spent ￥ <%=sumCost%>
                                </h4>
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


<jsp:include page="user-tail.jsp" flush="true"></jsp:include>
</body>
</html>
