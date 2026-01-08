package rohstoff.Echo2BusinessLogic.ABZUEGE;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Vector;

import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.maggie.DotFormatter;


/**
 * @author martin
 * klasse zuer berechnung der abzuege einer vorgangsposition
 */
public class BL_AbzugsKalkulator
{

	//2011-04-13: weitere abzugskalkulation: Anteil Abzug (Betrag) aufgrund Mengenabzuegen  (reduziert Menge von Brutto auf Nettomenge)
	//                                       Anteil Abzug (Betrag) aufgrund Inhaltsabzuegen (reduziert nicht die Nettomenge, nur den Preis)
	//                                       Anteil Abzug (Betrag) aufgrund Vorauszahlungsabzug (wird separat behandelt)

	private Vector<String>    vAbzugsTypen_Betrag_wegen_realemMengenAbzug = bibALL.get_Vector(
												    							BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AUSGANGSMENGE,
												    							BL_CONST_ABZUG.ABZUGTYP_MENGE_PROZENT_AUF_AKTUELLEMENGE,
												    							BL_CONST_ABZUG.ABZUGTYP_MENGE_ABSOLUT
												    							); 
    
    private Vector<String>    vAbzugsTypen_Betrag_auf_NettoMenge = 			bibALL.get_Vector(
																				BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AUSGANGSPREIS,
																				BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_AUF_AKTUELLPREIS,
																				BL_CONST_ABZUG.ABZUGTYP_EPREIS_ABSOLUT,
																				BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT,
																				BL_CONST_ABZUG.ABZUGTYP_EPREIS_BW_ABSOLUT,
																				BL_CONST_ABZUG.ABZUGTYP_BETRAG_BW_ABSOLUT,
																				BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AUSGANGSMENGE,
																				BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_AUF_AKTUELLEMENGE,
																				BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE,
																				BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE,
																				
																				//2011-06-09: neue abzuege
												    							BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AUSGANGSMENGE,
												    							BL_CONST_ABZUG.ABZUGTYP_MENGE_WIE_INHALT_PROZENT_AUF_AKTUELLEMENGE,
																				
												    							//2015-12-07: neuer abzug menge absolut anhaftend
												    							BL_CONST_ABZUG.ABZUGTYP_MENGE_ANHAFTEND,
												    							
																				BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG                          //alte vorauszahlung wird wie ein herkoemmlicher abzug behandelt
																				);
    
    private Vector<String>    vAbzugsTypen_Betrag_auf_Vorauszahlung = 			bibALL.get_Vector(
    																			BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG_NG 
																				);

	
    private double dAusgangsMenge = 			0;
    private double dAusgangsEPreis = 			0;
    private double dAusgangsEPreis_FW = 		0;
    private double dMengenDivisor = 			0;
    private double dWaehrungskurs = 			0;
    
    private Vector<BL_AbzugsKalkulator_AbzugZeile> vAbzugsZeilen = new Vector<BL_AbzugsKalkulator_AbzugZeile>();
    
    
    private String cBasisWaehrungSymbol = null;
    private String cFremdWaehrungSymbol = null;
    private String cEinheit = null;
	private String cEinheitPreis = null;

    //neue AbzugsBerechnungen
    
    
    
