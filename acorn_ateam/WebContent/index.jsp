<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=s, initial-scale=1.0" />
    <title>Acorn Pension</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Reset.css?v=<%=System.currentTimeMillis() %>" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css?v=<%=System.currentTimeMillis() %>" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Navbar.css?v=<%=System.currentTimeMillis() %>" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/SideBar.css?v=<%=System.currentTimeMillis() %>" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Main.css?v=<%=System.currentTimeMillis() %>" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Home.css?v=<%=System.currentTimeMillis() %>" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/RoomIntroduce.css?v=<%=System.currentTimeMillis() %>" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/RoomCard.css?v=<%=System.currentTimeMillis() %>" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Food.css?v=<%=System.currentTimeMillis() %>" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Footer.css?v=<%=System.currentTimeMillis() %>" />

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css" />

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet" />
    <!-- <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&family=Yeon+Sung&display=swap" rel="stylesheet" /> -->
    <script src="https://kit.fontawesome.com/c0a9fdc19a.js"></script>


    <script  type="module"  src="${pageContext.request.contextPath}/assets/js/main.js?v=<%=System.currentTimeMillis() %>"></script>
    <script defer src="${pageContext.request.contextPath}/assets/js/navBar.js?v=<%=System.currentTimeMillis() %>"></script>

    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0f27c20b6cce8806ccf9b044c36339d0&libraries=services"></script>	
  </head>

  <body class="animate__animated animate__zoomIn animate__delay-0.3s">
    <jsp:include page="pages/templates/nav.jsp"></jsp:include>
    <!-- sideBar -->
    <aside id="sideBar">
      <div class="container">
        <ul>
          
          <li><i class="fas fa-book"></i></li>
          <li><a href="${pageContext.request.contextPath}/pages/reserve-calender.jsp"><i class="far fa-calendar-alt"></i></a></li>
          <li><a href="${pageContext.request.contextPath}/pages/reviews.jsp"><i class="fas fa-clipboard-list"></i></a></li>
        </ul>
      </div>
    </aside>

    <!--Home-->
    <main id="home" class="main">
      <div class="home__container">
        <div class="home__container__info">
          <h1 class="home__title">
            Rooms
          </h1>
          <p class="home__description">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Quisquam pariatur libero sed et architecto nobis molestiae? Ratione, dignissimos explicabo.
            Quo exercitationem qui optio a provident eius impedit architecto nesciunt similique.
          </p>
          <div class="home__button">
            <span>More Info</span>
          </div>
        </div>
        <div class="home__container__card">
          <ul class="room__cards">
            <li class="room__rows">
              <img id="room1" src="./assets/images/room1.jpg" alt="" />
            </li>
            <li class="room__rows">
              <img id="room2" src="./assets/images/room2.jpg" alt="" />
            </li>
            <li class="room__rows">
              <img id="room3" src="./assets/images/room3.jpg" alt="" />
            </li>
            <li class="room__rows">
              <img id="room4" src="./assets/images/room4.jpg" alt="" />
            </li>
          </article>
        </div>
      </div>
    </main>

    <!-- main -->
    <section id="mainBar" class="section">
      <div class="main__container">
        <div class="main__container__column">
          <button class="openWhereModalBtn">위치 찾기</button>
        </div>
        <div class="main__container__column">
          <button disabled>자세히 보기</button>
          <!-- <a href="">자세히보기</a> -->
        </div>
        <div class="main__container__column">
          <button class="openFoodModalBtn">주변 식당</button>
          <!-- <a href="">주변 식당</a> -->
        </div>
      </div>
    </section>

    <article id="whereModal" class="whereModal hidden">
      <div class="modal__overaly"></div>
      <div class="modal__content flex-column">
          <button class="modal__close__btn">X</button>
          <div id="modal__top">
            <h4>찾아오는길</h4>
          </div>
          <div class="flex__rows">
            <div id="map"></div>
          </div>  
          <div class="flex__rows">
            <a href="https://map.kakao.com/link/to/삼원타워,37.4987345,127.0316116" target="__blank">길찾기</a>
          </div>
          <div id="modal__foot" class="flex-row flex__rows">
            <div class="flex__column">
              <h4>에이콘 팬션</h4>
            </div>
            <div class="flex__column">
              <div class="divider"></div>
            </div>
            <div class="flex__column">
              <h3>서울시 강남구 테헤란로 삼원타워 5층</h3>
            </div>
          </div>  
        </div>
     </div>
    </article>

    <article id="foodModal" class="whereModal hidden">
      <div class="modal__overaly"></div>
      <div class="modal__content flex-column">
          <button class="modal__close__btn">X</button>
          <div id="modal__top">
            <h4>주인장이 추천하는 음식</h4>
          </div>
          <div class="flex__rows">
            <div id="map2"></div>
          </div>  
          <div class="flex__rows">
            <a href="https://map.kakao.com/link/to/역전우동,37.497554, 127.032973" target="__blank">역전우동</a>
            <a href="https://map.kakao.com/link/to/버거킹,37.498882, 127.027533" target="__blank">버거킹</a>
          </div>
        </div>
     </div>
    </article>

    
    <!-- food -->
    <section id="food" class="section">

      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perspiciatis debitis eveniet at quas libero recusandae. Dignissimos maxime distinctio voluptates a similique minus autem corrupti illum quibusdam quod perferendis, dolorum laudantium.</p>

      <!-- <div class="__title">
        <h1>Food</h1>
        <div class="__title__line animate__animated animate__pulse animate__infinite"></div>
      </div>
      <ul class="list__area">
        <li>
          <div class="thum">
            <a href="#">
              <img src="http://udon0410.com/wp-content/uploads/2018/08/main_slide1-1910x1075.png" alt="역전우동" />
            </a>
          </div>
          <div class="cont_area">
            <i>
              <span>#</span>

              역전우동
            </i>
            <h4>따뜻한 국물이 일품인 백종원의 역전우동</h4>
            <div class="cont">
              "어쩌구 저쩌구 튀김우동 존맛탱이라고~"
            </div>
            <a href="#">➡Find</a>
          </div>
        </li>
        <li>
          <div class="thum">
            <a href="#">
              <img src="https://m.foodnjob.com/upload/upload/thumb3_offer_user/201911/1572593574_mem.PNG" alt="오미라식당" />
            </a>
          </div>
          <div class="cont_area">
            <i>
              <span>#</span>
              오마리식당
            </i>
            <h4>오미라 머시기</h4>
            <div class="cont">
              "어쩌구 저쩌구 한식뷔페입니다"
            </div>
            <a href="#">➡Find</a>
          </div>
        </li>
      </ul> -->
    </section>
    <jsp:include page="pages/templates/footer.jsp"></jsp:include>

    <!-- footer
    <footer id="footer">
      <div class="section">
        <div class="container">
          <div class="footer__row">
            <h4>에이콘 팬션</h4>
          </div>
          <div class="footer__row">
            <h5>이용약관</h5>
            <div class="divider"></div>
            <h5>개인정보 처리방침</h5>
            <div class="divider"></div>
            <h5>사업자정보확인</h5>
          </div>
          <div class="footer__row">
            <span>
              대표 : 000 상호/대표자명: 에이콘펜션/000 | 사업자등록번호: 000-00-00000 | 통신판매업신고번호: 제 202020-0000-0000호
            </span>
          </div>
          <div class="footer__row">
            <span>
              주소: 서울특별시 강남구 테헤란로 124 삼원타워 | 대표전화 : 000-000-0000 | 예약문의/이메일: 010-0000-0000, 0000@naver.com | 입금계좌: 00금고 0,
              예금주: 000
            </span>
          </div>
          <div class="footer__row">
            <div class="line"></div>
          </div>
          <div class="footer__row">
            <span>Copyright ⓒ All rights reserved.</span>
          </div>
        </div>
      </div>
    </footer> -->
  </body>
</html>
