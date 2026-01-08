package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class UMA_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public UMA_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_UMA_KONTRAKT", "", false);
		
		this.add_SQLField(new SQLField("JT_UMA_KONTRAKT.ID_ADRESSE", "INFO_ID_ADRESSE", new MyE2_String("Zusatzfeld-ID-Adresse"),  bibE2.get_CurrSession()),false);

		this.add_JOIN_Table("JT_ADRESSE", 
				"JT_ADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:ORT", true, "JT_UMA_KONTRAKT.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE", "", 
			bibALL.get_HashMap("NAME_ORT", "SUBSTR(NVL(JT_ADRESSE.NAME1,'')||' - '||NVL(JT_ADRESSE.ORT,'')||'                               ',1,30)"));
//
//		this.add_JOIN_Table("JT_ARTIKEL_BEZ", 
//				"ARTIKEL_BEZ_AUSGANG", 
//				SQLFieldMAP.LEFT_OUTER, 
//				":ANR2:", true, "JT_UMA_KONTRAKT.ID_ARTIKEL_BEZ_AUSGANG=ARTIKEL_BEZ_AUSGANG.ID_ARTIKEL_BEZ", "ARTBEZ_AUSGANG", 
//			null);

		this.add_JOIN_Table("JD_USER", 
				"JD_USER", 
				SQLFieldMAP.LEFT_OUTER, 
				":ID_USER:", true, "JT_UMA_KONTRAKT.ID_USER_BETREUER=JD_USER.ID_USER", "BETREUER", 
			null);

		
//		this.add_JOIN_Table("JT_ARTIKEL_BEZ", 
//				"ARTIKEL_BEZ_ZIEL", 
//				SQLFieldMAP.LEFT_OUTER, 
//				":ANR2:", true, "JT_UMA_KONTRAKT.ID_ARTIKEL_BEZ_ZIEL=ARTIKEL_BEZ_ZIEL.ID_ARTIKEL_BEZ", "ARTBEZ_ZIEL", 
//			null);
//		
//		this.add_JOIN_Table("JT_ARTIKEL", 
//				"ARTIKEL_AUSGANG", 
//				SQLFieldMAP.LEFT_OUTER, 
//				":ANR1:", true, "ARTIKEL_BEZ_AUSGANG.ID_ARTIKEL=ARTIKEL_AUSGANG.ID_ARTIKEL", "ARTIKEL_AUSGANG", 
//			null);
//
//		this.add_JOIN_Table("JT_ARTIKEL", 
//				"ARTIKEL_ZIEL", 
//				SQLFieldMAP.LEFT_OUTER, 
//				":ANR1:", true, "ARTIKEL_BEZ_ZIEL.ID_ARTIKEL=ARTIKEL_ZIEL.ID_ARTIKEL", "ARTIKEL_ZIEL", 
//			null);
		
//		this.add_SQLField(new SQLField("NVL(ARTIKEL_AUSGANG.ANR1,'')||'-'||NVL(ARTIKEL_BEZ_AUSGANG.ANR2,'')||' '||NVL(ARTIKEL_BEZ_AUSGANG.ARTBEZ1,'')", "ARTIKEL_AUSGANG", new MyE2_String("Ausgangssorte"),  bibE2.get_CurrSession()),false);
//		this.add_SQLField(new SQLField("NVL(ARTIKEL_ZIEL.ANR1,'')||'-'||NVL(ARTIKEL_BEZ_ZIEL.ANR2,'')||' '||NVL(ARTIKEL_BEZ_ZIEL.ARTBEZ1,'')", "ARTIKEL_ZIEL", new MyE2_String("Zielsorte"),  bibE2.get_CurrSession()),false);
		this.add_SQLField(new SQLField("NVL(JD_USER.VORNAME,'')||' '||NVL(JD_USER.NAME1,'')", "BETREUER", new MyE2_String("Betreuer"),  bibE2.get_CurrSession()),false);
		
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_UMA_KONTRAKT_WICHTIGKEIT","BESCHREIBUNG","ID_UMA_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_UMA_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_UMA_KONTRAKT.ID_USER="+cID_USER+" OR JT_UMA_KONTRAKT.ID_UMA IN (SELECT ID_UMA FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
