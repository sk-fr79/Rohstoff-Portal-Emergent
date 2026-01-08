package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BUCHUNG;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FBAM;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FBAM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRecordCompareRule;
import panter.gmbh.indep.dataTools.MyRecordComparer;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS.DA_Decision_CheckKreditVersicherung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print.___Sammler_ID_VPOS_TPA_FUHRE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.____DA_DecisionHat_EU_VERTRAG;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.____DA_Decision_BorderCrossing;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.____DA_Decision_DatumsDifferenz;
import rohstoff.Echo2BusinessLogic._TAX.PRUEFKLASSEN.__FU_Validator_FuhreErlaubt;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;

public class FUB___LIST_BT_Buchung extends MyE2_ButtonInLIST  implements DS_IF_components4decision
{

	private E2_NavigationList 		oNaviList = null;
	private boolean     			bUseInList = false;
	
	//hier werden erst alle Statement-Vectoren gesammelt
	private VECTOR_BUCH_StatementBuilder   vBuchStatementVector = new VECTOR_BUCH_StatementBuilder();
	
	//dann werden nochmal ungeeignete rausgefiltert
	private VECTOR_BUCH_StatementBuilder   vBuchStatementVectorBereitZumBuchen = new VECTOR_BUCH_StatementBuilder();
	
	
	// 2011-03-29: weitere ActionAgents, die von aussen eingeschluesst werden koennen
	private Vector<XX_ActionAgent>         vAgents_4_BuchungsStartButton = new Vector<XX_ActionAgent>();
	


	/*
	 * kann sowohl als standard button aus dem menue, als auch als listenbutton benutzt werden
	 */
	public FUB___LIST_BT_Buchung(E2_NavigationList NaviList, boolean bFuerListe)
	{
		
		super(new MyE2_String("Buchung Mengenpositionen"));
		
		this.setToolTipText(new MyE2_String("Buchungssätze der Fuhre in Rechnungs- und Gutschriftenpositionen erzeugen").CTrans());
	
		this.bUseInList = bFuerListe;
		
		if (bFuerListe)
		{
			this.set_Text("");
			this.setIcon(E2_ResourceIcon.get_RI("listlabel_empty.png"));
			this.setStyle(MyE2_Button.StyleImageButtonNoBorders());
		}
			
		//2016-02-01: entscheidungsfrage, wenn datumsdifferenz zu gross ist
		this.add_oActionAgent(new ____DA_Decision_DatumsDifferenz(this, new fuhren_sammler()));
		
		//2016-02-23: und die neue EU-Vertrag-Pruefung
		this.add_oActionAgent(new ____DA_DecisionHat_EU_VERTRAG(this,new fuhren_sammler()));

		//2016-03-07: die kreditpruefung auch als decision
		this.add_oActionAgent(new own_Decision_CheckKredit(this));

		
		//2016-04-06: weitere desicion: bordercrossing
		this.add_oActionAgent(new ____DA_Decision_BorderCrossing(this,new fuhren_sammler()));

		
		this.add_oActionAgent(new ownActionAgentStartYesNO());
		
		

		this.add_IDValidator(new validDarfMengenbuchung());
		//2014-01-30: pruefung auf korrekte fuhre vor der buchung
		this.add_IDValidator(new __FU_Validator_FuhreErlaubt());
		
		this.add_GlobalAUTHValidator(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "BUCHUNG_MENGEN_POSITIONEN");

		
		this.oNaviList = NaviList;
		
	}
	
	

	/*
	 * hier fuer die benutzung aus dem Speichern- und -Mehr-dialog aus der maske
	 */
	public FUB___LIST_BT_Buchung(E2_NavigationList NaviList, String cID_FUHRE)
	{
		
		super(new MyE2_String("Buchung Mengenpositionen"));
		
		this.setToolTipText(new MyE2_String("Buchungssätze der Fuhre in Rechnungs- und Gutschriftenpositionen erzeugen").CTrans());
	
		this.bUseInList = true;         
		this.EXT().set_C_MERKMAL(cID_FUHRE);          //dann holt er sich die ID_Fuhre aus der EXT
		
		//2016-02-01: entscheidungsfrage, wenn datumsdifferenz zu gross ist
		this.add_oActionAgent(new ____DA_Decision_DatumsDifferenz(this, new fuhren_sammler()));
		
		//2016-02-23: und die neue EU-Vertrag-Pruefung
		this.add_oActionAgent(new ____DA_DecisionHat_EU_VERTRAG(this,new fuhren_sammler()));

		//2016-03-07: die kreditpruefung auch als decision
		this.add_oActionAgent(new own_Decision_CheckKredit(this));

		//2016-04-06: weitere desicion: bordercrossing
		this.add_oActionAgent(new ____DA_Decision_BorderCrossing(this,new fuhren_sammler()));

		
		
		this.add_oActionAgent(new ownActionAgentStartYesNO());

		this.add_IDValidator(new validDarfMengenbuchung());
		//2014-01-30: pruefung auf korrekte fuhre vor der buchung
		this.add_IDValidator(new __FU_Validator_FuhreErlaubt());

		
		
		this.add_GlobalAUTHValidator(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "BUCHUNG_MENGEN_POSITIONEN");

		
		this.oNaviList = NaviList;
		
	}
	

