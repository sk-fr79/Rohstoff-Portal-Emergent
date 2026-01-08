package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_DoubleSelectFieldForTime;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class CAL_ComponentMAP_MASK_TERMIN extends E2_ComponentMAP 
{

	private CAL_ComponentMAP_MASK_TERMIN_USER  E2_ComponentMAP_TerminUser = null;
	private CAL_ModuleContainer_MASK           oModuleContainerMASK = null;
	
	public CAL_ComponentMAP_MASK_TERMIN(CAL_ModuleContainer_MASK ModuleContainerMASK)  throws myException
	{
		super(new CAL_SQLFieldMAP_TERMIN());
		
		this.oModuleContainerMASK = ModuleContainerMASK;
		
		SQLFieldMAP oSqlFM = this.get_oSQLFieldMAP();
		
		this.add_Component( new MyE2_DB_Label(oSqlFM.get_("ID_TERMIN")),new MyE2_String("ID_TERMIN"));
		this.add_Component( new MyE2_DB_TextField(oSqlFM.get_("DATUM")),new MyE2_String("Datum"));
		this.add_Component( new MyE2_DB_DoubleSelectFieldForTime(oSqlFM.get_("ZEIT_VON")),new MyE2_String("von"));
		this.add_Component( new MyE2_DB_DoubleSelectFieldForTime(oSqlFM.get_("ZEIT_BIS")),new MyE2_String("bis"));
		this.add_Component( new MyE2_DB_TextField(oSqlFM.get_("KURZTEXT")),new MyE2_String("Kurztext"));
		this.add_Component( new MyE2_DB_TextArea(oSqlFM.get_("LANGTEXT")),new MyE2_String("Langtext"));

		this.E2_ComponentMAP_TerminUser = new CAL_ComponentMAP_MASK_TERMIN_USER(oSqlFM,this.oModuleContainerMASK);

		this.get__Comp("DATUM").EXT().set_bDisabledFromBasic(true);

		((MyE2_DB_TextArea)this.get__Comp("LANGTEXT")).set_iWidthPixel(400);
		((MyE2_DB_TextArea)this.get__Comp("LANGTEXT")).set_iRows(5);
		
		((MyE2_DB_TextField)this.get__Comp("KURZTEXT")).set_iWidthPixel(400);
		

		
		
	}

	public CAL_ComponentMAP_MASK_TERMIN_USER get_E2_ComponentMAP_TerminUser() 
	{
		return E2_ComponentMAP_TerminUser;
	}

	public CAL_ModuleContainer_MASK get_oModuleContainerMASK() 
	{
		return oModuleContainerMASK;
	}

}
