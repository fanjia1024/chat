<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="refresh" content="2"> 
<style type="text/css">
<!--
body {
	background-color: #90C6FF;
}
-->
</style>

<p>欢迎 <%=((Map<String,Object>)session.getAttribute("userInfo")).get("NICKNAME") %> 光临JJ聊天室，请注意文明聊天。</p>
<hr>
<%
		List<String> list = (List<String>)application.getAttribute("list");
		//System.out.println(list);
		if(list!=null){
		for(int i=list.size()-1;i>=0;i--){
			//System.out.println("1111");
%>
<p>&nbsp; <%=list.get(i) %> </p>
<%}}%>

<p>   </p>