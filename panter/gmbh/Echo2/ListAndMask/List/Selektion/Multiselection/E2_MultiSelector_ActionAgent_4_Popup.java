package panter.gmbh.Echo2.ListAndMask.List.Selektion.Multiselection;

import java.util.Iterator;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class E2_MultiSelector_ActionAgent_4_Popup extends XX_ActionAgent{

	private E2_MultiSelector parent 												= null;

	private E2_Grid grid_4_popUp;

	private E2_BasicModuleContainer 				container_4_additionnal_values 	= null;

	private Vector<XX_ListSelector_4Multiselect> 	vector_use_in_case_of_cancel	= new Vector<XX_ListSelector_4Multiselect>();				

	public E2_MultiSelector_ActionAgent_4_Popup(E2_MultiSelector oParent) throws myException {
		super();

		this.parent = oParent;

		this.grid_4_popUp = new E2_Grid()._setSize(300,20,20)._st(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		oParent.format_gridInPopup(this.grid_4_popUp);

	}

	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {

		this.vector_use_in_case_of_cancel.clear();

		if (parent.get_vectorOfSelectors().size()<=1)
		{		
			XX_ListSelector_4Multiselect neu_sel = this.parent.generate_selector_part(this.parent.get_vectorOfSelectors().get(0));

			this.parent.get_vectorOfSelectors().add(neu_sel);	

			this.parent.get_vectorOfSelectors().add(this.parent.generate_selector_part(null));

		}
		else{
			XX_ListSelector_4Multiselect neu_sel = this.parent.generate_selector_part(this.parent.get_vectorOfSelectors().get(0));

			this.parent.get_vectorOfSelectors().add(1, neu_sel);


		}

		for(XX_ListSelector_4Multiselect sel: this.parent.get_vectorOfSelectors()){
			vector_use_in_case_of_cancel.addElement(this.parent.generate_selector_part(sel));
		}

		this.container_4_additionnal_values=parent.get_PopupContainer();

		this.container_4_additionnal_values.add_CloseActions(new own_close_action(container_4_additionnal_values));
		
		this.container_4_additionnal_values.add(this.grid_4_popUp, E2_INSETS.I_5_5_5_5);

		this._clear_and_fill_grid();

		this.container_4_additionnal_values.CREATE_AND_SHOW_POPUPWINDOW(new Extent(370), new Extent(300), new MyE2_String("Weitere Bereiche zufügen .."));
	}


	private void _clear_and_fill_grid() throws myException {

		MyE2_Button btSpeichern = 				new MyE2_Button("OK",
				new MyE2_String("Auswahl speichern und Mehrfachselektion ausführen"),
				null,true);

		MyE2_Button btAbbruch = 				new MyE2_Button("Abbrechen",
				new MyE2_String("Auswahl abbrechen, nichts verändern"),
				new ownActionCancelListe(),true);

		MyE2_Button btClear = 					new MyE2_Button("Selektion löschen",
				new MyE2_String("Alle Felder löschen"),
				null,true);

		//wichtiges hervorheben
		btClear.setIcon(E2_ResourceIcon.get_RI("multi_select_delete.png"));

		//2014-07-03: BTNClear
		btClear.add_oActionAgent(new ownActionClearMultiSelect());
		for (XX_ActionAgent a : E2_MultiSelector_ActionAgent_4_Popup.this.parent.get_vAgents4ActiveComponentsBeforeSelection()){
			btClear.add_oActionAgent(a);
		}
		btClear.add_oActionAgent(E2_MultiSelector_ActionAgent_4_Popup.this.parent.get_agent_4_selection());
		for (XX_ActionAgent a2 :E2_MultiSelector_ActionAgent_4_Popup.this.parent.get_vAgents4ActiveComponentsAfterSelection()){
			btClear.add_oActionAgent(a2);
		}



		//der speicherbutton bekommt dann noch die zusatzaction des listenneubaus
		//2014-07-03: weitere agenten vor-und nach der Selektion (Ableitung von XX_ListSelektor_EXT
		btSpeichern.setFont(new E2_FontBold());

		btSpeichern.add_oActionAgent(new ownActionSaveListe(), true);
		for (XX_ActionAgent a3 : E2_MultiSelector_ActionAgent_4_Popup.this.parent.get_vAgents4ActiveComponentsBeforeSelection()){
			btSpeichern.add_oActionAgent(a3);
		}
		btSpeichern.add_oActionAgent(E2_MultiSelector_ActionAgent_4_Popup.this.parent.get_agent_4_selection());
		for (XX_ActionAgent a4 : E2_MultiSelector_ActionAgent_4_Popup.this.parent.get_vAgents4ActiveComponentsAfterSelection()){
			btSpeichern.add_oActionAgent(a4);
		}
		// ganz am Anfang das SaveListe-Event ausführen


		this.grid_4_popUp.removeAll();

		for (int i=1; i<this.parent.get_vectorOfSelectors().size();i++){

			this.grid_4_popUp._a(this.parent.get_vectorOfSelectors().get(i).render_representation_4_popup_selector(null),new RB_gld()._ins(0,0,5,5));//1, E2_INSETS.I_0_0_5_5);

			this.grid_4_popUp._a(new ownButtonDeleteSelectField(i),new RB_gld()._ins(0,0,5,5));

			if ( i==this.parent.get_vectorOfSelectors().size()-1 )         //beim letzten einen add-button zufuegen
			{
				this.grid_4_popUp._a(new ownButtonHinzuFuegen(),new RB_gld()._ins(0,0,5,5));
			}
			else
			{
				this.grid_4_popUp._a(new MyE2_Label(" "),new RB_gld()._ins(0,0,5,5));
			}
		}

		int iBreiteButtons[] = {80,150,120};

		this.grid_4_popUp._a(new E2_ComponentGroupHorizontal_NG(btSpeichern,btClear,btAbbruch,iBreiteButtons), new RB_gld()._ins(0,10,5,5));		
	}

	private void close_action() throws myException{
		E2_MultiSelector_ActionAgent_4_Popup oThis = E2_MultiSelector_ActionAgent_4_Popup.this;

		oThis.parent.get_vectorOfSelectors().clear();

		for(XX_ListSelector_4Multiselect sel: oThis.vector_use_in_case_of_cancel){
			if(S.isFull(sel.get_WhereBlock())){
				oThis.parent.get_vectorOfSelectors().add(oThis.parent.generate_selector_part(sel));					
			}
		}

		if(oThis.parent.get_vectorOfSelectors().size()==0){
			oThis.parent.get_vectorOfSelectors().add(oThis.parent.generate_selector_part(null));
			oThis.parent.get_vectorOfSelectors().get(0).add_ActionAgentToComponent(oThis.parent.get_agent_4_selection());
		}
		else{
			oThis.parent.get_vectorOfSelectors().remove(0);
			oThis.parent.get_vectorOfSelectors().get(0).add_ActionAgentToComponent(oThis.parent.get_agent_4_selection());
		}

		if(oThis.parent.get_vectorOfSelectors().size()>1){
			oThis.parent.render_selector(true);
			oThis.parent.get_button_4_multiSelect().set_StatusHasMultiSelect(oThis.parent.get_vectorOfSelectors().size());
		}
		else{
			oThis.parent.render_selector(false);
			oThis.parent.get_button_4_multiSelect().set_StatusHasMultiSelect(0);
		}
	}

	private class own_close_action extends XX_ActionAgentWhenCloseWindow{

		public own_close_action(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(this.get_oContainer().get_cWINDOW_CLOSE_STATUS().equals(E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_CANCEL)){
				close_action();
			}	
		}
	}
	
	private class ownActionCancelListe extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_MultiSelector_ActionAgent_4_Popup oThis = E2_MultiSelector_ActionAgent_4_Popup.this;
			oThis.container_4_additionnal_values.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}


	private class ownActionClearMultiSelect extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_MultiSelector_ActionAgent_4_Popup.this.parent.clear_selector();

			E2_MultiSelector_ActionAgent_4_Popup.this.container_4_additionnal_values.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}


	private class ownButtonDeleteSelectField extends MyE2_Button
	{
		private int iNumberInVector = 0;

		public ownButtonDeleteSelectField(int NumberInVector)
		{
			super(E2_ResourceIcon.get_RI("multi_select_delete.png"), true);
			this.setToolTipText(new MyE2_String("Diese Auswahlmöglichkeit entfernen").CTrans());
			this.iNumberInVector=NumberInVector;

			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					if (E2_MultiSelector_ActionAgent_4_Popup.this.parent.get_vectorOfSelectors().size()>2)    //falls noch mindestens 2 vorhanden sind, dann das select-field loeschen,
					{
						E2_MultiSelector_ActionAgent_4_Popup.this.parent.get_vectorOfSelectors().remove(ownButtonDeleteSelectField.this.iNumberInVector);
					}

					E2_MultiSelector_ActionAgent_4_Popup.this._clear_and_fill_grid();

				}
			});

		}
	}

	private class ownButtonHinzuFuegen extends MyE2_Button
	{
		public ownButtonHinzuFuegen()
		{
			super(E2_ResourceIcon.get_RI("multi_select_add_new.png"), true);
			this.setToolTipText(new MyE2_String("Weitere Auswahlmöglichkeit hinzufügen").CTrans());
			this.add_oActionAgent(new ownActionAddSelection());
		}
	}

	private class ownActionAddSelection extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_MultiSelector_ActionAgent_4_Popup oThis = E2_MultiSelector_ActionAgent_4_Popup.this;

			XX_ListSelector_4Multiselect neu_selektor = oThis.parent.generate_selector_part(null);

			oThis.parent.get_vectorOfSelectors().add(neu_selektor);

			oThis._clear_and_fill_grid();
		}
	}

	private class ownActionSaveListe extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException{

			E2_MultiSelector 						oThis 			= E2_MultiSelector_ActionAgent_4_Popup.this.parent;
			E2_MultiSelector_ActionAgent_4_Popup 	oThis2 			= E2_MultiSelector_ActionAgent_4_Popup.this;


			//first of the popup becomes the first of the selectors
			oThis.get_vectorOfSelectors().remove(0);

			//removing empty selectors
			for(Iterator<XX_ListSelector_4Multiselect> iter = oThis.get_vectorOfSelectors().iterator(); iter.hasNext(); ){	
				if(S.isEmpty(iter.next().get_WhereBlock())){
					iter.remove();
				}
			}

			//removing duplicates clauses
			if(oThis.get_vectorOfSelectors().size()>1){
				if(oThis.get_vectorOfSelectors().get(0).get_WhereBlock().equals(oThis.get_vectorOfSelectors().get(1).get_WhereBlock())){
					oThis.get_vectorOfSelectors().remove(1);
				}
			}
			//when no selector, then create a selector
			if(oThis.get_vectorOfSelectors().size()==0){
				oThis.get_vectorOfSelectors().add(oThis.generate_selector_part(null));
			}

			//affect to the first selector an action agent;
			oThis.get_vectorOfSelectors().get(0).add_ActionAgentToComponent(oThis.get_agent_4_selection());

			//then no selectors create a new selector
			if (oThis.get_vectorOfSelectors().size()==0)
			{
				oThis.get_vectorOfSelectors().add(oThis.generate_selector_part(null));
				oThis.get_vectorOfSelectors().get(0).add_ActionAgentToComponent(oThis.get_agent_4_selection());
				oThis.render_selector(false);
				oThis.get_button_4_multiSelect().set_StatusHasMultiSelect(0);
			}
			//then 1 selector, display only the selector
			else if (oThis.get_vectorOfSelectors().size()==1)
			{
				oThis.render_selector(false);
				oThis.get_button_4_multiSelect().set_StatusHasMultiSelect(0);
			}
			//then more than one selector, display multiple selector.
			else
			{
				oThis.render_selector(true);
				oThis.get_button_4_multiSelect().set_StatusHasMultiSelect(oThis.get_vectorOfSelectors().size());
			}



			oThis2.container_4_additionnal_values.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}
}
