package rohstoff.Echo2BusinessLogic.ABZUEGE;

import java.math.BigDecimal;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibNUM;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;

public class BL_AbzugsKalkulator_AbzugZeile 
{

    
    private double 		dAusgangsMenge = 		0;
    private double 		dAusgangsEPreis = 		0;
    private double 		dAusgangsEPreis_FW = 	0;
    private String 		cABZUGSTYP	= 			null;

	private double 		dMengeVorAbzug	= 		0;
    private double 		dMengeNachAbzug	= 		0;
    private double 		dEPreisVorAbzug	= 		0;
    private double 		dEPreisVorAbzug_FW	=	0;
    private double 		dEPreisNachAbzug	= 	0;
    private double 		dEPreisNachAbzug_FW	= 	0;
    private double 		dABZUG				= 	0;
    private double 		dABZUG2				= 	0;
    private double   	dMengenDivisor  	=   1;
    
    private double		dAbzugVonMenge		= 	0;
    //2011-01-11: weitere abzugsbetrachtung: Abzug "Inhaltsanteil" wird im Lager nicht als Abzug gewertet
    private double		dAbzugVonMengeLager		= 	0;
    
    
    private double		dAbzugVonEPreis		= 	0;
    private double		dAbzugVonEPreis_FW		= 	0;
    
    private double		dAbzug_Anteil_am_GESAMT_BETRAG  	= 0;
    private double		dAbzug_Anteil_am_GESAMT_BETRAG_FW  	= 0;
   
    
    private double   	dWaehrungsKurs = 0;
    
    /*
     * um die auflistung der abzuege lesbar zu machen, mussen 2 felder
     * vorhanden sein, die die abzuege plausibel machen:
     * Mengenfaktor * preisfaktor gibt dAbzug_Anteil_am_GESAMT_BETRAG
     */
    
    private double      dMengenFaktor_fuer_Druck = 0;
    private double      dPreisFaktor_fuer_Druck = 0;
    private double      dPreisFaktor_Fuer_Druck_FW = 0;
    
    
   
    
    /*
     * wird nur in der datenbank-seitigen berechnung mitgefuehrt, damit ein kompletter updatestack erzeugt werden kann
     * in der Masken-berechnung ohne relevanz
     */
    private String    	ID_IN_ABZUG_TABLE_FORMATED  = null;
    
    
    
    private String cBasisWaehrungSymbol = null;
    private String cFremdWaehrungSymbol = null;
    private String cEinheit = null;
    private String cEinheitPreis = null;

    private String cBezeichnungMitPlatzhalter = null;
    private String cBezeichnungReinschrift = null;
    

