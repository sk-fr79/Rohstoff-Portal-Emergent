package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;
import rohstoff.Echo2BusinessLogic._TAX.PRUEFKLASSEN.Check__ObHandelErlaubt_Definiere_RC_Status_Sorte;

public abstract class HD_Station
{ 

	//uebergabe-variable  			(p_ = parameter)
	private boolean   						P_bHauptFuhre = 		true;
	private boolean 						P_bEK =					true;
	private String  						P_cID_ADRESSE_JUR = 	null;
	private String  						P_cID_ADRESSE_GEO = 	null;
	private String   						P_cID_ARTIKEL_BEZ =     null;
	private BigDecimal 						P_bdAbrechnungsMenge =  null;
	private String  						P_cTP_Verantwortung = 	null;     		
	

	private String  						P_cInfoTextName1_2 = 	null;     		
	private String  						P_cInfoTextOrt = 	null;     		
	private String  						P_cInfoTextSorte = 	null;     		
	

	//2013-10-01: preis muss uebergeben werden, um zu definieren, welcher steuersachverhalt gezogen wird 
	private BigDecimal   					P_bdPreis = null;
	
	



	//kenngroessen fuer bewertung   (b_ = bewertung)
	private boolean							B_bStationIstMandant = 	false;
	private Long 							B_IdLandJuristisch = 	null;
	private Long 							B_IdLandGeografisch = 	null;
	private boolean							B_bSorteRC = 			false;
	private boolean							B_bSorteProdukt=		false;
	private boolean							B_bSorteEoW=			false;
	private boolean							B_bSorteDienstleistung=	false;
	private boolean  						B_bFirmaKannUST = 		false;
	private String  						B_cTP_Verantwortung = 	null;     					
	

	//hilfswerte, erzeugt aus den uebergabeparametern (H_ = hilfswert)
	private RECORD_ADRESSE  				H_recAdresse_JUR = null;
	private RECORD_ADRESSE  				H_recAdresse_GEO = null;
	private RECORD_ARTIKEL  				H_recArtikel = null;
	
	
	
	private HD_FehlerBerichte  vFehlerVector = new HD_FehlerBerichte();
	


	private RECORD_VPOS_TPA_FUHRE    	    P_RecFuhre = null;
	private RECORD_VPOS_TPA_FUHRE_ORT  	    P_RecFuhreOrt = null;
	
	
	//2014-01-28: neue pruefung auf korrekte adresse und und den status der sorten / erlaubnis im Land die jeweilige sorte zu handeln
	private Check__ObHandelErlaubt_Definiere_RC_Status_Sorte   oCheckObUndWieHandelMoeglich = null;
	
	
	//2014-01-29: MessageVector sammelt Fehler, die aufgrund von Check_ErlaubtenHandel() gefunden werden
	private MyE2_MessageVector  			oMV_FehlerHandelMoeglich = new MyE2_MessageVector();
	
	
	//abstracte methoden 
	public abstract MyE2_MessageVector 		applyResults(HD_WarenBewegungEinstufung oHD_Fuhreneinstufung, String cID_TAX_UF, String cIntrastat_YN, String cTransit_YN, boolean bEK_true__VK_false) throws myException;
	public abstract boolean           		isUpdatingAllowd() throws myException;
	

	
	//20171114: steuersatz mit wechseldatum beruecksichtigen
	public MyDate       leistungsDatum = null;
	
	
	

	public HD_Station() 
	{
		super();
		
	}


