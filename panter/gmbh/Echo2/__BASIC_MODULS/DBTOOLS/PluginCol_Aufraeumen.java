package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.TestHandelsdefAufHistorie.BtStartVergleichHistorie;
import panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.LOGTRIG__CONST;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_Date_von_bis_POPUP_OWN;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_QUALIFIER_KEYS;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_QUALIFIER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_QUALIFIER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_Clean_ATOM_Tables;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.FS_Component_MASK_DAUGHTER_ZUSATZINFOS;
import rohstoff.Echo2BusinessLogic._4_ALL.BT_OpenMaskByID;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.WARENSTROEME_EINZELPOSITIONEN.AW_WarenstroemeHandler;
import echopointng.Separator;


public class PluginCol_Aufraeumen extends Basic_PluginColumn
{

	
	
	public PluginCol_Aufraeumen(ContainerForVerwaltungsTOOLS oMothercontainer) throws myException
	{
		super(oMothercontainer);
		
		MyE2_Grid  oGrid4Buttons = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
		
		MyE2_Button 		oButtonBuildAll = new MyE2_Button(new MyE2_String("Lösche temporäre Tabellen <JD_AAA_TEMPTABLE*>"),new MyE2_String("Löscht temporäre Abfragetabellen"),new ownActionDeleteTempTables());
		MyE2_Button 		oButtonDeleteLogTable = new MyE2_Button(new MyE2_String("Lösche DB-LOG-Table"),new MyE2_String("Löscht temporäre Logtabelle"),new ownActionDeleteLogTable());
		MyE2_Button 		oButtonKorrigiereEmailQualifiers = new MyE2_Button(new MyE2_String("Setze eMail-Qualifizierer um"),new MyE2_String("Übernimmt die eMail-Qualifizierer aus der Drop-Down-Definition in die Qualifizierer-Multiselektion ..."),new ownActionAgent_Uebernehme_Email_Qualifizierer());

		MyE2_Button 		oButtonBaueTableExView = new MyE2_Button(new MyE2_String("Baue Tabelle aus VIEW_FUHREN_RECHNUNGEN"),new MyE2_String("Baut eine Tabelle JT_FUHREN_RECHNUNGEN aus der View VIEW_FUHREN_RECHNUNGEN ..."),new ownActionAgentBaueAuswerteTable());
		MyE2_Button 		oButtonBaueTestReclist = new MyE2_Button(new MyE2_String("Baue RECLIST_FUHREN_RECHNUNGEN"),new MyE2_String("Baut RECLIST_FUHREN_RECHNUNGEN ..."),new ownActionTeste_RECORD_FUHREN_RECHNUNGEN());
		
		
		MyE2_Button 		oButtonTestRecordlistLaufzeit = new MyE2_Button(new MyE2_String("RECLIST_FUHREN_AUFBAUEN und Teste Laufzeit"),new MyE2_String("Baut RECLIST_FUHREN auf und misst die Laufzeit ..."),new ownActionTeste_RECORDLIST_FUHREN());
		
		
		MyE2_Button 		oButtonTextExecFuhrenDummy = new MyE2_Button(new MyE2_String("Validierer nach Adressanhang-tausch testen"),new MyE2_String("Validierer nach Adressanhang-tausch testen"),new teste_db_trigger());
		
		MyE2_Button    		oButtonStarteFuhrenSumme  = new MyE2_Button(new MyE2_String("Listensummation testen"),new MyE2_String("Listensummation testen"),new ownActionSummiere());
		
		
		
		oGrid4Buttons.add(oButtonBuildAll, E2_INSETS.I_2_2_2_2);
		oGrid4Buttons.add(oButtonDeleteLogTable, E2_INSETS.I_2_2_2_2);
		oGrid4Buttons.add(oButtonKorrigiereEmailQualifiers, E2_INSETS.I_2_2_2_2);
		oGrid4Buttons.add(oButtonBaueTableExView, E2_INSETS.I_2_2_2_2);
		oGrid4Buttons.add(oButtonBaueTestReclist, E2_INSETS.I_2_2_2_2);
		
		oGrid4Buttons.add(oButtonTestRecordlistLaufzeit, E2_INSETS.I_2_2_2_2);
		oGrid4Buttons.add(oButtonStarteFuhrenSumme, E2_INSETS.I_2_2_2_2);
		
		oGrid4Buttons.add(new ownButtonClearSetzkasten(), E2_INSETS.I_2_2_2_2);
		oGrid4Buttons.add(new ownButtonClearATOMRecords(), E2_INSETS.I_2_2_2_2);
		
		
//		oGrid4Buttons.add(oButtonTextExecFuhrenDummy, E2_INSETS.I_2_2_2_2);
//		
//		String[] arrTest = {"1111","2222","3333"};
//		MyE2_SelectField  oSelTest = new MyE2_SelectField(arrTest, "1111", false);
//		oGrid4Buttons.add(oSelTest, E2_INSETS.I_2_2_2_2);
//		oSelTest.setCellRenderer(evenOddListCellRenderer);
		
		 
		oGrid4Buttons.add(new ownDoubleDatePopup());
		
		oGrid4Buttons.add(new buttonTestSessionVariable());
		
		oGrid4Buttons.add(new ownButtonTestTriggerName());
//		oGrid4Buttons.add(new ownButtonTesteGroovy());
		
		oGrid4Buttons.add(new button_FuellePrivatUndGeschaeftlich());
		
		oGrid4Buttons.add(new ownButtonTestAdresse());
		
//		oGrid4Buttons.add(new FileNameCleaner_button());
		oGrid4Buttons.add(new BtStartVergleichHistorie());
		
		this.add(oGrid4Buttons);
		this.add( new Separator());
		this.add(this.get_TextArea4Output(),ContainerForVerwaltungsTOOLS.INSETS_LIST);
		
	}
	
