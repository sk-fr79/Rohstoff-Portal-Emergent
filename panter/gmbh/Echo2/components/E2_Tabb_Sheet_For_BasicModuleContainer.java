package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.E2_complex_object_resetter_INTERFACE;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.basics4project.CallStringAnalyser;
import panter.gmbh.basics4project.E2_TabbedPaneForFirstContainer;
import panter.gmbh.indep.exceptions.myException;
import echopointng.tabbedpane.DefaultTabModel;


/*
 * klasse, die enthaelt:
 * 		MyE2_Row					mit Label und closebutton fuer die darstellung im tab
 * 		E2_BasicModuleContainer		mit der anwendung/dem modul, das im jeweiligen tab dargestellt wird
 */
public class E2_Tabb_Sheet_For_BasicModuleContainer
{
	private MyE2_Row								oRowLabelButton = 			new MyE2_Row();
	private MyE2_Button								oButtonCloseTab			= 	new MyE2_Button(E2_ResourceIcon.get_RI("exit.png"));
	private MyE2_Button								oButtonTAB	= 				null;
	private E2_TabbedPaneForFirstContainer			oTabbedPaneThisBelongsTo = 	null;
	private E2_BasicModuleContainer					oModuleContainer = 			null;
	private E2_BasicModuleContainer  				oModuleContainerDummy =     new ownDummyContainer();
	private String   								cKompletterAufrufString = 	null;
	
	private CallStringAnalyser   					callAnalyser = null;
	
	public E2_Tabb_Sheet_For_BasicModuleContainer(	CallStringAnalyser   					call_Analyser,
													E2_TabbedPaneForFirstContainer 			oTabbedPane,
													boolean   								bActiveAtGenerate) throws myException {
		this.callAnalyser = call_Analyser;
		
		this.oTabbedPaneThisBelongsTo = oTabbedPane;
		
		this.cKompletterAufrufString = this.callAnalyser.get_completeCallStringWithUserInfo();
		
		RowLayoutData oRowLayout = new RowLayoutData();
		oRowLayout.setInsets(new Insets(2,0,2,0));
		
		this.oRowLabelButton = new MyE2_Row();
		this.oButtonTAB = new MyE2_Button(call_Analyser.get_userText());
		this.oButtonTAB.set_COLORS(null,Color.BLACK,null,Color.DARKGRAY);

		this.oRowLabelButton.add(this.oButtonTAB);
		this.oRowLabelButton.add(this.oButtonCloseTab);
		this.oButtonCloseTab.setLayoutData(oRowLayout);
		
		//2012-02-02:  neuer style
		this.oButtonTAB.setStyle(MyE2_Button.StyleTextButtonWithClickForeground(false));
		this.oButtonTAB.setLayoutData(oRowLayout);
		
		if (bActiveAtGenerate) {
			this.build_ModuleContainer();
		}
		
		this.oButtonCloseTab.add_oActionAgent(new ownActionAgentCloseTab());

		this.oButtonTAB.add_oActionAgent(new own_TabbedPane_For_BasicModuleContainer_SwitchAgent(this.cKompletterAufrufString));
		
	}
	

	
	
	
	
	
	/**
	 * 
	 * @return entweder den dummy-container oder den (nach aktivierung) richtigen Container
	 */
	public E2_BasicModuleContainer get_oModuleContainer()
	{
		if (this.oModuleContainer!=null) {
			return this.oModuleContainer;
		} else {
			return this.oModuleContainerDummy;
		}
	}



	public MyE2_Row get_oRowLabelButton()
	{
		return oRowLabelButton;
	}



	public String get_cKompletterAufrufString()
	{
		return cKompletterAufrufString;
	}



	private class ownDummyContainer extends E2_BasicModuleContainer {

		public ownDummyContainer() {
			super();
			
			this.add(new MyE2_Label("...... ich bin am Laden der Module .... ", MyE2_Label.STYLE_TITEL_BIG()), E2_INSETS.I(0,0,0,0));
		}
	}


	public void build_ModuleContainer() throws myException {
		if (this.oModuleContainer==null && this.callAnalyser !=null) {
			this.oModuleContainer = this.callAnalyser.generate_ThisContainer();
			this.oModuleContainer.set_bIsStartContainer_4_DBTimeStamps(true);	//nur diese container werden als startcontainer definiert
		}
	}


