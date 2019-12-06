package com.wx.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class DBconn {
	static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet res = null;
	
	static String url = "jdbc:mysql://172.27.0.14:3306/greens";
	static String username = "root";
	static String password = "xh19990928";
	
	//���ݿ������
	static public void init() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("���ݿ�����ʧ��");
			e.printStackTrace();
		}	
	}
	
	
	//���ݿ�Ĺر�
	static public void closeconn() {
		try {
			conn.close();
			ps.close();
			res.close();
		} catch (SQLException e) {
			System.out.println("���ݿ�ر��쳣");
			e.printStackTrace();
		}	
	}
	
	//���ݿ�Ĳ�ѯ����
	static public ResultSet selectSql(String sql) {
		try {
			ps = conn.prepareStatement(sql);
			res = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("���ݲ�ѯ�쳣");
			e.printStackTrace();
		}
		return res;
	}
	
	//���ݿ��DML(��ɾ��)����
	static public int addUpdDel(String sql) {
		int i=0;
		try {
			ps = conn.prepareStatement(sql);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����DML�����쳣");
			e.printStackTrace();
		}
		return i;
		
	}
	
	
	//�����һ�����ݺ󣬷��ظ����ݵ�id
	static public int addUpdDelForId(String sql) {
		int i=0;
		try {
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			while(rs.next()) {
				i = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
		
	}
	
}
