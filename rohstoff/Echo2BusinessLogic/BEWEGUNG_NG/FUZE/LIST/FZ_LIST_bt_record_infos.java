package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.MODEL_VIEW.BEW_ModelView_bt;

public class FZ_LIST_bt_record_infos extends BEW_ModelView_bt implements E2_IF_Copy{

	public FZ_LIST_bt_record_infos() throws myException {
		super(_TAB.bewegung_vektor);
	}

	@Override
	public String get_id_table() throws myException {
		return this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
	}
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		
	}
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			return new FZ_LIST_bt_record_infos();
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}
}