	// 2011-03-29: weitere ActionAgents, die von aussen eingeschluesst werden koennen
	public Vector<XX_ActionAgent> get_vAgents_4_BuchungsStartButton() 
	{
		return vAgents_4_BuchungsStartButton;
	}


	
	private class validDarfMengenbuchung extends XX_ActionValidator
	{

		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) 
		{
			return new MyE2_MessageVector();
		}

		
		public MyE2_MessageVector isValid(String cID_Unformated) throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			PRUEF_RECORD_VPOS_TPA_FUHRE  recFuhre = new PRUEF_RECORD_VPOS_TPA_FUHRE(cID_Unformated,true);

			
			RECLIST_FBAM     recList = 			new RECLIST_FBAM("SELECT * FROM "+bibE2.cTO()+".JT_FBAM " +
																	" WHERE JT_FBAM.ID_VPOS_TPA_FUHRE_ORT IS NULL" +
																	" AND JT_FBAM.ID_VPOS_TPA_FUHRE="+recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
			
			RECLIST_FBAM     recList_Ort = 		new RECLIST_FBAM("SELECT * FROM "+bibE2.cTO()+".JT_FBAM WHERE" +
														" JT_FBAM.ID_VPOS_TPA_FUHRE IS NULL AND JT_FBAM.ID_VPOS_TPA_FUHRE_ORT IN ("+
																	"SELECT ID_VPOS_TPA_FUHRE_ORT " +
																	" FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT " +
																			" WHERE  NVL(DELETED,'N')='N' " +
																			" AND    JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE=" +recFuhre.get_ID_VPOS_TPA_FUHRE_cUF()+")");
			
			recList.ADD(recList_Ort, false);
			
			for (int i=0; i<recList.get_vKeyValues().size();i++)
			{
				RECORD_FBAM  recFBAM = recList.get(i);
				if (recFBAM.is_WM_GESPERRT_NO() || recFBAM.is_ABGESCHLOSSEN_BEHEBUNG_NO() || recFBAM.is_ABGESCHLOSSEN_KONTROLLE_NO())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Fuhre oder ein Zusatz hat eine offene Beanstandungsmeldung, BITTE DIESE ZUERST ABSCHLIESSEN !!!"));
					return oMV;
				}
			}
			
			if (recFuhre.is_IST_STORNIERT_YES())
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Fuhre wurde bereits storniert ... -> ID:"+cID_Unformated));

			if (recFuhre.is_DELETED_YES())
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Fuhre wurde gelöscht ... -> ID:"+cID_Unformated));
			
			if (recFuhre.is_OHNE_ABRECHNUNG_YES())
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Fuhre ist ohne Abrechnung ... -> ID:"+cID_Unformated));

			
			if (recFuhre.is_FUHRE_KOMPLETT_NO())
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Fuhre (Fahrplan) ist noch nicht komplett ausgefüllt ... -> ID:"+cID_Unformated));


			
			//falls bereits meldung, dann wird hier beendet
			if (oMV.get_bHasAlarms())
			{
				return oMV;
			}

			int iStatusBuchung = recFuhre.__Actual_StatusBuchung();

