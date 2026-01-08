package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BAM_IMPORT_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public BAM_IMPORT_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_BAM_IMPORT", "", false);
		

		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("BELEGNUMMER").set_cDefaultValueFormated("");
		this.get_("ID_BAM_IMPORT").set_cDefaultValueFormated("");
		
		this.add_JOIN_Table(
				"JT_WIEGEKARTE", 
				"JT_WIEGEKARTE", 
				SQLFieldMAP.LEFT_OUTER, 
				":ID_ADRESSE_LIEFERANT:ID_ARTIKEL_SORTE:ID_ARTIKEL_BEZ:GEWICHT_NACH_ABZUG_FUHRE:LFD_NR:", 
				true, 
				"JT_BAM_IMPORT.ID_WIEGEKARTE = JT_WIEGEKARTE.ID_WIEGEKARTE", 
				"",
				null);

		this.add_JOIN_Table("JT_ADRESSE", 
				"JT_ADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:NAME2:PLZ:ORT:ID_ADRESSE:"
				, true, 
				"JT_ADRESSE.ID_ADRESSE = JT_WIEGEKARTE.ID_ADRESSE_LIEFERANT", 
				"",
				null);
		
		this.add_JOIN_Table(
				"JT_ARTIKEL", 
				"JT_ARTIKEL", 
				SQLFieldMAP.LEFT_OUTER, 
				":ANR1:ARTBEZ:ID_ARTIKEL:", 
				true, 
				"JT_ARTIKEL.ID_ARTIKEL = JT_WIEGEKARTE.ID_ARTIKEL_SORTE", 
				"",
				null);
//			bibALL.get_HashMap("ART_INFO", "NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(JT_ARTIKEL.ARTBEZ1,'')"));

		this.add_JOIN_Table(
				"JT_ARTIKEL_BEZ", 
				"JT_ARTIKEL_BEZ", 
				SQLFieldMAP.LEFT_OUTER, 
				":ANR2:ARTBEZ1:", 
				true, 
				"JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ = JT_WIEGEKARTE.ID_ARTIKEL_BEZ", 
				"",
				null);
		
		this.add_SQLField(new SQLField(" CASE WHEN JT_WIEGEKARTE.IST_LIEFERANT = 'Y' then 'WE' else 'WA' end", "WE_WA", 
										new MyE2_String("WE/WA"), 
										bibE2.get_CurrSession()), 
							true);
		
		this.add_SQLField(new SQLField(	" JT_ARTIKEL.ANR1 || '-' || JT_ARTIKEL_BEZ.ANR2 || ' ' || JT_ARTIKEL_BEZ.ARTBEZ1" , 
        								"ARTIKEL_INFO", 
        								new MyE2_String("ANR"), 
        								bibE2.get_CurrSession()), 
        				    true);
		
		this.add_SQLField(new SQLField(	  " CASE WHEN JT_WIEGEKARTE.ID_ADRESSE_LIEFERANT is null then JT_WIEGEKARTE.ADRESSE_LIEFERANT "
											+ " ELSE NVL(JT_ADRESSE.NAME1,'') ||  NVL2(JT_ADRESSE.NAME2,' ' || JT_ADRESSE.NAME2,'') || NVL2(JT_ADRESSE.ORT,', ' || JT_ADRESSE.ORT,'')  end" , 
											"KUNDEN_INFO", 
											new MyE2_String("Kunde"), 
											bibE2.get_CurrSession()), 
							true);

		
//			bibALL.get_HashMap("ADDRESS_INFO", " NVL(JT_ADRESSE.ORT,'') || ' (' || NVL(JT_ADRESSE.PLZ,'') || '), ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') || NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'') "));
		
		
		
		
     //		this.addField("(CASE WHEN ID_WIEGEKARTE IS NOT NULL THEN 'JA - Wiegekarte' WHEN ID_VPOS_TPA_FUHRE IS NOT NULL THEN 'JA - Fuhre' ELSE '-' END)", "ID_ASSIGNED");
		
		/*
				SQLField oField = new SQLField(	"(CASE WHEN ID_WIEGEKARTE IS NOT NULL THEN 1 WHEN ID_VPOS_TPA_FUHRE IS NOT NULL THEN 1 ELSE 0 END)", "ID_ASSIGNED", new MyString(), bibE2.get_CurrSession());
		oField.set_bWriteable(false);
		oField.set_bFieldCanBeWrittenInMask(false);
		this.add_SQLField(oField, false);

		 */

		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_BAM_IMPORT_WICHTIGKEIT","BESCHREIBUNG","ID_BAM_IMPORT_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_BAM_IMPORT_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_BAM_IMPORT.ID_USER="+cID_USER+" OR JT_BAM_IMPORT.ID_BAM_IMPORT IN (SELECT ID_BAM_IMPORT FROM "+bibE2.cTO()+".JT_BAM_IMPORT_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
