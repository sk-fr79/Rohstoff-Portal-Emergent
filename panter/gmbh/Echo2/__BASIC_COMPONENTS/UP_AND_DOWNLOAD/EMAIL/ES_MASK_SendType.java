package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.indep.exceptions.myException;


public class ES_MASK_SendType extends RB_SelectField {

	public ES_MASK_SendType() throws myException {
		super(EMAIL_SEND.send_type);
		
		this.set_ListenInhalt(ES_CONST.SEND_TYPE.BCC.get_dd_Array(true), false);
		this.setWidth(new Extent(500));
	}

	
	


}
