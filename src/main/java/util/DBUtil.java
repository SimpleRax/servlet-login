package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

/**
 * translate.baidu.com
 * 
 * ���ݿ����ӵĹ�����
 * */
public class DBUtil {
	private static BasicDataSource ds;
	static{
		try {
			Properties prop = new Properties();
			/*
			 * DBUtil.calss.getClassLoader();��ü���DBUtil���������(ClassLoader)
			 * gerResourceAsStream����������ṩ�ķ�����������·�������������ļ�(.properties�ļ�)����InputStream
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
			//�������������
			ds.setMaxActive(maxActive);
			//�������ȴ�ʱ��
			ds.setMaxWait(maxWait);
			
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * ��ȡһ�����ݿ�����
	 * */
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		try {
			/*
			 * �����ӳػ�ȡ����
			 * �����ӳ���û�п�������ʱ���÷�����������ǰ�̣߳�����ʱ�������ӳ����õ�maxWait���������������������ӳ����˿�������ʱ�����������ӷ��ء�
			 * ����ʱ��Ȼû�п�������ʱ���÷������׳��쳣
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
				 * ���ӳص����Ӷ���close�����Ĵ����ǽ����ӵ�״̬����Ϊ���ж�����Ľ���ر�
				 * */
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