	private class ownButtonTestAdresse extends BT_OpenMaskByID {

		public ownButtonTestAdresse() {
			super(new MyE2_String("Adresse 5525 Öffnen"), MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN());
			
			this.set_cID_BASICTABLE_UF("5525");
			this.set_cSTATUS_MASKE(E2_ComponentMAP.STATUS_EDIT);
		}

		@Override
		public MyE2_MessageVector do_AfterCreatedAndFilledMaskObject(E2_BasicModuleContainer_MASK oMaskContainer, String cID_MainTable_UF, String cEDITSTATUS) throws myException {
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			return oMV;
		}

		@Override
		public E2_BasicModuleContainer_MASK get_BasicModuleContainer_MASK() throws myException {
			
			return new FS_ModulContainer_MASK();
		}

		@Override
		public MyE2_MessageVector do_AfterPopupMask(E2_BasicModuleContainer_MASK oMaskContainer, String cID_MainTable_UF, String cEDITSTATUS) throws myException {
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			FS_Component_MASK_DAUGHTER_ZUSATZINFOS oDaughter = (FS_Component_MASK_DAUGHTER_ZUSATZINFOS)oMaskContainer.get_vCombinedComponentMAPs().get(0).get__Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS);

			((FS_ModulContainer_MASK)oMaskContainer).set_TabAsActive(FS_CONST.TABTEXT_INFOS_IN_ADRESSMASK);
			
			oDaughter.get_oListContainer().get_oNavigationList().gotoSiteWithID_orFirstSite("14102");
			oDaughter.get_oListContainer().get_oNavigationList().set_CheckBox_To_AllIdsInVector(bibALL.get_Vector("14102"));
			return oMV;
		}

			@Override
		public E2_ButtonAUTHValidator getValdiatorEdit() {
			
			return new E2_ButtonAUTHValidator(E2_MODULNAMES.MODUL_KENNER_PROGRAMM_WIDE,"BEARBEITE_ADRESSE_AUS_LISTE");
		}

		@Override
		public E2_ButtonAUTHValidator getValdiatorView() {
			return new E2_ButtonAUTHValidator(E2_MODULNAMES.MODUL_KENNER_PROGRAMM_WIDE, "ANZEIGE_ADRESSE_AUS_LISTE");
		}
		
	}
	
