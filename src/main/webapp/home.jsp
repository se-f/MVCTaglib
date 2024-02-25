<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <title>To-Do Notes</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2>To-Do Notes</h2>

    <!-- Form for adding notes -->
    <form action="addnote" method="post" class="mb-3">
        <div class="form-group">
            <label for="noteInput">Add Note:</label>
            <input type="text" class="form-control" id="noteInput" name="noteInput" placeholder="Enter your note">
        </div>
        <button type="submit" class="btn btn-primary">Add</button>
    </form>

    <!-- To-Do List -->
    <ul class="list-group">

        <%--
         For each item "todoItem" of the session attribute "notes", we're going to use a bean "todo"
        of type "ToDoBean" in which we're going set the propreties "content" and "isComplete" of the "todoItem"
         --%>

        <jsp:useBean id="todo" class="org.todoapp.todoapp.beans.ToDoBean"/>

        <c:forEach var="todoItem" items="${notes}">

            <jsp:setProperty name="todo" property="content" value="${todoItem.content}"/>
            <jsp:setProperty name="todo" property="isComplete" value="${todoItem.isComplete}"/>

            <li class="list-group-item <%= todo.getIsComplete().equals("1") ? "bg-success text-white" : "" %>">
                <div class="d-flex justify-content-between align-items-center">

                        <%--Display the content of the note bean with getProperty--%>
                    <span><jsp:getProperty name="todo" property="content"/></span>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-success btn-sm">Complete</button>
                        <button type="button" class="btn btn-danger btn-sm">Delete</button>
                    </div>
                </div>
            </li>

        </c:forEach>

    </ul>

</div>


</body>
</html>