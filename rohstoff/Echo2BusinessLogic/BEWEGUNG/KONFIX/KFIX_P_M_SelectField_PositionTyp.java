package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_SelectField_PositionTyp extends RB_SelectField{

	public KFIX_P_M_SelectField_PositionTyp(IF_Field field) throws myException {
		super(field);
		
		this.setWidth(new Extent(55));
		
		String[][] cBase = new String[4][2];
		cBase[0][0]= "-";  				cBase[0][1]= "";
		cBase[1][0]= "Artikel";  		cBase[1][1]= myCONST.VG_POSITION_TYP_ARTIKEL;  
		cBase[2][0]= "Alternative";  	cBase[2][1]= myCONST.VG_POSITION_TYP_ALTERNATIV;  
		cBase[3][0]= "Zusatztext";  	cBase[3][1]= myCONST.VG_POSITION_TYP_ZUSATZTEXT;  
		this.set_oDataToViewForDatabase(new dataToView(cBase,true,bibE2.get_CurrSession() ));
		this.setFont(new E2_FontItalic(-2));
	}
	
}
