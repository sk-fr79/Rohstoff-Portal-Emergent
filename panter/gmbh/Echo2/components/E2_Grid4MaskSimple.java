package panter.gmbh.Echo2.components;


import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Style;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;


public class E2_Grid4MaskSimple extends MyE2_Grid {

	private E2_ComponentMAP     map = null;
	private Insets  			usedInset = E2_INSETS.I(0,0,4,0);
	private int 				iCols  = 0;
	private int       			iUsedColWidth = 100;
	private Alignment           alignment = new Alignment(Alignment.LEFT, Alignment.TOP);
	private int    				iColSpan = 1;
	private int    				iRowSpan = 1;
	private Color               color_back = null;
	
	public E2_Grid4MaskSimple() {
		super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	}

	
	public E2_Grid4MaskSimple(MutableStyle  style) {
		super(style);
	}

	
	/**
	 * Defining actual insets
	 * @param insets4Mask
	 * @return
	 */
	public E2_Grid4MaskSimple def_(Insets insets4Mask) {
		this.usedInset=insets4Mask;
		return this;
	}

	/**
	 * Defining actual Alignment
	 * @param p_align
	 * @return
	 */
	public E2_Grid4MaskSimple def_(Alignment p_align) {
		this.alignment=p_align;
		return this;
	}

	
	/**
	 * Defining actual Alignment
	 * @param left top
	 * @return
	 */
	public E2_Grid4MaskSimple left_top_() {
		this.alignment=new Alignment(Alignment.LEFT, Alignment.TOP);
		return this;
	}

	
	/**
	 * Defining actual Alignment
	 * @param left center
	 * @return
	 */
	public E2_Grid4MaskSimple left_center_() {
		this.alignment=new Alignment(Alignment.LEFT, Alignment.CENTER);
		return this;
	}
	
	/**
	 * Defining actual Alignment
	 * @param left bottom
	 * @return
	 */
	public E2_Grid4MaskSimple left_bottom_() {
		this.alignment=new Alignment(Alignment.LEFT, Alignment.BOTTOM);
		return this;
	}
	

	/**
	 * Defining actual Alignment
	 * @param left top
	 * @return
	 */
	public E2_Grid4MaskSimple center_top_() {
		this.alignment=new Alignment(Alignment.CENTER, Alignment.TOP);
		return this;
	}

	
	/**
	 * Defining actual Alignment
	 * @param left center
	 * @return
	 */
	public E2_Grid4MaskSimple center_center_() {
		this.alignment=new Alignment(Alignment.CENTER, Alignment.CENTER);
		return this;
	}
	
	/**
	 * Defining actual Alignment
	 * @param left bottom
	 * @return
	 */
	public E2_Grid4MaskSimple center_bottom_() {
		this.alignment=new Alignment(Alignment.CENTER, Alignment.BOTTOM);
		return this;
	}
	

	/**
	 * Defining actual Alignment
	 * @param left top
	 * @return
	 */
	public E2_Grid4MaskSimple right_top_() {
		this.alignment=new Alignment(Alignment.RIGHT, Alignment.TOP);
		return this;
	}

	
	/**
	 * Defining actual Alignment
	 * @param left center
	 * @return
	 */
	public E2_Grid4MaskSimple right_center_() {
		this.alignment=new Alignment(Alignment.RIGHT, Alignment.CENTER);
		return this;
	}
	
	/**
	 * Defining actual Alignment
	 * @param left bottom
	 * @return
	 */
	public E2_Grid4MaskSimple right_bottom_() {
		this.alignment=new Alignment(Alignment.RIGHT, Alignment.BOTTOM);
		return this;
	}
	
	
	
	/**
	 * Defining actual style
	 * @param style
	 * @return
	 */
	public E2_Grid4MaskSimple def_(Style style) {
		this.setStyle(style);
		return this;
	}


	/**
	 * Defining actual style
	 * @param colSpan
	 * @param rowSpan
	 * @return
	 */
	public E2_Grid4MaskSimple def_(int colSpan, int rowSpan) {
		this.iColSpan = colSpan;
		this.iRowSpan = rowSpan;
		return this;
	}

	
	
