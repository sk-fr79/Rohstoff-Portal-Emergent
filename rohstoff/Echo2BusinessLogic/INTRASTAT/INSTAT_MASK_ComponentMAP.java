package rohstoff.Echo2BusinessLogic.INTRASTAT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class INSTAT_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public INSTAT_MASK_ComponentMAP() throws myException
	{
		super(new INSTAT_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		/*
		 * beispiele fuer felder in der map
		 *
		*/
//		String[] cFieldsStandard = {"ANMELDEFORM","ANMELDEMONAT","BELEGNR","BESTIMM_LAND","BESTIMM_REGION","BEZUGSJAHR","BEZUGSMONAT","BUNDESLAND_FA","EIGENMASSE","ERZEUGT_AM","ERZEUGT_VON","EXPORTFREIGABE","EXPORTIERT_AM","GEAENDERT_VON","GESCHAEFTSART","ID_INTRASTAT_MELDUNG","ID_MANDANT","LAND_URSPRUNG","LETZTE_AENDERUNG","MASSEINHEIT","MELDEART","PAGINIERNUMMER","RECHBETRAG","STATISTISCHER_WERT","STEUERNR","UNTERSCHEIDUNGSNR","VERKEHRSZWEIG","WAEHRUNGSKENNZIFFER","WARENNR"}; 
//		String[] cFieldsInfolabs = {"ANMELDEFORM","ANMELDEMONAT","BELEGNR","BESTIMM_LAND","BESTIMM_REGION","BEZUGSJAHR","BEZUGSMONAT","BUNDESLAND_FA","EIGENMASSE","ERZEUGT_AM","ERZEUGT_VON","EXPORTFREIGABE","EXPORTIERT_AM","GEAENDERT_VON","GESCHAEFTSART","ID_INTRASTAT_MELDUNG","ID_MANDANT","LAND_URSPRUNG","LETZTE_AENDERUNG","MASSEINHEIT","MELDEART","PAGINIERNUMMER","RECHBETRAG","STATISTISCHER_WERT","STEUERNR","UNTERSCHEIDUNGSNR","VERKEHRSZWEIG","WAEHRUNGSKENNZIFFER","WARENNR"}; 

		//E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_INTRASTAT_MELDUNG_WICHTIGKEIT","BESCHREIBUNG","ID_INSTAT_WICHTIGKEIT","ISTSTANDARD",true);
		
//		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, cFieldsInfolabs, oFM, false, false, this, 400);

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_INTRASTAT_MELDUNG"),true,50,10,true), new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BUNDESLAND_FA"),true,25,2,true), new MyE2_String("Land FA"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("STEUERNR"),true,100,11,true), new MyE2_String("Steuernr"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("UNTERSCHEIDUNGSNR"),true,25,3,true), new MyE2_String("UnterscheidungsNr"));

//		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("EXPORTFREIGABE")), new MyE2_String("Export"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("EXPORTIERT_AM"),true,50,10,true), new MyE2_String("Exportiert am"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_TPA_FUHRE"),true,50,10,true), new MyE2_String("ID Fuhre"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_TPA_FUHRE_ORT"),true,50,10,true), new MyE2_String("ID FuhrenOrt"));

		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MELDEART"),true,15,1,true), new MyE2_String("1Ein-/2Ausfuhr"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ANMELDEFORM"),true,15,1,true), new MyE2_String("Anmeldeform"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("ANMELDEMONAT"),true,25,2,true), new MyE2_String("Monat"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("PAGINIERNUMMER"),true,50,6,true), new MyE2_String("PosNr"));
		

		this.add_Component(new MyE2_DB_TextField(oFM.get_("BESTIMM_LAND"),true,25,3,true), new MyE2_String("Land Bestimmung/Ursprung"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BESTIMM_REGION"),true,25,2,true), new MyE2_String("Region Bestimmung/Ursprung"));

//		this.add_Component(new MyE2_DB_TextField(oFM.get_("GESCHAEFTSART"),true,25,2,true), new MyE2_String("Geschäftsart"));
		String sSql =   " SELECT to_nchar(INTRASTAT_SCHLUESSEL) || ' - ' || GESCHAEFTSART,  to_nchar(INTRASTAT_SCHLUESSEL,'FM9,999,999') " +
						" FROM JT_INTRASTAT_GESCHAEFTSART " +
						" ORDER BY INTRASTAT_SCHLUESSEL ";
		
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("GESCHAEFTSART"), sSql,false, false ), new MyE2_String("Geschäftsart"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("VERKEHRSZWEIG"),true,15,2,true), new MyE2_String("Verkehrszweig"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("WARENNR"),true,100,8,true), new MyE2_String("WarenNr"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("EIGENMASSE"),true,100,11,false), new MyE2_String("Eigenmasse"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MASSEINHEIT"),true,100,11,false), new MyE2_String("Masseinheit"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("RECHBETRAG"),true,100,11,false), new MyE2_String("Rechnungsbetrag"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("STATISTISCHER_WERT"),true,100,11,false), new MyE2_String("Stat. Wert"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("BEZUGSMONAT"),true,25,2,true), new MyE2_String("Bezugsmonat"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BEZUGSJAHR"),true,25,2,true), new MyE2_String("Bezugsjahr"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("LAND_URSPRUNG"),true,25,3,true), new MyE2_String("Ursprungsland"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("WAEHRUNGSKENNZIFFER"),true,15,1,true), new MyE2_String("Waehrung"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("EXPORT_NO_PRICE")), new MyE2_String("Export mit 0Euro"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("FRACHTKOSTEN"),true,100,11,false), new MyE2_String("Eingerechnete Transp.-Kost."));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("NICHT_MELDEN"),true), new MyE2_String("Nicht melden"));
		/*
		 * beispiele fuer beliebige felder
		this.add_Component(new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER")), new MyE2_String("Besitzer"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_INSTAT_WICHTIGKEIT"),oDDWichtigkeit.getDD(),false), new MyE2_String("Wichtig ?"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_INSTAT")), new MyE2_String("ID"));

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
		this.set_oMAPSettingAgent(new INSTAT_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new INSTAT_MASK_FORMATING_Agent());
	}
	
}
