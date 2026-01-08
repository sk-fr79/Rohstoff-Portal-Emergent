package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ComponentMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO_MASK_ComponentMAP;

public class FU_AL_SucheLieferBed {

	public static enum ENUM_GEFUNDENER_TYP {
		AUS_ADRESSE,
		AUS_ANGEBOT,
		AUS_KONTRAKT,
		AUS_FUHRE,
		UNDEFINIERT,
		IST_EIGENE_ADRESSE
	} ;
	
	boolean bINIT = false;
	
	private String   cLIEFERBED_incl_Fuhre = "";
	private String   cLIEFERBED_incl_Fuhre_kurz = "";
	
	private String   cLIEFERBED_ohne_Fuhre = "";
	
	
	//fieldnames fuer pruefung der lieferbedingung
	private E2_ComponentMAP oMAP = 							null;
	private String 			NAME_FU_LIEFEBED_ALTERNATIV = 	null;
	private String 			NAME_FU_ID_ADRESSE = 			null;
	private String 			NAME_FU_ID_VPOS_KON = 			null;
	private String 			NAME_FU_ID_VPOS_STD = 			null;
	private String 			NAME_AD_ID_LIEFERBED = 			null;
	
	private FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP  enumGefundenerTYP = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.UNDEFINIERT;
	private FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP  enumGefundenerTYP_ohneFuhre = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.UNDEFINIERT;
 

	//weitere variante fuer die ermittlung bei einem bestehenden MyRecord
	//falls ein solcher vorhanden ist, dann muss die ermittlung vorrangig daraus laufen
	private MyRECORD        oRecord = null;        
	

	/**
	 * 
	 * @param oMAP
	 * @param nAME_FU_ID_ADRESSE
	 * @param nAME_FU_LIEFEBED_ALTERNATIV
	 * @param nAME_FU_ID_VPOS_KON
	 * @param nAME_FU_ID_VPOS_STD
	 * @param nAME_AD_ID_LIEFERBED
	 * @throws myException
	 */
	public FU_AL_SucheLieferBed(	E2_ComponentMAP  	mAP, 
									String 				nAME_FU_ID_ADRESSE, 
									String 				nAME_FU_LIEFEBED_ALTERNATIV,
									String 				nAME_FU_ID_VPOS_KON,
									String 				nAME_FU_ID_VPOS_STD, 
									String 				nAME_AD_ID_LIEFERBED,
									boolean    			bStelleGleichFest) throws myException {
		super();
		
		this.oMAP = mAP;
		this.oRecord = null;
		NAME_FU_LIEFEBED_ALTERNATIV = 	nAME_FU_LIEFEBED_ALTERNATIV;
		NAME_FU_ID_ADRESSE = 			nAME_FU_ID_ADRESSE;
		NAME_FU_ID_VPOS_KON = 			nAME_FU_ID_VPOS_KON;
		NAME_FU_ID_VPOS_STD = 			nAME_FU_ID_VPOS_STD;
		NAME_AD_ID_LIEFERBED = 			nAME_AD_ID_LIEFERBED;

		if (bStelleGleichFest) {
			this.INIT_SEARCH();
		}
		
	}

	

