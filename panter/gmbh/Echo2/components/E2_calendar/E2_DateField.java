package panter.gmbh.Echo2.components.E2_calendar;


import java.util.Date;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimple;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextField;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.maggie.TestingDate;

/* 
 * textfeld mit einem popup-selektor, der varianten darstellt
 */
public class E2_DateField extends E2_Grid implements MyE2IF__Component, E2_IF_Copy {

	private RB_TextField									oTextField = 	new RB_TextField();
	private	int 											iWidthPixel = 	92;

	private E2_Button										oButtonCalendar	  = null;
	private E2_CalendarComponent_WithDayButtons_Version2	oCalendarSelect = null;

	private ownContainerDatePopup  			  				ownContainer = null;              

	private Vector<XX_ActionAgent>                      	vActionAgentsZusatz = new Vector<XX_ActionAgent>();


	private myDateHelper   									oLastDateKlicked = null;

	private boolean   										miniIcon = 			true;
	private boolean 										passivAction = 		false;
	private boolean											popupSmallMode = 	false;
	private boolean  										withErasor = 		false;

	private E2_TF_4_Date_Enum								eMode = E2_TF_4_Date_Enum.OLD_SELECTION_MODE;
	

	//2011-08-16:  neuer clean-button dazufuegen
	private E2_Button                                 		oButtonEraser = null;


	
	/**
	 * 
	 * @author martin
	 * @date 11.03.2019
	 *
	 * @param datum  in form: 31.12.2019
	 * @throws myException
	 */
	public E2_DateField(String datum) throws myException {
		super();
		this.oTextField.setText(S.NN(datum));
		if (S.isFull(datum)) {
			this.oLastDateKlicked = new myDateHelper(datum);
		}
		this.oButtonCalendar=(E2_Button)new E2_Button()._image(E2_ResourceIcon.get_RI("calendar_mini.png"),true)
											._aaa(()->{E2_DateField.this.ownContainer=new ownContainerDatePopup();});
		this.oButtonEraser = (E2_Button)new E2_Button()._image(E2_ResourceIcon.get_RI("eraser.png"))._aaa(()-> {
											E2_DateField.this.oTextField.setText("");
											E2_DateField.this.oLastDateKlicked = null;  //2015-07-13
		});

		this.oTextField.setWidth(new Extent(this.get_iWidthPixel()));
		this.oTextField._maxInputSize(10);
		this.oTextField._f(new E2_FontPlain());
		this.oTextField.EXT().set_STYLE_FACTORY(new StyleFactory_TextField());
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
		
		this._render();
	}  




	
	public E2_DateField _render() {
		this._clear();
		
		this.oTextField._w(this.iWidthPixel);
		
		int[] spalten = new int[] {this.iWidthPixel,10}; 
		if (this.withErasor) {
			spalten = new int[] {this.iWidthPixel,10,10}; 
		}
		this._setSize(spalten);
		
		if (this.miniIcon) 	{
			this.oButtonCalendar._image(E2_ResourceIcon.get_RI("calendar_mini.png"),true);
		} else {
			this.oButtonCalendar._image(E2_ResourceIcon.get_RI("calendar.png"),true);
		}

		this._a(this.oTextField, new RB_gld()._ins(0, 0, 3,0)._left_mid());
		if (this.withErasor) {
			this._a(this.oButtonEraser, new RB_gld()._ins(0, 0, 3,0)._left_mid());
		}
		this._a(this.oButtonCalendar, new RB_gld()._ins(0, 0, 3,0)._left_mid());

		return this;
	}


	public Object get_Copy(Object ob) throws myExceptionCopy{
		throw new myExceptionCopy("Not implemeted yet");	
	}



