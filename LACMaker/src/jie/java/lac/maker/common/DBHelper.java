package jie.java.lac.maker.common;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class DBHelper {

	private Connection conn = null;
	
	public DBHelper() {
	}
	
	public int init(String dbfile) throws SQLException {
		try {
			
			Class.forName("org.sqlite.JDBC");
			
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile);
			conn.setAutoCommit(false);			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}		
		return 0;
	}
	
	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void execSQL(final String sql) throws SQLException {
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} finally {
			stat.close();
		}
	}	
	
	private int execSQLWithReturn(final String sql) throws SQLException {
		int last = -1;
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);

			last = stat.getGeneratedKeys().getInt(1);
		} finally {
			stat.close();
		}
		return last;
	}

}