//	private class ownButtonTesteGroovy extends MyE2_Button
//	{
//
//		public ownButtonTesteGroovy()
//		{
//			super(new MyE2_String("Teste Groovy"));
//			
//			this.add_oActionAgent(new XX_ActionAgent()
//			{
//				
//				@Override
//				public void executeAgentCode(ExecINFO oExecInfo) throws myException
//				{
//					String cWert =  bibGroovy.interpretGroovy(bibGroovy.get_GroovyCodes().get("AKTUELLES_JAHR"),"AKTUELLES_JAHR");
//					bibMSG.add_MESSAGE(new MyE2_Info_Message("Jahr="+cWert));
//				}
//			});
//			
//		}
//		
//	}
	
	
	
	
	private class ownButtonClearSetzkasten extends MyE2_Button
	{

		public ownButtonClearSetzkasten()
		{
			super("ATOM-Setzkasten löschen");
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					UTIL_Clean_ATOM_Tables oUtil = new UTIL_Clean_ATOM_Tables();
					boolean bRet = oUtil.cleanSetzkasten();
					
					PluginCol_Aufraeumen.this.get_TextArea4Output().setText("Setzkasten-Einträge löschen: " + (bRet ? "OK" : "Fehler!") );
				}
			});
		}
		
	}
	
	
	
	private class ownButtonClearATOMRecords extends MyE2_Button
	{

		public ownButtonClearATOMRecords()
		{
			super("ATOM-Einträge löschen");
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					UTIL_Clean_ATOM_Tables oUtil = new UTIL_Clean_ATOM_Tables();
					boolean bRet = 	oUtil.cleanSetzkasten();
					PluginCol_Aufraeumen.this.get_TextArea4Output().setText("Setzkasten-Einträge löschen: " + (bRet ? "OK" : "Fehler!") );
					bRet = oUtil.cleanATOMTables();
					PluginCol_Aufraeumen.this.get_TextArea4Output().setText("ATOM-Einträge löschen: " + (bRet ? "OK" : "Fehler!") );
				}
			});
		}
		
	}
	
	private class ownButtonTestTriggerName extends MyE2_Button
	{

		public ownButtonTestTriggerName()
		{
			super("Name von LogTrigger testen");
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					Vector<String> vTables = DB_META.get_vTableNamesOfSchema(null, true);
					for (String cTable: vTables)
					{
						Vector<String> vFields = DB_META.get_vFields(cTable, bibE2.cTO());
						
						for (String cField: vFields)
						{
							String cTrigName = LOGTRIG__CONST.generateTriggerName(cTable, cField);
							if (cTrigName.length()>26)	System.out.println(cTrigName+" -- "+cTrigName.length());
						}
					}
					
				}
			});
		}
		
	}
	
	
	
	private class buttonTestSessionVariable extends MyE2_Button
	{

		public buttonTestSessionVariable()
		{
			super("dbms_session.get_identifier");
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("ID-Benutzer: "+bibDB.EinzelAbfrage("SELECT sys_context('USERENV','CLIENT_IDENTIFIER')  FROM DUAL")));
					
					MyConnection oConnTest = 			new MyConnection(	(String) bibALL.getSessionValue("CLASSNAME"),
            				(String) bibALL.getSessionValue("JDBC_STRING"),
            				(String) bibALL.getSessionValue("JAVA_LOGIN"),
            				(String) bibALL.getSessionValue("JAVA_PASSWORT"),
            				(String) bibALL.getSessionValue("DBKENNUNG"));

				}
			});
			
		}
		
	}
	
	
	private class ownDoubleDatePopup extends MyE2_TextField_Date_von_bis_POPUP_OWN
	{

		public ownDoubleDatePopup() throws myException
		{
			super();
			
			this.set_bAutoCloseOnBisCalendar(false);
			this.set_bShowOKButton(true);
			
			
			this.get_vActionAgentsZusatzVonCalender().add(new XX_ActionAgent()
			{
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("von-datum")));
				}
			});
			
			this.get_vActionAgentsZusatzBisCalender().add(new XX_ActionAgent()
			{
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("bis-datum")));
				}
			});

		}
		
		

		@Override
		public void Ordne_Komponenten_An(MyE2_TextField oTextFieldVon, MyE2_TextField oTextFieldBis, MyE2_Button oButtonCalendar, MyE2_Button oButtonEraserVon, MyE2_Button oButtonEraserBis) throws myException
		{
			this.setSize(5);
			this.add(oTextFieldVon);
			this.add(oButtonEraserVon);
			this.add(oTextFieldBis);
			this.add(oButtonEraserBis);
			this.add(oButtonCalendar);
		}
		
	}
	

	
	private class ownActionDeleteTempTables extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Vector<String> vTempTables = DB_META.get_vTableNamesOfSchema("TABLE_NAME LIKE 'JD_AAA_TEMPTABLE%'", true);
			
			String cInfos = PluginCol_Aufraeumen.this.get_TextArea4Output().getText();
			for (String Name: vTempTables)
			{
				if (bibDB.ExecSQL("DROP TABLE "+Name, true))
				{
					cInfos += new MyE2_String("Gelöscht:",true,Name,false).CTrans()+"\n";
				}
			}
			
			PluginCol_Aufraeumen.this.get_TextArea4Output().setText(cInfos);
		}
	}

	
	
	private class ownActionDeleteLogTable extends XX_ActionAgent
	{
		@Override 
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			String cInfos = PluginCol_Aufraeumen.this.get_TextArea4Output().getText();
			if (bibDB.ExecSQL("DELETE FROM JD_DB_LOG", true))
			{
				cInfos += new MyE2_String("Gelöscht: Alle Inhalt der DB-LOG-Datei \n");
			}
			
			PluginCol_Aufraeumen.this.get_TextArea4Output().setText(cInfos);
		}
	}

	
	
	
	/**
	 * umsetzer fuer die eMail-Type-Drop-Downs in die MailVerwendungs-Qualifizierer 
	 * @author martin
	 *
	 */
	private class ownActionAgent_Uebernehme_Email_Qualifizierer extends XX_ActionAgent
	{
		MyE2_MessageVector  oMVSammler = new MyE2_MessageVector();

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			new E2_ServerPushMessageContainer(new Extent(400),new Extent(150),new MyE2_String("Umbau der eMail-Definitionen läuft ..."),true,true,false,1000)
			{
				@Override
				public void Run_Loop() throws myException
				{
					String[][] 	cID_Adressen = bibDB.EinzelAbfrageInArray("SELECT DISTINCT ID_ADRESSE FROM JT_EMAIL ORDER BY ID_ADRESSE");

					
					for (int i=0;i<cID_Adressen.length;i++)
					{
						RECLIST_EMAIL  recListEMail = new RECORD_ADRESSE(cID_Adressen[i][0]).get_DOWN_RECORD_LIST_EMAIL_id_adresse();
						
						for (int k=0;k<recListEMail.get_vKeyValues().size();k++)
						{
							//jetzt abfragen, was fuer typen mit dieser mailadresse in der liste vorkommen
							RECORD_EMAIL  recEmail = recListEMail.get(k);
							
							if (S.isFull(recEmail.get_EMAIL_cUF_NN("")))
							{
								String cSQL = "SELECT TYPE FROM JT_EMAIL WHERE ID_ADRESSE="+cID_Adressen[i][0]+" AND UPPER(EMAIL)=UPPER("+recEmail.get_EMAIL_VALUE_FOR_SQLSTATEMENT()+")";
								String[][] WERTE = bibDB.EinzelAbfrageInArray(cSQL,"-");
								
								if (WERTE==null)
								{
									System.out.println(cSQL);
									continue;
								}
								
								if (WERTE.length!=0)
								{
									for (int l=0;l<WERTE.length;l++)
									{
										this.add_Qualifier(recEmail.get_ID_EMAIL_cUF(), WERTE[l][0],oMVSammler);
									}
								}
							}

						}
						//fortschrittsmeldungen
						this.get_oGridBaseForMessages().removeAll();
						this.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String(""+i+"/"+cID_Adressen.length,false)));
					}
					
				}  //run_loop
				
				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
				{
				}

				
				private void add_Qualifier(String cID_EMAIL, String cDropDownValue, MyE2_MessageVector oMVSammler) throws myException
				{
					if 		(cDropDownValue.equals("ALLEBELEGE"))
					{
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_ABNAHMEANGEBOT, oMVSammler);
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_ANGEBOT, oMVSammler);
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_AUFT_BEST, oMVSammler);
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_EK_KONTRAKT, oMVSammler);
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_GUTSCHRIFT, oMVSammler);
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_LIEFERSCHEIN, oMVSammler);
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_RECHNUNG, oMVSammler);
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_TRANSPORT, oMVSammler);
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_VK_KONTRAKT, oMVSammler);
					}
					else if (cDropDownValue.equals(myCONST.VORGANGSART_ANGEBOT))
					{
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_ANGEBOT, oMVSammler);
					}
					else if (cDropDownValue.equals(myCONST.VORGANGSART_ABNAHMEANGEBOT))
					{
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_ABNAHMEANGEBOT, oMVSammler);
					}
					else if (cDropDownValue.equals(myCONST.VORGANGSART_AUFT_BEST))
					{
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_AUFT_BEST, oMVSammler);
					}
					else if (cDropDownValue.equals(myCONST.VORGANGSART_EK_KONTRAKT))
					{
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_EK_KONTRAKT, oMVSammler);
					}
					else if (cDropDownValue.equals(myCONST.VORGANGSART_GUTSCHRIFT))
					{
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_GUTSCHRIFT, oMVSammler);
					}
					else if (cDropDownValue.equals(myCONST.VORGANGSART_LIEFERSCHEIN))
					{
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_LIEFERSCHEIN, oMVSammler);
					}
					else if (cDropDownValue.equals(myCONST.VORGANGSART_RECHNUNG))
					{
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_RECHNUNG, oMVSammler);
					}
					else if (cDropDownValue.equals(myCONST.VORGANGSART_TRANSPORT))
					{
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_TRANSPORT, oMVSammler);
					}
					else if (cDropDownValue.equals(myCONST.VORGANGSART_VK_KONTRAKT))
					{
						new ownNewQualifier(cID_EMAIL, myCONST.EMAIL_TYPE_VALUE_VK_KONTRAKT, oMVSammler);
					}
				}
			};
			
			bibMSG.add_MESSAGE(this.oMVSammler);
			
		}
	}
	
	public  class ownNewQualifier extends RECORDNEW_QUALIFIER
	{

		public ownNewQualifier(String cID_EMAIL, String cDATENBANK_TAG, MyE2_MessageVector oMV_Sammler ) throws myException
		{
			super();
			this.set_NEW_VALUE_CLASS_KEY(Q_QUALIFIER_KEYS.QUALIFIER_KEY_EMAIL_VERWENDUNGSTYP);
			this.set_NEW_VALUE_DATENBANKTAG(cDATENBANK_TAG);
			this.set_NEW_VALUE_ID_TABLE(cID_EMAIL);
			this.set_NEW_VALUE_TABLENAME("EMAIL");
			
			MyE2_MessageVector oMV = new MyE2_MessageVector(); 
			
			String cSQL_check = "SELECT COUNT(*) FROM JT_QUALIFIER WHERE "+	RECORD_QUALIFIER.FIELD__ID_TABLE+"="+cID_EMAIL+" AND "+
																			RECORD_QUALIFIER.FIELD__DATENBANKTAG+"='"+cDATENBANK_TAG+"' AND "+
																			RECORD_QUALIFIER.FIELD__CLASS_KEY+"='"+Q_QUALIFIER_KEYS.QUALIFIER_KEY_EMAIL_VERWENDUNGSTYP+"' AND "+
																			RECORD_QUALIFIER.FIELD__TABLENAME+"='EMAIL'";
			
			if (bibDB.EinzelAbfrage(cSQL_check).equals("1"))
			{
				return;
			}
			this.do_WRITE_NEW_QUALIFIER(oMV);
			oMV_Sammler.add_MESSAGE(oMV.get_AlarmMessages());
		}
		
	}

	
	/*
	 * aus der auswerte-view VIEW_FUHREN_RECHNUNGEN eine benutzbar tabelle erzeugeb
	 */
	private class ownActionAgentBaueAuswerteTable extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{

			Vector<String>  vGenerateTable = new Vector<String>();
			
			vGenerateTable.add("drop table JT_FUHREN_RECHNUNGEN");
//			vGenerateTable.add("DROP SEQUENCE TEMP_SEQ_FUHREN_RECHNUNGEN");
			vGenerateTable.add("DROP SEQUENCE SEQ_FUHREN_RECHNUNGEN");
//			vGenerateTable.add("CREATE SEQUENCE TEMP_SEQ_FUHREN_RECHNUNGEN  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1000 NOCACHE  NOORDER  NOCYCLE");
			vGenerateTable.add("CREATE SEQUENCE SEQ_FUHREN_RECHNUNGEN  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 1000 NOCACHE  NOORDER  NOCYCLE");
			vGenerateTable.add("create table JT_FUHREN_RECHNUNGEN AS SELECT * FROM VIEW_FUHREN_RECHNUNGEN");
			vGenerateTable.add("alter table JT_FUHREN_RECHNUNGEN drop column ID");
			vGenerateTable.add("alter table jt_fuhren_rechnungen add (ID_FUHREN_RECHNUNGEN NUMBER(10),LETZTE_AENDERUNG date, GEAENDERT_VON nvarchar2(10), ERZEUGT_VON NVARCHAR2(10), ERZEUGT_AM date)");

			// Spaltentypen anpassen
			// alte spalten umbennenen
			vGenerateTable.add(" alter table JT_FUHREN_RECHNUNGEN rename column MENGE to MENGE_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column MENGE_ABZUG to MENGE_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column MENGE_NACH_ABZUG to MENGE_NACH_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PREIS_GESAMT to R_PREIS_GESAMT_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PREIS_ABZUG to R_PREIS_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PREIS_NACH_ABZUG to R_PREIS_NACH_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PREIS_EINZEL to R_PREIS_EINZEL_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PREIS_ABZUG_EINZEL to R_PREIS_ABZUG_EINZEL_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PREIS_EINZEL_RESULT to R_PREIS_EINZEL_RESULT_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PCT_ABZUG to R_PCT_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column R_PCT_METALL to R_PCT_METALL_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN rename column MENGE_LAGER to MENGE_LAGER_1 ");
			
			// neue Spalten hinzfügen
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add MENGE number(12,3)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add MENGE_ABZUG number(12,3)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add MENGE_NACH_ABZUG number(12,3)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PREIS_GESAMT number(12,2)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PREIS_ABZUG number(12,2)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PREIS_NACH_ABZUG number(12,2)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PREIS_EINZEL number(12,2)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PREIS_ABZUG_EINZEL number(12,2)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PREIS_EINZEL_RESULT number(12,2)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PCT_ABZUG number(12,3)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add R_PCT_METALL number(12,3)");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN add MENGE_LAGER number(12,3)");
			
			// daten updaten
			String s1 = 
				" UPDATE JT_FUHREN_RECHNUNGEN                                           " +
				" set MENGE = round(MENGE_1,3)                                          " +
				" ,MENGE_ABZUG = round(MENGE_ABZUG_1,3)                                 " +
				" ,MENGE_NACH_ABZUG = round(MENGE_NACH_ABZUG_1,3)                       " +
				" ,R_PREIS_GESAMT = round( R_PREIS_GESAMT_1 ,2)                         " +
				" ,R_PREIS_ABZUG = round( R_PREIS_ABZUG_1 ,2)                           " +
				" ,R_PREIS_NACH_ABZUG = round( R_PREIS_NACH_ABZUG_1 ,2)                 " +
				" ,R_PREIS_EINZEL = round( R_PREIS_EINZEL_1 ,2)                         " +
				" ,R_PREIS_ABZUG_EINZEL = round( R_PREIS_ABZUG_EINZEL_1 ,2)             " +
				" ,R_PREIS_EINZEL_RESULT = round( R_PREIS_EINZEL_RESULT_1 ,2)           " +
				" ,R_PCT_ABZUG = round( R_PCT_ABZUG_1 ,2)                               " +
				" ,R_PCT_METALL = round( R_PCT_METALL_1 ,2)                             " +
				" ,MENGE_LAGER = round( MENGE_LAGER_1 ,3)                               " ;
			vGenerateTable.add(s1);
			
			// alte spalten löschen
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column MENGE_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column MENGE_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column MENGE_NACH_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PREIS_GESAMT_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PREIS_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PREIS_NACH_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PREIS_EINZEL_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PREIS_ABZUG_EINZEL_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PREIS_EINZEL_RESULT_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PCT_ABZUG_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column R_PCT_METALL_1 ");
			vGenerateTable.add(" alter table  JT_FUHREN_RECHNUNGEN drop column MENGE_LAGER_1 ");
			
			// alte zahlenwerte zwischenspeichern
			vGenerateTable.add("alter table jt_fuhren_rechnungen add (R_PREIS_NACH_ABZUG_ORI NUMBER(10,2), R_PREIS_EINZEL_RESULT_ORI NUMBER(10,2), MENGE_NACH_ABZUG_ORI NUMBER(12,3), MENGE_LAGER_ORI NUMBER(12,3), KORREKTUR int)");
			
			vGenerateTable.add("update 		jt_fuhren_rechnungen set jt_fuhren_rechnungen.ID_FUHREN_RECHNUNGEN=SEQ_FUHREN_RECHNUNGEN.nextval");
			vGenerateTable.add("alter table jt_fuhren_rechnungen add primary key (ID_FUHREN_RECHNUNGEN)");
			
			vGenerateTable.add("ALTER TABLE JT_fuhren_rechnungen  ADD CONSTRAINT FK01_fuhren_rechnungen  FOREIGN KEY  (ID_VPOS_TPA_FUHRE) REFERENCES JT_VPOS_TPA_FUHRE (ID_VPOS_TPA_FUHRE) ON DELETE CASCADE");
			vGenerateTable.add("ALTER TABLE JT_fuhren_rechnungen  ADD CONSTRAINT FK02_fuhren_rechnungen  FOREIGN KEY  (ID_VPOS_TPA_FUHRE_ORT) REFERENCES JT_VPOS_TPA_FUHRE_ORT (ID_VPOS_TPA_FUHRE_ORT) ON DELETE CASCADE");
			vGenerateTable.add("ALTER TABLE JT_fuhren_rechnungen  ADD CONSTRAINT FK03_fuhren_rechnungen  FOREIGN KEY  (ID_VPOS_KON) REFERENCES JT_VPOS_KON  (ID_VPOS_KON) ON DELETE CASCADE");
			vGenerateTable.add("ALTER TABLE JT_fuhren_rechnungen  ADD CONSTRAINT FK04_fuhren_rechnungen  FOREIGN KEY  (ID_ARTIKEL) REFERENCES JT_ARTIKEL  (ID_ARTIKEL) ON DELETE CASCADE");
			vGenerateTable.add("ALTER TABLE JT_fuhren_rechnungen  ADD CONSTRAINT FK05_fuhren_rechnungen  FOREIGN KEY  (ID_VKOPF_RG) REFERENCES JT_VKOPF_RG  (ID_VKOPF_RG) ON DELETE CASCADE");
			vGenerateTable.add("ALTER TABLE JT_fuhren_rechnungen  ADD CONSTRAINT FK06_fuhren_rechnungen  FOREIGN KEY  (ID_VPOS_RG) REFERENCES JT_VPOS_RG  (ID_VPOS_RG) ON DELETE CASCADE");
			
			vGenerateTable.add("update jt_fuhren_rechnungen set R_PREIS_NACH_ABZUG_ORI = R_PREIS_NACH_ABZUG, R_PREIS_EINZEL_RESULT_ORI = R_PREIS_EINZEL_RESULT, MENGE_NACH_ABZUG_ORI = MENGE_NACH_ABZUG, MENGE_LAGER_ORI = MENGE_LAGER , KORREKTUR = 0 ");
			
			vGenerateTable.add("create index INDEX1 on JT_FUHREN_RECHNUNGEN (LEISTUNGSDATUM)");
			vGenerateTable.add("create index INDEX2 on JT_FUHREN_RECHNUNGEN (ID_ARTIKEL)");
			vGenerateTable.add("create index INDEX3 on JT_FUHREN_RECHNUNGEN (ID_LAGER)");
			vGenerateTable.add("create index INDEX4 on JT_FUHREN_RECHNUNGEN (ID_ADRESSE_START)");
			vGenerateTable.add("create index INDEX5 on JT_FUHREN_RECHNUNGEN (ID_ADRESSE_STANDORT_START)");
			vGenerateTable.add("create index INDEX6 on JT_FUHREN_RECHNUNGEN (ID_ADRESSE_ZIEL)");
			vGenerateTable.add("create index INDEX7 on JT_FUHREN_RECHNUNGEN (ID_ADRESSE_STANDORT_ZIEL)");
			vGenerateTable.add("create index INDEX8 on JT_FUHREN_RECHNUNGEN (ID_LAGER_GEGENSEITE)");
			
			for (int i=0;i<vGenerateTable.size();i++)
			{
				if ( bibDB.ExecSQL(vGenerateTable.get(i), true))
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message("OK:   "+vGenerateTable.get(i)));
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("ERR:   "+vGenerateTable.get(i)));
				}
			}
			
			this.BaueTrigger();
			this.baueViews();
		}
		
		
		private void BaueTrigger()
		{
			Vector<String> vTablesWithoutTrigger = new Vector<String>();
			vTablesWithoutTrigger.add("JD_DB_LOG");
			vTablesWithoutTrigger.add("JD_LOGIN");
			vTablesWithoutTrigger.add("TT_SORT_TABLE");
		
			String cTabelle = "JT_FUHREN_RECHNUNGEN";
					
			if (!vTablesWithoutTrigger.contains(cTabelle))
			{
				
				// zuerst die (falls noch nicht vorhanden) zusatzfelder fuer das mitprotokollieren des erstellers eine satzes und des erstelldatums eines satzes
				// in die tabellen reinschreiben
				String cAlterTable1 = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"ALTER TABLE "+bibE2.cTO()+"."+cTabelle+" ADD (ERZEUGT_VON NVARCHAR2(10))";
				String cAlterTable2 = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"ALTER TABLE "+bibE2.cTO()+"."+ cTabelle+" ADD (ERZEUGT_AM DATE)";
				String cFill1		= MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"UPDATE "+bibE2.cTO()+"."+ cTabelle+" SET ERZEUGT_AM=LETZTE_AENDERUNG WHERE ERZEUGT_AM IS NULL";
				String cFill2		= MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"UPDATE "+bibE2.cTO()+"."+ cTabelle+" SET ERZEUGT_VON=GEAENDERT_VON WHERE ERZEUGT_VON IS NULL";
		
				
				MyE2_String  cMeldung = new MyE2_String("");
				
				if (bibDB.ExecSQL(cAlterTable1, true))
				{
					cMeldung.addUnTranslated("<ERZEUGT_VON added>");
				}
				
				if (bibDB.ExecSQL(cAlterTable2, true))
				{
					cMeldung.addUnTranslated("<ERZEUGT_AM added>");
				}

				if (!bibDB.ExecSQL(cFill1, true))
				{
					cMeldung.addUnTranslated("<Error Filling: ERZEUGT_AM>");
				}
				if (!bibDB.ExecSQL(cFill2, true))
				{
					cMeldung.addUnTranslated("<Error Filling: ERZEUGT_VON>");
				}

				StringBuilder oTrigg = new StringBuilder();
				oTrigg.append("CREATE OR REPLACE TRIGGER trigg_##WERT## \n");
				oTrigg.append("BEFORE INSERT OR UPDATE OR DELETE \n");
				oTrigg.append("ON ##TABELLE## \n");
				oTrigg.append("FOR EACH ROW \n");
				oTrigg.append("DECLARE \n");
			    oTrigg.append("ora_sess        NVARCHAR2(100); \n");
				oTrigg.append("BEGIN \n");
				oTrigg.append("IF INSERTING THEN \n");
				oTrigg.append("INSERT INTO JD_DB_LOG (ID_DB_LOG,ID_TABLE,TABLENAME,AKTION,DBSESSION,TIMESTAMPMILLISECS) VALUES  (SEQ_DB_LOG.NEXTVAL,:NEW.ID_##WERT##,'##TABELLE##','INSERT',"+DB_META.ORA_SESSION_QUERY+","+DB_META.ORA_TIMESTAMP_MILLISECS+"); \n");
				oTrigg.append(":NEW.ERZEUGT_VON:=:NEW.GEAENDERT_VON; \n");
				oTrigg.append(":NEW.ERZEUGT_AM:=:NEW.LETZTE_AENDERUNG; \n");
				oTrigg.append("ELSIF UPDATING THEN \n");
				oTrigg.append("INSERT INTO JD_DB_LOG (ID_DB_LOG,ID_TABLE,TABLENAME,AKTION,DBSESSION,TIMESTAMPMILLISECS) VALUES  (SEQ_DB_LOG.NEXTVAL,:OLD.ID_##WERT##,'##TABELLE##','UPDATE',"+DB_META.ORA_SESSION_QUERY+","+DB_META.ORA_TIMESTAMP_MILLISECS+"); \n");
				oTrigg.append("ELSIF DELETING THEN \n");
				oTrigg.append("INSERT INTO JD_DB_LOG (ID_DB_LOG,ID_TABLE,TABLENAME,AKTION,DBSESSION,TIMESTAMPMILLISECS) VALUES  (SEQ_DB_LOG.NEXTVAL,:OLD.ID_##WERT##,'##TABELLE##','DELETE',"+DB_META.ORA_SESSION_QUERY+","+DB_META.ORA_TIMESTAMP_MILLISECS+"); \n");
				oTrigg.append("END IF; \n");
				oTrigg.append("END; \n");

				String cTriggerStatement = oTrigg.toString();

				
				String cTriggerHelp = bibALL.ReplaceTeilString(cTriggerStatement, "##WERT##", cTabelle.substring(3));
				cTriggerHelp = 	bibALL.ReplaceTeilString(cTriggerHelp, "##TABELLE##", cTabelle);
				cTriggerHelp = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cTriggerHelp;
				
				if (bibDB.ExecSQL(cTriggerHelp, true))
				{
					cMeldung.addUnTranslated("<Creating Trigger OK>");
					bibMSG.add_MESSAGE(new MyE2_Info_Message(cMeldung));
				}
				else
				{
					cMeldung.addUnTranslated("<Creating Trigger ERROR>");	
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cMeldung));
					
				}
			}
		}
		
		
		private void baueViews()
		{
			String 		cAbfrageMandanten = "SELECT ID_MANDANT,NVL(NAME1,TO_CHAR(ID_MANDANT)) FROM "+bibE2.cTO()+".JD_MANDANT order by ID_MANDANT";
			String[][] 	cMandanten = bibDB.EinzelAbfrageInArray(cAbfrageMandanten, false);

			if (cMandanten == null || cMandanten.length==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der vorhandenen Mandanten !"));
				return;
			}
			
			
			//Vector<String> vOutPutMessages = new Vector<String>();
			String cTabelle = "JT_FUHREN_RECHNUNGEN";
			
			
			for (int i=0;i<cMandanten.length;i++)
			{
				String cNamenView = "V" + cMandanten[i][0].trim() + "_" + cTabelle.substring(3);

				if (cTabelle.toUpperCase().startsWith("JD_"))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mandant: ",true,cMandanten[i][0].trim(),false," --> Keinen View erzeugt --> ",true,cTabelle,false," ist eine Definitionstabelle !",true)));
				}
				else
				{
					String cSqlBaueNeuView = "CREATE OR REPLACE VIEW " + cNamenView + " AS SELECT * FROM " + cTabelle + " WHERE ID_MANDANT=" + cMandanten[i][0].trim();

					if (bibDB.ExecSQL(cSqlBaueNeuView,true))
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("OK!     Neuen View ",true,cNamenView,false," erfolgreich erstellt !",true)));
					else
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("ERROR!  Neuen View ",true,cNamenView,false," nicht erstellt !",true)));
				}
			}
		}
	}
	
	
	
	public class ownActionTeste_RECORD_FUHREN_RECHNUNGEN extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
