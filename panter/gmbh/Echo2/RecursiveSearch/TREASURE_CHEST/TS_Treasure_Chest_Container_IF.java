package panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

public interface TS_Treasure_Chest_Container_IF {
	
	public Vector<TS_Treasure_Chest> get_my_treasure_chests() throws myException;
	
}
