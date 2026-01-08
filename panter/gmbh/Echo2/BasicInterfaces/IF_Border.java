package panter.gmbh.Echo2.BasicInterfaces;



import java.lang.reflect.Method;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.IF__Reflections;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;

@SuppressWarnings("unchecked")
public interface IF_Border<T> extends IF__Reflections {
	
	/**
	 * set Border
	 * @param border
	 * @return
	 */
	public default T _bord(Border border) {
		
		Method m =this.CheckMethod(this.getClass().getMethods(), "setBorder");
		
		if (m!=null) {
			try {
				m.invoke(this, border);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		return (T)this;
	}
	
//	public T setNeutralBorder(Border border);
	
	public default T setNeutralBorder(Border border) {
		return this._bord(new Border(0, new E2_ColorBase(), Border.STYLE_NONE));
	}

	
	
	/**
	 * set border with E2_ColorDark 
	 * @return
	 */
	public default T _bord_d() {
		return this._bord(new Border(1,new E2_ColorDark(), Border.STYLE_SOLID));
	}
	
	/**
	 * set border with E2_ColorDDark 
	 * @return
	 */
	public default T _bord_dd() {
		return this._bord(new Border(1,new E2_ColorDDark(), Border.STYLE_SOLID));
	}
	
	/**
	 * set border with E2_ColorDDDark 
	 * @return
	 */
	public default T _bord_ddd() {
		return this._bord(new Border(1,new E2_ColorDDDark(), Border.STYLE_SOLID));
	}

	
	/**
	 * set border with color BLACK 
	 * @return
	 */
	public default T _bord_black() {
		return this._bord(new Border(1,Color.BLACK, Border.STYLE_SOLID));
	}

	
	/**
	 * set border with color DARKGRAY 
	 * @return
	 */
	public default T _bord_dgrey() {
		return this._bord(new Border(1,Color.DARKGRAY, Border.STYLE_SOLID));
	}

	/**
	 * set border with color LIGHTGRAY 
	 * @return
	 */
	public default T _bord_lgrey() {
		return this._bord(new Border(1,Color.LIGHTGRAY, Border.STYLE_SOLID));
	}

	/**
	 * no Border
	 * @return
	 */
	public default T _nB() {
		
		Method m =this.CheckMethod(this.getClass().getMethods(), "setBorder");
		
		if (m!=null) {
			try {
				Border b = new Border(new Extent(0),Color.WHITE, Border.STYLE_SOLID);
				m.invoke(this, b);
				this.setNeutralBorder(b);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return (T) this;
	}
	
	/**
	 * single Border, DDark
	 * @return
	 */
	public default T _sBDD() {
		
		Method m =this.CheckMethod(this.getClass().getMethods(), "setBorder");
		
		if (m!=null) {
			try {
				Border b = new Border(new Extent(1),new E2_ColorDDark(), Border.STYLE_SOLID);
				m.invoke(this, b);
				this.setNeutralBorder(b);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return (T) this;
	}
	

	
}