//			RECLIST_FUHREN_RECHNUNGEN  recListTest = new RECLIST_FUHREN_RECHNUNGEN("SELECT * FROM JT_FUHREN_RECHNUNGEN WHERE ID_FUHREN_RECHNUNGEN<5000");
			
//			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Reclist erzeugt, Anzahl Saetze: "+recListTest.get_vKeyValues().size())));
			
			AW_WarenstroemeHandler oHandler = new AW_WarenstroemeHandler();
			oHandler.korrigiereEintraege();
			
			get_TextArea4Output().setText(bibMSG.MV().get_MessagesAsText());
			
		}
	}
	

	
	public class ownActionTeste_RECORDLIST_FUHREN extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			long start = System.currentTimeMillis();
			long end = System.currentTimeMillis();
			String duration = Long.toString(((end-start) / 1000));
			
			start = System.currentTimeMillis();
			RECLIST_VPOS_TPA_FUHRE recListTest = new RECLIST_VPOS_TPA_FUHRE(" rownum < 10000","");
			end = System.currentTimeMillis();
			duration = Long.toString(((end-start) / 1000)); 
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Reclist erzeugt in " + duration + " Sekunden.  Anzahl Saetze: "+recListTest.get_vKeyValues().size())));
			
			get_TextArea4Output().setText(bibMSG.MV().get_MessagesAsText());
			
		}
	}
	

	
	private class teste_db_trigger extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Vector<String>  vSQL = new Vector<String>();
			
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_ADRESSE SET LIEF_NR=null WHERE ID_ADRESSE=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_ADRESSE SET ABN_NR=null WHERE ID_ADRESSE=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_LIEFERADRESSE SET ERZEUGT_VON=ERZEUGT_VON  WHERE ID_ADRESSE_BASIS=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_MITARBEITER SET ERZEUGT_VON=ERZEUGT_VON  WHERE ID_ADRESSE_BASIS=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_VKOPF_KON SET ERZEUGT_VON=ERZEUGT_VON  WHERE ID_ADRESSE=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_VKOPF_RG SET ERZEUGT_VON=ERZEUGT_VON  WHERE ID_ADRESSE=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_VKOPF_TPA SET ERZEUGT_VON=ERZEUGT_VON  WHERE ID_ADRESSE=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_VPOS_RG SET ERZEUGT_VON=ERZEUGT_VON  WHERE ID_ADRESSE=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_VPOS_TPA_FUHRE SET ERZEUGT_VON=ERZEUGT_VON  WHERE ID_ADRESSE_LAGER_ZIEL=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_VPOS_TPA_FUHRE SET ERZEUGT_VON=ERZEUGT_VON  WHERE ID_ADRESSE_ZIEL=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_VPOS_TPA_FUHRE SET ERZEUGT_VON=ERZEUGT_VON  WHERE ID_ADRESSE_SPEDITION=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_EMAIL SET ERZEUGT_VON=ERZEUGT_VON  WHERE ID_ADRESSE=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_INTERNET SET ERZEUGT_VON=ERZEUGT_VON  WHERE ID_ADRESSE=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_KONTO SET ERZEUGT_VON=ERZEUGT_VON  WHERE ID_ADRESSE=24884");
			vSQL.add("UPDATE " + bibE2.cTO() +".JT_KOMMUNIKATION SET ERZEUGT_VON=ERZEUGT_VON  WHERE ID_ADRESSE=24884");

			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL,true));
			
			
		}
		
	}
	
	
	
	//test fuer recordweises summieren
	private class ownActionSummiere extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			//zuerst ein array mit eine anzahl fuhren
			String[][] idFuhren = bibDB.EinzelAbfrageInArray("SELECT ID_VPOS_TPA_FUHRE FROM JT_VPOS_TPA_FUHRE ORDER BY 1");
			
			BigDecimal bdSumme = new BigDecimal(0);

			String cWhere = "";
			cWhere = bibALL.Concatenate(bibVECTOR.get_VectorFromArray(idFuhren),",","0");
			
			String cSQL ="SELECT SUM(ANTEIL_LADEMENGE_LIEF) FROM JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE IN ("+cWhere+")"; 
			
			System.out.println(bibDB.EinzelAbfrage(cSQL));
			
