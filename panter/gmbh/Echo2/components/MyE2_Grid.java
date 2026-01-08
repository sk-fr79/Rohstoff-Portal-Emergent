package panter.gmbh.Echo2.components;

import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.BasicInterfaces.IF_Border;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_Formatable;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_Formatable_Container;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.utils.myCoordinates;

public class MyE2_Grid extends Grid implements MyE2IF__Component,E2_IF_Copy, IF_GetInBorder, IF_ADDING_Allowed, IF_BaseComponent4Mask, IF_Formatable_Container, IF_Border<MyE2_Grid>
{


	
	private MyE2EXT__Component oEXT = new MyE2EXT__Component(this);
	
	/*
	 * zaehler fuer die fortlaufenden eingefuegten komponenten
	 */
	private int iNextCol = 0;
	
	

	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
	}

	
	public void removeAll()
	{
		super.removeAll();
		this.iNextCol = 0;
	}
	
	public MyE2_Grid()
	{
		super();
		this.setStyle(new ownGridStyle(1, new Insets(2,2)));
	}
	
	public MyE2_Grid(MutableStyle oStyle)
	{
		super();
		if (oStyle != null) this.setStyle(oStyle);
	}

	public MyE2_Grid(int iNumCols)
	{
		super(iNumCols);
		this.setStyle(new ownGridStyle(1, new Insets(2,2)));
	}
	
	public MyE2_Grid(int iNumCols, int iBorderSize)
	{
		super(iNumCols);
		this.setStyle(new ownGridStyle(iBorderSize, new Insets(2,2)));
	}

	
	public MyE2_Grid(int iNumCols,MutableStyle oStyle)
	{
		super(iNumCols);
		if (oStyle != null) this.setStyle(oStyle);
	}

	
	
	public MyE2_Grid(int[] iSpalten,int iBorderSize)
	{
		super(iSpalten.length);
		for (int i=0;i<iSpalten.length;i++)
		{
			this.setColumnWidth(i,new Extent(iSpalten[i]));
		}
		this.setStyle(new ownGridStyle(iBorderSize, new Insets(2,2)));
	}
	
	public MyE2_Grid(int[] iSpalten,MutableStyle oStyle)
	{
		super(iSpalten.length);
		for (int i=0;i<iSpalten.length;i++)
		{
			this.setColumnWidth(i,new Extent(iSpalten[i]));
		}
		if (oStyle != null) this.setStyle(oStyle);
	}
	
	
	public MyE2_Grid(int[] iSpalten,MutableStyle oStyle, boolean bScaleRowsTo100Percent)
	{
		super(iSpalten.length);
		
		if (!bScaleRowsTo100Percent)
		{
			for (int i=0;i<iSpalten.length;i++)
			{
				this.setColumnWidth(i,new Extent(iSpalten[i]));
			}
		}
		else
		{
			int iBreitePixel = 0;
			for (int i=0;i<iSpalten.length;i++)
			{
				iBreitePixel += iSpalten[i];
			}

			for (int i=0;i<iSpalten.length;i++)
			{
				Double dAnteil = new Double((iSpalten[i]*100))/new Double(iBreitePixel);
				this.setColumnWidth(i,new Extent(new Integer(dAnteil.intValue()),Extent.PERCENT));
			}
		}
		
		if (oStyle != null) this.setStyle(oStyle);
	}
	
	
	
	
	public MyE2_Grid(int iSpalten, MutableStyle oStyle, GridLayoutData gl, Component... components) {
		this(iSpalten,oStyle);
		for (Component c: components) {
			this.add(c,gl);
		}
	}
	

	public MyE2_Grid(int iAbstand, Component... components) {
		this(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		for (Component c: components) {
			this.add(c,E2_INSETS.I(0,0,0,iAbstand));
		}
	}
	
	
	
	
	
	
	//2011-06-06: neues breiten-setting
	public void set_Spalten(int[] iSpalten)
	{
		this.setSize(iSpalten.length);
		for (int i=0;i<iSpalten.length;i++)
		{
			this.setColumnWidth(i,new Extent(iSpalten[i]));
		}
	}
	
	

	/**
	 * 2013-11-15: 0 setzen der folgenden zeile
	 * @param iZaehler
	 */
	public void set_SpaltenZaehler(int iZaehler) {
		this.iNextCol=iZaehler;
	}
	
	
	
	public void add(Component oComp)
	{
		super.add(oComp);
		
		int iColSpan = 1;
		int iPosTest = 0;
		
		if (oComp.getLayoutData() != null)
			if (oComp.getLayoutData() instanceof GridLayoutData)
			{
				iColSpan = ((GridLayoutData)oComp.getLayoutData()).getColumnSpan();
				iPosTest = this.iNextCol+iColSpan;
				if (iPosTest> this.getSize())
				{
					//neues layout-data festlegen
					GridLayoutData  oGLNEU = new GridLayoutData();
					oGLNEU.setAlignment(((GridLayoutData)oComp.getLayoutData()).getAlignment());
					oGLNEU.setBackground(((GridLayoutData)oComp.getLayoutData()).getBackground());
					oGLNEU.setBackgroundImage(((GridLayoutData)oComp.getLayoutData()).getBackgroundImage());
					oGLNEU.setInsets(((GridLayoutData)oComp.getLayoutData()).getInsets());
					oGLNEU.setRowSpan(((GridLayoutData)oComp.getLayoutData()).getRowSpan());
					
					oGLNEU.setColumnSpan(1);
					
					oComp.setLayoutData(oGLNEU);
//					
//					((GridLayoutData)oComp.getLayoutData()).setColumnSpan(1);
					iColSpan = 1;
				}
			}
		
//		iPosTest = this.iNextCol+iColSpan;

		if (iColSpan>=2)
			this.iNextCol+=iColSpan;
		else
			this.iNextCol++;
		
		if (this.iNextCol>=this.getSize())
			this.iNextCol=0;
	}
	
	
	/**
	 * add-methode, die nicht das an der komponente haftende layout-data aendert
	 * @param oComp
	 */
	public void add_SUPER(Component oComp)
	{
		super.add(oComp);
		
		int iColSpan = 1;
		
		if (oComp.getLayoutData() != null)
		{
			if (oComp.getLayoutData() instanceof GridLayoutData)
			{
				iColSpan = ((GridLayoutData)oComp.getLayoutData()).getColumnSpan();
			}
		}

		if (iColSpan>=2)
			this.iNextCol+=iColSpan;
		else
			this.iNextCol++;
		
		if (this.iNextCol>=this.getSize())
			this.iNextCol=0;
	}

	
	
	
	public void add(Component oComp, Insets oInsets)
	{
		super.add(oComp);
		oComp.setLayoutData(new ownLayout(oInsets, 0, 1, null));
		this.iNextCol++;
		if (this.iNextCol>=this.getSize())
			this.iNextCol=0;
	}

	public void add(Component oComp, GridLayoutData oLayoutData)
	{
		super.add(oComp);
		oComp.setLayoutData(oLayoutData);
		
		int iPosTest = this.iNextCol+oLayoutData.getColumnSpan();

		if (iPosTest> this.getSize())
			oLayoutData.setColumnSpan(1);

		
		if (oLayoutData.getColumnSpan()>=2)
			this.iNextCol+=oLayoutData.getColumnSpan();
		else
			this.iNextCol++;
		
		
		if (this.iNextCol>=this.getSize())
			this.iNextCol=0;
	}


	/**
	 * 2013-11-15: neuen add_raw-komponente
	 * @param oComp
	 * @param oLayoutData
	 */
	public void add_RAW(Component oComp, GridLayoutData oLayoutData)
	{
		super.add(oComp);
		oComp.setLayoutData(oLayoutData);
	}


	
	/**
	 * 2014-09-05: neuen add_raw-komponente, keinerlei layout-data-erzeugung
	 * @param oComp
	 */
	public void add_RAW_noLayoutData(Component oComp)
	{
		super.add(oComp);
	}


	
	/**
	 * 2014-11-11: add component, autoextent Size
	 * @param oComp
	 * @param oLayoutData
	 */
	public void add_RAW_AutoResize(Component o_Comp, Extent extBreite,  GridLayoutData oLayoutData)
	{
		super.add(o_Comp);
		
	
		if (oLayoutData!=null) {
			o_Comp.setLayoutData(oLayoutData);
		}
		
		int iSize = 0;
		Component[] arr_comp = this.getComponents();
		
		if (arr_comp!=null && arr_comp.length>0) {
			for (int i=0; i<arr_comp.length;i++) {
				Component oComp = arr_comp[i];
				if (oComp.getLayoutData()!=null && oComp.getLayoutData() instanceof GridLayoutData) {
				  iSize += ((GridLayoutData)oComp.getLayoutData()).getColumnSpan();
				} else {
					iSize++;
				}
			}
		}
		if (iSize==0) {
			iSize=1;
		}
		
		this.setSize(iSize);
		
		if (extBreite!=null) {
			this.setColumnWidth(iSize-1, extBreite);
		}
		
	}

	
	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param oInsets
	 * @throws myException
	 */
	public void add(Component oComp,int iiColSpan, Insets oInsets)
	{
		int iColSpan = iiColSpan;
		
		if (iColSpan == 0)
			iColSpan =1;
		
		int iPosTest = this.iNextCol+iColSpan;
		
		if (iPosTest> this.getSize())
			iColSpan = 1;
		
		iPosTest = this.iNextCol+iColSpan;
		
		super.add(oComp);
		oComp.setLayoutData(new ownLayout(oInsets,iColSpan, 1, null));
		
		this.iNextCol = iPosTest;
		if (this.iNextCol>=this.getSize())
			this.iNextCol=0;
		
	}
	

	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public void add(Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment)
	{
		int iColSpan = iiColSpan;
		int iRowSpan = iiRowSpan;
		
		if (iColSpan == 0)
			iColSpan =1;

		if (iRowSpan == 0)
			iRowSpan =1;
		
		int iPosTest = this.iNextCol+iColSpan;
		
		if (iPosTest> this.getSize())
			iColSpan = 1;
		
		iPosTest = this.iNextCol+iColSpan;
		
		super.add(oComp);
		oComp.setLayoutData(new ownLayout(oInsets,iColSpan, iRowSpan, oAlignment));
		
		this.iNextCol = iPosTest;
		if (this.iNextCol>=this.getSize())
			this.iNextCol=0;
		
	}


	
	/**
	 * 2013-11-15: neues add-raw
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public void add_RAW(Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment)
	{
		int iColSpan = iiColSpan;
		int iRowSpan = iiRowSpan;
		
		if (iColSpan == 0)
			iColSpan =1;

		if (iRowSpan == 0)
			iRowSpan =1;
		
		super.add(oComp);
		oComp.setLayoutData(new ownLayout(oInsets,iColSpan, iRowSpan, oAlignment));
		
	}


	
	
	
	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public void add_ext(Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment)
	{
		int iColSpan = iiColSpan;
		int iRowSpan = iiRowSpan;
		
		if (iColSpan == 0)
			iColSpan =1;

		if (iRowSpan == 0)
			iRowSpan =1;
		
		if (this.getOrientation() == ORIENTATION_HORIZONTAL){
			
			int iPosTest = this.iNextCol+iColSpan;
			
			if (iPosTest> this.getSize())
				iColSpan = 1;
			
			iPosTest = this.iNextCol+iColSpan;
			
			super.add(oComp);
			oComp.setLayoutData(new ownLayout(oInsets,iColSpan, iRowSpan, oAlignment));
			
			this.iNextCol = iPosTest;
			if (this.iNextCol>=this.getSize())
				this.iNextCol=0;
			
		} else {
			
			int iPosTest = this.iNextCol+iRowSpan;
			
			if (iPosTest> this.getSize())
				iRowSpan = 1;
			
			iPosTest = this.iNextCol+iRowSpan;
			
			super.add(oComp);
			oComp.setLayoutData(new ownLayout(oInsets,iColSpan, iRowSpan, oAlignment));
			
			this.iNextCol = iPosTest;
			if (this.iNextCol>=this.getSize())
				this.iNextCol=0;
			
		}
		
		
	}

	
	
	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @param oCol
	 * @throws myException
	 */
	public void add(Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment, Color oCol)
	{
		int iColSpan = iiColSpan;
		int iRowSpan = iiRowSpan;
		
		if (iColSpan == 0)
			iColSpan =1;

		if (iRowSpan == 0)
			iRowSpan =1;
		
		int iPosTest = this.iNextCol+iColSpan;
		
		if (iPosTest> this.getSize())
			iColSpan = 1;
		
		iPosTest = this.iNextCol+iColSpan;
		
		super.add(oComp);
		oComp.setLayoutData(new ownLayout(oInsets,iColSpan, iRowSpan, oAlignment, oCol));
		
		this.iNextCol = iPosTest;
		if (this.iNextCol>=this.getSize())
			this.iNextCol=0;
		
	}

	
	
	
	public void addAfterRemoveAll(Component oComp)
	{
		super.removeAll();
		super.add(oComp);
		
		int iColSpan = 1;
		int iPosTest = 0;
		
		if (oComp.getLayoutData() != null)
			if (oComp.getLayoutData() instanceof GridLayoutData)
			{
				iColSpan = ((GridLayoutData)oComp.getLayoutData()).getColumnSpan();
				iPosTest = this.iNextCol+iColSpan;
				if (iPosTest> this.getSize())
				{
					((GridLayoutData)oComp.getLayoutData()).setColumnSpan(1);
					iColSpan = 1;
				}
			}
		
		iPosTest = this.iNextCol+iColSpan;

		if (iColSpan>=2)
			this.iNextCol+=iColSpan;
		else
			this.iNextCol++;
		
		if (this.iNextCol>=this.getSize())
			this.iNextCol=0;
	}
	
	
	public void addAfterRemoveAll(Component oComp, Insets oInsets)
	{
		super.removeAll();
		super.add(oComp);
		oComp.setLayoutData(new ownLayout(oInsets, 0, 1, null));
		this.iNextCol++;
		if (this.iNextCol>=this.getSize())
			this.iNextCol=0;
	}

	public void addAfterRemoveAll(Component oComp, GridLayoutData oLayoutData)
	{
		super.removeAll();
		super.add(oComp);
		oComp.setLayoutData(oLayoutData);
		
		int iPosTest = this.iNextCol+oLayoutData.getColumnSpan();

		if (iPosTest> this.getSize())
			oLayoutData.setColumnSpan(1);

		
		if (oLayoutData.getColumnSpan()>=2)
			this.iNextCol+=oLayoutData.getColumnSpan();
		else
			this.iNextCol++;
		
		
		if (this.iNextCol>=this.getSize())
			this.iNextCol=0;
	}

	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param oInsets
	 * @throws myException
	 */
	public void addAfterRemoveAll(Component oComp,int iiColSpan, Insets oInsets)
	{
		super.removeAll();

		int iColSpan = iiColSpan;

		
		if (iColSpan == 0)
			iColSpan =1;
		
		int iPosTest = this.iNextCol+iColSpan;
		
		if (iPosTest> this.getSize())
			iColSpan = 1;
		
		iPosTest = this.iNextCol+iColSpan;
		
		super.add(oComp);
		oComp.setLayoutData(new ownLayout(oInsets,iColSpan, 1, null));
		
		this.iNextCol = iPosTest;
		if (this.iNextCol>=this.getSize())
			this.iNextCol=0;
		
	}
	

	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public void addAfterRemoveAll(Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment)
	{
		
		super.removeAll();
		
		int iColSpan = iiColSpan;
		int iRowSpan = iiRowSpan;

		
		if (iColSpan == 0)
			iColSpan =1;

		if (iRowSpan == 0)
			iRowSpan =1;
		
		int iPosTest = this.iNextCol+iColSpan;
		
		if (iPosTest> this.getSize())
			iColSpan = 1;
		
		iPosTest = this.iNextCol+iColSpan;
		
		super.add(oComp);
		oComp.setLayoutData(new ownLayout(oInsets,iColSpan, iRowSpan, oAlignment));
		
		this.iNextCol = iPosTest;
		if (this.iNextCol>=this.getSize())
			this.iNextCol=0;
		
	}

	
	
	
	public void NewLine()
	{
		if (this.iNextCol==0)
			return;
		
		for (int i=this.iNextCol;i<this.getSize();i++)
			super.add(new Label(" "));
		
		this.iNextCol = 0;
	}
	

	//2011-02-03: grid-copy aktiviert
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_Grid  oGridCopy = new MyE2_Grid(this.getSize());
		
		oGridCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oGridCopy));

		oGridCopy.setStyle(this.getStyle());
		
		return oGridCopy;

		//throw new myExceptionCopy(myExceptionCopy.ERROR_COPY_NOT_IMPLEMENTED);
	}

	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}


	
	//2011-02-10: Grid in Rahmen
	public MyE2_Grid get_InBorderGrid(Border oBorder, Extent oExt, Insets oInsets)
	{
		
		MutableStyle  oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER, oBorder!=null?oBorder:new Border(1,Color.BLACK,Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_INSETS, oInsets!=null?oInsets:E2_INSETS.I_1_1_1_1);
		if (oExt!=null)
		{		
			oStyle.setProperty(Grid.PROPERTY_WIDTH, oExt);
		}
		
		MyE2_Grid_InLIST  oGridRueck = new MyE2_Grid_InLIST(1,oStyle);

		oGridRueck.add(this);
		
		return oGridRueck;
	}
	
	
	//2011-02-10: Grid in Rahmen
	public MyE2_Grid_InLIST get_InBorderGrid_inList(Border oBorder, Extent oExt, Insets oInsets)
	{
		
		MutableStyle  oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER, oBorder!=null?oBorder:new Border(1,Color.BLACK,Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_INSETS, oInsets!=null?oInsets:E2_INSETS.I_1_1_1_1);
		if (oExt!=null)
		{		
			oStyle.setProperty(Grid.PROPERTY_WIDTH, oExt);
		}
		
		MyE2_Grid_InLIST  oGridRueck = new MyE2_Grid_InLIST(1,oStyle);

		oGridRueck.add(this);
		
		return oGridRueck;
	}
	

	
	
	private class ownLayout extends GridLayoutData
	{
		public ownLayout(Insets oInsets, int iColSpan, int iRowSpan, Alignment oAlignment)
		{
			super();
			if (oInsets != null)
				this.setInsets(oInsets);
			
			if (iColSpan>1)
				this.setColumnSpan(iColSpan);
			
			if (iRowSpan>1)
				this.setRowSpan(iRowSpan);
			
			if (oAlignment != null)
				this.setAlignment(oAlignment);
		}

		public ownLayout(Insets oInsets, int iColSpan, int iRowSpan, Alignment oAlignment, Color oCol)
		{
			super();
			if (oInsets != null)
				this.setInsets(oInsets);
			
			if (iColSpan>1)
				this.setColumnSpan(iColSpan);
			
			if (iRowSpan>1)
				this.setRowSpan(iRowSpan);
			
			if (oAlignment != null)
				this.setAlignment(oAlignment);
			
			if (oCol != null) {
				this.setBackground(oCol);
			}
			
		}

	}



	
	private class ownGridStyle extends MutableStyle
	{

		public ownGridStyle(int iBorderSize, Insets oInsets)
		{
			super();
			if (iBorderSize>0) this.setProperty( Grid.PROPERTY_BORDER, new Border(iBorderSize, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
			this.setProperty( Grid.PROPERTY_INSETS, oInsets); 

		}
		
	}

	
	
	public static MutableStyle 	STYLE_GRID_DDARK_BORDER()
	{
		MutableStyle oStyleDDARKBorder = new MutableStyle();
		oStyleDDARKBorder.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		return oStyleDDARKBorder;
	}
	
	public static MutableStyle 	STYLE_GRID_NO_BORDER()
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		return oStyleNOBorder;
	}

	public static MutableStyle 	STYLE_GRID_NO_BORDER_NO_INSETS()
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyleNOBorder.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		return oStyleNOBorder;
	}

	
	public static MutableStyle 	STYLE_GRID_DEBUG_BORDER_NO_INSETS()
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(1,Color.RED,Border.STYLE_SOLID));
		oStyleNOBorder.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		return oStyleNOBorder;
	}


	public static MutableStyle 	STYLE_GRID_NO_BORDER_NO_INSETS_VERTICAL()
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyleNOBorder.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oStyleNOBorder.setProperty( Grid.PROPERTY_ORIENTATION, new Integer(MyE2_Grid.ORIENTATION_VERTICAL));
		return oStyleNOBorder;
	}

	public static MutableStyle 	STYLE_GRID_NO_BORDER_NO_INSETS_VERTICAL_W100()
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyleNOBorder.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oStyleNOBorder.setProperty( Grid.PROPERTY_ORIENTATION, new Integer(MyE2_Grid.ORIENTATION_VERTICAL));
		oStyleNOBorder.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oStyleNOBorder;
	}

	

	public static MutableStyle 	STYLE_GRID_NO_BORDER_INSETS_11()
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyleNOBorder.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		return oStyleNOBorder;
	}

	public static MutableStyle 	STYLE_GRID_DDARK_BORDER_INSETS_11()
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyleNOBorder.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		return oStyleNOBorder;
	}


	
	public static MutableStyle 	STYLE_GRID_DDARK_BORDER_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oStyle;
	}
	
	public static MutableStyle 	STYLE_GRID_NO_BORDER_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oStyle;
	}

	public static MutableStyle 	STYLE_GRID_NO_BORDER_NO_INSETS_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oStyle;
	}

	
	
	public static MutableStyle 	STYLE_GRID_BLACK_BORDER_NO_INSETS_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,Color.BLACK,Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oStyle;
	}


	public static MutableStyle 	STYLE_GRID_GREY_BORDER_NO_INSETS_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,Color.DARKGRAY,Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oStyle;
	}


	
	public static MutableStyle 	STYLE_GRID_NO_BORDER_INSETS_11_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oStyle;
	}

	public static MutableStyle 	STYLE_GRID_DDARK_BORDER_INSETS_11_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oStyle;
	}


	public static MutableStyle 	STYLE_GRID_DDARK_BORDER_NO_INSETS_W100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oStyle;
	}


	public static MutableStyle 	STYLE_GRID_DDARK_BORDER_W100_H100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(99,Extent.PERCENT));
		return oStyle;
	}
	
	public static MutableStyle 	STYLE_GRID_NO_BORDER_W100_H100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(99,Extent.PERCENT));
		return oStyle;
	}

	public static MutableStyle 	STYLE_GRID_NO_BORDER_NO_INSETS_W100_H100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(99,Extent.PERCENT));
		return oStyle;
	}

	
	public static MutableStyle 	STYLE_GRID_NO_BORDER_INSETS_11_W100_H100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(99,Extent.PERCENT));
		return oStyle;
	}

	public static MutableStyle 	STYLE_GRID_DDARK_BORDER_INSETS_11_W100_H100()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(99,Extent.PERCENT));
		return oStyle;
	}


	public static MutableStyle 	STYLE_GRID_DDARK_BORDER_NO_INSETS()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		return oStyle;
	}


	
	//neue styles mit schwarzem rahmen
	public static MutableStyle 	STYLE_GRID_BORDER(Color oCol)
	{
		MutableStyle oStyleDDARKBorder = new MutableStyle();
		oStyleDDARKBorder.setProperty(Grid.PROPERTY_BORDER,new Border(1,oCol,Border.STYLE_SOLID));
		
		return oStyleDDARKBorder;
	}
	
	//neue styles mit schwarzem rahmen
	public static MutableStyle 	STYLE_GRID_BORDER(Border oBorder)
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,oBorder);
		
		return oStyle;
	}
	

	
	
	public static MutableStyle 	STYLE_GRID_BORDER_INSETS_11(Color oCol)
	{
		MutableStyle oStyleNOBorder = new MutableStyle();
		oStyleNOBorder.setProperty(Grid.PROPERTY_BORDER,new Border(1,oCol,Border.STYLE_SOLID));
		oStyleNOBorder.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		return oStyleNOBorder;
	}


	
	public static MutableStyle 	STYLE_GRID_BORDER_W100(Color oCol)
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,oCol,Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oStyle;
	}
	


	public static MutableStyle 	STYLE_GRID_BORDER_INSETS_11_W100(Color oCol)
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,oCol,Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oStyle;
	}


	public static MutableStyle 	STYLE_GRID_BORDER_W100_H100(Color oCol)
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,oCol,Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(99,Extent.PERCENT));
		return oStyle;
	}
	
	
	public static MutableStyle 	STYLE_GRID_BORDER_INSETS_11_W100_H100(Color oCol)
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,oCol,Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		oStyle.setProperty(Grid.PROPERTY_HEIGHT, new Extent(99,Extent.PERCENT));
		return oStyle;
	}

	//ende styles mit schwarz
	
	
	public static GridLayoutData GRID_LAYOUTDATA(Insets oInsets, int iColSpan, Alignment oAlignment)
	{
		GridLayoutData oLayout = new GridLayoutData();
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (iColSpan>1)
			oLayout.setColumnSpan(iColSpan);
		
		if (oAlignment != null)
			oLayout.setAlignment(oAlignment);
		
		return oLayout;
	}
	
	 

	public static GridLayoutData LAYOUT_LEFT(Insets oInsets)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_CENTER(Insets oInsets)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_RIGHT(Insets oInsets)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	

	public static GridLayoutData LAYOUT_LEFT_TOP(Insets oInsets)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_LEFT_CENTER(Insets oInsets)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_CENTER_CENTER(Insets oInsets)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}

	
	public static GridLayoutData LAYOUT_CENTER_CENTER(Insets oInsets, Color oColorBackground, Integer iColSpan, Integer iRowSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);
		
		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		if (iRowSpan != null)
			oLayout.setRowSpan(iRowSpan.intValue());

		return oLayout;
	}

	
	public static GridLayoutData LAYOUT_LEFT_CENTER(Insets oInsets, Color oColorBackground, Integer iColSpan, Integer iRowSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);
		
		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		if (iRowSpan != null)
			oLayout.setRowSpan(iRowSpan.intValue());

		
		return oLayout;
	}
	
	
	
	public static GridLayoutData LAYOUT_CENTER_TOP(Insets oInsets)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_RIGHT_TOP(Insets oInsets)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}

	public static GridLayoutData LAYOUT_RIGHT_CENTER(Insets oInsets)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}

	
	public static GridLayoutData LAYOUT_RIGHT_CENTER(Insets oInsets, Color oColorBackground, Integer iColSpan, Integer iRowSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);
		
		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		if (iRowSpan != null)
			oLayout.setRowSpan(iRowSpan.intValue());

		
		return oLayout;
	}

	
	
	public static GridLayoutData LAYOUT_LEFT_BOTTOM(Insets oInsets)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.BOTTOM));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_CENTER_BOTTOM(Insets oInsets)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.BOTTOM));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_RIGHT_BOTTOM(Insets oInsets)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}