    /**
     * @param ausgangsMengeFormatiert
     * @param ausgangsEPreisFormatiert
     * @param ausgangsEPreisFormatiert_FW
     * @param cMengenDivisorFormatiert
     * @param cWaehrungskursFormatiert
     * @param BasisWaehrungSymbol
     * @param FremdWaehrungSymbol
     * @param Einheit
     * @param EinheitPreis
     * @throws myException
     */
    public BL_AbzugsKalkulator(		String ausgangsMengeFormatiert, 
    								String ausgangsEPreisFormatiert, 
    								String ausgangsEPreisFormatiert_FW,
    								String cMengenDivisorFormatiert,
    								String cWaehrungskursFormatiert,
    								String BasisWaehrungSymbol,
    								String FremdWaehrungSymbol,
    								String Einheit,
    								String EinheitPreis) throws myException
    {
        super();
        DotFormatter oDFAusgangsMenge = 			new DotFormatter(ausgangsMengeFormatiert,3,Locale.GERMAN,true,3);
        DotFormatter oDFAusgangsEPreis = 			new DotFormatter(ausgangsEPreisFormatiert,2,Locale.GERMAN,true,3);
        DotFormatter oDFAusgangsEPreisFW = 			new DotFormatter(ausgangsEPreisFormatiert_FW,2,Locale.GERMAN,true,3);
        DotFormatter oDFMengenDivisor = 			new DotFormatter(cMengenDivisorFormatiert,4,Locale.GERMAN,true,3);
        DotFormatter oDFWaehrungskursFormatiert = 	new DotFormatter(cWaehrungskursFormatiert,4,Locale.GERMAN,true,3);
        
        this.cBasisWaehrungSymbol = BasisWaehrungSymbol;
        this.cFremdWaehrungSymbol = FremdWaehrungSymbol;
        this.cEinheit = 			S.NN(Einheit);
        this.cEinheitPreis =		S.NN(EinheitPreis);
        
        if (!oDFAusgangsMenge.doFormat() | !oDFAusgangsEPreis.doFormat() | !oDFAusgangsEPreisFW.doFormat() | !oDFMengenDivisor.doFormat() | !oDFWaehrungskursFormatiert.doFormat())
            throw new myException("VorgangsPositionsAbzuege: Keine korrekten Ausgangswerte (Menge/Einzelpreis/MengenDivisor)");

        if (bibALL.isEmpty(this.cBasisWaehrungSymbol) || bibALL.isEmpty(this.cFremdWaehrungSymbol) ||bibALL.isEmpty(this.cEinheit) ||bibALL.isEmpty(this.cEinheitPreis))
        	throw new myException("VorgangsPositionsAbzuege: Keine korrekten Ausgangswerte Währungssymbole / Einheiten");
        
        
        
        this.dAusgangsMenge = oDFAusgangsMenge.getDoubleValue();
        this.dAusgangsEPreis = oDFAusgangsEPreis.getDoubleValue();
        this.dAusgangsEPreis_FW = oDFAusgangsEPreisFW.getDoubleValue();
        this.dMengenDivisor = oDFMengenDivisor.getDoubleValue();
        this.dWaehrungskurs = oDFWaehrungskursFormatiert.getDoubleValue();
        
        if (this.dMengenDivisor == 0)
        	throw new myException("VorgangsPositionsAbzuege: Mengendivisor 0 ist nicht erlaubt !");
            
    }

    

