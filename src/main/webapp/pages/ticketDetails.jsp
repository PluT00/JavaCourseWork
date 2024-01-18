<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Flight Details</title>
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
                            <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/flights">Flights</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/planes">Planes</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/users">Users</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/companies">Companies</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="${pageContext.request.contextPath}/tickets">Tickets</a>
                        </li>
                    </ul>
                    <span class="navbar-text">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                    </span>
                </div>
            </div>
        </nav>
    </div>

    <h2>Update Flight</h2>

    <form action="${pageContext.request.contextPath}/ticket_details" method="post" class="mb-3">
        <div class="form-row">
            <div class="col-md-3">
                <input type="hidden" name="id" value="${ticket.getId()}">
                <input type="hidden" name="action" value="update">
                <select class="form-select" name="user" id="user">
                    <c:forEach var="user" items="${users}">
                        <option value="${user.getUsername()}">${user.getUsername()}</option>
                    </c:forEach>
                </select> <br>
                <select class="form-select" name="flight" id="flight">
                    <c:forEach var="flight" items="${flights}">
                        <option value="${flight.getId()}">${flight.getId()} | ${flight.getSource()} -> ${flight.getDestination()}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <br>
        <button type="submit" class="btn btn-primary mt-2">Update Ticket</button>
    </form>

    <form action="${pageContext.request.contextPath}/ticket_details" method="post">
        <input type="hidden" name="id" value="${ticket.id}">
        <input type="hidden" name="action" value="delete">
        <button class="btn btn-danger mt-2" type="submit">Delete Ticket</button>
    </form>

    <table class="table mt-8">
        <thead>
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Flight</th>
        </tr>
        </thead>
        <tbody>
            <tr>

                <td><a href="/ticket_details?id=${ticket.getId()}">${ticket.getId()}</a></td>
                <td><a href="/user_details?id=${ticket.getUser().getId()}">${ticket.getUser().getUsername()}</a></td>
                <td><a href="/flight_details?id=${ticket.getFlight().getId()}">${ticket.getFlight().getId()} | ${ticket.getFlight().getSource()} -> ${ticket.getFlight().getDestination()}</a></td>
            </tr>
        </tbody>
    </table>
</div>
</body>
