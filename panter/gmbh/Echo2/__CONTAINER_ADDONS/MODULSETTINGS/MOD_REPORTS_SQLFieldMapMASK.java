package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.REPORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MOD_REPORTS_SQLFieldMapMASK extends SQLFieldMAP
{

	public MOD_REPORTS_SQLFieldMapMASK(String cMODULE_KENNER_LIST_BELONGS_TO) throws myException
	{
		super("JT_REPORT", bibE2.get_CurrSession());
		
		if (bibALL.isEmpty(cMODULE_KENNER_LIST_BELONGS_TO))
			throw new myException("MV_SQLFieldMapLIST:Constructor:Parameter MOD_REPORTS_SQLFieldMapMASK is a MUST");
		
		this.addCompleteTable_FIELDLIST("JT_REPORT",":ID_REPORT:MODULE_KENNER:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_REPORT","ID_REPORT","ID_REPORT",new MyE2_String("ID-Adresse"),
				bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_REPORT.NEXTVAL FROM DUAL",true), false);

		String cHelp = bibALL.MakeSql(cMODULE_KENNER_LIST_BELONGS_TO);
		
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_REPORT","MODULE_KENNER","MODULE_KENNER",new MyE2_String("Modulkenner"),cHelp,bibE2.get_CurrSession()), false);
		
		this.get_("ALLOW_PDF").set_cDefaultValueFormated("Y");
		this.get_("PASS_NO_ID").set_cDefaultValueFormated("Y");
		
		this.get_(_DB.REPORT$PREFER_PDF).set_cDefaultValueFormated("Y");
		this.get_(REPORT.aktiv).set_cDefaultValueFormated("Y");
		
		this.initFields();
	}

}
