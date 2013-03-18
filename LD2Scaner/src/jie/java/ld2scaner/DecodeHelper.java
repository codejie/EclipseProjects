package jie.java.ld2scaner;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class DecodeHelper {
	
	public class Decoder {
		private Charset charset = null;
		private CharsetDecoder decoder = null;
		private int charsPerByte = -1;
		
		public Decoder(final String name) {
			charset = Charset.forName(name);
			decoder = charset.newDecoder();
			charsPerByte = (int) decoder.maxCharsPerByte();
		}
		
		public final char[] decode(final ByteBuffer in, final int size, final CharBuffer out) {
			char[] ret = new char[size * charsPerByte];
			
		}
	}
	
}
