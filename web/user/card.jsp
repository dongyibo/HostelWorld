<%@ page import="com.nju.hostelworld.model.VIPCard" %>
<%@ page import="com.nju.hostelworld.model.VIP" %><%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/14
  Time: 下午12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="user-head.jsp" flush="true"></jsp:include>
    <link href="<%=request.getContextPath()%>/static/css/register.css" rel="stylesheet">
    <title>user card</title>
</head>
<body id="page-top" class="index">
<jsp:include page="user.jsp" flush="true"></jsp:include>
<%
    VIP vip = (VIP) session.getAttribute("vip");
    VIPCard vipCard = (VIPCard) session.getAttribute("vipCard");
    String state = vip.getState();
    if (state.equals("正常")){
        state = "normal";
    }
    else if (state.equals("暂停")){
        state = "frozen";
    }
    else{
        state = "Discarded";
    }
%>
<section id="content">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>VIP card</h2>
                <hr>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">card information</h3>
                    </div>
                    <div class="panel-body">

                        <div class="row">
                            <div class="col-lg-4 col-lg-offset-4">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <span style="color: #848484">
                                        Membership is divided into four levels<br>
                                            level <strong>0</strong> enjoy %90 discount<br>
                                            level <strong>1</strong> enjoy %85 discount<br>
                                            level <strong>2</strong> enjoy %80 discount<br>
                                            level <strong>3</strong> enjoy %75 discount<br>
                                            500 points can be exchanged for ￥40 for deposit
                                        </span>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row">
                            <div class="col-lg-8 col-lg-offset-2">
                                <ul class="list-group">
                                    <li class="list-group-item">
                                        <span
                                                class="span_prompt">cardID </span><%=vipCard.getVcid()%>
                                    </li>
                                    <li class="list-group-item"><span
                                            class="span_prompt">point </span><span id="point_span"><%=vipCard.getPoint()%></span>
                                    </li>
                                    <li class="list-group-item"><span
                                            class="span_prompt">level </span><%=vipCard.getGrade()%>
                                    </li>

                                    <li class="list-group-item"><span
                                            class="span_prompt">deposit </span><span id="deposit_span"><%=vipCard.getDeposit()%></span>
                                    </li>

                                    <li class="list-group-item"><span
                                            class="span_prompt">state </span><span><%=state%></span>
                                    </li>

                                    <li class="list-group-item"><span
                                            class="span_prompt">deadline </span><span><%=vip.getDeadline().toLocaleString()%></span>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-8 col-lg-offset-2">
                                <div class="col-lg-2">
                                    <button onclick="convertible(<%=vipCard.getVcid()%>);"
                                            type="button" class="btn btn-info">convertible cash
                                    </button>
                                </div>
                                <div class="col-lg-1 col-lg-offset-8" id="recharge_btn">
                                    <button onclick="showRecharge();" type="button" class="btn btn-info">recharge
                                    </button>
                                </div>
                                <div class="col-lg-10" id="recharge_div">
                                    <div class="col-lg-7 col-lg-offset-1">
                                        <input id="money_input" type="text" class="form-control"
                                               placeholder="please input the amount charged">
                                    </div>
                                    <div class="btn-group col-lg-4">
                                        <button onclick="recharge(<%=vipCard.getVcid()%>);" type="button" class="btn btn-default">confirm</button>
                                        <button onclick="hideRecharge();" type="button" class="btn btn-default">cancel
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-8 col-lg-offset-2">
                                <div class="col-lg-2" style="margin-top: 10px">
                                    <a href="card-renewal?vcid=<%=vipCard.getVcid()%>">
                                        <button type="button" class="btn btn-warning">renewals (one year)
                                        </button>
                                    </a>
                                </div>
                                <div class="col-lg-1 col-lg-offset-8" style="margin-top: 10px">
                                    <a href="card-abandon?vid=<%=vip.getVid()%>">
                                        <button type="button" class="btn btn-danger">abandon
                                        </button>
                                    </a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<% if (request.getAttribute("renewalFail") != null && request.getAttribute("renewalFail").equals("InsufficientBalance")) {
%>
<script>
    alert("insufficient balance");
</script>
<%
    }
%>
<jsp:include page="user-tail.jsp" flush="true"></jsp:include>
<script src="<%=request.getContextPath()%>/static/js/card.js"></script>
</body>
</html>
