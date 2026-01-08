package panter.gmbh.Echo2.ListAndMask.List;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_Const.ListBuildType;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

public interface E2_NavigationList_dataPrepare {
	
	public abstract MyE2_MessageVector prepareData(ListBuildType type) throws myException;
	
}
