package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.math.BigDecimal;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class FU__PruefeKorrektePreiseingabeImMoment_des_Preisabschlusses {

	private String 		cPreisInMaske = null;
	private boolean 	bPreisIstManuell = false;
	
	private Long      	lID_Angebot = null;
	private Long      	lID_Kontrakt = null;
	
	private String   	cFehlerMeldung = "";
	
    private boolean 	bEK = true; 
	
	public FU__PruefeKorrektePreiseingabeImMoment_des_Preisabschlusses(		String PreisInMaske, 
																			boolean PreisIstManuell, 
																			Long ID_Angebot,
																			Long ID_Kontrakt,
																			boolean EK) {
		super();
		this.cPreisInMaske = 		PreisInMaske;
		this.bPreisIstManuell = 	PreisIstManuell;
		this.lID_Angebot = 			ID_Angebot;
		this.lID_Kontrakt = 		ID_Kontrakt;
		this.bEK = 					EK;
		
		String Bezeichner = this.bEK?"Ladeseite":"Abladeseite";
		
		//zuerst den Preis in der Maske pruefen
		if (S.isEmpty(this.cPreisInMaske)) {
			return;       		//leere preis, wird anderweitig geprueft                          
		}
		
		MyBigDecimal  oBD = new MyBigDecimal(this.cPreisInMaske);
		
		if (oBD.get_bdWert() == null) {
			return;       		//falsche Zahl, wird anderweitig geprueft
		}
		
		if (this.bPreisIstManuell) {
			return;       		//dann gibt es einen manuellen, nicht leeren preis
		}
		
		try {
			
			if (this.lID_Kontrakt!=null) {
				RECORD_VPOS_KON recKON = new RECORD_VPOS_KON(this.lID_Kontrakt);
				
				BigDecimal  bdPreisKontrakt = recKON.get_EINZELPREIS_bdValue(null, 2);
				if (bdPreisKontrakt==null) {
					this.cFehlerMeldung=Bezeichner+": Der zugeordnete Kontrakt hat keinen Preis, deshalb kein Preisabschluss möglich (nur mit manuellem Preis) !";
				} else {
					if (bdPreisKontrakt.compareTo(oBD.get_bdWert().setScale(2, BigDecimal.ROUND_HALF_UP))!=0) {
						this.cFehlerMeldung=Bezeichner+": Der zugeordnete Kontraktpreis stimmt nicht mit dem Fuhren-Preis überein!";
					}
				}
			} else if (this.lID_Angebot!=null) {
					RECORD_VPOS_STD recAngebot = new RECORD_VPOS_STD(this.lID_Angebot);
					
					BigDecimal  bdPreisAngebot = recAngebot.get_EINZELPREIS_bdValue(null, 2);
					if (bdPreisAngebot==null) {
						this.cFehlerMeldung=Bezeichner+": Das zugeordnete Angebot hat keinen Preis, deshalb kein Preisabschluss möglich (nur mit manuellem Preis) !";
					} else {
						if (bdPreisAngebot.compareTo(oBD.get_bdWert().setScale(2, BigDecimal.ROUND_HALF_UP))!=0) {
							this.cFehlerMeldung=Bezeichner+": Der zugeordnete Angebotspreis stimmt nicht mit dem erfassten Preis überein!";
						}
					}
				
			} else {
				this.cFehlerMeldung=Bezeichner+": Der zugeordnete Angebotspreis stimmt nicht mit dem erfassten Preis überein!";				
			}
		} catch (myException e) {
			e.printStackTrace();
			this.cFehlerMeldung = Bezeichner+": Fehler bei der Prüfung der Richtigkeit des Preises!";
		}
		
		
		
		
	}
	
	public String get_cFehlerMeldung() {
		return this.cFehlerMeldung;
	}

	
	
	
	
}
