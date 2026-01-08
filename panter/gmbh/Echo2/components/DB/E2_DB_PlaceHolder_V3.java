package panter.gmbh.Echo2.components.DB;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_DB_PlaceHolder_V3 extends E2_Grid implements MyE2IF_DB_SimpleComponent, E2_IF_Copy {

	/**
	 * @throws myException
	 */
	public E2_DB_PlaceHolder_V3() {
		super();
	}

	public abstract void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db,String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException;
	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String value_from_db,String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		if (E2_ComponentMAP.is_visible_in_NavigationList(this)) {
			this.set_cActual_Formated_DBContent_To_Mask_WhenVisisble(value_from_db,cMASK_STATUS, oResultMAP);
		}
	}

	
	
}
