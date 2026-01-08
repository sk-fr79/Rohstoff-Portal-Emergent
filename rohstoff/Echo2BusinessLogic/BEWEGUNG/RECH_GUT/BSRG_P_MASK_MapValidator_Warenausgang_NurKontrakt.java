package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.MyArtikelbezeichung_NG;


/*
 * Validator, sorgt dafuer, dass die kombination warenausgang und nicht-dienstleistungsposition nicht moeglich ist
 * positionen vom typ text den lagerfaktor 0 bekommen
 */
public class BSRG_P_MASK_MapValidator_Warenausgang_NurKontrakt extends XX_MAP_ValidBeforeSAVE
{

	public BSRG_P_MASK_MapValidator_Warenausgang_NurKontrakt()
	{
		super();
	}


	public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,SQLMaskInputMAP oInputMap, String cSTATUS_MASKE)
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();

		try 
		{
			//kontrakt wird nur bei positionen geprueft, die nicht aus fuhren stammen, da dort ebenfalls validierer greifen
			if (!(oInputMap.get_LActualValue("ID_VPOS_TPA_FUHRE_ZUGEORD", true,true , null) !=null || oInputMap.get_LActualValue("ID_VPOS_TPA_FUHRE_ORT_ZUGEORD", true,true , null) !=null ))
			{
				if (oInputMap.get_InputString("LAGER_VORZEICHEN").equals("-1"))   //warenausgang
				{
					String cID_ARTIKEL_BEZ = bibALL.ReplaceTeilString(bibALL.null2leer(oInputMap.get_InputString("ID_ARTIKEL_BEZ")),".","");
					String cID_VPOS_KON_ZUGEORD = bibALL.ReplaceTeilString(bibALL.null2leer(oInputMap.get_InputString("ID_VPOS_KON_ZUGEORD")),".","");
					
					if (bibALL.isLong(cID_ARTIKEL_BEZ))
					{
						if (bibALL.isEmpty(cID_VPOS_KON_ZUGEORD))
						{
							//pruefen, ob dienstleistung
							MyArtikelbezeichung_NG oArtBez = new MyArtikelbezeichung_NG(cID_ARTIKEL_BEZ);
							
							if (! (oArtBez.get_bIstDienstleistung() || oArtBez.get_bIstProdukt() ||  oArtBez.get_bIstEndOfWaste() ))
							{
								vRueck.add_MESSAGE(new MyE2_Alarm_Message("Warenausgangs/-Rechnungspositionen können nur bei Dienstleistungen oder Produkt/EndOfWaste ohne Kontrakt erzeugt werden !"));
							}
						}
					}
					else
					{
						vRueck.add_MESSAGE(new MyE2_Alarm_Message("Warenbewegungsposition kann nur mit korrekter Sortenbezeichnung erzeugt werden !"));
					}
				}
			}
		} 
		catch (myException ex) 
		{
			ex.printStackTrace();
			vRueck.add_MESSAGE(ex.get_ErrorMessage());
		}
		
		
		
		return vRueck;
	}

	
	
}
