package panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER;

import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.exceptions.myException;

public interface SCAN_DESCRIPTION_IF {
	
	public String 							get_ArchivBaseTable() throws myException;
	public String 							get_ArchiveIdTable() throws myException;
	public E2_NavigationList        		get_ArchivNaviList() throws myException;
	public boolean   						get_ScanIs4Download();
	public VALID_ENUM_MODULES.RANGE_KEY 	get_RANGE_KEY() throws myException;
	
}