//-------------------
	
	
	
	public static GridLayoutData LAYOUT_LEFT(Insets oInsets, Color oColorBackground, Integer iColSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);
		
		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());
		
		
		
		
		return oLayout;
	}
	
	
	/**
	 * 
	 * @param oInsets
	 * @param oColorBackground
	 * @param iColSpan
	 * @return
	 */
	public static GridLayoutData LAYOUT_CENTER(Insets oInsets, Color oColorBackground, Integer iColSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);
		
		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());
		
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_RIGHT(Insets oInsets, Color oColorBackground, Integer iColSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);

		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		return oLayout;
	}
	

	public static GridLayoutData LAYOUT_LEFT_TOP(Insets oInsets, Color oColorBackground, Integer iColSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);

		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_CENTER_TOP(Insets oInsets, Color oColorBackground, Integer iColSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);

		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());
		
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_RIGHT_TOP(Insets oInsets, Color oColorBackground, Integer iColSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
		if (oInsets != null)
			oLayout.setInsets(oInsets);

		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);
		
		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		return oLayout;
	}


	
	public static GridLayoutData LAYOUT_LEFT_BOTTOM(Insets oInsets, Color oColorBackground, Integer iColSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.BOTTOM));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);

		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_CENTER_BOTTOM(Insets oInsets, Color oColorBackground, Integer iColSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.BOTTOM));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);

		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());
		
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_RIGHT_BOTTOM(Insets oInsets, Color oColorBackground, Integer iColSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		if (oInsets != null)
			oLayout.setInsets(oInsets);

		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);
		
		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		return oLayout;
	}

	
	//----
	
	
	public static GridLayoutData LAYOUT_LEFT(Insets oInsets, Color oColorBackground, Integer iColSpan, Integer iRowSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);
		
		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());
		
		if (iRowSpan != null)
			oLayout.setRowSpan(iRowSpan.intValue());
		
		
		
		
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_CENTER(Insets oInsets, Color oColorBackground, Integer iColSpan, Integer iRowSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);
		
		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		if (iRowSpan != null)
			oLayout.setRowSpan(iRowSpan.intValue());
		
		
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_RIGHT(Insets oInsets, Color oColorBackground, Integer iColSpan, Integer iRowSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);

		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		if (iRowSpan != null)
			oLayout.setRowSpan(iRowSpan.intValue());

		return oLayout;
	}
	

	public static GridLayoutData LAYOUT_LEFT_TOP(Insets oInsets, Color oColorBackground, Integer iColSpan, Integer iRowSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);

		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		if (iRowSpan != null)
			oLayout.setRowSpan(iRowSpan.intValue());

		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_CENTER_TOP(Insets oInsets, Color oColorBackground, Integer iColSpan, Integer iRowSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);

		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		if (iRowSpan != null)
			oLayout.setRowSpan(iRowSpan.intValue());
		
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_RIGHT_TOP(Insets oInsets, Color oColorBackground, Integer iColSpan, Integer iRowSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
		if (oInsets != null)
			oLayout.setInsets(oInsets);

		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);
		
		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		if (iRowSpan != null)
			oLayout.setRowSpan(iRowSpan.intValue());

		return oLayout;
	}


	
	public static GridLayoutData LAYOUT_LEFT_BOTTOM(Insets oInsets, Color oColorBackground, Integer iColSpan, Integer iRowSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.BOTTOM));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);

		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		if (iRowSpan != null)
			oLayout.setRowSpan(iRowSpan.intValue());
		
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_CENTER_BOTTOM(Insets oInsets, Color oColorBackground, Integer iColSpan, Integer iRowSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.BOTTOM));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);

		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		if (iRowSpan != null)
			oLayout.setRowSpan(iRowSpan.intValue());
		
		return oLayout;
	}
	
	public static GridLayoutData LAYOUT_RIGHT_BOTTOM(Insets oInsets, Color oColorBackground, Integer iColSpan, Integer iRowSpan)
	{
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		if (oInsets != null)
			oLayout.setInsets(oInsets);

		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);
		
		if (iColSpan != null)
			oLayout.setColumnSpan(iColSpan.intValue());

		if (iRowSpan != null)
			oLayout.setRowSpan(iRowSpan.intValue());

		return oLayout;
	}	
	//----
	
	
	//weitere positionsmethoden fuer grid
	public void add_raw(Component oComp, GridLayoutData oLayoutData)
	{
		super.add(oComp);
		oComp.setLayoutData(oLayoutData);
	}

	
	//weitere positionsmethoden fuer grid
	public void add_raw(Component oComp)
	{
		super.add(oComp);
	}


	
	public myCoordinates get_NextCoordinates()
	{
		Component[] cComps = this.getComponents();
		
		int iCol = 0;
		int iRow = 0;
		
		for (int i=0;i<cComps.length;i++)
		{
			Component  cTest = cComps[i];
			int iColspan = 1; 
			if (cTest.getLayoutData()!=null && cTest.getLayoutData() instanceof GridLayoutData)
			{
				//iColspan = (GridLayoutData)cTest.getLayoutData()).set
				iColspan = ((GridLayoutData)cTest.getLayoutData()).getColumnSpan();
				if (iColspan==0){   //es koennen fehlerhafte layoutdatas mit colspan=0 uebergeben werden, diese werden wie colspan 1 behandelt
					iColspan=1;
				}
			}
			iCol+=iColspan;
			if (iCol>=this.getSize())
			{
				iCol=0;
				iRow++;
			}
		}

		return new myCoordinates(iRow, iCol);
	}
	
	
	
	
	
	public Vector<Component>  get_vComponents(Vector<String> vClassTypes, Vector<String> vClassesExclude)
	{
		E2_RecursiveSearch_Component recSearch = new E2_RecursiveSearch_Component(this, vClassTypes, vClassesExclude);
		return recSearch.get_vAllComponents();
	}
	

	
	//2012-12-13: alle zeilen gleich hoch machen
	public void set_all_rows_Height(int iHeightInPixel)
	{
		Extent oHeight =  new Extent(iHeightInPixel);
		
		try
		{
			for (int i=0;i<1000;i++)
			{
				this.setRowHeight(i, oHeight);
			}
		}
		catch (Exception ex)
		{}
	}


	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		Vector<IF_ADDING_Allowed> vRueck = new Vector<IF_ADDING_Allowed>();
		vRueck.add(this);
		return vRueck;
	}

	
	
	
	/**
	 * 	/**
	 * methode, um an ein bestehendes Grid ein weiteres anzuhaengen, das allerding die gleiche size braucht
	 * @param oGridAppend
	 * @param oTrennerComponent: komponente, die ueber die ganze breite zwischen die grid-teile gelegt wird
	 * @throws myException
	 */
	public void append_Grid(MyE2_Grid oGridAppend, Component  oTrennerComponent, GridLayoutData oGL4Trenner) throws myException
	{
		if (this.getSize()!=oGridAppend.getSize())
		{
			throw new myException(this, "Appended grid MUST have same size as base-grid!");
		}
		
		
		if (oTrennerComponent != null && oGL4Trenner != null)
		{
			oGL4Trenner.setColumnSpan(this.getSize());
			this.add(oTrennerComponent, oGL4Trenner);
		}
		
		Component[] Comps = oGridAppend.getComponents();
		
		for (int i=0; i<Comps.length;i++)
		{
			this.add_raw(Comps[i]);
		}
	}
	
	
	
	/**
	 * debug-hilfe, listet alle elemente eines grid auf
	 */
	public void _debugListAlleElemente()
	{
		System.out.println("");
		System.out.println("");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Grid-Info: Zahl Elemente: "+this.getComponents().length);
		
		for (int i=0; i<this.getComponents().length;i++)
		{
			
			String cInfo = "";
			
			if (this.getComponents()[i] instanceof Label)
			{
				cInfo += (""+i+" Label: "+((Label)this.getComponents()[i]).getText());
			}
			else
			{
				cInfo += (""+i+" kein label "+this.getComponents()[i].getClass().getName());
			}
			
			
			if (this.getComponents()[i].getLayoutData()!=null)
			{
				cInfo += ("Span: "+((GridLayoutData)this.getComponents()[i].getLayoutData()).getColumnSpan());
			}
			else
			{
				cInfo += ("layout-null");
			}
			
			
			System.out.println(cInfo);
		}
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("");
		System.out.println("");


	}
	
	
	public void add_Separator(Insets oInsets) {
		this.add(new Separator(), this.getSize(), oInsets);
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

	
	/**
	 * 2016-02-23
	 * @param comp
	 * @param gl
	 * @return
	 */
	public MyE2_Grid _add(Component comp, GridLayoutData gl) {
		comp.setLayoutData(gl);
		super.add(comp);
		return this;
	}
	
	
	/**
	 * 
	 * @param i  die spaltenbreiten in pixel definieren
	 * @return
	 */
	public MyE2_Grid _setSize(int... i) {
		int[] i_rueck = new int[i.length];
		
		int i_pos =0;
		for (int l: i) {
			i_rueck[i_pos++]=l;
		}

		this.set_Spalten(i_rueck);
		return this;
		
	}


	@Override
	public Vector<IF_Formatable> get_formatables() throws myException {
		Vector<IF_Formatable> formatables = new Vector<>();
		
		for (Component c: this.getComponents()) {
			if (c instanceof IF_Formatable) {
				formatables.add((IF_Formatable)c);
			}
		}

		return formatables;
	}
	
	
	/**
	 * adds raw (just using Echo-grids add-method and sets layoutdata to component)
	 * @param oComp
	 * @return
	 */
	public MyE2_Grid _add_r(Component oComp, GridLayoutData oLayoutData) {
		super.add(oComp);
		oComp.setLayoutData(oLayoutData);
		return this;
	}

	
	
	/**
	 * adds raw (just using Echo-grids add-method)
	 * @param oComp
	 * @return
	 */
	public MyE2_Grid _add_r(Component oComp) {
		super.add(oComp);
		return this;
	}


	
	
	//2016-09-08: martin
	// FluentInterface-methoden aus E2_Grid 
	private Color 						f_background = null;
	private Border 						f_border = null;
	private Extent 						f_width = null;
	private Extent 						f_height = null;
	private MutableStyle 				f_style = null;
	private Insets	 					f_insets = null;
	private int[]    					f_spalten= null;

	// standard-layoutData
	private RB_gld 						f_gridLayoutData = new RB_gld();

	/**
	 * Set Background-Color
	 * @param back_col
	 * @return
	 */
	public MyE2_Grid _bc(Color back_col) {
		this.setBackground(back_col);
		this.f_background = back_col;
		return this;
	}

	/**
	 * set Border
	 * @param bord
	 * @return
	 */
	public MyE2_Grid _bo(Border bord) {
		this.setBorder(bord);
		this.f_border = bord;
		return this;
	}

	/**
	 * set Border Black
	 * @return
	 */
	public MyE2_Grid _bo_b() {
		this.f_border = new Border(1, Color.BLACK, Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}
	
	/**
	 * set Border DarkGrey
	 * @return
	 */
	public MyE2_Grid _bo_dg() {
		this.f_border = new Border(1, Color.DARKGRAY, Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}
	
	/**
	 * set Border GREEN
	 * @return
	 */
	public MyE2_Grid _bo_green() {
		this.f_border = new Border(1, Color.GREEN, Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}

	/**
	 * set Border RED
	 * @return
	 */
	public MyE2_Grid _bo_red() {
		this.f_border = new Border(1, Color.RED, Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}

	/**
	 * set Border Color
	 * @return
	 */
	public MyE2_Grid _bo_col(Color col) {
		this.f_border = new Border(1, col, Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}

	
	
	/**
	 * set Border Dark
	 * @return
	 */
	public MyE2_Grid _bo_d() {
		this.f_border = new Border(1, new E2_ColorDark(), Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}
	
	/**
	 * set Border DDark
	 * @return
	 */
	public MyE2_Grid _bo_dd() {
		this.f_border = new Border(1, new E2_ColorDDark(), Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}
	
	/**
	 * set Border DDark
	 * @return
	 */
	public MyE2_Grid _bo_ddd() {
		this.f_border = new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}
	

	/**
	 * set NO-Border
	 * @return
	 */
	public MyE2_Grid _bo_no() {
		this.f_border = new Border(0, new E2_ColorBase(), Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}

	
	
	/**
	 * set Width of Grid
	 * @param width
	 * @return
	 */
	public MyE2_Grid _w(Extent width) {
		this.setWidth(width);
		this.f_width = width;
		return this;
	}

	
	
	/**
	 * set Width 100 %
	 * @param width
	 * @return
	 */
	public MyE2_Grid _w100() {
		this.setWidth(new Extent(100, Extent.PERCENT));
		this.f_width = new Extent(100, Extent.PERCENT);
		return this;
	}
	
	
	/**
	 * set Height
	 * @param hight
	 * @return
	 */
	public MyE2_Grid _h(Extent hight) {
		this.setHeight(hight);
		this.f_height = hight;
		return this;
	}

	/**
	 * setStyle
	 * @param style
	 * @return
	 */
	public MyE2_Grid _st(MutableStyle style) {
		this.setStyle(style);
		this.f_style = style;
		return this;
	}

	
	/**
	 * set Size (number of cols)
	 * @param i
	 * @return
	 */
	public MyE2_Grid _s(int i) {
		this.setSize(i);
		return this;
	}

	
	/**
	 * set global INSETS
	 * @param ins
	 * @return
	 */
	public MyE2_Grid _ins(Insets ins) {
		this.setInsets(ins);
		this.f_insets = ins;
		return this;
	}
	

	/**
	 * alles entfernen
	 * @return
	 */
	public MyE2_Grid _clear() {
		this.removeAll();
		return this;
	}

	public MyE2_Grid addSeparator() throws myException {
		this._add(new Separator(), new RB_gld()._ins(1)._span(this.getSize()));
		return this;
	}

	
}
