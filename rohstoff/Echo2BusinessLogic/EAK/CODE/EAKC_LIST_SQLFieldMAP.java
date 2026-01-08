package rohstoff.Echo2BusinessLogic.EAK.CODE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class EAKC_LIST_SQLFieldMAP extends Project_SQLFieldMAP
{

	public EAKC_LIST_SQLFieldMAP() throws myException
	{
		super("JT_EAK_CODE","ID_EAK_GRUPPE", false);
		
		this.add_SQLField(new SQLFieldForRestrictTableRange(	"JT_EAK_CODE",
																"ID_EAK_GRUPPE",
																"ID_EAK_GRUPPE",
																new MyE2_String("Gruppe"),
																"NULL",
																bibE2.get_CurrSession()), false);
		this.get_("GEFAEHRLICH").set_cDefaultValueFormated("N");
		this.get_("TRANSPORT_OK").set_cDefaultValueFormated("N");
		this.get_("LAGERUNG_OK").set_cDefaultValueFormated("N");
		this.get_("VERARBEITUNG_OK").set_cDefaultValueFormated("N");
		
		// jetzt verbindungen zu den uebergeordneten tabellen (um den ganzen code einzublenden)
		this.add_ConnectedLookUpTable("JT_EAK_GRUPPE","KEY_GRUPPE","G_",null);
		this.add_ConnectedLookUpTable("JT_EAK_BRANCHE","KEY_BRANCHE","B_","JT_EAK_GRUPPE.ID_EAK_BRANCHE=JT_EAK_BRANCHE.ID_EAK_BRANCHE");
		
		this.initFields();
	}

	
}
