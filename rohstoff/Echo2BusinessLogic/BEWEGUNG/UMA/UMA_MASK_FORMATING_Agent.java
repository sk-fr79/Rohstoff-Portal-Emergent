package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_UMA_KON_ARTB_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_UMA_KON_ARTB_RUECKLIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KONTRAKT;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class UMA_MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
		((UMA_MASK_EINHEITEN_LABEL)oMAP.get__Comp(UMA_CONST.MASK_COMP_EINHEIT_AUSGANGSORTE)).set_Text("<eh>");
		((UMA_MASK_EINHEITEN_LABEL)oMAP.get__Comp(UMA_CONST.MASK_COMP_EINHEIT_ZIELSORTE)).set_Text("<eh>");
	}
	

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		oMAP.set_bEnabled_For_Edit_and_set_DisabledFromInteractiveFlag_ALL(true);

		((UMA_MASK_EINHEITEN_LABEL)oMAP.get__Comp(UMA_CONST.MASK_COMP_EINHEIT_AUSGANGSORTE)).set_Text("<eh>");
		((UMA_MASK_EINHEITEN_LABEL)oMAP.get__Comp(UMA_CONST.MASK_COMP_EINHEIT_ZIELSORTE)).set_Text("<eh>");

		
		if (oUsedResultMAP != null)
		{
			RECORD_UMA_KONTRAKT 			recUMA = new RECORD_UMA_KONTRAKT(oUsedResultMAP.get_LActualDBValue(RECORD_UMA_KONTRAKT.FIELD__ID_UMA_KONTRAKT, true).longValue());
			
			RECLIST_UMA_KON_ARTB_LIEF 		recListArtLief = 		recUMA.get_DOWN_RECORD_LIST_UMA_KON_ARTB_LIEF_id_uma_kontrakt(); 	
			RECLIST_UMA_KON_ARTB_RUECKLIEF 	recListArtRueckLief = 	recUMA.get_DOWN_RECORD_LIST_UMA_KON_ARTB_RUECKLIEF_id_uma_kontrakt();
			
			//zur feststellung der einheit von beiden seiten wird das jeweils erste element genommen
			RECORD_ARTIKEL recSorteLief = 		null;
			RECORD_ARTIKEL recSorteRueckLief =  null;
			
			if (recListArtLief.get_vKeyValues().size()>0)	{recSorteLief = recListArtLief.get(0).get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel();}
			if (recListArtRueckLief.get_vKeyValues().size()>0)	{recSorteRueckLief = recListArtRueckLief.get(0).get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel();}
			
			if (recSorteLief != null)
			{
				if (recSorteLief.get_UP_RECORD_EINHEIT_id_einheit()!=null)
				{
					((UMA_MASK_EINHEITEN_LABEL)oMAP.get__Comp(UMA_CONST.MASK_COMP_EINHEIT_AUSGANGSORTE)).set_Text(recSorteLief.get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cUF_NN("<eh>"));
				}
			}
			
			if (recSorteRueckLief != null)
			{
				if (recSorteRueckLief.get_UP_RECORD_EINHEIT_id_einheit()!=null)
				{
					((UMA_MASK_EINHEITEN_LABEL)oMAP.get__Comp(UMA_CONST.MASK_COMP_EINHEIT_ZIELSORTE)).set_Text(recSorteRueckLief.get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cUF_NN("<eh>"));
				}
			}
			
		}
		
	}

}
