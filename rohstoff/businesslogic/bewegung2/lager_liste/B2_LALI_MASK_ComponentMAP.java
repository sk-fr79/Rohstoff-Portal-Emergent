package rohstoff.businesslogic.bewegung2.lager_liste;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class B2_LALI_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public B2_LALI_MASK_ComponentMAP() throws myException
	{
		super(new B2_LALI_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		/*
		 * beispiele fuer felder in der map
		 *
		*/
//		String[] cFieldsStandard = {"ABGERECHNET","ABRECHENBAR","ABZUG_MENGE","ARTBEZ1","ARTBEZ2","BEMERKUNG","BEMERKUNG_SACHBEARBEITER","BESTELLNUMMER","BUCHUNGSNUMMER","DEL_DATE","DELETED","DEL_GRUND","DEL_KUERZEL","E_PREIS","E_PREIS_RESULT_NETTO_MGE","ERZEUGT_AM","ERZEUGT_VON","EU_STEUER_VERMERK","GEAENDERT_VON","ID_ARTIKEL","ID_ARTIKEL_BEZ","ID_BEWEGUNG","ID_BEWEGUNG_ATOM","ID_BEWEGUNG_VEKTOR","ID_MANDANT","ID_VPOS_KON","ID_VPOS_STD","KONTRAKTZWANG","KONTRAKTZWANG_AUS_AM","KONTRAKTZWANG_AUS_GRUND","KONTRAKTZWANG_AUS_VON","LEISTUNGSDATUM","LETZTE_AENDERUNG","LIEFERINFO_TPA","MENGE","NATIONALER_ABFALL_CODE","NOTIFIKATION_NR","ORDNUNGSNUMMER_CMR","PLANMENGE","POSNR","POSTENNUMMER","PREISERMITTLUNG","PRUEFUNG_MENGE","PRUEFUNG_MENGE_AM","PRUEFUNG_MENGE_VON","SETZKASTEN_KOMPLETT","STEUERSATZ","STORNIERT","STORNIERT_AM","STORNIERT_GRUND","STORNIERT_VON","ZEITSTEMPEL"}; 
//		String[] cFieldsInfolabs = {"ABGERECHNET","ABRECHENBAR","ABZUG_MENGE","ARTBEZ1","ARTBEZ2","BEMERKUNG","BEMERKUNG_SACHBEARBEITER","BESTELLNUMMER","BUCHUNGSNUMMER","DEL_DATE","DELETED","DEL_GRUND","DEL_KUERZEL","E_PREIS","E_PREIS_RESULT_NETTO_MGE","ERZEUGT_AM","ERZEUGT_VON","EU_STEUER_VERMERK","GEAENDERT_VON","ID_ARTIKEL","ID_ARTIKEL_BEZ","ID_BEWEGUNG","ID_BEWEGUNG_ATOM","ID_BEWEGUNG_VEKTOR","ID_MANDANT","ID_VPOS_KON","ID_VPOS_STD","KONTRAKTZWANG","KONTRAKTZWANG_AUS_AM","KONTRAKTZWANG_AUS_GRUND","KONTRAKTZWANG_AUS_VON","LEISTUNGSDATUM","LETZTE_AENDERUNG","LIEFERINFO_TPA","MENGE","NATIONALER_ABFALL_CODE","NOTIFIKATION_NR","ORDNUNGSNUMMER_CMR","PLANMENGE","POSNR","POSTENNUMMER","PREISERMITTLUNG","PRUEFUNG_MENGE","PRUEFUNG_MENGE_AM","PRUEFUNG_MENGE_VON","SETZKASTEN_KOMPLETT","STEUERSATZ","STORNIERT","STORNIERT_AM","STORNIERT_GRUND","STORNIERT_VON","ZEITSTEMPEL"}; 
//
//		//E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_BEWEGUNG_ATOM_WICHTIGKEIT","BESCHREIBUNG","ID_ATOM_LAG_WICHTIGKEIT","ISTSTANDARD",true);
//		
//		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, cFieldsInfolabs, oFM, false, false, this, 400);
//
//		//hier kommen die Felder	
//		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ABGERECHNET")), new MyE2_String("ABGERECHNET"));
//		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ABRECHENBAR")), new MyE2_String("ABRECHENBAR"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ABZUG_MENGE"),true,240,12,false), new MyE2_String("ABZUG_MENGE"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ARTBEZ1"),true,500,80,false), new MyE2_String("ARTBEZ1"));
//		this.add_Component(new MyE2_DB_TextArea(oFM.get_("ARTBEZ2"),500,8), new MyE2_String("ARTBEZ2"));
//		this.add_Component(new MyE2_DB_TextArea(oFM.get_("BEMERKUNG"),500,8), new MyE2_String("BEMERKUNG"));
//		this.add_Component(new MyE2_DB_TextArea(oFM.get_("BEMERKUNG_SACHBEARBEITER"),500,8), new MyE2_String("BEMERKUNG_SACHBEARBEITER"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("BESTELLNUMMER"),true,350,30,false), new MyE2_String("BESTELLNUMMER"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("BUCHUNGSNUMMER"),true,200,20,false), new MyE2_String("BUCHUNGSNUMMER"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("DEL_DATE"),true,120,10,false), new MyE2_String("DEL_DATE"));
//		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("DELETED")), new MyE2_String("DELETED"));
//		this.add_Component(new MyE2_DB_TextArea(oFM.get_("DEL_GRUND"),500,5), new MyE2_String("DEL_GRUND"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("DEL_KUERZEL"),true,100,10,false), new MyE2_String("DEL_KUERZEL"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("E_PREIS"),true,200,10,false), new MyE2_String("E_PREIS"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("E_PREIS_RESULT_NETTO_MGE"),true,200,10,false), new MyE2_String("E_PREIS_RESULT_NETTO_MGE"));
//		this.add_Component(new MyE2_DB_TextArea(oFM.get_("EU_STEUER_VERMERK"),500,5), new MyE2_String("EU_STEUER_VERMERK"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_ARTIKEL"),true,200,10,false), new MyE2_String("ID_ARTIKEL"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_ARTIKEL_BEZ"),true,200,10,false), new MyE2_String("ID_ARTIKEL_BEZ"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_BEWEGUNG"),true,200,10,false), new MyE2_String("ID_BEWEGUNG"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_BEWEGUNG_ATOM"),true,200,10,false), new MyE2_String("ID_BEWEGUNG_ATOM"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_BEWEGUNG_VEKTOR"),true,200,10,false), new MyE2_String("ID_BEWEGUNG_VEKTOR"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_KON"),true,200,10,false), new MyE2_String("ID_VPOS_KON"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_STD"),true,200,10,false), new MyE2_String("ID_VPOS_STD"));
//		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("KONTRAKTZWANG")), new MyE2_String("KONTRAKTZWANG"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("KONTRAKTZWANG_AUS_AM"),true,120,10,false), new MyE2_String("KONTRAKTZWANG_AUS_AM"));
//		this.add_Component(new MyE2_DB_TextArea(oFM.get_("KONTRAKTZWANG_AUS_GRUND"),500,5), new MyE2_String("KONTRAKTZWANG_AUS_GRUND"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("KONTRAKTZWANG_AUS_VON"),true,100,10,false), new MyE2_String("KONTRAKTZWANG_AUS_VON"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("LEISTUNGSDATUM"),true,120,10,false), new MyE2_String("LEISTUNGSDATUM"));
//		this.add_Component(new MyE2_DB_TextArea(oFM.get_("LIEFERINFO_TPA"),500,8), new MyE2_String("LIEFERINFO_TPA"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("MENGE"),true,240,12,false), new MyE2_String("MENGE"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("NATIONALER_ABFALL_CODE"),true,200,20,false), new MyE2_String("NATIONALER_ABFALL_CODE"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("NOTIFIKATION_NR"),true,350,30,false), new MyE2_String("NOTIFIKATION_NR"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ORDNUNGSNUMMER_CMR"),true,350,30,false), new MyE2_String("ORDNUNGSNUMMER_CMR"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("PLANMENGE"),true,240,12,false), new MyE2_String("PLANMENGE"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("POSNR"),true,200,10,false), new MyE2_String("POSNR"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("POSTENNUMMER"),true,500,100,false), new MyE2_String("POSTENNUMMER"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("PREISERMITTLUNG"),true,200,20,false), new MyE2_String("PREISERMITTLUNG"));
//		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("PRUEFUNG_MENGE")), new MyE2_String("PRUEFUNG_MENGE"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("PRUEFUNG_MENGE_AM"),true,120,10,false), new MyE2_String("PRUEFUNG_MENGE_AM"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("PRUEFUNG_MENGE_VON"),true,100,10,false), new MyE2_String("PRUEFUNG_MENGE_VON"));
//		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("SETZKASTEN_KOMPLETT")), new MyE2_String("SETZKASTEN_KOMPLETT"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("STEUERSATZ"),true,200,10,false), new MyE2_String("STEUERSATZ"));
//		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("STORNIERT")), new MyE2_String("STORNIERT"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("STORNIERT_AM"),true,120,10,false), new MyE2_String("STORNIERT_AM"));
//		this.add_Component(new MyE2_DB_TextArea(oFM.get_("STORNIERT_GRUND"),500,5), new MyE2_String("STORNIERT_GRUND"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("STORNIERT_VON"),true,100,10,false), new MyE2_String("STORNIERT_VON"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ZEITSTEMPEL"),true,200,16,false), new MyE2_String("ZEITSTEMPEL"));
//		

		/*
		 * beispiele fuer beliebige felder
		this.add_Component(new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER")), new MyE2_String("Besitzer"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_ATOM_LAG_WICHTIGKEIT"),oDDWichtigkeit.getDD(),false), new MyE2_String("Wichtig ?"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_ATOM_LAG")), new MyE2_String("ID"));

		((MyE2_DB_TextArea)this.get__Comp("AUFGABENTEXT")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("ANTWORTTEXT")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("AUFGABENTEXT")).set_iRows(4);
		((MyE2_DB_TextArea)this.get__Comp("ANTWORTTEXT")).set_iRows(4);
		
		((MyE2_DB_TextField)this.get__Comp("AUFGABEKURZ")).set_iWidthPixel(600);
		((MyE2_DB_TextField)this.get__Comp("ANTWORTKURZ")).set_iWidthPixel(600);

		((MyE2_DB_CheckBox)this.get__Comp("ERLEDIGT")).add_oActionAgent(new cbActionAgent());
		*/	

		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new B2_LALI_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new B2_LALI_MASK_FORMATING_Agent());
	}
	
}
