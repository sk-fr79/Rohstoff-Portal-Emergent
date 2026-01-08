package rohstoff.Echo2BusinessLogic.FIBU.EXPORT_PROFILES;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.LayoutData;
import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_FOR_DATE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_CONST;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;
import rohstoff.utils.ECHO2.E2_AuswahlSelectorUsers;
import rohstoff.utils.ECHO2.LIST_SELECTOR_BENUTZER;


public class FIBU_EXPORT_PROFILES_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public FIBU_EXPORT_PROFILES_MASK_ComponentMAP() throws myException
	{
		super(new FIBU_EXPORT_PROFILES_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		/*
		 * beispiele fuer felder in der map
		 *
		*/

		//E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_DATEV_PROFILE_WICHTIGKEIT","BESCHREIBUNG","ID_FIBU_EXPORT_PROFILES_WICHTIGKEIT","ISTSTANDARD",true);
		
//		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, cFieldsInfolabs, oFM, false, false, this, 400);

		//hier kommen die Felder	
//		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("DATEV_GESCHAEFTSJAHRESBEGINN"),true,120,10,false), new MyE2_String("DATEV_GESCHAEFTSJAHRESBEGINN"));
//		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("DATEV_GESCHAEFTSJAHRESBEGINN"),"dd.mm.yyyy",120,false,null,false), new MyE2_String("DATEV_GESCHAEFTSJAHRESBEGINN"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("DESCRIPTION"),true,100,10,false), new MyE2_String("Beschreibung"));
		this.add_Component(new MyE2_DB_TextField_FOR_DATE(oFM.get_("DATEV_GESCHAEFTSJAHRESBEGINN")), new MyE2_String("Geschäftsjahresbeginn (Datev)"));

//		public MyE2_DB_TextField_DatePOPUP_OWN(SQLField osqlField, String text, int iwidthPixel, boolean bDisabledFromBasic, LayoutData oLayout, boolean bMiniIcon) throws myException

		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("DATEV_BERATERNUMMER"),true,100,10,false), new MyE2_String("Datev-Beraternummer"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("DATEV_MANDANTNUMMER"),true,100,10,false), new MyE2_String("Datev-Mandantnummer"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_DATEV_PROFILE"),true,200,10,false), new MyE2_String("ID_DATEV_PROFILE"));
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_DRUCKER"),true,200,10,false), new MyE2_String("Drucker"));
		
		// Data definition and dropdown select field for "Artikelgruppen" 
		E2_DropDownSettingsNew  oDD = new E2_DropDownSettingsNew("SELECT ID_DRUCKER || ' - ' || NAME AS DRUCKER_NAME, ID_DRUCKER FROM JT_DRUCKER WHERE aktiv = 'Y' ORDER BY ID_DRUCKER ASC", true, true);
		MyE2_DB_SelectField sf_DRUCKER= new MyE2_DB_SelectField(oFM.get_("ID_DRUCKER"), new dataToView(oDD.getDD(), false, bibE2.get_CurrSession()));
		this.add_Component(sf_DRUCKER, new MyE2_String("Drucker"));

		E2_DropDownSettingsNew  oDD2 = new E2_DropDownSettingsNew("SELECT ID_DRUCKER || ' - ' || NAME AS DRUCKER_NAME_PROTOKOLLE, ID_DRUCKER AS ID_DRUCKER_PROTOKOLLE FROM JT_DRUCKER WHERE aktiv = 'Y' ORDER BY ID_DRUCKER ASC", true, true);
		MyE2_DB_SelectField sf2_DRUCKER= new MyE2_DB_SelectField(oFM.get_("ID_DRUCKER_PROTOKOLLE"), new dataToView(oDD2.getDD(), false, bibE2.get_CurrSession()));
		this.add_Component(sf2_DRUCKER, new MyE2_String("Protokoll-Drucker"));

		
//		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_USER"),true,200,10,false), new MyE2_String("ID_USER"));

		
//		DB_Component_USER_DROPDOWN oSelectID_USER_BESITZER = new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER"));
//		this.add_Component(oSelectID_USER_BESITZER, new MyE2_String( "Eintrag von:"));
		
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("EXPORT_DIR"),true,100,10,false), new MyE2_String("Server-Exportpfad"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("EXPORTS_STARTING_DATE"),true,100,10,false), new MyE2_String("Server-Exportpfad"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("EXPORTS_STARTING_ID"),true,100,10,false), new MyE2_String("Server-Exportpfad"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("FLUSH_TABLES")), new MyE2_String("Exporttabellen bei Export leeren"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("PRINT_PROTOCOLS")), new MyE2_String("Server-Exportpfad"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("CORRECT_DATES")), new MyE2_String("Server-Exportpfad"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("STAMP_IMPORTED_WITH_DEBUGFLAGS")), new MyE2_String("Server-Exportpfad"));
				


		/*
		 * beispiele fuer beliebige felder
		this.add_Component(new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER")), new MyE2_String("Besitzer"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_FIBU_EXPORT_PROFILES_WICHTIGKEIT"),oDDWichtigkeit.getDD(),false), new MyE2_String("Wichtig ?"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_FIBU_EXPORT_PROFILES")), new MyE2_String("ID"));

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
		this.set_oMAPSettingAgent(new FIBU_EXPORT_PROFILES_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new FIBU_EXPORT_PROFILES_MASK_FORMATING_Agent());
	}
	
}
