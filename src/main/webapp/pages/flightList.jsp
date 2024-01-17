<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Flight List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row mb-5">
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
                            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/flights">Flights</a>
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

    <h2>Create New Flight</h2>

    <form action="" method="post" class="mb-3">
        <div class="form-row">
            <div class="col-md-3">
                <input type="text" name="source" class="form-control" placeholder="Source" required> <br>
                <input type="text" name="destination" class="form-control" placeholder="Destination" required> <br>
                <input type="datetime-local" name="departureTime" class="form-control" required> <br>
                <input type="datetime-local" name="arrivalTime" class="form-control" required> <br>
                <select class="form-select" name="planeModel" id="planeModel">
                    <c:forEach var="plane" items="${planes}">
                        <option value="${plane.getModel()}">${plane.getManufacturer()} ${plane.getModel()}</option>
                    </c:forEach>
                </select>
                <br>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="isDeparture" name="isDeparture" id="isDeparture">
                    <label class="form-check-label" for="isDeparture">
                        Is departure
                    </label>
                </div>
            </div>
        </div>
        <br>
        <button type="submit" class="btn btn-primary mt-2">Create Flight</button>
    </form>

    <h2>Flights List</h2>

    <table class="table mt-8">
        <thead>
        <tr>
            <th>ID</th>
            <th>Source</th>
            <th>Destination</th>
            <th>Departure Time</th>
            <th>Arrival Time</th>
            <th>Plane</th>
            <th>Is Departure</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="flight" items="${flights}">
            <tr>

                <td><a href="/flight_details?id=${flight.getId()}">${flight.getId()}</a></td>
                <td>${flight.getSource()}</td>
                <td>${flight.getDestination()}</td>
                <td>${flight.getDepartureTime()}</td>
                <td>${flight.getArrivalTime()}</td>
                <td>${flight.getPlane().getModel()}</td>
                <td>${flight.isDeparture()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
