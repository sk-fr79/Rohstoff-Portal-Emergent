package rohstoff.Echo2BusinessLogic._4_ALL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.E2_Selection_Row_With_Multi_Cols;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;

public class FirmenSelectorBranche extends E2_Selection_Row_With_Multi_Cols
{
	public FirmenSelectorBranche() throws myException
	{
		super(MyE2_Row.STYLE_THIN_BORDER(),200,true);
		this.add_QueryStringForSteps("SELECT DISTINCT NVL(JT_FIRMENINFO.ID_BRANCHE,0),NVL(JT_BRANCHE.KURZBEZEICHNUNG,'-') " +
									"  FROM JT_FIRMENINFO,JT_BRANCHE,JT_ADRESSE WHERE " +
									" JT_FIRMENINFO.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE AND " +
									" NVL(JT_ADRESSE.AKTIV,'N')='Y' AND " +
									" JT_FIRMENINFO.ID_BRANCHE=JT_BRANCHE.ID_BRANCHE (+) " +
									" ORDER BY NVL(JT_BRANCHE.KURZBEZEICHNUNG,'-')" , new MyE2_String("Branche"),200,1,null);

		this.add_QueryStringForSteps("SELECT ID_LAND,LAENDERNAME FROM JD_LAND WHERE ID_LAND IN (SELECT ID_LAND FROM JT_ADRESSE WHERE " +
												"   ADRESSTYP=1 AND" +
												" 	NVL(JT_ADRESSE.AKTIV,'N')='Y' AND " +
												"   ID_ADRESSE IN (" +
												" SELECT ID_ADRESSE FROM JT_FIRMENINFO WHERE " +
												" NVL(JT_FIRMENINFO.ID_BRANCHE,0) = #WERT1#)) " +
												" ORDER BY LAENDERNAME",new MyE2_String("Land"),200,1,null);

		this.add_QueryStringForSteps("SELECT ID_ADRESSE, " +
									"NVL(NAME1,'')||' '||NVL(NAME2,'')||' '||PLZ||'  '||NVL(ORT,'')" +
									" FROM JT_ADRESSE WHERE " +
									" ADRESSTYP=1  AND" +
									" NVL(JT_ADRESSE.AKTIV,'N')='Y' AND " +
									"  ID_ADRESSE IN (SELECT ID_ADRESSE FROM JT_FIRMENINFO WHERE NVL(JT_FIRMENINFO.ID_BRANCHE,0) = #WERT1#)" +
									" AND ID_LAND='#WERT2#' ORDER BY NAME1", new MyE2_String("Firmen"), 400,1,null);

	}

}
