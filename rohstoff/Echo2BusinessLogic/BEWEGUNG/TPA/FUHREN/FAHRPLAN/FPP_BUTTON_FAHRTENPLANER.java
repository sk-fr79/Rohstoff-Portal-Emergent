package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;


/*
 * umbuchen einer Fahrt oder Fahrtgruppe in einen anderen fahrplan
 */
public class FPP_BUTTON_FAHRTENPLANER extends MyE2_Button
{
	private E2_NavigationList 			oNaviList = null;
	
	/*
	 * komponenten fuer das popup-fenster
	 */
	private MyE2_Button					oButtonSTART_Planung	= null;
	private  E2_BasicModuleContainer   	oPopUpContainer = null;
	private PlanSchema					oPlanSchema	= new PlanSchema();
	private Vector<String> 				vSelectedIDs = null;
	
	
	
	public FPP_BUTTON_FAHRTENPLANER(	E2_NavigationList 	list)
	{
		super(new MyE2_String("Fahrtenplaner"));
		
		this.oNaviList = list;
		this.oButtonSTART_Planung = new MyE2_Button("Fahrten eintragen");
		
		this.add_oActionAgent(new ownActionAgentStart());
		this.oButtonSTART_Planung.add_oActionAgent(new ownActionAgentSave());
		
		this.add_IDValidator(new FP_Validator_FAHRT_GELOESCHT_ODER_STORNIERT());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("FAHRTEN_FOLGEWOCHEN_PLANEN"));
	}

	public PlanSchema	get_oPlanSchema()
	{
		return this.oPlanSchema;
	}

	
	
	private class ownActionAgentStart extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			FPP_BUTTON_FAHRTENPLANER oThis = FPP_BUTTON_FAHRTENPLANER.this;
			
			oThis.oPopUpContainer =  new E2_BasicModuleContainer();
			oThis.oPopUpContainer.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());
			
			oThis.vSelectedIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			if (oThis.vSelectedIDs.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte mindestens eine Fahrt auswählen !"));
				return;
			}
			
			
			try
			{
				bibMSG.add_MESSAGE(oThis.valid_IDValidation(oThis.vSelectedIDs));
				
				MyE2_Column		oCol =	new MyE2_Column();	
	
				// Window bestuecken ...
				// oCol nimmt die elemente auf
				MyE2_Label oLabTitel = new MyE2_Label(new MyE2_String("Fahrt(en) in die folgenden Wochen buchen"));
				oLabTitel.setFont(new E2_FontPlain(2));
				oCol.add(oLabTitel,new Insets(10,10,2,10));
				oCol.add(new Separator(),new Insets(2,2,2,2));
				
				/*
				 * selectfield fuer die anzuzeigenden wochen,
				 * ist aktiv  -- baut die checkbox-matrix auf
				 */
				String[][] cWochen = new String[11][2];
				
				cWochen[0][0] = "-"; 			cWochen[0][1]="0";
				cWochen[1][0] = "1 Woche"; 		cWochen[1][1]="1";
				cWochen[2][0] = "2 Wochen"; 	cWochen[2][1]="2";
				cWochen[3][0] = "3 Wochen"; 	cWochen[3][1]="3";
				cWochen[4][0] = "4 Wochen"; 	cWochen[4][1]="4";
				cWochen[5][0] = "5 Wochen"; 	cWochen[5][1]="5";
				cWochen[6][0] = "6 Wochen"; 	cWochen[6][1]="6";
				cWochen[7][0] = "7 Wochen"; 	cWochen[7][1]="7";
				cWochen[8][0] = "8 Wochen"; 	cWochen[8][1]="8";
				cWochen[9][0] = "9 Wochen"; 	cWochen[9][1]="9";
				cWochen[10][0] = "10 Wochen"; 	cWochen[10][1]="10";
				
				MyE2_SelectField oSelectWeeks = new MyE2_SelectField(cWochen,"0",true);
				oSelectWeeks.add_oActionAgent(new XX_ActionAgent() 
						{
							public void executeAgentCode(ExecINFO ExecInfo)
							{
								MyE2_SelectField oSel = (MyE2_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();
								int iZahl = 6;
								try
								{
									Integer IntVal = new Integer(oSel.get_ActualWert());
									iZahl = IntVal.intValue();
								}
								catch (Exception ex) {}
								
								FPP_BUTTON_FAHRTENPLANER.this.get_oPlanSchema().fillSchema(iZahl);
							}
						});
				
				/*
				 * ENDE SELECTFIELD	
				 */
				
				oCol.add(
						new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Anzahl Wochen: ")),oSelectWeeks,new Insets(2))
						,new Insets(10,10,2,10));

				oCol.add(new Separator(),new Insets(2,2,2,2));
				
				oCol.add(FPP_BUTTON_FAHRTENPLANER.this.get_oPlanSchema(),new Insets(10,10,2,10));
				oCol.add(new Separator(),new Insets(2,2,2,2));
				
				MyE2_Button oButtonCancel = new MyE2_Button(new MyE2_String("Abbruch"));
				
				oButtonCancel.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO o_ExecInfo) throws  myException
					{
						FPP_BUTTON_FAHRTENPLANER.this.oPopUpContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					}
				}
				);
				
				
				oThis.oPopUpContainer.add(oCol);
				oThis.oPopUpContainer.set_Component_To_ButtonPane(new E2_ComponentGroupHorizontal(0,FPP_BUTTON_FAHRTENPLANER.this.oButtonSTART_Planung,oButtonCancel,E2_INSETS.I_10_2_10_2));
				oThis.oPopUpContainer.CREATE_AND_SHOW_POPUPWINDOW_SPLIT( new Extent(500),new Extent(700),new MyE2_String("Fahrten planen ..."));
				
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Auswahl der Fahrten !"));
				return;
			}

		}
	}
	
	
	/*
	 * actionagent startet die planung/traegt die fuhren ein
	 */
	private class ownActionAgentSave extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			/*
			 * infos beschaffen
			 */
			FPP_BUTTON_FAHRTENPLANER 	oThis = 				FPP_BUTTON_FAHRTENPLANER.this;
			Vector<String> 				v_SelectedIDs = 		oThis.vSelectedIDs;
			Vector<MyE2_CheckBox>		vCheckBox = 			oThis.oPlanSchema.vCheckBoxesDate;
			
			Vector<String> vSQL = new Vector<String>();
			
			try
			{
				int iCount = 0;

				Vector<String> v_ids_new = new Vector<String>();

				
				for (int i=0;i<v_SelectedIDs.size();i++)
				{
					String cID = (String)v_SelectedIDs.get(i);
					
					for (int k=0;k<vCheckBox.size();k++)
					{
						MyE2_CheckBox oCB = (MyE2_CheckBox)vCheckBox.get(k);
						if (oCB.isSelected())
						{
							GregorianCalendar oZielDat = (GregorianCalendar)oCB.EXT().get_O_PLACE_FOR_EVERYTHING();
							Date oDate = oZielDat.getTime();
							
							String id_next = bibDB.get_NextSequenceValueOfTable(_TAB.vpos_tpa_fuhre);
							
							v_ids_new.add(id_next);
							
							vSQL.add(new FP_SQLCopyFuhre(cID,FP__ALL.CopyTyp.FAHRPLANPOOL_PLANER,id_next).get_cINSERT_String());
							
//							vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET EAN_CODE_FP='FU-'||TO_CHAR(ID_VPOS_TPA_FUHRE)," +
//																				" DAT_VORGEMERKT_FP="+bibALL.makeSQL(oDate,true)+",DAT_VORGEMERKT_ENDE_FP="+bibALL.makeSQL(oDate,true)+
//																				" WHERE ID_VPOS_TPA_FUHRE=(SELECT MAX(ID_VPOS_TPA_FUHRE) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE)");
							vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET EAN_CODE_FP='FU-'||TO_CHAR(ID_VPOS_TPA_FUHRE)," +
																				" DAT_VORGEMERKT_FP="+bibALL.makeSQL(oDate,true)+
																				",DAT_VORGEMERKT_ENDE_FP="+bibALL.makeSQL(oDate,true)+
																				",DATUM_ABHOLUNG="+bibALL.makeSQL(oDate,true)+
																				",DATUM_ABHOLUNG_ENDE="+bibALL.makeSQL(oDate,true)+
																				",DATUM_ANLIEFERUNG="+bibALL.makeSQL(oDate,true)+
																				",DATUM_ANLIEFERUNG_ENDE="+bibALL.makeSQL(oDate,true)+
																				" WHERE ID_VPOS_TPA_FUHRE="+id_next);
							iCount ++;
						}
					}
				}
				
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL,true));
				if (bibMSG.get_bIsOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzahl neu geplante Fuhren: "+iCount));
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Planen der Fahrten !!!"));
				}
				
				FPP_BUTTON_FAHRTENPLANER.this.oPopUpContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				
				/*
				 * liste neu aufbauen, dazu (positionierung) den ersten selektierten eintrag aktivieren
				 */
