package panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST;

import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_CONST.TS_DEFINITION;
import panter.gmbh.indep.exceptions.myException;

public abstract class TS_Treasure_Chest {
	
	public abstract TS_DEFINITION get_TREASURE_CHEST_DEF();
	public abstract Object        get_TREASURE_CHEST() throws myException;
	
	
}
