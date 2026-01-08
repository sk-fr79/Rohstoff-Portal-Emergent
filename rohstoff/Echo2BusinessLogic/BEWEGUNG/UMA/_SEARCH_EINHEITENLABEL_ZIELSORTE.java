package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import java.util.Vector;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;

public class _SEARCH_EINHEITENLABEL_ZIELSORTE extends E2_RecursiveSearch_AB_Basis
{

	public _SEARCH_EINHEITENLABEL_ZIELSORTE()
	{
		super(UMA_MASK_EINHEITEN_LABEL.class.getName());
	}
	
	
	public UMA_MASK_EINHEITEN_LABEL  get_foundLabel()
	{
		
		Vector<UMA_MASK_EINHEITEN_LABEL> vFound = new Vector<UMA_MASK_EINHEITEN_LABEL>();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof UMA_MASK_EINHEITEN_LABEL)
			{
				if (!((UMA_MASK_EINHEITEN_LABEL)this.get_vAllComponents().get(i)).get_bAUSGANGSSORTE())
				{
					vFound.add((UMA_MASK_EINHEITEN_LABEL)this.get_vAllComponents().get(i));
				}
			}
		}

		if (vFound.size()==1)
		{
			return vFound.get(0);
		}
		
		return null;
		
	}

}
