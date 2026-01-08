package rohstoff.Echo2BusinessLogic._TAX.PRUEFKLASSEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.__FS_Adress_Check;

/**
 * klasse zu pruefung, ob eine handelssituation erlaubt ist
 * Es wird geprueft: 
 * Kann ein Handelsgeschaeft stattfinden mit Firma, Ladeort, Sorte (RC in diesem Land)
 * Dazu werden hier 2 Dinge erledigt: 
 * 		1. Es wird geprueft, ob der Handel erlaubt ist (UST-ID-Sorten-Zusammenhang mit erfassten UST-ID und Land des Handels)
 * 		2. Es wird der vom Land abhaengige RC-Status der Sorte festgestellt
 * 		3. Es wird definiert, ob die Adresse als Firma oder als Privatadresse eingestuft wird
 * 
 * 
 * @author martin
 *
 */
public class Check__ObHandelErlaubt_Definiere_RC_Status_Sorte {

	//input-variable
	private RECORD_ADRESSE		recADRESSE_GEO = 	null;
	private RECORD_ADRESSE		recADRESSE_JUR = 		null;
	private RECORD_FIRMENINFO	recFI_JUR = 			null;
	private RECORD_ARTIKEL 		rec_ARTIKEL = 		null;
	private RECORD_LAND  		rec_LAND_GEO = 		null;
	private boolean 			bOnlyWarning = 		false;
	
	//ergebnis-variable
	private MyE2_MessageVector  oMV = 						new MyE2_MessageVector(); 		//messagevector gibt den fehlerstatus vor


	private boolean   			bIstFirma = 				false;
	private boolean   			bSorteIstRC = 				false;
	private boolean      		bSorteIstProdukt = 			false;
	private boolean   			bSorteIstEoW = 				false;
	private boolean      		bSorteIstDienstleistung = 	false;
	
	
	/**
	 * 
	 * @param id_ADRESSE_LIEF_UF (GEOGRAFISCHE ADRESSE!!)
	 * @param id_SORTE_UF
	 * @param onlyWarning
	 * @throws myException
	 */
	public Check__ObHandelErlaubt_Definiere_RC_Status_Sorte(String id_ADRESSE_LIEF_UF, String id_SORTE_UF, boolean onlyWarning) throws myException {
		super();
		this.__INIT(id_ADRESSE_LIEF_UF, id_SORTE_UF, onlyWarning);
	}
	
	
	/**
	 * 
	 * @param recFuhre
	 * @param bLadeSeite
	 * @param onlyWarning
	 * @throws myException
	 */
	public Check__ObHandelErlaubt_Definiere_RC_Status_Sorte(RECORD_VPOS_TPA_FUHRE recFuhre, boolean bLadeSeite, boolean onlyWarning) throws myException {
		super();
	
		if (S.isEmpty(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF_NN("")) || S.isEmpty(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF_NN(""))) {
			this.oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fuhre mit der ID: ",true,recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),false," hat noch keine vollständige Sortendefinition !",true)));
		} else {
		
		
			if (bLadeSeite) {
				this.__INIT(recFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN(""), 
							recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF_NN(""),onlyWarning);
			} else {
				this.__INIT(recFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN(""), 
						recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF_NN(""),onlyWarning);
			}
		}
	}

	

	/**
	 * 
	 * @param recFuhreOrt
	 * @param onlyWarning
	 * @throws myException
	 */
	public Check__ObHandelErlaubt_Definiere_RC_Status_Sorte(RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt, boolean onlyWarning) throws myException {
		super();
		if (S.isEmpty(recFuhreOrt.get_ID_ARTIKEL_BEZ_cUF_NN(""))) {
			this.oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fuhren-Zudatzort mit der ID: ",true,recFuhreOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),false," hat noch keine vollständige Sortendefinition !",true)));
		} else {
			this.__INIT(recFuhreOrt.get_ID_ADRESSE_LAGER_cUF_NN(""), 
					recFuhreOrt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF_NN(""),onlyWarning);
		}
	}


	
	
	
	
