package com.example.java_web_app_architecture.servlet;


@Controller
public class PersonAction {
  @Inject
  private PersonService personService;

  @RequestMapping("/create")
  public String createPerson() {
    return "PersonInputPage";
  }
}