//				oThis.oNaviList.Mark_ID_IF_IN_Page((String)v_SelectedIDs.get(0));
//				oThis.oNaviList._REBUILD_COMPLETE_LIST("");
				
				
				oThis.oNaviList.ADD_NEW_ID_TO_ALL_VECTORS_IN_FRONT(v_ids_new);
				
				oThis.oNaviList.BUILD_ComponentMAP_Vector_from_ActualSegment();
				oThis.oNaviList.FILL_GRID_From_InternalComponentMAPs(true, true);

				Vector<String>  v_id_to_check = new Vector<String>();
				v_id_to_check.addAll(v_SelectedIDs);
				v_id_to_check.addAll(v_ids_new);
				
				oThis.oNaviList.set_CheckBox_To_AllIdsInVector(v_id_to_check);
				oThis.oNaviList.Mark_ID_IF_IN_Page(v_ids_new);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die originalen und zugefügten Fuhren sind angehakt, die neuen rot markiert !")));
				
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("FP_BUTTON_UMBUCHEN:ownActionAgentSave:doAction: Error: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			
		}
		
	}
	
	
	/**
	 * 2013-05-03: planungswerkzeug auf samstag erweitern
	 * @author martin
	 *
	 */
	/*
	 * plan mit den tagen
	 */
	private class PlanSchema extends MyE2_Grid
	{
		private Vector<MyE2_CheckBox> vCheckBoxesDate = new Vector<MyE2_CheckBox>();           // alle checkboxen mit den tagen
		
		public PlanSchema()
		{
			super(6);      // 6 spalten (montag bis samstag)
			this.fillSchema(0);
			
		}
		
		
		public void fillSchema(int iWeeks)
		{
			this.vCheckBoxesDate.removeAllElements();
			this.removeAll();
			
			Insets oIn = new Insets(5,5,5,5);
			
			this.add(new MyE2_Label(new MyE2_String("Montag")),oIn);
			this.add(new MyE2_Label(new MyE2_String("Dienstag")),oIn);
			this.add(new MyE2_Label(new MyE2_String("Mittwoch")),oIn);
			this.add(new MyE2_Label(new MyE2_String("Donnerstag")),oIn);
			this.add(new MyE2_Label(new MyE2_String("Freitag")),oIn);
			this.add(new MyE2_Label(new MyE2_String("Samstag")),oIn);
			
			
			if (iWeeks<=0)
				return;                      // nur ueberschrift
			
			
			GregorianCalendar 	oCalStart 			= new GregorianCalendar();             				// heute
			
			
			DateFormat 			df = 		DateFormat.getDateInstance(DateFormat.SHORT);
			
			int iCountWeeks = 0;
			
			/*
			 * aktuellen montag suchen (GregorianCalendar.DAY_OF_WEEK:Sonntag = 1)
			 */
			
			for (int i=7;i>0;i--)
			{
				if (oCalStart.get(GregorianCalendar.DAY_OF_WEEK)==2)    //montag gefunden, raus aus der schleife
					break;
				else
					oCalStart.add(GregorianCalendar.DATE,-1);
			}
			
			
			// schleife beginnt mit aktuellem montag
			do
			{

				if (oCalStart.get(GregorianCalendar.DAY_OF_WEEK)==1)   // sonntag
				{
					oCalStart.add(GregorianCalendar.DATE,1);   //sprung auf montag folgewoche
					iCountWeeks++;
					
					if (iCountWeeks>=iWeeks)
						break;
					
					continue;
				}
				
				String cTag = (df.format(oCalStart.getTime())).substring(0,5);    //dd.mm
				MyE2_CheckBox oCB = new MyE2_CheckBox(cTag);
				this.add(oCB,oIn);
				this.vCheckBoxesDate.add(oCB);
				oCB.EXT().set_O_PLACE_FOR_EVERYTHING(oCalStart.clone());
				oCB.EXT().set_I_MERKMAL(new Integer(iCountWeeks));                       // merkt sich die planungswoche
				oCalStart.add(GregorianCalendar.DATE,1);
				oCB.add_oActionAgent(new checkBoxActionAgent(this.vCheckBoxesDate));
				
			} 
			while (iCountWeeks<iWeeks);
		
		}
	}
	
	
