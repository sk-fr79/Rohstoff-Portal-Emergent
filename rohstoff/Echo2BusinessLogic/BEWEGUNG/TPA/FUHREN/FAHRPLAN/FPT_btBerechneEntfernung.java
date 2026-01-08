/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.GEO_FAHRPLAN.GEO_FAHR_Routing_popup;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.VectorSingle;

/**
 * @author martin
 * Button, um die entfernungsschnellberechnung zu starten
 */
public class FPT_btBerechneEntfernung extends E2_Button {

	private E2_NavigationList naviList;

	public FPT_btBerechneEntfernung(E2_NavigationList p_naviList) {
		super();
		this.naviList = p_naviList;
		this._ttt(new MyE2_String("Routen planen, Entfernung und Zeitprognose"));
		this.__setImages(E2_ResourceIcon.get_RI("routing.png"),E2_ResourceIcon.get_RI("routing__.png"));

		this._aaa( ()-> {call_entfernung_berechnung_tool();});

	}

	private void call_entfernung_berechnung_tool() throws myException{
		VEK<String> vFuhrenIds = new VEK<String>()._a(this.naviList.get_vectorSegmentation());

		VEK<Rec20> adresse_pool = new VEK<>();
		
		VectorSingle vSingleIds = new VectorSingle();
		for(String id_vpos_tpa_fuhre: vFuhrenIds) {
			Rec20 rec_fuhre = new Rec20(_TAB.vpos_tpa_fuhre)._fill_id(id_vpos_tpa_fuhre);
			
			String id_adresse_start = rec_fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_start);
			String id_adresse_lager_start = rec_fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_start);
			String id_adresse_ziel = rec_fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_ziel);
			String id_adresse_lager_ziel = rec_fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_ziel);

			if(! id_adresse_start.equals(id_adresse_lager_start)) {
				vSingleIds.add(rec_fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_start));
			}else {
				vSingleIds.add(rec_fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_start));
			}

			if(! id_adresse_ziel.equals(id_adresse_lager_ziel)) {
				vSingleIds.add(rec_fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_ziel));
			}else {
				vSingleIds.add(rec_fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_ziel));
			}
		}

		for(String extracted_id: vSingleIds) {
			adresse_pool._a(new Rec20(_TAB.adresse)._fill_id(extracted_id));
		}

		GEO_FAHR_Routing_popup popup = new GEO_FAHR_Routing_popup();
		popup
		._set_adress_pool(adresse_pool)
//		._set_destination(adresse_pool)
		.render_popup();	

		popup.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1350), new Extent(600), new MyE2_String("Fahrplan tool"));
	}

}
