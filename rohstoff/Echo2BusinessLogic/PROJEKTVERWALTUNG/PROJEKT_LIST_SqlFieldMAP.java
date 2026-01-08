package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class PROJEKT_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public PROJEKT_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_PROJEKT", "", false);
		

		
		this.add_ConnectedLookUpTable("JD_USER", "", "U_", "JT_PROJEKT.ID_USER=JD_USER.ID_USER (+)");
		this.add_SQLField(new SQLField("NVL(JD_USER.NAME,'-')||' ('||NVL(JD_USER.KUERZEL,'-')",
										"U_NAME_KUERZEL",
										new MyE2_String("Name-Kürzel"),
										bibE2.get_CurrSession()), false);
		
		this.add_ConnectedLookUpTable("JT_PROJEKTGEWICHT", "", "P_", "JT_PROJEKT.ID_PROJEKTGEWICHT=JT_PROJEKTGEWICHT.ID_PROJEKTGEWICHT (+)");
		this.add_SQLField(new SQLField("NVL(JT_PROJEKTGEWICHT.KURZBEZEICHNUNG,'-')||' ('||NVL(TO_CHAR(JT_PROJEKTGEWICHT.GEWICHT_1_100),'-')",
										"P_GEWICHT",
										new MyE2_String("Projektgewicht"),
										bibE2.get_CurrSession()), false);
		

		this.add_ConnectedLookUpTable("JT_PROJEKTSTATUSVARIANTE", "", "S_", "JT_PROJEKT.ID_PROJEKTSTATUSVARIANTE=JT_PROJEKTSTATUSVARIANTE.ID_PROJEKTSTATUSVARIANTE (+)");
		this.add_SQLField(new SQLField("NVL(JT_PROJEKTSTATUSVARIANTE.KURZBEZEICHNUNG,'-')",
										"S_STATUS",
										new MyE2_String("Projektstatus"),
										bibE2.get_CurrSession()), false);
		
		
		this.initFields();
	}
	
}
