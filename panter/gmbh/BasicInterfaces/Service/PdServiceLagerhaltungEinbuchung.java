/**
 * panter.gmbh.BasicInterfaces.Service
 * @author sebastien
 * @date 20.12.2018
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.util.GregorianCalendar;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__TOOLS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE_BOX;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE.LH_P_CONST;

/**
 * @author sebastien
 * @date 20.12.2018
 *
 */
public class PdServiceLagerhaltungEinbuchung {

	public PdServiceLagerhaltungEinbuchung _einbuchung(RB_TransportHashMap m_trp_hm, MyE2_MessageVector mv) throws myException{
		boolean is_lagerhaltung = false;
		RB_KM maskKey 	= null;
		RB_KF boxFieldKey 	= null;
		RB_KF paletteIdFieldKey = null;
		
		if(m_trp_hm.getLeadingMaskKey().get_db_table() ==  _TAB.lager_palette) {
			is_lagerhaltung 	= true;
			maskKey 			= LH_P_CONST.getLeadingMaskKey();
			boxFieldKey			= LAGER_PALETTE.id_lager_box.fk();
			paletteIdFieldKey	= LAGER_PALETTE.id_lager_palette.fk();
		}
				

		if(is_lagerhaltung) {
			RB_DataobjectsCollector_V2 do_collector = (RB_DataobjectsCollector_V2) m_trp_hm.getMaskDataObjectsCollector();

			RB_Dataobject_V2 palette_do = (RB_Dataobject_V2) do_collector.get(maskKey);

			RB_ComponentMap compMap = m_trp_hm.getMaskComponentMapCollector().get(maskKey);

			String lager_box_id = RB__TOOLS.find_comp(compMap, boxFieldKey).get__actual_maskstring_in_view();

			if(S.isFull(lager_box_id)) {

				if(palette_do.rb_MASK_STATUS() == MASK_STATUS.NEW || palette_do.rb_MASK_STATUS() == MASK_STATUS.NEW_COPY) {

					Rec21  rec_palbox = new Rec21(_TAB.lager_palette_box)
							._nv(LAGER_PALETTE_BOX.id_lager_box, lager_box_id, mv);
					
					rec_palbox._setNewVal(LAGER_PALETTE_BOX.einbuchung_am, new GregorianCalendar().getTime(), mv);

					rec_palbox._put_raw_value(LAGER_PALETTE_BOX.id_lager_palette, _TAB.lager_palette.seq_currval(), false);

					do_collector._addSqlAtEnd(rec_palbox.get_sql_4_save(true, mv));

				}else if(palette_do.rb_MASK_STATUS() == MASK_STATUS.EDIT && S.isEmpty(palette_do.rec21().getUfs(LAGER_PALETTE.id_lager_box))) {

					String palette_id = RB__TOOLS.find_comp(compMap, paletteIdFieldKey).get__actual_maskstring_in_view();

					String q = new SEL().FROM(_TAB.lager_palette_box).WHERE(new vgl(LAGER_PALETTE_BOX.id_lager_palette,palette_id)).s();
					q = q + " AND " + LAGER_PALETTE_BOX.ausbuchung_am.tnfn() + " IS NULL";

					
					//18.10.2019@sebastien: wenn palette ist durch eine Fuhre eingebucht dann buchungsnr_hand muss leer sein.
					boolean is_hand_einbuchung = RB__TOOLS.find_comp(compMap, LAGER_PALETTE.einbuchung_hand.fk()).get__actual_maskstring_in_view().equals("Y");
					if(! is_hand_einbuchung) {
						palette_do.rec21().nv(LAGER_PALETTE.buchungsnr_hand, "", mv);
					}

					Rec21  rec_palbox = new Rec21(_TAB.lager_palette_box)._fill_sql(q)
							._nv(LAGER_PALETTE_BOX.id_lager_box, lager_box_id, mv)
							._nv(LAGER_PALETTE_BOX.id_lager_palette, palette_id, mv);
					
					rec_palbox._setNewVal(LAGER_PALETTE_BOX.einbuchung_am,  new GregorianCalendar().getTime(), mv);
					
					
					do_collector._addSqlAtEnd(rec_palbox.get_sql_4_save(true, mv));
				}
			}
		}else {
			mv._addAlarm("Systemfehler:  3cfd2012-cb2d-11e9-a32f-2a2ae2dbcce4");
		}
		return this;
	}
}
