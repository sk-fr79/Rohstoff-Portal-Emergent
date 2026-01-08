package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;

public class EMS_DBToolBox extends MyDBToolBox {

	public EMS_DBToolBox() throws myException {
		super(bibALL.get_oConnectionNormal());
        this.setZusatzFelder(bibALL.get_DB_ZusatzFelder(false, true, true, null, bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN("")));
        this.set_ErsetzungTableView((String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT"));
        this.set_bErsetzungTableView(false);   //abfragen erfolgen auf den tables
	}

}
