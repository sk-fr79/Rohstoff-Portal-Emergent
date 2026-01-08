
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
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.BasicInterfaces.IF_Border;
import panter.gmbh.Echo2.BasicInterfaces.IF_Dimension;
import panter.gmbh.Echo2.BasicInterfaces.IF_LayoutData;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_Formatable;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_Formatable_Container;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.O;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.utils.myCoordinates;


public class E2_Grid extends Grid implements 	MyE2IF__Component
												,IF_ADDING_Allowed 
												,IF_BaseComponent4Mask 
												,IF_Formatable_Container
												,IF_LayoutData<E2_Grid>
												,IF_Dimension<E2_Grid>
												,IF_Border<E2_Grid>
												,E2_IF_Copy
												{

	private MyE2EXT__Component 			oEXT = new MyE2EXT__Component(this);

	// styleelemente
	private Color 						f_background = null;
	private Border 						f_border = null;
	private Extent 						f_width = null;
	private Extent 						f_height = null;
	private MutableStyle 				f_style = null;
	private Insets	 					f_insets = null;

	// standard-layoutData
	private RB_gld 						f_gridLayoutData = new RB_gld();

	private Vector<MyE2IF__Component> 	vADDONS_IN_WRAP = new Vector<MyE2IF__Component>();
	private boolean 					b_OnComponentInFront = true;
	private int   						i_SpaceInPixel = 2;

	private int[]    					f_spalten= null;
	
	private int[] 						f_rowHights = null;
	
	public E2_Grid() {
		super();
	}

	
	public E2_Grid _a() {
		RB_lab l = new RB_lab()._t("");
		this.add(l);
		return this;
	}
	
	/**
	 * 
	 * @param text (no translation)
	 * @return
	 */
	public E2_Grid _a(String text) {
		RB_lab l = new RB_lab()._t(text);
		this.add(l);
		return this;
	}
	
	public E2_Grid _a(MyString text) {
		RB_lab l = new RB_lab()._t(text.CTrans());
		this.add(l);
		return this;
	}

	
	public E2_Grid _a(String text, GridLayoutData gl) {
		RB_lab l = new RB_lab()._t(text);
		return this._a(l, gl);
	}
	
	public E2_Grid _a(MyString text, GridLayoutData gl) {
		RB_lab l = new RB_lab()._t(text.CTrans())._lw();
		
		return this._a(l, gl);
	}


	
	public E2_Grid _a(Component c) {
		c.setLayoutData(this.f_gridLayoutData);
		this.add(c);
		return this;
	}

	
	public E2_Grid _a(GridLayoutData layoutFirst, GridLayoutData layoutRest, Component ... c) {
		for (int i=0;i<c.length;i++) {
			Component co = c[i];
			
			if (i==1) {
				this._a(co,layoutFirst);
			} else {
				this._a(co,layoutRest);
			}
		}
		return this;
	}

	
	public E2_Grid _a(GridLayoutData layoutFirst, Component ... c) {
		return this._a(layoutFirst,layoutFirst,c);
	}

	
	
	
	
	public E2_Grid _a(Component c, GridLayoutData gl) {
		c.setLayoutData(gl);
		this.add(c);
		return this;
	}
	

	
//	public E2_Grid _a(IF_RB_Component c, GridLayoutData gl) {
//		((Component)c).setLayoutData(gl);
//		this.add((Component)c);
//		return this;
//	}

	
	
	
	public E2_Grid _add_raw(Component c) {
		this.add(c);
		return this;
	}

	
	
	public E2_Grid _clear() {
		this.removeAll();
		return this;
	}
	
	
	
	
	/**
	 * add right, top
	 * @param c
	 * @return
	 */
	public E2_Grid _a_rt(Component c) {
		RB_gld gld = this.f_gridLayoutData._c()._al(new Alignment(Alignment.RIGHT, Alignment.TOP));
		c.setLayoutData(gld);
		this.add(c);
		return this;
	}

	
	/**
	 * add center, top
	 * @param c
	 * @return
	 */
	public E2_Grid _a_ct(Component c) {
		RB_gld gld = this.f_gridLayoutData._c()._al(new Alignment(Alignment.CENTER, Alignment.TOP));
		c.setLayoutData(gld);
		this.add(c);
		return this;
	}

	/**
	 * add left, top
	 * @param c
	 * @return
	 */
	public E2_Grid _a_lt(Component c) {
		RB_gld gld = this.f_gridLayoutData._c()._al(new Alignment(Alignment.LEFT, Alignment.TOP));
		c.setLayoutData(gld);
		this.add(c);
		return this;
	}

	
	/**
	 * add right, center
	 * @param c
	 * @return
	 */
	public E2_Grid _a_rm(Component c) {
		RB_gld gld = this.f_gridLayoutData._c()._al(new Alignment(Alignment.RIGHT, Alignment.CENTER));
		c.setLayoutData(gld);
		this.add(c);
		return this;
	}
	

	
	/**
	 * add center, center
	 * @param c
	 * @return
	 */
	public E2_Grid _a_cm(Component c) {
		RB_gld gld = this.f_gridLayoutData._c()._al(new Alignment(Alignment.CENTER, Alignment.CENTER));
		c.setLayoutData(gld);
		this.add(c);
		return this;
	}
	
	/**
	 * add left, center
	 * @param c
	 * @return
	 */
	public E2_Grid _a_lm(Component c) {
		RB_gld gld = this.f_gridLayoutData._c()._al(new Alignment(Alignment.LEFT, Alignment.CENTER));
		c.setLayoutData(gld);
		this.add(c);
		return this;
	}


	/**
	 * add right, bottom
	 * @param c
	 * @return
	 */
	public E2_Grid _a_rb(Component c) {
		RB_gld gld = this.f_gridLayoutData._c()._al(new Alignment(Alignment.RIGHT, Alignment.BOTTOM));
		c.setLayoutData(gld);
		this.add(c);
		return this;
	}
	
	/**
	 * add center, bottom
	 * @param c
	 * @return
	 */
	public E2_Grid _a_cb(Component c) {
		RB_gld gld = this.f_gridLayoutData._c()._al(new Alignment(Alignment.CENTER, Alignment.BOTTOM));
		c.setLayoutData(gld);
		this.add(c);
		return this;
	}
	
	/**
	 * add left, bottom
	 * @param c
	 * @return
	 */
	public E2_Grid _a_lb(Component c) {
		RB_gld gld = this.f_gridLayoutData._c()._al(new Alignment(Alignment.LEFT, Alignment.BOTTOM));
		c.setLayoutData(gld);
		this.add(c);
		return this;
	}

	
	
	@Override
	public void add_raw(Component c) {
		this.add(c);
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException {

		Vector<IF_ADDING_Allowed> v_rueck = new Vector<>();
		v_rueck.add(this);
		return v_rueck;
	}

	@Override
	public Vector<IF_Formatable> get_formatables() throws myException {

		Vector<IF_Formatable> formatables = new Vector<>();

		for (Component c : this.getComponents()) {
			if (c instanceof IF_Formatable) {
				formatables.add((IF_Formatable) c);
			}
		}
		return formatables;
	}

	
	@Override
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}

	@Override
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	@Override
	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	
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

	
	
	
	// FluentInterface-methoden
	
	/**
	 * Set Background-Color
	 * @param back_col
	 * @return
	 */
	public E2_Grid _bc(Color back_col) {
		this.setBackground(back_col);
		this.f_background = back_col;
		return this;
	}

	/**
	 * set Border
	 * @param bord
	 * @return
	 */
	public E2_Grid _bo(Border bord) {
		this.setBorder(bord);
		this.f_border = bord;
		return this;
	}

	/**
	 * set Border Black
	 * @return
	 */
	public E2_Grid _bo_b() {
		this.f_border = new Border(1, Color.BLACK, Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}
	
	/**
	 * set Border DarkGrey
	 * @return
	 */
	public E2_Grid _bo_dg() {
		this.f_border = new Border(1, Color.DARKGRAY, Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}
	
	/**
	 * set Border GREEN
	 * @return
	 */
	public E2_Grid _bo_green() {
		this.f_border = new Border(1, Color.GREEN, Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}

	/**
	 * set Border RED
	 * @return
	 */
	public E2_Grid _bo_red() {
		this.f_border = new Border(1, Color.RED, Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}

	/**
	 * set Border Color
	 * @return
	 */
	public E2_Grid _bo_col(Color col) {
		this.f_border = new Border(1, col, Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}

	
	
	/**
	 * set Border Dark
	 * @return
	 */
	public E2_Grid _bo_d() {
		this.f_border = new Border(1, new E2_ColorDark(), Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}
	
	/**
	 * set Border DDark
	 * @return
	 */
	public E2_Grid _bo_dd() {
		this.f_border = new Border(1, new E2_ColorDDark(), Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}
	
	/**
	 * set Border DDark
	 * @return
	 */
	public E2_Grid _bo_ddd() {
		this.f_border = new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}
	

	/**
	 * set NO-Border
	 * @return
	 */
	public E2_Grid _bo_no() {
		this.f_border = new Border(0, new E2_ColorBase(), Border.STYLE_SOLID);
		this.setBorder(this.f_border);
		return this;
	}

	
	
	/**
	 * set Width of Grid
	 * @param width
	 * @return
	 */
	public E2_Grid _w(Extent width) {
		this.setWidth(width);
		this.f_width = width;
		return this;
	}

	/**
	 * set Width of Grid
	 * @param width
	 * @return
	 */
	public E2_Grid _w(int width) {
		this.setWidth(new Extent(width));
		this.f_width = new Extent(width);
		return this;
	}

	
	/**
	 * set Width 100 %
	 * @param width
	 * @return
	 */
	public E2_Grid _w100() {
		this.setWidth(new Extent(100, Extent.PERCENT));
		this.f_width = new Extent(100, Extent.PERCENT);
		return this;
	}
	
	
	/**
	 * set Height
	 * @param hight
	 * @return
	 */
	public E2_Grid _h(Extent hight) {
		this.setHeight(hight);
		this.f_height = hight;
		return this;
	}

	/**
	 * set Height
	 * @param hight
	 * @return
	 */
	public E2_Grid _h(int hight) {
		this.setHeight(new Extent(hight));
		this.f_height = new Extent(hight);
		return this;
	}

	/**
	 * setStyle
	 * @param style
	 * @return
	 */
	public E2_Grid _st(MutableStyle style) {
		this.setStyle(style);
		this.f_style = style;
		return this;
	}

	
	/**
	 * set Size (number of cols)
	 * @param i
	 * @return
	 */
	public E2_Grid _s(int i) {
		this.setSize(i);
		return this;
	}

	
	/**
	 * set global INSETS
	 * @param ins
	 * @return
	 */
	public E2_Grid _ins(Insets ins) {
		this.setInsets(ins);
		this.f_insets = ins;
		return this;
	}

	/**
	 * set Standard-LayoutData for added Components
	 * @param gld
	 * @return
	 */
	public E2_Grid _gld(RB_gld gld) {
		this.f_gridLayoutData = gld;
		return this;
	}

	
	/**
	 * 
	 * @param i  die spaltenbreiten in pixel definieren
	 * @return
	 */
	public E2_Grid _setSize(int... i) {
		int[] i_rueck = new int[i.length];
		
		int i_pos =0;
		for (int l: i) {
			i_rueck[i_pos++]=l;
		}
		
		this.f_spalten = i_rueck;
		this.setSize(f_spalten.length);
		
		for (int l=0;l<f_spalten.length;l++) {
			this.setColumnWidth(l,new Extent(f_spalten[l]));
		}
		
		return this;
	}

	
	
	/**
	 * 
	 * @param intArray  die spaltenbreiten in pixel definieren
	 * @return
	 */
	public E2_Grid _setSizeInteger(Integer ... intArray) {
		int[] i_rueck = new int[intArray.length];
		
		int i_pos =0;
		for (Integer l: intArray) {
			i_rueck[i_pos++]=O.NN(l,1);   //null ausschliessen
		}
		
		this.f_spalten = i_rueck;
		this.setSize(f_spalten.length);
		
		for (int l=0;l<f_spalten.length;l++) {
			this.setColumnWidth(l,new Extent(f_spalten[l]));
		}
		
		return this;
	}
	
	
	/**
	 * 
	 * @param extend ...  die spaltenbreiten in pixel definieren
	 * @return
	 */
	public E2_Grid _setSize(Extent... colExt) {
		
		this.setSize(colExt.length);
		
		for (int l=0;l<colExt.length;l++) {
			this.setColumnWidth(l,colExt[l]);
		}
		
		return this;
	}


	
	/**
	 * 
	 * @param i  die zeilenhoehe in pixel definieren
	 * @return
	 */
	public E2_Grid _setRowHight(int... i) {
		int[] i_rueck = new int[i.length];
		
		int i_pos =0;
		for (int l: i) {
			i_rueck[i_pos++]=l;
		}
		
		this.f_rowHights = i_rueck;
		
		try {
			for (int l=0;l<f_rowHights.length;l++) {
				this.setRowHeight(l, new Extent(f_rowHights[l]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}

	/**
	 * fuer einzelne zeile die hoehe definieren 
	 * @param i_rownumber
	 * @param row_height
	 * @return
	 */
	public E2_Grid _setRowH(int i_rownumber, int row_height) {
		this.setRowHeight(i_rownumber, new Extent(row_height));
		return this;
	}
	
	
	
	
	
	
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed() && this.EXT().get_bCanBeEnabled());
	}

	
	public Color get_background() {
		return f_background;
	}

	
	public Border get_border() {
		return f_border;
	}

	
	public Extent get_width() {
		return f_width;
	}

	
	public Extent get_height() {
		return f_height;
	}
	
	public MutableStyle get_style() {
		return f_style;
	}

	
	public Insets get_insets() {
		return f_insets;
	}

	
	public GridLayoutData get_gridLayoutData() {
		return f_gridLayoutData;
	}

	
	public int[] get_spalten() {
		return f_spalten;
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
	
	
	/**
	 * fuegt eine leerzelle mit via gl definiertem colspan an (falls null oder colspan=0, dann nichts
	 * @param gl
	 * @return
	 * @throws myException 
	 */
	public E2_Grid  _startLine(GridLayoutData gl) throws myException {
		//falls die zeile noch nicht fertig ist, leerzelle anhaengen
		myCoordinates  coord = this.get_NextCoordinates();
		if (coord.iCol!=0) {
			this._endLine(gl);
		}

		
		if (gl==null || gl.getColumnSpan()==0) {
			return this;
		}
		return this._a(new RB_lab(""),gl);
	}

	
	
	
	/**
	 * fuegt eine leerzelle mit via gl bis zum zeilenende an
	 * @param gl
	 * @return
	 * @throws myException 
	 */
	public E2_Grid  _endLine(GridLayoutData gl) throws myException {
		if (gl==null || gl.getColumnSpan()==0) {
			return this;
		}
		return this._endLine(new MyE2_Label(""),gl);
	}


	/**
	 * fuellt den rest der zeile mit gld-LayoutData belegtem MyE2_Label("") auf 
	 * @param gl
	 * @return
	 */
	public E2_Grid  _endLine(Object comp_, GridLayoutData gl) throws myException {
		Component comp = null;
		if (comp_ instanceof Component) {
			comp = (Component) comp_;
		} else if (comp_ instanceof MyString) {
			comp = new MyE2_Label((MyString)comp_);
		} else if (comp_ instanceof String) {
			comp = new RB_lab((String)comp_);
		} 
		
		
		if (comp == null) {
			throw new myException(this, "comp must be not null and must extend Component /MyString/String!");
		}
		
		myCoordinates  coord = this.get_NextCoordinates();
		if (coord.iCol>0) {		//==0 heißt naechste zeile wuerde beginnnen
			int col_span = this.getSize()-coord.iCol;
			if (col_span>0) {
				GridLayoutData  glnew = LayoutDataFactory.get_GL_Copy(gl);
				glnew.setColumnSpan(col_span);
				this._a(comp, glnew);
			}
		}
		return this;
	}
	
	/**
	 * 
	 * @param gl layoutdata fuer ein singluaeres element
	 * @param gli layoutdata fuer das innere grid
	 * @param components
	 * @return
	 * @throws myException
	 */
	public E2_Grid _endLine(GridLayoutData gl, GridLayoutData gli, IF_RB_Component... components) throws myException {
		if (gli==null) {
			gli=MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,0,2,0));
		}
		if (components==null || components.length==0) {
			throw new myException(this, "comp must be not null and must extend Component !");
		} else if (components.length==1) {
			return this._endLine(components[0],gl);
		} else {
			E2_Grid4MaskSimple grid_innen = new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			for (IF_RB_Component c: components) {
				c.c().setLayoutData(gli);
				grid_innen.add_raw(c.c());
			}
			grid_innen.setSize(components.length);
			return this._endLine(grid_innen,gl);
		}
	}


	/**
	 * 
	 * @param gl layoutdata fuer ein singluaeres element
	 * @param gli layoutdata fuer das innere grid
	 * @param components  (hier wird, falls eine komponente null ist, ein @@@ERROR@@@ eingeblendet
	 * @return
	 * @throws myException
	 */
	public E2_Grid _endLine(GridLayoutData gl, GridLayoutData gli, Component... components) throws myException {
		for (int i=0;i<components.length;i++) {
			if (components[i]==null) {
				components[i]=new RB_lab("@@@ERROR-not-found@@@@");
			}
		}
		
		if (gli==null) {
			gli=MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,0,2,0));
		}
		if (components==null || components.length==0) {
			throw new myException(this, "comp must be not null and must extend Component !");
		} else if (components.length==1) {
			return this._endLine(components[0],gl);
		} else {
			E2_Grid4MaskSimple grid_innen = new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			for (Component c: components) {
				c.setLayoutData(gli);
				grid_innen.add_raw(c);
			}
			grid_innen.setSize(components.length);
			return this._endLine(grid_innen,gl);
		}
	}


	
	/**
	 * setzt die zahl der spalten so, dass alles in eine zeile passt (damit ersatz fuer eine row)
	 * !!!MUSS nach dem zufuegen aller komponenten erfolgen 
	 * @return
	 */
	public E2_Grid _setSizeSingleLine() {
		this.setSize(this.getComponentCount());
		return this;
	}
	
	
	
	public E2_Grid addSeparator() throws myException {
		this._endLine(new RB_gld());
		this._a(new Separator(), new RB_gld()._ins(1)._span(this.getSize()));
		return this;
	}

	
	//20171123: grid-copy aktiviert
	public Object get_Copy(Object objHelp) throws myExceptionCopy	{
		E2_Grid  oGridCopy = new E2_Grid()._s(this.getSize());
		
		oGridCopy.f_background = 	this.f_background;
		oGridCopy.f_border = 		this.f_border;
		oGridCopy.f_width = 		this.f_width;
		oGridCopy.f_height = 		this.f_height;
		oGridCopy.f_style = 		this.f_style;
		oGridCopy.f_insets = 		this.f_insets;

		// standard-layoutData
		oGridCopy.f_gridLayoutData = this.f_gridLayoutData;
		
		if (this.f_spalten!=null && this.f_spalten.length>0) {
			oGridCopy._setSize(this.f_spalten);
		}

		
		oGridCopy.setBackground(this.getBackground());
		oGridCopy.setBorder(this.getBorder());
		oGridCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oGridCopy));
		return oGridCopy;
	}

	
	
	/**
	 * change this grid to raster
	 * @param iSpaltenZahl
	 * @param iColWidth
	 * @return
	 */
	public E2_Grid _rstCreate(int iSpaltenZahl, int iColWidth) {
		
		int[] spalten = new int[iSpaltenZahl];
		for (int i=0;i<iSpaltenZahl;i++) {
			spalten[i]=iColWidth;
		}
		this._setSize(spalten);
		return this;
	}
	
	
	
	public E2_Grid _addSeparator(int left, int top, int right, int bottom) {
		this._a(new Separator(), new RB_gld()._span(this.getSize())._ins(left,top,right,bottom));
		return this;
	}
	
	public E2_Grid _addSeparator() {
		return this._addSeparator(2, 2, 2, 2);
	}
	
	
	/**
	 * creates line of components, only space between components
	 * @author martin
	 * @date 18.03.2019
	 *
	 * @param space
	 * @param size
	 * @param comps
	 * @return
	 * @throws myException
	 */
	public E2_Grid _line(int space, int[] size, Component ... comps) throws myException {
		boolean first = true;
		
		this._setSize(size);
		
		for (Component c: comps)  {
			if (first) {
				this._a(c, new RB_gld()._ins(0, 0, 0, 0));
				first = false;
			} else {
				this._a(c, new RB_gld()._ins(space, 0, 0, 0));
			}
		}
		
		
		return this;
	}
	
	
}
