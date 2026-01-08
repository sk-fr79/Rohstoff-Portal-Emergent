package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgent_TOGGLE_Y_N;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KONTRAKT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class UMA_LIST_BT_LOCK_UMA_KONTRAKT extends MyE2_ButtonInLIST
{
	
	/**
	 * 
	 * @param ID_VPOS_KON_to_close (wenn dieser wert null ist, dann holt er sich das aus der E2_ComponentMAP
	 */
	public UMA_LIST_BT_LOCK_UMA_KONTRAKT() 
	{
		super(E2_ResourceIcon.get_RI("empty10.png"),true);

		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_UMA_KONTRAKT","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Der UMA-Kontrakt wurde bereits gelöscht !")));
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LOCK_UNLOCK_UMA_KONTAKT"));
		this.setToolTipText(new MyE2_String("Öffnen/Schliessen eines Umarbeitungskontraktes").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
	}

	
	public void set_RED_GREEN_LockButton(boolean bIsLocked)
	{
		this.setIcon(E2_ResourceIcon.get_RI(bIsLocked?"locked.png":"unlocked.png"));
		this.__setImages(E2_ResourceIcon.get_RI(bIsLocked?"locked.png":"unlocked.png"), true);
		this.setLayoutData(bIsLocked?UMA_CONST.get_RedLayout4OpenCloseButton():UMA_CONST.get_GreenLayout4OpenCloseButton());
		this.EXT().set_oLayout_ListElement(bIsLocked?UMA_CONST.get_RedLayout4OpenCloseButton():UMA_CONST.get_GreenLayout4OpenCloseButton());
	}

	public void set_DELETED_LockButton()
	{
		this.setIcon(E2_ResourceIcon.get_RI("empty10.png"));
		this.__setImages(E2_ResourceIcon.get_RI("empty10.png"), true);
		this.setLayoutData(UMA_CONST.get_DelLayout4OpenCloseButton());
		this.EXT().set_oLayout_ListElement(UMA_CONST.get_DelLayout4OpenCloseButton());
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		return new UMA_LIST_BT_LOCK_UMA_KONTRAKT();
	}
	
	
	private class actionSetRedGreen extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			String cID = UMA_LIST_BT_LOCK_UMA_KONTRAKT.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			RECORD_UMA_KONTRAKT recUMA = new RECORD_UMA_KONTRAKT(cID);
			
			UMA_LIST_BT_LOCK_UMA_KONTRAKT.this.set_RED_GREEN_LockButton(recUMA.is_ABGESCHLOSSEN_YES());
			
			//sonderfall
			if (recUMA.is_DELETED_YES())
			{
				UMA_LIST_BT_LOCK_UMA_KONTRAKT.this.set_DELETED_LockButton();
			}
		}
	}
	
	
	
	
	private class ownActionAgent extends ButtonActionAgent_TOGGLE_Y_N
	{

		public ownActionAgent() 
		{
			super(new MyE2_String("Öffnen/Schliessen UMA-Kontrakt"),
					null, 
					"ABGESCHLOSSEN",
					"JT_UMA_KONTRAKT",
					"ID_UMA_KONTRAKT");
			
			this.get_vZusatzAgents().removeAllElements();
			this.get_vZusatzAgents().add(new actionSetRedGreen());
		}

		
		
		/*
		 * macht aus der ID_VPOS_KON eine ID_VPOS_KON_TRAKT
		 */
		public String Create_ID_FOR_UpdateStatement(String cID_UMA_KONTRAKT) throws myException
		{
			return cID_UMA_KONTRAKT;
		}

		/*
		 * methode, um fuer aktionen, wo keine navilist uebergeben wird, trotzdem ids zu erzeugen
		 * zusammenzufuheren (normalerweise ist es die gleiche, kann aber unterschieden sein
		 */
		public Vector<String> get_IDS_FOR_Toggle() throws myException
		{
			String cID = UMA_LIST_BT_LOCK_UMA_KONTRAKT.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			return bibALL.get_Vector(cID);
		}
		

		
		@Override
		public Vector<String> get_vSQL_Before_TOGGLE(String cID_toToggleUnformated, String cNewValue)	throws myException 
		{
			return null;
		}

		@Override
		public Vector<String> get_vSQL_After_TOGGLE(String cID_toToggleUnformated, String cNewValue) throws myException 
		{
			return null;
		}

		@Override
		public void Execute_Before_TOGGLE(Vector<String> vIDs_toToggleUnformated)	throws myException 
		{
			
		}

		@Override
		public void Execute_After_TOGGLE(Vector<String> vIDs_toToggleUnformated)	throws myException 
		{
			
		}

		@Override
		public MyE2_MessageVector CheckIdToToggle(Vector<String> vID_UnformatedToDelete) 
		{
			return new MyE2_MessageVector();
		}
		
	}

}
