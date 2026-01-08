/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author martin
 * @date 18.08.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.math.BigDecimal;
import java.util.Date;

import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.TAX_AENDERUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.PdDateTools;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 18.08.2020
 *
 */
public class Rec21_tax extends Rec21 {
	public Rec21_tax() throws myException {
		super(_TAB.tax);
	}


	public Rec21_tax(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab() != _TAB.tax) {
			throw new myException(this,"Only Record from type TAX are allowed !");
		}
	}

	
	public BigDecimal getSteuerSatzKorrigiert(Date leistungsdatum) throws myException {
		
		BigDecimal steuer = this.getBigDecimalDbValue(TAX.steuersatz);
		
		if (leistungsdatum != null && this.getDateDbValue(TAX.wechseldatum)!=null && this.getBigDecimalDbValue(TAX.steuersatz_neu)!=null && PdDateTools.isDate2GreaterOrEqualDate1DayBase(this.getDateDbValue(TAX.wechseldatum), leistungsdatum)) {
			steuer = this.getBigDecimalDbValue(TAX.steuersatz_neu);
		}
		
		return steuer;
	}

	
	
	/**
	 * 
	 * @author martin
	 * @date 27.11.2020
	 *
	 * @return s liste aller steuersaetze, die in der ausnahmeliste vorkommen
	 */
	public VEK<String> getFormatedTaxesAusnahmen() {
		VEK<String> ret = new VEK<String>();
		
		try {
			RecList21 ausnahmen = this.get_down_reclist21(TAX_AENDERUNGEN.id_tax);
			if (ausnahmen.size()>0) {
				for (Rec21 ta: ausnahmen) {
					ret._a(ta.get_fs_dbVal(TAX_AENDERUNGEN.steuersatz, ""));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public Rec21_tax _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}
	
}
