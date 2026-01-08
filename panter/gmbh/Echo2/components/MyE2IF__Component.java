package panter.gmbh.Echo2.components;

import java.lang.reflect.Method;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.BasicInterfaces.IF__Reflections;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.BasicInterfaces.IF_wrapInBorder;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public interface MyE2IF__Component extends IF__Reflections,IF_ConvertibleToComponent
{
	/*
	 * EXT enthaelt alle settings, die ohne zusatzcode uebergeben und uebernommen werden
	 */
	public void set_EXT(MyE2EXT__Component oEXT);
	public MyE2EXT__Component EXT();

	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException;
	
	public void show_InputStatus(boolean bInputIsOK);
	
	//2014-09-08: wrapper-methoden, die eine funktion liefern, um die komponente in eine Grid zu wickeln, wobei dann zusatzelemente eingefuegt werden koennen
	public void set_ME_First(boolean ME_InFront);
	public void set_SpaceInPX(int iSpace);
	public MyE2IF__Component ME() throws myException;
	public Component C_ME() throws myException;
	public Vector<MyE2IF__Component> get_vADDONS_IN_WRAP() throws myException;
	
	
	
	
	/**
	 * rueckgabe eines MyE2_Grid, das die komponente umfasst als rahmen
	 * @param colorOfBorder
	 * @param layout4component  (fuer die anordnung der komponente in dem 1x1-grid)
	 * @param breite
	 * @return
	 */
	default public MyE2_Grid  in_border(Color colorOfBorder, GridLayoutData layout4component, Extent breite, Extent hoehe) {
		
		MyE2_Grid grid = new MyE2_Grid(1);
		if (breite!=null) {
			grid.setColumnWidth(0, breite);
		}
		if (hoehe!=null) {
			grid.setRowHeight(0, hoehe);
		}
		
		grid.setInsets(E2_INSETS.I(0,0,0,0));
		grid.setBorder(new Border(1, colorOfBorder==null?new E2_ColorDDDark():colorOfBorder,Border.STYLE_SOLID));
		
		grid.add((Component)this, layout4component==null?MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,0,0,0)):layout4component);
		return grid;
	}
	
	
	
	default public E2_Grid in_border_black() {
		E2_Grid g = new E2_Grid()._s(1);
		g	._a((Component)this,new RB_gld()._left_top()._ins(2))
			._bo_b()
			;
		return g;
	}
	
	default public E2_Grid in_border_dd() {
		E2_Grid g = new E2_Grid()._s(1);
		g	._a((Component)this,new RB_gld()._left_top()._ins(2))
			._bo_dd()
			;
		return g;
	}
	
	
	
	default public E2_Grid in_border_ddd() {
		E2_Grid g = new E2_Grid()._s(1);
		g	._a((Component)this,new RB_gld()._left_top()._ins(2))
			._bo_ddd()
			;
		return g;
	}
	
	
	default public E2_Grid in_grid() {
		return new E2_Grid()._gld(new RB_gld()._center_mid())._a((Component)this);
	}

	default public E2_Grid in_grid(Extent width, Extent height, Border border, RB_gld  gld) {
		return new E2_Grid()._w(width)._h(height)._bo(border)._gld(gld)._a((Component)this);
	}

	default public E2_Grid in_grid(int width, int height) {
		return new E2_Grid()._w(new Extent(width))._h(new Extent(height))._a((Component)this);
	}
	
	
	
	default public Component c() {
		return (Component)this;
	}
	
	
	public default void focus_off() {
		((Component)this).setFocusTraversalParticipant(false);
	}
	public default void focus_on() {
		((Component)this).setFocusTraversalParticipant(true);
	}

	
	//2020-01-03: focus-index setzen
	public default MyE2IF__Component _setFocusTraversalIndex(int index) {
		((Component)this).setFocusTraversalIndex(index);
		return this;
	}
	public default MyE2IF__Component _setFI(int index) {
		return this._setFocusTraversalIndex(index);
	}
	
	

	/**
	 * 
	 * @author martin
	 * @date 11.03.2020
	 *
	 * @return
	 */
	public default MyE2IF__Component _setFocus() {
		if (this instanceof Component) {
			ApplicationInstance.getActive().setFocusedComponent((Component)this);
		}
		return this;
	}
	
	
	/**
	 * 
	 * @param tooltips
	 * @return
	 * @throws myException
	 */
	public default MyE2IF__Component _ttt(MyE2_String tooltips) throws myException{
		if (this instanceof AbstractButton) {
			((AbstractButton)this).setToolTipText(tooltips.CTrans());
		}  else {
			Method m =this.CheckMethod(this.getClass().getMethods(), "setToolTipText");
			
			if (m!=null) {
				try {
					m.invoke(this, tooltips.CTrans());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else { 
				throw new myException("methode _ttt() can only be used at class of type AbstractButton");
			}
		}
		return this;
	}

	

	/**
	 * 
	 * @param tooltips
	 * @return
	 * @throws myException
	 */
	public default MyE2IF__Component _ttt_ut(String tooltips) throws myException{
		if (this instanceof AbstractButton) {
			((AbstractButton)this).setToolTipText(tooltips);
		}  else {
			Method m =this.CheckMethod(this.getClass().getMethods(), "setToolTipText");
			
			if (m!=null) {
				try {
					m.invoke(this, tooltips);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else { 
				throw new myException("methode setToolTipText() not found !");
			}
		}
		return this;
	}

	
	
	/**
	 * 
	 * @param tooltips
	 * @return onw class
	 */
	public default MyE2IF__Component _ttt_ut_if_longer(String tooltips){
		if (S.NN(tooltips).equals("")) {
			return this;
		}

		Method m =this.CheckMethod(this.getClass().getMethods(), "setToolTipText");
		Method m2 =this.CheckMethod(this.getClass().getMethods(), "getToolTipText");
		if (m!=null && m2!=null) {
			try {
				String old_tt = S.NN((String) m2.invoke(this));
				
				if (old_tt.length()<tooltips.length()) {
					m.invoke(this, tooltips);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this;
	}

	
	
	
	/**
	 * 
	 * @param align
	 * @return onw class
	 */
	public default MyE2IF__Component _align(Alignment align){
		if (align==null) {
			return this;
		}

		
		Method m =this.CheckMethod(this.getClass().getMethods(), "setAlignment");
		if (m!=null) {
			try {
				m.invoke(this, align);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this;
	}


	/**
	 * align left 
	 * @return onw class
	 * @deprecated
	 * use interface IF_align instead
	 */
	public default MyE2IF__Component _align_left(){
		return this._align(new Alignment(Alignment.LEFT, Alignment.CENTER));
	}
	
	/**
	 * align center 
	 * @return onw class
	 * @deprecated
	 * use interface IF_align instead
	 */
	public default MyE2IF__Component _align_center(){
		return this._align(new Alignment(Alignment.CENTER, Alignment.CENTER));
	}

	/**
	 * align right 
	 * @return onw class
	 * @deprecated
	 * use interface IF_align instead
	 */
	public default MyE2IF__Component _align_right(){
		return this._align(new Alignment(Alignment.RIGHT, Alignment.CENTER));
	}


	
	
	/**
	 * 
	 * @param tooltips (is translated)
	 * @return
	 * @throws myException
	 */
	public default MyE2IF__Component _ttt(String tooltips) throws myException{
		if (this instanceof AbstractButton) {
			((AbstractButton)this).setToolTipText(new MyE2_String(tooltips).CTrans());
		}  else {
			Method m =this.CheckMethod(this.getClass().getMethods(), "setToolTipText");
			
			if (m!=null) {
				try {
					m.invoke(this, tooltips);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else { 
				throw new myException("methode setToolTipText() not found !");
			}
		}
		return this;
	}

	
	/**
	 * 
	 * @param tooltips (is translated)
	 * @return
	 * @throws myException
	 */
	public default MyE2IF__Component _ttt_when_possible(String tooltips) {
		if (this instanceof AbstractButton) {
			((AbstractButton)this).setToolTipText(new MyE2_String(tooltips).CTrans());
		}  else {
			Method m =this.CheckMethod(this.getClass().getMethods(), "setToolTipText");
			
			if (m!=null) {
				try {
					m.invoke(this, tooltips);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return this;
	}

	
	/**
	 * 
	 * @param wrapper (lambda-term, sets the border type)
	 * @return
	 * @throws myException
	 */
	public default E2_Grid  inBorder(IF_wrapInBorder wrapper) throws myException {
		return wrapper.in_border((Component)this);
	}
	
	
	/*
	 * 2018-08-02: erlauterungstext fuer dir Listenauswahl hinterlegen
	 */
	public default MyE2IF__Component _setLongText4ColumnSelection(MyString text) {
		this.EXT().setLongString4ColumnSelection(text);
		return this;
	}
	
	/*
	 * 2018-08-09: listenlayout in die EXT einfuegen
	 */
	public default MyE2IF__Component _setGridLayout4List(RB_gld ld) {
		this.EXT().set_oLayout_ListElement(ld);
		return this;
	}
	
	/*
	 * 2018-08-09: ListenTitleLayout in die EXT einfuegen
	 */
	public default MyE2IF__Component _setGridLayout4ListTitle(RB_gld ld) {
		this.EXT().set_oLayout_ListTitelElement(ld);
		return this;
	}
	
	/*
	 * 2019-02-19: style von automatisch erzeugten listensort-buttons
	 */
	public default MyE2IF__Component _setStyleForSortButton(MutableStyle s) {
		this.EXT().setStyleOfAutoTitelButton(s);
		return this;
	}
	
	/**
	 * 
	 * @param ld
	 * @return MyE2IF__Component (itself)
	 */
	public default MyE2IF__Component _setLD(LayoutData ld){
		if (ld==null) {
			return this;
		}
		Method m =this.CheckMethod(this.getClass().getMethods(), "setLayoutData");
		if (m!=null) {
			try {
				m.invoke(this, ld);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this;
	}



	/**
	 * 
	 * @param ld
	 * @return (Component) itself
	 */
	public default Component _setLDC(LayoutData ld){
		if (ld==null) {
			return (Component)this;
		}
		Method m =this.CheckMethod(this.getClass().getMethods(), "setLayoutData");
		if (m!=null) {
			try {
				m.invoke(this, ld);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (Component)this;
	}



	/**
	 * 
	 * @author martin
	 * @date 18.02.2019
	 *
	 * @param w
	 * @return
	 */
	public default MyE2IF__Component _setWidth(int w) {
		this.EXT().set_oColExtent(new Extent(w));
		return this;
	}
	
	/**
	 * 
	 * @author martin
	 * @date 18.02.2019
	 *
	 * @param w
	 * @return
	 */
	public default MyE2IF__Component _setWidthOfTitleButton(int w) {
		this.EXT().setWidthOfTitelSortButton(w);
		return this;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 18.02.2019
	 *
	 * @param w
	 * @param wButton
	 * @return
	 */
	public default MyE2IF__Component _setWidth(int wcol, int wButton) {
		this.EXT().set_oColExtent(new Extent(wcol));
		this.EXT().setWidthOfTitelSortButton(wButton);
		return this;
	}
	
	
}
