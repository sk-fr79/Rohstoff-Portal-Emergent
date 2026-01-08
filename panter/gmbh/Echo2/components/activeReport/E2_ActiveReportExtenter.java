package panter.gmbh.Echo2.components.activeReport;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.components.MyE2_CheckBox;

public class E2_ActiveReportExtenter extends MyE2_CheckBox 
{

	public E2_ActiveReportExtenter() 
	{
		super();
		this.setStateIcon(E2_ResourceIcon.get_RI("submenue.png"));
		this.setSelectedStateIcon(E2_ResourceIcon.get_RI("submenuedown.png"));
		
		this.setSelected(true);
	}

	
	
}
