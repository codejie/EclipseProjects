package jie.java.lac.maker.databasegenerator;

import java.sql.SQLException;

import jie.java.lac.maker.common.DBHelper;

public class Generator {

	public static void main(String[] args) {
		Generator generator = new Generator(Generator.DEFAULT_DB_FILE);
		generator.generate();
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
		String sql = "CREATE TABLE [sys_info] ([idx] INTEGER,[Value] TEXT)";
		dbHelper.execSQL(sql);
		
		sql = "CREATE TABLE [dict_info] ([idx] INTEGER, [state] INTEGER, [title] TEXT, [file] TEXT, [offset] INTEGER, [d_decoder] INTEGER, [x_decoder] INTEGER, [source] TEXT, [target] TEXT, [owner] TEXT, version INTEGER)";
		dbHelper.execSQL(sql);
		
		sql = "CREATE TABLE [word_info] ([idx] INTEGER PRIMARY KEY AUTOINCREMENT, [word] TEXT NOT NULL, [flag] INTEGER)";
		dbHelper.execSQL(sql);
		
		sql = "CREATE TABLE [word_extra_data] ([word_idx] INTEGER PRIMARY KEY, [text_data] TEXT, [int_data] INTEGER)";
		dbHelper.execSQL(sql);
	}
	
}
