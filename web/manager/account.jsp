<%@ page import="com.sun.org.apache.bcel.internal.generic.LUSHR" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nju.hostelworld.model.Hostel" %>
<%@ page import="com.nju.hostelworld.model.Trade" %>
<%@ page import="com.nju.hostelworld.util.Time" %><%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/16
  Time: 下午5:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../user/user-head.jsp" flush="true"></jsp:include>
    <title>manager account</title>
</head>
<body id="page-top" class="index">
<jsp:include page="manager.jsp" flush="true"></jsp:include>
<%
    List<Hostel> hostels = (List<Hostel>) session.getAttribute("accountHostels");
    List<Trade> trades = (List<Trade>) session.getAttribute("unPaidTrades");
%>
<section id="content">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>settle accounts</h2>
                <hr>
            </div>
        </div>

        <%
            if (trades == null) {
        %>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">hostel list</h3>
                    </div>
                    <div class="panel-body">
                        <%
                            if (hostels == null || hostels.size() == 0) {
                        %>
                        All hostels are paid for
                        <%
                        } else {
                        %>
                        <table class="table table-condensed">
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
                                    <a href="manager-settle?hid=<%=hostel.getHid()%>">
                                        <button type="button" class="btn btn-info">settlement</button>
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
                    </div>
                </div>
            </div>
        </div>

        <%
        } else {
            String hid = "";
        %>


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">trades list</h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-condensed">
                            <thead>
                            <tr>
                                <th>tradeId</th>
                                <th>date</th>
                                <th>days</th>
                                <th>fee</th>
                                <th>button</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Trade trade : trades) {
                            %>
                            <tr>
                                <td><%=trade.getTid()%>
                                </td>
                                <td><%=Time.dateFormat(trade.getDate())%>
                                </td>
                                <td><%=trade.getDays()%>
                                </td>
                                <td><%=trade.getFee()%>
                                </td>
                                <td>
                                    <a href="manager-pay?tid=<%=trade.getTid()%>&hid=<%=trade.getPlan().getHostel().getHid()%>">
                                        <button type="button" class="btn btn-info">pay</button>
                                    </a>
                                </td>
                            </tr>
                            <%
                                    hid = trade.getPlan().getHostel().getHid();
                                }
                            %>
                            </tbody>
                        </table>
                        <div class="row">
                            <div class="col-lg-2 col-lg-offset-3">
                                <a href="manager-allPay?hid=<%=hid%>">
                                    <button type="button" class="btn btn-primary">one-click payment</button>
                                </a>
                            </div>
                            <div class="col-lg-1 col-lg-offset-2">
                                <a href="manager-showAccount">
                                    <button type="button" class="btn btn-primary">back</button>
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

<jsp:include page="../user/user-tail.jsp" flush="true"></jsp:include>
</body>
</html>
