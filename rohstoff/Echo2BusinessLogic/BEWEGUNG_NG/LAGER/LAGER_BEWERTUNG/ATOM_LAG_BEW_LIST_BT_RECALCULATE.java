package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;


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
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerBewegungHandler;

/**
 * 
 * @author manfred
 * @date 21.05.2015
 * Fehler: Berechnet Daten im Alten Lager-Bestand und muss ggf. angepasst werden.
 * rausgenommen!!!!
 */
@Deprecated
public class ATOM_LAG_BEW_LIST_BT_RECALCULATE extends MyE2_Button
{

	/**
	 * 
	 */
	private static final long 						serialVersionUID = 6572104782005909594L;
	private ATOM_LAG_Mengenermittlung_ext 				oME = null;
	private ATOM_LAG_BEW_LIST_Panel_MengenErmittlung 	oPanelParent = null;
	private E2_NavigationList 							oNavigationList = null;

	
	private MyE2_Column  								oColLoopInfo = new MyE2_Column();
	
	
	public ATOM_LAG_BEW_LIST_BT_RECALCULATE(E2_NavigationList onavigationList,
										ATOM_LAG_Mengenermittlung_ext oMengenErmittlung,
										ATOM_LAG_BEW_LIST_Panel_MengenErmittlung oPanel)
	{
		//super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		super("Lagerbewegungen verbuchen");
		this.oPanelParent = oPanel;
		this.oME = oMengenErmittlung;
		this.oNavigationList = onavigationList;
		this.setToolTipText(new MyE2_String("Lagerausgänge neu verbuchen.").CTrans());
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LAG_BEW_RECALCULATE"));
	
	}
	

	private class ownActionAgent extends XX_ActionAgent
	{
		public ownActionAgent() {
			// TODO Auto-generated constructor stub
			super();
		}

		
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {
			ATOM_LAG_BEW_LIST_BT_RECALCULATE oThis = ATOM_LAG_BEW_LIST_BT_RECALCULATE.this;
			
			new ownPushContainer();
			
//			//super.executeAgentCode(execInfo);
//			LAG_LagerPreisHandler oPreise = new LAG_LagerPreisHandler(null);
//			oPreise.ReorganisePriceEntries();
//			oPreise.executeSqlStatements(true);
//
//			// DIE MENGEN VERTEILEN
//			LAG_LagerBewegungHandler oBewegung = new LAG_LagerBewegungHandler();
//			oBewegung.ReorganiseLagerEntries();
//			
//			// löschen der Mengen-/Preisermittlungen
//			if (oThis.oME != null){
//				oThis.oME.clearAuswahlListe();
//			}
//			
//			if (oThis.oPanelParent != null){
//				oThis.oPanelParent.setErgebnis("");
//			}
//			
//			oThis.oNavigationList._REBUILD_ACTUAL_SITE("");
//			
//			
//			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Die Verbuchung der getätigten Lagerbewegungen wurde abgeschlossen.")));
//
		}
	}
	

	
	private class ownPushContainer extends E2_ServerPushMessageContainer
	{

		public ownPushContainer()
		{
			super(new Extent(350), new Extent(200), new MyE2_String("Lagerbewegung verbuchen "), true, false,false, 4000);
			
			this.get_oGridBaseForMessages().add(ATOM_LAG_BEW_LIST_BT_RECALCULATE.this.oColLoopInfo,  E2_INSETS.I_10_10_10_10);
			
		}

		@Override
		public void Run_Loop() throws myException
		{
			ATOM_LAG_BEW_LIST_BT_RECALCULATE oThis = ATOM_LAG_BEW_LIST_BT_RECALCULATE.this;
			
//			LAG_LagerPreisHandler oPreise = new LAG_LagerPreisHandler(oThis.oColLoopInfo);
//			oPreise.ReorganisePriceEntries(bibALL.get_ID_MANDANT());
			
			
			// DIE MENGEN VERTEILEN
			LAG_LagerBewegungHandler oBewegung = new LAG_LagerBewegungHandler(oThis.oColLoopInfo);
			oBewegung.ReorganiseLagerEntries(bibALL.get_ID_MANDANT());
			
			// löschen der Mengen-/Preisermittlungen
			if (oThis.oME != null){
				oThis.oME.clearAuswahlListe();
			}
			
			if (oThis.oPanelParent != null){
				oThis.oPanelParent.setErgebnis("");
			}
			
			oThis.oNavigationList._REBUILD_ACTUAL_SITE("");

			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Die Verbuchung der getätigten Lagerbewegungen wurde abgeschlossen.")));

		
		}

		
		@Override
		public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
		{
		}

	}

	
	
}
