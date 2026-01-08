package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.My_MWSTSaetze;

public class BS_ComboBox_MWST extends 		MyE2_DB_ComboBoxErsatz
{
	public BS_ComboBox_MWST(SQLFieldMAP osqlFieldMap) throws myException
	{
		super(osqlFieldMap.get_("STEUERSATZ"));
		this.set_WidthAndHeightOfDropDown(new Extent(100),new Extent(80),null, new Integer(60));
		this.get_oTextField().setFont(new E2_FontPlain(-2));
		
		My_MWSTSaetze oMWST = new My_MWSTSaetze(null,null);
		
		String[][] cWerte = oMWST.get_MWST_DropDownArray_AllMWST(true); 
		
		this.set_Varianten(cWerte,null,null, false);
	}

	
	public BS_ComboBox_MWST(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.set_WidthAndHeightOfDropDown(new Extent(100),new Extent(80),null, new Integer(60));
		this.get_oTextField().setFont(new E2_FontPlain(-2));
		
		My_MWSTSaetze oMWST = new My_MWSTSaetze(null,null);
		
		String[][] cWerte = oMWST.get_MWST_DropDownArray_AllMWST(true); 
		
		this.set_Varianten(cWerte,null,null, false);
	}

	
}
