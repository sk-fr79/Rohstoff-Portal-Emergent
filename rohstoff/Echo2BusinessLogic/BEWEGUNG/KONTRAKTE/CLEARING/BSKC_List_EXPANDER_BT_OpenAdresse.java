package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.exceptions.myException;

public class BSKC_List_EXPANDER_BT_OpenAdresse extends E2_BUTTON_OPEN_MASK_FromID
{
	private BSKC_List_EXPANDER_4_ComponentMAP_Gengen_Kontrakt ownExpander = null;
	
	public BSKC_List_EXPANDER_BT_OpenAdresse(BSKC_List_EXPANDER_4_ComponentMAP_Gengen_Kontrakt oExpander, String cID_to_open) throws myException
	{
		super(	oExpander.get_oBSKC_ModulContainerLIST().get_oFirmenMASK(),
				new MyE2_String("Adresse"), 
				null,	
				"ADRESSE_BEARBEITEN", 
				"ADRESSE_ANSICHT");
		
		this.ownExpander = oExpander;
		
		this.EXT().set_C_MERKMAL(cID_to_open);
		this.setText(MyNumberFormater.formatDez(new Long(cID_to_open), true, '.'));
		
		this.get_vActionAgentsAfterSave().add(new actionAfterSave());
		this.get_vActionAgentsAfterCancel().add(new actionAfterSave());

	}

	private class actionAfterSave extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSKC_List_EXPANDER_BT_OpenAdresse.this.ownExpander.refreshDaughterComponent();
		}
	}

	
	
	@Override
	public void put_SpecialButtonsToRowForButtons_EDIT(MyE2_Row rowForButtons)	throws myException
	{
	}

	@Override
	public void put_SpecialButtonsToRowForButtons_VIEW(MyE2_Row rowForButtons)	throws myException
	{

	}

}
