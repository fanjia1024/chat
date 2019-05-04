package com.turing.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.turing.dao.DaoImpl;
import com.turing.dao.IDao;

public class DoneServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.接值
		request.setCharacterEncoding("utf-8");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String qq = request.getParameter("qq");
		String email = request.getParameter("email");
		String img = request.getParameter("img");
		
		//2.调dao存数据库
		try {
			IDao dao = new DaoImpl();
			String sql = "insert into users "+
					"   (id, nickname, password, sex, img, oicq, email) "+
					" values "+
					"   (?, ?, ?, ?, ?, ?, ?)";
			int[] types = new int[7];
			types[0] = Types.VARCHAR;
			types[1] = Types.VARCHAR;
			types[2] = Types.VARCHAR;
			types[3] = Types.VARCHAR;
			types[4] = Types.VARCHAR;
			types[5] = Types.VARCHAR;
			types[6] = Types.VARCHAR;
			
			Object[] values = new Object[7];
			values[0] = UUID.randomUUID().toString();
			values[1] = nickname;
			values[2] = password;
			values[3] = sex;
			values[4] = img;
			values[5] = qq;
			values[6] = email;
			
			dao.executeUpdate(sql, types, values);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"");
		out.println("\"http://www.w3.org/TR/html4/loose.dtd\">");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">");
		out.println("<title>注册完成</title>");
		out.println("<style type=\"text/css\">");
		out.println("<!--");
		out.println("body {");
		out.println("	background-color: #90C6FF;");
		out.println("}");
		out.println(".style1 {");
		out.println("	font-size: 24px;");
		out.println("	color: #FF0000;");
		out.println("	font-weight: bold;");
		out.println("}");
		out.println(".style3 {font-size: 18px}");
		out.println("-->");
		out.println("</style></head>");
		out.println("");
		out.println("<body>");
		out.println("<p align=\"center\" class=\"style1\">&nbsp;</p>");
		out.println("  <p align=\"center\" class=\"style1\">恭喜你，注册成功了!</p>");
		out.println("  <table width=\"300\" border=\"1\" align=\"center\" bordercolor=\"#6666CC\">");
		out.println("    <tr>");
		out.println("      <td align=\"center\"  colspan=\"2\"><span class=\"style3\">您的信息如下</span></td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("      <td width=\"100\" align=\"right\">昵&emsp;&emsp;称：</td>");
		out.println("      <td>"+nickname+"</td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("      <td align=\"right\">头&emsp;&emsp;像：</td>");
		out.println("      <td><img src=\"images/"+img+".gif\" width=\"32\" height=\"32\"></td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("      <td align=\"right\">性&emsp;&emsp;别：</td>");
		out.println("      <td>"+sex+"</td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("      <td align=\"right\">OICQ号码：</td>");
		out.println("      <td>"+qq+"</td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("      <td align=\"right\">邮件地址：</td>");
		out.println("      <td>"+email+"</td>");
		
		out.println("    </tr>");
		out.println("  </table>");
		out.println("  <p align=\"center\">");
		out.println("  <A HREF=\"javascript:window.close()\"> 关 闭 窗 口</A>&emsp;&emsp;");
		out.println("  <A HREF=\"login.htm\">回到登陆页面</A>");
		out.println("  </p>");
		out.println("");
		out.println("</body>");
		out.println("</html>");
		
	}

}
