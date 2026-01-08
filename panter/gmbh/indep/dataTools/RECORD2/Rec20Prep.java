/**
 * panter.gmbh.indep.dataTools.RECORD2
 * @author manfred
 * @date 23.02.2018
 * 
 */
package panter.gmbh.indep.dataTools.RECORD2;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyDBResultSet_Prepared;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;



/**
 * Rec20 basierend auf PreparedStatements
 * @author manfred
 * @date 23.02.2018
 */
public class Rec20Prep extends Rec20 {

	protected HashMap<IF_Field, Rec20Prep>     	hm_upRecords = new HashMap<>();
	protected HashMap<String, Reclist20Prep>   	hm_downRecLists = new HashMap<>();    //der key besteht aus dem feldnamen (auf Laenge 100 gefuellt) und dem Where-Statement des aufrufs	

	
	protected Vector<ParamDataObject> 			_parameter = new Vector<>();
	
	
	
	
	/**
	 * @author manfred
	 * @date 23.02.2018
	 *
	 * @param p_tab
	 * @throws myException
	 */
	public Rec20Prep(_TAB p_tab) throws myException {
		super(p_tab);
	}

	
	
	/**
	 * @author manfred
	 * @date 23.02.2018
	 *
	 * @param baseRec
	 * @throws myException
	 */
	public Rec20Prep(Rec20 baseRec) throws myException {
		super(baseRec);
		if (baseRec instanceof Rec20Prep){
			this._parameter.clear();
			this._parameter.addAll(((Rec20Prep)baseRec)._parameter);
		} 
		
	}
	
	
	
	/**
	 *  Füllt den Record über die ID (String)
	 */
	public Rec20 _fill_id(String id) throws myException {
		MyLong lid = new MyLong(id);
		
		if (lid.get_bOK()) {
			this._parameter.clear();
			this._parameter.add(new  Param_Long(lid.get_lValue()));
			this.execute_query("SELECT * FROM "+bibE2.cTO()+"."+this.tab.fullTableName()+" WHERE "+this.tab.keyFieldName()+"= ?",_parameter);
		} else {
			throw new myException(this,"Error ID "+id+" is no number !");
		}
		return this;
	}
	
