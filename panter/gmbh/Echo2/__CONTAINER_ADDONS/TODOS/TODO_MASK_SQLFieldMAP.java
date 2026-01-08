package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class TODO_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public TODO_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_TODO", "", false);
		
		this.get_("GENERIERUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		this.get_("ERLEDIGT").set_cDefaultValueFormated("N");
		this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED());

		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_TODO_WICHTIGKEIT","BESCHREIBUNG","ID_TODO_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_TODO_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());

		this.initFields();
	}

}
