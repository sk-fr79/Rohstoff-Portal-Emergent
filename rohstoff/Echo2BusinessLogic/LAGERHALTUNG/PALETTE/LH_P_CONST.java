
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public class LH_P_CONST {


	public enum LH_P_NAMES implements IF_enum_4_db {

		CHECKBOX_LISTE( S.ms("Auswahl-Checkbox"))
		,MARKER_LISTE( S.ms("Markierung Liste"))
		,DIRECT_DEL( S.ms("Loeschbutton in Listenzeile"))
		,DIRECT_EDIT( S.ms("Editbutton in Listenzeile"))
		,DIRECT_VIEW( S.ms("Anzeigebutton in Listenzeile"))
		,DIRECT_UPLOAD( S.ms("Dateien hochladen"))
		,DIRECT_AUS_EIN_BUCHUNG(S.ms("Palette Einbuchen/Ausbuchen"))
		,SHOW_HISTORY(S.ms("Palette Einbuchen/Ausbuchen"))
		,SPRUNG_2_FUHRE(S.ms("Sprung zu Fuhre"))
		,ETIKETT_DRUCKEN(S.ms("Etikett drucken"))
		,FUHRE_DETAIL(S.ms("Fuhre"))
		;

		private MyE2_String userText = null; 
		private String      m_dbVal = null;

		private LH_P_NAMES(MyE2_String p_userText) {
			this.userText = p_userText;
		}

		//konstuktor mit abweichenden werten
		private LH_P_NAMES(MyE2_String p_userText, String dbVal) {
			this.userText = p_userText;
			this.m_dbVal=dbVal;
		}

		@Override
		public String db_val() {
			if (S.isFull(m_dbVal)) {
				return m_dbVal;
			}
			return this.name();
		}

		@Override
		public String user_text() {
			if (S.isEmpty(this.userText)) {
				return this.name();
			} else {
				return this.userText.CTrans();
			}
		}

		@Override
		public String user_text_lang() {
			return this.user_text();
		}

		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return null;
		}

	}



	/**
	 * 
	 * @return der ueberall verwendete mask-key
	 * @throws myException
	 */
	public static _TAB getLeadingTable() throws myException {
		return _TAB.lager_palette;
	}


	/**
	 * 
	 * @return der ueberall verwendete mask-key
	 * @throws myException
	 */
	public static RB_KM getLeadingMaskKey() throws myException {
		return _TAB.lager_palette.rb_km();
	}

	public enum ENUM_PALETTE_LISTKEY{
		EINBUCHUNGSDATUM_LISTKEY("KEY_EINBUCHUNGSDATUM")
		,AUSBUCHUNGSDATUM_LISTKEY("KEY_AUSBUCHUNGSDATUM")
		,ARTIKEL_INFO ("KEY_ARTIKEL_INFO")
		;

		private String key;
    	
		private ENUM_PALETTE_LISTKEY(String p_key) {
    		this.key = p_key;
    	}
    	
    	public String k() {
    		return key;
    	}
	}



	public enum LH_P_BUTTONS implements IF_enum_4_db  {
		DELETE("LOESCHE_LAGER_PALETTE")
		,EDIT("BEARBEITE_LAGER_PALETTE")
		,VIEW("ANZEIGE_LAGER_PALETTE")
		,NEW("NEUEINGABE_LAGER_PALETTE")


		;

		private String KEY = null;

		private LH_P_BUTTONS() {
			this.KEY=this.name();
		}
		private LH_P_BUTTONS(String key) {
			this.KEY=key;
		}
		public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
		@Override
		public String user_text_lang() {return this.name();    }
		@Override
		public String user_text() {        return this.name();        }
		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(LH_P_BUTTONS.values(), emptyPairInFront);
		}

	}


	/**
	 * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
	 * @author martin
	 *
	 */
	public enum TRANSLATOR {

		//bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner LAGER_PALETTE_LIST  und LAGER_PALETTE_MASK ergaenzen,
		//sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
		/*
		 * beispiele:
         ,LAGER_PALETTE_LIST(MODUL_TYP.LIST		, S.ms("Listenspalten (Liste)"))
	     ,LAGER_PALETTE_MASK(MODUL_TYP.MASK_RB	, S.ms("Listenspalten (Maske)"))
		 *
		 */


		LIST						(E2_MODULNAME_ENUM.MODUL.LAGER_PALETTE_LIST)
		,MASK						(E2_MODULNAME_ENUM.MODUL.LAGER_PALETTE_MASK)
		,LAGER_PALETTE_LIST_INTERNAL(E2_MODULNAME_ENUM.MODUL.LAGER_PALETTE_LIST_INTERNAL)
		;

		private MODUL  modul = null;
		private TRANSLATOR(E2_MODULNAME_ENUM.MODUL p_modul) {
			modul = p_modul;
		}
		public MODUL get_modul() {
			return modul;
		}
	}




	/*
	 * enum: hier koennen numerische (long) werte zentral gesteuert werden 
	 */
	public static enum LH_P_NUM_CONST {
		MASKPOPUP_WIDTH(new Integer(800))
		,MASKPOPUP_HEIGHT(new Integer(800))
		,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(160))
		,MASKPOPUP_FIELD_COL_SIZE(new Integer(700))

		;

		private Integer   m_value = null;

		private LH_P_NUM_CONST(Integer p_value) {
			this.m_value=p_value;
		}

		public Integer getValue() {
			return this.m_value;
		}
	}


}


