/**
 * panter.gmbh.Echo2.ListAndMask
 * @author martin
 * @date 04.12.2018
 * 
 */
package panter.gmbh.Echo2.ListAndMask;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 04.12.2018
 * interface, das es erlaubt, fuer jede zeile und jede beteiligte tabelle ein rec21 zu hinterlegen,
 * in einer HashMap<String,Rec21> um in jeder zeile jede rec21 nur einmal aufrufen zu muessen
 */
public interface E2_ComponentMAP_IF_Rec21 {
	
	
/// !!! wichtig! die copy-methode von E2_ComponentMap  muss ueberschrieben werden
//		public  FSZ_LIST_ComponentMAP get_Copy(Object objHelp) throws myExceptionCopy 	{
//			FSZ_LIST_ComponentMAP oRueck = new FSZ_LIST_ComponentMAP();
//			oRueck.set_oSQLFieldMAP(this.get_oSQLFieldMAP());
//			E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
//		return oRueck;
//	

	
	/**
	 * 
	 * @author martin
	 * @date 07.01.2019
	 *
	 * @param tab
	 * @return s row-id from leading table
	 * @throws myException
	 */
	public default Rec21 getRec21(_TAB tab) throws myException {
		E2_ComponentMAP map = (E2_ComponentMAP)this;
		
		if (map.getHmRec21().get(tab.fullTableName())==null) {
			Rec21 r = new Rec21(tab)._fill_id(map.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			map.getHmRec21().put(tab.fullTableName(), r);
		}
		
		return map.getHmRec21().get(tab.fullTableName());
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 07.01.2019
	 * 
	 * @param tab
	 * @param fieldAliasInSqlFieldMap
	 * @return s Rec21 from tab where id = resultMap.get_UnFormatedValue(fieldAliasInSqlFieldMap) or null
	 */
	public default Rec21 getRec21(_TAB tab, String fieldAliasInSqlFieldMap) {
		
		E2_ComponentMAP map = (E2_ComponentMAP)this;
		
		if (map.getHmRec21().get(tab.fullTableName()+"@"+fieldAliasInSqlFieldMap)==null) {
			try {
				//falls der wert vorhanden ist, dann muss es eine zahl sein
				if (S.isFull(map.get_oInternalSQLResultMAP().get_UnFormatedValue(fieldAliasInSqlFieldMap))) {
					Rec21 r = new Rec21(tab)._fill_id(map.get_oInternalSQLResultMAP().get_UnFormatedValue(fieldAliasInSqlFieldMap));
					map.getHmRec21().put(tab.fullTableName()+"@"+fieldAliasInSqlFieldMap, r);
				}
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		
		return map.getHmRec21().get(tab.fullTableName()+"@"+fieldAliasInSqlFieldMap);

	}
	
	
	
	/**
	 * falls es nur eine 
	 * @author martin
	 * @date 04.12.2018
	 *
	 * @return
	 * @throws myException
	 */
	public default Rec21 getRec21() throws myException {
		E2_ComponentMAP map = (E2_ComponentMAP)this;
		
		String tableName = map.get_oSQLFieldMAP().get_cMAIN_TABLE();
		
		_TAB tab = _TAB.find_Table(tableName);
		
		if (tab==null) {
			throw new myException("System-error: Cannot find table to tablename: <"+S.NN(tableName)+">");
		}
		return this.getRec21(tab);
	}
	
	
   /// !!! wichtig! die copy-methode von E2_ComponentMap  muss ueberschrieben werden
//	public  FSZ_LIST_ComponentMAP get_Copy(Object objHelp) throws myExceptionCopy 	{
//		FSZ_LIST_ComponentMAP oRueck = new FSZ_LIST_ComponentMAP();
//		oRueck.set_oSQLFieldMAP(this.get_oSQLFieldMAP());
//		E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
//	return oRueck;
//	

	
}
