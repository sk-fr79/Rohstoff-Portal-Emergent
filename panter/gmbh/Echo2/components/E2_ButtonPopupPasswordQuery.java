package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;



public abstract class E2_ButtonPopupPasswordQuery extends MyE2_Button {

	/**
	 * uebergibt das fuer den button geforderte password, wenn leer, dann normale weiterverarbeitung
	 * @return
	 * @throws myException
	 */
	public abstract String 						get_password_for_this_button() throws myException;
	public abstract MyE2_Button  				generate_password_confirm_button() throws myException;
	public abstract E2_BasicModuleContainer   	generate_password_confirm_popup_modulcontainer() throws myException;
	
	private MyE2_Button          				password_confirm_button = null;
	private E2_BasicModuleContainer             password_query_container = null;
	
	private Vector<XX_ActionAgent>			    v_actionAgents = new Vector<XX_ActionAgent>();
	private MyE2_PasswordField  				pw_textField = new MyE2_PasswordField();

	
	public E2_ButtonPopupPasswordQuery() throws myException {
		super();
		this._init();
	}
	
	
	private void _init() throws myException {
		this.password_confirm_button = this.generate_password_confirm_button();
		this.password_query_container = this.generate_password_confirm_popup_modulcontainer();

		this.password_query_container.set_bVisible_Row_For_Messages(true);
		this.password_query_container.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());
		
		//pseudoaktion vorschalten, die das passwort prueft und danach entweder die eigentlichen buttons nachlaedt oder
		//den abfragepopup aufmacht
		super.add_oActionAgent(new ownActionAgent4Button());
		
