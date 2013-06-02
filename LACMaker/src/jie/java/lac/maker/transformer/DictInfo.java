package jie.java.lac.maker.transformer;

public class DictInfo {
	public Integer id = -1;
	public String file = null;
	public String title = null;
	public Integer revision = 0;
	public String source = null;
	public String target = null;
	public String extra_field = null;
	
	public boolean check() {
		return id != -1 && file != null && title != null && source != null 
				&& target != null;
	}
	
	public String toString() {
		return null;
	}
}
