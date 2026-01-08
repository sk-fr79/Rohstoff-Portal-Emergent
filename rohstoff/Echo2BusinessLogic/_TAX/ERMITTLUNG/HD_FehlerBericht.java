package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import panter.gmbh.indep.MyString;

public class HD_FehlerBericht
{
	private boolean b_EK = true;
	private MyString c_FehlerIstWo = null;
	private MyString c_FehlerBetrifft = null;
	private MyString c_FehlerInfo = null;
	
	
	/**
	 * 
	 * @param bEK
	 * @param cFehlerIstWo
	 * @param cFehlerBetrifftFeld
	 * @param cFehlerInfo
	 */
	public HD_FehlerBericht(boolean bEK, MyString cFehlerIstWo, MyString cFehlerBetrifftFeld, MyString cFehlerInfo)
	{
		super();
		this.b_EK = bEK;
		this.c_FehlerIstWo = cFehlerIstWo;
		this.c_FehlerBetrifft = cFehlerBetrifftFeld;
		this.c_FehlerInfo = cFehlerInfo;
	}

	public boolean get_bEK()
	{
		return b_EK;
	}

	public MyString get_cFehlerIstWo()
	{
		return c_FehlerIstWo;
	}

	public MyString get_cFehlerBetrifft()
	{
		return c_FehlerBetrifft;
	}

	public MyString get_cFehlerInfo()
	{
		return c_FehlerInfo;
	}
	
	
	public String get_Untranslated_Error_Gesamt() {
		String cRueck = this.b_EK?"Ladeseite":"AbladeSeite";
		if (this.c_FehlerIstWo!=null) {
			cRueck+=" // "+this.c_FehlerIstWo.COrig();
		}
		if (this.c_FehlerBetrifft!=null) {
			cRueck+=" // "+this.c_FehlerBetrifft.COrig();
		}
		if (this.c_FehlerInfo!=null) {
			cRueck+=" // "+this.c_FehlerInfo.COrig();
		}
		return cRueck;
	}
	
	
}