    /**
     * @param ausgangsMenge
     * @param ausgangsEPreis
     * @param ausgangsEPreis_FW
     * @param cabzugstyp
     * @param dabzug
     * @param dabzug2
     * @param mengeVorAbzug
     * @param preisVorAbzug
     * @param preisVorAbzug_FW
     * @param mengenDivisor
     * @param cID_IN_ABZUG_TABLE_FORMATED
     * @param dWaehrungskurs   WICHTIG !!!!  ALLE ABZUGE MIT ABSOLUTEN PREISEN WERDEN IN DER FREMDWAEHRUNG EINGEGEBEN
     * @throws myException
     */
    public BL_AbzugsKalkulator_AbzugZeile(	double 	ausgangsMenge,
					                    	double 	ausgangsEPreis,
					                    	double 	ausgangsEPreis_FW,
					                    	String 	cabzugstyp,
					                    	double 	dabzug,
					                    	double  dabzug2,
					                    	double 	mengeVorAbzug,
					                    	double 	preisVorAbzug,
					                    	double 	preisVorAbzug_FW,
					                    	double  mengenDivisor,
					                    	String  cID_IN_ABZUG_TABLE_FORMATED,
					                    	double  dWaehrungskurs,
					                    	String BasisWaehrungSymbol,
		    								String FremdWaehrungSymbol,
		    								String Einheit,
		    								String EinheitPreis,
		    								String BezeichnungMitPlatzhalter,
		    								BL_AbzugsKalkulator  oAbzugsKalkulator
					                    	) throws myException
    {
        super();
        this.dAusgangsMenge = 		ausgangsMenge;
        this.dAusgangsEPreis = 		ausgangsEPreis;
        this.dAusgangsEPreis_FW = 	ausgangsEPreis_FW; 
        this.cABZUGSTYP = 			cabzugstyp;
        this.dABZUG	= 				dabzug;
        this.dABZUG2 = 				dabzug2;
        this.dMengeVorAbzug = 		mengeVorAbzug;
        this.dEPreisVorAbzug = 		preisVorAbzug;
        this.dEPreisVorAbzug_FW = 	preisVorAbzug_FW;
        this.dMengenDivisor = 		mengenDivisor;
        this.dWaehrungsKurs = 		dWaehrungskurs;
        
        this.cBasisWaehrungSymbol = BasisWaehrungSymbol;
        this.cFremdWaehrungSymbol = FremdWaehrungSymbol;
        this.cEinheit = 			S.NN(Einheit);
        this.cEinheitPreis =		S.NN(EinheitPreis);

        this.cBezeichnungMitPlatzhalter = BezeichnungMitPlatzhalter;
        
        ID_IN_ABZUG_TABLE_FORMATED = cID_IN_ABZUG_TABLE_FORMATED;
        
        
        String cAbzugFuerText = "";
        String cAbzug2FuerText = "";

        
        //Strings fuer die eintragung der vorgaenger- oder positionsmenge, sowie vorgaenger oder positionspreis
        //fuer die einblendung in die schablonentexte
        String cVorgaengerMenge = MyNumberFormater.formatDez(this.dMengeVorAbzug,3,true);
        String cAusgangsMenge = MyNumberFormater.formatDez(this.dAusgangsMenge,3,true);
        
        String cVorgaengerPreis = MyNumberFormater.formatDez(this.dAusgangsEPreis_FW,2,true);
        String cAusgangsPreis = MyNumberFormater.formatDez(this.dEPreisVorAbzug_FW,2,true);
        
        
        
        int iMengenNachkommaGenauigkeit = 3;
        if (bibALL.get_RoundAbzuegeMengen())
        	iMengenNachkommaGenauigkeit = 0;

        
        /*
         * sonderfall, einzelpreis und menge bleiben wie sie waren
         */
        if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT))
        {
       		this.dAbzug_Anteil_am_GESAMT_BETRAG_FW =  this.dABZUG;
       		this.dAbzug_Anteil_am_GESAMT_BETRAG =bibALL.Round(this.dABZUG/this.dWaehrungsKurs,2);
        	
            this.dEPreisNachAbzug=		this.dEPreisVorAbzug;
            this.dEPreisNachAbzug_FW=	this.dEPreisVorAbzug_FW;
            this.dMengeNachAbzug=		this.dMengeVorAbzug;
            
            this.dMengenFaktor_fuer_Druck = 0;
            this.dPreisFaktor_fuer_Druck  = 0;
            this.dPreisFaktor_Fuer_Druck_FW = 0;
            
            cAbzugFuerText = MyNumberFormater.formatDez(this.dABZUG, 2, true,',','.',true);
            
            
        } 
        else if  (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_BETRAG_BW_ABSOLUT))
        {
        	//2011-08-03:  Neue Abzuege fuer die fuhre mit basis-preisen
        	
       		this.dAbzug_Anteil_am_GESAMT_BETRAG_FW =  	bibALL.Round(this.dABZUG*this.dWaehrungsKurs,2);
       		this.dAbzug_Anteil_am_GESAMT_BETRAG =		this.dABZUG;
        	
            this.dEPreisNachAbzug=		this.dEPreisVorAbzug;
            this.dEPreisNachAbzug_FW=	this.dEPreisVorAbzug_FW;
            this.dMengeNachAbzug=		this.dMengeVorAbzug;
            
            this.dMengenFaktor_fuer_Druck = 0;
            this.dPreisFaktor_fuer_Druck  = 0;
            this.dPreisFaktor_Fuer_Druck_FW = 0;
            
            cAbzugFuerText = MyNumberFormater.formatDez(this.dABZUG, 2, true,',','.',true);
        }
        else
        {
        
	        
	        if (BL_CONST_ABZUG.HM_ABZUG_PLATZHALTER.containsKey(cABZUGSTYP))
	        {
	        	
	            if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_ABSOLUT))
	            {
	                this.dAbzugVonMenge=0;
	                this.dAbzugVonMengeLager=0;
	                
	               	this.dAbzugVonEPreis_FW =  	this.dABZUG;
	               	this.dAbzugVonEPreis =  	bibALL.Round(this.dABZUG/this.dWaehrungsKurs,2);
	                
	                this.dEPreisNachAbzug=this.dEPreisVorAbzug-this.dAbzugVonEPreis ;
	                this.dEPreisNachAbzug_FW=this.dEPreisVorAbzug_FW-this.dAbzugVonEPreis_FW ;
	                
	                this.dMengeNachAbzug=this.dMengeVorAbzug;
	
	                this.dMengenFaktor_fuer_Druck = this.dMengeNachAbzug;
	
	           		this.dPreisFaktor_fuer_Druck  		= this.dAbzugVonEPreis;
	           		this.dPreisFaktor_Fuer_Druck_FW 	= this.dAbzugVonEPreis_FW;
	           		
	        		this.dAbzug_Anteil_am_GESAMT_BETRAG = bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_fuer_Druck/this.dMengenDivisor,2);
	        		this.dAbzug_Anteil_am_GESAMT_BETRAG_FW = bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_Fuer_Druck_FW/this.dMengenDivisor,2);

	                cAbzugFuerText = MyNumberFormater.formatDez(this.dABZUG, 2, true,',','.',true);
	            }

	        	//2011-08-03:  Neue Abzuege fuer die fuhre mit basis-preisen
	            if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_BW_ABSOLUT))
	            {
	                this.dAbzugVonMenge=0;
	                this.dAbzugVonMengeLager=0;
	                
	               	this.dAbzugVonEPreis_FW =  	bibALL.Round(this.dABZUG*this.dWaehrungsKurs,2);;
	               	this.dAbzugVonEPreis =  	this.dABZUG;
	                
	                this.dEPreisNachAbzug=this.dEPreisVorAbzug-this.dAbzugVonEPreis ;
	                this.dEPreisNachAbzug_FW=this.dEPreisVorAbzug_FW-this.dAbzugVonEPreis_FW ;
	                
	                this.dMengeNachAbzug=this.dMengeVorAbzug;
	
	                this.dMengenFaktor_fuer_Druck = this.dMengeNachAbzug;
	
	           		this.dPreisFaktor_fuer_Druck  		= this.dAbzugVonEPreis;
	           		this.dPreisFaktor_Fuer_Druck_FW 	= this.dAbzugVonEPreis_FW;
	           		
	        		this.dAbzug_Anteil_am_GESAMT_BETRAG = bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_fuer_Druck/this.dMengenDivisor,2);
	        		this.dAbzug_Anteil_am_GESAMT_BETRAG_FW = bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_Fuer_Druck_FW/this.dMengenDivisor,2);

	                cAbzugFuerText = MyNumberFormater.formatDez(this.dABZUG, 2, true,',','.',true);
	            }

	            
	            if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_MENGE_ABSOLUT))
	            {
	                this.dAbzugVonMenge=this.dABZUG;
	                this.dAbzugVonMengeLager=this.dABZUG;
	                
	                this.dAbzugVonEPreis = 0;
	                this.dAbzugVonEPreis_FW = 0;
	                
	                this.dEPreisNachAbzug=this.dEPreisVorAbzug;
	                this.dEPreisNachAbzug_FW=this.dEPreisVorAbzug_FW;
	                
	                this.dMengeNachAbzug=this.dMengeVorAbzug-this.dAbzugVonMenge;
	
	                this.dMengenFaktor_fuer_Druck = this.dAbzugVonMenge;
	                this.dPreisFaktor_fuer_Druck  = this.dEPreisNachAbzug;
	                this.dPreisFaktor_Fuer_Druck_FW  = this.dEPreisNachAbzug_FW;
	
	                this.dAbzug_Anteil_am_GESAMT_BETRAG = 	bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_fuer_Druck/this.dMengenDivisor,2);
	                this.dAbzug_Anteil_am_GESAMT_BETRAG_FW = bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_Fuer_Druck_FW/this.dMengenDivisor,2);
	                
	                cAbzugFuerText = MyNumberFormater.formatDez(this.dABZUG, 3, true,',','.',true);

	                
	            }
	            if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AKTUELLPREIS))
	            {
	                if (this.dABZUG>100)
	                    throw new myExceptionForUser("Bitte nicht mehr als 100 % abziehen !!");
	
	                this.dAbzugVonMenge	=	0;
	                this.dAbzugVonMengeLager = 0;
	                
	                this.dAbzugVonEPreis  = 	bibALL.Round((this.dABZUG/100)*this.dEPreisVorAbzug,2);
	                this.dAbzugVonEPreis_FW  = 	bibALL.Round((this.dABZUG/100)*this.dEPreisVorAbzug_FW,2);
	                
	                this.dEPreisNachAbzug=		this.dEPreisVorAbzug-this.dAbzugVonEPreis;
	                this.dEPreisNachAbzug_FW=	this.dEPreisVorAbzug_FW-this.dAbzugVonEPreis_FW;
	                
	                this.dMengeNachAbzug=this.dMengeVorAbzug;
	                
	                this.dMengenFaktor_fuer_Druck = this.dMengeNachAbzug;
	                this.dPreisFaktor_fuer_Druck  = this.dAbzugVonEPreis;
	                this.dPreisFaktor_Fuer_Druck_FW  = this.dAbzugVonEPreis_FW;
	
	                this.dAbzug_Anteil_am_GESAMT_BETRAG = 		bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_fuer_Druck/this.dMengenDivisor,2);
	                this.dAbzug_Anteil_am_GESAMT_BETRAG_FW = 	bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_Fuer_Druck_FW/this.dMengenDivisor,2);

	                cAbzugFuerText = MyNumberFormater.formatDez(this.dABZUG, 4, true,',','.',true);

	            }
	            
	            if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AUSGANGSPREIS))
	            {
	                if (this.dABZUG>100)
	                    throw new myExceptionForUser("Bitte nicht mehr als 100 % abziehen !!");
	
	                this.dAbzugVonMenge	=	0;
	                this.dAbzugVonMengeLager = 0;
	
	                this.dAbzugVonEPreis  = 	bibALL.Round((this.dABZUG/100)*this.dAusgangsEPreis,2);
	                this.dAbzugVonEPreis_FW  = 	bibALL.Round((this.dABZUG/100)*this.dAusgangsEPreis_FW,2);
	                
	                this.dEPreisNachAbzug=		this.dEPreisVorAbzug-this.dAbzugVonEPreis;
	                this.dEPreisNachAbzug_FW=	this.dEPreisVorAbzug_FW-this.dAbzugVonEPreis_FW;
	                
	                this.dMengeNachAbzug=this.dMengeVorAbzug;
	                
	                this.dMengenFaktor_fuer_Druck = this.dMengeNachAbzug;
	                
	                this.dPreisFaktor_fuer_Druck  = this.dAbzugVonEPreis;
	                this.dPreisFaktor_Fuer_Druck_FW  = this.dAbzugVonEPreis_FW;
	
	                this.dAbzug_Anteil_am_GESAMT_BETRAG = bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_fuer_Druck/this.dMengenDivisor,2);
	                this.dAbzug_Anteil_am_GESAMT_BETRAG_FW = bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_Fuer_Druck_FW/this.dMengenDivisor,2);
	                
	                cAbzugFuerText = MyNumberFormater.formatDez(this.dABZUG, 4, true,',','.',true);
	            }


	            if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG))
	            {
	                if (this.dABZUG>100)
	                    throw new myExceptionForUser("Es können nicht mehr als 100 % vorausgezahlt werden !!");
	
	                this.dAbzugVonMenge	=	0;
	                this.dAbzugVonMengeLager = 0;

	                //2011-01-31: abzug fuer vorauszahlung wird nicht mehr von basis-ausgangspreis sondern vom reduzierten ausgangspreis berechnet !!!
//	                this.dAbzugVonEPreis  = 	bibALL.Round(((100-this.dABZUG)/100)*this.dAusgangsEPreis,2);
//	                this.dAbzugVonEPreis_FW  = 	bibALL.Round(((100-this.dABZUG)/100)*this.dAusgangsEPreis_FW,2);
	                this.dAbzugVonEPreis  = 	bibALL.Round(((100-this.dABZUG)/100)*this.dEPreisVorAbzug,2);
	                this.dAbzugVonEPreis_FW  = 	bibALL.Round(((100-this.dABZUG)/100)*this.dEPreisVorAbzug_FW,2);

	                
	                this.dEPreisNachAbzug=		this.dEPreisVorAbzug-this.dAbzugVonEPreis;
	                this.dEPreisNachAbzug_FW=	this.dEPreisVorAbzug_FW-this.dAbzugVonEPreis_FW;
	                
	                this.dMengeNachAbzug=this.dMengeVorAbzug;
	                
	                this.dMengenFaktor_fuer_Druck = this.dMengeNachAbzug;
	                
	                this.dPreisFaktor_fuer_Druck  = this.dAbzugVonEPreis;
	                this.dPreisFaktor_Fuer_Druck_FW  = this.dAbzugVonEPreis_FW;
	
	                this.dAbzug_Anteil_am_GESAMT_BETRAG = bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_fuer_Druck/this.dMengenDivisor,2);
	                this.dAbzug_Anteil_am_GESAMT_BETRAG_FW = bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_Fuer_Druck_FW/this.dMengenDivisor,2);
	                
	                cAbzugFuerText = MyNumberFormater.formatDez(this.dABZUG, 4, true,',','.',true);
	            }


	            
	            //2011-04-19: neue vorauszahlung, wird komplett anders behandelt und steht immer am ende einer abzugszeile
	            if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG_NG))
	            {
	                if (this.dABZUG>100)
	                    throw new myExceptionForUser("Es können nicht mehr als 100 % vorausgezahlt werden !!");
	
	                this.dAbzugVonMenge	=	   0;
	                this.dAbzugVonMengeLager = 0;

	                
	                this.dAbzugVonEPreis  = 	0;
	                this.dAbzugVonEPreis_FW  = 	0;

	                
	                this.dEPreisNachAbzug=		this.dEPreisVorAbzug;
	                this.dEPreisNachAbzug_FW=	this.dEPreisVorAbzug_FW;
	                
	                this.dMengeNachAbzug=this.dMengeVorAbzug;
	                
	
	                //anteil am gesamten abzug ist basierend auf dem vorigen gesamten postionsbetrag
	                double dBetragVorDiesemAbzug = 		oAbzugsKalkulator.get_BETRAG_OHNE_VORAUSZAHLUNGSABZUG();
	                double dBetragVorDiesemAbzug_FW = 	oAbzugsKalkulator.get_BETRAG_OHNE_VORAUSZAHLUNGSABZUG_FW();
	                
	                this.dAbzug_Anteil_am_GESAMT_BETRAG = 		bibALL.Round(((100-this.dABZUG)/100)*dBetragVorDiesemAbzug,2);
	                this.dAbzug_Anteil_am_GESAMT_BETRAG_FW = 	bibALL.Round(((100-this.dABZUG)/100)*dBetragVorDiesemAbzug_FW,2);
	                
	                this.dMengenFaktor_fuer_Druck = 1;
	                
	                this.dPreisFaktor_fuer_Druck  = 	this.dAbzug_Anteil_am_GESAMT_BETRAG;
	                this.dPreisFaktor_Fuer_Druck_FW  = 	this.dAbzug_Anteil_am_GESAMT_BETRAG_FW;
	                
	                cAbzugFuerText = MyNumberFormater.formatDez(this.dABZUG, 4, true,',','.',true);
	            }
	            
	            
	            
	            
	            
	            if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AKTUELLEMENGE) || 
	            	cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AKTUELLEMENGE)	|| 
	        		cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE) ||
	        		cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AKTUELLEMENGE)
	        		)
	            {
	                if (this.dABZUG>100)
	                    throw new myExceptionForUser("Mehr als 100 % sind nicht möglich !!");
	                
	                if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE))
	                {
	                	if (100-(this.dABZUG-this.dABZUG2)>=100)
	                	{
	                		throw new myExceptionForUser("Mehr als 100 % Abzug sind nicht möglich !!");
	                	}
	                }
	                		
	                if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AKTUELLEMENGE))
	                {
	                	this.dAbzugVonMenge				=	bibALL.Round((this.dABZUG/100)*this.dMengeVorAbzug,iMengenNachkommaGenauigkeit);
	                	this.dAbzugVonMengeLager		=	bibALL.Round((this.dABZUG/100)*this.dMengeVorAbzug,iMengenNachkommaGenauigkeit);
	                }
	                else if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AKTUELLEMENGE))
	                {
	                	this.dAbzugVonMenge				=	bibALL.Round(((100-this.dABZUG)/100)*this.dMengeVorAbzug,iMengenNachkommaGenauigkeit);
	                	this.dAbzugVonMengeLager		=	0;
	                }
	                else if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE))
	                {
	                	this.dAbzugVonMenge		=			bibALL.Round(((100-(this.dABZUG-this.dABZUG2))/100)*this.dMengeVorAbzug,iMengenNachkommaGenauigkeit);
	                	this.dAbzugVonMengeLager		=	0;
	                }
	                else if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AKTUELLEMENGE))   //wird gerechnet wie eine menge, aber bleibt im stoff (wird behandelt wie ein Inhaltsabzug)
	                {
	                	this.dAbzugVonMenge		=			bibALL.Round((this.dABZUG/100)*this.dMengeVorAbzug,iMengenNachkommaGenauigkeit);
	                	this.dAbzugVonMengeLager		=	0;
	                }
	                else
	                {
	                	throw new myException(this,"Abzugstyp <"+cABZUGSTYP+"> not allowed (1)!");
	                }
	                
	                this.dAbzugVonEPreis  		= 	0;
	                this.dAbzugVonEPreis_FW  	= 	0;
	                
	                this.dEPreisNachAbzug=this.dEPreisVorAbzug;
	                this.dEPreisNachAbzug_FW=this.dEPreisVorAbzug_FW;
	                
	                this.dMengeNachAbzug=this.dMengeVorAbzug-this.dAbzugVonMenge;
	                
	                //this.dAbzug_Anteil_am_GESAMT_BETRAG = this.calc_abzugAnteil();
	
	                this.dMengenFaktor_fuer_Druck = this.dAbzugVonMenge;
	                this.dPreisFaktor_fuer_Druck  = this.dEPreisNachAbzug;
	                this.dPreisFaktor_Fuer_Druck_FW  = this.dEPreisNachAbzug_FW;
	
	                this.dAbzug_Anteil_am_GESAMT_BETRAG = bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_fuer_Druck/this.dMengenDivisor,2);
	                this.dAbzug_Anteil_am_GESAMT_BETRAG_FW = bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_Fuer_Druck_FW/this.dMengenDivisor,2);

	                cAbzugFuerText = MyNumberFormater.formatDez(this.dABZUG, 4, true,',','.',true);
	                cAbzug2FuerText = MyNumberFormater.formatDez(this.dABZUG2, 4, true,',','.',true);

	                
	            }
	            if (	cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AUSGANGSMENGE) || 
	            		cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AUSGANGSMENGE) || 
	            		cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE) ||
	        	      	cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AUSGANGSMENGE)) 
	            {
	                if (this.dABZUG>100)
	                    throw new myExceptionForUser("Mehr als 100 % sind nicht möglich !!");
	
	                if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE))
	                {
	                	if (100-(this.dABZUG-this.dABZUG2)>=100)
	                	{
	                		throw new myExceptionForUser("Mehr als 100 % Abzug sind nicht möglich !!");
	                	}
	                }
	
	                
	                if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AUSGANGSMENGE))
	                {
	                	this.dAbzugVonMenge				=	bibALL.Round((this.dABZUG/100)*this.dAusgangsMenge,iMengenNachkommaGenauigkeit);
	                	this.dAbzugVonMengeLager		=	bibALL.Round((this.dABZUG/100)*this.dAusgangsMenge,iMengenNachkommaGenauigkeit);
	                }
	                else if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AUSGANGSMENGE))
	                {
	                	this.dAbzugVonMenge				=	bibALL.Round(((100-this.dABZUG)/100)*this.dAusgangsMenge,iMengenNachkommaGenauigkeit);
	                	this.dAbzugVonMengeLager		=	0;
	                }
	                else if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE))
	                {
	                	this.dAbzugVonMenge				=	bibALL.Round(((100-(this.dABZUG-this.dABZUG2))/100)*this.dAusgangsMenge,iMengenNachkommaGenauigkeit);
	                	this.dAbzugVonMengeLager		=	0;
	                }
	                else if (cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AUSGANGSMENGE))
	                {
	                	this.dAbzugVonMenge				=	bibALL.Round((this.dABZUG/100)*this.dAusgangsMenge,iMengenNachkommaGenauigkeit);
	                	this.dAbzugVonMengeLager		=	0;
	                }
	                else
	                {
	                	throw new myException(this,"Abzugstyp <"+cABZUGSTYP+"> not allowed (1)!");
	                }
	                
	                this.dAbzugVonEPreis  	= 	0;
	                this.dAbzugVonEPreis_FW	= 	0;
	                
	                this.dEPreisNachAbzug=this.dEPreisVorAbzug;
	                this.dEPreisNachAbzug_FW=this.dEPreisVorAbzug_FW;
	                
	                this.dMengeNachAbzug=this.dMengeVorAbzug-this.dAbzugVonMenge;
	                
	                //this.dAbzug_Anteil_am_GESAMT_BETRAG = this.calc_abzugAnteil();
	                
	                this.dMengenFaktor_fuer_Druck = this.dAbzugVonMenge;
	                this.dPreisFaktor_fuer_Druck  = this.dEPreisNachAbzug;
	                this.dPreisFaktor_Fuer_Druck_FW  = this.dEPreisNachAbzug_FW;
	                
	                this.dAbzug_Anteil_am_GESAMT_BETRAG = 		bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_fuer_Druck   / this.dMengenDivisor,2);
	                this.dAbzug_Anteil_am_GESAMT_BETRAG_FW = 	bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_Fuer_Druck_FW/this.dMengenDivisor,2);

	                cAbzugFuerText = MyNumberFormater.formatDez(this.dABZUG, 4, true,',','.',true);
	                cAbzug2FuerText = MyNumberFormater.formatDez(this.dABZUG2, 4, true,',','.',true);

	            }
	            
	            //2015-12-07: neuer abzugstyp: Absolute mengen anhaftend 
	            if (	cABZUGSTYP.equals(BL_CONST_ABZUG.ABZUGTYP_MENGE_ANHAFTEND) )   {
	            	BigDecimal bdMengeVorAbzug = 	new MyBigDecimal(dMengeVorAbzug, 3).get_bdWert();
	            	BigDecimal bdMengeAbzug = 		new MyBigDecimal(this.dABZUG, 3).get_bdWert();
	            	if (bdMengeAbzug.abs().compareTo(bdMengeVorAbzug.abs())>0) {
	            		throw new myExceptionForUser("Sie ziehen mehr ab als vorhanden ist !!");
	            	}

                	this.dAbzugVonMenge				=	this.dABZUG;
                	this.dAbzugVonMengeLager		=	0;                     //bleibt auf lager
                	
	                this.dAbzugVonEPreis  	= 	0;
	                this.dAbzugVonEPreis_FW	= 	0;
	                
	                this.dEPreisNachAbzug=this.dEPreisVorAbzug;
	                this.dEPreisNachAbzug_FW=this.dEPreisVorAbzug_FW;
	                
	                this.dMengeNachAbzug=this.dMengeVorAbzug-this.dAbzugVonMenge;
                
	                this.dMengenFaktor_fuer_Druck = this.dAbzugVonMenge;
	                this.dPreisFaktor_fuer_Druck  = this.dEPreisNachAbzug;
	                this.dPreisFaktor_Fuer_Druck_FW  = this.dEPreisNachAbzug_FW;
	                
	                this.dAbzug_Anteil_am_GESAMT_BETRAG = 		bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_fuer_Druck   / this.dMengenDivisor,2);
	                this.dAbzug_Anteil_am_GESAMT_BETRAG_FW = 	bibALL.Round(this.dMengenFaktor_fuer_Druck*this.dPreisFaktor_Fuer_Druck_FW/this.dMengenDivisor,2);

	                cAbzugFuerText = MyNumberFormater.formatDez(this.dABZUG, 4, true,',','.',true);
	                cAbzug2FuerText = MyNumberFormater.formatDez(this.dABZUG2, 4, true,',','.',true);

	            }
	            //2015-12-07   --- ende : neuer abzugstyp: Absolute mengen anhaftend 
	            
	        }
	        else
	        {
	            throw new myException("VorgangsPositionsAbzuege:AbzugZeile: Type of ABZUG not allowed !!!");
	        }
	      }
        
        //aus der beschreibungszeile alle platzhalter entfernen
        /*
        platzhalter:  #EH# = Mengeneinheit   
        	#EHP# = preiseinheit    
        	#BW# = Basiswährung    
        	#FW# = Fremdwährung
        	#PM# = Positionsmenge
            #LM# = Laufende Menge
            #PP" = Positionspreis
            "LP" = Laufender Preis  
        	#EINGABE#=Mengenfeld 1    
        	#EINGABE2#=Mengenfeld 2
        */
        this.cBezeichnungReinschrift =  bibALL.ReplaceTeilString( this.cBezeichnungMitPlatzhalter, "#EH#", this.cEinheit);
        this.cBezeichnungReinschrift =  bibALL.ReplaceTeilString( this.cBezeichnungReinschrift, "#EHP#", this.cEinheitPreis);
        this.cBezeichnungReinschrift =  bibALL.ReplaceTeilString( this.cBezeichnungReinschrift, "#BW#", this.cBasisWaehrungSymbol);
        this.cBezeichnungReinschrift =  bibALL.ReplaceTeilString( this.cBezeichnungReinschrift, "#FW#", this.cFremdWaehrungSymbol);
        this.cBezeichnungReinschrift =  bibALL.ReplaceTeilString( this.cBezeichnungReinschrift, "#EINGABE#", cAbzugFuerText);
        this.cBezeichnungReinschrift =  bibALL.ReplaceTeilString( this.cBezeichnungReinschrift, "#EINGABE2#", cAbzug2FuerText);

        this.cBezeichnungReinschrift =  bibALL.ReplaceTeilString( this.cBezeichnungReinschrift, "#PM#", cAusgangsMenge);
        this.cBezeichnungReinschrift =  bibALL.ReplaceTeilString( this.cBezeichnungReinschrift, "#LM#", cVorgaengerMenge);
        this.cBezeichnungReinschrift =  bibALL.ReplaceTeilString( this.cBezeichnungReinschrift, "#PP#", cAusgangsPreis);
        this.cBezeichnungReinschrift =  bibALL.ReplaceTeilString( this.cBezeichnungReinschrift, "#LP#", cVorgaengerPreis);
 
    }
    
    
    public double get_dAbzugVonEPreis()      				{     	return dAbzugVonEPreis;   					}
    public double get_dAbzugVonEPreis_FW()    				{     	return dAbzugVonEPreis_FW; 					}
    public double get_dAbzugVonMenge()  	  			 	{ 		return dAbzugVonMenge;   					}
    public double get_dAbzugVonMengeLager()  	  		 	{ 		return dAbzugVonMengeLager;   					}
    public double get_dEPreisNachAbzug()    				{		return dEPreisNachAbzug;    				}
    public double get_dEPreisNachAbzug_FW()    				{		return dEPreisNachAbzug_FW;    				}
    public double get_dMengeNachAbzug()    					{		return dMengeNachAbzug;    					}
    public double get_dAbzug_Anteil_am_GESAMT_BETRAG()  	{  		return this.dAbzug_Anteil_am_GESAMT_BETRAG; }
    public double get_dAbzug_Anteil_am_GESAMT_BETRAG_FW()  	{  		return this.dAbzug_Anteil_am_GESAMT_BETRAG_FW; }


    
    public String get_UpdateStatement__Fuer_RechnungGutschrift_ABZUEGE(String cTableNameAbzuege) throws myException
    {
    	if (bibALL.isEmpty(this.ID_IN_ABZUG_TABLE_FORMATED))
    		throw new myException(this,"Error creating UpdateStatement !");
    		
    	DotFormatterGermanFixed oDF = new DotFormatterGermanFixed(this.ID_IN_ABZUG_TABLE_FORMATED);
    	if (!oDF.doFormat())
    		throw new myException(this,"Error creating UpdateStatement !");
    	
    	MySqlStatementBuilder oSQL = new MySqlStatementBuilder();
    	oSQL.addSQL_Paar("MENGE_VOR_ABZUG",MyNumberFormater.formatDezForDATABASE(this.dMengeVorAbzug, 3),false);
    	oSQL.addSQL_Paar("MENGE_NACH_ABZUG",MyNumberFormater.formatDezForDATABASE(this.dMengeNachAbzug, 3),false);
    	oSQL.addSQL_Paar("EPREIS_VOR_ABZUG",MyNumberFormater.formatDezForDATABASE(this.dEPreisVorAbzug, 3),false);
    	oSQL.addSQL_Paar("EPREIS_VOR_ABZUG_FW",MyNumberFormater.formatDezForDATABASE(this.dEPreisVorAbzug_FW, 3),false);
    	oSQL.addSQL_Paar("EPREIS_NACH_ABZUG",MyNumberFormater.formatDezForDATABASE(this.dEPreisNachAbzug, 3),false);
    	oSQL.addSQL_Paar("EPREIS_NACH_ABZUG_FW",MyNumberFormater.formatDezForDATABASE(this.dEPreisNachAbzug_FW, 3),false);
    	oSQL.addSQL_Paar("ANTEIL_ABZUG_GESAMT",MyNumberFormater.formatDezForDATABASE(this.dAbzug_Anteil_am_GESAMT_BETRAG, 2),false);
    	oSQL.addSQL_Paar("ANTEIL_ABZUG_GESAMT_FW",MyNumberFormater.formatDezForDATABASE(this.dAbzug_Anteil_am_GESAMT_BETRAG_FW, 2),false);
    	
    	oSQL.addSQL_Paar("MENGENFAKTOR_FUER_DRUCK",MyNumberFormater.formatDezForDATABASE(this.dMengenFaktor_fuer_Druck, 2),false);
    	oSQL.addSQL_Paar("PREISFAKTOR_FUER_DRUCK",MyNumberFormater.formatDezForDATABASE(this.dPreisFaktor_fuer_Druck, 2),false);
    	oSQL.addSQL_Paar("PREISFAKTOR_FUER_DRUCK_FW",MyNumberFormater.formatDezForDATABASE(this.dPreisFaktor_Fuer_Druck_FW, 2),false);
    	
    	oSQL.addSQL_Paar("ABZUG_BELEGTEXT",this.cBezeichnungReinschrift,true);
    	
    	String cSQL = oSQL.get_CompleteUPDATEString(cTableNameAbzuege, 
    			bibE2.cTO(),"ID"+cTableNameAbzuege.substring(2)+"="+oDF.getStringUnFormated(), null);
    	
    	return cSQL;
    }


	public double get_dMengenFaktor()
	{
		return dMengenFaktor_fuer_Druck;
	}


	public double get_dPreisFaktor()
	{
		return dPreisFaktor_fuer_Druck;
	}


	public double get_dPreisFaktor_FW()
	{
		return dPreisFaktor_Fuer_Druck_FW;
	}


	public String get_cBezeichnungReinschrift()
	{
		return cBezeichnungReinschrift;
	}
    
	
    public String get_cABZUGSTYP()
	{
		return cABZUGSTYP;
	}


    
    
    

}
