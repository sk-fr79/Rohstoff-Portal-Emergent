package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._INFO_NT;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

public class AI__SelectSettings extends MyE2_SelectField implements IF_Saveable{

	private MyE2_String  	beschriftung=null;
	private int 			iWidth = 100;
	
	public AI__SelectSettings(MyE2_String cText, String[] aDefArray, String cdefaultValue, Extent oWidth, MyE2_String toolTips) throws myException {
		super(aDefArray, cdefaultValue, false, oWidth);
		this.beschriftung = cText;
		if (oWidth!=null && oWidth.getUnits()==Extent.PX) {
			this.iWidth = oWidth.getValue();
		}
		if (toolTips!=null) {
			this.setToolTipText(toolTips.toString());
		}
	}

	@Override
	public String get_value_to_save() throws myException {
		return this.get_ActualWert();
	}

	@Override
	public void restore_value(String value) throws myException {
		this.set_ActiveValue_OR_FirstValue(value);
	}

	@Override
	public void set_component_to_status_not_saved() throws myException {
		this.setSelectedIndex(2);
	}

	@Override
	public Component get_Comp() throws myException {
		MyE2_Grid grid = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		grid.setColumnWidth(0, new Extent(60));
		grid.setColumnWidth(1, new Extent(this.iWidth));

		grid.add(new MyE2_Label(this.beschriftung), E2_INSETS.I(0,0,3,0));
		grid.add(this, E2_INSETS.I(0,0,0,0));
		
		return grid;
	}

	
	@Override
	public void add_action(XX_ActionAgent agent) throws myException {
		super.add_oActionAgent(agent);
	}

	
	
}
