<%@page import="reservJAVA_app.dto.MemberDTO"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="java.sql.*, java.sql.Date, javax.sql.*, javax.naming.*,	java.util.*, java.io.PrintWriter" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String state = (String)request.getAttribute("anPayment");
out.println(state);
%>