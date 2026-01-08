package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN._TP_KOSTEN_DIVERSE;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EINHEIT;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class TP_Helper_StelleFest_HaefigsteAbrechnungseinheit {
	
	private RECORD_EINHEIT  recEinheitHaeufigst = null;

	public TP_Helper_StelleFest_HaefigsteAbrechnungseinheit() throws myException {
		super();
		
		String cQuery = 
			" SELECT * FROM ( "+
					" SELECT COUNT(*) AS ANZAHL , JT_ARTIKEL.ID_EINHEIT_PREIS AS ID FROM "+bibE2.cTO()+".JT_ARTIKEL " +
							" GROUP BY ID_EINHEIT_PREIS "+
					" ) ORDER BY ANZAHL DESC ";
		
		String[][] arrEinheit = bibDB.EinzelAbfrageInArray(cQuery, "");
		
		if (arrEinheit.length>0) {
			this.recEinheitHaeufigst = new RECORD_EINHEIT(arrEinheit[0][1]);
		} else {
			throw new myException(this,"Error asking for most used EINHEIT ...");
		}
	}

	public RECORD_EINHEIT get_recEinheitHaeufigst() {
		return recEinheitHaeufigst;
	}  
	
	
}
