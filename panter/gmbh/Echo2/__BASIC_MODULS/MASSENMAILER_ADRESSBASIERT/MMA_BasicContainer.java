package panter.gmbh.Echo2.__BASIC_MODULS.MASSENMAILER_ADRESSBASIERT;


import java.util.Vector;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS__QUALIFIER_GRID_4_EMAIL;


/**
 * klasse, die es ermoeglicht, bestimmte Formulare als pdf zu erzeugen und mit den bei den
 * adressen hinterlegten eMail-Adressen zu versenden 
 * @author martin
 *
 */
public abstract class MMA_BasicContainer extends MyE2_Grid {
	
	
	
	//2013-11-20: mail-typen selektor (wird als selektionswerkzeug benutzt)
	private FS__QUALIFIER_GRID_4_EMAIL   	oQualifierMatrix = null;
	

	private Vector<E2_JasperHASH> 			vJasperHashes = new Vector<E2_JasperHASH>();
	
	private MailBlock_Vector                vMailBlocks = new MailBlock_Vector();

	private MyE2_Button   					oButtonToStartMailDialog = null;
	
	public MMA_BasicContainer() throws myException {
		super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.oButtonToStartMailDialog = new ownButtonBaueReportsAuf();
		
		this.oQualifierMatrix = new FS__QUALIFIER_GRID_4_EMAIL(new SQLField("JT_TEST","ID_TEST",new MyE2_String(""),bibE2.get_CurrSession()), "JT_TEST");
		
		//jetzt die vorgabe-auswahl definieren
		this.oQualifierMatrix.Preset_Values(this.get_vMailTypenVorgabe());
		
		this.do_BasicSettingsWithThisContainer();
		
	}


	
	private class ownButtonBaueReportsAuf extends MyE2_Button  {

		public ownButtonBaueReportsAuf() {
			super("Baue Dokumente", MyE2_Button.StyleTextButtonCentered(Color.GREEN, Color.LIGHTGRAY, Color.BLACK, Color.DARKGRAY, new E2_FontBold(2)));
			
			this.setToolTipText(new MyE2_String("Aufbau der Dokumente, sammeln der Mailadressen und Anzeigen des Sendedialogs ...").CTrans());
			
			this.add_oActionAgent(new ownActionAgent());
			
		}

		
		private class ownActionAgent extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				MMA_BasicContainer oThis = MMA_BasicContainer.this;
				
				Vector<MMA_ID_ADRESSE>  vID_Adressen = oThis.get_vID_ADRESSE();
				
				if (vID_Adressen.size()==0) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurden keine Adressen definiert ...")));
					return;
				}
				
				oThis.vJasperHashes.removeAllElements();
				oThis.vMailBlocks.removeAllElements();
				
				int iFehlerKeinReport = 0;
				int iReportErzeugtErfolgreichMitInhalt = 0;
				int iReportErzeugtAberAllesLeer = 0;
				
