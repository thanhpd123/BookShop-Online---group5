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
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Post detail</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Checkout</p>
                </div>
            </div>
        </div>
        <c:if test="${blogResponseDTO ne null}">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2">
                        <div class="card border-0 p-5">
                            <h4 class="mb-4">View details Blog Post</h4>
                            <h3></h3>
                            <form action="blogdetailcontroller" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="title">Title:</label>
                                    <input type="text" class="form-control" id="title" value="${blogResponseDTO.getTitle()}" name="title" required>
                                    <input style="display: none" type="text" class="form-control" id="title" value="${blogResponseDTO.getBlogsID()}" name="blogid" required>
                                </div>
                                <div class="form-group">
                                    <label for="brief">Brief Information:</label>
                                    <input type="text" class="form-control" id="brief" value="${blogResponseDTO.getBriefInformation()}" name="brief" required>
                                </div>
                                <div class="form-group">
                                    <label for="content">Content:</label>
                                    <div id="descriptionBlog" ></div>
                                </div>
                                <div class="form-group">
                                    <label for="status">Status:</label>
                                    <select class="form-control" id="status" name="status" required>
                                        <option value="0" ${blogResponseDTO.isIsPublished() eq false ? 'selected' : ''}>Off</option>
                                        <option value="1" ${blogResponseDTO.isIsPublished() eq true ? 'selected' : ''}>On</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="thumbnail">Thumbnail:</label>
                                    <input type="file" class="form-control" id="thumbnail" name="image" class="img-fluid responsive-image" accept="image/*">
                                </div>
                                <div class="mt-2">
                                    <img src="${blogResponseDTO.getBlogImg()}" alt="Current Additional Image" class="img-fluid" id="currentAdditionalImage">
                                </div>
                                <div style="padding-top: 20px; width: 150px; margin-left: 40%; margin-right: 40%" class="form-group">
                                    <button type="submit" class="btn btn-primary btn-block">Update Post</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
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

        <script>
            tinymce.init({
                selector: '#descriptionBlog',
                height: 300,
                language: 'vi',
                plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount linkchecker',
                toolbar: 'undo redo | formatselect | bold italic backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat | help',
                setup: function (editor) {
                    editor.on('init', function (e) {
                        editor.setContent(`${blogResponseDTO.getContent()}`);
                    });
                }
            });
        </script>
    </body>

</html>
