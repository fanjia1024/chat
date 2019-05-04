package com.turing.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;


/**
 * 数据库连接对象
 * @author 	赵刚
 * @time	2013-01-21
 */
public class DaoImpl implements IDao{
	
	
	//连接MySql数据库 需要包  mysql-connector-java-3.1.13-bin.jar
	private String sDBDriver = "com.mysql.cj.jdbc.Driver"; 
	private String sConnStr = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";//设置数据库名称为：pubs 
	private String user = "root"; //登录数据库用户名 
	private String password = "12345678"; //登录数据库密码
	
	//连接SQLServer数据库 需要包  jtds-1.2.2.jar
//	private String sDBDriver = "net.sourceforge.jtds.jdbc.Driver";
//	private String sConnStr = "jdbc:jtds:sqlserver://localhost:1433/strutslx";//设置数据库名称为：pubs
//    private String user = "sa";  //登录数据库用户名
//    private String password = "sa";   //登录数据库密码
    
    //连接Oracle数据库 需要包  classes12.jar 或者 ojdbc14.jar
//	private String sDBDriver = "oracle.jdbc.driver.OracleDriver"; 
//	private String sConnStr = "jdbc.url=jdbc:oracle:thin:@localhost:1521:turing";//设置数据库名称为：pubs 
//	private String user = "liukai"; //登录数据库用户名 
//	private String password = "123456"; //登录数据库密码

    
    /**
     * 建立连接
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException{
    	
		 Class.forName(sDBDriver); //指定JDBC数据库驱动程序

		 return DriverManager.getConnection(sConnStr,user,password);
    }


	/**
     * 根据sql查询列表数据(查询一条)，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
	public Map<String, Object> executeQueryForMap(String sql)throws ClassNotFoundException, SQLException {
    	Connection connect =this.getConnection();
		Statement stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<Map<String, Object>> list = this.rsToList(rs);
		if( !list.isEmpty() ){
			return list.get(0);
		}
		this.releaseConnection(rs, stmt, connect);//关闭连接
		return null;
	}

	/**
	 * 根据sql查询列表数据(查询一条)，支持预编译的方式
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public Map<String, Object> executeQueryForMap(String sql, int[] types,Object[] ObjectValues) throws ClassNotFoundException, SQLException, IOException {
    	Connection connect = this.getConnection();
		PreparedStatement pst =  connect.prepareStatement(sql);
		
		if( types != null ){
			prepareSetValue(types, ObjectValues, pst);
		}

		ResultSet rs = pst.executeQuery();
		List<Map<String, Object>> list = this.rsToList(rs);
		if( !list.isEmpty() ){
			return list.get(0);
		}
		this.releaseConnection(rs, pst, connect);
		return null;
	}

	/**
	 * 预编译传参设置传参
	 * @param types	参数类型
	 * @param ObjectValues	参数的具体数据
	 * @param pst	预编译可执行对象
	 * @throws SQLException	SQL异常
	 * @throws FileNotFoundException 文件没找到
	 * @throws IOException	IO异常:数据库没连上
	 */
	private void prepareSetValue(int[] types, Object[] ObjectValues,
			PreparedStatement pst) throws SQLException, FileNotFoundException,
			IOException {
		for(int i=0;i<types.length;i++){
			switch( types[i] ){
			case Types.VARCHAR:
				pst.setString(i+1, String.valueOf( ObjectValues[i] ) );
				break;
			case Types.INTEGER:
				pst.setInt(i+1, Integer.parseInt( String.valueOf( ObjectValues[i] ) ));
				break;
			case Types.DOUBLE:
				pst.setDouble(i+1, Double.parseDouble(  String.valueOf( ObjectValues[i] ) ));
				break;
			case Types.BLOB:
				InputStream in = new FileInputStream( (File)ObjectValues[i] );
				pst.setBinaryStream(i+1, in , in.available()  );
				break;
			case Types.DATE:
				pst.setDate(i+1, new java.sql.Date(((java.util.Date)ObjectValues[i]).getTime()) );
				break;
			case Types.DECIMAL:
				pst.setDouble(i+1, Double.parseDouble(String.valueOf( ObjectValues[i]) ));
				break;
			case Types.NUMERIC:
				pst.setDouble(i+1, Double.parseDouble(String.valueOf( ObjectValues[i]) ));
				break;
			case Types.FLOAT:
				pst.setFloat(i+1, Float.parseFloat(String.valueOf( ObjectValues[i]) ) );
				break;
			case Types.TIME:
				pst.setTime(i+1, new java.sql.Time(((java.util.Date)ObjectValues[i]).getTime()));
				break;
			}
		}
	}
    /**
     * 根据sql查询列表数据，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Map<String, Object>> executeQueryForList(String sql) throws ClassNotFoundException, SQLException{
		
    	Connection connect =this.getConnection();
		Statement stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<Map<String, Object>> list = this.rsToList(rs);
		this.releaseConnection(rs, stmt, connect);//关闭连接
		return list;
	}

    /**
     * 执行 增、删、改、等的操作，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
	public int executeUpdate(String sql) throws ClassNotFoundException, SQLException {
		
		Connection connect=this.getConnection();
		Statement stmt=connect.createStatement();
		int count=stmt.executeUpdate(sql);

		this.releaseConnection(stmt, connect);//关闭连接
	
		return count;
	}
	
	
	/**
	 * 根据sql查询列表数据，支持预编译的方式
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public List<Map<String, Object>> executeQueryForList(String sql , int[] types,Object[] ObjectValues) throws ClassNotFoundException, SQLException, IOException{
	    	Connection connect = this.getConnection();
			PreparedStatement pst =  connect.prepareStatement(sql);
			
			if( types != null ){
				prepareSetValue(types, ObjectValues, pst);
			}

			ResultSet rs = pst.executeQuery();
			List<Map<String, Object>> list = this.rsToList(rs);
			this.releaseConnection(rs, pst, connect);
			return list;
	}


	/**
	 * 预编译sql操作，   支持insert ， update  ， delete  语句
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public int executeUpdate(String sql , int[] types,Object[] ObjectValues) throws ClassNotFoundException, SQLException, IOException{

		Connection connect = this.getConnection();
		PreparedStatement pst =  connect.prepareStatement(sql);
		
		if( types != null ){
			prepareSetValue(types, ObjectValues, pst);
		}

		int count = pst.executeUpdate();
		this.releaseConnection(pst, connect);
		return count;  
	}
	
	/**
	 * 查询一个整数，例如记录总数（不支持预编译）
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int executeQueryForCount(String sql) throws ClassNotFoundException, SQLException{

    	Connection connect =this.getConnection();
		Statement stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			return rs.getInt(1);
		}
		this.releaseConnection(rs, stmt, connect);
		return 0; 
	}
	/**
	 * 查询一个整数，例如记录总数（支持预编译）
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public int executeQueryForCount(String sql , int[] types,Object[] ObjectValues) throws ClassNotFoundException, SQLException, IOException{

		Connection connect = this.getConnection();
		PreparedStatement pst =  connect.prepareStatement(sql);
		
		if( types != null ){
			prepareSetValue(types, ObjectValues, pst);
		}

		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		this.releaseConnection(rs, pst, connect);
		return 0; 
	}
	/**
	 * 将ResultSet中的结果包装成list中装Map的结构
	 * @author 		赵刚
	 * @time		2014-1-21
	 * @param		 rs
	 * @return
	 * @throws SQLException
	 */
	private List<Map<String, Object>> rsToList( ResultSet rs ) throws SQLException{
		List<Map<String, Object>> row = new ArrayList<Map<String, Object>>();
		 while (rs.next()) {
			 Map<String, Object> col = new HashMap<String, Object>();
			 for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				 //System.out.println(  rs.getMetaData().getColumnTypeName(i)+" " +rs.getMetaData().getColumnType(i) );
				 switch( rs.getMetaData().getColumnType(i) ){
			 	 case Types.VARCHAR:
			 		col.put(rs.getMetaData().getColumnName(i), rs.getString(i));
			 		break;
			 	 case Types.INTEGER:
				 	col.put(rs.getMetaData().getColumnName(i), rs.getInt(i));
				 	break;	
			 	 case Types.LONGVARBINARY:
			 		InputStream in1 = rs.getBinaryStream(i);
				 	col.put(rs.getMetaData().getColumnName(i), in1 );
				 	break;	
			 	 case Types.DATE:
			 		 col.put(rs.getMetaData().getColumnName(i), rs.getDate(i)+" "+rs.getTime(i) );
			 		 break;	
			 	 case Types.BLOB:
			 		 InputStream in2 = rs.getBinaryStream(i);
			 		 col.put(rs.getMetaData().getColumnName(i), in2 );
			 		 break;	
				 default:
					 col.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				 	break;	
				 }
				 
			 }
			row.add(col);
		}
		 return row;
	}
	
	@SuppressWarnings("unused")
	private void releaseConnection(Connection connect) throws SQLException{
	    try {
	        if (connect != null && !connect.isClosed()){
	        	connect.close();
	        }
	    } catch (SQLException se){
	        System.out.println("Close the connection encounter error!\n" + se.getMessage());
	        throw new SQLException("关闭连接异常！");
	    }
	}
	
	private void releaseConnection(Statement stmt, Connection connect) throws SQLException{
	    try {
	        if (stmt != null){
	        	stmt.close();
	        }
	        if (connect != null && !connect.isClosed()){
	        	connect.close();
	        }
	    } catch (SQLException se){
	        System.out.println("Close the connection encounter error!\n" + se.getMessage());
	        throw new SQLException("关闭连接异常！");
	    }
	}
	private void releaseConnection(PreparedStatement pst, Connection connect) throws SQLException{
	    try {
	        if (pst != null){
	            pst.close();
	        }
	        if (connect != null && !connect.isClosed()){
	        	connect.close();
	        }
	    } catch (SQLException se){
	        System.out.println("Close the connection encounter error!\n" + se.getMessage());
	        throw new SQLException("关闭连接异常！");
	    }
	}
	
	private void releaseConnection(ResultSet rs, Statement stmt, Connection connect) throws SQLException{
	    try {
	        if (rs != null){
	            rs.close();
	        }
	        if (stmt != null){
	        	stmt.close();
	        }
	        if (connect != null && !connect.isClosed()){
	        	connect.close();
	        }
	    } catch (SQLException se){
	        System.out.println("Close the connection encounter error!\n" + se.getMessage());
	        throw new SQLException("关闭连接异常！");
	    }
	}
	private void releaseConnection(ResultSet rs, PreparedStatement pst, Connection connect) throws SQLException{
	    try {
	        if (rs != null){
	            rs.close();
	        }
	        if (pst != null){
	            pst.close();
	        }
	        if (connect != null && !connect.isClosed()){
	        	connect.close();
	        }
	    } catch (SQLException se){
	        System.out.println("Close the connection encounter error!\n" + se.getMessage());
	        throw new SQLException("关闭连接异常！");
	    }
	}
	
	
	@Override
	public List<?> executeQueryForList(String sql, int[] types,
			Object[] values, Class<?> cla) throws ClassNotFoundException,
			SQLException {
		System.out.println("此dao的方法为空，请换一个方法");
		return null;
	}


	@Override
	public Object executeQueryForObject(String sql, Class<?> cla)
			throws ClassNotFoundException, SQLException {
		System.out.println("此dao的方法为空，请换一个方法");
		return null;
	}


	@Override
	public Object executeQueryForObject(String sql, int[] types,
			Object[] values, Class<?> cla) throws ClassNotFoundException,
			SQLException {
		System.out.println("此dao的方法为空，请换一个方法");
		return null;
	}


	@Override
	public List<?> executeQueryForList(String sql, Class<?> cla)
			throws ClassNotFoundException, SQLException {
		System.out.println("此dao的方法为空，请换一个方法");
		return null;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
			
		IDao dao = new DaoImpl();
		String sql = "select * from users";
		List<Map<String, Object>> list = dao.executeQueryForList(sql );
		System.out.println(list);
	}
	
}
