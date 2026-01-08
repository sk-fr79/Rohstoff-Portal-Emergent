package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.DATUMSKORREKTUR;

import java.util.Date;
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
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
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date_Button;
import panter.gmbh.Echo2.components.E2_calendar.E2_calendar;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_Date_Selection_Von_Bis_Container_v2 extends E2_BasicModuleContainer{

	//	private This_date_component 			date_selektion_comp = null;
	private MyE2_TextField					startTf 		= null;
	private MyE2_TextField					endTf 			= null;

	private String startDate ;
	private E2_TF_4_Date_Button startBt = null;

	private String endDate;
	private E2_TF_4_Date_Button endBt = null;

	private MyE2_MessageVector omv;

	private boolean bOk = false;

	//	private boolean firstLoad = true;

	private simpleCalendar kalenderVon = null;
	private simpleCalendar kalenderBis = null;

	public E2_Date_Selection_Von_Bis_Container_v2(String cActualDayFormated) throws myException {
		super();

		this.startDate = "";	
		this.endDate = "";

		_build(cActualDayFormated);
	}

	public E2_Date_Selection_Von_Bis_Container_v2() throws myException{
		super();

		this.startDate = "";	
		this.endDate = "";


		_build(bibALL.get_cDateNOW());
	}

	public E2_Date_Selection_Von_Bis_Container_v2(String formatedDatumVon, String formatedDatumBis) throws myException{
		super();
		if(S.isEmpty(formatedDatumVon)){
			this.startDate = myDateHelper.get_cCalendarActual(-30);//bibALL.get_cDateNOW();
		}else{
			this.startDate = formatedDatumVon;	
		}

		if(S.isEmpty(formatedDatumBis)){
			this.endDate = bibALL.get_cDateNOW();
		}else{
			this.endDate = formatedDatumBis;
		}

		_build("");

		setDatumRange(
				new myDateHelper(startDate), 
				new myDateHelper(endDate)
				);
	}

	public E2_Date_Selection_Von_Bis_Container_v2(MyRECORD record, IF_Field datum_field_von, IF_Field datum_field_bis) throws myException{
		super();

		this.startDate = myDateHelper.ChangeDBFormatStringToNormalString(record.get_UnFormatedValue(datum_field_von.fieldName()));	
		this.endDate = myDateHelper.ChangeDBFormatStringToNormalString(record.get_UnFormatedValue(datum_field_bis.fieldName()));

		_build(startDate);

		setDatumRange(
				new myDateHelper(startDate), 
				new myDateHelper(endDate)
				);

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
		if(S.isFull(datumVon.get_cDateFormatForMask())){
			this.startDate 				= datumVon.get_cDateFormatForMask();
			this.startTf.setText(startDate);
		}
		if(S.isFull(datumBis.get_cDateFormatForMask())){
			this.endDate 				= datumBis.get_cDateFormatForMask();
			this.endTf.setText(endDate);
		}
	}

	private void setStartdate(String formatedDate, boolean isStartDate) throws myException{
		if(S.isFull(formatedDate)){
			if(isStartDate){
				this.startDate = formatedDate;
				this.startTf.setText(startDate);
			}else{
				this.endDate = formatedDate;
				this.endTf.setText(endDate);
			}
		}
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
		if(S.isEmpty(cActualDayFormated)){
			this.kalenderVon = new simpleCalendar(startDate, true);
			this.kalenderBis = new simpleCalendar(endDate, false);
		}else{
			this.kalenderVon = new simpleCalendar(cActualDayFormated, true);
			this.kalenderBis = new simpleCalendar(cActualDayFormated, false);
		}
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

		MyE2_Grid KalenderGrid = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		KalenderGrid.add(kalenderVon, E2_INSETS.I(10,0,10,0));
		KalenderGrid.add(kalenderBis, E2_INSETS.I(25,0,10,0));

		MyE2_Grid popUpPanel = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		popUpPanel.add(headGrid, 1, 1, E2_INSETS.I(8,0,0,0), E2_ALIGN.LEFT_MID);
		popUpPanel.add(new Separator());
		popUpPanel.add(KalenderGrid);
		popUpPanel.add(footGrid, 1, 1, E2_INSETS.I(0,7,0,0), E2_ALIGN.RIGHT_MID);

		this.add(popUpPanel);
	}

	/**
	 * Hier wird die Daten und die UI zurückgestellt
	 * @throws myException
	 */
	private void resetSelection() throws myException{
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

				E2_Date_Selection_Von_Bis_Container_v2.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}else bibMSG.add_MESSAGE(omv);
		}

	}

	private class saveDataValidator extends XX_ActionValidator{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			if(			S.isEmpty(startDate) && S.isEmpty(endDate)){
				omv.add_MESSAGE(new MyE2_Alarm_Message("Es ist weder ein Start- noch ein End-Datum angegeben"));
			} else if(	S.isEmpty(startDate)){
				omv.add_MESSAGE(new MyE2_Alarm_Message("Es wurde kein Start-Datum angegeben"));	
			} else if(	S.isEmpty(endDate)){
				omv.add_MESSAGE(new MyE2_Alarm_Message("Es wurde kein End-Datum angegeben"));	
			} else if(	myDateHelper.get_Date1_Greater_Date2(startDate, endDate)){
				omv.add_MESSAGE(new MyE2_Alarm_Message("Ungültiger Bereich: das End-Datum liegt vor dem Start-Datum"));				
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

	private class simpleCalendar extends E2_calendar{

		private boolean isStart = false;
		public simpleCalendar(String c_actual_day_formated, boolean isStartCalender) throws myException {
			super(c_actual_day_formated);
			this.isStart = isStartCalender;
		}

		@Override
		public void do_day_button_action(ExecINFO execInfo) throws myException {
			omv.removeAllElements();
			MyE2_Button oDateButton = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
			String lastDatum = ((myDateHelper)oDateButton.EXT().get_O_PLACE_FOR_EVERYTHING()).get_cDateFormatForMask();
			setStartdate(lastDatum,	this.isStart);
			this.get_calendar_body().SPRINGE_AUF_DATUM(lastDatum);
			oDateButton._bord(new Border(1,Color.BLACK,Border.STYLE_SOLID));
		}

		@Override
		public Vector<Date> get_DatesToHighlight() throws myException {
			return null;
		}

	}

}
