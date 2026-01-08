package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.BSA__CONST;

/*
 * 
 */
public class BS_SelectField_POSITIONSTYP extends MyE2_DB_SelectField 
{

	private String cTABLE_NAME = null;
	
	/**
	 * @param osqlField
	 * @param osqlFieldGroup
	 * @param bAlternative
	 * @param actionAgent (wenn null, dann wird der standard-agent genommen)
	 * @throws myException
	 */
	public BS_SelectField_POSITIONSTYP(SQLField osqlField, SQLFieldMAP osqlFieldGroup, boolean bAlternative) throws myException
	{
		super(osqlField);
		
		this.cTABLE_NAME = osqlFieldGroup.get_cMAIN_TABLE();
		
		if (bAlternative)
		{
			String[][] cBase = new String[4][2];
			cBase[0][0]= "-";  				cBase[0][1]= "";
			cBase[1][0]= "Artikel";  		cBase[1][1]= myCONST.VG_POSITION_TYP_ARTIKEL;  
			cBase[2][0]= "Alternative";  	cBase[2][1]= myCONST.VG_POSITION_TYP_ALTERNATIV;  
			cBase[3][0]= "Zusatztext";  	cBase[3][1]= myCONST.VG_POSITION_TYP_ZUSATZTEXT;  
			this.set_oDataToViewForDatabase(new dataToView(cBase,true,bibE2.get_CurrSession() ));
		}
		else
		{
			String[][] cBase = new String[3][2];
			cBase[0][0]= "-";  				cBase[0][1]= "";
			cBase[1][0]= "Artikel";  		cBase[1][1]= myCONST.VG_POSITION_TYP_ARTIKEL;  
			cBase[2][0]= "Zusatztext";  	cBase[2][1]= myCONST.VG_POSITION_TYP_ZUSATZTEXT;  
			this.set_oDataToViewForDatabase(new dataToView(cBase,true,bibE2.get_CurrSession() ));
		}
		
		this.setFont(new E2_FontItalic(-2));
		this.add_oActionAgent(new ownActionAgentSettingPositionsTyp());
		
		//auf jeden fall nur bei neueingabe erlaubt
		this.EXT().set_MaskEnabled_Combination(true,false,false,false,false);
		
		
	}
	

	

	/**
	 * @param osqlField
	 * @param osqlFieldGroup
	 * @param bNormal
	 * @param bAlternative
	 * @param bZusatzText
	 * @throws myException
	 */
	public BS_SelectField_POSITIONSTYP(	SQLField 		osqlField, 
										SQLFieldMAP 	osqlFieldGroup,
										boolean 		bNormal, 
										boolean 		bAlternative, 
										boolean 		bZusatzText) throws myException
	{
		super(osqlField);
		
		this.cTABLE_NAME = osqlFieldGroup.get_cMAIN_TABLE();
		
		if (bNormal && bAlternative && bZusatzText )
		{
			String[][] cBase = new String[4][2];
			cBase[0][0]= "-";  				cBase[0][1]= "";
			cBase[1][0]= "Artikel";  		cBase[1][1]= myCONST.VG_POSITION_TYP_ARTIKEL;  
			cBase[2][0]= "Alternative";  	cBase[2][1]= myCONST.VG_POSITION_TYP_ALTERNATIV;  
			cBase[3][0]= "Zusatztext";  	cBase[3][1]= myCONST.VG_POSITION_TYP_ZUSATZTEXT;  
			this.set_oDataToViewForDatabase(new dataToView(cBase,true,bibE2.get_CurrSession() ));
		}
		else if (bNormal && bAlternative )
		{
			String[][] cBase = new String[3][2];
			cBase[0][0]= "-";  				cBase[0][1]= "";
			cBase[1][0]= "Artikel";  		cBase[1][1]= myCONST.VG_POSITION_TYP_ARTIKEL;  
			cBase[2][0]= "Alternative";  	cBase[2][1]= myCONST.VG_POSITION_TYP_ALTERNATIV;  
			this.set_oDataToViewForDatabase(new dataToView(cBase,true,bibE2.get_CurrSession() ));
		}
		else if (bNormal && bZusatzText )
		{
			String[][] cBase = new String[3][2];
			cBase[0][0]= "-";  				cBase[0][1]= "";
			cBase[1][0]= "Artikel";  		cBase[1][1]= myCONST.VG_POSITION_TYP_ARTIKEL;  
			cBase[2][0]= "Zusatztext";  	cBase[2][1]= myCONST.VG_POSITION_TYP_ZUSATZTEXT;  
			this.set_oDataToViewForDatabase(new dataToView(cBase,true,bibE2.get_CurrSession() ));
		}
		else if (bNormal)
		{
			String[][] cBase = new String[2][2];
			cBase[0][0]= "-";  				cBase[0][1]= "";
			cBase[1][0]= "Artikel";  		cBase[1][1]= myCONST.VG_POSITION_TYP_ARTIKEL;  
			this.set_oDataToViewForDatabase(new dataToView(cBase,true,bibE2.get_CurrSession() ));
		}
		
		this.setFont(new E2_FontItalic(-2));
		this.add_oActionAgent(new ownActionAgentSettingPositionsTyp());
		
		//auf jeden fall nur bei neueingabe erlaubt
		this.EXT().set_MaskEnabled_Combination(true,false,false,false,false);
		
		
	}

	
	
		
	
	private class ownActionAgentSettingPositionsTyp extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_DB_SelectField oSelField = (MyE2_DB_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();
			
			Vector<String> vZusatzFieldsToDisable = new Vector<String>();
			if (BS_SelectField_POSITIONSTYP.this.cTABLE_NAME.toUpperCase().equals("JT_VPOS_STD"))
			{
				vZusatzFieldsToDisable.add(BSA__CONST.HASH_KEY_POSITION_MASK_SET_ACTUAL_MONTH);
				vZusatzFieldsToDisable.add(BSA__CONST.HASH_KEY_POSITION_MASK_SET_NEXT_MONTH);
				vZusatzFieldsToDisable.add("GUELTIG_VON");
				vZusatzFieldsToDisable.add("GUELTIG_BIS");
			}
			
			E2_ComponentMAP oMAP = 		oSelField.EXT().get_oComponentMAP();
			new BS_MaskSetter_POS_TYP(oMAP,oSelField.get_ActualWert(),vZusatzFieldsToDisable);
		}
	}
	
	
	
}
