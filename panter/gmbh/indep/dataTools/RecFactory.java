package panter.gmbh.indep.dataTools;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;

public class RecFactory {

	
	/**
	 * liefert RECORD_XXX - object oder null (wenn falsche oder leere uebergabe) 
	 * @param tab
	 * @param id
	 * @return
	 */
	public static MyRECORD_IF_RECORDS gen(_TAB tab, String id) {

		MyLong  l = new MyLong(id);
		
		if (l.isOK()) {
		
			try {
				MyRECORD_IF_RECORDS rec = tab.nativeRecord();
				((MyRECORD) rec).PrepareAndBuild("*", bibE2.cTO()+"."+tab.fullTableName(), tab.keyFieldName()+"="+l.get_cUF_LongString());
				return rec;
			} catch (myException e) {
				e.printStackTrace();
			}		
		
		} 
		
		
		return null;
	}
	
	
	
}
