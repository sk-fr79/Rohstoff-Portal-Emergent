package panter.gmbh.Echo2.components.E2_calendar;

import java.util.Date;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public class E2_text_with_date_popup extends MyE2_Grid {

	private E2_BasicModuleContainer container = null;
	private MyE2_TextField  		tf_date = 				new MyE2_TextField("", 100, 10);
	private MyE2_Button             bt_calendar_start_popup=new MyE2_Button(E2_ResourceIcon.get_RI("calendar_mini.png"),true);
	private MyE2_Button             bt_eraser = 			new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"),true);
	
	private static int[]            i_breite = {100,20,20};
	
	public E2_text_with_date_popup() {
		super(i_breite,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.add(this.tf_date,E2_INSETS.I(0,1,5,1));
		this.add(this.bt_eraser,E2_INSETS.I(0,1,5,1));
		this.add(this.bt_calendar_start_popup,E2_INSETS.I(0,1,5,1));
		
		this.bt_calendar_start_popup.add_oActionAgent(new ownActionStartPopup());
		this.bt_eraser.add_oActionAgent(new ownActionCleanTextFeld());
	}

	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException {
		this.tf_date.set_bEnabled_For_Edit(bEnabled);
		this.bt_calendar_start_popup.set_bEnabled_For_Edit(bEnabled);
		this.bt_eraser.set_bEnabled_For_Edit(bEnabled);
	
	}

	private class ownActionStartPopup extends XX_ActionAgent  {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_text_with_date_popup oThis = E2_text_with_date_popup.this;
			
			oThis.container = new ownBasicContainer();
			
			ownCalendar cal = new ownCalendar();
			
			cal.SPRINGE_AUF_DATUM(oThis.tf_date.getText());
			
			oThis.container.add(cal,E2_INSETS.I(4,4,4,1));
			
			oThis.container.CREATE_AND_SHOW_POPUPWINDOW(new Extent(210), new Extent(280), new MyE2_String("Datum wählen"));
		}
	}
	
	
	private class ownActionCleanTextFeld extends XX_ActionAgent {
		
		public ownActionCleanTextFeld()	{
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
			E2_text_with_date_popup.this.tf_date.setText("");
		}
	}

	private class ownCalendar extends E2_calendar {

		public ownCalendar() throws myException {
			super(E2_text_with_date_popup.this.tf_date.getText());
		}

		@Override
		public void do_day_button_action(ExecINFO execInfo) throws myException {
			MyE2_Button oDateButton = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
			String  selected_date_formated = ((myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_cDateFormatForMask();
			if (E2_text_with_date_popup.this.container != null) {
				container.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
			E2_text_with_date_popup.this.tf_date.setText(selected_date_formated);
		}

		@Override
		public Vector<Date> get_DatesToHighlight() throws myException {
			return null;
		}
		
	}
	
	
	private class ownBasicContainer extends E2_BasicModuleContainer {
	}


	public MyE2_TextField get_tf_date() {
		return this.tf_date;
	}

	public MyE2_Button get_bt_calendar() {
		return this.bt_calendar_start_popup;
	}

	public MyE2_Button get_bt_eraser() {
		return this.bt_eraser;
	}
	
}
