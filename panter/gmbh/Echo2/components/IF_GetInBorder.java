package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.exceptions.myException;

public interface IF_GetInBorder 
{
	public MyE2_Grid get_InBorderGrid(Border oBorder, Extent oExt, Insets oInsets)  throws myException;
	
	
	public default MyE2_Grid InBorderStd() {
		MyE2_Grid  g = new MyE2_Grid(1);
		
		MutableStyle  style = new MutableStyle();
		style.setProperty(Grid.PROPERTY_BORDER, new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		style.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		g.setStyle(style);
		
		GridLayoutData  gl = new GridLayoutData();
		gl.setInsets(E2_INSETS.I(1,1,1,1));
		
		if (this instanceof Component) {
			((Component)this).setLayoutData(gl);
			g.add_raw((Component)this);
		}
		
		return g;
	}

	public default MyE2_Grid InBorderCentered() {
		MyE2_Grid  g = new MyE2_Grid(1);
		
		MutableStyle  style = new MutableStyle();
		style.setProperty(Grid.PROPERTY_BORDER, new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		style.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		g.setStyle(style);
		
		if (this instanceof Component) {
			((Component)this).setLayoutData(new RB_gld()._center_mid()._ins(E2_INSETS.I(1,1,1,1)));
			g.add_raw((Component)this);
		}
		
		return g;
	}

}
