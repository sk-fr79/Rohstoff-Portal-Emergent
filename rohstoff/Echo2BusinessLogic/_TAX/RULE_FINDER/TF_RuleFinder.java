package rohstoff.Echo2BusinessLogic._TAX.RULE_FINDER;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Vector;

import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_ErmittlePassendeHandelsDef;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_RECORD_VPOS_TPA_FUHRE;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_RECORD_VPOS_TPA_FUHRE_ORT;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_Stationen;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD__FinderProformaSteuersatzFuerMandantenorte;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_Station;

public class TF_RuleFinder {

	private long lAnzahlRelationen_geprueft = 0;
	private long lAnzahlRelationen_erstellt = 0;
	private long lAnzahlRelationen_uebersprungen_weil_vorhanden = 0;
	private long lAnzahlRelationen_uebersprungen_weil_fehlerhaft = 0;
	private long lAnzahlAdresseWederPrivatNochFirma = 0;
	private long lAnzahlFuhreOhneFuhrenverantwortung = 0;
	private long lAnzahlFehlerBeimSchreibenNeueHandelsdef = 0;
	private long lAnzahlFehlerUNDEF = 0;
	
	private MyE2_MessageVector  oMV_SammlerUndefinedErrors = new MyE2_MessageVector();  
	
//	public MyE2_MessageVector get_oMV_SammlerUndefinedErrors() {
//		return oMV_SammlerUndefinedErrors;
//	}


	//2014-06-10: weitere umdefinition der funktionalitaet: die tatsache, dass ein ort  der mandant ist, muss wieder benutzt werden
	private boolean  b_IgnoriereMandantenstatusDerStationen = bibALL.get_RECORD_MANDANT().is_STEUERFINDUNG_OHNE_EIGENORTE_YES();
	private Long     l_ID_STEUERSATZ_PROFORMA = null;

