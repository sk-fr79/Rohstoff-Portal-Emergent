package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;


public class E2_SimpleStatus_an_aus extends E2_ListSelektorMultiselektionStatusFeld {

	private String f_text_cbAn = "an";
	private String f_text_cbAus = "aus";
	
	private String f_tooltip_text_cbAn = 	"Zeige Datensätze mit Status an";
	private String f_tooltip_text_cbAus = 	"Zeige Datensätze mit Status aus";

	private String f_textTitel = "Schalter";
	
	private String f_tableNamefieldName = null;
	
	private Integer spaltenbreiten = new Integer(100);
	
	private boolean f_an_is_checked = true;
	private boolean f_aus_is_checked = true;
	

	
	/**
	 * 
	 * @param c_tableNamefieldName (incl. tablebezeichner)
	 * @param p_text_cbAn
	 * @param p_text_cbAus
	 * @param p_tooltip_cbAn
	 * @param p_tooltip_cbAus
	 * @param p_textUeberschrift
	 * @param p_an_is_checked
	 * @param p_aus_is_checked
	 * @param spaltenBreite
	 */
	public E2_SimpleStatus_an_aus(	String c_tableNamefieldName, 
									String p_text_cbAn, 
									String p_text_cbAus, 
									String p_tooltip_cbAn, 
									String p_tooltip_cbAus,
									String p_textUeberschrift, 
									boolean p_an_is_checked,
									boolean p_aus_is_checked,
									Integer spaltenBreite) {
		
		super(3, false, new MyE2_String(""), new Extent(100));
		this.f_text_cbAn = p_text_cbAn;
		this.f_text_cbAus = p_text_cbAus;
		this.f_tooltip_text_cbAn = p_tooltip_cbAn;
		this.f_tooltip_text_cbAus = p_tooltip_cbAus;
		this.f_textTitel = p_textUeberschrift;
		this.f_tableNamefieldName = c_tableNamefieldName;
		this.f_an_is_checked = p_an_is_checked;
		this.f_aus_is_checked = p_aus_is_checked;
		this.spaltenbreiten = spaltenBreite;
		
		
		this.ADD_STATUS_TO_Selector(this.f_an_is_checked,	"NVL("+this.f_tableNamefieldName+",'N')='Y'",	new MyE2_String(this.f_text_cbAn), 		new MyE2_String(this.f_tooltip_text_cbAn));		
		this.ADD_STATUS_TO_Selector(this.f_aus_is_checked,	"NVL("+this.f_tableNamefieldName+",'N')='N'",	new MyE2_String(this.f_text_cbAus), 	new MyE2_String(this.f_tooltip_text_cbAus));		

	}



	@Override
	public Component get_oComponentForSelection() throws myException {
		//baut ein grid mit ueberschrift kursiv, klein und checkboxen an/aus nebeneinander
		E2_Grid  g = new E2_Grid()._a(new RB_lab(this.f_textTitel)._fs(-2)._i(), new RB_gld()._ins(E2_INSETS.I(1,1,1,1))._span(2));
		g._a(this.get_vCheckBoxTypen().get(0), new RB_gld()._ins(E2_INSETS.I(1,1,1,1)));
		g._a(this.get_vCheckBoxTypen().get(1), new RB_gld()._ins(E2_INSETS.I(1,1,1,1)));
		g._setSize(this.spaltenbreiten.intValue(), this.spaltenbreiten.intValue());
		
		return g;
	}

}
