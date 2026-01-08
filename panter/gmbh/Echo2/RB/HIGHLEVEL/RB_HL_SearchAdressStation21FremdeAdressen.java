/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 13.03.2019
 * 
 */
package panter.gmbh.Echo2.RB.HIGHLEVEL;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField_actionStartSearch;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 * @date 13.03.2019
 *
 */
public class RB_HL_SearchAdressStation21FremdeAdressen extends RB_HL_SearchAdressStation21 {
	
	
	public RB_HL_SearchAdressStation21FremdeAdressen() throws myException {
		super();
		
		this._setExcludeOwnAdresses(true)
			._setOnlyHauptAdressen(false)
			._setShowEraser(true)
			._allowEmptySearchfield(false)
			;
		
		RB_gld gl1 = new RB_gld()._ins(0,0,2,0)._left_top();
		RB_gld gl2 = new RB_gld()._ins(0,0,0,2)._left_top();

		this.set_width_popup_window(new Extent(1000));
		this.set_height_popup_window(new Extent(800));
		
		
		this._clear()._setSize(100,20,20)
				._a(this.get_tf_search_input(), gl1)
				._a(this.get_buttonErase(), gl1)
				._a(this.get_buttonStartSearch(), gl1)
				._a(this.get_gridContainer_to_show_searchResult(), gl2._c()._span(3));

		this.get_tf_search_input()._w(100);

		this.get_tf_search_input().focus_on();
		this.get_buttonStartSearch().focus_on();
		this.get_buttonErase().focus_on();
		this.get_button_to_open_mask_to_referenced_record().focus_off();
		
	}

	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownModulContainer();
	}

	private class ownModulContainer extends E2_BasicModuleContainer {
	}



	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String id_adresse, boolean aktiv) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		return mv;
	}


	public  XX_ActionAgent  generate_StartSearchAction() throws myException {
		return new RB_SearchField_actionStartSearch(this);
	}



	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		return ((Rec21)p_result_record).getUfs(ADRESSE.id_adresse);
	}



	@Override
	public void render_search_result_visible_on_mask(E2_Grid gridResults, String c_result_value_4_db) throws myException {

		gridResults._clear()._setSize(20,130);
		MyLong ml = new MyLong(c_result_value_4_db);

		if (ml.isNotOK()) {
			LayoutData ld = gridResults.getLayoutData();
			if (ld !=null && ld instanceof GridLayoutData) {
				RB_gld ldNew =  new RB_gld()._copyLayoutData((GridLayoutData)ld)._col_back(new E2_ColorBase());
				gridResults.setLayoutData(ldNew);
			}
			return;
		}
			
		Rec21_adresse  recAdresse = new Rec21_adresse()._fill_id(ml.getLong());
		Rec21_adresse  recAdresseMain = recAdresse._getMainAdresse();
		
		

		gridResults	._a(this.get_button_to_open_mask_to_referenced_record())
					._a(new RB_lab(recAdresse.get__FullNameAndAdress_Typ2())._fsa(-2))
					;
		if (recAdresse.getId()==recAdresseMain.getId()) {
			gridResults	._a()._a(new RB_lab(S.ms("(Hauptadresse)"))._fsa(-2), new RB_gld()._ins(0,0,0,0));
		} else {
			gridResults	._a()._a(new RB_lab(S.ms("Lieferadresse von:  ").ut(recAdresseMain.get__FullNameAndAdress_mit_id()))._fsa(-2), new RB_gld()._ins(0,0,0,0));
		}
		
	}

}
