package rohstoff.Echo2BusinessLogic._4_ALL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.E2_Selection_Row_With_Multi_Cols;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;

public class FirmenSelectorKlasse extends E2_Selection_Row_With_Multi_Cols
{
	public FirmenSelectorKlasse() throws myException
	{
		super(MyE2_Row.STYLE_THIN_BORDER(),200,true);
		this.add_QueryStringForSteps("SELECT ID_ADRESSKLASSE_DEF,KURZBEZEICHNUNG,BEZEICHNUNG FROM JT_ADRESSKLASSE_DEF ORDER BY KURZBEZEICHNUNG" , 
								new MyE2_String("Adressklasse"),150,1,null);
		
		this.add_QueryStringForSteps("SELECT ID_LAND,LAENDERNAME FROM JD_LAND WHERE ID_LAND IN (SELECT ID_LAND FROM JT_ADRESSE WHERE " +
												"  ADRESSTYP=1 AND" +
												" NVL(JT_ADRESSE.AKTIV,'N')='Y' AND " +
												"  ID_ADRESSE IN (SELECT ID_ADRESSE FROM JT_ADRESSKLASSE WHERE ID_ADRESSKLASSE_DEF = #WERT1#))" +
												"  ORDER BY LAENDERNAME",
												new MyE2_String("Land"),150,1,null);

		
//		this.add_QueryStringForSteps("SELECT DISTINCT UPPER(ORT),ORT FROM JT_ADRESSE WHERE" +
//												" ADRESSTYP=1 AND" +
//												" NVL(JT_ADRESSE.AKTIV,'N')='Y' AND " +
//												"  ID_ADRESSE IN (SELECT ID_ADRESSE FROM JT_ADRESSKLASSE " +
//												" WHERE ID_ADRESSKLASSE_DEF = #WERT1#) AND " +
//												" ID_LAND='#WERT2#' ORDER BY UPPER(ORT)", 
//												 new MyE2_String("Ort"),200,1,null);
//
//		this.add_QueryStringForSteps("SELECT ID_ADRESSE, " +
//												"NVL(NAME1,'')||' '||NVL(NAME2,'')||' '||PLZ||'  '||NVL(ORT,'')" +
//												" FROM JT_ADRESSE WHERE " +
//												" ADRESSTYP=1  AND" +
//												" NVL(JT_ADRESSE.AKTIV,'N')='Y' AND " +
//												"  ID_ADRESSE IN (SELECT ID_ADRESSE FROM JT_ADRESSKLASSE WHERE ID_ADRESSKLASSE_DEF = #WERT1#) " +
//												" AND ID_LAND='#WERT2#' AND UPPER(ORT)='#WERT3#' ORDER BY NAME1", new MyE2_String("Firmen"),
//												400,1,null);

		
//		this.add_QueryStringForSteps("SELECT DISTINCT UPPER(ORT),ORT FROM JT_ADRESSE WHERE" +
//												" ADRESSTYP=1 AND" +
//												" NVL(JT_ADRESSE.AKTIV,'N')='Y' AND " +
//												"  ID_ADRESSE IN (SELECT ID_ADRESSE FROM JT_ADRESSKLASSE " +
//												" WHERE ID_ADRESSKLASSE_DEF = #WERT1#) AND " +
//												" ID_LAND='#WERT2#' ORDER BY UPPER(ORT)", 
//												 new MyE2_String("Ort"),200,1,null);
		
		this.add_QueryStringForSteps("SELECT ID_ADRESSE, " +
										"NVL(NAME1,'')||' '||NVL(NAME2,'')||' '||PLZ||'  '||NVL(ORT,'')" +
										" FROM JT_ADRESSE WHERE " +
										" ADRESSTYP=1  AND" +
										" NVL(JT_ADRESSE.AKTIV,'N')='Y' AND (NVL(JT_ADRESSE.WARENEINGANG_SPERREN,'N')='N') AND (NVL(JT_ADRESSE.WARENAUSGANG_SPERREN,'N')='N') AND " +
										"  ID_ADRESSE IN (SELECT ID_ADRESSE FROM JT_ADRESSKLASSE WHERE ID_ADRESSKLASSE_DEF = #WERT1#) " +
										" AND ID_LAND='#WERT2#' ORDER BY NAME1", new MyE2_String("Firmen"),
										400,1,null);

	}

}
