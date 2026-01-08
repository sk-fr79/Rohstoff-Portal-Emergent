/**
 * panter.gmbh.indep.dataTools.RECORD2
 * @author manfred
 * @date 23.02.2018
 * 
 */
package panter.gmbh.indep.dataTools.RECORD2;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBResultSet_Prepared;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 23.02.2018
 *
 */
public class Reclist20Prep extends RecList20 {

	protected Vector<ParamDataObject> _parameter = new Vector<>();
	
	
	/**
	 * @author manfred
	 * @date 23.02.2018
	 *
	 * @param p_tab
	 */
	public Reclist20Prep(_TAB p_tab) {
		super(p_tab);
		
	}

	
	
	/**
	 * 
	 * @param bedingung (ohne WHERE) 
	 * @param order  (ohne ORDER)
	 * @return
	 * @throws myException
	 */
	public RecList20 _fill(String bedingung,String order) throws myException {
		throw new myException("Reclist20Prep::_fill(string,string): prepared-Records müssen eine Parameterliste besitzen");	
	}

	
	
	
	/**
	 * 
	 * @param complete_sql   ACHTUNG! die query darf nur ergebnissspalten aus der definierten tabelle enthalten
	 * @return
	 * @throws myException
	 */
	public RecList20 _fill(String complete_sql) throws myException {
		throw new myException("Reclist20Prep::_fill(string): prepared-Records müssen eine Parameterliste besitzen");	
	}

	
	
	/**
	 * 
	 * @param bedingung (ohne WHERE) 
	 * @param order  (ohne ORDER)
	 * @return
	 * @throws myException
	 */
	public RecList20 _fill(String bedingung,String order,Vector<ParamDataObject> param) throws myException {
		this._parameter.clear();
		this._parameter.addAll(param);
		
		
		String c_sql = "SELECT * FROM "+bibE2.cTO()+"."+this.tab.fullTableName();
		if (S.isFull(bedingung)) {
			c_sql = c_sql + " WHERE " +bedingung;
		}
		if (S.isFull(order)) {
			c_sql = c_sql + " ORDER BY " +order;
		}
		
		this.execute_query(c_sql,param);
		return this;
	}

	
	
	
	/**
	 * 
	 * @param complete_sql   ACHTUNG! die query darf nur ergebnissspalten aus der definierten tabelle enthalten
	 * @return
	 * @throws myException
	 */
	public RecList20 _fill(String complete_sql,Vector<ParamDataObject> param) throws myException {
		this.execute_query(complete_sql , param);
		return this;
	}

	
	
	
	
	private void execute_query(String sql,Vector<ParamDataObject> param) throws myException {

		this.clear();
		
		boolean toolbox_created = false;
		if (this.oDB==null) {
			this.oDB = bibALL.get_myDBToolBox();
			toolbox_created=true;
		}
		this.sql4query = sql;
		
		MyDBResultSet_Prepared oRS = this.oDB.OpenResultSet_Prepared(sql, param);
		
		if (oRS.RS != null) {
 
            try  {
                
            	int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();

                if (iAnzahlSpalten > 0) {
                    
                    while (oRS.RS.next()) {
                    	Rec20Prep r = new Rec20Prep(this.tab);
                    	
                        for (int i = 0; i < iAnzahlSpalten; i++) {
                        	MyMetaFieldDEF mdef =   new MyMetaFieldDEF(oRS.RS,i, this.tab.fullTableName());
                        	MyResultValue oResult = new MyResultValue(mdef,oRS.RS,false);
                        	IF_Field f= r.find_field(mdef.get_FieldName());
                        	
                        	
                        	if (f==null) {
                               	System.out.println("Field "+mdef.get_FieldName()+" is not in the regular list of table "+this.tab.fullTableName()+": "+sql);
                               	r.getOverheadFields().put(mdef.get_FieldName().toUpperCase(), oResult);
                        	} else {
                        		r.get(f)._setResult(oResult);
                        	}
                        }
                        
//                        r.set_sql4query(r.createOwnQuery());
                        r.createOwnQuery();
                        
                        this._add(r);
                    }

                }
                
            }
            catch (myException ex)
            {
            	oRS.Close();
                if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
            	throw ex;
            }
            catch (Exception e)
            {
            	oRS.Close();
                if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
            	e.printStackTrace();
            	throw new myException(e.getLocalizedMessage());
            }
 		}
		else
		{
        	DEBUG.System_println("--------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println("Rec20: Error SQL  ---------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println(sql,DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println("--------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
            if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
          	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT,"Rec20:__build_Hash: Cannnot open resultset !"+sql);
		}

        oRS.Close();
        if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }

	}

	
}
