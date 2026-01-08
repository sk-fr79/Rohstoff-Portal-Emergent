package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MESSAGE_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public MESSAGE_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_NACHRICHT", "", false);
		
		this.get_("BESTAETIGT").set_cDefaultValueFormated("");
		this.get_("ID_NACHRICHT").set_cDefaultValueFormated("");
		this.get_("ID_NACHRICHT_PARENT").set_cDefaultValueFormated("");
		this.get_("ID_USER").set_cDefaultValueFormated("");
		this.get_("ID_USER_SENDER").set_cDefaultValueFormated("");
		this.get_("NACHRICHT").set_cDefaultValueFormated("");
		this.get_("TITEL").set_cDefaultValueFormated("");

		this.add_JOIN_Table("JD_USER", 
				"JD_USER1", 
				SQLFieldMAP.LEFT_OUTER, 
				"", true, "JT_NACHRICHT.ID_USER=JD_USER1.ID_USER", "", 
			bibALL.get_HashMap("USER_ADRESSE", "NVL(JD_USER1.VORNAME,'') || ' ' ||NVL(JD_USER1.NAME1,'')"));

		this.add_JOIN_Table("JD_USER", 
				"JD_USER2", 
				SQLFieldMAP.LEFT_OUTER, 
				"", true, "JT_NACHRICHT.ID_USER_SENDER =JD_USER2.ID_USER", "", 
			bibALL.get_HashMap("USER_SENDER", "NVL(JD_USER2.VORNAME,'') || ' ' ||NVL(JD_USER2.NAME1,'')"));

		this.add_JOIN_Table("JT_NACHRICHT_KATEGORIE", 
				"JT_NACHRICHT_KATEGORIE", 
				SQLFieldMAP.LEFT_OUTER, 
				":KATEGORIE:", 
				true, 
				"JT_NACHRICHT.ID_NACHRICHT_KATEGORIE=JT_NACHRICHT_KATEGORIE.ID_NACHRICHT_KATEGORIE", 
				"", 
				null);

		/*	
		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_NACHRICHT_WICHTIGKEIT","BESCHREIBUNG","ID_MESSAGE_WICHTIGKEIT","ISTSTANDARD",true);
		this.get_("ID_MESSAGE_WICHTIGKEIT").set_cDefaultValueFormated(oDDWichtigkeit.getDefault());
		*/
		String cID_USER = bibALL.get_ID_USER();
		
		String sBedingungAktivAb = " AND ( NVL(jt_nachricht.AKTIV_AB, to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') )  <= to_date( to_char(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD') ) "  ; 
		String sBedingungEigene = " ( ( jt_nachricht.ID_USER =  " +  cID_USER + sBedingungAktivAb + ") OR jt_nachricht.ID_USER_SENDER =  " +  cID_USER + " ) " ; 

		
		if (!bibALL.get_bIST_SUPERVISOR())
		{
			this.add_BEDINGUNG_STATIC(sBedingungEigene);
		}
		
		
		this.add_ORDER_SEGMENT("JT_NACHRICHT.AKTIV_AB DESC, JT_NACHRICHT.ID_NACHRICHT DESC" );

		
		this.initFields();
	}
	
}
