package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.ColumnLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.indep.exceptions.myException;


/*
 * panel, das es erlaubt, bedienelemente zu gruppieren
 */
public class E2_ComponentGroupVertical extends MyE2_Column  implements MyE2IF__Component
{
	
	public static ColumnLayoutData ownColLayout=null;;
	static
	{
		ColumnLayoutData oCL = new ColumnLayoutData();
		oCL.setInsets(new Insets(2,2,2,2));
		oCL.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		ownColLayout = oCL;
	}

	
	
	private MyE2EXT__Component 		oEXT = new MyE2EXT__Component(this);

	public E2_ComponentGroupVertical()
	{
		super();
		this.setStyle(new ownStyle(0));
	}

	public E2_ComponentGroupVertical(Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5)
	{
		super();
		this.setStyle(new ownStyle(0));
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
	}
	
	
	/**
	 * Neuer Konstruktor mit n Componenten
	 * @param oInsets
	 * @param iBorder
	 * @param oComp
	 */
	public E2_ComponentGroupVertical(Insets oInsets,int iBorder, Component... oComps){
		super();
		this.setStyle(new ownStyle(iBorder, oInsets));
		for(Component o: oComps){
			if (o != null) this.add(o,E2_INSETS.I_0_0_0_0);
		}
	}
	
	
	public void add(Component oComp)
	{
		super.add(oComp);
		oComp.setLayoutData(ownColLayout);
	}
	
	
	/**
	 * fügt eine Komponente mit eigenem Inset ein
	 * @author manfred
	 * @date 23.09.2016
	 *
	 * @param oComp
	 * @param oInsets
	 * @return
	 */
	public E2_ComponentGroupVertical addComponent(Component oComp, Insets oInsets){
		super.add(oComp);
		
		ColumnLayoutData oCL = new ColumnLayoutData();
		oCL.setInsets(oInsets);
		oCL.setAlignment(ownColLayout.getAlignment());
		oComp.setLayoutData(oCL);
		return this;
	}
	
	
	private class ownStyle extends MutableStyle
	{

		public ownStyle(int iBorder)
		{
			super();
			if (iBorder>0) this.setProperty( Column.PROPERTY_BORDER, new Border(iBorder, new E2_ColorBase(-20), Border.STYLE_OUTSET));
			this.setProperty( Column.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		}
		
		public ownStyle(int iBorder, Insets oInsets){
			super();
			if (iBorder>0) this.setProperty( Column.PROPERTY_BORDER, new Border(iBorder, new E2_ColorBase(-20), Border.STYLE_OUTSET));
			this.setProperty( Column.PROPERTY_INSETS, oInsets); 
		}
		
	}

	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
	{
		for (int i=0;i<this.getComponentCount();i++)
		{
			if (this.getComponent(i) instanceof MyE2IF__Component)
				((MyE2IF__Component)this.getComponent(i)).set_bEnabled_For_Edit(bEnabled && this.EXT().is_ValidEnableAlowed());
		}
	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}
	
	
}
