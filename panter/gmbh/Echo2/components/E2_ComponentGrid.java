package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.indep.exceptions.myException;


/*
 * panel, das es erlaubt, Component-groups zu gruppieren
 */
public class E2_ComponentGrid extends MyE2_Grid implements MyE2IF__Component
{

	public static GridLayoutData oLayoutData = null;
	static
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setInsets(new Insets(0,0,10,5));
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		oLayoutData = oLayout;
	}
	
	
	
	private MyE2EXT__Component 		oEXT = new MyE2EXT__Component(this);

	public E2_ComponentGrid(int iColumns)
	{
		super();
		this.setSize(iColumns);
		this.setStyle(new ownStyle());
	}

//	public E2_ComponentGrid(int iColumns,Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,Component oComp6)
//	{
//		super();
//		this.setSize(iColumns);
//		if (oComp1 != null) this.add(oComp1);
//		if (oComp2 != null) this.add(oComp2);
//		if (oComp3 != null) this.add(oComp3);
//		if (oComp4 != null) this.add(oComp4);
//		if (oComp5 != null) this.add(oComp5);
//		if (oComp6 != null) this.add(oComp6);
//
//		this.setStyle(new ownStyle());
//	}
//	

	public E2_ComponentGrid(int iColumns,Component... oCompArray)
	{
		super();
		this.setSize(iColumns);
		
		for (Component oComp: oCompArray) {
			// Manfred: 2015-05-19: hier auch auf null testen!
			if (oComp != null) 	this.add(oComp);
		}
		
		this.setStyle(new ownStyle());
	}
	

	
	/**
	 * 2015-01-27: neue methode, die komponenten, die in gridlayout haben, behalten das, ansonsten wird das 
	 *             uebergebene benutzt, sonst das in der statischen definition der klasse
	 * @param iColumns
	 * @param gridLayout
	 * @param oComp1
	 */
	public E2_ComponentGrid(int iColumns,GridLayoutData gridLayout, Component... oComp1)
	{
		super();
		this.setSize(iColumns);
		Component[] compliste = oComp1;
		
		for (Component oComp: compliste) {
			if (oComp.getLayoutData()!=null && oComp.getLayoutData() instanceof GridLayoutData) {
				super.add_SUPER(oComp);
			} else if (gridLayout != null) {
				oComp.setLayoutData(gridLayout);
				super.add_SUPER(oComp);
			} else {
				oComp.setLayoutData(E2_ComponentGrid.oLayoutData);
				super.add_SUPER(oComp);
			}
		}
		
		this.setStyle(new ownStyle());
	}
	
	
	
	
	
	
	
	public void add(Component oComp)
	{
		super.add(oComp);
		oComp.setLayoutData(oLayoutData);
	}
	
	

	private class ownStyle extends MutableStyle
	{

		public ownStyle()
		{
			super();
			this.setProperty( Grid.PROPERTY_INSETS, new Insets(0,2,0,2)); 
		}
		
	}
	
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
	{
		
	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	
}
