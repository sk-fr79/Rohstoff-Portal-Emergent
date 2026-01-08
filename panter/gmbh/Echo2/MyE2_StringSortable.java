package panter.gmbh.Echo2;

import panter.gmbh.indep.MyString;

public class MyE2_StringSortable extends MyE2_String implements Comparable<MyE2_StringSortable>
{
	
	public Object  oPlace4All = null;
	
	
	
	public MyE2_StringSortable(MyString string, String nullValue)
	{
		super(string, nullValue);
	}

	public MyE2_StringSortable(String untrans1, boolean trans1,
			String untrans2, boolean trans2, String untrans3, boolean trans3,
			String untrans4, boolean trans4, String untrans5, boolean trans5,
			String untrans6, boolean trans6)
	{
		super(untrans1, trans1, untrans2, trans2, untrans3, trans3, untrans4, trans4,
				untrans5, trans5, untrans6, trans6);
	}

	public MyE2_StringSortable(String untrans1, boolean trans1,
			String untrans2, boolean trans2, String untrans3, boolean trans3,
			String untrans4, boolean trans4, String untrans5, boolean trans5)
	{
		super(untrans1, trans1, untrans2, trans2, untrans3, trans3, untrans4, trans4,
				untrans5, trans5);
	}

	public MyE2_StringSortable(String untrans1, boolean trans1,
			String untrans2, boolean trans2, String untrans3, boolean trans3,
			String untrans4, boolean trans4)
	{
		super(untrans1, trans1, untrans2, trans2, untrans3, trans3, untrans4, trans4);
	}

	public MyE2_StringSortable(String untrans1, boolean trans1,
			String untrans2, boolean trans2, String untrans3, boolean trans3)
	{
		super(untrans1, trans1, untrans2, trans2, untrans3, trans3);
	}

	public MyE2_StringSortable(String untrans1, boolean trans1,
			String untrans2, boolean trans2)
	{
		super(untrans1, trans1, untrans2, trans2);
	}

	public MyE2_StringSortable(String untranslated, boolean translated)
	{
		super(untranslated, translated);
	}

	public MyE2_StringSortable(String untranslated, boolean translated, Object obj4Place4All)
	{
		super(untranslated, translated);
		this.oPlace4All = obj4Place4All;
	}

	
	public MyE2_StringSortable(String untranslated)
	{
		super(untranslated);
	}

	@Override
	public int compareTo(MyE2_StringSortable o)
	{
		return this.CTrans().compareTo(o.CTrans());
	}

	public Object get_oPlace4All()
	{
		return oPlace4All;
	}

	public void set_oPlace4All(Object place4All)
	{
		oPlace4All = place4All;
	}

	public boolean equals(Object oComObject)
	{
		return this.CTrans().equals(oComObject);
	}
	
	public int hashCode()
	{
		return this.CTrans().hashCode();
	}
}
