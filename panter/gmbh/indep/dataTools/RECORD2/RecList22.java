/**
 * 
 */
package panter.gmbh.indep.dataTools.RECORD2;

import java.util.Comparator;
import java.util.Iterator;

import panter.gmbh.BasicInterfaces.Check;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class RecList22 extends RecList2<Rec22> {

	protected SqlStringExtended  	sql4queryExt = null; 

	/**
	 * @param p_tab
	 */
	public RecList22(_TAB p_tab) {
		super(p_tab);
	}
	

	
	/**
	 * 
	 * @param complete_sql extended
	 * @return
	 * @throws myException
	 */
	public RecList22 _fill(SqlStringExtended completeSqlExt) throws myException {
		this.execute_query(completeSqlExt);
		return this;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 27.03.2020
	 *
	 * @param sel
	 * @return
	 * @throws myException
	 */
	public RecList22 _fill(SEL sel) throws myException {
		this.execute_query(sel.getSqlExt());
		return this;
	}

	
	
	
	/**
	 * 
	 * @param bedingung (ohne WHERE) 
	 * @param order  (ohne ORDER)
	 * @return
	 * @throws myException
	 */
	public RecList22 _fill(String bedingung,String order) throws myException {
		String c_sql = "SELECT * FROM "+bibE2.cTO()+"."+this.tab.fullTableName();
		if (S.isFull(bedingung)) {
			c_sql = c_sql + " WHERE " +bedingung;
		}
		if (S.isFull(order)) {
			c_sql = c_sql + " ORDER BY " +order;
		}
		
		this.execute_query(c_sql);
		return this;
	}

	
	/**
	 * 
	 * @param complete_sql   ACHTUNG! die query darf nur ergebnissspalten aus der definierten tabelle enthalten
	 * @return
	 * @throws myException
	 */
	public RecList22 _fill(String complete_sql) throws myException {
		this.execute_query(complete_sql);
		return this;
	}

	/**
	 * ein select * from _TAB
	 * @return
	 * @throws myException 
	 */
	public RecList22 _fillWithAll() throws myException {
//		SEL s= new SEL(this.tab).FROM(this.tab);
//		SqlStringExtended sql = new SqlStringExtended(s.s());
//		this._fill(sql);
		this._fillWithAll(null);
		return this;
	}
	

	/**
	 * ein select * from _TAB
	 * @param vOrders  (IF_Fields zum sortieren)
	 * @return
	 * @throws myException 
	 */
	public RecList22 _fillWithAll(VEK<IF_Field> vOrders) throws myException {

		SEL s= new SEL(this.tab).FROM(this.tab);

		if (vOrders!=null && vOrders.size()>0) {
			for (IF_Field f: vOrders) {
				s.ORDERUP(f);
			}
		}
		
		SqlStringExtended sql = new SqlStringExtended(s.s());
		this._fill(sql);
		return this;
	}
	

	
	/**
	 * ein select * from _TAB
	 * @param vOrders  (IF_Fields zum sortieren)
	 * @param validator (if check returns true, then rec22 inkept in list)
	 * @return
	 * @throws myException 
	 */
	public RecList22 _fillWithAll(VEK<IF_Field> vOrders, Check<Rec22> validator) throws myException {

		SEL s= new SEL(this.tab).FROM(this.tab);

		if (vOrders!=null && vOrders.size()>0) {
			for (IF_Field f: vOrders) {
				s.ORDERUP(f);
			}
		}
		
		SqlStringExtended sql = new SqlStringExtended(s.s());
		this._fill(sql);
		
		VEK<String> keysToRemove = new VEK<String>();
		
		for (Rec22 r: this) {
			if (!validator.isOk(r)) {
				keysToRemove._a(r.get_key_value());
			}
		}
		
		for (String key: keysToRemove) {
			this.remove(key);
		}
		
		return this;
	}
	
	
	
	
	/**
	 * ein select * from _TAB
	 * @return
	 * @throws myException 
	 */
	public RecList22 _fillWithAllFromMandant() throws myException {
		return this._fillWithAllFromMandant(null,null);
	}
	

	/**
	 * ein select * from _TAB
	 * @param vOrders  (IF_Fields zum sortieren)
	 * @return
	 * @throws myException 
	 */
	public RecList22 _fillWithAllFromMandant(VEK<IF_Field> vOrders) throws myException {
		return this._fillWithAllFromMandant(vOrders,null);
	}
	

	
	/**
	 * ein select * from _TAB
	 * @param vOrders  (IF_Fields zum sortieren)
	 * @param validator (if check returns true, then rec21 inkept in list)
	 * @return
	 * @throws myException 
	 */
	public RecList22 _fillWithAllFromMandant(VEK<IF_Field> vOrders, Check<Rec21> validator) throws myException {

		IF_Field  mandant = _TAB.find_field(this.tab, "ID_MANDANT");
		SEL s= new SEL(this.tab).FROM(this.tab).WHERE(new vgl(mandant,bibSES.get_ID_MANDANT_UF()));

		if (vOrders!=null && vOrders.size()>0) {
			for (IF_Field f: vOrders) {
				s.ORDERUP(f);
			}
		}
		
		SqlStringExtended sql = new SqlStringExtended(s.s());
		this._fill(sql);
		
		if (validator!=null) {
			VEK<String> keysToRemove = new VEK<String>();
			
			for (Rec21 r: this) {
				if (!validator.isOk(r)) {
					keysToRemove._a(r.get_key_value());
				}
			}
			
			for (String key: keysToRemove) {
				this.remove(key);
			}
		}
		return this;
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public RecList22 _add(Rec22 rec) throws myException {
		super._add(rec);
		return this;
	}

	@Override
	public RecList22 _set_dbtoolbox(MyDBToolBox db) {
		super._set_dbtoolbox(db);
		return this;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.RECORD2.RecList2#generate(panter.gmbh.basics4project.DB_ENUMS._TAB)
	 */
	@Override
	public Rec22 generate(_TAB tab) throws myException {
		return new Rec22(tab);
	}

	
	
	
	protected void execute_query(SqlStringExtended sql) throws myException {

		this.clear();
		
		boolean toolbox_created = false;
		if (this.oDB==null) {
			this.oDB = bibALL.get_myDBToolBox();
			toolbox_created=true;
		}
		this.sql4queryExt = sql;
		
		MyDBResultSet oRS = this.sql4queryExt.generateResultset(this.oDB);
		
		if (oRS.RS != null) {
 
            try  {
                
            	int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();

                if (iAnzahlSpalten > 0) {
                    
                    while (oRS.RS.next()) {
                    	Rec22 r = this.generate(this.tab);
                    	
                        for (int i = 0; i < iAnzahlSpalten; i++) {
                        	MyMetaFieldDEF mdef =   new MyMetaFieldDEF(oRS.RS,i, this.tab.fullTableName());
                        	MyResultValue oResult = new MyResultValue(mdef,oRS.RS,false);
                        	IF_Field f= r.find_field(mdef.get_FieldName());
                        	
                        	if (f==null) {
                               	r.getOverheadFields().put(mdef.get_FieldName().toUpperCase(), oResult);
                        	} else {
                        		r.get(f)._setResult(oResult);
                        	}
                        }
                        r.set_sql4query(r.createOwnQuery());
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
        	DEBUG.System_println(sql4queryExt.getSqlString(),DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println("--------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
            if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
          	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT,"Rec20:__build_Hash: Cannnot open resultset !"+sql);
		}

        oRS.Close();
        if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }

	}
	
	

	@Override
	public Iterator<Rec22> iterator() {
		return this.values().iterator();
	}

	public RecList22 get_filtered_list(IF_RecListFilter filter) throws myException {
		RecList22 rl = new RecList22(this.tab);
		for (Rec22 rec: this) {
			if (filter.isInFilter(rec)) {
				rl._add(rec);
			}
		}
		return rl;
	}
	

	/**
	 * sets all included records to the given prepared-status
	 * @param prepared
	 * @return s itself
	 */
	@Deprecated
	public RecList22 _setPreparedStatusAllMembers(boolean prepared) {
		for (Rec22 r: this) {
			r._setPrepared(prepared);
		}
		return this;
	}
	
	
	
	public RecList22 _addRecWatch(RecWatch rw) {
		for (Rec22 r: this) {
			r._addRecWatch(rw);
		}
		return this;
	}
	
	public RecList22 _addRecWatch(VEK<RecWatch> rws) {
		for (Rec22 r: this) {
			r._addRecWatch(rws);
		}
		return this;
	}
	
	
	
	public RecList22 getSorted(Comparator<Rec22> comp) throws myException {
		VEK<Rec22> v = this.get_sorted_vector(comp);
		
		VEK<String> hasError = new VEK<String>();
		RecList22 rlNeu = new RecList22(this.tab);
		
		
		v.stream().forEach(r->{try {
			rlNeu._add(r);
		} catch (myException e) {
			e.printStackTrace();
			hasError._a( "Error: "+e.getMessage());
		}});
		
		if (hasError.size()>0) {
			throw new myException(hasError.concatenante(" / "));
		}
		
		return rlNeu;
		
	}
	
	
}