			if 		(iStatusBuchung== myCONST.STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Fuhre ist aus dem Archivbereich und kann nicht gebucht werden -> ID:"+cID_Unformated));
			}
			else if 		(iStatusBuchung== myCONST.STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Fuhre wurde noch nicht vollständig erfasst -> ID:"+cID_Unformated));
			}
			else if 		(iStatusBuchung== myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Fuhre besitzt keine Buchungspositionen -> ID:"+cID_Unformated));
			}
			else if (iStatusBuchung== myCONST.STATUS_FUHRE__GANZGEBUCHT)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Fuhre wurde bereits gebucht -> ID:"+cID_Unformated));
			}
			else if (iStatusBuchung== myCONST.STATUS_FUHRE__TEILSGEBUCHT)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Fuhre wurde bereits teilweise gebucht -> ID:"+cID_Unformated));
			}
			
			return oMV;
		}
	}
	
	

	/*
	 * hier deaktiviert, immer enabled = true
	 * 
	 */
	public void set_bEnabled_For_Edit(boolean _enabled) throws myException
	{
		boolean enabled = true;
		this.setEnabled(enabled);
			
	}

	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		FUB___LIST_BT_Buchung oButton = new FUB___LIST_BT_Buchung(this.oNaviList,this.bUseInList);
		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));

		return oButton;
	}

	
	private  class ownActionAgentStartYesNO extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FUB___LIST_BT_Buchung oThis = FUB___LIST_BT_Buchung.this;
			
			
			Vector<String> vID_VPOS_TPA_FUHRE = new Vector<String>();
			if (oThis.bUseInList)
			{
				vID_VPOS_TPA_FUHRE.add(oThis.EXT().get_C_MERKMAL());
			}
			else
			{
				vID_VPOS_TPA_FUHRE.addAll(oThis.oNaviList.get_vSelectedIDs_Unformated());
			}
			
			
			if (vID_VPOS_TPA_FUHRE.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte mindestens eine Position auswählen !",true),false);
			}
			else
			{
				// dann die validierung, die erkennen muesste, dass die fuhre evtl. nicht vollstaendig ist
				// zuerst nochmal die daemonen bemuehen, wegen des preisholens
				Vector<String> vSQL = new Vector<String>();
				for (int i=0;i<vID_VPOS_TPA_FUHRE.size();i++)
				{
					vSQL.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET L_NAME1=L_NAME1 WHERE ID_VPOS_TPA_FUHRE="+vID_VPOS_TPA_FUHRE.get(i));
				}
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));

				if (bibMSG.get_bIsOK())
				{

					//hier koennte sich der status nochmals veraendert haben, deshalb validieren
					// WICHTIG: die validierung wird einzeln ausgefuehrt, damit im falle der batch-verarbeitung
					//          die fehlerfrei validierten verarbeitet werden koennen
					
					MyE2_MessageVector  oMV_From_Validation = new MyE2_MessageVector();
					Vector<String>  vIDs_OK = new Vector<String>();
					for (int i=0;i<vID_VPOS_TPA_FUHRE.size();i++)
					{
						MyE2_MessageVector  oMV_innen = bibE2.get_LAST_ACTIONEVENT().make_ID_Validation(bibALL.get_Vector(vID_VPOS_TPA_FUHRE.get(i)));
						if (oMV_innen.get_bIsOK())
						{
							vIDs_OK.add(vID_VPOS_TPA_FUHRE.get(i));
						}
						
						oMV_From_Validation.add_MESSAGE(oMV_innen);
					}
					
					
					//falls kein vIDs_OK gefunden wurde, dann die meldungen in den globalen messagevector und schluss
					if (vIDs_OK.size()==0)
					{
						bibMSG.add_MESSAGE(oMV_From_Validation);
					}
					
					
					//2012-04-11: weitere pruefung, ob evtl. ein angebot nicht korrekt zum leistungsdatum passt, wenn ja, dann ein rechteck einblenden
					MutableStyle  oStyleWarning = new MutableStyle();
					oStyleWarning.setProperty(Grid.PROPERTY_BORDER,new Border(4,new E2_ColorAlarm(),Border.STYLE_SOLID));
					oStyleWarning.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_5_5_5_5);
					oStyleWarning.setProperty( Grid.PROPERTY_WIDTH, new Extent(99, Extent.PERCENT));

					MyE2_Grid oGridWarnung = new MyE2_Grid(1);
					oGridWarnung.setStyle(oStyleWarning);
					Vector<MyE2_String>  vMeldungenAngebotsdatumFalsch = new Vector<MyE2_String>();
					Vector<MyE2_String>  vMeldungenWiegeSorteUngleichFuhrensorte = new Vector<MyE2_String>();
					oGridWarnung.setVisible(false);
					//2012-04-11
					
					
					if (bibMSG.get_bIsOK())
					{
						ownYESNOContainer oContainerZustimmung = null;
	
						//jetzt den klassenweiten VECTOR_BUCH_StatementBuilder fuellen mit allen Statements
						oThis.vBuchStatementVector.removeAllElements();
						oThis.vBuchStatementVectorBereitZumBuchen.removeAllElements();
						
						MyE2_MessageVector  oMV_SammleMessagesAufFuhrenEbene = new MyE2_MessageVector();
						
						for (int i=0;i<vID_VPOS_TPA_FUHRE.size();i++)
						{
							PRUEF_RECORD_VPOS_TPA_FUHRE 	recFuhre = 	new PRUEF_RECORD_VPOS_TPA_FUHRE(vID_VPOS_TPA_FUHRE.get(i),false);
							VECTOR_BUCH_StatementBuilder  	vSTMT_Tmp = recFuhre.get_vAbrechnungsStatements(false);

							//2012-04-11: weitere pruefung, ob evtl. ein angebot nicht korrekt zum leistungsdatum passt, wenn ja, dann ein rechteck einblenden
							vMeldungenAngebotsdatumFalsch.addAll(recFuhre.get_PruefListe_Angebot_korreliert_mit_Leistungsdatum());
							//2012-05-07: weitere pruefung: ist die sorte im wiegeschein gleich der fuhrensorte ??
							vMeldungenWiegeSorteUngleichFuhrensorte.addAll(recFuhre.get_PruefListe_WiegescheinSorteGleichFuhrenSorte());

							
							if (vSTMT_Tmp.get_bIstKomplettBuchbar())   		//bei kompletten Buchungspositionen werden die mengen links und rechts untersucht
							{
								//dann die mengendifferenz links und rechts vergleichen
								BigDecimal[] bd = recFuhre.get_MengenVorAbrechnung();   //3 werte links/rechts (incl.Abzuege) und verhaeltnis
								
								
								if ( (bd[2].compareTo(bibALL.get_RECORD_MANDANT().get_GRENZE_MENG_DIFF_ABRECH_PROZ_bdValue(new BigDecimal(0.0001)))>0) ||
									 (bd[0].compareTo(BigDecimal.ZERO)==0 && bd[1].compareTo(BigDecimal.ZERO)!=0) ||
									 (bd[0].compareTo(BigDecimal.ZERO)!=0 && bd[1].compareTo(BigDecimal.ZERO)==0) )
								{
									MyE2_String cHelp = new MyE2_String("Mengendifferenz in der Fuhre: ",true,MyNumberFormater.formatDez(bd[0],1,true)+" <--> "+MyNumberFormater.formatDez(bd[1],1,true)+
														" --> "+MyNumberFormater.formatDez(bd[2],3,true)+" %",false);
			
									cHelp.addTranslated("    Fuhre:");
									cHelp.addUnTranslated(""+vID_VPOS_TPA_FUHRE.get(i));
									
									oMV_SammleMessagesAufFuhrenEbene.add(new MyE2_Warning_Message(cHelp));
								}
							}
							
							if (vSTMT_Tmp.get_bIstBereitZumBuchungsVorgang())
							{
								oThis.vBuchStatementVector.addAll(vSTMT_Tmp);
							}
							else
							{
								oMV_SammleMessagesAufFuhrenEbene.add(new MyE2_Warning_Message(new MyE2_String("Fuhre kann hat keine Buchungspostionen oder ist noch nicht bereit zum Buchen: ",true,vID_VPOS_TPA_FUHRE.get(i),false)));
							}
						}
						
						
						boolean bWarnungTitel = false;
						
						//2012-04-11: weitere pruefung, ob evtl. ein angebot nicht korrekt zum leistungsdatum passt, wenn ja, dann ein rechteck einblenden
						if (vMeldungenAngebotsdatumFalsch.size()>0)
						{
							oGridWarnung.setVisible(true);
							oGridWarnung.add(new MyE2_Label(new MyE2_String("ACHTUNG: Bitte prüfen und gegebenenfalls korrigieren !!"),MyE2_Label.STYLE_ERROR_BIGBIG()));
							bWarnungTitel = true;
							
							for (MyE2_String  cMeldung: vMeldungenAngebotsdatumFalsch)
							{
								oGridWarnung.add(new MyE2_Label(cMeldung));
							}
						}
						
						//2012-05-07: weitere pruefung: ist die sorte im wiegeschein gleich der fuhrensorte ??
						if (vMeldungenWiegeSorteUngleichFuhrensorte.size()>0)
						{
							if (!bWarnungTitel)
							{
								oGridWarnung.setVisible(true);
								oGridWarnung.add(new MyE2_Label(new MyE2_String("ACHTUNG: Bitte prüfen und gegebenenfalls korrigieren !!"),MyE2_Label.STYLE_ERROR_BIGBIG()));
							}
							
							for (MyE2_String  cMeldung: vMeldungenWiegeSorteUngleichFuhrensorte)
							{
								oGridWarnung.add(new MyE2_Label(cMeldung));
							}
						}
						
						
						//2011-03-18: Fehlerbehandlung: nfalls fehler auftreten muessen diese erst beseitigt werden
						MyE2_MessageVector  oMV_FehlerBeiPruefung = new MyE2_MessageVector();
						
						//jetzt noch die einzelnen statements auf fehler pruefen und die moeglichen zurueckgeben (hier werden auch die eigenen ladeorte und 0 - mengen rausgefiltert
						MyE2_MessageVector oMV_SammleMessagesAufBuchungsEbene = oThis.vBuchStatementVector.get_PruefeBuchungsStatements_Sammle_Gute(oThis.vBuchStatementVectorBereitZumBuchen,oMV_FehlerBeiPruefung);
						
						
						//2018-06-11: pruefung, ob eine fremdwaehrung im spiel ist
						MyE2_MessageVector mvWegenWaehrungen = new MyE2_MessageVector();
						FUB_OwnInfoGridWaehrungen gridForWaehrungen = new FUB_OwnInfoGridWaehrungen();
						try {
							//waehrungsgrid fuellen
							gridForWaehrungen._init(vID_VPOS_TPA_FUHRE);
							
							if (gridForWaehrungen.hasSomethingToShow()) {
								oGridWarnung._add(gridForWaehrungen, new RB_gld()._span(oGridWarnung.getSize())._ins(2, 5, 2, 2));
								oGridWarnung.setVisible(true);
							}
							
						} catch (Exception e) {
							mvWegenWaehrungen._addAlarm(S.ms("Fehler bei Währungsprüfung!").ut(e.getLocalizedMessage()));
							e.printStackTrace();
						}
						oMV_FehlerBeiPruefung._add(mvWegenWaehrungen);
						
						
						
						if (oMV_FehlerBeiPruefung.get_bIsOK())
						{
							if (oThis.vBuchStatementVectorBereitZumBuchen.size()>0 && oMV_FehlerBeiPruefung.get_bIsOK())
							{
								//jetzt die messagevectoren sammeln
								MyE2_MessageVector oMV_AussenInnen = new MyE2_MessageVector();
								oMV_AussenInnen.add_MESSAGE(oMV_From_Validation);
								oMV_AussenInnen.add_MESSAGE(oMV_SammleMessagesAufFuhrenEbene);
								oMV_AussenInnen.add_MESSAGE(oMV_SammleMessagesAufBuchungsEbene);
								
								oContainerZustimmung = new ownYESNOContainer(
										    		new MyE2_String("Sicherheitsabfrage "),
										    		oMV_AussenInnen.size()==0?new MyE2_String("Positionen erzeugen"):new MyE2_String("Es gibt Mengendifferenzen (EK-VK-Seite)"),
										    		oMV_AussenInnen,
										    		oMV_AussenInnen.size()==0?new MyE2_String("JA"):new MyE2_String("Trotzdem Positionen erzeugen"),
										    		new MyE2_String("Nein"),
										    		oGridWarnung
										    		);
								Vector<XX_ActionAgent> vAgents = new Vector<XX_ActionAgent>();
								vAgents.add(new ownActionAgentMacheBuchung());
								vAgents.add(new ownActionAgentKontrolliere_Nullsummen_Buchungen());
								
								//2011-02-03: weitere agent, der prueft:
								/*
								 * wird eine Fuhre, die geschlossen ist, wieder geoeffnet, dann wird im falle, dass die position bereits einem nicht gedruckten rechnungskopf zugeordnet wurde,
								 * diese position aus dem kopf geloescht und dann neu in die freien positionen geschoben. Dieser agent prueft die gleichheit der position und ihres geloeschten
								 * vorgaengers und ordnet bei gleichheit die position einem evtl. vorhandenen vorgangskopf wieder zu
								 */
								vAgents.add(new FUB_ZUSATZ_ACTIONAGENT_Pruefe_Ob_Neue_Pos_InKopfsatz_muss(oThis, FUB___LIST_BT_Buchung.this.bUseInList, FUB___LIST_BT_Buchung.this.oNaviList));
								//2011-02-02: ende
								
								vAgents.add(new ownActionRefreshListe());
								
								//2011-03-29: weiter agents von aussen
								if (oThis.vAgents_4_BuchungsStartButton.size()>0)
								{
									vAgents.addAll(oThis.vAgents_4_BuchungsStartButton);
								}
								
								
								oContainerZustimmung.set_ActionAgentForOK(vAgents);
								
								oContainerZustimmung.show_POPUP_BOX();
							}
							else
							{
								bibMSG.add_MESSAGE(oMV_SammleMessagesAufFuhrenEbene);
								bibMSG.add_MESSAGE(oMV_SammleMessagesAufBuchungsEbene);
							}
						}
						else
						{
							bibMSG.add_MESSAGE(oMV_FehlerBeiPruefung);
						}
					}
				
				
				}
				
				
				
					
			}
		}
	}
	
	
	
	
	
	
	
	
	/*
	 * eigene popup-klasse zum groessenspeichern
	 */
	private class ownYESNOContainer extends E2_ConfirmBasicModuleContainer
	{

		public ownYESNOContainer(MyE2_String windowtitle,MyE2_String texttitle, MyE2_MessageVector oMV,MyE2_String labelOKButton, MyE2_String labelCancelButton, MyE2_Grid oGridWarnung)  throws myException
		{

			//super(windowtitle, texttitle, oMV, null,null,null, labelOKButton, labelCancelButton, new Extent(800), new Extent(500));
			super(windowtitle, texttitle, oMV, null,oGridWarnung,null, labelOKButton, labelCancelButton, new Extent(800), new Extent(500));
			//2012-04-11: warnungsbox einbauen, wenn datum des angebotes falsch ist

			
		}

		
	}
	
	
	
	
	private class ownActionAgentMacheBuchung extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FUB___LIST_BT_Buchung oThis = FUB___LIST_BT_Buchung.this;
			