    /**
     * @param cABZUGTYP
     * @param cMengeVorAbzugFORMATTED
     * @param cEPreisVorAbzugFORMATTED
     * @param cEPreisVorAbzugFORMATTED_FW
     * @param cAbzugFORMATTED
     * @param cAbzug2FORMATTED
     * @param cID_IN_ABZUG_TABLE_FORMATTED
     * @param cBezeichungMitPlatzhalter
     * @throws myException
     */
    public BL_AbzugsKalkulator_AbzugZeile add_AbzugsKalkulationsZeile(	String cABZUGTYP,
									    								String cMengeVorAbzugFORMATTED, 
									    								String cEPreisVorAbzugFORMATTED, 
									    								String cEPreisVorAbzugFORMATTED_FW,
									    								String cAbzugFORMATTED,
									    								String cAbzug2FORMATTED,
									    								String cID_IN_ABZUG_TABLE_FORMATTED,
									    								String cBezeichungMitPlatzhalter) throws myException
    {
        DotFormatter oDFMengeVorAbzug = 	new DotFormatter(cMengeVorAbzugFORMATTED,3,Locale.GERMAN,true,3);
        DotFormatter oDFEPreisVorAbzug = 	new DotFormatter(cEPreisVorAbzugFORMATTED,2,Locale.GERMAN,true,3);
        DotFormatter oDFEPreisVorAbzug_FW = new DotFormatter(cEPreisVorAbzugFORMATTED_FW,2,Locale.GERMAN,true,3);
        
        //2011-10-11: nachkommastellen des abzugs auf 4 (wie in der datenbank)
        DotFormatter oDFEAbzug = 			new DotFormatter(cAbzugFORMATTED,4,Locale.GERMAN,true,3);
        
        String cAbzug2Help = cAbzug2FORMATTED;
        
        if (bibALL.isEmpty(cAbzug2Help) && 
        	(cABZUGTYP.equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE) || 
             cABZUGTYP.equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE)))
        {
        	throw new myExceptionForUser("Bitte den 2. Abzugswert einfügen !");
        }

        if (bibALL.isEmpty(cAbzug2Help))
        {
        	cAbzug2Help = "0";                      //damit alles glattlaeuft fuer die einwertigen abzuege
        }

        
        //2011-10-11: nachkommastellen des abzugs auf 4 (wie in der datenbank)
        DotFormatter oDFEAbzug2 = 			new DotFormatter(cAbzug2Help,4,Locale.GERMAN,true,3);
        
        
        if (oDFMengeVorAbzug.doFormat() 	& oDFEPreisVorAbzug.doFormat() & oDFEPreisVorAbzug_FW.doFormat() & 
        	oDFEAbzug.doFormat()		 	& oDFEAbzug2.doFormat())
        {
        	BL_AbzugsKalkulator_AbzugZeile oAbzug = new BL_AbzugsKalkulator_AbzugZeile(	
        													this.dAusgangsMenge,
			        										this.dAusgangsEPreis,
			        										this.dAusgangsEPreis_FW,
			        										cABZUGTYP,
			        										oDFEAbzug.getDoubleValue(),
			        										oDFEAbzug2.getDoubleValue(),
			        										oDFMengeVorAbzug.getDoubleValue(),
			        										oDFEPreisVorAbzug.getDoubleValue(),
			        										oDFEPreisVorAbzug_FW.getDoubleValue(),
			        										this.dMengenDivisor,
			        										cID_IN_ABZUG_TABLE_FORMATTED,
			        										this.dWaehrungskurs,
			        										this.cBasisWaehrungSymbol,
			        										this.cFremdWaehrungSymbol,
			        										this.cEinheit,
			        										this.cEinheitPreis,
			        										cBezeichungMitPlatzhalter,
			        										this);
            this.vAbzugsZeilen.add(oAbzug);
            return oAbzug;
        }
        else
        {
            throw new myException("VorgangsPositionsAbzuege:add_AbzugsZeile:Fehler in den Werten !");
        }
    }


    public String get_cEinheit()
	{
		return cEinheit;
	}
    public String get_cEinheit_Preis()
	{
		return cEinheitPreis;
	}
    
    
    
    
    public double get_GESAMT_EPREIS_ABZUG()
    {
        double dRueck = 0;
        
        for (int i=0;i<this.vAbzugsZeilen.size();i++)
        {
            BL_AbzugsKalkulator_AbzugZeile oAbzug = (BL_AbzugsKalkulator_AbzugZeile)this.vAbzugsZeilen.get(i);
            dRueck += oAbzug.get_dAbzugVonEPreis();
        }
        return dRueck;
    }

    
    public double get_GESAMT_EPREIS_ABZUG_FW()
    {
        double dRueck = 0;
        
        for (int i=0;i<this.vAbzugsZeilen.size();i++)
        {
            BL_AbzugsKalkulator_AbzugZeile oAbzug = (BL_AbzugsKalkulator_AbzugZeile)this.vAbzugsZeilen.get(i);
            dRueck += oAbzug.get_dAbzugVonEPreis_FW();
        }
        return dRueck;
    }

    
    
    public double get_GESAMTER_MENGENABZUG()
    {
        double dRueck = 0;
        
        for (int i=0;i<this.vAbzugsZeilen.size();i++)
        {
            BL_AbzugsKalkulator_AbzugZeile oAbzug = (BL_AbzugsKalkulator_AbzugZeile)this.vAbzugsZeilen.get(i);
            dRueck += oAbzug.get_dAbzugVonMenge();
        }
        return dRueck;
    }


    public BigDecimal get_bdGESAMTER_MENGENABZUG()
    {
        return new MyBigDecimal(this.get_GESAMTER_MENGENABZUG(),3).get_bdWert();
    }

    public BigDecimal get_bdEINZELPREIS_ABZUG()
    {
        return new MyBigDecimal(this.get_GESAMT_EPREIS_ABZUG(),2).get_bdWert();
    }
    
    public BigDecimal get_bdEINZELPREIS_ABZUG_FW()
    {
        return new MyBigDecimal(this.get_GESAMT_EPREIS_ABZUG_FW(),2).get_bdWert();
    }
    
    public BigDecimal get_bdGESAMTPREIS_ABZUG()
    {
        return new MyBigDecimal(this.get_ABZUG_VOM_GESAMTPREIS(),2).get_bdWert();
    }
   
    public BigDecimal get_bdGESAMTPREIS_ABZUG_FW()
    {
        return new MyBigDecimal(this.get_ABZUG_VOM_GESAMTPREIS_FW(),2).get_bdWert();
    }

    
    public BigDecimal get_bdRESULTIERENDER_EINZELPREIS()
    {
        return new MyBigDecimal(this.get_RESULTIERENDER_EINZELPREIS(),2).get_bdWert();
    }
   
    public BigDecimal get_bdRESULTIERENDER_EINZELPREIS_FW()
    {
        return new MyBigDecimal(this.get_RESULTIERENDER_EINZELPREIS_FW(),2).get_bdWert();
    }
    

    
    //2011-01-11: Abzug fuer die Fuhre/Lager: inhaltsangabe wird nicht als abzug gewertet
    public double get_GESAMTER_MENGENABZUG_Lager()
    {
        double dRueck = 0;
        
        for (int i=0;i<this.vAbzugsZeilen.size();i++)
        {
            BL_AbzugsKalkulator_AbzugZeile oAbzug = (BL_AbzugsKalkulator_AbzugZeile)this.vAbzugsZeilen.get(i);
            dRueck += oAbzug.get_dAbzugVonMengeLager();
        }
        return dRueck;
    }


    public BigDecimal get_bdGESAMTER_MENGENABZUG_Lager()
    {
        return new MyBigDecimal(this.get_GESAMTER_MENGENABZUG_Lager(),3).get_bdWert();
    }
 
    
    
    public double get_ABZUG_VOM_GESAMTPREIS()
    {
    	double dRueck = 0;
    	for (BL_AbzugsKalkulator_AbzugZeile oAbzugZeile: this.vAbzugsZeilen)
    	{
    		dRueck += oAbzugZeile.get_dAbzug_Anteil_am_GESAMT_BETRAG();
    	}
        return dRueck;
        
    }


    public double get_ABZUG_VOM_GESAMTPREIS_FW()
    {
    	double dRueck = 0;
    	for (BL_AbzugsKalkulator_AbzugZeile oAbzugZeile: this.vAbzugsZeilen)
    	{
    		dRueck += oAbzugZeile.get_dAbzug_Anteil_am_GESAMT_BETRAG_FW();
    	}
        return dRueck;
        
    }

    
    /*
     * berechnet den resultierenden einzelpreis bei der Positionsmenge,
     * damit der berechnete endpreisabzug rauskommt 
     */
    public double get_RESULTIERENDER_EINZELPREIS()
    {
        double dRueck = this.dAusgangsEPreis;
        double dGesamtPreisNachAbzug = bibALL.Round(this.dAusgangsEPreis*(this.dAusgangsMenge/this.dMengenDivisor),2)-this.get_ABZUG_VOM_GESAMTPREIS();
        if (this.dAusgangsMenge!=0)
            dRueck = bibALL.Round(dGesamtPreisNachAbzug/(this.dAusgangsMenge/this.dMengenDivisor),2);
        
        return dRueck;
    }
    

    /*
     * berechnet den resultierenden einzelpreis bei der Positionsmenge,
     * damit der berechnete endpreisabzug rauskommt 
     */
    public double get_RESULTIERENDER_EINZELPREIS_FW()
    {
        double dRueck = this.dAusgangsEPreis_FW;
        double dGesamtPreisNachAbzug_FW = bibALL.Round(this.dAusgangsEPreis_FW*(this.dAusgangsMenge/this.dMengenDivisor),2)-this.get_ABZUG_VOM_GESAMTPREIS_FW();
        if (this.dAusgangsMenge!=0)
            dRueck = bibALL.Round(dGesamtPreisNachAbzug_FW/(this.dAusgangsMenge/this.dMengenDivisor),2);
        
        return dRueck;
    }
    

    
    
    /**
     * @returns den endpreis der letzen berechneten zeile, um diese in die naechste einzutragen (als startpreis)  
     */
    public double get_dEPreisNachAbzugLetzteAbzugsZeile()
    {
        double dRueck = 0;
        if(this.vAbzugsZeilen.size()>0)
        {
            dRueck = ((BL_AbzugsKalkulator_AbzugZeile)this.vAbzugsZeilen.get(this.vAbzugsZeilen.size()-1)).get_dEPreisNachAbzug();
        }
        return dRueck;
    }
    
    
    /**
     * @returns den endpreis der letzen berechneten zeile, um diese in die naechste einzutragen (als startpreis)  
     */
    public double get_dEPreisNachAbzugLetzteAbzugsZeile_FW()
    {
        double dRueck = 0;
        if(this.vAbzugsZeilen.size()>0)
        {
            dRueck = ((BL_AbzugsKalkulator_AbzugZeile)this.vAbzugsZeilen.get(this.vAbzugsZeilen.size()-1)).get_dEPreisNachAbzug_FW();
        }
        return dRueck;
    }
    

    

    /**
     * @returns die endmenge der letzen berechneten zeile, um diese in die naechste einzutragen (als startpreis)  
     */
    public double get_dMengeNachAbzugLetzteAbzugsZeile()
    {
        double dRueck = 0;
        if(this.vAbzugsZeilen.size()>0)
        {
            dRueck = ((BL_AbzugsKalkulator_AbzugZeile)this.vAbzugsZeilen.get(this.vAbzugsZeilen.size()-1)).get_dMengeNachAbzug();
        }
        return dRueck;
    }

    
    public Vector<String> get_vSQL_CompleteUpdateStack_Fuer_RechnungGutschrift_ABZUEGE(String cTableNameAbzuege) throws myException
    {
    	Vector<String> vRueck = new Vector<String>();
    	
    	for (int i=0;i<this.vAbzugsZeilen.size();i++)
    	{
    		vRueck.add(((BL_AbzugsKalkulator_AbzugZeile)this.vAbzugsZeilen.get(i)).get_UpdateStatement__Fuer_RechnungGutschrift_ABZUEGE(cTableNameAbzuege));
    	}
    	return vRueck;
    }
    
    
    
    public String get_LastZeileBelegText()
    {
    	return this.vAbzugsZeilen.get(this.vAbzugsZeilen.size()-1).get_cBezeichnungReinschrift();
    }

    
    
	//2011-04-13: weitere abzugskalkulation: Anteil Abzug (Betrag) aufgrund Mengenabzuegen  (reduziert Menge von Brutto auf Nettomenge)
	//                                       Anteil Abzug (Betrag) aufgrund Inhaltsabzuegen (reduziert nicht die Nettomenge, nur den Preis)
	//                                       Anteil Abzug (Betrag) aufgrund Vorauszahlungsabzug (wird separat behandelt)
    //
	//                                       Benennung:  dAbzugBetragBruttomengeZuNettoMenge   dAbzugBetragBruttomengeZuNettoMenge_FW
	//                                                   dAbzugBetragInhaltsabzug              dAbzugBetragInhaltsabzug_FW
	//                                                   dAbzugBetragVorauszahlung             dAbzugBetragVorauszahlung_FW
    

    public double get_dSumme_Geldabzug_wegen_realem_MengenAbzug()
    {
    	return this.get_dSummeAbzugBestimmteTypen(this.vAbzugsTypen_Betrag_wegen_realemMengenAbzug);
    }
    
    public double get_dSumme_Geldabzug_wegen_realem_MengenAbzug_FW()
    {
    	return this.get_dSummeAbzugBestimmteTypen_FW(this.vAbzugsTypen_Betrag_wegen_realemMengenAbzug);
    }
    
    public double get_dSumme_Geldabzug_auf_NettoMenge()
    {
    	return this.get_dSummeAbzugBestimmteTypen(this.vAbzugsTypen_Betrag_auf_NettoMenge);
    }
    
    public double get_dSumme_Geldabzug_auf_NettoMenge_FW()
    {
    	return this.get_dSummeAbzugBestimmteTypen_FW(this.vAbzugsTypen_Betrag_auf_NettoMenge);
    }

    public double get_dSumme_Geldabzug_Vorauszahlung()
    {
    	return this.get_dSummeAbzugBestimmteTypen(this.vAbzugsTypen_Betrag_auf_Vorauszahlung);
    }
    
    public double get_dSumme_Geldabzug_Vorauszahlung_FW()
    {
    	return this.get_dSummeAbzugBestimmteTypen_FW(this.vAbzugsTypen_Betrag_auf_Vorauszahlung);
    }

    
    public BigDecimal get_bdSumme_Geldabzug_wegen_realem_MengenAbzug()
    {
    	return new MyBigDecimal(this.get_dSumme_Geldabzug_wegen_realem_MengenAbzug(),2).get_bdWert();
    }
    
    public BigDecimal get_bdSumme_Geldabzug_wegen_realem_MengenAbzug_FW()
    {
    	return new MyBigDecimal(this.get_dSumme_Geldabzug_wegen_realem_MengenAbzug_FW(),2).get_bdWert();
    }
    
    public BigDecimal get_bdSumme_Geldabzug_auf_NettoMenge()
    {
    	return new MyBigDecimal(this.get_dSumme_Geldabzug_auf_NettoMenge(),2).get_bdWert();
    }
    
    public BigDecimal get_bdSumme_Geldabzug_auf_NettoMenge_FW()
    {
    	return new MyBigDecimal(this.get_dSumme_Geldabzug_auf_NettoMenge_FW(),2).get_bdWert();
    }

    public BigDecimal get_bdSumme_Geldabzug_Vorauszahlung()
    {
    	return new MyBigDecimal(this.get_dSumme_Geldabzug_Vorauszahlung(),2).get_bdWert();
    }
    
    public BigDecimal get_bdSumme_Geldabzug_Vorauszahlung_FW()
    {
    	return new MyBigDecimal(this.get_dSumme_Geldabzug_Vorauszahlung_FW(),2).get_bdWert();
    }

    
    
    
    
    public double get_dNettoMenge()
    {
    	return this.dAusgangsMenge-this.get_GESAMTER_MENGENABZUG_Lager();
    }
    
    public BigDecimal  get_bdNettoMenge(boolean bRoundAuf_3nachkommastellen)
    {
    	MyBigDecimal bdRueck = new MyBigDecimal(this.get_dNettoMenge(),(bRoundAuf_3nachkommastellen?new Integer(3):null));
    	return bdRueck.get_bdWert();
    }
    

    
    
    /*
     * berechnet den resultierenden einzelpreis bei der Positionsmenge,
     * damit der berechnete endpreisabzug rauskommt 
     */
    public double get_RESULTIERENDER_EINZELPREIS_AUF_LAGERMENGE()
    {
    	double dRueck = 0;
    	
        if (this.get_dNettoMenge()!=0 && this.dMengenDivisor!=0)
        {
        	dRueck = bibALL.Round(this.get_BETRAG_OHNE_VORAUSZAHLUNGSABZUG()/(this.get_dNettoMenge()/this.dMengenDivisor),2);
        }
        return dRueck;
    }
    

    /*
     * berechnet den resultierenden einzelpreis bei der Positionsmenge,
     * damit der berechnete endpreisabzug rauskommt 
     */
    public double get_RESULTIERENDER_EINZELPREIS_AUF_LAGERMENGE_FW()
    {
    	double dRueck = 0;
    	
        if (this.get_dNettoMenge()!=0 && this.dMengenDivisor!=0)
        {
        	dRueck = bibALL.Round(this.get_BETRAG_OHNE_VORAUSZAHLUNGSABZUG_FW()/(this.get_dNettoMenge()/this.dMengenDivisor),2);
        }
        return dRueck;
    }
    
    
    
    /*
     * berechnet den resultierenden einzelpreis bei der Positionsmenge,
     * damit der berechnete endpreisabzug rauskommt 
     */
    public double get_BETRAG_OHNE_VORAUSZAHLUNGSABZUG()
    {
    	double GesamtPreis1 = bibALL.Round(this.dAusgangsEPreis*(this.dAusgangsMenge/this.dMengenDivisor),2);    //der gesamtpreis der position
    	double Gesamtpreis2 = GesamtPreis1 - this.get_dSumme_Geldabzug_wegen_realem_MengenAbzug()-this.get_dSumme_Geldabzug_auf_NettoMenge();   //alle abzuege ausser Vorauszahlungen

        return Gesamtpreis2;
    }
    
    
    /*
     * berechnet den resultierenden einzelpreis bei der Positionsmenge,
     * damit der berechnete endpreisabzug rauskommt 
     */
    public double get_BETRAG_OHNE_VORAUSZAHLUNGSABZUG_FW()
    {
    	double GesamtPreis1 = bibALL.Round(this.dAusgangsEPreis_FW*(this.dAusgangsMenge/this.dMengenDivisor),2);    //der gesamtpreis der position
    	double Gesamtpreis2 = GesamtPreis1 - this.get_dSumme_Geldabzug_wegen_realem_MengenAbzug_FW()-this.get_dSumme_Geldabzug_auf_NettoMenge_FW();   //alle abzuege ausser Vorauszahlungen

        return Gesamtpreis2;
    }
    
    
    

    
    
    public BigDecimal get_bdRESULTIERENDER_EINZELPREIS_AUF_LAGERMENGE()
    {
        return new MyBigDecimal(this.get_RESULTIERENDER_EINZELPREIS_AUF_LAGERMENGE(),2).get_bdWert();
    }
    

    public BigDecimal get_bdRESULTIERENDER_EINZELPREIS_AUF_LAGERMENGE_FW()
    {
        return new MyBigDecimal(this.get_RESULTIERENDER_EINZELPREIS_AUF_LAGERMENGE_FW(),2).get_bdWert();
    }

     
    
    
    
    private double get_dSummeAbzugBestimmteTypen(Vector<String> vTypen)
    {
    	double dRueck = 0;
    	
    	for (int i=0;i<this.vAbzugsZeilen.size();i++)
    	{
    		if (vTypen.contains(this.vAbzugsZeilen.get(i).get_cABZUGSTYP()))
    		{
    			dRueck += this.vAbzugsZeilen.get(i).get_dAbzug_Anteil_am_GESAMT_BETRAG();
    		}
    	}
    	return dRueck;
    }
    
    private double get_dSummeAbzugBestimmteTypen_FW(Vector<String> vTypen)
    {
    	double dRueck = 0;
    	
    	for (int i=0;i<this.vAbzugsZeilen.size();i++)
    	{
    		if (vTypen.contains(this.vAbzugsZeilen.get(i).get_cABZUGSTYP()))
    		{
    			dRueck += this.vAbzugsZeilen.get(i).get_dAbzug_Anteil_am_GESAMT_BETRAG_FW();
    		}
    	}
    	return dRueck;
    }
    

}
