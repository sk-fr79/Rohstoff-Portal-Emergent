/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 12.08.2020
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.math.BigDecimal;
import java.util.Date;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT_STEUERVERMERK;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MWSTSCHLUESSEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_STEUERVERMERK;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.__FS_Adress_Check;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_mwstschluessel;

/**
 * @author martin
 * @date 12.08.2020
 *
 */
public class PdServiceErmittleSteuerAlgorithmisch {

	
	private Rec21_adresse   	adresseLagerQuelle = null;
	private Rec21_adresse   	adresseLagerZiel = null;
	
	private Rec21_artikel_bez  	artikelBezQuelle = null;
	private Rec21_artikel_bez  	artikelBezZiel = null;
	
	private Date         		leistungsDatumQuelle = 			null;
	private Date         		leistungsDatumZiel = 			null;
	
	private BigDecimal   		epreisQuelle = null;
	private BigDecimal   		epreisZiel = null;
	

	
	private __FS_Adress_Check 	oADC_EK =  null; 
	private __FS_Adress_Check 	oADC_VK =  null;

	private boolean 			bEK_STATION_IM_STAMMLAND_LIEFERANT = false;
	private boolean 			bVK_STATION_IM_STAMMLAND_ABNEHMER = false;
	
	
	private boolean 			isPreisQuellGroesserGleichNull = true;
	private boolean 			isPreisZielGroesserGleichNull = true;
	
	private boolean 			bEK_Sorte_ist_RC = false;
	private boolean 			bVK_Sorte_ist_RC = false;
	
	private boolean    			bRC_EKDatum_Gueltig = false;
	private boolean    			bRC_VKDatum_Gueltig = false;
	
	
	//neu: 2020-06-27: zwei steuersaetze wegen ausnahmen / zeitraeumen
	private BigDecimal  		bdStandardMwstEK = null;
	private BigDecimal  		bdStandardMwstVK = null;

	//rueckgabewerte
	private boolean 			isSteuerVermerkErmitteltQuelle = false;
	private boolean 			isSteuerVermerkErmitteltZiel = false;
	private boolean 			isSteuerSatzErmitteltQuelle = 	false;
	private boolean 			isSteuerSatzErmitteltZiel = 	false;
	
	private String  			steuerVermerkQuelle = 	null;
	private String  			steuerVermerkZiel = 	null;
	
	private BigDecimal  		steuersatzQuelle = 			null;
	private BigDecimal  		steuersatzZiel = 			null;

