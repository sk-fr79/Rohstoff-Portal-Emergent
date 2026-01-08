package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.FU__decide_correct_avv_code_transport;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO_MASK_BasicModuleContainer;
import rohstoff.Echo2BusinessLogic._4_ALL.MASK_COMPONENT_SEARCH_EAK_CODES;

public class FU__decide_correct_avv_code_transport_fuhre extends FU__decide_correct_avv_code_transport {

	private Long lID_ADRESSE_LAGER_START = null;
	private Long lID_ADRESSE_LAGER_ZIEL = null;
	
	private Long lID_ARTIKELBEZ_START = null;
	private Long lID_ARTIKELBEZ_ZIEL = null;
	
	private MASK_COMPONENT_SEARCH_EAK_CODES  relevant_avv_field = null;
	
	
	public FU__decide_correct_avv_code_transport_fuhre(MyE2IF__Component oComponentFomMask) throws myException {
		super();
		
		E2_BasicModuleContainer_MASK  	oFU_MaskContainer = null;
		FUO_MASK_BasicModuleContainer 	oFUO_MaskContainer = null;
		
		//zuerst die Maske beschaffen (Fuhre oder Fuhrenort)
		E2_BasicModuleContainer_MASK  oModuleContainerMask = oComponentFomMask.EXT().get_ModuleContainer_MASK_this_BelongsTo();
		
		
		/*
		 * wird entweder aus einem Fuhrenort oder einer Fuhre/TPA-Position ausgeloest
		 */
		if (oModuleContainerMask instanceof FUO_MASK_BasicModuleContainer)	{
			oFUO_MaskContainer = (FUO_MASK_BasicModuleContainer)oModuleContainerMask;
			oFU_MaskContainer  = (E2_BasicModuleContainer_MASK)(new E2_RecursiveSearchParent_BasicModuleContainer(
					oFUO_MaskContainer.get_oFUO_DaughterComponent())).get_First_MaskFoundContainer__ThrowExWhenNotFound();
			
		}
		else    
		{
			// hier ist der container eine fuhre oder ein TPA
			oFU_MaskContainer = oModuleContainerMask;
			oFUO_MaskContainer  = null;
		}

		
		//variante 1: Hauptfuhre
		if (oFUO_MaskContainer == null)	{
			lID_ADRESSE_LAGER_START = oFU_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE.id_adresse_lager_start.tnfn()).EXT_DB().get_LActualMaskValue(true, true, null);
			lID_ADRESSE_LAGER_ZIEL = oFU_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE.id_adresse_lager_ziel.tnfn()).EXT_DB().get_LActualMaskValue(true, true, null);

			lID_ARTIKELBEZ_START = oFU_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE.id_artikel_bez_ek.tnfn()).EXT_DB().get_LActualMaskValue(true, true, null);
			lID_ARTIKELBEZ_ZIEL = oFU_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE.id_artikel_bez_vk.tnfn()).EXT_DB().get_LActualMaskValue(true, true, null);
			
			relevant_avv_field = (MASK_COMPONENT_SEARCH_EAK_CODES)oFU_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE.id_eak_code.tnfn());
			
		} else {
			if (oFUO_MaskContainer.get_EK_VK().equals("EK")) {
				lID_ADRESSE_LAGER_START = oFUO_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE_ORT.id_adresse_lager.tnfn()).EXT_DB().get_LActualMaskValue(true, true, null);
				lID_ADRESSE_LAGER_ZIEL = oFU_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE.id_adresse_lager_ziel.tnfn()).EXT_DB().get_LActualMaskValue(true, true, null);

				lID_ARTIKELBEZ_START = oFUO_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE_ORT.id_artikel_bez.tnfn()).EXT_DB().get_LActualMaskValue(true, true, null);
				lID_ARTIKELBEZ_ZIEL = oFU_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE.id_artikel_bez_vk.tnfn()).EXT_DB().get_LActualMaskValue(true, true, null);
				
				relevant_avv_field = (MASK_COMPONENT_SEARCH_EAK_CODES)oFUO_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE_ORT.id_eak_code.tnfn());
			} else {
				lID_ADRESSE_LAGER_START = oFU_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE.id_adresse_lager_start.tnfn()).EXT_DB().get_LActualMaskValue(true, true, null);
				lID_ADRESSE_LAGER_ZIEL = oFUO_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE_ORT.id_adresse_lager.tnfn()).EXT_DB().get_LActualMaskValue(true, true, null);

				lID_ARTIKELBEZ_START = oFU_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE.id_artikel_bez_ek.tnfn()).EXT_DB().get_LActualMaskValue(true, true, null);
				lID_ARTIKELBEZ_ZIEL = oFUO_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE_ORT.id_artikel_bez.tnfn()).EXT_DB().get_LActualMaskValue(true, true, null);
				
				relevant_avv_field = (MASK_COMPONENT_SEARCH_EAK_CODES)oFUO_MaskContainer.get_DBComponent(VPOS_TPA_FUHRE_ORT.id_eak_code.tnfn());

			}
		}

	}
	
	
	@Override
	public Long read_actual_id_adress_start_geo() throws myException {
		return this.lID_ADRESSE_LAGER_START;
	}

	@Override
	public Long read_actual_id_adress_ziel_geo() throws myException {
		return this.lID_ADRESSE_LAGER_ZIEL;
	}

	@Override
	public Long read_actual_id_artikelbez_start() throws myException {
		return this.lID_ARTIKELBEZ_START;
	}

	@Override
	public Long read_actual_id_artikelbez_ziel() throws myException {
		return this.lID_ARTIKELBEZ_ZIEL;
	}

	@Override
	public void write_found_avvcode_to_mask(Long id_avv_code) throws myException {
		if (MyLong.isNotNull(id_avv_code)) {
			this.relevant_avv_field.set_cActual_Formated_DBContent_To_Mask(id_avv_code.toString(),null,null);
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Ermittlung AVV-Code: ").ut(this.getInfoText().CTrans())));
		} else {
			if (S.isAllFull(this.getInfoText().CTrans())) {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Ermittlung AVV-Code: ").ut(this.getInfoText().CTrans())));
			}
		}
	}


	@Override
	public void clear_avvcode_in_mask() throws myException {
		this.relevant_avv_field.prepare_ContentForNew(false);

	}

}
