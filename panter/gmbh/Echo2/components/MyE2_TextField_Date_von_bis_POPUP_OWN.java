package panter.gmbh.Echo2.components;


import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextField;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

/*
 * 2012-11-16: 2 textfelder fuer datumsbereich mit einem popup-selektor, der beide auswahlen zulaesst
 */
public abstract class MyE2_TextField_Date_von_bis_POPUP_OWN extends MyE2_Grid implements MyE2IF__Component, E2_IF_Copy
{

	private MyE2_TextField								oTextFieldVon = 	new MyE2_TextField();
	private MyE2_TextField								oTextFieldBis = 	new MyE2_TextField();
	
	private	int 										iWidthPixel = 	92;

	private MyE2_Button									oButtonCalendar	  = null;
	private E2_CalendarComponent_WithDayButtons        	oCalendarSelectVon = null;
	private E2_CalendarComponent_WithDayButtons        	oCalendarSelectBis = null;
	
	private ownContainerDatePopup  			  			ownContainer = null;              
	
	private Vector<XX_ActionAgent>                     	vActionAgentsZusatzVonCalender = new Vector<XX_ActionAgent>();
	private Vector<XX_ActionAgent>                     	vActionAgentsZusatzBisCalender = new Vector<XX_ActionAgent>();
	
	private myDateHelper  								oLastDateKlickedVon = null;
	private myDateHelper  								oLastDateKlickedBis = null;
	
	private boolean 									m_bPassiveAction = false;
	
	
	//2011-08-16:  neuer clean-button dazufuegen
	private MyE2_Button                                	oButtonEraserVon = null;
	private MyE2_Button                                	oButtonEraserBis = null;
	
	
	private boolean   									bAutoCloseOnBisCalendar = false;
	
	private boolean  									bShowOKButton = false;

	private MyE2_Button  								oButtonOK_and_close = new MyE2_Button(E2_ResourceIcon.get_RI("ok.png"));





	//abstrakte methode, die die komponente baut (anordnung und breite der buttons und felder)
	public abstract void Ordne_Komponenten_An(
								MyE2_TextField oTextFieldVon,
								MyE2_TextField oTextFieldBis,
								MyE2_Button oButtonCalendar,
								MyE2_Button oButtonEraserVon,
								MyE2_Button oButtonEraserBis) throws myException;
	

	
	
	public MyE2_TextField_Date_von_bis_POPUP_OWN() throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		
		this.__setBasic();
	}  
	
	
	
	public MyE2_TextField_Date_von_bis_POPUP_OWN(String cTextVon,String cTextBis) throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		
		this.oTextFieldVon.setText(cTextVon);
		this.oTextFieldBis.setText(cTextBis);
		
		if (S.isFull(cTextVon)) 	{	this.oLastDateKlickedVon = new myDateHelper(cTextVon);}
		if (S.isFull(cTextBis)) 	{	this.oLastDateKlickedBis = new myDateHelper(cTextBis);}
		
		this.__setBasic();
	}  
	
	
	
	private void __setBasic() throws myException
	{
		this.oTextFieldVon.setWidth(new Extent(this.iWidthPixel));
		this.oTextFieldVon.set_iMaxInputSize(10);
		this.oTextFieldVon.setFont(new E2_FontPlain());
		this.oTextFieldVon.EXT().set_STYLE_FACTORY(new StyleFactory_TextField());
		this.oTextFieldVon.setStyle(this.oTextFieldVon.EXT().get_STYLE_FACTORY().get_Style(true,true,false));

		this.oTextFieldBis.setWidth(new Extent(this.iWidthPixel));
		this.oTextFieldBis.set_iMaxInputSize(10);
		this.oTextFieldBis.setFont(new E2_FontPlain());
		this.oTextFieldBis.EXT().set_STYLE_FACTORY(new StyleFactory_TextField());
		this.oTextFieldBis.setStyle(this.oTextFieldBis.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
		
		this.oButtonEraserVon = new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"));
		this.oButtonEraserVon.add_oActionAgent(new ownActionCleanTextFeld(this.oTextFieldVon));
		this.oButtonEraserBis = new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"));
		this.oButtonEraserBis.add_oActionAgent(new ownActionCleanTextFeld(this.oTextFieldBis));

		this.oButtonCalendar=new MyE2_Button(E2_ResourceIcon.get_RI("calendar_mini.png"),true);
		
		this.oTextFieldVon.setToolTipText(new MyE2_String("Startdatum des Datumsbereichs").CTrans());
		this.oTextFieldBis.setToolTipText(new MyE2_String("Enddatum des Datumsbereichs").CTrans());
		this.oButtonEraserVon.setToolTipText(new MyE2_String("Löschen des Startdatumsfeldes").CTrans());
		this.oButtonEraserBis.setToolTipText(new MyE2_String("Löschen des Enddatumsfeldes").CTrans());
		this.oButtonCalendar.setToolTipText(new MyE2_String("Aufruf der Kalender-Auswahl").CTrans());
		
		
		this.oButtonCalendar.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				MyE2_TextField_Date_von_bis_POPUP_OWN.this.ownContainer=new ownContainerDatePopup();
			}
		});

		this.oButtonOK_and_close.setStyle(MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
		this.oButtonOK_and_close.setToolTipText(new MyE2_String("Auswahl schliessen ...").CTrans());
		this.oButtonOK_and_close.add_oActionAgent(new XX_ActionAgent()
		{
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				if (MyE2_TextField_Date_von_bis_POPUP_OWN.this.ownContainer!=null)
				{
					MyE2_TextField_Date_von_bis_POPUP_OWN.this.ownContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			}
		});
		
		this.Ordne_Komponenten_An(this.oTextFieldVon, this.oTextFieldBis, this.oButtonCalendar, this.oButtonEraserVon, this.oButtonEraserBis);
	}

	
	
	
	
