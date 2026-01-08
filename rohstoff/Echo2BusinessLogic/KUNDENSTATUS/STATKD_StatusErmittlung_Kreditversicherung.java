package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Handler;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Info;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Info_Entry;

/**
 * Klasse ermittelt die offenen Forderungen und den aktuellen KV-Status für eine bestimmte Fuhre
 * bzw. für einen bestimmten Kunden  
 * @author manfred
 *
 */
public class STATKD_StatusErmittlung_Kreditversicherung {

	
	private RECLIST_USER m_rlUser = null;
	private Vector<MESSAGE_Entry> m_vMessageEntries= new Vector<MESSAGE_Entry>();
	
	
	//2016-03-07: umstellung auf DA_Decision_Check_KreditVersicherung
	private boolean b_test = false;
	private RECORD_VPOS_TPA_FUHRE   own_REC_FUHRE		= null;
	private Vector<fehlerinfo>      v_fehler		 	= new Vector<>();      //beteiligte Adressen mit problemen
	
	private Vector<KV_Info_Entry>	_vKVEntries			= null;  			  // Vektor mit den Kreditversicherungs-Infos einer Adresse
	
	
	public STATKD_StatusErmittlung_Kreditversicherung() {
	}

	
	/**
	 * Schreibt die Warnmeldung erneut, mit dem zusätzlichen Hinweis dass die Fuhre trotz Kreditlimits freigegeben wurde.  
	 * @throws myException 
	 */
	public boolean warnung_FuhreBestaetigt() throws myException{
		return createNewMessage("*** ACHTUNG*** \nTrotz Überschreitung des Kreditlimits wurde die Fuhre freigegeben.\n");
	}
	
	
	/**
	 * Ermittelt für die Fuhre, ob eine beteiligte Firma den Kreditversicherungs-Betrag überschreitet
	 * Falls die Kundenforderung über die Kreditversicherung hinaus geht, wird false zurückgeliefert und
	 * eine Meldung an den/die im System definierten Mitarbeiter geschickt.
	 * 
	 * 
	 * @param sIDFuhre
	 * @throws myException 
	 */
	public boolean pruefeFuhre(String sIDFuhre) throws myException{
		boolean bRet = true;
		m_vMessageEntries.clear();
		
		this.v_fehler.clear();
		
		
		RECORD_VPOS_TPA_FUHRE oRecFuhre = null;
		RECLIST_VPOS_TPA_FUHRE_ORT oRecListOrt = null;
		HashSet<String> setIDAdresse = new HashSet<String>();
		
		try {
			oRecFuhre = new RECORD_VPOS_TPA_FUHRE(sIDFuhre);
			oRecListOrt = oRecFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre();
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fuhre " + sIDFuhre + " konnte nicht geladen werden."));
			return false;
		}
		
		
		this.own_REC_FUHRE = new RECORD_VPOS_TPA_FUHRE(oRecFuhre);
		
		// Prüfung nur dann, wenn die Fuhre keine Fremdwaren-Fuhre ist
		if (oRecFuhre.get_ID_ADRESSE_FREMDAUFTRAG_cUF() != null || oRecFuhre.get_OHNE_ABRECHNUNG_cUF_NN("N").equalsIgnoreCase("Y") ){
			return true;
		}
		

		// Alle Adress-IDs ermitteln und in eine HashSet legen 
		try {
			setIDAdresse.add( oRecFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("*")) ;
			setIDAdresse.add( oRecFuhre.get_ID_ADRESSE_START_cUF_NN("*")) ;
			if (oRecListOrt != null && oRecListOrt.size() > 0){
				for (RECORD_VPOS_TPA_FUHRE_ORT oOrt : oRecListOrt.values()){
					setIDAdresse.add(oOrt.get_ID_ADRESSE_cUF_NN("*"));
				}
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		// Mitarbeiter die die Meldung bekommen:
		m_rlUser = new RECLIST_USER("nvl(MELDUNG_KREDITVERS_BETRAG,'N') = 'Y'", "");
		
		// dann die Adressen prüfen
		for (String sIDAdr : setIDAdresse){
			if ( !sIDAdr.equalsIgnoreCase("*") && !sIDAdr.equalsIgnoreCase(bibALL.get_ID_ADRESS_MANDANT()) ){
				
				// prüfen, ob ein KV-Vertrag vorhanden ist:
				BigDecimal bdKVBetrag = getKreditlimitInfos(sIDAdr);
				BigDecimal bdForderung = BigDecimal.ZERO;
				
				//testschalter, wenn true ist immer der vertrag ueberschritten
				if (b_test) {
					bdKVBetrag = new BigDecimal(1000);
				}
				
				
				if (bdKVBetrag != null){
					// Manfred: 2018-08-21: ein KV-Vertrag kann für mehrere Firmen gelten...

					HashSet<String> vIDAdressen = new HashSet<>();
					vIDAdressen.add(sIDAdr);
					
					KV_Info oKVInfo = new KV_Info();
					
					// durch die Liste der KV-Infos gehen und alle Adresse zu dem KV-Kopf ermitteln, da die Verbindlichkeiten summiert werden müssen
					if (_vKVEntries != null && _vKVEntries.size() > 0){
						for (KV_Info_Entry o: _vKVEntries){
							String idKopf = o.get_idKVKopf();
							if (idKopf != null){
								vIDAdressen.addAll(oKVInfo.getAllAddressIDsConnected(idKopf) ); 
							}
						}
						
					}
					
					
					// alle Forderungen aller Firmen summieren
					bdForderung = BigDecimal.ZERO;
					for (String idAdr: vIDAdressen){
						// Forderungen erst ermitteln, wenn ein KV-Betrag angegeben ist.
						BigDecimal bdFord = getForderungen(idAdr);
						if (bdFord != null ){
							bdForderung = bdForderung.add(bdFord);
						}
					}
					
					
					//testschalter, wenn true ist immer der vertrag ueberschritten
					if (b_test) {
						bdForderung = new BigDecimal(2000);
					}

					// wenn die Forderung größer als der Kreditversicherungsvertrag ist, dann warnung
					if (bdForderung != null &&  bdForderung.compareTo(bdKVBetrag)>0){
						bRet =false;

						// Adresse lesen 
						RECORD_ADRESSE recAdr = new RECORD_ADRESSE(sIDAdr);
						String sName = recAdr.get_NAME1_cUF_NN("") + ' ' + recAdr.get_NAME2_cUF_NN("") + ", " + recAdr.get_ORT_cUF_NN("") + " (" + recAdr.get_ID_ADRESSE_cUF_NN("?") + ")";
						String sFuhre = "Fuhre " + sIDFuhre;

						// Falls kein User angegeben wurde, der die Meldung bekommt, wird der Sachbearbeiter gewarnt.
						if (m_rlUser == null || m_rlUser.size() <= 0){
							bibMSG.add_MESSAGE(new MyE2_Warning_Message("ACHTUNG: Forderungsbetrag von " + sName + " ist höher als die aktuelle Kreditversicherung!"));
						} else {
							
							
							String sText = new MyString(" *** ACHTUNG *** \nFuhrenID: " + sIDFuhre + "\nDie Forderungen des Kunden sind höher als die abgeschlossene Kreditversicherungs-Summe: \nForderungen:").CTrans() +
												bibALL.convertBigDecimalToString(bdForderung,2) + 
												new MyString(" \nKreditversicherung: ").CTrans() + 
												bibALL.convertBigDecimalToString(bdKVBetrag,2) ;
							
							
							Vector<MESSAGE_Entry_Target> vTargets = new Vector<MESSAGE_Entry_Target>();
							// Sprung zur Adresse
							MESSAGE_Entry_Target oTarget = new MESSAGE_Entry_Target(
									sIDAdr, 
									E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK,
									FS_CONST.TABTEXT_FINANZEN_IN_ADRESSMASK, 
									null);
							vTargets.add(oTarget);
							// Sprung zur Fuhre
							oTarget = new MESSAGE_Entry_Target(
									sIDFuhre, 
									E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,
									null, 
									sFuhre);
							vTargets.add(oTarget);
							
							
//							MESSAGE_Entry oEntry = new MESSAGE_Entry(
//									sName,
//									sText , 
//									m_rlUser.get_vKeyValues(), 
//									true, 
//									bibALL.get_cDateNOWInverse("-"), 
//									null,
//									vTargets, 
//									MESSAGE_CONST.REMINDER_Kennung.MESSAGE_KREDITVERSICHERUNG_UEBERZOGEN.toString());
							
							MESSAGE_Entry oEntry = new MESSAGE_Entry()
								.set_Titel(sName)
								.set_Nachricht(sText)
								.set_vIdEmpfaenger(m_rlUser.get_vKeyValues())
								.set_Sofortanzeige(true)
								.set_DtAnzeigeAb(bibALL.get_cDateNOWInverse("-"))
								.set_vTargets(vTargets)
								.set_Kategorie(MESSAGE_CONST.REMINDER_Kennung.MESSAGE_KREDITVERSICHERUNG_UEBERZOGEN.toString());
							
							m_vMessageEntries.add(oEntry);
						}
						
						//2016-03-07: adressen sammeln
						this.add_fehler(new fehlerinfo(sIDAdr,new MyE2_String("").t("ACHTUNG: Forderungsbetrag von ").ut(sName).t(" ist höher als die aktuelle Kreditversicherung!")));
					}
				}
			}
		}
		
		createNewMessage("");
		return bRet;
	}

	//sorgt dafuer, dass jede adresse nur einmal erwaehnt wird
	private void add_fehler(fehlerinfo fehler_new) {
		for (fehlerinfo fehler: this.v_fehler) {
			if (fehler.id_adresse.equals(fehler_new.id_adresse)) {
				return;
			}
		}
		this.v_fehler.addElement(fehler_new);
		
	}
	
	
	/**
	 * fügt dieMessges des Vektors in die Datenbank ein
	 * @return
	 * @throws myException
	 */
	private boolean createNewMessage (String sTextPraefix) throws myException{
		boolean bRet = true;
		
		// User vorhanden: Message an eingetragene User verschicken
		MESSAGE_Handler oHandler = new MESSAGE_Handler(MESSAGE_CONST.MESSAGE_TYP_USER);
		
		for(MESSAGE_Entry oMessage : m_vMessageEntries){

			if (!bibALL.isEmpty(sTextPraefix) ){
				sTextPraefix += "\n" ;
				oMessage.set_Nachricht(sTextPraefix + oMessage.get_Nachricht());
			}
			
			bRet &= oHandler.saveMessage(oMessage);

		}
		
		return bRet;
	}
	
	
	/**
	 * Gibt alle generierten Meldungen der Prüfung als Vector<String> zurück
	 * @return
	 */
	public Vector<String> getMessageTexts(){
		Vector<String> vRet = new Vector<String>();
		
		for(MESSAGE_Entry oMessage : m_vMessageEntries){
			String s = oMessage.get_Titel() + "\n\n" + oMessage.get_Nachricht();
			vRet.add(s);
		}
		
		return vRet;
	}
	
	
	/**
	 * Ermittelt den Kreditbetrag zum aktuellen Zeitpunkt und gibt den Betrag zurück.
	 * Wenn null, dann kein Kreditvertrag-Eintrag vorhanden.
	 * 
	 * @param sIDAdresse
	 * @return
	 */
	private BigDecimal getKreditlimitInfos ( String sIDAdresse ){
		BigDecimal bdRet = null;
		
		GregorianCalendar cal = new GregorianCalendar();
		Date dtNow		= cal.getTime();
		
		Date dtBeginnKL = null;
		Date dtEndeKL	= null;
		
		KV_Info oKVInfo = new KV_Info();
		_vKVEntries =  oKVInfo.getKreditlimitsFor(sIDAdresse);
		if (_vKVEntries != null && _vKVEntries.size() > 0){
			bdRet = BigDecimal.ZERO;
			for (KV_Info_Entry o: _vKVEntries){
				// zum Kreditlimit kann nur gerechnet werden, wenn es aktuell gültig ist.
				dtBeginnKL = (o.get_Gueltig_ab() == null ? new GregorianCalendar(1000, 0, 1).getTime() : o.get_Gueltig_ab());
				dtEndeKL   = (o.get_Gueltig_bis() == null ? new GregorianCalendar(3000, 11, 31).getTime() : o.get_Gueltig_bis());
				
				// nur wenn der Betrag im Zeitraum liegt.
				if( dtNow.compareTo(dtBeginnKL) >=0 && dtNow.compareTo(dtEndeKL) <= 0  ){
					bdRet = bdRet.add(o.get_Betrag());
				}
				
			}
		}
		
		return bdRet;
	}
	
	
	/**
	 * Ermittelt die Forderungen (auch aus noch nicht gefahrenen Fuhren) die zur angegebenen Adresse gehören
	 * @param sIDAdresse
	 * @return
	 */
	private BigDecimal getForderungen(String sIDAdresse){
		BigDecimal bdRet = null;
		STATKD_DataRowStatus row = null;

		// ermitteln der Forderungen
		STATKD_StatusErmittlung oStatkd = new STATKD_StatusErmittlung();
		try {
			row = oStatkd.ErmittleKundenStatus_Forderungen(sIDAdresse);
			if (row != null){
				bdRet = row.get_Gesamt_FORDERUNG();
			}
			
		} catch (myException e) {
			e.printStackTrace();
		}

		return bdRet;
	}

	

	/**
	 * 2016-03-07: martin
	 * @return
	 */
	public RECORD_VPOS_TPA_FUHRE get_REC_FUHRE() {
		return own_REC_FUHRE;
	}


	public Vector<fehlerinfo> get_v_fehler() {
		return this.v_fehler;
	}

	
	public class fehlerinfo {
		public String id_adresse=null;
		public MyE2_String fehlertext=null;

		public fehlerinfo(String p_id_adresse, MyE2_String p_fehlertext) {
			super();
			this.id_adresse = p_id_adresse;
			this.fehlertext = p_fehlertext;
		}
	}
	
}
