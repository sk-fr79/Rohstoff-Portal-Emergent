package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class PROJEKT_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public PROJEKT_MASK_ComponentMAP() throws myException
	{
		super(new PROJEKT_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		/*
		 * beispiele fuer felder in der map
		 *
		*/
		String[] cFieldsStandard = {"AKTIV","BEENDET","PROJEKTBEGIN","PROJEKTBESCHREIBUNG","PROJEKTDEADLINE","PROJEKTNAME","WIEDERVORLAGE"}; 
		String[] cFieldsInfolabs = {"AKTIV","BEENDET","PROJEKTBEGIN","PROJEKTBESCHREIBUNG","PROJEKTDEADLINE","PROJEKTNAME","WIEDERVORLAGE"}; 

		E2_DropDownSettings oDDsGewicht = new E2_DropDownSettings(
													"JT_PROJEKTGEWICHT",
													"KURZBEZEICHNUNG||' ('||TO_CHAR(GEWICHT_1_100)||')'",
													"ID_PROJEKTGEWICHT",
													"",
													"IST_STANDARD",
													true,
													"GEWICHT_1_100");

		E2_DropDownSettings oDDsStatus = new E2_DropDownSettings(
													"JT_PROJEKTSTATUSVARIANTE",
													"KURZBEZEICHNUNG",
													"ID_PROJEKTSTATUSVARIANTE",
													"",
													"IST_STANDARD",
													true,
													"KURZBEZEICHNUNG");

		
		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, cFieldsInfolabs, oFM, false, false, this, 400);

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_PROJEKT")), new MyE2_String("ID_PROJEKT"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_PROJEKTGEWICHT"),oDDsGewicht.getDD(),false), new MyE2_String("ID_PROJEKTGEWICHT"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_PROJEKTSTATUSVARIANTE"),oDDsStatus.getDD(),false), new MyE2_String("ID_PROJEKTSTATUSVARIANTE"));
		this.add_Component(new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER")), new MyE2_String("Projektleiter"));
		
		((MyE2_DB_TextField)this.get__Comp("PROJEKTNAME")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("PROJEKTBESCHREIBUNG")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("PROJEKTBESCHREIBUNG")).set_iRows(6);
		
		
		this.add_Component(PROJEKT_LIST_BasicModuleContainer.NAME_OF_FIRMEN_DAUGHTER, new PROJEKT_MASK_Comp_Daughter_Firmen(oFM,this), new MyE2_String("beteiligte Firmen"));
		this.add_Component(PROJEKT_LIST_BasicModuleContainer.NAME_OF_MITARBEITER_DAUGHTER, new PROJEKT_MASK_Comp_Daughter_Mitarbeiter(oFM,this), new MyE2_String("Mitarbeiter und Funktion"));
		this.add_Component(PROJEKT_LIST_BasicModuleContainer.NAME_OF_FORTGANG_DAUGHTER, new PROJEKT_MASK_Comp_Daughter_FORTGANG(oFM,this), new MyE2_String("Fortgang des Projekts"));
		
		
		this.set_oMAPSettingAgent(new PROJEKT_MASK_MapSettingAgent());
		
	}
	
}
