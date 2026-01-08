package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class FSK_DD_AdressenStart extends MyE2_DB_SelectField {

	public FSK_DD_AdressenStart(SQLField osqlField) throws myException {
		super(osqlField);
		this.setFont(new E2_FontPlain(-2));
		this.setWidth(new Extent(210));
	}

	
	/**
	 * refresh muss jedesmal beim aufruf der adress-maske ausgefuehrt werden
	 * @throws myException
	 */
	public void refreshDD(String cID_ADRESSE_UF) throws myException {
			
		String cSQL = "SELECT " +
				" CASE WHEN ADRESSTYP=1 THEN CSCONVERT('HA -- ','NCHAR_CS') ELSE CSCONVERT('LA -- ','NCHAR_CS') END|| ' '||NAME1||' '||NAME2||' ('||ID_ADRESSE||')', " +
				" ID_ADRESSE " +
				" FROM "+bibE2.cTO()+".JT_ADRESSE"+
				  	   " WHERE "+_DB.ADRESSE$SONDERLAGER+" IS NULL "+	
					   " AND ID_ADRESSE IN " +
					   " (SELECT JT_LIEFERADRESSE.ID_ADRESSE_LIEFER FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BASIS=#WERT#) OR ID_ADRESSE=#WERT#";
		
		String cSQL_ADRESSE = bibALL.ReplaceTeilString(cSQL, "#WERT#", cID_ADRESSE_UF);
		
		E2_DropDownSettings oDD = new E2_DropDownSettings(cSQL_ADRESSE+ " ORDER By 1",  true);
		dataToView  oDD_Real = new dataToView(oDD.getDD(), false, bibE2.get_CurrSession());
		this.set_oDataToView(oDD_Real);
		
	}
	
}
