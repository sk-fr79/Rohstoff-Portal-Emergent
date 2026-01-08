package panter.gmbh.indep.dataTools;

import java.util.Vector;


/**
 * hilfsklasse, die einer sql-map uebergeben werden kann,
 * um zusaetzlich sql-statements einzuschleusen
 */
public abstract class SQL_Update_AGENT
{
	public abstract Vector<String> get_vZusatzStatements(SQLFieldMAP hmActualMAP,SQLResultMAP oActualResult, SQLMaskInputMAP oMaskInputValues);
	
}