	/**
	 * 
	 * @param record
	 * @param nAME_FU_ID_ADRESSE
	 * @param nAME_FU_LIEFEBED_ALTERNATIV
	 * @param nAME_FU_ID_VPOS_KON
	 * @param nAME_FU_ID_VPOS_STD
	 * @param nAME_AD_ID_LIEFERBED
	 * @param bStelleGleichFest
	 * @throws myException
	 */
	public FU_AL_SucheLieferBed(	MyRECORD  			record, 
									String 				nAME_FU_ID_ADRESSE, 
									String 				nAME_FU_LIEFEBED_ALTERNATIV,
									String 				nAME_FU_ID_VPOS_KON,
									String 				nAME_FU_ID_VPOS_STD, 
									String 				nAME_AD_ID_LIEFERBED,
									boolean    			bStelleGleichFest) throws myException {
		super();
		
		this.oMAP = null;
		this.oRecord = record;
		NAME_FU_LIEFEBED_ALTERNATIV = 	nAME_FU_LIEFEBED_ALTERNATIV;
		NAME_FU_ID_ADRESSE = 			nAME_FU_ID_ADRESSE;
		NAME_FU_ID_VPOS_KON = 			nAME_FU_ID_VPOS_KON;
		NAME_FU_ID_VPOS_STD = 			nAME_FU_ID_VPOS_STD;
		NAME_AD_ID_LIEFERBED = 			nAME_AD_ID_LIEFERBED;

		if (bStelleGleichFest) {
			this.INIT_SEARCH();
		}
		
	}

		
	/**
	 * methode, um die lieferbedingung zu ermitteln (je nach maskensituation)
	 * @throws myException
	 */
	public void INIT_SEARCH() throws myException {
		
		
		this.enumGefundenerTYP = 			FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.UNDEFINIERT;
		this.enumGefundenerTYP_ohneFuhre = 	FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.UNDEFINIERT;
		
		//zuerst den mandanten checken
		if (this.get_bIS_MANDANT()) {
			this.cLIEFERBED_incl_Fuhre = "";
			this.cLIEFERBED_ohne_Fuhre = "";
			this.enumGefundenerTYP = 			FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.IST_EIGENE_ADRESSE;
			this.enumGefundenerTYP_ohneFuhre = 	FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.IST_EIGENE_ADRESSE;
		} else {
		
			String cLIEFERBED_ALTERNATIV = null;
			
			if (oRecord != null) {
				cLIEFERBED_ALTERNATIV = this.oRecord.get_UnFormatedValue(this.NAME_FU_LIEFEBED_ALTERNATIV, "");
			} else {
				if (this.get_bCallAusListe()) {
					cLIEFERBED_ALTERNATIV = this.oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue(this.NAME_FU_LIEFEBED_ALTERNATIV,"");
				} else {
					cLIEFERBED_ALTERNATIV = this.oMAP.get_cActualDBValueFormated(this.NAME_FU_LIEFEBED_ALTERNATIV,"");
				}
			}
			
			
			this.cLIEFERBED_ohne_Fuhre = this.ermittle_LieferBedingung_OhneFuhre();
			if (S.isEmpty(cLIEFERBED_ALTERNATIV)) {
				this.cLIEFERBED_incl_Fuhre = this.cLIEFERBED_ohne_Fuhre;
			} else {
				this.cLIEFERBED_incl_Fuhre = cLIEFERBED_ALTERNATIV;
				this.enumGefundenerTYP = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_FUHRE;
			}
		}
		
		this.cLIEFERBED_incl_Fuhre_kurz = (S.NN(this.cLIEFERBED_incl_Fuhre)+"   ").substring(0, 3);
		
		this.bINIT = true;
	}
	