	public E2_DateField _setMiniIcon(boolean bMiniIcon) {
		this.miniIcon = bMiniIcon;
		return this;
	}



	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled() ;
		this.oTextField.set_bEnabled_For_Edit(bVoraussetzung);
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(bVoraussetzung,true,false));
		this.oButtonCalendar.set_bEnabled_For_Edit(bVoraussetzung);
		this.oButtonEraser.set_bEnabled_For_Edit(bVoraussetzung);
	}





	public void show_InputStatus(boolean bInputIsOK) {
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(this.oTextField.isEnabled(),true,!bInputIsOK));
	}


	public RB_TextField get_oTextField() {
		return this.oTextField;
	}




	private class ownContainerDatePopup extends E2_BasicModuleContainer	{
		E2_DateField oThis = null;

		public ownContainerDatePopup() throws myException	{
			super();

			//hier wird der calendar-selector eingebaut
			oThis = E2_DateField.this;

			MyE2_Column oColBasic = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());

			String cDatumInText = S.NN(oThis.oTextField.getText());
			if (S.isEmpty(cDatumInText))
			{
				cDatumInText = bibALL.get_cDateNOW();
			}
			TestingDate oTD = new TestingDate(cDatumInText);
			if (!oTD.testing())
			{
				cDatumInText =  bibALL.get_cDateNOW();
			}

			//wenn jetzt immer noch falsches datum, dann error
			oTD = new TestingDate(cDatumInText);
			if (!oTD.testing())
			{
				throw new myException(this,"Error finding system-date");
			}

			oThis.oCalendarSelect = new ownCalendarComponent_WithDayButtons(cDatumInText);
			oThis.oCalendarSelect.setMode(eMode);
			oThis.oCalendarSelect.set_bPassivAction(passivAction);

			oColBasic.add(oThis.oCalendarSelect, E2_INSETS.I_2_2_2_2);

			this.add(oColBasic, E2_INSETS.I_0_0_0_0);

			if (S.isFull(oThis.EXT().get_MASK_SAVE_TAG()))
			{
				this.set_cADDON_TO_CLASSNAME(oThis.EXT().get_MASK_SAVE_TAG());
			}

			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(300), new MyE2_String("Bitte wählen Sie das Datum aus"));
		}

		public void refreshContainer(String cSelectedDatum) throws myException{
			oThis.oCalendarSelect.refreshCalender(cSelectedDatum);
		}
	}



	private class ownCalendarComponent_WithDayButtons extends E2_CalendarComponent_WithDayButtons_Version2
	{
		RB_TextField  oTF = null;
		E2_TF_4_Date_Button oDateButton = null;

		public ownCalendarComponent_WithDayButtons(String cLastDayInput) throws myException	{
			super(cLastDayInput);
		}

		@Override
		public void doDayButtonAction(ExecINFO execInfo) throws myException
		{
			oTF = E2_DateField.this.oTextField;
			oDateButton = (E2_TF_4_Date_Button)execInfo.get_MyActionEvent().getSource();
			
			switch(getMode()){
			case OLD_SELECTION_MODE:
				refreshCalendarComponent(execInfo);
				break;
			case DIRECT_SELECTION_MODE:
				standardActionMode(execInfo);
				break;
			case SLIP_SELECTION_MODE:
				slipCalendarComponent(execInfo);
				break;
			}
			
			for (int i=0;i<E2_DateField.this.vActionAgentsZusatz.size();i++)
			{
				E2_DateField.this.vActionAgentsZusatz.get(i).ExecuteAgentCode(execInfo);

				//falls fehlermessage gezogen wird, dann ausstieg
				if (bibMSG.get_bHasAlarms())
				{
					break;
				}
			}
		}

		private void standardActionMode(ExecINFO execInfo) throws myException {
			oTF.setText(((myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_cDateFormatForMask());
			E2_DateField.this.oLastDateKlicked = (myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING();
			E2_DateField.this.ownContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}

		private void refreshCalendarComponent(ExecINFO execInfo) throws myException{
			E2_DateField.this.oLastDateKlicked = (myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING();
			E2_DateField.this.ownContainer.refreshContainer(
					((myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_cDateFormatForMask()
				);
			
		} 

		private void slipCalendarComponent(ExecINFO execInfo) throws myException{
			int selectedMonate = ((myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_IMonth();
			int referenzMonate = getActiveMonthNumber();
			
			
			if(selectedMonate == referenzMonate){
				standardActionMode(execInfo);
			}else refreshCalendarComponent(execInfo);
		}

		@Override
		public Vector<Date> get_DatesToHighlight() throws myException {
			return null;
		}

		@Override
		public void saveAction(ExecINFO oExecInfo) throws myException {
			
			if(oDateButton == null){
				E2_DateField.this.oTextField.setText(oLastDateKlicked.get_cDateFormatForMask());
			}else{
				oDateButton.isActiveMonth();
				
				
				E2_DateField.this.oTextField.setText(((myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_cDateFormatForMask());
				E2_DateField.this.oLastDateKlicked = (myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING();
			}
			E2_DateField.this.ownContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}		
	}

	public E2_Button get_oButtonEraser() {
		return oButtonEraser;
	}

	public boolean isMiniIcon() {
		return miniIcon;
	}

	public myDateHelper getLastDateKlicked() 	{
		return oLastDateKlicked;
	}

	public E2_DateField setLastDateKlicked(myDateHelper oLastDateKlicked) {
		this.oLastDateKlicked = oLastDateKlicked;
		return this;
	}

	public Vector<XX_ActionAgent> get_vActionAgentsZusatz() {
		return vActionAgentsZusatz;
	}

	
	public E2_DateField _addZusatzAction(IF_agentSimple a) {
		this.vActionAgentsZusatz.add(a.genActionAgent());
		return this;
	}
	

	/**
	 * gibt den Button "Kalender öffnen" zurück
	 * @return
	 */
	public E2_Button get_oButtonCalendar() {
		return oButtonCalendar;
	}

	/**
	 * gibt das Kalender-Objekt zurück
	 * @return
	 */
	public E2_CalendarComponent_WithDayButtons_Version2 get_oCalendarObject(){
		return oCalendarSelect;
	}


	/**
	 * Setzt das Attribute PassiveAction für die Buttons der Komponente
	 * @param bPassive
	 */
	public E2_DateField _setPassivAction(boolean bPassive){
		this.passivAction = bPassive;
		this.oButtonCalendar.set_bPassivAction(bPassive);
		return this;
	}


	/**
	 * 
	 * @returns Date-object wenn alles ok oder null
	 * @throws myException
	 */
	public MyDate  getDateFromTextField() throws myException	{
		MyDate  oDate = new MyDate(this.oTextField.getText());

		if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK))	{
			return oDate;
		} else {
			return null;
		}
	}


	/**
	 * 
	 * @returns Date-object (immer, evtl. mit fehlerstatus im MyDate-Object)
	 * @throws myException
	 */
	public MyDate  getDateFromTextField_even_when_not_OK() throws myException	{
		return new MyDate(this.oTextField.getText());
	}



	/**
	 * 
	 * @returns formatiertes Datum (31.12.2011) wenn alles ok oder ""
	 * @throws myException
	 */
	public String  getFormatedDateFromTextField() throws myException	{
		MyDate  oDate = new MyDate(this.oTextField.getText());

		if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK)){
			return oDate.get_cUmwandlungsergebnis();
		} else {
			return "";
		}
	}

	/**
	 * 
	 * @returns formatiertes Datum (2011-12-31) wenn alles ok oder ""
	 * @throws myException
	 */
	public String  getDBFormatedDateFromTextField() throws myException {
		MyDate  oDate = new MyDate(this.oTextField.getText());

		if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK)) {
			return oDate.get_cDBFormatErgebnis();
		} else {
			return "";
		}
	}



	public E2_TF_4_Date_Enum getSelectionMode() {
		return eMode;
	}

	public E2_DateField  _setSelectionMode(E2_TF_4_Date_Enum eMode) {
		this.eMode = eMode;
		return this;
	}

	public boolean isPopUpInSmallMode() {
		return popupSmallMode;
	}

	public E2_DateField _setPopUpInSmallMode(boolean m_bSetPopUpInSmallMode) {
		this.popupSmallMode = m_bSetPopUpInSmallMode;
		return this;
	}


	public int get_iWidthPixel() {
		return iWidthPixel;
	}


	public E2_DateField _setWidthPixel(int iWidthPixel) {
		this.iWidthPixel = iWidthPixel;
		return this;
	}


	public boolean isWithErasor() {
		return withErasor;
	}


	public E2_DateField _setAddErasor(boolean addErasor) {
		this.withErasor = addErasor;
		return this;
	}


}
