package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;



/**
 * Button zum einblenden einer Erfassungsmaske fuer moegliche Felderweiterungen
 * @author martin
 *
 */
public class MZ_BUTTON_TO_DEF_ZUSATZFELDER extends MyE2_Button 
{

	public MZ_BUTTON_TO_DEF_ZUSATZFELDER() 
	{
		super(new MyE2_String("Zusatzfelder"));
		
		this.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				new ownModulContainerEditZusatzFieldNames();
			}
		});
		
	}

	
	
	private class ownModulContainerEditZusatzFieldNames extends Project_BasicModuleContainer
	{
		private E2_NavigationList 					oNavigationList = null;
		private E2_BASIC_EditListButtonPanel	  	oBasicEditListPanel = null;
		private ownComponentMAP_ZusatzFelder  		oComponentMAP_List = null;
		 
		 
		public ownModulContainerEditZusatzFieldNames() throws myException
		{
			super(E2_CONSTANTS_AND_NAMES.NAME_MODUL_MANDANTENZUSATZFELDER);
			
			
			this.oComponentMAP_List = 	new ownComponentMAP_ZusatzFelder();
			this.oNavigationList = 		new E2_NavigationList();
		
			this.oBasicEditListPanel = 	new E2_BASIC_EditListButtonPanel(this.oNavigationList,
					    							true,true,true,null,null,null,"", null, null, null);
				
			//jetzt die automatik-insert/update-felder ohne id_mandant bestimmen
			//this.oBasicEditListPanel.set_arrayDBAutomatikFelder(bibALL.get_DB_ZusatzFelder(false, true, true, bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF(), bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN("-")));
			
			this.oBasicEditListPanel.add(new ownDATASEARCH(), new Insets(10,2,2,2));

			this.oBasicEditListPanel.set_bShowOnlyInputRowsAt_NEW_AND_COPY(true);
			this.oBasicEditListPanel.set_BUTTON_STATUS_VIEW();
			
			this.oNavigationList.INIT_WITH_ComponentMAP(this.oComponentMAP_List,new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), null);
			//this.oNavigationList.get_vectorSegmentation().set_iSegmentGroesse(5);
			
			E2_ComponentGroupHorizontal oHorizontalgroup= new E2_ComponentGroupHorizontal(E2_INSETS.I_0_2_2_2);
			oHorizontalgroup.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNavigationList));
			oHorizontalgroup.add(this.oBasicEditListPanel);
			this.add(oHorizontalgroup, E2_INSETS.I_2_2_2_2);
			this.add_In_ContainerEX(this.oNavigationList,new Extent(100,Extent.PERCENT),new Extent(100,Extent.PERCENT), null);
			
			this.oNavigationList._REBUILD_COMPLETE_LIST("");
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), new MyE2_String("Erlaubte Zusatzfeld-Namen"));
			
		}

	
		private class ownDATASEARCH extends E2_DataSearch
		{

			public ownDATASEARCH() throws myException
			{
				super("JD_MANDANT_ZUSATZ_FELDNAMEN","ID_MANDANT_ZUSATZ_FELDNAMEN",null);
				
				E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(ownModulContainerEditZusatzFieldNames.this.oNavigationList);
				this.set_oSearchAgent(oSearchAgent);
				
				this.addSearchDef("ID_MANDANT_ZUSATZ_FELDNAMEN","ID",true);
				this.addSearchDef("FIELDNAME","Feldname",false);
				this.addSearchDef("RELATION_INFO","Verwendungszweck",false);
				this.addSearchDef("DEFAULT_LONG_VALUE","Ganzzahl-Wert",true);
				this.addSearchDef("DEFAULT_FLOAT_VALUE","Fliesskomma-Wert",true);
				this.addSearchDef("DEFAULT_TEXT_VALUE","Text",false);
				this.addSearchDef("DEFAULT_YES_NO_VALUE","Ja-Nein-Feld",false);
				

				//20180523: datenbank gestützte suche zufuegen
				this.initAfterConstruction();
			}

			private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
			{
				String cSearch = "";
				if (bNumber)
					cSearch = "SELECT JD_MANDANT_ZUSATZ_FELDNAMEN.ID_MANDANT_ZUSATZ_FELDNAMEN FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN WHERE TO_CHAR(JD_MANDANT_ZUSATZ_FELDNAMEN."+cFieldName+")='#WERT#'";
				else
					cSearch = "SELECT JD_MANDANT_ZUSATZ_FELDNAMEN.ID_MANDANT_ZUSATZ_FELDNAMEN FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ_FELDNAMEN WHERE UPPER(JD_MANDANT_ZUSATZ_FELDNAMEN."+cFieldName+") like upper('%#WERT#%')";
				
				this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
			}
				
		}

	}
	
	
	/* 
	 * sqlfieldmap fuer die in die maske platzierte liste der Artikelbezeichnungen
	 */
	private class ownSQLFieldMapZusatzFelder extends Project_SQLFieldMAP
	{ 
		public ownSQLFieldMapZusatzFelder() throws myException
		{
			super("JD_MANDANT_ZUSATZ_FELDNAMEN",null,false);
			this.initFields();
		} 
	}

	
	
	
	public class ownComponentMAP_ZusatzFelder extends E2_ComponentMAP
	{
	   
	 
		public ownComponentMAP_ZusatzFelder() throws myException
		{
			super(new ownSQLFieldMapZusatzFelder());
			
			this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
			this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
			
			SQLFieldMAP oSQLFM = this.get_oSQLFieldMAP();

			this.add_Component(new MyE2_DB_TextField(oSQLFM.get_("FIELDNAME"),true,200),new MyE2_String("Feldname"));
			this.add_Component(new MyE2_DB_TextField(oSQLFM.get_("RELATION_INFO"),true,200,null,new E2_FontPlain(-2)),new MyE2_String("Verwendungszweck"));

			this.add_Component(new MyE2_DB_TextArea(oSQLFM.get_("DEFAULT_TEXT_VALUE"),500,6,false,new E2_FontPlain(-2)),new MyE2_String("Vorgabe-Textwert"));

			this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("DEFAULT_YES_NO_VALUE")),new MyE2_String("Vorgabe-Ja/Nein"));
			this.add_Component(new MyE2_DB_TextField(oSQLFM.get_("DEFAULT_LONG_VALUE"),true,200),new MyE2_String("Vorgabe-Ganzzahl-Wert"));
			this.add_Component(new MyE2_DB_TextField(oSQLFM.get_("DEFAULT_FLOAT_VALUE"),true,200),new MyE2_String("Vorgabe-Fliess-Komma-Wert"));
			this.add_Component(new MyE2_DB_Label(oSQLFM.get_("ID_MANDANT_ZUSATZ_FELDNAMEN")),new MyE2_String("ID-Zusatz"));

			this.get__Comp("FIELDNAME").EXT().set_oColExtent(new Extent(200));
			this.get__Comp("RELATION_INFO").EXT().set_oColExtent(new Extent(200));
			this.get__Comp("DEFAULT_TEXT_VALUE").EXT().set_oColExtent(new Extent(500));
			
			this.get__Comp("ID_MANDANT_ZUSATZ_FELDNAMEN").EXT().set_bIsVisibleInList(false);
			this.get__Comp("DEFAULT_YES_NO_VALUE").EXT().set_bIsVisibleInList(false);
			this.get__Comp("DEFAULT_LONG_VALUE").EXT().set_bIsVisibleInList(false);
			this.get__Comp("DEFAULT_FLOAT_VALUE").EXT().set_bIsVisibleInList(false);
			
		}

	}

	

}
