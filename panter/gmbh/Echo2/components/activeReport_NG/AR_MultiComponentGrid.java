package panter.gmbh.Echo2.components.activeReport_NG;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.components.MyE2_Grid;

public class AR_MultiComponentGrid extends MyE2_Grid  implements IF_AR_Component {

	private GridLayoutData gl = null;
	
	
	public AR_MultiComponentGrid(GridLayoutData layoutData, MutableStyle style,Component... components ) {
		super();
		
		this.setSize(components.length);
		this.gl= layoutData;
		
		
		if (style != null) {this.setStyle(style);}
		this.setLayoutData(layoutData);
		
		for (Component c: components) {
			this.add_raw(c);
		}
	}

	@Override
	public GridLayoutData get_layoutData() {
		return this.gl;
	}

	@Override
	public Component comp() {
		return this;
	}

}
