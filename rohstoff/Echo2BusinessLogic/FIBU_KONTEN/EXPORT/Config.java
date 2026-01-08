package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.math.BigDecimal;

import panter.gmbh.basics4project.TestSession;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.exceptions.myException;

public class Config {
	private static final Config SINGLETON = new Config();
	
	private HashMap<String, Object> prop;

	public static Config instance()
	{
		return SINGLETON;
	}	

	private Object get(String key) {
		return prop.get(key);
	}
	
	public String getString(String key) {
		String s; 
		try {
			s = (String)get(key);
		} catch (ClassCastException e) {
			s = get(key).toString();
		}
		return s;
		
	}
	
	public int getInt(String key) {
		return ((BigDecimal)get(key)).intValue();
	}
	
	public HashMap<Integer, String> getHashMap(String key) {
		String val = getString(key);
		String[] temp;
		HashMap<Integer, String> res = new HashMap<Integer, String>();
		temp = val.split(",");
		for(int i =0; i < temp.length ; i++) {
		    res.put(i, temp[i]);
		}
		return res;
	}

	private Config() {
		reload();
	}
	
	public void reload()  {
        // Load properties from file in root path
    	this.prop = new HashMap<String, Object>();
   	 
		String idExportProfile = null;
		try {
			idExportProfile = bibALL.get_RECORD_USER()
					.get_ID_DATEV_PROFILE_cUF_NN("");
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SELECT s = new SELECT("*").from("JT_DATEV_PROFILE").where("ID_DATEV_PROFILE", idExportProfile);
		try {
			HashMap<String, Object> res = DBUtil.selectOne(s);
			this.prop = res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this.prop);
		
				
    }
	
	private boolean getBooleanProperty(String propertyName) {
		boolean answer = false;
		String tmp = getString(propertyName);
		if (tmp != null && (tmp.equalsIgnoreCase("1") || tmp.equalsIgnoreCase("true") || tmp.equalsIgnoreCase("Y"))) {
			answer = true;	
		}
		return answer; 
	}

	/**
	 * Whether to flush the export tables before doing an export. 
	 * Use with great caution; this deletes all old exports upon beginning
	 * a new export. Defaults to false.
	 * @return
	 */
	public boolean flushExportTablesBeforeExporting() {
		//return getBooleanProperty("FLUSH_TABLES");
		return false;
	}

	/**
	 * Whether to include the debug protocols in the output data
	 * (along with the CSVs) the exporter produces.
	 */
	public boolean printProtocols() {
		return getBooleanProperty("PRINT_PROTOCOLS");
	}

	/**
	 * Whether to correct the dates as such as that dates of invoices
	 * that are disallowed for booking are being corrected to the first
	 * possible/allowed date.
	 */
	public boolean correctDates() {
		return getBooleanProperty("CORRECT_DATES");
	}
	
	/**
	 * Whether to stamp the Import with an "I" debug flag, so that 
	 * one will see entries being imported. Used during transition
	 * between manual and imported booking. Is supposed to be
	 * set to false after the full introduction of the exporter 
	 * (which is expected in fall 2015).
	 */
	public boolean stampImportedWithDebugflag() {
		return getBooleanProperty("STAMP_IMPORTED_WITH_DEBUGFLAGS");
	}
	
	public static String getWebInfPath() {
		String s = Config.class.getClassLoader().getResource(".")
				.toString();
		s = s.substring(5); // Strip file:
		s = s.substring(0, s.indexOf("/WEB-INF/") + 9);
		return s;
	}
	
	/** 
	 * Returns the SQL date for first vKopf to be taken into account for 
	 * the DatevExporter. All vkopf before this date will not be taken
	 * into account for the exporter. 
	 */
	public String getFirstRelevantVkopfExportDate() {
		String answer = ((java.sql.Timestamp)get("EXPORTS_STARTING_DATE")).toString().substring(0, 10);
		if (answer == null || answer.equals("")) {
			answer = "2014-01-01";
		}
		return answer;
	}

	/** 
	 * The first old export ID to be included in the list of old exports
	 * (the dropdown menu in {@see FIBU_EXPORT_LIST_BedienPanel}).
	 * If the {@see Config} file has a such a first old export id set to be printed, only
	 * IDs largert that the one configured are printed to the list (to prevent overflow).
	 */
	public String getFirstOldExportId() {
		String answer = "" + getString("EXPORTS_STARTING_ID");
		if (answer == null || answer.equals("")) {
			answer = "1000";
		}
		return answer;
	}

	public String getExportDir() {
		// TODO Auto-generated method stub
		return getString("EXPORT_DIR");
	}
}