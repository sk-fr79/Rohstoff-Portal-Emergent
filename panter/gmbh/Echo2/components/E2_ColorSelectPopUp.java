package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.extras.app.ColorSelect;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;

public class E2_ColorSelectPopUp extends E2_BasicModuleContainer
{

	
	private ColorSelect  oSelectCol = null;
	
	private MyE2_Button oButtonOk = 		new MyE2_Button(new MyE2_String("OK"));
	private MyE2_Button oButtonAbbruch = 	new MyE2_Button(new MyE2_String("Abbruch"));
	
	/*
	 *2014-02-17: falls keine aenderung erfolgte wird (im falle  bWarnIfNothingChanged nur eine meldung abgegeben, fenster bleibt offen)
	 */
	private boolean     bWarnIfNothingChanged = false;
	private Color       oColorAtStart = null;
	

	public E2_ColorSelectPopUp(XX_ActionAgent AgentActionAfterOK, Color oColor) throws myException
	{
		super();
		
		if (oColor==null)
		{
			this.oSelectCol = new ColorSelect();
		}
		else
		{
			this.oSelectCol = new ColorSelect(oColor);
			this.oColorAtStart = oColor;
		}
		
		this.set_bVisible_Row_For_Messages(false);
		
		this.add(this.oSelectCol, E2_INSETS.I_5_5_5_5);
		this.add(new E2_ComponentGroupHorizontal(0,this.oButtonOk,this.oButtonAbbruch,E2_INSETS.I_0_2_5_2));
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(300), new Extent(300), new MyE2_String("Farbauswahl ..."));

		this.oButtonOk.add_oActionAgent(new ownActionAgentPruefeClose());
		this.oButtonOk.add_oActionAgent(AgentActionAfterOK);
		this.oButtonOk.add_oActionAgent(new ownActionAgentOK());
		this.oButtonAbbruch.add_oActionAgent(new ownActionAgentAbbruch());
	}

	/*
	 * 2014-02-17: evtl. abbrechen vor OK, um zu verhindern, dass der anwender vergisst in das Farbenfeld zu klicken
	 */
	private class ownActionAgentPruefeClose extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ColorSelectPopUp oThis = E2_ColorSelectPopUp.this;
			if (oThis.bWarnIfNothingChanged && !oThis.get_bColorHasChanged()) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung ! Es wurde keine Farbe gewählt/verändert. Bitte klicken Sie in das Farbquadrat !" )));
			}
		}
	}
	
	private class ownActionAgentOK extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			
			E2_ColorSelectPopUp.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}
	
	
	private class ownActionAgentAbbruch extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_ColorSelectPopUp.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}
	
	public int get_RED()
	{
		return this.oSelectCol.getColor().getRed();
	}
	
	public int get_GREEN()
	{
		return this.oSelectCol.getColor().getGreen();
	}
	
	public int get_BLUE()
	{
		return this.oSelectCol.getColor().getBlue();
	}
	
	public Color  get_oColorWasSelected()
	{
		return new Color(this.get_RED(),this.get_GREEN(),this.get_BLUE());
	}
	
	
	public boolean get_bWarnIfNothingChanged() {
		return this.bWarnIfNothingChanged;
	}

	public void set_bWarnIfNothingChanged(boolean warnIfNothingChanged) {
		this.bWarnIfNothingChanged = warnIfNothingChanged;
	}

	
	public boolean get_bColorHasChanged() {
		boolean bRueck = true;
		
		if (this.oColorAtStart==null && this.get_RED()==0 && this.get_GREEN()==0 && this.get_BLUE()==0) {
			bRueck = false;
		} else if (	this.oColorAtStart!=null &&
					this.oColorAtStart.getRed()==this.get_RED() &&
					this.oColorAtStart.getGreen()==this.get_GREEN() &&
					this.oColorAtStart.getBlue()==this.get_BLUE()) {
			bRueck = false;
		}
		
		return bRueck;
	}
	
}
