<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<jsp:include page="includes/header.jsp"/>

<h3>Hello: ${user.login}</h3>

User Name: <b>${user.login}</b>
<br />


</body>
</html>