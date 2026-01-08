/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS
 * @author manfred
 * @date 21.06.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS;

import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING_ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.Reclist21_AH7P_GlobalProfiles;

/**
 * @author manfred
 * @date 21.06.2018
 *
 */
public class BorderCrossing_RecordList {
	
	// felder der Elemente
	public static enum Fields {
		ID_BORDERCROSSING (),
		ERINNERUNG_BEI_ANLAGE (),
		ID_LAND_QUELLE (),
		ID_LAND_ZIEL (),
		INTERVALL_TAGE (),
		MESSAGE (),
		OFFSET_BEFORE_START (),
		TITLE (),
		ID_BORDERCROSSING_ARTIKEL (),
		ID_ARTIKEL (),
		MENGE ()
		;
		
		private Fields(){
			
		}
		
		public String Name() {
            return this.name();
        }
	}
	
	
	// der Vektor mit den Records
	private Vector<BorderCrossing_Record>  _list = null;
	
	
	
	/**
	 * Füllen der Liste mit allen BorderCrossing Einträgen
	 * @author manfred
	 * @throws myException 
	 * @date 21.06.2018
	 *
	 */
	public BorderCrossing_RecordList() throws myException {

		_list = new Vector<>();
		String _sel = new SEL()	.ADDFIELD(
									 BORDERCROSSING.id_bordercrossing
									,BORDERCROSSING.id_land_quelle
									,BORDERCROSSING.id_land_ziel
									,BORDERCROSSING.title
									,BORDERCROSSING.message
									,BORDERCROSSING.erinnerung_bei_anlage
									,BORDERCROSSING.intervall_tage
									,BORDERCROSSING.offset_before_start
									,BORDERCROSSING_ARTIKEL.id_bordercrossing_artikel
									,BORDERCROSSING_ARTIKEL.id_artikel
									,BORDERCROSSING_ARTIKEL.menge)
							.FROM(_TAB.bordercrossing)
							.LEFTOUTER(_TAB.bordercrossing_artikel, BORDERCROSSING.id_bordercrossing, BORDERCROSSING_ARTIKEL.id_bordercrossing)
							.ORDERUP(BORDERCROSSING.id_bordercrossing).ORDERUP(BORDERCROSSING_ARTIKEL.id_bordercrossing_artikel)
							.s();
		
		SqlStringExtended s = new SqlStringExtended(_sel);
		
		MyDBToolBox m_MyDBToolbox = bibALL.get_myDBToolBox();

		
		String [][] recs = m_MyDBToolbox.EinzelAbfrageInArray(s);
		for(int i=0; i<recs.length; i++){
			int col = 0;
			BorderCrossing_Record r = new BorderCrossing_Record()
					.setValue(Fields.ID_BORDERCROSSING, recs[i][col++])
					.setValue(Fields.ID_LAND_QUELLE, recs[i][col++])
					.setValue(Fields.ID_LAND_ZIEL, recs[i][col++])
					.setValue(Fields.TITLE, recs[i][col++])
					.setValue(Fields.MESSAGE, recs[i][col++])
					.setValue(Fields.ERINNERUNG_BEI_ANLAGE, recs[i][col++])
					.setValue(Fields.INTERVALL_TAGE, recs[i][col++])
					.setValue(Fields.OFFSET_BEFORE_START, recs[i][col++])
					.setValue(Fields.ID_BORDERCROSSING_ARTIKEL, recs[i][col++])
					.setValue(Fields.ID_ARTIKEL, recs[i][col++])
					.setValue(Fields.MENGE, recs[i][col++])
					;
			
			_list.add(r);
			
		}
	
	}
	
	
	
	/**
	 * gibt die Liste der Bordercrossing-Records zurück
	 * @author manfred
	 * @date 21.06.2018
	 *
	 * @return
	 */
	public Vector<BorderCrossing_Record> get_recList(){
		return this._list;
	}
	

}