////
	
	public MyE2_Button get_oButtonEraserVon()
	{
		return oButtonEraserVon;
	}

	public MyE2_Button get_oButtonEraserBis()
	{
		return oButtonEraserBis;
	}



	public myDateHelper get_oLastDateKlickedVon() 
	{
		return oLastDateKlickedVon;
	}
	
	
	public myDateHelper get_oLastDateKlickedBis() 
	{
		return oLastDateKlickedBis;
	}


	public void set_oLastDateKlickedVon(myDateHelper oLastDateKlicked) 
	{
		this.oLastDateKlickedVon = oLastDateKlicked;
	}

	public void set_oLastDateKlickedBis(myDateHelper oLastDateKlicked) 
	{
		this.oLastDateKlickedBis = oLastDateKlicked;
	}

	
	

	
	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled() ;
		this.oTextFieldVon.set_bEnabled_For_Edit(bVoraussetzung);
		this.oTextFieldVon.setStyle(this.oTextFieldVon.EXT().get_STYLE_FACTORY().get_Style(bVoraussetzung,true,false));
		this.oTextFieldBis.set_bEnabled_For_Edit(bVoraussetzung);
		this.oTextFieldBis.setStyle(this.oTextFieldBis.EXT().get_STYLE_FACTORY().get_Style(bVoraussetzung,true,false));
		this.oButtonCalendar.set_bEnabled_For_Edit(bVoraussetzung);
		this.oButtonEraserVon.set_bEnabled_For_Edit(bVoraussetzung);
		this.oButtonEraserBis.set_bEnabled_For_Edit(bVoraussetzung);
	}


	


	public void show_InputStatus(boolean bInputIsOK)
	{
		this.oTextFieldVon.setStyle(this.oTextFieldVon.EXT().get_STYLE_FACTORY().get_Style(this.oTextFieldVon.isEnabled(),true,!bInputIsOK));
		this.oTextFieldBis.setStyle(this.oTextFieldBis.EXT().get_STYLE_FACTORY().get_Style(this.oTextFieldBis.isEnabled(),true,!bInputIsOK));
	}

	public void show_InputStatusStart(boolean bInputIsOK)
	{
		this.oTextFieldVon.setStyle(this.oTextFieldVon.EXT().get_STYLE_FACTORY().get_Style(this.oTextFieldVon.isEnabled(),true,!bInputIsOK));
	}
	
	public void show_InputStatusEnd(boolean bInputIsOK)
	{
		this.oTextFieldBis.setStyle(this.oTextFieldBis.EXT().get_STYLE_FACTORY().get_Style(this.oTextFieldBis.isEnabled(),true,!bInputIsOK));
	}
	

	public MyE2_TextField get_oTextFieldVon()
	{
		return this.oTextFieldVon;
	}

	public MyE2_TextField get_oTextFieldBis()
	{
		return this.oTextFieldBis;
	}

	
	
	private class ownContainerDatePopup extends E2_BasicModuleContainer
	{

		public ownContainerDatePopup() throws myException 
		{
			super();
			
			//hier wird der calendar-selector eingebaut
			MyE2_TextField_Date_von_bis_POPUP_OWN oThis = MyE2_TextField_Date_von_bis_POPUP_OWN.this;
			
			//E2_ComponentMAP oMap = MyE2_TextField_With_DatePOPUP.this.EXT().get_oComponentMAP();
			
			MyE2_Grid oGridBasic = new MyE2_Grid(oThis.bShowOKButton?3:2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

			String cDatumInTextVon = this.get_DatumAusTextFeld(oThis.oTextFieldVon);
			String cDatumInTextBis = this.get_DatumAusTextFeld(oThis.oTextFieldBis);
			
			
			oThis.oCalendarSelectVon = new ownCalendarComponent_WithDayButtons(cDatumInTextVon,oThis.oTextFieldVon,true);
			oThis.oCalendarSelectVon.set_bPassivAction(m_bPassiveAction);
	
			oThis.oCalendarSelectBis = new ownCalendarComponent_WithDayButtons(cDatumInTextBis,oThis.oTextFieldBis,false);
			oThis.oCalendarSelectBis.set_bPassivAction(m_bPassiveAction);
	
			oGridBasic.add(oThis.oCalendarSelectVon, MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_2_2_10_2));
			oGridBasic.add(oThis.oCalendarSelectBis, MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_2_2_10_2));
			if (oThis.bShowOKButton)
			{
				oGridBasic.add(oThis.oButtonOK_and_close, MyE2_Grid.LAYOUT_LEFT_BOTTOM(E2_INSETS.I_2_2_10_2));
			}
				 
			
			this.add(oGridBasic, E2_INSETS.I_0_0_0_0);
			
			if (S.isFull(oThis.EXT().get_MASK_SAVE_TAG()))
			{
				this.set_cADDON_TO_CLASSNAME(oThis.EXT().get_MASK_SAVE_TAG());
			}
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(300), new MyE2_String("Datumsbereich auswählen"));
		}
		
		
		private String  get_DatumAusTextFeld(MyE2_TextField oTF) throws myException
		{
			String cDatumInTextVon = S.NN(oTF.getText());
			if (S.isEmpty(cDatumInTextVon))
			{
				cDatumInTextVon = bibALL.get_cDateNOW();
			}
			TestingDate oTDVon = new TestingDate(cDatumInTextVon);
			if (!oTDVon.testing())
			{
				cDatumInTextVon =  bibALL.get_cDateNOW();
			}
			
			//wenn jetzt immer noch falsches datum, dann error
			oTDVon = new TestingDate(cDatumInTextVon);
			if (!oTDVon.testing())
			{
				throw new myException(this,"Error finding system-date");
			}
			return cDatumInTextVon;
		}
	}
	
	
	
	private class ownCalendarComponent_WithDayButtons extends E2_CalendarComponent_WithDayButtons
	{

		private MyE2_TextField 	oTF_4_date = null;
		private boolean     	bIsStartDatum = false;
		
		public ownCalendarComponent_WithDayButtons(String cLastDayInput, MyE2_TextField TF_4_date, boolean IsStartDatum) throws myException
		{
			super(cLastDayInput);
			this.oTF_4_date = TF_4_date;
			this.bIsStartDatum = IsStartDatum;
		}

		@Override
		public void doDayButtonAction(ExecINFO execInfo) throws myException
		{
			MyE2_TextField_Date_von_bis_POPUP_OWN oThis = MyE2_TextField_Date_von_bis_POPUP_OWN.this;
			
			MyE2_Button oDateButton = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
			this.oTF_4_date.setText(((myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_cDateFormatForMask());
				  
				 
			if (this.bIsStartDatum)
			{
				oThis.oLastDateKlickedVon = (myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING();
				oThis.oCalendarSelectVon.set_iMonth(oThis.oLastDateKlickedVon.get_IMonth());
				oThis.oCalendarSelectVon.set_iYear(oThis.oLastDateKlickedVon.get_IYear());
				oThis.oCalendarSelectVon.set_cSelectedDayFormated(oThis.oLastDateKlickedVon.get_cDateFormatForMask());
				oThis.oCalendarSelectVon.baue_calender();
				
				
				//jetzt pruefen, ob das zieldatum leer ist oder kleiner als das startdatum, dann das uebernehmen
				boolean bUebernehmen = false;
				if (S.isEmpty(oThis.oTextFieldBis.getText()))
				{
					bUebernehmen = true;
				}
				else
				{
					TestingDate oTD_bis = new TestingDate(oThis.oTextFieldBis.getText().trim());
					if (!oTD_bis.testing())
					{
						bUebernehmen=true;
					}
					else
					{
						myDateHelper oDH_datumBis = new myDateHelper(oTD_bis.get_Calendar());
						
						if (myDateHelper.get_Date1_GreaterEqual_Date2(oThis.oLastDateKlickedVon.get_cDateFormatForMask(), oDH_datumBis.get_cDateFormatForMask()))
						{
							bUebernehmen=true;
						}
					}
				}
				if (bUebernehmen)
				{
					oThis.oCalendarSelectBis.set_iMonth(oThis.oLastDateKlickedVon.get_IMonth());
					oThis.oCalendarSelectBis.set_iYear(oThis.oLastDateKlickedVon.get_IYear());
					oThis.oCalendarSelectBis.set_cSelectedDayFormated(oThis.oLastDateKlickedVon.get_cDateFormatForMask());
					oThis.oCalendarSelectBis.baue_calender();
					oThis.oTextFieldBis.setText(oThis.oLastDateKlickedVon.get_cDateFormatForMask());
				}

				//jetzt evtl vorhandene zusatzagents abarbeiten
				for (int i=0;i<MyE2_TextField_Date_von_bis_POPUP_OWN.this.vActionAgentsZusatzVonCalender.size();i++)
				{
					MyE2_TextField_Date_von_bis_POPUP_OWN.this.vActionAgentsZusatzVonCalender.get(i).ExecuteAgentCode(execInfo);
					
					//falls fehlermessage gezogen wird, dann ausstieg
					if (bibMSG.get_bHasAlarms())
					{
						break;
					}
				}
				
				
			}
			else
			{
				
				oThis.oLastDateKlickedBis = (myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING();
				oThis.oCalendarSelectBis.set_iMonth(oThis.oLastDateKlickedBis.get_IMonth());
				oThis.oCalendarSelectBis.set_iYear(oThis.oLastDateKlickedBis.get_IYear());
				oThis.oCalendarSelectBis.set_cSelectedDayFormated(oThis.oLastDateKlickedBis.get_cDateFormatForMask());
				oThis.oCalendarSelectBis.baue_calender();

				//wenn das zieldatum < als das startdatum, dann das startdatum gleichsetzen
				boolean bUebernehmen = false;
				
				TestingDate oTD_von = new TestingDate(oThis.oTextFieldVon.getText().trim());
				if (!oTD_von.testing())
				{
					bUebernehmen=true;
				}
				else
				{
					myDateHelper oDH_datumVon = new myDateHelper(oTD_von.get_Calendar());
					
					if (myDateHelper.get_Date1_GreaterEqual_Date2(oDH_datumVon.get_cDateFormatForMask(), oThis.oLastDateKlickedBis.get_cDateFormatForMask() ))
					{
						bUebernehmen=true;
					}
				}
				if (bUebernehmen)
				{
					oThis.oCalendarSelectVon.set_iMonth(oThis.oLastDateKlickedBis.get_IMonth());
					oThis.oCalendarSelectVon.set_iYear(oThis.oLastDateKlickedBis.get_IYear());
					oThis.oCalendarSelectVon.set_cSelectedDayFormated(oThis.oLastDateKlickedBis.get_cDateFormatForMask());
					oThis.oCalendarSelectVon.baue_calender();
					oThis.oTextFieldVon.setText(oThis.oLastDateKlickedBis.get_cDateFormatForMask());
				}
				//------------------------
				
				
				
				
				
				if (oThis.bAutoCloseOnBisCalendar)
				{
					MyE2_TextField_Date_von_bis_POPUP_OWN.this.ownContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
				
				MyE2_TextField_Date_von_bis_POPUP_OWN.this.oLastDateKlickedVon = (myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING();

				//jetzt evtl vorhandene zusatzagents abarbeiten
				for (int i=0;i<MyE2_TextField_Date_von_bis_POPUP_OWN.this.vActionAgentsZusatzBisCalender.size();i++)
				{
					MyE2_TextField_Date_von_bis_POPUP_OWN.this.vActionAgentsZusatzBisCalender.get(i).ExecuteAgentCode(execInfo);
					
					//falls fehlermessage gezogen wird, dann ausstieg
					if (bibMSG.get_bHasAlarms())
					{
						break;
					}
				}
			}
		}
		
	}



	public Vector<XX_ActionAgent> get_vActionAgentsZusatzVonCalender()
	{
		return vActionAgentsZusatzVonCalender;
	}

	public Vector<XX_ActionAgent> get_vActionAgentsZusatzBisCalender()
	{
		return vActionAgentsZusatzBisCalender;
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
	 * gibt das Kalender-Objekt zurück (von)
	 * @return
	 */
	public E2_CalendarComponent_WithDayButtons get_oCalendarObjectVon(){
		return oCalendarSelectVon;
	}

	/**
	 * gibt das Kalender-Objekt zurück (bis)
	 * @return
	 */
	public E2_CalendarComponent_WithDayButtons get_oCalendarObjectBis(){
		return oCalendarSelectVon;
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
	public MyDate  get_oDateFromTextFieldVon() throws myException
	{
		MyDate  oDate = new MyDate(this.oTextFieldVon.getText());
		
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
	 * @returns Date-object wenn alles ok oder null
	 * @throws myException
	 */
	public MyDate  get_oDateFromTextFieldBis() throws myException
	{
		MyDate  oDate = new MyDate(this.oTextFieldBis.getText());
		
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
	public MyDate  get_oDateFromTextFieldVon_even_when_not_OK() throws myException
	{
		return new MyDate(this.oTextFieldVon.getText());
	}

	/**
	 * 
	 * @returns Date-object (immer, evtl. mit fehlerstatus im MyDate-Object)
	 * @throws myException
	 */
	public MyDate  get_oDateFromTextFieldBis_even_when_not_OK() throws myException
	{
		return new MyDate(this.oTextFieldBis.getText());
	}

	
	
	/**
	 * 
	 * @returns formatiertes Datum (31.12.2011) wenn alles ok oder ""
	 * @throws myException
	 */
	public String  get_oFormatedDateFromTextFieldVon() throws myException
	{
		MyDate  oDate = new MyDate(this.oTextFieldVon.getText());
		
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
	public String  get_oDBFormatedDateFromTextFieldVon() throws myException
	{
		MyDate  oDate = new MyDate(this.oTextFieldVon.getText());
		
		if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			return oDate.get_cDBFormatErgebnis();
		}
		else
		{
			return "";
		}
	}


	
	/**
	 * 
	 * @returns formatiertes Datum (31.12.2011) wenn alles ok oder ""
	 * @throws myException
	 */
	public String  get_oFormatedDateFromTextFieldBis() throws myException
	{
		MyDate  oDate = new MyDate(this.oTextFieldBis.getText());
		
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
	public String  get_oDBFormatedDateFromTextFieldBis() throws myException
	{
		MyDate  oDate = new MyDate(this.oTextFieldBis.getText());
		
		if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			return oDate.get_cDBFormatErgebnis();
		}
		else
		{
			return "";
		}
	}

	
	
	
	
	
	private class ownActionCleanTextFeld extends XX_ActionAgent
	{
		private MyE2_TextField oTF_to_erase = null;
		
		
		public ownActionCleanTextFeld(MyE2_TextField TF_to_erase)
		{
			super();
			this.oTF_to_erase = TF_to_erase;
		}


		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			this.oTF_to_erase.setText("");
		}
	}

	
	
	public boolean get_bAutoCloseOnBisCalendar()
	{
		return bAutoCloseOnBisCalendar;
	}



	public void set_bAutoCloseOnBisCalendar(boolean bAutoCloseOnBisCalendar)
	{
		this.bAutoCloseOnBisCalendar = bAutoCloseOnBisCalendar;
	}

	
	public boolean get_bShowOKButton()
	{
		return bShowOKButton;
	}



	public void set_bShowOKButton(boolean bShowOKButton)
	{
		this.bShowOKButton = bShowOKButton;
	}

	
	public MyE2_Button get_oButtonOK()
	{
		return oButtonOK_and_close;
	}

	
}
