/**
 * panter.gmbh.BasicInterfaces.Service
 * @author sebastien
 * @date 20.12.2018
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.util.Date;
import java.util.GregorianCalendar;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__TOOLS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE_BOX;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.LH_CONST;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE.LH_P_CONST;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_LagerPalette;

/**
 * @author sebastien
 * @date 20.12.2018
 *
 */
public class PdServiceLagerhaltungAusbuchung {

	public PdServiceLagerhaltungAusbuchung _ausbuchung_mit_fuhre(VEK<String> vIdpaletten, String ausbuchungsfuhre_id, String ausbuchungsdatum,  MyE2_MessageVector mv) throws myException{

		if(S.isAllFull(ausbuchungsfuhre_id, ausbuchungsdatum) && vIdpaletten.size()>0) {
			
			for(String id_palette: vIdpaletten) {
				
				Rec21 rec_palette = new Rec21(_TAB.lager_palette)._fill_id(bibALL.convertID2UnformattedID(id_palette));

				Rec21 rec_palette_box = new Rec21(_TAB.lager_palette_box)._fill_sql(
						new SEL().FROM(_TAB.lager_palette_box).WHERE(new vgl(LAGER_PALETTE_BOX.id_lager_palette, id_palette))
						.AND(new VglNull(LAGER_PALETTE_BOX.ausbuchung_am)).s());

				rec_palette_box._nv(LAGER_PALETTE_BOX.ausbuchung_am, ausbuchungsdatum, mv);
				
				
				rec_palette._nv(LAGER_PALETTE.id_vpos_tpa_fuhre_aus, ausbuchungsfuhre_id, mv);

				if(mv.get_bIsOK()) {
					rec_palette_box._SAVE(true, mv);
					rec_palette._SAVE(true, mv);
				}
			}
		}else {

			mv._addAlarm("FEHLER :3332e802-ccf4-4b39-9f5f-7e2fff3af14c: Fehler bei Ausbuchung");

		}

		return this;
	}

