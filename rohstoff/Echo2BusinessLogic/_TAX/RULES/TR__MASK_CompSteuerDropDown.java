package rohstoff.Echo2BusinessLogic._TAX.RULES;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.RB.IF.IF_WrappedSimple;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_STEUERDEF;

public class TR__MASK_CompSteuerDropDown extends TAX__DD_STEUERDEF implements IF_WrappedSimple {
	
	private MyE2_Grid   gridContainer = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

	public TR__MASK_CompSteuerDropDown(SQLField osqlField, int iWidth, 	boolean bUseInList) throws myException {
		super(osqlField, iWidth, bUseInList);
	}

//	@Override
//	public Component get_containerWith_ME() {
//		this.gridContainer.removeAll();
//		this.gridContainer.add(this,E2_INSETS.I(0,0,0,0));
//		return this.gridContainer;
//	}
//
//	@Override
//	public Component get_wrappedComponent() {
//		return this;
//	}

	public MyE2_Grid get_GridContainer() {
		return gridContainer;
	}

	@Override
	public Component box(MyE2IF__Component comp) {
		this.gridContainer.removeAll();
		this.gridContainer.add(this,E2_INSETS.I(0,0,0,0));
		return this.gridContainer;
	}
	
	
}
