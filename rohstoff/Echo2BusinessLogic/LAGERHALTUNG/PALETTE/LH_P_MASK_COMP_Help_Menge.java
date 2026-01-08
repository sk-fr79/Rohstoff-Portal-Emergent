/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE
 * @author sebastien
 * @date 23.01.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author sebastien
 * @date 23.01.2019
 *
 */
public class LH_P_MASK_COMP_Help_Menge extends E2_Button implements IF_RB_Component	 {
	private RB_TransportHashMap m_trp_hm;


	public LH_P_MASK_COMP_Help_Menge(RB_TransportHashMap p_trp_hm) {
		super();
		this.m_trp_hm = p_trp_hm;

		this._image("wizard.png")._ttt(S.ms("Nettomenge rechnen"));

		this._aaa(()->fill());

	}



	private void fill() throws myException {
		RB_ComponentMap maskCompMap = this.m_trp_hm.getMaskComponentMapCollector().get(LH_P_CONST.getLeadingMaskKey());

		MyLong brutto_mge = new MyLong(maskCompMap._find_component_in_neighborhood(LAGER_PALETTE.bruttomenge).get__actual_maskstring_in_view(),0l,0l);
		MyLong tara_mge = new MyLong(maskCompMap._find_component_in_neighborhood(LAGER_PALETTE.taramenge).get__actual_maskstring_in_view()) ;
		if(brutto_mge.isOK() && tara_mge.isOK() && brutto_mge.get_oLong()>0) {
			if(brutto_mge.get_oLong()<tara_mge.get_oLong()) {
				bibMSG.MV()._addWarn("ACHTUNG: die Taramenge ist groesser als die Bruttomenge");
			}
			long netto_mge =brutto_mge.get_oLong() - tara_mge.get_oLong();
			maskCompMap._find_component_in_neighborhood(LAGER_PALETTE.nettomenge).rb_set_db_value_manual(""+netto_mge);

		}else if(tara_mge.isNotOK()){ 
			bibMSG.MV()._addAlarm("FEHLER - Die Taramenge darf nicht leer sein !");
		}else if(brutto_mge.isNotOK()){ 
			bibMSG.MV()._addAlarm("FEHLER - Die Bruttomenge darf nicht leer sein !");
		}
		else{
			bibMSG.MV()._addAlarm("FEHLER - fe8c59a9-0179-4b77-a181-0590885ddea0");
		}

	}

	/*private void fill_with_fuhre_menge() throws myException{

		RB_ComponentMap maskCompMap = this.m_trp_hm.getMaskComponentMapCollector().get(LH_P_CONST.getLeadingMaskKey());
		RB_Dataobject doCollector = this.m_trp_hm.getMaskDataObjectsCollector().get(LH_P_CONST.getLeadingMaskKey());

		if(maskCompMap != null) {

			String id_fuhre = maskCompMap._find_component_in_neighborhood(LAGER_PALETTE.id_vpos_tpa_fuhre_ein).get__actual_maskstring_in_view();//, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, false);

			//			MyLong do_actual_bruttomenge = doCollector.rec21().get_myLong_dbVal(LAGER_PALETTE.bruttomenge, new MyLong(0));

			//			MyLong actual_bruttomenge = new MyLong(maskCompMap._find_component_in_neighborhood(LAGER_PALETTE.bruttomenge).get__actual_maskstring_in_view(),0l,0l);

			if(S.isFull(id_fuhre)) {
					Rec21 fuhre_rec = new Rec21(_TAB.vpos_tpa_fuhre)._fill_id(bibALL.convertID2UnformattedID(id_fuhre));

					MyLong anteil_ablademenge_lief = new MyLong(fuhre_rec.getUfs(VPOS_TPA_FUHRE.anteil_lademenge_lief, "0"));

					SEL query_sum_bruttomenge_palette = new SEL("SUM("+LAGER_PALETTE.bruttomenge+")").FROM(_TAB.lager_palette)
							.WHERE(new vgl(LAGER_PALETTE.id_vpos_tpa_fuhre_ein, id_fuhre))
							.AND(new VglNull(LAGER_PALETTE.id_vpos_tpa_fuhre_aus));

					if(doCollector.rb_MASK_STATUS() == MASK_STATUS.EDIT) {
						query_sum_bruttomenge_palette.AND(new vgl(LAGER_PALETTE.id_lager_palette, COMP.NOT_EQ, maskCompMap._find_component_in_neighborhood(LAGER_PALETTE.id_lager_palette).get__actual_maskstring_in_view()));
					}

					MyLong l_gesamt_aktuel_mge = new MyLong(bibDB.EinzelAbfrage(query_sum_bruttomenge_palette.s()));

					if(anteil_ablademenge_lief.get_oLong()>0) {
						long menge_diff = anteil_ablademenge_lief.get_oLong() - (l_gesamt_aktuel_mge.get_oLong());
						if(menge_diff>0) {
							maskCompMap._find_component_in_neighborhood(LAGER_PALETTE.bruttomenge).rb_set_db_value_manual(""+menge_diff);
						}else {
							bibMSG.MV()._addAlarm("Die fuhre Menge ist überschritten");
						}
					}

				}else {
					bibMSG.MV()._addAlarm("Bitte wählen sie eine Fuhre");
				}

			}
		}*/

}
