package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import java.util.HashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN.TP_KST__CONST.SQLFIELDMAP_FIELDS;

public class TP_KST_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public TP_KST_LIST_SqlFieldMAP() throws myException 
	{
		//super("V1_TPA_KOSTEN", "", false);
		super("JT_VPOS_TPA","",false);

		// TPA-KOPF
		this.add_JOIN_Table("JT_VKOPF_TPA", 
				"JT_VKOPF_TPA", 
				SQLFieldMAP.INNER, 
				"",
				true,
				" JT_VPOS_TPA.ID_VKOPF_TPA = JT_VKOPF_TPA.ID_VKOPF_TPA ", "", null);

		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_SPED", 
				SQLFieldMAP.INNER, 
				":NAME1:NAME2:ORT:"
				, true, "JT_VKOPF_TPA.ID_ADRESSE = ADR_SPED.ID_ADRESSE", "ADR_SPED_", null);

		
		// FUHRE
		this.add_JOIN_Table("JT_VPOS_TPA_FUHRE", 
				"JT_VPOS_TPA_FUHRE", 
				SQLFieldMAP.INNER, 
				":DATUM_ABLADEN:DATUM_AUFLADEN:",
				true,
				" JT_VPOS_TPA.ID_VPOS_TPA = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA ", "", null);
		
		
		HashMap<String, String> hmZusatz = new HashMap<String, String>();
		hmZusatz.put(TP_KST__CONST.SQLFIELDMAP_FIELDS.FU_SORTE._hash(), TP_KST__CONST.SQLFIELDMAP_FIELDS.FU_SORTE._query());
		// gefahrene Sorte
		this.add_JOIN_Table("JT_ARTIKEL", 
				"JT_ARTIKEL", 
				SQLFieldMAP.INNER, 
				":"+ARTIKEL.artbez1.fn()+":"+ARTIKEL.anr1.fn()+":",
				true,
				" JT_VPOS_TPA_FUHRE.ID_ARTIKEL = JT_ARTIKEL.ID_ARTIKEL ", "ART_", hmZusatz);
		
		
		
		// START-ADRESSE
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_START", 
				SQLFieldMAP.INNER, 
				":ID_LAND:PLZ:ORT:"
				, true, "JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_START = ADR_START.ID_ADRESSE", "ADR_START_", null);
		// ZIEL-ADRESSE
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_ZIEL", 
				SQLFieldMAP.INNER, 
				":ID_LAND:PLZ:ORT:"
				, true, "JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_ZIEL = ADR_ZIEL.ID_ADRESSE", "ADR_ZIEL_", null);

		
		
		
		this.add_JOIN_Table("JD_LAND", 
				"LAND_START", 
				SQLFieldMAP.INNER, 
				":LAENDERNAME:"
				, true, "LAND_START.ID_LAND = ADR_START.ID_LAND", "LAND_START_", null);

		this.add_JOIN_Table("JD_LAND", 
				"LAND_ZIEL", 
				SQLFieldMAP.INNER, 
				":LAENDERNAME:"
				, true, "LAND_ZIEL.ID_LAND = ADR_ZIEL.ID_LAND", "LAND_ZIEL_", null);

		
		//2015-05-12: ersetzung durch enum-aufzaehlung
		for (SQLFIELDMAP_FIELDS field: SQLFIELDMAP_FIELDS.values()) {
			this.add_SQLField(new SQLField(field._query(), field._hash(),field._descript(),	bibE2.get_CurrSession()), true);
		}
		
		
