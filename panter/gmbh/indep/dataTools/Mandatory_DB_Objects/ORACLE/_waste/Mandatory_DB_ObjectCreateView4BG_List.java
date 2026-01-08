/**
 * 
 */
package panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE._waste;

import panter.gmbh.basics4project.BasicTools.TextFileLoader;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE.Mandatory_DB_Object_ORACLE;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class Mandatory_DB_ObjectCreateView4BG_List extends Mandatory_DB_Object_ORACLE {

	
	String idMandant = null;
	/**
	 * @param ObjectName
	 * @throws myException 
	 */
	public Mandatory_DB_ObjectCreateView4BG_List(String ObjectBaseName, String id_mandant) throws myException {
		super("S"+id_mandant+"_"+ObjectBaseName);
		
		this.idMandant = id_mandant;
	}

	@Override
	public boolean ObjectExists() {
		return this.ObjectExists_View();
	}

	@Override
	public boolean ObjectIsValid() throws myException {
		return true;
	}

	@Override
	public boolean CreateObject() {

		boolean ret = false;
		
		try {
			String cSqlView = new TextFileLoader(getClass(), "view_LIST4BG.txt").get_loadedText();

			cSqlView = bibALL.ReplaceTeilString(cSqlView,"#VIEW#" , this.getObjectName());
			cSqlView = bibALL.ReplaceTeilString(cSqlView,"#ID_MANDANT#" , this.idMandant);
			
			ret=bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cSqlView, true);
			
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

}
