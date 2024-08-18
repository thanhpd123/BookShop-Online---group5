<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.lang.*, entity.Book"%>
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
            .col-md-8 img{
                height: 420px;
                width: 315px;
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
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Product Details</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Checkout</p>
                </div>
            </div>
        </div>
        <!-- Page Header End -->

        <!-- Checkout Start -->
        <c:if test="${not empty book}">
            <form action="productdetailformarketing" method="post" enctype="multipart/form-data">
                <div class="row" style="margin-top: 30px; margin-bottom: 20px">
                    <input type="hidden" name="BookID" value="${book.getBookID()}">
                    <div class="col-md-2"></div>
                    <div class="col-md-8 form-container">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-2"></div>
                                    <div class="col-md-8">
                                        <%
                                            Book book = (Book)request.getAttribute("book");
                                            String img = book.getBookImg();
                                            if (img != null && img.length() >= 5) {
                                                String sub = img.substring(0, 6);
                                                if (sub.equals("/book/")) {
                                        %>
                                        <img src="${pageContext.request.contextPath}<%=img%>" class="img">
                                        <%
                                                } else {
                                        %>
                                        <img src="<%=img%>" class="img">
                                        <%
                                                }
                                            }
                                        %>
                                        <label>Image update: </label>
                                        <input type="file" name="image" accept="image/img,image/png,image/jpeg,image/jpg"><br>
                                    </div>
                                    <div class="col-md-2"></div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Title: </label>
                                <input class="form-control" type="text" name="title" value="${book.getName()}" >
                                <div class="row">
                                    <div class="col-6">
                                        <label>Category: </label>
                                        <select class="form-control" name="category">
                                            <c:forEach items="${listCa}" var="listCate">
                                                <c:if test="${book.getCategoryName() eq listCate.getCategoryName()}">
                                                    <option value="${listCate.getCategoryID()}" selected>${listCate.getCategoryName()}</option>
                                                </c:if>
                                                <c:if test="${book.getCategoryName() ne listCate.getCategoryName()}">
                                                    <option value="${listCate.getCategoryID()}">${listCate.getCategoryName()}</option>
                                                </c:if>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-6">
                                        <label>Author: </label>
                                        <select class="form-control" name="author">
                                            <c:forEach items="${listAu}" var="listAuth">
                                                <c:if test="${book.getAuthorName() eq listAuth.getAuthorName()}">
                                                    <option value="${listAuth.getAuthorID()}" selected>${listAuth.getAuthorName()}</option>
                                                </c:if>
                                                <c:if test="${book.getAuthorName() ne listAuth.getAuthorName()}">
                                                    <option value="${listAuth.getAuthorID()}">${listAuth.getAuthorName()}</option>
                                                </c:if>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <label>Quantity: </label>
                                <input class="form-control" type="text" name="quantity" value="${book.getQuantity()}" placeholder="Quantity . . .">
                                <label>List Price: </label>
                                <input class="form-control" type="text" name="listprice" value="${book.getPrice()}" placeholder="List Price . . .">
                                <label>Sale Price: </label>
                                <input class="form-control" type="text" name="saleprice" value="${book.getSalePrice()}" placeholder="Sale Price . . .">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Status: </label>
                                        <select class="form-control" name="status">
                                            <option value="available" ${book.getStatus() eq 'available' ? 'selected' : 'Available'}>Available</option>
                                            <option value="outofstock" ${book.getStatus() eq 'outofstock' ? 'selected' : 'outofstock'}>Out of stock</option>
                                        </select>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Flag To Turn: </label>
                                        <select class="form-control" name="flagtoturn">
                                            <option value="1" ${book.getFlag() eq 'on' ? 'selected' : 'on'}>ON</option>
                                            <option value="0" ${book.getFlag() eq 'off' ? 'selected' : 'off'}>OFF</option>
                                        </select
                                    </div>
                                </div>
                            </div>
                            <label>Publisher name: </label>
                            <input class="form-control" type="text" name="publishername" value="${book.getPublisherName()}" placeholder="Brief Information . . .">
                        </div>
                        <div style="color: red; text-align: center; margin: 0 auto;">
                            ${msg}
                        </div>
                    </div>
                    <div class="row"> 
                        <div class="col-md-5"></div>
                        <div class="col-md-3 form-group" style="margin-top: 20px">
                            <button class="btn btn-primary">Update product</button>
                        </div>
                        <div class="col-md-4"></div>
                    </div> 
                </div>
                <div class="col-md-2"></div>
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
</body>

</html>