	private boolean get_bCallAusListe() {
		if (this.oRecord != null) {
			return false;
		} else {
			return !(this.oMAP instanceof FU_MASK_ComponentMAP || this.oMAP instanceof FUO_MASK_ComponentMAP );
		}
	}
	
	
	private String ermittle_LieferBedingung_OhneFuhre() throws myException {
		
		String cRueck = "";
		
		long lID_ADRESSE =  0l;
		long lID_KONTRAKT = 0l;
		long lID_ANGEBOT =  0l;
		
		if (this.oRecord != null) {
			lID_ADRESSE =  this.oRecord.get_longValue(this.NAME_FU_ID_ADRESSE, 0l).longValue();
			lID_KONTRAKT = this.oRecord.get_longValue(this.NAME_FU_ID_VPOS_KON, 0l).longValue();
			lID_ANGEBOT =  this.oRecord.get_longValue(this.NAME_FU_ID_VPOS_STD, 0l).longValue();
		
		} else {
		
			if (this.get_bCallAusListe()) {
				if (this.oMAP.get_oInternalSQLResultMAP()!=null) {
					lID_ADRESSE =  this.oMAP.get_oInternalSQLResultMAP().get_LActualDBValue(this.NAME_FU_ID_ADRESSE,true).longValue();
					lID_KONTRAKT = this.oMAP.get_oInternalSQLResultMAP().get_LActualDBValue(this.NAME_FU_ID_VPOS_KON,true).longValue();
					lID_ANGEBOT =  this.oMAP.get_oInternalSQLResultMAP().get_LActualDBValue(this.NAME_FU_ID_VPOS_STD,true).longValue();
				}
			} else {
				lID_ADRESSE =  this.oMAP.get_LActualDBValue(this.NAME_FU_ID_ADRESSE, 0l, 0l).longValue();
				lID_KONTRAKT = this.oMAP.get_LActualDBValue(this.NAME_FU_ID_VPOS_KON, 0l, 0l).longValue();
				lID_ANGEBOT =  this.oMAP.get_LActualDBValue(this.NAME_FU_ID_VPOS_STD, 0l, 0l).longValue();
			}
		}
		
		if (lID_ADRESSE>0) {
				
			//nur dann beachten
			if (lID_KONTRAKT>0) {
				cRueck = this.queryLfdBd_Kontrakt(lID_KONTRAKT);
				this.enumGefundenerTYP = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_KONTRAKT;
				this.enumGefundenerTYP_ohneFuhre = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_KONTRAKT;
			} else if (lID_ANGEBOT>0) {
				cRueck = this.queryLfdBd_Angebot(lID_ANGEBOT);
				this.enumGefundenerTYP = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_ANGEBOT;
				this.enumGefundenerTYP_ohneFuhre = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_ANGEBOT;
				if (S.isEmpty(cRueck)) {
					cRueck = this.queryLfdBd_Adresse(lID_ADRESSE, this.NAME_AD_ID_LIEFERBED);
					this.enumGefundenerTYP = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_ADRESSE;
					this.enumGefundenerTYP_ohneFuhre = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_ADRESSE;
					if (S.isEmpty(cRueck)) {
						this.enumGefundenerTYP = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.UNDEFINIERT;
						this.enumGefundenerTYP_ohneFuhre = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.UNDEFINIERT;
					}
				}
			} else {
				cRueck = this.queryLfdBd_Adresse(lID_ADRESSE, this.NAME_AD_ID_LIEFERBED);
				this.enumGefundenerTYP = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_ADRESSE;
				this.enumGefundenerTYP_ohneFuhre = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_ADRESSE;
				if (S.isEmpty(cRueck)) {
					this.enumGefundenerTYP = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.UNDEFINIERT;
					this.enumGefundenerTYP_ohneFuhre = FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.UNDEFINIERT;
				}
			}
		}
		
		return cRueck;
	}
	
	
	
	
	
	public boolean get_bIS_MANDANT() throws myException {
		boolean bRueck = false;
		
		long lID_ADRESSE = 0;
		
		if (this.oRecord != null) {
			lID_ADRESSE = this.oRecord.get_longValue(this.NAME_FU_ID_ADRESSE, 0l).longValue();
		} else {
			if (this.get_bCallAusListe()) {
				lID_ADRESSE = this.oMAP.get_oInternalSQLResultMAP().get_LActualDBValue(this.NAME_FU_ID_ADRESSE,true).longValue();
			} else {
				lID_ADRESSE = this.oMAP.get_LActualDBValue(this.NAME_FU_ID_ADRESSE, 0l, 0l).longValue();
			}
		}
		
		if (lID_ADRESSE>0) {
			if (lID_ADRESSE==bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(-1l).longValue()) {
				bRueck = true;
			}
		}
		return bRueck;
	}
	
	
		
	private String queryLfdBd_Angebot(long lID_VPOS_STD) {
		return bibDB.EinzelAbfrage("SELECT "+
											_DB.VPOS_STD$LIEFERBEDINGUNGEN+" FROM "+
											bibE2.cTO()+"."+_DB.VPOS_STD+" WHERE "+
											_DB.VPOS_STD$ID_VPOS_STD+"="+lID_VPOS_STD,"","","");
	}
	
	private String queryLfdBd_Kontrakt(long lID_VPOS_KON) {
		return bibDB.EinzelAbfrage("SELECT "+
				_DB.VPOS_KON$LIEFERBEDINGUNGEN+" FROM "+
				bibE2.cTO()+"."+_DB.VPOS_KON+" WHERE "+
				_DB.VPOS_KON$ID_VPOS_KON+"="+lID_VPOS_KON,"","","");
	}
	
