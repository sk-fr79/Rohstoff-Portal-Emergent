package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorString;

public class Interpreter_JASPERHASH 
{
	/**
	 * falls der interpretierte jasper-hash-eintrag keine einfacher String, sondern ein Vector aus Strings ist,
	 * werden die Teilstrings mit diesem Separierer getrennt!
	 */
	public static String    SEPARATOR_MULTISTRING_WHEN_VECTOR = "|";
	
	private String 			cEingabeText = "";
	
	private String          cAntwort = null;
	


	/**
	 * 
	 * @param EingabeText  MUSS in der Hashmap beginnen beginnen
	 * @param oJasperHash
	 */
	public Interpreter_JASPERHASH(String EingabeText, E2_JasperHASH oJasperHash) throws myException
	{
		super();
		this.cEingabeText = EingabeText.trim();

		//falls der key mit # # geschrieben wird
		this.cEingabeText = bibALL.ReplaceTeilString(this.cEingabeText, "#", "");
		
		

		if (this.cEingabeText.startsWith("<JASPERHASH>"))
		{
			this.cEingabeText = this.cEingabeText.substring("<JASPERHASH>".length());
		}
		else
		{
			throw new myException(this,"Error: JASPERHASH-Evaluation: NO JASPERHASH-Script !!!");
		}


		if (S.isEmpty(this.cEingabeText))
		{
			throw new myException(this,"Error: JASPER-Evaluation: NO empty Hash-Value allowed !!!");
		}

		
		//2014-09-22: es wird auch ein mehrfach-wert in dem ausgelesenen E2_JASPERHASH-Tag zugelassen und in einen einzelnen String umgewandelt
		if (!oJasperHash.containsKey(this.cEingabeText))
		{
			throw new myException(this,"Error: JasperHash: Value "+S.NN(this.cEingabeText,"--")+" is not in present in HashSet!");
		}
		else if (oJasperHash.containsKey(this.cEingabeText) && !
					(
						(oJasperHash.get(this.cEingabeText) instanceof String) ||
						(oJasperHash.get(this.cEingabeText) instanceof VectorString)
					)
				)
		{
			throw new myException(this,"Error: JasperHash: Value "+S.NN(this.cEingabeText,"--")+" is not a String!");
		}
		else
		{
//			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Ergebnis: ",true,(String)hm_Austausch.get(this.cEingabeText),false)));
			if (oJasperHash.get(this.cEingabeText) instanceof String) {
				this.cAntwort = (String)oJasperHash.get(this.cEingabeText);
			} else {
				this.cAntwort = ((VectorString)oJasperHash.get(this.cEingabeText)).get_VerketteteWerte(Interpreter_JASPERHASH.SEPARATOR_MULTISTRING_WHEN_VECTOR);
			}
		}
	}
	
	
	public String get_cAntwort() 
	{
		return cAntwort;
	}

	
	
	
}
