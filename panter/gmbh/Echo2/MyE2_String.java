package panter.gmbh.Echo2;

import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;

public class MyE2_String extends MyString
{

	public MyE2_String(String cUntranslated)
	{
		super(cUntranslated);
	}

	public MyE2_String(MyString cString, String cNullValue)
	{
		super();
		if (cString==null)
		{
			this.addString(new MyE2_String(cNullValue));
		}
		else
		{
			this.addString(cString);
		}
	}

	
	public MyE2_String(String cUntranslated, boolean bTRanslated)
	{
		super(cUntranslated, bTRanslated);
	}

	public MyE2_String(String cUntrans1, boolean bTrans1, String cUntrans2, boolean bTrans2)
	{
		super(cUntrans1, bTrans1);
		if (!bibALL.isEmpty(cUntrans2))
			super.addString(new MyString(cUntrans2,bTrans2));

	}

	public MyE2_String(String cUntrans1, boolean bTrans1, String cUntrans2, boolean bTrans2, String cUntrans3, boolean bTrans3)
	{
		super(cUntrans1, bTrans1);
		if (!bibALL.isEmpty(cUntrans2))
			super.addString(new MyString(cUntrans2,bTrans2));

		if (!bibALL.isEmpty(cUntrans3))
			super.addString(new MyString(cUntrans3,bTrans3));
		
	}

	
	public MyE2_String(String cUntrans1, boolean bTrans1, String cUntrans2, boolean bTrans2, String cUntrans3, boolean bTrans3, String cUntrans4, boolean bTrans4)
	{
		super(cUntrans1, bTrans1);
		if (!bibALL.isEmpty(cUntrans2))
			super.addString(new MyString(cUntrans2,bTrans2));

		if (!bibALL.isEmpty(cUntrans3))
			super.addString(new MyString(cUntrans3,bTrans3));
		
		if (!bibALL.isEmpty(cUntrans4))
			super.addString(new MyString(cUntrans4,bTrans4));

	}
	

	
	public MyE2_String(String cUntrans1, boolean bTrans1, String cUntrans2, boolean bTrans2, String cUntrans3, boolean bTrans3, String cUntrans4, boolean bTrans4, String cUntrans5, boolean bTrans5)
	{
		super(cUntrans1, bTrans1);
		if (!bibALL.isEmpty(cUntrans2))
			super.addString(new MyString(cUntrans2,bTrans2));

		if (!bibALL.isEmpty(cUntrans3))
			super.addString(new MyString(cUntrans3,bTrans3));
		
		if (!bibALL.isEmpty(cUntrans4))
			super.addString(new MyString(cUntrans4,bTrans4));

		if (!bibALL.isEmpty(cUntrans5))
			super.addString(new MyString(cUntrans5,bTrans5));

	}

	
	public MyE2_String(String cUntrans1, boolean bTrans1, String cUntrans2, boolean bTrans2, String cUntrans3, boolean bTrans3, String cUntrans4, boolean bTrans4, String cUntrans5, boolean bTrans5, String cUntrans6, boolean bTrans6)
	{
		super(cUntrans1, bTrans1);
		if (!bibALL.isEmpty(cUntrans2))
			super.addString(new MyString(cUntrans2,bTrans2));

		if (!bibALL.isEmpty(cUntrans3))
			super.addString(new MyString(cUntrans3,bTrans3));
		
		if (!bibALL.isEmpty(cUntrans4))
			super.addString(new MyString(cUntrans4,bTrans4));

		if (!bibALL.isEmpty(cUntrans5))
			super.addString(new MyString(cUntrans5,bTrans5));

		if (!bibALL.isEmpty(cUntrans6))
			super.addString(new MyString(cUntrans6,bTrans6));

	}


	
	
	
	public MyE2_String(	String cUntrans1, boolean bTrans1, 
						String cUntrans2, boolean bTrans2, 
						String cUntrans3, boolean bTrans3, 
						String cUntrans4, boolean bTrans4, 
						String cUntrans5, boolean bTrans5, 
						String cUntrans6, boolean bTrans6,
						String cUntrans7, boolean bTrans7)
	{
		super(cUntrans1, bTrans1);
		if (!bibALL.isEmpty(cUntrans2))
			super.addString(new MyString(cUntrans2,bTrans2));

		if (!bibALL.isEmpty(cUntrans3))
			super.addString(new MyString(cUntrans3,bTrans3));
		
		if (!bibALL.isEmpty(cUntrans4))
			super.addString(new MyString(cUntrans4,bTrans4));

		if (!bibALL.isEmpty(cUntrans5))
			super.addString(new MyString(cUntrans5,bTrans5));

		if (!bibALL.isEmpty(cUntrans6))
			super.addString(new MyString(cUntrans6,bTrans6));

		if (!bibALL.isEmpty(cUntrans7))
			super.addString(new MyString(cUntrans7,bTrans7));
	}

	
	
