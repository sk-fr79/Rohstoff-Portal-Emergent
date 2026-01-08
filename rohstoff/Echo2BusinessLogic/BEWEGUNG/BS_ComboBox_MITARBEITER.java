package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class BS_ComboBox_MITARBEITER extends 		MyE2_DB_ComboBoxErsatz
{
//	public BS_ComboBox_MITARBEITER(SQLFieldMAP osqlFieldMap) throws myException
//	{
////		super(osqlFieldMap.get_("NAME_BEARBEITER_INTERN"), 
////								"SELECT  NVL(VORNAME,'-')||' '|| NVL(NAME1,'-') AS NAM, NVL(VORNAME,'-')||' '|| NVL(NAME1,'-') FROM "
////								+bibE2.cTO()+".JD_USER WHERE NVL(JD_USER.AKTIV,'N')='Y' " +
////										" AND ID_MANDANT="+bibALL.get_ID_MANDANT()+
////										" AND NVL(IST_VERWALTUNG,'N')='Y' " +
////										" AND TRIM( NVL(VORNAME,'-')||' '|| NVL(NAME1,'-'))<>'- -' "+
////								" UNION "+
////								"SELECT  NVL(VORNAME,'-')||' '|| NVL(NAME1,'-') AS NAM, NVL(VORNAME,'-')||' '|| NVL(NAME1,'-') FROM "
////								+bibE2.cTO()+".JD_USER WHERE NVL(JD_USER.AKTIV,'N')='Y' " +
////										" AND ID_MANDANT="+bibALL.get_ID_MANDANT()+
////										" AND NVL(IST_VERWALTUNG,'N')='N' " +
////										" AND TRIM( NVL(VORNAME,'-')||' '|| NVL(NAME1,'-'))<>'- -' "+
////								" ORDER BY NAM", false);
////		this.set_WidthAndHeightOfDropDown(new Extent(200),new Extent(100),null, new Integer(200));
//		
//	}

	
	public BS_ComboBox_MITARBEITER(SQLFieldMAP osqlFieldMap, String cNameSQLField) throws myException
	{
		super(osqlFieldMap.get_(cNameSQLField));
		
		
		String cSelect1 = "SELECT NVL(VORNAME,'')||' '||NVL(NAME1,NVL(NAME,'-'))||' ('|| NVL(KUERZEL,'-')||')',NVL(VORNAME,'-')||' '|| NVL(NAME1,'-') FROM "+bibE2.cTO()+".JD_USER " +
							" WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(IST_VERWALTUNG,'N')='Y' AND  NVL(AKTIV,'N')='Y' ORDER BY NVL(VORNAME,'')||' '||NVL(NAME1,NVL(NAME,'-'))||' ('|| NVL(KUERZEL,'-')||')'";
		
		String cSelect2 = "SELECT NVL(VORNAME,'')||' '||NVL(NAME1,NVL(NAME,'-'))||' ('|| NVL(KUERZEL,'-')||')',NVL(VORNAME,'-')||' '|| NVL(NAME1,'-') FROM "+bibE2.cTO()+".JD_USER " +
							" WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(IST_FAHRER,'N')='Y'     AND  NVL(AKTIV,'N')='Y' ORDER BY NVL(VORNAME,'')||' '||NVL(NAME1,NVL(NAME,'-'))||' ('|| NVL(KUERZEL,'-')||')'";


		String[][] cRueck1 = bibDB.EinzelAbfrageInArrayFormatiert(cSelect1,"");;
		String[][] cRueck2 = bibDB.EinzelAbfrageInArrayFormatiert(cSelect2,"");;
		
		//jetzt die liste aufbauen
		Vector<String[]> vPaare = new Vector<String[]>();
		Vector<String>   vAusschluss = new Vector<String>();
		

		this.add_(cRueck1, vPaare, vAusschluss);
		this.add_(cRueck2, vPaare, vAusschluss);

		String[][] cWerte = new String[vPaare.size()][2]; 
		for (int i=0;i<vPaare.size();i++)
		{
			cWerte[i][0]=vPaare.get(i)[0];
			cWerte[i][1]=vPaare.get(i)[1];
		}

		
		this.set_Varianten(cWerte, "", null, false);
		
		this.set_WidthAndHeightOfDropDown(new Extent(200),new Extent(100),null, new Integer(150));
		
	}

	private void add_(String[][] cPair, Vector<String[]> vPaare, Vector<String>   vAusschluss)
	{
		for (int i=0;i<cPair.length;i++)
		{
			if (vAusschluss.contains(cPair[i][1]))
			{
				continue;
			}
			vPaare.add(cPair[i]);
			vAusschluss.add(cPair[i][1]);
		}
	}

}
