package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LIVEAUSWERTUNG;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public abstract class XX_ClassSammler4AuswerteZentrale
{
	
	private E2_NavigationList                   oNaviList = null;
	private E2_ComponentMAP  					oComponentMAP = null;
	
	
	
//	public abstract void  					   	init() throws myException;
	
	public abstract E2_ComponentMAP  			build__oComponentMAP() throws myException;
	
	public abstract E2_ListSelectorContainer    get__oListSelectorContainer()  throws myException;
	
	public abstract MyE2_Grid   				get__oListBedienPanelWithButtons() throws myException;
	
	public abstract E2_DataSearch   			get__oListSearch() throws myException;
	
	public abstract String  					get__cMODUL_KENNER_ADDON();
	
	/*
	 * gibt die basis-query zurueck, die statt einer tabelle hier verwendet wird (im simplen Fall: select * from jt_adresse)
	 */
	public abstract String   					get__cBaseSQLQuery() throws myException;
	

	
	public SQLFieldMAP   get__oSQLFieldMAP() throws myException
	{
		return this.oComponentMAP.get_oSQLFieldMAP();
	}
	
	public void INIT_WITH_ComponentMAP(MutableStyle StyleForGrid, String MODULE_IDENTIFIER_OF_CONTAINING_MODULE) throws myException
	{
		this.oComponentMAP = this.build__oComponentMAP();
		this.oNaviList = new E2_NavigationList();
		this.oNaviList.set_bSetGridWidthFixed(true);
		
		this.oNaviList.INIT_WITH_ComponentMAP(this.oComponentMAP, StyleForGrid, MODULE_IDENTIFIER_OF_CONTAINING_MODULE);
	}
	
	
	public E2_NavigationList get_oNaviList()
	{
		return this.oNaviList;
	}
	
	
	public E2_ButtonToSelectVisibleListColumns_and_other_settings get_oButtonToSelectVisibleColumns()
	{
		return new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNaviList);
	}

	public MyE2_Button get_oButtonToShowBaseQuery()
	{
		return new ownButtonShowSQL_Statement();
	}
	
	
	public E2_ComponentMAP get_oComponentMAP()
	{
		return oComponentMAP;
	}
	
	
	private class ownButtonShowSQL_Statement extends MyE2_Button
	{

		public ownButtonShowSQL_Statement()
		{
			super(E2_ResourceIcon.get_RI("sql_button.png"), true);
			this.setToolTipText(new MyE2_String("Zeigt die SQL-Abfrage an, die der Liste zugrunde liegt ...").CTrans());
			this.add_oActionAgent(new ownActionAgent());
		}
		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				E2_BasicModuleContainer  oContainer = new E2_BasicModuleContainer();
				MyE2_TextArea            oTF = new MyE2_TextArea(XX_ClassSammler4AuswerteZentrale.this.get__cBaseSQLQuery(), 700, 0, 40);
				oContainer.add(oTF, E2_INSETS.I_2_2_2_2);

				oTF.setEnabled(false);
				
				oContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(750), new Extent(750), new MyE2_String("Innere Abfrage, die dieser Liste zugrunde liegt .."));
			}
		}
		
		
	}
	
	
}
