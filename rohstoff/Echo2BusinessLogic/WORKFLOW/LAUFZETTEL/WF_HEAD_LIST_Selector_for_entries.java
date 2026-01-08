package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ExpandableRow_NG;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class WF_HEAD_LIST_Selector_for_entries extends E2_ExpandableRow_NG /*E2_ListSelectorContainer*/{


	//2011-12-02: variable zu klassenvariablen hochgestuft
	//2014-10-13: optionen in eigenen bereich verlegt, um selektion immer zu ermöglichen
	WF_CheckBoxOption oCB_Undeleted  = new WF_CheckBoxOption("ungelöscht", EnumDisplayOptions.SHOW_UNDELETED);
	WF_CheckBoxOption oCB_Deleted = new WF_CheckBoxOption("gelöscht",EnumDisplayOptions.SHOW_DELETED);

	WF_CheckBoxOption oCB_Finished = new WF_CheckBoxOption("abgeschlossen", EnumDisplayOptions.SHOW_FINISHED);
	WF_CheckBoxOption oCB_Open = new WF_CheckBoxOption("offen", EnumDisplayOptions.SHOW_OPEN);
	
	WF_CheckBoxOption oCB_Own_Bearbeiter = new WF_CheckBoxOption("Bearbeiter",EnumDisplayOptions.SHOW_BEARBEITER_JA);
	WF_CheckBoxOption oCB_Others_Bearbeiter = new WF_CheckBoxOption("nicht Bearbeiter",EnumDisplayOptions.SHOW_BEARBEITER_NEIN);
	
	WF_CheckBoxOption oCB_Own_Besitzer = new WF_CheckBoxOption("Besitzer",EnumDisplayOptions.SHOW_BESITZER_JA);
	WF_CheckBoxOption oCB_Others_Besitzer = new WF_CheckBoxOption("nicht Besitzer",EnumDisplayOptions.SHOW_BESITZER_NEIN);
	
	
	
//	private E2_SelectionComponentsVector 	oSelVector = null;
	private E2_NavigationList oNaviList = null;
	
//	private MyE2_Row          oRowButtons = null;
	private MyE2_Grid		  oGridButtons = null;
	
	public WF_HEAD_LIST_Selector_for_entries(E2_NavigationList oNavigationList, String cMODULE_KENNER/*, E2_SelectionComponentsVector oSelVector*/) throws myException {
		super(new MyE2_String("Aufgaben-Auswahl ausgeblendet ..."),null,null);
		
		this.oNaviList = oNavigationList;
		
		oCB_Undeleted.setToolTipText(new MyString("Zeige ungelöschte Aufgaben an.").CTrans());
		oCB_Deleted.setToolTipText(new MyString("Zeige gelöschte Aufgaben an.").CTrans());
		oCB_Open.setToolTipText(new MyString("Zeige offene Aufgaben an.").CTrans());
		oCB_Finished.setToolTipText(new MyString("Zeige abgeschlossene Aufgaben an.").CTrans());
		
		oCB_Own_Besitzer.setToolTipText(new MyString("Zeige Aufgaben an, deren Besitzer der ausgewählte Mitarbeiter ist.").CTrans());
		oCB_Others_Besitzer.setToolTipText(new MyString("Zeige Aufgaben an, bei denen der gewählte Mitarbeiter nicht der Besitzer ist.").CTrans());
		
		oCB_Own_Bearbeiter.setToolTipText(new MyString("Zeige Aufgaben an, deren Bearbeiter der ausgewählte Mitarbeiter ist.").CTrans());
		oCB_Others_Bearbeiter.setToolTipText(new MyString("Zeige Aufgaben an, bei denen der gewählte Mitarbeiter nicht der Bearbeiter ist.").CTrans());
		
		//testCheckBox oCB_Deleted = new testCheckBox(new MyE2_String("Zeige gelöschte Einträge"));
		oCB_Undeleted.add_oActionAgent(new OptionSelectorAction());
		oCB_Deleted.add_oActionAgent(new OptionSelectorAction());
		oCB_Open.add_oActionAgent(new OptionSelectorAction());
		oCB_Finished.add_oActionAgent(new OptionSelectorAction());
		oCB_Own_Bearbeiter.add_oActionAgent(new OptionSelectorAction());
		oCB_Others_Bearbeiter.add_oActionAgent(new OptionSelectorAction());
		oCB_Own_Besitzer.add_oActionAgent(new OptionSelectorAction());
		oCB_Others_Besitzer.add_oActionAgent(new OptionSelectorAction());
		
		oCB_Own_Bearbeiter.setSelected(true);
		oCB_Own_Besitzer.setSelected(true);
		oCB_Open.setSelected(true);
		oCB_Undeleted.setSelected(true);
		
		oGridButtons = new MyE2_Grid(6, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		MyE2_Label lblAufgabeDesc =new MyE2_Label(true);
		lblAufgabeDesc.set_Text( new MyE2_String("Zeige die Aufgabe im aufgeklappten Laufzettel an, falls für Mitarbeiter / Aufgabenstatus folgendes zutrifft: "));
		
		oGridButtons.add(lblAufgabeDesc,1,3,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_TOP);
		oGridButtons.add(new MyE2_Label( new MyE2_String("Der Mitarbeiter ist ")),1,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_LEFT);
		oGridButtons.add(oCB_Own_Bearbeiter);
		oGridButtons.add(oCB_Own_Besitzer);
		oGridButtons.add(oCB_Others_Bearbeiter);
		oGridButtons.add(oCB_Others_Besitzer);
		
//		oGridButtons.add(new MyE2_Label( new MyE2_String("")));
		oGridButtons.add(new MyE2_Label( new MyE2_String("Die Aufgabe ist")),1,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_LEFT);
		oGridButtons.add(oCB_Open);
		oGridButtons.add(oCB_Finished);
		oGridButtons.add(new MyE2_Label( new MyE2_String("")));
		oGridButtons.add(new MyE2_Label( new MyE2_String("")));

//		oGridButtons.add(new MyE2_Label( new MyE2_String("")));
		oGridButtons.add(new MyE2_Label( new MyE2_String("Die Aufgabe ist")),1,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_LEFT);
		oGridButtons.add(oCB_Undeleted);
		oGridButtons.add(oCB_Deleted);
		oGridButtons.add(new MyE2_Label( new MyE2_String("")));
		oGridButtons.add(new MyE2_Label( new MyE2_String("")));
		
		oGridButtons.setColumnWidth(0, new Extent(360));
		oGridButtons.setColumnWidth(1, new Extent(120));
		oGridButtons.setColumnWidth(2, new Extent(150));
		oGridButtons.setColumnWidth(3, new Extent(150));
		oGridButtons.setColumnWidth(4, new Extent(150));
		oGridButtons.setColumnWidth(5, new Extent(150));
		
		this.add(oGridButtons);

	}
	
	

	
	
	/**
	 * Action Agent-Klasse für die Behandlung der Auswahloptionen
	 * @author manfred
	 *
	 */
	
	private class OptionSelectorAction extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_NavigationList oList = WF_HEAD_LIST_Selector_for_entries.this.oNaviList;
			
			Vector<E2_ComponentMAP>  vMaps = oList.get_vComponentMAPS();
			
			for (E2_ComponentMAP oMap: vMaps)
			{
				if (oMap.get_List_EXPANDER_4_ComponentMAP().get_bIsOpen())
				{
					oMap.get_List_EXPANDER_4_ComponentMAP().refreshDaughterComponent();
				}
			}
			
		}
		
		
		
	}
	

}
