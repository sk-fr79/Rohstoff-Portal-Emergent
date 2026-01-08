package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportInfoUndPasswort;

import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.COMP.RB_DoubleText_4_PW;
import panter.gmbh.Echo2.RB.COMP.RB_DoubleText_4_PW_complexity.SecurityLevel;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class IP_PasswordField extends RB_DoubleText_4_PW {

	
	public IP_PasswordField(  	IF_Field 		field, 
								int 			minPasswordLength,	
								SecurityLevel 	e_securityLevel, 
								int 			iWidthInPixel, 
								boolean 		b_isMasked) throws myException {
		
		super(field, minPasswordLength, e_securityLevel, iWidthInPixel, b_isMasked);
	}

	public IP_PasswordField(IF_Field field, int minPasswordLength,SecurityLevel e_securityLevel, int iWidthInPixel)	throws myException {
		super(field, minPasswordLength, e_securityLevel, iWidthInPixel);
	}

	public IP_PasswordField(IF_Field field, SecurityLevel e_securityLevel,int iWidthInPixel) throws myException {
		super(field, e_securityLevel, iWidthInPixel);
	}

	@Override
	public void build_component_4_mask(TextField tf1, TextField tf2) 	throws myException {
		this.removeAll();
		this.setSize(4);
		this.add(tf1, E2_INSETS.I(0,0,0,0));
		this.add(new MyE2_Label(new MyE2_String(" ...bitte wiederholen: ")), E2_INSETS.I(10,0,0,0));
		this.add(tf2, E2_INSETS.I(0,0,0,0));
	}

}
