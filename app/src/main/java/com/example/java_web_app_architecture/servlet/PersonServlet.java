package com.example.java_web_app_architecture.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) {
    System.out.println("何らかの処理を行う");
  }
}
