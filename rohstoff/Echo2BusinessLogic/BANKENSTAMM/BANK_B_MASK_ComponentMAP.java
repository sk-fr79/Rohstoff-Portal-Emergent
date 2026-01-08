package rohstoff.Echo2BusinessLogic.BANKENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BANK_B_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public BANK_B_MASK_ComponentMAP(BANK_MASK_ComponentMAP oMotherComponentMAP) throws myException
	{
		super(new BANK_B_MASK_SQLFieldMAP((BANK_MASK_SQLFieldMAP)oMotherComponentMAP.get_oSQLFieldMAP()));
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		String[] cFieldsStandard = {"BANKLEITZAHL","SWIFTCODE","IBAN_NR","BEMERKUNGEN"}; 
		String[] cFieldsInfolabs = {"BANKLEITZAHL","SWIFTCODE","IBAN_NR","BEMERKUNGEN"}; 

		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, cFieldsInfolabs, oFM, false, false, this, 400);
		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_BANKENSTAMM")), new MyE2_String("ID-Banken"));
	
		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNGEN")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNGEN")).set_iRows(8);
		
		
	}
	
}