	//fehler-sammler, wenn etwas drinsteht, dann gescheitert !
	private VEK<MyString>       errorMessages = new   VEK<MyString>();      

	
	
	
	
	
	public PdServiceErmittleSteuerAlgorithmisch _initAndFindTax(	Rec21_adresse   	adresseLagerQuelle,  
																	Rec21_adresse   	adresseLagerZiel,
																	Rec21_artikel_bez  	artikelBezQuelle,
																	Rec21_artikel_bez  	artikelBezZiel,
																	Date          		leistungsDatumQuelle,
																	Date          		leistungsDatumZiel,
																	BigDecimal      	epreisQuelle,
																	BigDecimal      	epreisZiel) throws Exception {
		
		if (O.isOneNull(adresseLagerQuelle,  adresseLagerZiel,artikelBezQuelle,artikelBezZiel,leistungsDatumQuelle,leistungsDatumZiel,epreisQuelle,epreisZiel)) {
			throw new Exception("No null-values-allowed !! <741e8eaa-dca2-11ea-87d0-0242ac130003>");
		}
		
		this.adresseLagerQuelle = 	adresseLagerQuelle; 
		this.adresseLagerZiel = 	adresseLagerZiel; 
		this.artikelBezQuelle = 	artikelBezQuelle; 
		this.artikelBezZiel = 		artikelBezZiel; 
		this.leistungsDatumQuelle = leistungsDatumQuelle; 
		this.leistungsDatumZiel = 	leistungsDatumZiel; 
		this.epreisQuelle = 		epreisQuelle; 
		this.epreisZiel = 			epreisZiel; 
		
		
		//hier beginnt die bewertung
		this.bEK_STATION_IM_STAMMLAND_LIEFERANT = 	this.adresseLagerQuelle.getLongDbValue(ADRESSE.id_land).longValue()==this.adresseLagerQuelle._getMainAdresse().getLongDbValue(ADRESSE.id_land).longValue();
		
		DEBUG.println("Adresse-Ziel: ID: "+this.adresseLagerZiel.getId());
		
		this.bVK_STATION_IM_STAMMLAND_ABNEHMER = 	this.adresseLagerZiel.getLongDbValue(ADRESSE.id_land).longValue()==this.adresseLagerZiel._getMainAdresse().getLongDbValue(ADRESSE.id_land).longValue();
		
		
		//dann die Adress-Check-objekte fuellen
		this.oADC_EK =  new __FS_Adress_Check(new RECORD_ADRESSE(this.adresseLagerQuelle._getMainAdresse().getId())); 
		this.oADC_VK =  new __FS_Adress_Check(new RECORD_ADRESSE(this.adresseLagerZiel._getMainAdresse().getId()));

		if ((! oADC_EK.get_bADRESSE_IST_OK()) || (! oADC_VK.get_bADRESSE_IST_OK())) {
			errorMessages._a(S.ms("Steuerermittlung erst möglich wenn beteiligte Firmen korrekt erfasst sind !!!"));
			for (MyE2_Message m:oADC_EK.get_Messages_4_Bewertung() ) {
				errorMessages._a(m.get_cMessage());
			}
			for (MyE2_Message m:oADC_VK.get_Messages_4_Bewertung() ) {
				errorMessages._a(m.get_cMessage());
			}
		}
		if (errorMessages.size()>0) { return this; }

		
		Long idLandMandant =	bibALL.get_RECORD_MANDANT().get_longValue(MANDANT.id_land.fn());
		Long idMandant = 		bibALL.get_RECORD_MANDANT().get_longValue(MANDANT.id_mandant.fn());

		this.isPreisQuellGroesserGleichNull = (this.epreisQuelle.compareTo(BigDecimal.ZERO)>=0);
		this.isPreisZielGroesserGleichNull =  (this.epreisZiel.compareTo(BigDecimal.ZERO)>=0);
		

		//zuerst den rc-steuervermerk raussuchen
		RECLIST_MANDANT_STEUERVERMERK  recListRC_Vermerk_HOMELAND = 
			     new RECLIST_MANDANT_STEUERVERMERK(
			    		 "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT_STEUERVERMERK WHERE NVL(REVERSE_CHARGE,'N')='Y' " +
			    				" AND   ID_MANDANT="+idMandant+
			    		 		" AND   ID_LAND="+idLandMandant);
		
		
		
		//Standard-Steuersatz ermitteln
		RECLIST_MWSTSCHLUESSEL  recMWST = new RECLIST_MWSTSCHLUESSEL("SELECT * FROM "+bibE2.cTO()+"."+
				_DB.MWSTSCHLUESSEL+" WHERE "+
				"NVL("+_DB.MWSTSCHLUESSEL$IST_STANDARD+",'N')='Y' AND "+
				_DB.MWSTSCHLUESSEL$ID_LAND+"="+idLandMandant);
		
		if (recMWST.get_vKeyValues().size()!=1) {
			errorMessages._a(S.ms("Error: Bitte genau einen Standard-Steuersatz für nicht RC-Sorten erfassen !!"));
		} 
		
		if (errorMessages.size()>0) { return this; }

		
		//jetzt evtl. ausnahmen beruecksichtigen
		Rec21_mwstschluessel rec21Mwst = (Rec21_mwstschluessel)new Rec21_mwstschluessel()._fill_id(recMWST.get(0).get_ID_MWSTSCHLUESSEL_lValue(null));
		this.bdStandardMwstEK = rec21Mwst.getSteuersatz(leistungsDatumQuelle);
		this.bdStandardMwstVK = rec21Mwst.getSteuersatz(leistungsDatumZiel);
		
		
		RECORD_MANDANT_STEUERVERMERK   recRC_HOMELAND = null;
		if (recListRC_Vermerk_HOMELAND.get_vKeyValues().size()!=1)	{
			errorMessages._a(S.ms("Error: Bitte genau einen Reverse-Charge-Steuervermerk pro Land und Mandant erfassen !!"));
		} else if (recListRC_Vermerk_HOMELAND.get_vKeyValues().size()==1)	{
			recRC_HOMELAND = recListRC_Vermerk_HOMELAND.get(0);
		}
		
		if (errorMessages.size()>0) { return this; }
		
		
		//sind sorten in D reverse-Charge
		this.bEK_Sorte_ist_RC = this.artikelBezQuelle.__get_rec21_artikel().isReverseChargeInZolltarifnummer();
		this.bVK_Sorte_ist_RC = this.artikelBezZiel.__get_rec21_artikel().isReverseChargeInZolltarifnummer();
		
		
		MyDate RC_Gueltig_Ab = new MyDate(recRC_HOMELAND.get_GUELTIG_AB_cF_NN("31.12.2199"));
		
		//jetzt nachsehen, ob das leistungsdatum >= dem startdatum des RC-Vermerks ist
		if ( this.leistungsDatumQuelle.after(RC_Gueltig_Ab.getDate()) || leistungsDatumQuelle.equals(RC_Gueltig_Ab.getDate())) {
			this.bRC_EKDatum_Gueltig = true;
		}
		if (this.leistungsDatumZiel.after(RC_Gueltig_Ab.getDate()) || leistungsDatumZiel.equals(RC_Gueltig_Ab.getDate())) 	{
			this.bRC_VKDatum_Gueltig = true;
		}

		
		//2013-12-06: falls lieferung von deutschland/deutschland nach firma ins eu-ausland (land geo=land jur) dann innergemeinschaftliche lieferung
		if (this.oADC_EK.get_bADRESSE_IST_FIRMA_HOMELAND() && this.bEK_STATION_IM_STAMMLAND_LIEFERANT) {
			if ( 	this.oADC_VK.get_bADRESSE_IST_FIRMA_EU() && 
					!this.oADC_VK.get_bADRESSE_IST_FIRMA_HOMELAND() &&
					this.bVK_STATION_IM_STAMMLAND_ABNEHMER) {
					
					this.steuerVermerkZiel = bibALL.get_RECORD_MANDANT().get_EU_STEUER_VERMERK_cUF_NN("");
					this.isSteuerVermerkErmitteltZiel=true;
					this.steuersatzZiel = new BigDecimal(0);
					this.isSteuerSatzErmitteltZiel=true;
			}
		}
		
		
		//2013-12-06: falls lieferung von firma aus eu-ausland (land geo=land jur) nach deutschland/deutschland dann innergemeinschaftliche lieferung
		if (this.oADC_VK.get_bADRESSE_IST_FIRMA_HOMELAND() && this.bVK_STATION_IM_STAMMLAND_ABNEHMER) {
			if ( 	this.oADC_EK.get_bADRESSE_IST_FIRMA_EU() && 
					!this.oADC_EK.get_bADRESSE_IST_FIRMA_HOMELAND() &&
					this.bEK_STATION_IM_STAMMLAND_LIEFERANT) {
				
					this.steuerVermerkQuelle = bibALL.get_RECORD_MANDANT().get_EU_STEUER_VERMERK_cUF_NN("");
					this.isSteuerVermerkErmitteltQuelle=true;
					this.steuersatzQuelle = new BigDecimal(0);
					this.isSteuerSatzErmitteltQuelle=true;
			}
		}
		
		//2013-12-06: falls lieferung von deutschland/deutschland nach firma ins nicht-eu-ausland (land geo=land jur) dann aussenlieferung
		if (this.oADC_EK.get_bADRESSE_IST_FIRMA_HOMELAND() && this.bEK_STATION_IM_STAMMLAND_LIEFERANT) {
			if ( 	this.oADC_VK.get_bADRESSE_IST_FIRMA_EX_EU() && 
					this.bVK_STATION_IM_STAMMLAND_ABNEHMER) {
				
				this.steuerVermerkZiel = bibALL.get_RECORD_MANDANT().get_AUSSEN_STEUER_VERMERK_cF_NN("");
				this.isSteuerVermerkErmitteltZiel=true;
				this.steuersatzZiel = new BigDecimal(0);
				this.isSteuerSatzErmitteltZiel=true;
			}
		}
		
		
		//2010-12-30: reverse-charge (mandant wird dabei erstmal behandelt wie lieferant, spaeter durch dummy-werte ueberschrieben)
		//hier reversecharge pruefen, falls zutreffend, dann die vorigen werte ueberschreiben
		//zuerst EK
		if ( oADC_EK.get_bADRESSE_IST_FIRMA_HOMELAND() && this.bEK_STATION_IM_STAMMLAND_LIEFERANT) { 
			if (this.bVK_STATION_IM_STAMMLAND_ABNEHMER) {
				if (this.isPreisQuellGroesserGleichNull && this.bRC_EKDatum_Gueltig) {
					if (this.bEK_Sorte_ist_RC) {
						this.steuersatzQuelle = recRC_HOMELAND.get_STEUERSATZ_bdValue(new BigDecimal(0));
						this.isSteuerSatzErmitteltQuelle=true;
						this.steuerVermerkQuelle = recRC_HOMELAND.get_STEUERVERMERK_GUTSCHRIFT_cUF_NN("<Steuervermerk>");
						this.isSteuerVermerkErmitteltQuelle=true;
					} else {
						
						//bei nicht RC-Sorten gilt standard-satz
						//this.bdRUECK_MWST_EK = this.bdStandardWMST;
						//20200627: 
						this.steuersatzQuelle = this.bdStandardMwstEK;
						
						this.isSteuerSatzErmitteltQuelle=true;
						this.steuerVermerkQuelle = FU___CONST.EU_STEUERVERMERK_LEER;
						this.isSteuerVermerkErmitteltQuelle=true;
					}
				}
			}
		}
	
		//dann VK
		if ( oADC_VK.get_bADRESSE_IST_FIRMA_HOMELAND() && this.bVK_STATION_IM_STAMMLAND_ABNEHMER) { 
			if (this.bEK_STATION_IM_STAMMLAND_LIEFERANT) {
				if (this.isPreisZielGroesserGleichNull && this.bRC_VKDatum_Gueltig) {
					if (this.bVK_Sorte_ist_RC) {
						this.steuersatzZiel = recRC_HOMELAND.get_STEUERSATZ_bdValue(new BigDecimal(0));
						this.isSteuerSatzErmitteltZiel=true;
						this.steuerVermerkZiel = recRC_HOMELAND.get_STEUERVERMERK_RECHNUNG_cUF_NN("<Steuervermerk>");
						this.isSteuerVermerkErmitteltZiel=true;
					} else {
						
						//this.bdRUECK_MWST_VK = this.bdStandardWMST;
						//20200627
						this.steuersatzZiel = this.bdStandardMwstVK;
						
						this.isSteuerSatzErmitteltZiel=true;
						this.steuerVermerkZiel =  FU___CONST.EU_STEUERVERMERK_LEER;
						this.isSteuerVermerkErmitteltZiel=true;
					}
				}
			}
		}
	
		

		/*
		 * privater lieferant
		 */
		if (this.oADC_EK.get_bAdresse_IST_PRIVAT()) {
			if (this.isPreisQuellGroesserGleichNull) {
				this.steuersatzQuelle = BigDecimal.ZERO;
			} else {
				this.steuersatzQuelle = this.bdStandardMwstEK;
			}
			this.isSteuerSatzErmitteltQuelle=true;
			this.steuerVermerkQuelle = FU___CONST.EU_STEUERVERMERK_LEER;
			this.isSteuerVermerkErmitteltQuelle=true;
		}
		
		/*
		 * privater abnehmer
		 */
		if (this.oADC_VK.get_bAdresse_IST_PRIVAT()) {
			if (this.isPreisZielGroesserGleichNull) {
				this.steuersatzZiel = this.bdStandardMwstVK;
			} else {
				this.steuersatzZiel = BigDecimal.ZERO;
			}
			this.isSteuerSatzErmitteltZiel=true;
			this.steuerVermerkZiel = FU___CONST.EU_STEUERVERMERK_LEER;
			this.isSteuerVermerkErmitteltZiel=true;
		}
		
		
		//jetzt noch global werte besetzen, wenn es der mandant ist (an einem ort)
		if (this.adresseLagerQuelle.isAdressOfMandant()) {
			this.steuersatzQuelle = new BigDecimal(0);
			this.isSteuerSatzErmitteltQuelle=true;
			this.steuerVermerkQuelle = FU___CONST.EU_STEUERVERMERK_LAGER;
			this.isSteuerVermerkErmitteltQuelle=true;
		}

		if (this.adresseLagerZiel.isAdressOfMandant()) {
			this.steuersatzZiel = new BigDecimal(0);
			this.isSteuerSatzErmitteltZiel=true;
			this.steuerVermerkZiel = FU___CONST.EU_STEUERVERMERK_LAGER;
			this.isSteuerVermerkErmitteltZiel=true;
		}
		return this;
	}


