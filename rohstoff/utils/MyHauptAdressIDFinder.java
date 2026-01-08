package rohstoff.utils;

import javax.servlet.http.HttpSession;

import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;



//NEU01

/*
 * sucht die ID der Hauptadresse einer lieferadresse oder gibt die eigene (falls eine Hauptadresse) id zurueck
 */
public class MyHauptAdressIDFinder 
{
	private String cID_ADRESSE_HAUPT_Unformated = null;
	private String cID_ADRESSTYP = null;

	
	
	/**
	 * @param cID_ADRESS_TO_TEST (kann auch in schreibweise formatiert sein)
	 * @param oSES
	 * @throws myException
	 */
	public MyHauptAdressIDFinder(String ID_ADRESS_TO_TEST,HttpSession oSES) throws myException
	{
		super();
		
		String cID_ADRESS_TO_TEST = null;
		
		DotFormatterGermanFixed oDF = new DotFormatterGermanFixed(ID_ADRESS_TO_TEST);
		if (oDF.doFormat())
		{
			cID_ADRESS_TO_TEST = oDF.getStringUnFormated();
		}
		else
		{
			throw new myException("MyAdressIDFinder:Constructor:"+ID_ADRESS_TO_TEST+" is no correct number!");
		}
		
		String cQuery1 = "SELECT ID_ADRESSE,ADRESSTYP FROM "+bibALL.get_TABLEOWNER()+".JT_ADRESSE WHERE ID_ADRESSE="+bibALL.ReplaceTeilString(cID_ADRESS_TO_TEST, ".", "");
		
		String[][] cAntwort = bibDB.EinzelAbfrageInArray(cQuery1);
		
		if (cAntwort == null)
			throw new myException("MyAdressIDFinder:Constructor:"+cID_ADRESS_TO_TEST+" not found (1)!");
		
		if (cAntwort.length == 0)
			throw new myException("MyAdressIDFinder:Constructor:"+cID_ADRESS_TO_TEST+" not found (2)!");
		
		
		if (cAntwort[0][1].equals(""+myCONST.ADRESSTYP_FIRMENINFO))
		{
			this.cID_ADRESSE_HAUPT_Unformated = 	cAntwort[0][0];
			this.cID_ADRESSTYP = 			cAntwort[0][1];
		}
		else
		{
			if (cAntwort[0][1].equals(""+myCONST.ADRESSTYP_LIEFERADRESSE))
			{
				String cQuery2 = "SELECT ID_ADRESSE_BASIS FROM "+bibALL.get_TABLEOWNER()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_LIEFER="+bibALL.ReplaceTeilString(cID_ADRESS_TO_TEST, ".", "");
				
				String[][] cAntwort2 = bibDB.EinzelAbfrageInArray(cQuery2);
				
				if (cAntwort2 == null)
					throw new myException("MyAdressIDFinder:Constructor:"+cID_ADRESS_TO_TEST+" not found (3)!");
				
				if (cAntwort2.length != 1)
					throw new myException("MyAdressIDFinder:Constructor:"+cID_ADRESS_TO_TEST+" not found (4)!");
				
				this.cID_ADRESSE_HAUPT_Unformated = 	cAntwort2[0][0];
				this.cID_ADRESSTYP = 					""+myCONST.ADRESSTYP_LIEFERADRESSE;
			}
		}
		
	}



	public String get_cID_ADRESSTYP() 
	{
		return cID_ADRESSTYP;
	}



	public String get_cID_ADRESSE_HAUPT_Unformated() 
	{
		return cID_ADRESSE_HAUPT_Unformated;
	}


	
	
	
	
	
}
