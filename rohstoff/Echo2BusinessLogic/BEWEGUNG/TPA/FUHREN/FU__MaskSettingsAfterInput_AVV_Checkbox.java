package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_CODE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingleIgnoreEmptys;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO_MASK_BasicModuleContainer;

public class FU__MaskSettingsAfterInput_AVV_Checkbox
{

	public FU__MaskSettingsAfterInput_AVV_Checkbox(MyE2IF__Component oComponentFomMask) throws myException
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
		
		Long lID_ADRESSE_LAGER_START = null;
		Long lID_ADRESSE_LAGER_ZIEL = null;
		Long lID_ARTIKEL = null;
		Long lID_EAK_CODE = null;
		String cEUCODE = null;
		String cBASEL_CODE = null;
		String cLAND_TRANSIT1 = null;
		String cLAND_TRANSIT2 = null;
		String cLAND_TRANSIT3 = null;
		MyE2_DB_CheckBox oCB_AVV_Druck = null;

		
		//variante 1: Hauptfuhre
		if (oFUO_MaskContainer == null)
		{
			lID_ADRESSE_LAGER_START = oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_START").EXT_DB().get_LActualMaskValue(true, true, null);
			lID_ADRESSE_LAGER_ZIEL = oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_ZIEL").EXT_DB().get_LActualMaskValue(true, true, null);
			lID_ARTIKEL = oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_ARTIKEL").EXT_DB().get_LActualMaskValue(true, true, null);
			lID_EAK_CODE = oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_EAK_CODE").EXT_DB().get_LActualMaskValue(true, true, null);
			cEUCODE = oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.EUCODE").get_cActualMaskValue();
			cBASEL_CODE = oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.BASEL_CODE").get_cActualMaskValue();
			oCB_AVV_Druck = (MyE2_DB_CheckBox)	oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.PRINT_EU_AMTSBLATT");
			cLAND_TRANSIT1 = oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.LAENDERCODE_TRANSIT1").get_cActualMaskValue();
			cLAND_TRANSIT2 = oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.LAENDERCODE_TRANSIT2").get_cActualMaskValue();
			cLAND_TRANSIT3 = oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.LAENDERCODE_TRANSIT3").get_cActualMaskValue();

		}
		else
		{
			if (oFUO_MaskContainer.get_EK_VK().equals("EK"))
			{
				lID_ADRESSE_LAGER_START = oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.ID_ADRESSE_LAGER").EXT_DB().get_LActualMaskValue(true, true, null);
				lID_ADRESSE_LAGER_ZIEL = oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_ZIEL").EXT_DB().get_LActualMaskValue(true, true, null);
			}
			else
			{
				lID_ADRESSE_LAGER_START = oFU_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_START").EXT_DB().get_LActualMaskValue(true, true, null);
				lID_ADRESSE_LAGER_ZIEL = oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.ID_ADRESSE_LAGER").EXT_DB().get_LActualMaskValue(true, true, null);
			}

			lID_ARTIKEL = 		oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.ID_ARTIKEL").EXT_DB().get_LActualMaskValue(true, true, null);
			lID_EAK_CODE = 		oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.ID_EAK_CODE").EXT_DB().get_LActualMaskValue(true, true, null);
			cEUCODE = 			oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.EUCODE").get_cActualMaskValue();
			cBASEL_CODE = 		oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.BASEL_CODE").get_cActualMaskValue();
			oCB_AVV_Druck = 	(MyE2_DB_CheckBox)	oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.PRINT_EU_AMTSBLATT");
			cLAND_TRANSIT1 = 	oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.LAENDERCODE_TRANSIT1").get_cActualMaskValue();
			cLAND_TRANSIT2 = 	oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.LAENDERCODE_TRANSIT2").get_cActualMaskValue();
			cLAND_TRANSIT3 = 	oFUO_MaskContainer.get_DBComponent("JT_VPOS_TPA_FUHRE_ORT.LAENDERCODE_TRANSIT3").get_cActualMaskValue();

		}
		
		
		this.set_AVV_CheckBox(	lID_ADRESSE_LAGER_START,
								lID_ADRESSE_LAGER_ZIEL,
								lID_ARTIKEL,
								lID_EAK_CODE,
								cEUCODE,
								cBASEL_CODE,
								cLAND_TRANSIT1,
								cLAND_TRANSIT2,
								cLAND_TRANSIT3,
								oCB_AVV_Druck);
		
	}
	

	
	
	private void set_AVV_CheckBox(	Long lID_ADRESSE_LAGER_START,
									Long lID_ADRESSE_LAGER_ZIEL,
									Long lID_ARTIKEL,
									Long lID_EAK_CODE,
									String cEUCODE,
									String cBASEL_CODE,
									String cLAND_TRANSIT1,
									String cLAND_TRANSIT2,
									String cLAND_TRANSIT3,
									MyE2_DB_CheckBox oCB_AVV_Druck
									) throws myException
	{
		
		
		//nur wenn alle 3 vorhanden sind, geht diese pruefung weiter
		if (lID_ADRESSE_LAGER_START != null && lID_ADRESSE_LAGER_ZIEL != null && lID_ARTIKEL != null)
		{
			//Long lID_EAK_CODE = oInputMAP.get_LActualValue("JT_VPOS_TPA_FUHRE.ID_EAK_CODE", true, true, new Long(-1));
			
			RECORD_ARTIKEL recArtikel = new RECORD_ARTIKEL(lID_ARTIKEL.longValue());
			RECORD_ADRESSE recLagerStart = new RECORD_ADRESSE(lID_ADRESSE_LAGER_START.longValue());
			RECORD_ADRESSE recLagerZiel = new RECORD_ADRESSE(lID_ADRESSE_LAGER_ZIEL.longValue());
			
			boolean bAVV_GEFAHR_oder_leer = true;

			if (lID_EAK_CODE != null)
			{
				bAVV_GEFAHR_oder_leer = !(new RECORD_EAK_CODE(lID_EAK_CODE.longValue()).is_GEFAEHRLICH_NO());
			}
			
			boolean bBASEL_GEFAHR = bibALL.null2leer(cBASEL_CODE).trim().toUpperCase().startsWith("A");
			boolean bOECD_GEFAHR = bibALL.null2leer(cEUCODE).trim().toUpperCase().startsWith("A");
			
			boolean b_gefaehrlich_oder_leere_AVV = bAVV_GEFAHR_oder_leer || bBASEL_GEFAHR || bOECD_GEFAHR;
		
			// jetzt noch pruefen, ob es ein produkt ist, dann darf auch kein begleitschein gedruckt werden
			boolean bIst_Produkt_o_EOF_oder_Dienstleistung = recArtikel.is_IST_PRODUKT_YES()||recArtikel.is_DIENSTLEISTUNG_YES()||recArtikel.is_END_OF_WASTE_YES();


			if (b_gefaehrlich_oder_leere_AVV || bIst_Produkt_o_EOF_oder_Dienstleistung)
			{
				//dann auf jeden fall AVV-schalter ausmachen
				//((MyE2_DB_CheckBox) oFuhreMask.get_Component("JT_VPOS_TPA_FUHRE.PRINT_EU_AMTSBLATT")).setSelected(false);
				oCB_AVV_Druck.setSelected(false);
				return;
			}


			//dann die laendercodes pruefen
			VectorSingleIgnoreEmptys  vTest = new VectorSingleIgnoreEmptys();
			vTest.add(recLagerStart.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN(""));
			vTest.add(recLagerZiel.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN(""));
			vTest.add(cLAND_TRANSIT1);
			vTest.add(cLAND_TRANSIT2);
			vTest.add(cLAND_TRANSIT3);
			
			
			oCB_AVV_Druck.setSelected(false);
			if (vTest.size()>1)
			{
				//dann auf jeden fall AVV-schalter ausmachen
				oCB_AVV_Druck.setSelected(true);
				return;
			}
		}
	}

	
}
