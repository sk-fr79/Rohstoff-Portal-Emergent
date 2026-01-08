package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_CONST
{		
	public enum SONDERNAMEN {
		COMP_ANLAGEN_EMAILS
	}
	
	
	public static String KEY_SPALTE_BETRAGBLOCK = 						"KEY_SPALTE_BETRAGBLOCK";
	public static String KEY_SPALTE_SUMME_ZAHLUNGEN = 					"KEY_SPALTE_SUMME_ZAHLUNGEN";
	public static String KEY_SPALTE_REST = 								"KEY_SPALTE_REST";
	public static String KEY_SPALTE_DOWNLOAD_RECH_GUT_AUS_ARCHIV  = 	"KEY_SPALTE_DOWNLOAD_RECH_GUT_AUS_ARCHIV";
	public static String KEY_SPALTE_JUMP_TO_RG  = 						"KEY_SPALTE_JUMP_TO_RG";
	
	public static String KEY_SPALTE_INFOS  = 							"KEY_SPALTE_INFOS";
	
	public static String KEY_SPALTE_MAHNUNGSBUTTONS = 					"KEY_SPALTE_MAHNUNGSBUTTONS";
	public static String KEY_SPALTE_MAHNUNGSBUTTONS_NEU= 				"KEY_SPALTE_MAHNUNGSBUTTONS_NEU";
	public static String KEY_SPALTE_FIBU_INFOS_AUS_ADRESSE = 			"KEY_SPALTE_FIBU_INFOS_AUS_ADRESSE";
	public static String KEY_SPALTE_FIBU_INFOS_INTERN = 				"KEY_SPALTE_FIBU_INFOS_INTERN";
	
	public static String ORDER_ADRESS_UP = 			"NVL(JT_ADRESSE.NAME1,'')||' '||NVL(JT_ADRESSE.ORT,'')||TO_CHAR(JT_FIBU.ID_ADRESSE)  ASC,JT_FIBU.BUCHUNGSBLOCK_NR ASC,JT_FIBU.ID_FIBU ASC";
	public static String ORDER_ADRESS_DOWN = 		"NVL(JT_ADRESSE.NAME1,'')||' '||NVL(JT_ADRESSE.ORT,'')||TO_CHAR(JT_FIBU.ID_ADRESSE) DESC,JT_FIBU.BUCHUNGSBLOCK_NR ASC,JT_FIBU.ID_FIBU ASC";
	public static String ORDER_BUCHUNGSBLOCK_UP = 	"JT_FIBU.BUCHUNGSBLOCK_NR ASC,JT_FIBU.ID_FIBU ASC";
	public static String ORDER_BUCHUNGSBLOCK_DOWN = "JT_FIBU.BUCHUNGSBLOCK_NR DESC,JT_FIBU.ID_FIBU ASC";
	
	public static Vector<String> ORDER_TYPES = bibALL.get_Vector(
						FIBU_CONST.ORDER_ADRESS_UP, 
						FIBU_CONST.ORDER_ADRESS_DOWN,
						FIBU_CONST.ORDER_BUCHUNGSBLOCK_UP, 
						FIBU_CONST.ORDER_BUCHUNGSBLOCK_DOWN);
	
	
	public static String KEY_SPALTE_ZEIGE_STORNO_INFOS = 		"KEY_SPALTE_ZEIGE_STORNO_INFOS";
	

	
	public static String get_SelectQueryFibuFirmenBase(boolean bComplete, String cAddonToFirma)
	{
		String cQuery = "SELECT DISTINCT   NVL(JT_ADRESSE.NAME1,'-')||'-'||  NVL(JT_ADRESSE.NAME2,'-')||" +
							"'-'||  NVL(JT_ADRESSE.ORT,'-') ||' ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')'||'" + S.NN(cAddonToFirma)+ "' AS NAMEN , JT_ADRESSE.ID_ADRESSE "+
							" FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE IN "+
							" (SELECT DISTINCT JT_FIBU.ID_ADRESSE FROM "+bibE2.cTO()+".JT_FIBU WHERE NVL(JT_FIBU.VORLAEUFIG,'N')='N' ";
		
		if (bComplete)
		{
			cQuery += ")" +" ORDER BY NAMEN";
		}
			    
		return cQuery;
	}


	
	//2012-06-19: fragt die firmen ab, die zwar belege enthalten, aber keine offenen
	public static String get_SelectQueryFibuFirmenNurAbgeschlosseneBelege(String cAddonToFirma)
	{
		String cQuery = "SELECT DISTINCT   NVL(JT_ADRESSE.NAME1,'-')||'-'||  NVL(JT_ADRESSE.NAME2,'-')||" +
							"'-'||  NVL(JT_ADRESSE.ORT,'-') ||' ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')'||'" + S.NN(cAddonToFirma)+ "' AS NAMEN , JT_ADRESSE.ID_ADRESSE "+
							" FROM "+bibE2.cTO()+".JT_ADRESSE " +
						     " WHERE ID_ADRESSE IN "+
							    " (SELECT DISTINCT JT_FIBU.ID_ADRESSE FROM "+bibE2.cTO()+".JT_FIBU WHERE NVL(JT_FIBU.VORLAEUFIG,'N')='N') "+
							" AND ID_ADRESSE NOT IN "+
						        " (SELECT DISTINCT JT_FIBU.ID_ADRESSE FROM "+bibE2.cTO()+".JT_FIBU WHERE NVL(JT_FIBU."+RECORD_FIBU.FIELD__BUCHUNG_GESCHLOSSEN+",'N')='N')";
		
		return cQuery;
	}
	
	

	public enum BUCHUNGSTYP implements IF_enum_4_db {
		 DRUCK_RECHNUNG("Rech","Rechnung","Druck Rechnung","Beleg wurde durch Rechnungsdruck erzeugt",true)
		,DRUCK_GUTSCHRIFT("Gut","Gutschrift","Druck Gutschrift","Beleg wurde durch Gutschriftsdruck erzeugt",true)
		,ZAHLUNGSEINGANG("ZE","Zahlungseingang","Zahlungseingang","Beleg wurde durch das Erfassen eines Zahlungseingangs erzeugt",false)
		,ZAHLUNGSAUSGANG("ZA","Zahlungsausgang","Zahlungsausgang","Beleg wurde durch das Erfassen eines Zahlungsausgangs erzeugt",false)
		,SCHECKDRUCK("SD","Scheckdruck","Zahlungsausgang durch Scheckdruck","Beleg wurde durch den Druck eines Schecks erzeugt",false)
		;
		
		private String db_val = null;
		private String kurz = null;
		private String klarname = null;
		private String herkunft = null;
		private String erleuterung = null;
		private boolean b_is_from_beleg = false;
	
		private BUCHUNGSTYP(String p_kurz, String p_klarname, String p_herkunft, String p_erleuterung, boolean b_is_beleg) {
			this.db_val = this.name();
			this.kurz = p_kurz;
			this.klarname = p_klarname;
			this.herkunft = p_herkunft;
			this.erleuterung = p_erleuterung;
			this.b_is_from_beleg = b_is_beleg;
		}
		public String get_db_val() 		{ 	return db_val;		}
		public String getKurz() 		{	return kurz;		}
		public String getKlarname() 	{	return klarname;	}
		public String getHerkunft() 	{	return herkunft;	}
		public String getErleuterung() 	{	return erleuterung;	}
		
		@Override
		public String db_val() {
			return null;
		}
		@Override
		public String user_text() {
			return klarname;
		}
		@Override
		public String user_text_lang() {
			return erleuterung;
		}
		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(BUCHUNGSTYP.values(), emptyPairInFront);
		}
		public boolean is_beleg() {
			return b_is_from_beleg;
		}
		
		public static BUCHUNGSTYP find_buchungstyp(String db_val) {
			if (S.isFull(db_val)) {
				for (BUCHUNGSTYP  typ: BUCHUNGSTYP.values()) {
					if (typ.get_db_val().equals(db_val)) {
						return typ;
					}
				}
			}
			return null;
		}
	}
	
	public enum FORMULARSTYP{
		ZAHLUNGSAVIS("ZAHLUNGSAVIS"),
		VERRECHNUNGSVERREINBARUNG("VERRECHNUNGSVERREINBARUNG"),
		KONTOKORRENTABREDE("KONTOKORRENTABREDE");
		
		String typ;
		
		private FORMULARSTYP(String p_typ) {
			typ = p_typ;
		}
		
		public String getTyp() {
			return typ;
		}
	}
}
