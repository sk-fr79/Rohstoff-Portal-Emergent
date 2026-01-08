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
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ButtonActionAgentMULTIDELETE;

public class FUO_LIST_BT_DELETE extends MyE2_Button
{
	private FU_DAUGHTER_ORTE  				oFUO_DaughterComponent = null;

	public FUO_LIST_BT_DELETE(E2_NavigationList onavigationList, FU_DAUGHTER_ORTE FUO_DaugherComponent)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));

		this.oFUO_DaughterComponent = FUO_DaugherComponent;
	
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_oActionAgent(new ownActionReloadBasisNaviLists());
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LOESCHE_FUHRENZUSATZORT"));
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_TPA_FUHRE_ORT","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Der Vorgang wurde bereits gelöscht !")));

	}
	

	private class ownActionAgent extends BS_ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(onavigationList,"JT_VPOS_TPA_FUHRE_ORT" , true);
			this.get_vWeitereActionsAfterDeleteOK().add(new ownActionReloadFuhrenMaske());

		}
		
		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete)  	{return  new MyE2_MessageVector();}

		@Override
		public void PruefeWeiterLoeschungen(Vector<String> vID_VposTPA_Fuhre_ORT, Vector<String> stack, String loeschInfoText) throws myException
		{
			if (vID_VposTPA_Fuhre_ORT != null && vID_VposTPA_Fuhre_ORT.size()>0)
			{
				for (int i=0;i<vID_VposTPA_Fuhre_ORT.size();i++)
				{
					RECORD_VPOS_TPA_FUHRE_ORT recOrt = new RECORD_VPOS_TPA_FUHRE_ORT(vID_VposTPA_Fuhre_ORT.get(i));
					stack.add("UPDATE JT_VPOS_TPA_FUHRE SET ERZEUGT_VON=ERZEUGT_VON WHERE ID_VPOS_TPA_FUHRE="+recOrt.get_ID_VPOS_TPA_FUHRE_cUF());    //Fuhren-Trigger aktivieren
				}
			}
			
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
			E2_BasicModuleContainer_MASK  oMaskFuhre = FUO_LIST_BT_DELETE.this.oFUO_DaughterComponent.EXT().get_oComponentMAP().get_oModulContainerMASK_This_BelongsTo();
			E2_ComponentMAP   			  oMapFuhre = FUO_LIST_BT_DELETE.this.oFUO_DaughterComponent.EXT().get_oComponentMAP();
			
			if (oMaskFuhre == null)
			{
				throw new myException(this,"Cannot save changed fuhrenmask !");
			}
			oMapFuhre.update_ResultMAP_and_MaskComponents(null);
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
