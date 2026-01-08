package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Locale;

import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.maggie.DotFormatter;


public class BS_PreisCalculator
{
	
	//rueckgabe-werte
	private Double 	dGesamtpreis = null;
	private Double  dAnzahl = null;
	private Double  dEinzelPreis = null;
	private Long 	lMengenDivisor = null;
	private Double  dWaehrungskurs = null;
	private Double  dEinzelPreis_FW = null;
	private Double  dGesamtPreis_FW = null;
	
	private boolean  bCalcOK = false;
	
	
	/**
	 * @param cANZAHL
	 * @param cEINZELPREIS
	 * @param cMENGENDIVISOR
	 * @param cWAEHRUNGSKURS
	 * @param bStringsAreFormated
	 * @throws myException
	 */
	public BS_PreisCalculator(	String cANZAHL, 
								String cEINZELPREIS, 
								String cMENGENDIVISOR,
								String cWAEHRUNGSKURS,
								boolean bStringsAreFormated) throws myException
	{
		super();
		
		try
		{
			String cFormAnzahl = cANZAHL;
			String cFormEinzelpreis = cEINZELPREIS;
			String cFormMengeDiv = cMENGENDIVISOR;
			String cWaehrungskurs = cWAEHRUNGSKURS;
			
			
			// zuerst formatierte werte beschaffen (falls unformatiert)
			if (!bStringsAreFormated)
			{
				cFormAnzahl = 		MyNumberFormater.formatDez(cFormAnzahl,3,true);
				cFormEinzelpreis = 	MyNumberFormater.formatDez(cFormEinzelpreis,2,true);
				cFormMengeDiv = 		MyNumberFormater.formatDez(cFormMengeDiv,true);
				cWaehrungskurs = 		MyNumberFormater.formatDez(cWaehrungskurs,4,true);
			}
			
			
			/*
			 * 2012-11-07: Anzahl muss auf 3 Stellen gerundet werden 
			 */
			//alt
			//DotFormatter oDFAnzahl = new DotFormatter(cFormAnzahl,2,Locale.GERMAN,true,3);
			//neu 
			DotFormatter oDFAnzahl = new DotFormatter(cFormAnzahl,3,Locale.GERMAN,true,3);
			DotFormatter oDFEpreis = new DotFormatter(cFormEinzelpreis,2,Locale.GERMAN,true,3);
			DotFormatter oDFDivisor = new DotFormatter(cFormMengeDiv,0,Locale.GERMAN,true,3);
			DotFormatter oDFWaehrungskurs = new DotFormatter(cWaehrungskurs,4,Locale.GERMAN,true,3);
			
			if (oDFAnzahl.doFormat() & oDFEpreis.doFormat() & oDFDivisor.doFormat() & oDFWaehrungskurs.doFormat())
			{
				double d_Anzahl = oDFAnzahl.getDoubleValue();
				double dEpreis = oDFEpreis.getDoubleValue();
				double dDivisor = oDFDivisor.getDoubleValue();
				double d_Waehrungskurs = oDFWaehrungskurs.getDoubleValue();
				
				this.dGesamtpreis = new Double(bibALL.Round((d_Anzahl*dEpreis)/dDivisor,2));
				
				this.dEinzelPreis_FW = new Double(bibALL.Round(dEpreis*d_Waehrungskurs, 2));
				this.dGesamtPreis_FW = new Double(bibALL.Round((d_Anzahl*this.dEinzelPreis_FW.doubleValue())/dDivisor,2));
				
				
				//jetzt die uebergebenen werte als Double fuer rueckgabe
				this.dAnzahl = oDFAnzahl.get_oDouble();
				this.dEinzelPreis = oDFEpreis.get_oDouble();
				this.lMengenDivisor = oDFDivisor.get_oLong();
				this.dWaehrungskurs = oDFWaehrungskurs.get_oDouble();
				
				this.bCalcOK = true;
			}
			else
			{
				throw new myExceptionForUser("Fehleingabe ...");
			}
		}
		
		catch (myException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException(this,ex.getLocalizedMessage());
		}
	}

	
	public Double get_dGesamtPreis()
	{
		return dGesamtpreis;
	}


	public Double get_dEinzelPreis_FW() 
	{
		return dEinzelPreis_FW;
	}


	public Double get_dGesamtPreis_FW() 
	{
		return dGesamtPreis_FW;
	}


	public boolean is_bCalcOK()
	{
		return bCalcOK;
	}


	public Double get_dAnzahl()
	{
		return dAnzahl;
	}


	public Double get_dEinzelPreis()
	{
		return dEinzelPreis;
	}


	public Long get_lMengenDivisor()
	{
		return lMengenDivisor;
	}


	public Double get_dWaehrungskurs()
	{
		return dWaehrungskurs;
	}
	
}
