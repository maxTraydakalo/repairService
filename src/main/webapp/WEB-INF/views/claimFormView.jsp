<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <link href="<c:url value="css/claimForm.css"/>" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Claim Form</title>
</head>
<body>

<jsp:include page="includes/header.jsp"/>

<div class="col-sm-4 card card-body bg-light">
    <form accept-charset="UTF-8" action="${pageContext.request.contextPath}/claimForm" method="POST">

        <div class="form-group">
            <label for="nameOfClaim">Name of your claim</label>
            <input name="nameOfClaim" type="text" class="form-control" id="nameOfClaim" placeholder="Name of claim">
        </div>

        <div class="form-group">
            <div>
                <label for="claim">Your claim</label>
                <textarea class="form-control" id="claim" name="claim"
                          onkeypress="charcountupdate(this.value)"
                          placeholder="Type in your message" rows="5"></textarea>
            </div>
        </div>

        <div class="mt-3">
            <button class="btn btn-primary" type="submit">Add new claim</button>
            <div class="mt-375 float-right ">
                <span id="charcount">0 out of 400 characters</span>
            </div>
        </div>
    </form>
</div>

<script src="<c:url value="js/claimForm.js"/>"></script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/core.js"></script>
</body>
</html>