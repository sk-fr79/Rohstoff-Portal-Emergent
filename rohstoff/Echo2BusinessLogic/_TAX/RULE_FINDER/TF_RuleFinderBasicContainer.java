package rohstoff.Echo2BusinessLogic._TAX.RULE_FINDER;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;


/**
 * klasse sucht alle fuhren durch und definiert die steuerdefinitions-saetze ohne steuern
 * @author martin
 *
 */ 
public class TF_RuleFinderBasicContainer extends Project_BasicModuleContainer
{

	private static int[]  	iSpalten = {200,100};
	private MyE2_Grid  		gridInhalt = new MyE2_Grid(iSpalten, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	private MyE2_CheckBox  	oCB_ErsetzeLeereVerantwortung_DurchMandant = new MyE2_CheckBox(true, false);
	private MyE2_Button  	oBT_StarteSuchen = new MyE2_Button(new MyE2_String("Start"),MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN());
	
	public TF_RuleFinderBasicContainer() throws myException
	{
		super(E2_MODULNAMES.MODUL_TAXRULES_FINDER);
		
		this.add(this.gridInhalt, E2_INSETS.I(5,10,2,2));
		
		//layout der maske
		this.gridInhalt.add(new MyE2_Label(new MyE2_String("Behandle Fuhren mit undefinierter Transportverantwortung wie Mandanten-Fuhren")), E2_INSETS.I(0,5,10,5));
		this.gridInhalt.add(this.oCB_ErsetzeLeereVerantwortung_DurchMandant, E2_INSETS.I(0,5,10,5));
		
		this.gridInhalt.add(new E2_ComponentGroupHorizontal(0, this.oBT_StarteSuchen,E2_INSETS.I_0_0_0_0), 2, E2_INSETS.I(0,5,10,5));
		
		this.oBT_StarteSuchen.add_oActionAgent(new ownActionBuildRules());
	}

	
	private class ownActionBuildRules extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			new E2_ServerPushMessageContainer(new Extent(800),new Extent(250),new MyE2_String("Aufbau der Regeln läuft ..."),true,false,true,4000)
			{
				@Override
				public void Run_Loop() throws myException {
					new TF_RuleFinder(this.get_oGridBaseForMessages(),TF_RuleFinderBasicContainer.this.oCB_ErsetzeLeereVerantwortung_DurchMandant.isSelected(), this);
				}

				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException {
				}
			};
		}
	}
	
	
	
}
