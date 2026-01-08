package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO_MASK_BasicModuleContainer;

public class FU__MaskSettingsAfterInput_Pruefe_Kontrakt_Adresse_Sorte
{

	/*
	 * modul loesche den kontrakt-eintrag, wenn festgestellt wird, dass eine nicht passende Adresse oder
	 * sorte in bezug auf den kontrakt vorliegt 
	 * 
	 */
	public FU__MaskSettingsAfterInput_Pruefe_Kontrakt_Adresse_Sorte(MyE2IF__Component oComponentFomMask) throws myException
	{
		super();
		
		
		E2_BasicModuleContainer_MASK  	oFU_MaskContainer = null;
		FUO_MASK_BasicModuleContainer 	oFUO_MaskContainer = null;
		
		//zuerst die Maske beschaffen (Fuhre oder Fuhrenort)
		E2_BasicModuleContainer_MASK  oModuleContainerMask = oComponentFomMask.EXT().get_ModuleContainer_MASK_this_BelongsTo();
		
		
		/*
		 * wird entweder aus einem Fuhrenort oder einer Fuhre/TPA-Position ausgeloest
		 */
		if (oModuleContainerMask instanceof FUO_MASK_BasicModuleContainer)
		{
			//der erste gefundene Container ist der eingebettete listencontainer, deswegen den ersten mit messagebox
			//oFU_MaskContainer  = (E2_BasicModuleContainer_MASK)(new E2_RecursiveSearchParent_BasicModuleContainer(oModuleContainerMask.get_oComponentWhichStartetThisPopup())).get_First_MaskFoundContainer__ThrowExWhenNotFound();
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
		if (oFUO_MaskContainer == null)
		{

			pruefe_relation(oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_VPOS_KON_EK"), 
							oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_ADRESSE_START"), 
							oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_ARTIKEL_BEZ_EK"), 
							oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_ARTIKEL"),
							oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_EAK_CODE"),
							"EK");
			
			pruefe_relation(oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_VPOS_KON_VK"), 
							oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_ADRESSE_ZIEL"), 
							oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_ARTIKEL_BEZ_VK"), 
							null,
							null,
							"VK");
			
		}
		else
		{
			String cEK_VK = oFUO_MaskContainer.get_EK_VK();
			
			pruefe_relation(oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_KON"),
							oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.ID_ADRESSE"),
							oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.ID_ARTIKEL_BEZ"),
							oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.ID_ARTIKEL"),
							oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.ID_EAK_CODE"),
							cEK_VK);
		}

	}
	

	
	private void pruefe_relation(	MyE2IF__DB_Component compVPOS_KON, 
									MyE2IF__DB_Component compAdresse, 
									MyE2IF__DB_Component compARTBEZ, 
									MyE2IF__DB_Component compArtikel,
									MyE2IF__DB_Component compEAK_CODE,
									String cEK_VK) throws myException
	{
		Long lID_VPOS_KON =			compVPOS_KON.EXT_DB().get_LActualMaskValue(true, true, null);
		Long lID_ADRESSE =			compAdresse.EXT_DB().get_LActualMaskValue(true, true, null);
		Long lID_ARTBEZ =			compARTBEZ.EXT_DB().get_LActualMaskValue(true, true, null);
		
		String cEKVK_Help = " ("+cEK_VK+")";
		
		boolean bDeleteVPOS_KON = false;
		boolean bDeleteArtBez = false;
		boolean bDeleteSorteUndSortenbez = false;
		
		//schritt 1: stimmt die adresse/kontrakt nicht, dann kontrakt leermachen
		if (lID_VPOS_KON != null && lID_ADRESSE != null)
		{
			if (new RECORD_VPOS_KON(lID_VPOS_KON.longValue()).get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_ADRESSE_lValue(new Long(-1)).longValue() != lID_ADRESSE.longValue())
			{
				bDeleteVPOS_KON = true;
			}
		}
		
		//schritt 2: stimmt die sorte/kontrakt (Stellen 1-2 in ANR1) nicht, dann kontrakt leermachen
		if (lID_VPOS_KON != null && lID_ARTBEZ != null)
		{
			String cANR1_12_a = (new RECORD_VPOS_KON(lID_VPOS_KON.longValue()).get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("  ")+"  ").substring(0,2);
			String cANR1_12_b = (new RECORD_ARTIKEL_BEZ(lID_ARTBEZ.longValue()).get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("  ")+"  ").substring(0,2);

			if (!cANR1_12_a.equals(cANR1_12_b))
			{
				bDeleteVPOS_KON = true;
			}
		}
		
		
		//schritt 3 (nur EK-Seite): stimmt die sorte nicht mit der sortenbezeichnung, dann sortenbezeichung leermachen
		if (compArtikel != null)
		{
			Long lID_ARTIKEL =	compArtikel.EXT_DB().get_LActualMaskValue(true, true, null);
			if (lID_ARTIKEL != null && lID_ARTBEZ != null)
			{
				if (new RECORD_ARTIKEL_BEZ(lID_ARTBEZ.longValue()).get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_lValue(new Long(-1)).longValue()!=
					new RECORD_ARTIKEL(lID_ARTIKEL.longValue()).get_ID_ARTIKEL_lValue(new Long(-1)).longValue())
				{
					bDeleteArtBez = true;
				}
			}
		}

		
		//schritt 4: im EK-Bereich muss die Sorte fuer den lieferanten gelistet sein
		if (lID_ADRESSE != null  &&  (! (lID_ADRESSE.longValue()+"").equals(bibALL.get_ID_ADRESS_MANDANT())) && compArtikel != null)
		{
			if (cEK_VK.equals("EK") && lID_ARTBEZ!=null)
			{
				if (new RECORD_ADRESSE(lID_ADRESSE.longValue()).get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse("JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ="+lID_ARTBEZ.longValue(), "ID_ARTIKEL_BEZ",false).size()==0)
				{
					bDeleteSorteUndSortenbez = true;
				}
			}
			
		}
		
		
		
		if (bDeleteVPOS_KON && lID_VPOS_KON != null)
		{
			compVPOS_KON.prepare_ContentForNew(false);
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Maskeneintrag <Kontrakt"+cEKVK_Help+"> wurde entfernt !"));
		}
		
		if (bDeleteArtBez && lID_ARTBEZ != null)
		{
			compARTBEZ.prepare_ContentForNew(false);
			if (compEAK_CODE != null && cEK_VK.equals("EK"))
			{
				compEAK_CODE.prepare_ContentForNew(false);
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Maskeneintrag <AVV-CODE> wurde entfernt !"));
			}
			
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Maskeneintrag <Artikelbezeichnung"+cEKVK_Help+"> wurde entfernt !"));
		}

		if (bDeleteSorteUndSortenbez && compArtikel != null)
		{
			compArtikel.prepare_ContentForNew(false);
			compARTBEZ.prepare_ContentForNew(false);
			if (compEAK_CODE != null && cEK_VK.equals("EK"))
			{
				compEAK_CODE.prepare_ContentForNew(false);
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Maskeneintrag <AVV-CODE> wurde entfernt !"));
			}

			bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Maskeneintrag <Artikel/Artikelbezeichnung"+cEKVK_Help+"> wurde entfernt !"));
			
		}
		
	}
	
	
	
}
