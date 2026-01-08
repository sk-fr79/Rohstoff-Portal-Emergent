package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public class FUO_LIST_BT_EDIT extends MyE2_ButtonWithKey
{
	private FU_DAUGHTER_ORTE  				oFUO_DaughterComponent = null;

	public FUO_LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException
	{
		super(E2_ResourceIcon.get_RI("edit.png") , E2_ResourceIcon.get_RI("leer.png"),KeyStrokeListener.VK_F4);

		this.oFUO_DaughterComponent = FUO_DaugherComponent;
	
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this.get_oButton()));
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BEARBEITE_FUHRENZUSATZORT"));
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_TPA_FUHRE_ORT","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Der Vorgang wurde bereits gelöscht !")));
	}
	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton) throws myException
		{
			super(new MyE2_String("Bearbeiten eines zusätzliche Fuhrenortes"), onavigationList, omaskContainer, oownButton, null, null);
			this.get_oButtonMaskSave().add_oActionAgent(new ownActionReloadFuhrenMaske());
			this.get_oButtonMaskSave().add_oActionAgent(new ownActionReloadBasisNaviLists());
		}
	}
	
	public FU_DAUGHTER_ORTE get_oFUO_DaughterComponent()
	{
		return oFUO_DaughterComponent;
	}
	
	
	private class ownActionReloadFuhrenMaske extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_BasicModuleContainer_MASK  oMaskFuhre = FUO_LIST_BT_EDIT.this.oFUO_DaughterComponent.EXT().get_oComponentMAP().get_oModulContainerMASK_This_BelongsTo();
			E2_ComponentMAP   			  oMapFuhre = FUO_LIST_BT_EDIT.this.oFUO_DaughterComponent.EXT().get_oComponentMAP();
			

			
			if (oMaskFuhre == null)
			{
				throw new myException(this,"Cannot save changed fuhrenmask !");
			}
			oMapFuhre.update_ResultMAP_and_MaskComponents(null);

			
			
			
			E2_ComponentMAP 			oLeadingMaskMAP = 				oMaskFuhre.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN();
			/*
			 * !!!!!!!!!!!!!!!!
			 * die maske muss hier unbedingt geladen werden, damit neu eingetragene abzuege nicht im status NEW mehrmals gespeichert werden
			 * !!!!!!!!!!!!!!!!
			 * 
			 * 
			 * je nachdem, ob der vorgang "Fuhrenort-speichern" im Transportauftrag oder der fuhre stattfindet, muss die maske mit dem jeweils
			 * fuehrenden id-wert geladen werden
			 */
			String cID_FUHRE_oder_tpa_pos = oLeadingMaskMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			oMaskFuhre.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,cID_FUHRE_oder_tpa_pos);

			
		}
		
	}

	/*
	 * actionagent, der alle ausgangsnavilists ausser der in der maske eingebetteten fuhrenort-liste refresht
	 */
	private class ownActionReloadBasisNaviLists extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			Vector<E2_NavigationList> oNavis = new E2_RecursiveSearch_NavigationList().get_vNavigationLists();
			for (int i=0;i<oNavis.size();i++)
			{
				if (!oNavis.get(i).get_oComponentMAP__REF().get_oSQLFieldMAP().get_cMAIN_TABLE().equals("JT_VPOS_TPA_FUHRE_ORT"))
				{
					oNavis.get(i)._REBUILD_ACTUAL_SITE(null);
				}
			}
		}
	}


	
	
}
