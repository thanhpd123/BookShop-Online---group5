<%-- 
    Document   : AboutUs
    Created on : Jun 13, 2024, 11:08:41 PM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="jakarta.servlet.http.HttpSession,java.util.Vector, entity.Book, entity.Account"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Về Chúng Tôi</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
        <style>
            .dropbtn {
                background-color: #4CAF50;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown-content a:hover {
                background-color: #f1f1f1
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropdown:hover .dropbtn {
                background-color: #3e8e41;
            }

            .logo{
                width: 15%;
                height: auto;
                display: flex;
                justify-content: center;
                margin-left: auto;
                margin-right: auto;
            }

            .searchbar{
                margin-top: auto;
                margin-bottom: auto;
            }

            #mybar{
                width: 1300px;
                display: inline;

            }

            .sidebar{
                width: 200px;
                margin-left: 30%;
            }

            #cate{
                padding-top: 20px;
                padding-bottom: 20px;
                margin: 0 auto;
                text-align: center;
                border-bottom: solid 1px;
                border-color: #D3D3D3;
            }

            #sidebar{
                float: left;
                height: 540px;
                border-bottom: solid 1px;
                border-right: solid 1px;

            }

            #sidebar-list{
                list-style-type: none;
                padding-top: 10px;
                margin-bottom: 10px;
            }

            .menu{
                display: flex;
                padding: 0;
                border-bottom: solid 1px;
                border-color: #D3D3D3;
            }

            #element{
                margin-left: 100px;
                list-style-type: none;
                display: flex;
            }

            #item{
                margin-top: 5px;
                padding-right: 15px;
                margin: 0 40px;
                font-size: 110%;

            }

            a{
                text-decoration: none;
            }

            nav{
                position: absolute;
                right: 0;
                display: flex;
                justify-content: flex-end;
            }

            .navbar{
                padding-top: 20px;
                padding-bottom: 20px;
                margin: 0 auto;
                text-align: center;
                border: solid 1px;
                border-color: #D3D3D3;
            }

            #navbar-list{

                margin: 0 50px;
            }

            .button1{
                border-color: white;
                color: white;
                background-color: #E5D3B3;
            }

            .button1:hover {
                border-color: #D2B48C;
                background-color: white;
                color: #E5D3B3;
            }

            .button2{
                border-color: white;
                color: white;
                background-color: #E5D3B3;
            }

            .button2:hover {
                border-color: #D2B48C;
                background-color: white;
                color: #E5D3B3;
            }

        </style>
    </head>
    <body>
        <%
            Account acc = (Account)session.getAttribute("acc");
        %>
        <!-- menu -->
        <div class="menu container-fluid" style="height: 90px; background-color: #E5D3B3">
            <div class="row">
                <!-- logo -->
                <div class="cl-lg-3 mt-auto mb-auto">
                    <a href="Home?service=listAll"><img class="logo" src = "${pageContext.request.contextPath}/assets/logo.PNG" alt="Logo"></a>
                </div>

                <!-- search bar -->
                <div class="cl-lg-4 d-flex justify-content-center align-items-center ml-5">
                    <form action="BookController?service=search&page=1" method="POST">
                        <div style="display: inline-block"><input type="text" placeholder="Search Book Name" name="Name" style="width: 380px; height: 35px; color: #664229"></div>
                        <div style="display: inline-block"><button type="submit" class="" style="height: 35px; width: 50px; border-color: white; color: white; background-color: #E5D3B3"><i class="bi bi-search"></i></button></div>
                    </form>
                </div>

                <!-- menu item -->
                <div class="cl-lg-4 d-flex justify-content-center align-items-center">
                    <%
            String logged = (String)session.getAttribute("msg1");
            if (logged != null) {
                    %>
                    <nav>
                        <ul id="element">
                            <div class="dropdown">
                                <li id="item"><a href="userprofile" style="color: #664229">
                                        <i class="bi bi-person h3"></i>
                                    </a></li>
                                <div class="dropdown-content" style="background-color: #E5D3B3">
                                    <div><a href="userprofile" style="color: #664229"><i class="bi bi-person h5"></i> <%=acc.getLastName()%></a></div>
                                    <div><a href="OrderController" style="color: #664229"><i class="bi bi-wallet2"></i> Đơn Hàng</a></div>
                                    <div><a href="LogOut" style="color: #664229"><i class="bi bi-box-arrow-right h5"></i> Đăng Xuất</a></div>
                                </div>
                            </div>
                            <li id="item"><a href="BookCart?service=showCart&userID=<%=acc.getUserID()%>" style="color: #664229">
                                    <i class="bi bi-cart h4"></i>
                                </a></li>
                            <li id="item" style="color: #E5D3B3">m
                                </a></li>
                            <li id="item" style="color: #E5D3B3">m
                                </a></li>
                        </ul>
                    </nav>
                    <%
                        }
                        if(logged==null){
                    %>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-4">
                            </div>
                            <div class="col-3">
                                <a href="register"><button class="button1 mt-3 mb-3 ml-5" style="font-size:18px; width: 120px; height: 50px;">Đăng Ký</button></a>
                            </div>
                            <div class="col-1">
                            </div>
                            <div class="col-3">
                                <a href="login"><button class="button2 mt-3 mb-3 ml-5" style="font-size:18px; width: 120px; height: 50px;">Đăng Nhập</button></a>
                            </div>
                            <div class="col-1">
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>

                </div>
            </div>
        </div>

        <!-- content -->        
        <div class="container-fluid">
            <div class="row">
                <div class="col-2">
                </div>
                <div class="col-8">
                    <div class="navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="navbar-nav mr-auto py-0 ml-auto justify-content-center">
                            <div class="navbar">
                                <div>
                                    <a id="navbar-list" href="Home?service=listAll" style="color: black">Trang Chủ</a>
                                    <a id="navbar-list" href="AboutUs" style="color: black">Về Chúng tôi</a>
                                    <a id="navbar-list" href="BookController?service=listAll" style="color: black">Sách</a>
                                    <div class="dropdown">
                                        <p id="navbar-list" href="" style="color: black">Thể Loại</p>
                                        <div class="dropdown-content">
                                            <a href="BookController?service=bookByCat&cat=CAT1&page=1">Tiểu Thuyết</a>
                                            <a href="BookController?service=bookByCat&cat=CAT2&page=1">Khoa Học</a>
                                            <a href="BookController?service=bookByCat&cat=CAT3&page=1">Tài Chính</a>
                                            <a href="BookController?service=bookByCat&cat=CAT4&page=1">Tự Lực</a>
                                            <a href="BookController?service=bookByCat&cat=CAT5&page=1">Pháp Luật</a>
                                            <a href="BookController?service=bookByCat&cat=CAT6&page=1">Lập Trình</a>
                                            <a href="BookController?service=bookByCat&cat=CAT7&page=1">Tâm Lý Học</a>
                                        </div>
                                    </div>
                                    <a id="navbar-list" href="" style="color: black">Liên Hệ</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-2">
                </div>
            </div>
            <div class="row mt-3 mb-3">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8 text-center">
                    <h2 style="color: #664229">About Us</h2>
                </div>
                <div class="col-lg-2">
                </div>
            </div>
            <div class="row mb-5">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-4">
                    <p>
                        Trang web "BeS" cam kết mang đến cho người dùng không chỉ là nơi mua sách trực tuyến, mà còn là một nguồn cảm hứng vô tận về tri thức và giáo dục. Sứ mệnh của chúng tôi là khơi dậy đam mê đọc sách từ khi còn nhỏ, bằng cách cung cấp một thư viện sách đa dạng và phong phú, phù hợp với mọi độ tuổi và sở thích.
                        <br>
                        <br>
                        Tại "BeS", chúng tôi tập trung vào việc chọn lọc các tựa sách chất lượng, từ sách giáo khoa, sách truyện tranh đến các tác phẩm văn học kinh điển, đảm bảo rằng mỗi cuốn sách đều mang lại giá trị và ý nghĩa sâu sắc cho người đọc. Chúng tôi đặc biệt chú trọng vào sự phát triển toàn diện của trẻ em thông qua việc khuyến khích đọc sách và khai thác tiềm năng sáng tạo của từng độc giả nhí.
                        <br>
                        <br>
                        Ngoài việc cung cấp các sản phẩm sách chất lượng, "BeS" cũng là nơi để phụ huynh và giáo viên có thể tìm kiếm các tài liệu hữu ích, từ sách hướng dẫn nuôi dạy con cái đến những phương pháp giảng dạy mới nhất. Chúng tôi tin rằng với việc khai thác sức mạnh của tri thức, mỗi đứa trẻ có thể phát triển toàn diện và thành công trong tương lai.
                        <br>
                        <br>
                        Hơn thế nữa, "BeS" cam kết về sự tiện lợi và an toàn trong trải nghiệm mua sắm trực tuyến. Với giao diện thân thiện và dịch vụ khách hàng nhiệt tình, chúng tôi mong muốn trở thành đối tác tin cậy của mọi gia đình, giúp họ dễ dàng tiếp cận với những kho tàng tri thức bao la chỉ qua một vài thao tác đơn giản trên "BeS".</p>
                </div>
                <div class="col-lg-4">
                    <img class="d-flex ml-auto mr-auto pt-5" src="${pageContext.request.contextPath}/assets/bookAes.jpg" style="width: auto; height: 500px;">
                </div>
                <div class="col-lg-2">
                </div>
            </div>
            <div class="row mt-3 mb-0" style="background-color: #E5D3B3; height: 100px">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8">
                </div>
                <div class="col-lg-2">
                </div>
            </div>
            <div class="row pt-0" style="background-color: #E5D3B3;">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-2 text-center">
                    <h5 style="font-weight: normal; color: white">Hơn</h5>
                    <h4 style="font-weight: normal; color: white">100 +</h4>
                    <h5 style="font-weight: normal; color: white">Đầu Sách</h5>
                </div>
                <div class="col-lg-2 text-center">
                    <h5 style="font-weight: normal; color: white">Nhiều</h5>
                    <h4 style="font-weight: normal; color: white">Thể Loại</h4>
                    <h5 style="font-weight: normal; color: white">Khác Nhau</h5>
                </div>
                <div class="col-lg-2 text-center">
                    <h5 style="font-weight: normal; color: white">Nhiều</h5>
                    <h4 style="font-weight: normal; color: white">Tác Giả</h4>
                    <h5 style="font-weight: normal; color: white">Nổi Tiếng</h5>
                </div>
                <div class="col-lg-2 text-center">
                    <h5 style="font-weight: normal; color: white">Nhiều</h5>
                    <h4 style="font-weight: normal; color: white">Tác Phẩm</h4>
                    <h5 style="font-weight: normal; color: white">Nước ngoài</h5>
                </div>
                <div class="col-lg-2">
                </div>
            </div>
            <div class="row mb-3"  style="background-color: #E5D3B3; height: 100px">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8">
                </div>
                <div class="col-lg-2">
                </div>
            </div>
            <div class="row mt-3 mb-3">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8 text-center">
                    <p>

                    </p>
                </div>
                <div class="col-lg-2">
                </div>
            </div>
        </div>

        <!-- footer -->
        <div class="footer container-fluid text-dark pt-3" style="background-color: #D2B48C">
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



        <br>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