		this.password_confirm_button.add_oActionAgent(new ownActionConfirmPassword());
	}
	

	
	

	
	

	public E2_ButtonPopupPasswordQuery(ImageReference oImg,	boolean bAutoDisabled, MyString cToolTips, XX_ActionAgent oAgent) throws myException  {
		super(oImg, bAutoDisabled, cToolTips, oAgent);
		
		this._init();
		
	}

	public E2_ButtonPopupPasswordQuery(ImageReference oImg,	boolean bAutoDisabled)  throws myException {
		super(oImg, bAutoDisabled);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(ImageReference oImg,	ImageReference oimgDisabled, MyString cToolTips,XX_ActionAgent oAgent)  throws myException {
		super(oImg, oimgDisabled, cToolTips, oAgent);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(ImageReference oImg,ImageReference oimgDisabled) throws myException  {
		super(oImg, oimgDisabled);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(ImageReference oImg, MyString cToolTips,XX_ActionAgent oAgent) throws myException  {
		super(oImg, cToolTips, oAgent);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(ImageReference oImg)  throws myException {
		super(oImg);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(MyString cText, E2_MutableStyle oStyle,MyString cToolTips, XX_ActionAgent oAgent) throws myException  {
		super(cText, oStyle, cToolTips, oAgent);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(MyString cText, E2_MutableStyle oStyle) throws myException  {
		super(cText, oStyle);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(MyString cText, MyString cToolTips,XX_ActionAgent oAgent, boolean bCentered) throws myException  {
		super(cText, cToolTips, oAgent, bCentered);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(MyString cText, MyString cToolTips,	XX_ActionAgent oAgent) throws myException  {
		super(cText, cToolTips, oAgent);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(MyString cText) throws myException {
		super(cText);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(Object cText, ImageReference oImg,ImageReference oimgDisabled, MyString cToolTips, XX_ActionAgent oAgent)  throws myException {
		super(cText, oImg, oimgDisabled, cToolTips, oAgent);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(Object cText, ImageReference oImg,ImageReference oimgDisabled)  throws myException {
		super(cText, oImg, oimgDisabled);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(String cText, E2_MutableStyle oStyle,MyString cToolTips, XX_ActionAgent oAgent)  throws myException {
		super(cText, oStyle, cToolTips, oAgent);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(String cText, E2_MutableStyle oStyle) throws myException  {
		super(cText, oStyle);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(String cText, MyString cToolTips,XX_ActionAgent oAgent, boolean bCentered)  throws myException {
		super(cText, cToolTips, oAgent, bCentered);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(String cText, MyString cToolTips,	XX_ActionAgent oAgent)  throws myException {
		super(cText, cToolTips, oAgent);
		this._init();
	}

	public E2_ButtonPopupPasswordQuery(String cText)  throws myException {
		super(cText);
		this._init();
	}

	
	public void add_oActionAgent(XX_ActionAgent actionAgent) {
		if (actionAgent != null) {
			this.v_actionAgents.add(actionAgent);
		}
	}

	
	public void add_oActionAgent(XX_ActionAgent actionAgent, boolean bInFront) {
		if (actionAgent != null){
			if (bInFront){
				this.v_actionAgents.add(actionAgent);
			} else{
				this.v_actionAgents.add(actionAgent);
			}
		}
	}

	
	
	/*
	 * 2012-02-07: weitere methode hinzugefuegt, martin 
	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
	 */
	public void add_oActionAgent(Vector<XX_ActionAgent> vActionAgent, boolean bInFront) {
		if (vActionAgent != null)	{
			if (bInFront){
				this.v_actionAgents.addAll(0,vActionAgent);
			} else {
				this.v_actionAgents.addAll(vActionAgent);
			}
		}
	}

	

	
	/*
	 * 2012-10-15: weitere methode hinzugefuegt, martin 
	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
	 */
	public void add_oActionAgent_only_if_ClassNotInList(XX_ActionAgent ActionAgent, boolean bInFront) {
		boolean bIstSchonDa = false;
		for (XX_ActionAgent oAgentVorhanden:this.v_actionAgents){
			if (oAgentVorhanden.getClass().getName().equals(ActionAgent.getClass().getName()))	{
				bIstSchonDa = true;
			}
		}
		if (!bIstSchonDa) {
			if (bInFront) {
				this.v_actionAgents.add(0,ActionAgent);
			} else {
				this.v_actionAgents.add(ActionAgent);
			}
		}
	}

	
	/*
	 * 2012-10-15: weitere methode hinzugefuegt, martin 
	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
	 */
	public void add_oActionAgent_only_if_ClassNotInList(Vector<XX_ActionAgent> ActionAgents, boolean bInFront)
	{
		for (XX_ActionAgent oAgent: ActionAgents) {
			boolean bIstSchonDa = false;
			for (XX_ActionAgent oAgentVorhanden:this.v_actionAgents) 	{
				if (oAgentVorhanden.getClass().getName().equals(oAgent.getClass().getName()))	{
					bIstSchonDa = true;
				}
			}
			if (!bIstSchonDa)	{
				if (bInFront) {
					this.v_actionAgents.add(0,oAgent);
				} else {
					this.v_actionAgents.add(oAgent);
				}
			}
		}
	}

	
	
	public void remove_oActionAgent(XX_ActionAgent actionAgent)
	{
		for (int i=0;i<this.v_actionAgents.size();i++)  {
			if (this.v_actionAgents.get(i)==actionAgent) {
				this.v_actionAgents.remove(i);
			}
		}
	}

	
	public void remove_AllActionAgents() {
		this.v_actionAgents.removeAllElements();
	}

	
	

	private class ownActionAgent4Button extends XX_ActionAgent {
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ButtonPopupPasswordQuery  oThis = E2_ButtonPopupPasswordQuery.this;
			
			oThis.password_query_container.RESET_Content();
			
			String c_password = oThis.get_password_for_this_button();
			if (S.isFull(c_password)) {
				
				int[] breite = {140,140};
				
				oThis.pw_textField.setText("");
				
				MyE2_Grid  gridHelp = new MyE2_Grid(breite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
				gridHelp.add(new MyE2_Label(new MyE2_String("Passwort: ")),E2_INSETS.I(0,0,20,0));
				gridHelp.add(oThis.pw_textField,E2_INSETS.I(0,0,0,0));
				
				
				gridHelp.add(new E2_Grid4MaskSimple().def_(50).add_(oThis.password_confirm_button),E2_INSETS.I(0,15,0,0));
				
				oThis.password_query_container.add(gridHelp,E2_INSETS.I(6,15,3,6));
				oThis.password_query_container.CREATE_AND_SHOW_POPUPWINDOW(new Extent(380), new Extent(170), new MyE2_String("Bitte Passwort eingeben ..."));				
				
				ApplicationInstance.getActive().setFocusedComponent(oThis.pw_textField);

				
			} else {
				Vector<XX_ActionAgent>  v_actions = oThis.get_vActionAgents();
				//im laufenden ablauf die schleife erweitern ....				
				v_actions.addAll(oThis.v_actionAgents);
			}
		}
	}
	
	
	private class ownActionConfirmPassword extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ButtonPopupPasswordQuery  oThis = E2_ButtonPopupPasswordQuery.this;
			
			//falls das passwort stimmt, dann alle actionAgents hinter diesen button haengen
			if (S.NN(oThis.pw_textField.getText()).trim().equals(oThis.get_password_for_this_button().trim())) {
				oThis.password_confirm_button.get_vActionAgents()
						.addAll(oThis.v_actionAgents);
				E2_ButtonPopupPasswordQuery.this.password_query_container.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Passwort wird nicht akzeptiert !")));
			}
		}
	}

	
	
	
	
	
}
