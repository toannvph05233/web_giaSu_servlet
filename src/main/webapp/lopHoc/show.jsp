<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        body {
            background: -webkit-linear-gradient(left, #3931af, #00c6ff);
        }

        .emp-profile {
            padding: 3%;
            margin-top: 3%;
            margin-bottom: 3%;
            border-radius: 0.5rem;
            background: #fff;
        }

        .profile-img {
            text-align: center;
        }

        .profile-img img {
            width: 70%;
            height: 100%;
        }

        .profile-img .file {
            position: relative;
            overflow: hidden;
            margin-top: -20%;
            width: 70%;
            border: none;
            border-radius: 0;
            font-size: 15px;
            background: #212529b8;
        }

        .profile-img .file input {
            position: absolute;
            opacity: 0;
            right: 0;
            top: 0;
        }

        .profile-head h5 {
            color: #333;
        }

        .profile-head h6 {
            color: #0062cc;
        }

        .profile-edit-btn {
            border: none;
            border-radius: 1.5rem;
            width: 70%;
            padding: 2%;
            font-weight: 600;
            color: #6c757d;
            cursor: pointer;
        }

        .proile-rating {
            font-size: 12px;
            color: #818182;
            margin-top: 5%;
        }

        .proile-rating span {
            color: #495057;
            font-size: 15px;
            font-weight: 600;
        }

        .profile-head .nav-tabs {
            margin-bottom: 5%;
        }

        .profile-head .nav-tabs .nav-link {
            font-weight: 600;
            border: none;
        }

        .profile-head .nav-tabs .nav-link.active {
            border: none;
            border-bottom: 2px solid #0062cc;
        }

        .profile-work {
            padding: 14%;
            margin-top: -15%;
        }

        .profile-work p {
            font-size: 12px;
            color: #818182;
            font-weight: 600;
            margin-top: 10%;
        }

        .profile-work a {
            text-decoration: none;
            color: #495057;
            font-weight: 600;
            font-size: 14px;
        }

        .profile-work ul {
            list-style: none;
        }

        .profile-tab label {
            font-weight: 600;
        }

        .profile-tab p {
            font-weight: 600;
            color: #0062cc;
        }
    </style>
</head>
<body>
<div class="container emp-profile">

    <c:if test="${account.role == 'gv'}">
        <h1 style="padding-left: 60px">Lớp học của tôi.</h1>
        <hr>
        <a href="/managerLopHoc?action=create" type="button" class="btn btn-success">Create</a>
        <br>
    </c:if>
    <c:if test="${account.role == 'admin'}">
        <h1 style="padding-left: 60px">Lớp Học Trong Dự Án.</h1>
    </c:if>

    <%--    Tab--%>
    <br>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="/managerLopHoc">Lớp học</a>
        </li>

    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Date</th>
                <th>Price</th>
                <th>Price GS</th>
                <th>Content</th>
                <th>Img</th>
                <th>Name GS</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="lh" items="${lopHocs}">
                <tr>
                    <td>${lh.id}</td>
                    <td>${lh.tenLopHoc}</td>
                    <td>${lh.ngayHoc}</td>
                    <td>${lh.hocPhi}</td>
                    <td>${lh.phiGiaSu}</td>
                    <td>${lh.moTa}</td>
                    <td><img src="${lh.hinhAnh}" width="200" height="170"></td>
                    <td>${lh.usernameGiaSu}</td>
                    <td><a href="/managerLopHoc?action=edit&id=${lh.id}" type="button"
                           class="btn btn-warning">Edit</a></td>
                    <td><a onclick="deleteLH(${lh.id})" type="button"
                           class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
        <br>
    </div>
</div>

<%--    End Tab--%>


</div>

</body>
</html>
<script>
    function deleteLH(idLH) {
        let check = confirm('Xóa lớp học!')
        if (check) {
            location.href = "/managerLopHoc?action=delete&id=" + idLH;
        }
    }

</script>
