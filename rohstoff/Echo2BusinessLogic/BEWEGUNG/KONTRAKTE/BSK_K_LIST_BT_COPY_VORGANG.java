package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.__SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_Date_von_bis_POPUP_OWN;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_CopyVorgang;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.valid_KopiereNurBelegeMitAktiveAdressen;

public class BSK_K_LIST_BT_COPY_VORGANG extends MyE2_Button
{
	private MyE2_CheckBox  						oCB_MengenKopieren = new MyE2_CheckBox(new MyE2_String("Mengen kopieren")); 
	private btSetDateRange 						btSetzeMonat = new btSetDateRange(true);
	private btSetDateRange 						btSetzeMonat_plus_1 = new btSetDateRange(false);
	
	
	private E2_NavigationList              		oNaviList = null;
	private BSK_K_MASK__ModulContainer			oMaskContainer = null;
	
	private ownDateVonBis  						f_ownDateVonBis = new ownDateVonBis();
	
	public BSK_K_LIST_BT_COPY_VORGANG(E2_NavigationList onavigationList,BSK_K_MASK__ModulContainer omaskContainer) throws myException
	{
		super(E2_ResourceIcon.get_RI("copy.png"), E2_ResourceIcon.get_RI("leer.png"));

		this.oNaviList = onavigationList;
		this.oMaskContainer = omaskContainer;
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(omaskContainer.get_SETTING().get_cMODULCONTAINER_LIST_IDENTIFIER(),"KOPIEREN_KONTRAKT"));
		this.setToolTipText(new MyE2_String("Kopieren eines (oder mehrerer) Kontrakte").CTrans());
		
		//aenderung 2010-12-22: keine kopie von inaktiven adressen
		this.add_IDValidator(new ownValidator());
		
