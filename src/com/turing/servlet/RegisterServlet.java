package com.turing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String img = request.getParameter("id");
		
		if(img==null){
			img = "face5";
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"");
		out.println("\"http://www.w3.org/TR/html4/loose.dtd\">");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
		out.println("<title>用户注册</title>");
		out.println("<style type=\"text/css\">");
		out.println("<!--");
		out.println("body {");
		out.println("	background-color: #90C6FF;");
		out.println("}");
		out.println(".style1 {font-size: 18px}");
		out.println(".style2 {color: #FF0000}");
		out.println("-->");
		out.println("</style></head>");
		out.println("");
		out.println("<body>");
		out.println("<form name=\"form1\" method=\"post\" action=\"done.do\">");
		out.println("  <table width=\"363\" border=\"1\" align=\"center\" bordercolor=\"#6666CC\">");
		out.println("    <tr>");
		out.println("      <td align=\"center\" colspan=\"2\"><span class=\"style1\">注册新用户信息</span></td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("      <td width=\"100\" align=\"right\">头&emsp;&emsp;像：</td>");
		out.println("      <td><img src=\"images/"+img+".gif\" width=\"32\" height=\"32\"> <input type='hidden' name='img' value='"+img+"'/> <a href=\"face.htm\">请首先选择头像</a> </td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("      <td align=\"right\">昵&emsp;&emsp;称：</td>");
		out.println("      <td>");
		out.println("          <input name=\"nickname\" type=\"text\" size=\"15\"><span class=\"style2\">*</span>");
		out.println("	  </td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("      <td align=\"right\">密&emsp;&emsp;码：</td>");
		out.println("      <td>");
		out.println("          <input name=\"password\" type=\"password\" size=\"15\"><span class=\"style2\">*</span>");
		out.println("	  </td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("      <td align=\"right\">性&emsp;&emsp;别：</td>");
		out.println("      <td>");
		out.println("			<input type=\"radio\" name=\"sex\" value=\"男\" checked>男");
		out.println("			<input type=\"radio\" name=\"sex\" value=\"女\">女");
		out.println("			<input type=\"radio\" name=\"sex\" value=\"保密\">保密");
		out.println("	  </td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("      <td align=\"right\">OICQ号码：</td>");
		out.println("      <td><input type=\"text\" name=\"qq\"></td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("      <td align=\"right\">邮件地址：</td>");
		out.println("      <td><input type=\"text\" name=\"email\"></td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("      <td  colspan=\"2\">&emsp;带&nbsp;<span class=\"style2\">*</span>&nbsp;号为必须填写项目</td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("      <td  colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"开始注册\"></td>");
		out.println("    </tr>");
		out.println("  </table>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");

		
	}

}