	/**
	 * Füllt den Record über die ID (long)
	 */
	public Rec20 _fill_id(long id) throws myException {
		return _fill_id(Long.toString(id));
	}
	
	
	/**
	 * Mehtode zum füllen des Records über ein Statement mit Parametern.
	 * @author manfred
	 * @date 23.02.2018
	 *
	 * @param sql
	 * @param params
	 * @return
	 * @throws myException
	 */
	public Rec20 _fill_sql(String sql, Vector<ParamDataObject> params) throws myException {
		if (sql.toUpperCase().contains("DELETE") ||sql.toUpperCase().contains("INSERT") || sql.toUpperCase().contains("UPDATE")  ) {
			throw new myException(this,"Error sql "+sql+" is no query-statement!");
		} else {
			this.execute_query(sql, params);
		}
		return this;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.RECORD2.Rec20#execute_query(java.lang.String)
	 */
	@Override
	protected void execute_query(String sql) throws myException {
		throw new myException("Rec20Prep::execute_query(string): prepared-Records müssen eine Parameterliste besitzen");
//		super.execute_query(sql);
	}
	

	
	
	/**
	 * 
	 * @param key_in_daughter = schluessel in der tochter-tabelle
	 * @param where -null allowed
	 * @param order -null allowed
	 * @param force_rebuild    
	 * @return
	 * @throws myException
	 */
	public Reclist20Prep get_down_reclist20(IF_Field key_in_daughter, String where, String order, boolean force_rebuild) throws myException {
		
		String key = key_in_daughter.tnfn()+"|"+S.NN(where);
		
		
		Reclist20Prep rl = this.hm_downRecLists.get(key);
		
		if (rl==null || force_rebuild) {
			rl = new Reclist20Prep(key_in_daughter._t());
			
			//falls es ein recnew ist, der bereits abespeichert wurde, dann finden sich die werte im @rec_after_save_new
			Vector<ParamDataObject> vParam = new Vector<>();
			
			if (S.isFull(this.get_key_value())) {
				vParam.addElement(new Param_Long(Long.parseLong(this.get_key_value())));
				rl._fill(key_in_daughter.tnfn() + " = ? " + ( S.isFull(where) ? " AND " + where : "" ) , order, vParam);
				this.hm_downRecLists.put(key, rl);
			} else {
				if (this.rec_after_save_new!=null && S.isFull(this.rec_after_save_new.get_key_value())) {
					vParam.addElement(new Param_Long(Long.parseLong(this.get_key_value())));
					rl._fill(key_in_daughter.tnfn()+" = ? "+(S.isFull(where)?" AND "+where:""), order, vParam);
					this.hm_downRecLists.put(key, rl);
				}	
			}
		}
		
		return rl;
	}
	
	
	
	
	
	/**
	 * Führt das Statement aus als Prepared-Statement 
	 * @author manfred
	 * @date 23.02.2018
	 *
	 * @param sql
	 * @param vParam
	 * @throws myException
	 */
	protected void execute_query(String sql, Vector<ParamDataObject> vParam) throws myException {

		//zuerst die alten resultvalues rausschmeissen
		for (IF_Field f: this.keySet()) {
			this.get(f)._setResult(null);
		}
		
		
		boolean toolbox_created = false;
		if (this.oDB==null) {
			this.oDB = bibALL.get_myDBToolBox();
			toolbox_created=true;
		}
		
		this.sql4query = sql;
		MyDBResultSet_Prepared oRS = this.oDB.OpenResultSet_Prepared(sql, vParam);
		
		if (oRS.RS != null) {
 
            try  {
                
            	int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();

                int iCount = 0;
                if (iAnzahlSpalten > 0) {
                    
                    while (oRS.RS.next()) {
                        iCount++;
                        if (iCount>1) {
                        	oRS.Close();
                            if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
                           	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_UNIQUE,"Rec20:__build_Hash: More than on result-rows cannot be !!"+sql);
                        }
                        for (int i = 0; i < iAnzahlSpalten; i++) {
                        	MyMetaFieldDEF mdef = new MyMetaFieldDEF(oRS.RS,i, null);
                        	MyResultValue oResult = new MyResultValue(mdef,oRS.RS,false);
                        	
                        	IF_Field f= this.find_field(mdef.get_FieldName());

                        	if (f==null) {
                               	this.hmOverheadFields.put(mdef.get_FieldName().toUpperCase(), oResult);
                        	} else {
                        		this.get(f)._setResult(oResult);
                        	}
                        }
                    }

                }
                
                if (iCount==0) {
                	oRS.Close();
                    if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
                    
                    this.sql4query=null;   // es bleibt ein record im status new uebrig
                    
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

	
	/**
	 * gibt das sql für das eigene Objekt zurüc
	 * muss noch mit getParameterList() vervollständigt werden
	 */
	public String createOwnQuery() throws myException {
		this._parameter.clear();
		this._parameter.addElement(new Param_Long(Long.parseLong(this.get_key_value() ) ) );
		this.sql4query = "SELECT * FROM " + bibE2.cTO() + "." + this.tab.fullTableName() + " WHERE " + this.tab.keyFieldName() + " = ? ";
		return this.sql4query;
	}


	/**
	 * setzt das sql für die query des Objekts
	 */
	public void set_sql4query(String p_sql4query) {
		this.sql4query = p_sql4query;
	}
	
	
	/**
	 * gibt die parameterliste des Objekts zurück
	 * @author manfred
	 * @date 23.02.2018
	 *
	 * @return
	 */
	public Vector<ParamDataObject> getParameterList(){
		return getParameterList();
	}
	
	/**
	 * setzt die Parameterliste des Objekts
	 * @author manfred
	 * @date 23.02.2018
	 *
	 * @param paramList
	 * @return
	 */
	public Rec20Prep setParameterList(Vector<ParamDataObject> paramList){
		this._parameter.clear();
		this._parameter.addAll(paramList);
		return this;
	}
	
	
	
}
