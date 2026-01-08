package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_SELECTOR_AlleEigenenAdressen;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.DB_SELEKTOR_MaschinenTyp;


public class MS_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public MS_MASK_ComponentMAP(E2_BasicModuleContainer  oMaskContainer) throws myException
	{
		super(new MS_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("AKTIV")), new MyE2_String("AKTIV"));

		this.add_Component(new MyE2_DB_TextArea(oFM.get_("BEMERKUNG"),500,5), new MyE2_String("BEMERKUNG"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("BESCHREIBUNG"),500,5), new MyE2_String("BESCHREIBUNG"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BRIEFNUMMER"),true,200,60,false), new MyE2_String("BRIEFNUMMER"));
		
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("DATUM_ANSCHAFFUNG")), new MyE2_String("DATUM_ANSCHAFFUNG"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("FAHRGESTELLNUMMER"),true,200,60,false), new MyE2_String("FAHRGESTELLNUMMER"));
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("GEKAUFT_AB")), new MyE2_String("GEKAUFT_AB"));
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("GEWAEHRLEISTUNG_BIS")), new MyE2_String("GEWAEHRLEISTUNG_BIS"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("HERSTELLER"),true,200), new MyE2_String("HERSTELLER"));
		this.add_Component(new DB_SELECTOR_AlleEigenenAdressen(oFM.get_("ID_ADRESSE_STANDORT"),200), new MyE2_String("ID_ADRESSE_STANDORT"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_MASCHINEN")), new MyE2_String("ID_MASCHINEN"));
		this.add_Component(new DB_SELEKTOR_MaschinenTyp(oFM.get_("ID_MASCHINENTYP"), 200,false), new MyE2_String("ID_MASCHINENTYP"));
		this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER_BEDIENER1"),true,200), new MyE2_String("ID_USER_BEDIENER1"));
		this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER_BEDIENER2"),true,200), new MyE2_String("ID_USER_BEDIENER2"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("KFZKENNZEICHEN"),true,200,40,false), new MyE2_String("KFZKENNZEICHEN"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("KOSTEN_ANSCHAFFUNG"),true,200,14,false), new MyE2_String("KOSTEN_ANSCHAFFUNG"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("KOSTENSTELLE1"),true,30,3,false), new MyE2_String("KOSTENSTELLE1"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("KOSTENSTELLE2"),true,30,3,false), new MyE2_String("KOSTENSTELLE2"));
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("LEASING_BIS")), new MyE2_String("LEASING_BIS"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("TYPENBEZ"),true,300), new MyE2_String("TYPENBEZ"));
		

		//jetzt die Tochter mit den Aufgaben einfuegen
		this.add_Component(MS__CONST.HASHKEY_MASK_INLAY_AUFGABEN, new MS__MASK_FullDaughterAufgaben(oFM.get_oSQLFieldPKMainTable(), oMaskContainer), new MyE2_String("Aufgaben-Liste"));
		this.add_Component(MS__CONST.HASHKEY_MASK_INLAY_KOSTEN,  new MS__MASK_FullDaughterKosten(oFM.get_oSQLFieldPKMainTable(), oMaskContainer), new MyE2_String("Kosten-Liste"));
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new MS_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new MS_MASK_FORMATING_Agent());
	}
	
}