	/**
	 * Ausbuchung hand
	 * @author sebastien
	 * @date 22.10.2019
	 *
	 * @param vIdpaletten
	 * @param ausbuchungsdatum
	 * @param bemerkung 
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public PdServiceLagerhaltungAusbuchung _ausbuchung_von_hand(VEK<String> vIdpaletten, String ausbuchungsdatum,  String bemerkung, MyE2_MessageVector mv) throws myException{

		if(vIdpaletten.size()>0) {
			
			for(String id_palette: vIdpaletten) {
				
				Rec21 rec_palette = new Rec21(_TAB.lager_palette)._fill_id(bibALL.convertID2UnformattedID(id_palette));

				Rec21 rec_palette_box = new Rec21(_TAB.lager_palette_box)._fill_sql(
						new SEL()
						.FROM(_TAB.lager_palette_box)
						.WHERE(new vgl(LAGER_PALETTE_BOX.id_lager_palette, id_palette))
						.AND(new VglNull(LAGER_PALETTE_BOX.ausbuchung_am))
						.s());

				if(S.isFull(ausbuchungsdatum)) {
					rec_palette_box._nv(LAGER_PALETTE_BOX.ausbuchung_am, ausbuchungsdatum, mv);
				}else {
					rec_palette_box._setNewVal(LAGER_PALETTE_BOX.einbuchung_am, new GregorianCalendar().getTime(), mv);
				}
				
				rec_palette._nv(LAGER_PALETTE.ausbuchung_hand, "Y", mv);

				if(S.isFull(bemerkung)) {
					rec_palette._nv(LAGER_PALETTE.hand_ausbuchung_bemerkung, bemerkung,mv);
				}

				if(mv.get_bIsOK()) {
					rec_palette_box._SAVE(true, mv);
					rec_palette._SAVE(true, mv);
				}
			}
		}else {

			mv._addAlarm("FEHLER :3332e802-ccf4-4b39-9f5f-7e2fff3af14c: Fehler bei Ausbuchung");

		}

		return this;
	}


	public void _ausbuchung_box_aenderung(RB_TransportHashMap m_trp_hm, boolean commit,  MyE2_MessageVector mv) throws myException {
		RB_DataobjectsCollector_V2 do_collector = (RB_DataobjectsCollector_V2)  m_trp_hm.getMaskDataObjectsCollector();
		RB_ComponentMapCollector cm_collector 	= m_trp_hm.getMaskComponentMapCollector();
		RB_Dataobject_V2 dataObject 			= (RB_Dataobject_V2) do_collector.get(LH_P_CONST.getLeadingMaskKey());

		String mask_box_id 	= RB__TOOLS.find_comp(cm_collector.get(LH_P_CONST.getLeadingMaskKey()), LAGER_PALETTE.id_lager_box.fk()).get__actual_maskstring_in_view();
		String do_box_id 	= dataObject.rec21().get_ufs_dbVal(LAGER_PALETTE.id_lager_box,"");

		String id_palette = RB__TOOLS.find_comp(m_trp_hm.getMaskComponentMapCollector().get(LH_P_CONST.getLeadingMaskKey()), LAGER_PALETTE.id_lager_palette.fk()).get__actual_maskstring_in_view();;

		Rec21 rec_palette = m_trp_hm.getMaskDataObjectsCollector().get(LH_P_CONST.getLeadingMaskKey()).rec21();

		SEL query = new SEL("NVL(MAX("+LAGER_PALETTE_BOX.id_lager_palette_box.tnfn()+"),'')").FROM(_TAB.lager_palette_box).WHERE(new vgl(LAGER_PALETTE_BOX.id_lager_palette, id_palette)).AND(new VglNull(LAGER_PALETTE_BOX.ausbuchung_am));

		String id_box_palette__letzte = bibDB.EinzelAbfrage(query.s());
		
		if(S.isFull(id_box_palette__letzte)) {
			Rec21 rec_palette_box = new Rec21(_TAB.lager_palette_box)._fill_id(id_box_palette__letzte);

			Date datum = new GregorianCalendar().getTime();
			
			rec_palette_box.is_newRecordSet();
			rec_palette_box._setNewVal(LAGER_PALETTE_BOX.ausbuchung_am, datum, mv)._SAVE(commit, mv);

			String fuhre = (String)m_trp_hm.getFromExtender(LH_CONST.LH_EXTENDER.LH_AUSBUCHUNGSFUHRE);

			if(S.isFull(fuhre)) {
				rec_palette._nv(LAGER_PALETTE.id_vpos_tpa_fuhre_aus, fuhre, mv);
			}

			if(S.isAllFull(mask_box_id,do_box_id )) {
				if(! do_box_id.equals(mask_box_id)) {

					rec_palette_box._nv(LAGER_PALETTE_BOX.id_lager_box, do_box_id, mv)._SAVE(commit, mv);

					new Rec21(_TAB.lager_palette_box)
							._nv(LAGER_PALETTE_BOX.id_lager_box, mask_box_id, mv)
							._nv(LAGER_PALETTE_BOX.id_lager_palette, id_palette, mv)
							._setNewVal(LAGER_PALETTE_BOX.einbuchung_am, new GregorianCalendar().getTime(), mv)
							._SAVE(commit, mv);
				}
			}
			mv._addInfo(S.ms("Änderung in der Box: die Palette wurde neu gebucht"));
		}else {
			mv._addAlarm(S.ms("Lagerhaltung - FEHLER: bec6e2a2-93b6-4bbe-ab24-96c41ecfba36"));
		}
	}


	public PdServiceLagerhaltungAusbuchung _ausbuchungInLagerPaletteBox(RB_TransportHashMap m_tpHashMap, boolean commit, MyE2_MessageVector mv) throws myException {
		
		String id_palette = RB__TOOLS.find_comp(m_tpHashMap.getMaskComponentMapCollector().get(LH_P_CONST.getLeadingMaskKey()), LAGER_PALETTE.id_lager_palette.fk()).get__actual_maskstring_in_view();;
		
		SEL query = new SEL("NVL(MAX("+LAGER_PALETTE_BOX.id_lager_palette_box.tnfn()+"),'')").FROM(_TAB.lager_palette_box).WHERE(new vgl(LAGER_PALETTE_BOX.id_lager_palette, id_palette)).AND(new VglNull(LAGER_PALETTE_BOX.ausbuchung_am));

		String id_box_palette__letzte = bibDB.EinzelAbfrage(query.s());
		
		if(S.isFull(id_box_palette__letzte)) {
			Rec21 rec_palette_box = new Rec21(_TAB.lager_palette_box)._fill_id(id_box_palette__letzte);
			Date datum = new GregorianCalendar().getTime();
			rec_palette_box._setNewVal(LAGER_PALETTE_BOX.ausbuchung_am, datum, mv)
							._SAVE(commit, mv);
		}
		
		return this;
	}


	
	
	/**
	 * 
	 * @author martin
	 * @date 15.09.2020
	 *
	 * @param idLagerPalette
	 * @param mv
	 * @return
	 */
	public PdServiceLagerhaltungAusbuchung _storniereAusbuchnung(Long idLagerPalette,   MyE2_MessageVector mv) {
		
		
		try {
			Rec21_LagerPalette recLagerPalette = new Rec21_LagerPalette()._fill_id(idLagerPalette); 
			
			if (!recLagerPalette.isAusgebucht() ) {
				mv._addAlarm(S.ms("Die gewählte Palette ist nicht ausgebucht !"));
			} else {
				recLagerPalette._setNewVal(LAGER_PALETTE.id_vpos_tpa_fuhre_aus, null, mv);
				recLagerPalette._setNewVal(LAGER_PALETTE.ausbuchung_hand, "N", mv);
				recLagerPalette._setNewVal(LAGER_PALETTE.hand_ausbuchung_bemerkung, null, mv);
				
				Rec21 lagerPaletteBox = new Rec21(_TAB.lager_palette_box)._setNewVal(LAGER_PALETTE_BOX.id_lager_box, recLagerPalette.getLongDbValue(LAGER_PALETTE.id_lager_box), mv)
																		._setNewVal(LAGER_PALETTE_BOX.id_lager_palette, recLagerPalette.getLongDbValue(LAGER_PALETTE.id_lager_palette), mv)
																		 ._setNewVal(LAGER_PALETTE_BOX.einbuchung_am, new Date(), mv);
				
				MyE2_MessageVector mv2 = bibMSG.newMV()._add(bibDB.execMultiRecSave(new VEK<Rec21>()._a(recLagerPalette,lagerPaletteBox),true));
				if (mv2.isOK()) {
					mv._addInfo(S.ms("Die Palette ID: ").ut(""+recLagerPalette.getId()).t(" liegt wieder in der LagerBox: "+recLagerPalette.get_up_Rec21(LAGER_BOX.id_lager_box).getFs(LAGER_BOX.boxnummer, "??")));
				} else {
					mv._add(mv2);
				}
			}
			
		} catch (myException e) {
			e.printStackTrace();
			mv._add(e.get_ErrorMessage());
		}
		
		
		return this;
	}
	
	
	
}
