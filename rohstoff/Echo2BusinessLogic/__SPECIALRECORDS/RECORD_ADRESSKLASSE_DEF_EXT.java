package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_ColorGrid;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSKLASSE_DEF;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_ADRESSKLASSE_DEF_EXT extends RECORD_ADRESSKLASSE_DEF {

	public RECORD_ADRESSKLASSE_DEF_EXT(RECORD_ADRESSKLASSE_DEF recordOrig) {
		super(recordOrig);
	}
	
	
	
	public Color  generate_Color() throws myException {
		Color col = null;
		Integer i_r = this.checkColor(this.get_COLOR_RED_cUF_NN(""));
		Integer i_g = this.checkColor(this.get_COLOR_GREEN_cUF_NN(""));
		Integer i_b = this.checkColor(this.get_COLOR_BLUE_cUF_NN(""));
		
		if (i_r!=null && i_g!=null && i_b!=null) {
			col=new Color(i_r, i_g, i_b);
		}
		return col;
	}

	
	
	private Integer checkColor(String wert) {
		Integer i_rueck = null;
		MyInteger i_test = new MyInteger(wert);
		if (i_test.get_bOK()) {
			i_rueck = i_test.get_oInteger();
			
			if (i_rueck.intValue()<0 || i_rueck.intValue()>255) {
				i_rueck = null;
			}
		}
		
		return i_rueck;
	}


	public MyE2_Grid grid_with_color(int iPixWidth, int iPixHeight, Color colorWhenNull) throws myException {
		Color myCol = this.generate_Color();
		if (myCol==null) {
			myCol=colorWhenNull;
		}
		return new E2_ColorGrid(iPixWidth, iPixHeight, myCol,new MyE2_String(this.get_BEZEICHNUNG_cUF_NN("<->")+" ("+this.get_KURZBEZEICHNUNG_cUF_NN("")+")",false));
	}
	

	
}
