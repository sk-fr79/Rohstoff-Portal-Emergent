package panter.gmbh.Echo2.components.DB;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public abstract class MyE2_DB_PlaceHolder_onlyWhenVisisble extends MyE2_DB_PlaceHolder {

	
	public MyE2_DB_PlaceHolder_onlyWhenVisisble(SQLField osqlField) throws myException {
		super(osqlField);
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	}

	public abstract void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db,String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException;
	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String value_from_db,String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		if (E2_ComponentMAP.is_visible_in_NavigationList(this)) {
			this.set_cActual_Formated_DBContent_To_Mask_WhenVisisble(value_from_db,cMASK_STATUS, oResultMAP);
		}
	}

}
