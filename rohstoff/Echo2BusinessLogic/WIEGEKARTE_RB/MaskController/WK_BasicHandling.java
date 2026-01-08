package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController;

import java.io.File;
import java.util.Date;
import java.util.Vector;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.BasicInterfaces.IF_BasicTypeWrapper.DataTypeException;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector.MessageException;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_TYPE;
import panter.gmbh.basics4project.ENUM_EmailType;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER_STATION;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER_ZYKLUS;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_BLOCK;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_BEFUND;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.archive.Archiver.ENUM_ARCHIV_AUFTEILUNG;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.archive.Archiver_CONST.MEDIENKENNER;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.RECORD2.Rec22.Rec22FieldNotInTableException;
import panter.gmbh.indep.dataTools.RECORD2.Rec22.Rec22FieldNotNullableException;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.pdf.PDF_Overlay_Factory_4_FreeText;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_ENUM_WKTYP;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMap_Wiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintWiegekarte_STORNO;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarteBefund;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_WK_Abzug_Geb;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_WK_Mge_Abz;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.EnumTaetigkeit;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22Archivmedien;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22EmailSend;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22EmailSend.EmailDoubleKennungAndKeyException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList22_Archivmedien;

public class WK_BasicHandling  {

	
	//zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   _transportHM = null;
	
    // Print-Handler muss darf nicht lokal sein, da sonst zu früh zerstört 
    WK_RB_PrintWiegekarte_STORNO wkPrint = null;
    
    
	/**
	 * @author manfred
	 * @date 19.02.2021
	 *
	 */
	public WK_BasicHandling(RB_TransportHashMap transportData) {
		this._transportHM = transportData;
	}
	
	

