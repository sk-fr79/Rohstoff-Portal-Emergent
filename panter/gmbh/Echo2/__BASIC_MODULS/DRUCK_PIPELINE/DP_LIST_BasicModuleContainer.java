package panter.gmbh.Echo2.__BASIC_MODULS.DRUCK_PIPELINE;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObjects_LISTHEADLINE;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.ComboBoxErsatzMitReportFilenames;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class DP_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{

	//array mit den reportnames, wird im ganzen modul verwendet, deshalb hier einmal erstellt und verteilt
//	private String[][] 				arrayReportNames = null;
	private E2_NavigationList		oNaviListReportPipeline = null;
	
	


	

	public DP_LIST_BasicModuleContainer() throws myException 
	{
		super(E2_CONSTANTS_AND_NAMES.NAME_MODUL_REPORTPIPELINE);
		
		this.oNaviListReportPipeline = 		new E2_NavigationList();
		
		E2_BASIC_EditListButtonPanel  		oBasicEditListPanel = 	new E2_BASIC_EditListButtonPanel(
																					oNaviListReportPipeline,
																					true,true,true,null,null,
																					this.get_MODUL_IDENTIFIER(),
																					"", null, null, null);
		
		
		//2014-04-28: exporter um komplette pipeline-definitionen zu exportieren
		if (bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES())
		{
			oBasicEditListPanel.add(new DP_ButtonExportPipeLine(oNaviListReportPipeline), new Insets(10,2,2,2));
		}
		
		oBasicEditListPanel.add(new ownDATASEARCH(oNaviListReportPipeline), new Insets(10,2,2,2));
		
		oBasicEditListPanel.set_bShowOnlyInputRowsAt_NEW_AND_COPY(true);
		oBasicEditListPanel.set_BUTTON_STATUS_VIEW();
		
		oNaviListReportPipeline.INIT_WITH_ComponentMAP(new componentMapList_REPORT_PIPELINE(),new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), null);
		//oNaviListReportPipeline.get_vectorSegmentation().set_iSegmentGroesse(10);
		
		E2_ComponentGroupHorizontal oHorizontalgroup= new E2_ComponentGroupHorizontal(E2_INSETS.I_0_2_2_2);
		oHorizontalgroup.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviListReportPipeline));
		oHorizontalgroup.add(oBasicEditListPanel);
		this.add(oHorizontalgroup, E2_INSETS.I_2_2_2_2);
		this.add_In_ContainerEX(oNaviListReportPipeline,new Extent(100,Extent.PERCENT),new Extent(100,Extent.PERCENT), null);
		
		oNaviListReportPipeline._REBUILD_COMPLETE_LIST("");
		
	}


	public E2_NavigationList get_oNaviListReportPipeline() 
	{
		return oNaviListReportPipeline;
	}

	
	
	private class sqlFieldMap_REPORT_PIPELINE extends Project_SQLFieldMAP 
	{

		public sqlFieldMap_REPORT_PIPELINE() throws myException 
		{
			super("JT_REPORT_PIPELINE", null,true);
		}
		
	}
	
	
	private class componentMapList_REPORT_PIPELINE extends E2_ComponentMAP
	{

		public componentMapList_REPORT_PIPELINE() throws myException
		{
			super(new sqlFieldMap_REPORT_PIPELINE());
			
			SQLFieldMAP oSQLFM = this.get_oSQLFieldMAP();
			
			ComboBoxErsatzMitReportFilenames oSelektReportNames = new ComboBoxErsatzMitReportFilenames(oSQLFM.get_("REPORTFILEBASENAME"),400,300);
			
			
			this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
			this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
			
			this.add_Component("EXTENDER",new E2_ListButtonExtendDaughterObject(false, null),new MyE2_String("+"));
//			
//			//jetzt der spalte mit dem Tochterlisten-extender einen header verpassen
			MyE2IF__Component   oExtender = this.get__Comp("EXTENDER");
			oExtender.EXT().set_oCompTitleInList(new E2_ListButtonExtendDaughterObjects_LISTHEADLINE(DP_LIST_BasicModuleContainer.this.oNaviListReportPipeline));
			this.set_List_EXPANDER_4_ComponentMAP(new DP_ListExpander_Pipeline_Positions(	DP_LIST_BasicModuleContainer.this.oNaviListReportPipeline,
																							DP_LIST_BasicModuleContainer.this));
			
			
			this.add_Component(oSelektReportNames, new MyE2_String("Report"));
			this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("DIREKTDRUCKE_ZUSAMMENFASSEN")), new MyE2_String("Direktdruck zusammenfassen"));
			this.add_Component(new MyE2_DB_Label(oSQLFM.get_("ID_REPORT_PIPELINE")),new MyE2_String("ID"));
			
			
			
		}
	}
	
	
	
	private class ownDATASEARCH extends E2_DataSearch
	{

		public ownDATASEARCH(E2_NavigationList  oNaviList) throws myException
		{
			super("JD_REPORT_PIPELINE","ID_REPORT_PIPELINE",null);
			
			E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
			this.set_oSearchAgent(oSearchAgent);
			
			this.addSearchDef("ID_REPORT_PIPELINE","ID",true);
			this.addSearchDef("REPORTFILEBASENAME","Reportname",false);
			

			//20180523: datenbank gestützte suche zufuegen
			this.initAfterConstruction();
		}

		private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
		{
			String cSearch = "";
			if (bNumber)
				cSearch = "SELECT JD_REPORT_PIPELINE.ID_REPORT_PIPELINE FROM "+bibE2.cTO()+".JD_REPORT_PIPELINE WHERE TO_CHAR(JD_REPORT_PIPELINE."+cFieldName+")='#WERT#'";
			else
				cSearch = "SELECT JD_REPORT_PIPELINE.ID_REPORT_PIPELINE FROM "+bibE2.cTO()+".JD_REPORT_PIPELINE WHERE UPPER(JD_REPORT_PIPELINE."+cFieldName+") like upper('%#WERT#%')";
			
			this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
		}
		
	}

	
	
}
