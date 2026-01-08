package panter.gmbh.indep.dataTools;

import panter.gmbh.indep.exceptions.myException;


public class MyDBToolBox_FAB_raw extends MyDBToolBox_FAB {

	@Override
	public MyDBToolBox generate_INDIVIDUELL_DBToolBox(MyConnection conn) throws myException {
		MyDBToolBox tb= MyDBToolBox_FAB.get_myDBToolBox(false, false, conn);
		return tb;
	}

}
