package panter.gmbh.Echo2.components.E2_calendar;

import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.components.MyE2_Button;

public class E2_TF_4_Date_Button extends MyE2_Button {
	private boolean b_isActiveMonth = false;

	public E2_TF_4_Date_Button(String string) {
		super(string);
	}

	public boolean isActiveMonth() {
		return b_isActiveMonth;
	}

	public void setActiveMonth(boolean b_isActiveMonth) {
		if(!b_isActiveMonth){
			this.setForeground(new E2_ColorGray(128));
		}
		this.b_isActiveMonth = b_isActiveMonth;
	}

}
