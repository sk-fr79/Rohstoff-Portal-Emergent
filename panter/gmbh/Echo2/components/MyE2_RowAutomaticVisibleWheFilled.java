package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.RowLayoutData;

public class MyE2_RowAutomaticVisibleWheFilled extends MyE2_Row {

	public MyE2_RowAutomaticVisibleWheFilled() {
		super();
		this.setVisible(false);
	}

	public MyE2_RowAutomaticVisibleWheFilled(MutableStyle oStyle) {
		super();
		this.setVisible(false);
	}

	
	public void add(Component oComp) {
		super.add(oComp);
		this.setVisible(true);
	}

	
	
	public void add(Component oComp, Insets oInsets)
	{
		super.add(oComp);
		this.setVisible(true);
	}


	public void add(Component oComp, RowLayoutData oRowLayoutData)
	{
		super.add(oComp);
		this.setVisible(true);
	}

	

	public void addAfterRemoveAll(Component oComp, Insets oInsets)
	{
		super.removeAll();
		super.add(oComp);
		this.setVisible(true);
	}


	public void addAfterRemoveAll(Component oComp, RowLayoutData oRowLayoutData)
	{
		super.removeAll();
		super.add(oComp);
		this.setVisible(true);
	}


	
	
}
