/**
 * 
 */
package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.XX_MessageManipulator;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.E2_PopupWithButtonCascading;
import panter.gmbh.Echo2.components.E2_PopupWithButtonCascading.IfActionAgentFactory;
import panter.gmbh.Echo2.components.E2_PopupWithButtonCascading.MenueEntry;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public abstract class RB_SelFieldAsCascadingPopup extends E2_Grid implements IF_RB_Component_Savable, E2_IF_Handles_ActionAgents {
	
	private VEK<MenueEntry> 			vMenues = null;
	
	private RB_TextField   				tf = new RB_TextField();
	private E2_PopupWithButtonCascading popupCascade = null;
	
	private String 						actualValue = null;
	
	public abstract void 				executeClick(MenueEntry entry) throws myException;
	public abstract VEK<MenueEntry> 	generateMenueStructur() throws myException;

	private VEK<E2_Button>  			allRealEndpointButtons = new VEK<E2_Button>();
	private VEK<MenueEntry>  			allRealEndpointEntrys = new VEK<MenueEntry>();

	private RB_KF Key = null;
	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();

	
	/**
	 * 
	 * 
	 * @param osqlField (can be null)
	 * @param iTextFieldLen = breite des textfeldes zur anzeige, wenn null, dann wird kein textfeld angezeigt 
	 * @throws myException
	 */
	public RB_SelFieldAsCascadingPopup(Integer iTextFieldLen, Integer p_popup_width) throws myException {
		super();
		
		this.popupCascade =new E2_PopupWithButtonCascading(S.ms(""),new ownActionFactory(), p_popup_width)._init();
		
		if (iTextFieldLen!=null) {
			this.tf.setEnabled(false);  //nur ueber den popup zu bedienen
			this.tf.setWidth(new Extent(iTextFieldLen));
			this._setSize(iTextFieldLen,25);
			this._a(this.tf, new RB_gld()._ins(0, 0, 3, 0))._a(this.popupCascade, new RB_gld()._ins(0, 0, 0, 0));
		} else {
			this._setSize(25);
			this._a(this.popupCascade, new RB_gld()._ins(0, 0, 0, 0));
		}
		
		this.vMenues = this.generateMenueStructur();
		

		this.popupCascade._populate(this.vMenues);
		
		this.allRealEndpointButtons._a(this.popupCascade.getRealMenueButtons());
		this.allRealEndpointEntrys._a(this.popupCascade.getRealMenueButtonEntrys());
		
	}



	


	public VEK<MenueEntry> getMenues() {
		return vMenues;
	}

	
	

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//block fuer die implementierung des Interfaces: E2_IF_Handles_ActionAgents
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void add_IDValidator(XX_ActionValidator oValid) {
		for (E2_Button b: this.allRealEndpointButtons) {
			b.add_IDValidator(oValid);
		}
	}
	
	
	@Override
	public void add_GlobalValidator(Vector<XX_ActionValidator> vValid) {
		for (E2_Button b: this.allRealEndpointButtons) {
			b.add_GlobalValidator(vValid);
		}
	}

	
	@Override
	public void add_IDValidator(Vector<XX_ActionValidator> vValid){
		for (E2_Button b: this.allRealEndpointButtons) {
			b.add_IDValidator(vValid);
		}
	}

	@Override
	public MyE2_MessageVector valid_GlobalValidation() throws myException {
		throw new myException(this,"Function valid_GlobalValidation() Not implemented");
	}

	
	
	@Override
	public MyE2_MessageVector valid_IDValidation(Vector<String> vID_Unformated) throws myException	{
		throw new myException(this,"Function valid_GlobalValidation() Not implemented");
	}


	@Override
	public void add_oActionAgent(XX_ActionAgent actionAgent) {
		for (E2_Button b: this.allRealEndpointButtons) {
			b.add_oActionAgent(actionAgent);
		}
	}

	
	@Override
	public void add_oActionAgent(XX_ActionAgent actionAgent, boolean bInFront) {
		for (E2_Button b: this.allRealEndpointButtons) {
			b.add_oActionAgent(actionAgent, bInFront);
		}
	}

	
	@Override
	public void add_oActionAgent(Vector<XX_ActionAgent> vActionAgent, boolean bInFront){
		for (E2_Button b: this.allRealEndpointButtons) {
			b.add_oActionAgent(vActionAgent, bInFront);
		}
	}
	
	
	
	@Override
	public void remove_oActionAgent(XX_ActionAgent actionAgent) {
		for (E2_Button b: this.allRealEndpointButtons) {
			b.remove_oActionAgent(actionAgent);
		}
	}

	
	@Override
	public void remove_AllActionAgents() {
		for (E2_Button b: this.allRealEndpointButtons) {
			b.remove_AllActionAgents();
		}
	}

	
	
	
	@Override
	public Vector<XX_ActionAgent> get_vActionAgents() {
		Vector<XX_ActionAgent> v = new Vector<XX_ActionAgent>();
		for (E2_Button b: this.allRealEndpointButtons) {
			v.addAll(b.get_vActionAgents());
			break;
		}
		return v;
	}

	

	@Override
	public void doActionPassiv() {
	}


	@Override
	public boolean get_bIsPassivAction() {
		return false;
	}


	@Override
	public void set_bPassivAction(boolean bPassiv) {
	}

	
	//2011-09-30: neue methode im interface fuer das passive abarbeiten eine Button-codes innerhalb eines 
	//            anderen actionagenten
	@Override
	public void do_OnlyCode_from_OtherActionAgent(MyActionEvent oActionEventOtherActionAgent) throws myException	{
	}

	@Override
	public Vector<XX_ActionAgent> 	get_vInternalActionAgents() {
		return null;
	}

	@Override
	public void add_oInternalActionAgent(XX_ActionAgent actionAgent) {
	}

	
	@Override
	public void add_oInternalActionAgent(XX_ActionAgent actionAgent, boolean bInFront)	{
	}
	
	@Override
	public void remove_oInternalActionAgent(XX_ActionAgent actionAgent) {
	}

	@Override
	public void remove_AllInternalActionAgents()  {
	}

	@Override
	public boolean get_bMustSet_MILLISECONDSTAMP_TO_Session() {
		return false;
	}

	@Override
	public void set_bMustSet_MILLISECONDSTAMP_TO_Session(boolean bMustSet_MILLISECONDSTAMP) {
	}

	@Override
	public void add_GlobalValidator(XX_ActionValidator oValid) {
		for (E2_Button b: this.allRealEndpointButtons) {
			b.add_GlobalValidator(oValid);
		}
	}

	@Override
	public XX_MessageManipulator get_MessageManipulator() {
		XX_MessageManipulator manip=null;
		for (E2_Button b: this.allRealEndpointButtons) {
			manip= b.get_MessageManipulator();
		}
		return manip;
	}

	@Override
	public void set_MessageManipulator(XX_MessageManipulator oManipulator) {
		for (E2_Button b: this.allRealEndpointButtons) {
			b.set_MessageManipulator(oManipulator);
		}
	}

	@Override
	public E2_Break4PopupController getBreak4PopupController() {
		E2_Break4PopupController con = null;
		for (E2_Button b: this.allRealEndpointButtons) {
			con = b.getBreak4PopupController();
		}
		
		return con;
	}
	
	@Override
	public void setBreak4PopupController(E2_Break4PopupController controller) {
		for (E2_Button b: this.allRealEndpointButtons) {
			b.setBreak4PopupController(controller);
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//end block fuer die implementierung des Interfaces: E2_IF_Handles_ActionAgents
	////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	private class ownActionFactory implements IfActionAgentFactory {

		@Override
		public VEK<XX_ActionAgent> getActionAgents(MenueEntry entry) throws myException {
			return new VEK<XX_ActionAgent>()._a( new OwnAgent(entry));
		}

		private class OwnAgent extends XX_ActionAgent {
			private MenueEntry m_e = null; 
			
			public OwnAgent(MenueEntry e) {
				super();
				this.m_e = e;
			}

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				RB_SelFieldAsCascadingPopup.this.rb_set_db_value_manual(this.m_e.getDbVal());
				RB_SelFieldAsCascadingPopup.this.executeClick(m_e);
			}
			
		}
	}


	public E2_PopupWithButtonCascading getPopupCascade() {
		return popupCascade;
	}
	
	
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(enabled);
		
		this.tf.set_bEnabled_For_Edit(enabled);  //setzt den background
		this.tf.setEnabled(false);   			 //aber immer gesperrt
		this.popupCascade.getButton().set_bEnabled_For_Edit(enabled);
		this.popupCascade.getPopUp().set_bEnabled_For_Edit(enabled);
	}
	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.rec20().is_newRecordSet()) {
			this.rb_set_db_value_manual("");
		} else {
			//falls der wert nicht im hm_content_real ist, dann in den shadow reinschreiben
			String value = S.NN(((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME()));
			this.rb_set_db_value_manual(value);
		}
	}
	
	
	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.actualValue = valueFormated;
		
	}
	


	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	
	
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}
	
	
	@Override
	public void mark_Neutral() throws myException {
	}
	
	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.actualValue;
	}

	
}
