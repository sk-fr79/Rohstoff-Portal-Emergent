package panter.gmbh.Echo2.components.DB;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.If_ComponentWithOwnKey;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_UniversalListComponent extends E2_Grid implements If_ComponentWithOwnKey, MyE2IF_DB_SimpleComponent, E2_IF_Copy
{

	/**
	 * @throws myException
	 */
	public E2_UniversalListComponent() {
		super();
	}

	public abstract void populate(SQLResultMAP resultMap) throws myException;
	
	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String value_from_db,String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		if (E2_ComponentMAP.is_visible_in_NavigationList(this)) {
			this.populate(oResultMAP);
		}
	}

	
	public Long getIdOfRow() {
		Long l = null;
		try {
			if (this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()!=null) {
				MyLong ml = new MyLong(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				if (ml.isOK()) {
					l=ml.get_oLong();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			bibSES.getServletContext().log("Fehler: E2_UniversalListComponent: "+e.getLocalizedMessage());
		}
		return l;
	}
	


	
	
}
