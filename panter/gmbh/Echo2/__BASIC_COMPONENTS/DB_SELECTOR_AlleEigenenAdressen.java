package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class DB_SELECTOR_AlleEigenenAdressen extends MyE2_DB_SelectField 
{
	public DB_SELECTOR_AlleEigenenAdressen(SQLField osqlField, int iWidth) throws myException
	{
		super(osqlField);
		
		String cID_ADRESSE_OWN = bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1");
		

		String cQuery = "select substr('Fa. - ' ||  A.NAME1 || ' ' || A.NAME2 || ', '|| A.STRASSE || ' '|| A.HAUSNUMMER|| ', ' || A.ORT,1,80), A.ID_ADRESSE "+
						" from JT_ADRESSE A "+
						" where A.ID_ADRESSE="+cID_ADRESSE_OWN+
						"	UNION "+
						" select   substr('Lag. - ' ||  A.NAME1 || ' ' || A.NAME2 || ', '|| A.STRASSE|| ' ' || A.HAUSNUMMER || ', '|| A.ORT,1,80), A.ID_ADRESSE "+
						" FROM JT_LIEFERADRESSE L  "+
						" left outer join JT_ADRESSE A ON L.ID_ADRESSE_LIEFER = A.ID_ADRESSE "+
						" where L.ID_ADRESSE_BASIS="+cID_ADRESSE_OWN+" AND NVL(A.AKTIV,'N')='Y' "+
						" UNION "+
						//2015-12-04: mitarbeiteradressen weniger info 
						//" select   substr('Mitarb. - ' ||  A.NAME1 || ' ' || A.VORNAME || ', '|| A.STRASSE || ' ' || A.HAUSNUMMER|| ', ' || A.ORT,1,80), A.ID_ADRESSE "+ 
						" select   substr('Mitarb. - ' ||  A.NAME1 || ' ' || A.VORNAME,1,80), A.ID_ADRESSE "+
						" from JT_MITARBEITER L "+
						"  left outer join JT_ADRESSE A ON L.ID_ADRESSE_MITARBEITER = A.ID_ADRESSE "+
						"  where L.ID_ADRESSE_BASIS="+cID_ADRESSE_OWN+" AND NVL(A.AKTIV,'N')='Y' " +
						" ORDER by 1";
		
		String cQuery_passiv = 
						" select   substr('Lag. - ' ||  A.NAME1 || ' ' || A.NAME2 || ', '|| A.STRASSE|| ' ' || A.HAUSNUMMER || ', '|| A.ORT,1,80), A.ID_ADRESSE "+
						" FROM JT_LIEFERADRESSE L  "+
						" left outer join JT_ADRESSE A ON L.ID_ADRESSE_LIEFER = A.ID_ADRESSE "+
						" where L.ID_ADRESSE_BASIS="+cID_ADRESSE_OWN+" AND NVL(A.AKTIV,'N')='N' "+
						" UNION "+
						//2015-12-04: mitarbeiteradressen weniger info 
						//" select   substr('Mitarb. - ' ||  A.NAME1 || ' ' || A.VORNAME || ', '|| A.STRASSE || ' ' || A.HAUSNUMMER|| ', ' || A.ORT,1,80), A.ID_ADRESSE "+ 
						" select   substr('Mitarb. - ' ||  A.NAME1 || ' ' || A.VORNAME,1,80), A.ID_ADRESSE "+
						" from JT_MITARBEITER L "+
						"  left outer join JT_ADRESSE A ON L.ID_ADRESSE_MITARBEITER = A.ID_ADRESSE "+
						"  where L.ID_ADRESSE_BASIS="+cID_ADRESSE_OWN+" AND NVL(A.AKTIV,'N')='N' " +
						" ORDER by 1";



		String[][] cArray = bibDB.EinzelAbfrageInArrayFormatiert(cQuery,"");
		
		String[][] cWerte = null; 
		if ((cArray == null || cArray.length==0))
		{
			cWerte = new String[1][2];
			cWerte[0][0]="-";
			cWerte[0][1]="";
		}
		else
		{
			cWerte = new String[1+cArray.length][2];
			cWerte[0][0]="-";
			cWerte[0][1]="";

			int icount = 1;
			for (int i=0;i<cArray.length;i++)
			{
				cWerte[icount][0] = cArray[i][0];
				cWerte[icount][1] = cArray[i][1];
				icount++;
			}
		}
		
		this.set_ListenInhalt(cWerte,false);
		this.setWidth(new Extent(iWidth));
		
		String[][] cArrayPassiv = bibDB.EinzelAbfrageInArrayFormatiert(cQuery_passiv,"");
		if (cArrayPassiv != null && cArrayPassiv.length>0) {
			dataToView  dv_passiv = new dataToView(cArrayPassiv, false, bibE2.get_CurrSession());
			this.set_odataToViewShadow(dv_passiv);
		}
		
	}

}


