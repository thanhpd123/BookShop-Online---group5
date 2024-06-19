<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog Details - Impact Bootstrap Template</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com" rel="preconnect">
        <link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

        <!-- Main CSS File -->
        <link href="assets/css/main.css" rel="stylesheet">

        <!-- =======================================================
        * Template Name: Impact
        * Template URL: https://bootstrapmade.com/impact-bootstrap-business-website-template/
        * Updated: Jun 02 2024 with Bootstrap v5.3.3
        * Author: BootstrapMade.com
        * License: https://bootstrapmade.com/license/
        ======================================================== -->
    </head>

    <body class="blog-details-page">

        <header id="header" class="header fixed-top" style="background-color: #E5D3B3">



            <div class="branding d-flex align-items-cente">

                <div class="container position-relative d-flex align-items-center justify-content-between">
                    <a href="Home?service=listAll" class="logo d-flex align-items-center">
                        <!-- Uncomment the line below if you also wish to use an image logo -->
                        <!-- <img src="assets/img/logo.png" alt=""> -->
                        <img class="logo" src = "${pageContext.request.contextPath}/assets/logo.PNG" alt="Logo">
                    </a>

                    <nav id="navmenu" class="navmenu">
                        <ul>
                            <li><a href="#hero" class=""><br></a></li>
                            <li><a href="#about"></a></li>
                            <li><a href="#services"></a></li>
                            <li><a href="#portfolio"></a></li>
                            <li><a href="#team"></a></li>
                            <li><a href="blog.html"></a></li>
                            <li>Hello:<a href="userprofile" style="color: red"><c:if test="${acc != null}">${acc.getFirstName()} ${acc.getLastName()}</c:if></a></li>
                            </ul>
                            <i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
                        </nav>

                    </div>

                </div>

            </header>

            <main class="main">

                <!-- Page Title -->
                <div class="page-title" data-aos="fade" style="background-color: #987554">
                    <div class="heading">
                        <div class="container">
                            <div class="row d-flex justify-content-center text-center">
                                <div class="col-lg-8">
                                    <h1>Blog Details</h1>
                                </div>
                            </div>
                        </div>
                    </div>
                    <nav class="breadcrumbs">
                        <div class="container">
                            <ol>
                                <li><a href="Home?service=listAll">Home</a></li>
                                <li class="current">Blog Details</li>
                            </ol>
                        </div>
                    </nav>
                </div><!-- End Page Title -->

                <div class="container">
                    <div class="row">

                        <div class="col-lg-8">
                            <!-- Blog Details Section -->
                            <div id="blog-details" class="blog-details section">
                                <div class="container">
                                    <article class="article">

                                        <div class="post-img m-2">
                                            <img style="width: 80%; aspect-ratio:4/2.5" src="${blogData.getBlogImg()}" alt="" class="img-fluid">
                                    </div>

                                    <h2 class="title">${blogData.getTitle()}</h2>

                                    <div class="meta-top">
                                        <ul>
                                            <li class="d-flex align-items-center"><i class="bi bi-person"></i> <a href="blog-details.html">${blogData.getAuthorName()}</a></li>
                                            <li class="d-flex align-items-center"><i class="bi bi-clock"></i> <a href="blog-details.html"><time datetime="2020-01-01">${blogData.getCreatedDate()}</time></a></li>
                                            <li class="d-flex align-items-center"><i class="bi bi-chat-dots"></i> <a href="blog-details.html">12 Comments</a></li>
                                        </ul>
                                    </div><!-- End meta top -->

                                    <div class="content">
                                        <p>${blogData.getContent()}</p>
                                    </div><!-- End post content -->
                                    <div class="meta-bottom">
                                        <i class="bi bi-folder"></i>
                                        <ul class="cats">
                                            <li><a href="#"><c:if test="${acc != null}">${acc.getFirstName()} ${acc.getLastName()}</c:if></a></li>
                                            </ul>

                                            <i class="bi bi-tags"></i>
                                            <ul class="tags">
                                                <li><a href="#">Phan</a></li>
                                                <li><a href="#">Duy</a></li>
                                                <li><a href="#">Thanh</a></li>
                                            </ul>
                                        </div><!-- End meta bottom -->

                                    </article>

                                </div>
                            </div><!-- /Blog Details Section -->
                            <!-- Blog Author Section -->
                            <section id="blog-author" class="blog-author section">

                                <div class="container">
                                    <div class="author-container d-flex align-items-center">
                                        <img src="${blogData.getBlogAuthorImg()}" class="rounded-circle flex-shrink-0" alt="">
                                    <div>
                                        <h4>${blogData.getAuthorName()}</h4>
                                    </div>
                                </div>
                            </div>

                        </section><!-- /Blog Author Section -->
                        <!-- Blog Comments Section -->
                        <section id="blog-comments" class="blog-comments section">

                            <div class="container">

                                <h4 class="comments-count">${commentDataLength} Comments</h4>
                                <c:forEach items="${commentData}" var="i">
                                    <div class="comment">
                                        <div class="d-flex">
                                            <div class="comment-img"><img src="${i.getImgUser()}" alt=""></div>
                                            <div>
                                                <h5><a href="">${i.getUserFullName()}</a></h5>
                                                <time type="date">${i.getCommentDate()}</time>
                                                <p>
                                                    ${i.getCommentText()}
                                                </p>
                                            </div>
                                        </div>
                                    </div><!-- End comment #1 -->
                                </c:forEach>
                                <div class="reply-form">

                                    <h4>Leave a Reply</h4>
                                    <form action="BlogDetailServlet" method="post">
                                        <input type="hidden" name="blogID" value="${blogData.getBlogID()}">
                                        <input type="hidden" name="userID" value="${acc.getUserID()}">
                                        <div class="row">
                                            <div class="col form-group">
                                                <textarea name="commentText" class="form-control" rows="5" placeholder="Your Comment"></textarea>
                                            </div>
                                        </div>

                                        <div class="text-center">
                                            <button type="submit" class="btn btn-primary">Post Comment</button>
                                        </div>
                                    </form>

                                </div>

                            </div>

                        </section><!-- /Blog Comments Section -->

                    </div>

                    <div class="col-lg-4 sidebar">

                        <div class="widgets-container">

                            <!-- Search Widget -->
                            <div class="search-widget widget-item">

                                <h3 class="widget-title">Search</h3>
                                <form action="">
                                    <input type="text">
                                    <button type="submit"><i class="bi bi-search"></i></button>
                                </form>

                            </div><!--/Search Widget -->

                            <!-- Categories Widget -->
                            <div class="categories-widget widget-item">

                                <h3 class="widget-title">Categories</h3>
                                <ul class="mt-3">
                                    <li><a href="#">Tiểu Thuyết <span>(25)</span></a></li>
                                    <li><a href="#">Khoa Học <span>(12)</span></a></li>
                                    <li><a href="#">Tài Chính <span>(5)</span></a></li>
                                    <li><a href="#">Đời Sống <span>(22)</span></a></li>
                                    <li><a href="#">Toán <span>(8)</span></a></li>
                                    <li><a href="#">Triết Học <span>(14)</span></a></li>
                                </ul>

                            </div><!--/Categories Widget -->

                            <!-- Recent Posts Widget -->
                            <div class="recent-posts-widget widget-item">

                                <h3 class="widget-title">Recent Posts</h3>

                                <div class="post-item">
                                    <img src="assets/img/blog/un-1.jpg" alt="" class="flex-shrink-0">
                                    <div>
                                        <h4><a href="blog-details.html">Cuộc sống và những điều nhỏ bé</a></h4>
                                        <time datetime="2020-01-01">Jan 1, 2020</time>
                                    </div>
                                </div><!-- End recent post item-->

                                <div class="post-item">
                                    <img src="assets/img/blog/un-2.jpg" alt="" class="flex-shrink-0">
                                    <div>
                                        <h4><a href="blog-details.html">Ẩm thực Việt Nam: Hương vị quê hương</a></h4>
                                        <time datetime="2020-01-01">Jan 1, 2020</time>
                                    </div>
                                </div><!-- End recent post item-->

                                <div class="post-item">
                                    <img src="assets/img/blog/un-3.jpg" alt="" class="flex-shrink-0">
                                    <div>
                                        <h4><a href="blog-details.html">Du lịch khám phá: Những điểm đến không thể bỏ qua</a></h4>
                                        <time datetime="2020-01-01">Jan 1, 2020</time>
                                    </div>
                                </div><!-- End recent post item-->

                                <div class="post-item">
                                    <img src="assets/img/blog/un-4.jpg" alt="" class="flex-shrink-0">
                                    <div>
                                        <h4><a href="blog-details.html">Sách và văn hóa đọc trong thời đại số</a></h4>
                                        <time datetime="2020-01-01">Jan 1, 2020</time>
                                    </div>
                                </div><!-- End recent post item-->

                                <div class="post-item">
                                    <img src="assets/img/blog/blog-recent-5.jpg" alt="" class="flex-shrink-0">
                                    <div>
                                        <h4><a href="blog-details.html">Chăm sóc sức khỏe: Bí quyết sống khỏe mỗi ngày</a></h4>
                                        <time datetime="2020-01-01">Jan 1, 2020</time>
                                    </div>
                                </div><!-- End recent post item-->

                            </div><!--/Recent Posts Widget -->

                        </div>

                    </div>

                </div>
            </div>

        </main>

        <!-- footer -->
        <div class="footer container-fluid text-dark mt-5 pt-5" style="background-color: #D2B48C">
            <div class="row px-xl-5 pt-5">
                <div class="col-lg-6 col-md-12 mb-5 pr-3 pr-xl-5">
                    <a href="">
                        <h1 class="mb-4 display-5 font-weight-semi-bold" style="color: white">Book E Shop</h1>
                    </a>
                    <p style="color: white">Cửa hàng sách online của chúng tôi cung cấp một loạt các đầu sách đa dạng, từ văn học cổ điển đến sách khoa học, đáp ứng mọi nhu cầu đọc của bạn. Bạn có thể dễ dàng tìm và mua những cuốn sách yêu thích từ mọi thể loại và chủ đề. Cảm ơn bạn đã ủng hộ cửa hàng sách của chúng tôi!</p>
                    <p class="mb-2" style="color: white"><i class="fa fa-map-marker-alt text-primary mr-3"></i>Hoa Lac, Ha Noi, VietNam</p>
                    <p class="mb-2" style="color: white"><i class="fa fa-envelope text-primary mr-3"></i>bes@gmail.com</p>
                    <p class="mb-0" style="color: white"><i class="fa fa-phone-alt text-primary mr-3"></i>+84 98 123 4567</p>
                </div>
                <div class="col-lg-6 col-md-12">
                    <h5 class="font-weight-bold mb-4" style="color: white">Quick Links</h5>
                    <div class="d-flex flex-column justify-content-start">
                        <a class="text-white mb-2" href="index.jsp"><i class="fa fa-angle-right mr-2"></i>Home</a>
                        <a class="text-white mb-2" href=""><i class="fa fa-angle-right mr-2"></i>Our Shop</a>
                        <a class="text-white mb-2" href=""><i class="fa fa-angle-right mr-2"></i>Shop Detail</a>
                        <a class="text-white mb-2" href="BookCart?service=showCart"><i class="fa fa-angle-right mr-2"></i>Shopping Cart</a>
                        <a class="text-white mb-2" href=""><i class="fa fa-angle-right mr-2"></i>Checkout</a>
                        <a class="text-white" href=""><i class="fa fa-angle-right mr-2"></i>Contact Us</a>

                    </div>
                </div>
            </div>
            <div class="row border-top border-light mx-xl-5 py-4">
                <div class="col-md-6 px-xl-0">
                    <p class="mb-md-0 text-center text-md-left" style="color: white">
                        &copy; 
                        <a class="text-white font-weight-semi-bold" href="Home?service=listAll" >BeS | Book E Shop</a>. All Rights Reserved. Designed
                        by BeS
                    </p>
                </div>
                <div class="col-md-6 px-xl-0 text-center text-md-right">
                </div>
            </div>
        </div>
        <!-- Scroll Top -->
        <a href="#" id="scroll-top" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

        <!-- Preloader -->
        <div id="preloader"></div>

        <!-- Vendor JS Files -->
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>
        <script src="assets/vendor/aos/aos.js"></script>
        <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
        <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="assets/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
        <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>

        <!-- Main JS File -->
        <script src="assets/js/main.js"></script>

    </body>

</html>