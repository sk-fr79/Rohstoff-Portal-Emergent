package panter.gmbh.Echo2.Container;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.FillImage;
import nextapp.echo2.app.FillImageBorder;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.ResourceImageReference;

public class E2_FillImageBorder extends FillImageBorder
{

	public E2_FillImageBorder()
	{
		super();
		this.setBorders();
	}

	public E2_FillImageBorder(Color color, Insets borderInsets,	Insets contentInsets)
	{
		super(color, borderInsets, contentInsets);
		this.setBorders();
	}
	
	private void setBorders()
	{
		//ResourceImageReference oRSI = new ResourceImageReference("/resources/iconsng/leer.png");
		ResourceImageReference oRSDark = new ResourceImageReference("/resources/iconsng/grau_120.png");
		ResourceImageReference oRSLight = new ResourceImageReference("/resources/iconsng/grau_120.png");
		
		
		this.setFillImage(FillImageBorder.BOTTOM, new FillImage(oRSLight));
		this.setFillImage(FillImageBorder.BOTTOM_LEFT, new FillImage(oRSLight));
		this.setFillImage(FillImageBorder.BOTTOM_RIGHT, new FillImage(oRSLight));
		this.setFillImage(FillImageBorder.LEFT, new FillImage(oRSDark));
		this.setFillImage(FillImageBorder.RIGHT, new FillImage(oRSLight));
		this.setFillImage(FillImageBorder.TOP, new FillImage(oRSDark));
		this.setFillImage(FillImageBorder.TOP_LEFT, new FillImage(oRSDark));
		this.setFillImage(FillImageBorder.TOP_RIGHT, new FillImage(oRSDark));
		
	}

}