				for (MMA_ID_ADRESSE oID_ADRESSE : vID_Adressen) {
					
					E2_JasperHASH  	oJasperHash = oThis.get_JasperHash(oID_ADRESSE.ID_ADRESSE);
					MailBlock   	oMailBlock  = null;
					try {
						oJasperHash.Build_tempFile(false);
					} catch (myException ex) {
						iFehlerKeinReport++;
						continue;
					}

					if (oJasperHash.get_bResultIsEmpty()) {
						iReportErzeugtAberAllesLeer ++;
					} else {
						
						iReportErzeugtErfolgreichMitInhalt ++;
						oMailBlock = oJasperHash.get_BUILD_AND_GET_MAILBLOCK();
						oMailBlock.add_VMailAdress4MailBlock(new MMA__V_MailAdressesFromAdresse(	oID_ADRESSE.ID_ADRESSE, 
																									oID_ADRESSE.bINCLUDE_BASISMAILS,
																									oID_ADRESSE.bINCLUDE_MitarbeiterMails,
																									oID_ADRESSE.bINCLUDE_LagerMails,
																									true, 
																									oThis.get_vMailTypenNachBenutzerEingabe()));
						if (oMailBlock.get_v_MailAdress4MailBlock().size()>0) {
							oThis.vMailBlocks.add(oMailBlock);
							oThis.vJasperHashes.add(oJasperHash);
						}
					}
				}
					
				
				if (oThis.vMailBlocks.size()>0) {
					
					if (iFehlerKeinReport>0) {
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Anzahl Adressen fehlerhaftem Report:",true,""+iFehlerKeinReport,false)));
					}

					if (iReportErzeugtAberAllesLeer>0) {
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Anzahl Adressen mit leerem Report:",true,""+iReportErzeugtAberAllesLeer,false)));
					}
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Anzahl erfolgreich erzeugte Mailblöcke :",true,""+iReportErzeugtErfolgreichMitInhalt,false)));
					
					oThis.STARTE_MASSENMAILER(oThis);

					
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde keine Mailadressen im gewählten Bereich ODER nur leere Report-Ergebnisse gefunden !!")));
					if (iFehlerKeinReport>0) {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Anzahl Adressen mit leerem oder fehlerhaftem Report:",true,""+iFehlerKeinReport,false)));
					}
				}
				
			}
			
		}
	}
	
	

	//abstracte methoden und funktionen
	
	//methode liefert die Adressen, die dem Versand zugrunde liegen
	public abstract Vector<MMA_ID_ADRESSE>  get_vID_ADRESSE() throws myException;

	public abstract void     		do_BasicSettingsWithThisContainer() throws myException;
	
	
	/**
	 * Beispiel fuer eigene subclass
	 * 	private class ownJasperHash extends E2_JasperHASH_STD {

		public ownJasperHash(String cID_Adresse) throws myException {
			super(		"INVENTUR_FORMULAR.jasper",
						new JasperFileDef_PDF(),
						"Serienmail",
						"Inventur",
						new MailSecurityPolicyAllowAll(),
						new MyE2_String("Mail an Lieferadresse"),
						new MyE2_String("Lieferadresse"),
						true);
			this.put("id_adresse", cID_Adresse);
		}
		
	}

	 * @param cID_ADRESSE
	 * @return
	 * @throws myException
	 */
	public abstract E2_JasperHASH   get_JasperHash(String cID_ADRESSE) throws myException;
	
	public abstract Vector<String>  get_vMailTypenVorgabe() throws myException;
	public abstract Vector<String>  get_vMailTypenNachBenutzerEingabe() throws myException;
	
	public abstract String          		get_TEXT_KLEMMBRETT_KENNER_FUER_BETREFF();
	public abstract String          		get_TEXT_KLEMMBRETT_KENNER_FUER_MAILTEXT();
	public abstract String         		 	get_TEXT_NAMENSANTEIL_FUER_ARCHIV();
	
	
	/**
	 * Methodenaufruf mit Standardwerten:
	 * 			new MMA_MassMailerStandard(oMMA_Container, new MailSecurityPolicyAllowAll()).baue_MailMaske(
					oMMA_Container.get_vMailBlocks(), 
					bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""), false, false, false, 0, null);

	 * @param oMMA_Container
	 * @throws myException
	 */
	public abstract void 					STARTE_MASSENMAILER(MMA_BasicContainer oMMA_Container) throws myException;
	
	
	
	public FS__QUALIFIER_GRID_4_EMAIL get_oQualifierMatrix() {
		return oQualifierMatrix;
	}

	public Vector<E2_JasperHASH> get_vJasperHashes() {
		return vJasperHashes;
	}

	public MailBlock_Vector get_vMailBlocks() {
		return vMailBlocks;
	}

	public MyE2_Button get_oButtonToStartMailDialog() {
		return oButtonToStartMailDialog;
	}


	
	
	
}