	/**
	 * 
	 * @param p_bHauptFuhre
	 * @param p_bQuelle
	 * @param p_cID_ADRESSE_JUR_UF
	 * @param p_cID_ADRESSE_GEO_UF
	 * @param p_cID_ARTIKEL_BEZ_UF
	 * @param p_bdAbrechnungsMenge
	 * @param p_cTP_Verantwortung
	 * @param p_RecFuhre
	 * @param p_RecFuhreOrt
	 * @param p_cInfoTextName1_2
	 * @param p_cInfoTextOrt
	 * @param p_cInfoTextSorte
	 * @param p_bd_Preis
	 * @param p_leistungsDatum
	 * @throws myException
	 */
	public void init(	boolean						p_bHauptFuhre, 
						boolean 					p_bQuelle, 
						String 						p_cID_ADRESSE_JUR_UF, 
						String 						p_cID_ADRESSE_GEO_UF, 
						String 						p_cID_ARTIKEL_BEZ_UF, 
						BigDecimal 					p_bdAbrechnungsMenge, 
						String 						p_cTP_Verantwortung,
						RECORD_VPOS_TPA_FUHRE  		p_RecFuhre,
						RECORD_VPOS_TPA_FUHRE_ORT  	p_RecFuhreOrt,
						String 						p_cInfoTextName1_2,
						String 						p_cInfoTextOrt,
						String 						p_cInfoTextSorte,
						BigDecimal 		  			p_bd_Preis,
						MyDate                      p_leistungsDatum
						) throws myException 	{
		
	
//		DEBUG.System_println(" ");
//		DEBUG.System_println("-------------------------------------------");
//		DEBUG.System_println("Hauptfuhre: "+(p_bQuelle?"EK":"VK")+"->"+(p_bHauptFuhre?"JA":"NEIN"));
//		DEBUG.System_println("-------------------------------------------");
//		DEBUG.System_println(" ");
		
		this.P_bHauptFuhre = 		p_bHauptFuhre;
		this.P_bEK = 				p_bQuelle;
		this.P_cID_ADRESSE_JUR =	p_cID_ADRESSE_JUR_UF;
		this.P_cID_ADRESSE_GEO = 	p_cID_ADRESSE_GEO_UF;
		this.P_cID_ARTIKEL_BEZ = 	p_cID_ARTIKEL_BEZ_UF;
		this.P_bdAbrechnungsMenge = p_bdAbrechnungsMenge;
		this.P_cTP_Verantwortung = 	p_cTP_Verantwortung;
		
		this.P_cInfoTextName1_2 = 	p_cInfoTextName1_2;
		this.P_cInfoTextOrt = 		p_cInfoTextOrt;
		this.P_cInfoTextSorte = 	p_cInfoTextSorte;
		
		this.P_RecFuhre 		=	p_RecFuhre;
		this.P_RecFuhreOrt 		=	p_RecFuhreOrt;
		
		//20171114: steuersatz mit datumswechsel
		this.leistungsDatum = p_leistungsDatum;
		
		//2013-10-01: preis muss uebergeben werden, um zu definieren, welcher steuersachverhalt gezogen wird
		//            es reicht nicht, die fuhre / fuhrenort zu uebergeben, diese koennen null sein
		this.P_bdPreis = 			p_bd_Preis;	

		if (this.P_bdPreis==null) {
			this.P_bdPreis=BigDecimal.ZERO;   //ein leerer preis wird wie eine positive 0 behandelt
		}
		
		
		//moeglich sind; recFuhre null und ort ist null oder je eins von beiden, beide duerfen nicht voll sein
		if (this.P_RecFuhre!=null && this.P_RecFuhreOrt!=null) {
			throw new myException("One of the fields RECORD_VPOS_TPA_FUHRE or RECORD_VPOS_TPA_FUHRE_ORT MUST NOT NOT NULL, or both must be null");
		}
		
		//pruefung, ob die angaben der warenbewegungsstation vollstaendig sind (fehler landen im this.vFehlerVector)
		this._pruefeVariable_und_baue_HilfsWerte();
		
		//wenn diese pruefung erfolgreich war, dann die naechste
		if (this.vFehlerVector.size()==0) {
			
			this.oCheckObUndWieHandelMoeglich = new Check__ObHandelErlaubt_Definiere_RC_Status_Sorte(this.P_cID_ADRESSE_GEO, this.H_recArtikel.get_ID_ARTIKEL_cUF(), false);
			
			//sehen ob es Beanstandungen gibt (falsche adresse, handel nicht moeglich usw)
			// Fehlermeldungen werden in der _H_Station abgelegt
			this.oMV_FehlerHandelMoeglich.add_MESSAGE(this.oCheckObUndWieHandelMoeglich.get_oMV());
			this.__init();
		}
	}
	
	
	
