package panter.gmbh.Echo2.RB.TOOLS;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_Summe;
import panter.gmbh.Echo2.RB.IF.IF_WrappedSimple;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.myCoordinates;

/**
 * ein grid fuer masken, prueft bei jeder uebergeben komponente ob diese 
 * das interface _IF_WrapInComponent erfuellt und falls ja, wird die komponente durch
 * ein .get_containerWith_ME(), die wrapper-komponente ersetzt
 * @author martin
 * @deprecated
 *
 */
public class RB_MaskGrid extends MyE2_Grid {

	IF_Summe  s = (arr)-> {int[] i_arr=(int[])arr;  int sum=0; for (int i_wert: i_arr) {sum+=i_wert;}; return sum;};
	
	public RB_MaskGrid() {
		super();
	}

	public RB_MaskGrid(int iAbstand, Component... components) {
		super(iAbstand, components);
	}

	public RB_MaskGrid(int iNumCols, int iBorderSize) {
		super(iNumCols, iBorderSize);
	}

	public RB_MaskGrid(int iSpalten, MutableStyle oStyle, GridLayoutData gl,Component... components) {
		super(iSpalten, oStyle, gl, components);
	}

	public RB_MaskGrid(int iNumCols, MutableStyle oStyle) {
		super(iNumCols, oStyle);
	}

	public RB_MaskGrid(int iNumCols) {
		super(iNumCols);
	}

	public RB_MaskGrid(int[] iSpalten, int iBorderSize) {
		super(iSpalten, iBorderSize);
		this.setWidth(new Extent(s.sum(iSpalten)));
	}


	public RB_MaskGrid(int[] iSpalten, MutableStyle oStyle) {
		super(iSpalten, oStyle);
		this.setWidth(new Extent(s.sum(iSpalten)));
	}

	public RB_MaskGrid(MutableStyle oStyle) {
		super(oStyle);
	}

	
	
	public RB_MaskGrid _cols(int[] iSpalten) {
		super.set_Spalten(iSpalten);
		this.setWidth(new Extent(s.sum(iSpalten)));
		return this;
	}
	
	public RB_MaskGrid _style(MutableStyle style) {
		this.setStyle(style);
		return this;
	}
	
	
	private Component check_wrapper(Component comp){
		Component comp_r = comp;
		
		//pruefen, ob die komponente vorher bereits ein gridlayoutdata hatte, wenn ja, das sichern, da in der methode get_containerWith_ME() vermulich
		//ein neues vergeben wird fuer das wrapper-element
		GridLayoutData gl_old = null;
		if (comp.getLayoutData()!=null && comp.getLayoutData() instanceof GridLayoutData) {
			gl_old = (GridLayoutData)comp.getLayoutData();
		}
		
		if (comp instanceof IF_WrappedSimple && comp instanceof MyE2IF__Component) {
			comp_r = (Component)((IF_WrappedSimple)comp).box((MyE2IF__Component)comp);
			if (gl_old != null) {
				comp_r.setLayoutData(gl_old);
			}
		}
		
		return comp_r;
	}
	 
	
	/**
	 * add-methode, die nicht das an der komponente haftende layout-data aendert
	 * @param oComp
	 */
	public void add_SUPER(Component oComp) 	{
		super.add_SUPER(this.check_wrapper(oComp));
	}

	

	
	
