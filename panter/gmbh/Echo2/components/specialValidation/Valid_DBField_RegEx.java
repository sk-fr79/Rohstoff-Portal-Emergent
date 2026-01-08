package panter.gmbh.Echo2.components.specialValidation;

import java.util.Vector;
import java.util.regex.Pattern;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldSetter_AND_Validator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIELD_RULE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class Valid_DBField_RegEx extends XX_FieldSetter_AND_Validator
{
	
	private MyE2IF__DB_Component  			oDB_Component = null;
	private Vector<RECORD_FIELD_RULE>  		vRecAllowedRegEx = null;
	
	
	
	
	public Valid_DBField_RegEx(MyE2IF__DB_Component DB_Component, Vector<RECORD_FIELD_RULE> AllowedRegEx)
	{
		super();
		this.oDB_Component = DB_Component;
		this.vRecAllowedRegEx = AllowedRegEx;
	}




	@Override
	public MyE2_MessageVector isValid(String cSTATUS_MAP, MyE2EXT__Component EXT_own) throws myException
	{
		MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();

		//schritt 1: wenn der eintrag leer ist, dann keine pruefung
		String cWert = this.oDB_Component.get_cActualDBValueFormated();
		
		if (S.isFull(cWert))
		{
			boolean bEinAusdruckErfuellt = false;
			
			for (RECORD_FIELD_RULE recREGEX: this.vRecAllowedRegEx)
			{
				if (S.isFull(recREGEX.get_RULE_cUF_NN("")))
				{
					if (Pattern.matches(recREGEX.get_RULE_cUF_NN(""), cWert))
					{
						bEinAusdruckErfuellt=true;
						break;
					}
				}
			}
			
			if (!bEinAusdruckErfuellt)
			{
				String cPraefix = "Feld:"+this.oDB_Component.EXT_DB().get_oSQLField().get_cFieldLabel()+":"+" Erlaubte Feldregeln: ";
				String cInfo = cPraefix;
				for (RECORD_FIELD_RULE recREGEX: this.vRecAllowedRegEx)
				{
					cInfo=cInfo+(cInfo.equals(cPraefix)?"":" // ")+recREGEX.get_RULE_INFO_cUF_NN(recREGEX.get_RULE_cUF_NN("<keine Info zur Eingaberegel ...>"));
				}
				oMV_Rueck.add(new MyE2_Alarm_Message(new MyE2_String(cInfo,false)));
			}
		}
		
		return oMV_Rueck;
	}

}
