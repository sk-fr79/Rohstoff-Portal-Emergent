package rohstoff.Echo2BusinessLogic.INVENTUR_FREMDLAGER;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.Service.PdServiceConcatenatePdfs;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowAll;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowNothing;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.__BASIC_MODULS.MASSENMAILER_ADRESSBASIERT.MMA_BasicContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.MASSENMAILER_ADRESSBASIERT.MMA_ID_ADRESSE;
import panter.gmbh.Echo2.__BASIC_MODULS.MASSENMAILER_ADRESSBASIERT.MMA_JasperHASH;
import panter.gmbh.Echo2.__BASIC_MODULS.MASSENMAILER_ADRESSBASIERT.MMA_MassMailerStandard;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.bibVECTOR;
import echopointng.Separator;

public class INF_ANFORDERUNG_INVENTUR_BasicModuleContainer extends Project_BasicModuleContainer {

	public static String   KLEMMBRETTKEY_INVENTURMAIL_BETREFF = "INVENTURMAIL_BETREFF";
	public static String   KLEMMBRETTKEY_INVENTURMAIL_TEXT = 	"INVENTURMAIL_TEXT";
	public static String   INVENTURMAIL_NAME_ARCHIV = 			"INVENTURMAIL";
	public static String   BUTTONKENNER_STARTE_VERSAND = 		"STARTE_VERSAND";
	
	


	private MyE2_CheckBox  oCB_Aktiv = 						new MyE2_CheckBox(new MyE2_String("Aktive Lager"),true,false);
	private MyE2_CheckBox  oCB_InAktiv = 					new MyE2_CheckBox(new MyE2_String("Inaktive Lager"),true,false);
	
	private MyE2_CheckBox  oCB_EigenWare = 					new MyE2_CheckBox(new MyE2_String("Lager mit Eigenware"),true,false);
	private MyE2_CheckBox  oCB_Fremdware = 					new MyE2_CheckBox(new MyE2_String("Lager mit Fremdware"),true,false);
	
	private MyE2_CheckBox  oCB_BlendeInfozeileEin = 		new MyE2_CheckBox(new MyE2_String("Infozeile einblenden"),true,false); 
	private MyE2_CheckBox  oCB_HauptlagerAuchDrucken = 		new MyE2_CheckBox(new MyE2_String("Hauptlager einbeziehen"),true,false);
	
	
	private Component_USER_DROPDOWN_NEW  oSelectUser = 		new Component_USER_DROPDOWN_NEW(false, 
																			new Integer(200), 
																			new E2_FontPlain(), 
																			new MyE2_String("Falls nur Kunden eines Sachbearbeiters selektiert werden sollen",true)) ;

	private INF_Selektor_LeftRight_Sorten  oSelektierAusblendSorten = new INF_Selektor_LeftRight_Sorten();
	
	
	
