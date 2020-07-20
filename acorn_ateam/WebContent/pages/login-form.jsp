<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에이콘팬션 로그인</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Reset.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Home.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Navbar.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/RoomIntroduce.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/RoomCard.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Food.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Footer.css" />


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css" />
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&family=Yeon+Sung&display=swap" rel="stylesheet" />
<script src="https://kit.fontawesome.com/c0a9fdc19a.js" crossorigin="anonymous"></script>
<script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>



</head>
<body>
	<jsp:include page="templates/nav.jsp"></jsp:include>
	
<div class="container">
		<form action="login.jsp" method="post">
			<div class="form-group">
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" />
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input type="password" name="pwd" id="pwd" />
			</div>
			<input type="submit" value="로그인" />
		</form>
	</div>
</body>
</html>