//			for (int i=0;i<idFuhren.length;i++)
//			{
//				String cAntwort = bibDB.EinzelAbfrage("SELECT ANTEIL_LADEMENGE_LIEF FROM JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE="+idFuhren[i][0]);
//				
//				MyBigDecimal  bdTest = new MyBigDecimal(cAntwort, BigDecimal.ZERO, BigDecimal.ZERO, false);
//				
//				bdSumme.add(bdTest.get_bdWert());
//				
//				if (i % 10==0)
//				{
//					System.out.println(""+i);
//				}
//			}
			
			
			
		}
		
	}
	
	
	
	
//	private class ownbuttonTestJasperExecuter extends MyE2_Button {
//
//		private ownJasperHash oJHash = null;
//		public ownbuttonTestJasperExecuter() throws myException {
//			super(new MyE2_String("Test Executers"));
//			
//			oJHash = new ownJasperHash("kontrakt", new JasperFileDef_PDF());
//			
//			oJHash.get_vExecuters().add(new Jasper_Executer_Test("Test1"));
//			oJHash.get_vExecuters().add(new Jasper_Executer_Test("test2"));
//			oJHash.get_vExecuters().add(new ownExecuter("Achtung ! Ableitung !"));
//			
//			
//			this.add_oActionAgent(new XX_ActionAgent() {
//				
//				@Override
//				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//					ownbuttonTestJasperExecuter.this.oJHash.ExecuterStart(null, ownExecuter.class, null);
//				}
//			});
//		}
//		
//	}
//	
//	
//	private class ownExecuter extends Jasper_Executer_Test {
//
//		public ownExecuter(String c_TextAusgabe) {
//			super(c_TextAusgabe);
//		}
//		
//	}
//	
//	
//	private class ownJasperHash extends E2_JasperHASH {
//
//		public ownJasperHash(String cNameOfReport, JasperFileDef jasperFileDef) throws myException {
//			super(cNameOfReport, jasperFileDef);
//
//		}
//
//		@Override
//		protected MailBlock create_MailBlock() throws myException {
//			return null;
//		}
//
//		@Override
//		public boolean get_bIsDesignedForMail() throws myException {
//			return false;
//		}
//
//		@Override
//		public void doActionAfterCreatedFile() throws myException {
//		}
//		
//	}
//	

	
}


