 
package panter.gmbh.Echo2.RB.COMP.TextListe;
  
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSQL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
  
public class TL_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
	
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public TL_LIST_SqlFieldMAP(RB_TransportHashMap  p_tpHashMap) throws myException   {
    	
        super(_TAB.text_liste.n(), "", false);
        
        
        this.m_tpHashMap = p_tpHashMap;        
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);
        
		String tableName = ((EnumTableTranslator)m_tpHashMap.getSB(TL_CONST.TabTrans)).getTableNameForTextListe();

        
        //hier wird als zusatz restrictionfield der tablename eingehaengt
        IF_Field restrictFieldName = TEXT_LISTE.tablename;
		SQLFieldForRestrictTableRange sfr = new SQLFieldForRestrictTableRange(	restrictFieldName.tn()
																				, restrictFieldName.fn()
																				, restrictFieldName.fn()
																				, S.ms(restrictFieldName.fn())
																				, bibSQL.includeInTicks(tableName)
																				, bibE2.get_CurrSession());
		this.add_SQLField(sfr,true);
		
        this.initFields();
    }
    
}
 
 
