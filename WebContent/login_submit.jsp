<%@page import="java.util.Map"%>
<%@page import="com.turing.dao.DaoImpl"%>
<%@page import="com.turing.dao.IDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	//1.接用户名和密码
	request.setCharacterEncoding("utf-8");
	String yhm = request.getParameter("textfield");
	String mm = request.getParameter("textfield2");
	
	//2.调dao查库
	IDao dao = new DaoImpl();
	Map<String,Object> userInfo = 
			dao.executeQueryForMap("select * from users where nickname='"+yhm+"' and password='"+mm+"'");
	//System.out.println(userInfo);
	//3.根据查询结果，转向
	if(userInfo==null){//3.1失败
		response.sendRedirect(request.getContextPath()+"/error.jsp");
	}else{//3.2成功
		session.setAttribute("userInfo", userInfo);
		
	
	
		response.sendRedirect(request.getContextPath()+"/frame.htm");
	}

%>