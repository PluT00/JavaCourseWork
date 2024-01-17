<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>Registration</title>
</head>
<body>
<div class="container">
    <div class="row">
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                    <img src="https://cdn2.iconfinder.com/data/icons/covid-19-2/64/20-World-512.png" alt="Bootstrap" width="30" height="30">
                    Airport
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/flights">Flights</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/planes">Planes</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/users">Users</a>
                        </li>
                    </ul>
                    <span class="navbar-text">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                    </span>
                </div>
            </div>
        </nav>
    </div>
    <div class="row mt-5 justify-content-center">
        <div class="col-lg-10 center">
            <h1 class="row my-5 mx-auto">Welcome to the Airport website!</h1>
            <h4>Here's everything you can control here:</h4>
            <ul>
                <li><h5><a href="${pageContext.request.contextPath}/flights">Flights</a></h5></li>
                <li><h5><a href="${pageContext.request.contextPath}/planes">Planes</a></h5></li>
                <li><h5><a href="${pageContext.request.contextPath}/users">Users</a></h5></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
