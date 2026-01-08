package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;

public class FU__MASK_CB_KEIN_KONTRAKT_NOETIG extends MyE2_DB_CheckBox 
{

	public FU__MASK_CB_KEIN_KONTRAKT_NOETIG(SQLField osqlField) throws myException 
	{
		super(osqlField);
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new ownValidator());
		this.add_GlobalAUTHValidator_AUTO("KEIN_KONTRAKT_NOETIG");
		this.EXT().set_bDisabledFromBasic(true);
		this.setToolTipText(new MyE2_String("Zeigt an, dass in dieser Fuhre kein Warenausgangskontrakt angegeben werden darf!").CTrans());

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
			FU__MASK_CB_KEIN_KONTRAKT_NOETIG oThis = FU__MASK_CB_KEIN_KONTRAKT_NOETIG.this;
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			boolean bRueck = false;
			
			
			// pruefen, ob im edit-modus
			if (oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()!=null)
			{
				String cID_VPOS_TPA_FUHRE = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				PRUEF_RECORD_VPOS_TPA_FUHRE oFuhre = new PRUEF_RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE,true);
				
				int iStatus = oFuhre.__Actual_StatusBuchung();
				
				if ((iStatus!=myCONST.STATUS_FUHRE__GANZGEBUCHT) && iStatus!=myCONST.STATUS_FUHRE__TEILSGEBUCHT)
				{
					bRueck = true;
				}
			}
			else
			{
				bRueck = true;
			}
			if (!bRueck)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nur möglich, wenn noch keine Rechnungs-/Gutschriftpositionen zu dieser Transport-Position gebucht wurden !")));
			}
			return oMV;
		}

		public MyE2_MessageVector isValid(String cID_Unformated) throws myException 
		{
			return new MyE2_MessageVector();
		}
		
	}
	
}