//		this.add_SQLField(new SQLField("ADR_START.ID_LAND" ,
//				"ADR_START_ID_LAND",
//				new MyString("Land Start"),
//				bibE2.get_CurrSession()), true);
//
//		this.add_SQLField(new SQLField("ADR_START.PLZ" ,
//				"ADR_START_PLZ",
//				new MyString("PLZ Start"),
//				bibE2.get_CurrSession()), true);
//
//		this.add_SQLField(new SQLField("ADR_START.ORT" ,
//				"ADR_START_ORT",
//				new MyString("Ort Start"),
//				bibE2.get_CurrSession()), true);
//
//		this.add_SQLField(new SQLField("ADR_ZIEL.ID_LAND" ,
//				"ADR_ZIEL_ID_LAND",
//				new MyString("Land Ziel"),
//				bibE2.get_CurrSession()), true);
//
//		this.add_SQLField(new SQLField("ADR_ZIEL.PLZ" ,
//				"ADR_ZIEL_PLZ",
//				new MyString("PLZ Ziel"),
//				bibE2.get_CurrSession()), true);
//
//		this.add_SQLField(new SQLField("ADR_ZIEL.ORT" ,
//				"ADR_ZIEL_ORT",
//				new MyString("Ort Ziel"),
//				bibE2.get_CurrSession()), true);
//		
//		this.add_SQLField(new SQLField("LAND_ZIEL.LAENDERNAME" ,
//				"LAND_ZIEL_LAENDERNAME",
//				new MyString("Land Ziel"),
//				bibE2.get_CurrSession()), true);
//		
//		this.add_SQLField(new SQLField("LAND_START.LAENDERNAME" ,
//				"LAND_START_LAENDERNAME",
//				new MyString("Land Start"),
//				bibE2.get_CurrSession()), true);
//
//		
//		this.add_SQLField(new SQLField("JT_VKOPF_TPA.NAME1 || ' ' || JT_VKOPF_TPA.NAME2 || ', ' || JT_VKOPF_TPA.ORT" ,
//				"SPEDITION_NAME",
//				new MyString("Spedition"),
//				bibE2.get_CurrSession()), true);
//
		
		
		
		
//		String sSqlStatic = " (JT_VPOS_TPA.ID_VPOS_TPA = (" +
//				"SELECT MAX(id_vpos_tpa) from "+bibE2.cTO()+".JT_VPOS_TPA T" +
//				" WHERE t.id_mandant = JT_VPOS_TPA.ID_MANDANT" +
//				" AND T.ID_VKOPF_TPA = JT_VPOS_TPA.ID_VKOPF_TPA" +
//				" AND T.ID_ARTIKEL = JT_VPOS_TPA.ID_ARTIKEL" +
//				" AND T.ANZAHL = JT_VPOS_TPA.ANZAHL" +
//				" AND T.EINZELPREIS = JT_VPOS_TPA.EINZELPREIS" +
//				" AND T.GESAMTPREIS = JT_VPOS_TPA.GESAMTPREIS" +
//				" AND T.EINHEIT_PREIS_KURZ = JT_VPOS_TPA.EINHEIT_PREIS_KURZ ) )" ;
		
//		this.add_BEDINGUNG_STATIC(sSqlStatic);
		
		String sSqlStatic = "( JT_VPOS_TPA.DELETED is null OR JT_VPOS_TPA.DELETED = 'N')";
		this.add_BEDINGUNG_STATIC(sSqlStatic);
		
		this.add_ORDER_SEGMENT("NVL(JT_VPOS_TPA_FUHRE.DATUM_ABLADEN,'31.12.1000')");
		
		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_VPOS_TPA_WICHTIGKEIT","BESCHREIBUNG","ID_TP_KST_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_TP_KST_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/

		/*
		 * Beispiel fuer sonderabfragen, die immer zugrunde liegen
 		 *
		cSonderQuery =  "(JT_VPOS_TPA.ID_USER="+cID_USER+" OR JT_VPOS_TPA.ID_TP_KST IN (SELECT ID_TP_KST FROM "+bibE2.cTO()+".JT_VPOS_TPA_TEILNEHMER WHERE ID_USER="+cID_USER+"))";
		
		this.add_BEDINGUNG_STATIC(cSonderQuery);
		*/
		this.initFields();
	}
	
}
