package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

/**
 * translate.baidu.com
 * 
 * 数据库连接的管理类
 * */
public class DBUtil {
	private static BasicDataSource ds;
	static{
		try {
			Properties prop = new Properties();
			/*
			 * DBUtil.calss.getClassLoader();获得加载DBUtil的类加载器(ClassLoader)
			 * gerResourceAsStream是类加载器提供的方法，会依据路径区查找属性文件(.properties文件)返回InputStream
			 * */
			prop.load(DBUtil.class.getClassLoader().getResourceAsStream("config.properties"));
			String driverclass = prop.getProperty("driverclass");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			int maxActive = Integer.parseInt(prop.getProperty("maxactive"));
			int maxWait = Integer.parseInt(prop.getProperty("maxwait"));
			System.out.println("driverclass:"+driverclass);
			System.out.println("url:"+url);
			System.out.println("username:"+username);
			System.out.println("password:"+password);
			
			ds = new BasicDataSource();
			ds.setDriverClassName(driverclass);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			//设置最大连接数
			ds.setMaxActive(maxActive);
			//设置最大等待时间
			ds.setMaxWait(maxWait);
			
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 获取一个数据库连接
	 * */
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		try {
			/*
			 * 向连接池获取连接
			 * 若连接池中没有可用连接时，该方法会阻塞当前线程，阻塞时间由连接池设置的maxWait决定。当阻塞过程中连接池有了可用连接时会立即将连接返回。
			 * 若超时仍然没有可用连接时，该方法会抛出异常
			 * */
			return ds.getConnection();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void closeConnection(Connection conn){
		try {
			if(conn!=null){
				conn.setAutoCommit(true);
				/*
				 * 连接池的连接对于close方法的处理是将连接的状态设置为空闲而非真的将其关闭
				 * */
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
