package panter.gmbh.indep;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.button.AbstractButton;

public class bibFONT {

	/**
	 * return copy of original font
	 * @param fontOrig
	 * @param lineThrough
	 * @return
	 */
	public static Font getLineThroughFont(Font fontOrig, boolean lineThrough) {
		Font fontRueck = null;
		if (fontOrig != null) {
			if ((!fontOrig.isLineThrough()) && lineThrough) {
				fontRueck = new Font(fontOrig.getTypeface(), bibFONT.get_Style(fontOrig)+Font.LINE_THROUGH, fontOrig.getSize());
			} else if ((fontOrig.isLineThrough())&& !lineThrough) {
				fontRueck = new Font(fontOrig.getTypeface(), bibFONT.get_Style(fontOrig)-Font.LINE_THROUGH, fontOrig.getSize());
			} else if (fontOrig != null) {
				fontRueck = bibFONT.get_Copy(fontOrig);
			}
		}
		return fontRueck;
	}
	
	

	public static void change_fontToLineThrough(Component comp, boolean lineThrough) {
		if (comp != null) {
			//zuerst den direkt zudefinerten font
			Font fontOrig = comp.getFont();
			//dann den via style
			if (fontOrig==null && comp.getStyle()!=null) {
				fontOrig = (Font)comp.getStyle().getProperty(AbstractButton.PROPERTY_FONT);
			}
			if (fontOrig!=null) {
				comp.setFont(bibFONT.getLineThroughFont(fontOrig, lineThrough));
			}
		}
	}
	
	
	/**
	 * return copy of original font
	 * @param fontOrig
	 * @param italic
	 * @return
	 */
	public static Font getItalicFont(Font fontOrig, boolean italic) {
		Font fontRueck = null;
		if (fontOrig != null) {
			if ((!fontOrig.isItalic()) && italic) {
				fontRueck = new Font(fontOrig.getTypeface(), bibFONT.get_Style(fontOrig)+Font.ITALIC, fontOrig.getSize());
			} else if ((fontOrig.isLineThrough())&& !italic) {
				fontRueck = new Font(fontOrig.getTypeface(), bibFONT.get_Style(fontOrig)-Font.ITALIC, fontOrig.getSize());
			} else if (fontOrig != null) {
				fontRueck = bibFONT.get_Copy(fontOrig);
			}
		}
		return fontRueck;
	}
	
	

	public static void change_fontToItalic(Component comp, boolean italic) {
		if (comp != null) {
			//zuerst den direkt zudefinerten font
			Font fontOrig = comp.getFont();
			//dann den via style
			if (fontOrig==null && comp.getStyle()!=null) {
				fontOrig = (Font)comp.getStyle().getProperty(AbstractButton.PROPERTY_FONT);
			}
			if (fontOrig!=null) {
				comp.setFont(bibFONT.getItalicFont(fontOrig, italic));
			}
		}
	}
	
	
	
	/**
	 * return copy of original font
	 * @param fontOrig
	 * @param bold
	 * @return
	 */
	public static Font getBoldFont(Font fontOrig, boolean bold) {
		Font fontRueck = null;
		if (fontOrig != null) {
			if ((!fontOrig.isItalic()) && bold) {
				fontRueck = new Font(fontOrig.getTypeface(), bibFONT.get_Style(fontOrig)+Font.BOLD, fontOrig.getSize());
			} else if ((fontOrig.isLineThrough())&& !bold) {
				fontRueck = new Font(fontOrig.getTypeface(), bibFONT.get_Style(fontOrig)-Font.BOLD, fontOrig.getSize());
			} else if (fontOrig != null) {
				fontRueck = bibFONT.get_Copy(fontOrig);
			}
		}
		return fontRueck;
	}
	
	

	public static void change_fontToBold(Component comp, boolean bold) {
		if (comp != null) {
			//zuerst den direkt zudefinerten font
			Font fontOrig = comp.getFont();
			//dann den via style
			if (fontOrig==null && comp.getStyle()!=null) {
				fontOrig = (Font)comp.getStyle().getProperty(AbstractButton.PROPERTY_FONT);
			}
			if (fontOrig!=null) {
				comp.setFont(bibFONT.getBoldFont(fontOrig, bold));
			}
		}
	}
	
	
	
	
	
	public static Font get_Copy(Font fontOrig) {
		Font fontNew = null;
		if (fontOrig!=null) {
			fontNew=new Font(fontOrig.getTypeface(),bibFONT.get_Style(fontOrig),fontOrig.getSize());
		}
		
		return fontNew;
	}
	
	
	public static int get_Style(Font fontOrig) {
		int iStyle=0;
		if (fontOrig!=null) {
			if (fontOrig.isBold()) {
				iStyle+=Font.BOLD;		//BOLD=1
			}
			if (fontOrig.isItalic()) {  //ITALIC = 2
				iStyle+=Font.ITALIC;
			}
			if (fontOrig.isUnderline()) {  //UNDERLINE = 4
				iStyle+=Font.UNDERLINE;
			}
			if (fontOrig.isOverline()) {  //OVERLINE = 8
				iStyle+=Font.OVERLINE;
			}
			if (fontOrig.isLineThrough()) {  //LINE_THROUGH = 16
				iStyle+=Font.LINE_THROUGH;
			}
		}		
		return iStyle;
	}

	
	/**
	 * if (fontCompare != null and fontCompare is lineThrougt then fontNew is set to lineThrough)
	 * @param fontNew : font which is set to object
	 * @param fontCompare: old font from object
	 */
	public static Font equal_LineThrough_status(Font fontNew, Font fontCompare) {
		if (fontNew!=null && fontCompare!=null) {
			if (fontCompare.isLineThrough()) {
				fontNew = bibFONT.getLineThroughFont(fontNew, fontCompare.isLineThrough());
			}
		}
		return fontNew;
	}
	
	/**
	 * if (fontCompare != null and fontCompare is lineThrougt then fontNew is set to lineThrough)
	 * @param fontNew : font which is set to object
	 * @param component before setting new font
	 */
	public static Font equal_LineThrough_status(Font fontNew, Component component) {
		if (fontNew!=null && component!=null) {
			Font fontOrig = component.getFont();
			if (fontOrig==null && component.getStyle()!=null) {
				fontOrig = (Font)component.getStyle().getProperty(AbstractButton.PROPERTY_FONT);
			}
			
			if (fontOrig!=null && fontOrig.isLineThrough()) {
				fontNew = bibFONT.getLineThroughFont(fontNew, fontOrig.isLineThrough());
			}
		}
		return fontNew;
	}

	
}
