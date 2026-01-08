package panter.gmbh.Echo2.components.E2_calendar;

import java.util.Date;
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.E2_calendar.E2_calendar;
import panter.gmbh.Echo2.components.MultiValueSelector.E2IF_MultiValueSelectorItem;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public  class MD_CalenderInPopup extends E2_calendar  implements E2IF_MultiValueSelectorItem{

	private String           last_date_value = null;
	
	public MD_CalenderInPopup() throws myException {
		super(bibALL.get_cDateNOW());
		this.last_date_value=bibALL.get_cDateNOW();
	}
	


	@Override
	public void setValue(Object o) throws myException {
		String datum = bibALL.get_cDateNOW();
		
		if (o instanceof String) {
			MyDate  date = new MyDate((String)o);
			
			if (date.get_bOK()) {
				datum = date.get_cDateStandardFormat();
			}
		} 
		
		this.last_date_value= datum;
		this.SPRINGE_AUF_DATUM(this.last_date_value);
	}


	@Override
	public Object getValue()  throws myException {
		return this.last_date_value;
	}


	@Override
	public void do_day_button_action(ExecINFO execInfo) throws myException {
		MyE2_Button oDateButton = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
		this.last_date_value = ((myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_cDateFormatForMask();
		this.get_calendar_body().SPRINGE_AUF_DATUM(this.last_date_value );
	}


	@Override
	public Vector<Date> get_DatesToHighlight() throws myException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
