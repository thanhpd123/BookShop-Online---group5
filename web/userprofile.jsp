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
                <h1 class="font-weight-semi-bold text-uppercase mb-3">User profile</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="Home?service=listAll">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Checkout</p>
                </div>
            </div>
        </div>
        <!-- Page Header End -->
        <div class="row">
            <div class="col-md-9">
            </div>
            <div class="col-md-3 form-group mt-3" >
                <a href="changepassword">Change Password</a>
            </div>
        </div>
        <!-- Checkout Start -->
        <form action="userprofile" method="post" enctype="multipart/form-data">
            <div class="row" style="margin-bottom: 20px">
                <div class="col-md-3"></div>
                <div class="col-md-6 form-container">
                    <div class="row">
                        <div class="col-md-6">
                            <img src="${customer.getImgUser()}" alt="" class="form-control" style="margin-bottom: 15px; width: 60%;
                                 margin-bottom: 47px;
                                 height: 56%;">
                            <label>Image update: </label>
                            <input type="file" name="image" accept="image/img,image/png,image/jpeg,image/jpg"><br>
                            <label>Email address: </label>
                            <input class="form-control" type="text" name="email" value="${customer.getEmail()}" disabled>
                        </div>
                        <div class="col-md-6">
                            <label>First name: </label>
                            <input class="form-control" type="text" name="fname" value="${customer.getFirstName()}" placeholder="first name . . .">
                            <label>Last name: </label>
                            <input class="form-control" type="text" name="lname" value="${customer.getLastName()}" placeholder="last name . . .">
                            <label>Phone number: </label>
                            <input class="form-control" type="text" name="phone" value="${customer.getPhoneNo()}" placeholder="phone number . . .">
                            <label>Address: </label>
                            <input class="form-control" type="text" name="address" value="${customer.getAddress()}" placeholder="address . . .">
                            <label>Gender: </label>
                            <select class="form-control" name="gender">
                                <option value="0" ${customer.isGender() eq 'false' ? 'selected' : ''}>Male</option>
                                <option value="1" ${customer.isGender() eq 'true' ? 'selected' : ''}>Female</option>
                            </select>
                            <label>Dob: </label>
                            <input class="form-control" name="date" type="Date" value="${customer.getDOB()}">
                        </div>
                        <div style="color: red; text-align: center; margin: 0 auto;">
                            ${msg}
                        </div>
                    </div>
                    <div class="row"> 
                        <div class="col-md-4"></div>
                        <div class="col-md-4 form-group" style="margin-top: 20px">
                            <button class="btn btn-primary btn-block">Update profile</button>
                        </div>
                        <div class="col-md-4"></div>
                    </div> 
                </div>
                <div class="col-md-3"></div>
            </div>
        </form>

        <!--                <label>E-mail</label>
                                    <input class="form-control" type="text" placeholder="example@email.com">
                                    <label>Mobile No</label>
                                    <input class="form-control" type="text" placeholder="+123 456 789">
                                    <label>Address Line 1</label>
                                    <input class="form-control" type="text" placeholder="123 Street">
                                    <label>Address Line 2</label>
                                    <input class="form-control" type="text" placeholder="123 Street">
                                    <label>City</label>
                                    <input class="form-control" type="text" placeholder="New York">
                                    <label>State</label>
                                    <input class="form-control" type="text" placeholder="New York">-->
        <!-- JavaScript Libraries -->
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
