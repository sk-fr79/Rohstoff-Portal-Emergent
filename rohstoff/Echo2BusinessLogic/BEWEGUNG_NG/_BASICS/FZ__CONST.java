package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS;

import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.enumtools.IF_enum_4_fielddefinition;
import panter.gmbh.indep.exceptions.myException;

public class FZ__CONST {

	
	/**
	 * feldbreiten vereinheitlichung
	 * @author martin
	 *
	 */
	public enum FIELD_WIDTH {
		
		 ADRESSSUCHE(90)
		,ARTIKEL_SUCHE(90)
		,KONTRAKT_SUCHE(90)
		,MENGEN(90)
		,PREIS(90)
		,DATUM(90)
		;
		
		private int pixel = 0;

		/**
		 * @param pixel
		 */
		private FIELD_WIDTH(int p_pixel) {
			this.pixel = p_pixel;
		}

		public int get_pixel() {
			return pixel;
		}
	}
	
	
	public enum COLUMN_WIDTH{
		INFOS(4)
		,STATION(7)
		,SORTE(7)
		,KONTRAKT(10)
		,MENGE(6)
		,PREIS(3)
		,EINHEIT(2)
		,BETRAG(4)
		,AKTION(4)
		,LDATUM(6)
		;
		private int column_span = 0;

		/**
		 * @param pixel
		 */
		private COLUMN_WIDTH(int p_column_span) {
			this.column_span = p_column_span;
		}

		public int get_column_span() {
			return column_span;
		}
	}
	
	
	public enum FIELDS implements 	IF_enum_4_fielddefinition {
		STARTADRESSE_NAME_ORT(null,		"ADS.NAME1||' '||ADS.ORT","Startstation")
		,STARTBESITZER_NAME_ORT(null,	"ADBS.NAME1||' '||ADBS.ORT","Startbesitzer")
		,ZIELADRESSE_NAME_ORT(null,		"ADZ.NAME1||' '||ADZ.ORT","Zielstation")
		,ZIELBESITZER_NAME_ORT(null,	"ADBZ.NAME1||' '||ADBZ.ORT","Zielbesitzer")
		
		;

		private String alias = 		null; 
		private String fieldDef = 	null; 
		private String userText =	null;
		private FIELDS(String p_alias, String p_fieldDef, String p_userText) {
			this.alias = p_alias;
			this.fieldDef = p_fieldDef;
			this.userText = p_userText;
		}
		
		@Override
		public String alias() {
			return S.isEmpty(this.alias)?this.name():this.alias;
		}

		@Override
		public String querydef() {
			return S.isEmpty(this.fieldDef)?this.name():this.fieldDef;
		}

		@Override
		public String user_text_lang() {
			return S.isEmpty(this.userText)?this.name():this.userText;
		}

		@Override
		public IF_enum_4_fielddefinition[] get_all_defs() {
			return FZ__CONST.FIELDS.values();
		}
		
		
	}

	
	
	
	
	//sammelt die sondername der spalten (nicht direkt an felder geknuepft)
	public enum SPALTEN_NAMEN {
		 STARTORT(null,null, new MyE2_String("Information über den Start der Warenbewegung"))
		,ZIELORT(null,null, new MyE2_String("Information über das Ziel der Warenbewegung"))
		,SORTE(null,null, new MyE2_String("Sorte"))
		,VEKTORTYP(null,null, new MyE2_String("Typ"))
		,COMP_GRID_STARTADRESSEN(null,null,new MyE2_String("Startstation/-Besitzer"))
		,COMP_GRID_ZIELADRESSEN(null,null,new MyE2_String("Zielstation/-Besitzer"))
		,BUTTON_VIEW_RECORDSTRUCTURE(null,null,new MyE2_String("Info"))
		,
		
		;
		
		private String 			hashKey = null;    			//key in der listen-componentmap
		private String 			sqlFieldAlias = null; 		//alias, falls ein sql-field dafuer definiert wird
		private MyE2_String  	info4User = null;
		
		private SPALTEN_NAMEN(String p_hashKey, String p_sqlFieldAlias, MyE2_String p_info4User) {
			this.hashKey = 			p_hashKey;
			this.sqlFieldAlias = 	p_sqlFieldAlias;
			this.info4User = 		p_info4User;
		}

		public String hashKey() {
			return this.hashKey==null?this.name():this.hashKey;
		}

		public String sqlFieldAlias() {
			return this.sqlFieldAlias==null?this.name():this.sqlFieldAlias;
		}

		public MyE2_String userInfo() {
			return this.info4User==null?new MyE2_String(this.name(),false):this.info4User;
		}
		
	}

	
	
	
	
	/**
	 * spezielle field-keys fuer diverse felder
	 * @author martin
	 *
	 */
	public enum f_keys {
		
//		 KOMBI_ANG_KON(BEWEGUNG_ATOM.id_vpos_kon)
		EINHEIT_PREIS(BEWEGUNG_ATOM.id_artikel_bez)
		,EINHEIT(BEWEGUNG_ATOM.id_artikel_bez)
		,EINHEIT_PREIS_LEFT(BEWEGUNG_ATOM.id_artikel_bez)
		,EINHEIT_PREIS_RIGHT(BEWEGUNG_ATOM.id_artikel_bez)
		,EINHEIT_LEFT(BEWEGUNG_ATOM.id_artikel_bez)
		,EINHEIT_RIGHT(BEWEGUNG_ATOM.id_artikel_bez)
		,KOMBI_ANG_KON_LEFT(BEWEGUNG_ATOM.id_vpos_kon)
		,KOMBI_ANG_KON_RIGHT(BEWEGUNG_ATOM.id_vpos_kon)
		,LAGER_DIFF_MENGE_WE(BEWEGUNG_ATOM.menge)  			//sonderfeld, falls eine unterschiedliche Lagermenge im Wareneingang gebucht wird (Beispiel: Lieferant wiegt selbst, hat 10000 wir wiegen 9000)
		,ANR12(BEWEGUNG_ATOM.id_artikel_bez)
		,
		
		;
		
		private IF_Field field = null;
		private f_keys(IF_Field f) {
			this.field = f;
		}
		public RB_KF k() throws myException {
			return new RB_KF(this.field, this.name());
		}
	}
	
	
	
	/**
	 * definition der detail-varianten der fuhrenliste
	 * @author martin
	 *
	 */
	public enum DETAIL_SWITCH {
		
		ONELINE_SIMPLE(new MyE2_String("Einfache Ansicht, einzeilig"))
		,DIFFERENT(new MyE2_String("Ausführliche Ansicht"))
		
		
		;
		
		
		private MyE2_String  info4User = null;

		private DETAIL_SWITCH(MyE2_String p_info4User) {
			this.info4User = p_info4User;
		}

		public MyE2_String info4User() {
			return info4User;
		}
		
		
		public static String[][] get_array4SelectField() {
			String[][] s =  new String[DETAIL_SWITCH.values().length][2];
			int count = 0;
			for (DETAIL_SWITCH ds: DETAIL_SWITCH.values()) {
				s[count][0]=ds.info4User.CTrans();
				s[count][1]=ds.name();
				count++;
			}
			
			
			return s;
		}
	}
	

	
	
	public enum SEARCH_ANGEBET_OR_KONTRAKT {
		KONTRAKT
		,ANGEBOT
	}
	
	
	public enum SEARCH_EK_OR_VK {
		EK
		,VK
	}


	
	
	public static 	GridLayoutData  gl = MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,1,4,1));

	
	
}