	public void add_RAW(Component oComp, GridLayoutData oLayoutData) {
		super.add_RAW(this.check_wrapper(oComp), oLayoutData);
	}


	
	public void add_RAW_noLayoutData(Component oComp){
		super.add_RAW_noLayoutData(this.check_wrapper(oComp));
	}


	
	public void add_RAW_AutoResize(Component oComp, Extent extBreite,  GridLayoutData oLayoutData) {
		super.add_RAW_AutoResize(this.check_wrapper(oComp), extBreite,  oLayoutData);
	}

	
	



	
	/**
	 * 2013-11-15: neues add-raw
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public void add_RAW(Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment)	{
		super.add_RAW(this.check_wrapper(oComp),iiColSpan, iiRowSpan, oInsets, oAlignment);
	}


	
	
	
	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public void add_ext(Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment)	{
		super.add_ext(this.check_wrapper(oComp),iiColSpan, iiRowSpan, oInsets, oAlignment);
	}

	
	
	

	
	
	
	
	public void addAfterRemoveAll(Component oComp)	{
		super.addAfterRemoveAll(this.check_wrapper(oComp));
	}
	
	
	public void addAfterRemoveAll(Component oComp, Insets oInsets)	{
		super.addAfterRemoveAll(this.check_wrapper(oComp), oInsets);
	}

	public void addAfterRemoveAll(Component oComp, GridLayoutData oLayoutData)	{
		super.addAfterRemoveAll(this.check_wrapper(oComp), oLayoutData);
	}

	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param oInsets
	 * @throws myException
	 */
	public void addAfterRemoveAll(Component oComp,int iiColSpan, Insets oInsets)	{
		super.addAfterRemoveAll(this.check_wrapper(oComp), iiColSpan, oInsets);
	}
	

	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public void addAfterRemoveAll(Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment)	{
		super.addAfterRemoveAll(this.check_wrapper(oComp),iiColSpan, iiRowSpan, oInsets, oAlignment);
	}

	
	
	
	
	//-----------------------------------------------------------------------------------------------------
	/**
	 * add-methode, die nicht das an der komponente haftende layout-data aendert
	 * @param oComp
	 */
	public void add_SUPER(IF_RB_Component oComp) 	{
		super.add_SUPER(this.check_wrapper((Component)oComp));
	}

	
	
	

	
	/**
	 * 
	 * @param oComp
	 * @param oLayoutData
	 */
	public void add_RAW(IF_RB_Component oComp, GridLayoutData oLayoutData) {
		super.add_RAW(this.check_wrapper((Component)oComp), oLayoutData);
	}


	
	
	
	public void add_RAW_noLayoutData(IF_RB_Component oComp){
		super.add_RAW_noLayoutData((Component)this.check_wrapper((Component)oComp));
	}


	
	public void add_RAW_AutoResize(IF_RB_Component oComp, Extent extBreite,  GridLayoutData oLayoutData) {
		super.add_RAW_AutoResize((Component)this.check_wrapper((Component)oComp), extBreite,  oLayoutData);
	}

	
	
	


	
	/**
	 * 2013-11-15: neues add-raw
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public void add_RAW(IF_RB_Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment)	{
		super.add_RAW(this.check_wrapper((Component)oComp),iiColSpan, iiRowSpan, oInsets, oAlignment);
	}


	
	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public void add_ext(IF_RB_Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment)	{
		super.add_ext(this.check_wrapper((Component)oComp),iiColSpan, iiRowSpan, oInsets, oAlignment);
	}

	
	
	
	

	
	
	
	public void addAfterRemoveAll(IF_RB_Component oComp)	{
		super.addAfterRemoveAll(this.check_wrapper((Component)oComp));
	}
	
	
	public void addAfterRemoveAll(IF_RB_Component oComp, Insets oInsets)	{
		super.addAfterRemoveAll(this.check_wrapper((Component)oComp), oInsets);
	}

	public void addAfterRemoveAll(IF_RB_Component oComp, GridLayoutData oLayoutData)	{
		super.addAfterRemoveAll(this.check_wrapper((Component)oComp), oLayoutData);
	}

	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param oInsets
	 * @throws myException
	 */
	public void addAfterRemoveAll(IF_RB_Component oComp,int iiColSpan, Insets oInsets)	{
		super.addAfterRemoveAll(this.check_wrapper((Component)oComp), iiColSpan, oInsets);
	}
	

	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public void addAfterRemoveAll(IF_RB_Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment)	{
		super.addAfterRemoveAll(this.check_wrapper((Component)oComp),iiColSpan, iiRowSpan, oInsets, oAlignment);
	}


    //alle add-methoden sammeln
	public void add(Component oComp) {
		super.add(this.check_wrapper(oComp));
	}
	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @param oCol
	 * @throws myException
	 */
	public void add(IF_RB_Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment, Color oCol)	{
		super.add(this.check_wrapper((Component)oComp),iiColSpan, iiRowSpan, oInsets, oAlignment, oCol);
	}

	
	public void add(Component oComp, Insets oInsets) {
		super.add(this.check_wrapper(oComp), oInsets);
	}

