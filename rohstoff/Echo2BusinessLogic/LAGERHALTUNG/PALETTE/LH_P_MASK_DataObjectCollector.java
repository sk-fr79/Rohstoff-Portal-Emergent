
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import java.util.GregorianCalendar;

import panter.gmbh.BasicInterfaces.Service.PdServiceLagerhaltungAusbuchung;
import panter.gmbh.BasicInterfaces.Service.PdServiceLagerhaltungEinbuchung;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__TOOLS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE_BOX;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;



public class LH_P_MASK_DataObjectCollector extends RB_DataobjectsCollector_V2 {

	private RB_TransportHashMap   m_tpHashMap = null;

	public LH_P_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();
		this.m_tpHashMap = p_tpHashMap;     

		this._setUseRec21(true);

		this.m_tpHashMap._setMaskDataObjectsCollector(this);    
		this.m_tpHashMap._setMaskStatusOnLoad(MASK_STATUS.NEW);

		this.registerComponent(_TAB.lager_palette.rb_km(), new LH_P_MASK_DataObject(this.m_tpHashMap));


		this._addMessageTranslator(new RB_MessageTranslator(
				new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));

	}


	public LH_P_MASK_DataObjectCollector(RB_TransportHashMap  p_tpHashMap, String id_LAGER_PALETTE, MASK_STATUS status) throws myException {
		super();

		this.m_tpHashMap = p_tpHashMap;     

		if(this.m_tpHashMap.getLeadingMaskKey().get_db_table() == _TAB.lager_palette_box) {
			this.m_tpHashMap._setLeadingMaskKey(LH_P_CONST.getLeadingMaskKey());
		}

		this._setUseRec21(true);


		this.m_tpHashMap._setMaskDataObjectsCollector(this);    
		this.m_tpHashMap._setMaskStatusOnLoad(status);

		this.registerComponent(_TAB.lager_palette.rb_km(), new LH_P_MASK_DataObject(new Rec21(_TAB.lager_palette)._fill_id(id_LAGER_PALETTE),status,this.m_tpHashMap));

		this._addMessageTranslator(new RB_MessageTranslator(
				new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));
	}

	@Override
	public void database_to_dataobject(Object startPoint) throws myException {
		startPoint.getClass();
	}

	@Override
	public RB_DataobjectsCollector_V2 get_copy() throws myException {
		return null;
	}


	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv)	throws myException {
		
		RB_MaskController maskController = new RB_MaskController(do_collector);

		RB_Dataobject dataObject = do_collector.get(LH_P_CONST.getLeadingMaskKey());
		RB_ComponentMap compMap = this.m_tpHashMap.getMaskComponentMapCollector().get(LH_P_CONST.getLeadingMaskKey());

		IF_RB_Component taramenge_cmp = compMap._find_component_in_neighborhood(LAGER_PALETTE.taramenge);
		if(taramenge_cmp != null) {
			if(S.isEmpty(taramenge_cmp.get__actual_maskstring_in_view())){
				dataObject.rec20().nv(LAGER_PALETTE.taramenge, "0", mv);
				//taramenge_cmp.rb_set_db_value_manual("0");
			}
		}
		
		
		//18.10.2019@sebastien: wenn palette ist durch eine Fuhre eingebucht dann buchungsnr_hand muss leer sein.
		boolean is_hand_einbuchung = RB__TOOLS.find_comp(compMap, LAGER_PALETTE.einbuchung_hand.fk()).get__actual_maskstring_in_view().equals("Y");
		if(! is_hand_einbuchung) {
			dataObject.rec20().nv(LAGER_PALETTE.buchungsnr_hand, "", mv);
		}
		
		
		if(dataObject.rb_MASK_STATUS() == MASK_STATUS.NEW || dataObject.rb_MASK_STATUS() == MASK_STATUS.NEW_COPY) {
			new PdServiceLagerhaltungEinbuchung()._einbuchung(this.m_tpHashMap, mv);
		} else {
			String idBoxDaten = dataObject.rec20().getUfs(LAGER_PALETTE.id_lager_box, "");
			
			if (S.isAllFull(maskController.get_liveVal(LAGER_PALETTE.id_lager_box))) {   //sonst kommt sowieso ein fehler, da die box gesetz sein muss
			
				String idBoxMaske = bibALL.convertID2UnformattedID(RB__TOOLS.find_comp(compMap, LAGER_PALETTE.id_lager_box.fk()).get__actual_maskstring_in_view());
				
				//wenn die box sich geaendert hat, umbuchung schreiben
				if (!idBoxDaten.equals(idBoxMaske)) {
					
					//letzte box ausbuchen
					new PdServiceLagerhaltungAusbuchung()._ausbuchungInLagerPaletteBox(m_tpHashMap, false , mv);
					
					String idPaletteAufMaske = ""+dataObject.get_RecORD().get_PRIMARY_KEY_VALUE();
					
					//wieder einbuchung schreiben
					if (mv.get_bIsOK() ) {
						new Rec21(_TAB.lager_palette_box)
						._nv(LAGER_PALETTE_BOX.id_lager_box, idBoxMaske, mv)
						._nv(LAGER_PALETTE_BOX.id_lager_palette, idPaletteAufMaske, mv)
						._setNewVal(LAGER_PALETTE_BOX.einbuchung_am, new GregorianCalendar().getTime(), mv)
						._SAVE(false, mv);
					}
				}
				
				if (mv.isOK()) {
				
					
					Long 	idFuhreAusbuchung = (Long)maskController.getValueFromScreen( LH_P_CONST.getLeadingMaskKey(),LAGER_PALETTE.id_vpos_tpa_fuhre_aus);
					boolean is_hand_ausbuchung = RB__TOOLS.find_comp(compMap, LAGER_PALETTE.ausbuchung_hand.fk()).get__actual_maskstring_in_view().equals("Y");
					if(is_hand_ausbuchung || idFuhreAusbuchung!=null) {
						new PdServiceLagerhaltungAusbuchung()._ausbuchungInLagerPaletteBox(m_tpHashMap, false , mv);
//					} else if(! idBoxMaske.equals(idBoxDaten)) {
//						new PdServiceLagerhaltungAusbuchung()._ausbuchung_box_aenderung(m_tpHashMap,false, mv);
					}
				}
			}
		}
	}

	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V2 do_collector,	MyE2_MessageVector mv) throws myException {

	}
}


