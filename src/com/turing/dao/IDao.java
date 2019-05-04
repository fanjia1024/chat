package com.turing.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/************************************
 * 数据库连接对象
 * @author 	赵刚
 * @time	2011-05-11
 ************************************/
public interface IDao {

	/**
     * 根据sql查询列表数据(查询一条)，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Map<String, Object> executeQueryForMap(String sql) throws ClassNotFoundException, SQLException;
	/**
	 * 根据sql查询列表数据(查询一条)，支持预编译的方式
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public Map<String, Object> executeQueryForMap(String sql , int[] types,Object[] ObjectValues) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException ;
    
	  /**
     * 根据sql查询列表数据(查询多条)，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Map<String, Object>> executeQueryForList(String sql) throws ClassNotFoundException, SQLException;
	/**
	 * 根据sql查询列表数据(查询多条)，支持预编译的方式
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public List<Map<String, Object>> executeQueryForList(String sql , int[] types,Object[] ObjectValues) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException ;
    
	

    /**
     * 执行 增、删、改、等的操作，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
	public int executeUpdate(String sql) throws ClassNotFoundException, SQLException ;
	/**
	 * 预编译sql操作，   支持insert ， update  ， delete  语句
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public int executeUpdate(String sql , int[] types,Object[] ObjectValues) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException ;
	
	/**
	 * 预编译sql操作，   支持  select 语句
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int executeQueryForCount(String sql ) throws ClassNotFoundException, SQLException ;
	/**
	 * 预编译sql操作，   支持  select 语句
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public int executeQueryForCount(String sql , int[] types,Object[] ObjectValues) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException ;
	/**
	 * 预编译  根据实体类返回一个list<对象>的数据格式
	 * @param sql
	 * @param types
	 * @param values
	 * @param cla
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<?> executeQueryForList(String sql, int[] types, Object[] values,Class<?> cla) throws ClassNotFoundException, SQLException;
	/**
	 * 返回的是一个对象
	 * @param sql
	 * @param cla
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Object executeQueryForObject(String sql, Class<?> cla)throws ClassNotFoundException, SQLException;
	/**
	 * 预编译  返回的是一个对象
	 * @param sql
	 * @param types
	 * @param values
	 * @param cla
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Object executeQueryForObject(String sql, int[] types, Object[] values,Class<?> cla) throws ClassNotFoundException, SQLException;
	/**
	 * 返回的是list<对象>
	 * @param sql
	 * @param cla
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<?> executeQueryForList(String sql, Class<?> cla)throws ClassNotFoundException, SQLException;

}
