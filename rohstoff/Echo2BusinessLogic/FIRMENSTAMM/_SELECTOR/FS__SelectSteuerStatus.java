package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import java.util.HashMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class FS__SelectSteuerStatus extends MyE2_SelectField {

	private E2_ListSelectorStandard  oSelector = null;

	public FS__SelectSteuerStatus() throws myException {
		super();
		
		this.setWidth(new Extent(110));
		
		String[][] cWerte = new String[4][2];
		
		cWerte[0][0]= "*";							cWerte[0][1]= "ALLE";
		cWerte[1][0]= "Als PRIVAT eingestuft";		cWerte[1][1]= "PRIVAT";
		cWerte[2][0]= "Als FIRMA eingestuft";		cWerte[2][1]= "FIRMA";
		cWerte[3][0]= "Noch nicht eingestuft";		cWerte[3][1]= "UNDEF";
		
		HashMap<String, String>   hmStatements = new HashMap<String, String>();
		hmStatements.put("ALLE", 	"");
		hmStatements.put("PRIVAT", 	"(NVL("+_DB.FIRMENINFO+"."+_DB.FIRMENINFO$PRIVAT+",'N')='Y')");
		hmStatements.put("FIRMA", 	"(NVL("+_DB.FIRMENINFO+"."+_DB.FIRMENINFO$FIRMA+",'N')='Y')");
		hmStatements.put("UNDEF", 	"(NVL("+_DB.FIRMENINFO+"."+_DB.FIRMENINFO$FIRMA+",'N')='N') AND (NVL("+_DB.FIRMENINFO+"."+_DB.FIRMENINFO$PRIVAT+",'N')='N')");
  
		this.set_ListenInhalt(cWerte, false);
		
		this.oSelector = new E2_ListSelectorStandard(this, hmStatements, new MyE2_String(""), 10);
		
	}

	
	public E2_ListSelectorStandard get_oSelector() {
		return oSelector;
	}

	
	public MyE2_Grid  get_oComponentForSelection() {
		int[] iSpalte = {80,110};
		MyE2_Grid oGrid = new MyE2_Grid(iSpalte, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGrid.add(new MyE2_Label(new MyE2_String("Steuerstatus")), E2_INSETS.I(0,0,0,2));
		oGrid.add(this, E2_INSETS.I(0,0,0,0));
		
		return oGrid;
	}
	
}
