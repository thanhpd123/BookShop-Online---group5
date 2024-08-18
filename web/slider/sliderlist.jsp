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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="index_1_ForMarketing.jsp" />
        <meta name="Version" content="v1.2.0" />
        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="css/style.min.css" rel="stylesheet">
        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
        <!--<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>-->
        <!--        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">-->
        <style>
            .product-actions {
                display: flex;
                align-items: center;
                margin-bottom: 15px;
            }

            .product-actions .btn1 {
                border-radius: 5px;
                margin-left: 15px;
                margin-right: 5px;
                background: green;
                font-size: 10px;
                color: white;
                size: 10px
            }
            .product-actions .btn2 {
                border-radius: 5px;
                margin-left: 15px;
                margin-right: 5px;
                background: pink;
                font-size: 10px;
                color: black;

            }
            .product-container {
                border-radius: 10px;
                border: 1px solid #ccc;
            }
            .product-actions .btn3 {
                border-radius: 5px;
                background: red;
                font-size: 10px;
                color: white;
            }
            .thumbnail{
                height: 100%;
                width: 100%;
                margin-left: 15px;
                margin-top: 15px;
            }
            .content{
                margin-left: 15px;
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

        <div class="container-fluid bg-secondary mb-2">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 100px">
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Slider list</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Checkout</p>
                </div>
            </div>
        </div>
        <form action="sliderlist" method="Get" style="margin-bottom: 35px">
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-2">
                </div>
                <div class="col-md-2">
                    <label>Status: </label>
                    <div class="position-relative">
                        <select class="form-control time-during select2input" name="status" style="height: 40px;">
                            <option value="All" ${status != 'true' && status != 'false' ? 'selected' : ''}>All</option>
                            <option value="true" ${status == 'true' ? 'selected' : ''}>On</option>
                            <option value="false" ${status == 'false' ? 'selected' : ''}>Off</option>
                        </select>

                    </div>
                </div>
                <div class="col-md-5">
                    <label>Search: ${find}</label>
                    <input type="search" class="form-control border rounded-pill" name="find" placeholder="Search Title..................." style="height: 40px;">
                </div> 
                <div class="col-md-1">
                    <label></label>
                    <button type="submit" class="btn btn-primary" name="action" value="search" style="margin-top: 30px">Search</button>
                </div>
            </div>
        </form>
        <div class="row">
            <div class="col-lg-1"></div>
            <c:forEach items="${sliderlist}" var="slider">
                <div class="col-lg-2">
                    <div class="products-row">
                        <div class="product-container">
                            <div class="thumbnail">
                                <img style="height: 260px; width: 170px" src="${slider.getSilderImg()}" alt="Product Image 1">
                            </div>
                            <div class="product-info" style="margin-top: 10px">
                                <p class="content">SliderID: ${slider.getSilderID()}</p>
                                <p class="content">Title: ${slider.getTile()}</p>
                                <p class="content">Status: ${slider.isStatus() ? 'ON' : 'OFF'}</p>
                            </div>
                            <div class="product-actions">
                                <form action="sliderlist" method="get">
                                    <c:if test="${slider.isStatus() == false}">
                                    <button class="btn2" value="${slider.getSilderID()}" name="buttonShow">Show</button>
                                    <input type="hidden" name="status" value="${status}">
                                    <input type="hidden" name="find" value="${find}">
                                    <input type="hidden" name="action" value="${action}">
                                    <input type="hidden" name="page" value="${page}">
                                    </c:if>
                                    <c:if test="${slider.isStatus() == true}">
                                    <button class="btn1" value="${slider.getSilderID()}" name="buttonHide">Hide</button>
                                    <input type="hidden" name="status" value="${status}">
                                    <input type="hidden" name="find" value="${find}">
                                    <input type="hidden" name="action" value="${action}">
                                    <input type="hidden" name="page" value="${page}">   
                                    </c:if>
                                </form>
                                <form action="sliderdetail" method="get">
                                    <button class="btn3">Edit</button>
                                    <input type="hidden" name="SilderID" value="${slider.getSilderID()}">  
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div class="col-lg-1"></div>
        </div>
        <div class="" style="margin-top: 20px">
            <%@include file="pagination.jsp" %>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
        <script src="https://cdn.tiny.cloud/1/3dnf4g9cgv4sfog9pgz0n3f33fqrskhx8tz3ejpcmhn480gh/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
    </body>

</html>
