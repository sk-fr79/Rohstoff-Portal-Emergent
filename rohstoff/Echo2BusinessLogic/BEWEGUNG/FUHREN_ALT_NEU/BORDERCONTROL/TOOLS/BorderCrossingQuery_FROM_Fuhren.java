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
public class BorderCrossingQuery_FROM_Fuhren {

	public BorderCrossingQuery_FROM_Fuhren() {
		super();
	}

	public ArrayList<BorderCrossingInfo> get_al_BorderCrossingInfo(Vector<String> v_id_fuhren) throws myException {
		
		ArrayList<BorderCrossingInfo> al_borderCrossingInfos = new ArrayList<>();

		if (v_id_fuhren == null) {
			throw new myException(this,"Error: v_id_fuhren-parameter NULL is not allowed !!");
		}
		
		
		if (v_id_fuhren.size()>0) {
			
			String view_name = "V"+bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+"_FUHREN";

			String c_query = "SELECT  NVL(ST.ID_LAND,0) AS ID_LAND_START"
									+ ", NVL(ZI.ID_LAND,0) AS ID_LAND_ZIEL"
									+ ", V.ID_VPOS_TPA_FUHRE "
									+ ", TO_CHAR(V.DATUM_ABHOLUNG,'DD.MM.YYYY') "
									+ ", 'FUHREN'||'@'||TRIM(TO_CHAR(V.ID_VPOS_TPA_FUHRE,'0000000000'))||'@'||TRIM(TO_CHAR(NVL(V.ID_VPOS_TPA_FUHRE_ORT,0),'0000000000'))"
									+ "||'@'||'BORDERCROSSING'||'@'||TO_CHAR(V.DATUM_ABHOLUNG,'DD.MM.YYYY')"
									+ "||'LAENDER-FROM'||TRIM(TO_CHAR(ST.ID_LAND,'0000000000'))||'TO'||TRIM(TO_CHAR(ZI.ID_LAND,'0000000000'))"
									+ ", NVL(ST.NAME1,'')||', '||NVL(ST.ORT,'') AS STARTORT "
									+ ", NVL(ZI.NAME1,'')||', '||NVL(ZI.ORT,'') AS ZIELORT "
									+ ", NVL2(ARTS.ANR1,ARTS.ANR1 || ' - ', '') || NVL(ARTS.ARTBEZ1,'') AS STARTSORTE "
									+ ", NVL2(ARTZ.ANR1,ARTS.ANR1 || ' - ', '') || NVL(ARTZ.ARTBEZ1,'') AS ZIELSORTE "
									+ ", NVL(ARTS.ID_ARTIKEL,'')  AS ID_STARTSORTE "
									+ ", NVL(ARTZ.ID_ARTIKEL,'')  AS ID_ZIELSORTE "
									
						+ " FROM "+view_name+" V "
						+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE ST ON (V.ID_ADRESSE_LAGER_START=ST.ID_ADRESSE) "
						+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE ZI ON (V.ID_ADRESSE_LAGER_ZIEL=ZI.ID_ADRESSE) "
						+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ABS ON (V.ID_ARTIKEL_BEZ_EK=ABS.ID_ARTIKEL_BEZ) "
						+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ABZ ON (V.ID_ARTIKEL_BEZ_VK=ABZ.ID_ARTIKEL_BEZ) "
						+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL ARTS ON (ABS.ID_ARTIKEL=ARTS.ID_ARTIKEL) "
						+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL ARTZ ON (ABZ.ID_ARTIKEL=ARTZ.ID_ARTIKEL) "
						
						+ " WHERE ID_VPOS_TPA_FUHRE IN ("+bibALL.Concatenate(v_id_fuhren, ",", "0")+")";
			
			String[][] arr_id = bibDB.EinzelAbfrageInArray(c_query, "");
			
			if (arr_id==null || arr_id.length==0) {
				throw new myException(this,"Error: SQL-Query: "+c_query);
			}
			
			for (int i=0; i<arr_id.length;i++) {
				if (S.isFull(arr_id[i][0]) && S.isFull(arr_id[i][1]) ) {
					al_borderCrossingInfos.add(new BorderCrossingInfo(
																arr_id[i][2]
																,_TAB.vpos_tpa_fuhre 
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
