package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KUNDE_MWST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT_STEUERVERMERK;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MWSTSCHLUESSEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KUNDE_MWST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_STEUERVERMERK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MWSTSCHLUESSEL;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.__FS_Adress_Check;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_mwstschluessel;

public class FU___ERMITTLE_STEUERVERMERK_UND_MWST
{
	
	private RECORD_ADRESSE  recADRESSE_LAGER_START = null;
	private RECORD_ADRESSE  recADRESSE_LAGER_ZIEL = null;
	private RECORD_ADRESSE  recADRESSE_BASIS_START = null;
	private RECORD_ADRESSE  recADRESSE_BASIS_ZIEL = null;
	
	private RECORD_ARTIKEL  recArtikelEK = null;
	private RECORD_ARTIKEL  recArtikelVK = null;
	
	private boolean 		bSteuerVermerkErmitteltEK = false;
	private boolean 		bSteuerVermerkErmitteltVK = false;
	private boolean 		bSteuerSatzErmitteltEK = 	false;
	private boolean 		bSteuerSatzErmitteltVK = 	false;
	
	private String  		cRUECK_STEUERVERMERK_EK = 	null;
	private String  		cRUECK_STEUERVERMERK_VK = 	null;
	
	private BigDecimal  	bdRUECK_MWST_EK = 			null;
	private BigDecimal  	bdRUECK_MWST_VK = 			null;
	
	private Integer        intNummerInDropDownEK =    	new Integer(0);    //nummer des dropdown-Eintrags in select-field
	private Integer        intNummerInDropDownVK = 		new Integer(0);    //nummer des dropdown-Eintrags in select-field
	
	private MyDate         oLeistungsdatumEK = 			null;
	private MyDate         oLeistungsdatumVK = 			null;
	
	//2013-12-06: steuerermittlung nur noch in den (hoffentlich) 100% korrekten faellen
	private boolean    	 	bALTE_STEUERERMITTLUNG_TOETEN = true; 
	private __FS_Adress_Check 	oADC_EK =  null; 
	private __FS_Adress_Check 	oADC_VK =  null;

	private boolean 			bEK_STATION_IM_STAMMLAND_LIEFERANT = false;
	private boolean 			bVK_STATION_IM_STAMMLAND_ABNEHMER = false;
	
	
	private BigDecimal   		EPREIS_EK = null;
	private BigDecimal   		EPREIS_VK = null;
	
	
	private boolean 			bEKPreisGroesserGleichNull = true;
	private boolean 			bVKPreisGroesserGleichNull = true;

	
	private boolean 			bEK_Sorte_ist_RC = false;
	private boolean 			bVK_Sorte_ist_RC = false;
	
	private boolean    			bRC_EKDatum_Gueltig = false;
	private boolean    			bRC_VKDatum_Gueltig = false;
	
	//private BigDecimal  		bdStandardWMST = null;
	
	//neu: 2020-06-27: zwei steuersaetze wegen ausnahmen / zeitraeumen
	private BigDecimal  		bdStandardMwstEK = null;
	private BigDecimal  		bdStandardMwstVK = null;
	
	
	
