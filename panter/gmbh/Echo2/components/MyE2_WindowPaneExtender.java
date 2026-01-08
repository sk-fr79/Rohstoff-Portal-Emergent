package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Extent;

public class MyE2_WindowPaneExtender {
	
	private Extent extLeft = new Extent(20);
	private Extent extTop = new Extent(20);
	private Extent extWidth = new Extent(1000);
	private Extent extHeight = new Extent(500);
	
	public MyE2_WindowPaneExtender(Extent ext_Left, Extent ext_Top, Extent ext_Width, Extent ext_Height) {
		super();
		this.extLeft = ext_Left;
		this.extTop = ext_Top;
		this.extWidth = ext_Width;
		this.extHeight = ext_Height;
	}
	
	
	public MyE2_WindowPaneExtender(Extent ext_Width, Extent ext_Height) {
		super();
		this.extLeft = null;
		this.extTop = null;
		this.extWidth = ext_Width;
		this.extHeight = ext_Height;
	}
	
	
	

	public Extent get_extLeft() {
		return extLeft;
	}

	public void set_xtLeft(Extent extLeft) {
		this.extLeft = extLeft;
	}

	public Extent get_extTop() {
		return extTop;
	}

	public void set_extTop(Extent extTop) {
		this.extTop = extTop;
	}

	public Extent get_extWidth() {
		return extWidth;
	}

	public void set_extWidth(Extent extWidth) {
		this.extWidth = extWidth;
	}

	public Extent get_extHeight() {
		return extHeight;
	}

	public void set_extHeight(Extent extHeight) {
		this.extHeight = extHeight;
	}
	
	
	
}
