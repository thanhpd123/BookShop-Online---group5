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
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Dashboard</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Checkout</p>
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <div class="layout-specing">
                <div class="d-md-flex justify-content-between">
                    <h5 class="mb-0">Marketing Dashboard </h5>

                    <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                        <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                            <li class="breadcrumb-item active" aria-current="page">Dashboard</li>

                        </ul>
                    </nav>
                </div>
                <!--start select date range-->
                <div class="content">
                    <div class="container text-left" style="margin-top: 20px;">
                        <div class="row justify-content-center">
                            <div class="col-lg-10">
                                <form action="dashboardinformationformarketing" class="row" id="dateForm" method="post">
                                    <div class="col-md-6">
                                        <div class="form-group col-sm-10" style=" display: flex">
                                            <label for="input_from" class="col-sm-3" style="margin: auto 0">From</label>
                                            <input type="date" class="form-control col-xs-12" id="input_from" name="from_date">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group col-sm-10" style=" display: flex" >
                                            <label for="input_from" class="col-sm-3" style="margin: auto 0">To</label>
                                            <input type="date" class="form-control col-xs-12" id="input_to" name = "to_date" readonly>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <form action="dashboardformarketing" method="GET" id="sub">
                    <input type="hidden" title="hidden_param1" name="hidden_param1" id="hidden_param1" value="2023-01-01">
                    <input type="hidden" title="hidden_param2" name="hidden_param2" id="hidden_param2" value="2023-01-01">
                    <input type="submit" style="display: none;" id="submit"/>
                    <!-- Các trường input khác -->
                </form>
                <script>
                    // Lắng nghe sự kiện khi người dùng thay đổi giá trị của input
                    document.getElementById('input_from').addEventListener('change', updateHiddenParams);
                    document.getElementById('input_to').addEventListener('change', updateHiddenParams);

                    // Hàm cập nhật giá trị của các trường input ẩn
                    function updateHiddenParams() {
                        document.getElementById('hidden_param1').value = document.getElementById('input_from').value;
                        document.getElementById('hidden_param2').value = document.getElementById('input_to').value;
                        // Gửi form
                        document.getElementById('sub').submit();
                    }
                </script>

                <script>
                    // Lấy ngày hiện tại và lùi lại 7 ngày
                    var toDate = new Date();
                    var fromDate = new Date(toDate.getTime() - (7 * 24 * 60 * 60 * 1000)); // 7 ngày trước

                    // Chuyển đổi ngày thành chuỗi với định dạng 'yyyy-MM-dd' để truyền vào trường input
                    var formattedFromDate = fromDate.toISOString().split('T')[0];
                    var formattedToDate = toDate.toISOString().split('T')[0];

                    // Đặt giá trị mặc định cho các trường input
                    if (!sessionStorage.getItem('fromDate') && !sessionStorage.getItem('toDate')) {
                        document.getElementById('input_from').value = formattedFromDate;
                        document.getElementById('input_to').value = formattedToDate;
                    } else {
                        document.getElementById('input_from').value = sessionStorage.getItem('fromDate');
                        document.getElementById('input_to').value = sessionStorage.getItem('toDate');
                        sessionStorage.clear();
                    }

                    // Lắng nghe sự kiện change trên các trường input ngày tháng
                    document.getElementById('input_from').addEventListener('change', function () {
                        updateToDate(); // Cập nhật ngày cho toDate
                        checkAndSubmitForm(); // Kiểm tra và gửi form
                    });

                    function updateToDate() {
                        // Lấy ngày từ input
                        var fromDateValue = document.getElementById('input_from').value;

                        // Chuyển đổi ngày từ chuỗi về kiểu Date
                        var fromDate = new Date(fromDateValue);

                        // Tính toán ngày cho toDate: 7 ngày sau fromDate
                        var toDate = new Date(fromDate.getTime() + (7 * 24 * 60 * 60 * 1000));

                        // Chuyển đổi ngày thành chuỗi với định dạng 'yyyy-MM-dd' để truyền vào trường input
                        var formattedToDate = toDate.toISOString().split('T')[0];

                        // Gán giá trị cho trường input toDate
                        document.getElementById('input_to').value = formattedToDate;
                    }

                    document.getElementById('input_to').addEventListener('change', function () {
                        checkAndSubmitForm(); // Kiểm tra và gửi form
                    });

                    function checkAndSubmitForm() {
                        var fromDate = document.getElementById('input_from').value;
                        var toDate = document.getElementById('input_to').value;

                        // Kiểm tra xem cả hai trường input đều đã có giá trị hay không
                        if (fromDate && toDate && fromDate !== formattedFromDate && toDate !== formattedToDate) {
                            // Lưu giá trị vào sessionStorage
                            sessionStorage.setItem('fromDate', fromDate);
                            sessionStorage.setItem('toDate', toDate);

                            // Gửi form
                            document.getElementById('dateForm').submit();
                        }
                    }
                </script>

                <div class="container-fluid">
                    <div class="layout-specing" style="padding-top: 0px !important">
                        <div class="row">
                            <!--Tổng post-->
                            <div class="col-xl-3 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-file-alt h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2" style="margin-left: 10px">
                                            <h5 class="mb-0">${total_blog}</h5>
                                            <p class="text-muted mb-0">Posts</p>
                                        </div>
                                    </div>
                                </div>
                            </div><!--end col-->
                            <!--tổng products-->
                            <div class="col-xl-3 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-apps h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2" style="margin-left: 10px">
                                            <h5 class="mb-0">${total_product}</h5>
                                            <p class="text-muted mb-0">Products</p>
                                        </div>
                                    </div>
                                </div>
                            </div><!--end col-->
                            <!--tổng customer-->
                            <div class="col-xl-3 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-user-check h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2" style="margin-left: 10px">
                                            <h5 class="mb-0">${total_account}</h5>
                                            <p class="text-muted mb-0">Customers</p>
                                        </div>
                                    </div>
                                </div>
                            </div><!--end col-->
                            <!--tổng feedback-->
                            <div class="col-xl-3 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-feedback h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2" style="margin-left: 10px">
                                            <h5 class="mb-0">${total_feedback}</h5>
                                            <p class="text-muted mb-0">Feedback</p>
                                        </div>
                                    </div>
                                </div>
                            </div><!--end col-->
                        </div><!--end row-->
                        <!--Test biểu đồ-->
                        <div class="row">
                            <div class="col-xl-12 col-lg-7 mt-4">
                                <canvas id="myChart"></canvas>
                                <script>
                                    // Xử lý sự kiện khi người dùng thay đổi phạm vi ngày
                                    document.getElementById('input_from').addEventListener('change', updateChartLabels);

                                    let myChart = document.getElementById('myChart').getContext('2d');
                                    // Khai báo biến để lưu trữ mảng nhận được từ servlet
                                    let dataArrayFromServlet = [];
                                    var fromDate = document.getElementById('input_from').value;
                                    var toDate = document.getElementById('input_to').value;
                                    // Gửi yêu cầu đến servlet để nhận mảng
                                    let xhr = new XMLHttpRequest();
                                    xhr.open('GET', 'dashboardformarketing?hidden_param1=' + fromDate + "&hidden_param2=" + toDate, true);
                                    xhr.onreadystatechange = function () {
                                        if (xhr.readyState === 4 && xhr.status === 200) {
                                            // Parse dữ liệu JSON trả về từ servlet thành mảng JavaScript
                                            let responseData = JSON.parse(xhr.responseText);
                                            // Lưu trữ dữ liệu nhận được
                                            dataArrayFromServlet = responseData.listDay;

                                            let staticsCustomerData = responseData.staticsCustomer;
                                            let staticsPostData = responseData.staticsPost;
                                            let staticsProductData = responseData.staticsProduct;
                                            let staticsFeedbackData = responseData.staticsFeedback;

                                            // Sau khi nhận được dữ liệu, vẽ biểu đồ
                                            drawChart(dataArrayFromServlet, staticsCustomerData, staticsPostData, staticsProductData, staticsFeedbackData);
                                        }
                                    };

                                    // Gửi yêu cầu
                                    xhr.send();

                                    function drawChart(labels, staticsCustomerData, staticsPostData, staticsProductData, staticsFeedbackData) {
                                        // Global Options
                                        Chart.defaults.global.defaultFontFamily = 'Lato';
                                        Chart.defaults.global.defaultFontSize = 16;
                                        Chart.defaults.global.defaultFontColor = '#666';

                                        let ctx = document.getElementById('myChart').getContext('2d');
                                        let massPopChart = new Chart(ctx, {
                                            type: 'line', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
                                            data: {
                                                labels: labels,
                                                datasets: [
                                                    {
                                                        label: 'Posts',
                                                        data: staticsPostData,
                                                        backgroundColor: 'antiquewhite',
                                                        borderWidth: 1,
                                                        borderColor: '#777',
                                                        hoverBorderWidth: 2,
                                                        hoverBorderColor: '#000',
                                                        tension: 0
                                                    },
                                                    {
                                                        label: 'Products',
                                                        data: staticsProductData,
                                                        backgroundColor: 'thistle',
                                                        borderWidth: 1,
                                                        borderColor: '#777',
                                                        hoverBorderWidth: 2,
                                                        hoverBorderColor: '#000',
                                                        tension: 0
                                                    },
                                                    {
                                                        label: 'Customers',
                                                        data: staticsCustomerData,
                                                        backgroundColor: '#33FFFF',
                                                        borderWidth: 1,
                                                        borderColor: '#777',
                                                        hoverBorderWidth: 2,
                                                        hoverBorderColor: '#000', 
                                                        tension: 0
                                                    },
                                                    {
                                                        label: 'Feedbacks',
                                                        data: staticsFeedbackData,
                                                        backgroundColor: '#00FF00',
                                                        borderWidth: 1,
                                                        borderColor: '#777',
                                                        hoverBorderWidth: 2,
                                                        hoverBorderColor: '#000',
                                                        tension: 0
                                                    }
                                                ]
                                            },
                                            options: {
                                                title: {
                                                    display: true,
                                                    text: 'The chart shows the ratio',
                                                    fontSize: 25
                                                },
                                                legend: {
                                                    display: true,
                                                    position: 'right',
                                                    labels: {
                                                        fontColor: '#000'
                                                    }
                                                },
                                                layout: {
                                                    padding: {
                                                        left: 50,
                                                        right: 0,
                                                        bottom: 0,
                                                        top: 0
                                                    }
                                                },
                                                tooltips: {
                                                    enabled: true
                                                },
                                                scales: {
                                                    yAxes: [{
                                                            ticks: {
                                                                beginAtZero: true,
                                                                suggestedMin: 0
                                                            }
                                                        }]
                                                }
                                            }
                                        });
                                    }
                                    // Hàm để cập nhật label của biểu đồ dựa trên phạm vi ngày mới
                                    function updateChartLabels() {
                                        var fromDate = document.getElementById('input_from').value;
                                        var toDate = document.getElementById('input_to').value;
                                        // Tạo một đối tượng FormData để gửi dữ liệu form
                                        var formData = new FormData();
                                        formData.append('from_date', fromDate);
                                        formData.append('to_date', toDate);

                                        // Tạo một đối tượng XMLHttpRequest
                                        var xhr = new XMLHttpRequest();

                                        // Thiết lập request method và URL
                                        xhr.open('GET', 'dashboardformarketing?hidden_param1=' + fromDate + "&hidden_param2=" + toDate, true);

                                        // Thiết lập header nếu cần
                                        // xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

                                        // Xử lý sự kiện khi request hoàn thành
                                        xhr.onreadystatechange = function () {
                                            if (xhr.readyState === XMLHttpRequest.DONE) {
                                                if (xhr.status === 200) {
                                                    // Nếu request thành công, cập nhật biểu đồ
                                                    let newLabels = JSON.parse(xhr.responseText);
                                                    massPopChart.data.labels = newLabels;
                                                    massPopChart.update();

                                                    // Cập nhật giá trị của hidden_param1 và hidden_param2
                                                    document.getElementById('hidden_param1').value = fromDate;
                                                    document.getElementById('hidden_param2').value = toDate;
                                                } else {
                                                    // Nếu request không thành công, log lỗi
                                                    console.error('Lỗi khi gửi yêu cầu Ajax');
                                                }
                                            }
                                        };
                                        // Gửi request với dữ liệu form
                                        xhr.send(formData);
                                    }


                                </script>
                            </div>
                        </div>
                    </div>
                </div><!--end container-->
            </div>
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