	/**
	 * defining actual componentmap
	 * @param p_map
	 * @return
	 */
	public E2_Grid4MaskSimple def_(E2_ComponentMAP p_map) {
		this.map = p_map;
		return this;
	}

	
	/**
	 * defining actual columnwidth
	 * @param columnWidth
	 * @return
	 */
	public E2_Grid4MaskSimple def_(int columnWidth) {
		this.iUsedColWidth = columnWidth;
		return this;
	}

	/**
	 * defining actual backgroundcolor
	 * @param p_color
	 * @return
	 */
	public E2_Grid4MaskSimple def_(Color p_color) {
		this.color_back = p_color;
		return this;
	}


	/**
	 * Label
	 * @param s (adds a myLabel-Object)
	 * @return
	 */
	public E2_Grid4MaskSimple add_(MyE2_String s) {
		this.iCols++;
		this.setSize(iCols);
		this.add(new MyE2_Label(s),this.generateGl());
		this.define_col_width();
		return this;
	}

	/**
	 * Label
	 * @param s (adds a myLabel-Object)
	 * @return
	 */
	public E2_Grid4MaskSimple add_(Component c) {
		this.iCols++;
		this.setSize(iCols);
		this.add_raw(c,this.generateGl());
		this.define_col_width();
		return this;
	}

	/**
	 * Label
	 * @param s (adds a myLabel-Object)
	 * @return
	 */
	public E2_Grid4MaskSimple add_without_setting_layout_data_(Component c) {
		this.iCols++;
		this.setSize(iCols);
		this.add_raw(c);
		this.define_col_width();
		return this;
	}


	/**
	 * adds a field from Componentmap
	 * @param field_name
	 * @return
	 * @throws myException
	 */
	public E2_Grid4MaskSimple add_(String field_name) throws myException {
		if (this.map==null) {
			throw new myException(this,"IT MUST BE A MAP DEFINED");
		}
		this.iCols++;
		this.setSize(iCols);
		Component comp = (Component)this.map.get__Comp(field_name);
		this.define_col_width();
		this.add_raw(comp,this.generateGl());
		return this;
	}


	/**
	 * adds a field from Componentmap
	 * @param field_name
	 * @return
	 * @throws myException
	 */
	public E2_Grid4MaskSimple add_(IF_Field field_name) throws myException {
		return this.add_(field_name.fn());
	}

	/**
	 */
	public E2_Grid4MaskSimple setSize_(int iSize) throws myException {
		this.setSize(iSize);
		return this;
	}


	public E2_Grid4MaskSimple setSize_(int[] iColWidhArray) throws myException {
		this.set_Spalten(iColWidhArray);
		return this;
	}
	
	
	
	
	
	private void define_col_width() {
		if (this.iUsedColWidth>0) {
			this.setColumnWidth(this.getSize()-1, new Extent(this.iUsedColWidth));
		}
	}
	
	
	private GridLayoutData generateGl() {
	   GridLayoutData gl = new GridLayoutData();
	   
	   if (this.usedInset!=null) {
		   gl.setInsets(this.usedInset);
	   }
		
	   if (this.alignment!=null){
		   gl.setAlignment(this.alignment);
	   }
	   
	   if (this.iColSpan>0) {
		   gl.setColumnSpan(this.iColSpan);
	   }
	   
	   if (this.iRowSpan>0) {
		   gl.setRowSpan(this.iRowSpan);
	   }
	   
	   if (this.color_back != null) {
		   gl.setBackground(this.color_back);
	   }
	   
	   //debug
	   //DEBUG.System_println(" Colspan:   "+gl.getColumnSpan());
	   
	   return gl;
	}
	
	/**
	 * 
	 * @param i  die spaltenbreiten in pixel definieren
	 * @return
	 */
	public E2_Grid4MaskSimple _setSize(int... i) {
		int[] i_rueck = new int[i.length];
		
		int i_pos =0;
		for (int l: i) {
			i_rueck[i_pos++]=l;
		}

		this.set_Spalten(i_rueck);
		return this;
		
	}
	
	/**
	 * 2016-02-23
	 * @param comp
	 * @param gl
	 * @return
	 */
	public E2_Grid4MaskSimple _add(Component comp, GridLayoutData gl) {
		comp.setLayoutData(gl);
		super.add(comp);
		return this;
	}

	
}
