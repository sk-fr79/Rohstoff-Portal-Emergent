package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

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
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE.DoAnyThingWithAll;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public class FPT_BUTTON_OpenCalendar extends MyE2_Button
{

	private E2_DateBrowser  		oDateBrowser = null;
	private MyE2_SelectField 		oSelectLKWs = null;
	private MyE2_Button 			oButtonOpenFP = null;
	
	private RECLIST_VPOS_TPA_FUHRE  oRecs = null;
	private String 					cDateStringYYYY_MM = null;     

	private PopupContainer   		oPopupContainer = null;
	
	
	public FPT_BUTTON_OpenCalendar(E2_DateBrowser DateBrowser, MyE2_SelectField SelectLKWs, MyE2_Button ButtonOpenFP)
	{
		super(E2_ResourceIcon.get_RI("calendar.png"), true);
		
		this.oDateBrowser = DateBrowser;
		this.oSelectLKWs = 	SelectLKWs;
		this.oButtonOpenFP = ButtonOpenFP;
		this.setToolTipText(new MyE2_String("Übersicht über den Fahrplan-Monat ...").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
	}

	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_DateBrowser  ooDateBrowser =  FPT_BUTTON_OpenCalendar.this.oDateBrowser;
			
			//wenn der date-browser noch keinen eintrag hat, wird der aktuelle monat benutzt, sonst der monat des browsers
			int iMonat = new Integer(bibALL.get_cMonthNow()).intValue();
			int iJahr = new Integer(bibALL.get_cYearNow()).intValue();
			
			myDateHelper oDH = ooDateBrowser.get_oDateHelper();
			
			if (oDH != null)
			{
				iMonat = oDH.get_IMonth();
				iJahr = oDH.get_IYear();
			}
			FPT_BUTTON_OpenCalendar.this.oPopupContainer= new PopupContainer(iMonat,iJahr);
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
			if (!dateHelperActualDay.get_cDateFormat_ISO_FORMAT().substring(0,7).equals(FPT_BUTTON_OpenCalendar.this.cDateStringYYYY_MM))
			{
				FPT_BUTTON_OpenCalendar.this.oRecs = null;       //neue Abfrage erzwingen
			}
			
			
			//jetzt die lkws, in diesem monat rausziehen
			if (FPT_BUTTON_OpenCalendar.this.oRecs ==null)
			{
				FPT_BUTTON_OpenCalendar.this.oRecs = new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM JT_VPOS_TPA_FUHRE WHERE NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N' AND  ID_MASCHINEN_LKW_FP IS NOT NULL AND TO_CHAR(DAT_FAHRPLAN_FP,'yyyy-MM')='"+dateHelperActualDay.get_cDateFormat_ISO_FORMAT().substring(0,7)+"'");
				FPT_BUTTON_OpenCalendar.this.cDateStringYYYY_MM = dateHelperActualDay.get_cDateFormat_ISO_FORMAT().substring(0,7);
			}
			
			//jetzt die an dem besagten tag als unterliste rausholen
			RECLIST_VPOS_TPA_FUHRE oSubRecs = FPT_BUTTON_OpenCalendar.this.oRecs.get_SUBLIST(new RECLIST_VPOS_TPA_FUHRE.Validation()
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

		public buttonSelFahrplanAusPopup(String cDatum, String cLKW_Nummer, String cID_LKW_UnFormated)
		{
			super(new MyE2_String(cLKW_Nummer,false));;
			this.EXT().set_C_MERKMAL(cID_LKW_UnFormated);
			this.EXT().set_C_MERKMAL2(cDatum);
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					buttonSelFahrplanAusPopup oThis = buttonSelFahrplanAusPopup.this;
					
					FPT_BUTTON_OpenCalendar.this.oDateBrowser.get_oDatumsFeld().setText(oThis.EXT().get_C_MERKMAL2());
					FPT_BUTTON_OpenCalendar.this.oSelectLKWs.set_ActiveValue(oThis.EXT().get_C_MERKMAL());
					FPT_BUTTON_OpenCalendar.this.oButtonOpenFP.doActionPassiv();
					
					FPT_BUTTON_OpenCalendar.this.oPopupContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
				
			});
			

		}
		
	}
	
}