	public void add(Component oComp, GridLayoutData oLayoutData)	{
		super.add(this.check_wrapper(oComp), oLayoutData);
	}
	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param oInsets
	 * @throws myException
	 */
	public void add(Component oComp,int iiColSpan, Insets oInsets) {
		super.add(this.check_wrapper(oComp),iiColSpan, oInsets);
	}
	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @param oCol
	 * @throws myException
	 */
	public void add(Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment, Color oCol)	{
		super.add(this.check_wrapper(oComp),iiColSpan, iiRowSpan, oInsets, oAlignment, oCol);
	}


	
	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public void add(Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment){
		super.add(this.check_wrapper(oComp),iiColSpan,  iiRowSpan, oInsets, oAlignment);
	}
	/**
	 * 
	 * @param oComp
	 * @param oInsets
	 */
	public void add(IF_RB_Component oComp, Insets oInsets) {
		super.add(this.check_wrapper((Component)oComp), oInsets);
	}

	/**
	 * 
	 * @param oComp
	 */
	public void add(IF_RB_Component oComp) {
		super.add(this.check_wrapper((Component)oComp));
	}

	/**
	 * 
	 * @param oComp
	 * @param oLayoutData
	 */
	public void add(IF_RB_Component oComp, GridLayoutData oLayoutData)	{
		super.add(this.check_wrapper((Component)oComp), oLayoutData);
	}

	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param oInsets
	 * @throws myException
	 */
	public void add(IF_RB_Component oComp,int iiColSpan, Insets oInsets) {
		super.add(this.check_wrapper((Component)oComp),iiColSpan, oInsets);
	}


	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public void add(IF_RB_Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment){
		super.add(this.check_wrapper((Component)oComp),iiColSpan, iiRowSpan, oInsets, oAlignment);
	}


	
	//alle add-methoden verdoppeln (mit rueckgabe des eigenen objekts
	public  RB_MaskGrid _add(Component oComp) {
		this.add(oComp);
		return this;
	}
		
	
	/**
	 * append empty col
	 * @return
	 */
	public  RB_MaskGrid _add() {
		this.add(new MyE2_Label(""));
		return this;
	}
	
	
	/**
	 * append empty col with gridlayoutdata
	 * @return
	 */
	public  RB_MaskGrid _add(GridLayoutData ld) {
		this.add(new MyE2_Label(" "),ld);
		return this;
	}
	
	

	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @param oCol
	 * @throws myException
	 */
	public  RB_MaskGrid _add(IF_RB_Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment, Color oCol)	{
		this.add( oComp, iiColSpan,  iiRowSpan,  oInsets,  oAlignment,  oCol);
		return this;
	}

	
	public  RB_MaskGrid _add(Component oComp, Insets oInsets) {
		this.add(oComp, oInsets);
		return this;
	}

	public  RB_MaskGrid _add(Component oComp, GridLayoutData oLayoutData)	{
		this.add(oComp, oLayoutData);
		return this;
	}
	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param oInsets
	 * @throws myException
	 */
	public  RB_MaskGrid _add(Component oComp,int iiColSpan, Insets oInsets) {
		this.add(oComp,iiColSpan, oInsets);
		return this;
	}
	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @param oCol
	 * @throws myException
	 */
	public  RB_MaskGrid _add(Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment, Color oCol)	{
		this.add(oComp,iiColSpan, iiRowSpan, oInsets, oAlignment, oCol);
		return this;
	}


	
	
	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public  RB_MaskGrid _add(Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment){
		this.add(oComp,iiColSpan,  iiRowSpan, oInsets, oAlignment);
		return this;
	}
	
	
	/**
	 * 
	 * @param oComp
	 * @param oInsets
	 */
	public  RB_MaskGrid _add(IF_RB_Component oComp, Insets oInsets) {
		this.add(oComp, oInsets);
		return this;
	}

	/**
	 * 
	 * @param oComp
	 */
	public  RB_MaskGrid _add(IF_RB_Component oComp) {
		this.add(oComp);
		return this;
	}

	/**
	 * 
	 * @param oComp
	 * @param oLayoutData
	 */
	public  RB_MaskGrid _addIF(IF_RB_Component oComp, GridLayoutData oLayoutData)	{
		this.add(oComp, oLayoutData);
		return this;
	}

	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param oInsets
	 * @throws myException
	 */
	public  RB_MaskGrid _add(IF_RB_Component oComp,int iiColSpan, Insets oInsets) {
		this.add(oComp,iiColSpan, oInsets);
		return this;
	}