	public FU___ERMITTLE_STEUERVERMERK_UND_MWST(	RECORD_ADRESSE  RecADRESSE_LAGER_START,  
													RECORD_ADRESSE  RecADRESSE_LAGER_ZIEL,
													RECORD_ARTIKEL  rec_ArtikelEK,
													RECORD_ARTIKEL  rec_ArtikelVK,
													String          cLeistungsDatumFormatiert_EK,
													String          cLeistungsDatumFormatiert_VK,
													BigDecimal      bdEPREIS_EK,
													BigDecimal      bdEPREIS_VK
													) throws myException
	{
		super();

		this.recADRESSE_LAGER_START = RecADRESSE_LAGER_START;
		this.recADRESSE_LAGER_ZIEL = RecADRESSE_LAGER_ZIEL;
		
		this.recArtikelEK = rec_ArtikelEK;
		this.recArtikelVK = rec_ArtikelVK;
		
		this.oLeistungsdatumEK = new MyDate(cLeistungsDatumFormatiert_EK);
		this.oLeistungsdatumVK = new MyDate(cLeistungsDatumFormatiert_VK);
		
		
		this.EPREIS_EK = bdEPREIS_EK;
		this.EPREIS_VK = bdEPREIS_VK;
		
		if (	this.recADRESSE_LAGER_START==null || 
				this.recADRESSE_LAGER_ZIEL==null || 
				this.recArtikelEK==null || 
				this.recArtikelVK==null || 
				(!oLeistungsdatumEK.get_cErrorCODE().equals(MyDate.ALL_OK)) || 
				(!oLeistungsdatumVK.get_cErrorCODE().equals(MyDate.ALL_OK)))       //gar nix machen
		{
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Steuerermittlung erst moeglich, wenn Sortenbezeichner und Adressen, sowie Lade- und Abladedatum von allen Fuhren/Fuhrenorten vorhanden sind !!!"));
			return;
		}
		
		if (this.recADRESSE_LAGER_START.get_ADRESSTYP_lValue(null)==myCONST.ADRESSTYP_FIRMENINFO)	{
			this.recADRESSE_BASIS_START = recADRESSE_LAGER_START;
		} else {
			this.recADRESSE_BASIS_START = recADRESSE_LAGER_START.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
		}
		
		if (this.recADRESSE_LAGER_ZIEL.get_ADRESSTYP_lValue(null)==myCONST.ADRESSTYP_FIRMENINFO) {
			this.recADRESSE_BASIS_ZIEL = recADRESSE_LAGER_ZIEL;
		} else {
			this.recADRESSE_BASIS_ZIEL = recADRESSE_LAGER_ZIEL.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
		}

		this.bEK_STATION_IM_STAMMLAND_LIEFERANT = (this.recADRESSE_BASIS_START.get_ID_LAND_cUF_NN("@").equals(this.recADRESSE_LAGER_START.get_ID_LAND_cUF_NN("@@")));
		this.bVK_STATION_IM_STAMMLAND_ABNEHMER = (this.recADRESSE_BASIS_ZIEL.get_ID_LAND_cUF_NN("@").equals(this.recADRESSE_LAGER_ZIEL.get_ID_LAND_cUF_NN("@@")));
		
		
		
		//dann die Adress-Check-objekte fuellen
		this.oADC_EK =  new __FS_Adress_Check(this.recADRESSE_BASIS_START); 
		this.oADC_VK =  new __FS_Adress_Check(this.recADRESSE_BASIS_ZIEL);

		
		if ((! oADC_EK.get_bADRESSE_IST_OK()) || (! oADC_VK.get_bADRESSE_IST_OK())) {
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Steuerermittlung erst möglich wenn beteiligte Firmen korrekt erfasst sind !!!"));
			bibMSG.add_MESSAGE(oADC_EK.get_Messages_4_Bewertung());
			bibMSG.add_MESSAGE(oADC_VK.get_Messages_4_Bewertung());
			return;
		}
		
		_ermittleWerte();

	}
	
	
	

	
	
	
	/**
	 * 2013-12-11: komplett umdefiniert
	 * @throws myException
	 */
	private void _ermittleWerte() throws myException
	{
		
		String cID_LAND_MANDANT = bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF_NN("");
		String cID_MANDANT = bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF_NN("");
		//RECORD_LAND recLandMandant = new RECORD_LAND(cID_LAND_MANDANT);

		if (! this.bALTE_STEUERERMITTLUNG_TOETEN) {
			this.alte_steuer_ermittlung_vor_2013_12_06();			
		}

		
		if (this.EPREIS_EK!=null) {
			this.bEKPreisGroesserGleichNull = (this.EPREIS_EK.compareTo(BigDecimal.ZERO)>=0);
		}
		if (this.EPREIS_VK!=null) {
			this.bVKPreisGroesserGleichNull = (this.EPREIS_VK.compareTo(BigDecimal.ZERO)>=0);
		}
		

		//zuerst den rc-steuervermerk raussuchen
		RECLIST_MANDANT_STEUERVERMERK  recListRC_Vermerk_HOMELAND = 
			     new RECLIST_MANDANT_STEUERVERMERK(
			    		 "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT_STEUERVERMERK WHERE NVL(REVERSE_CHARGE,'N')='Y' " +
			    				" AND   ID_MANDANT="+cID_MANDANT+
			    		 		" AND   ID_LAND="+cID_LAND_MANDANT);
		
		RECORD_MANDANT_STEUERVERMERK   recRC_HOMELAND = null;
		
		
		//Standard-Steuersatz ermitteln
		RECLIST_MWSTSCHLUESSEL  recMWST = new RECLIST_MWSTSCHLUESSEL("SELECT * FROM "+bibE2.cTO()+"."+
				_DB.MWSTSCHLUESSEL+" WHERE "+
				"NVL("+_DB.MWSTSCHLUESSEL$IST_STANDARD+",'N')='Y' AND "+
				_DB.MWSTSCHLUESSEL$ID_LAND+"="+cID_LAND_MANDANT);
		
		if (recMWST.get_vKeyValues().size()!=1) {
			throw new myException("Error: Bitte genau einen Standard-Steuersatz für nicht RC-Sorten erfassen !!");
		} 
		
		//this.bdStandardWMST = recMWST.get(0).get_STEUERSATZ_bdValue(BigDecimal.ZERO);
		
		//jetzt evtl. ausnahmen beruecksichtigen
		Rec21_mwstschluessel rec21Mwst = (Rec21_mwstschluessel)new Rec21_mwstschluessel()._fill_id(recMWST.get(0).get_ID_MWSTSCHLUESSEL_lValue(null));
		this.bdStandardMwstEK = rec21Mwst.getSteuersatz(oLeistungsdatumEK.getDate());
		this.bdStandardMwstVK = rec21Mwst.getSteuersatz(oLeistungsdatumVK.getDate());
		
		
		if (recListRC_Vermerk_HOMELAND.get_vKeyValues().size()!=1)	{
			throw new myException("Error: Bitte genau einen Reverse-Charge-Steuervermerk pro Land und Mandant erfassen !!");
		} else if (recListRC_Vermerk_HOMELAND.get_vKeyValues().size()==1)	{
			recRC_HOMELAND = recListRC_Vermerk_HOMELAND.get(0);
		}


		//sind sorten in D reverse-Charge
		if (this.recArtikelEK.get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer()!=null) {
			if (this.recArtikelEK.get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer().is_REVERSE_CHARGE_YES()) {
				this.bEK_Sorte_ist_RC = true;
			}
		}
		if (this.recArtikelVK.get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer()!=null) {
			if (this.recArtikelVK.get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer().is_REVERSE_CHARGE_YES()) {
				this.bVK_Sorte_ist_RC = true;
			}
		}
		
		
		MyDate RC_Gueltig_Ab = new MyDate(recRC_HOMELAND.get_GUELTIG_AB_cF_NN("31.12.2199"));
		
		//jetzt nachsehen, ob das leistungsdatum >= dem startdatum des RC-Vermerks ist
		if (this.oLeistungsdatumEK.get_cDBFormatErgebnis().compareTo(RC_Gueltig_Ab.get_cDBFormatErgebnis())>=0) 	{
			this.bRC_EKDatum_Gueltig = true;
		}
		if (this.oLeistungsdatumVK.get_cDBFormatErgebnis().compareTo(RC_Gueltig_Ab.get_cDBFormatErgebnis())>=0) 	{
			this.bRC_VKDatum_Gueltig = true;
		}

		
		
		
		
		
		//2013-12-06: falls lieferung von deutschland/deutschland nach firma ins eu-ausland (land geo=land jur) dann innergemeinschaftliche lieferung
		if (this.oADC_EK.get_bADRESSE_IST_FIRMA_HOMELAND() && this.bEK_STATION_IM_STAMMLAND_LIEFERANT) {
			if ( 	this.oADC_VK.get_bADRESSE_IST_FIRMA_EU() && 
					!this.oADC_VK.get_bADRESSE_IST_FIRMA_HOMELAND() &&
					this.bVK_STATION_IM_STAMMLAND_ABNEHMER) {
					
					this.cRUECK_STEUERVERMERK_VK = bibALL.get_RECORD_MANDANT().get_EU_STEUER_VERMERK_cUF_NN("");
					this.bSteuerVermerkErmitteltVK=true;
					this.bdRUECK_MWST_VK = new BigDecimal(0);
					this.bSteuerSatzErmitteltVK=true;
					this.intNummerInDropDownVK = new Integer(0);
			}
		}
		
		
		//2013-12-06: falls lieferung von firma aus eu-ausland (land geo=land jur) nach deutschland/deutschland dann innergemeinschaftliche lieferung
		if (this.oADC_VK.get_bADRESSE_IST_FIRMA_HOMELAND() && this.bVK_STATION_IM_STAMMLAND_ABNEHMER) {
			if ( 	this.oADC_EK.get_bADRESSE_IST_FIRMA_EU() && 
					!this.oADC_EK.get_bADRESSE_IST_FIRMA_HOMELAND() &&
					this.bEK_STATION_IM_STAMMLAND_LIEFERANT) {
				
					this.cRUECK_STEUERVERMERK_EK = bibALL.get_RECORD_MANDANT().get_EU_STEUER_VERMERK_cUF_NN("");
					this.bSteuerVermerkErmitteltEK=true;
					this.bdRUECK_MWST_EK = new BigDecimal(0);
					this.bSteuerSatzErmitteltEK=true;
					this.intNummerInDropDownEK = new Integer(0);

			}
		}
		
	
		
		
		//2013-12-06: falls lieferung von deutschland/deutschland nach firma ins nicht-eu-ausland (land geo=land jur) dann aussenlieferung
		if (this.oADC_EK.get_bADRESSE_IST_FIRMA_HOMELAND() && this.bEK_STATION_IM_STAMMLAND_LIEFERANT) {
			if ( 	this.oADC_VK.get_bADRESSE_IST_FIRMA_EX_EU() && 
					this.bVK_STATION_IM_STAMMLAND_ABNEHMER) {
				
				this.cRUECK_STEUERVERMERK_VK = bibALL.get_RECORD_MANDANT().get_AUSSEN_STEUER_VERMERK_cF_NN("");
				this.bSteuerVermerkErmitteltVK=true;
				this.bdRUECK_MWST_VK = new BigDecimal(0);
				this.bSteuerSatzErmitteltVK=true;
				this.intNummerInDropDownVK = new Integer(0);
			}
		}
		

/*
 *status "steuerfreie Einfuhr" ist unklar, 2013-12-11 		
 */
//		//2013-12-06: falls lieferung von firma aus nicht-eu-ausland (land geo=land jur) nach deutschland/deutschland dann aussenlieferung
//		if (this.oADC_VK.get_bADRESSE_IST_FIRMA_HOMELAND() && this.bVK_STATION_IM_STAMMLAND_ABNEHMER) {
//			if ( 	this.oADC_EK.get_bADRESSE_IST_FIRMA_EX_EU() && 
//					this.bEK_STATION_IM_STAMMLAND_LIEFERANT) {
//				
//				this.cRUECK_STEUERVERMERK_EK = bibALL.get_RECORD_MANDANT().get_AUSSEN_STEUER_VERMERK_cF_NN("");
//				this.bSteuerVermerkErmitteltEK=true;
//				this.bdRUECK_MWST_EK = new BigDecimal(0);
//				this.bSteuerSatzErmitteltEK=true;
//
//				this.intNummerInDropDownEK = new Integer(0);
//			}
//		}
//		
		
		
		
		
		//2010-12-30: reverse-charge (mandant wird dabei erstmal behandelt wie lieferant, spaeter durch dummy-werte ueberschrieben)
		//hier reversecharge pruefen, falls zutreffend, dann die vorigen werte ueberschreiben
		//zuerst EK
		if ( oADC_EK.get_bADRESSE_IST_FIRMA_HOMELAND() && this.bEK_STATION_IM_STAMMLAND_LIEFERANT) { 
			if (this.bVK_STATION_IM_STAMMLAND_ABNEHMER) {
				if (this.bEKPreisGroesserGleichNull && this.bRC_EKDatum_Gueltig) {
					if (this.bEK_Sorte_ist_RC) {
						this.bdRUECK_MWST_EK = recRC_HOMELAND.get_STEUERSATZ_bdValue(new BigDecimal(0));
						this.bSteuerSatzErmitteltEK=true;
						this.cRUECK_STEUERVERMERK_EK = recRC_HOMELAND.get_STEUERVERMERK_GUTSCHRIFT_cUF_NN("<Steuervermerk>");
						this.bSteuerVermerkErmitteltEK=true;
	
						this.intNummerInDropDownEK = new Integer(0);
					} else {
						
						//bei nicht RC-Sorten gilt standard-satz
						//this.bdRUECK_MWST_EK = this.bdStandardWMST;
						//20200627: 
						this.bdRUECK_MWST_EK = this.bdStandardMwstEK;
						
						this.bSteuerSatzErmitteltEK=true;
						this.cRUECK_STEUERVERMERK_EK = FU___CONST.EU_STEUERVERMERK_LEER;
						this.bSteuerVermerkErmitteltEK=true;
	
						this.intNummerInDropDownEK = new Integer(0);
					}
				}
			}
		}
	
		//dann VK
		if ( oADC_VK.get_bADRESSE_IST_FIRMA_HOMELAND() && this.bVK_STATION_IM_STAMMLAND_ABNEHMER) { 
			if (this.bEK_STATION_IM_STAMMLAND_LIEFERANT) {
				if (this.bVKPreisGroesserGleichNull && this.bRC_VKDatum_Gueltig) {
					if (this.bVK_Sorte_ist_RC) {
						this.bdRUECK_MWST_VK = recRC_HOMELAND.get_STEUERSATZ_bdValue(new BigDecimal(0));
						this.bSteuerSatzErmitteltVK=true;
						this.cRUECK_STEUERVERMERK_VK = recRC_HOMELAND.get_STEUERVERMERK_RECHNUNG_cUF_NN("<Steuervermerk>");
						this.bSteuerVermerkErmitteltVK=true;
	
						this.intNummerInDropDownVK = new Integer(0);
					} else {
						
						//this.bdRUECK_MWST_VK = this.bdStandardWMST;
						//20200627
						this.bdRUECK_MWST_VK = this.bdStandardMwstVK;
						
						this.bSteuerSatzErmitteltVK=true;
						this.cRUECK_STEUERVERMERK_VK =  FU___CONST.EU_STEUERVERMERK_LEER;
						this.bSteuerVermerkErmitteltVK=true;
	
						this.intNummerInDropDownVK = new Integer(0);
					}
				}
			}
		}
	
		

		/*
		 * privater lieferant
		 */
		if (this.oADC_EK.get_bAdresse_IST_PRIVAT()) {
			//jetzt private sachverhalte entwickeln
			//BigDecimal bdSteuerSatzPrivatGeldEingang = null;
			//BigDecimal bdSteuerSatzPrivatGeldAusgang = null;
			
			//standard-steuersatz muss eindeutig sein
			//bdSteuerSatzPrivatGeldEingang = this.bdStandardWMST;  //recMWST.get(0).get_STEUERSATZ_bdValue(BigDecimal.ZERO);
			
			//20200627:
			//bdSteuerSatzPrivatGeldEingang = this.bdStandardMwstEK;
			
			//bdSteuerSatzPrivatGeldAusgang = BigDecimal.ZERO;
				
			if (this.bEKPreisGroesserGleichNull) {
				this.bdRUECK_MWST_EK = BigDecimal.ZERO;
			} else {
				this.bdRUECK_MWST_EK = this.bdStandardMwstEK;
			}
			this.bSteuerSatzErmitteltEK=true;
			this.cRUECK_STEUERVERMERK_EK = FU___CONST.EU_STEUERVERMERK_LEER;
			this.bSteuerVermerkErmitteltEK=true;

			this.intNummerInDropDownEK = new Integer(0);

		}
		
		/*
		 * privater abnehmer
		 */
		if (this.oADC_VK.get_bAdresse_IST_PRIVAT()) {
			//jetzt private sachverhalte entwickeln
//			BigDecimal bdSteuerSatzPrivatGeldEingang = null;
//			BigDecimal bdSteuerSatzPrivatGeldAusgang = null;
			
			//standard-steuersatz ist eindeutig
			//bdSteuerSatzPrivatGeldEingang = this.bdStandardWMST; //recMWST.get(0).get_STEUERSATZ_bdValue(BigDecimal.ZERO);
//			bdSteuerSatzPrivatGeldEingang = this.bdStandardMwstVK;
//			bdSteuerSatzPrivatGeldAusgang = BigDecimal.ZERO;
			
			if (this.bVKPreisGroesserGleichNull) {
				this.bdRUECK_MWST_VK = this.bdStandardMwstVK;
			} else {
				this.bdRUECK_MWST_VK = BigDecimal.ZERO;
			}
			this.bSteuerSatzErmitteltVK=true;
			this.cRUECK_STEUERVERMERK_VK = FU___CONST.EU_STEUERVERMERK_LEER;
			this.bSteuerVermerkErmitteltVK=true;

			this.intNummerInDropDownVK = new Integer(0);

		}
		
		
		
		
		
		//jetzt noch global werte besetzen, wenn es der mandant ist (an einem ort)
		if (this.recADRESSE_BASIS_START.get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()))
		{
			this.bdRUECK_MWST_EK = new BigDecimal(0);
			this.bSteuerSatzErmitteltEK=true;
			this.cRUECK_STEUERVERMERK_EK = FU___CONST.EU_STEUERVERMERK_LAGER;
			this.bSteuerVermerkErmitteltEK=true;

			this.intNummerInDropDownEK = new Integer(0);
		}

