<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib url="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
  <body>
    <form id="form" action="/spring_mvc_person/confirm" method="POST">
      <div ${person.personId == null ? 'style="display:none"' : ""}>
        <span>ID: ${person.personId}</span>
      </div>

      <table border="0">
        <tr>
          <td>名前</td>
          <td><input type="text" id="personName" name="personName" value="${person.personName}"></td>
          <%-- formタグ: Spring MVCが提供するタグ --%>
          <td><form:errors path="person.personName" cssStyle="color: red" /></td>
        </tr>
        <tr>
          <td>年齢</td>
          <td><input type="text" id="age" name="age" value="${person.age}"></td>
          <td><form:errors path="person.age" cssStyle="color: red" /></td>
        </tr>
        <tr>
          <td>性別</td>
          <td>
            <input type="radio" id="gender" name="gender" value="male" ${person.gender == 'male' ? 'checked' : ''}>
            <input type="radio" id="gender" name="gender" value="female" ${person.gender == 'female' ? 'checked' : ''}>
          </td>
          <td><form:errors path="person.gender" cssStyle="color: red" /></td>
        </tr>
      </table>
      <div><input type="submit" value="確認画面へ" /></div>
      <input type="hidden" name="personId" value="${person.personId}" />
    </form>
  </body>
</html>