	private void __INIT(String id_ADRESSE_LIEF_UF, String id_SORTE_UF, boolean onlyWarning) throws myException {
		this.bOnlyWarning = onlyWarning;
		
		//pruefen der uebergabe
		MyLong  lID_ADRESSE_LIEF = 	new MyLong(id_ADRESSE_LIEF_UF);
		MyLong  lID_SORTE = 		new MyLong(id_SORTE_UF);
		
		if (!lID_ADRESSE_LIEF.get_bOK()) {
			throw new myException(this,"ADRESS-ID is not correct ...");
		}
		
		if (!lID_SORTE.get_bOK()) {
			throw new myException(this,"SORT-ID is not correct ...");
		}
		
		//als erstes die Records sammeln, die benoetigt werden
		this.recADRESSE_GEO = new RECORD_ADRESSE(lID_ADRESSE_LIEF.get_lValue());
		if (this.recADRESSE_GEO.get_ADRESSTYP_lValue(-999l).longValue()==myCONST.ADRESSTYP_FIRMENINFO) {
			this.recADRESSE_JUR = this.recADRESSE_GEO;
		} else {
			this.recADRESSE_JUR = this.recADRESSE_GEO.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
		}
		
		this.recFI_JUR = this.recADRESSE_JUR.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
		this.rec_ARTIKEL = new RECORD_ARTIKEL(lID_SORTE.get_lValue());
		
		
		//erster check: ist die Adresse ok ? damit werden deutsche Adressen bereits abschliessend bewertet
		__FS_Adress_Check  oCheckAdress = new __FS_Adress_Check(this.recADRESSE_JUR);
		

		oMV.add_MESSAGE(bOnlyWarning?oCheckAdress.get_MessagesOnLoad():oCheckAdress.get_Messages_4_Bewertung());
		
		if (oCheckAdress.get_Messages_4_Bewertung().get_bIsOK()) {
			
			//dann nachsehen, ob eine der Adresse (geographisch oder juristisch) im Ausland liegt
			String cID_LAND_HOMELAND = 	bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF_NN("");
			String cID_LAND_GEO = 		recADRESSE_GEO.get_ID_LAND_cUF_NN("");
			String cID_LAND_JUR= 		recADRESSE_JUR.get_ID_LAND_cUF_NN("");
			
			//sicherheitscheck
			if (S.isEmpty(cID_LAND_HOMELAND) || S.isEmpty(cID_LAND_GEO) || S.isEmpty(cID_LAND_JUR)) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine beteiligte Adresse oder der Mandant hat keinen korrekten Ländereintrag. Damit ist eine Bewertung unmöglich !!")));
				return;
			}
			
			this.rec_LAND_GEO = new RECORD_LAND(cID_LAND_GEO);
			
			this.bSorteIstDienstleistung = 	this.rec_ARTIKEL.is_DIENSTLEISTUNG_YES();
			this.bSorteIstProdukt = 		this.rec_ARTIKEL.is_IST_PRODUKT_YES();
			this.bSorteIstEoW = 			this.rec_ARTIKEL.is_END_OF_WASTE_YES();
			
			if (oCheckAdress.get_bAdresse_IST_PRIVAT()) {
				//ende, alles erlaubt
				//die sorte ist nach der definition rc, wenn im homeland rc, da privatgeschaeft immer im Inland stattfinden
				this.bSorteIstRC = new Check_RC_Status_Sorte(cID_LAND_HOMELAND, id_SORTE_UF).is_RC();
				this.bIstFirma = false;
				
			} else {
				
				this.bIstFirma = this.recFI_JUR.is_FIRMA_YES();

				//einstufung RC erfolgt ueber das jeweilige Handelsland 
				this.bSorteIstRC = new Check_RC_Status_Sorte(cID_LAND_GEO, id_SORTE_UF).is_RC();
				
				// weiterhin muss geprueft werden, ob die firma im geografischen land eine ust-id hat
				if (!(cID_LAND_GEO.equals(cID_LAND_HOMELAND) && cID_LAND_JUR.equals(cID_LAND_HOMELAND))) {
					MyE2_Message cFehlerMeldung = new Check_HatAdresseKorrekteUSTID(this.recADRESSE_JUR, this.rec_LAND_GEO, this.rec_ARTIKEL,this.bOnlyWarning).get_cErrorMessage();
					
					if (cFehlerMeldung != null) {
						oMV.add_MESSAGE(cFehlerMeldung);
					}
				}
			}
		}

	}

	
//	private MyE2_Message createMessage(MyE2_String cString) throws myException {
//		return new MyE2_Message(cString,this.bOnlyWarning?MyE2_Message.TYP_WARNING:MyE2_Message.TYP_ALARM, false);
//
//	}
	
	public boolean get_bIstFirma() {
		return bIstFirma;
	}


	public boolean get_bSorteIstRC() {
		return bSorteIstRC;
	}


	public boolean get_bSorteIstProdukt() {
		return bSorteIstProdukt;
	}

	public boolean get_bSorteIstEoW() {
		return bSorteIstEoW;
	}


	public boolean isbSorteIstDienstleistung() {
		return bSorteIstDienstleistung;
	}

	public MyE2_MessageVector get_oMV() {
		return oMV;
	}

	
}
