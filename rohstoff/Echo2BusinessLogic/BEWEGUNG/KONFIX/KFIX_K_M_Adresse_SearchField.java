package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.HIGHLEVEL.E2_BtEditAdress;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SearchMainAdress;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class KFIX_K_M_Adresse_SearchField extends RB_HL_SearchMainAdress {

	private VORGANGSART vorgangsart = null;    //EK_KONTRAKT  , VK_KONTRAKT
	
	public KFIX_K_M_Adresse_SearchField(VORGANGSART p_vorgangsart) throws myException {
		super(true);

		this.vorgangsart=p_vorgangsart;
		
		int[] iBreite = {120,25,25,25,400};
		
		this.get_buttonStartSearch().setDisabledIcon(KFIX___CONST.IKON.EMPTY.getIkon());
		this.get_buttonErase().setDisabledIcon(KFIX___CONST.IKON.EMPTY.getIkon());
		
		this._clear()	._setSize(iBreite)._gld(new RB_gld()._left_top())
						._a_lt(this.get_tf_search_input()._w(iBreite[0]))
						._a_ct(this.get_buttonStartSearch())
						._a_ct(this.get_buttonErase())
						._a_ct(new own_Edit_Adress())
						._gld(new RB_gld()._span(3))
						._a(this.get_gridContainer_to_show_searchResult(), new RB_gld()._ins(10,2,0,0))
						;
		
		if (this.vorgangsart==VORGANGSART.EK_KONTRAKT) {
			this.and_statement_collector_4_basic().and(new vgl_YN(ADRESSE.wareneingang_sperren, false));
		} else {
			this.and_statement_collector_4_basic().and(new vgl_YN(ADRESSE.warenausgang_sperren, false));
		}
		
	}
	
	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownModulContainer();
	}


	
	private class ownModulContainer extends E2_BasicModuleContainer {
		
	}
	
	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		return ((RECORD_ADRESSE)p_result_record).get_ID_ADRESSE_cUF();
	}

	
	@Override
	public void render_search_result_visible_on_mask(E2_Grid gridcontainer_4_search_results, String c_result_value_4_db) throws myException {
		
		gridcontainer_4_search_results.removeAll();
		RB_lab lab_help = null;
		if (S.isEmpty(c_result_value_4_db)) {
			lab_help=new RB_lab(" ");
		} else {
			
			RECORD_ADRESSE_extend  recAdresse = new RECORD_ADRESSE_extend(bibALL.convertID2UnformattedID(c_result_value_4_db));
			String label_txt = recAdresse.get__FullNameAndAdress_Typ2();
			lab_help=new RB_lab(label_txt)._i()._fsa(-2);	
			lab_help._line_wrap(true);
			
		}
		gridcontainer_4_search_results._a_lm(lab_help);	
	}



	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String unformated_MaskValue, boolean aktiv) throws myException {
		
		KFIX_K_M_Controller oControllerMap = new KFIX_K_M_Controller(this._find_componentMapCollector_i_belong_to());
		oControllerMap.do_mask_settings(this, unformated_MaskValue, true);
		

		return null;
	}

//	@Override
//	public MyRECORD_IF_RECORDS get_result_record_from_string(String id_adresse) throws myException {
//		return new RECORD_ADRESSE(id_adresse);
//	}

	
	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.get_tf_search_input().setText(valueFormated);
		this.render_search_result_visible_on_mask(this.get_gridContainer_to_show_searchResult(), valueFormated);
		this.set_c_vergleichswert_dbfeld(valueFormated);
	}

	public String getIdAdresse() {
	
		return bibALL.convertID2UnformattedID(this.get_tf_search_input().getText());
	}

	
	
	
	private class own_Edit_Adress extends E2_BtEditAdress {

		public own_Edit_Adress() throws myException {
			super();
			this._ttt(S.mt("Adresse bearbeiten"));
		}

		@Override
		public MyLong find_id_adress() throws myException {
			String id_adresse = bibALL.convertID2FormattedID(KFIX_K_M_Adresse_SearchField.this.rb_readValue_4_dataobject() );
			return new MyLong(id_adresse);
		}
		
		
	}

}
