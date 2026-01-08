package rohstoff.Echo2BusinessLogic.FIBU;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


public class FIBU_LIST_BT_BUCHUNG_TRENNEN extends MyE2_Button
{

	private E2_NavigationList  oNaviList = null;

	public FIBU_LIST_BT_BUCHUNG_TRENNEN(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("buchungen_trennen.png") , E2_ResourceIcon.get_RI("leer.png"));
		
		this.oNaviList = onavigationList;
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BUCHUNG_TRENNEN"));
		this.setToolTipText(new MyE2_String("Buchungsblock trennen").CTrans());
		this.add_IDValidator(new ownValidator());
	}
	
	
	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component componentWhichIsValidated)	throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_FIBU)	throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			RECORD_FIBU  recFibu = new RECORD_FIBU(cID_FIBU);
			
		
			if (recFibu.is_STORNIERT_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Stornierte Buchung: Keine weitere Bearbeitung möglich !"));
				return oMV; 
			}
			
			
			return oMV;
		}
		
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FIBU_LIST_BT_BUCHUNG_TRENNEN oThis = FIBU_LIST_BT_BUCHUNG_TRENNEN.this;
			
			Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
			Vector<String> vIDAusBlock = new Vector<String>();
			
			if (vIDs.size() != 1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte genau eine Buchung aus einem Block auswählen  !!"));
				return;
			}

			bibMSG.add_MESSAGE(execInfo.make_ID_Validation(vIDs));
			
			if (bibMSG.get_bIsOK())
			{
				String cID_FIBU = vIDs.get(0);
				
				RECORD_FIBU  recFibu     = new RECORD_FIBU(cID_FIBU);
				RECLIST_FIBU reclistFIBU = new RECLIST_FIBU("SELECT * FROM "+bibE2.cTO()+".JT_FIBU WHERE BUCHUNGSBLOCK_NR="+recFibu.get_BUCHUNGSBLOCK_NR_cUF());
				


				Vector<String> vSQL = new Vector<String>();
				
				for (int i=0;i<reclistFIBU.get_vKeyValues().size();i++)
				{
					//programm-marker: 	KORREKTUR-FIBU-BUG
					
					reclistFIBU.get(i).set_NEW_VALUE_BUCHUNGSBLOCK_NR(bibALL.get_SEQUENCE_VECT_VALUES("SEQ_BUCHUNGSBLOCK_FIBU", 1).get(0));
					reclistFIBU.get(i).set_NEW_VALUE_BUCHUNG_GESCHLOSSEN("N");
					
					
					BigDecimal  bdPositionsBetrag = reclistFIBU.get(i).get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).multiply(
													reclistFIBU.get(i).get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal.ONE),MathContext.DECIMAL32);

					
					reclistFIBU.get(i).set_NEW_VALUE_RESTBETRAG_FREMD_WAEHRUNG(MyNumberFormater.formatDez(bdPositionsBetrag,2,true));
					
					vSQL.add(reclistFIBU.get(i).get_SQL_UPDATE_STATEMENT(null, true));
					
					vIDAusBlock.add(reclistFIBU.get(i).get_ID_FIBU_cUF());
				}
				
				
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
				
				if (bibMSG.get_bIsOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Selektierter Buchungsblock wurde aufgelöst / Buchungen wurden geöffnet !",true)));
				}
				
				oThis.oNaviList._REBUILD_ACTUAL_SITE("");
				oThis.oNaviList.Check_ID_IF_IN_Page(vIDAusBlock);
			}
			
		}
	}
	
	
	
}
