package com.example.java_web_app_architecture.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.java_web_app_architecture.dto.Person;

@Controller
public class PersonAction {
  @Inject
  private PersonService personService;
  private final int NOT_REGISTERED = 0;

  /**
   * 「入力画面」に遷移する。
   * @return String page - 入力画面のHTML
   */
  @RequestMapping("/create")
  public String createPerson() {
    return "PersonInputPage";
  }

  /**
   * 「確認画面」に遷移する。
   * <p>
   * @ModelAttribute: 送信されたフォームの入力値を自動的にPOJOに格納する。
   * @Valid: Bean Validationを利用する。
   *        （@NotNull, @NotEmpty, @Size, @Pattern, @Min, @Max, @DecimanMin, @DecimanMax等）
   * BindingResult: バリデーションで検出したエラーの格納先
   * </p>
   * @param Person person - 登録者情報
   * @param BindingResult errors
   * @param HttpSession session - セッションオブジェクト
   * @return String page - 遷移先の画面のHTML
   */
  @RequestMapping("/confirm")
  public String confirm(@ModelAttribute @Valid Person person, BindingResult errors, HttpSession session) {
    if (errors.hasErrors()) {
      return "PersonInputPage";
    }

    session.setAttribute("person", person);
    return "PersonUpdatePage";
  }

  /**
   * 「入力画面」に戻る。
   * @return String page - 入力画面のHTML
   */
  @RequestMapping("/back")
  public String back() {
    return "PersonInputPage";
  }

  /**
   * 新規登録・更新
   * @param Model model
   * @param HttpSession session
   * @return String PersonTablePage - 一覧画面のHTML
   */
  @RequestMapping("/update")
  public String updatePerson(Model model, HttpSession session) {
    Person person = (Person) session.getAttribute("person");
    if (person.personId() == NOT_REGISTERED) {
      // 新規登録
      personService.addPerson(person);
    } else {
      // 更新
      personService.updatePerson(person);
    }

    List<Person> personList = personService.getPersonList();
    model.addAttribute("personList", personList);
    session.removeAttribute("person");
    return "PersonTablePage";
  }

  /**
   * 編集
   * <p>
   * @RequestParamアノテーション: HTTPリクエストのパラメータをメソッドの引数にバインドする。
   * Model: viewにデータを渡すために使用する。
   * </p>
   * @param int personId
   * @param Model model
   */
  @RequestMapping("/edit")
  public String editPerson(@RequestParam("personId") int personId, Model model) {
    Person person = personService.getPerson(personId);
    model.addAttribute("person", person);
    return "PersonInputPage";
  }

  /**
   * 削除
   * @param int personId
   * @param Model model
   * @return String ???
   */
  @RequestMapping("/remove")
  public String removePerson(@RequestParam("personId") int personId, Model model) {
    // WIP
  }

  /**
   * 一覧表示
   * @param Model model
   * @return String PersonTablePage - 一覧画面
   */
  @RequestMapping("/viewList")
  public String viewPersonList(Model model) {
    List<Person> personList = personService.getPersonList();
    model.addAttribute("personList", personList);
    return "PersonTablePage";
  }
}
