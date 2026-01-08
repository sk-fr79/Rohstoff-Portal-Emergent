/**
 * 
 */
package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.IF_Interpreter;
import panter.gmbh.Echo2.XX_MessageManipulator;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.E2_PopupWithButtonCascading;
import panter.gmbh.Echo2.components.E2_PopupWithButtonCascading.IfActionAgentFactory;
import panter.gmbh.Echo2.components.E2_PopupWithButtonCascading.MenueEntry;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public abstract class E2_DB_SelFieldAsCascadingPopup extends E2_Grid implements MyE2IF__DB_Component, E2_IF_Handles_ActionAgents {
	
	private VEK<MenueEntry> 			vMenues = null;
	
	private RB_TextField   				tf = new RB_TextField();
	private E2_PopupWithButtonCascading popupCascade = null;
	
	private MyE2EXT__DB_Component 		oEXTDB=new MyE2EXT__DB_Component(this);
	
	private String 						actualValue = null;
	
	public abstract void 				executeClick(MenueEntry entry) throws myException;
	public abstract VEK<MenueEntry> 	generateMenueStructur() throws myException;

	private VEK<E2_Button>  			allRealEndpointButtons = new VEK<E2_Button>();
	private VEK<MenueEntry>  			allRealEndpointEntrys = new VEK<MenueEntry>();

	
	//2020-11-13: interpreter fuer die exaktere definition der tooltips auf der maske
	private VEK<IF_Interpreter<String, String>> toolTipsInterpreter = new VEK<IF_Interpreter<String, String>>();
	
	/**
	 * 
	 * 
	 * @param osqlField (can be null)
	 * @param iTextFieldLen = breite des textfeldes zur anzeige, wenn null, dann wird kein textfeld angezeigt 
	 * @throws myException
	 */
	public E2_DB_SelFieldAsCascadingPopup(SQLField osqlField, Integer iTextFieldLen, Integer p_popup_width) throws myException {
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
		
		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);

		this.popupCascade._populate(this.vMenues);
		
		this.allRealEndpointButtons._a(this.popupCascade.getRealMenueButtons());
		this.allRealEndpointEntrys._a(this.popupCascade.getRealMenueButtonEntrys());
		
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String numberFormated, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("E2_DB_SelFieldAsCascadingPopup: set_cActual_Formated_DBContent_To_Mask:Status not allowed !");

		this.set_cActualMaskValue(numberFormated);
	}


	
	
	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this.tf.setText("");
		this.actualValue="";
		this.EXT_DB().set_cLASTActualDBValueFormated("");
		
		if (this.EXT_DB().get_oSQLField().get_cDefaultValueFormated() != null && bSetDefault) {
			String numberFormated = this.EXT_DB().get_oSQLField().get_cDefaultValueFormated();
			if (S.isFull(numberFormated)) {
				MenueEntry e = this.popupCascade.findDbVal(numberFormated);
				if (e != null) {
					this.tf.setText(e.getText());
					this.actualValue=e.getDbVal();
					this.EXT_DB().set_cLASTActualDBValueFormated(this.actualValue);
				}
			}
		}
	}

	
	@Override
	public String get_cActualMaskValue() throws myException {
		return this.tf.getText();
	}

	@Override
	public String get_cActualDBValueFormated() throws myException {
		String maskText = this.get_cActualMaskValue();
		if (S.isFull(maskText)) {
			MenueEntry m = this.popupCascade.findMenueEntry(maskText);
			if (m!=null) {
				return m.getDbVal();
			}
		}
		return "";
	}

	
	@Override
	public void set_cActualMaskValue(String dbVal) throws myException {
		//jetzt alle vorhandenen pairs durchsuchen und den text der korrekten anzeigen
		this.tf.setText("");
		this.actualValue=null;
		this.EXT_DB().set_cLASTActualDBValueFormated("");
		
		if (S.isFull(dbVal)) {
			MenueEntry m = this.popupCascade.findDbVal(dbVal);
			if (m!=null) {
				this.actualValue=dbVal;
				this.tf.setText(m.getText());
				this.EXT_DB().set_cLASTActualDBValueFormated(dbVal);
			} else {
				this.tf.setText("@err");
				this.actualValue=null;
				this.EXT_DB().set_cLASTActualDBValueFormated("");
			}
		}
		
		//jetzt den endpoint dafuer suchen und markieren
		for (MenueEntry e: this.allRealEndpointEntrys) {
			if (e.isButton() && e.getDbVal().equals(this.actualValue)) {
				e.getButtonEndPoint().setBorder(new Border(1, Color.BLACK, Border.STYLE_SOLID));
			} else {
				e.getButtonEndPoint().setBorder(null);
			}
		}
		
		//2020-11-13: tooltips interpreter
		if (toolTipsInterpreter.size()>0) {
			String tooltips = "";
			for (IF_Interpreter<String, String> ip: toolTipsInterpreter) {
				tooltips=tooltips+(S.isEmpty(tooltips)?ip.getResult(dbVal):"\n"+ip.getResult(dbVal));
			}
			this.tf.setToolTipText(tooltips);

		} else {
		
			this.tf.setToolTipText(this.tf.getText());
		}
	}

	@Override
	public void set_bIsComplexObject(boolean bisComplex) {
	}


	@Override
	public boolean get_bIsComplexObject() {
		return false;
	}

	@Override
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap)	throws myException {
		return null;
	}

	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap)	throws myException {
		return null;
	}

	@Override
	public MyE2EXT__DB_Component EXT_DB() {
		return this.oEXTDB;
	}

	@Override
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB) {
		this.oEXTDB = oEXT_DB; 
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
				E2_DB_SelFieldAsCascadingPopup.this.set_cActualMaskValue(this.m_e.getDbVal());
				E2_DB_SelFieldAsCascadingPopup.this.executeClick(m_e);
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
	
	
	/**
	 * @return the toolTipsInterpreter
	 */
	public VEK<IF_Interpreter<String, String>> getToolTipsInterpreter() {
		return toolTipsInterpreter;
	}

	
	
	public E2_DB_SelFieldAsCascadingPopup _registerTooltipGenerator(IF_Interpreter<String, String> interpreter) {
		this.toolTipsInterpreter._a(interpreter);
		return this;
	}
	
}
