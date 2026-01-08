package panter.gmbh.Echo2.components;


import java.util.ArrayList;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;

public class E2_Grid4Mask extends MyE2_Grid {

	private ArrayList<Component> compTitel = new ArrayList<Component>();
	private ArrayList<Component> compElement = new ArrayList<Component>();
	
	private GridLayoutData  	 glTitle = MyE2_Grid.LAYOUT_CENTER_CENTER( E2_INSETS.I(2,1,2,1));
	private GridLayoutData  	 glElement = MyE2_Grid.LAYOUT_CENTER_CENTER( E2_INSETS.I(2,1,2,1));
		
	public E2_Grid4Mask() {
		super();
	}
	
	public E2_Grid4Mask(MutableStyle oStyle) {
		super(oStyle);
	}

	public E2_Grid4Mask set_Cols(int[] width_in_pixel) {
		super.set_Spalten(width_in_pixel);
		return this;
	}
	
	public E2_Grid4Mask set_LayoutData(GridLayoutData p_glTitle, GridLayoutData p_glElement) {
		this.glTitle = p_glTitle;
		this.glElement = p_glElement;
		this.fill();
		return this;
	}
	
	public E2_Grid4Mask set_LayoutStandard4CenteredView() {
		this.set_LayoutData(MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(1,1,1,1), new E2_ColorDDark(), 1, 1), MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(1,1,1,1), new E2_ColorBase(), 1, 1));
		return this;
	}
	
	
	
	public E2_Grid4Mask add(Component title, Component comp) {
		this.compTitel.add(title);
		this.compElement.add(comp);
		this.fill();
		return this;
	}
	

	private void fill() {
		this.removeAll();
		for (Component c: this.compTitel) {
			this.add(c,this.glTitle);
		}
		
		for (Component c: this.compElement) {
			this.add(c,this.glElement);
		}
	}

}
