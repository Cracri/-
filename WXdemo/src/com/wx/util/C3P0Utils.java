package com.wx.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//���ӳ�
public class C3P0Utils {
		
		//��ComboPooledDataSource()  �޲ε������  Ĭ�϶�ȡc3p0-config.xml�����е� Ĭ��ѡ�
		public static ComboPooledDataSource dataSourse = new ComboPooledDataSource();
		
		public static DataSource getDataSourse() {
			return dataSourse;
		} 
		
		public static Connection getConnection() {
			try {
				return dataSourse.getConnection();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
}
