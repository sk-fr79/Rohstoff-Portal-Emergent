package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class WF_HEAD_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	private String DefaultStatus;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5392634440351432267L;

	public WF_HEAD_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_LAUFZETTEL", "", false);
	
		this.add_SQLField(new SQLField("LETZTE_AENDERUNG",WF_HEAD_CONST.HASH_SONDERFELD_LETZTE_AENDERUNG,new MyE2_String("Letzte Änderung"),bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField("GEAENDERT_VON",WF_HEAD_CONST.HASH_SONDERFELD_GEANDERT_VON,new MyE2_String("Geändert von"),bibE2.get_CurrSession()), true);
		
		this.get_("ID_USER_BESITZER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED());
		this.get_("ANGELEGT_AM").set_cDefaultValueFormated(bibALL.get_cDateNOW());

		//MAP_LAUFZETTEL_STATUS s = new MAP_LAUFZETTEL_STATUS("ISDEFAULT = 'Y'");
		RECLIST_LAUFZETTEL_STATUS s = new RECLIST_LAUFZETTEL_STATUS("SELECT * FROM " + bibE2.cTO()+ ".JT_LAUFZETTEL_STATUS WHERE ISDEFAULT = 'Y'");
		if (s.size() > 0) {
			this.DefaultStatus = s.get(0).get_ID_LAUFZETTEL_STATUS_cF();
//			this.DefaultStatus = s.get_cF_ID_LAUFZETTEL_STATUS(0);
		}

		if (DefaultStatus != null) {
			this.get_("ID_LAUFZETTEL_STATUS").set_cDefaultValueFormated(DefaultStatus);
		}


		
		
		
		/*
		 * beispiel fuer felder
		 *		
		this.get_("GENERIERUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		this.get_("ERLEDIGT").set_cDefaultValueFormated("N");
		this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED(bibE2.get_CurrSession()));
		*/

		this.initFields();
	}

}
