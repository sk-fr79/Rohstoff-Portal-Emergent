package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class PROJEKT_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public PROJEKT_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_PROJEKT", "", false);
	
		this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED());
		this.get_("PROJEKTBEGIN").set_cDefaultValueFormated(bibALL.get_cDateNOW());

		E2_DropDownSettings oDDsGewicht = new E2_DropDownSettings(
									"JT_PROJEKTGEWICHT",
									"KURZBEZEICHNUNG||' ('||TO_CHAR(GEWICHT_1_100)||')'",
									"ID_PROJEKTGEWICHT",
									"",
									"IST_STANDARD",
									true,
									"GEWICHT_1_100");
		
		E2_DropDownSettings oDDsStatus = new E2_DropDownSettings(
											"JT_PROJEKTSTATUSVARIANTE",
											"KURZBEZEICHNUNG",
											"ID_PROJEKTSTATUSVARIANTE",
											"",
											"IST_STANDARD",
											true,
											"KURZBEZEICHNUNG");

		
		this.get_("ID_PROJEKTGEWICHT").set_cDefaultValueFormated(bibALL.null2leer(oDDsGewicht.getDefault()));
		this.get_("ID_PROJEKTSTATUSVARIANTE").set_cDefaultValueFormated(bibALL.null2leer(oDDsStatus.getDefault()));
		this.get_("AKTIV").set_cDefaultValueFormated("Y");
		
		this.get_("ID_USER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PROJEKTNAME").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PROJEKTBEGIN").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PROJEKTDEADLINE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.initFields();
	}

}
