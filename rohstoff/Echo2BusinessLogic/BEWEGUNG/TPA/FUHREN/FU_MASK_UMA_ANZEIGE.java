package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.RecursiveSearch.E2_SEARCH_TAGS;
import panter.gmbh.Echo2.components.MyE2_Grid;


public class FU_MASK_UMA_ANZEIGE extends MyE2_Grid
{
	public FU_MASK_UMA_ANZEIGE()
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.setVisible(false);
		this.EXT().add_SEARCH_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.FUHRENMASKE_UMA_ANZEIGE);
	}

}
