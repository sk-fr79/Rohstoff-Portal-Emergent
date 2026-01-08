package panter.gmbh.indep.dataTools;

import javax.servlet.http.HttpSession;

import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class SQLFieldForPrimaryKey extends SQLField
{
	private String cQueryForNextSequence = null;

	private String cLastCreatedPrimaryKEY_Formated = "";
	private String cLastCreatedPrimaryKEY_UnFormated = "";
	
	/**
	 * @param ctablename
	 * @param cfieldname
	 * @param cfieldalias
	 * @param cfieldlabelforUser
	 * @param oses
	 * @param cqueryForNextSequence
	 * @param bWriteable (wenn false, dann ist die ganze tabelle nicht beschreibbar)
	 * @throws myException
	 */
	public SQLFieldForPrimaryKey(	String 		ctablename, 
									String 		cfieldname, 
									String 		cfieldalias, 
									MyString 	cfieldlabelforUser, 
									HttpSession	oses,
									String 		cqueryForNextSequence,
									boolean 	bWriteable) throws myException
	{
		super(ctablename,  cfieldname, cfieldalias, cfieldlabelforUser,false, oses);
		this.cQueryForNextSequence = cqueryForNextSequence;
		
		this.set_bFieldCanBeWrittenInMask(false);
		this.set_bWriteable(bWriteable);
	}

	
	
	
	public String cQueryNext_KEY_ValueFormated() throws myException
	{
		String cRueck = null;
		
		if (!this.get_bWriteable())
			throw new myException("SQLFieldForPrimaryKey:cQueryNextSEQ_Value:Not allowed for only-view-tables !!");
		
		//MyDBToolBox oDB = bibALL.Get_DB_FOR_ALL(this.get_oSES());
		
		if (bibALL.isEmpty(this.cQueryForNextSequence))
			throw new myException("SQLFieldForPrimaryKey:cQueryNextSEQ_Value:Sequence-Query is empty!");
		
		cRueck = bibDB.EinzelAbfrage(this.cQueryForNextSequence);
		
		if (cRueck.startsWith("@"))
			throw new myException("SQLFieldForPrimaryKey:cQueryNextSEQ_Value:No value !!");
		
		/*
		 * sicherheitsabfrage, da hier ein integer-wert zwingend noetig ist,
		 * es wird an der stelle ein unformatierter wert wie ein formatierter wert 
		 * behandelt. das geht immer mit ganzzahlen
		 */
		if (! bibALL.isInteger(cRueck))
			throw new myException("SQLFieldForPrimaryKey:cQueryNextSEQ_Value:No Integer-Value !!");
			
		this.cLastCreatedPrimaryKEY_Formated=cRueck;
		this.cLastCreatedPrimaryKEY_UnFormated=cRueck;
		
		return cRueck;
	}

	
	
	public String get_cLastCreatedPrimaryKEY_Unformated()
	{
		return cLastCreatedPrimaryKEY_UnFormated;
	}
	
	public String get_cLastCreatedPrimaryKEY_Formated()
	{
		return cLastCreatedPrimaryKEY_Formated;
	}
	
	
	
}
