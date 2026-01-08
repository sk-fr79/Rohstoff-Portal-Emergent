package panter.gmbh.Echo2.Factorys;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.layout.GridLayoutData;

public class GLD extends GridLayoutData  {

	private GLD(Color col, int span, int alignHor)	{
		super();
		this.setBackground(col);
		this.setColumnSpan(span);
		this.setAlignment(new Alignment(alignHor, Alignment.CENTER));
	}
	public static GLD L(Color col, int span, int alignHor) {
		return new GLD(col,span,alignHor);
	}

	
}
