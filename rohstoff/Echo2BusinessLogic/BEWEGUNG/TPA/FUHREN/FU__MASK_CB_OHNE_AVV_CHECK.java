package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class FU__MASK_CB_OHNE_AVV_CHECK extends MyE2_DB_CheckBox 
{

	public FU__MASK_CB_OHNE_AVV_CHECK(SQLField osqlField) throws myException 
	{
		super(osqlField);
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new ownValidator());
		this.add_GlobalAUTHValidator_AUTO("SETZE_OHNE_AVV_VERTRAG_CHECK");
		this.EXT().set_bDisabledFromBasic(true);
		this.setToolTipText(new MyE2_String("Zeigt an, dass in dieser Fuhre kein AVV-Vertragscheck gemacht wird").CTrans());
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
		}
	}
	
	/*
	 * sorgt dafuer, dass das einschalten nur moeglich ist, wenn noch keine positionen dieser fuhre zugeordnet wurden 
	 */
	private class ownValidator extends XX_ActionValidator
	{

		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException 
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			return oMV;
		}

		public MyE2_MessageVector isValid(String cID_Unformated) throws myException 
		{
			return new MyE2_MessageVector();
		}
		
	}
	
}
