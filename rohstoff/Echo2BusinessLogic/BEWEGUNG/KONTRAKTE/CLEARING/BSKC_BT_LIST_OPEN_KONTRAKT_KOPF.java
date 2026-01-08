package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_DB_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class BSKC_BT_LIST_OPEN_KONTRAKT_KOPF extends E2_DB_BUTTON_OPEN_MASK_FromID
{
	
	private String 				    		cEK_VK = null;
	private E2_BasicModuleContainer_MASK 	oModulContainerMASK_EK = null;
	private E2_BasicModuleContainer_MASK 	oModulContainerMASK_VK = null;
	

	public BSKC_BT_LIST_OPEN_KONTRAKT_KOPF(		SQLFieldMAP 					osqlFieldGroup, 
												E2_BasicModuleContainer_MASK 	ModulContainerMASK_EK,
												E2_BasicModuleContainer_MASK 	ModulContainerMASK_VK,
												String EK_VK) throws myException
	{
		super(	osqlFieldGroup.get_("K_ID_VKOPF_KON"), 
				EK_VK.equals("EK")?ModulContainerMASK_EK:ModulContainerMASK_VK,
				EK_VK.equals("EK")?new MyE2_String("EK-Kontrakt"):new MyE2_String("VK-Kontrakt"), 
				null,
				"KONTRAKT_KOPF_BEARBEITEN", 
				"KONTRAKT_KOPF_ANSICHT");
		
		this.cEK_VK = EK_VK;
		this.oModulContainerMASK_EK = ModulContainerMASK_EK;
		this.oModulContainerMASK_VK = ModulContainerMASK_VK;
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery(	"JT_VKOPF_KON",
				"  NVL(ABGESCHLOSSEN,'N'),  NVL(DELETED,'N')",
				bibALL.get_Array("N","N"),
				true, new MyE2_String("Bearbeiten nur erlaubt bei Kontrakten, die NICHT abgeschlossen und NICHT geloescht sind !")));
		
	}

	
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		BSKC_BT_LIST_OPEN_KONTRAKT_KOPF oButtCopy = null;
		
		try
		{
			oButtCopy = new BSKC_BT_LIST_OPEN_KONTRAKT_KOPF(	this.EXT_DB().get_oSQLFieldMAP(),
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
			throw new myExceptionCopy("BSKC_BT_LIST_OPEN_KONTRAKT_KOPF_VK:get_Copy:Copy-Error!");
		}
		return oButtCopy;
	}

}