	public boolean get_bActive() {
		return (this.oModuleContainer!=null);
	}

	
	
	
	/**
	 * 2011-12-06:
	 * neue version mit beruecksichtigung der speicherfunktion
	 * @author martin
	 *
	 */
	private class ownActionAgentCloseTab extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			DefaultTabModel 				oTabModel = 	(DefaultTabModel)E2_Tabb_Sheet_For_BasicModuleContainer.this.oTabbedPaneThisBelongsTo.getModel();
			E2_TabbedPaneForFirstContainer 	oTabbedPane = 	E2_Tabb_Sheet_For_BasicModuleContainer.this.oTabbedPaneThisBelongsTo;
			
			for (int i=0;i<oTabModel.size();i++)
			{
				if (oTabModel.getTabContentAt(i) == E2_Tabb_Sheet_For_BasicModuleContainer.this.get_oModuleContainer())
				{
					//2011-12-06:  wenn der modulcontainer das interface E2_complex_object_resetter_INTERFACE erfuellt,
					//             dann die naviliste resetten
					if (E2_Tabb_Sheet_For_BasicModuleContainer.this.get_oModuleContainer() instanceof E2_complex_object_resetter_INTERFACE)
					{
						((E2_complex_object_resetter_INTERFACE)E2_Tabb_Sheet_For_BasicModuleContainer.this.get_oModuleContainer()).get_Object_resetter().clean_list();
					}
					
					
					//2015-04-20: was passiert, wenn ein tab geschlossen wird, dazu statusfestlegung
					int iCountTabs = oTabbedPane.get_vTabbs().size();
					int iPosToDel = i;
					int iPosActual = oTabbedPane.getSelectedIndex();
					//jetzt nachsehen, ob eines geladen werden muss 
					if (iPosToDel==iPosActual) {
						//dann muss der rechts daneben oder (wenn es der letzte ist) der links daneben geladen werden
						if (iPosToDel==(iCountTabs-1)) {  		//es ist der ganz rechts aktive
							if ((iCountTabs-2)>=0) {           	//sonst wird der letzte geschlossen
								oTabbedPane.get_vTabbs().get(iCountTabs-2).build_ModuleContainer();
								oTabbedPane.reload_own_Tabs(null);
							}
						} else {
							if ((iPosToDel+1)<iCountTabs) {    //der 1 rechts rueckt an die freie stelle und muss geoffnet werden 
								oTabbedPane.get_vTabbs().get(iPosToDel+1).build_ModuleContainer();
								oTabbedPane.reload_own_Tabs(null);
							}
						}
					}
					//-------
					
					
					
					/*
					 * die karteikarte und die entsprechung im vector des tabbedpanes entfernen
					 */
					oTabModel.removeTabAt(i);
					E2_Tabb_Sheet_For_BasicModuleContainer.this.oTabbedPaneThisBelongsTo.get_vTabbs().remove(i);
					
					
					if (oTabModel.size()==0) {
						E2_Tabb_Sheet_For_BasicModuleContainer.this.oTabbedPaneThisBelongsTo.setVisible(false);
					} else {
						// falls ein anderer weiter links entfernt wird, dann wird "umpositioniert", d.h. die nummmer des aktiven bleibt gleich, aber nicht der tab,
						// deshalb nachpositionieren
						if (iPosToDel<iPosActual) {
							//dann wieder auf den vorigen inhalt stellen (der bereits aktiv war)
							oTabbedPane.setSelectedIndex(iPosActual-1);
						}
					}
						
					break;
				}
			}
		}
		
	}


	/**
	 * 2015-04-16: switch-agent als intrinsic-class definiert
	 */
	public class own_TabbedPane_For_BasicModuleContainer_SwitchAgent extends XX_ActionAgent 
	{
		private String cKompletterAufrufString = null;
		
		public own_TabbedPane_For_BasicModuleContainer_SwitchAgent(String KompletterAufrufString) 
		{
			super();
			this.cKompletterAufrufString = KompletterAufrufString;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			
			Vector<E2_Tabb_Sheet_For_BasicModuleContainer>  vTabs = bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().get_vTabbs();
			
			for (int i=0;i<vTabs.size();i++)
			{
				if (vTabs.get(i).get_cKompletterAufrufString().equals(this.cKompletterAufrufString))   //gefunden
				{
					if (!vTabs.get(i).get_bActive()) {        //das E2_Tabb_Sheet_For_BasicModuleContainer aktiv schalten und alles neu aufbauen
						vTabs.get(i).build_ModuleContainer();            //hier wird der eigentliche E2_BasicModulContainer gebaut
						bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().reload_own_Tabs(new Integer(i));
					} else {
						bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().setSelectedIndex(i);
					}
					break;
				}
			}
			
			
		}

	}


	public CallStringAnalyser get_CallAnalyser() {
		return callAnalyser;
	}




	
}
