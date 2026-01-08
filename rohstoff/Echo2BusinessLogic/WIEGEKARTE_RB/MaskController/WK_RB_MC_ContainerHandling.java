package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector.MessageException;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_TYPE;
import panter.gmbh.basics4project.ENUM_EmailType;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER_STATION;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER_ZYKLUS;
import panter.gmbh.basics4project.DB_ENUMS.LAGERPLATZ;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.WAEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_CONTAINER_ZYKLUS;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.Rec22.Rec22FieldNotInTableException;
import panter.gmbh.indep.dataTools.RECORD2.Rec22.Rec22FieldNotNullableException;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_Gueterkategorie;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.EnumTaetigkeit;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_ENUM_WKTYP;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMap_Wiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung1;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung2;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_ContainerZyklus;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_Lagerplatz;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.Rec_ContainerZyklus;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.Rec_WaageUser;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.Rec_container;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22EmailSend;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22LandRcSorte;

public class WK_RB_MC_ContainerHandling extends RB_MaskController {

	public WK_RB_MC_ContainerHandling(IF_RB_Component p_component) throws myException {
		super(p_component);
	}

	public WK_RB_MC_ContainerHandling(RB_ComponentMap p_componentMap) throws myException {
		super(p_componentMap);
	}

	public WK_RB_MC_ContainerHandling(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);
	}

	public WK_RB_MC_ContainerHandling(RB_DataobjectsCollector p_dataObjectsCollector) throws myException {
		super(p_dataObjectsCollector);
	}

	public WK_RB_MC_ContainerHandling(IF_RB_Component p_component, MyE2_MessageVector mvForErrors) {
		super(p_component, mvForErrors);
	}

	public WK_RB_MC_ContainerHandling(RB_ComponentMap p_componentMap, MyE2_MessageVector mvForErrors) {
		super(p_componentMap, mvForErrors);
	}

	public WK_RB_MC_ContainerHandling(RB_ComponentMapCollector p_componentMapCollector,
			MyE2_MessageVector mvForErrors) {
		super(p_componentMapCollector, mvForErrors);
	}

	public WK_RB_MC_ContainerHandling(RB_DataobjectsCollector p_dataObjectsCollector, MyE2_MessageVector mvForErrors) {
		super(p_dataObjectsCollector, mvForErrors);
	}

	public WK_RB_MC_ContainerHandling(IF_RB_Component p_component, boolean immediateBuild) throws myException {
		super(p_component, immediateBuild);
	}

	public WK_RB_MC_ContainerHandling(RB_ComponentMap p_componentMap, boolean immediateBuild) throws myException {
		super(p_componentMap, immediateBuild);
	}

	public WK_RB_MC_ContainerHandling(RB_ComponentMapCollector p_componentMapCollector, boolean immediateBuild)
			throws myException {
		super(p_componentMapCollector, immediateBuild);
	}

	public WK_RB_MC_ContainerHandling(RB_DataobjectsCollector p_dataObjectsCollector, boolean immediateBuild)
			throws myException {
		super(p_dataObjectsCollector, immediateBuild);
	}

	/**
	 * Schreibt ein neues Gewicht für einen Container in die Container-Tabelle
	 * 
	 * @author manfred
	 * @date 21.01.2021
	 *
	 * @return
	 * @throws myException
	 */
	public WK_RB_MC_ContainerHandling setNewTaraValue(String idContainer, BigDecimal bdNewValue, MyE2_MessageVector mv)
			throws myException {

		Rec_container recContainer = new Rec_container()._fill_id(idContainer);
		recContainer._setNewValue(CONTAINER.tara_korrektur, bdNewValue, mv);
		recContainer._setNewValueInDatabaseTerminus(CONTAINER.datum_korrektur, "SYSDATE ");
		recContainer._SAVE(true, mv);

		return this;
	}

	/**
	 * schickt eine Message an den/die ausgewählten Benutzer wenn bei der Verwiegung
	 * die UVV abgelaufen, oder knapp davor ist
	 * 
	 * @throws myException
	 */
	public WK_RB_MC_ContainerHandling sendMessage_IfUVVInvalid(MyE2_MessageVector mv) throws myException {
		HashSet<String> hsKeys = new HashSet<String>();

		String sMessage = null;
		String sTitle = null;

		String sMailKenner = "";
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String sDate = df.format(new Date());
		

		// prüfen, ob die UVV invalid ist
		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);

		RecDOWiegekarte _recWK = (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		Long idCont = _recWK.get_raw_resultValue_Long(WIEGEKARTE.id_container_eigen);
		boolean bSend = false;
		
		if (idCont != null) {
			
			// VErwieger-Daten
			String sVerwieger = "-";
			String idVerwieger = null;
			RecDOWaegung1 waeg1 = _recWK._get_Waegung1();
			RecDOWaegung2 waeg2 = _recWK._get_Waegung2();
			if (waeg2 != null) {
				idVerwieger = waeg2.getUfs(WAEGUNG.id_user_waegung);
			} else if (waeg1 != null) {
				idVerwieger = waeg1.getUfs(WAEGUNG.id_user_waegung);
			}
			if (idVerwieger != null) {
				Rec22 recUser = new Rec22(_TAB.user)._fill_id(idVerwieger);
				if (recUser != null) {
					sVerwieger = recUser.getUfs(USER.kuerzel, "-");
				}
			}
			
			
			Rec_container recCont = new Rec_container()._fill_id(idCont);

			MyDate dtUVV = recCont.get_myDate_dbVal(CONTAINER.uvv_datum);
			String sUVVDatum = "-";
			
			if (dtUVV != null && dtUVV.get_bOK()) {
				sUVVDatum = dtUVV.get_cDateStandardFormat();
				Long diff = MyDate.get_DayDifferenceDate2MINUSDate1(new MyDate(new Date()),dtUVV);
				
				if (diff <= 30 && diff > 0) {
					sTitle = String.format(new MyString("Prüfung steht an: Container %s: UVV: %s ").CTrans(),
							recCont.getUfs(CONTAINER.container_nr), recCont.get_fs_dbVal(CONTAINER.uvv_datum));
					sMessage = String.format(
							new MyString("Der Container %s muss geprüft werden.%n" 
									+ "Wiegekarte: %s%n"
									+ "Verwieger: %s%n"
									+ "UVV: %s %n"
									+ "Grund: die UVV des Containers läuft in %d Tagen ab.").CTrans(),
							recCont.getUfs(CONTAINER.container_nr), _recWK.getUfs(WIEGEKARTE.lfd_nr,"-"),sVerwieger, recCont.get_fs_dbVal(CONTAINER.uvv_datum), diff);
					bSend = true;
				} else if (diff <= 0) {
					sTitle = String.format(
							new MyString("Prüfung dringend erforderlich: Container %s: UVV: %s ").CTrans(),
							recCont.getUfs(CONTAINER.container_nr), recCont.get_fs_dbVal(CONTAINER.uvv_datum));
					sMessage = String.format(
							new MyString("Der Container %s muss geprüft werden.%n" 
									+ "Wiegekarte: %s%n"
									+ "Verwieger: %s%n"
										+ "UVV: %s %n"
										+ "Grund: die UVV des Containers ist seit %d Tagen abgelaufen.").CTrans(),
							recCont.getUfs(CONTAINER.container_nr), _recWK.getUfs(WIEGEKARTE.lfd_nr,"-"),sVerwieger,recCont.get_fs_dbVal(CONTAINER.uvv_datum), diff);
					bSend = true;
				}
			} else {
				// keine UVV vorhanden -> auch melden...
				sTitle = String.format(new MyString("Prüfung dringend erforderlich: Container %s: UVV: ****** NICHT VORHANDEN ****** ").CTrans(),
						recCont.getUfs(CONTAINER.container_nr));
				sMessage = String.format(
						new MyString("Der Container %s muss geprüft werden.%n"
								+ "Wiegekarte: %s%n"
								+ "Verwieger: %s%n"
								+ "UVV: ****** NICHT VORHANDEN ****** %n"
								+ "Grund: die UVV des Containers ist nicht erfasst.").CTrans(),
						recCont.getUfs(CONTAINER.container_nr),_recWK.getUfs(WIEGEKARTE.lfd_nr,"-"),sVerwieger);
				bSend = true;
			}
			

			// Mail veschicken
			if (bSend ) {
				sMailKenner = String.format("WK_CONTAINER_UVV_ABLAUF#%s#%s#%s", _recWK.getIdLong(),idCont,sUVVDatum);
				
				WK_BasicHandling basicHandling = new WK_BasicHandling(_recWK.getTransportHashMap());
				Vector<String> vecReceiver = basicHandling.getReceiverlist(ENUM_MANDANT_CONFIG.EMAIL_WIEGEKARTE_UVV_FEHLER.getValue(bibALL.get_ID_MANDANT()));

				try {
					_sendMail(_recWK,ENUM_EmailType.MAILVERSAND_WAAGE_UVV_INVALID,sTitle,sMessage,sMailKenner,vecReceiver);
					bibMSG.add_MESSAGE(new MyE2_Info_Message("UVV des Containers abgelaufen! Mail wurde verschickt!!"));
				} catch (Exception e) {
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Fehler beim versenden der Mail wegen UVV-Datum!" + e.getMessage()));
				}
			}
		}
		
		return this;
	}
	
	

	
	
	/**
	 * schickt eine Message an den/die ausgewählten Benutzer wenn bei der Verwiegung
	 * die UVV abgelaufen, oder knapp davor ist
	 * 
	 * @throws myException
	 */
	public WK_RB_MC_ContainerHandling sendMessage_IfContainerTaraChanged(MyE2_MessageVector mv) throws myException {
		HashSet<String> hsKeys = new HashSet<String>();

		String sMessage = null;
		String sTitle = null;

		// Emailverteiler lesen...
		String sVerteiler = ENUM_MANDANT_CONFIG.EMAIL_WIEGEKARTE_TARA_AENDERUNG.getValue(bibALL.get_ID_MANDANT());

		// prüfen, ob die UVV invalid ist
		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this
				.get_ComponentMapCollector().get(RecDOWiegekarte.key);

		RecDOWiegekarte _recWK = (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		Long idCont = _recWK.get_raw_resultValue_Long(WIEGEKARTE.id_container_eigen);

		BigDecimal bdTara = null;
		BigDecimal bdTaraKorr = null;
		String sMailKenner = "";
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String sDate = df.format(new Date());
	
		if (idCont != null) {
			Rec_container recCont = new Rec_container()._fill_id(idCont);

			String sVerwieger = "-";
			String idVerwieger = null;
			RecDOWaegung1 waeg1 = _recWK._get_Waegung1();
			RecDOWaegung2 waeg2 = _recWK._get_Waegung2();
			if (waeg2 != null) {
				idVerwieger = waeg2.getUfs(WAEGUNG.id_user_waegung);
			} else if (waeg1 != null) {
				idVerwieger = waeg1.getUfs(WAEGUNG.id_user_waegung);
			}
			if (idVerwieger != null) {
				Rec22 recUser = new Rec22(_TAB.user)._fill_id(idVerwieger);
				if (recUser != null) {
					sVerwieger = recUser.getUfs(USER.kuerzel, "-");
				}
			}
			
			
			bdTara = recCont.get_raw_resultValue_bigDecimal(CONTAINER.tara);
			bdTaraKorr = recCont.get_raw_resultValue_bigDecimal(CONTAINER.tara_korrektur);
			
			if(bdTaraKorr != null && !bdTara.equals(bdTaraKorr)) {
				sTitle = String.format(new MyString("Container Tara wurde geändert! Container-Nr: %s").CTrans(),
						recCont.getUfs(CONTAINER.container_nr));
				sMessage = String.format(
						new MyString("Das Tara des Containers %s wurde während der Verwiegung der Wiegekarte %s geändert.%n"
								    + "Verwieger: %s %n"
									+ "Datum: %s %n" 
									+ "Altes Tara: %s Kg%n"
									+ "Neues Tara: %s Kg%n"
									).CTrans(),
						recCont.getUfs(CONTAINER.container_nr),_recWK.getUfs(WIEGEKARTE.lfd_nr),sVerwieger, sDate, bdTara, bdTaraKorr);

				
				
				sMailKenner = String.format("WK_CONTAINER_TARA_AENDRUNG#%s#%s#%s#%s", _recWK.getIdLong(),bdTara,bdTaraKorr,sDate);

				WK_BasicHandling basicHandling = new WK_BasicHandling(_recWK.getTransportHashMap());
				Vector<String> vecReceiver = basicHandling.getReceiverlist(ENUM_MANDANT_CONFIG.EMAIL_WIEGEKARTE_TARA_AENDERUNG.getValue(bibALL.get_ID_MANDANT()));

				// Mail veschicken		
				try {
					_sendMail(_recWK,ENUM_EmailType.MAILVERSAND_WAAGE_TARA_KORR,sTitle,sMessage,sMailKenner,vecReceiver);
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Tara-Änderung! Mail wurde verschickt!!"));
				} catch (Exception e) {
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Fehler beim versenden der Mail wegen der Tara-Änderung!"));
				}
								
			}

		}
		
		return this;
	}
	
	
	/**
	 * schickt eine Message an den/die ausgewählten Benutzer wenn bei der Container-Einbuchung
	 * der Container schon einmal auf dem Lager steht
	 * 
	 * @throws myException
	 */
	public WK_RB_MC_ContainerHandling sendMessage_IfContainerBookedMultiple(MyE2_MessageVector mv) throws myException {
		HashSet<String> hsKeys = new HashSet<String>();

		String sMessage = null;
		String sTitle = null;

		// Emailverteiler lesen...
		String sVerteiler = ENUM_MANDANT_CONFIG.EMAIL_ZYKLUS_WK_DOPPELTE_EINBUCHUNG.getValue(bibALL.get_ID_MANDANT());

		// prüfen, ob die UVV invalid ist
		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this
				.get_ComponentMapCollector().get(RecDOWiegekarte.key);

		RecDOWiegekarte _recWK = (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		Long idCont = _recWK.get_raw_resultValue_Long(WIEGEKARTE.id_container_eigen);

		
		
		String sMailKenner = "";
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String sDate = df.format(new Date());
	
		if (idCont != null && S.isFull(sVerteiler)) {
			
			//  
			String sql = "SELECT count(*) as COUNT , " + CONTAINER.container_nr.tnfn()  
					+ " FROM " + CONTAINER_ZYKLUS.fullTabName() 
					+ " INNER JOIN  " +CONTAINER.fullTabName()	+ " ON " + CONTAINER_ZYKLUS.id_container.tnfn() + " = " + CONTAINER.id_container.tnfn()
					+ " WHERE NVL(" + CONTAINER_ZYKLUS.abgeschlossen.tnfn() + ",'N') = 'N' AND "
					+ " " + CONTAINER_ZYKLUS.id_container.tnfn()  + " = ? "
					+ " GROUP BY " + CONTAINER_ZYKLUS.id_container.tnfn() + "," + CONTAINER.container_nr.tnfn() ;
			
			VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
			vecParam._a(new Param_Long(idCont));
			
			SqlStringExtended  sqlExt = new SqlStringExtended(sql)._addParameters(vecParam);
			
			String[][] result = bibDB.EinzelAbfrageInArray(sqlExt, "0");
			
			int nCount = 0;
			String sCount  = "";
			String sContainerNr = "";
			
			if (result.length >= 1) {
				sCount = result[0][0];
				sContainerNr = result[0][1];
				
				nCount = Integer.parseInt(sCount);
			}
			
			String sWiegekarteNr =_recWK.get_ufs_dbVal(WIEGEKARTE.lfd_nr,"n/a"); 
			
			if(nCount > 1) {
				sTitle = String.format(new MyString("Container wurde mehrfach eingebucht! Container-Nr: %s").CTrans(),	sContainerNr);
				sMessage = String.format(
						new MyString("Der Container %s wurde zum %s. mal bei der Verwiegung der Wiegekarte %s eingebucht.%n").CTrans(),
						sContainerNr,nCount,sWiegekarteNr);
				
				
				sMailKenner = String.format(sVerteiler+"#%s#%s#%s", sContainerNr,sWiegekarteNr,sDate);

				WK_BasicHandling basicHandling = new WK_BasicHandling(_recWK.getTransportHashMap());
				Vector<String> vecReceiver = basicHandling.getReceiverlist(ENUM_MANDANT_CONFIG.EMAIL_ZYKLUS_WK_DOPPELTE_EINBUCHUNG.getValue(bibALL.get_ID_MANDANT()));

				// Mail veschicken		
				try {
					_sendMail(_recWK,ENUM_EmailType.MAILVERSAND_CONTAINER_MEHRFACH_BUCHUNG,sTitle,sMessage,sMailKenner,vecReceiver);
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Containerzyklus Doppelbuchung! Mail wurde verschickt!!"));
				} catch (Exception e) {
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Fehler beim versenden der Mail wegen der Container-Doppelbuchung!"));
				}
								
			}

		}
		
		return this;
	}
	

	
	
	
	
	
	
	
	public WK_RB_MC_ContainerHandling _sendMail(RecDOWiegekarte recWK, ENUM_EmailType emailType, String subject, String mailText, String sendKey, Vector<String> vecReceiver) throws myException, MessageException, Exception {
		
		//hier wird eine email erzeugt und gespeichert
		Rec22EmailSend r2 = new Rec22EmailSend()._setAllowArchivmedienDifferentTableAndId(true);
		r2._setFromAdress(bibALL.get_RECORD_USER().get_EMAIL_cUF_NN("mail@portal.de"))
		._setSendTyp(SEND_TYPE.SINGLE)
		._setMailText(mailText)
		._setSubject(subject)
		._setTABBelongsTo(recWK.get_tab())
		._setTABIdBelongsTo(recWK.getIdLong())
		._setEmailType(emailType)
		._setMailEmailVerificationKey(sendKey);
		
		
		for (String receiver: vecReceiver) {
			r2._addTarget(receiver);
		}
		
		// speichern...
		r2._saveAll(true);
		
		// senden...
		r2._sendEmail(null);
		
		return this;
	}
	
	
	
	
	/**
	 * Schreibt einen neuen Containerzyklus und die erste Station, wenn ein Container beim WE angeliefert wird
	 * Schreibt eine Container-Ausgangs-Station des Containers, wenn eine Ausgangswiegung stattfindet.
	 * @author manfred
	 * @date 08.03.2021
	 *
	 * @return
	 * @throws myException 
	 * @throws Exception 
	 * @throws MessageException 
	 * @throws Rec22FieldNotInTableException 
	 * @throws Rec22FieldNotNullableException 
	 */
	public WK_RB_MC_ContainerHandling handleBuchungContainerstation(MyE2_MessageVector mv) throws myException  {
		// WE / WA
		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		
		RecDOWiegekarte _recWK = (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		Long idWK = _recWK.getActualID();
		BigDecimal bdNettogewicht = _recWK.get_raw_resultValue_bigDecimal(WIEGEKARTE.gewicht_nach_abzug_fuhre);
		
		Long countPrint  = _recWK.get_raw_resultValue_Long(WIEGEKARTE.druckzaehler,0L);
		
		RecDOWaegung2 waeg2 =  _recWK._get_Waegung2();
		Long idUserWaeg     = waeg2.get_raw_resultValue_Long(WAEGUNG.id_user_waegung);
		Date dtBuchung		= waeg2.getDateDbValue(WAEGUNG.datum);
		String zeitBuchung  = waeg2.getUfs(WAEGUNG.zeit);
		
		
		
		String 	istLieferant	= _recWK.get_ufs_dbVal(WIEGEKARTE.ist_lieferant,"N");
		boolean bWE = istLieferant.equalsIgnoreCase("Y");
		
		Long idAdresse 	 		= _recWK.getLongDbValue(WIEGEKARTE.id_adresse_lieferant);
		Long idUserHaendler 	= null;
		
		if (idAdresse != null) {
			Rec22 recAdr = new Rec22(_TAB.adresse)._fill_id(idAdresse);
			Long typAdresse = recAdr.get_raw_resultValue_Long(ADRESSE.adresstyp);
			
			// idUserHändler abhängig vom Typ der Adresse...
			idUserHaendler = typAdresse == 1 ? recAdr.get_raw_resultValue_Long(ADRESSE.id_user, null) : recAdr.get_raw_resultValue_Long(ADRESSE.id_user_lager_haendler, null);
			
			// wenn Lieferadresse und Händler ist leer
			if (idUserHaendler == null && recAdr.getUfs(ADRESSE.adresstyp,"")!= "1") {
				idUserHaendler = recAdr.get_raw_resultValue_Long(ADRESSE.id_user_lager_haendler,null);
				
				// wenn kein Lagerhändler...
				if (idUserHaendler == null) {
					// Hauptadresse finden...
					SqlStringExtended sqlext = new SqlStringExtended("SELECT * FROM JT_LIEFERADRESSE WHERE ID_ADRESSE_LIEFER = ?");
					sqlext._addParameter(new Param_Long(idAdresse));
					RecList22 recAdrLief = new RecList22(_TAB.lieferadresse)._fill(sqlext);
					if (recAdrLief != null && recAdrLief.size() > 0) {
						Rec22 recMain = recAdrLief.get(0);
						Long idAdresseMain = recMain.get_raw_resultValue_Long(LIEFERADRESSE.id_adresse_basis,null);
						
						if (idAdresseMain != null ) {
							Rec22 recAdrMain = new Rec22(_TAB.adresse)._fill_id(idAdresseMain);
							idUserHaendler = recAdrMain.get_raw_resultValue_Long(ADRESSE.id_user, null);
						}
					}
				}
			}
		}
		
		
		Long idContainer 		= _recWK.getLongDbValue(WIEGEKARTE.id_container_eigen);
		Long idArtikel 			= _recWK.getLongDbValue(WIEGEKARTE.id_artikel_sorte);
		
		Long idLagAbsetz 		= _recWK.getLongDbValue(WIEGEKARTE.id_lagerplatz_absetz);
		Long idLagSchuett 		= _recWK.getLongDbValue(WIEGEKARTE.id_lagerplatz_schuett);
		Long idLagerplatz 		= idLagAbsetz != null ? idLagAbsetz : idLagSchuett ;
		
		String  sKategorie 		= _recWK.get_ufs_dbVal(WIEGEKARTE.gueterkategorie,"n/a");
				
		
		String LKW				= _recWK.get_ufs_dbVal(WIEGEKARTE.kennzeichen);
		String ANH				= _recWK.get_ufs_dbVal(WIEGEKARTE.trailer);
		String kennzeichen 		= S.NN(LKW, "") + (S.isFull(ANH) ? " / " + ANH : "");
	
		String wkTyp  			= _recWK.get_ufs_dbVal(WIEGEKARTE.typ_wiegekarte);
		
        // Letzte Tätigkeit
		EnumTaetigkeit letzteTaetigkeit = null;
		
		
		if (countPrint != 1 || idContainer == null ) {
			// messages schreiben....
			
			return this;
		}

		// Dokumentarische Verwiegung wird nicht eingetragen
		if (wkTyp.equals(WK_RB_ENUM_WKTYP.DOKUMENTARISCH.db_val())) {
			return this;
		}
		
		// Fremd-Verwiegung wird nicht eingetragen
		if (wkTyp.equals(WK_RB_ENUM_WKTYP.FREMD.db_val())) {
			return this;
		}
				
		

		 
		
		// Buchungsdatum und Zeit zusammen bringen...
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dtBuchungKomplett = df.format(dtBuchung) + " " + zeitBuchung;
		
		// und sql-String draus machen
		String datumBuchung = String.format("to_date('%s','YYYY-MM-DD HH24:MI')",dtBuchungKomplett) ;


		if (bWE ) {
			
			// prüfen, ob man den default-Lagerplatz braucht
			if (idLagerplatz == null) {
				idLagerplatz = getIDDefaultLagerplatz();
			} 
			
			// beenden, wenn Zyklus für Schüttgut nicht angelegt werden soll
			if (sKategorie.equals(ENUM_Gueterkategorie.SCHUETTGUT.db_val()) && ENUM_MANDANT_CONFIG.CONT_ZYKLUS_WK_SCHUETTGUT_STARTEN.equals("N")) {
				return this;
			}

			// beenden, wenn Zyklus für Stückgut nicht angelegt werden soll
			if (sKategorie.equals(ENUM_Gueterkategorie.STUECKGUT.db_val()) && ENUM_MANDANT_CONFIG.CONT_ZYKLUS_WK_STUECKGUT_STARTEN.equals("N")) {
				return this;
			}
			
			
			try {

				// erzeuge CONTAINERZYKLUS mit der ersten STATION
				Rec22 recContainerzyklus = new Rec22(_TAB.container_zyklus);
				Rec22 recContainerstation = new Rec22(_TAB.container_station);
				Rec22 recContainerstation2 = null;

				recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.id_adresse, idAdresse != null ? idAdresse: null);
				recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.id_user_haendler, idUserHaendler );
				recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.id_container, idContainer);
				recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.id_wiegekarte, idWK);
				recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.id_artikel, idArtikel != null ? idArtikel : null);
				recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.nettogewicht_beginn, bdNettogewicht );
				recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.abgeschlossen, "N");
				recContainerzyklus._setNewValueInDatabaseTerminus(CONTAINER_ZYKLUS.datum_zeit_buchung, datumBuchung );
				recContainerzyklus._setNewValueInDatabaseTerminus(CONTAINER_ZYKLUS.datum_zeit_buchung_last, datumBuchung );
				recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.id_user_letzte_pos_buchung, idUserWaeg );
				
				
				
				
				letzteTaetigkeit = EnumTaetigkeit.EINGANGSWIEGUNG;
				recContainerstation._setNewValueInDatabaseTerminus(CONTAINER_STATION.id_container_zyklus.fn(),CONTAINER_ZYKLUS._tab().seq_currval());
				recContainerstation._setNewVal(CONTAINER_STATION.id_lagerplatz, idLagerplatz);
				recContainerstation._setNewVal(CONTAINER_STATION.taetigkeit, letzteTaetigkeit.name());
				recContainerstation._setNewVal(CONTAINER_STATION.id_user_buchung, idUserWaeg);
				recContainerstation._setNewValueInDatabaseTerminus(CONTAINER_STATION.datum_zeit_buchung, datumBuchung);
				recContainerstation._setNewVal(CONTAINER_STATION.kfzkennzeichen, kennzeichen);

				
				// Schüttgut Zyklus abschliessen, wenn 
				if (sKategorie.equals(ENUM_Gueterkategorie.SCHUETTGUT.db_val())	&& idLagSchuett != null	) {
					recContainerstation2  = new Rec22(_TAB.container_station);

					letzteTaetigkeit = EnumTaetigkeit.KIPPEN;					
					recContainerstation2._setNewValueInDatabaseTerminus(CONTAINER_STATION.id_container_zyklus.fn(),CONTAINER_ZYKLUS._tab().seq_currval());
					recContainerstation2._setNewVal(CONTAINER_STATION.id_lagerplatz, idLagerplatz);
					recContainerstation2._setNewVal(CONTAINER_STATION.taetigkeit, letzteTaetigkeit.name());
					recContainerstation2._setNewVal(CONTAINER_STATION.id_user_buchung, idUserWaeg);
					recContainerstation2._setNewValueInDatabaseTerminus(CONTAINER_STATION.datum_zeit_buchung, datumBuchung);
					recContainerstation2._setNewVal(CONTAINER_STATION.kfzkennzeichen, kennzeichen);
				}
				
				// Schüttgut Zyklus abschliessen, wenn 
				if (sKategorie.equals(ENUM_Gueterkategorie.SCHUETTGUT.db_val())
						&& idLagSchuett != null
						&& ENUM_MANDANT_CONFIG.CONT_ZYKLUS_WK_SCHUETTGUT_ABSCHLIESSEN.getCheckedValue().toUpperCase().contains("ENTLADEN")
						) {
					recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.abgeschlossen, "Y");
					
				}
			
				if (sKategorie.equals(ENUM_Gueterkategorie.SCHUETTGUT.db_val())
						&& idLagAbsetz != null
						&& ENUM_MANDANT_CONFIG.CONT_ZYKLUS_WK_SCHUETTGUT_ABSCHLIESSEN.getCheckedValue().toUpperCase().contains("EINLAGERN")) {
					recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.abgeschlossen, "Y");
				}
				
				// Stückgut Zyklus abschliessen, wenn 
				if (sKategorie.equals(ENUM_Gueterkategorie.STUECKGUT.db_val())
						&& idLagSchuett != null
						&& ENUM_MANDANT_CONFIG.CONT_ZYKLUS_WK_STUECKGUT_ABSCHLIESSEN.getCheckedValue().toUpperCase().contains("ENTLADEN")) {
					recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.abgeschlossen, "Y");
				}
			
				if (sKategorie.equals(ENUM_Gueterkategorie.STUECKGUT.db_val())
						&& idLagAbsetz != null
						&& ENUM_MANDANT_CONFIG.CONT_ZYKLUS_WK_STUECKGUT_ABSCHLIESSEN.getCheckedValue().toUpperCase().contains("EINLAGERN")) {
					recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.abgeschlossen, "Y");
				}
				
				
				// Tätigkeit der Letzten Buchungsposition
				recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.taetigkeit_letzte_pos, letzteTaetigkeit.name() );

				
				
				recContainerzyklus._SAVE(false);
				if (recContainerstation2 != null ) {
					recContainerstation._SAVE(false);
					recContainerstation2._SAVE(true);
				} else {
					recContainerstation._SAVE(true);
				}
				
			} catch (Exception e) {
				mv._addInfo("Fehler beim Anlegen des Containerzyklus");
			}
			
			
		} else {
			// WA
			//
			// suche nach einem aktiven Vorgang mit dem Container...
			//
			RecList_ContainerZyklus rl = new RecList_ContainerZyklus(RecList_ContainerZyklus.getSqlExt_LastActive( idContainer.toString()) );
			
			// 1. gefundenen Zyklus abschliessen
			if (rl != null && rl.size() > 0) {
				Rec22 recZyklus = rl.get(0);
				
				Rec_ContainerZyklus rZyklus = new Rec_ContainerZyklus(recZyklus);
				
				// Zyklus updaten
				try {
					rZyklus._setNewVal(CONTAINER_ZYKLUS.abgeschlossen, "Y");
					// Abschluss-Station erzeugen
					Rec22 recContainerstation = new Rec22(_TAB.container_station);
					idLagerplatz = getIDDefaultLagerplatz();
					letzteTaetigkeit = EnumTaetigkeit.AUSGANGSWIEGUNG;
					recContainerstation._setNewValueInDatabaseTerminus(CONTAINER_STATION.id_container_zyklus.fn(),rZyklus.getIdLong().toString());
					recContainerstation._setNewVal(CONTAINER_STATION.id_lagerplatz, idLagerplatz);
					recContainerstation._setNewVal(CONTAINER_STATION.taetigkeit, letzteTaetigkeit.name());
					recContainerstation._setNewVal(CONTAINER_STATION.id_user_buchung, idUserWaeg);
					recContainerstation._setNewValueInDatabaseTerminus(CONTAINER_STATION.datum_zeit_buchung, datumBuchung);
					recContainerstation._setNewVal(CONTAINER_STATION.kfzkennzeichen, kennzeichen);
					
					// Speichern
					rZyklus._SAVE(false);
					recContainerstation._SAVE(true);
					
					rl.remove(recZyklus.getActualID().toString());
					
					// alle weiteren Zyklen abschliessen
					for (Rec22 rec: rl) {
						rZyklus = new Rec_ContainerZyklus(rec);
						
						// Zyklus updaten
						rZyklus._setNewVal(CONTAINER_ZYKLUS.abgeschlossen, "Y");
						rZyklus._setNewValueInDatabaseTerminus(CONTAINER_ZYKLUS.datum_zeit_buchung_last, datumBuchung );
						rZyklus._setNewVal(CONTAINER_ZYKLUS.id_user_letzte_pos_buchung, idUserWaeg);
						rZyklus._setNewVal(CONTAINER_ZYKLUS.taetigkeit_letzte_pos, letzteTaetigkeit.name() );
						
						// Speichern
						rZyklus._SAVE(false);
					}
				} catch (Exception e) {
					mv._addInfo("Fehler beim Abschließen des Containerzyklus");
				}
				
			} else {
				// kein aktiver Container-Zyklus gefunden, also den Container ausbuchen, damit man weiss, dass er den Hof verlassen hat
				// erzeuge CONTAINERZYKLUS mit der ersten STATION
				Rec22 recContainerzyklus = new Rec22(_TAB.container_zyklus);
				try { 
					recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.id_adresse, idAdresse != null ? idAdresse: null);
					recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.id_user_haendler, idUserHaendler );
					recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.id_container, idContainer);
					recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.id_wiegekarte, idWK);
					recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.id_artikel, idArtikel != null ? idArtikel : null);
					recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.nettogewicht_beginn, bdNettogewicht );
					recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.abgeschlossen, "Y");
					recContainerzyklus._setNewValueInDatabaseTerminus(CONTAINER_ZYKLUS.datum_zeit_buchung, datumBuchung );
					recContainerzyklus._setNewValueInDatabaseTerminus(CONTAINER_ZYKLUS.datum_zeit_buchung_last, datumBuchung );
					recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.id_user_letzte_pos_buchung, idUserWaeg );
					
					
					Rec22 recContainerstation = new Rec22(_TAB.container_station);
					idLagerplatz = getIDDefaultLagerplatz();
					letzteTaetigkeit = EnumTaetigkeit.AUSGANGSWIEGUNG;
					recContainerstation._setNewValueInDatabaseTerminus(CONTAINER_STATION.id_container_zyklus.fn(),CONTAINER_ZYKLUS._tab().seq_currval());
					recContainerstation._setNewVal(CONTAINER_STATION.id_lagerplatz, idLagerplatz);
					recContainerstation._setNewVal(CONTAINER_STATION.taetigkeit, letzteTaetigkeit.name());
					recContainerstation._setNewVal(CONTAINER_STATION.id_user_buchung, idUserWaeg);
					recContainerstation._setNewValueInDatabaseTerminus(CONTAINER_STATION.datum_zeit_buchung, datumBuchung);
					
					
					// Tätigkeit der Letzten Buchungsposition
					recContainerzyklus._setNewVal(CONTAINER_ZYKLUS.taetigkeit_letzte_pos, letzteTaetigkeit.name() );
					
					recContainerzyklus._SAVE(false);
					recContainerstation._SAVE(true);
				} catch (Exception e) {
					mv._addInfo("Fehler beim Anlegen des Containerzyklus");
				}
			} 
			
		}
		
		return this;
	}
	

	
	
	
	
	
	/**
	 * holt die ID für den Default-Lagerplatz
	 * @author manfred
	 * @date 11.03.2021
	 *
	 * @return
	 * @throws myException
	 */
	private Long getIDDefaultLagerplatz() throws myException {
		Long lRet = null;
		Rec22 r = null;
		RecList_Lagerplatz rl = new RecList_Lagerplatz()._fill(RecList_Lagerplatz.getSql_Lagerplatz_default());
		if (rl.size() == 1) {
			r = rl.get(0);
			lRet = Long.parseLong(r.getUfs(LAGERPLATZ.id_lagerplatz));
		} else if (rl.size() > 1){
			r = rl.get(0);
			lRet = Long.parseLong(r.getUfs(LAGERPLATZ.id_lagerplatz));
		} else {
			throw(new myException("Es ist kein Default-Lagerplatz definiert"));
		}
		
		return lRet;
	}
	
	

}
