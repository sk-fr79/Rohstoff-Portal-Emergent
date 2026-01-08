package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class FU__Mask_ZEIGE_SORTENBEZ_Label 
{

	//fuellt die labels, die die sortenbezeichnung auf seite 1 der fuhre fuellen
	public FU__Mask_ZEIGE_SORTENBEZ_Label(MyE2IF__Component oComponentFomMask) throws myException 
	{
		super();
		
		E2_ComponentMAP  oMAP_Fuhre = oComponentFomMask.EXT().get_oComponentMAP();
		

		//die komponenten existieren nur in der Fuhrenmaske, deshalb existenz in der MAP abpruefen 
		if (oMAP_Fuhre != null && oMAP_Fuhre.containsKey(FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_EK) && oMAP_Fuhre.containsKey(FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_VK))
		{
			FU__ARTBEZ_ANZEIGE  oArtbezAnzeigeEK = (FU__ARTBEZ_ANZEIGE)oMAP_Fuhre.get__Comp(FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_EK);
			FU__ARTBEZ_ANZEIGE  oArtbezAnzeigeVK = (FU__ARTBEZ_ANZEIGE)oMAP_Fuhre.get__Comp(FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_VK);
			oArtbezAnzeigeEK.baue_anzeige("","","EK");
			oArtbezAnzeigeVK.baue_anzeige("","","VK");

			long id_artbez_ek = oMAP_Fuhre.get_LActualDBValue("ID_ARTIKEL_BEZ_EK", new Long(-1), new Long(-1)) ;
			long id_artbez_vk = oMAP_Fuhre.get_LActualDBValue("ID_ARTIKEL_BEZ_VK", new Long(-1), new Long(-1)) ;

			if (id_artbez_ek>0)
			{
				long lID_ADRESSE_LAGER_START = oMAP_Fuhre.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_START, new Long(-1), new Long(-1)) ;
				String cID_ADRESSE_LAGER_START = lID_ADRESSE_LAGER_START>0?(""+lID_ADRESSE_LAGER_START):"";
				oArtbezAnzeigeEK.baue_anzeige(""+id_artbez_ek,cID_ADRESSE_LAGER_START,"EK");
			}
			if (id_artbez_vk>0)
			{
				long lID_ADRESSE_LAGER_ZIEL = oMAP_Fuhre.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_ZIEL, new Long(-1), new Long(-1)) ;
				String cID_ADRESSE_LAGER_ZIEL = lID_ADRESSE_LAGER_ZIEL>0?(""+lID_ADRESSE_LAGER_ZIEL):"";
				oArtbezAnzeigeVK.baue_anzeige(""+id_artbez_vk,cID_ADRESSE_LAGER_ZIEL,"VK");
			}
		}
		
	}

}
