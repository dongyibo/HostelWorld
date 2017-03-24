<%--
  Created by IntelliJ IDEA.
  User: dongyibo
  Date: 2017/1/4
  Time: 下午7:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" style="background: #505D6E url(/HostelWorld/static/images/home/bg3.jpg) no-repeat center center fixed">
  <head>
    <jsp:include page="home-head.jsp" flush="true"></jsp:include>
    <title>Hostel World</title>

  </head>

  <body class="theme-invert">

  <nav class="mainmenu">
    <div class="container">
      <div class="row">
        <div class="col-md-2 col-md-offset-4">
          <span><a style="font-weight: 800" href="userLogin.jsp" class="btn btn-default btn-lg">user</a></span>
        </div>
        <div class="col-md-2">
          <span><a style="font-weight: 800" href="hostelLogin.jsp" class="btn btn-default btn-lg">hostel</a></span>
        </div>
      </div>
    </div>
  </nav>

  <!-- Main (Home) section -->
  <section class="section" id="head">
    <div class="container">

      <div class="row">
        <div class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 text-center">

          <!-- Site Title, your name, HELLO msg, etc. -->
          <h1 class="title">Hostel World</h1>
          <h2 class="subtitle">the website for booking hotels</h2>
          <!-- Short introductory (optional) -->
          <h3 class="tagline">

            You will experience the best of accommodation in HostelWorld
          </h3>

        </div> <!-- /col -->
      </div> <!-- /row -->

    </div>

  </section>

  <footer>
    <div class="container text-center">
      <p>Copyright &copy; HostelWorld 2017 </p>
    </div>
  </footer>
  <jsp:include page="home-tail.jsp" flush="true"></jsp:include>
  </body>
</html>
