/*
 * Created on Jun 21, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;




public class CAL_GridWithDayButtons extends MyE2_Grid
{
	
	private myDateHelper  			oDateHelper = null;
	private CAL_BasicModuleContainer 	 	oCalendarBasicRow = null;
	
	public CAL_GridWithDayButtons(CAL_BasicModuleContainer CalendarBasicRow) throws myException
	{
		super(2,MyE2_Grid.STYLE_GRID_NO_BORDER());
		this.oCalendarBasicRow = CalendarBasicRow;
	}

	
	//	after the calendar was clicked show up in order to show all the termins and add new termin and click into old termin
	 public void buildDay(myDateHelper   dateHelper) throws myException
	 {	
		 
		 if (dateHelper != null)
			 this.oDateHelper = 		dateHelper;
		 
		 if (this.oDateHelper == null)
			 throw new myException("MyColumnDay:buildDay:Object has no dateHelper");
		 

		 this.removeAll();	
		 
		 this.setColumnWidth(0,new Extent(200));
		 this.setColumnWidth(1,new Extent(200));
		 
		 //this.setBackground(new E2_ColorLight());	
		 this.setBorder(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));

		 
		 MyE2_Button buttonNewTermin = new MyE2_Button(new MyE2_String("Neuer Termin"));
		 buttonNewTermin.add_oActionAgent(new actionNewTermin());
		 
		 MyE2_String oTitle = new MyE2_String("Termine am   ");
		 oTitle.addUnTranslated(this.oDateHelper.get_cDateFormatForMask());
		 this.add(new MyE2_Label(oTitle, MyE2_Label.STYLE_NORMAL_BOLD_ITALIC()),2, new Insets(2,2,2,20));
		 this.add(buttonNewTermin,2, E2_INSETS.I_2_2_2_2);
		 
		 // showing existing termins		 
		 String cSQL = "select jt_termin_user.id_termin_user, NVL(jt_termin_user.is_owner,'N') from "+ bibE2.cTO()+".JT_TERMIN," +
					 bibE2.cTO()+".jt_termin_user where jt_termin_user.id_user ="+bibALL.get_ID_USER()+" and " +
					 		" jt_termin_user.ID_TERMIN = jt_termin.ID_TERMIN and " +
					 		" datum = '"+this.oDateHelper .get_cDateFormat_ISO_FORMAT()+"' ORDER BY ZEIT_VON";		 
		 
		 String cTermin[][]= bibDB.EinzelAbfrageInArray(cSQL,"@@@@@@");		
		 
		 if(cTermin.length<= 0)
		 {
			 this.add(new MyE2_Label(new MyE2_String("Keine Termine eingetragen "),MyE2_Label.STYLE_SMALL_ITALIC()),2,E2_INSETS.I_2_2_2_2);
		 }
		 else
		 {
			 for(int i = 0; i< cTermin.length;i++)		
			 {
				 CAL_TerminDataRecordHashMap oRecordMap = new CAL_TerminDataRecordHashMap(cTermin[i][0]);
				 this.add(new CAL_TerminButton_EDIT(oRecordMap,this), E2_INSETS.I_2_2_2_2);
				 if (cTermin[i][1].equals("Y"))
				 {
					 this.add(new CAL_TerminButton_SEND_MAIL(oRecordMap), E2_INSETS.I_2_2_2_2);
				 }
				 else
				 {
					 this.add(new MyE2_Label(""), E2_INSETS.I_2_2_2_2);
				 }
			 }
		 }
	 }
	 
	 
	 
	 private class actionNewTermin extends XX_ActionAgent
	 {
		public void executeAgentCode(ExecINFO oExecInfo)  throws myException
		{
			CAL_GridWithDayButtons oThis = CAL_GridWithDayButtons.this;
			CAL_ModuleContainer_MASK oContainer = null;
			try
			{
				oContainer = 
					new CAL_ModuleContainer_MASK(E2_ComponentMAP.STATUS_NEW_EMPTY,oThis.oCalendarBasicRow);
		
				E2_vCombinedComponentMAPs vCombinedComponentMAPs = oContainer.get_vCombinedComponentMAPs();

				// datum festlegen und id_user festlegen
				vCombinedComponentMAPs.get_oE2_ComponentMAP_MAIN().get_oSQLFieldMAP().get_("DATUM").set_cDefaultValueFormated(oThis.oDateHelper.get_cDateFormatForMask());
				((SQLFieldForRestrictTableRange)((E2_ComponentMAP)vCombinedComponentMAPs.get(1)).get_oSQLFieldMAP().get_("ID_USER")).set_cRestrictionValue_IN_DB_FORMAT(bibALL.get_ID_USER());
				
				oContainer.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_NEW_EMPTY,null);

				oContainer.CREATE_AND_SHOW_POPUPWINDOW(null,null,null);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("MyColumnDay:actionNewTermin:Error: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				if (oContainer != null)
					oContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		}
		 
	 }
	 
	 
	 public void removeAll()
	 {
		 super.removeAll();
		 this.setBackground(null);	
		 this.setBorder(null);
	 }


	public CAL_BasicModuleContainer get_oCalendarBasicRow() 
	{
		return oCalendarBasicRow;
	}
	

}
