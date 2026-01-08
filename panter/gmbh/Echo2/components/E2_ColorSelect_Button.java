package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_ColorSelect_Button extends MyE2_Button {
	
	private String cNAME_RGB_Field_RED = null;
	private String cNAME_RGB_Field_GREEN = null;
	private String cNAME_RGB_Field_BLUE = null;
	
	private E2_ComponentMAP  	oMaskMAP = null;
	private boolean   			bIsInitialized = false;

	
	public E2_ColorSelect_Button(MyString cText) {
		super(cText);
		
		this.add_oActionAgent(new ownActionAgentFail());
		this.add_oActionAgent(new ColorButtonAction());
	}
	
	public void INIT_Button(String name_RGB_Field_RED, String name_RGB_Field_GREEN, String name_RGB_Field_BLUE, E2_ComponentMAP  maskMAP) {
		this.cNAME_RGB_Field_RED = 		name_RGB_Field_RED;
		this.cNAME_RGB_Field_GREEN = 	name_RGB_Field_GREEN;
		this.cNAME_RGB_Field_BLUE = 	name_RGB_Field_BLUE;
		this.oMaskMAP = maskMAP;
		
		
		this.bIsInitialized = true;
	}
	

	public abstract void fill_Anzeige_Mit_Color_AfterSelect(Color  oCol) throws myException;
	
	
	public void fill_Anzeige_Mit_Color_FromMask() throws myException {
		//falls nix in den maskenfeldern steht, dann wird 0,0,0 (schwarz) vorgegeben
		int iRed =  this.oMaskMAP.get_LActualDBValue(this.cNAME_RGB_Field_RED, true, true, new Long(0)).intValue();
		int iGreen =this.oMaskMAP.get_LActualDBValue(this.cNAME_RGB_Field_GREEN, true, true, new Long(0)).intValue();
		int iBlue = this.oMaskMAP.get_LActualDBValue(this.cNAME_RGB_Field_BLUE, true, true, new Long(0)).intValue();

		this.fill_Anzeige_Mit_Color_AfterSelect(new Color(iRed, iGreen, iBlue));
		
	}
	
	private class ownActionAgentFail extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (!E2_ColorSelect_Button.this.bIsInitialized) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error! Button is not initialized yet !!")));
				
				//damit abbruch
			}
		}
		
	}
	
	
	private class ColorButtonAction extends XX_ActionAgent
	{
		private E2_ColorSelectPopUp oSelPopup = null;
		
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_ColorSelect_Button oThis = E2_ColorSelect_Button.this;
			
			//falls nix in den maskenfeldern steht, dann wird 0,0,0 (schwarz) vorgegeben
			int iRed =  oThis.oMaskMAP.get_LActualDBValue(oThis.cNAME_RGB_Field_RED, true, true, new Long(0)).intValue();
			int iGreen =oThis.oMaskMAP.get_LActualDBValue(oThis.cNAME_RGB_Field_GREEN, true, true, new Long(0)).intValue();
			int iBlue = oThis.oMaskMAP.get_LActualDBValue(oThis.cNAME_RGB_Field_BLUE, true, true, new Long(0)).intValue();

			
			Color oStartCol = null;
			if (iRed>=0 && iRed<255 && iGreen>=0 && iGreen<255 && iBlue>=0 && iBlue<255) {
				oStartCol = new Color(iBlue,iGreen,iBlue);
			}
			
			this.oSelPopup = new E2_ColorSelectPopUp(new colorSaveAction(),oStartCol);
			this.oSelPopup.set_bWarnIfNothingChanged(true);
		}
		
		
		private class colorSaveAction extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				E2_ColorSelect_Button oThis = E2_ColorSelect_Button.this;
				E2_ColorSelectPopUp   pop = ColorButtonAction.this.oSelPopup;
				
				((MyE2IF__DB_Component)oThis.oMaskMAP.get__Comp(oThis.cNAME_RGB_Field_RED)).set_cActualMaskValue(""+pop.get_RED());
				((MyE2IF__DB_Component)oThis.oMaskMAP.get__Comp(oThis.cNAME_RGB_Field_GREEN)).set_cActualMaskValue(""+pop.get_GREEN());
				((MyE2IF__DB_Component)oThis.oMaskMAP.get__Comp(oThis.cNAME_RGB_Field_BLUE)).set_cActualMaskValue(""+pop.get_BLUE());
				
				oThis.fill_Anzeige_Mit_Color_AfterSelect(new Color(pop.get_RED(), pop.get_GREEN(),pop.get_BLUE()));
				
			}
			
		}

		
	}
	
	
	
}
