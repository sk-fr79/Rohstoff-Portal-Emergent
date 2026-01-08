package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import java.util.HashMap;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.MyArtikel;
import rohstoff.utils.My_MWST;
import rohstoff.utils.My_MWSTSaetze;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;

public class BS_VL_MASK_DB_SEARCH_ArtikelBez extends DB_SEARCH_ArtikelBez 
{

	public BS_VL_MASK_DB_SEARCH_ArtikelBez(SQLField osqlField, Insets INSETS_For_Components) throws myException 
	{
		super(osqlField, INSETS_For_Components, null, null, null, null);
		
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskActionAfterFound());
		this.set_bTextForAnzeigeVisible(false);
	}

	
	/*
	 * mask-setting-agent fuer das laden der adress-werte in die maske
	 */
	private class ownMaskActionAfterFound extends XX_MaskActionAfterFound
	{
		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException 
		{
			// BSAA_ComponentMAP_Mask oThis = BSAA_ComponentMAP_Mask.this;
			if (bAfterAction)
			{
				
				E2_ComponentMAP 			oMap = 				oSearchField.EXT().get_oComponentMAP();
				
				String cQuery = "SELECT  JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ," +				// 0
										"JT_ARTIKEL.ID_ARTIKEL, " +						// 1
										"JT_ARTIKEL_BEZ.ARTBEZ1," +						// 2
										"JT_ARTIKEL_BEZ.ARTBEZ2, " +					// 3
										"JT_ARTIKEL.ANR1, " +							// 4
										"JT_ARTIKEL_BEZ.ANR2 " +						// 5
										"  FROM " +
										bibE2.cTO()+".JT_ARTIKEL_BEZ, "+bibE2.cTO()+".JT_ARTIKEL " +
										"  WHERE  JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL  AND " +
										" JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ="+cMaskValue;
				
				MyDBToolBox oDB = bibALL.get_myDBToolBox();
				String[][] oRueck = oDB.EinzelAbfrageInArray(cQuery,"");
				bibALL.destroy_myDBToolBox(oDB);
				
				
				if (oRueck == null || oRueck.length != 1)
				{
					throw new myException("BSAA_P_ComponentMAP_List:ownMaskSettingAgent:Error filling mask with search-result");
				}
				else
				{
					HashMap<String, MyE2IF__Component> oHMReal = oMap.get_REAL_ComponentHashMap();
					
					
					
					// wenn  nur ein gueltiger MWST-Satz fuer diese Art-Bez existiert, dann 
					// wird dieser 
					My_MWSTSaetze oBSMW = new My_MWSTSaetze(null,oRueck[0][0]);
					((MyE2IF__DB_Component)oHMReal.get("STEUERSATZ")).set_cActualMaskValue("");
					if (oBSMW.get_vMWSTArtBez().size()==1)
					{
						My_MWST oBSMWST = (My_MWST)oBSMW.get_vMWSTArtBez().get(0);
						((MyE2IF__DB_Component)oHMReal.get("STEUERSATZ")).set_cActualMaskValue(oBSMWST.get_cMWST_Formated());
					}
					
					MyArtikel oArtikel = new MyArtikel(oRueck[0][1]);
					
					
					((MyE2IF__DB_Component)oHMReal.get("ANR1")).set_cActualMaskValue(oRueck[0][4]);
					((MyE2IF__DB_Component)oHMReal.get("ANR2")).set_cActualMaskValue(oRueck[0][5]);

					((MyE2IF__DB_Component)oHMReal.get("ARTBEZ1")).set_cActualMaskValue(oRueck[0][2]);
					((MyE2IF__DB_Component)oHMReal.get("ARTBEZ2")).set_cActualMaskValue(oRueck[0][3]);
					
					((MyE2IF__DB_Component)oHMReal.get("ID_ARTIKEL")).set_cActualMaskValue(oRueck[0][1]);
					
					((MyE2IF__DB_Component)oHMReal.get("EINHEITKURZ")).set_cActualMaskValue(oArtikel.get_EINHEIT());
					((MyE2IF__DB_Component)oHMReal.get("EINHEIT_PREIS_KURZ")).set_cActualMaskValue(oArtikel.get_EINHEIT_PREIS());
					((MyE2IF__DB_Component)oHMReal.get("MENGENDIVISOR")).set_cActualMaskValue(oArtikel.get_MENGENDIVISOR());
					
				}
				
			}
		}
		
	}
}
