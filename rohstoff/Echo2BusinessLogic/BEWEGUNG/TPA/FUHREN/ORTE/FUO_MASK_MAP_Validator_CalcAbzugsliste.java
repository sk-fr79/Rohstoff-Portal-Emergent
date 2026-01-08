package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import java.math.BigDecimal;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_Daughter_Abzuege;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.__FU_FUO_ABZUGSLISTE;

public class FUO_MASK_MAP_Validator_CalcAbzugsliste extends  XX_MAP_ValidBeforeSAVE
{
    private String cEK_VK = null;
	
	
	public FUO_MASK_MAP_Validator_CalcAbzugsliste(String cek_vk)
	{
		super();
		this.cEK_VK = cek_vk;
	}


	@Override
	public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,SQLMaskInputMAP inputMap, String cstatus_maske) throws myException
	{ 

		//jetzt die abzugslisten initialisieren
		MyE2_MessageVector oMV = new MyE2_MessageVector();

		try
		{
			//jetzt die abzugsliste initialisieren
			BL_Daughter_Abzuege oAbzuege = (BL_Daughter_Abzuege) oMap.get__Comp(FUO__CONST.HASHKEY_MASK_ABZUG_FUHRE_ORT);
			
			String cFeldNameMenge = this.cEK_VK.equals("EK")?"ANTEIL_LADEMENGE":"ANTEIL_ABLADEMENGE";
			
			//2011-01-11: beim speichern auch die planmenge zur berechnung des abzugs verwenden
			String cFeldNamePlanMenge = "ANTEIL_PLANMENGE";
			
			BigDecimal bdActualMenge = 		oMap.get_bdActualDBValue(cFeldNameMenge, BigDecimal.ZERO, BigDecimal.ZERO);
			BigDecimal bdActualPlanMenge = 	oMap.get_bdActualDBValue(cFeldNamePlanMenge, BigDecimal.ZERO, BigDecimal.ZERO);
			
			
			String cFormatedValueMenge = MyNumberFormater.formatDez(bdActualMenge,3,true);
			String cFormatedValuePlanMenge = MyNumberFormater.formatDez(bdActualPlanMenge,3,true);
				
			Long       lID_ArtBez = oMap.get_LActualDBValue("ID_ARTIKEL_BEZ", new Long(0), new Long(0));	
			
			RECORD_ARTIKEL recArtikel = null;
			if (lID_ArtBez.longValue()>0)
			{
				recArtikel = new RECORD_ARTIKEL_BEZ(""+lID_ArtBez.longValue()).get_UP_RECORD_ARTIKEL_id_artikel();
			}
				
			
			
			// ================================================================================================================
			//2016-12-20: abzuege auf der kunden/lieferantenseite muss auf das jeweilige abrechnungsgewicht definiert werden
			boolean b_ek = this.cEK_VK.equals("EK");
			((__FU_FUO_ABZUGSLISTE)oAbzuege).set_NAME_OF_DBFIELD_MENGE(b_ek?VPOS_TPA_FUHRE_ORT.anteil_lademenge:VPOS_TPA_FUHRE_ORT.anteil_ablademenge);   //vorgabe
			//jetzt die noetigen werte beschaffen
			String id_adresse =	""+inputMap.get_LActualValue(VPOS_TPA_FUHRE_ORT.id_adresse.fn(), true, true, -1l).longValue();
			
			if (!id_adresse.equals(bibALL.get_ID_ADRESS_MANDANT())) { 		//nur bei fremdadressen relevant
				if (b_ek) {
					boolean b_lademenge_gutschrift = inputMap.get_InputString(VPOS_TPA_FUHRE_ORT.lademenge_gutschrift.fn(), "N").equals("Y");
					if (!b_lademenge_gutschrift) {
						((__FU_FUO_ABZUGSLISTE)oAbzuege).set_NAME_OF_DBFIELD_MENGE(VPOS_TPA_FUHRE_ORT.anteil_ablademenge);
					}
				} else {
					boolean b_ablademenge_rechnung = inputMap.get_InputString(VPOS_TPA_FUHRE_ORT.ablademenge_rechnung.fn(), "N").equals("Y");
					if (!b_ablademenge_rechnung) {
						((__FU_FUO_ABZUGSLISTE)oAbzuege).set_NAME_OF_DBFIELD_MENGE(VPOS_TPA_FUHRE_ORT.anteil_lademenge);
					}
				}
			}
			// ================================================================================================================

			
			
			
			
			//2011-01-11: beim speichern auch die planmenge zur berechnung des abzugs verwenden
//			oAbzuege.Set_Werte_fuer_Nutzung_IN_FUHRE(bdActualMenge.intValue()!=0?cFormatedValueMenge:cFormatedValuePlanMenge,recArtikel);
			oAbzuege.bCalc_ganze_AbzugsListe(oMV);

			//nach der abzugsberechnung stehen die neuen werte in der maske, muessen aber noch in die inputmap uebertragen werden
			
			inputMap.put("ABZUG_MENGE","0");
			if (S.isFull(oMap.get_cActualDBValueFormated("ABZUG_MENGE")))
			{
				inputMap.put("ABZUG_MENGE",oMap.get_cActualDBValueFormated("ABZUG_MENGE"));
			}

			
		} 
		catch (myException e)
		{
			e.printStackTrace();
			oMV.add_MESSAGE(e.get_ErrorMessage());
		}
		
		return oMV;
		
	}

}
