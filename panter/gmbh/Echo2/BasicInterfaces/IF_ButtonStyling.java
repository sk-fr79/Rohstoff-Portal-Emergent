package panter.gmbh.Echo2.BasicInterfaces;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.button.AbstractButton;
import panter.gmbh.BasicInterfaces.IF__Reflections;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;

@SuppressWarnings("unchecked")
public interface IF_ButtonStyling<T>  extends IF__Reflections{


	
	
	/**
	 * setzt das standard-aussehen fuer Standard-Speicherbutton
	 * @return
	 */
	public default T _setShapeSaveAndClose() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._image("save.png",true)._s_Image();
		}

		return (T) this;
	}
	
	/**
	 * setzt das standard-aussehen fuer Standard-Speicher - und reopen-Button
	 * @return
	 */
	public default T _setShapeSaveAndReOpen() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._image("save_edit.png",true)._s_Image();
		}

		return (T) this;
	}

	
	/**
	 * setzt das standard-aussehen fuer Standard-mask-close
	 * @return
	 */
	public default T _setShapeCancelAndClose() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._image("cancel.png",true)._s_Image();
		}
		return (T) this;
	}

	
	
	
	/**
	 * setzt das standard-aussehen fuer Standard-mask-next-button
	 * @return
	 */
	public default T _setShapeMoveForeward() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._image("mask_right.png",true)._s_Image();
		}
		return (T) this;
	}

	
	/**
	 * setzt das standard-aussehen fuer Standard-mask-back-button
	 * @return
	 */
	public default T _setShapeMoveBackward() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._image("mask_left.png",true)._s_Image();
		}
		return (T) this;
	}

	/**
	 * setzt das standard-aussehen fuer Standard-mask-back-button
	 * @return
	 */
	public default T _setShapeDeleteButton() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._image("delete_mini.png",true)._s_Image();
		}
		return (T) this;
	}

	
	/**
	 * setzt das standard-aussehen fuer Standard-mask-back-button
	 * @return
	 */
	public default T _setShapeMuellLeerButton() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._image("delete_leer_mini.png",true)._s_Image();
		}
		return (T) this;
	}

	/**
	 * setzt das standard-aussehen fuer Standard-mask-back-button
	 * @return
	 */
	public default T _setShapeMuellVollButton() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._image("delete_voll_mini.png",true)._s_Image();
		}
		return (T) this;
	}
	
	
	
	
	/**
	 * setzt das standard-aussehen fuer Standard-mask-back-button
	 * @return
	 */
	public default T _setShapeStornoButton() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._image("storno_unstorniert.png",true)._s_Image();
		}
		return (T) this;
	}

	
	/**
	 * setzt das standard-aussehen fuer Standard-mask-back-button
	 * @return
	 */
	public default T _setShapeUnStornoButton() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._image("storno_storniert.png",true)._s_Image();
		}
		return (T) this;
	}


	/**
	 * setzt das standard-aussehen fuer Standard-mask-back-button
	 * @return
	 */
	public default T _setShapeBigHighLightText() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._s_BorderTextCentered()._fsa(2)._insets(2)._bc(null);
		}
		return (T) this;
	}


	
	/**
	 * setzt das standard-aussehen fuer Standard-Textbutton
	 * @return
	 */
	public default T _setShapeStandardTextButton() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._s_BorderTextCentered()._insets(2)._bc(null);
		}
		return (T) this;
	}


	/**
	 * 
	 * @author martin
	 * @date 17.05.2019
	 *
	 * @param backGound (null allowed)
	 * @param borderColor (null allowed)
	 * @return
	 */
	public default T _setShapeStandardTextButtonLeftMidFontNormal(Color backGound, Color borderColor) {
		if (this instanceof E2_Button) {
			((E2_Button)this)._s_BorderTextLeft()._insets(2)._bc(backGound)._f(new E2_FontPlain());
			if (borderColor==null) {
				((E2_Button)this).setBorder(null);
			} else {
				((E2_Button)this).setBorder(new Border(1, borderColor, Border.STYLE_SOLID));
			}
		}
		return (T) this;
	}

	
	

	/**
	 * 
	 * @author martin
	 * @date 20.02.2020
	 *
	 * @param backGound (null allowed)
	 * @param borderColor (null allowed)
	 * @return
	 */
	public default T _setShapeStandardTextButtonCenterMidFontNormal(Color backGound, Color borderColor) {
		if (this instanceof E2_Button) {
			
			E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
			
			Color	oColorBackground = new E2_ColorBase(-10);
			Color	oColorBackgroundDisabled = new E2_ColorBase(-10);
			Color	oColorForeground = Color.BLACK;
			
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColorBackground, oColorBackgroundDisabled); 
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, oColorForeground,Color.DARKGRAY);
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(false));
			
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,Alignment.ALIGN_CENTER);
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_TEXT_ALIGNMENT,Alignment.ALIGN_CENTER);

			((E2_Button)this).setStyle(ostyleTextButton);
			if (borderColor==null) {
				((E2_Button)this).setBorder(null);
			} else {
				((E2_Button)this).setBorder(new Border(1, borderColor, Border.STYLE_SOLID));
			}
		}
		return (T) this;
	}

	public default T _setShapeStyleLikeLabel() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._style(E2_Button.styleLikeLabel());
		}
		return (T) this;
	}
	
	
	
	
	public default T _setShapeStyleStandard() {
		if (this instanceof E2_Button) {
			((E2_Button)this)._style(E2_Button.baseStyle());
		}
		return (T) this;
	}
	
	public default T _setShapeStyleStandard(Font f) {
		if (this instanceof E2_Button) {
			((E2_Button)this)._style(E2_Button.baseStyle(f));
		}
		return (T) this;
	}
	
	
	public default T _setShapeStyleStandard(Font f, Color backcolor) {
		if (this instanceof E2_Button) {
			E2_MutableStyle style = E2_Button.baseStyle(f);
			style.setProperty( AbstractButton.PROPERTY_BACKGROUND, backcolor,backcolor); 
			((E2_Button)this)._style(style);
		}
		return (T) this;
	}
	
	
	
	
	
	public default T _setShapeStyleStandard(Font f, Color backcolor, Color borderColor) {
		if (this instanceof E2_Button) {
			E2_MutableStyle style = E2_Button.baseStyle(f);
			style.setProperty( AbstractButton.PROPERTY_BACKGROUND, backcolor,backcolor); 
			style.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, borderColor, Border.STYLE_SOLID));
			((E2_Button)this)._style(style);
		}
		return (T) this;
	}
	
	
}