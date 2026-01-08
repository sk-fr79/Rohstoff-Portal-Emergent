package panter.gmbh.Echo2.Container;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.SplitPane;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;


public class E2_SplitPane_in_3_lines extends E2_ContentPane {
	
	private E2_ContentPane  pane_top = new E2_ContentPane(true);
	private E2_ContentPane  pane_mid = new E2_ContentPane(true);
	private E2_ContentPane  pane_bottom = new E2_ContentPane(true);
	
	private SplitPane       split1 = new SplitPane(SplitPane.ORIENTATION_VERTICAL);
	private SplitPane       split2 = new SplitPane(SplitPane.ORIENTATION_VERTICAL);
	
	public E2_SplitPane_in_3_lines() {
		super(true);
		
		this.add(this.split1);
		E2_ContentPane  pane_help = new E2_ContentPane(true);
		this.split1.add(this.pane_top);
		this.split1.add(pane_help);
		
		pane_help.add(this.split2);
		this.split2.add(this.pane_mid);
		this.split2.add(this.pane_bottom);
		
		this.split1.setResizable(true);
		this.split1.setSeparatorHeight(new Extent(0));
		this.split2.setResizable(true);
		this.split2.setSeparatorHeight(new Extent(0));
		
		this.split1.setSeparatorColor(new E2_ColorBase());
		this.split2.setSeparatorColor(new E2_ColorBase());
		
		
	}

	public E2_SplitPane_in_3_lines _set_to_top_pane(Component comp_top) {
		this.pane_top.add(comp_top);
		return this;
	}

	
	public E2_SplitPane_in_3_lines _set_to_mid_pane(Component comp_mid) {
		this.pane_mid.add(comp_mid);
		return this;
	}

	
	public E2_SplitPane_in_3_lines _set_to_bottom_pane(Component comp_bot) {
		this.pane_bottom.add(comp_bot);
		return this;
	}

	public E2_SplitPane_in_3_lines _set_height_top(int i_pixel) {
		this.split1.setSeparatorPosition(new Extent(i_pixel));
		return this;
	}

	public E2_SplitPane_in_3_lines _set_height_mid(int i_pixel) {
		this.split2.setSeparatorPosition(new Extent(i_pixel));
		return this;
	}
	
	public void set_separator_line1(int iSize, Color color) {
		this.split1.setSeparatorHeight(new Extent(iSize));
		this.split1.setSeparatorColor(color);
	}
	public void set_separator_line2(int iSize, Color color) {
		this.split2.setSeparatorHeight(new Extent(iSize));
		this.split2.setSeparatorColor(color);
	}
	
	
}
