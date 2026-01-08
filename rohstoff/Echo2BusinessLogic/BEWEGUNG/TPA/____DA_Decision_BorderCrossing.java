package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossingInfo;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossingInfoCollector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossingQuery_FROM_Fuhren;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS.DA_Decision_BorderCrossing;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print.___Sammler_ID_VPOS_TPA_FUHRE;


public class ____DA_Decision_BorderCrossing extends DA_Decision_BorderCrossing {

	private ___Sammler_ID_VPOS_TPA_FUHRE 	o_Sammler = null;

	
	public ____DA_Decision_BorderCrossing(	DS_IF_components4decision p_actionComponent,
											___Sammler_ID_VPOS_TPA_FUHRE p_sammler) {
		super(p_actionComponent);
		
		this.o_Sammler = p_sammler;
		this.set_BorderCrossingInfoCollector(new own_BorderCrossingInfoCollector());

	}

	public class own_BorderCrossingInfoCollector extends BorderCrossingInfoCollector {
		
		public ArrayList<BorderCrossingInfo> get_al_BorderCrossingInfo() throws myException {
		
//			ArrayList<BorderCrossingInfo> warenbewegungen = new ArrayList<>();
			 
//			____DA_Decision_BorderCrossing oThis = ____DA_Decision_BorderCrossing.this;
			 
			Vector<String> v_id_fuhren = ____DA_Decision_BorderCrossing.this.o_Sammler.get_vID_VPOS_TPA_FUHRE();

			ArrayList<BorderCrossingInfo> borderCrossingInfos = new BorderCrossingQuery_FROM_Fuhren().get_al_BorderCrossingInfo(v_id_fuhren);
			
//			if (v_id_fuhren.size()>0) {
//			
//				String view_name = "V"+bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+"_FUHREN";
//	
//				String c_query = "SELECT  NVL(ST.ID_LAND,0) AS ID_LAND_START"
//										+ ", NVL(ZI.ID_LAND,0) AS ID_LAND_ZIEL"
//										+ ", V.ID_VPOS_TPA_FUHRE "
//										+ ", TO_CHAR(V.DATUM_ABHOLUNG,'DD.MM.YYYY') "
//										+ ", 'FUHREN'||'@'||TRIM(TO_CHAR(V.ID_VPOS_TPA_FUHRE,'0000000000'))||'@'||TRIM(TO_CHAR(NVL(V.ID_VPOS_TPA_FUHRE_ORT,0),'0000000000'))"
//										+ "||'@'||'BORDERCROSSING'||'@'||TO_CHAR(V.DATUM_ABHOLUNG,'DD.MM.YYYY')"
//										+ "||'LAENDER-FROM'||TRIM(TO_CHAR(ST.ID_LAND,'0000000000'))||'TO'||TRIM(TO_CHAR(ZI.ID_LAND,'0000000000'))"
//										+ ", NVL(ST.NAME1,'')||', '||NVL(ST.ORT,'') AS STARTORT "
//										+ ", NVL(ZI.NAME1,'')||', '||NVL(ZI.ORT,'') AS ZIELORT "
//										+ ", NVL(ARTS.ARTBEZ1,'') AS STARTSORTE "
//										+ ", NVL(ARTZ.ARTBEZ1,'') AS ZIELSORTE "
//							+ " FROM "+view_name+" V "
//							+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE ST ON (V.ID_ADRESSE_LAGER_START=ST.ID_ADRESSE) "
//							+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE ZI ON (V.ID_ADRESSE_LAGER_ZIEL=ZI.ID_ADRESSE) "
//							+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ABS ON (V.ID_ARTIKEL_BEZ_EK=ABS.ID_ARTIKEL_BEZ) "
//							+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ABZ ON (V.ID_ARTIKEL_BEZ_VK=ABZ.ID_ARTIKEL_BEZ) "
//							+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL ARTS ON (ABS.ID_ARTIKEL=ARTS.ID_ARTIKEL) "
//							+ " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL ARTZ ON (ABZ.ID_ARTIKEL=ARTZ.ID_ARTIKEL) "
//							
//							+ " WHERE ID_VPOS_TPA_FUHRE IN ("+bibALL.Concatenate(v_id_fuhren, ",", "0")+")";
//				
//				String[][] arr_id = bibDB.EinzelAbfrageInArray(c_query, "");
//				
//				for (int i=0; i<arr_id.length;i++) {
//					if (S.isFull(arr_id[i][0]) && S.isFull(arr_id[i][1]) ) {
//						warenbewegungen.add(new BorderCrossingInfo(
//																	arr_id[i][2]
//																	,_TAB.vpos_tpa_fuhre 
//																	,arr_id[i][0]
//																	,arr_id[i][1] 
//																	,new MyDate(arr_id[i][3])
//																	,arr_id[i][4]
//																	,arr_id[i][5]
//																	,arr_id[i][6]
//																	,arr_id[i][7]
//																	,arr_id[i][8]));
//					}
//				}
//			}
			
			return borderCrossingInfos;
		}
	}

	
	
}
