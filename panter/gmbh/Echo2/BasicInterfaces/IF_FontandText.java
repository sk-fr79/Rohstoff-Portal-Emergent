package panter.gmbh.Echo2.BasicInterfaces;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Style;
import nextapp.echo2.app.button.AbstractButton;
import panter.gmbh.BasicInterfaces.IF__Reflections;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

@SuppressWarnings("unchecked")
public interface IF_FontandText<T>  extends IF__Reflections{

	/**
	 * set Font
	 * @param f
	 * @return
	 */
	public default T _fo(Font f) {
		if (this instanceof Component) {
			((Component)this).setFont(f);
		}
		return (T)this;
	}

	
	/**
	 * 
	 * @param iSizeAdd  (increase or decrease fontsize)
	 * @return
	 */
	public default T _fo_s_plus(int iSizeAdd) {
		if (this instanceof Component) {
			Font f = ((Component)this).getFont();
			if (f==null) {
				Style st = ((Component)this).getStyle();
				if (st!=null &&st.getProperty(AbstractButton.PROPERTY_FONT)!=null) {
					f= (Font)st.getProperty(AbstractButton.PROPERTY_FONT);
				} else {
					f=new E2_FontPlain();
				}
			}
			
			int style = 0;   //entspricht plain
			if (f.isBold()) {style+=Font.BOLD;}
			if (f.isItalic()) {style+=Font.ITALIC;}
			if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
			if (f.isOverline()) {style+=Font.OVERLINE;}
			if (f.isUnderline()) {style+=Font.UNDERLINE;}
			
			((Component)this).setFont(new Font(f.getTypeface(),style, new Extent(f.getSize().getValue()+iSizeAdd,Extent.PT)));
		}
		return (T)this;
	}
	
	
	/**
	 * set fontsize
	 * @param iSize
	 * @return
	 */
	public default T _fo_s(int iSize) {
		if (this instanceof Component) {
			Component oThis = (Component)this;
			Font f = oThis.getFont();
			if (f==null) {
				Style st = oThis.getStyle();
				if (st!=null &&st.getProperty(AbstractButton.PROPERTY_FONT)!=null) {
					f= (Font)st.getProperty(AbstractButton.PROPERTY_FONT);
				} else {
					f=new E2_FontPlain();
				}
			}
			int style = 0;   //entspricht plain
			if (f.isBold()) {style+=Font.BOLD;}
			if (f.isItalic()) {style+=Font.ITALIC;}
			if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
			if (f.isOverline()) {style+=Font.OVERLINE;}
			if (f.isUnderline()) {style+=Font.UNDERLINE;}
			
			oThis.setFont(new Font(f.getTypeface(),style, new Extent(iSize,Extent.PT)));
		}
		return (T)this;
	}
	
	
	/**
	 * set font bold
	 * @return
	 */
	public default T _fo_bold() {
		if (this instanceof Component) {
			Component oThis = (Component)this;
			Font f = oThis.getFont();
			if (f==null) {
				Style st = oThis.getStyle();
				if (st!=null &&st.getProperty(AbstractButton.PROPERTY_FONT)!=null) {
					f= (Font)st.getProperty(AbstractButton.PROPERTY_FONT);
				} else {
					f=new E2_FontPlain();
				}
			}
			int style = Font.BOLD;   //entspricht plain
			if (f.isItalic()) {style+=Font.ITALIC;}
			if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
			if (f.isOverline()) {style+=Font.OVERLINE;}
			if (f.isUnderline()) {style+=Font.UNDERLINE;}
			
			oThis.setFont(new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		
		}
		return (T)this;
	}
	
	
	
	
	/**
	 * set font bold
	 * @return
	 */
	public default T _fo_plain() {
		if (this instanceof Component) {
			Component oThis = (Component)this;
			Font f = oThis.getFont();
			if (f==null) {
				Style st = oThis.getStyle();
				if (st!=null &&st.getProperty(AbstractButton.PROPERTY_FONT)!=null) {
					f= (Font)st.getProperty(AbstractButton.PROPERTY_FONT);
				} else {
					f=new E2_FontPlain();
				}
			}
			int style = Font.PLAIN;   //entspricht plain
			if (f.isItalic()) {style+=Font.ITALIC;}
			if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
			if (f.isOverline()) {style+=Font.OVERLINE;}
			if (f.isUnderline()) {style+=Font.UNDERLINE;}
			
			oThis.setFont(new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		
		}
		return (T)this;
	}

	
	
	
	
	
	/**
	 * set font italic
	 * @return
	 */
	public default  T _fo_italic() {
		if (this instanceof Component) {
			Component oThis = (Component)this;
			Font f = oThis.getFont();
			if (f==null) {
				Style st = oThis.getStyle();
				if (st!=null &&st.getProperty(AbstractButton.PROPERTY_FONT)!=null) {
					f= (Font)st.getProperty(AbstractButton.PROPERTY_FONT);
				} else {
					f=new E2_FontPlain();
				}
			}
			int style = Font.ITALIC;   //entspricht plain
			if (f.isBold()) {style+=Font.BOLD;}
			if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
			if (f.isOverline()) {style+=Font.OVERLINE;}
			if (f.isUnderline()) {style+=Font.UNDERLINE;}
			
			oThis.setFont(new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		}
		return (T)this;
	}


	/**
	 * set font underlined
	 * @return
	 */
	public default  T _fo_underline() {
		if (this instanceof Component) {
			Component oThis = (Component)this;
			Font f = oThis.getFont();
			if (f==null) {
				Style st = oThis.getStyle();
				if (st!=null &&st.getProperty(AbstractButton.PROPERTY_FONT)!=null) {
					f= (Font)st.getProperty(AbstractButton.PROPERTY_FONT);
				} else {
					f=new E2_FontPlain();
				}
			}
			int style = Font.UNDERLINE;   //entspricht plain
			if (f.isBold()) {style+=Font.BOLD;}
			if (f.isItalic()) {style+=Font.ITALIC;}
			if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
			if (f.isOverline()) {style+=Font.OVERLINE;}
			
			oThis.setFont(new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		}
		return (T)this;
	}

	
	
	/**
	 * set font underlined
	 * @return
	 */
	public default  T _fo_lineThrough() {
		if (this instanceof Component) {
			Component oThis = (Component)this;
			Font f = oThis.getFont();
			if (f==null) {
				Style st = oThis.getStyle();
				if (st!=null &&st.getProperty(AbstractButton.PROPERTY_FONT)!=null) {
					f= (Font)st.getProperty(AbstractButton.PROPERTY_FONT);
				} else {
					f=new E2_FontPlain();
				}
			}
			int style = Font.LINE_THROUGH;   //entspricht plain
			if (f.isBold()) {style+=Font.BOLD;}
			if (f.isItalic()) {style+=Font.ITALIC;}
			if (f.isUnderline()) {style+=Font.UNDERLINE;}
			if (f.isOverline()) {style+=Font.OVERLINE;}
			
			oThis.setFont(new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		}
		return (T)this;
	}

	

	
	/**
	 * 
	 * @param wrap
	 * @return
	 */
	public default T  _line_wrap(boolean wrap) {
		if (this instanceof Component && this.CheckMethod(this.getClass().getMethods(),"setLineWrap")!=null) {
			try {
				this.CheckMethod(this.getClass().getMethods(),"setLineWrap").invoke(this, wrap);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}

		
//		if (this instanceof Button) {
//			Button oThis = (Button)this;
//			oThis.setLineWrap(wrap);
//		}
//		if (this instanceof Label) {
//			Label oThis = (Label)this;
//			oThis.setLineWrap(wrap);
//		}
//		
//		if (this instanceof CheckBox) {
//			CheckBox oThis = (CheckBox)this;
//			oThis.setLineWrap(wrap);
//		}
		
		return (T)this;
	}

	/**
	 * 
	 * activate linewrap
	 * @return
	 */
	public default T  _lw() {
		if (this instanceof Component && this.CheckMethod(this.getClass().getMethods(),"setLineWrap")!=null) {
			try {
				this.CheckMethod(this.getClass().getMethods(),"setLineWrap").invoke(this, true);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return (T)this;
	}

	/**
	 * 
	 * deactivate linewrap
	 * @return
	 */
	public default T  _lwn() {
		if (this instanceof Component && this.CheckMethod(this.getClass().getMethods(),"setLineWrap")!=null) {
			try {
				this.CheckMethod(this.getClass().getMethods(),"setLineWrap").invoke(this, false);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return (T)this;
	}
	
	
	/**
	 * 
	 * @param text (is translated)
	 * @return
	 */
	public default  T _txt_trans(String text) {
		return this._txt(new MyE2_String(text).CTrans());
	}

	/**
	 * Sets Text 
	 * @param text (no translation)
	 * @return
	 */
	public default T _txt(String text) {
		if (this instanceof Component && this.CheckMethod(this.getClass().getMethods(),"setText")!=null) {
			try {
				this.CheckMethod(this.getClass().getMethods(),"setText").invoke(this, text);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return (T)this;
	}

	

	
	
	/**
	 * Sets Text 
	 * @param text
	 * @return
	 */
	public default T _txt(MyString text) {
		return this._txt(text.CTrans());
	}
	

	
	
	/**
	 * Sets Tooltips 
	 * @param text (untranslated)
	 * @return
	 */
	public default T _tooltxt(String text) {
		if (this instanceof Component && this.CheckMethod(this.getClass().getMethods(),"setToolTipText")!=null) {
			try {
				this.CheckMethod(this.getClass().getMethods(),"setToolTipText").invoke(this, text);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return (T)this;
	}

	/**
	 * Sets Tooltips 
	 * @param text (translated)
	 * @return
	 */
	public default T _tooltxt_tr(String text) {
		if (this instanceof Component && this.CheckMethod(this.getClass().getMethods(),"setToolTipText")!=null) {
			try {
				this.CheckMethod(this.getClass().getMethods(),"setToolTipText").invoke(this, new MyE2_String(text).CTrans());
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return (T)this;
	}

	

	/**
	 * Sets Tooltips 
	 * @param text
	 * @return
	 */
	public default T _tooltxt(MyString text) {
		if (this instanceof Component && this.CheckMethod(this.getClass().getMethods(),"setToolTipText")!=null) {
			try {
				this.CheckMethod(this.getClass().getMethods(),"setToolTipText").invoke(this, text.CTrans());
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return (T)this;
	}

	
	
	
	//20170803: weitere textmethoden zum verketten
	/**
	 * 
	 * @param text (no translation)
	 * @return
	 */
	public default T _t_add(String text) throws myException {
		if (this instanceof Component && (this.CheckMethod(this.getClass().getMethods(),"setText")!=null) && (this.CheckMethod(this.getClass().getMethods(),"getText")!=null)) {
			try {
				String oldString = S.NN((String)this.CheckMethod(this.getClass().getMethods(),"getText").invoke(this));
				String newString = oldString+S.NN(text);
				this.CheckMethod(this.getClass().getMethods(),"setText").invoke(this, newString);
			} catch (Exception e) {
				e.printStackTrace();
				throw new myException(e.getMessage()+":SYSTEM-Error: Method setText or getText not existing in class "+this.getClass().getName());
			}
		} else {
			throw new myException("SYSTEM-Error: Method setText or getText not existing in class "+this.getClass().getName());
		}
		return (T)this;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public default T _t_add(MyE2_String text) throws myException {
		if (this instanceof Component && (this.CheckMethod(this.getClass().getMethods(),"setText")!=null) && (this.CheckMethod(this.getClass().getMethods(),"getText")!=null)) {
			try {
				String oldString = S.NN((String)this.CheckMethod(this.getClass().getMethods(),"getText").invoke(this));
				String newString = oldString+S.NN(text.CTrans());
				this.CheckMethod(this.getClass().getMethods(),"setText").invoke(this, newString);
			} catch (Exception e) {
				e.printStackTrace();
				throw new myException(e.getMessage()+":SYSTEM-Error: Method setText or getText not existing in class "+this.getClass().getName());
			}
		} else {
			throw new myException("SYSTEM-Error: Method setText or getText not existing in class "+this.getClass().getName());
		}
		return (T)this;
	}

	/**
	 * 
	 * @param text (is translated)
	 * @return
	 */
	public default T _tr_add(String text) throws myException {
		if (this instanceof Component && (this.CheckMethod(this.getClass().getMethods(),"setText")!=null) && (this.CheckMethod(this.getClass().getMethods(),"getText")!=null)) {
			try {
				String oldString = S.NN((String)this.CheckMethod(this.getClass().getMethods(),"getText").invoke(this));
				String newString = oldString+S.NN(new MyE2_String(text).CTrans());
				this.CheckMethod(this.getClass().getMethods(),"setText").invoke(this, newString);
			} catch (Exception e) {
				e.printStackTrace();
				throw new myException(e.getMessage()+":SYSTEM-Error: Method setText or getText not existing in class "+this.getClass().getName());
			}
		} else {
			throw new myException("SYSTEM-Error: Method setText or getText not existing in class "+this.getClass().getName());
		}
		return (T)this;
	}
	// --------------------
	

	
	
}