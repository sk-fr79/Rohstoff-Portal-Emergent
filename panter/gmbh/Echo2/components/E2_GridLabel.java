package panter.gmbh.Echo2.components;

import panter.gmbh.indep.S;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;

/**
 * componente, um einen label innerhalb eines grid anzuzeigen,
 * mit definierter breite, hintergrundfarbe usw
 * @author martin
 *
 */
public class E2_GridLabel extends MyE2_Grid {
	
	private Color  		color_background = null;
	private Color  		color_foreground = new Color(0, 0, 0);    //foreground standard ist schwarz
	private Alignment  	alignment = null;
	private String  	text = null;
	private Border  	border = null;
	private Integer 	width = null;
	private Insets   	insets = null;
	private String      toolTips = null;
	private Font  		font = null;

	private boolean     aktiv = true;
	
	public E2_GridLabel() {
		super();
	}
	
	public E2_GridLabel set_color_background(Color  p_color_back) {
		this.color_background = p_color_back;
		return this.build();
	}
	
	public E2_GridLabel set_passiv() {
		this.aktiv = false;
		return this.build();
	}
	public E2_GridLabel set_aktiv() {
		this.aktiv = true;
		return this.build();
	}

	
	public E2_GridLabel set_color_foreground(Color  p_color_foreground) {
		this.color_foreground = p_color_foreground;
		return this.build();
	}
	
	public E2_GridLabel set_font(Font  p_font) {
		this.font = p_font;
		return this.build();
	}

	
	public E2_GridLabel set_alignment(Alignment  p_alignment) {
		this.alignment = p_alignment;
		return this.build();
	}
	
	public E2_GridLabel set_text(String  p_text) {
		this.text = p_text;
		return this.build();
	}
	
	public E2_GridLabel set_border(Border  p_border) {
		this.border = p_border;
		return this.build();
	}

	public E2_GridLabel set_width(Integer  p_width) {
		this.width = p_width;
		return this.build();
	}
	
	
	public E2_GridLabel set_insets(Insets  p_insets) {
		this.insets = p_insets;
		return this.build();
	}
	
	
	public E2_GridLabel set_tooltips(String  p_tooltips) {
		this.toolTips = p_tooltips;
		return this.build();
	}

	
	public E2_GridLabel build() {
		if (this.aktiv) {
			this.removeAll();
			this.setSize(1);
			
			GridLayoutData gl = new GridLayoutData();
			gl.setAlignment(this.alignment);
			gl.setBackground(this.color_background);
			gl.setInsets(this.insets);
			
			
			if (this.border!=null) {
				this.setBorder(this.border);
			}
			if (this.width!=null) {
				this.setColumnWidth(0,new Extent(this.width));
				this.setWidth(new Extent(this.width));
			}
			
			MyE2_Label  lab = new MyE2_Label(S.NN(this.text));
			lab.setForeground(this.color_foreground);
			lab.setToolTipText(this.toolTips);
			if (this.font!=null) {
				lab.setFont(this.font);
			}
			
			lab.setLayoutData(gl);
			this.add_RAW_noLayoutData(lab);
		}
		return this;
	}
	
	
}
