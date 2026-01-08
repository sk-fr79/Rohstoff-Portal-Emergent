package rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LAG_BEW_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 333486641275451569L;

	public LAG_BEW_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_LAGER_KONTO", "", false);
		
		this.add_JOIN_Table("JT_ARTIKEL", 
				"JT_ARTIKEL", 
				SQLFieldMAP.LEFT_OUTER, 
				":ANR1:ARTBEZ:ID_ARTIKEL:",
				true, 
				"JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL ", "", 
				bibALL.get_HashMap("ART_INFO", " NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(JT_ARTIKEL.ARTBEZ1,'')"));
		
		
		this.add_JOIN_Table("JT_ADRESSE", 
				"JT_ADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:NAME2:PLZ:ORT:ID_ADRESSE:",
				true,
				"JT_LAGER_KONTO.ID_ADRESSE_LAGER=JT_ADRESSE.ID_ADRESSE ", "", 
//			bibALL.get_HashMap("ADDRESS_INFO", " NVL(JT_ADRESSE.NAME1,'') || ' '|| NVL(JT_ADRESSE.NAME2,'')|| ', '|| NVL(JT_ADRESSE.PLZ,'') || ' ' || NVL(JT_ADRESSE.ORT,'')"));
     		bibALL.get_HashMap("ADDRESS_INFO", " NVL(JT_ADRESSE.ORT,'') || ' (' || NVL(JT_ADRESSE.PLZ,'') || '), ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') || NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'') "));

		this.add_JOIN_Table("JT_VPOS_TPA_FUHRE", 
				"JT_VPOS_TPA_FUHRE", 
				SQLFieldMAP.LEFT_OUTER, 
				"",
				true,
				" JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE ", "",null); 
		
		this.add_JOIN_Table("JT_LIEFERADRESSE", 
				"JT_LIEFERADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":BEZEICHNUNG:", 
				true, 
				" JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER ",
				"", null
				);

		
		
		//	bibALL.get_HashMap("FUHRE_INFO", " NVL(JT_VPOS_TPA_FUHRE.L_NAME1,'') || ' '|| NVL(JT_VPOS_TPA_FUHRE.L_NAME2,'')|| ', '|| NVL(JT_VPOS_TPA_FUHRE.L_PLZ,'') || ' ' || NVL(JT_VPOS_TPA_FUHRE.L_ORT,'')"));
		this.add_SQLField(new SQLField("NVL(JT_VPOS_TPA_FUHRE.L_NAME1,'') || ' '|| NVL(JT_VPOS_TPA_FUHRE.L_NAME2,'')|| ', '|| NVL(JT_VPOS_TPA_FUHRE.L_PLZ,'') || ' ' || NVL(JT_VPOS_TPA_FUHRE.L_ORT,'')"  ,
				"LIEFERANT_INFO",
				new MyString("Lieferant"),
				bibE2.get_CurrSession()), true);

		
		
		this.add_JOIN_Table("JT_VPOS_TPA_FUHRE_ORT", 
				"JT_VPOS_TPA_FUHRE_ORT", 
				SQLFieldMAP.LEFT_OUTER, 
				"",
				true,
				" JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE_ORT = JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE_ORT ", "", 
			bibALL.get_HashMap("FUHRE_ORT_INFO", " NVL(JT_VPOS_TPA_FUHRE_ORT.NAME1,'') || ' '|| NVL(JT_VPOS_TPA_FUHRE_ORT.NAME2,'')|| ', '|| NVL(JT_VPOS_TPA_FUHRE_ORT.PLZ,'') || ' ' || NVL(JT_VPOS_TPA_FUHRE_ORT.ORT,'')"));
		
		
		
		
		
		this.add_SQLField(new SQLField("(Select sum(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " + 
										" FROM JT_LAGER_BEWEGUNG WHERE JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO )",
										"VERBUCHT",
										new MyString("Verbucht"),
										bibE2.get_CurrSession()), true);
		
		
		
		this.add_SQLField(new SQLField("( nvl(JT_LAGER_KONTO.MENGE,0.0) - " +
										"(Select sum(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " + 
										" FROM JT_LAGER_BEWEGUNG WHERE JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO ) " +
										") ",
										"OFFEN",
										new MyString("OFFEN"),
										bibE2.get_CurrSession()), true);
		
		// Wherebedingung um die Werte vor der Inventur nicht zu berücksichtigen
		String sWhereInventurdatum = 
			" AND NVL( (SELECT NVL(max(I.BUCHUNGSDATUM), to_date('2000-01-01','yyyy-MM-dd') ) AS INVENTURDATUM " +
			" FROM JT_LAGER_INVENTUR I " +
			" WHERE I.ID_ADRESSE_LAGER = JT_LAGER_KONTO.ID_ADRESSE_LAGER AND I.ID_ARTIKEL_SORTE = JT_LAGER_KONTO.ID_ARTIKEL_SORTE " +
			" GROUP BY I.ID_ADRESSE_LAGER, I.ID_ARTIKEL_SORTE " +
			") , to_date('2000-01-01','yyyy-MM-dd') )  < JT_LAGER_KONTO.BUCHUNGSDATUM ";

		String sWhere =    " JT_LAGER_KONTO.BUCHUNGSTYP      = 'WE' " +
						   " AND ( " +
						   "     JT_LAGER_KONTO.STORNO is null"+ 
						   "     or JT_LAGER_KONTO.STORNO = 'N'"+
						   " ) " +
						   sWhereInventurdatum;
					
	
		this.add_BEDINGUNG_STATIC(sWhere);

		//		this.add_ORDER_SEGMENT("NVL(JT_ADRESSE.NAME1,'') || ' '|| NVL(JT_ADRESSE.NAME2,'')|| ', '|| NVL(JT_ADRESSE.PLZ,'') || ' ' || NVL(JT_ADRESSE.ORT,''),  NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(JT_ARTIKEL.ARTBEZ1,''),PREIS");
		this.add_ORDER_SEGMENT("NVL(JT_ADRESSE.ORT,'') || ' (' || NVL(JT_ADRESSE.PLZ,'') || '), ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') || NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,''),PREIS");


		/*
		 * beispiel fuer die definition von standard-werten
		
		this.get_("BUCHUNGSDATUM").set_cDefaultValueFormated("");
		this.get_("BUCHUNGSTYP").set_cDefaultValueFormated("");
		this.get_("ID_ADRESSE_LAGER").set_cDefaultValueFormated("");
		this.get_("ID_ARTIKEL_SORTE").set_cDefaultValueFormated("");
		this.get_("ID_LAGER_KONTO").set_cDefaultValueFormated("");
		this.get_("ID_LAGER_KONTO_PARENT").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_RG").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_TPA_FUHRE").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_TPA_FUHRE_ORT").set_cDefaultValueFormated("");
		this.get_("IST_KOMPLETT").set_cDefaultValueFormated("");
		this.get_("MENGE").set_cDefaultValueFormated("");
		this.get_("PREIS").set_cDefaultValueFormated("");
		this.get_("SALDO").set_cDefaultValueFormated("");
		this.get_("STORNO").set_cDefaultValueFormated("");
		 */
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_LAGER_KONTO_WICHTIGKEIT","BESCHREIBUNG","ID_LAG_BEW_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_LAG_BEW_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_LAGER_KONTO.ID_USER="+cID_USER+" OR JT_LAGER_KONTO.ID_LAG_BEW IN (SELECT ID_LAG_BEW FROM "+bibE2.cTO()+".JT_LAGER_KONTO_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
