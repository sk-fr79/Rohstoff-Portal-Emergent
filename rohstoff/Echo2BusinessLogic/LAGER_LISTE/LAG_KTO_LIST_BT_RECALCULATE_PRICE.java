package rohstoff.Echo2BusinessLogic.LAGER_LISTE;


import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerPreisHandler;


public class LAG_KTO_LIST_BT_RECALCULATE_PRICE extends MyE2_Button
{

	/**
	 * 
	 */
	private LAG_KTO_LIST_Summary oPanelParent = null;
	private E2_NavigationList oNavigationList = null;
	
	private MyE2_Column  oColLoopInfo = new MyE2_Column();

	
	public LAG_KTO_LIST_BT_RECALCULATE_PRICE(E2_NavigationList onavigationList,
										LAG_KTO_LIST_Summary oPanel)
	{
		//super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		super("Lagerpreise aktualisieren");
		this.oPanelParent = oPanel;
		this.oNavigationList = onavigationList;
		this.setToolTipText(new MyE2_String("Preise der Lagerpositionen aktualisieren").CTrans());
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LAG_KTO_LIST_RECALCULATE_PRICES"));
	
	}
	

	private class ownActionAgent extends XX_ActionAgent
	{
		public ownActionAgent() {
			// TODO Auto-generated constructor stub
			super();
		}

		
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			
			new ownPushContainer();
			
//			LAG_KTO_LIST_BT_RECALCULATE_PRICE oThis = LAG_KTO_LIST_BT_RECALCULATE_PRICE.this;
//			
//			
//			//super.executeAgentCode(execInfo);
//			LAG_LagerPreisHandler oPreise = new LAG_LagerPreisHandler(null);
//			oPreise.ReorganisePriceEntries(bibALL.get_ID_MANDANT());
//			oPreise.executeSqlStatements(true);
//			
//			oThis.oNavigationList._REBUILD_ACTUAL_SITE("");
//			
//			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Die aktualisierung der Preise wurde abgeschlossen.")));
			
		}

	}
	

	private class ownPushContainer extends E2_ServerPushMessageContainer
	{

		public ownPushContainer()
		{
			super(new Extent(350), new Extent(200), new MyE2_String("Lagerpreise ermitteln..."), true, false,false, 2000);
			
			this.get_oGridBaseForMessages().add(LAG_KTO_LIST_BT_RECALCULATE_PRICE.this.oColLoopInfo,  E2_INSETS.I_10_10_10_10);
		}

		@Override
		public void Run_Loop() throws myException
		{
			LAG_KTO_LIST_BT_RECALCULATE_PRICE oThis = LAG_KTO_LIST_BT_RECALCULATE_PRICE.this;

			//super.executeAgentCode(execInfo);
			LAG_LagerPreisHandler oPreise = new LAG_LagerPreisHandler(oThis.oColLoopInfo);
			oPreise.ReorganisePriceEntries(bibALL.get_ID_MANDANT());
			//oPreise.executeSqlStatements(true);
			
			oThis.oNavigationList._REBUILD_ACTUAL_SITE("");
			
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Die aktualisierung der Preise wurde abgeschlossen.")));

		}
		
		@Override
		public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
		{
		}

	}

	
	
}
