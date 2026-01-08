package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SELECTORS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_SELECTOR;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class SEL_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
 	
	public SEL_LIST_SqlFieldMAP(String CallingModuleContainer) throws myException 
	{
		super(_DB.SELECTOR, ":"+_DB.SELECTOR$ERZEUGT_VON+":"+_DB.SELECTOR$ERZEUGT_AM+":"+RECORD_SELECTOR.FIELD__MODULKENNER, false);
		
		this.add_SQLField(new SQLFieldForRestrictTableRange(RECORD_SELECTOR.TABLENAME, 
											RECORD_SELECTOR.FIELD__MODULKENNER, 
											RECORD_SELECTOR.FIELD__MODULKENNER, 
											new MyE2_String("Modulkenner rufendes Modul"), 
											bibALL.MakeSql(CallingModuleContainer), bibE2.get_CurrSession()), false);
		
		this.initFields();
	}
	
}
