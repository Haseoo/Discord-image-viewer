<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Images from ${serverName}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>

<body>
<div class="col-lg-8 mx-auto p-3 py-md-5">
    <header class="d-flex align-items-center pb-3 mb-3 justify-content-center border-bottom">
        <a href="/dsc-viewer" class="d-flex align-items-center text-dark text-decoration-none">
            <span class="fs-3 text-center">Images from ${serverName}</span>
        </a>
    </header>
    <nav class="jumbotron text-center">
        <div class="container">
            <p class="lead text-muted">Select channel</p>
            <div style="padding: 10px 0" class="dropdown">
                <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    ${channel != '' ? channel : 'Nie wybrano'}
                </a>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                    <c:forEach items="${channels}" var="item">
                        <li><a class="dropdown-item" href="?channel=${item}">${item}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </nav>
    <c:if test="${channel != ''}">
        <main class="container">
            <div class="row d-flex align-items-center">
                <c:forEach items="${images}" var="image">
                    <div class="col-md-4">
                        <div class="card mb-4 box-shadow">
                            <a target="_blank" href="${image.imageUrl}">
                                <img class="card-img-top"
                                     alt="${'image by'.concat(image.username)}"
                                     style="height: 100%; width: 100%; max-height: 300px; object-fit: contain; display: block;"
                                     src="${image.imageUrl}"
                                     data-holder-rendered="true">
                            </a>
                            <div class="card-body container">
                                <div>Sent by ${image.username}</div>
                                <div class="text-secondary text-end fst-italic">${image.sendTime}</div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </main>
        <div class="d-flex justify-content-between align-items-center">
            <a href="?chanel=${channel}&page=${pageNumber - 1}"
               class="${pageNumber == 1 ? '' : 'disabled'} btn btn-primary disabled">Previous</a>
            <span>${pageNumber}/${totalPages}</span>
            <a href="?chanel=${channel}&page=${pageNumber + 1}"
               class="${pageNumber != totalPages ? '' : 'disabled'} btn btn-primary">Next</a>
        </div>
    </c:if>
</div>
</body>
</html>