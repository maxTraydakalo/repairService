<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
<h3> Student information: </h3>
<form action="${pageContext.request.contextPath}/claim" method="post">
    <h1>Hello</h1>
    <p>
        <label for="name">What's your name?</label>
        <input id="name" name="name" >
        <span class="error">${messages.name}</span>
    </p>
    <p>
        <label for="age">What's your age?</label>
        <input id="age" name="claim" >
        <span class="error">${messages.age}</span>
    </p>
    <p>
        <input type="submit">
        <span class="success">${messages.success}</span>
    </p>
</form>

<h1></h1>
</body>
</html>


