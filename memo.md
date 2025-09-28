# アプリケーションアーキテクチャ設計パターン

## スコープ

- リクエストスコープ
  - 1つのリクエスト（HTTPリクエスト受信からHTTPレスポンス送信まで）に結び付く記憶領域。
  - JavaEEでは`HttpServletRequest`によって提供される。

  ``` Java
    // データ格納
    request.setAttribute("foo", foo);

    // データの取得
    Foo foo = (Foo) request.getAttribute("foo");
  ```

- セッションスコープ
  - 1つのHTTPセッション（通常はログインからログアウトまで）に結び付く記憶領域。
  - 同一ユーザの情報を複数リクエストに跨って保持するためのもの。
  - JavaEEでは`HttpSession`によって提供される。

  ``` Java
    // 明示的にインスタンスを取得する必要がある。
    HttpSession session = request.getSession();

    // データの格納
    session.setAttribute("foo", foo);

    // データの取得
    Foo foo = (Foo) session.getAttribute("foo");
  ```

- アプリケーションスコープ
  - 1つのWebアプリケーション（開始から停止まで）に結び付く記憶領域。
  - 複数ユーザ間に跨ったデータを保持するためのもの。
  - JavaEEでは`ServletContext`によって提供される。

  ``` Java
    // 明示的にインスタンスを取得する必要がある。
    ServeletContext sc = getServletContext();

    // データの格納
    sc.setAttribute("foo", foo);

    // データの取得
    Foo foo = (Foo) sc.getAttribute("foo");
  ```

### サーブレット・JSPページの連携方法

- ディスパッチ方式

``` mermaid
flowchart LR
  browser["Webブラウザ"]

  subgraph JavaEEコンテナ
    forward_src["フォワード元<br>サーブレット・JSP"]
    forward_dst["フォワード先<br>サーブレット・JSP"]
  end

  browser -- HTTPリクエスト --> forward_src;
  forward_src -- 処理を委譲 --> forward_dst;
  forward_dst -- HTTPレスポンス --> browser;
```

``` Java
  RequestDispatcher rd = request.getRequestDispatcher("/FooPage");
  rd.forward(request, response);
```


- インクルード方式

``` mermaid
flowchart LR
  browser["Webブラウザ"]

  subgraph JavaEEコンテナ
    forward_src["フォワード元<br>サーブレット・JSP"]
    forward_dst["フォワード先<br>サーブレット・JSP"]
  end

  browser -- HTTPリクエスト --> forward_src;
  forward_src --> forward_dst;
  forward_dst -- 出力を合成 --> forward_src;
  forward_src -- HTTPレスポンス --> browser;
```

``` Java
  RequestDispatcher rd = request.getRequestDispatcher("/FooServlet");
  rd.include(request, response);
```

- リダイレクト方式

``` mermaid
flowchart LR
  browser["Webブラウザ"]

  subgraph JavaEEコンテナ1
    redirect_src["リダイレクト元<br>サーブレット・JSP"]
  end

  subgraph JavaEEコンテナ2
    redirect_dst["リダイレクト先<br>サーブレット・JSP"]
  end

  browser -- HTTPリクエスト --> redirect_src;
  redirect_src -- HTTPレスポンス<br>(301 or 302) --> browser;
  browser -- HTTPリクエスト --> redirect_dst;
  redirect_dst -- HTTPレスポンス --> browser;
```

``` Java
  response.sendRedirect(request.getContextPath() + "/RedirectPage");
```

## 4.2.2 フィルタ

- Webブラウザから送信されたHTTPリクエストをインターセプトし、サーブレット・JSPページの呼び出し前後に任意の処理を組み込むためのコンポーネント。

``` mermaid
flowchart LR
  browser["Webブラウザ"]

  subgraph JavaEEコンテナ
    filter["フィルタ"]
    servlet["呼び出し対象のサーブレット・JSP"]
  end

  browser -- HTTPリクエスト --> filter;
  filter -- HTTPリクエスト --> servlet;
  servlet -- HTTPレスポンス --> filter;
  filter -- HTTPレスポンス --> browser;
```

``` Java
  @WebServlet("/PersonServlet")
  public class PersonFilter extends Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
      // 何らかの前処理を行う

      // HTTPリクエスト・HTTPレスポンスを転送する
      chain.doFilter(req, res);

      // 何らかの後処理を行う
    }
  }
```

## 4.2.4 他システムとの連携方式

- リダイレクト方式

``` mermaid
flowchart LR
  browser_src["Webページ(遷移元)"]
  browser_dst["Webページ(遷移先)"]

  subgraph JavaEEコンテナ1
    servlet_src["コントローラ(Servlet)"]
  end

  subgraph JavaEEコンテナ2
    servlet_dst["ビュー(JSP)"]
  end

  browser_src -- HTTPリクエスト --> servlet_src;
  servlet_src -- HTTPレスポンス<br>(302 or 303) --> browser_src;
  browser_src -- HTTPリクエスト --> servlet_dst;
  servlet_dst -- HTTPレスポンス --> browser_dst;

  browser_src -- 画面遷移 --> browser_dst;
```

- 空ページ自動サブミット方式
  - windowオブジェクトのonloadプロパティにイベントハンドラを設定することによって、空ページがロードされた直後に自動的にフォームをサブミットする。
  - リダイレクト方式と異なり、POSTメソッドで他システムと連携できる点が特徴。

``` HTML
  <script type="text/javascript">
    window.onload = function() {
      document.forms[0].submit();
    }
  </script>

  <body>
    <form action="連携先のURL" method="POST">
      <!-- inputタグはhiddenにして非表示にする -->
      <input type="hidden" name="personName" value="${personName}">
      <input type="hidden" name="age" value="${personAge}">
    </form>
  </body>
```

## 4.3 セッション管理方式

- TODO: 大規模システム向けの内容なので優先度を下げて、次回まとめる。

