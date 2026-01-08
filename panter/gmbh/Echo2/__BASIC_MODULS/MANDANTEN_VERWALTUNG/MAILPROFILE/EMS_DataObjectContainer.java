package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V1;
import panter.gmbh.Echo2.RB.DATA.RB_StatementBuilderCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.exceptions.myException;

public class EMS_DataObjectContainer extends RB_DataobjectsCollector_V1
{

	private boolean Edit = false;                 //true=edit, false=view
	
	
	/**
	 * 
	 * @param id_Email_send_schablone (wenn null, dann leer, sonst kommts auf edit an)
	 * @param edit
	 * @throws myException 
	 */
	public EMS_DataObjectContainer(String id_Email_send_schablone, boolean edit) throws myException
	{
		super();
		this.Edit = edit;
		
		this.database_to_dataobject(id_Email_send_schablone);
		this.set_DBToolBox_FAB(new ownToolBox_FAB());
	}

	@Override
	public void database_to_dataobject(Object startPoint) throws myException {
		
		if (startPoint == null) {
			this.registerComponent(new RB_KM(_TAB.email_send_schablone), 
					new EMS_DataObject(new EMS_RECNEW_EMAIL_SEND_SCHABLONE(bibSES.get_MetaFieldDEF(_DB.EMAIL_SEND_SCHABLONE))));
			
		} else {
			String id_email_send_schablone = (String)startPoint;
			
			this.registerComponent(new RB_KM(_TAB.email_send_schablone), 
					new EMS_DataObject(new EMS_RECORD_EMAIL_SEND_SCHABLONE(id_email_send_schablone),this.Edit?MASK_STATUS.EDIT:MASK_STATUS.VIEW));
		}
		
	}

	@Override
	public void dataobject_to_database_connect_RB_MASK_DATA(RB_StatementBuilderCollector Statemenbuilder_Collector) throws myException {

	}

	private class ownToolBox_FAB extends MyDBToolBox_FAB {
		@Override
		public MyDBToolBox generate_INDIVIDUELL_DBToolBox(MyConnection conn) throws myException {
			return MyDBToolBox_FAB.generate_DBToolBox_WithOut_ID_MANDANT_AUTOMATIC(conn);
		}
	}



}
