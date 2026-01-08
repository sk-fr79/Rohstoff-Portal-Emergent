package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.exceptions.myException;


/*
 * tochterkomponente fuer die positionseingabe in der kopf-maske
 */
public class BSK_K_MASK_COMP_FullDaughterPositions extends XX_FULL_DAUGHTER
{

	private	BSK_K_MASK__ModulContainer 	oModulContainer_MASK_InTheTOP = null;
	
	// wenn die komponente im listen-masken-zusammenhang benutzt wird, dann kann hier die Kopf-Navilist uebergeben werden,
	// dann aktualisiert ein Speicher-Vorgang aus der Maske auch die liste
	private E2_NavigationList			oNaviList_VKOPF = null;
	
	private BSK_P_LIST__ModulContainer	oModulContainer_LIST_VorgangsPosition = null;
	private BSK_P_MASK__ModulContainer	oModulContainer_MASK_VorgangsPosition = null;
	
	
	public BSK_K_MASK_COMP_FullDaughterPositions(	SQLFieldForPrimaryKey 		osqlField, 
													BSK_K_MASK__ModulContainer 	oMaskContainerInTheTOP) throws myException
	{
		super(osqlField);
	
		this.oModulContainer_MASK_InTheTOP = oMaskContainerInTheTOP;
		
		this.oModulContainer_MASK_VorgangsPosition = new BSK_P_MASK__ModulContainer(	oMaskContainerInTheTOP.get_SETTING());
		
		this.oModulContainer_LIST_VorgangsPosition = new BSK_P_LIST__ModulContainer(oMaskContainerInTheTOP,this.oModulContainer_MASK_VorgangsPosition);
		this.set_bIsActive(true);
		
		//2018-05-28: 
		this.oModulContainer_LIST_VorgangsPosition.get_oListBedienPanel().getButNEW().add_oActionAgent(new OwnActionAgent());
	}

	
	/**
	 * 2018-05-28: fehler-fix: wenn in der kopfmaske einmal eine neuerfassung laeuft, dann kann die waehrung nicht mehr geaendert werden
	 */
	private class OwnActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ComponentMAP mapForMask = oModulContainer_MASK_InTheTOP.get_vCombinedComponentMAPs().get(0);
			mapForMask.get__Comp("ID_WAEHRUNG_FREMD").set_bEnabled_For_Edit(false);
		}
	}
	
	
	
	public Component build_content_for_Mask(String cValueFormated, String cValueUnformated, String cMASK_STATUS) throws myException
	{
		this.set_buttons_IN_Status(cMASK_STATUS);   // die buttons passend einstellen
		
		
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY))
		{
			return new saveButtonAndReload();
		}
		else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			/*
			 * feststellen, ob in der kopf-liste (falls vorhanden) der schalter "zeige geloeschte saetze" an ist
			 */
			boolean bZeigeGeloeschte = false;
			if (this.oModulContainer_MASK_InTheTOP.get_oBSK_K_ModulContainerLIST() != null)
			{
				bZeigeGeloeschte =this.oModulContainer_MASK_InTheTOP.get_oBSK_K_ModulContainerLIST().get_oSelektor().get_oCB_ShowDeletedRows().isSelected();
			}
			this.oModulContainer_LIST_VorgangsPosition.get_oComponentMAP_List().get_oSQLFieldMAP().clear_BEDINGUNG_STATIC();
			if (! bZeigeGeloeschte)
				this.oModulContainer_LIST_VorgangsPosition.get_oComponentMAP_List().get_oSQLFieldMAP().add_BEDINGUNG_STATIC("  NVL(JT_VPOS_KON.DELETED,'N')='N'");

			
			
			this.oModulContainer_LIST_VorgangsPosition.set_ID_VKOPF_KON_IN_Position(cValueUnformated);
			this.oModulContainer_LIST_VorgangsPosition.get_oNavigationList().Fill_NavigationList("");
			this.oModulContainer_MASK_VorgangsPosition.make_SettingsFrom_Head_to_Position(cValueUnformated);
			return this.oModulContainer_LIST_VorgangsPosition;
		}
		else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
		{

			/*
			 * feststellen, ob in der kopf-liste (falls vorhanden) der schalter "zeige geloeschte saetze" an ist
			 */
			boolean bZeigeGeloeschte = false;
			if (this.oModulContainer_MASK_InTheTOP.get_oBSK_K_ModulContainerLIST() != null)
			{
				bZeigeGeloeschte =this.oModulContainer_MASK_InTheTOP.get_oBSK_K_ModulContainerLIST().get_oSelektor().get_oCB_ShowDeletedRows().isSelected();
			}
			this.oModulContainer_LIST_VorgangsPosition.get_oComponentMAP_List().get_oSQLFieldMAP().clear_BEDINGUNG_STATIC();
			if (! bZeigeGeloeschte)
				this.oModulContainer_LIST_VorgangsPosition.get_oComponentMAP_List().get_oSQLFieldMAP().add_BEDINGUNG_STATIC("  NVL(JT_VPOS_KON.DELETED,'N')='N'");

			
			this.oModulContainer_LIST_VorgangsPosition.set_ID_VKOPF_KON_IN_Position(cValueUnformated);
			this.oModulContainer_LIST_VorgangsPosition.get_oNavigationList().Fill_NavigationList("");
			this.oModulContainer_MASK_VorgangsPosition.make_SettingsFrom_Head_to_Position(cValueUnformated);
			return this.oModulContainer_LIST_VorgangsPosition;
		}
		else
			return new MyE2_Label(new MyE2_String("Fehler !!!"));
		
	}

	public void prepare_DaughterContentForNew() throws myException
	{
		this.removeAll();
		this.add(new saveButtonAndReload());
	}

	public Component build_non_active_placeholder() throws myException
	{
		return new MyE2_Label(new MyE2_String("INAKTIV !!!"));
	}



	public void set_buttons_IN_Status(String cMASK_STATUS) throws myException
	{
		this.oModulContainer_LIST_VorgangsPosition.get_oListBedienPanel().set_ButtonStatus(cMASK_STATUS);
	}

	public void set_oNaviList_VKOPF(E2_NavigationList naviList_VKOPF)
	{
		oNaviList_VKOPF = naviList_VKOPF;
	}
	
	
	private class saveButtonAndReload extends MyE2_Button
	{
		public saveButtonAndReload()
		{
			super(new MyE2_String("Positionen können erst erfasst werden, wenn der Kopfsatz gespeichert wurde!"));
			this.add_oActionAgent(new ownActionAgent());
		}
		
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(BSK_K_MASK_COMP_FullDaughterPositions.this.oModulContainer_MASK_InTheTOP, 
												BSK_K_MASK_COMP_FullDaughterPositions.this.oNaviList_VKOPF);
			
			try
			{
				oSaveMask.doSaveMask(true);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim speichern !!")));
			}
		}
		
	}


	
}
