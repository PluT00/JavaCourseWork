<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login Page</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyA8fN9aJq4cbatxX2T6U5V73TEmVYl" crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
    <h2>Login</h2>

    <%-- Проверка наличия ошибки --%>
    <% if (request.getParameter("error") != null && request.getParameter("error").equals("true")) { %>
    <div class="alert alert-danger" role="alert">
        Invalid username or password!
    </div>
    <% } %>

    <form action="/login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
        integrity="sha384-g6eVqg1iSg91IjwAg5cZViT6C+R5P4QPPiO5f9tc1cTaUj7FqF8C1l46R2t0LFR0"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyA8fN9aJq4cbatxX2T6U5V73TEmVYl"
        crossorigin="anonymous"></script>

</body>
</html>
