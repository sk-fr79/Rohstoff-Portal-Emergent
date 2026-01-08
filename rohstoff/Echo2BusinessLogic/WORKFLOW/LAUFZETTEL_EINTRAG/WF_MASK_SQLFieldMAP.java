package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_PRIO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class WF_MASK_SQLFieldMAP extends Project_SQLFieldMAP {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3497623560713616635L;

	private String DefaultStatus;
	private String DefaultPrio;

	/**
	 * @deprecated Use {@link #WF_MASK_SQLFieldMAP(String,String,String)}
	 *             instead
	 */
	public WF_MASK_SQLFieldMAP(String ID_LAUFZETTEL, String ID_USER_BEARBEITER)
			throws myException {
		this(ID_LAUFZETTEL, ID_USER_BEARBEITER, null);
	}

	public WF_MASK_SQLFieldMAP(String ID_LAUFZETTEL, String ID_USER_BEARBEITER,
			String ID_EINTRAG_PARENT) throws myException {
		super("JT_LAUFZETTEL_EINTRAG", "", false);

		/*
		 * beispiel fuer felder
		 * 
		 * this.get_("GENERIERUNGSDATUM").set_cDefaultValueFormated(bibALL.
		 * get_cDateNOW());
		 * this.get_("ERLEDIGT").set_cDefaultValueFormated("N");
		 * this.get_("ID_USER"
		 * ).set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED
		 * (bibE2.get_CurrSession()));
		 */
		this.get_("ID_USER_BESITZER").set_cDefaultValueFormated(
				bibALL.get_ID_USER_FORMATTED());
		
		this.get_("ANGELEGT_AM").set_cDefaultValueFormated(
				bibALL.get_cDateNOW());

		RECLIST_LAUFZETTEL_PRIO p = new RECLIST_LAUFZETTEL_PRIO("SELECT * FROM  " + bibE2.cTO()+ ".JT_LAUFZETTEL_PRIO WHERE ISDEFAULT = 'Y'");
		if (p.values().size()>0) {
			this.DefaultPrio = p.get(0).get_ID_LAUFZETTEL_PRIO_cF();
		}
		RECLIST_LAUFZETTEL_STATUS s = new RECLIST_LAUFZETTEL_STATUS("SELECT * FROM  " + bibE2.cTO()+ ".JT_LAUFZETTEL_STATUS WHERE ISDEFAULT = 'Y'");
		if (s.values().size() > 0) {
			this.DefaultStatus = s.get(0).get_ID_LAUFZETTEL_STATUS_cF();
		}

		if (DefaultPrio != null) {
			this.get_("ID_LAUFZETTEL_PRIO").set_cDefaultValueFormated(DefaultPrio);
		}

		if (DefaultStatus != null) {
			this.get_("ID_LAUFZETTEL_STATUS").set_cDefaultValueFormated(DefaultStatus);
		}


		//
		this.add_SQLField(new SQLField("LETZTE_AENDERUNG",WF_CONST.HASH_SONDERFELD_LETZTE_AENDERUNG,new MyE2_String("Letzte Änderung"),bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField("GEAENDERT_VON",WF_CONST.HASH_SONDERFELD_GEANDERT_VON,new MyE2_String("Geändert von"),bibE2.get_CurrSession()), true);

		// zu erledigen von(Bearbeiter):
		// this.get_("ID_USER_BEARBEITER").set_cDefaultValueFormated(bibALL.
		// get_ID_USER_FORMATTED(bibE2.get_CurrSession()));
		// Status:

		//

		// wenn ein Parent übergeben wird, wird der automatisch gesetzt.
		if (ID_EINTRAG_PARENT != null) 
		{
			this.get_("ID_EINTRAG_PARENT").set_cDefaultValueFormated( ID_EINTRAG_PARENT );
		}

		if (!bibALL.isEmpty(ID_LAUFZETTEL)) 
		{
			this.add_SQLField(new SQLFieldForRestrictTableRange(
					"JT_LAUFZETTEL_EINTRAG", "ID_LAUFZETTEL", "ID_LAUFZETTEL",
					new MyE2_String("ID_Laufzettel"), ID_LAUFZETTEL, bibE2
							.get_CurrSession()), true);
		}

		
		
		this.initFields();
	}
	
	
	

}
