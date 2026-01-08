package panter.gmbh.Echo2.components.DB;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * checkbox fuer einsatz in listen, mit der moeglichkeit, gleich eine aktion (z.B. umschalt-vorgang Y-N auszuloesen)
 */
public abstract class MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction extends MyE2_DB_CheckBox
{

	public MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction(SQLField osqlField,boolean bDisableFromBasic, MyE2_String cTooltips)	throws myException
	{
		super(osqlField, bDisableFromBasic, cTooltips);
	}

	public MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction(SQLField osqlField,boolean bDisableFromBasic) throws myException
	{
		super(osqlField, bDisableFromBasic);
	}

	public MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction(SQLField osqlField,MyE2_String cText, MyE2_String cTooltips) throws myException
	{
		super(osqlField, cText, cTooltips);
	}

	public MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction(SQLField osqlField,MyE2_String cTooltips) throws myException
	{
		super(osqlField, cTooltips);
	}

	public MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction(SQLField osqlField) throws myException
	{
		super(osqlField);
	}


	public abstract Object get_Copy(Object ob) throws myExceptionCopy;

	

	public abstract XX_ActionAgent get_ToggleAction() throws myException;
	
	
	//ist immer enabled
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
	}

	
	
	
}