	public MyE2_String(	String cUntrans1, boolean bTrans1, 
						String cUntrans2, boolean bTrans2, 
						String cUntrans3, boolean bTrans3, 
						String cUntrans4, boolean bTrans4, 
						String cUntrans5, boolean bTrans5, 
						String cUntrans6, boolean bTrans6,
						String cUntrans7, boolean bTrans7,
						String cUntrans8, boolean bTrans8)
	{
		super(cUntrans1, bTrans1);
		if (!bibALL.isEmpty(cUntrans2))
			super.addString(new MyString(cUntrans2,bTrans2));
		
		if (!bibALL.isEmpty(cUntrans3))
			super.addString(new MyString(cUntrans3,bTrans3));
		
		if (!bibALL.isEmpty(cUntrans4))
			super.addString(new MyString(cUntrans4,bTrans4));
		
		if (!bibALL.isEmpty(cUntrans5))
			super.addString(new MyString(cUntrans5,bTrans5));
		
		if (!bibALL.isEmpty(cUntrans6))
			super.addString(new MyString(cUntrans6,bTrans6));
		
		if (!bibALL.isEmpty(cUntrans7))
			super.addString(new MyString(cUntrans7,bTrans7));
		
		if (!bibALL.isEmpty(cUntrans8))
			super.addString(new MyString(cUntrans8,bTrans8));
	}


	public MyE2_String(	String cUntrans1, boolean bTrans1, 
			String cUntrans2, boolean bTrans2, 
			String cUntrans3, boolean bTrans3, 
			String cUntrans4, boolean bTrans4, 
			String cUntrans5, boolean bTrans5, 
			String cUntrans6, boolean bTrans6,
			String cUntrans7, boolean bTrans7,
			String cUntrans8, boolean bTrans8,
			String cUntrans9, boolean bTrans9)
	{
		super(cUntrans1, bTrans1);
		
		if (!bibALL.isEmpty(cUntrans2))
		super.addString(new MyString(cUntrans2,bTrans2));
		
		if (!bibALL.isEmpty(cUntrans3))
		super.addString(new MyString(cUntrans3,bTrans3));
		
		if (!bibALL.isEmpty(cUntrans4))
		super.addString(new MyString(cUntrans4,bTrans4));
		
		if (!bibALL.isEmpty(cUntrans5))
		super.addString(new MyString(cUntrans5,bTrans5));
		
		if (!bibALL.isEmpty(cUntrans6))
		super.addString(new MyString(cUntrans6,bTrans6));
		
		if (!bibALL.isEmpty(cUntrans7))
		super.addString(new MyString(cUntrans7,bTrans7));
		
		if (!bibALL.isEmpty(cUntrans8))
		super.addString(new MyString(cUntrans8,bTrans8));
		
		if (!bibALL.isEmpty(cUntrans9))
		super.addString(new MyString(cUntrans9,bTrans9));
	}


	public MyE2_String(	String cUntrans1, boolean bTrans1, 
			String cUntrans2, boolean bTrans2, 
			String cUntrans3, boolean bTrans3, 
			String cUntrans4, boolean bTrans4, 
			String cUntrans5, boolean bTrans5, 
			String cUntrans6, boolean bTrans6,
			String cUntrans7, boolean bTrans7,
			String cUntrans8, boolean bTrans8,
			String cUntrans9, boolean bTrans9,
			String cUntrans10, boolean bTrans10)
	{
		super(cUntrans1, bTrans1);
		
		if (!bibALL.isEmpty(cUntrans2))
		super.addString(new MyString(cUntrans2,bTrans2));
		
		if (!bibALL.isEmpty(cUntrans3))
		super.addString(new MyString(cUntrans3,bTrans3));
		
		if (!bibALL.isEmpty(cUntrans4))
		super.addString(new MyString(cUntrans4,bTrans4));
		
		if (!bibALL.isEmpty(cUntrans5))
		super.addString(new MyString(cUntrans5,bTrans5));
		
		if (!bibALL.isEmpty(cUntrans6))
		super.addString(new MyString(cUntrans6,bTrans6));
		
		if (!bibALL.isEmpty(cUntrans7))
		super.addString(new MyString(cUntrans7,bTrans7));
		
		if (!bibALL.isEmpty(cUntrans8))
		super.addString(new MyString(cUntrans8,bTrans8));
		
		if (!bibALL.isEmpty(cUntrans9))
		super.addString(new MyString(cUntrans9,bTrans9));

		if (!bibALL.isEmpty(cUntrans10))
		super.addString(new MyString(cUntrans10,bTrans10));
	}

