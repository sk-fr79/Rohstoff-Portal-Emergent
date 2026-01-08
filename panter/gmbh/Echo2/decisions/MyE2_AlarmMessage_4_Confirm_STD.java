package panter.gmbh.Echo2.decisions;

import java.util.UUID;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.event.WindowPaneEvent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.Messaging.E2_WindowPane_4_PopupMessages;
import panter.gmbh.Echo2.Messaging.IF_Message_ForceIntoPopup;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPaneExtender;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_AlarmMessage_4_Confirm_STD extends MyE2_AlarmMessage_4_Confirm_ABSTRACT {

	private MyE2_Button oButtonOK = 	null;
	private MyE2_Button oButtonCancel = null;
	
	private int i_BreiteMessage = 0;
	private int i_BreiteButtons = 0;
	
	private int i_HoehePopupWindow = 0;

	private ownWindowPane4Message oWindow = new ownWindowPane4Message();
	
	FingerPrint_Generator       o_GeneratorFingerPrints = null;
	
	
	public MyE2_AlarmMessage_4_Confirm_STD(	MyString 					cmessage, 
											MyString 					Text_4_OK_Button, 
											MyString 					Text_4_CancelButton,
											int 						iBreiteMessage,
											int 						iBreiteButtons,
											int 						iHoehePopupWindow,
											E2_IF_Handles_ActionAgents 	oComponentCalling, 
											UUID 						uuidActionAgent_OR_Validator,
											FingerPrint_Generator       oGeneratorFingerPrints) throws myException {
		super(cmessage, oComponentCalling, uuidActionAgent_OR_Validator);
		
		this.oButtonOK = new MyE2_Button(Text_4_OK_Button);
		this.oButtonCancel = new MyE2_Button(Text_4_CancelButton);
		
		this.oButtonOK.add_oActionAgent(this.get_ActionAgent_4_SettingStamp_STEP2());
		this.oButtonOK.add_oActionAgent(this.get_ActionAgent_4_Executing_CallingComponent());
		
		this.i_BreiteMessage = iBreiteMessage;
		this.i_BreiteButtons = iBreiteButtons;
		this.i_HoehePopupWindow = iHoehePopupWindow;
		
		this.o_GeneratorFingerPrints = oGeneratorFingerPrints;
		
		this.oWindow.set_Size(new MyE2_WindowPaneExtender(new Extent(this.i_BreiteMessage+this.i_BreiteButtons+10),new Extent(this.i_HoehePopupWindow) ));
		
	
	}

	
	@Override
	public XX_ActionAgent get_ActionAgent_4_SettingStamp_STEP2() throws myException {
		return new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibDECISIONS.SAVE_VALID_FingerPrint_TO_SESSION_STEP2(	
											MyE2_AlarmMessage_4_Confirm_STD.this.get_uuid_ComponentWhichStartedThis(), 
											MyE2_AlarmMessage_4_Confirm_STD.this.get_uuid_ActionAgent_OR_Validator(), 
											MyE2_AlarmMessage_4_Confirm_STD.this.o_GeneratorFingerPrints.GenerateFingerprint());
			}
		};
		
	}

	@Override
	public XX_ActionAgent get_ActionAgent_4_Executing_CallingComponent() throws myException {
		return new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				MyE2_AlarmMessage_4_Confirm_STD.this.get_oComponentCalling().doActionPassiv();
			}
		};
	}


	public MyE2_Button get_oButtonOK() {
		return oButtonOK;
	}

	public MyE2_Button get_oButtonCancel() {
		return oButtonCancel;
	}

	@Override
	public Color get_Color_4_MessageBackground(MyE2_Message oMessage) {
		return new E2_ColorAlarm();
	}

	@Override
	public Vector<E2_IF_Handles_ActionAgents> get_vActiveComponents(MyE2_Message oMessage) {
		Vector<E2_IF_Handles_ActionAgents> vRueck = new Vector<E2_IF_Handles_ActionAgents>();
		vRueck.add(this.oButtonOK);
		vRueck.add(this.oButtonCancel);
		return vRueck;
	}

	@Override
	public void disable_ActiveComponents(MyE2_Message oMessage) throws myException {
		this.oButtonOK.set_bEnabled_For_Edit(false);
		this.oButtonCancel.set_bEnabled_For_Edit(false);
	}

	@Override
	public void enable_ActiveComponents(MyE2_Message oMessage) throws myException {
		this.oButtonOK.set_bEnabled_For_Edit(true);
		this.oButtonCancel.set_bEnabled_For_Edit(true);
	}


	@Override
	public void create_WindowPane4ShowingMessages(IF_Message_ForceIntoPopup msg) {
	}


	@Override
	public E2_WindowPane_4_PopupMessages get_WindowPane4ShowingMessages(IF_Message_ForceIntoPopup msg) {
		return this.oWindow;
	}


	@Override
	public void windowPaneClosing(WindowPaneEvent arg0) {
		try {
			if ( (arg0 != null) && (arg0.getSource() instanceof E2_WindowPane_4_PopupMessages)) {
				if (((E2_WindowPane_4_PopupMessages)arg0.getSource()).get_bCloseEventIsFromWindowButtonRightCorner()) {
					bibDECISIONS.CLEAN_Component_Fingerprint_in_SESSION(this.get_uuid_ComponentWhichStartedThis());				}
			}
		} catch (myException e) {
			e.printStackTrace();
		}
	}


	private class ownWindowPane4Message extends E2_WindowPane_4_PopupMessages {

		public ownWindowPane4Message() {
			super();
		}
	}


	@Override
	public Component get_ComponentWithMessageAndButtons(MyE2_Message oMessage) {
		MyE2_Grid gridRueck = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		gridRueck.setColumnWidth(0, new Extent(this.i_BreiteMessage));
		gridRueck.setColumnWidth(1, new Extent(this.i_BreiteButtons));
		
		gridRueck.add(new MyE2_Label(oMessage.get_cMessage()), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorAlarm(), 1, 1));
		
		MyE2_Grid oGridRet = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridRet.add(this.oButtonOK,MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorAlarm(), 1, 1));
		oGridRet.add(this.oButtonCancel,MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorAlarm(), 1, 1));
		gridRueck.add(oGridRet, MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(0,2,2,2), new E2_ColorAlarm(), 1, 1));
		
		return gridRueck;
	}



	@Override
	public Insets get_Insets_4_MessageInMessageGrid(MyE2_Message oMessage) {
		return E2_INSETS.I(2,2,2,2);
	}
	

}
