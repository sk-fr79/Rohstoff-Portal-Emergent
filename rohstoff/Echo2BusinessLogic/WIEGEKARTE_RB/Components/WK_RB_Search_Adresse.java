/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.Components
 * @author manfred
 * @date 23.03.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.text.TextComponent;
import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.BasicInterfaces.IF_HasChangeListeners;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimple;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_LabNoDatabase;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField_actionStartSearch;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SearchAdressStation21;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author manfred
 * @date 23.03.2020
 *
 */
public class WK_RB_Search_Adresse extends RB_HL_SearchAdressStation21 implements IF_HasChangeListeners<WK_RB_Search_Adresse>{

	private static final int _len_name_field = 400;
	private static final int _len_search_field = 100;
	
	
	/**
	 * @author manfred
	 * @date 23.03.2020
	 *
	 * @throws myException
	 */
	public WK_RB_Search_Adresse() throws myException {
		super();
		
		this._setExcludeOwnAdresses(false)
			._setOnlyHauptAdressen(false)
			._setShowEraser(true)
			._allowEmptySearchfield(false)
			;
		
		RB_gld gl1 = new RB_gld()._ins(0,0,4,2)._left_mid();
		RB_gld gl2 = new RB_gld()._ins(0,0,4,2)._left_top();

		this.set_width_popup_window(new Extent(1000));
		this.set_height_popup_window(new Extent(800));
		
		
		this._clear()
			._a(this.get_gridContainer_to_show_searchResult()._bo_dd(), gl1._c()) //._ins(0,0,0,2))
			._a(this.get_tf_search_input(), gl1._c()._ins(2,0,4,2))
			._a(this.get_buttonErase(), gl1._c()._ins(0, 2, 2, 0))
			._a(this.get_buttonStartSearch(), gl1._c()._ins(0, 2, 2, 0))
			._setSize(_len_name_field,_len_search_field,20,20)
			;


		this.get_tf_search_input()._w(_len_search_field)._align(Alignment.ALIGN_LEFT);
		
		this.get_tf_search_input().focus_on();
		this.get_buttonStartSearch().focus_on();
		this.get_buttonErase().focus_on();
		this.get_button_to_open_mask_to_referenced_record().setVisible(false);
		this.get_button_to_open_mask_to_referenced_record().focus_off();
		
//		this.get_buttonStartSearch()._aaa(new ActionOnChangeListeners());
		this.get_buttonErase()._aaa(new ActionOnChangeListeners());
		
		this._aaaAfterChange(new ActionOnChangeListeners());
		
	}

	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownModulContainer();
	}
	
	private class ownModulContainer extends E2_BasicModuleContainer {
	}
	


	@Override
	public XX_ActionAgent generate_StartSearchAction() throws myException {
		RB_SearchField_actionStartSearch actionStartSearch = new RB_SearchField_actionStartSearch(this);
		// Dialog auch zeigen, wen nur eine Zeile gefunden wurde...
		actionStartSearch.set_showDialogOnSingleResult(true);
		return actionStartSearch;
//		return new RB_SearchField_actionStartSearch(this);
		
	}


	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		return ((Rec21)p_result_record).getUfs(ADRESSE.id_adresse);
	}

	
	@Override
	public void render_search_result_visible_on_mask(E2_Grid gridResults, String c_result_value_4_db) throws myException {

		gridResults._clear()._setSize(20,_len_name_field);
		MyLong ml = new MyLong(c_result_value_4_db);

		if (ml.isNotOK()) {
			LayoutData ld = gridResults.getLayoutData();
			if (ld !=null && ld instanceof GridLayoutData) {
				RB_gld ldNew =  new RB_gld()._copyLayoutData((GridLayoutData)ld)._col_back(new E2_ColorBase());
				gridResults.setLayoutData(ldNew);
				
				TextField emptyField = new TextField();
				emptyField.setWidth(new Extent(_len_name_field - 25));
				emptyField.setEnabled(false);
				
				MutableStyle oStyle = new MutableStyle();
				oStyle.setProperty( TextComponent.PROPERTY_BACKGROUND, new E2_ColorBase(0));
				oStyle.setProperty( TextComponent.PROPERTY_BORDER, new Border(0, new E2_ColorDDark(), Border.STYLE_NONE)); 

				emptyField.setStyle(oStyle);
				
				gridResults	._a(this.get_button_to_open_mask_to_referenced_record(), new RB_gld()._left_mid());
				gridResults._a(emptyField, new RB_gld()._left_mid()._ins(0, 0, 0, 0))
				;
	
				
			}
			return;
		}
			
		String sAdresse = "?";
		Rec21_adresse  recAdresse = new Rec21_adresse()._fill_id(ml.getLong());
		Rec21_adresse  recMain = recAdresse._getMainAdresse();

		sAdresse = recAdresse.get__Name1Name2StrassePlzOrt(", ");
		
		try {
			if (recMain != null && recMain.getId() != recAdresse.getId()) {
				sAdresse +=  " ( " + recMain.get__FullNameAndAdress_Typ2() + ")";
			}
		} catch (Exception e) {
			// falls recMain irgend ein Problem hat...
		}
				
		gridResults	._a(this.get_button_to_open_mask_to_referenced_record(), new RB_gld()._left_mid())
					._a(new RB_LabNoDatabase(sAdresse)._fsa(_mFontSizeResultAdd), new RB_gld()._left_mid())
					;
		
	}
	
	
	private int _mFontSizeResultAdd = 0;
	
	/**
	 * die Größenänderung in Pixel des Fonts des Ergebnis in der Maske
	 * @author manfred
	 * @date 25.03.2020
	 *
	 * @param additionalFontSize
	 * @return
	 */
	public WK_RB_Search_Adresse _setFontSizeAdd(int additionalFontSize) {
		_mFontSizeResultAdd = additionalFontSize;
		return this;
	}
	
	
	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String idAdresse, boolean aktiv)
			throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		return mv;
	}


	private VEK<IF_ExecuterOnComponentV2<WK_RB_Search_Adresse>>    changeListeners = new   VEK<>();
	
	@Override
	public WK_RB_Search_Adresse _clearChangeListener() {
		changeListeners._clear();
		return this;
	}

	@Override
	public WK_RB_Search_Adresse _addChangeListener(IF_ExecuterOnComponentV2<WK_RB_Search_Adresse> changeListener) {
		changeListeners._a(changeListener);		
		return this;
	}
	
	
	
	
	
	private class ActionOnChangeListeners extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			for (IF_ExecuterOnComponentV2<WK_RB_Search_Adresse> executer: changeListeners) {
				mv._add(executer.execute(WK_RB_Search_Adresse.this));
			}
			bibMSG.MV()._add(mv);
		}
	}
	
	
	/**
	 * gibt die ID der Adresse zurück
	 * @author manfred
	 * @date 16.07.2020
	 *
	 * @return
	 * @throws myException
	 */
	public String _getIDAdresse() throws myException {
		return rb_readValue_4_dataobject();
	}
	
}
