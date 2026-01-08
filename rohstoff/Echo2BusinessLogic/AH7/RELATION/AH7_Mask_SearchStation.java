/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SearchAdressStation;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

/**
 * @author martin
 * Suchfeld fuer die station, ist aktiv und fuellt dann die passiven hauptadressen
 */
public class AH7_Mask_SearchStation extends RB_HL_SearchAdressStation {

	/**
	 * @param bShowEraser
	 * @param exclude_own_adress
	 * @throws myException
	 */
	public AH7_Mask_SearchStation() throws myException {
		super(true,false);
		this.get_tf_search_input()._width(100);
		RB_gld gl = new RB_gld()._ins(2,2,2,2)._left_top();

		this._clear()
				._a(this.get_gridContainer_to_show_searchResult()._width(450), gl)
				._a(this.get_tf_search_input(), gl)
				._a(this.get_buttonErase(), gl)
				._a(this.get_buttonStartSearch(), gl)
				._setSize(450,100,20,20);

		
	}

	@Override
	public void render_search_result_visible_on_mask(E2_Grid gridcontainer_4_search_results, String c_result_value_4_db) throws myException {
		
		gridcontainer_4_search_results.removeAll();
		MyE2_Label lab_help = null;
		if (S.isEmpty(c_result_value_4_db)) {
			lab_help=new MyE2_Label(" ");
		} else {
			RECORD_ADRESSE_extend  recAdresse = new RECORD_ADRESSE_extend(c_result_value_4_db);
			String label_txt = recAdresse.get__FullNameAndAdress_Typ2()+" ("+new MyE2_String("Hauptadresse").CTrans()+")";
			if (!recAdresse.is_main_adress()) {
				RECORD_ADRESSE_extend rh = recAdresse.get_main_Adress();
				label_txt = recAdresse.get__FullNameAndAdress_Typ2()+" ("+new MyE2_String("bei ").CTrans()+rh.get__FullNameAndAdress_Typ2()+")";
			}
			
			
			lab_help=new MyE2_Label(label_txt, new E2_FontItalic(-2),true);				
		}
		gridcontainer_4_search_results.add(lab_help);
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField#generate_container_4_popup_window()
	 */
	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownContainer();
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField#do_mask_settings_after_search(java.lang.String, boolean)
	 */
	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String unformated_MaskValue, boolean aktiv) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		RB_MaskController c = new RB_MaskController(this.rb_get_belongs_to());
		
		IF_Field maskField = _TAB.find_field(_TAB.ah7_steuerdatei, this.rb_KF().FIELDNAME());
		MyLong actualAdressId = new MyLong(c.get_liveVal(maskField));

		if (actualAdressId.isOK()) {
			Rec20_adresse lager = new Rec20_adresse(new Rec20(_TAB.adresse)._fill_id(actualAdressId.get_lValue()));
			if (maskField == AH7_STEUERDATEI.id_adresse_geo_start) {
				c.set_maskVal(AH7_STEUERDATEI.id_adresse_jur_start, lager.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse), mv);
			} else if (maskField == AH7_STEUERDATEI.id_adresse_geo_ziel) {
				c.set_maskVal(AH7_STEUERDATEI.id_adresse_jur_ziel, lager.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse), mv);
			} else {
				mv.add_MESSAGE(new MyE2_Alarm_Message(S.ms("Only allowed with geo-adresses !!")));
			}
		}
		
		
		//falls der wert geaendert wurde, die dropdowns neu besetzen und deren werte leermachen
		MyLong oldVal = O.NN(c.get_MyLong_dbVal(maskField), new MyLong(""));
		MyLong newVal = new MyLong(unformated_MaskValue);
		
		boolean cleanFields = true;
		
		if (oldVal.isOK() && newVal.isOK()) {
			if (oldVal.get_lValue()==newVal.get_lValue()) {
				cleanFields=false;
			}
		}
		
		if (cleanFields) {
			new AH7__ActualizeDropDownsInMask(this.rb_ComponentMap_this_belongsTo(), mv, false);
			((RB_cb)c.get_comp(AH7_STEUERDATEI.drucke_blatt2, mv)).setSelected(false);
			((RB_cb)c.get_comp(AH7_STEUERDATEI.drucke_blatt2, mv)).doActionPassiv();
			((RB_cb)c.get_comp(AH7_STEUERDATEI.drucke_blatt3, mv)).setSelected(false);
			((RB_cb)c.get_comp(AH7_STEUERDATEI.drucke_blatt3, mv)).doActionPassiv();
		}
		
		return mv;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField#get_result_string_from_record(panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS)
	 */
	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		return ((RECORD_ADRESSE)p_result_record).get_ID_ADRESSE_cUF();
	}

	private class ownContainer extends E2_BasicModuleContainer {
	}
	
}
