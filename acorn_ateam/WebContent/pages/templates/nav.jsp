<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <% %> <% String users_id = (String)session.getAttribute("users_id");
System.out.println(users_id); boolean isLogged = (users_id == null) ? false :true; System.out.println(isLogged); %>
<!-- Navbar -->
<nav id="navbar" class="">
  <div class="navbar__logo">
    <a href="${pageContext.request.contextPath}/index.jsp">ACORN PENSION</a>
    <img id="home__cup" src="${pageContext.request.contextPath}/assets/images/cup.png" alt="cup" />
  </div>
  <ul class="navbar__menu">
    <li class="navbar__menu__item" data-link="#reserve">
      <a href="${pageContext.request.contextPath}/pages/reserve-calender.jsp">
        <i class="far fa-calendar-alt"></i><br />
        <span>예약하기</span>
      </a>
    </li>
    <li class="navbar__menu__item" data-link="#review">
      <a href="${pageContext.request.contextPath}/pages/reviews.jsp">
        <i class="fas fa-clipboard-list"></i><br />
        <span>이용후기</span>
      </a>
    </li>
    <li class="navbar__menu__item" data-link="#login">
      <%if(isLogged){ %>
      <a href="${pageContext.request.contextPath}/pages/private/logout.jsp">
        <i class="fas fa-sign-in-alt"></i><br />
        <span>로그아웃</span>
      </a>
      <%}else{ %>
      <a href="${pageContext.request.contextPath}/pages/login-form.jsp">
        <i class="fas fa-sign-in-alt"></i><br />
        <span>로그인</span>
      </a>
      <%} %>
    </li>
    <li class="navbar__menu__item" data-link="#signup">
      <%if(isLogged){ %>
      <a href="${pageContext.request.contextPath}/pages/private/mypages/main.jsp">
        <i class="fas fa-sign-in-alt"></i><br />
        <span>마이페이지</span>
      </a>
      <%}else{ %>
      <a href="${pageContext.request.contextPath}/pages/signup-form.jsp">
        <i class="fas fa-sign-in-alt"></i><br />
        <span>회원가입</span>
      </a>
      <%} %>
    </li>
  </ul>
  <button class="navbar__toggle-btn">
    <i class="fas fa-bars"></i>
  </button>
</nav>
<!-- <div class="main"></div> -->