		if (this.recADRESSE_BASIS_ZIEL.get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()))
		{
			this.bdRUECK_MWST_VK = new BigDecimal(0);
			this.bSteuerSatzErmitteltVK=true;
			this.cRUECK_STEUERVERMERK_VK = FU___CONST.EU_STEUERVERMERK_LAGER;
			this.bSteuerVermerkErmitteltVK=true;

			this.intNummerInDropDownVK = new Integer(0);
		}

		
		
	}
	
	
	

	public boolean get_bSteuerVermerkErmittelt_EK()
	{
		return this.bSteuerVermerkErmitteltEK;
	}
	public boolean get_bSteuerVermerkErmittelt_VK()
	{
		return this.bSteuerVermerkErmitteltVK;
	}
	
	public boolean get_bSteuerSatzErmittelt_EK()
	{
		return this.bSteuerSatzErmitteltEK;
	}
	public boolean get_bSteuerSatzErmittelt_VK()
	{
		return this.bSteuerSatzErmitteltVK;
	}

	public String get_cRUECK_STEUERVERMERK_EK()
	{
		return this.cRUECK_STEUERVERMERK_EK;
	}
	public String get_cRUECK_STEUERVERMERK_VK()
	{
		return this.cRUECK_STEUERVERMERK_VK;
	}

	public BigDecimal get_bdRUECK_MWST_EK()
	{
		return this.bdRUECK_MWST_EK;
	}
	public BigDecimal get_bdRUECK_MWST_VK()
	{
		return this.bdRUECK_MWST_VK;
	}

	public Integer get_intNummerInDropDownEK()
	{
		return intNummerInDropDownEK;
	}

	public Integer get_intNummerInDropDownVK()
	{
		return intNummerInDropDownVK;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @deprecated
	 * @throws myException
	 */
	private void alte_steuer_ermittlung_vor_2013_12_06() throws myException {
		
		String cID_LAND_MANDANT = bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF_NN("");
		 
		RECORD_LAND recLandMandant = new RECORD_LAND(cID_LAND_MANDANT);

		
		//dann in den steuersaetzen mit vermerk "Standard" nach einem Vermerk der Landes-id suchen
		RECLIST_MWSTSCHLUESSEL reclistSTEUER = 
			new RECLIST_MWSTSCHLUESSEL("SELECT * FROM "+bibE2.cTO()+".JT_MWSTSCHLUESSEL WHERE ID_LAND="+recLandMandant.get_ID_LAND_lValue(new Long(-1)));
		
		RECORD_MWSTSCHLUESSEL recMandantMWST = null;
		//gibts nur eine, dann gilt die, sonst gilt der standard innerhalb des landes
		if (reclistSTEUER.get_vKeyValues().size()==1)
		{
			recMandantMWST = reclistSTEUER.get(0);
		}
		else
		{
			for (int i=0;i<reclistSTEUER.get_vKeyValues().size();i++)
			{
				if (reclistSTEUER.get(i).is_IST_STANDARD_YES())
				{
					recMandantMWST = reclistSTEUER.get(i);
					break;
				}
			}
		}
		
		
		BigDecimal  	bdEK_Steuer = null;
		
		
		
		
		/*
		 * !!! AENDERUNG !!! 	Egal wie die Konstellation ist, bei lieferanten wird (falls eindeutig) der Steuersatz aus dem
		 * 						Adress-Stamm verwendet (falls es keine Lagerposition ist!!
		 */
		if (! this.recADRESSE_BASIS_START.get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()))
		{
			//wenn die adresse nicht der mandant ist dann nachschauen
			RECLIST_KUNDE_MWST  recListKDMWSt =   this.recADRESSE_BASIS_START.get_DOWN_RECORD_LIST_KUNDE_MWST_id_adresse();
			Vector<String> 		vSteuersatz =     new Vector<String>();
			Vector<BigDecimal>	vBDSteuersatz =   new Vector<BigDecimal>();
			
			
			Iterator<Entry<String, RECORD_KUNDE_MWST>> it = recListKDMWSt.entrySet().iterator(); 
			while (it.hasNext())
			{
				RECORD_KUNDE_MWST recKD_MWST=it.next().getValue();
				vSteuersatz.add(recKD_MWST.get_UP_RECORD_MWSTSCHLUESSEL_id_mwstschluessel().get_STEUERSATZ_cF_NN(""));
				vBDSteuersatz.add(recKD_MWST.get_UP_RECORD_MWSTSCHLUESSEL_id_mwstschluessel().get_STEUERSATZ_bdValue(BigDecimal.ZERO));
			}
			
			if (vSteuersatz.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Der Lieferant ",true,
																			this.recADRESSE_BASIS_START.get_NAME1_cUF_NN(" - "),false,
																			" hat keine Angaben zur Lieferanten-MWSt. hinterlegt !!",true)));
			}
			else if (vSteuersatz.size()>1)
			{
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Der Lieferant ",true,
															this.recADRESSE_BASIS_START.get_NAME1_cUF_NN(" - "),false,
															" hat mehrere Lieferanten-Steuersätze hinterlegt: ",true,
															bibALL.Concatenate(vSteuersatz, "/", ""),false)));
			}
			else
			{
				bdEK_Steuer = vBDSteuersatz.get(0);
			}
		}
		

		
		// laender sind gleich 
		if  (this.recADRESSE_BASIS_START.get_ID_LAND_lValue(new Long(-1)).longValue()==this.recADRESSE_BASIS_ZIEL.get_ID_LAND_lValue(new Long(-2)))
		{
			// laender sind gleich und gleich dem land des mandanten
			if (this.recADRESSE_BASIS_START.get_ID_LAND_lValue(new Long(-1)).longValue()==recLandMandant.get_ID_LAND_lValue(new Long(-2))) 
			{
				if (bdEK_Steuer!=null)
				{
					this.bdRUECK_MWST_EK = bdEK_Steuer;
					this.bSteuerSatzErmitteltEK=true;
				}
				this.cRUECK_STEUERVERMERK_EK = FU___CONST.EU_STEUERVERMERK_LEER;
				this.bSteuerVermerkErmitteltEK = true;
				this.intNummerInDropDownEK = new Integer(0);
				
				if (recMandantMWST!=null)
				{
					this.bdRUECK_MWST_VK = recMandantMWST.get_STEUERSATZ_bdValue(null);
					this.bSteuerSatzErmitteltVK=true;
				}
				this.cRUECK_STEUERVERMERK_VK = FU___CONST.EU_STEUERVERMERK_LEER;
				this.bSteuerVermerkErmitteltVK = true;
				this.intNummerInDropDownVK = new Integer(0);
			}
		}
		else
		{
			
			///////////    EK EK EK EK EK EK EK EK EK EK EK EK
			// gleiche laender quelle und mandant
			if (this.recADRESSE_BASIS_START.get_ID_LAND_lValue(new Long(-1)).longValue()==recLandMandant.get_ID_LAND_lValue(new Long(-2)))
			{
				if (bdEK_Steuer!=null)
				{
					this.bdRUECK_MWST_EK = bdEK_Steuer;
					this.bSteuerSatzErmitteltEK=true;
				}

				this.cRUECK_STEUERVERMERK_EK = FU___CONST.EU_STEUERVERMERK_LEER;
				this.bSteuerVermerkErmitteltEK = true;
				this.intNummerInDropDownEK = new Integer(0);

			}
			else
			{
				//quelle und mandant ist unterschiedlich und beides sind intrastat-laender ==> 0 Steuer und EU-Liefervermerk
				if (this.recADRESSE_BASIS_START.get_UP_RECORD_LAND_id_land().is_INTRASTAT_JN_YES() && recLandMandant.is_INTRASTAT_JN_YES())
				{
					if (bdEK_Steuer!=null)
					{
						this.bdRUECK_MWST_EK = bdEK_Steuer;
						this.bSteuerSatzErmitteltEK=true;

						// wenn der emittelte steuersatz 0 ist, wird die steuerfrei-bemerkung eingedruckt
						if (bdEK_Steuer.compareTo(BigDecimal.ZERO)==0)
						{
							this.cRUECK_STEUERVERMERK_EK = bibALL.get_RECORD_MANDANT().get_EU_STEUER_VERMERK_cUF_NN("");
							this.bSteuerVermerkErmitteltEK=true;
							this.intNummerInDropDownEK = new Integer(0);
						}
						else
						{
							this.cRUECK_STEUERVERMERK_EK = FU___CONST.EU_STEUERVERMERK_LEER;
							this.bSteuerVermerkErmitteltEK = true;
							this.intNummerInDropDownEK = new Integer(0);
						}
					}


				}
				// aussenlieferung 
				else if (this.recADRESSE_BASIS_START.get_UP_RECORD_LAND_id_land().is_INTRASTAT_JN_NO() && recLandMandant.is_INTRASTAT_JN_YES())
				{
					if (bdEK_Steuer!=null)
					{
						this.bdRUECK_MWST_EK = bdEK_Steuer;
						this.bSteuerSatzErmitteltEK=true;

						// wenn der emittelte steuersatz 0 ist, wird die steuerfrei-bemerkung eingedruckt
						if (bdEK_Steuer.compareTo(BigDecimal.ZERO)==0)
						{
							this.cRUECK_STEUERVERMERK_EK = bibALL.get_RECORD_MANDANT().get_AUSSEN_STEUER_VERMERK_cUF_NN("");
							this.bSteuerVermerkErmitteltEK=true;
							this.intNummerInDropDownEK = new Integer(0);
						}
						else
						{
							this.cRUECK_STEUERVERMERK_EK = FU___CONST.EU_STEUERVERMERK_LEER;
							this.bSteuerVermerkErmitteltEK = true;
							this.intNummerInDropDownEK = new Integer(0);
							
						}
					}
				}
			}
			///////////    EK EK EK  EK EK EK EK EK EK EK EK EK
			/// --------------------------------------------------

			
			
			
			///////////    VK VK VK VK VK VK VK VK VK VK VK VK
			// Gleiche Laender Ziel und Mandant
			// 1. verhaeltnis mandant zu ziel
			if (this.recADRESSE_BASIS_ZIEL.get_ID_LAND_lValue(new Long(-1)).longValue()==recLandMandant.get_ID_LAND_lValue(new Long(-2)))
			{
				if (recMandantMWST != null)
				{
					this.bdRUECK_MWST_VK = recMandantMWST.get_STEUERSATZ_bdValue(null);
					this.bSteuerSatzErmitteltVK=true;
					this.cRUECK_STEUERVERMERK_VK = FU___CONST.EU_STEUERVERMERK_LEER;
					this.bSteuerVermerkErmitteltVK = true;

					this.intNummerInDropDownVK = new Integer(0);
				}
			}
			else
			{
				//Verschiedene Laender mandant und ziel und beides sind intrastat-laender ==> 0 Steuer und EU-Liefervermerk
				if (this.recADRESSE_BASIS_ZIEL.get_UP_RECORD_LAND_id_land().is_INTRASTAT_JN_YES() && recLandMandant.is_INTRASTAT_JN_YES())
				{
					this.bdRUECK_MWST_VK = new BigDecimal(0);
					this.bSteuerSatzErmitteltVK=true;
					this.cRUECK_STEUERVERMERK_VK = bibALL.get_RECORD_MANDANT().get_EU_STEUER_VERMERK_cUF_NN("");
					this.bSteuerVermerkErmitteltVK=true;

					this.intNummerInDropDownVK = new Integer(0);
				}
				else if (this.recADRESSE_BASIS_ZIEL.get_UP_RECORD_LAND_id_land().is_INTRASTAT_JN_NO() && recLandMandant.is_INTRASTAT_JN_YES())
				{
					this.bdRUECK_MWST_VK = new BigDecimal(0);
					this.bSteuerSatzErmitteltVK=true;
					this.cRUECK_STEUERVERMERK_VK = bibALL.get_RECORD_MANDANT().get_AUSSEN_STEUER_VERMERK_cUF_NN("");
					this.bSteuerVermerkErmitteltVK=true;

					this.intNummerInDropDownVK = new Integer(0);
				}
			}
			///////////    VK VK VK VK VK VK VK VK VK VK VK VK
			/// --------------------------------------------------
		}

	}
	

	
}
