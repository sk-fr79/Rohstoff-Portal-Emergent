package panter.gmbh.Echo2.BasicInterfaces;



import java.lang.reflect.Method;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.BasicInterfaces.IF__Reflections;

@SuppressWarnings("unchecked")

public interface IF_Dimension<T> extends IF__Reflections{
	
	/**
	 * sets Width
	 * @param backcolor
	 * @return
	 */
	public default T _width(Extent width) {
		Method m =this.CheckMethod(this.getClass().getMethods(), "setWidth");

		if (m!=null) {
			try {
				m.invoke(this, width);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return (T)this;
	}
	
	
	/**
	 * sets Width
	 * @param backcolor
	 * @return
	 */
	public default T _width(int width) {
		Method m =this.CheckMethod(this.getClass().getMethods(), "setWidth");

		if (m!=null) {
			try {
				m.invoke(this, new Extent(width));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return (T)this;
	}
	

	/**
	 * sets Width
	 * @param backcolor
	 * @return
	 */
	public default T _height(Extent width) {
		Method m =this.CheckMethod(this.getClass().getMethods(), "setHeight");

		if (m!=null) {
			try {
				m.invoke(this, width);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return (T)this;
	}
	
	
	/**
	 * sets height
	 * @param backcolor
	 * @return
	 */
	public default T _height(int height) {
		Method m =this.CheckMethod(this.getClass().getMethods(), "setHeight");

		if (m!=null) {
			try {
				m.invoke(this, new Extent(height));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return (T)this;
	}

	
	
	public default T _sizeWH(int width, int height) {
		this._width(width);
		this._height(height);
		
		return (T) this;
	}
	
	
	
	/**
	 * sets number of rows
	 * @param backcolor
	 * @return
	 */
	public default T _rows(int rows) {
		Method m =this.CheckMethod(this.getClass().getMethods(), "setHeight");

		if (m!=null) {
			try {
				m.invoke(this, new Extent(rows, Extent.PC));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return (T)this;
	}

	
	/**
	 * sets insets round same pixels
	 * @param backcolor
	 * @return
	 */
	public default T _insets(int iRound) {
		Method m =this.CheckMethod(this.getClass().getMethods(), "setInsets");

		if (m!=null) {
			try {
				m.invoke(this, new Insets(iRound,iRound,iRound,iRound));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return (T)this;
	}


	/**
	 * sets insets round same pixels
	 * @param backcolor
	 * @return
	 */
	public default T _insets(int left, int top, int right, int bottom) {
		Method m =this.CheckMethod(this.getClass().getMethods(), "setInsets");

		if (m!=null) {
			try {
				m.invoke(this, new Insets(left,top,right,bottom));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return (T)this;
	}


	
}