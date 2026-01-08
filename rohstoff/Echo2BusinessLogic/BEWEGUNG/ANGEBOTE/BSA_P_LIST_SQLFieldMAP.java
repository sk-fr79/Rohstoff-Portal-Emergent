package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BSA_P_LIST_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSA_P_LIST_SQLFieldMAP() throws myException 
	{
		super("JT_VPOS_STD", ":ID_VKOPF_STD:", false);
		
		
		/*
		 * beschraenkung fuer das Feld VORGANG_TYP
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VPOS_STD","ID_VKOPF_STD","ID_VKOPF_STD",new MyE2_String("ID-VKOPF"),"NULL",bibE2.get_CurrSession()), false);

		
		/*
		 * zusatzfelder aus der JT_VPOS_STD_ANGEBOT
		 */
		this.add_JOIN_Table("JT_VPOS_STD_ANGEBOT", 
				"JT_VPOS_STD_ANGEBOT", 
				SQLFieldMAP.LEFT_OUTER, 
				":GUELTIG_VON:GUELTIG_BIS:", true, "JT_VPOS_STD_ANGEBOT.ID_VPOS_STD=JT_VPOS_STD.ID_VPOS_STD", 
				"",null);

		/*
		 * zusatzfelder aus der JD_WAEHRUNG
		 */
		this.add_JOIN_Table("JD_WAEHRUNG", 
				"JD_WAEHRUNG", 
				SQLFieldMAP.LEFT_OUTER, 
				":KURZBEZEICHNUNG:WAEHRUNGSSYMBOL:", true, "JT_VPOS_STD.ID_WAEHRUNG_FREMD=JD_WAEHRUNG.ID_WAEHRUNG", 
				"",null);
		
		
		// dann ein query-field fuer datum von-bis
		this.add_SQLField(new SQLField(" SUBSTR(  NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'DD.MM.YYYY'),'-------'),1,6)||' - '||SUBSTR(  NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_BIS,'DD.MM.YYYY'),'------'),1,6)","DATUMSBEREICH",new MyE2_String("Gültigkeit"),bibE2.get_CurrSession()), false);

		this.add_SQLField(new SQLField(" NVL(JT_VPOS_STD.ANR1,'----')||' - '||NVL(JT_VPOS_STD.ANR2,'--')","ANR1_ANR2",new MyE2_String("Artikelcode"),bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField(" NVL(JD_WAEHRUNG.WAEHRUNGSSYMBOL,'?')||'/'||NVL(JT_VPOS_STD.EINHEIT_PREIS_KURZ,'')","WAEHRUNG_PRO_EINHEIT",new MyE2_String("FW"),bibE2.get_CurrSession()), false);
		
		
		/*
		 * defaultwerte setzen
		 */
		//this.get_("POSITIONSKLASSE").set_cDefaultValueFormated(myCONST.VG_POSITIONSKLASSE_BELEGPOSITION);
		this.get_("POSITION_TYP").set_cDefaultValueFormated(myCONST.VG_POSITION_TYP_ARTIKEL);
		this.get_("LAGER_VORZEICHEN").set_cDefaultValueFormated("0");
		this.get_("MENGENDIVISOR").set_cDefaultValueFormated("1");
		
		this.get_("WAEHRUNGSKURS").set_cDefaultValueFormated("1");

		
		/*
		 * must-values
		 */
		this.get_("POSITION_TYP").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		
		// standard-sortierung nach positionsnummer
		this.add_ORDER_SEGMENT("JT_VPOS_STD.POSITIONSNUMMER");
		
		this.initFields();
	}

}