//	private class PlanSchema extends MyE2_Grid
//	{
//		private Vector<MyE2_CheckBox> vCheckBoxesDate = new Vector<MyE2_CheckBox>();           // alle checkboxen mit den tagen
//		
//		public PlanSchema()
//		{
//			super(5);      // 5 spalten
//			this.fillSchema(0);
//			
//		}
//		
//		
//		public void fillSchema(int iWeeks)
//		{
//			this.vCheckBoxesDate.removeAllElements();
//			this.removeAll();
//			
//			Insets oIn = new Insets(5,5,5,5);
//			
//			this.add(new MyE2_Label(new MyE2_String("Montag")),oIn);
//			this.add(new MyE2_Label(new MyE2_String("Dienstag")),oIn);
//			this.add(new MyE2_Label(new MyE2_String("Mittwoch")),oIn);
//			this.add(new MyE2_Label(new MyE2_String("Donnerstag")),oIn);
//			this.add(new MyE2_Label(new MyE2_String("Freitag")),oIn);
//			
//			
//			if (iWeeks<=0)
//				return;                      // nur ueberschrift
//			
//			
//			GregorianCalendar 	oCalStart 			= new GregorianCalendar();             				// heute
//			
//			
//			DateFormat 			df = 		DateFormat.getDateInstance(DateFormat.SHORT);
//			
//			int iCountWeeks = 0;
//			
//			/*
//			 * aktuellen montag suchen (GregorianCalendar.DAY_OF_WEEK:Sonntag = 1)
//			 */
//			
//			for (int i=7;i>0;i--)
//			{
//				if (oCalStart.get(GregorianCalendar.DAY_OF_WEEK)==2)
//					break;
//				else
//					oCalStart.add(GregorianCalendar.DATE,-1);
//			}
//			
//			
//			
//			do
//			{
//
//				if (oCalStart.get(GregorianCalendar.DAY_OF_WEEK)==7)   // samstag
//				{
//					oCalStart.add(GregorianCalendar.DATE,2);
//					iCountWeeks++;
//					
//					if (iCountWeeks>=iWeeks)
//						break;
//					
//					continue;
//				}
//				
//				String cTag = (df.format(oCalStart.getTime())).substring(0,5);    //dd.mm
//				MyE2_CheckBox oCB = new MyE2_CheckBox(cTag);
//				this.add(oCB,oIn);
//				this.vCheckBoxesDate.add(oCB);
//				oCB.EXT().set_O_PLACE_FOR_EVERYTHING(oCalStart.clone());
//				oCB.EXT().set_I_MERKMAL(new Integer(iCountWeeks));                       // merkt sich die planungswoche
//				oCalStart.add(GregorianCalendar.DATE,1);
//				oCB.add_oActionAgent(new checkBoxActionAgent(this.vCheckBoxesDate));
//				
//			} 
//			while (iCountWeeks<iWeeks);
//		
//		}
//	}

	
	/*
	 * markiert alle gleichen tage spaeterer wochen "untendrunter"
	 */
	private class checkBoxActionAgent extends XX_ActionAgent
	{
		private Vector<MyE2_CheckBox>		vAllCheckBoxes = null;
		
		public checkBoxActionAgent(Vector<MyE2_CheckBox>	vallCheckBoxes)
		{
			super();
			this.vAllCheckBoxes = vallCheckBoxes;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			MyE2_CheckBox oCB = (MyE2_CheckBox)bibE2.get_LAST_ACTIONEVENT().getSource();
			
			if (!oCB.isSelected())             // es werden nur markierungen durchgereicht
				return;
			
			int iWeek = oCB.EXT().get_I_MERKMAL().intValue();
			int iDay  = ((GregorianCalendar)oCB.EXT().get_O_PLACE_FOR_EVERYTHING()).get(GregorianCalendar.DAY_OF_WEEK);
			
			for (int i=0;i<this.vAllCheckBoxes.size();i++)
			{
				MyE2_CheckBox oCBTest = (MyE2_CheckBox)this.vAllCheckBoxes.get(i);
				
				int iWeekTest = oCBTest.EXT().get_I_MERKMAL().intValue();
				if (iWeekTest>iWeek)
				{
					int iDayTest  = ((GregorianCalendar)oCBTest.EXT().get_O_PLACE_FOR_EVERYTHING()).get(GregorianCalendar.DAY_OF_WEEK);
					if (iDayTest==iDay)
					{
						oCBTest.setSelected(true);
					}
				}
			}
			
		}
		
	}
	
	
}
