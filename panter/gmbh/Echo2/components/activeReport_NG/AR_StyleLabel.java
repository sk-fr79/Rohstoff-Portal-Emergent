package panter.gmbh.Echo2.components.activeReport_NG;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_MANDANT_ext;

public class AR_StyleLabel extends MutableStyle {

	public AR_StyleLabel(int font_size) throws myException {
		this(false,false,false,font_size);
	}

	
	public AR_StyleLabel(int font_size, boolean bold) throws myException {
		this(bold,false,false,font_size);
	}
	
	public AR_StyleLabel(boolean bold, boolean italic, boolean grey, int font_size) throws myException {
		super();
		if (bold && italic) {
			this.setProperty(Label.PROPERTY_FONT, new E2_FontBoldItalic(font_size));
		} else if (bold) {
			this.setProperty(Label.PROPERTY_FONT, new E2_FontBold(font_size));
		} else if (italic) {
			this.setProperty(Label.PROPERTY_FONT, new E2_FontItalic(font_size));
		} else {
			this.setProperty(Label.PROPERTY_FONT, new E2_FontPlain(font_size));
		}
		
		if (grey) {
			this.setProperty(Label.PROPERTY_FOREGROUND, new RECORD_MANDANT_ext(bibALL.get_RECORD_MANDANT()).get_COLOR_BACKTEXT());
		} else {
			this.setProperty(Label.PROPERTY_FOREGROUND, Color.BLACK);
		}
		
	}

}
