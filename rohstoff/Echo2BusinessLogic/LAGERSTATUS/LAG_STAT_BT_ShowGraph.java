package rohstoff.Echo2BusinessLogic.LAGERSTATUS;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class LAG_STAT_BT_ShowGraph extends MyE2_Button
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3992061515807555980L;
	private E2_NavigationList m_navigationList = null;
	private LAG_STAT_LIST_Selector m_Selector = null;
	
	public LAG_STAT_BT_ShowGraph(E2_NavigationList onavigationList,LAG_STAT_LIST_Selector oSelector)
	{
		super(E2_ResourceIcon.get_RI("graph.png") , E2_ResourceIcon.get_RI("leer.png"));
		m_navigationList = onavigationList;
		m_Selector = oSelector;
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LAG_STAT_SHOW_GRAPH"));
		
		this.add_GlobalValidator(new XX_ActionValidator() {
			
			@Override
			protected MyE2_MessageVector isValid(String cID_Unformated)
					throws myException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)
					throws myException {
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				LAG_STAT_BT_ShowGraph oThis = LAG_STAT_BT_ShowGraph.this;
				
				if (oThis.m_Selector != null){
					if (bibALL.isEmpty(m_Selector.getSelectedLager()) || bibALL.isEmpty(m_Selector.getSelectedSorte())){
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Für die Grafik muss Lager und Sorte ausgewählt werden.")));
					}
				} else {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Allgemeiner Fehler: kein Selektor vorhanden!")));
				}
				
				return oMV;
			}
		});
		
	}

	
	
	//private class ownActionAgent extends ButtonActionAgentNEW
	private class ownActionAgent extends XX_ActionAgent
	{

		public ownActionAgent(E2_NavigationList onavigationList,  MyE2_Button oownButton)
		{
			super();
		}

		
		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ListAndMask.List.ActionAgents.XX_ButtonActionAgent_FromListToMask#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			new LAG_STAT_DisplayGraph(m_navigationList);
		}

		
		
	}
	
}
