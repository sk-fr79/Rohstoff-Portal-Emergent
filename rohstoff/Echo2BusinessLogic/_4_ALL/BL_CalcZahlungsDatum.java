package rohstoff.Echo2BusinessLogic._4_ALL;

import java.util.Date;
import java.util.GregorianCalendar;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;


public class BL_CalcZahlungsDatum
{
	private TestingDate  oLeistungsDatum = null;
	private Long         LZahlTage = null;
	private Long         LFixMonat = null;
	private Long         LFixTag = null;
	
	
	private GregorianCalendar 	oZahlungsdatum = null;
	
	/**
	 * @param c_Formated_LeistungsDatum
	 * @param zahlTage
	 * @param fixMonat
	 * @param fixTag
	 * @throws myException
	 */
	public BL_CalcZahlungsDatum(	String c_Formated_LeistungsDatum, 
									Long zahlTage,
									Long fixMonat, 
									Long fixTag)  throws myException
	{
		super();
		
		this.oLeistungsDatum = new TestingDate(c_Formated_LeistungsDatum);
		this.LZahlTage = zahlTage;
		this.LFixMonat = fixMonat;
		this.LFixTag = fixTag;
		
		if (!this.oLeistungsDatum.testing())
		{
			throw new myException(this,"Error Testing Date ! Code: <84f6ca12-9743-11e9-bc42-526af7764f64>");
		}
		
		this.oZahlungsdatum = this.oLeistungsDatum.get_Calendar();
		
		if (this.LFixMonat != null && this.LFixTag != null)
		{
			int iFixM = this.LFixMonat.intValue();
			
			for (int i=0;i<iFixM;i++)
			{
				this.oZahlungsdatum = myDateHelper.Find_First_Day_NextMonth(oZahlungsdatum);
			}
			
			int iFixT = this.LFixTag.intValue();

			if (iFixT>=1)
			{
				this.oZahlungsdatum = myDateHelper.Add_Day_To_Calendar(iFixT-1, oZahlungsdatum);
			}

		}
		else if (this.LZahlTage != null)
		{
			this.oZahlungsdatum = myDateHelper.Add_Day_To_Calendar(this.LZahlTage.intValue(), oZahlungsdatum);
		}
		
	}

	public String get_cDateForMask() throws myException
	{
		return new myDateHelper(this.oZahlungsdatum).get_cDateFormatForMask();
	}

	public String get_cDateForORACLE_SQL(boolean bMitApostroph) throws myException
	{
		if (bMitApostroph)
		{
			return bibALL.MakeSql(new myDateHelper(this.oZahlungsdatum).get_cDateFormat_ISO_FORMAT());
		}
		else
		{
			return new myDateHelper(this.oZahlungsdatum).get_cDateFormat_ISO_FORMAT();
		}
	}

	
	/**
	 * 2019-06-24
	 * @author martin
	 * @date 24.06.2019
	 *
	 * @return
	 */
	public Date getZahlungsdatum() {
		return oZahlungsdatum.getTime();
	}
	
	
}
