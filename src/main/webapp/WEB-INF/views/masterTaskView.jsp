<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Task</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>

<body class="m-3">
<div class="container">

    <div class="row">
        <jsp:include page="_menu.jsp"></jsp:include>
    </div>

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
            </tr>

            <c:forEach items="${claimsOfMaster}" var="claimOfMaster">
                <tr>
                    <td>${claimOfMaster.id}</td>
                    <td>${claimOfMaster.name}</td>
                    <td>${claimOfMaster.claim}</td>
                    <td>${claimOfMaster.completed}</td>
                    <td>${claimOfMaster.userId}</td>
                    <td>${claimOfMaster.managerId}</td>
                    <td>${claimOfMaster.price}</td>
                    <td>${claimOfMaster.masterId}</td>
                    <form method="POST" action="${pageContext.request.contextPath}/masterTask?id=${claimOfMaster.id}">
                        <td><input type="submit" value="Complete"
                                <c:if test="${claimOfMaster.completed == true}">
                                    disabled="disabled"
                                </c:if>/>
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </table>

    </div>

    <div class="row justify-content-center">
        <div aria-label="Navigation for countries">
            <ul class="pagination   ">
                <li class="page-item
                    <c:if test="${currentPage == 1}"> disabled </c:if>">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/masterTask?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                </li>


                <c:if test="${currentPage >= 5 && currentPage < noOfPages - 4 }">
                    <c:set var="begin" scope="page" value="${currentPage-4}"/>
                    <c:set var="end" scope="page" value="${currentPage+4}"/>
                </c:if>
                <c:if test="${currentPage >= noOfPages - 4}">
                    <c:set var="begin" scope="page" value="${noOfPages-9}"/>
                    <c:set var="end" scope="page" value="${noOfPages}"/>
                </c:if>
                <c:if test="${currentPage < 5}">
                    <c:set var="begin" scope="page" value="1"/>
                    <c:set var="end" scope="page" value="10"/>
                </c:if>
                <c:if test="${noOfPages < 10}">
                    <c:set var="begin" scope="page" value="1"/>
                    <c:set var="end" scope="page" value="${noOfPages}"/>
                </c:if>

                <c:forEach begin="${begin}" end="${end}" var="i">
                    <c:choose>
                        <c:when test="${currentPage == i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="${pageContext.request.contextPath}/masterTask?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <li class="page-item
                    <c:if test="${currentPage == noOfPages}">disabled</c:if>">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/masterTask?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

</body>
</html>