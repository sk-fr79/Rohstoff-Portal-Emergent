package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import panter.gmbh.indep.bibALL;

public class FileNameCleaner {

	public enum CHAR_REPLACE {
		 AE("Ä")
		,OE("Ö")
		,UE("Ü")
		,ae("ä")
		,oe("ö")
		,ue("ü")
		,ss("ß")

		;
		private String c_orig_unclean = null;
		private CHAR_REPLACE(String p_orig_unclean) {
			this.c_orig_unclean=p_orig_unclean;
		}
		
		public String clean() {
			return this.name();
		}
		
		public String unclean() {
			return this.c_orig_unclean;
		}
	}

	
	public static String  allowed_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890._-!?() ";
//	public static String  allowed_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890._-!()";
	private String  repace_char_4_dirty_chars = "_";
	
	
	private String name_dirty = null;
	private String name_clean = null;
	
	public FileNameCleaner(String c_orig_dirty) {
		super();
		
		this.name_dirty = c_orig_dirty;
		this.name_clean = this.clean(this.name_dirty);
	}

	private String clean(String dirty) {
		String c_clean = dirty;
		
		//zuerst die zusammengesetzten
		for (CHAR_REPLACE rp: CHAR_REPLACE.values()) {
			c_clean= bibALL.ReplaceTeilString(c_clean, rp.unclean(), rp.clean());
		}
		
		//dann die simplen
		StringBuffer sb = new StringBuffer();
		for (char c :  c_clean.toCharArray()) {
			if (FileNameCleaner.allowed_chars.contains(""+c)) {
				sb.append(c);
			} else {
				sb.append(this.repace_char_4_dirty_chars);
			}
		}
		c_clean=sb.toString();
		
		return c_clean;
	}
	
	
	public String get_filename_orig_dirty() {
		return this.name_dirty;
	}

	public String get_filename_clean() {
		return this.name_clean;
	}

	public String get_repace_char_4_dirty_chars() {
		return repace_char_4_dirty_chars;
	}

	public void set_repace_char_4_dirty_chars(String repace_char_4_dirty_chars) {
		this.repace_char_4_dirty_chars = repace_char_4_dirty_chars;
	}
	
}
