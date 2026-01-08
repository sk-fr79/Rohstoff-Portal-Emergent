package panter.gmbh.Echo2.RB.COMP.SearchField;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_SearchField_AbstractActionStartSearch extends XX_ActionAgent {

	private RB_SearchField  rb_searchfield_this_belongs_to = null;
	private String          lastSearchTextInput = null;

	// wenn true, wird der Ergebnis-Dialog auch angezeigt, wenn nur eine Zeile gefunden wurde, um die Auswahl zu bestätigen
	private boolean         _showDialogOnSingleResult = false;
	
	
	protected abstract MyE2_MessageVector  fillResultButtonArray() throws myException;
	
	public RB_SearchField_AbstractActionStartSearch(RB_SearchField p_rb_searchfield_this_belongs_to) {
		super();
		this.rb_searchfield_this_belongs_to = p_rb_searchfield_this_belongs_to;
	}


	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		
//		if (!this.rb_searchfield_this_belongs_to.is_allow_empty_searchfield()) {
//			if (S.isEmpty(this.rb_searchfield_this_belongs_to.get_tf_search_input().getText())) {
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte geben Sie Suchbegriffe ein !")));
//				return;
//			}
//		}

		String c_text_4_search = S.NN(this.rb_searchfield_this_belongs_to.get_tf_search_input().getText());
		
		/*
		 * wenn der text ohne punkt eine integer ist, dann den punkt rauswerfen, damit auch formatierte IDs gefunden werden
		 */
		String cTextTest = bibALL.ReplaceTeilString(c_text_4_search,".","");
		if (bibALL.isInteger(cTextTest)) {
			c_text_4_search=cTextTest;
		}
				
		try	{
			
			this.lastSearchTextInput = c_text_4_search;
			
			
			this.rb_searchfield_this_belongs_to.get_rb_ResultButtonArray().clear();
			// bibMSG.add_MESSAGE(this.rb_searchfield_this_belongs_to.execute_searchquery_and_fill_resultbutton_array(c_text_4_search));
			bibMSG.add_MESSAGE(this.fillResultButtonArray());

			if (bibMSG.get_bIsOK()) {
			
				//evtl. vorhandene gespeicherte sortierung laden
				if (this.rb_searchfield_this_belongs_to.get_key_4_save_sorting()!=null) {
					new RB_SaveSortOfPopup(this.rb_searchfield_this_belongs_to).RESTORE();
				}
				
				
				//vorsortierung falls werte gesetzt sind
				this.rb_searchfield_this_belongs_to.get_rb_ResultButtonArray().sort();
				
				if (this.rb_searchfield_this_belongs_to.get_rb_ResultButtonArray().size()==0) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Nichts gefunden ..."),false);
					bibMSG.add_MESSAGE(this.rb_searchfield_this_belongs_to.do_mask_settings_after_search("",true));
				} else if (this.rb_searchfield_this_belongs_to.get_rb_ResultButtonArray().size()==1 && !_showDialogOnSingleResult ) {
					((E2_IF_Handles_ActionAgents)this.rb_searchfield_this_belongs_to.get_rb_ResultButtonArray().get(0)[0]).doActionPassiv();				
					bibMSG.add_MESSAGE(new MyE2_Warning_Message("Genau ein Datensatz gefunden und geladen !"),false);
				} else {
					//popup aufbauen	
					E2_BasicModuleContainer  container = this.rb_searchfield_this_belongs_to.get_container_4_popupWindow();
	
					this.rb_searchfield_this_belongs_to.get_grid_container_4_popupWindow().removeAll();;
					this.rb_searchfield_this_belongs_to.fill_grid_4_popup(	this.rb_searchfield_this_belongs_to.get_grid_container_4_popupWindow(),
																			this.rb_searchfield_this_belongs_to.get_rb_ResultButtonArray(), 
																			oExecInfo);
					container.RESET_Content();
					container.add(this.rb_searchfield_this_belongs_to.get_grid_container_4_popupWindow(), E2_INSETS.I(4,4,4,4));
					
					
					container.CREATE_AND_SHOW_POPUPWINDOW(	this.rb_searchfield_this_belongs_to.get_width_popup_window(), 
															this.rb_searchfield_this_belongs_to.get_height_popup_window(),
															this.rb_searchfield_this_belongs_to.get_title_of_popup());
	
					container.save_focusable_components_outside(rb_searchfield_this_belongs_to.get_tf_search_input());
					
					if (this.rb_searchfield_this_belongs_to.isFirstColInResultPopupFocusAble()) {
						this.rb_searchfield_this_belongs_to.get_rb_ResultButtonArray().set_1st_column_focusable();
					}
					MyE2IF__Component  focusable = this.rb_searchfield_this_belongs_to.get_rb_ResultButtonArray().get_focus_component();
					bibALL.setFocus((Component) focusable);
					
				}
			}
		} catch (myException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new myException(ex.getLocalizedMessage());
		}
			

		
	}
	
	public String getLastSearchTextInput() {
		return lastSearchTextInput;
	}


	public void setLastSearchTextInput(String lastSearchTextInput) {
		this.lastSearchTextInput = lastSearchTextInput;
	}

	public RB_SearchField getRb_searchfield_this_belongs_to() {
		return rb_searchfield_this_belongs_to;
	}

	public boolean is_showDialogOnSingleResult() {
		return _showDialogOnSingleResult;
	}

	public void set_showDialogOnSingleResult(boolean _showDialogOnSingleResult) {
		this._showDialogOnSingleResult = _showDialogOnSingleResult;
	}

}
