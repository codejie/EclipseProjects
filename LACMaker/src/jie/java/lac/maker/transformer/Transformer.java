package jie.java.lac.maker.transformer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jie.java.lac.maker.common.DBHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Transformer {

	public static void main(String[] args) {
		Transformer trans = new Transformer();
		trans.analyse(".\\doc\\transformer_template.xml");
	}
	
	public List<DictInfo> analyse(final String file) {
		
		ArrayList<DictInfo> dict = new ArrayList<DictInfo>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			Document doc = factory.newDocumentBuilder().parse(file);
//			processDoc(doc);
//			
			NodeList node = doc.getElementsByTagName("dictionary");
			for (int i = 0; i < node.getLength(); ++ i) {
				Node n = node.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					DictInfo info = new DictInfo();
					NodeList child = n.getChildNodes();
					for (int j = 0; j < child.getLength(); ++ j) {
						n = child.item(j);
						if (n.getNodeType() == Node.ELEMENT_NODE) {
							if (n.getNodeName() == "id") {
								info.id = Integer.valueOf(n.getChildNodes().item(0).getNodeValue());
								System.out.println(n.getNodeName() + " value : " + n.getChildNodes().item(0).getNodeValue());
							} else if (n.getNodeName() == "file") {
								info.file = n.getChildNodes().item(0).getNodeValue();
								System.out.println(n.getNodeName() + " value : " + n.getChildNodes().item(0).getNodeValue());
							} else if (n.getNodeName() == "title") {
								info.title = n.getChildNodes().item(0).getNodeValue();
								System.out.println(n.getNodeName() + " value : " + n.getChildNodes().item(0).getNodeValue());
							} else if (n.getNodeName() == "revision") {
								info.revision = Integer.valueOf(n.getChildNodes().item(0).getNodeValue());
								System.out.println(n.getNodeName() + " value : " + n.getChildNodes().item(0).getNodeValue());
							} else if (n.getNodeName() == "source") {
								info.source = n.getChildNodes().item(0).getNodeValue();
								System.out.println(n.getNodeName() + " value : " + n.getChildNodes().item(0).getNodeValue());
							} else if (n.getNodeName() == "target") {
								info.target = n.getChildNodes().item(0).getNodeValue();
								System.out.println(n.getNodeName() + " value : " + n.getChildNodes().item(0).getNodeValue());
							} else if (n.getNodeName() == "extra_field") {
								info.extra_field = n.getChildNodes().item(0).getNodeValue();
								System.out.println(n.getNodeName() + " value : " + n.getChildNodes().item(0).getNodeValue());
							}
						}
					}
					if (info.check()) {
						dict.add(info);
					}
				}
			}			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return dict;
	}
	
	public boolean transform(final String dbfile, final List<DictInfo> dict) {
		DBHelper db = new DBHelper();
		
		try {
			if (db.init(dbfile) != 0) {
				return false;
			}			
			
			for (final DictInfo info : dict) {
				if (!transform(db, info)) {
					return false;
				}
			}
		} catch (SQLException e) {
			
			return false;
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return true;
	}
	
	public boolean transform(DBHelper db, final DictInfo info) throws SQLException {
		//db
		makeDatabase(db, info);
		
		//block
		//data
		
		
		return false;
	}
	
	private void makeDatabase(DBHelper db, DictInfo info) throws SQLException {
		//create tables
		String sql = "CREATE TABLE [block_info_" + info.id + "] ([idx] INTEGER PRIMARY KEY, [offset] INTEGER, [length] INTEGER, [start] INTEGER, [end] INTEGER);";
		db.execSQL(sql);
		
		sql = "CREATE TABLE [word_index_" + info.id + "] ( [word_idx] INTEGER, [idx] INTEGER, [offset] INTEGER, [length] INTEGER, [block1] INTEGER);";
		db.execSQL(sql);
		
		sql = "CREATE INDEX [index_word_index_" + info.id + "_word_idx] ON [word_index_" + info.id + "] (word_idx ASC);";
		db.execSQL(sql);

		sql = "CREATE TABLE [word_extra_data_" + info.id + "] ([word_idx] INTEGER, [text_data] TEXT, [int_data] INTEGER)";
		db.execSQL(sql);

		sql = "CREATE INDEX [index_word_extra_data_" + info.id + "_word_idx] ON [word_extra_data_" + info.id + "] (word_idx ASC);";
		db.execSQL(sql);
		
		//write dict info		
	}

	
}
