package panter.gmbh.Echo2.BasicInterfaces;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.BasicInterfaces.IF__Reflections;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.exceptions.myException;

@SuppressWarnings("unchecked")
public interface IF_LayoutData<T>  extends IF__Reflections {
	
	
	/**
	 * 
	 * @param layout 
	 * @return
	 */
	public default T _set_ld(LayoutData layout) {
		if (this instanceof Component && this.CheckMethod(this.getClass().getMethods(),"setLayoutData")!=null) {
			try {
				this.CheckMethod(this.getClass().getMethods(),"setLayoutData").invoke(this, layout);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return (T)this;
	}

	
	
	/**
	 * 
	 * @param iLeft
	 * @return
	 */
	public default T _set_gld(int iLeft, int iTop, int iRight, int iBtm, Alignment align, Color background) {
		if (this instanceof Component && this.CheckMethod(this.getClass().getMethods(),"setLayoutData")!=null) {
			GridLayoutData  gld = new GridLayoutData();
			gld.setInsets(new Insets(iLeft, iTop, iRight, iBtm));
			if (align!=null) {
				gld.setAlignment(align);
			}
			if (background!=null) {
				gld.setBackground(background);
			}
			
			try {
				this.CheckMethod(this.getClass().getMethods(),"setLayoutData").invoke(this, gld);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return (T)this;
	}


	/**
	 * adds alignment left-top to compnent
	 * @return
	 * @throws myException
	 */
	public default T _gld_align_lt() throws myException {
		return this._gld_add_align(new Alignment(Alignment.LEFT,Alignment.TOP));
	}
	
	/**
	 * adds alignment center-top to compnent
	 * @return
	 * @throws myException
	 */
	public default T _gld_align_ct() throws myException {
		return this._gld_add_align(new Alignment(Alignment.CENTER,Alignment.TOP));
	}
	
	/**
	 * adds alignment right-top to compnent
	 * @return
	 * @throws myException
	 */
	public default T _gld_align_rt() throws myException {
		return this._gld_add_align(new Alignment(Alignment.RIGHT,Alignment.TOP));
	}
	

	
	/**
	 * adds alignment left-mid to compnent
	 * @return
	 * @throws myException
	 */
	public default T _gld_align_lm() throws myException {
		return this._gld_add_align(new Alignment(Alignment.LEFT,Alignment.CENTER));
	}
	
	/**
	 * adds alignment center-mid to compnent
	 * @return
	 * @throws myException
	 */
	public default T _gld_align_cm() throws myException {
		return this._gld_add_align(new Alignment(Alignment.CENTER,Alignment.CENTER));
	}
	
	/**
	 * adds alignment right-mid to compnent
	 * @return
	 * @throws myException
	 */
	public default T _gld_align_rm() throws myException {
		return this._gld_add_align(new Alignment(Alignment.RIGHT,Alignment.CENTER));
	}
	
	
	/**
	 * adds alignment left-bottom to compnent
	 * @return
	 * @throws myException
	 */
	public default T _gld_align_lb() throws myException {
		return this._gld_add_align(new Alignment(Alignment.LEFT,Alignment.BOTTOM));
	}
	
	/**
	 * adds alignment center-bottom to compnent
	 * @return
	 * @throws myException
	 */
	public default T _gld_align_cb() throws myException {
		return this._gld_add_align(new Alignment(Alignment.CENTER,Alignment.BOTTOM));
	}
	
	/**
	 * adds alignment right-bottom to compnent
	 * @return
	 * @throws myException
	 */
	public default T _gld_align_rb() throws myException {
		return this._gld_add_align(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
	}
	
	

	
	
	public default T _gld_add_align(Alignment align) throws myException {
		if (this instanceof Component && (this.CheckMethod(this.getClass().getMethods(),"setLayoutData")!=null) && (this.CheckMethod(this.getClass().getMethods(),"getLayoutData")!=null)) {
			try {
				LayoutData oldLd = (LayoutData)this.CheckMethod(this.getClass().getMethods(),"getLayoutData").invoke(this);
				GridLayoutData gld = null;
				if (oldLd!=null && oldLd instanceof GridLayoutData) {
					gld = (GridLayoutData)oldLd;
					gld.setAlignment(align);
				} else {
					gld = new RB_gld()._al(align);
				}
				this.CheckMethod(this.getClass().getMethods(),"setLayoutData").invoke(this, gld);
			} catch (Exception e) {
				e.printStackTrace();
				throw new myException(e.getMessage()+":SYSTEM-Error: Executing setLayoutData or getLayoutData causes error "+this.getClass().getName());
			}
		} else {
			throw new myException("SYSTEM-Error: Method setLayoutData or getLayoutData not existing in class "+this.getClass().getName());
		}
		return (T)this;
	}
	
	
	
	
	public default T _gld_add_insets(Insets insets) throws myException {
		if (this instanceof Component && (this.CheckMethod(this.getClass().getMethods(),"setLayoutData")!=null) && (this.CheckMethod(this.getClass().getMethods(),"getLayoutData")!=null)) {
			try {
				LayoutData oldLd = (LayoutData)this.CheckMethod(this.getClass().getMethods(),"getLayoutData").invoke(this);
				GridLayoutData gld = null;
				if (oldLd!=null && oldLd instanceof GridLayoutData) {
					gld = (GridLayoutData)oldLd;
					gld.setInsets(insets);
				} else {
					gld = new RB_gld()._ins(insets);
				}
				this.CheckMethod(this.getClass().getMethods(),"setLayoutData").invoke(this, gld);
			} catch (Exception e) {
				e.printStackTrace();
				throw new myException(e.getMessage()+":SYSTEM-Error: Executing setLayoutData or getLayoutData causes error "+this.getClass().getName());
			}
		} else {
			throw new myException("SYSTEM-Error: Method setLayoutData or getLayoutData not existing in class "+this.getClass().getName());
		}
		return (T)this;
	}

	
	public default T _gld_add_insets(int left, int top, int right, int bottom) throws myException {
		return this._gld_add_insets(new Insets(left,  top,  right,  bottom));
	}
	
}

	
	

