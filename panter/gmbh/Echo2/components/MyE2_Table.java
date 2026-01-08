package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Table;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.table.DefaultTableModel;
import nextapp.echo2.app.table.TableCellRenderer;
import nextapp.echo2.app.table.TableColumnModel;
import nextapp.echo2.app.table.TableModel;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_Table extends Table  implements MyE2IF__Component, E2_IF_Copy
{
	private MyE2EXT__Component oEXT = new MyE2EXT__Component(this);
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
	}

	
	
	public MyE2_Table()
	{
		super();
		this.setStyle(new Style_Table_Normal(null));
		this.__define();
	}
	public MyE2_Table(int arg0, int arg1)
	{
		super(arg0, arg1);
		this.setStyle(new Style_Table_Normal(null));
		this.__define();
	}
	public MyE2_Table(TableModel arg0, TableColumnModel arg1)
	{
		super(arg0, arg1);
		this.setStyle(new Style_Table_Normal(null));
		this.__define();
	}
	public MyE2_Table(TableModel arg0)
	{
		super(arg0);
		this.setStyle(new Style_Table_Normal(null));
		this.__define();
	}

	public MyE2_Table(MutableStyle oStyle)
	{
		super();
		if (oStyle != null) this.setStyle(oStyle);
		this.__define();
	}
	public MyE2_Table(int arg0, int arg1,MutableStyle oStyle)
	{
		super(arg0, arg1);
		if (oStyle != null) this.setStyle(new Style_Table_Normal(null));
		this.__define();
	}
	public MyE2_Table(TableModel arg0, TableColumnModel arg1,MutableStyle oStyle)
	{
		super(arg0, arg1);
		if (oStyle != null) this.setStyle(oStyle);
		this.__define();
	}
	public MyE2_Table(TableModel arg0,MutableStyle oStyle)
	{
		super(arg0);
		if (oStyle !=null) this.setStyle(oStyle);
		this.__define();
	}

	public void set_ColumnWidth(Extent ex, int iColumn) throws myException
	{
		TableColumnModel oColumn = this.getColumnModel();
		if (oColumn.getColumnCount()<=iColumn)
			throw new myException("MyE2_Table:set_ColumnWidth:Column-Count too small");

		oColumn.getColumn(iColumn).setWidth(ex);
		
	}
	
	public void set_ColumnEmpty(int iColumn) throws myException
	{
		DefaultTableModel oTabMod = (DefaultTableModel)this.getModel();
		TableColumnModel oColumn = this.getColumnModel();
		if (oColumn.getColumnCount()<=iColumn)
			throw new myException("MyE2_Table:set_ColumnWidth:Column-Count too small");

		for (int i=0;i<oTabMod.getRowCount();i++)
		{
			oTabMod.setValueAt(new Label(""),iColumn,i); 
		}
	}
	
	
	
	private void __define()
	{
		this.setDefaultRenderer(Object.class, new StandardComponentRenderer());
		this.setDefaultHeaderRenderer(new StandardHeaderRenderer());
	}
	
	
	
	/*
	 * standard ist: dem tablemodel werden components uebergeben
	 */
	private class StandardComponentRenderer implements TableCellRenderer 
	{ 
		public Component getTableCellRendererComponent(Table table, Object value, int column, int row) 
		{ 
			if (value instanceof Component)
				return (Component)value;
			else if (value instanceof String)
				return new MyE2_Label(value);
			else
				return (new MyE2_Label("A@@@"));
			
		} 
		
	}

	private class StandardHeaderRenderer implements TableCellRenderer 
	{ 
		public Component getTableCellRendererComponent(Table table, Object value, int column, int row) 
		{ 
			if (value instanceof Component)
				return (Component)value;
			else if (value instanceof String)
				return new MyE2_Label(value);
			else
				return (new MyE2_Label("H@@@"));
		} 
	}

	

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		throw new myExceptionCopy(myExceptionCopy.ERROR_COPY_NOT_IMPLEMENTED);
	}


	
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}
	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	
	
	
	//2014-09-08: methoden um das objekt in ein grid einzuwickeln
	private Vector<MyE2IF__Component> 	vADDONS_IN_WRAP = new Vector<MyE2IF__Component>();
	private boolean 					b_OnComponentInFront = true;
	private int   						i_SpaceInPixel = 2;
	@Override
	public MyE2IF__Component ME() throws myException {
		MyE2_Grid oGridRueck = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridRueck.setSize(1+this.vADDONS_IN_WRAP.size());
		//die alte layoutdata sichern und an das aussengrid uebergeben
		LayoutData oLayoutThis = this.getLayoutData();
		GridLayoutData 	oLayoutInnen1 = new GridLayoutData();
		GridLayoutData 	oLayoutInnen2 = new GridLayoutData();
		oLayoutInnen1.setInsets(E2_INSETS.I(0,0,this.i_SpaceInPixel,0));
		oLayoutInnen1.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		oLayoutInnen2.setInsets(E2_INSETS.I(0,0,0,0));
		oLayoutInnen2.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		if (this.b_OnComponentInFront) {
			this.setLayoutData(oLayoutInnen1);
			oGridRueck.add_raw(this);
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			((Component)this.vADDONS_IN_WRAP.get(this.vADDONS_IN_WRAP.size()-1)).setLayoutData(oLayoutInnen2);
		} else {
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			this.setLayoutData(oLayoutInnen2);
			oGridRueck.add_raw(this);
		}
		
		oGridRueck.setLayoutData(oLayoutThis);
		return oGridRueck;
	}

	@Override
	public Component C_ME() throws myException {
		return (Component) this.ME();
	}

	@Override
	public Vector<MyE2IF__Component> get_vADDONS_IN_WRAP() throws myException {
		return vADDONS_IN_WRAP;
	}

	@Override
	public void set_ME_First(boolean ME_InFront) {
		this.b_OnComponentInFront = ME_InFront;
	}

	@Override
	public void set_SpaceInPX(int iSpace) {
		this.i_SpaceInPixel=iSpace;
	}

}
