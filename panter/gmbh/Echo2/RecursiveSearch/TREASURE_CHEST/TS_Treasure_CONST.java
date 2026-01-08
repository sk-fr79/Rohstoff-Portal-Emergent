package panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AllMembersOfClass;
import panter.gmbh.indep.exceptions.myException;


public class TS_Treasure_CONST {

	/**
	 * hier muessen alle treasure-chest-kenner hinterlegt sein
	 * @author martin
	 *
	 */
	public enum TS_DEFINITION {
		 UPLOADFILES_TREASURE_CHEST
		,MAIL_PROFILE_INLAY_IN_MANDANTENMASK          	//2015-05-06, martin
		,POPUP_HILFETEXTE  								//2015-10-05, martin
	}
	
	
	/**
	 * 
	 * @param def
	 * @return s the one found treasure-chest of the type def, wenn more than 1 found, then exception, when 0 found then null
	 * @throws myException
	 */
	public static TS_Treasure_Chest find_TreasureChest(TS_Treasure_CONST.TS_DEFINITION def) throws myException{
		return new TS_Treasure_Chest_searcher(def).get_found_treasure_chest();
	}
	
	
	public static class TS_Treasure_Chest_searcher extends E2_RecursiveSearch_AllMembersOfClass {

		private TS_Treasure_CONST.TS_DEFINITION definition = null;
		
		public TS_Treasure_Chest_searcher(TS_Treasure_CONST.TS_DEFINITION d_efinition) {
			super(TS_Treasure_Chest_Container_IF.class.getName());
			this.definition = d_efinition;
		}
		
		public TS_Treasure_Chest get_found_treasure_chest() throws myException {
			Vector<TS_Treasure_Chest> vRueck = new Vector<TS_Treasure_Chest>();
			
			for (Component comp: this.get_vAllComponents())  {
				if (comp instanceof TS_Treasure_Chest_Container_IF) {
					TS_Treasure_Chest_Container_IF tsContainer = (TS_Treasure_Chest_Container_IF)comp;
					for (TS_Treasure_Chest tc: tsContainer.get_my_treasure_chests()) {
						if (tc.get_TREASURE_CHEST_DEF()==this.definition) {
							vRueck.add(tc);
						}
					}
				} else {
					//duerfte nicht vorkommen
					throw new myException("Error at finding treasure-chest ...");
				}
			}
			if (vRueck.size() >1) {
				throw new myException("Error at finding treasure-chest ... IT MUST BE FOUND EXACTLY ONE OR NONE !!!");
			}
			if (vRueck.size()==1) {
				return vRueck.get(0);
			} else {
				return null;
			}
		}
		
	}
	
}
