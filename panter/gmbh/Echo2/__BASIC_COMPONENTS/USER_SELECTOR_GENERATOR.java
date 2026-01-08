package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;

public class USER_SELECTOR_GENERATOR
{
	private String[][] arrBuero = null;
	private String[][] arrBetrieb = null;
	private String[][] arrInaktiv = null;

	/*
	 * Hilfsklasse, was ein array fuer drop-down-selectoren erzeugt, die ueberall verwendet werden koennen.
	 * Dabei werden die Buero-Benutzer zuerst genannt, dann die benutzer aus dem betrieb dahinter
	 */
	public USER_SELECTOR_GENERATOR(boolean bFormatedIDs)
	{
		super();
		
		//zuerst die buero-benutzer, dann die anderen, dann die inaktiven (versteckt-shadow)
		
		String cSelect1 = "SELECT NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||')',ID_USER FROM "+bibE2.cTO()+".JD_USER " +
							" WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(IST_VERWALTUNG,'N')='Y' AND  NVL(AKTIV,'N')='Y' ORDER BY 1 ";
		
		String cSelect2 = "SELECT NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||'    <Betrieb>)',ID_USER  FROM "+bibE2.cTO()+".JD_USER " +
							" WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(IST_VERWALTUNG,'N')='N'     AND  NVL(AKTIV,'N')='Y' ORDER BY 1";

		
		String cSQLInaktiv = "SELECT NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||' *)',ID_USER FROM "+bibE2.cTO()+".JD_USER" +
						"  WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(AKTIV,'N')='N' ORDER BY 1";
		
		

		if (bFormatedIDs)
		{
			arrBuero = bibDB.EinzelAbfrageInArrayFormatiert(cSelect1,"");
			arrBetrieb = bibDB.EinzelAbfrageInArrayFormatiert(cSelect2,"");
			arrInaktiv = bibDB.EinzelAbfrageInArrayFormatiert(cSQLInaktiv,"");
		}
		else
		{
			arrBuero = bibDB.EinzelAbfrageInArray(cSelect1,"");
			arrBetrieb = bibDB.EinzelAbfrageInArray(cSelect2,"");
			arrInaktiv = bibDB.EinzelAbfrageInArray(cSQLInaktiv,"");
		}

		
	}
	
	
	private void add_(String[][] cPair, Vector<String[]> vPaare)
	{
		for (int i=0;i<cPair.length;i++)
		{
			vPaare.add(cPair[i]);
		}
	}

	/**
	 * 
	 * @param bMitLeerSelectVoraus
	 * @param arrTextValuePair_4_SelectWhereUsersNotSet (wenn null oder laenge<>2 wirds nicht beachtet)
	 * @return
	 */
	public String[][] get_AktiveBueroNutzer(boolean bMitLeerSelectVoraus, String[] arrTextValuePair_4_SelectWhereUsersNotSet)
	{
		
		//jetzt die liste aufbauen
		Vector<String[]> vPaare = new Vector<String[]>();
		
		if (bMitLeerSelectVoraus)
		{
			String[] cLeer = {"--",""};
			vPaare.add(cLeer);
		}

		//2013-06-21: text-werte-paar, wenn im dropdown sowas wie <undefinierte benutzer stehen soll)
		if (arrTextValuePair_4_SelectWhereUsersNotSet != null && arrTextValuePair_4_SelectWhereUsersNotSet.length==2) {
			vPaare.add(arrTextValuePair_4_SelectWhereUsersNotSet);
		}
		
		
		this.add_(arrBuero, vPaare);
		
		String[][] cWerte = new String[vPaare.size()][2]; 
		for (int i=0;i<vPaare.size();i++)
		{
			cWerte[i][0]=vPaare.get(i)[0];
			cWerte[i][1]=vPaare.get(i)[1];
		}

		return cWerte;
	}
	
	/**
	 * 
	 * @param bMitLeerSelectVoraus
	 * @param arrTextValuePair_4_SelectWhereUsersNotSet (wenn null oder laenge<>2 wirds nicht beachtet)
	 * @return
	 */
	public String[][] get_AktiveBenutzer(boolean bMitLeerSelectVoraus, String[] arrTextValuePair_4_SelectWhereUsersNotSet)
	{
		
		//jetzt die liste aufbauen
		Vector<String[]> vPaare = new Vector<String[]>();
		
		if (bMitLeerSelectVoraus)
		{
			String[] cLeer = {"--",""};
			vPaare.add(cLeer);
		}

		//2013-06-21: text-werte-paar, wenn im dropdown sowas wie <undefinierte benutzer stehen soll)
		if (arrTextValuePair_4_SelectWhereUsersNotSet != null && arrTextValuePair_4_SelectWhereUsersNotSet.length==2) {
			vPaare.add(arrTextValuePair_4_SelectWhereUsersNotSet);
		}

		this.add_(arrBuero, vPaare);
		this.add_(arrBetrieb, vPaare);
		
		
		String[][] cWerte = new String[vPaare.size()][2]; 
		for (int i=0;i<vPaare.size();i++)
		{
			cWerte[i][0]=vPaare.get(i)[0];
			cWerte[i][1]=vPaare.get(i)[1];
		}

		return cWerte;
	}
	

	/**
	 * 
	 * @param bMitLeerSelectVoraus
	 * @param arrTextValuePair_4_SelectWhereUsersNotSet (wenn null oder laenge<>2 wirds nicht beachtet)
	 * @return
	 */
	public String[][] get_AlleBenutzer(boolean bMitLeerSelectVoraus, String[] arrTextValuePair_4_SelectWhereUsersNotSet)
	{
		
		//jetzt die liste aufbauen
		Vector<String[]> vPaare = new Vector<String[]>();
		
		if (bMitLeerSelectVoraus)
		{
			String[] cLeer = {"--",""};
			vPaare.add(cLeer);
		}

		//2013-06-21: text-werte-paar, wenn im dropdown sowas wie <undefinierte benutzer stehen soll)
		if (arrTextValuePair_4_SelectWhereUsersNotSet != null && arrTextValuePair_4_SelectWhereUsersNotSet.length==2) {
			vPaare.add(arrTextValuePair_4_SelectWhereUsersNotSet);
		}

		this.add_(arrBuero, vPaare);
		this.add_(arrBetrieb, vPaare);
		this.add_(arrInaktiv, vPaare);
		
		String[][] cWerte = new String[vPaare.size()][2]; 
		for (int i=0;i<vPaare.size();i++)
		{
			cWerte[i][0]=vPaare.get(i)[0];
			cWerte[i][1]=vPaare.get(i)[1];
		}

		return cWerte;
	}


	public String[][] get_arrAktiveBuero_User()
	{
		return arrBuero;
	}


	public String[][] get_arrAktiveBetrieb_User()
	{
		return arrBetrieb;
	}


	public String[][] get_arrInaktive_User()
	{
		return arrInaktiv;
	}
	

	
}
