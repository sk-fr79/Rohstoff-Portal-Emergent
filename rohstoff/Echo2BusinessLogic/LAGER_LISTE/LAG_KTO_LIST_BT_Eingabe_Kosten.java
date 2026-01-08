package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN.FUK__Button_Eingabe_Kosten;

public class LAG_KTO_LIST_BT_Eingabe_Kosten extends FUK__Button_Eingabe_Kosten
{
	
	
	public LAG_KTO_LIST_BT_Eingabe_Kosten()
	{
		super();
	}

	
	@Override
	public String get_cID_VPOS_TPA_FUHRE_UF() throws myException
	{
		String cID_VPOS_TPA_FUHRE = null;
		
		String cID_LAGER_KTO = this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
		
		RECORD_LAGER_KONTO  recLagerKto = new RECORD_LAGER_KONTO(cID_LAGER_KTO);
		
		if (recLagerKto.get_ID_VPOS_TPA_FUHRE_lValue(new Long(-1))>0)
		{
			cID_VPOS_TPA_FUHRE = recLagerKto.get_ID_VPOS_TPA_FUHRE_cUF();
		}
		return cID_VPOS_TPA_FUHRE;
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		LAG_KTO_LIST_BT_Eingabe_Kosten oButton = new LAG_KTO_LIST_BT_Eingabe_Kosten();

		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		if (this.get_oText()!=null) oButton.set_Text(this.get_oText());
		oButton.__setImages(this.get_oImgEnabled(),this.get_oImgDisabled());
		try
		{
			oButton.set_bEnabled_For_Edit(this.isEnabled());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
		
		oButton.setStyle(this.getStyle());
		
		if (this.getToolTipText() != null)
		{
			oButton.setToolTipText(this.getToolTipText());
		}
		
		return oButton;
	}


	@Override
	public XX_ActionAgentWhenCloseWindow get_ActionAgent4Close_Kosten_Window(E2_BasicModuleContainer oContainerToClose) throws myException
	{
		return new ownCloseAction(oContainerToClose);
	}

	
	//hier spezieller agent, der die zeile neu aufbaut nach einer eingabe
	private class ownCloseAction extends XX_ActionAgentWhenCloseWindow
	{
		public ownCloseAction(E2_BasicModuleContainer container)
		{
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			LAG_KTO_LIST_BT_Eingabe_Kosten.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
		}
	}	
	
	
}
