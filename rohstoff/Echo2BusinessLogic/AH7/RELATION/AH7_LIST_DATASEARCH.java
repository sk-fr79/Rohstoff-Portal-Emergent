package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;


public class AH7_LIST_DATASEARCH extends E2_DataSearch
{

	private VEK<IF_Field> vIdFields = new VEK<IF_Field>()
										._a(AH7_STEUERDATEI.id_adresse_geo_start)
										._a(AH7_STEUERDATEI.id_adresse_geo_ziel)
										._a(AH7_STEUERDATEI.id_adresse_jur_start)
										._a(AH7_STEUERDATEI.id_adresse_jur_ziel)
										._a(AH7_STEUERDATEI.id_1_abfallerzeuger)
										._a(AH7_STEUERDATEI.id_1_import_empfaenger)
										._a(AH7_STEUERDATEI.id_1_verbr_veranlasser)
										._a(AH7_STEUERDATEI.id_1_verwertungsanlage)
										._a(AH7_STEUERDATEI.id_2_abfallerzeuger)
										._a(AH7_STEUERDATEI.id_2_import_empfaenger)
										._a(AH7_STEUERDATEI.id_2_verbr_veranlasser)
										._a(AH7_STEUERDATEI.id_2_verwertungsanlage)
										._a(AH7_STEUERDATEI.id_3_abfallerzeuger)
										._a(AH7_STEUERDATEI.id_3_import_empfaenger)
										._a(AH7_STEUERDATEI.id_3_verbr_veranlasser)
										._a(AH7_STEUERDATEI.id_3_verwertungsanlage)
										;

	public AH7_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException 	{
		super("JT_AH7_STEUERDATEI","ID_AH7_STEUERDATEI",E2_MODULNAME_ENUM.MODUL.AH7_STEUERDATEI_LISTE.get_callKey());
		
			
		
		
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_AH7_STEUERDATEI","ID",true);

//		//hier kommen die Felder	
//		this.addSearchDef("DRUCKE_BLATT2","DRUCKE_BLATT2",false);
//		this.addSearchDef("DRUCKE_BLATT3","DRUCKE_BLATT3",false);
//		this.addSearchDef("ID_ADRESSE_GEO_START","ID_ADRESSE_GEO_START",false);
//		this.addSearchDef("ID_ADRESSE_GEO_ZIEL","ID_ADRESSE_GEO_ZIEL",false);
//		this.addSearchDef("ID_AH7_STEUERDATEI","ID_AH7_STEUERDATEI",false);
//		this.addSearchDef("ID_1_ABFALLERZEUGER","ID_1_ABFALLERZEUGER",false);
//		this.addSearchDef("ID_1_IMPORTEUR_EMPFAENGER","ID_1_IMPORTEUR_EMPFAENGER",false);
//		this.addSearchDef("ID_1_VERBRINGUNGVERANLASSER","ID_1_VERBRINGUNGVERANLASSER",false);
//		this.addSearchDef("ID_1_VERWERTUNGSANLAGE","ID_1_VERWERTUNGSANLAGE",false);
//		this.addSearchDef("ID_2_ABFALLERZEUGER","ID_2_ABFALLERZEUGER",false);
//		this.addSearchDef("ID_2_IMPORT_EMPFAENGER","ID_2_IMPORT_EMPFAENGER",false);
//		this.addSearchDef("ID_2_VERBR_VERANLASSER","ID_2_VERBR_VERANLASSER",false);
//		this.addSearchDef("ID_2_VERWERTUNGSANLAGE","ID_2_VERWERTUNGSANLAGE",false);
//		this.addSearchDef("ID_3_ABFALLERZEUGER","ID_3_ABFALLERZEUGER",false);
//		this.addSearchDef("ID_3_IMPORT_EMPFAENGER","ID_3_IMPORT_EMPFAENGER",false);
//		this.addSearchDef("ID_3_VERBR_VERANLASSER","ID_3_VERBR_VERANLASSER",false);
//		this.addSearchDef("ID_3_VERWERTUNGSANLAGE","ID_3_VERWERTUNGSANLAGE",false);

		/*
		this.addSearchDef("AUFGABEKURZ","Aufgabe (kurz)",false);
		this.addSearchDef("AUFGABENTEXT","Aufgabe (Details)",false);
		this.addSearchDef("ANTWORTKURZ","Ergebnis (kurz)",false);
		this.addSearchDef("ANTWORTTEXT","Ergebnis (Details)",false);
		*/		

		SEL selID = new SEL(AH7_STEUERDATEI.id_ah7_steuerdatei).FROM(_TAB.ah7_steuerdatei)
						.WHERE(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_adresse_geo_start.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_adresse_geo_ziel.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_adresse_jur_start.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_adresse_jur_ziel.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_1_abfallerzeuger.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_1_import_empfaenger.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_1_verbr_veranlasser.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_1_verwertungsanlage.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_2_abfallerzeuger.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_2_import_empfaenger.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_2_verbr_veranlasser.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_2_verwertungsanlage.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_3_abfallerzeuger.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_3_import_empfaenger.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_3_verbr_veranlasser.tnfn()+")='#WERT#'"))
						.OR(new TermSimple("TO_CHAR("+AH7_STEUERDATEI.id_3_verwertungsanlage.tnfn()+")='#WERT#'"))
						;
		
		this.add_SearchElement(selID.s(),S.ms("Suche einer AdressID"));
		this.add_SearchElement(this.genSuchQuery(ADRESSE.name1),S.ms("Suche Adress-Namen"));
		this.add_SearchElement(this.genSuchQuery(ADRESSE.plz),S.ms("Suche Adress-PLZ"));
		this.add_SearchElement(this.genSuchQuery(ADRESSE.ort),S.ms("Suche Adress-Ort"));

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
		
	}

	
	private String genSuchQuery(IF_Field adressTextField) throws myException {

		String sql = new SEL(AH7_STEUERDATEI.id_ah7_steuerdatei).FROM(_TAB.ah7_steuerdatei).s()+" WHERE ";
		
		for (IF_Field idField: this.vIdFields) {
			TermSimple t = new TermSimple("UPPER("+adressTextField.tnfn()+") LIKE UPPER('%#WERT#%')");
			sql=sql + idField.tnfn()+" IN ("+new SEL(ADRESSE.id_adresse).FROM(_TAB.adresse).WHERE(t).s()+" ) OR ";
		}

		sql = sql.substring(0, sql.length()-3);   //das letzte or wegschneiden    
		
		return sql;
	}
	
	
	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_AH7_STEUERDATEI.ID_AH7_STEUERDATEI FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE TO_CHAR(JT_AH7_STEUERDATEI."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_AH7_STEUERDATEI.ID_AH7_STEUERDATEI FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI WHERE UPPER(JT_AH7_STEUERDATEI."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_AH7_STEUERDATEI.ID_AH7_STEUERDATEI FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_AH7_STEUERDATEI.ID_AH7_STEUERDATEI="+cRefTableName+".ID_AH7_STEUERDATEI AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_AH7_STEUERDATEI.ID_AH7_STEUERDATEI FROM "+bibE2.cTO()+".JT_AH7_STEUERDATEI,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_AH7_STEUERDATEI.ID_AH7_STEUERDATEI="+cRefTableName+".ID_AH7_STEUERDATEI AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
