package panter.gmbh.Echo2.components.activeReport_NG;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class AR_LabelInBorder extends AR_Label {

	private Border border = new Border(1, Color.BLACK, Border.STYLE_SOLID);
	
	
	public AR_LabelInBorder(	GridLayoutData 	layoutData, 	
								Object 			cText,
								MutableStyle 	style,
								Border          p_border
								) throws myException {
		super(layoutData, cText, style);
		if (p_border!=null) {
			this.border = p_border;
		}
	}


	@Override
	public Component comp() {
		GridLayoutData layoutData4component_in_border_Grid = new GridLayoutData();
		layoutData4component_in_border_Grid.setInsets(E2_INSETS.I(1,1,1,1));
		
		MutableStyle   style = new MutableStyle();
		style.setProperty(Grid.PROPERTY_BORDER, this.border);
		style.setProperty(Grid.PROPERTY_SIZE, 1);
		style.setProperty(Grid.PROPERTY_WIDTH, new Extent(100,Extent.PERCENT));
		style.setProperty(Grid.PROPERTY_HEIGHT, new Extent(100,Extent.PERCENT));
		
		MyE2_Grid grid = new MyE2_Grid();

		grid.setStyle(style);
		this.setLayoutData(layoutData4component_in_border_Grid);
		
		grid.add(this);
		return grid;

	}
	
}
