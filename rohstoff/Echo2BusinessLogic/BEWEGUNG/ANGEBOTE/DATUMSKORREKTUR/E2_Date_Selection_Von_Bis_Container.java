package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.DATUMSKORREKTUR;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.E2_calendar.E2_CalendarComponent_Version2;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_Date_Selection_Von_Bis_Container extends E2_BasicModuleContainer{

	private This_date_component 			date_selektion_comp = null;
	private MyE2_TextField					startTf 		= null;
	private MyE2_TextField					endTf 			= null;

	private boolean bStart 		= false;
	private String startDate ;
	private E2_TF_4_Date_Button startBt = null;

	private boolean bEnd 		= false;
	private String endDate;
	private E2_TF_4_Date_Button endBt = null;

	private MyE2_MessageVector omv;

	private boolean bOk = false;
	
	private boolean firstLoad = true;
	
	public E2_Date_Selection_Von_Bis_Container(String cActualDayFormated) throws myException {
		super();

		this.startDate = "";	
		this.endDate = "";

		_build(cActualDayFormated);
	}

	public E2_Date_Selection_Von_Bis_Container() throws myException{
		super();

		this.startDate = myDateHelper.get_actualDateString(-30);	
		this.endDate = bibALL.get_cDateNOW();


		_build(bibALL.get_cDateNOW());
		
		setDatumRange(
				new myDateHelper(startDate), 
				new myDateHelper(endDate)
				);
	}
	
	public E2_Date_Selection_Von_Bis_Container(MyRECORD record, IF_Field datum_field_von, IF_Field datum_field_bis) throws myException{
		super();

		this.startDate = myDateHelper.ChangeDBFormatStringToNormalString(record.get_UnFormatedValue(datum_field_von.fieldName()));	
		this.endDate = myDateHelper.ChangeDBFormatStringToNormalString(record.get_UnFormatedValue(datum_field_bis.fieldName()));

		_build(startDate);

		setDatumRange(
				new myDateHelper(startDate), 
				new myDateHelper(endDate)
				);
		this.date_selektion_comp.updateCalendar_onLoad(new myDateHelper(startDate), new myDateHelper(endDate));
	}

	/**
	 * hier definiert wie sind die Daten gespeichert
	 * @throws myException
	 */
	public abstract void saveData() throws myException;

	/**
	 * Selektiert
	 * @param datumVon
	 * @param datumBis
	 * @throws myException
	 */
	public void setDatumRange(myDateHelper datumVon, myDateHelper datumBis) throws myException{
		this.startDate 				= datumVon.get_cDateFormatForMask();
		this.endDate 				= datumBis.get_cDateFormatForMask();

		this.startTf.setText(startDate);
		this.endTf.setText(endDate);

		this.bStart=true;
		this.bEnd=true;

		this.date_selektion_comp.set_iMonth(datumVon.get_IMonth());
		this.date_selektion_comp.set_iYear(datumVon.get_IYear());
		this.date_selektion_comp.baue_calender();
		this.date_selektion_comp.updateCalendar_onLoad(datumVon, datumBis);
	}

	/**
	 * @return Datum von
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @return Datum bis
	 */
	public String getEndDate() {
		return endDate;
	}

	public boolean isbOk() {
		return bOk;
	}

	/**
	 * Hier wird der Komponent gebaut.
	 * @param cActualDayFormated -> Die Heutige Datum
	 * @throws myException
	 */
	private void _build(String cActualDayFormated) throws myException{

		this.omv = new MyE2_MessageVector();

		this.date_selektion_comp = new This_date_component(
				new myDateHelper(cActualDayFormated).get_IMonth(), 	//Monate
				new myDateHelper(cActualDayFormated).get_IYear(), 	//Jahr
				E2_INSETS.I_2_2_2_2, 								//Insets
				true, 												//Navigator
				MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11(),			//Default Style
				cActualDayFormated 								//Die Default Datum
				);

		this.date_selektion_comp.baue_calender();

		this.startTf = new MyE2_TextField();
		this.startTf.set_iWidthPixel(120);
		this.startTf.set_bEnabled_For_Edit(false);
		this.endTf = new MyE2_TextField();
		this.endTf.set_bEnabled_For_Edit(false);
		this.endTf.set_iWidthPixel(120);
		
		MyE2_Button bt_speichern = new MyE2_Button(new MyE2_String("Ok"), new MyE2_String("") ,new ownUpdateDatabaseActionAgent());
		bt_speichern.add_GlobalValidator(new saveDataValidator());

		MyE2_Grid headGrid = new MyE2_Grid(5, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		headGrid.add(new RB_lab("Von:")._b()._i(),E2_INSETS.I(5,0,10,0));
		headGrid.add(startTf);
		headGrid.add(new RB_lab("Bis:")._b()._i(), E2_INSETS.I(40,0,10,0));
		headGrid.add(endTf);
		headGrid.add(new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"), new MyE2_String(""), new eraseActionAgent()), E2_INSETS.I(15,0,0,0));
		
		MyE2_Grid footGrid = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		footGrid.add(bt_speichern);
		
		MyE2_Grid popUpPanel = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		popUpPanel.add(headGrid, 1, 1, E2_INSETS.I(8,0,0,0), E2_ALIGN.LEFT_MID);
		popUpPanel.add(new Separator());
		popUpPanel.add(date_selektion_comp);
		popUpPanel.add(footGrid, 1, 1, E2_INSETS.I(0,7,0,0), E2_ALIGN.RIGHT_MID);
		
		this.add(popUpPanel);
	}

	/**
	 * Hier wird die Daten und die UI zurückgestellt
	 * @throws myException
	 */
	private void resetSelection() throws myException{
		bStart=false;
		bEnd=false;
		startDate="";
		endDate="";
		startTf.setText("");
		endTf.setText("");

		if(!(startBt == null)){
			startBt.setStyle(MyE2_Button.StyleTextButton());
			startBt.setFont(new E2_FontPlain());
			startBt.setBorder(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
			startBt.setForeground(new E2_ColorGray(128));
			startBt.setBackground(new E2_ColorDark());
		}

		if(!(endBt == null)){
			endBt.setStyle(MyE2_Button.StyleTextButton());
			endBt.setFont(new E2_FontPlain());
			endBt.setBorder(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
			endBt.setForeground(new E2_ColorGray(128));
			endBt.setBackground(new E2_ColorDark());
		}

		this.date_selektion_comp.resetCalendar();
	}

	private void updateButtonStyle(E2_TF_4_Date_Button oBt, boolean isStartDateOrEnDate) {
		if(isStartDateOrEnDate){
			oBt.setBorder(new Border(1,Color.BLACK,Border.STYLE_SOLID));
			oBt.setFont(new E2_FontBold());
		}else{
			oBt.setFont(new E2_FontItalic());
		}
		oBt.setForeground(Color.BLACK);
		
		oBt.setBackground(new E2_ColorLLight());
	}

	/**
	 * Action Agent angeruft für speichern
	 * @author sebastien
	 */
	private class ownUpdateDatabaseActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(omv.get_bIsOK()){
				bOk = true;
				saveData();

				E2_Date_Selection_Von_Bis_Container.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}else bibMSG.add_MESSAGE(omv);
		}

	}

	private class saveDataValidator extends XX_ActionValidator{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
				if(!(bStart && bEnd)){
					omv.add_MESSAGE(new MyE2_Warning_Message("Ungültiger Bereich: Kein Start- oder End-Datum "));
					
					if(! startDate.equals(endDate)){
					return omv;
				}else{
					if(myDateHelper.get_Date1_Greater_Date2(startDate, endDate)){
						omv.add_MESSAGE(new MyE2_Alarm_Message("Ungültiger Bereich: das End-Datum liegt vor dem Start-Datum"));				}
					if(myDateHelper.get_Date1_Less_Date2(endDate, startDate)){
						omv.add_MESSAGE(new MyE2_Alarm_Message("Ungültiger Bereich: das End-Datum liegt vor dem Start-Datum"));
					}
				}
			}
			return omv;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {return null;}

	}

	/**
	 * Action Agent angeruft für der komponent zurückstellen
	 * @author sebastien
	 */
	private class eraseActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			resetSelection();
		}

	}

	/**
	 * Calendar Komponent
	 * @author sebastien
	 *
	 */
	private class This_date_component extends E2_CalendarComponent_Version2{

		public This_date_component(int Monat, int Jahr, Insets InsetsForButtons, boolean showNavigators,
				MutableStyle oStyleForGrid, String SelectedDayFormated) throws myException {
			super(Monat, Jahr, InsetsForButtons, showNavigators, oStyleForGrid, SelectedDayFormated);

		}

		@Override
		public Vector<Date> get_DatesToHighlight() throws myException {
			Vector<Date> vDate = new Vector<>();
			try {
				if(bStart){
					vDate.add(new SimpleDateFormat("dd.MM.yyyy").parse(startDate));
				}
				if(bEnd){
					vDate.add(new SimpleDateFormat("dd.MM.yyyy").parse(endDate));
				}
			} catch (ParseException e) {
			}
			return vDate;
		}

		@Override
		public void saveAction(ExecINFO oExecInfo) throws myException {
			if(bStart && bEnd){
				saveData();
			}else{
				omv.add_MESSAGE(new MyE2_Alarm_Message("Ungültiges Datum, es wurde nichts gespeichert"));
			}
		}


		public void updateCalendar(myDateHelper dateVon, myDateHelper dateBis) throws myException{
			this.highlight_date_von_bis(dateVon, dateBis);
		}

		public void updateCalendar_onLoad(myDateHelper dateVon, myDateHelper dateBis) throws myException{
			this.highlight_date_von_bis(dateVon, dateBis);
		}

		@Override
		public Component buildDayComponent(E2_CalendarComponent_Version2 ownCalendarGrid, myDateHelper oDateHelper,
				boolean bActiveMonth) throws myException {

			E2_TF_4_Date_Button oDateButton = new E2_TF_4_Date_Button("" + oDateHelper.get_IDay());

			oDateButton.setActiveMonth(false);
			oDateButton.add_oActionAgent(new ownDateButtonActionAgent());
			oDateButton.EXT().set_O_PLACE_FOR_EVERYTHING(oDateHelper);

			myDateHelper mdhStartDate 	= null;
			myDateHelper mdhEndDate 	= null;
			try
			{
				if(S.isFull(startDate)){
					mdhStartDate = new myDateHelper(startDate);
				} 

				if(S.isFull(endDate)){
					mdhEndDate = new myDateHelper(endDate);
				}
			}catch (myException e){
				e.printStackTrace();
			}

			if(mdhStartDate!=null && oDateHelper.equals(mdhStartDate)){
				startBt = oDateButton;
			}

			if(mdhEndDate!=null && oDateHelper.equals(mdhEndDate)){
				endBt= oDateButton;
			}

			if ((mdhStartDate!=null && oDateHelper.equals(mdhStartDate))||
					(mdhEndDate!=null && oDateHelper.equals(mdhEndDate))
					){

				updateButtonStyle(oDateButton, true);
			}else if(mdhStartDate!=null && mdhEndDate!=null &&
					myDateHelper.get_Date1_GreaterEqual_Date2(oDateHelper.get_cDateFormatForMask(), mdhStartDate.get_cDateFormatForMask()) &&
					myDateHelper.get_Date1_LessEqual_Date2(oDateHelper.get_cDateFormatForMask(), mdhEndDate.get_cDateFormatForMask())
					){
				updateButtonStyle(oDateButton, false);
			}
			return oDateButton;
		}	
	}

	private class ownDateButtonActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_TF_4_Date_Button oBt = (E2_TF_4_Date_Button) oExecInfo.get_MyActionEvent().getSource();
			myDateHelper startDateHelper 		= null;
			myDateHelper endDateHelper			= null;
			
			myDateHelper datum = (myDateHelper) oBt.EXT().get_O_PLACE_FOR_EVERYTHING();
			String strDatum = datum.get_cDateFormatForMask();
			
			
			if(bStart && bEnd){
				if(firstLoad){
					firstLoadProcedure(oBt, strDatum);					
				}else{
					normalProcedure(oBt, strDatum);
				}
				
			}else{

				firstLoad = false;

				updateButtonStyle(oBt, true);

				if(!bStart){
					startDateHelper =  datum;
					startDate = startDateHelper.get_cDateFormatForMask();
					startTf.setText(startDate);
					endDate = startDateHelper.get_cDateFormatForMask();
					endTf.setText(endDate);
					bStart=true;
					startBt = oBt;
				}else{
					endDateHelper =  datum;
					endDate = endDateHelper.get_cDateFormatForMask();
					bEnd=true;
					endBt = oBt;
				}

				if(bStart && bEnd){
					boolean endForStart = myDateHelper.get_Date1_Less_Date2(endDate, startDate);
					if(endForStart){
						endDate		= startDate;
						startDate 	= endDateHelper.get_cDateFormatForMask();
					}
					date_selektion_comp.updateCalendar(new myDateHelper(startDate), new myDateHelper(endDate));
				}
				startTf.setText(startDate);
				endTf.setText(endDate);
			}
		}

		private void firstLoadProcedure(E2_TF_4_Date_Button oBt, String datum) throws myException {

			resetSelection();

			updateButtonStyle(oBt, true);
	
			startDate = datum;
			startTf.setText(startDate);
			endDate = datum;
			endTf.setText(endDate);
			bStart=true;
			startBt = oBt;
		}


	}
	
	private void normalProcedure(E2_TF_4_Date_Button oBt, String datum) throws myException{
		if(myDateHelper.get_Date1_Less_Date2(datum, startDate)){
			startDate = datum;
		}else if(myDateHelper.get_Date1_Greater_Date2(datum, startDate) && myDateHelper.get_Date1_Less_Date2(datum, endDate)){
			startDate=datum;
		}else if(datum.equals(startDate) || datum.equals(endDate)){
			startDate = datum;
			endDate = datum;
		}
		else{
			endDate = datum;
		}
		startTf.setText(startDate);
		endTf.setText(endDate);
		
		date_selektion_comp.updateCalendar(new myDateHelper(startDate), new myDateHelper(endDate));

	}

}
