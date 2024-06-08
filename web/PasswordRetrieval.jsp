<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>EShopper - Bootstrap Shop Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <style>
            .form-container {
                border: 1px solid gray;
                border-radius: 10px;
                padding: 20px;
                background-color: #f9f9f9;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                margin-top: 30px;
            }
            .form-group label {
                margin-top: 10px;
            }
            .content{
                text-align: center;
            }
            .error{
                padding-top: 15px; 
                color: red;
                font-size: 12px;
            }
        </style>
    </head>

    <body>
        <!-- Topbar Start -->
        <div class="container-fluid">
            <div class="row bg-secondary py-2 px-xl-5">
                <div class="col-lg-6 d-none d-lg-block">
                    <div class="d-inline-flex align-items-center">
                        <a class="text-dark" href="">FAQs</a>
                        <span class="text-muted px-2">|</span>
                        <a class="text-dark" href="">Help</a>
                        <span class="text-muted px-2">|</span>
                        <a class="text-dark" href="">Support</a>
                    </div>
                </div>
                <div class="col-lg-6 text-center text-lg-right">
                    <div class="d-inline-flex align-items-center">
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-twitter"></i>
                        </a>
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-linkedin-in"></i>
                        </a>
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-instagram"></i>
                        </a>
                        <a class="text-dark pl-2" href="">
                            <i class="fab fa-youtube"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar End -->

        <!-- Page Header Start -->
        <div class="container-fluid bg-secondary mb-2">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 100px">
                <h1 class="font-weight-semi-bold text-uppercase mb-3">FORGOT PASSWORD ?</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Checkout</p>
                </div>
            </div>
        </div>
        
        <form action="passwordretrieval" method="post">
            <div class="content">
                <div style="font-size: 15px;font-style: italic;">Enter the email you registered with so we can send</div>
                <div style="font-size: 15px;font-style: italic;">a link to change your password to your email!</div>
            </div>
            <div class="row" style="">
                <div class="col-md-4"></div>
                <div class="col-md-4 form-container">
                    <label>Email address: </label>
                    <input class="form-control" name="txtemail" type="text" placeholder="Email address . . .">
                    <p style="" class="error">${error}</p>
                    <c:if test="${not empty mess}">
                        <p style="" class="error">${mess}</p>
                        <p style="display: inline-block;" class="error">Expires later:</p>
                        <div id="countdown" style="display: inline-block;" class="error"></div>
                        
                        <script>
                            // Lấy phần tử HTML để hiển thị đồng hồ đếm giờ
                            var countdownElement = document.getElementById("countdown");
                            // Thiết lập thời gian bắt đầu (5 phút 00 giây)
                            var startTime = new Date();
                            startTime.setMinutes(startTime.getMinutes() + 2);
                            startTime.setSeconds(0);
                            // Lấy thời gian hiện tại
                            var currentTime = new Date();
                            // Tính toán thời gian còn lại
                            var remainingTime = startTime - currentTime;
                            // Cập nhật đồng hồ đếm giờ sau mỗi giây
                            setInterval(updateCountdown, 1000);
                            function updateCountdown() {
                                // Lấy thời gian hiện tại
                                var currentTime = new Date();
                                // Tính toán thời gian còn lại
                                var remainingTime = startTime - currentTime;
                                // Chuyển đổi thời gian còn lại thành phút và giây
                                var minutes = Math.floor((remainingTime % (1000 * 60 * 60)) / (1000 * 60));
                                var seconds = Math.floor((remainingTime % (1000 * 60)) / 1000);
                                // Hiển thị thời gian còn lại trên đồng hồ đếm giờ
                                countdownElement.innerHTML = minutes + ":" + seconds.toString().padStart(2, "0");
                                // Khi đến 0 giây, dừng đồng hồ đếm giờ
                                if (remainingTime <= 0) {
                                    clearInterval(updateCountdown);
                                    countdownElement.innerHTML = "Hết giờ";
                                }
                            }
                        </script>
                        
                    </c:if>
                    <div class="row"> 
                        <div class="col-md-3"></div>
                        <div class="col-md-6 form-group" style="margin-top: 20px">
                            <button class="btn btn-primary btn-block">Password Retrieval</button>
                        </div>
                        <div class="col-md-3"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-12" style="text-align: center; margin-top: 10px;">
                            <a href="login" class="btn btn-link">login</a>
                            <a href="register" class="btn btn-link">Register</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
        </form>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>

</html>