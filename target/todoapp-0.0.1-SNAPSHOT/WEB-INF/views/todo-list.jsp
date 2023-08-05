<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Todo List</title>
    <style>
        /* Your CSS styles here */
        .completed {
            text-decoration: line-through;
        }
    </style>
</head>
<body>
    <h1>Todo List</h1>
    <form action="${contextPath}/todos" method="post">
        <input type="text" name="title" placeholder="Enter your todo" required/>
        <button type="submit">Add</button>
    </form>
    <ul>
        <c:forEach items="${todos}" var="todo">
            <li class="${todo.completed ? 'completed' : ''}">
                ${todo.title}
                <a href="${contextPath}/todos/${todo.id}/delete">Delete</a>
            </li>
        </c:forEach>
    </ul>
</body>
</html>