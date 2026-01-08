package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import java.util.HashMap;
import java.util.Map.Entry;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.E2_CalendarComponent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE.DoAnyThingWithAll;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public class FU_Selector_Fahrplan_FP_Button extends MyE2_Button
{
	private RECLIST_VPOS_TPA_FUHRE  oRecs = null;
	private String 					cDateStringYYYY_MM = null;     

	private PopupContainer   		oPopupContainer = null;
	
	private String      			cID_SELECTED_LKW = null;
	private String    				cSELECTED_DATE = null;
	
	private XX_ActionAgent   		o_Action4Selection = null;
	
	//der anzeige-container in der maske 
	private FU_Selector_Fahrplan_GRID  grid_FP_Selector_Anzeige = null;
	
	public FU_Selector_Fahrplan_FP_Button()
	{
		super(E2_ResourceIcon.get_RI("calendar.png"), true);
		
		this.setToolTipText(new MyE2_String("Selektion eines Fahrplans ...").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
	}

	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			int iMonat = new Integer(bibALL.get_cMonthNow()).intValue();
			int iJahr = new Integer(bibALL.get_cYearNow()).intValue();

			if (S.isFull(FU_Selector_Fahrplan_FP_Button.this.cSELECTED_DATE)) {
				MyDate oDate = new MyDate(FU_Selector_Fahrplan_FP_Button.this.cSELECTED_DATE);
				
				if (oDate.get_bOK()) {
					iMonat = oDate.get_INT_MONAT().get_oInteger();
					iJahr = oDate.get_INT_JAHR().get_oInteger();
				}
			}
			
			FU_Selector_Fahrplan_FP_Button.this.oPopupContainer= new PopupContainer(iMonat,iJahr);
		}
	}
	

	
	private class PopupContainer extends E2_BasicModuleContainer
	{

		public PopupContainer(int Monat, int Jahr) throws myException
		{
			super();
			
			this.add(new ownCalendar(Monat,Jahr));
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(400), new MyE2_String("Monatsübersicht"));
		}
		
		
	}
		
	
	
	private class ownCalendar extends E2_CalendarComponent
	{
		private HashMap<String, String>  hmLKWSammler = null;
		private String cActualDayFormated = null;
		
		public ownCalendar(int Monat, int Jahr)	throws myException
		{
			super(Monat, Jahr, new Insets(3,3,3,3), true,true, MyE2_Grid.STYLE_GRID_DDARK_BORDER_INSETS_11(), null);
		}

		@Override
		public Component buildDayComponent(E2_CalendarComponent ownCalendarGrid,	myDateHelper dateHelperActualDay) throws myException
		{
			this.cActualDayFormated = dateHelperActualDay.get_cDateFormatForMask();
			
			MyE2_Row oRow = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			oRow.add(new MyE2_Label(new MyE2_String(""+dateHelperActualDay.get_IDay(),false)),E2_INSETS.I_2_2_2_2);
			
			MyE2_PopUpMenue  oPopUpFahrplaene = new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("popup.png"),E2_ResourceIcon.get_RI("popup.png"),false);

			// wenn monat-jahr in der FPT_BUTTON_OpenCalendar.this.oRecs nicht mit dateHelper uebereinstimmt, dann muss es von vorne gefuellt werden
			// z.B. wenn der monat gewechselt wurde
			if (!dateHelperActualDay.get_cDateFormat_ISO_FORMAT().substring(0,7).equals(FU_Selector_Fahrplan_FP_Button.this.cDateStringYYYY_MM))
			{
				FU_Selector_Fahrplan_FP_Button.this.oRecs = null;       //neue Abfrage erzwingen
			}
			
			
			//jetzt die lkws, in diesem monat rausziehen
			if (FU_Selector_Fahrplan_FP_Button.this.oRecs ==null)
			{
				FU_Selector_Fahrplan_FP_Button.this.oRecs = new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM JT_VPOS_TPA_FUHRE WHERE NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N' AND  ID_MASCHINEN_LKW_FP IS NOT NULL AND TO_CHAR(DAT_FAHRPLAN_FP,'yyyy-MM')='"+dateHelperActualDay.get_cDateFormat_ISO_FORMAT().substring(0,7)+"'");
				FU_Selector_Fahrplan_FP_Button.this.cDateStringYYYY_MM = dateHelperActualDay.get_cDateFormat_ISO_FORMAT().substring(0,7);
			}
			
			//jetzt die an dem besagten tag als unterliste rausholen
			RECLIST_VPOS_TPA_FUHRE oSubRecs = FU_Selector_Fahrplan_FP_Button.this.oRecs.get_SUBLIST(new RECLIST_VPOS_TPA_FUHRE.Validation()
			{
				@Override
				public boolean isValid(RECORD_VPOS_TPA_FUHRE orecord) throws myException
				{
					return ownCalendar.this.cActualDayFormated.equals(orecord.get_DAT_FAHRPLAN_FP_cF());
				}
			});
			
			if (oSubRecs.size()==0)
			{
				oRow.add(new MyE2_Label(E2_ResourceIcon.get_RI("popup_ganz_hell.png")),E2_INSETS.I_2_2_2_2);
			}
			else
			{
				oRow.add(oPopUpFahrplaene,E2_INSETS.I_2_2_2_2);
				
				//jetzt die bereits verwendeten lkw's als button ins popup-menue
				if (ownCalendar.this.hmLKWSammler==null)
				{
					ownCalendar.this.hmLKWSammler=new HashMap<String, String>();
				}
				ownCalendar.this.hmLKWSammler.clear();
				
				oSubRecs.DoAnyThing(new DoAnyThingWithAll()
				{
					@Override
					public void doAnyThingWith(String hashKey,RECORD_VPOS_TPA_FUHRE orecord) throws myException
					{
						if (!ownCalendar.this.hmLKWSammler.containsKey(orecord.get_ID_MASCHINEN_LKW_FP_cUF()))    //es muss die unformatierte lkw-id sein
						{
							ownCalendar.this.hmLKWSammler.put(orecord.get_ID_MASCHINEN_LKW_FP_cUF(), orecord.get_UP_RECORD_MASCHINEN_id_maschinen_lkw_fp().get_KFZKENNZEICHEN_cF_NN("<ohne Kennzeichen>"));
						}
					}
				});
				
				
				//jetzt die buttons bauen
				for (Entry<String, String> oEntry: ownCalendar.this.hmLKWSammler.entrySet())
				{
					oPopUpFahrplaene.addButton(new buttonSelFahrplanAusPopup(dateHelperActualDay.get_cDateFormatForMask(),oEntry.getValue(),oEntry.getKey()),true);
				}
			}
			
			return oRow;
		}
	}
	
	
	private class buttonSelFahrplanAusPopup extends MyE2_Button
	{

		public buttonSelFahrplanAusPopup(String cDatum, String cLKW_Nummer, String cID_LKW_UnFormated) throws myException
		{
			super(new MyE2_String(cLKW_Nummer,false));;
			this.EXT().set_C_MERKMAL(cID_LKW_UnFormated);
			this.EXT().set_C_MERKMAL2(cDatum);
			
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					FU_Selector_Fahrplan_FP_Button.this.oPopupContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					
					//hier wird immer zuerst resettet
					FU_Selector_Fahrplan_FP_Button.this.cID_SELECTED_LKW = 	((MyE2_Button)execInfo.get_MyActionEvent().getSource()).EXT().get_C_MERKMAL();
					FU_Selector_Fahrplan_FP_Button.this.cSELECTED_DATE = 	((MyE2_Button)execInfo.get_MyActionEvent().getSource()).EXT().get_C_MERKMAL2();

				}
				
			});
			
			//das anzeige-grid refreshen
			this.add_oActionAgent(new ownActionAktualisiereAnzeige());
			
			//ausloesen der selektion mus nachher erfolgen
			this.add_oActionAgent(FU_Selector_Fahrplan_FP_Button.this.o_Action4Selection);

		}
	}

	
	public String get_cWhereStatement() throws myException {
		if (S.isFull(this.cID_SELECTED_LKW) && S.isFull(this.cSELECTED_DATE)) {
			
			String cSQL = 	 "(" +_DB.VPOS_TPA_FUHRE+ "."+_DB.VPOS_TPA_FUHRE$ID_MASCHINEN_LKW_FP+"="+this.cID_SELECTED_LKW+" AND "
							 +"TO_CHAR("+_DB.VPOS_TPA_FUHRE+ "."+_DB.VPOS_TPA_FUHRE$DAT_FAHRPLAN_FP+",'DD.MM.YYYY')='"+this.cSELECTED_DATE+"')";
			
			return cSQL;
		} else {
			return "";
		}
	}


	public void set_oAction4Selection(XX_ActionAgent oAction4Selection) {
		this.o_Action4Selection = oAction4Selection;
	}
	
	
	public void ClearSelection() throws myException {
		//hier wird immer zuerst resettet
		this.cID_SELECTED_LKW = 	null;
		this.cSELECTED_DATE = 	null;
		this.grid_FP_Selector_Anzeige.fill_Grid();
	}


	public String get_cID_SELECTED_LKW() {
		return cID_SELECTED_LKW;
	}


	public String get_cSELECTED_DATE() {
		return cSELECTED_DATE;
	}


	public FU_Selector_Fahrplan_GRID get_grid_FP_Selector_Anzeige() {
		return grid_FP_Selector_Anzeige;
	}


	public void set_grid_FP_Selector_Anzeige(FU_Selector_Fahrplan_GRID grid_FP_Selector_Anzeige) {
		this.grid_FP_Selector_Anzeige = grid_FP_Selector_Anzeige;
	}
	
	
	private class ownActionAktualisiereAnzeige extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (FU_Selector_Fahrplan_FP_Button.this.grid_FP_Selector_Anzeige != null) {
				FU_Selector_Fahrplan_FP_Button.this.grid_FP_Selector_Anzeige.fill_Grid();
			}
		}
		
	}
	
}
