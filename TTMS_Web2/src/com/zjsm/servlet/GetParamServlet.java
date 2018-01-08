package com.zjsm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(urlPatterns =
//{ "/GetParamServlet" }, initParams =
//{ @WebInitParam(name = "dbname", value = "mysql", description = "默认数据库"),
//@WebInitParam(name = "dbversion", value = "8.0", description = "默认数据库版本") })
public class GetParamServlet extends HttpServlet
{
 private static final long serialVersionUID = 1L;
 private String dbname;
 private String dbversion;

 @Override
 public void init(ServletConfig config) throws ServletException
 {
     super.init(config);
     dbname = config.getInitParameter("dbname");
     dbversion = config.getInitParameter("dbversion");

 }

 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
 {
     doPost(request, response);
 }

 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
 {
     response.setContentType("text/html;charset=UTF-8");
     PrintWriter out = response.getWriter();
     out.print("数据库名称：" + dbname);
     out.print("<br>");
     out.print("数据库版本：" + dbversion);
     out.close();
 }

}
