package rohstoff.Echo2BusinessLogic._TAX;

import java.util.HashMap;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class TAX__hmVerantwortung extends HashMap<String, String>
{

	public TAX__hmVerantwortung() throws myException
	{
		super();
		this.put(TAX_CONST.TP_VERANTWORTUNG_MANDANT, bibALL.get_RECORD_MANDANT().get___KETTE(bibVECTOR.get_Vector("NAME1", "NAME2"), "<Mandant>", "", "", " "));
		this.put(TAX_CONST.TP_VERANTWORTUNG_QUELLE, "Lieferant");
		this.put(TAX_CONST.TP_VERANTWORTUNG_ZIEL, "Abnehmer");
	}
	
}
