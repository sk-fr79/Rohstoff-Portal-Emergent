package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import java.math.BigDecimal;

import panter.gmbh.indep.S;

public class FSI_SortObject_NG implements Comparable<FSI_SortObject_NG>
{
	public String 		ANZEIGE = null;
	public String 		SORTTEXT = null;
	public BigDecimal  	BD_SORT = null;
	
	public FSI_SortObject_NG(String aNZEIGE)
	{
		super();
		ANZEIGE = aNZEIGE;
		SORTTEXT = aNZEIGE;
	}

	public FSI_SortObject_NG(String aNZEIGE, String sORTTEXT)
	{
		super();
		ANZEIGE = aNZEIGE;
		SORTTEXT = sORTTEXT;
	}

	/**
	 * Wenn BigDecimal zu sortieren verwendet wird
	 * @param aNZEIGE
	 * @param bD_SORT
	 */
	public FSI_SortObject_NG(String aNZEIGE, BigDecimal bD_SORT)
	{
		super();
		ANZEIGE = aNZEIGE;
		BD_SORT = bD_SORT;
	}

	@Override
	public int compareTo(FSI_SortObject_NG o)
	{
		if (this.BD_SORT!=null && o.BD_SORT!=null)    //dann wird nach den zahlen sortiert, sonst nach den texten sonst gar nicht
		{
			return this.BD_SORT.compareTo(o.BD_SORT);
		}
		else
		{
			return S.NN(this.SORTTEXT).compareTo(S.NN(o.SORTTEXT));
		}

	}
	
	
	
	
	
}