	/**
	 * Storniert die Wiegekarte
	 * @author manfred
	 * @date 19.02.2021
	 *
	 * @param IDWiegekarte
	 * @param Stornogrund
	 * @return
	 */
	public WK_BasicHandling _storniereWiekgekarte(Long IDWiegekarte, String Stornogrund ,MyE2_MessageVector mv) {
		Rec22 recWK;
		MyE2_MessageVector mv_local = bibMSG.getNewMV();
		
		try {
			recWK = new Rec22(_TAB.wiegekarte)._fill_id(IDWiegekarte);
			
			if (recWK.getUfs(WIEGEKARTE.storno, "N").equalsIgnoreCase("Y")) {
				mv._addAlarm("Die Wiegekarte wurde bereits storniert. Der Vorgang wird abgebrochen.");
			} else {
				recWK._setNewValue(WIEGEKARTE.storno, "Y", mv);
				recWK._setNewValue(WIEGEKARTE.storno_grund, Stornogrund, mv);
				recWK._setNewValueInDatabaseTerminus(WIEGEKARTE.storniert_am, "SYSDATE");
				recWK._setNewVal(WIEGEKARTE.storniert_von, bibALL.get_USERNAME(), mv);
				recWK._SAVE(true, mv);
			}
			
			// email verschicken, wenn das Storno geklappt hat...
			// Storno-WK drucken
			// Container-Zyklus stornieren
			if (mv.isOK()) {
				
				
				mv._addInfo(new MyString("Die Wiegekarte wurde storniert!").CTrans());
				try {
					// eMail zur Stornierung schicken
					_sendEmailWhenStorno(recWK, mv_local);
				} catch (Exception e) {
					e.printStackTrace();
				}

				
				try {
					// Storno-Wiegekarte automatisch drucken...
					// Druck wird immer gestartet, auch wenn die Verwiegung noch nicht komplett ist.
					if (ENUM_MANDANT_DECISION.WIEGEKARTE_DRUCKE_STORNO_AUTO.is_YES()) {
						wkPrint = new WK_RB_PrintWiegekarte_STORNO(IDWiegekarte.toString());
						if (wkPrint != null) {
							wkPrint.Print();
						}
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				//
				// Container-Zyklus stornieren...
				handleBuchungContainerstation_STORNO(IDWiegekarte, mv);
				
			}
		} catch (myException e) {
			mv._addAlarm("Fehler beim Speichern der stornierten Wiegekarte.");
		}
		
		// alle lokalen Meldungen anhängen...
		mv._add(mv_local);
		
		return this;

	}

		
	/**
	 * Email beim Stornieren einer Wiegekarte verschicken!
	 * @author manfred
	 * @date 23.02.2021
	 *
	 * @param recWK
	 * @param mv
	 * @return
	 * @throws myException 
	 * @throws EmailDoubleKennungAndKeyException
	 * @throws DataTypeException
	 * @throws MessageException
	 * @throws Exception
	 */
	public WK_BasicHandling _sendEmailWhenStorno(Rec22 recWK,MyE2_MessageVector mv) throws myException  {
		
		if (recWK.get_tab().equals(_TAB.wiegekarte)) {
			
			// Standard-Sende-Email
			String sEmailSender =bibALL.get_RECORD_USER().get_EMAIL_cUF_NN("mail@portal.de"); 
			
			Vector<String> vecReceiver = getReceiverlist(ENUM_MANDANT_CONFIG.EMAIL_WIEGEKARTE_STORNIERUNG.getValue(bibALL.get_ID_MANDANT()));
			
			if (vecReceiver.size() <= 0) {
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Kein Empfänger für die Versendung der WK-Stornomeldung angegeben"));
			} else {
				
				Long idWiegekarte = recWK.getActualID();
				String IDWiegekarte = recWK.getActualID().toString();
				
				// email-Kennung
				String sendKey = "#WK_STORNO_#"+IDWiegekarte.toString();
				
				String WKNo = recWK.get_ufs_dbVal(WIEGEKARTE.lfd_nr,"-");
				
				String MailSubject = String.format(new MyString("Wiegekarte-Nr %s wurde storniert!").CTrans(),WKNo) ;
				String 
				MailText = String.format(new MyString("Die Wiegekarte mit der Nr %s wurde storniert.\n").CTrans(),WKNo);
				MailText += String.format(new MyString("Storno-Grund: %s.\n").CTrans(),recWK.get_ufs_dbVal(WIEGEKARTE.storno_grund,"%"));
				MailText += String.format(new MyString("Storno-Datum: %s.\n").CTrans(),recWK.get_fs_dbVal(WIEGEKARTE.storniert_am,"%"));
				MailText += String.format(new MyString("Storiert von %s.\n").CTrans(),recWK.get_ufs_dbVal(WIEGEKARTE.storniert_von,"%"));
				MailText += "\n\n";
//				MailText += String.format(new MyString("Wiegekarte wurde gedruck am: \n").CTrans(),recWK.get_fs_dbVal(WIEGEKARTE.gedruckt_am,"-"));
				
				
				// erzeugen einer neuen Archivdatei, die den Anhang der Storno-Mail beinhaltet...NULL, wenn keine Dokumente
				String idArchive = createArchiveFileForStorno(idWiegekarte);

				//hier wird eine email erzeugt und gespeichert
				Rec22EmailSend r2 = new Rec22EmailSend()._setAllowArchivmedienDifferentTableAndId(true);
				
				try {

					r2._setFromAdress(bibALL.get_RECORD_USER().get_EMAIL_cUF_NN("mail@portal.de"))
					._setSendTyp(SEND_TYPE.SINGLE)
					._setMailText(MailText)
					._setSubject(MailSubject)
					._setTABBelongsTo(_TAB.wiegekarte)
					._setTABIdBelongsTo(idWiegekarte)
					._setEmailType(ENUM_EmailType.MAILVERSAND_WAAGE_STORNO)
					._setMailEmailVerificationKey(sendKey);
					
					if (idArchive != null) {
						r2._addAttachment(new Rec22Archivmedien()._fill_id(idArchive));
					}
					
					for (String receiver: vecReceiver) {
						r2._addTarget(receiver);
					}
				
					// speichern...hier wird auch der sendkey geprüft
					r2._saveAll(true);
					// senden...
					r2._sendEmail(null);
					
					// Meldung
					mv._addInfo(new MyString("Storno-Info Mail wurde versandt.").CTrans());

				}catch (Exception ex ) {
					mv._addAlarm(ex.getMessage());
				}
			}
		}
		
		return this;
	}
	

	
	/**
	 * 
	 * @author manfred
	 * 
	 * @param sKenner
	 * @return
	 * @throws myException
	 */
	public Vector<String> getReceiverlist(String sKenner) throws myException{
		Vector<String> vec = new Vector<>();
		SEL sel = new SEL("*")
				.FROM(_TAB.email_block)
				.WHERE(new vglParam(EMAIL_BLOCK.titel));

		
		SqlStringExtended  sqlExt = new SqlStringExtended(sel.s())
					._addParameters(new VEK<ParamDataObject>()
					._a(new Param_String("",sKenner))
					);
		
		RecList22 rl = new RecList22(_TAB.email_block)._fill(sqlExt);
		
		if (rl != null && rl.size() > 0) {
			for (Rec22 r : rl) {
				if (r.get_tab().equals(_TAB.email_block)) {
					String emails =  r.getUfs(EMAIL_BLOCK.email_liste);
					vec = S.tokenize(emails);
				}
			}
		}
		return vec;
	}
	
	
	
	
	/**
	 * 
	 * @author manfred
	 * 
	 *
	 * @param idWiegekarte
	 * @return
	 * @throws myException
	 */
	private String createArchiveFileForStorno(Long idWiegekarte) throws myException {
			String id = null;
			
			// Archivmedium zusammensetzen aus allen PRINT-Dateien
			RecList22_Archivmedien rlArch = new RecList22_Archivmedien(_TAB.wiegekarte.baseTableName(), idWiegekarte,MEDIENKENNER.PRINT)	;
			if (rlArch.size() > 0) {
				myTempFile  tf1 = rlArch.generate_pdf_tempFile_concatenated();
				
				
				// stempeln...
				myTempFile tf = new PDF_Overlay_Factory_4_FreeText().generateCopyFromOriginal(tf1.getFileName(),"STORNO",Color.RED); 
				
				tf.set_bDeleteAtFinalize(true);
				
				Archiver arch = new Archiver.Builder(_TAB.wiegekarte.baseTableName(),Archiver_CONST.MEDIENKENNER.WK_STORNO )
						.setTable("JT_WIEGEKARTE")
						.setAufteilung(new Date(), ENUM_ARCHIV_AUFTEILUNG.YEAR_MONTH_DAY)
						.build();
				
				String sRet = "";
				try {
					String file_1 = tf.getFile().getPath();
					String filepath = file_1.substring(0, file_1.lastIndexOf(File.separator));
					String filename = file_1.substring(file_1.lastIndexOf(File.separator)+1);
					
					sRet = arch.writeTempFileToArchive(filepath, filename, idWiegekarte.toString(), "Email-Storno-Versand der Wiegekarte", "WK_"+idWiegekarte+"_STORNO.pdf");
					id = arch.get_cLastNew_SEQ_ARCHIVMEDIEN();
				} catch (myException e) {
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Storno-Dokument konnte nicht archivert werden."));
					id = null;
				}
			}
			
			
			return id;
	}
	
	

	
	
	
	/**
	 * @author manfred
	 * 
	 * @throws myException
	 */
	public Long  _createStornoKorrekturWiegekarte( String IDWiegekarte, MyE2_MessageVector _mv, boolean bCommit) throws myException   {

		RecDOWiegekarte rec 			=  new RecDOWiegekarte(MASK_STATUS.NEW)._fill_id(IDWiegekarte);
		RecDOWiegekarteBefund recBefund = rec._get_Befundung();
		
		
		// Anlegen der Folgewiegekarte...		
		Long idBaseParent = rec.get_raw_resultValue_Long(WIEGEKARTE.id_wiegekarte);
		
		
		VEK<IF_Field> fieldsToClear = new VEK<IF_Field>();
		fieldsToClear._a(rec.getAutoFieldsStd());
		fieldsToClear
						._a(WIEGEKARTE.id_wiegekarte)
						._a(WIEGEKARTE.lfd_nr)
						._a(WIEGEKARTE.gedruckt_am)
						._a(WIEGEKARTE.druckzaehler)
						._a(WIEGEKARTE.druckzaehler_eingangsschein)
						._a(WIEGEKARTE.storno)
						._a(WIEGEKARTE.storno_grund)
						._a(WIEGEKARTE.storniert_am)
						._a(WIEGEKARTE.storniert_von)
						._a(WIEGEKARTE.nettogewicht)
						._a(WIEGEKARTE.gewicht_abzug)
						._a(WIEGEKARTE.gewicht_abzug_fuhre)
						._a(WIEGEKARTE.gewicht_nach_abzug_fuhre)
						._a(WIEGEKARTE.gewicht_nach_abzug)
//					  ._a(WIEGEKARTE.anz_behaelter)
//					  ._a(WIEGEKARTE.anz_bigbags)
//					  ._a(WIEGEKARTE.anz_gitterboxen)
//					  ._a(WIEGEKARTE.anz_paletten)
//					  ._a(WIEGEKARTE.befund)
//					  ._a(WIEGEKARTE.bemerkung1)
//					  ._a(WIEGEKARTE.bemerkung2)
//					  ._a(WIEGEKARTE.bemerkung_intern)
//					  ._a(WIEGEKARTE.container_nr)
//					  ._a(WIEGEKARTE.container_tara)
//					  ._a(WIEGEKARTE.id_container_eigen)
//					  ._a(WIEGEKARTE.befund)
//					  ._a(WIEGEKARTE.es_nr)
//					  ._a(WIEGEKARTE.id_artikel_bez)
//					  ._a(WIEGEKARTE.id_artikel_sorte)
//					  ._a(WIEGEKARTE.id_wiegekarte_parent)
//					  ._a(WIEGEKARTE.sorte_hand)
//					  ._a(WIEGEKARTE.ist_gesamtverwiegung)
//					  ._a(WIEGEKARTE.container_nr)
//					  ._a(WIEGEKARTE.container_tara)
//					  ._a(WIEGEKARTE.siegel_nr)
//					  ._a(WIEGEKARTE.gueterkategorie)
//					  ._a(WIEGEKARTE.id_lagerplatz_absetz)
//					  ._a(WIEGEKARTE.id_lagerplatz_schuett)
					  ;
					  
	
		RecDOWiegekarte _recCopy  = new RecDOWiegekarte(rec.getRecForCreateCopy(fieldsToClear,_mv),MASK_STATUS.NEW_COPY);
		
		
		_recCopy._setNewValueInDatabaseTerminus(WIEGEKARTE.lfd_nr, "SEQ_" + bibALL.get_ID_MANDANT() + "_WIEGEKARTENNR.NEXTVAL");
		_recCopy._setNewValueInDatabaseTerminus(WIEGEKARTE.id_wiegekarte, _TAB.wiegekarte.seq_nextval());
		//_recCopy._setNewValue(WIEGEKARTE.id_wiegekarte_storno, idBaseParent, _mv);
		
		// Typ der Wiegekarte ist Wiegekarte_Korrektur
		_recCopy._setNewValue(WIEGEKARTE.typ_wiegekarte, WK_RB_ENUM_WKTYP.WIEGEKARTE_KORREKTUR.db_val(), _mv);
		_recCopy._setNewValue(WIEGEKARTE.id_wiegekarte_storno, IDWiegekarte, _mv);

		
		// WK_BEFUND erzeugen
		VEK<IF_Field> fieldsToClearBefund = new VEK<IF_Field>();
		RecDOWiegekarteBefund  _recwkBefund = null;
		if (recBefund != null ) {
			fieldsToClearBefund._a(recBefund.getAutoFieldsStd());
			fieldsToClearBefund._a(WIEGEKARTE_BEFUND.id_wiegekarte_befund);
			
			_recwkBefund = new RecDOWiegekarteBefund(recBefund.getRecForCreateCopy(fieldsToClearBefund, _mv),MASK_STATUS.NEW_COPY);
			_recwkBefund._setNewValueInDatabaseTerminus(WIEGEKARTE_BEFUND.id_wiegekarte, _TAB.wiegekarte.seq_currval());
			_recwkBefund._setNewValueInDatabaseTerminus(WIEGEKARTE_BEFUND.id_wiegekarte_befund, _TAB.wiegekarte_befund.seq_nextval());
		} 
		
		
		_recCopy._SAVE(false, _mv);
		if (_recwkBefund != null) {
			_recwkBefund._SAVE(false, _mv);
		}
		
		
		Long id = null;
		
		if (_mv.isOK()) {
			id = _recCopy.getId();
			Long id_new = _recCopy.getActualID();
			
			// die Liste der Tara-Abzüge kopieren
			RecList_WK_Abzug_Geb rlGeb = new RecList_WK_Abzug_Geb(IDWiegekarte);
			for (Rec22 r : rlGeb) {
				Rec22 rNew = new Rec22(r.getRecForCreateCopyStdExclude(_mv));
				rNew._setNewValue(WIEGEKARTE_ABZUG_GEB.id_wiegekarte , id.toString(),_mv);
				rNew._setNewValueInDatabaseTerminus(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_abzug_geb, _TAB.wiegekarte_abzug_geb.seq_nextval());
				
				try {
					rNew._SAVE(false);
				} catch (Exception e) {
					// 	
				}
			}
			
			
			
			// die Liste der Fuhrenabzüge kopieren
			RecList_WK_Mge_Abz rlMge = new RecList_WK_Mge_Abz(IDWiegekarte);
			for (Rec22 r : rlMge) {
				Rec22 rNew = new Rec22(r.getRecForCreateCopyStdExclude(_mv));
				rNew._setNewValue(WIEGEKARTE_MGE_ABZ.id_wiegekarte , id.toString(),_mv);
				rNew._setNewValueInDatabaseTerminus(WIEGEKARTE_MGE_ABZ.id_wiegekarte_mge_abz, _TAB.wiegekarte_mge_abz.seq_nextval());
				try {
					rNew._SAVE(false);
				} catch (Exception e) {
					// 	
				}
				
			}

			boolean bOK = true;
			if (bCommit) {
				bOK = bibDB.Commit();
			} 
			
			if(bOK && bCommit) {
				_mv.add_MESSAGE(new MyE2_Info_Message("StornoKorrektur-Wiegekarte angelegt: ID " + String.valueOf(id) + " ... " + String.valueOf(id_new)));
			}
			
		}
		
		return id;
	}

	
	

	/**
	 * Schreibt einen neuen Containerzyklus und die erste Station, wenn ein Container beim WE angeliefert wird
	 * Schreibt eine Container-Ausgangs-Station des Containers, wenn eine Ausgangswiegung stattfindet.
	 * @author manfred
	 * @date 08.03.2021
	 *
	 * @return
	 * @throws Exception 
	 * @throws MessageException 
	 * @throws Rec22FieldNotInTableException 
	 * @throws Rec22FieldNotNullableException 
	 */
	public WK_BasicHandling handleBuchungContainerstation_STORNO(Long IDWiegekarte, MyE2_MessageVector mv) throws myException  {
		RecDOWiegekarte _recWK 	=  new RecDOWiegekarte(MASK_STATUS.NEW)._fill_id(IDWiegekarte);
	
		// Stornierung nur, wenn Container vorhanden ist.
		Long idContainer 		= _recWK.getLongDbValue(WIEGEKARTE.id_container_eigen);
		if (idContainer == null) {
			return this;
		}
				
		

		String sUserSTORNO     	= _recWK.getUfs(WIEGEKARTE.storniert_von);
		Date dtStorno			= _recWK.getDateDbValue(WIEGEKARTE.storniert_am);
		
		String sGrund			= _recWK.getUfs(WIEGEKARTE.storno_grund);
		sGrund = S.isFull(sGrund) ? "Storno: " + sGrund + " / Von:" + sUserSTORNO: null ;
		
		String sKFZ				= _recWK.getUfs(WIEGEKARTE.kennzeichen) ;
		String sTrailer 		= _recWK.getUfs(WIEGEKARTE.trailer);
		sKFZ = sKFZ + (S.isFull(sTrailer) ? " / " + sTrailer : "" );
		
		
		// prüfen, ob es einen aktuellen Zyklus mit dem Container gibt.
		SEL sel = new SEL(_TAB.container_zyklus).FROM(_TAB.container_zyklus)
						.WHERE(new vglParam(CONTAINER_ZYKLUS.id_container))
						.AND(new vglParam(CONTAINER_ZYKLUS.id_wiegekarte))
						.AND(new vgl(
								new TermSimple(" nvl (" + CONTAINER_ZYKLUS.abgeschlossen.tnfn() + ",'N')")
										,COMP.EQ
										,new TermSimple( "'N'" )
								)
							)
						;
		
		VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
		vecParam._a(new Param_Long(idContainer))
				._a(new Param_Long(IDWiegekarte));
		
		SqlStringExtended  sqlExt = new SqlStringExtended(sel.s())._addParameters(vecParam);
		
		try {
			RecList22 rl = new RecList22(_TAB.container_zyklus)._fill(sqlExt);
			if (rl.size() > 0) {
				// letzte Zyklus-Station holen für den Lagerplatz
				Rec22 rZyklus = rl.get(0);
				
				Rec22 recContainerstation = new Rec22(_TAB.container_station);
				
				Long idContainerZyklus = rZyklus.getActualID();
				
				RecList22 rlStationen = rZyklus.get_down_reclist22(CONTAINER_STATION.id_container_zyklus,"",CONTAINER_ZYKLUS.id_container_zyklus + " DESC");
				if (rlStationen.size() > 0) {
					Rec22 rStation = rlStationen.get(0);
					Long idLagerplatz = rStation.get_raw_resultValue_Long(CONTAINER_STATION.id_lagerplatz);
					
					recContainerstation._setNewVal(CONTAINER_STATION.id_container_zyklus.fn(),idContainerZyklus);
					
					recContainerstation._setNewVal(CONTAINER_STATION.id_lagerplatz, idLagerplatz);
					recContainerstation._setNewVal(CONTAINER_STATION.taetigkeit, EnumTaetigkeit.STORNO.name());
					recContainerstation._setNewVal(CONTAINER_STATION.id_user_buchung, Long.parseLong(bibALL.get_ID_USER()));
					recContainerstation._setNewVal(CONTAINER_STATION.datum_zeit_buchung, dtStorno);
					recContainerstation._setNewVal(CONTAINER_STATION.kfzkennzeichen, sKFZ);
					recContainerstation._setNewVal(CONTAINER_STATION.bemerkung, sGrund);
					// Speichern
					recContainerstation._SAVE(false);
					
				}
				
				//
				rZyklus._setNewVal(CONTAINER_ZYKLUS.bemerkung, sGrund);
				rZyklus._setNewVal(CONTAINER_ZYKLUS.abgeschlossen, "Y");
				rZyklus._SAVE(true);
				
				
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}
	
	
	
	
	
	
}
