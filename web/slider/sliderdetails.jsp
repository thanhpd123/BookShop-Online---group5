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
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Slider details</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Checkout</p>
                </div>
            </div>
        </div>
        <div>
            <a href="sliderlist" style="text-align: center; margin-left: 26%">Slider List</a>
        </div>
        <c:if test="${not empty slider}">
            <form action="sliderdetail" method="post" enctype="multipart/form-data">
                <div class="row" style="margin-top: 30px; margin-bottom: 20px">
                    <div class="col-md-3"></div>
                    <div class="col-md-6 form-container">
                        <div class="row">
                            <div class="col-md-6">
                                <img src="${slider.getSilderImg()}" alt="" class="form-control" style="margin-bottom: 15px; width: 60%;
                                     margin-bottom: 47px;
                                     height: 90%;
                                     width: 90%">
                            </div>
                            <div class="col-md-6">
                                <label>Slider ID: </label>
                                <input class="form-control" type="text" name="sliderid" value="${slider.getSilderID()}" disabled>
                                <input hidden value="${slider.getSilderID()}" name="sliID">
                                <label>Title: </label>
                                <input class="form-control" type="text" name="title" value="${slider.getTile()}" placeholder="title . . .">
                                <label>Status: </label>
                                <select class="form-control" name="status">
                                    <option value="0" ${slider.isStatus() eq 'false' ? 'selected' : ''}>ON</option>
                                    <option value="1" ${slider.isStatus() eq 'true' ? 'selected' : ''}>OFF</option>
                                </select>
                                <label>Node: </label>
                                <input class="form-control" type="text" name="node" value="${slider.getNote()}" placeholder="node . . .">
                                <label>Image update: </label>
                                <input type="file" name="image" accept="image/img,image/png,image/jpeg,image/jpg"><br>
                            </div>
                            <div style="color: red; text-align: center; margin: 0 auto; margin-top: 10px">
                                ${msg}
                            </div>
                        </div>
                        <div class="row"> 
                            <div class="col-md-4"></div>
                            <div class="col-md-4 form-group" style="margin-top: 25px">
                                <button class="btn btn-primary btn-block" style="width: 140px;">Update</button>
                            </div>
                            <div class="col-md-4"></div>
                        </div> 
                    </div>
                    <div class="col-md-3"></div>
                </div>
            </form>
        </c:if>
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
