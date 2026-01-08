package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER_OR_DEVELOPER;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonToCreate_SQL_ExportStatements;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;


public class MZ_MASK_BEZ_ModulContainerLIST extends E2_BasicModuleContainer 
{
	private E2_NavigationList 					oNavigationList = null;
	private E2_BASIC_EditListButtonPanel	  	oBasicEditListPanel = null;
	private MZ_MASK_BEZ_LIST_ComponentMAP  		oComponentMAP_List = null;
	 
	 


	public MZ_MASK_BEZ_ModulContainerLIST() throws myException
	{
		super();
		
		this.set_bVisible_Row_For_Messages(false);
		
		this.oComponentMAP_List = 	new MZ_MASK_BEZ_LIST_ComponentMAP();
		this.oNavigationList = 		new E2_NavigationList();
	
//		this.oButtonZusatzFelder  = new MZ_BUTTON_TO_DEF_ZUSATZFELDER();
		
		this.oBasicEditListPanel = 	new E2_BASIC_EditListButtonPanel(this.oNavigationList,
				    							true,true,true,null,null,null,"", null, null, null);
		
		
		//2017-03-07: sql-export-statement
		E2_ButtonToCreate_SQL_ExportStatements bt_sql = new E2_ButtonToCreate_SQL_ExportStatements();
		this.oBasicEditListPanel.add(bt_sql, new Insets(10,2,2,2));
		
		
		this.oBasicEditListPanel.add(new ownDATASEARCH(), new Insets(10,2,2,2));
		
		//jetzt die automatik-insert/update-felder ohne id_mandant bestimmen
		this.oBasicEditListPanel.set_arrayDBAutomatikFelder(bibALL.get_DB_ZusatzFelder(false, true, true, bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF(), bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN("-")));
		
		
		this.oBasicEditListPanel.set_bShowOnlyInputRowsAt_NEW_AND_COPY(true);
		this.oBasicEditListPanel.set_BUTTON_STATUS_VIEW();
		
		this.oNavigationList.INIT_WITH_ComponentMAP(this.oComponentMAP_List,new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), null);
		//this.oNavigationList.get_vectorSegmentation().set_iSegmentGroesse(5);
		
		bt_sql._init(this.oNavigationList, new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER_OR_DEVELOPER());
		
		E2_ComponentGroupHorizontal oHorizontalgroup= new E2_ComponentGroupHorizontal(E2_INSETS.I_0_2_2_2);
		oHorizontalgroup.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNavigationList));
		oHorizontalgroup.add(this.oBasicEditListPanel);
		this.add(oHorizontalgroup, E2_INSETS.I_2_2_2_2);
		this.add_In_ContainerEX(this.oNavigationList,new Extent(100,Extent.PERCENT),new Extent(100,Extent.PERCENT), null);
		
		this.oNavigationList._REBUILD_COMPLETE_LIST("");
	}

	
	/*
	 * basis-id setzen, wird von der maske aus gesteuert
	 */
	public void set_ID_MANDANT_IN_ZUSATZ_TABELLE(String cID_Unformated) throws myException
	{
		try
		{
			((SQLFieldForRestrictTableRange)this.oComponentMAP_List.get_oSQLFieldMAP().get("ID_MANDANT")).set_cRestrictionValue_IN_DB_FORMAT(""+cID_Unformated);
				
		}
		catch (Exception ex)
		{
			throw new myException(this,"set_ID_MANDANT_IN_ZUSATZ: Unknown Error: "+ex.getLocalizedMessage());
		}
	}
	
	public E2_NavigationList 		get_oNavigationList() 			{		return oNavigationList;		}
	public E2_BASIC_EditListButtonPanel get_oBasicEditListPanel()	{		return oBasicEditListPanel;	}

	
	

	
	
	private class ownDATASEARCH extends E2_DataSearch
	{

		public ownDATASEARCH() throws myException
		{
			super("JD_MANDANT_ZUSATZ","ID_MANDANT_ZUSATZ",null);
			
			E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(MZ_MASK_BEZ_ModulContainerLIST.this.oNavigationList);
			this.set_oSearchAgent(oSearchAgent);
			
			this.addSearchDef("ID_MANDANT_ZUSATZ","ID",true);
			this.addSearchDef("FIELDNAME","Feldname",false);
			this.addSearchDef("LONG_VALUE","Ganzzahl-Wert",true);
			this.addSearchDef("FLOAT_VALUE","Fliesskomma-Wert",true);
			this.addSearchDef("TEXT_VALUE","Text",false);
			this.addSearchDef("YES_NO_VALUE","Ja-Nein-Feld",false);
			

			//20180523: datenbank gestützte suche zufuegen
			this.initAfterConstruction();
		}

		private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
		{
			String cSearch = "";
			if (bNumber)
				cSearch = "SELECT JD_MANDANT_ZUSATZ.ID_MANDANT_ZUSATZ FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ WHERE TO_CHAR(JD_MANDANT_ZUSATZ."+cFieldName+")='#WERT#'";
			else
				cSearch = "SELECT JD_MANDANT_ZUSATZ.ID_MANDANT_ZUSATZ FROM "+bibE2.cTO()+".JD_MANDANT_ZUSATZ WHERE UPPER(JD_MANDANT_ZUSATZ."+cFieldName+") like upper('%#WERT#%')";
			
			this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
		}
		


			
	}
	
	
}
