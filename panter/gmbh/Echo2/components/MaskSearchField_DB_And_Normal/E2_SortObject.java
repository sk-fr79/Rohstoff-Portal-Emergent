package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

public class E2_SortObject
{
	public XX_Button4SearchResultList[]		ArrayButtons	= null;
	public String							SortString		= null;
	public BigDecimal						BDSort			= null;
	public GregorianCalendar				Calendar		= null;

	public E2_SortObject(XX_Button4SearchResultList[] arrayButtons, String sortString)
	{
		super();
		this.ArrayButtons = arrayButtons;
		this.SortString = sortString;
	}

	public E2_SortObject(XX_Button4SearchResultList[] arrayButtons, BigDecimal bdSort)
	{
		super();
		this.ArrayButtons = arrayButtons;
		this.BDSort = bdSort;
	}

	public E2_SortObject(XX_Button4SearchResultList[] arrayButtons, GregorianCalendar calendar)
	{
		super();
		this.ArrayButtons = arrayButtons;
		this.Calendar = calendar;
	}

}
