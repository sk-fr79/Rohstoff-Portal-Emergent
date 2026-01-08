package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V1;
import panter.gmbh.Echo2.RB.DATA.RB_StatementBuilderCollector;
import panter.gmbh.indep.exceptions.myException;

public class ADK_M_Dataobjects_Container extends RB_DataobjectsCollector_V1 {

	private RB__CONST.MASK_STATUS f_status = null;
	
	public ADK_M_Dataobjects_Container(String id_adressklasse_def, RB__CONST.MASK_STATUS status) throws myException {
		super();
		this.f_status=status;
		
		this.database_to_dataobject(id_adressklasse_def);
	}

	@Override
	public void database_to_dataobject(Object id_adressklasse_def) throws myException {
		if (id_adressklasse_def==null) {
			this.registerComponent(new ADK_M_Key(), new ADK_M_dataObject());
		} else {
			this.registerComponent(new ADK_M_Key(), new ADK_M_dataObject((String)id_adressklasse_def,this.f_status));
		}
	}

	@Override
	public void dataobject_to_database_connect_RB_MASK_DATA(RB_StatementBuilderCollector Statemenbuilder_Collector)	throws myException {

	}


}
