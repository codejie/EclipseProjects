package jie.java.lac.maker;

import jie.java.lac.maker.databasegenerator.Generator;
import jie.java.lac.maker.transformer.Transformer;

public class Maker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Maker maker = new Maker();
		//maker.load(".\\doc\\transformer_3gpp.xml");
		maker.load(".\\doc\\transformer_vicon_ec.xml");
	}

	private String makefile = null;
	
	public boolean load(final String makefile) {
		
		Generator generator = new Generator(Generator.DEFAULT_DB_FILE);
		if (generator.generate() != 0)
			return false;
		
		Transformer transformer = new Transformer();
		return transformer.transform(makefile, Generator.DEFAULT_DB_FILE);
	}
}