		this.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException 	{
				if (BSK_K_LIST_BT_COPY_VORGANG.this.oNaviList.get_vSelectedIDs_Unformated().size()==0)	{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte wählen Sie mindestens einen Kontrakt aus !"));
					return;
				}
				
				//aenderung 2010-12-22: keine kopie von inaktiven adressen
				bibMSG.add_MESSAGE(BSK_K_LIST_BT_COPY_VORGANG.this.valid_IDValidation(BSK_K_LIST_BT_COPY_VORGANG.this.oNaviList.get_vSelectedIDs_Unformated()));
				
				if (bibMSG.get_bIsOK())	{
					new ownPopup();
				}
			}
		});
	}
	
	
	//aenderung 2010-12-22: keine kopie von inaktiven adressen
	private class ownValidator extends valid_KopiereNurBelegeMitAktiveAdressen {

		@Override
		public VectorSingle SammleAdressIDs(String cID_BelegToCopy)		throws myException	{
			VectorSingle vRueck = new VectorSingle();
			
			RECORD_VKOPF_KON  recVkopf = new RECORD_VKOPF_KON(cID_BelegToCopy);
			
			vRueck.add(recVkopf.get_ID_ADRESSE_cUF());
			
			return vRueck;
		}

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)		throws myException	{
			return null;
		}
	}

	
	
	
	private class ownPopup extends E2_BasicModuleContainer {

		private MyE2_Button   		            	btStart = new MyE2_Button(new MyE2_String("START"));
		private MyE2_Button   		            	btAbbruch = new MyE2_Button(new MyE2_String("Abbruch"));

		
		public ownPopup() throws myException {
			super();
			
			BSK_K_LIST_BT_COPY_VORGANG oThis = BSK_K_LIST_BT_COPY_VORGANG.this;
			
			MyE2_Grid oGridInnen = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
			
			this.add(oGridInnen, E2_INSETS.I_10_10_10_10);
			
			oGridInnen.add(new MyE2_Label(new MyE2_String("Kontrakte kopieren "), MyE2_Label.STYLE_TITEL_BIG()),2,E2_INSETS.I_10_5_10_5);
			oGridInnen.add(oThis.f_ownDateVonBis,2,E2_INSETS.I_10_5_10_5);
			
			oGridInnen.add(oThis.oCB_MengenKopieren,1,E2_INSETS.I_10_5_10_5);
			oGridInnen.add(new E2_ComponentGroupHorizontal(0,oThis.btSetzeMonat,oThis.btSetzeMonat_plus_1,E2_INSETS.I_0_0_20_0),1,E2_INSETS.I_10_5_10_5);
			
			
			oGridInnen.add(new E2_ComponentGroupHorizontal(0,this.btStart,this.btAbbruch,E2_INSETS.I_0_0_20_0),2,E2_INSETS.I_10_5_10_5);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(250), new MyE2_String("Kontrakte kopieren ..."));
			
			this.btAbbruch.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					ownPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			});
					
			this.btStart.add_oActionAgent(new actionCopySTART());
			
		}
		
		
		private class actionCopySTART extends XX_ActionAgent
		{

			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				BSK_K_LIST_BT_COPY_VORGANG oThis = BSK_K_LIST_BT_COPY_VORGANG.this;
				
				//neue variante
				oThis.f_ownDateVonBis.show_InputStatus(true);  //voreinstellung
				bibMSG.add_MESSAGE(oThis.f_ownDateVonBis.checkInput());

				if (bibMSG.get_bHasAlarms())
					return;
				
				boolean bCopyMengen = oThis.oCB_MengenKopieren.isSelected();
				
				HashMap<String, String> hmErsatzPos_Zusatz = new HashMap<String, String>();

				//2015-08-20: von bis ueber ein kombifeld
				hmErsatzPos_Zusatz.put("GUELTIG_VON", "'"+oThis.f_ownDateVonBis.get_oDBFormatedDateFromTextFieldVon()+"'");
				hmErsatzPos_Zusatz.put("GUELTIG_BIS", "'"+oThis.f_ownDateVonBis.get_oDBFormatedDateFromTextFieldBis()+"'");

				HashMap<String, String> hmErsatzPos = new HashMap<String, String>();
				if (!bCopyMengen) hmErsatzPos.put("ANZAHL", "NULL");
				hmErsatzPos.put("BESTELLNUMMER","NULL");
				hmErsatzPos.put("EINZELPREIS","NULL");
				
				
				Vector<String> vIDsSelected = oThis.oNaviList.get_vSelectedIDs_Unformated();
				
				
				//2016-12-20: meldungen fuer kontrakte anzeigen
				for (String id: vIDsSelected) {
					RECORD_VKOPF_KON recKon = new RECORD_VKOPF_KON(id);
			    	new __SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN(recKon.ufs(VKOPF_KON.id_adresse), recKon.ufs(VKOPF_KON.vorgang_typ)).ACTIVATE_MESSAGES();
				}
				//---------------------------------------------
				
				
				Vector<String> vAllSql = new Vector<String>();
				
				MyE2_MessageVector mv = bibMSG.getNewMV();
				
				for (int i=0;i<vIDsSelected.size();i++)
				{
					BS_CopyVorgang oCopy = new BS_CopyVorgang(	(String)vIDsSelected.get(i),
																oThis.oMaskContainer.get_SETTING().get_oVorgangTableNames(),
																null, null ,hmErsatzPos , hmErsatzPos_Zusatz);
					vAllSql.addAll(oCopy.get_vSQL_STACK_ForCopy());
					
					try {
						//2020-02-21: text-liste mitkopieren
						Long id = new MyLong(vIDsSelected.get(i)).get_oLong();

						SEL sel = new SEL(_TAB.text_liste).FROM(_TAB.text_liste)
								.WHERE(new vgl(TEXT_LISTE.tablename, _TAB.vkopf_kon.baseTableName()))
								.AND(  new vgl(TEXT_LISTE.id_table,id.toString() ))
								;
						RecList21 rlTextListe = new RecList21(_TAB.text_liste)._fill(new SqlStringExtended(sel.s()));
						
						
						for (Rec21 textLine: rlTextListe) {
							Rec20 kopieText = textLine.getRec20CopyAsNewWithoutAutomaticFields(mv, null);
							kopieText._setNewValueInDatabaseTerminus(TEXT_LISTE.id_table, _TAB.vkopf_kon.seq_currval());
							String sql = kopieText.get_sql_4_save(true,mv);
							if (S.isFull(sql) && mv.isOK()) {
								vAllSql.add(sql);
							} else {
								throw new myException("Error creating Copy of Contract-Text "+"<6df122c6-548b-11ea-a2e3-2e728ce88125>");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						mv._addWarn(S.ms("Fehler beim Kopieren der Textliste <6df122c6-548b-11ea-a2e3-2e728ce88125>"+e.getLocalizedMessage()));
					}
				}
				
				bibMSG.MV()._add(mv);
				
				
				
				
				String cQueryMaxID = "SELECT   NVL(MAX("+oThis.oMaskContainer.get_SETTING().get_oVorgangTableNames().get_cVKOPF_PK()+"),0) FROM "+
											bibE2.cTO()+"."+oThis.oMaskContainer.get_SETTING().get_oVorgangTableNames().get_cVKOPF_TAB();
				

				String cLASTID = bibDB.EinzelAbfrage(cQueryMaxID);
				
				
				DEBUG._print(vAllSql);
				
				//zuerst pruefen, welcher der letzte id wert im kopfsatz war,
				//dann alle danach geschriebenen in die liste haengen, dann kann der stack am stueck verarbeitet werden
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vAllSql,true));
				if (bibMSG.get_bIsOK())
				{
					String cLASTID2 = bibDB.EinzelAbfrage(cQueryMaxID);
					try
					{
						long iLastID = new Long(cLASTID).longValue();
						long iLastID2 = new Long(cLASTID2).longValue();
						
						Vector<String> vNeue = new Vector<String>();
						Vector<String> vSQL_String_Buchungsnummern = new Vector<String>();   //direkt auch buchungsnummern vergeben
						for (long i=iLastID+1;i<=iLastID2;i++)
						{
							vNeue.add(""+i);
							vSQL_String_Buchungsnummern.add("UPDATE JT_VKOPF_KON SET "+oThis.oMaskContainer.get_SETTING().get_SQL_UPDATE_Block_Fuer_Buchungsnummer()+" WHERE BUCHUNGSNUMMER IS NULL AND ID_VKOPF_KON="+i);
						}
						
						bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL_String_Buchungsnummern, true));
						
						oNaviList.ADD_NEW_ID_TO_ALL_VECTORS(vNeue);
						
						ownPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
						
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Kopie war erfolgreich!")));
						
					}
					catch (Exception ex) {}
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Kopieren ..."));
					//bibMSG.add_ALARMMESSAGE_Vector_Untranslated(vAllSql);
				}

			}
			
		}
		
		
		
	}
	
	
	
	

	
	
	
	private class btSetDateRange extends MyE2_Button
	{

		private boolean bAktuellerMonat = true;
		
		/*
		 * liefert entweder den aktuellen oder den naechsten monate von anfang bis ende in die maskenfelder
		 */
		public btSetDateRange(boolean AktuellerMonat)
		{
			super("",MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
			this.bAktuellerMonat = AktuellerMonat;
			
			GregorianCalendar oCal = new GregorianCalendar();
			
			if (!this.bAktuellerMonat)
				oCal = myDateHelper.Find_First_Day_NextMonth(oCal);
			
			try
			{
				myDateHelper oDateHelper = new myDateHelper(oCal);
			
				String cButtonText = oDateHelper.get_cDateFormatForMask().substring(3);
				this.set_Text(cButtonText);
				this.EXT().set_O_PLACE_FOR_EVERYTHING(oCal);

				this.add_oActionAgent(new ownActionAgent());
				
			}
			catch (myException ex)
			{
				this.set_Text("@@@ERROR@@@");
			}
			
		}

		
		
		private class ownActionAgent extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo)
			{
				MyE2_Button oButton = (MyE2_Button) bibE2.get_LAST_ACTIONEVENT().getSource();
				
				GregorianCalendar oCal = (GregorianCalendar)oButton.EXT().get_O_PLACE_FOR_EVERYTHING();
				
				try
				{
					
					GregorianCalendar oDateStart = myDateHelper.Find_First_Day_OfMonth(oCal);;
					GregorianCalendar oDateEnd = myDateHelper.Find_Last_Day_OfMonth(oCal);;
					
					myDateHelper oHelpStart = new myDateHelper(oDateStart);
					myDateHelper oHelpEnd = new myDateHelper(oDateEnd);

					BSK_K_LIST_BT_COPY_VORGANG.this.f_ownDateVonBis.get_oTextFieldVon().setText(oHelpStart.get_cDateFormatForMask());
					BSK_K_LIST_BT_COPY_VORGANG.this.f_ownDateVonBis.get_oTextFieldBis().setText(oHelpEnd.get_cDateFormatForMask());

				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error BSK_P_MaskButton_SetDateRange:ownactionagent: "));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
			}
		}
		
		
		
		
	}

	
	
	private class ownDateVonBis extends MyE2_TextField_Date_von_bis_POPUP_OWN {

		public ownDateVonBis() throws myException {
			super();
		}

		@Override
		public void Ordne_Komponenten_An(	MyE2_TextField 	oTextFieldVon, 	
											MyE2_TextField 	oTextFieldBis, 
											MyE2_Button 	oButtonCalendar,
											MyE2_Button 	oButtonEraserVon, 
											MyE2_Button 	oButtonEraserBis) throws myException {

			this.setSize(7);
			this.setColumnWidth(0, new Extent(50));
			this.add(new MyE2_Label(new MyE2_String("Gültigkeit: "),new E2_FontPlain(-2)),E2_INSETS.I(0, 0, 2, 0));
			this.add(oTextFieldVon,E2_INSETS.I(0, 0, 1, 0));
			this.add(oButtonEraserVon,E2_INSETS.I(0, 0, 5, 0));
			this.add(new MyE2_Label(new MyE2_String("bis"),new E2_FontPlain(-2)),E2_INSETS.I(0, 0, 5, 0));
			this.add(oTextFieldBis,E2_INSETS.I(0, 0, 1, 0));
			this.add(oButtonEraserBis,E2_INSETS.I(0,0,5,0));
			this.add(oButtonCalendar,E2_INSETS.I(0,0,1,0));
			
			this.set_bAutoCloseOnBisCalendar(true);
			
		}
		
		
		
		public MyE2_MessageVector checkInput() throws myException {
			MyE2_MessageVector  mv = new MyE2_MessageVector();
			
			this.show_InputStatus(true);
			
			MyDate start = this.get_oDateFromTextFieldVon();
			MyDate ende = this.get_oDateFromTextFieldBis();
			
			if (start != null && ende != null) {
				if (myDateHelper.get_Date1_Greater_Date2(start.get_cDateStandardFormat(), ende.get_cDateStandardFormat())) {
					mv.add(new MyE2_Alarm_Message(new MyE2_String("Bitte sorgen Sie dafür, daß das Startdatum vor dem Enddatum liegt !")));
					this.show_InputStatus(false);
				}
			} else {
				if (start==null) {
					mv.add(new MyE2_Alarm_Message(new MyE2_String("Bitte beim Startdatum einen korrekten Datumswert erfassen !")));
					this.show_InputStatusStart(false);
				} 
				
				if (ende==null) {
					mv.add(new MyE2_Alarm_Message(new MyE2_String("Bitte beim Enddatum einen korrekten Datumswert erfassen !")));
					this.show_InputStatusEnd(false);
				} 
			}
			
			return mv;
		}
		
		
	}
	
	
}
