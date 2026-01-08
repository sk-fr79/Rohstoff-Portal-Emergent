package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.components.E2_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class BSKC_List_EXPANDER_BT_OpenKontraktPOS extends E2_BUTTON_OPEN_MASK_FromID
{
	private BSKC_List_EXPANDER_4_ComponentMAP_Gengen_Kontrakt ownExpander = null;

	public BSKC_List_EXPANDER_BT_OpenKontraktPOS(BSKC_List_EXPANDER_4_ComponentMAP_Gengen_Kontrakt oExpander, RECORD_VPOS_KON oRecordVPosKON) throws myException
	{
		super(	oExpander.get_oBSKC_ModulContainerLIST().get_EK_VK().equals("EK")?
				oExpander.get_oBSKC_ModulContainerLIST().get_oKontraktPosMASK_VK():
				oExpander.get_oBSKC_ModulContainerLIST().get_oKontraktPosMASK_EK(),
				new MyE2_String("Kontrakt-Position"), 
				null,	
				"KONTRAKT_POS_BEARBEITEN", 
				"KONTRAKT_POS_ANSICHT");

		this.ownExpander = oExpander;
		
		this.EXT().set_C_MERKMAL(oRecordVPosKON.get_ID_VPOS_KON_cUF());
		if (S.isFull(oRecordVPosKON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_BUCHUNGSNUMMER_cUF()))
		{
			this.setText(oRecordVPosKON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_BUCHUNGSNUMMER_cUF()+"-"+oRecordVPosKON.get_POSITIONSNUMMER_cF_NN(""));
			this.setFont(new E2_FontItalic(-2));
			this.setInsets(E2_INSETS.I_0_1_0_1);
			this.setToolTipText("ID: "+oRecordVPosKON.get_ID_VPOS_KON_cUF());
		}
		else
		{
			this.setText(oRecordVPosKON.get_ID_VPOS_KON_cF());
		}
		
		
		this.get_vActionAgentsAfterSave().add(new actionAfterSave());
		this.get_vActionAgentsAfterCancel().add(new actionAfterSave());


	}

	private class actionAfterSave extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSKC_List_EXPANDER_BT_OpenKontraktPOS.this.ownExpander.refreshDaughterComponent();
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
