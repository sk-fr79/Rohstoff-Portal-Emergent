package rohstoff.Echo2BusinessLogic.BEWEGUNG;


public class BS_STEUERVERMERK 
{

	private String 	 cTextFuerPopup = null;
	private String   cVermerk = null;
	private String   cDateGueltigAbFormated = null;
	private String   cSteuersatzFormated = null;
	
	public BS_STEUERVERMERK(	String 	    TextFuerPopup, 
								String 		Vermerk,
								String 		DateGueltigAbFormated, 
								String 		SteuersatzFormated) 
	{
		super();
		this.cTextFuerPopup = TextFuerPopup;
		this.cVermerk = Vermerk;
		this.cDateGueltigAbFormated = DateGueltigAbFormated;
		this.cSteuersatzFormated = SteuersatzFormated;
	}
	
	
	
	
	public String getcTextFuerPopup() 
	{
		return cTextFuerPopup;
	}

	public String getcVermerk() 
	{
		return cVermerk;
	}

	public String getcDateGueltigAbFormated() 
	{
		return cDateGueltigAbFormated;
	}

	public String getcSteuersatzFormated() 
	{
		return cSteuersatzFormated;
	}

	
	

}
