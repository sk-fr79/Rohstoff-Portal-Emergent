package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/**
 * @author martin
 * 
 *  
 * Anpassung 2018-06-21 Manfred: zusätzliche Sorten-IDs für die erweiterte Kontrolle 
 *
 */
public class BorderCrossingQuery_FROM_Wiegekarte {

	public BorderCrossingQuery_FROM_Wiegekarte() {
		super();
	}

	public ArrayList<BorderCrossingInfo> get_al_BorderCrossingInfo(Vector<String> v_id_wiegekarte) throws myException {
		
		ArrayList<BorderCrossingInfo> al_borderCrossingInfos = new ArrayList<>();

		if (v_id_wiegekarte == null) {
			throw new myException(this,"Error: v_id_fuhren-parameter NULL is not allowed !!");
		}
		
		
		if (v_id_wiegekarte.size()>0) {

			String c_query = "SELECT "
									+ " case when nvl(V.IST_LIEFERANT,'N') = 'Y' then NVL(LIEFERANT.ID_LAND,0) else NVL(LAGER.ID_LAND,0) END AS ID_LAND_START , "
									+ " case when nvl(V.IST_LIEFERANT,'N') = 'Y' then NVL(LAGER.ID_LAND,0) else NVL(LIEFERANT.ID_LAND,0) END AS ID_LAND_ZIEL , "
									+ " V.ID_WIEGEKARTE , "
									+ " TO_CHAR(V.ERZEUGT_AM,'DD.MM.YYYY') ,"
									+ " 'WIEGEKARTE'||'@'||TRIM(TO_CHAR(V.ID_WIEGEKARTE,'0000000000'))||'@'||'BORDERCROSSING'||'@'||TO_CHAR(V.ERZEUGT_AM,'DD.MM.YYYY') "
									+ " 		||'LAGER'||TRIM(TO_CHAR(LAGER.ID_LAND,'0000000000'))||'KUNDE'||TRIM(TO_CHAR(LIEFERANT.ID_LAND,'0000000000')) || nvl(V.IST_LIEFERANT,'N')  , "
									+ " case when nvl(V.IST_LIEFERANT,'N') = 'Y' then NVL(LIEFERANT.NAME1,'')||', '||NVL(LIEFERANT.ORT,'')  ELSE NVL(LAGER.NAME1,'')||', '||NVL(LAGER.ORT,'') END AS STARTORT , "
									+ " case when nvl(V.IST_LIEFERANT,'N') = 'Y' then NVL(LAGER.NAME1,'')||', '||NVL(LAGER.ORT,'') ELSE NVL(LIEFERANT.NAME1,'')||', '||NVL(LIEFERANT.ORT,'') END AS ZIELORT , "
									+ " NVL(ARTS.ARTBEZ1,'')                   AS STARTSORTE , "
									+ " NVL(ARTS.ARTBEZ1,'')                   AS ZIELSORTE ,"
									+ " NVL(ARTS.ID_ARTIKEL,'')                   AS ID_STARTSORTE ,"
									+ " NVL(ARTS.ID_ARTIKEL,'')                   AS ID_ZIELSORTE "
									
						+ " FROM   "+bibE2.cTO()+".JT_WIEGEKARTE  V"
						+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE LAGER ON  ( V.ID_ADRESSE_LAGER = LAGER.ID_ADRESSE )"
						+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE LIEFERANT  ON  ( V.ID_ADRESSE_LIEFERANT=LIEFERANT.ID_ADRESSE)"
						+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ABS ON  ( V.ID_ARTIKEL_BEZ=ABS.ID_ARTIKEL_BEZ)" 
						+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL ARTS ON  ( ABS.ID_ARTIKEL=ARTS.ID_ARTIKEL)"
						
						+ " WHERE ID_WIEGEKARTE IN ("+bibALL.Concatenate(v_id_wiegekarte, ",", "0")+")";
			
			String[][] arr_id = bibDB.EinzelAbfrageInArray(c_query, "");
			
			if (arr_id==null || arr_id.length==0) {
				throw new myException(this,"Error: SQL-Query: "+c_query);
			}

			for (int i=0; i<arr_id.length;i++) {
				if (S.isFull(arr_id[i][0]) && S.isFull(arr_id[i][1]) ) {
					al_borderCrossingInfos.add(new BorderCrossingInfo(
																arr_id[i][2]
																,_TAB.wiegekarte 
																,arr_id[i][0]
																,arr_id[i][1] 
																,new MyDate(arr_id[i][3])
																,arr_id[i][4]
																,arr_id[i][5]
																,arr_id[i][6]
																,arr_id[i][7]
																,arr_id[i][8]
																,arr_id[i][9]
																,arr_id[i][10]));
				}
			}
		}
		
		return al_borderCrossingInfos;
	}
	
	
}
