package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMultiActionWithConfirmPOPUP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public abstract class BS_L_ActionAgentCopyVorgang extends ButtonActionAgentMultiActionWithConfirmPOPUP
{
	private 	BS__SETTING 	oSetting = null; 
	
	public BS_L_ActionAgentCopyVorgang(E2_NavigationList onavigationList, MyE2_Button oownButton, String cNameFuerFenster, BS__SETTING Setting)
	{
		super(	new MyE2_String("Kopiere "+cNameFuerFenster),
				onavigationList, 
				oownButton,new MyE2_String("Sicher? "),
				new MyE2_String(cNameFuerFenster+" kopieren ?"), 
				new Extent(400), 
				new Extent(200));
		
		
		this.oSetting = Setting;
	}

	public void do_ChangeAction(Vector<String> vIDsSelected, E2_NavigationList oNaviList) throws myException
	{
		
		Vector<String> vAllSql = new Vector<String>();
		
		for (int i=0;i<vIDsSelected.size();i++)
		{
			BS_CopyVorgang oCopy = new BS_CopyVorgang(	(String)vIDsSelected.get(i),
														this.oSetting.get_oVorgangTableNames(),
														null, null, null, null);
			vAllSql.addAll(oCopy.get_vSQL_STACK_ForCopy());
			
		}
		
		String cQueryMaxID = "SELECT   NVL(MAX("+this.oSetting.get_oVorgangTableNames().get_cVKOPF_PK()+"),0) FROM "+
									bibE2.cTO()+"."+this.oSetting.get_oVorgangTableNames().get_cVKOPF_TAB();
		

		String cLASTID = bibDB.EinzelAbfrage(cQueryMaxID);
		
		//zuerst pruefen, welcher der letzte id wert im kopfsatz war,
		//dann alle danach geschriebenen in die liste haengen, dann kann der stack am stueck verarbeitet werden
		bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vAllSql,true));
		if (bibMSG.get_bIsOK())
		{
			String cLASTID2 = bibDB.EinzelAbfrage(cQueryMaxID);
			try
			{
				long iLastID = new Long(cLASTID).longValue();
				long iLastID2 = new Long(cLASTID2).longValue();
				
				Vector<String> vNeue = new Vector<String>();
				for (long i=iLastID+1;i<=iLastID2;i++)
				{
					vNeue.add(""+i);
				}
				oNaviList.ADD_NEW_ID_TO_ALL_VECTORS(vNeue);
			}
			catch (Exception ex) {}
		}
		else
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Kopieren ..."));
		}
		
	}

	public MyE2_MessageVector CheckIdToChange(Vector<String> vID_UnformatedToDelete) {return null;}
	
}
