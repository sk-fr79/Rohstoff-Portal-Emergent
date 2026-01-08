package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgent_TOGGLE_Y_N;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.BSK__CONST;

public class BSKC__BT_LOCK_UNLOCK_KONTRAKTPOS extends MyE2_Button
{

	
	
	
	private String 					cID_VPOS_KON_to_close = null;            
	private String 					cID_VPOS_KON_to_refresh_in_list = null;            
	private E2_NavigationList    	oNaviList = null;
	
	/**
	 * 
	 * @param ID_VPOS_KON_to_close (wenn dieser wert null ist, dann holt er sich das aus der E2_ComponentMAP
	 */
	public BSKC__BT_LOCK_UNLOCK_KONTRAKTPOS(	String 				ID_VPOS_KON_to_close,
												String 				ID_VPOS_KON_to_refresh,
												boolean  			bIsLockedAtStart, 
												E2_NavigationList  	NaviList) 
	{
		super(E2_ResourceIcon.get_RI(bIsLockedAtStart?"locked.png":"unlocked.png"),true);

		this.cID_VPOS_KON_to_close = 			ID_VPOS_KON_to_close;
		this.cID_VPOS_KON_to_refresh_in_list = 	ID_VPOS_KON_to_refresh;
		this.oNaviList = 						NaviList;
		
		this.setLayoutData(bIsLockedAtStart?BSK__CONST.get_RedLayout4OpenCloseButton():BSK__CONST.get_GreenLayout4OpenCloseButton());
		
		this.add_IDValidator(BSK__CONST.VALID_VPOS_KON_ARTIKELPOS_MENGE_PREIS_NOT_LEER);

		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_KON","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Position wurde bereits gelöscht !")));
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LOCK_UNLOCK_KONTRAKTPOS"));
		this.setToolTipText(new MyE2_String("Öffnen/Schliessen einer Kontraktposition").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());

	}

	
	/*
	 *  normalerweise ist der button immer enabled
	 */
	public void set_bEnabled_For_Edit(boolean _enabled) throws myException
	{
		boolean enabled = this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();;
		
		this.setEnabled(enabled);
		if (this.get_oImgDisabled() != null && this.get_oImgEnabled() != null)
		{
			if (enabled)
				this.setIcon(this.get_oImgEnabled());
			else
				this.setIcon(this.get_oImgDisabled());
		}
	}


	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		return new BSKC__BT_LOCK_UNLOCK_KONTRAKTPOS(null,null,true,this.oNaviList);
	}
	
	
	
	private class ownActionAgent extends ButtonActionAgent_TOGGLE_Y_N
	{

		public ownActionAgent() 
		{
			super(new MyE2_String("Öffnen/Schliessen Kontrakt-Position"),
					null, 
					"ABGESCHLOSSEN",
					"JT_VPOS_KON_TRAKT",
					"ID_VPOS_KON_TRAKT");
			
			this.get_vZusatzAgents().removeAllElements();
			this.get_vZusatzAgents().add(new actionRefreshList());
			
		}

		
		private class actionRefreshList extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				String cActual_ID_VPOS_KON_refresh = BSKC__BT_LOCK_UNLOCK_KONTRAKTPOS.this.cID_VPOS_KON_to_refresh_in_list;
				
				if (BSKC__BT_LOCK_UNLOCK_KONTRAKTPOS.this.cID_VPOS_KON_to_refresh_in_list == null)   // dann erfolgt der aufruf aus der haupt-liste und muss rausgesucht werden
				{
					cActual_ID_VPOS_KON_refresh = BSKC__BT_LOCK_UNLOCK_KONTRAKTPOS.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				}
				BSKC__BT_LOCK_UNLOCK_KONTRAKTPOS.this.oNaviList.Refresh_ComponentMAP(cActual_ID_VPOS_KON_refresh, E2_ComponentMAP.STATUS_VIEW);
			}
		}

		
		/*
		 * macht aus der ID_VPOS_KON eine ID_VPOS_KON_TRAKT
		 */
		public String Create_ID_FOR_UpdateStatement(String cID_VPOS_KON) throws myException
		{
			return new RECORD_VPOS_KON(cID_VPOS_KON).get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_ID_VPOS_KON_TRAKT_cUF();
		}

		/*
		 * methode, um fuer aktionen, wo keine navilist uebergeben wird, trotzdem ids zu erzeugen
		 * zusammenzufuheren (normalerweise ist es die gleiche, kann aber unterschieden sein
		 */
		public Vector<String> get_IDS_FOR_Toggle() throws myException
		{
			if (BSKC__BT_LOCK_UNLOCK_KONTRAKTPOS.this.cID_VPOS_KON_to_close == null)   // dann erfolgt der aufruf aus der liste und muss rausgesucht werden
			{
				return bibALL.get_Vector(BSKC__BT_LOCK_UNLOCK_KONTRAKTPOS.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			}
			return bibALL.get_Vector(BSKC__BT_LOCK_UNLOCK_KONTRAKTPOS.this.cID_VPOS_KON_to_close);
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