	private ownMassMailContainer    	oMassMailer = new ownMassMailContainer();
	
	
	public INF_ANFORDERUNG_INVENTUR_BasicModuleContainer() throws myException {
		super(E2_MODULNAMES.NAME_MODUL_VERSENDE_INVENTUR_ANFORDERUNG);
		
		this.add(this.oMassMailer, E2_INSETS.I(2,2,2,2));
		
		//jetzt evtl. alte schalterstellungen eintragen
		new INF_SAVE_SETTINGS().restore_values(this);
		
		
		/*
		 * actionagent triggert das speichern der maskeneinstellungen
		 */
		this.oMassMailer.get_oButtonToStartMailDialog().add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				new INF_SAVE_SETTINGS().save_Values(INF_ANFORDERUNG_INVENTUR_BasicModuleContainer.this);
			}
		});
		
	}

	
	private class ownMassMailContainer extends MMA_BasicContainer {

		public ownMassMailContainer() throws myException {
			super();
			
			
		}

		@Override
		public Vector<MMA_ID_ADRESSE> get_vID_ADRESSE() throws myException {
			
			INF_ANFORDERUNG_INVENTUR_BasicModuleContainer oThis = INF_ANFORDERUNG_INVENTUR_BasicModuleContainer.this;
			
			
			Vector<MMA_ID_ADRESSE> vID_ADRESSE = new Vector<MMA_ID_ADRESSE>();
			 			
			
			String cBedingungAktiv = "1=1";
			if (oThis.oCB_Aktiv.isSelected() && !oThis.oCB_InAktiv.isSelected()) {
				cBedingungAktiv ="NVL("+_DB.ADRESSE+"."+_DB.ADRESSE$AKTIV+",'N')='Y'";
			} else if (!oThis.oCB_Aktiv.isSelected() && oThis.oCB_InAktiv.isSelected()) {
				cBedingungAktiv ="NVL("+_DB.ADRESSE+"."+_DB.ADRESSE$AKTIV+",'N')='N'";
			} else if (!oThis.oCB_Aktiv.isSelected() && !oThis.oCB_InAktiv.isSelected()) {
				cBedingungAktiv ="1=2";
			}
				
			String cBedingungEigenWare = "1=1";
			if (oThis.oCB_EigenWare.isSelected() && !oThis.oCB_Fremdware.isSelected()) {
				cBedingungEigenWare ="NVL("+_DB.LIEFERADRESSE+"."+_DB.LIEFERADRESSE$ID_ADRESSE_FREMDWARE+",0)=0";
			} else if (!oThis.oCB_EigenWare.isSelected() && oThis.oCB_Fremdware.isSelected()) {
				cBedingungEigenWare ="NVL("+_DB.LIEFERADRESSE+"."+_DB.LIEFERADRESSE$ID_ADRESSE_FREMDWARE+",0)<>0";
			} else if (!oThis.oCB_EigenWare.isSelected() && !oThis.oCB_Fremdware.isSelected()) {
				cBedingungEigenWare ="1=2";
			}
				
			String cBedingungLagerSachbearbeiter = "1=1";
			if (S.isFull(oThis.oSelectUser.get_ActualWert())) {
				cBedingungLagerSachbearbeiter = "NVL("+_DB.ADRESSE$ID_USER_LAGER_SACHBEARB+",-1)="+oThis.oSelectUser.get_ActualWert();
			}
			
			
			
			String cID_ADRESSE_MAND = bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1");
			
			RECLIST_ADRESSE  recList = new RECLIST_ADRESSE("SELECT * FROM "+bibE2.cTO()+"."+_DB.ADRESSE+
														   " WHERE "+_DB.ADRESSE$SONDERLAGER+ " IS NULL AND " +_DB.ADRESSE$ID_ADRESSE+" IN " +
														   " (" +
														   " SELECT "+_DB.LIEFERADRESSE+"."+_DB.LIEFERADRESSE$ID_ADRESSE_LIEFER+
														   " FROM "+
														   _DB.LIEFERADRESSE+
														   " WHERE "+
														   _DB.LIEFERADRESSE$ID_ADRESSE_BASIS+"="+cID_ADRESSE_MAND+
														   " AND " +cBedingungEigenWare+
														   " AND " +cBedingungLagerSachbearbeiter+
														   	" )" +
														   	"" +
														   	" AND "+cBedingungAktiv);

			if (oThis.oCB_HauptlagerAuchDrucken.isSelected()) {
				vID_ADRESSE.add(new MMA_ID_ADRESSE(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("0"), true, false, false));
			}

			Vector<RECORD_ADRESSE>  vSortedAdress = recList.GET_SORTED_VECTOR(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1,_DB.ADRESSE$NAME2), true);
			
			
			for (int i=0; i<vSortedAdress.size();i++) {
				
				vID_ADRESSE.add(new MMA_ID_ADRESSE(vSortedAdress.get(i).get_ID_ADRESSE_cUF(),false,false,true));	
			}
			
			
			return vID_ADRESSE;
		}
 
		 
		
		@Override
		public void do_BasicSettingsWithThisContainer() throws myException {
			
			INF_ANFORDERUNG_INVENTUR_BasicModuleContainer oThis = INF_ANFORDERUNG_INVENTUR_BasicModuleContainer.this;
			
			this.setSize(2);
			
			this.add(new MyE2_Label(new MyE2_String("Versenden von Anforderungsformularen für Lagerbestände Aussenlager"), new E2_FontBold(2)),2, E2_INSETS.I(2,2,2,20));
			
			this.add(new Separator(),2,E2_INSETS.I(2,2,2,2));
			
			this.add(oThis.oCB_Aktiv,1,E2_INSETS.I(2,2,2,2));
			this.add(oThis.oCB_InAktiv,1,E2_INSETS.I(2,2,2,2));

			this.add(oThis.oCB_EigenWare,1,E2_INSETS.I(2,2,2,2));
			this.add(oThis.oCB_Fremdware,1,E2_INSETS.I(2,2,2,2));
			
			this.add(oThis.oCB_HauptlagerAuchDrucken,1,E2_INSETS.I(2,2,2,2));
			this.add(oThis.oCB_BlendeInfozeileEin,1,E2_INSETS.I(2,2,2,2));
			
			this.add(new Separator(),2,E2_INSETS.I(2,2,2,2));
			
			this.add(new MyE2_Label("Nur Kunden des Lager-Sachbearbeiters: "),1,E2_INSETS.I(6,2,2,2));
			this.add(oThis.oSelectUser,1,E2_INSETS.I(2,2,2,2));
			this.add(new MyE2_Label("Sorten ausblenden:"),1,E2_INSETS.I(6,2,2,2));
			this.add(oThis.oSelektierAusblendSorten,1,E2_INSETS.I(2,2,2,2));
			
			
			
			this.add(new Separator(),2,E2_INSETS.I(2,2,2,20));
			
			this.add(this.get_oQualifierMatrix(),2, E2_INSETS.I(2,2,2,20));
			
			MyE2_Button  oButtonStart = this.get_oButtonToStartMailDialog();
			oButtonStart.add_GlobalAUTHValidator_AUTO(INF_ANFORDERUNG_INVENTUR_BasicModuleContainer.BUTTONKENNER_STARTE_VERSAND);
			
			this.add(this.get_oButtonToStartMailDialog(),2, E2_INSETS.I(2,2,2,2));
			
		}

		@Override
		public E2_JasperHASH get_JasperHash(String cID_ADRESSE)	throws myException {
			RECORD_ADRESSE recAdresse = new RECORD_ADRESSE(cID_ADRESSE);
			
			return new ownJasperHash(recAdresse);
		}

		@Override
		public Vector<String> get_vMailTypenVorgabe() throws myException {
			return bibVECTOR.get_Vector(myCONST.EMAIL_TYPE_VALUE_LAGER);
		}

		@Override
		public Vector<String> get_vMailTypenNachBenutzerEingabe() throws myException {
			return  this.get_oQualifierMatrix().get_vAllSelectedMailTags();  
		}

		@Override
		public String get_TEXT_KLEMMBRETT_KENNER_FUER_BETREFF() {
			return INF_ANFORDERUNG_INVENTUR_BasicModuleContainer.KLEMMBRETTKEY_INVENTURMAIL_BETREFF;
		}

		@Override
		public String get_TEXT_KLEMMBRETT_KENNER_FUER_MAILTEXT() {
			return INF_ANFORDERUNG_INVENTUR_BasicModuleContainer.KLEMMBRETTKEY_INVENTURMAIL_TEXT;
		}

		@Override
		public String get_TEXT_NAMENSANTEIL_FUER_ARCHIV() {
			return INF_ANFORDERUNG_INVENTUR_BasicModuleContainer.INVENTURMAIL_NAME_ARCHIV;
		}

		@Override
		public void STARTE_MASSENMAILER(MMA_BasicContainer oMMA_Container) throws myException {
			
			MMA_MassMailerStandard oMailer = new MMA_MassMailerStandard(oMMA_Container, new MailSecurityPolicyAllowNothing());

			E2_Button downloadAll = new E2_Button().__setImages(E2_ResourceIcon.get_RI("download_selektierte.png"), E2_ResourceIcon.get_RI("download_selektierte.png"))._ttt(S.ms("Alle selektierten PDFs zusammenfügen und herunterladen"));
			downloadAll._aaa(new OwnActionDownAll())._setShapeStyleStandard();
			
			oMailer.baue_MailMaske(
					oMMA_Container.get_vMailBlocks(), 
					bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""), false, false, false, 0, new VEK<MyE2IF__Component>()._a(downloadAll));
			oMailer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(800), new MyE2_String("Versand von Inventuranforderungen..."));
		}


		
		private class OwnActionDownAll extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {	
				MailBlock_Vector mailBlocks = get_vMailBlocks(); 
				VEK<String> pdfs = new VEK<>();
				for (MailBlock mb: mailBlocks) {
					if (mb.get_b_EVEN_ONE_TargetAdress_isSelected()) {
						for (FileWithSendName fws: mb.get_vOwnFiles()) {
							pdfs._a(fws.get_cNameWithPath());
						}
					}
				}
				if (pdfs.size()==0) {
					bibMSG.MV()._addAlarm(S.ms("Sie haben nichts zum Download selektiert !"));
				} else {
					new PdServiceConcatenatePdfs().startDownload(pdfs, "sammelformular-lagerinventur");
				}
				
			}
		}
		
	}
	
	
	
	private class ownJasperHash extends MMA_JasperHASH {

		public ownJasperHash(RECORD_ADRESSE recAdresse) throws myException {
			super(		"INVENTUR_FORMULAR.jasper",
						new JasperFileDef_PDF(),
						"Serienmail",
						"Inventur",
						new MailSecurityPolicyAllowAll(),
						new MyE2_String(		"Lieferadresse:",true,
												recAdresse.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1,_DB.ADRESSE$NAME2)),false,
												"  ("+(recAdresse.is_AKTIV_YES()?"AKTIV":"INAKTIV")+")",false),
						true);
			this.put("id_adresse", recAdresse.get_ID_ADRESSE_cUF());
			this.put("INFOFELD_Y_N",INF_ANFORDERUNG_INVENTUR_BasicModuleContainer.this.oCB_BlendeInfozeileEin.isSelected()?"Y":"N");
			this.put("ARTIKEL_AUSSCHLUSS_BLOCK",INF_ANFORDERUNG_INVENTUR_BasicModuleContainer.this.oSelektierAusblendSorten.get_SQL_WHERE_BLOCK_SORTENAUSSCHLUSS());
			
			this.set_bEliminateEmptyJasperTempFiles(true);
			this.set_bShowErrorMessageWhenResultIsEmpty(false);
		}
		
	}


	public MyE2_CheckBox get_oCB_Aktiv() {
		return oCB_Aktiv;
	}



	public MyE2_CheckBox get_oCB_InAktiv() {
		return oCB_InAktiv;
	}



	public MyE2_CheckBox get_oCB_EigenWare() {
		return oCB_EigenWare;
	}



	public MyE2_CheckBox get_oCB_Fremdware() {
		return oCB_Fremdware;
	}



	public MyE2_CheckBox get_oCB_BlendeInfozeileEin() {
		return oCB_BlendeInfozeileEin;
	}



	public MyE2_CheckBox get_oCB_HauptlagerAuchDrucken() {
		return oCB_HauptlagerAuchDrucken;
	}



	public Component_USER_DROPDOWN_NEW get_oSelectUser() {
		return oSelectUser;
	}



	public INF_Selektor_LeftRight_Sorten get_oSelektierAusblendSorten() {
		return oSelektierAusblendSorten;
	}

	public String get_SaveStringAusgeblendete_Sorten() {
		return oSelektierAusblendSorten.get_SAVE_STRING_BLOCK_SORTENAUSSCHLUSS();
	}

	public void restore_SavedStringAusgeblendete_Sorten(String cID_Sorten_AusschlussListe) {
		oSelektierAusblendSorten.restore_SAVED_SORTENAUSSCHLUSS(cID_Sorten_AusschlussListe);
	}
	
	
	
	
	public String get_SaveStringMailTypenMatrix() {
		return this.oMassMailer.get_oQualifierMatrix().get_hmQ_DEF_CheckBoxen().get_StatusAnAus_4_ActualMatrix();
	}
	
	/**
	 * 
	 * @param cTypenArray = "YNYYYNY ..." als repraesentant der Y-N-zusammenstellung aller schalter
	 * @throws myException 
	 */
	public void restore_MailTypenMatrix(String cTypenArray) throws myException {
		this.oMassMailer.get_oQualifierMatrix().get_hmQ_DEF_CheckBoxen().set_StatusAnAus_4_ActualMatrix(cTypenArray);
	}
	
}
