<%--
  Created by IntelliJ IDEA.
  User: maxtr
  Date: 30.09.2019
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body class="m-3">
<div class="container">

    <div class="row">
        <jsp:include page="includes/header.jsp"/>
    </div>
    <form method="POST" action="${pageContext.request.contextPath}/manageTask?id=${claim.id}">
    <div class="row">
        <table class="table table-striped table-bordered table-sm">
            <tr>
                <th>id</th>
                <th>name</th>
                <th>claim</th>
                <th>completed</th>
                <th>userId</th>
                <th>managerId</th>
                <th>price</th>
                <th>masterId</th>
                <th>rejection</th>
            </tr>

            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/manageTask?id=${claim.id}">
                        ${claim.id}
                    </a>
                </td>
                <td>${claim.name}</td>
                <td>${claim.claim}</td>
                <td>${claim.completed}</td>
                <td>${claim.userId}</td>
                <td>${claim.managerId}</td>
                <td><input type="text" name="price" value= "${claim.price}"/></td>
                <td><input type="text" name="masterId" value= "${claim.masterId}"/></td>
                <td><input type="text" name="rejection" value= "${claim.rejection}"/></td>
            </tr>
        </table>
        <input type="submit" value= "Submit" />
    </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
</body>
</html>