	/**
	 * @param oComp
	 * @param iColSpan  Cols, this row is using more than its own
	 * @param iRowSpan
	 * @param oInsets
	 * @throws myException
	 */
	public  RB_MaskGrid _add(IF_RB_Component oComp,int iiColSpan, int iiRowSpan, Insets oInsets, Alignment oAlignment){
		this.add(oComp,iiColSpan,  iiRowSpan, oInsets, oAlignment);
		return this;
	}


	public RB_MaskGrid _add(GridLayoutData gl, Component... components) {
		return this._add(gl,null,components);
	}

	
	public RB_MaskGrid _add(GridLayoutData gl, GridLayoutData gli, Component... components) {
		if (gli==null) {
			gli=MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,0,2,0));
		}
		if (components==null || components.length==0) {
			return this;
		} else if (components.length==1) {
			this._add(components[0],gl);
		} else {
			E2_Grid4MaskSimple grid_innen = new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			for (Component c: components) {
				c.setLayoutData(gli);
				grid_innen.add_raw(c);
			}
			grid_innen.setSize(components.length);
			this._add(grid_innen,gl);
		}
		return this;	
	}
	
	public RB_MaskGrid _add(GridLayoutData gl, IF_RB_Component... components) {
		return this._add(gl,null,components);
	}

	
	public RB_MaskGrid _add(GridLayoutData gl, GridLayoutData gli, IF_RB_Component... components) {
		if (gli==null) {
			gli=MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,0,2,0));
		}
		if (components==null || components.length==0) {
			return this;
		} else if (components.length==1) {
			this._addIF(components[0],gl);
		} else {
			E2_Grid4MaskSimple grid_innen = new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			for (IF_RB_Component c: components) {
				c.c().setLayoutData(gli);
				grid_innen.add_raw(c.c());
			}
			grid_innen.setSize(components.length);
			this._add(grid_innen,gl);
		}
		return this;	
	}
	
	
	
	
	/**
	 * fuegt eine leerzelle mit via gl bis zum zeilenende an
	 * @param gl
	 * @return
	 * @throws myException 
	 */
	public RB_MaskGrid  _endLine(GridLayoutData gl) throws myException {
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
	public RB_MaskGrid  _endLine(Object comp_, GridLayoutData gl) throws myException {
		Component comp = null;
		if (comp_ instanceof Component) {
			comp = (Component) comp_;
		}
		
		if (comp == null) {
			throw new myException(this, "comp must be not null and must extend Component !");
		}
		
		myCoordinates  coord = this.get_NextCoordinates();
		if (coord.iCol>0) {		//==0 heißt naechste zeile wuerde beginnnen
			int col_span = this.getSize()-coord.iCol;
			if (col_span>0) {
				GridLayoutData  glnew = LayoutDataFactory.get_GL_Copy(gl);
				glnew.setColumnSpan(col_span);
				this._add(comp, glnew);
			}
		}
		return this;
	}
	
	
	public RB_MaskGrid _endLine(GridLayoutData gl, GridLayoutData gli, IF_RB_Component... components) throws myException {
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
	 * fuegt eine leerzelle mit via gl definiertem colspan an (falls null oder colspan=0, dann nichts
	 * @param gl
	 * @return
	 * @throws myException 
	 */
	public RB_MaskGrid  _startLine(GridLayoutData gl) throws myException {
		//falls die zeile noch nicht fertig ist, leerzelle anhaengen
		myCoordinates  coord = this.get_NextCoordinates();
		if (coord.iCol!=0) {
			this._endLine(gl);
		}

		
		if (gl==null || gl.getColumnSpan()==0) {
			return this;
		}
		return this._add(gl);
	}

	
	/**
	 * 
	 * @param i  die spaltenbreiten in pixel definieren
	 * @return
	 */
	public RB_MaskGrid _setSize(int... i) {
		int[] i_rueck = new int[i.length];
		
		int i_pos =0;
		for (int l: i) {
			i_rueck[i_pos++]=l;
		}

		this.set_Spalten(i_rueck);
		return this;
		
	}


}
