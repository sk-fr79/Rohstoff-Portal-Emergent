package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_DB_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.BSK__CONST;

public class BSKC_BT_LIST_OPEN_KONTRAKT_POS extends E2_DB_BUTTON_OPEN_MASK_FromID
{
	
	private String 				    		cEK_VK = null;
	private E2_BasicModuleContainer_MASK 	oModulContainerMASK_EK = null;
	private E2_BasicModuleContainer_MASK 	oModulContainerMASK_VK = null;
	

	public BSKC_BT_LIST_OPEN_KONTRAKT_POS(		SQLFieldMAP 					osqlFieldGroup, 
												E2_BasicModuleContainer_MASK 	ModulContainerMASK_EK,
												E2_BasicModuleContainer_MASK 	ModulContainerMASK_VK,
												String 							EK_VK) throws myException
	{
		super(	osqlFieldGroup.get_("ID_VPOS_KON"), 
				EK_VK.equals("EK")?ModulContainerMASK_EK:ModulContainerMASK_VK,
				EK_VK.equals("EK")?new MyE2_String("EK-Kontrakt-Position"):new MyE2_String("VK-Kontrakt-Position"), 
				null,
				"KONTRAKT_POS_BEARBEITEN", 
				"KONTRAKT_POS_ANSICHT");
		
		this.oModulContainerMASK_EK = ModulContainerMASK_EK;
		this.oModulContainerMASK_VK = ModulContainerMASK_VK;
		
		this.cEK_VK = EK_VK;

		this.add_IDValidator(BSK__CONST.VALID_VPOS_KON_POSITION_OFFEN);
		this.get_vActionsBeforeExecute().add(new actionSetRestrictionID_KOPF());
		
	}

	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		BSKC_BT_LIST_OPEN_KONTRAKT_POS oButtCopy = null;
		
		try
		{
			oButtCopy = new BSKC_BT_LIST_OPEN_KONTRAKT_POS(	this.EXT_DB().get_oSQLFieldMAP(),
																this.oModulContainerMASK_EK,
																this.oModulContainerMASK_VK,
																this.cEK_VK
																);
			oButtCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButtCopy));
			if (this.getIcon() != null)
				oButtCopy.setIcon(this.getIcon());
			
			if (this.get_oText() != null)
				oButtCopy.set_Text(this.get_oText());
			
			oButtCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oButtCopy));
			
			oButtCopy.setStyle(this.getStyle());
			oButtCopy.setInsets(this.getInsets());
			oButtCopy.get_vActionAgentsAfterCancel().addAll(this.get_vActionAgentsAfterCancel());
			oButtCopy.get_vActionAgentsAfterSave().addAll(this.get_vActionAgentsAfterSave());

			
			
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("BSKC_BT_LIST_OPEN_KONTRAKT_POS_VK:get_Copy:Copy-Error!");
		}
		return oButtCopy;
	}

	
	
	/*
	 * actionAgent um das id_kopf-restriction-feld zu fuellen
	 */
	public class actionSetRestrictionID_KOPF extends E2_DB_BUTTON_OPEN_MASK_FromID.X_ActionBeforeExecuteButton
	{
		@Override
		public void do_Action(String cID_VPOS_KON) throws myException
		{
			RECORD_VPOS_KON  					recVRecord_vpos_kon = 	new RECORD_VPOS_KON(cID_VPOS_KON);
				
			BSKC_BT_LIST_OPEN_KONTRAKT_POS		oThis = 				BSKC_BT_LIST_OPEN_KONTRAKT_POS.this; 
			
			((SQLFieldForRestrictTableRange)oThis.oModulContainerMASK_EK.
						get_ComponentMAP_FROM_TABLE("JT_VPOS_KON").get_oSQLFieldMAP().get_("ID_VKOPF_KON")).
							set_cRestrictionValue_IN_DB_FORMAT(recVRecord_vpos_kon.get_ID_VKOPF_KON_cUF());
			
			((SQLFieldForRestrictTableRange)oThis.oModulContainerMASK_VK.
					get_ComponentMAP_FROM_TABLE("JT_VPOS_KON").get_oSQLFieldMAP().get_("ID_VKOPF_KON")).
						set_cRestrictionValue_IN_DB_FORMAT(recVRecord_vpos_kon.get_ID_VKOPF_KON_cUF());
		
			
			
		}
		
	}
	

}
