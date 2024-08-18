<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
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
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Posts list</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Checkout</p>
                </div>
            </div>
        </div>
        <!-- Page Header End -->

        <!-- Filter and Search Form Start -->
        <div class="container">
            <form action="bloglistcontroller" method="Get">
                <div class="row">
                    <div class="col-md-2">
                    </div>
                    <div class="col-md-2">
                        <label>Author: </label>
                        <div class="position-relative">
                            <select class="form-control time-during select2input" name="author" style="height: 40px;">
                                <option value="-1">All</option>
                                <c:forEach items="${listAu}" var="listAuth">
                                    <option value="${listAuth.getAuthorName()}" ${listAuth.getAuthorName() eq author ? 'selected' : ''}>${listAuth.getAuthorName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <label>Status: </label>
                        <div class="position-relative">
                            <select class="form-control time-during select2input" name="status" style="height: 40px;">
                                <option value="1" ${status eq 1 ? 'selected' : ''}>On</option>
                                <option value="0" ${status eq 0 ? 'selected' : ''}>Off</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <label>Search: ${find}</label>
                        <input type="search" class="form-control border rounded-pill" name="find" placeholder="Search Title..................." style="height: 40px;">
                    </div> 
                    <div class="col-md-1">
                        <label></label>
                        <button type="submit" class="btn btn-primary" name="action" value="search">Search</button>
                    </div>
                </div>
                <!-- Sort Form Start -->
                <div class="row" style="margin-top: 20px;">
                    <div class="col-lg-7 col-md-2"></div>
                    <div class="col-lg-2 col-md-2"></div>
                    <div class="col-lg-2 col-md-2">
                        <label>Sort: </label>
                        <div class="mb-0 position-relative">
                            <select class="form-control time-during select2input" name="sort">
                                <option value="Allasc">All</option>
                                <option value="Bl.Title" ${sort=='Bl.Title'?'selected':''} >Title(A-Z)</option>
                                <option value="Bl.AuthorName" ${sort=='A.AuthorName'?'selected':''} >Author Name (A-Z)</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-1 col-md-2">
                        <div class="" style="border: 1px; background: #D2B48C;">
                            <a href="goAddNewPost" style="color: black; margin-left: 15px">Add</a>
                        </div>
                    </div>
                </div>
            </form>
            <div class="row" >
                <div class="col-12 mt-4">
                    <div class="table-responsive bg-white shadow rounded">
                        <c:if test="${blogList != null and !blogList.isEmpty()}">
                            <table class="table mb-0 table-center">
                                <thead>
                                    <tr style="text-align: center;">
                                        <th class="border-bottom p-3" style="min-width: 100px;text-align: center;">Post ID</th>
                                        <th class="border-bottom p-3" style="min-width: 100px;">Thumbnail</th>
                                        <th class="border-bottom p-3" style="min-width: 150px;">Title</th>
                                        <th class="border-bottom p-3" style="min-width: 180px;">Author's Name</th>
                                        <th class="border-bottom p-3" style="min-width: 50px;">Status</th>
                                        <th class="border-bottom p-3" style="min-width: 80px;">Content</th>
                                        <th class="text-end p-3">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="blog" items="${blogList}">
                                        <tr style="text-align: center;">
                                            <td class="p-3">${blog.getBlogsID()}</td>
                                            <td class="p-3">
                                                <img src="${blog.getBlogImg()}" style="width: 90px; height: 90px;">
                                            </td>
                                            <td class="p-3">${blog.getTitle()}</td>
                                            <td class="p-3">${blog.getAuthorName()}</td>
                                            <td class="p-3">${blog.isPublished ? 'On' : 'Off'}</td>
                                            <td class="p-3">
                                                <p style="min-width: 400px;">
                                                    <span id="dots_${blog.getBlogsID()}">....</span>
                                                    <span id="more_${blog.getBlogsID()}" style="display: none;"> ${blog.getBriefInformation()}</span>
                                                </p>
                                                <a style="color: #D19C97;" onclick="BlockFunction('${blog.getBlogsID()}')" id="myBtn_${blog.getBlogsID()}">Read more</a>
                                            </td>      
                                            <td class="text-end p-3">
                                                <a href="blogdetailcontroller?blogID=${blog.getBlogsID()}" class="btn btn-icon btn-pills btn-soft-primary">
                                                    <span class="uil uil-eye" style="border: 1px gray solid; color: #D19C97"></span>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div><!--end row-->
            <div class="" style="margin-top: 20px">
                <%@include file="pagination.jsp" %>
            </div>
        </div>
        <script>
            function BlockFunction(blogID) {
                var dots = document.getElementById("dots_" + blogID);
                var moreText = document.getElementById("more_" + blogID);
                var btnText = document.getElementById("myBtn_" + blogID);

                if (moreText.style.display === "none") {
                    dots.style.display = "none";
                    moreText.style.display = "inline";
                    btnText.innerHTML = "Read less";

                } else {
                    dots.style.display = "inline";
                    moreText.style.display = "none";
                    btnText.innerHTML = "Read more";

                }
            }
        </script>
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
