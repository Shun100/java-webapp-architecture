<%@ page contentType="text/html; charset=UTF-8" %>

<body>
  <form method="POST">
    <table border="0">
      <tr><td>名前</td><td>${person.personName}</td></tr>
      <tr><td>年齢</td><td>${person.age}</td></tr>
      <tr>
        <td>性別</td>
        <td>${person.gender == null ? '' : person.gender == 'male' ? '男性' : '女性'}</td>
      </tr>
    </table>
    <div>
      <%-- 
        従来のHTMLではフォーム一つに対してURLを一つしか設定できなかった。
        HTML5で導入されたformaction属性を使用すると、submitボタンごとに異なるURLを設定できる。
      --%>
      <button type="submit" formaction="/spring_mvc_person/back">戻る</button>
      <button type="submit" formaction="/spring_mvc_person/update">更新実行</button>
    </div>
  </form>
</body>