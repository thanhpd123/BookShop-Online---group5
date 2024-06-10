<%-- 
    Document   : index
    Created on : May 17, 2024, 11:04:16 PM
    Author     : Dung Dinh
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession,java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="CSS/style.css">
    </head>
    <body>
        <%
            String logged = (String)session.getAttribute("logged");
            if(logged!=null) {
             String user = ((UserInfo)session.getAttribute("userInfo")).getSsn();
            String FullName = ((UserInfo)session.getAttribute("userInfo")).getFullName();
        %>
        <div>
            <p> Welcome: <%=FullName%></p>
        </div>
        <!<!-- menu -->
        <div class="menu">

            <!-- logo -->
            <div>
                <img class="logo" src = "${pageContext.request.contextPath}/assets/logo.PNG" alt="Logo"/>
            </div>

            <!<!-- menu item -->
            <nav>
                <ul id="element">
                    <li id="item"><a href="">Account</a></li>
                    <li id="item"><a href="">Cart</a></li>
                    <li id="item"><a href="">Login</a></li>
                </ul>
            </nav>
        </div>

        <!-- nav bar -->
        <div class="navbar">
            <a id="navbar-list" href="#home">Home</a>
            <a id="navbar-list" href="#about">About</a>
            <a id="navbar-list" href="#services">Category</a>
            <a id="navbar-list" href="#contact">Contact</a>
        </div>

        <!-- sidebar -->
        <div id="sidebar">


        </div>

        <!-- slideshow -->
        <div class="slideshow-container">
            <div class="mySlides fade">
                <div class="numbertext">1 / 3</div>
                <img src="${pageContext.request.contextPath}/assets/featured1.PNG" style="width:100%">
                <div class="text">Caption Text</div>
            </div>

            <div class="mySlides fade">
                <div class="numbertext">2 / 3</div>
                <img src="${pageContext.request.contextPath}/assets/featured2.PNG" style="width:100%">
                <div class="text">Caption Two</div>
            </div>

            <!-- Next and previous buttons -->
            <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
            <a class="next" onclick="plusSlides(1)">&#10095;</a>
        </div>



        <script>
            let slideIndex = 1;
            showSlides(slideIndex);

            function plusSlides(n) {
                showSlides(slideIndex += n);
            }

            function currentSlide(n) {
                showSlides(slideIndex = n);
            }

            function showSlides(n) {
                let i;
                let slides = document.getElementsByClassName("mySlides");
                let dots = document.getElementsByClassName("dot");
                if (n > slides.length) {
                    slideIndex = 1
                }
                if (n < 1) {
                    slideIndex = slides.length
                }
                for (i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none";
                }
                for (i = 0; i < dots.length; i++) {
                    dots[i].className = dots[i].className.replace(" active", "");
                }
                slides[slideIndex - 1].style.display = "block";
                dots[slideIndex - 1].className += " active";
            }
        </script>
        <%
            }
        %>
        <br>
</body>
</html>