	/**
	 * @return the isSteuerVermerkErmitteltQuelle
	 */
	public boolean isSteuerVermerkErmitteltQuelle() {
		return isSteuerVermerkErmitteltQuelle;
	}


	/**
	 * @return the isSteuerVermerkErmitteltZiel
	 */
	public boolean isSteuerVermerkErmitteltZiel() {
		return isSteuerVermerkErmitteltZiel;
	}

	/**
	 * @return the isSteuerSatzErmitteltQuelle
	 */
	public boolean isSteuerSatzErmitteltQuelle() {
		return isSteuerSatzErmitteltQuelle;
	}

	/**
	 * @return the isSteuerSatzErmitteltZiel
	 */
	public boolean isSteuerSatzErmitteltZiel() {
		return isSteuerSatzErmitteltZiel;
	}

	/**
	 * @return the steuerVermerkQuelle
	 */
	public String getSteuerVermerkQuelle() {
		return steuerVermerkQuelle;
	}

	/**
	 * @return the steuerVernerkZiel
	 */
	public String getSteuerVermerkZiel() {
		return steuerVermerkZiel;
	}


	/**
	 * @return the steuersatzQuelle
	 */
	public BigDecimal getSteuersatzQuelle() {
		return steuersatzQuelle;
	}


	/**
	 * @return the steuersatzZiel
	 */
	public BigDecimal getSteuersatzZiel() {
		return steuersatzZiel;
	}

	/**
	 * @return the errorMessages
	 */
	public VEK<MyString> getErrorMessages() {
		return errorMessages;
	}
	
}
