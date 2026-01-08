 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
  
import org.hsqldb.User;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
  
public class WF_SIMPLE_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
	
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public WF_SIMPLE_LIST_SqlFieldMAP(RB_TransportHashMap  p_tpHashMap) throws myException   {
    	
        super(_TAB.laufzettel_eintrag.n(), "", false);
        
        
		// INNER JOIN -> JT_BEWEGUNG_ATOM (Einseitig, bringt immer beides Seiten des Atoms) 
		this.add_JOIN_Table(LAUFZETTEL.fullTabName(), 
				LAUFZETTEL.fullTabName(), 
				SQLFieldMAP.INNER, 
				S.Concatenate(":","", LAUFZETTEL.text.fieldName(), LAUFZETTEL.geloescht.fieldName() ),
				true, 
				LAUFZETTEL.id_laufzettel.tnfn() + "=" + LAUFZETTEL_EINTRAG.id_laufzettel.tnfn(), 
				"LZ_", 
				null);

        
		// left outer join-> user 
		this.add_JOIN_Table(USER.fullTabName(), 
				WF_SIMPLE_CONST.alias_bearbeiter, 
				SQLFieldMAP.LEFT_OUTER, 
				S.Concatenate(":","", USER.name.fieldName(),USER.name1.fieldName(),USER.vorname.fieldName(),USER.kuerzel.fieldName() ),
				true, 
				USER.id_user.fn(WF_SIMPLE_CONST.alias_bearbeiter) + "=" + LAUFZETTEL_EINTRAG.id_user_bearbeiter.tnfn(), 
				WF_SIMPLE_CONST.prefix_bearbeiter, 
				null);

		// left outer join-> user 
		this.add_JOIN_Table(USER.fullTabName(), 
				WF_SIMPLE_CONST.alias_besitzer, 
				SQLFieldMAP.LEFT_OUTER, 
				S.Concatenate(":","", USER.name.fieldName(),USER.name1.fieldName(),USER.vorname.fieldName(),USER.kuerzel.fieldName() ),
				true, 
				USER.id_user.fn(WF_SIMPLE_CONST.alias_besitzer) + "=" + LAUFZETTEL_EINTRAG.id_user_besitzer.tnfn(), 
				WF_SIMPLE_CONST.prefix_besitzer, 
				null);

		// left outer join-> user 
		this.add_JOIN_Table(USER.fullTabName(), 
				WF_SIMPLE_CONST.alias_abgeschlossen_von, 
				SQLFieldMAP.LEFT_OUTER, 
				S.Concatenate(":","", USER.name.fieldName(),USER.name1.fieldName(),USER.vorname.fieldName(),USER.kuerzel.fieldName() ),
				true, 
				USER.id_user.fn(WF_SIMPLE_CONST.alias_abgeschlossen_von) + "=" + LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von.tnfn(), 
				WF_SIMPLE_CONST.prefix_abgeschlossen_von, 
				null);
        
		this.add_JOIN_Table(LAUFZETTEL_STATUS.fullTabName(), 
				WF_SIMPLE_CONST.alias_status, 
				SQLFieldMAP.LEFT_OUTER, 
				S.Concatenate(":","", LAUFZETTEL_STATUS.status.fieldName() ),
				true, 
				LAUFZETTEL_STATUS.id_laufzettel_status.fn(WF_SIMPLE_CONST.alias_status) + "=" + LAUFZETTEL_EINTRAG.id_laufzettel_status.tnfn(), 
				WF_SIMPLE_CONST.prefix_status, 
				null);
        
		
		
		
		this.add_SQLField(new SQLField(
				USER.name.t(WF_SIMPLE_CONST.alias_bearbeiter).s() 
				+ "  || ' (' || " + USER.vorname.t(WF_SIMPLE_CONST.alias_bearbeiter).s() 
				+ "  || ' ' || " + USER.name1.t(WF_SIMPLE_CONST.alias_bearbeiter).s() 
				+ "  || ')' ",
				WF_SIMPLE_CONST.colname_bearbeiter_name,
				new MyString("Bearbeiter"),
				bibE2.get_CurrSession()), true);


		this.add_SQLField(new SQLField(
				USER.name.t(WF_SIMPLE_CONST.alias_besitzer).s() 
				+ "  || ' (' || " + USER.vorname.t(WF_SIMPLE_CONST.alias_besitzer).s() 
				+ "  || ' ' || " + USER.name1.t(WF_SIMPLE_CONST.alias_besitzer).s() 
				+ "  || ')' ",
				WF_SIMPLE_CONST.colname_besitzer_name,
				new MyString("Besitzer"),
				bibE2.get_CurrSession()), true);
		
		this.add_SQLField(new SQLField(
				USER.name.t(WF_SIMPLE_CONST.alias_abgeschlossen_von).s() 
				+ "  || ' (' || " + USER.vorname.t(WF_SIMPLE_CONST.alias_abgeschlossen_von).s() 
				+ "  || ' ' || " + USER.name1.t(WF_SIMPLE_CONST.alias_abgeschlossen_von).s() 
				+ "  || ')' ",
				WF_SIMPLE_CONST.colname_abgeschlossen_von_name,
				new MyString("Abgeschlossen von"),
				bibE2.get_CurrSession()), true);
		
        
        this.m_tpHashMap = p_tpHashMap;        
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);

        this.initFields();
    }
    
}
 
 
