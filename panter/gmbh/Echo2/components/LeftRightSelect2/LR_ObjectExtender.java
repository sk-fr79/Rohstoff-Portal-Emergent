package panter.gmbh.Echo2.components.LeftRightSelect2;

import panter.gmbh.indep.exceptions.myException;

public interface LR_ObjectExtender {
	
	public String get_text4_checkBox() throws myException;
	public String get_saveKEY() throws myException;
	public void   set_enableExtenderComponents(boolean bEnabled) throws myException;
	
}
