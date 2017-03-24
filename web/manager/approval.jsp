<%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/16
  Time: 下午2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nju.hostelworld.model.Hostel" %>
<%@ page import="com.nju.hostelworld.model.HostelCache" %>
<html>
<head>
    <jsp:include page="../user/user-head.jsp" flush="true"></jsp:include>
    <title>manager approval</title>
</head>
<body id="page-top" class="index">
<jsp:include page="manager.jsp" flush="true"></jsp:include>
<%
    List<Hostel> hostels = (List<Hostel>) session.getAttribute("hostelListsWithoutApproval");
    List<HostelCache> hostelCaches = (List<HostelCache>) session.getAttribute("hostelCaches");
%>
<section id="content">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>Approval application</h2>
                <hr>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">registration list</h3>
                    </div>
                    <div class="panel-body">
                        <%
                            if (hostels == null || hostels.size() == 0) {
                        %>
                        No unapproved hostels
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
                            for (Hostel hostel : hostels){
                                %>
                            <tr>
                                <td><%=hostel.getHid()%></td>
                                <td><%=hostel.getHname()%></td>
                                <td><%=hostel.getAddress()%></td>
                                <td><%=hostel.getPhone()%></td>
                                <td>
                                    <a href="manager-approve?hid=<%=hostel.getHid()%>">
                                        <button type="button" class="btn btn-info">approve</button>
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


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">changes list</h3>
                    </div>
                    <div class="panel-body">
                        <%
                            if (hostelCaches == null || hostelCaches.size() == 0) {
                        %>
                        No unapproved hostel changes
                        <%
                        } else {
                        %>
                        <table class="table table-condensed">
                            <thead>
                            <tr>
                                <th>id</th>
                                <th>name</th>
                                <th>new phone</th>
                                <th>button</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (HostelCache hostelCache : hostelCaches){
                                    Hostel h = hostelCache.getHostel();
                            %>
                            <tr>
                                <td><%=h.getHid()%></td>
                                <td><%=h.getHname()%></td>
                                <td><%=hostelCache.getPhone()%></td>
                                <td>
                                    <a href="manager-approveChange?hid=<%=h.getHid()%>&phone=<%=hostelCache.getPhone()%>">
                                        <button type="button" class="btn btn-info">approve</button>
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

    </div>
</section>

<jsp:include page="../user/user-tail.jsp" flush="true"></jsp:include>
</body>
</html>