//			Vector<String> vID_Fuhren = new Vector<String>();
//			if (oThis.bUseInList)
//			{
//				vID_Fuhren.add(oThis.EXT().get_C_MERKMAL());
//			}
//			else
//			{
//				vID_Fuhren.addAll(oThis.oNaviList.get_vSelectedIDs_Unformated());
//			}
//			
//			
//			if (vID_Fuhren.size()==0)
//			{
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte mindestens eine Position auswählen !",true),false);
//			}
//			else
//			{
//				bibE2.get_LAST_ACTIONEVENT().make_ID_Validation_ADD_TO_Global_MV(vID_Fuhren);
//				
//				if (bibMSG.get_bIsOK())
//				{
//					Vector<BUCH_StatementBuilder> vSQL_Statmentbuilders = new Vector<BUCH_StatementBuilder>();
//					
//					for (String cID: vID_Fuhren)
//					{
//						vSQL_Statmentbuilders.addAll(new  PRUEF_RECORD_VPOS_TPA_FUHRE(cID,false).get_vBuchbareAbrechnungsStatements());
//					}
//					
//					Vector<String> vSQL = new Vector<String>();
//
////  ------------------------------------------------------------------------------------------------------------
//					
					Vector<String> vSQL = new Vector<String>();
					
					int iCountRechnung = 0;
					int iCountGutschrift = 0;
					
					for (BUCH_StatementBuilder oSTMT: oThis.vBuchStatementVectorBereitZumBuchen)
					{
						
						// -hier muss geprueft werden, ob das abrechnungsstatement auch wirklich ausgefuehrt wird (wenn noch keine mengenpruefung erfolgt ist oder kein preis vorhanden ist, dann nicht !!!)
						vSQL.add(oSTMT.get_CompleteInsertString("JT_VPOS_RG", bibE2.cTO()));
						vSQL.addAll(oSTMT.get_vSQL_AbzugsStack());
						
						if (oSTMT.get_bEK())
						{
							iCountGutschrift++;
						}
						else
						{
							iCountRechnung++;
						}
							
					}
					
					bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
					
					
					if (bibMSG.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurden ",true," "+iCountGutschrift,false," Gutschriftspositionen und ",true," "+iCountRechnung,false," Rechnungspositionen geschrieben !",true)));
					}
					
