<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String message = request.getParameter("message");
	String person = request.getParameter("person");
	String say = request.getParameter("say");
	String color = request.getParameter("color");
	String pic = request.getParameter("pic");
	
	
	String nickname = ((Map<String,Object>)session.getAttribute("userInfo")).get("NICKNAME").toString();
	
	String str = (" "+nickname+""+say+"<font color='"+color+"'>"+message+" </font><img src='images/face"+pic+".gif'/>").replace("B", person);
	
	
	//List<String> list = application.getAttribute("list")==null?new ArrayList<String>():(List<String>)application.getAttribute("list");
	
	List<String> list = null;
	if(application.getAttribute("list")==null){
		list = new ArrayList<String>();
	}else{
		list = (List<String>)application.getAttribute("list");
	}
	
	list.add(str);
	
	application.setAttribute("list", list);
	
	
	response.sendRedirect(request.getContextPath()+"/2.jsp");
%>