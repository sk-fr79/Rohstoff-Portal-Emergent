package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_Daughter_Abzuege;

public class FU_MASK_MAP_VALIDATOR_SetzeDatumEnde_And_calc_abzugsliste extends XX_MAP_ValidBeforeSAVE
{
	
	public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,SQLMaskInputMAP oInputMap, String cSTATUS_MASKE)
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		
		/*
		 * pruefen, ob die felder datum bis leer sind, wenn ja mit den feldern datum von fuellen
		 */
		if (bibALL.isEmpty((String)oInputMap.get("DATUM_ABHOLUNG_ENDE")))
			oInputMap.put("DATUM_ABHOLUNG_ENDE", oInputMap.get("DATUM_ABHOLUNG"));
		
		if (bibALL.isEmpty((String)oInputMap.get("DATUM_ANLIEFERUNG_ENDE")))
			oInputMap.put("DATUM_ANLIEFERUNG_ENDE", oInputMap.get("DATUM_ANLIEFERUNG"));

		 
		
		//jetzt die abzugslisten initialisieren
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		BL_Daughter_Abzuege oAbzuege_EK=null;
		BL_Daughter_Abzuege oAbzuege_VK=null;
		try
		{
			oAbzuege_EK = (BL_Daughter_Abzuege) oMap.get__Comp(FU___CONST.HASH_KEY_MASKE_TOCHTER_ABZUEGE_EK);
			oAbzuege_VK = (BL_Daughter_Abzuege) oMap.get__Comp(FU___CONST.HASH_KEY_MASKE_TOCHTER_ABZUEGE_VK);
			
			//jetzt die abzugsliste initialisieren
//			BigDecimal bdActual_EK_LADEMENGE = oMap.get_bdActualDBValue("ANTEIL_LADEMENGE_LIEF", BigDecimal.ZERO, BigDecimal.ZERO);
//			BigDecimal bdActual_VK_ABLADEMENGE = oMap.get_bdActualDBValue("ANTEIL_ABLADEMENGE_ABN", BigDecimal.ZERO, BigDecimal.ZERO);
//			String cFormatedValue_EK_LADEMENGE = MyNumberFormater.formatDez(bdActual_EK_LADEMENGE,3,true);
//			String cFormatedValue_VK_ABLADEMENGE = MyNumberFormater.formatDez(bdActual_VK_ABLADEMENGE,3,true);
			
			//2011-01-11: beim speichern auch die planmenge zur berechnung des abzugs verwenden
//			BigDecimal bdActual_EK_PLANMENGE = oMap.get_bdActualDBValue("ANTEIL_PLANMENGE_LIEF", BigDecimal.ZERO, BigDecimal.ZERO);
//			BigDecimal bdActual_VK_PLANMENGE = oMap.get_bdActualDBValue("ANTEIL_PLANMENGE_ABN", BigDecimal.ZERO, BigDecimal.ZERO);
//			String cFormatedValue_EK_PLANMENGE = MyNumberFormater.formatDez(bdActual_EK_PLANMENGE,3,true);
//			String cFormatedValue_VK_PLANMENGE = MyNumberFormater.formatDez(bdActual_VK_PLANMENGE,3,true);

			
//			Long       lID_ArtBez = oMap.get_LActualDBValue("ID_ARTIKEL_BEZ_EK", new Long(0), new Long(0));	
			
//			RECORD_ARTIKEL recArtikel = null;
//			if (lID_ArtBez.longValue()>0)
//			{
//				recArtikel = new RECORD_ARTIKEL_BEZ(""+lID_ArtBez.longValue()).get_UP_RECORD_ARTIKEL_id_artikel();
//			}
			
			//2011-01-11: beim speichern auch die planmenge zur berechnung des abzugs verwenden	
//			oAbzuege_EK.Set_Werte_fuer_Nutzung_IN_FUHRE(bdActual_EK_LADEMENGE.intValue()!=0?cFormatedValue_EK_LADEMENGE:cFormatedValue_EK_PLANMENGE,recArtikel);
//			oAbzuege_VK.Set_Werte_fuer_Nutzung_IN_FUHRE(bdActual_VK_ABLADEMENGE.intValue()!=0?cFormatedValue_VK_ABLADEMENGE:cFormatedValue_VK_PLANMENGE,recArtikel);
			
			
			// ================================================================================================================
			//2016-12-20: abzuege auf der kunden/lieferantenseite muss auf das jeweilige abrechnungsgewicht definiert werden
			((__FU_FUO_ABZUGSLISTE)oAbzuege_EK).set_NAME_OF_DBFIELD_MENGE(VPOS_TPA_FUHRE.anteil_lademenge_lief);
			((__FU_FUO_ABZUGSLISTE)oAbzuege_VK).set_NAME_OF_DBFIELD_MENGE(VPOS_TPA_FUHRE.anteil_ablademenge_abn);
			//jetzt die noetigen werte beschaffen
			String id_adresse_start=	""+oInputMap.get_LActualValue(VPOS_TPA_FUHRE.id_adresse_start.fn(), true, true, -1l).longValue();
			String id_adresse_ziel=		""+oInputMap.get_LActualValue(VPOS_TPA_FUHRE.id_adresse_ziel.fn(), true, true, -1l).longValue();
			boolean b_lademenge_ist_gutschriftsmenge = oInputMap.get_InputString(VPOS_TPA_FUHRE.lademenge_gutschrift.fn(), "N").equals("Y");
			boolean b_ablademenge_ist_rechnungsmenge = oInputMap.get_InputString(VPOS_TPA_FUHRE.ablademenge_rechnung.fn(), "N").equals("Y");
			
			if (!id_adresse_start.equals(bibALL.get_ID_ADRESS_MANDANT())) {
				if (!b_lademenge_ist_gutschriftsmenge) {
					((__FU_FUO_ABZUGSLISTE)oAbzuege_EK).set_NAME_OF_DBFIELD_MENGE(VPOS_TPA_FUHRE.anteil_ablademenge_lief);
				}
			}
			
			if (!id_adresse_ziel.equals(bibALL.get_ID_ADRESS_MANDANT())) {
				if (!b_ablademenge_ist_rechnungsmenge) {
					((__FU_FUO_ABZUGSLISTE)oAbzuege_VK).set_NAME_OF_DBFIELD_MENGE(VPOS_TPA_FUHRE.anteil_lademenge_abn);
				}
			}
			// ================================================================================================================
			
			
			
			oAbzuege_EK.bCalc_ganze_AbzugsListe(oMV);
			oAbzuege_VK.bCalc_ganze_AbzugsListe(oMV);
			
			//nach der abzugsberechnung stehen die neuen werte in der maske, muessen aber noch in die inputmap uebertragen werden
			
			oInputMap.put("ABZUG_LADEMENGE_LIEF","0");
			oInputMap.put("ABZUG_ABLADEMENGE_ABN","0");
			if (S.isFull(oMap.get_cActualDBValueFormated("ABZUG_LADEMENGE_LIEF")))
			{
				oInputMap.put("ABZUG_LADEMENGE_LIEF",oMap.get_cActualDBValueFormated("ABZUG_LADEMENGE_LIEF"));
			}
			if (S.isFull(oMap.get_cActualDBValueFormated("ABZUG_ABLADEMENGE_ABN")))
			{
				oInputMap.put("ABZUG_ABLADEMENGE_ABN",oMap.get_cActualDBValueFormated("ABZUG_ABLADEMENGE_ABN"));
			}
			
			
			
		} 
		catch (myException e)
		{
			e.printStackTrace();
			oMV.add_MESSAGE(e.get_ErrorMessage());
		}
		
		vRueck.add_MESSAGE(oMV);

		return vRueck;
	}
  
	
}
