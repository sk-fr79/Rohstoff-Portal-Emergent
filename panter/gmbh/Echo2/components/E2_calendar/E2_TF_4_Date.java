package panter.gmbh.Echo2.components.E2_calendar;


import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
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
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
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
public class E2_TF_4_Date extends MyE2_Row implements MyE2IF__Component, E2_IF_Copy {

	private MyE2_TextField									oTextField = 	new MyE2_TextField();
	private	int 											iWidthPixel = 	92;

	private MyE2_Button										oButtonCalendar	  = null;
	private E2_CalendarComponent_WithDayButtons_Version2	oCalendarSelect = null;

	private ownContainerDatePopup  			  				ownContainer = null;              

	private Vector<XX_ActionAgent>                      	vActionAgentsZusatz = new Vector<XX_ActionAgent>();


	private myDateHelper   									oLastDateKlicked = null;

	private boolean   										b_MiniIcon = false;
	private boolean 										m_bPassiveAction = false;
	private boolean											m_bSetPopUpInSmallMode = false;

	private E2_TF_4_Date_Enum								eMode;

	//2011-08-16:  neuer clean-button dazufuegen
	private MyE2_Button                                 	oButtonEraser = null;


	public E2_TF_4_Date() throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());

		this.oLastDateKlicked = new myDateHelper(new GregorianCalendar());
		
		this.__setBasic(false,false);
	}  

	
	
	
	public E2_TF_4_Date(String cText,int iwidthPixel) throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());

		if (iwidthPixel>1)
		{
			this.set_iWidthPixel(iwidthPixel);
		}

		this.oTextField.setText(cText);

		if (S.isFull(cText))
		{
			this.oLastDateKlicked = new myDateHelper(cText);
		}

		this.__setBasic(false,false);

	}  

	public E2_TF_4_Date(String cText,int iwidthPixel, boolean bMiniIcon) throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());

		if (iwidthPixel>1)
		{
			this.set_iWidthPixel(iwidthPixel);
		}

		this.oTextField.setText(cText);

		if (S.isFull(cText))
		{
			this.oLastDateKlicked = new myDateHelper(cText);
		}

		this.__setBasic(bMiniIcon,false);

	}  

	//ein weiterer satz konstructoren mit eraser
	public E2_TF_4_Date(boolean bErasor) throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());

		this.__setBasic(false,bErasor);
	}  

	public E2_TF_4_Date(String cText,int iwidthPixel, boolean bMiniIcon, boolean bErasor) throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());

		if (iwidthPixel>1)
		{
			this.set_iWidthPixel(iwidthPixel);
		}

		this.oTextField.setText(cText);

		if (S.isFull(cText))
		{
			this.oLastDateKlicked = new myDateHelper(cText);
		}

		this.__setBasic(bMiniIcon, bErasor);

	}  

	public E2_TF_4_Date(
			String cText,
			int iwidthPixel, 
			boolean bMiniIcon, 
			boolean bErasor, 
			E2_TF_4_Date_Enum mode){

		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());

		this.setSelectionMode(mode);

		this.__setBasic(bMiniIcon, bErasor);
	}

	public E2_TF_4_Date(
			String cText,
			int iwidthPixel, 
			boolean bMiniIcon, 
			boolean bErasor, 
			E2_TF_4_Date_Enum mode,
			boolean bSmallPopUp){
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());

		this.setSelectionMode(mode);

		this.setPopUpInSmallMode(bSmallPopUp);

		this.__setBasic(bMiniIcon, bErasor);
	}

	protected void __setBasic(boolean bMiniIcon, boolean bAddEraser)
	{
		this.oTextField.setWidth(new Extent(this.get_iWidthPixel()));
		this.oTextField.set_iMaxInputSize(10);
		this.oTextField.setFont(new E2_FontPlain());
		this.oTextField.EXT().set_STYLE_FACTORY(new StyleFactory_TextField());
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(true,true,false));

		this.b_MiniIcon = bMiniIcon;

		if (bMiniIcon)
		{
			this.oButtonCalendar=new MyE2_Button(E2_ResourceIcon.get_RI("calendar_mini.png"),true);
		}
		else
		{
			this.oButtonCalendar=new MyE2_Button(E2_ResourceIcon.get_RI("calendar.png"),true);
		}

		if(eMode == null){
			eMode = E2_TF_4_Date_Enum.OLD_SELECTION_MODE;
		}

		this.oButtonCalendar.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				E2_TF_4_Date.this.ownContainer=new ownContainerDatePopup();
			}
		});

		this.add(this.oTextField,new Insets(0,0,2,0));

		if (bAddEraser)
		{
			this.oButtonEraser = new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"));
			this.oButtonEraser.add_oActionAgent(new ownActionCleanTextFeld());
			this.add(this.oButtonEraser,new Insets(0,0,2,0));
		}

		this.add(this.oButtonCalendar,new Insets(0,0,2,0));

		this.oTextField.setHorizontalScroll(new Extent(1));
		this.oTextField.setVerticalScroll(new Extent(1));

	}



	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		E2_TF_4_Date oRueck = null;

		try
		{
			oRueck =  new E2_TF_4_Date();
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_TextField_With_DatePOPUP:get_Copy:copy-error!"+ex.get_ErrorMessage().get_cMessage().COrig());
		}

		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));

		oRueck.setStyle(this.getStyle());
		oRueck.setFont(this.getFont());

		oRueck.get_oTextField().set_iMaxInputSize(this.oTextField.get_iMaxInputSize());
		oRueck.get_oTextField().set_iWidthPixel(this.oTextField.get_iWidthPixel());
		oRueck.get_oTextField().setText(this.oTextField.getText());
		oRueck.get_oTextField().setWidth(this.oTextField.getWidth());
		oRueck.get_oTextField().setAlignment(this.oTextField.getAlignment());
		oRueck.get_vActionAgentsZusatz().addAll(this.vActionAgentsZusatz);

		return oRueck;
	}



	//aenderung 20101020
	public void set_bMiniIcon(boolean bMiniIcon)
	{
		this.b_MiniIcon = bMiniIcon;

		if (bMiniIcon)
		{
			this.oButtonCalendar.__setImages(E2_ResourceIcon.get_RI("calendar_mini.png"),true);
		}
		else
		{
			this.oButtonCalendar.__setImages(E2_ResourceIcon.get_RI("calendar.png"),true);
		}
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
		if (this.oButtonEraser!=null) {
			this.oButtonEraser.set_bEnabled_For_Edit(bVoraussetzung);
		}
	}





	public void show_InputStatus(boolean bInputIsOK)
	{
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(this.oTextField.isEnabled(),true,!bInputIsOK));
	}


	public MyE2_TextField get_oTextField()
	{
		return this.oTextField;
	}




	private class ownContainerDatePopup extends E2_BasicModuleContainer
	{
		E2_TF_4_Date oThis = null;

		public ownContainerDatePopup() throws myException 
		{
			super();

			//hier wird der calendar-selector eingebaut
			oThis = E2_TF_4_Date.this;

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
			oThis.oCalendarSelect.set_bPassivAction(m_bPassiveAction);

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
		MyE2_TextField oTF = null;
		E2_TF_4_Date_Button oDateButton = null;

		public ownCalendarComponent_WithDayButtons(String cLastDayInput) throws myException
		{
			super(cLastDayInput);
		}

		@Override
		public void doDayButtonAction(ExecINFO execInfo) throws myException
		{
			oTF = E2_TF_4_Date.this.oTextField;
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
			
			for (int i=0;i<E2_TF_4_Date.this.vActionAgentsZusatz.size();i++)
			{
				E2_TF_4_Date.this.vActionAgentsZusatz.get(i).ExecuteAgentCode(execInfo);

				//falls fehlermessage gezogen wird, dann ausstieg
				if (bibMSG.get_bHasAlarms())
				{
					break;
				}
			}
		}

		private void standardActionMode(ExecINFO execInfo) throws myException {
			oTF.setText(((myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_cDateFormatForMask());
			E2_TF_4_Date.this.oLastDateKlicked = (myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING();
			E2_TF_4_Date.this.ownContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}

		private void refreshCalendarComponent(ExecINFO execInfo) throws myException{
			E2_TF_4_Date.this.oLastDateKlicked = (myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING();
			E2_TF_4_Date.this.ownContainer.refreshContainer(
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
				E2_TF_4_Date.this.oTextField.setText(oLastDateKlicked.get_cDateFormatForMask());
			}else{
				oDateButton.isActiveMonth();
				
				
				E2_TF_4_Date.this.oTextField.setText(((myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_cDateFormatForMask());
				E2_TF_4_Date.this.oLastDateKlicked = (myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING();
			}
			E2_TF_4_Date.this.ownContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}		
	}

	public MyE2_Button get_oButtonEraser()
	{
		return oButtonEraser;
	}

	public boolean get_bMiniIcon() 
	{
		return b_MiniIcon;
	}

	public myDateHelper get_oLastDateKlicked() 
	{
		return oLastDateKlicked;
	}

	public void set_oLastDateKlicked(myDateHelper oLastDateKlicked) 
	{
		this.oLastDateKlicked = oLastDateKlicked;
	}

	public Vector<XX_ActionAgent> get_vActionAgentsZusatz()
	{
		return vActionAgentsZusatz;
	}

	
	public E2_TF_4_Date _addZusatzAction(IF_agentSimple a) {
		this.vActionAgentsZusatz.add(a.genActionAgent());
		return this;
	}
	

	/**
	 * gibt den Button "Kalender öffnen" zurück
	 * @return
	 */
	public MyE2_Button get_oButtonCalendar()
	{
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
	public void set_bPassivAction(boolean bPassive){
		this.m_bPassiveAction = bPassive;
		this.oButtonCalendar.set_bPassivAction(bPassive);
	}


	/**
	 * 
	 * @returns Date-object wenn alles ok oder null
	 * @throws myException
	 */
	public MyDate  get_oDateFromTextField() throws myException
	{
		MyDate  oDate = new MyDate(this.oTextField.getText());

		if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			return oDate;
		}
		else
		{
			return null;
		}
	}


	/**
	 * 
	 * @returns Date-object (immer, evtl. mit fehlerstatus im MyDate-Object)
	 * @throws myException
	 */
	public MyDate  get_oDateFromTextField_even_when_not_OK() throws myException
	{
		return new MyDate(this.oTextField.getText());
	}



	/**
	 * 
	 * @returns formatiertes Datum (31.12.2011) wenn alles ok oder ""
	 * @throws myException
	 */
	public String  get_oFormatedDateFromTextField() throws myException
	{
		MyDate  oDate = new MyDate(this.oTextField.getText());

		if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			return oDate.get_cUmwandlungsergebnis();
		}
		else
		{
			return "";
		}
	}

	/**
	 * 
	 * @returns formatiertes Datum (2011-12-31) wenn alles ok oder ""
	 * @throws myException
	 */
	public String  get_oDBFormatedDateFromTextField() throws myException
	{
		MyDate  oDate = new MyDate(this.oTextField.getText());

		if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			return oDate.get_cDBFormatErgebnis();
		}
		else
		{
			return "";
		}
	}



	public E2_TF_4_Date_Enum getSelectionMode() {
		return eMode;
	}

	public void setSelectionMode(E2_TF_4_Date_Enum eMode) {
		this.eMode = eMode;
	}




	public boolean isPopUpInSmallMode() {
		return m_bSetPopUpInSmallMode;
	}

	public void setPopUpInSmallMode(boolean m_bSetPopUpInSmallMode) {
		this.m_bSetPopUpInSmallMode = m_bSetPopUpInSmallMode;
	}




	public int get_iWidthPixel() {
		return iWidthPixel;
	}




	public void set_iWidthPixel(int iWidthPixel) {
		this.iWidthPixel = iWidthPixel;
	}




	private class ownActionCleanTextFeld extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_TF_4_Date.this.oTextField.setText("");
			E2_TF_4_Date.this.oLastDateKlicked = null;  //2015-07-13

		}
	}


}
