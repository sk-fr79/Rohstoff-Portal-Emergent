package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class BS_SelectFieldOwnLAGER extends MyE2_DB_SelectField
{

	public BS_SelectFieldOwnLAGER(SQLField osqlField) throws myException
	{
		super(osqlField);
		
		// lageradressen
//		String cQuery = "SELECT   NVL(JT_ADRESSE.NAME1,'-')||' '|| NVL(JT_ADRESSE.STRASSE,'-')||' '|| NVL(JT_ADRESSE.ORT,'-')," +
//							"	JT_ADRESSE.ID_ADRESSE " +
//						" FROM "+
//							bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_LIEFERADRESSE "+
//					    " WHERE " +
//					    	" JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER " +
//					    " AND "+
//					    	" JT_LIEFERADRESSE.ID_ADRESSE_BASIS="+bibALL.get_ID_ADRESS_MANDANT()+
//					    " ORDER BY NAME1";

		String cQuery = "SELECT  NVL(JT_ADRESSE.PLZ,'-')||' '|| NVL(JT_ADRESSE.ORT,'-')||' '|| NVL(JT_ADRESSE.NAME1,'-')," +
							"	JT_ADRESSE.ID_ADRESSE " +
						" FROM "+
							bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_LIEFERADRESSE "+
					    " WHERE " +
					    	" JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER " +
					    " AND "+
					    	" JT_LIEFERADRESSE.ID_ADRESSE_BASIS="+bibALL.get_ID_ADRESS_MANDANT()+
					    " ORDER BY JT_ADRESSE.ORT";

		
		// eigene adresse
		String cQuery2 = "SELECT   NVL(JT_ADRESSE.NAME1,'-')||' '|| NVL(JT_ADRESSE.STRASSE,'-')||' '|| NVL(JT_ADRESSE.ORT,'-')," +
							"	JT_ADRESSE.ID_ADRESSE " +
						" FROM "+
							bibE2.cTO()+".JT_ADRESSE"+
					    " WHERE " +
					    	" ID_ADRESSE="+bibALL.get_ID_ADRESS_MANDANT();

		
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cArray = oDB.EinzelAbfrageInArrayFormatiert(cQuery,"");
		String[][] cArray2 = oDB.EinzelAbfrageInArrayFormatiert(cQuery2,"");
		bibALL.destroy_myDBToolBox(oDB);
		
		
		
		String[][] cWerte = null; 
		if ((cArray == null || cArray.length==0) && (cArray2 == null || cArray2.length==0) )
		{
			cWerte = new String[1][2];
			cWerte[0][0]="-";
			cWerte[0][1]="";
		}
		else
		{
			cWerte = new String[1+cArray.length+cArray2.length][2];
			cWerte[0][0]="-";
			cWerte[0][1]="";

			int icount = 1;
			for (int i=0;i<cArray2.length;i++)
			{
				cWerte[icount][0] = new MyE2_String("Hauptadresse: ").CTrans()+cArray2[i][0];
				cWerte[icount][1] = cArray2[i][1];
				icount++;
			}
			
			for (int i=0;i<cArray.length;i++)
			{
				cWerte[icount][0] = cArray[i][0];
				cWerte[icount][1] = cArray[i][1];
				icount++;
			}
		}

		this.set_ListenInhalt(cWerte,false);
		
	}

}
