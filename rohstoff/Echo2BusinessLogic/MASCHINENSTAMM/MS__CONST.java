package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_ENUMS.MASCHINEN;
import panter.gmbh.basics4project.DB_ENUMS.MASCHINEN_KOSTEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.SELECT.MinLenght;
import panter.gmbh.indep.enumtools.IF_enum_4_fielddefinition;

public class MS__CONST 
{
	public static String HASHKEY_MASK_INLAY_AUFGABEN = 	"HASHKEY_MASK_INLAY_AUFGABEN";
	public static String HASHKEY_MASK_INLAY_KOSTEN = 	"HASHKEY_MASK_INLAY_KOSTEN";
	
	public static String HASHKEY_MASK_INLAY_CONNECTOR = "HASHKEY_MASK_INLAY_CONNECTOR";
	public static String HASHKEY_LIST_KOSTENANZEIGE = "HASHKEY_LIST_KOSTENANZEIGE";
	
	public static String HASHKEY_LIST_KOSTENSTELLE_GES = "LIST_KOSTENSTELLE_GES";

	
	public enum ADDON_FIELDS implements IF_enum_4_fielddefinition {

		LIST_KOSTENSTELLE_GESAMT("LIST_KOSTENSTELLE_GESAMT",new MinLenght(MASCHINEN.kostenstelle1,3,'0').s()+"||'.'||"+new MinLenght(MASCHINEN.kostenstelle2,3,'0').s(),new MyE2_String("Kostenstelle Gesamt"))
		,LIST_ANZAHL_KOSTEN("ANZAHL_KOSTEN", "(SELECT COUNT(*) FROM "+_TAB.maschinen_kosten.n()+" WHERE "+MASCHINEN_KOSTEN.id_maschinen.tnfn()+"="+MASCHINEN.id_maschinen.tnfn()+")",S.ms("Kosten"))
		;
		
		private String sql_def = null;
		private String alias = null;
		private MyE2_String  fieldinfo = null;
		

		/**
		 * @param p_alias
		 * @param p_sql_def
		 * @param p_fieldinfo
		 */
		private ADDON_FIELDS(String p_alias, String p_sql_def, MyE2_String p_fieldinfo) {
			this.alias = p_alias;
			this.sql_def = p_sql_def;
			this.fieldinfo = p_fieldinfo;
		}

		@Override
		public String alias() {
			return this.alias;
		}

		@Override
		public String querydef() {
			return this.sql_def;
		}

		@Override
		public String user_text_lang() {
			return this.fieldinfo.CTrans();
		}

		@Override
		public IF_enum_4_fielddefinition[] get_all_defs() {
			return ADDON_FIELDS.values();
		}
		
	}
	
	
}
