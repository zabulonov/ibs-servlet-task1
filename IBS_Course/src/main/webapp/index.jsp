<%--
  Created by IntelliJ IDEA.
  User: zabulonov
  Date: 10/20/23
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.appline.logic.Model" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <h1>Домашняя страница по работе с пользователями</h1>
  Введите id пользователя (0 для вывода всего списка пользователей)
  <br/>

  Доступно: <%
    Model model = Model.getInstance();
    out.print(model.getFromList().size());
  %>

  <form method="get" action="getView">
    <label>id:
      <input type="text" name="id">
    </label>
    <button type="submit">Поиск</button>
  </form>
  <a href="addUser.html">Создать пользователя</a>
  </body>
</html>