	public TF_RuleFinder(MyE2_Grid  oGrid4LoopMessage, boolean bErsetzeLeerVerantwortungDurchMandant, E2_ServerPushMessageContainer oServerMSG_Container) throws myException {
		super();
		
		if (oGrid4LoopMessage != null) {
			oGrid4LoopMessage.setSize(8);
			oGrid4LoopMessage.setStyle(MyE2_Grid.STYLE_GRID_BLACK_BORDER_NO_INSETS_W100());
		}

		
		//zuerst die fuhren besorgen
		String cID_SQL_Query = "SELECT "+_DB.Z_VPOS_TPA_FUHRE$ID_VPOS_TPA_FUHRE +" FROM "+bibE2.cTO()+"."+_DB.VPOS_TPA_FUHRE+
								" WHERE NVL("+_DB.Z_VPOS_TPA_FUHRE$IST_STORNIERT+",'N')='N'" +
								" AND  "+_DB.Z_VPOS_TPA_FUHRE$ID_ARTIKEL+" IS NOT NULL " +
								" AND  "+_DB.Z_VPOS_TPA_FUHRE$ID_ARTIKEL_BEZ_EK+" IS NOT NULL " +
								" AND  "+_DB.Z_VPOS_TPA_FUHRE$ID_ARTIKEL_BEZ_VK+" IS NOT NULL " +
								" AND  "+_DB.Z_VPOS_TPA_FUHRE$ID_ADRESSE_START+" IS NOT NULL " +
								" AND  "+_DB.Z_VPOS_TPA_FUHRE$ID_ADRESSE_ZIEL+" IS NOT NULL " +
								" AND  "+_DB.Z_VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_START+" IS NOT NULL " +
								" AND  "+_DB.Z_VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_ZIEL+" IS NOT NULL " +
								" AND  NVL("+_DB.Z_VPOS_TPA_FUHRE$DELETED+",'N')='N'" +
								" AND  NVL("+_DB.Z_VPOS_TPA_FUHRE$STATUS_BUCHUNG+",-1)>0 ORDER BY "+_DB.Z_VPOS_TPA_FUHRE$ID_VPOS_TPA_FUHRE+" DESC";
		
		String[][] arrID = bibDB.EinzelAbfrageInArray(cID_SQL_Query);
		
		if (!b_IgnoriereMandantenstatusDerStationen) {
			HD__FinderProformaSteuersatzFuerMandantenorte  oFinder = new HD__FinderProformaSteuersatzFuerMandantenorte();
			if (oFinder.get_RECORD_TAX_proforma()==null) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(	new MyE2_String("Achtung! Im Mandanten ",true,
															bibALL.get_RECORD_MANDANT().get_NAME1_cUF_NN("<name1>"),false,
															" existiert noch keine Proforma-Steuersatz für die eigenen Lagerorte !!!",true)));
				return;
			}
			this.l_ID_STEUERSATZ_PROFORMA = oFinder.get_RECORD_TAX_proforma().get_ID_TAX_lValue(-1l); 
		}

		
		long lStartZeit = System.currentTimeMillis();
		
		for (int i=0;i<arrID.length;i++) {
		
			if (oGrid4LoopMessage != null) {
				if (i>0 && i%20==0) {
					oGrid4LoopMessage.removeAll();
					
					long jetzzeit = System.currentTimeMillis();
					long lZeitDiffinMinutes = (jetzzeit-lStartZeit)/60000;
					long lZeitDiffinSecs = (jetzzeit-lStartZeit)/1000;
					
					if (lZeitDiffinSecs==0l) { lZeitDiffinSecs=1;}
					
					BigDecimal  bdAnzahl_pro_sec = new BigDecimal(new Double(new Double(i).doubleValue()/new Double(lZeitDiffinSecs).doubleValue()));
					BigDecimal  bdRestZeitInMinuten = new BigDecimal(arrID.length).divide(bdAnzahl_pro_sec,RoundingMode.HALF_UP).divide(new BigDecimal(60),RoundingMode.HALF_UP);
					
					bdRestZeitInMinuten=bdRestZeitInMinuten.round(new MathContext(0, RoundingMode.HALF_UP));
					
					oGrid4LoopMessage.add(new MyE2_Label(new MyE2_String("Statistik der geprüften Relationen (Gesamt: "+arrID.length+"), bisherige Laufzeit (min): "+lZeitDiffinMinutes)+" / Gesamtzeit-Prognose (min): "+bdRestZeitInMinuten.longValue()),MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I(2,2,2,2),new E2_ColorDDDark(), 8));
					
					GridLayoutData oLayoutSubTitle = MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorDark(), 1);
					GridLayoutData oLayoutContent = MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorBase(), 1);
					
					oGrid4LoopMessage.add(new MyE2_Label(new MyE2_String("Anzahl gesamt"),new E2_FontItalic(-2),true),oLayoutSubTitle);
					oGrid4LoopMessage.add(new MyE2_Label(new MyE2_String("Relationen erstellt"),new E2_FontItalic(-2),true),oLayoutSubTitle);
					oGrid4LoopMessage.add(new MyE2_Label(new MyE2_String("Relationen vorhanden"),new E2_FontItalic(-2),true),oLayoutSubTitle);
					oGrid4LoopMessage.add(new MyE2_Label(new MyE2_String("Fehlerhaft (privat/Firma)"),new E2_FontItalic(-2),true),oLayoutSubTitle);
					oGrid4LoopMessage.add(new MyE2_Label(new MyE2_String("Fehlerhaft (TP-Verantwortung)"),new E2_FontItalic(-2),true),oLayoutSubTitle);
					oGrid4LoopMessage.add(new MyE2_Label(new MyE2_String("Fehlerhaft (allgemein)"),new E2_FontItalic(-2),true),oLayoutSubTitle);
					oGrid4LoopMessage.add(new MyE2_Label(new MyE2_String("Fehlerhaft (Speicherfehler)"),new E2_FontItalic(-2),true),oLayoutSubTitle);
					oGrid4LoopMessage.add(new MyE2_Label(new MyE2_String("Systemfehler"),new E2_FontItalic(-2),true),oLayoutSubTitle);

					oGrid4LoopMessage.add(new MyE2_Label(""+this.lAnzahlRelationen_geprueft),oLayoutContent);
					oGrid4LoopMessage.add(new MyE2_Label(""+this.lAnzahlRelationen_erstellt),oLayoutContent);
					oGrid4LoopMessage.add(new MyE2_Label(""+this.lAnzahlRelationen_uebersprungen_weil_vorhanden),oLayoutContent);
					oGrid4LoopMessage.add(new MyE2_Label(""+this.lAnzahlAdresseWederPrivatNochFirma),oLayoutContent);
					oGrid4LoopMessage.add(new MyE2_Label(""+this.lAnzahlFuhreOhneFuhrenverantwortung),oLayoutContent);
					oGrid4LoopMessage.add(new ownButton4UndefinedErrors(),oLayoutContent);
					oGrid4LoopMessage.add(new MyE2_Label(""+this.lAnzahlFehlerBeimSchreibenNeueHandelsdef),oLayoutContent);
					oGrid4LoopMessage.add(new MyE2_Label(""+this.lAnzahlFehlerUNDEF),oLayoutContent);
				}
			}

			
			HD_RECORD_VPOS_TPA_FUHRE  oRecFuhre =  new HD_RECORD_VPOS_TPA_FUHRE(new RECORD_VPOS_TPA_FUHRE(arrID[i][0]),false );
			
			HD_Stationen  oStationVector = new HD_Stationen(true);
			oStationVector.add(oRecFuhre.get_HD_StationEK(bErsetzeLeerVerantwortungDurchMandant));
			oStationVector.add(oRecFuhre.get_HD_StationVK(bErsetzeLeerVerantwortungDurchMandant));
			
			this.verarbeiteStationenPaar(oStationVector);
			
			RECLIST_VPOS_TPA_FUHRE_ORT  rlOrte = oRecFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre(
					 "NVL("+_DB.Z_VPOS_TPA_FUHRE_ORT$DELETED+",'N')='N'", null, true);

			for (RECORD_VPOS_TPA_FUHRE_ORT recORT: rlOrte.values()) {
				HD_RECORD_VPOS_TPA_FUHRE_ORT  rec_ORT = new HD_RECORD_VPOS_TPA_FUHRE_ORT(recORT, false);
				
				if (recORT.get_DEF_QUELLE_ZIEL_cUF().equals("EK")) {
					HD_Stationen  oStationVectorOrt = new HD_Stationen(true);
					oStationVectorOrt.add(rec_ORT.get_HD_Station(bErsetzeLeerVerantwortungDurchMandant));
					oStationVectorOrt.add(oRecFuhre.get_HD_StationVK(bErsetzeLeerVerantwortungDurchMandant));
					this.verarbeiteStationenPaar(oStationVectorOrt);
				} else {
					HD_Stationen  oStationVectorOrt = new HD_Stationen(true);
					oStationVectorOrt.add(oRecFuhre.get_HD_StationEK(bErsetzeLeerVerantwortungDurchMandant));
					oStationVectorOrt.add(rec_ORT.get_HD_Station(bErsetzeLeerVerantwortungDurchMandant));
					this.verarbeiteStationenPaar(oStationVectorOrt);
				}
			}
			
			
			if (oServerMSG_Container.get_bIsInterupted()) {
				break;
			}
		}
	}

	
	
	
	private void verarbeiteStationenPaar(HD_Stationen  oStationVector) throws myException {
		
		this.lAnzahlRelationen_geprueft ++;
		
		
		try {
			if (oStationVector.get_vFehlerVect().size()>0 || oStationVector.get_oMV_SammlerVonFehlern().get_bHasAlarms()) {
				
				if ( oStationVector.get(0).get_recAdresse_JUR() == null || oStationVector.get(1).get_recAdresse_JUR()==null) {
					this.lAnzahlRelationen_uebersprungen_weil_fehlerhaft ++;
					//hier werden die fehlercodes gesammelt
					this.oMV_SammlerUndefinedErrors.add_MESSAGE(oStationVector.get_vFehlerVect().get_FehlerAsMessageVector());
					this.oMV_SammlerUndefinedErrors.add_MESSAGE(oStationVector.get_oMV_SammlerVonFehlern().get_AlarmMessages());

				} else if (	(oStationVector.get(0).get_recAdresse_JUR().get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_FIRMA_NO() && 
					oStationVector.get(0).get_recAdresse_JUR().get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_PRIVAT_NO()) 
					||
					(oStationVector.get(1).get_recAdresse_JUR().get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_FIRMA_NO() && 
					oStationVector.get(1).get_recAdresse_JUR().get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_PRIVAT_NO())
					) {
					this.lAnzahlAdresseWederPrivatNochFirma++;
				} else if  (S.isEmpty(oStationVector.get(0).get_cTP_Verantwortung())) {	
					this.lAnzahlFuhreOhneFuhrenverantwortung ++;
				} else {
					this.lAnzahlRelationen_uebersprungen_weil_fehlerhaft ++;
					
					//hier werden die fehlercodes gesammelt
					this.oMV_SammlerUndefinedErrors.add_MESSAGE(oStationVector.get_vFehlerVect().get_FehlerAsMessageVector());
					this.oMV_SammlerUndefinedErrors.add_MESSAGE(oStationVector.get_oMV_SammlerVonFehlern().get_AlarmMessages());
				} 
			} else {
				
				HD_ErmittlePassendeHandelsDef  	oHD_Ermittler = new HD_ErmittlePassendeHandelsDef(
																				oStationVector.getHD_StationenEK(true).get(0),
																				oStationVector.getHD_StationenVK(true).get(0),
																				true);
				
				//dann wurde nix gefunden
				if (oHD_Ermittler.getWarenBewegungEinstufungen().size()>0) {
					this.lAnzahlRelationen_uebersprungen_weil_vorhanden ++;
				} else {
					HD_Station oStationEK = oStationVector.getHD_StationenEK(true).get(0);
					HD_Station oStationVK = oStationVector.getHD_StationenVK(true).get(0);
					
					
					RECORD_HANDELSDEF recHD = new RECORD_HANDELSDEF();
					//allgemein
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_AKTIV("N");
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_TP_VERANTWORTUNG(oStationEK.get_HDMASK_TP_VERANTWORTUNG());
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_AKTIV("N");
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_VERSIONSZAEHLER("0");
					//quelle
					if (this.b_IgnoriereMandantenstatusDerStationen) {
						recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_QUELLE_IST_MANDANT("N");
					} else {
						recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_QUELLE_IST_MANDANT(oStationEK.get_bStationIstMandant()?"Y":"N");
						if (oStationEK.get_bStationIstMandant()) {
							//ist die station eine mandanten-station, dann wird der proforma-tax eingesetzt
							recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_ID_TAX_QUELLE(this.l_ID_STEUERSATZ_PROFORMA.longValue());
						}
					}
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_TRANSIT_EK("N");
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_INTRASTAT_MELD_IN("N");
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_ID_LAND_QUELLE_JUR(oStationEK.get_HDMASK_ID_LAND_JUR());
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_ID_LAND_QUELLE_GEO(oStationEK.get_HDMASK_ID_LAND_GEO());
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_SORTE_PRODUKT_QUELLE(oStationEK.get_HDMASK_IST_PRODUKT());
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_SORTE_DIENSTLEIST_QUELLE(oStationEK.get_HDMASK_IST_DIENSTLEISTUNG());
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_SORTE_EOW_QUELLE(oStationEK.get_HDMASK_IST_EOW());
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_SORTE_RC_QUELLE(oStationEK.get_HDMASK_IST_SORTE_RC());
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_UST_TEILNEHMER_QUELLE(oStationEK.get_HDMASK_IST_UST_TEILNEHMER());
					//ziel
					if (this.b_IgnoriereMandantenstatusDerStationen) {
						recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_ZIEL_IST_MANDANT("N");
					} else {
						recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_ZIEL_IST_MANDANT(oStationVK.get_bStationIstMandant()?"Y":"N");
						if (oStationVK.get_bStationIstMandant()) {
							//ist die station eine mandanten-station, dann wird der proforma-tax eingesetzt
							recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_ID_TAX_ZIEL(this.l_ID_STEUERSATZ_PROFORMA.longValue());
						}
					}
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_TRANSIT_VK("N");
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_INTRASTAT_MELD_OUT("N");
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_ID_LAND_ZIEL_JUR(oStationVK.get_HDMASK_ID_LAND_JUR());
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_ID_LAND_ZIEL_GEO(oStationVK.get_HDMASK_ID_LAND_GEO());
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_SORTE_PRODUKT_ZIEL(oStationVK.get_HDMASK_IST_PRODUKT());
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_SORTE_DIENSTLEIST_ZIEL(oStationVK.get_HDMASK_IST_DIENSTLEISTUNG());
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_SORTE_EOW_ZIEL(oStationVK.get_HDMASK_IST_EOW());
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_SORTE_RC_ZIEL(oStationVK.get_HDMASK_IST_SORTE_RC());
					recHD.get_RECORDNEW_HANDELSDEF().set_NEW_VALUE_UST_TEILNEHMER_ZIEL(oStationVK.get_HDMASK_IST_UST_TEILNEHMER());
					
					
					MyE2_MessageVector oMV_TEST = new MyE2_MessageVector();
					String cSQL = recHD.get_RECORDNEW_HANDELSDEF().get_InsertSQLStatementWith_Id_Field(false, true);
					//System.out.println(cSQL);
					recHD.get_RECORDNEW_HANDELSDEF().do_WRITE_NEW_HANDELSDEF(oMV_TEST);
					if (oMV_TEST.get_bHasAlarms()) {
						this.lAnzahlFehlerBeimSchreibenNeueHandelsdef++;
						//DEBUG.System_println(oMV_TEST.get_MessagesAsText(),null);
					} else {
						DEBUG.System_println(cSQL,null);
						this.lAnzahlRelationen_erstellt++;
					}
				}
			}
		} catch (Exception e) {
			this.lAnzahlFehlerUNDEF++;
			e.printStackTrace();
		}
	}
	
	

	private class ownButton4UndefinedErrors extends MyE2_Button {

		public ownButton4UndefinedErrors() {
			super(""+TF_RuleFinder.this.lAnzahlRelationen_uebersprungen_weil_fehlerhaft, MyE2_Button.StyleTextButton());
			this.add_oActionAgent(new ownActionAgent());
		}
		
	}


	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector  oMV = TF_RuleFinder.this.oMV_SammlerUndefinedErrors;
			Vector<MyString> vMSG = oMV.get_MessagesAsMyStringVector();
			new E2_MessageBoxYesNo(new MyE2_String("Fehlerliste:"), new MyE2_String("OK"), new MyE2_String(""), true, false, vMSG, null);
		}
		
	}
	
	
	
	
}
