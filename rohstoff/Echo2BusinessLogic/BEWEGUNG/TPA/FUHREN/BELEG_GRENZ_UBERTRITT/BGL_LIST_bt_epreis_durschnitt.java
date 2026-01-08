package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.VectorSingle;

public class BGL_LIST_bt_epreis_durschnitt extends E2_Button{

	private RB_ComponentMap compMap;
	private VEK<String> vIdVposTpFuhre; 

	public BGL_LIST_bt_epreis_durschnitt(BGL_LIST_popup_BelegGrenzUbertritt oPopUp) throws myException {
		super();

		this.compMap = oPopUp.get_componentMap();
		this.vIdVposTpFuhre = oPopUp.get_selected_ids();

		this._image("calc.png", "calc__.png");
		this._ttt("Durschnittsreis berechnen");
		this._aaa(()->e_preis_durschnitt_rechnen());
	}

	private MyE2_MessageVector e_preis_durschnitt_rechnen() throws myException {
		VectorSingle vl_idArtikel_bez = new VectorSingle();

		VEK<Long> vEpreis = new VEK<>();

		MyE2_MessageVector mv = new MyE2_MessageVector();

		Double durschnittPreis = 0.0;
		
		for(String id : this.vIdVposTpFuhre) {
			vl_idArtikel_bez.add(new Rec21(_TAB.vpos_tpa_fuhre)._fill_id(id).get_ufs_dbVal(VPOS_TPA_FUHRE.id_artikel_bez_ek));
		}

		for(String id_artikel_bez : vl_idArtikel_bez) {
			String artikel_in_fuhre_abfrage = new SEL().FROM(_TAB.vpos_tpa_fuhre)
					.WHERE(new vgl(VPOS_TPA_FUHRE.id_artikel_bez_ek, id_artikel_bez))
					.AND(new vgl(VPOS_TPA_FUHRE.id_artikel_bez_vk, id_artikel_bez))
					.AND(new vgl(VPOS_TPA_FUHRE.id_mandant, bibALL.get_ID_MANDANT()))
					.AND(new vgl(VPOS_TPA_FUHRE.einzelpreis_ek,COMP.GT, "0"))
					.AND(new vgl(VPOS_TPA_FUHRE.einzelpreis_vk,COMP.GT, "0"))
					.AND("rownum", "<", "10")
					.ORDERUP(VPOS_TPA_FUHRE.letzte_aenderung)
					.s();

			RecList21 fuhre_with_artikel = new RecList21(_TAB.vpos_tpa_fuhre)._fill(artikel_in_fuhre_abfrage);
			int i_max_size = 10;
			if(fuhre_with_artikel.size()>3) {
				if(fuhre_with_artikel.size()<10) {
					i_max_size = fuhre_with_artikel.size();
				}
				
				for(int i=0; i<i_max_size;i++) {
					vEpreis._a(fuhre_with_artikel.get(i).get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.einzelpreis_ek).get_longValue());
					vEpreis._a(fuhre_with_artikel.get(i).get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.einzelpreis_vk).get_longValue());
				}
				
			}
		}
		
		//durschnitt rechnung
		Integer sum = 0;
		if(!vEpreis.isEmpty() || vEpreis.size()>3) {
		    for (Long ePreis : vEpreis) {
		        sum += (ePreis.intValue());
		    }
		    durschnittPreis = (sum.doubleValue() / vEpreis.size());
		    ((RB_TextField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.E_PREIS.name())).setText(new MyBigDecimal(durschnittPreis,2).get_FormatedRoundedNumber(2));
		}else {
			mv._addAlarm("Zu wenige Einträge, ein Durchschnittspreis kann nicht berechnet werden !");
		}	
		bibMSG.add_MESSAGE(mv);
		return mv;
	}
}
