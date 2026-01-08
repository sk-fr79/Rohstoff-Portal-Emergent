/**
 * panter.gmbh.Echo2.ListAndMask
 * @author martin
 * @date 08.02.2019
 * 
 */
package panter.gmbh.Echo2.ListAndMask;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Font.Typeface;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 08.02.2019
 *
 */
public class E2_LineDeleteMarker {

	public static E2_ComponentMAP markAsDeletedLineThrough(E2_ComponentMAP map) throws myException {
		for (MyE2IF__Component c:  map.get_hmRealComponents().values()) {
			if (c instanceof Button) {
				Font f = ((Button)c).getFont();
				((Button)c).setFont(E2_LineDeleteMarker.makeLineThrough(f));
			} else if (c instanceof Label) {
				Font f = ((Label)c).getFont();
				((Label)c).setFont(E2_LineDeleteMarker.makeLineThrough(f));
			} else if (c instanceof TextField) {
				Font f = ((TextField)c).getFont();
				((TextField)c).setFont(E2_LineDeleteMarker.makeLineThrough(f));
			} else if (c instanceof TextArea) {
				Font f = ((TextArea)c).getFont();
				((TextArea)c).setFont(E2_LineDeleteMarker.makeLineThrough(f));
			} 
			
			
			
		}
		
		
		
		return map;
	}
	
	
	
	
	
	public static Font makeLineThrough(Font f) {
		int fontAttrib = 0;
		if (f.isBold()) {
			fontAttrib += Font.BOLD;
		}
		if (f.isItalic()) {
			fontAttrib += Font.ITALIC;
		}
		if (f.isPlain()) {
			fontAttrib += Font.PLAIN;
		}
		fontAttrib += Font.LINE_THROUGH;
		
		Typeface tf = f.getTypeface();
		
		return new Font(tf, fontAttrib, f.getSize());
	}
	
}