	public MyE2_String(	String cUntrans1, boolean bTrans1, 
			String cUntrans2, boolean bTrans2, 
			String cUntrans3, boolean bTrans3, 
			String cUntrans4, boolean bTrans4, 
			String cUntrans5, boolean bTrans5, 
			String cUntrans6, boolean bTrans6,
			String cUntrans7, boolean bTrans7,
			String cUntrans8, boolean bTrans8,
			String cUntrans9, boolean bTrans9,
			String cUntrans10, boolean bTrans10,
			String cUntrans11, boolean bTrans11)
	{
		super(cUntrans1, bTrans1);
		
		if (!bibALL.isEmpty(cUntrans2))
		super.addString(new MyString(cUntrans2,bTrans2));
		
		if (!bibALL.isEmpty(cUntrans3))
		super.addString(new MyString(cUntrans3,bTrans3));
		
		if (!bibALL.isEmpty(cUntrans4))
		super.addString(new MyString(cUntrans4,bTrans4));
		
		if (!bibALL.isEmpty(cUntrans5))
		super.addString(new MyString(cUntrans5,bTrans5));
		
		if (!bibALL.isEmpty(cUntrans6))
		super.addString(new MyString(cUntrans6,bTrans6));
		
		if (!bibALL.isEmpty(cUntrans7))
		super.addString(new MyString(cUntrans7,bTrans7));
		
		if (!bibALL.isEmpty(cUntrans8))
		super.addString(new MyString(cUntrans8,bTrans8));
		
		if (!bibALL.isEmpty(cUntrans9))
		super.addString(new MyString(cUntrans9,bTrans9));

		if (!bibALL.isEmpty(cUntrans10))
		super.addString(new MyString(cUntrans10,bTrans10));

		if (!bibALL.isEmpty(cUntrans11))
		super.addString(new MyString(cUntrans11,bTrans11));
	}

	
	
	public MyE2_String(	String cUntrans1, boolean bTrans1, 
			String cUntrans2, boolean bTrans2, 
			String cUntrans3, boolean bTrans3, 
			String cUntrans4, boolean bTrans4, 
			String cUntrans5, boolean bTrans5, 
			String cUntrans6, boolean bTrans6,
			String cUntrans7, boolean bTrans7,
			String cUntrans8, boolean bTrans8,
			String cUntrans9, boolean bTrans9,
			String cUntrans10, boolean bTrans10,
			String cUntrans11, boolean bTrans11,
			String cUntrans12, boolean bTrans12)
	{
		super(cUntrans1, bTrans1);
		
		if (!bibALL.isEmpty(cUntrans2))
		super.addString(new MyString(cUntrans2,bTrans2));
		
		if (!bibALL.isEmpty(cUntrans3))
		super.addString(new MyString(cUntrans3,bTrans3));
		
		if (!bibALL.isEmpty(cUntrans4))
		super.addString(new MyString(cUntrans4,bTrans4));
		
		if (!bibALL.isEmpty(cUntrans5))
		super.addString(new MyString(cUntrans5,bTrans5));
		
		if (!bibALL.isEmpty(cUntrans6))
		super.addString(new MyString(cUntrans6,bTrans6));
		
		if (!bibALL.isEmpty(cUntrans7))
		super.addString(new MyString(cUntrans7,bTrans7));
		
		if (!bibALL.isEmpty(cUntrans8))
		super.addString(new MyString(cUntrans8,bTrans8));
		
		if (!bibALL.isEmpty(cUntrans9))
		super.addString(new MyString(cUntrans9,bTrans9));

		if (!bibALL.isEmpty(cUntrans10))
		super.addString(new MyString(cUntrans10,bTrans10));

		if (!bibALL.isEmpty(cUntrans11))
		super.addString(new MyString(cUntrans11,bTrans11));
		
		if (!bibALL.isEmpty(cUntrans12))
			super.addString(new MyString(cUntrans12,bTrans12));

	}

	
	
