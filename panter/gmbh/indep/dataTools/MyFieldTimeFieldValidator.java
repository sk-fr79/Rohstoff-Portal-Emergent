package panter.gmbh.indep.dataTools;

import panter.gmbh.indep.MyString;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;


/*
 * validator, ueberwacht die eingabe von vernueftigen zeitangaben
 * in der Form 12:55
 */
public class MyFieldTimeFieldValidator extends XX_FieldNewValueValidator
{

	public MyFieldTimeFieldValidator(SQLField field)
	{
		super(field);
	}

	public boolean doValidate(String cNewValueFormated)
	{
		/*
		 * leer ist erlaubt
		 */
		if (bibALL.isEmpty(cNewValueFormated))
			return true;
		
		
		this.set_oErrorStringForUser(null);
		
		boolean bRueck = false;
		
		/*
		 * die validierung wird mithilfe einer umwandlung gemacht
		 */
		try
		{
			StringSeparator oSep = new StringSeparator(cNewValueFormated,":");
			if (oSep.size()==2)
			{
				String Stunde = oSep.get_(0);
				String Minute = oSep.get_(1);
				
				if (Stunde.length()==2 && Minute.length()==2)
				{
					int iStunde = new Integer(Stunde).intValue();
					int iMinute = new Integer(Minute).intValue();
					
					if (iStunde>=0 && iStunde<=23)
					{
						if (iMinute >= 0 && iMinute<=59)
						{
							bRueck = true;
						}
					}
				}
			}
		}
		catch (Exception ex)
		{
			MyString oErrorString = new MyString("");
			oErrorString.addUnTranslatedInFront("Bitte füllen Sie Zeit-Felder mit einem gültigen Wert (z.B. 12:24");
			this.set_oErrorStringForUser(oErrorString);
			return false;
		}

		if (!bRueck)
		{
			MyString oErrorString = new MyString("");
			oErrorString.addUnTranslatedInFront("Bitte füllen Sie Zeit-Felder mit einem gültigen Wert (z.B. 12:24");
			this.set_oErrorStringForUser(oErrorString);
		}
		
		return bRueck;
	}



}
