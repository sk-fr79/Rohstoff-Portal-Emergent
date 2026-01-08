package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FSK_CB_LOCK extends MyE2_DB_CheckBox {

	public FSK_CB_LOCK(SQLField osqlField) throws myException {
		super(osqlField);
		
		this.setToolTipText(new MyE2_String("Verhindert das Überschreiben dieses Preises bei automatischen Ermittlungsläufen").CTrans());
		this.EXT().set_bLineWrapListHeader(true);
		this.EXT().set_oCompTitleInList(new MyE2_Button(E2_ResourceIcon.get_RI("locked_mini.png"),new MyE2_String("Verhindert das Überschreiben dieses Preises bei automatischen Ermittlungsläufen"),null));
	}
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		try {
			return new FSK_CB_LOCK(this.EXT_DB().get_oSQLField());
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}
	
	
}
