package panter.gmbh.Echo2.components.activeReport_NG;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.HAD_comp_Label;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;

public class AR_MultiLineLabel extends MyE2_Grid  implements IF_AR_Component {
	
	private GridLayoutData gl = null;

	/**
	 * 
	 * @param p_gl
	 * @param fTitel
	 * @param titel
	 * @param font
	 * @param s           alle uebergebenen Strings werden nochmals nach \n getrennt, sodaß fliesstexte korrekt dargestellt werden
	 */
	public AR_MultiLineLabel(GridLayoutData p_gl, Font fTitel, String titel, Font font, String... a_s) {
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.gl=p_gl;
		
		if (S.isFull(titel)) {
			this.add(new HAD_comp_Label(new AR_LayoutData(1, E2_INSETS.I(0,0,0,2)),titel,fTitel));
		}
		
		if (a_s != null && a_s.length>0) {
			for (String s: a_s ){
				if (S.isFull(s)) {
					Vector<String> v = bibALL.TrenneZeile(s, "\n");
				
					int count = 0;
					for (String c: v) {
						this.add(new HAD_comp_Label(new AR_LayoutData(1, E2_INSETS.I(0,0,0,0)),c,font));
						this.setRowHeight(count, new Extent(6));  	// sorgt dafuer, dass auch zeilen mit leerstrings (leere vorschuebe beim erfassen)
						 											// als sichtbare abschnitte angezeigt werden
						count++;
					}
				}
			}
		}
		this.setLayoutData(gl);
	}

	

	public AR_MultiLineLabel(GridLayoutData p_gl, Font font, MyString... a_c) {
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.gl=p_gl;
		
		if (a_c!=null && a_c.length>0) {
			int count = 0;
			for (MyString c: a_c) {
				this.add(new HAD_comp_Label(new AR_LayoutData(1, E2_INSETS.I(0,0,0,0)),c.CTrans(),font));
				this.setRowHeight(count, new Extent(6));  	// sorgt dafuer, dass auch zeilen mit leerstrings (leere vorschuebe beim erfassen)
				 											// als sichtbare abschnitte angezeigt werden
				count++;
			}
		}
		this.setLayoutData(gl);
	}

	
	public AR_MultiLineLabel(GridLayoutData p_gl, Font font, String... a_c) {
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.gl=p_gl;
		
		if (a_c!=null && a_c.length>0) {
			int count = 0;
			for (String c: a_c) {
				this.add(new HAD_comp_Label(new AR_LayoutData(1, E2_INSETS.I(0,0,0,0)),c,font));
				this.setRowHeight(count, new Extent(6));  	// sorgt dafuer, dass auch zeilen mit leerstrings (leere vorschuebe beim erfassen)
				 											// als sichtbare abschnitte angezeigt werden
				count++;
			}
		}
		this.setLayoutData(gl);
	}

	

	
	public AR_MultiLineLabel(GridLayoutData p_gl, Font font, String s) {
		this(p_gl, null, null, font, s);
	}

	
	
	@Override
	public GridLayoutData get_layoutData() {
		return this.gl;
	}

	@Override
	public Component comp() {
		return this;
	}

}