	/*
	 * 
	 */
	private void __init() throws myException
	{
		if (this.oCheckObUndWieHandelMoeglich == null) {
			throw new myException(this,"Error: the object oCheckObUndWieHandelMoeglich has no instance yet!");
		}
		
		this.B_bStationIstMandant = 	(this.H_recAdresse_JUR.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")));

		this.B_IdLandJuristisch = 		this.H_recAdresse_JUR.get_ID_LAND_lValue(null);
		this.B_IdLandGeografisch = 		this.H_recAdresse_GEO.get_ID_LAND_lValue(null);
		this.B_bSorteProdukt = 			this.oCheckObUndWieHandelMoeglich.get_bSorteIstProdukt();
		this.B_bSorteEoW = 				this.oCheckObUndWieHandelMoeglich.get_bSorteIstEoW();
		this.B_bSorteDienstleistung=	this.H_recArtikel.is_DIENSTLEISTUNG_YES();
		this.B_bSorteRC= 				this.oCheckObUndWieHandelMoeglich.get_bSorteIstRC();
		this.B_bFirmaKannUST =			this.oCheckObUndWieHandelMoeglich.get_bIstFirma(); 
	}
	



	

	/*
	 * nachsehen, ob alle variable komplett sind, damit eine bewertung erst sinn macht
	 */
	private void _pruefeVariable_und_baue_HilfsWerte()
	{
		//bei der hauptfuhre-quelle erstmal nachsehen, ob die fuhre bezgl. tpa-verantwortung klassifiziert ist
		if (this.P_bEK)
		{
			if (!(S.isFull(this.P_cTP_Verantwortung) && TAX_CONST.V_TP_VERANTWORTUNG.contains(this.P_cTP_Verantwortung)))
			{
				this.vFehlerVector.add(new HD_FehlerBericht(true,new MyE2_String("Gesamt"),new MyE2_String("Definition der Fuhrenverantwortung"),new MyE2_String("FEHLT oder UNKORREKT!")));
			}
		}

		this.B_cTP_Verantwortung = this.P_cTP_Verantwortung;
		
		//zuerst null zu strings und string mit punkt zu string unformated
		this.P_cID_ADRESSE_JUR = S.NN(this.P_cID_ADRESSE_JUR).replace(".", "");
		this.P_cID_ADRESSE_GEO = S.NN(this.P_cID_ADRESSE_GEO).replace(".", "");
		this.P_cID_ARTIKEL_BEZ = S.NN(this.P_cID_ARTIKEL_BEZ).replace(".", "");
		
		if (!bibALL.isLong(this.P_cID_ADRESSE_JUR)) {this.P_cID_ADRESSE_JUR="";}
		if (!bibALL.isLong(this.P_cID_ADRESSE_GEO)) {this.P_cID_ADRESSE_GEO="";}
		if (!bibALL.isLong(this.P_cID_ARTIKEL_BEZ)) {this.P_cID_ARTIKEL_BEZ="";}
		
		if (S.isEmpty(this.P_cID_ADRESSE_JUR)) 	{ this.add_fehler(new MyE2_String(""), new MyE2_String("Lieferant"), new MyE2_String("Fehlt"));}
		if (S.isEmpty(this.P_cID_ADRESSE_GEO)) 	{ this.add_fehler(new MyE2_String(""), new MyE2_String("Ladestation"), new MyE2_String("Fehlt"));}
		if (S.isEmpty(this.P_cID_ARTIKEL_BEZ)) 	{ this.add_fehler(new MyE2_String(""), new MyE2_String("Sortenbezeichnung"), new MyE2_String("Fehlt"));}
		
		
		
		if (this.vFehlerVector.size()==0)
		{
			try
			{
				this.H_recAdresse_JUR=	new RECORD_ADRESSE(this.P_cID_ADRESSE_JUR);
			}
			catch (Exception e)
			{
				this.add_fehler(new MyE2_String(""), new MyE2_String("Adresse Firma"), new MyE2_String("Fehler beim Laden aus der Datenbank"));
				e.printStackTrace();
			}
			try
			{
				this.H_recAdresse_GEO=	new RECORD_ADRESSE(this.P_cID_ADRESSE_GEO);
			}
			catch (Exception e)
			{
				this.add_fehler(new MyE2_String(""), new MyE2_String("Adresse Ladestation"), new MyE2_String("Fehler beim Laden aus der Datenbank"));
				e.printStackTrace();
			}
			try
			{
				this.H_recArtikel=		new RECORD_ARTIKEL_BEZ(this.P_cID_ARTIKEL_BEZ).get_UP_RECORD_ARTIKEL_id_artikel();
			}
			catch (Exception e)
			{
				this.add_fehler(new MyE2_String(""), new MyE2_String("Sorte"), new MyE2_String("Fehler beim Laden"));
				e.printStackTrace();
			}
		}
	}
	
	
	private void add_fehler(MyE2_String cWO, MyE2_String cBetrifft, MyE2_String cInfo)
	{
		MyE2_String WO = new MyE2_String(this.P_bHauptFuhre?"Hauptfuhre":"Fuhrenort",true," / ",false,this.P_bEK?"Ladeseite":"Abladeseite",true,S.isFull(cWO)?": "+cWO.CTrans():":",false);
		
		this.vFehlerVector.add(new HD_FehlerBericht(
				this.P_bEK,WO,cBetrifft,cInfo));
	}
	
	


	

	
	
	public int hashCode()
	{
		return this.baueCompareString(this).hashCode();
	}
	

	
	
	
	
	
	public boolean equals(Object theOther)
	{
		
		if (this == theOther)
		{
			return true;
		}
		
		if (theOther == null)
		{
			return false;
		}
		
		if (!(theOther instanceof HD_Station))
		{
			return false;
		}
		
		return this.baueCompareString(this).equals(this.baueCompareString((HD_Station)theOther));
	}
	
	

	/**
	 * 
	private boolean							B_bStationIstMandant = 	false;
	private Long 							B_IdLandJuristisch = 	null;
	private Long 							B_IdLandGeografisch = 	null;
	private boolean							B_bSorteRC = 			false;
	private boolean							B_bSorteProdukt = 		false;
	private boolean							B_bSorteDienstleistung =false;
	private boolean  						B_bFirmaKannUST = 		false;
	private String  						B_cTP_Verantwortung = 	null;     					
	 * @throws myException 

	 *
	 */
	
	private String baueCompareString(HD_Station oStation)
	{
		StringBuffer cCompareString = new StringBuffer();
		
		if (oStation.B_IdLandJuristisch == null)
		{
			cCompareString.append("NULL");
		}
		else
		{
			cCompareString.append(""+oStation.B_IdLandJuristisch.longValue());
		}
		
		
		if (oStation.B_IdLandGeografisch == null)
		{
			cCompareString.append("NULL");
		}
		else
		{
			cCompareString.append(""+oStation.B_IdLandGeografisch.longValue());
		}
		
		cCompareString.append(oStation.B_bSorteRC?"true":"false");
		cCompareString.append(oStation.B_bSorteProdukt?"true":"false");
		cCompareString.append(oStation.B_bSorteEoW?"true":"false");
		cCompareString.append(oStation.B_bSorteDienstleistung?"true":"false");
		
		//wenn der mandantenschalter gesetzt ist, dass mandantenstationen nicht von normalen Stationen unterschieden werden, dann 
		//      wird das nicht beruecksichtigt
		if (this.get_bBeachteMandantenStatusDerStation()) {
			cCompareString.append(oStation.B_bStationIstMandant?"true":"false");
		}
		cCompareString.append(oStation.B_bFirmaKannUST?"true":"false");
		cCompareString.append(B_cTP_Verantwortung);
		
		return cCompareString.toString();
	}


	public Long get_IdLandJuristisch()
	{
		return this.B_IdLandJuristisch;
	}


	public Long get_IdLandGeografisch()
	{
		return this.B_IdLandGeografisch;
	}


	public Boolean get_bSorteRC()
	{
		return this.B_bSorteRC;
	}


	public Boolean get_bSorteProdukt()
	{
		return this.B_bSorteProdukt;
	}
	public Boolean get_bSorteEoW()
	{
		return this.B_bSorteEoW;
	}


	public Boolean get_bSorteDienstleistung()
	{
		return this.B_bSorteDienstleistung;
	}
	
	
	private String get_SQL_BLOCK_IdLandJuristisch()
	{
		String cRueckSQL = this.P_bEK?RECORD_HANDELSDEF.FIELD__ID_LAND_QUELLE_JUR:RECORD_HANDELSDEF.FIELD__ID_LAND_ZIEL_JUR;
		if (this.B_IdLandJuristisch == null)
		{
			cRueckSQL += "=NULL";
		}
		else
		{
			cRueckSQL += "="+this.B_IdLandJuristisch.longValue();
		}
		
		return "("+cRueckSQL+")";
	}

	
	private String get_SQL_BLOCK_IdLandGeografisch()
	{
		String cRueckSQL = this.P_bEK?RECORD_HANDELSDEF.FIELD__ID_LAND_QUELLE_GEO:RECORD_HANDELSDEF.FIELD__ID_LAND_ZIEL_GEO;
		if (this.B_IdLandGeografisch == null)
		{
			cRueckSQL += "=NULL";
		}
		else
		{
			cRueckSQL += "="+this.B_IdLandGeografisch.longValue();
		}
		
		return "("+cRueckSQL+")";
	}


	

	private String get_SQL_BLOCK_bSorteRC()
	{
		String cRueckSQL = this.P_bEK?RECORD_HANDELSDEF.FIELD__SORTE_RC_QUELLE:RECORD_HANDELSDEF.FIELD__SORTE_RC_ZIEL;
		
		if (this.B_bSorteRC)              //der wert 0 steht fuer den eintrag "EGAL"
		{
			cRueckSQL += " IN (0,1)";
		}
		else
		{
			cRueckSQL += " IN (0,-1)";
		}
		
		return "("+cRueckSQL+")";
	}


	private String get_SQL_BLOCK_bSorteProdukt()
	{
		String cRueckSQL = this.P_bEK?RECORD_HANDELSDEF.FIELD__SORTE_PRODUKT_QUELLE:RECORD_HANDELSDEF.FIELD__SORTE_PRODUKT_ZIEL;
		
		if (this.B_bSorteProdukt)              //der wert 0 steht fuer den eintrag "EGAL"
		{
			cRueckSQL += " IN (0,1)";
		}
		else
		{
			cRueckSQL += " IN (0,-1)";
		}
		
		return "("+cRueckSQL+")";
	}

	
	private String get_SQL_BLOCK_bSorteEoW()
	{
		String cRueckSQL = this.P_bEK?RECORD_HANDELSDEF.FIELD__SORTE_EOW_QUELLE:RECORD_HANDELSDEF.FIELD__SORTE_EOW_ZIEL;
		
		if (this.B_bSorteEoW)              //der wert 0 steht fuer den eintrag "EGAL"
		{
			cRueckSQL += " IN (0,1)";
		}
		else
		{
			cRueckSQL += " IN (0,-1)";
		}
		
		return "("+cRueckSQL+")";
	}

	

	private String get_SQL_BLOCK_bSorteDienstleistung()
	{
		String cRueckSQL = this.P_bEK?RECORD_HANDELSDEF.FIELD__SORTE_DIENSTLEIST_QUELLE:RECORD_HANDELSDEF.FIELD__SORTE_DIENSTLEIST_ZIEL;
		
		if (this.B_bSorteDienstleistung)              //der wert 0 steht fuer den eintrag "EGAL"
		{
			cRueckSQL += " IN (0,1)";
		}
		else
		{
			cRueckSQL += " IN (0,-1)";
		}
		
		return "("+cRueckSQL+")";
	}

	

	private String get_SQL_BLOCK_bStationIstMandant()
	{
		String cRueckSQL = this.P_bEK?RECORD_HANDELSDEF.FIELD__QUELLE_IST_MANDANT:RECORD_HANDELSDEF.FIELD__ZIEL_IST_MANDANT;
		cRueckSQL = "NVL("+cRueckSQL+",'N')";
		
		if (this.B_bStationIstMandant)              
		{
			cRueckSQL += "='Y'";
		}
		else
		{
			cRueckSQL += "='N'";
		}
		
		return "("+cRueckSQL+")";
	}


	private String get_SQL_BLOCK_bKannSteuerAbzug()
	{
		String cRueckSQL = this.P_bEK?RECORD_HANDELSDEF.FIELD__UST_TEILNEHMER_QUELLE:RECORD_HANDELSDEF.FIELD__UST_TEILNEHMER_ZIEL;
		
		if (this.B_bFirmaKannUST)              //der wert 0 steht fuer den eintrag "EGAL"
		{
			cRueckSQL += " IN (0,1)";
		}
		else
		{
			cRueckSQL += " IN (0,-1)";
		}
		
		return "("+cRueckSQL+")";
	}

	
	private String get_SQL_BLOCK_TRANSPORTVERANTWORTUNG()
	{
		String cRueckSQL = "TO_CHAR("+RECORD_HANDELSDEF.FIELD__TP_VERANTWORTUNG+")";
		
		cRueckSQL += "="+bibALL.MakeSql(this.B_cTP_Verantwortung);
		
		return "("+cRueckSQL+")";
	}

	
	
	
	
	/**
	 * 
	 * fasst alle SQL-Strings zusammen
	 * @return
	 */
	public Vector<String> get_vWhereBlock()
	{
		Vector<String>  vRueck = new Vector<String>();
		vRueck.add(this.get_SQL_BLOCK_IdLandJuristisch());
		vRueck.add(this.get_SQL_BLOCK_IdLandGeografisch());
		vRueck.add(this.get_SQL_BLOCK_bSorteRC());
		vRueck.add(this.get_SQL_BLOCK_bSorteProdukt());
		vRueck.add(this.get_SQL_BLOCK_bSorteEoW());
		vRueck.add(this.get_SQL_BLOCK_bSorteDienstleistung());
		
		if (this.get_bBeachteMandantenStatusDerStation()) {
			vRueck.add(this.get_SQL_BLOCK_bStationIstMandant());
		}

		vRueck.add(this.get_SQL_BLOCK_bKannSteuerAbzug());
		
		if (this.P_bEK)
		{
			vRueck.add(this.get_SQL_BLOCK_TRANSPORTVERANTWORTUNG());
		}
		return vRueck;
	}




	public boolean get_bEK() {
		return this.P_bEK;
	}

	public boolean get_bVK() {
		return !this.P_bEK;
	}



	public boolean get_bFirmaKannUST()
	{
		return this.B_bFirmaKannUST;
	}



	public HD_FehlerBerichte get_vFehlerVector()
	{
		return vFehlerVector;
	}
	
	
	//debugging-hilfe
	public String get_WerteLesbar()
	{
		String cRueck = "";
		
		cRueck += "Station=Mandant: "+(this.B_bStationIstMandant?"Y":"N");
		cRueck += " // Land-JUR: "+this.B_IdLandJuristisch;
		cRueck += " // Land-GEO: "+this.B_IdLandGeografisch;
		cRueck += " // SORTE RC: "+(this.B_bSorteRC?"Y":"N");
		cRueck += " // SORTE PROD: "+(this.B_bSorteProdukt?"Y":"N");
		cRueck += " // SORTE EOW: "+(this.B_bSorteEoW?"Y":"N");
		cRueck += " // SORTE DIENST: "+(this.B_bSorteDienstleistung?"Y":"N");
		cRueck += " // Firma UST: "+(this.B_bFirmaKannUST?"Y":"N");
		cRueck += " // TP-Verantwortung: "+(this.B_cTP_Verantwortung);
		return cRueck;
		
	}


//	public BigDecimal get_bdAbrechnungsMenge()
//	{
//		return P_bdAbrechnungsMenge;
//	}
//
//	


	public RECORD_ADRESSE get_recAdresse_JUR()
	{
		return H_recAdresse_JUR;
	}


	public RECORD_ADRESSE get_recAdresse_GEO()
	{
		return H_recAdresse_GEO;
	}


	public RECORD_ARTIKEL get_recArtikel()
	{
		return H_recArtikel;
	}


	public String get_cTP_Verantwortung()
	{
		return B_cTP_Verantwortung;
	}


	public boolean get_bStationIstMandant()
	{
		return B_bStationIstMandant;
	}
	
	
	//eine reihe von gettern, die zur fuellung einer neuerfassungsmaske einer handelsdefinition benutzt werden
	public String get_HDMASK_IST_MANDANT()
	{
		if (this.get_bBeachteMandantenStatusDerStation()) {
			return this.B_bStationIstMandant?"Y":"N";	
		}
		return "N";
		
	}
	
	public String get_HDMASK_ID_LAND_JUR()
	{
		if (this.B_IdLandJuristisch != null)
		{
			return new MyLong(""+this.B_IdLandJuristisch, new Long(0),new Long(0)).get_cF_LongString();	
		}
		return "";
	}

	public String get_HDMASK_ID_LAND_GEO()
	{
		if (this.B_IdLandGeografisch != null)
		{
			return new MyLong(""+this.B_IdLandGeografisch, new Long(0),new Long(0)).get_cF_LongString();	
		}
		return "";
	}

	public String get_HDMASK_IST_SORTE_RC()
	{
		return this.B_bSorteRC?(""+TAX_CONST.AUSWAHL_Y_N_EGAL__Y):(""+TAX_CONST.AUSWAHL_Y_N_EGAL__N);
	}
	
	
	public String get_HDMASK_IST_PRODUKT()
	{
		return this.B_bSorteProdukt?(""+TAX_CONST.AUSWAHL_Y_N_EGAL__Y):(""+TAX_CONST.AUSWAHL_Y_N_EGAL__N);
	}

	public String get_HDMASK_IST_EOW()
	{
		return this.B_bSorteEoW?(""+TAX_CONST.AUSWAHL_Y_N_EGAL__Y):(""+TAX_CONST.AUSWAHL_Y_N_EGAL__N);
	}

	
	public String get_HDMASK_IST_DIENSTLEISTUNG()
	{
		return this.B_bSorteDienstleistung?(""+TAX_CONST.AUSWAHL_Y_N_EGAL__Y):(""+TAX_CONST.AUSWAHL_Y_N_EGAL__N);
	}
	
	public String get_HDMASK_IST_UST_TEILNEHMER()
	{
		return this.B_bFirmaKannUST?(""+TAX_CONST.AUSWAHL_Y_N_EGAL__Y):(""+TAX_CONST.AUSWAHL_Y_N_EGAL__N);
	}
	

	public String get_HDMASK_TP_VERANTWORTUNG()
	{
		if (this.B_cTP_Verantwortung != null)
		{
			return this.B_cTP_Verantwortung;	
		}
		return "";
	}

	public RECORD_VPOS_TPA_FUHRE get_P_RecFuhre() 
	{
		return P_RecFuhre;
	}


	public RECORD_VPOS_TPA_FUHRE_ORT get_P_RecFuhreOrt() 
	{
		return P_RecFuhreOrt;
	}


	public String get_cInfoTextName1_2() {
		return P_cInfoTextName1_2;
	}


	public String get_cInfoTextOrt() {
		return P_cInfoTextOrt;
	}


	public String get_cInfoTextSorte() {
		return P_cInfoTextSorte;
	}

	public String get_cInfoText_NameOrt_MengeSorte() {
		String cMenge = " ??? ";
		if (this.P_bdAbrechnungsMenge!=null) {
			cMenge = MyNumberFormater.formatDez(this.P_bdAbrechnungsMenge, 1, true, ',', '.', false);
		}
			
		
		return this.P_cInfoTextName1_2+", "+this.P_cInfoTextOrt+", "+cMenge+" "+this.P_cInfoTextSorte;
	}
	
	
	
	public boolean get_bGleicheFuhrenOderOrte(HD_Station oStationOther) throws myException {
		boolean bRueck = false;
		
		/*
		 * gleich heisst: entweder beide records sind null (bei neuerfassung fuhrenmaske oder bei bereits vorhandenen
		 * fuhren ist es entweder eine fuhrenstation oder eine fuhrenort-station (eines von beiden muss null sein
		 */
		if (this.P_RecFuhre==null && oStationOther.P_RecFuhre==null) {     //kann nur bei neuer, ungespeicherter fuhrenmaske der fall sein
			if (	this.get_bEK() && oStationOther.get_bEK() ||
					this.get_bVK() && oStationOther.get_bVK())	{
				bRueck = true;
			}			
		} else if (this.P_RecFuhre!=null && oStationOther.P_RecFuhre!=null) {
			if (this.P_RecFuhre.get_ID_VPOS_TPA_FUHRE_cUF().equals(oStationOther.P_RecFuhre.get_ID_VPOS_TPA_FUHRE_cUF()))	{
				if (	this.get_bEK() && oStationOther.get_bEK() ||
						this.get_bVK() && oStationOther.get_bVK())	{
					bRueck = true;
				}
			}
		} else if (this.P_RecFuhreOrt!=null && oStationOther.P_RecFuhreOrt!=null) {
			if (this.P_RecFuhreOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF().equals(oStationOther.P_RecFuhreOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))	{
				if (	this.get_bEK() && oStationOther.get_bEK() ||
						this.get_bVK() && oStationOther.get_bVK())	{
					bRueck = true;
				}
			}
		}
		return bRueck;
	}
	
	
	
	/**
	 * moeglichkeit, alle orte auch die mandantenzugehoerigen gleich zu behandeln
	 * @return
	 */
	private boolean get_bBeachteMandantenStatusDerStation() {
		boolean bIgnoriereMandantenstatusDerStationen = true;
		try {
			bIgnoriereMandantenstatusDerStationen = bibALL.get_RECORD_MANDANT().is_STEUERFINDUNG_OHNE_EIGENORTE_YES();
		} catch (myException e) {
			e.printStackTrace();
		}
		return !bIgnoriereMandantenstatusDerStationen;
	}
	
	
	
	
	public BigDecimal get_P_bdPreis()
	{
		return P_bdPreis;
	}

	
	/**
	 * 0 wird als positiv angesehen 
	 * @return
	 */
	public boolean get_bPreisIsPositiv() {
		return this.P_bdPreis.compareTo(BigDecimal.ZERO)>=0;
	}
	
	/**
	 * 0 wird als positiv angesehen 
	 * @return
	 */
	public boolean get_bPreisIsNegativ() {
		return !this.get_bPreisIsPositiv();
	}

	
	public MyE2_MessageVector get_oMV_FehlerHandelMoeglich() {
		return oMV_FehlerHandelMoeglich;
	}

	
	public String get_INFO_ZU_FUHRE() throws myException {
		String cRueck = "<keine Fuhreninfo möglich>";
		
		if (this.P_RecFuhre != null) {
			cRueck = "Hauptfuhre mit ID:"+this.P_RecFuhre.get_ID_VPOS_TPA_FUHRE_cUF_NN("-");
		} else if (this.P_RecFuhreOrt != null) {
			cRueck = "Zusatzort der Fuhre mit ID:"+this.P_RecFuhreOrt.get_ID_VPOS_TPA_FUHRE_cUF_NN("-");
		}
		
		return cRueck;
	}
	
	
	public MyDate getLeistungsDatum() {
		return leistungsDatum;
	}
	
	
	
}
