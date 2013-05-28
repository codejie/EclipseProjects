package jie.java.lac.maker.databasegenerator;

import java.sql.SQLException;

import jie.java.lac.maker.common.DBHelper;

public class Generator {

	public static void main(String[] args) {
	}
	
	public static final String DEFAULT_DB_FILE	=	"lac2.db";
	
	private String dbFile = null;
	private DBHelper dbHelper = null;
	
	public Generator (final String file) {
		dbFile = file;
	}

	public int generate() {
		dbHelper = new DBHelper();
		
		try {
			if (dbHelper.init(dbFile) != 0) {
				return -1;
			}
			
			createTables();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			dbHelper.close();
		}
		
		return 0;
	}

	private void createTables() throws SQLException {
		
	}
	
}
