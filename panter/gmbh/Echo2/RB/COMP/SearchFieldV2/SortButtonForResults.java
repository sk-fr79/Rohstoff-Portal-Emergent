package panter.gmbh.Echo2.RB.COMP.SearchFieldV2;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.exceptions.myException;


public class SortButtonForResults extends E2_Grid {
	
	private int    					columnNrSort = 	-1;



	private MyE2_String         	text = 					null;
	private E2_Button    	     	button_to_sort = 			null;
	private EnSortStatusButtonGrid  sortstatus_actual =         EnSortStatusButtonGrid.NEUTRAL;
	
	
	public EnSortStatusButtonGrid getSortstatus_actual() {
		return sortstatus_actual;
	}




	private RB_SearchFieldV2  		searchfield = null;
	
	
	/**
	 * 
	 * @param p_calling_searchField
	 * @param p_i_colum_of_this_button
	 * @param p_text
	 * @param style_button
	 */
	public SortButtonForResults(RB_SearchFieldV2 p_searchField, int column, MyE2_String p_text, E2_MutableStyle  style) {
		super();
		this._nB();
		
		this.searchfield = p_searchField;
		
		this.columnNrSort = column;
		this.text = p_text;
		
		this.button_to_sort = new E2_Button()._t(text)._style(style);
		
		this.button_to_sort.add_oActionAgent(new ownActionSort());
		
		this.button_to_sort.setFocusTraversalParticipant(false);
		
		this.setSize(2);
		this._init();
	}
	
	
	private void _init() {
		this.removeAll();
		this._w100();
//		this._bo_red();
		this._a(this.button_to_sort, new RB_gld()._ins(0));
		this._a(new RB_lab()._icon(this.sortstatus_actual.icon()),new RB_gld()._ins(2,0,0,0));
	}
	
	
	
	private class ownActionSort extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SortButtonForResults oThis = SortButtonForResults.this;

			//den naechsten eigenen status feststellen
			EnSortStatusButtonGrid myOwnStatus = oThis.sortstatus_actual.get_next();
			
			//alle neutralisieren
			for (SortButtonForResults  sortButton: searchfield.getSortButtonsForResults()) {
				sortButton.set_sortstatus_actual(EnSortStatusButtonGrid.NEUTRAL);
			}

			//dan den eigenen richtig setzen und dem resultButtos - vector die spalte und den status mitteilen
			searchfield.getResultButtons().setActualSortCol(oThis.columnNrSort);
			searchfield.getResultButtons().setActualSortStatus(myOwnStatus);
			oThis.set_sortstatus_actual(myOwnStatus);
					
			searchfield.getResultButtons().sort();
			searchfield.renderGrid4ResultPopup();
			
			searchfield._saveActualStatus();
			//status speichern
			
		}
	}

	

	public void set_sortstatus_actual(EnSortStatusButtonGrid p_sortstatus_actual) {
		this.sortstatus_actual = p_sortstatus_actual;
		this._init();
	}
	


	public int getColumnNrSort() {
		return columnNrSort;
	}

	
}
