<%@ page contentType="text/html; charset=UTF-8" %>

<body>
  <form method="POST">
    <table border="1">
      <tr>
        <th>ID</th>
        <th>名前</th>
        <th>年齢</th>
        <th>性別</th>
        <th>編集</th>
        <th>削除</th>
      </tr>
      <c:forEach items="${personList}" var="person">
        <tr>
          <td>${person.personId}</td>
          <td>${person.personName}</td>
          <td>${person.age}</td>
          <td>${person.gender == null ? "" : person.gender == "male" ? "男性" : "女性"}</td>
          <td><button type="submit" formaction="/spring_mvc/person/edit" name="personId" value="${person.personId}">編集</button></td>
          <td><button type="submit" formaction="/spring_mvc/person/delete" name="personId" value="${person.personId}">削除</button></td>
        </tr>
      </c:forEach>
    </table>
    <div>
      <button type="submit" formaction="/spring_mvc/person/create" name="personId">作成</button>
    </div>
  </form>
</body>