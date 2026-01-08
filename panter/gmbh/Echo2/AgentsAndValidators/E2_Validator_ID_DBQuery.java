package panter.gmbh.Echo2.AgentsAndValidators;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;

public class E2_Validator_ID_DBQuery extends XX_ActionValidator {

	private MyString 	cError = null;
	private String  	cSQLQuery = null;
	private String[]	cValidAnswerValues = null;
	private boolean   	bAnswersAreValid = true;

	/**
	 * @param SQLQuery (must contain #WERT# for ID which is validated) - ansonsten nur als globalValidator verwendbar
	 * @param AnswerValues
	 * @param bAnswersValid
	 * @param errorMessage
	 */
	public E2_Validator_ID_DBQuery(String SQLQuery, String[] AnswerValues, boolean bAnswersValid, MyString errorMessage)
	{
		super();
		this.bAnswersAreValid = bAnswersValid;
		
		this.__init( SQLQuery, AnswerValues, errorMessage);
	}


	
	public E2_Validator_ID_DBQuery(String TableName, String CheckedFieldBlock, String ID_NAME,String cValidValue, boolean bAnswersValid, MyString errorMessage)
	{
		this.bAnswersAreValid = bAnswersValid;

		String cQuery = "SELECT "+CheckedFieldBlock+" FROM "+bibE2.cTO()+"."+TableName+" WHERE "+ID_NAME+"=#WERT#";
		String[] cValidAnswers = new String[1];
		cValidAnswers[0]=cValidValue;
		
		this.__init( cQuery, cValidAnswers, errorMessage);
	}

	
	public E2_Validator_ID_DBQuery(String TableName, String CheckedFieldBlock, String cValidValue, boolean bAnswersValid, MyString errorMessage)
	{
		this.bAnswersAreValid = bAnswersValid;

		String cQuery = "SELECT "+CheckedFieldBlock+" FROM "+bibE2.cTO()+"."+TableName+" WHERE ID_"+TableName.substring(3)+"=#WERT#";
		String[] cValidAnswers = new String[1];
		cValidAnswers[0]=cValidValue;
		
		this.__init( cQuery, cValidAnswers, errorMessage);
	}
	
	
	public E2_Validator_ID_DBQuery(String TableName, String CheckedFieldBlock, String ID_NAME,String[] cValidValue, boolean bAnswersValid, MyString errorMessage)
	{
		this.bAnswersAreValid = bAnswersValid;

		String cQuery = "SELECT "+CheckedFieldBlock+" FROM "+bibE2.cTO()+"."+TableName+" WHERE "+ID_NAME+"=#WERT#";
		String[] cValidAnswers = new String[1];
		cValidAnswers=cValidValue;
		
		this.__init( cQuery, cValidAnswers, errorMessage);
	}

	
	public E2_Validator_ID_DBQuery(String TableName, String CheckedFieldBlock, String[] cValidValue, boolean bAnswersValid, MyString errorMessage)
	{
		this.bAnswersAreValid = bAnswersValid;

		String cQuery = "SELECT "+CheckedFieldBlock+" FROM "+bibE2.cTO()+"."+TableName+" WHERE ID_"+TableName.substring(3)+"=#WERT#";
		String[] cValidAnswers = new String[1];
		cValidAnswers=cValidValue;
		
		this.__init( cQuery, cValidAnswers, errorMessage);
	}

	
	private void __init(String SQLQuery, String[] validAnswerValues, MyString errorMessage)
	{
		this.cError = 			errorMessage;
		
		this.cSQLQuery = SQLQuery;
		
		cValidAnswerValues = validAnswerValues;
	}
	

	private MyE2_Message get_ErrorMessage() 
	{
		return new MyE2_Message(this.cError,MyE2_Message.TYP_ALARM,false);
	}

	
	/*
	 * (non-Javadoc)
	 * @see utilities.myEcho2.AgentsAndValidators.XX_ActionValidator#isValid()
	 * validierung auch bei nicht mit #WERT# versehene Querys, aber nur im global-bereich
	 */
	public MyE2_MessageVector isValid(Component oCompWhichIsValidated) 
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		boolean bHelp = true;
		
		bHelp = this.bIsValid(this.cSQLQuery);
		
		if (!bHelp)
		{
			oMV.add_MESSAGE(this.get_ErrorMessage());
		}
		return oMV;
	}

	
	
	public MyE2_MessageVector isValid(String cID_Unformated)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		boolean bHelp = true;

		if (this.cSQLQuery.indexOf("#WERT#")==-1)
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Systemfehler Validierung: Abfrage MUSS einen String #WERT# enthalten !")));
		}
		else
		{
			String cSQL = bibALL.ReplaceTeilString(this.cSQLQuery,"#WERT#",cID_Unformated);
			bHelp = this.bIsValid(cSQL);
			if (!bHelp)
			{
				oMV.add_MESSAGE(this.get_ErrorMessage());
			}
		}
		return oMV;
	}

	
	
	private boolean bIsValid(String cSQL)
	{
		boolean bRueck = true;
		
		String[][] cAnswer = bibDB.EinzelAbfrageInArray(cSQL,"");
		
		if (cAnswer == null || cAnswer.length==0)
		{
			this.cError = new MyE2_String("Error Validation-Query "+cSQL,false);
			return false;
		}
		
		if (cAnswer.length != 1)
		{
			this.cError = new MyE2_String("Systemfehler Validierung: Anzahl der Rueckgabeezeilen ist nicht gleich 1 ->"+cAnswer.length);
			return false;
		}
		
		if (cAnswer[0].length != this.cValidAnswerValues.length)
		{
			this.cError = new MyE2_String("Systemfehler Validierung: Anzahl der Abfragespalten muss gleich sein der Anzahl der Vergleichswerte !");
			return false;
		}

		/*
		 *  wenn bAnswersAreValid=true, dann muessen alle stimmen,
		 *  wenn bAnswersAreValid=false, dann darf keiner uebereinstimmen
		 */
		if (this.bAnswersAreValid)
		{
			for (int i=0;i<this.cValidAnswerValues.length;i++)
			{
				if (!this.cValidAnswerValues[i].equals(cAnswer[0][i]))
				{
					bRueck = false;
				}
			}
		}
		else
		{
			for (int i=0;i<this.cValidAnswerValues.length;i++)
			{
				if (this.cValidAnswerValues[i].equals(cAnswer[0][i]))
				{
					bRueck = false;
				}
			}
		}

		
		return bRueck;
	}
	

}