	private String queryLfdBd_Adresse(long lID_ADRESSE, String NAME_AD_ID_LIEFERBED) {
		
		String cSQL="SELECT "+	_DB.Z_LIEFERBEDINGUNGEN$KURZBEZEICHNUNG+" FROM " +
								_DB.ADRESSE+" LEFT OUTER JOIN "+_DB.LIEFERBEDINGUNGEN +
								" ON (" +
								_DB.ADRESSE+"."+NAME_AD_ID_LIEFERBED +
								"="+_DB.Z_LIEFERBEDINGUNGEN$ID_LIEFERBEDINGUNGEN +
								") WHERE "+_DB.Z_ADRESSE$ID_ADRESSE+"="+lID_ADRESSE;
		
		return bibDB.EinzelAbfrage(cSQL,"","","");
	}



	public String get_cLIEFERBED_incl_FUHRE() throws myException {
		if (!this.bINIT) {
			throw new myException(this,"YOU MUST CALL INIT_SEARCH()");
		}
		return cLIEFERBED_incl_Fuhre;
	}

	public String get_cLIEFERBED_ohne_FUHRE() throws myException {
		if (!this.bINIT) {
			throw new myException(this,"YOU MUST CALL INIT_SEARCH()");
		}
		return cLIEFERBED_ohne_Fuhre;
	}




	public FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP get_GefundenerTYP() {
		return enumGefundenerTYP;
	}
	
	
	public FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP get_GefundenerTYP_ohneFuhre() {
		return enumGefundenerTYP_ohneFuhre;
	}
		
	public MyE2_String get_cToolTipInfos() throws myException {
		if (!this.bINIT) {
			throw new myException(this,"YOU MUST CALL INIT_SEARCH()");
		}
		if (enumGefundenerTYP==FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_KONTRAKT) {
			return new MyE2_String("Lieferbedingung ",true,"("+this.cLIEFERBED_incl_Fuhre_kurz+")",false," wurde im Kontrakt festgelegt",true);
		} else  if (enumGefundenerTYP==FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_ANGEBOT) {
			return new MyE2_String("Lieferbedingung ",true,"("+this.cLIEFERBED_incl_Fuhre_kurz+")",false,"  wurde im Angebot festgelegt",true);
		} else  if (enumGefundenerTYP==FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_ADRESSE) {
			return new MyE2_String("Lieferbedingung ",true,"("+this.cLIEFERBED_incl_Fuhre_kurz+")",false,"  kommt aus dem Firmenstammsatz",true);
		} else  if (enumGefundenerTYP==FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.UNDEFINIERT) {
			return new MyE2_String("Lieferbedingung undefiniert");
		} else  if (enumGefundenerTYP==FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.IST_EIGENE_ADRESSE) {
			return new MyE2_String("Eigenes Lager, keine Lieferbedingung");
		} else  if (enumGefundenerTYP==FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_FUHRE) {
			return new MyE2_String("Lieferbedingung ",true,"("+this.cLIEFERBED_incl_Fuhre_kurz+")",false,"  ist im Bewegungssatz (Fuhre/Fuhrenort) festgelegt",true);
		} else {
			return new MyE2_String("Unbekannt ...");
		}

	}
	

	
	
	public MyE2_String get_cHerkunftsinfo_ohne_Fuhre() throws myException {
		if (!this.bINIT) {
			throw new myException(this,"YOU MUST CALL INIT_SEARCH()");
		}
		if (enumGefundenerTYP_ohneFuhre==FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_KONTRAKT) {
			return new MyE2_String("Herkunft: Kontrakt");
		} else  if (enumGefundenerTYP_ohneFuhre==FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_ANGEBOT) {
			return new MyE2_String("Herkunft: Angebot");
		} else  if (enumGefundenerTYP_ohneFuhre==FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.AUS_ADRESSE) {
			return new MyE2_String("Herkunft: Firmenstamm");
		} else  if (enumGefundenerTYP_ohneFuhre==FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.UNDEFINIERT) {
			return new MyE2_String("Undefiniert");
		} else  if (enumGefundenerTYP_ohneFuhre==FU_AL_SucheLieferBed.ENUM_GEFUNDENER_TYP.IST_EIGENE_ADRESSE) {
			return new MyE2_String("Eigenes Lager");
		} else {
			return new MyE2_String("Unbekannt ...");
		}

	}
	
	
	
}
