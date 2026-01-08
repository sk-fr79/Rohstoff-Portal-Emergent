package rohstoff.Echo2BusinessLogic.INVENTUR_FREMDLAGER;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELEKTOR_LEFT_RIGHT_Artikel;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.exceptions.myException;

public class INF_Selektor_LeftRight_Sorten extends SELEKTOR_LEFT_RIGHT_Artikel {

	public static String TRENNZEICHEN_SAVE_SORTENAUSSCHLUSS = "#";
	
	
	public INF_Selektor_LeftRight_Sorten() throws myException {
		super(new Extent(200), new Extent(350), null);
	}
	
	public String get_SQL_WHERE_BLOCK_SORTENAUSSCHLUSS() {
		String cSQL = "(-1,-2)";
		
		Vector<String> vValues = this.get_oSelZiel().get_DataToView().get_vDataValues();
		if (vValues.size()>0) {
			cSQL = "(";
			for (String cID_ARTIKEL:vValues) {
				cSQL += cID_ARTIKEL;
				cSQL +=",";
			}
			cSQL += "-1)";
		}
		
		//DEBUG.System_println(cSQL, null);
		
		return cSQL;
	}
	
	public String get_SAVE_STRING_BLOCK_SORTENAUSSCHLUSS() {
		String cReturnVal = "";
		
		Vector<String> vValues = this.get_oSelZiel().get_DataToView().get_vDataValues();
		if (vValues.size()>0) {
			cReturnVal = INF_Selektor_LeftRight_Sorten.TRENNZEICHEN_SAVE_SORTENAUSSCHLUSS;
			for (String cID_ARTIKEL:vValues) {
				cReturnVal += cID_ARTIKEL;
				cReturnVal +=INF_Selektor_LeftRight_Sorten.TRENNZEICHEN_SAVE_SORTENAUSSCHLUSS;
			}
		}
		return cReturnVal;
	}
	
	
	
	public void restore_SAVED_SORTENAUSSCHLUSS(String cID_Sorten_AusschlussListe) {
		Vector<String> vIDs_Ausschluss = bibALL.TrenneZeile(cID_Sorten_AusschlussListe, INF_Selektor_LeftRight_Sorten.TRENNZEICHEN_SAVE_SORTENAUSSCHLUSS);
		
		dataToView oDW_Quelle = this.get_DataToView_VORRAT();
		dataToView oDW_Auswahl = this.get_DataToView_AUSWAHL();
		
		for (int i=0;i<vIDs_Ausschluss.size();i++) {
			dataToView.zuOrdnung oZO = oDW_Quelle.remove_DataToView_Zuordnung(vIDs_Ausschluss.get(i));
			if (oZO != null) {
				oDW_Auswahl.add(0,oZO);
			}
		}
		
		this.RefreshSelektor();
//		
//		
//		DEBUG.System_println(cID_Sorten_AusschlussListe, null);
//		
		
	}

	
}
