/**
 * panter.gmbh.Echo2.FontsAndColors
 * @author martin
 * @date 06.08.2020
 * 
 */
package panter.gmbh.Echo2.FontsAndColors;

import nextapp.echo2.app.Font;
import nextapp.echo2.app.Font.Typeface;

/**
 * @author martin
 * @date 06.08.2020
 *
 */
public interface IF_PdFonts {

	public default E2_Font getCopy() {
		
		Font me = (Font)this;
		Typeface tf = me.getTypeface();

		int style = 0;
		if (me.isBold()) {style+=Font.BOLD;}
		if (me.isItalic()) {style+=Font.ITALIC;}
		if (me.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (me.isOverline()) {style+=Font.OVERLINE;}
		if (me.isUnderline()) {style+=Font.UNDERLINE;}
		
		return new E2_Font(tf, style, me.getSize());
	}
	
	public default E2_Font c() {
		return this.getCopy();
	}
	
}
