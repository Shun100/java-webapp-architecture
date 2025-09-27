package com.example.java_web_app_architecture.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter("/PersonServlet")
public class PersonFilter implements Filter {
  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
    // 何らかの前処理を行う
    System.out.println("何らかの前処理を行う");

    // HTTPリクエスト・HTTPレスポンスを転送する
    System.out.println("HTTPリクエスト・HTTPレスポンスを転送する");
    chain.doFilter(req, res);

    // 何らかの後処理を行う
    System.out.println("何らかの後処理を行う");
  }
}
