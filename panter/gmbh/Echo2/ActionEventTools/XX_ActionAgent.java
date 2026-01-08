package panter.gmbh.Echo2.ActionEventTools;

import java.util.UUID;

import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;


public abstract class XX_ActionAgent
{
	//2014-11-07: UUIDs erzeugen
	private UUID     							UUID = null;

	
	/*
	 * ein container, der benutzt werden kann, um innerhalb der executeAgentCode - ausfuehrung 
	 * evtl. ein popup-fenster einblenden zu koennen
	 */
	private V_Single_BasicModuleContainer_PopUp_BeforeExecute  	vContainerBeforeExecute = new V_Single_BasicModuleContainer_PopUp_BeforeExecute();
	private E2_BasicModuleContainer_PopUp_BeforeExecute         oActualContainerBeforeExecute = null;

	
	public XX_ActionAgent()
	{
		super();
	}

	
	public void ExecuteAgentCode(ExecINFO oExecInfo) throws myException
	{

		/*
		 * zuerst die potentiellen container-before-execute sammeln, zuerst die, die aus dem interface dynamisch eingesammelt wurden,
		 * dann die dem XX_ActionAgent statisch zugewiesen wurden
		 */
		V_Single_BasicModuleContainer_PopUp_BeforeExecute vPopups_Sammlung_temp = new V_Single_BasicModuleContainer_PopUp_BeforeExecute();
		if (oExecInfo.get_MyActionEvent()!= null )
		{
			if (oExecInfo.get_iNummerInActionAgentStack()==0)
			{
				/*
				 * die dynamischen, d.h. von ausfuehrenden element herruehrenden Popups duerfen nur beim ersten aufruf 
				 * des Vectors der ActionAgents ausgefuehrt werden, da sonst ein Button mit mehreren ActionAgents das popup mehrmals aufruft
				 */
				vPopups_Sammlung_temp.addAll(oExecInfo.get_MyActionEvent().get_vE2_ContainerBeforeExecute_Dynamic());
			}
		}
		vPopups_Sammlung_temp.addAll(this.vContainerBeforeExecute);       //das sind evtl. statische, d.h. dem actionAgenten direkt zugeordnete Popups
		
		
		//2014-10-08: pruefung: es darf nicht sein, dass ein XX_ActionAgentVector mit mehreren Teilnehmern ausgefuehrt wird,
		//                      der gleichzeitig popups ausfuehren soll. siehe fehlerbeschreibung im E2_InnerActionListenerForActionAgents
		// !! wenn dies eintritt, dann hier eine exception schmeissen
		if (vPopups_Sammlung_temp.size()>0 && oExecInfo.get_vActionAgentsREST().size()>0) {
			//2014-11-03: exception wegen fehlfunktion rausgenommen
//			throw new myExceptionForUser(new MyE2_String("Fehler beim Aufruf der AUTO-Windows - mehrfach vorhanden .. Bitte Info an SYSADMIN"));
		}
		// -----------------------------------------------------------------------------------------
		
		/*
		 * falls dem actionAgent ein Popup-Container zugeordnet wurde, dann muss dieser jetzt angezeigt werden
		 */
		if (vPopups_Sammlung_temp.size()>0 && this.oActualContainerBeforeExecute==null)    //dann den ersten ziehen
		{
			this.oActualContainerBeforeExecute = vPopups_Sammlung_temp.get(0);
		}
		else if (vPopups_Sammlung_temp.size()>0 && this.oActualContainerBeforeExecute != null)
		{
			//dann den naechsten suchen
			for (int i=0;i<vPopups_Sammlung_temp.size();i++)
			{
				if (vPopups_Sammlung_temp.get(i)==this.oActualContainerBeforeExecute)   //dann den naechsten aus dem vector holen (wenn eine da ist) 
				{
					if (vPopups_Sammlung_temp.size()>(i+1))
					{
						this.oActualContainerBeforeExecute = vPopups_Sammlung_temp.get(i+1);
						break;
					}
					else
					{
						this.oActualContainerBeforeExecute = null;                //ende der poup-orgie
					}
				}
			}
		}
		
		//vorab den anzeigestatus ermitteln, damit die methode nur einmal gerufen wird (evtl. vorhandene datenbank-querys)
		boolean bMustBeShown = false;
		if (this.oActualContainerBeforeExecute != null)
		{
			bMustBeShown = this.oActualContainerBeforeExecute.makePreparationAndCheck_IF_MUST_BE_SHOWN(oExecInfo);
		}
		
		//jetzt die bedingung des aufrufes pruefen (die abfrage this.oActualContainerBeforeExecute.isMustBeShown())
		if (this.oActualContainerBeforeExecute != null && !bMustBeShown)
		{
			//dann diese methode wieder aufrufen
			this.ExecuteAgentCode(oExecInfo);     //damit wird der naechste gezogen
		}
		else if (this.oActualContainerBeforeExecute != null && bMustBeShown)
		{
			//denn container aus der liste aufrufen
			this.oActualContainerBeforeExecute.POPUP(this, oExecInfo);
		}
		else
		{
			if (bibMSG.get_bIsOK())   // koennte eine meldung aus der pruefung this.oContainerBeforeExecute.isMustBeShown() kommen
			{
				this.executeAgentCode(oExecInfo);
			}
		}
	}
	
	
	public abstract void executeAgentCode(ExecINFO oExecInfo) throws myException;



	
	public void set_oPopup_ContainerBeforeExecute(E2_BasicModuleContainer_PopUp_BeforeExecute oPopupBeforeExec)
	{
		this.vContainerBeforeExecute.removeAllElements();
		if (oPopupBeforeExec != null)
		{
			this.vContainerBeforeExecute.add(oPopupBeforeExec);
		}
	}


	
	public void ResetLoop()
	{
		this.oActualContainerBeforeExecute = null;
		//damit nach einem abbruch einer mehrfach-kaskade diese wieder am anfang startet 
	}
	
	
	
	
	/**
	 * 2014-11-07: UUID wird erzeugt
	 * @return
	 */
	public UUID get_UUID() {
		if (this.UUID == null) {
			this.UUID = java.util.UUID.randomUUID();
		}
		return this.UUID;
	}
	

	

	
}
