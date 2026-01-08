package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.WARENSTROEME;

import java.util.HashMap;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.exceptions.myException;

public class hm_DatenTypUndMenge extends HashMap<String,DatenTypUndMenge>
{




	public hm_DatenTypUndMenge()
	{
		super();
	}

	
	/*
	 * fordert den passenden sammler an und erzeugt ihn gegebenenfalls
	 * LagerSorteHashGeneratoren erzeugt
	 */
	public DatenTypUndMenge get_DatenTypUndMenge(LagerSorteHashGenerator  oLagSortHashGen, RECORD_VPOS_TPA_FUHRE recFuhre) throws myException
	{
		if (!this.containsKey(oLagSortHashGen.get_HASHKEY()))
		{
			this.put(oLagSortHashGen.get_HASHKEY(), 
					new DatenTypUndMenge(oLagSortHashGen));
		}
		DatenTypUndMenge odRueck = this.get(oLagSortHashGen.get_HASHKEY());
		odRueck.add_ID_FUHRE(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
		
		
		return odRueck;
		
	}
	
	
	
	public DatenTypUndMenge get_DatenTypUndMenge(LagerSorteHashGenerator  oLagSortHashGen, RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt) throws myException
	{
		if (!this.containsKey(oLagSortHashGen.get_HASHKEY()))
		{
			this.put(oLagSortHashGen.get_HASHKEY(), 
					new DatenTypUndMenge(oLagSortHashGen));
		}
		DatenTypUndMenge odRueck = this.get(oLagSortHashGen.get_HASHKEY());
		odRueck.add_ID_FUHRE(recFuhreOrt.get_ID_VPOS_TPA_FUHRE_cUF());
		
		
		return odRueck;
		
	}
	
	
	
	
}
