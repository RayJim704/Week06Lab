<%-- 
    Document   : shoppingList
    Created on : Apr 25, 2021, 3:59:59 PM
    Author     : rizz4
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello ${username}</p>
        <p><a href="ShoppingList?action=logout">Log out</a></p>
        
        <form action="" method="POST">
            <h2>Add Item</h2>
            <input type="text" name="item">
            <input type="submit" value="Add Item">
            <input type="hidden" name="action" value="add">
        </form>
        
        <form action="" method="post">
            <c:forEach items="${listItems}" var="itemName">
                <tr>
                    <td>
                        <input type="radio" name="item" value="${itemName}">${itemName}
                    </td>
                </tr>
                <br>
            </c:forEach>
            <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
        </form>
    </body>
</html>
