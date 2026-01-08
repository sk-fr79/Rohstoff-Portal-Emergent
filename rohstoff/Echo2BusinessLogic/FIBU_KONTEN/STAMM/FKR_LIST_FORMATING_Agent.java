package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.STAMM;


import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE_UST_ID;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU_KONTENREGEL_LAND;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_UST_ID;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_KONTENREGEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_KONTENREGEL_LAND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;

public class FKR_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

/*
 * subquery-agent, füllt die infofelder wieviele mitarbeiter/Infos usw eine adresse hat
 */
public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException
{

}

public void fillComponents(E2_ComponentMAP oMAP,SQLResultMAP oUsedResultMAP) throws myException
{
	MyE2_Grid qGrid = (MyE2_Grid)oMAP.get__Comp(FKR__CONST.LIST_COMPONENT_QUELLEN_LAND_TABLE);
	MyE2_Grid zGrid = (MyE2_Grid)oMAP.get__Comp(FKR__CONST.LIST_COMPONENT_ZIEL_LAND_TABLE);
	
	String cID_RULE = oUsedResultMAP.get_cUNFormatedROW_ID();
	
	RECORD_FIBU_KONTENREGEL recRegel = new RECORD_FIBU_KONTENREGEL(cID_RULE);
	
	RECLIST_FIBU_KONTENREGEL_LAND relist_Regel_Land = recRegel.get_DOWN_RECORD_LIST_FIBU_KONTENREGEL_LAND_id_fibu_kontenregel();
	
//	relist_Regel_Land.GET_SORTED_VECTOR(new Vector<String>(), true)
	
	for (RECORD_FIBU_KONTENREGEL_LAND recRegelLand : relist_Regel_Land.values()) {
		String ekvk = recRegelLand.get_DEF_QUELLE_ZIEL_cUF();
		//if ()
		
		String land = recRegelLand.get_ID_LAND_cUF();
		
//		String land2 = recRegelLand.get_DOWN
		RECORD_LAND rLand = new RECORD_LAND("ID_LAND = "+land);
		
		//
		if (ekvk.toUpperCase().equals("EK")) {
			qGrid.add(new MyE2_Label(rLand.get_LAENDERCODE_cUF()));
		} else {
			zGrid.add(new MyE2_Label(rLand.get_LAENDERCODE_cUF()));
		}
	}
	
	//RECLIST_LAND land = relist_Regel_Land.get_DO
	
	
	
}
} 