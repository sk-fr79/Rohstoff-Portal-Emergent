package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_LabelFilledWithResult;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;

public class RB_label_einheitpreis extends RB_LabelFilledWithResult {

	public RB_label_einheitpreis() throws myException {
		super("...");
		this.setFont(new E2_FontPlain(-2));
		this.setLineWrap(true);
	}

	@Override
	public String transferDbValueToVisibleText(String c_id_artikel_bez) throws myException {
		MyLong  l_artbez = new MyLong(c_id_artikel_bez);
		if (l_artbez.get_bOK()) {
			RECORD_ARTIKEL r_artbez = new RECORD_ARTIKEL_BEZ(l_artbez.get_lValue()).get_UP_RECORD_ARTIKEL_id_artikel(); 
			return r_artbez.get_UP_RECORD_EINHEIT_id_einheit_preis().get_EINHEITKURZ_cUF_NN("??");
		}
		return "";
	}

}
