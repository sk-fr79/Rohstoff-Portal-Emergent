package panter.gmbh.Echo2.ActionEventTools;

import java.util.Vector;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.components.MyE2IF__Component;


/*
 * klasse, die den XX_ActionAgent.executeAgentCode() - Methoden uebergeben wird, um
 * diverse varianten von ausfuehrungen zu dokumentieren:
 * 1. STandard aus klick-event
 * 2. passiver klick-event
 * 3. ausfuehrung ohne event (nur der reine code) 
 */
public class ExecINFO 
{
	private MyActionEvent 											oEvent = null;
	private boolean  												bEventWasPassive = false;
	/*
	 * zaehler-nummer fuer die Schleife, damit innen bekannt ist, welche nummer in der Reihenfolge der Aufrufe mehrerer ActionAgents innerhalb
	 * eines Events ist
	 */
	private int   iNummerInActionAgentStack = 0;
	
	/*
	 * falls ein dynamischer (z.B. an einer ComponentMAP haengender)  E2_BasicModuleContainer_PopUp_BeforeExecute innerhalb eines actionagents ausgefuehrt wird 
	 * und noch weiter XX_ActionAgents im XX_ActionAgent-Vector vorhanden waren, wird der popup am ersten ActionAgent ausgefuehrt, die weiteren XX_ActionAgents
	 * werden dann aber trotzdem ausgefuehrt. Falls ein z.B. Speichervorgang innerhalb des popup-fensters abgesegnet wird, dann wird dieser erste actionAgent 
	 * erst mit dem ok-button ausgefuehrt, somit fehlen folgende XX_ActionAgents nach dem speichern.
	 * tritt so ein fall auf, dann werden die agents ab der position n+1 im XX_ActionAgentVector hier mit uebernommen und im ok-button des popups mit ausgefuehrt
	 */
	private Vector<XX_ActionAgent>  vActionAgentsREST = new Vector<XX_ActionAgent>();
	
	
	public ExecINFO(MyActionEvent oActionEvent, boolean eventWasPassive) 
	{
		super();
		oEvent = oActionEvent;
		bEventWasPassive = eventWasPassive;
	}


	public boolean get_bEventWasPassive() 
	{
		return bEventWasPassive;
	}
	
	
	public MyActionEvent get_MyActionEvent() 
	{
		return oEvent;
	}
	
	
	public boolean get_bWasCallWithoutEvent()
	{
		return (this.oEvent==null);
	}
	
	
	public void make_ID_Validation_ADD_TO_Global_MV(Vector<String> vIDsToHandle)
	{
		this.oEvent.make_ID_Validation(vIDsToHandle);
	}
	
	public MyE2_MessageVector make_ID_Validation(Vector<String> vIDsToHandle)
	{
		return this.oEvent.make_ID_Validation(vIDsToHandle);
	}

	
	public int get_iNummerInActionAgentStack()
	{
		return iNummerInActionAgentStack;
	}
	
	public void set_iNummerInActionAgentStack(int iNummerIm_ActionAgentVector)
	{
		this.iNummerInActionAgentStack= iNummerIm_ActionAgentVector;
	}


	public Vector<XX_ActionAgent> get_vActionAgentsREST()
	{
		return vActionAgentsREST;
	}

	
	//2012-12-27: evaluierung des hashkeys aus dieser methode raus
	public String get_HASHKEY_of_KLICK_COMPONENT()
	{
		String cHASHKEY = null;
		
		
		if (this.get_MyActionEvent()!=null 
			&& this.get_MyActionEvent().getSource()!=null 
			&& this.get_MyActionEvent().getSource() instanceof MyE2IF__Component)
		{
			cHASHKEY = ((MyE2IF__Component)this.get_MyActionEvent().getSource()).EXT().get_C_HASHKEY();
		}

		return cHASHKEY;
	}
	
	
	
	//2012-12-27: evaluierung des hashkeys aus dieser methode raus
	public E2_ComponentMAP get_ComponentMAP_of_KLICK_COMPONENT()
	{
		E2_ComponentMAP  oMAP = null;

		if (this.get_MyActionEvent()!=null 
			&& this.get_MyActionEvent().getSource()!=null 
			&& this.get_MyActionEvent().getSource() instanceof MyE2IF__Component)
		{
			oMAP = ((MyE2IF__Component)this.get_MyActionEvent().getSource()).EXT().get_oComponentMAP();
		}

		return oMAP;
	}

	
	
	//2015-02-27: innerhalb des RB-Umfelds kann der letzte genutzte
	public RB_KF get_RB_K_of_KLICK_COMPONENT()
	{
		RB_KF rb_k = null;
		if (this.get_MyActionEvent()!=null 
			&& this.get_MyActionEvent().getSource()!=null 
			&& this.get_MyActionEvent().getSource() instanceof MyE2IF__Component)
		{
			rb_k = ((MyE2IF__Component)this.get_MyActionEvent().getSource()).EXT().get_RB_K();
		}
		return rb_k;
	}

	

	
	
}
