<%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/8
  Time: 下午6:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<nav class="mainmenu">
    <div class="container">
        <div class="dropdown">
            <div class="row">
                <div class="col-lg-1">
                    <button type="button" class="navbar-toggle" data-toggle="dropdown"><span class="icon-bar"></span>
                        <span class="icon-bar"></span> <span class="icon-bar"></span></button>
                    <!-- <a data-toggle="dropdown" href="#">Dropdown trigger</a> -->
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <li><a id="login_link" href="<%=request.getContextPath()%>/manager/approval.jsp" class="link btn btn-link"></span>Approval application</a></li>
                        <li><a id="register_link" href="<%=request.getContextPath()%>/manager-showAccount" class="link btn btn-link"></span>Settle accounts</a></li>
                        <li><a id="back_link" href="<%=request.getContextPath()%>/manager-showHostel" class="link btn btn-link"></span>Statistical data</a></li>
                    </ul>
                </div>

                <div class="col-lg-1 col-lg-offset-10">
                    <button type="button" class="navbar-toggle" data-toggle="dropdown"><span class="icon-bar"></span>
                        <span class="icon-bar"></span> <span class="icon-bar"></span></button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <li><a id="friend_link" href="<%=request.getContextPath()%>/manager-logout" class="link btn btn-link"></span>Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>

<header id="head-user">
    <div class="container">
        <div class="row">
            <div class="col-lg-2">
                <div id="prompt_head">
                    <span class="prompt_text" id="prompt_head_span"></span>
                </div>
            </div>
            <div class="col-lg-10 intro-text">
                <div class="row">
                    <span id="user_name" class="col-lg-offset-3 col-lg-4 name"><c:out value="${manager.user.uname}"/></span>
                </div>
            </div>
        </div>
    </div>
    </div>
</header>
