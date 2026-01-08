package rohstoff.Echo2BusinessLogic.EAK.GRUPPE;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class EAKG_LIST_BUTTON_Goto_CODES extends MyE2_DB_Button implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{
	

	public EAKG_LIST_BUTTON_Goto_CODES(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.setIcon(E2_ResourceIcon.get_RI("rightright.png"));
		this.set_Text("");
		this.add_oActionAgent(new EAKG_LIST_ACTIONAGENT_Goto_CODES());
	}
	

	// wird ueberschrieben, da es ein Grafischer button ist
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_Button:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");
								
		this.set_cActualRowID(oResultMAP.get_cUNFormatedROW_ID());
	}


	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		EAKG_LIST_BUTTON_Goto_CODES oButtCopy = null;
		
		try
		{
			oButtCopy = new EAKG_LIST_BUTTON_Goto_CODES(this.EXT_DB().get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_Button:get_Copy:Copy-Error!");
		}
		
		oButtCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButtCopy));
		if (this.getIcon() != null)
			oButtCopy.setIcon(this.getIcon());
		
		if (this.get_oText() != null)
			oButtCopy.set_Text(this.get_oText());
		
		oButtCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oButtCopy));
		
		oButtCopy.setStyle(this.getStyle());
		oButtCopy.setInsets(this.getInsets());
		
	
		for (int i=0;i<this.get_vGlobalValidators().size();i++)
			oButtCopy.add_GlobalValidator((XX_ActionValidator)this.get_vGlobalValidators().get(i));
				
		
		for (int i=0;i<this.get_vIDValidators().size();i++)
			oButtCopy.add_IDValidator((XX_ActionValidator)this.get_vIDValidators().get(i));

		return oButtCopy;
	}

	
}
