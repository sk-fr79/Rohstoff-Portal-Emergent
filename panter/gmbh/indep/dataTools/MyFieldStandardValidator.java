package panter.gmbh.indep.dataTools;

import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionDataValueNotFittingToField;


public class MyFieldStandardValidator extends XX_FieldNewValueValidator
{

	public MyFieldStandardValidator(SQLField field)
	{
		super(field);
	}

	public boolean doValidate(String cNewValueFormated)
	{
		this.set_oErrorStringForUser(null);
		
		boolean bRueck = false;
		/*
		 * die validierung wird mithilfe einer umwandlung gemacht
		 */
		try
		{
			this.get_oSQLField().get_oFieldMetaDef().get_cStringForDataBase(cNewValueFormated, true,false);
			bRueck = true;
		}
		catch (myExceptionDataValueNotFittingToField ex)
		{
			
			//2015-02-25: vereinheitlichung der meldungsgenerierung
			String namensNennung = S.NN(this.get_oSQLField().get_cFieldLabelForUser().CTrans(), this.get_oSQLField().get_cFieldLabel());
			this.set_oErrorStringForUser(ex.get_Message(namensNennung,cNewValueFormated).get_cMessage());
			
//			if (ex.get_iErrorType() == myExceptionDataValueNotFittingToField.VALUE_IS_TOO_LONG)
//			{
//				MyString oErrorString = new MyString("Fehler: Eingabe ist zu lang !");
//				oErrorString.addUnTranslatedInFront(this.get_oSQLField().get_cFieldLabelForUser().CTrans()+": ");
//				this.set_oErrorStringForUser(oErrorString);
//			}
//			else if (ex.get_iErrorType() == myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_A_DATE)
//			{
//				MyString oErrorString = new MyString("Fehler: Eingabe ist kein Datumswert !");
//				oErrorString.addUnTranslatedInFront(this.get_oSQLField().get_cFieldLabelForUser().CTrans()+": ");
//				this.set_oErrorStringForUser(oErrorString);
//			}
//			else if (ex.get_iErrorType() == myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_A_NUMBER)
//			{
//				MyString oErrorString = new MyString("Fehler: Eingabe ist kein Zahlenwert !");
//				oErrorString.addUnTranslatedInFront(this.get_oSQLField().get_cFieldLabelForUser().CTrans()+": ");
//				this.set_oErrorStringForUser(oErrorString);
//			}
//			else if (ex.get_iErrorType() == myExceptionDataValueNotFittingToField.VALUE_CAN_NOT_BE_NULL)
//			{
//				MyString oErrorString = new MyString("Fehler: Eingabe darf nicht leer sein !");
//				oErrorString.addUnTranslatedInFront(this.get_oSQLField().get_cFieldLabelForUser().CTrans()+": ");
//				this.set_oErrorStringForUser(oErrorString);
//			}
			
		}
		catch (myException ex)
		{
			MyString oErrorString = new MyString("");
			oErrorString.addUnTranslatedInFront(ex.get_ErrorMessage().get_cMessage().COrig());
			this.set_oErrorStringForUser(oErrorString);
		}

		return bRueck;
	}



}
