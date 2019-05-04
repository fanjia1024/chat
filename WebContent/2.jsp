<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<style type="text/css">
<!--
body {
	background-color: #90C6FF;
}
-->
</style>
<form action="<%=request.getContextPath() %>/saveMessage.jsp" method="post"  >
<p>对象
  <input name="person" type="text" value="所有人" size="8">
  <select name="say">
	<option value='向B说道：' selected>说话</option>
	<option value='很严肃的问道B：'>请问</option>
	<option value='使劲地拍着巴掌向B喊：'>喝采</option>
	<OPTION VALUE="抿着嘴向着B：">微笑</OPTION>
	<OPTION VALUE="扭过头跟B说：">躲避</OPTION>
  </select>
  <select name="color">
    <option >颜色</option>
    <option value="red" style="background-color:red;color:FFFFFF"> </option>
    <option value="green" style="background-color:green;color:FFFFFF"> </option>
    <option value="blue" style="background-color:blue;color:FFFFFF"> </option>

  </select>
  <select name="pic">
	<option value="0" selected>贴图</option>
	<OPTION VALUE="1">微笑</OPTION>
	<OPTION VALUE="3">大笑</OPTION>
	<OPTION VALUE="4">爆笑</OPTION>
	<OPTION VALUE="2">甜笑</OPTION>
  </select>
<br>
信息
  <input name="message" type="text" size="40">
   
  <input type="submit" name="Submit" value="发送">

  <a href="login.htm" target="_parent"><img src="images/leave.gif" width="60" height="40" border="0"></a></p>
 </form>