package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.AA_EDIT_VIEW;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_MaskModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LG.LG_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LG.LG_DO__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LG.__LG_MASTER_KEY;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL.LL_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL.LL_DO__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL.__LL_MASTER_KEY;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST.ST_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST.ST_DO__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST.__ST_MASTER_KEY;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.TS.TS_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.TS.TS_DO__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.TS.__TS_MASTER_KEY;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA.WA_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA.WA_DO__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA.__WA_MASTER_KEY;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.WE_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.WE_DO__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.__WE_MASTER_KEY;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;

public class FZ_AA_edit_view extends XX_ActionAgent {
	
	private MyE2_Grid  			grid4Mask = 			new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Grid  			grid4MaskInternal = 	new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Grid  			grid4MaskExternal = 	new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

	private E2_NavigationList   naviList =              null;
	
//	//was ist schon implementiert ?
//	private Vector<VEKTOR_TYP>  v_typ_allowed = new Vector<>();
	
	private boolean  			edit = true;
	
	public FZ_AA_edit_view(E2_NavigationList p_naviList, boolean b_edit) {
		super();
		this.naviList = p_naviList;
				
//		this.v_typ_allowed.add(VEKTOR_TYP.WE);
//		this.v_typ_allowed.add(VEKTOR_TYP.LL);
		this.edit = b_edit;
		
		this.grid4Mask.add(this.grid4MaskInternal,E2_INSETS.I(2,2,2,2));
		this.grid4Mask.add(this.grid4MaskExternal,E2_INSETS.I(10,2,2,2));
		
	}

	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		FZ_AA_edit_view 				oThis = FZ_AA_edit_view.this;
		FZ_MASK_MaskModulContainer		mask = new FZ_MASK_MaskModulContainer(this.naviList);

		//jetzt nachsehen, welche typen selektiert wurden
		Vector<String>  v_id_vektor = new Vector<>();
		v_id_vektor = this.naviList.get_vSelectedIDs_Unformated();
		
		if (v_id_vektor.size()!=0) {
			for (String id: v_id_vektor) {
				RECORD_BEWEGUNG_VEKTOR  rv = new RECORD_BEWEGUNG_VEKTOR(id);
				ENUM_VEKTOR_TYP vt = ENUM_VEKTOR_TYP.find_typ(rv.ufs(BEWEGUNG_VEKTOR.variante,""));
				if (S.isFull(rv.get_UP_RECORD_BEWEGUNG_id_bewegung().ufs(BEWEGUNG.id_vpos_tpa_fuhre,""))) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Warenbewegungen, die aus Konvertierungen aus der Fuhrenzentrale herrühren, können hier nicht bearbeitet werden !")));
				} else if (vt==null) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Variante der Bewegung unbekannt <"+rv.ufs(BEWEGUNG_VEKTOR.variante,"")+"> !!!")));
				} else {
					switch (vt) {
					case WE: 
						__WE_MASTER_KEY  	we_key = 				new __WE_MASTER_KEY(this.edit?MASK_STATUS.EDIT:MASK_STATUS.VIEW);
						WE_CM__Collector  	we_mask_collector = 	new WE_CM__Collector(this.naviList, we_key);
						WE_DO__Collector 	we_dataColl = 			new WE_DO__Collector(id, 			we_key);
						mask.rb_register(	we_mask_collector);
						bibMSG.add_MESSAGE(	we_mask_collector.rb_COMPLETE_FILL_CYCLE(we_dataColl));
						break;
					
					case LG:
						__LG_MASTER_KEY 		lg_key 				= new __LG_MASTER_KEY(this.edit?MASK_STATUS.EDIT:MASK_STATUS.VIEW);
						LG_CM__Collector 	lg_mask_collector 	= new LG_CM__Collector(this.naviList,  lg_key);
						LG_DO__Collector	lg_dataCollector 	= new LG_DO__Collector(id, lg_key);
						mask.rb_register(	lg_mask_collector);
						bibMSG.add_MESSAGE(	lg_mask_collector.rb_COMPLETE_FILL_CYCLE(lg_dataCollector));
						break;
					
					case LL: 
						__LL_MASTER_KEY 		ll_key = 				new __LL_MASTER_KEY(this.edit?MASK_STATUS.EDIT:MASK_STATUS.VIEW);
						LL_CM__Collector  	ll_mask_collector = 	new LL_CM__Collector(this.naviList, ll_key);
						LL_DO__Collector 	ll_dataColl = 			new LL_DO__Collector(id, 			ll_key);
						mask.rb_register(	ll_mask_collector);
						bibMSG.add_MESSAGE(	ll_mask_collector.rb_COMPLETE_FILL_CYCLE(ll_dataColl));
						break;
						
					case S: 
						__ST_MASTER_KEY 		st_key = 				new __ST_MASTER_KEY(this.edit?MASK_STATUS.EDIT:MASK_STATUS.VIEW);
						ST_CM__Collector  	st_mask_collector = 	new ST_CM__Collector(this.naviList, st_key);
						ST_DO__Collector 	st_dataColl = 			new ST_DO__Collector(id, 			st_key);
						mask.rb_register(	st_mask_collector);
						bibMSG.add_MESSAGE(	st_mask_collector.rb_COMPLETE_FILL_CYCLE(st_dataColl));
						break;
					
					case WA: 
						__WA_MASTER_KEY 		wa_key 				= 	new __WA_MASTER_KEY(this.edit?MASK_STATUS.EDIT:MASK_STATUS.VIEW	);
						WA_CM__Collector  	wa_mask_collector 	= 	new WA_CM__Collector(this.naviList, wa_key						);
						WA_DO__Collector 	wa_dataColl 		= 	new WA_DO__Collector(id, 			wa_key						);
						mask.rb_register(	wa_mask_collector																		);
						bibMSG.add_MESSAGE(	wa_mask_collector.rb_COMPLETE_FILL_CYCLE(wa_dataColl)									);
						break;
						
					case TS: 
						__TS_MASTER_KEY 		tg_key 				= 	new __TS_MASTER_KEY(this.edit?MASK_STATUS.EDIT:MASK_STATUS.VIEW	);
						TS_CM__Collector  	tg_mask_collector 	= 	new TS_CM__Collector(this.naviList, tg_key						);
						TS_DO__Collector 	tg_dataColl 		= 	new TS_DO__Collector(id, 			tg_key						);
						mask.rb_register(	tg_mask_collector																		);
						bibMSG.add_MESSAGE(	tg_mask_collector.rb_COMPLETE_FILL_CYCLE(tg_dataColl)									);
						break;
						
					default:
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Variante der Bewegung noch nicht implementiert <"+rv.ufs(BEWEGUNG_VEKTOR.variante,"")+"> !!!")));
						break;
					}
				}
			}
			if (bibMSG.get_bIsOK()) {
				mask.rebuild_container_grid();
				
				mask.get_oRowForButtons().removeAll();
				mask.get_oRowForButtons().add(oThis.grid4Mask);
				oThis.grid4MaskInternal.removeAll();
				
				mask.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), new MyE2_String("Erfassen von Warenbewegungen"));
			}
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens eine Warenbewegung auswählen !")));
		}
		
	}

	

}