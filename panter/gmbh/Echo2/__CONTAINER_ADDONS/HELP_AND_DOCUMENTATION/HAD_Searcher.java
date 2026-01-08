package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_CONST.TS_DEFINITION;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_CONST.TS_Treasure_Chest_searcher;
import panter.gmbh.indep.exceptions.myException;

public class HAD_Searcher {

	private HAD__GridWithData  had_grid_with_data = null;
	
	/**
	 * sucht via treasure_chest das aktuelle help-popup und aktualisiert es  
	 * @throws myException 
	 */
	public HAD_Searcher() throws myException {
		super();
		
		TS_Treasure_Chest_searcher  search = new TS_Treasure_Chest_searcher(TS_DEFINITION.POPUP_HILFETEXTE);
		
		if (search.get_found_treasure_chest().get_TREASURE_CHEST()!=null) {
			this.had_grid_with_data = ((HAD__GridWithData)search.get_found_treasure_chest().get_TREASURE_CHEST());
		}
	}

	public HAD__GridWithData get_had_grid_with_data() {
		return had_grid_with_data;
	}

	public void _rebuild() throws myException {
		if (this.had_grid_with_data!=null) {
			this.had_grid_with_data._build();
		} else {
			throw new myException("Error searching helpgrid ....");
		}
	}
	
	
}