	public MyE2_String(	String cUntrans1, boolean bTrans1, 
			String cUntrans2, boolean bTrans2, 
			String cUntrans3, boolean bTrans3, 
			String cUntrans4, boolean bTrans4, 
			String cUntrans5, boolean bTrans5, 
			String cUntrans6, boolean bTrans6,
			String cUntrans7, boolean bTrans7,
			String cUntrans8, boolean bTrans8,
			String cUntrans9, boolean bTrans9,
			String cUntrans10, boolean bTrans10,
			String cUntrans11, boolean bTrans11,
			String cUntrans12, boolean bTrans12,
			String cUntrans13, boolean bTrans13)
	{
		super(cUntrans1, bTrans1);
		
		if (!bibALL.isEmpty(cUntrans2))
		super.addString(new MyString(cUntrans2,bTrans2));
		
		if (!bibALL.isEmpty(cUntrans3))
		super.addString(new MyString(cUntrans3,bTrans3));
		
		if (!bibALL.isEmpty(cUntrans4))
		super.addString(new MyString(cUntrans4,bTrans4));
		
		if (!bibALL.isEmpty(cUntrans5))
		super.addString(new MyString(cUntrans5,bTrans5));
		
		if (!bibALL.isEmpty(cUntrans6))
		super.addString(new MyString(cUntrans6,bTrans6));
		
		if (!bibALL.isEmpty(cUntrans7))
		super.addString(new MyString(cUntrans7,bTrans7));
		
		if (!bibALL.isEmpty(cUntrans8))
		super.addString(new MyString(cUntrans8,bTrans8));
		
		if (!bibALL.isEmpty(cUntrans9))
		super.addString(new MyString(cUntrans9,bTrans9));

		if (!bibALL.isEmpty(cUntrans10))
		super.addString(new MyString(cUntrans10,bTrans10));

		if (!bibALL.isEmpty(cUntrans11))
		super.addString(new MyString(cUntrans11,bTrans11));

		if (!bibALL.isEmpty(cUntrans12))
			super.addString(new MyString(cUntrans12,bTrans12));
		
		if (!bibALL.isEmpty(cUntrans13))
			super.addString(new MyString(cUntrans13,bTrans13));
	}

	
	public MyE2_String(	String cUntrans1, boolean bTrans1, 
			String cUntrans2, boolean bTrans2, 
			String cUntrans3, boolean bTrans3, 
			String cUntrans4, boolean bTrans4, 
			String cUntrans5, boolean bTrans5, 
			String cUntrans6, boolean bTrans6,
			String cUntrans7, boolean bTrans7,
			String cUntrans8, boolean bTrans8,
			String cUntrans9, boolean bTrans9,
			String cUntrans10, boolean bTrans10,
			String cUntrans11, boolean bTrans11,
			String cUntrans12, boolean bTrans12,
			String cUntrans13, boolean bTrans13,
			String cUntrans14, boolean bTrans14)
	{
		super(cUntrans1, bTrans1);
		
		if (!bibALL.isEmpty(cUntrans2))
		super.addString(new MyString(cUntrans2,bTrans2));
		
		if (!bibALL.isEmpty(cUntrans3))
		super.addString(new MyString(cUntrans3,bTrans3));
		
		if (!bibALL.isEmpty(cUntrans4))
		super.addString(new MyString(cUntrans4,bTrans4));
		
		if (!bibALL.isEmpty(cUntrans5))
		super.addString(new MyString(cUntrans5,bTrans5));
		
		if (!bibALL.isEmpty(cUntrans6))
		super.addString(new MyString(cUntrans6,bTrans6));
		
		if (!bibALL.isEmpty(cUntrans7))
		super.addString(new MyString(cUntrans7,bTrans7));
		
		if (!bibALL.isEmpty(cUntrans8))
		super.addString(new MyString(cUntrans8,bTrans8));
		
		if (!bibALL.isEmpty(cUntrans9))
		super.addString(new MyString(cUntrans9,bTrans9));

		if (!bibALL.isEmpty(cUntrans10))
		super.addString(new MyString(cUntrans10,bTrans10));

		if (!bibALL.isEmpty(cUntrans11))
		super.addString(new MyString(cUntrans11,bTrans11));

		if (!bibALL.isEmpty(cUntrans12))
			super.addString(new MyString(cUntrans12,bTrans12));
		
		if (!bibALL.isEmpty(cUntrans13))
			super.addString(new MyString(cUntrans13,bTrans13));

		if (!bibALL.isEmpty(cUntrans14))
			super.addString(new MyString(cUntrans14,bTrans14));

	}

	
	
	
	
	/**
	 * 2015-04-01: neuer konstruktor fuer MyE2_String
	 */
	public MyE2_String(S.TextAndTranslationInfo... texte) {
		for (S.TextAndTranslationInfo text: texte) {
			if (text.translate()) {
				super.addTranslated(text.text());
			} else {
				super.addUnTranslated(text.text());
			}
		}
	}
	
	
	/**
	 * append translated string 
	 */
	public MyE2_String t(String string) {
		this.addTranslated(string);
		return this;
	}
	
	/**
	 * append untranslated string 
	 */
	public MyE2_String ut(String string) {
		this.addUnTranslated(string);
		return this;
	}
	
}