//					//jetzt navilist neu aufbauen
				}
			
		
	}
	
	
	private class ownActionRefreshListe extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FUB___LIST_BT_Buchung oThis = FUB___LIST_BT_Buchung.this;
			
			Vector<String> vID_Fuhren = new Vector<String>();
			
			if (oThis.bUseInList)
			{
				vID_Fuhren.add(oThis.EXT().get_C_MERKMAL());
			}
			else
			{
				vID_Fuhren.addAll(oThis.oNaviList.get_vSelectedIDs_Unformated());
			}

			for (String cID: vID_Fuhren)
			{
				oThis.oNaviList.get_ComponentMAP(cID)._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, false, null);
			}
		}
	}
	
	
	

	/*
	 * ActionAgent prueft, ob die neubuchung mit einer gleichen, negativen buchung korrelliert
	 * und loescht dieses stornopaar dann weg
	 */
	private class ownActionAgentKontrolliere_Nullsummen_Buchungen extends XX_ActionAgent
	{
		private MyE2_MessageVector vInfos = new MyE2_MessageVector();

		
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FUB___LIST_BT_Buchung oThis = FUB___LIST_BT_Buchung.this;

			this.vInfos.removeAllElements();
			
			Vector<String> vID_Fuhren = new Vector<String>();
			if (oThis.bUseInList)
			{
				vID_Fuhren.add(oThis.EXT().get_C_MERKMAL());
			}
			else
			{
				vID_Fuhren.addAll(oThis.oNaviList.get_vSelectedIDs_Unformated());
			}

			
			Vector<String> vSQL_Stack = new Vector<String>();
			
			for (int i=0;i<vID_Fuhren.size();i++)
			{
				RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(vID_Fuhren.get(i));
				
				
				/*
				 * es muss mindestens 2 RG-positionen in den freien positionen geben: eine neue und eine S2 
				 * Erst dann kann geprueft werden ob die 2 letzten exact aufeinander passen
				 */
				
				//zuerst die haupt-fuhre bearbeiten (EK-Seite)
				RECLIST_VPOS_RG  recListPosEK = recFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord(
						"NVL(DELETED,'N')='N' AND ID_VKOPF_RG IS NULL AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL AND LAGER_VORZEICHEN=1", "ID_VPOS_RG DESC", true);
				
				vSQL_Stack.addAll(this.get_SQL_Statements_zur_Loeschung(recListPosEK, new MyE2_String("Position automatisch entfernt:  Fuhre ",true,""+vID_Fuhren.get(i),false," EK-Seite",true)));

				//dann die haupt-fuhre bearbeiten (VK-Seite)
				RECLIST_VPOS_RG  recListPosVK = recFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord(
						"NVL(DELETED,'N')='N' AND ID_VKOPF_RG IS NULL AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL AND LAGER_VORZEICHEN=-1", "ID_VPOS_RG DESC", true);
				
				vSQL_Stack.addAll(this.get_SQL_Statements_zur_Loeschung(recListPosVK, new MyE2_String("Position automatisch entfernt:  Fuhre ",true,""+vID_Fuhren.get(i),false," VK-Seite",true)));

				RECLIST_VPOS_TPA_FUHRE_ORT reclistORT = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N'", null, true);
				for (int k=0;k<reclistORT.get_vKeyValues().size();k++)
				{
					RECORD_VPOS_TPA_FUHRE_ORT recORT = reclistORT.get(k);
					RECLIST_VPOS_RG  recListPosOrt = recORT.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord("NVL(DELETED,'N')='N' AND ID_VKOPF_RG IS NULL", "ID_VPOS_RG DESC", true);

					vSQL_Stack.addAll(this.get_SQL_Statements_zur_Loeschung(recListPosOrt,
							recORT.get_DEF_QUELLE_ZIEL_cUF().equals("EK")?
							new MyE2_String("Position automatisch entfernt:  Fuhrenort zu Fuhre ",true,""+vID_Fuhren.get(i),false," EK-Seite",true):
							new MyE2_String("Position automatisch entfernt:  Fuhrenort zu Fuhre ",true,""+vID_Fuhren.get(i),false," VK-Seite",true)));
				}
				
			}

			if (vSQL_Stack.size()>0)
			{
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL_Stack, true));
				if (bibMSG.get_bIsOK())
				{
					bibMSG.add_MESSAGE(this.vInfos);
				}
			}
			
		}

		
		
		private Vector<String> get_SQL_Statements_zur_Loeschung(RECLIST_VPOS_RG  recListPosRG, MyE2_String Hinweis) throws myException
		{
			Vector<String> vSQL = new Vector<String>();
			
			if (recListPosRG.size()>=2)
			{
				RECORD_VPOS_RG recRG_NEU = 	recListPosRG.get(0);
				RECORD_VPOS_RG recRG_S2 = 	recListPosRG.get(1);
				
				//1. bedingung
				if (recRG_S2.get_ID_VPOS_RG_STORNO_VORGAENGER_lValue(new Long(-1)).longValue()>0)
				{
					//2. bedingung
					if (new ownRecordComparerComparePOS().IsEqual(recRG_NEU, recRG_S2))
					{
						RECORD_VPOS_RG recRG_S1 = new RECORD_VPOS_RG(""+recRG_S2.get_ID_VPOS_RG_STORNO_VORGAENGER_lValue(new Long(-1)).longValue());
						
						//3. Bedingung (muesste eigentlich immer so sein)
						if (recRG_S1.get_ID_VPOS_RG_STORNO_NACHFOLGER_lValue(new Long(-1)).longValue()==recRG_S2.get_ID_VPOS_RG_lValue(new Long(-2)))
						{
							//dann alles ausfuehren
							
							recRG_NEU.set_NEW_VALUE_DELETED("Y");
							recRG_NEU.set_NEW_VALUE_DEL_GRUND("AUTOMATISCHE STORNO-ENTFERNUNG");
							recRG_NEU.set_NEW_VALUE_DEL_DATE(bibALL.get_cDateNOW());
							
							recRG_S2.set_NEW_VALUE_DELETED("Y");
							recRG_S2.set_NEW_VALUE_DEL_GRUND("AUTOMATISCHE STORNO-ENTFERNUNG");
							recRG_S2.set_NEW_VALUE_DEL_DATE(bibALL.get_cDateNOW());
							recRG_S2.set_NEW_VALUE_ID_VPOS_RG_STORNO_VORGAENGER(null);
							
							recRG_S1.set_NEW_VALUE_ID_VPOS_RG_STORNO_NACHFOLGER(null);
							
							vSQL.add(recRG_NEU.get_SQL_UPDATE_STATEMENT(null, true));
							vSQL.add(recRG_S2.get_SQL_UPDATE_STATEMENT(null, true));
							vSQL.add(recRG_S1.get_SQL_UPDATE_STATEMENT(null, true));
							
							this.vInfos.add_MESSAGE(new MyE2_Info_Message(Hinweis));
							
						}
					}
				}
			}
			return vSQL;
		}
		
		
		
		private class ownRecordComparerComparePOS extends MyRecordComparer
		{
			
			public ownRecordComparerComparePOS() throws myException
			{
				super();
				this.add_CompareRule("ID_MANDANT");
				this.add_CompareRule("POSITION_TYP");
				this.add_CompareRule("ID_ARTIKEL");
				this.add_CompareRule("ARTBEZ1");
				this.add_CompareRule("ARTBEZ2");
				this.add_CompareRule("EINHEITKURZ");
				this.add_CompareRule("ANR1");
				this.add_CompareRule("ANR2");
				this.add_CompareRule("ZOLLTARIFNR");
				this.add_CompareRule("EINZELPREIS");
				this.add_CompareRule("STEUERSATZ");
				this.add_CompareRule("ID_VKOPF_RG");
				this.add_CompareRule("EINHEIT_PREIS_KURZ");
				this.add_CompareRule("MENGENDIVISOR");
				this.add_CompareRule("ID_ADRESSE");
				this.add_CompareRule("BESTELLNUMMER");
				this.add_CompareRule("VORGANG_TYP");
				this.add_CompareRule("EINZELPREIS_ABZUG");
				this.add_CompareRule("EINZELPREIS_RESULT");
				this.add_CompareRule("POSITIONSKLASSE");
				this.add_CompareRule("LAGER_VORZEICHEN");
				
				//2011-06-08: eunotiz und eucode sind nicht mehr relevant fuer gleichheit
				//this.add_CompareRule("EUNOTIZ");
				//this.add_CompareRule("EUCODE");
				
				this.add_CompareRule("ID_STRECKEN_DEF");
				this.add_CompareRule("AUSFUEHRUNGSDATUM");
				this.add_CompareRule("ID_ARTIKEL_BEZ");
				this.add_CompareRule("LIEFERBEDINGUNGEN");
				this.add_CompareRule("WIEGEKARTENKENNER");
				this.add_CompareRule("ID_VPOS_KON_ZUGEORD");
				this.add_CompareRule("ID_VPOS_TPA_FUHRE_ZUGEORD");
				this.add_CompareRule("BEMERKUNG_INTERN");
				this.add_CompareRule("ID_VPOS_X_NACHFOLGER");
				this.add_CompareRule("ID_VPOS_PREISKLAMMER");
				this.add_CompareRule("EINZELPREIS_FW");
				this.add_CompareRule("EINZELPREIS_ABZUG_FW");
				this.add_CompareRule("EINZELPREIS_RESULT_FW");
				this.add_CompareRule("WAEHRUNGSKURS");
				this.add_CompareRule("ID_WAEHRUNG_FREMD");
//				this.add_CompareRule("ZAHLUNGSBEDINGUNGEN");
//				this.add_CompareRule("ID_ZAHLUNGSBEDINGUNGEN");
//				this.add_CompareRule("ZAHLTAGE");
//				this.add_CompareRule("FIXMONAT");
//				this.add_CompareRule("FIXTAG");
				this.add_CompareRule("SKONTO_PROZENT");
				this.add_CompareRule("ID_VPOS_TPA_FUHRE_ORT_ZUGEORD");
				this.add_CompareRule("EU_STEUER_VERMERK");
				
				this.add_CompareRule(new ownCompareRuleNegativ("ANZAHL"));
				this.add_CompareRule(new ownCompareRuleNegativ("ANZAHL_ABZUG"));
				this.add_CompareRule(new ownCompareRuleNegativ("GESAMTPREIS"));
				this.add_CompareRule(new ownCompareRuleNegativ("GESAMTPREIS_FW"));
				this.add_CompareRule(new ownCompareRuleNegativ("GESAMTPREIS_ABZUG"));
				this.add_CompareRule(new ownCompareRuleNegativ("GESAMTPREIS_ABZUG_FW"));

				this.add_CompareRule(new ownCompareRuleSingleString("DELETED"));
				this.add_CompareRule(new ownCompareRuleSingleString("OHNE_STEUER"));
				this.add_CompareRule(new ownCompareRuleSingleString("IST_SONDERPOSITION"));
				/*
				 * situation: fuhre zuerst von leber nach swisssteal, dann geaendert in streckenfuhre
				 * skb-aussenlager leber zu swisssteal. Damit ist die Swisssteal-seite nicht beruehrt,
				 * wird aber trotzdem nicht rueckabgewickelt
				 */
//				this.add_CompareRule(new ownCompareRuleSingleString("STRECKENGESCHAEFT"));        
				this.add_CompareRule(new ownCompareRuleSingleString("GEBUCHT"));
				
				//2011-06-09: aenderungen an den zusaetzlichen abzugesfeldern muessen auch verglichen werden
				this.add_CompareRule(new ownCompareRuleNegativ("ANZAHL_ABZUG_LAGER"));
				this.add_CompareRule(new ownCompareRuleNegativ("GPREIS_ABZ_MGE"));
				this.add_CompareRule(new ownCompareRuleNegativ("GPREIS_ABZ_MGE_FW"));
				this.add_CompareRule(new ownCompareRuleNegativ("GPREIS_ABZ_AUF_NETTOMGE"));
				this.add_CompareRule(new ownCompareRuleNegativ("GPREIS_ABZ_AUF_NETTOMGE_FW"));
				this.add_CompareRule(new ownCompareRuleNegativ("GPREIS_ABZ_VORAUSZAHLUNG"));
				this.add_CompareRule(new ownCompareRuleNegativ("GPREIS_ABZ_VORAUSZAHLUNG_FW"));
				
				this.add_CompareRule("EPREIS_RESULT_NETTO_MGE");
				this.add_CompareRule("EPREIS_RESULT_NETTO_MGE_FW");
				//---2011-06-09
				
				
				
				
				
				
				
				
				
				
				
			}

			
			private class ownCompareRuleNegativ extends MyRecordCompareRule
			{
				public ownCompareRuleNegativ(String cfieldname)
				{
					super(cfieldname);
				}

				@Override
				public boolean IsEqual(MyRECORD rec1, MyRECORD rec2)	throws myException
				{
					BigDecimal  bd1 = rec1.get_bdValue(this.get_cFIELDNAME(), new BigDecimal(0), 6);
					BigDecimal  bd2 = rec2.get_bdValue(this.get_cFIELDNAME(), new BigDecimal(0), 6);

					bd2 = bd2.multiply(new BigDecimal(-1));
					
//					//debug
//					boolean bTest =bd1.compareTo(bd2)==0; 
//					System.out.println(bTest?"GLEICH: "+this.get_cFIELDNAME():"UNGLEICH: "+this.get_cFIELDNAME());
//					//debug

					
					
					return (bd1.compareTo(bd2)==0);
				}
			}

			
			private class ownCompareRuleSingleString extends MyRecordCompareRule
			{
				public ownCompareRuleSingleString(String cfieldname)
				{
					super(cfieldname);
				}

				@Override
				public boolean IsEqual(MyRECORD rec1, MyRECORD rec2)	throws myException
				{
					String cText1 = rec1.get_UnFormatedValue(this.get_cFIELDNAME(), "N");
					String cText2 = rec2.get_UnFormatedValue(this.get_cFIELDNAME(), "N");
					

					
//					//debug
//					boolean bTest =cText1.equals(cText2); 
//					System.out.println(bTest?"GLEICH: "+this.get_cFIELDNAME():"UNGLEICH: "+this.get_cFIELDNAME());
//					//debug

					
					
					return (cText1.equals(cText2));
				}
			}

			
		}
		
	}
	
	
	

	//2016-02-01: entscheidungsfrage, wenn die datumswerte zu stark abweichen
	private class fuhren_sammler extends ___Sammler_ID_VPOS_TPA_FUHRE {
		

		public fuhren_sammler() {
			super();
		}

		@Override
		public Vector<String> get_vID_VPOS_TPA_FUHRE() throws myException {
			Vector<String> v_ids = new Vector<>();
			if (FUB___LIST_BT_Buchung.this.bUseInList) {
				v_ids.add(FUB___LIST_BT_Buchung.this.EXT().get_C_MERKMAL());
			} else {
				v_ids.addAll(FUB___LIST_BT_Buchung.this.oNaviList.get_vSelectedIDs_Unformated());
			}
			
			return v_ids;
		}

		@Override
		public void rebuild_Navilist_if_Needed() throws myException {
		}
		
	}
	
	
	//2016-01-29: descision-pruefungen
	private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
	private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
	private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
	private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();
	
	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
		return this.storage_vector_4_all_agents;
	}

	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
		return this.storage_vector_4_standard_agents;
	}

	@Override
	public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
		return this.storage_vector_4_decision_agents;
	}

	@Override
	public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {
		return this.hm_descision_containers;
	}

	
	//2016-04-12: bei der buchung auch die kreditversicherung pruefen
	private class own_Decision_CheckKredit extends DA_Decision_CheckKreditVersicherung {
		public own_Decision_CheckKredit(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}
		
		@Override
		public Vector<String> get_v_ids_fuhren_2_check_kredit() throws myException {
			Vector<String> vID_VPOS_TPA_FUHRE = new Vector<String>(); 
			vID_VPOS_TPA_FUHRE.addAll(new fuhren_sammler().get_vID_VPOS_TPA_FUHRE());
			return vID_VPOS_TPA_FUHRE;
		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
			return null;
		}

	}

	
}
