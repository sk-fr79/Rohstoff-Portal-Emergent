/**
 * 
 */
package rohstoff.utils;

import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;

public class My_MWST
{
	/**
	 * 
	 */
	private double dMWSTWert = 0;
	private String cMWST_Formated = null;
	private String cMWST_Unformated = null;
	private boolean	 bIstStandard = false;
	
	public My_MWST(double MWSTWert, boolean Standard)
	{
		this.bIstStandard = Standard;
		this.dMWSTWert = MWSTWert;
		this.cMWST_Formated = MyNumberFormater.formatDez(MWSTWert,2,false);
		this.cMWST_Unformated = bibALL.ReplaceTeilString(this.cMWST_Formated,",",".");
	}

	public String get_cMWST_Formated()		{			return cMWST_Formated;		}
	public String get_cMWST_Unformated()		{			return cMWST_Unformated;		}
	public double get_dMWSTWert()			{			return dMWSTWert;		}
	public boolean get_bIstStandard()		{			return this.bIstStandard;		}
}