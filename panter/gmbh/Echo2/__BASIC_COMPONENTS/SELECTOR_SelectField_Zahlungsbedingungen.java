package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.exceptions.myException;

public class SELECTOR_SelectField_Zahlungsbedingungen extends MyE2_SelectField
{

	public SELECTOR_SelectField_Zahlungsbedingungen() throws myException  {
		super();
	//	this.
	}
	
	public SELECTOR_SelectField_Zahlungsbedingungen(boolean bAutoInit) throws myException  {
		super();
		if (bAutoInit) {
			this._INIT();
		}
	}
	
	public SELECTOR_SelectField_Zahlungsbedingungen(boolean bAutoInit, int widthInPixel) throws myException  {
		super();
		if (bAutoInit) {
			this._INIT();
		}
		this.setWidth(new Extent(widthInPixel));
	}

	
	public SELECTOR_SelectField_Zahlungsbedingungen(boolean bAutoInit, MyE2_String Tooltips4Dropdown) throws myException  {
		super();
		if (bAutoInit) {
			this._INIT();
		}
		
		if (Tooltips4Dropdown!=null) {
			this.setToolTipText(Tooltips4Dropdown.toString());
		}
		
	}

	public void _INIT() throws myException {
		this.populateCombobox(
				new SELECT(_DB.ZAHLUNGSBEDINGUNGEN$BEZEICHNUNG,_DB.ZAHLUNGSBEDINGUNGEN$ID_ZAHLUNGSBEDINGUNGEN)
						.from(_DB.ZAHLUNGSBEDINGUNGEN)
						.orderBy(_DB.ZAHLUNGSBEDINGUNGEN$BEZEICHNUNG).toString(),
				null, false, true, false, false);
	}

	

}
