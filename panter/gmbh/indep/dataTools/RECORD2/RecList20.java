package panter.gmbh.indep.dataTools.RECORD2;


import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Iterator;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class RecList20 extends RecList2<Rec20> {

	protected _TAB  			tab = null;            //_TAB muss immer vorhanden sein
	protected String  		sql4query = null; 
	protected MyDBToolBox 	oDB = null;

	
	
	
	/**
	 * @param p_tab
	 */
	public RecList20(_TAB p_tab) {
		super(p_tab);
		this.tab = p_tab;
	}
	
	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.RECORD2.RecList2#generate(panter.gmbh.basics4project.DB_ENUMS._TAB)
	 */
	@Override
	public Rec20 generate(_TAB tab) throws myException {
		return new Rec20(tab);
	}

	
	/**
	 * 
	 * @param bedingung (ohne WHERE) 
	 * @param order  (ohne ORDER)
	 * @return
	 * @throws myException
	 */
	public RecList20 _fill(String bedingung,String order) throws myException {
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
	public RecList20 _fill(String complete_sql) throws myException {
		this.execute_query(complete_sql);
		return this;
	}

	
	@Override
	public RecList20 _add(Rec20 rec) throws myException {
		super._add(rec);
		return this;
	}

	@Override
	public RecList20 _set_dbtoolbox(MyDBToolBox db) {
		super._set_dbtoolbox(db);
		return this;
	}
	
	
	/**
	 * ein select * from _TAB
	 * @return
	 * @throws myException 
	 */
	public RecList20 _fillWithAll() throws myException {
		SEL s= new SEL(this.tab).FROM(this.tab);
		this._fill(s.s());
		return this;
	}
	
	
//	/**
//	 * 
//	 * @param bedingung (ohne WHERE) 
//	 * @param order  (ohne ORDER)
//	 * @return
//	 * @throws myException
//	 */
//	public RecList20 _fill(String bedingung,String order) throws myException {
//		String c_sql = "SELECT * FROM "+bibE2.cTO()+"."+this.tab.fullTableName();
//		if (S.isFull(bedingung)) {
//			c_sql = c_sql + " WHERE " +bedingung;
//		}
//		if (S.isFull(order)) {
//			c_sql = c_sql + " ORDER BY " +order;
//		}
//		
//		this.execute_query(c_sql);
//		return this;
//	}

	
	
//	
//	/**
//	 * 
//	 * @param complete_sql   ACHTUNG! die query darf nur ergebnissspalten aus der definierten tabelle enthalten
//	 * @return
//	 * @throws myException
//	 */
//	public RecList20 _fill(String complete_sql) throws myException {
//		this.execute_query(complete_sql);
//		return this;
//	}

	
	
//	
//	/**
//	 * falls der record schon drine war, wird eine exception geschmissen
//	 * @param rec
//	 * @return
//	 * @throws myException
//	 */
//	public RecList20 _add(Rec20 rec) throws myException {
//		if (rec.get_tab()!=this.tab) {
//			throw new myException(this,"Only same-TAB-objects allowed !");
//		}
//		
//		if (rec.is_newRecordSet()) {
//			throw new myException(this,"New Records cannot be added!");
//		}
//		
//		String id = rec.get_key_value();
//		
//		if (S.isEmpty(id)) {
//			throw new myException(this,"Critical Error: Rec has no id-value !!!");
//		}
//		
//		if (this.keySet().contains(id)) {
//			throw new myException(this,"ID <"+id+">  is even represented in this record !");
//		}
//		
//		this.put(id, rec);
//		
//		return this;
//	}
//	
	
	
//	public _TAB get_tab() {
//		return tab;
//	}


//	public String get_sql4query() {
//		return sql4query;
//	}
//	

//	@Override
//	protected void execute_query(String sql) throws myException {
//
//		this.clear();
//		
//		boolean toolbox_created = false;
//		if (this.oDB==null) {
//			this.oDB = bibALL.get_myDBToolBox();
//			toolbox_created=true;
//		}
//		this.sql4query = sql;
//		MyDBResultSet oRS = this.oDB.OpenResultSet(sql);
//		
//		if (oRS.RS != null) {
// 
//            try  {
//                
//            	int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();
//
//                if (iAnzahlSpalten > 0) {
//                    
//                    while (oRS.RS.next()) {
//                    	Rec20 r = new Rec20(this.tab);
//                    	
//                        for (int i = 0; i < iAnzahlSpalten; i++) {
//                        	MyMetaFieldDEF mdef =   new MyMetaFieldDEF(oRS.RS,i, this.tab.fullTableName());
//                        	MyResultValue oResult = new MyResultValue(mdef,oRS.RS,false);
//                        	IF_Field f= r.find_field(mdef.get_FieldName());
//                        	
//                        	//2017-922: overheadFields
//                        	//alt:
////                        	if (f==null) {
////                        		oRS.Close();
////                        		if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
////                               	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT,"Field "+mdef.get_FieldName()+" ist not in the regular list of table "+this.tab.fullTableName()+": "+sql);
////                        	}
////                        	//der leere wert wird ersetzt durch einen mit result
////                        	r.get(f)._setResult(oResult);
//                        	
//                        	if (f==null) {
//                               	System.out.println("Field "+mdef.get_FieldName()+" is not in the regular list of table "+this.tab.fullTableName()+": "+sql);
//                               	r.getOverheadFields().put(mdef.get_FieldName().toUpperCase(), oResult);
//                        	} else {
//                        		r.get(f)._setResult(oResult);
//                        	}
//                        }
//                        r.set_sql4query(r.createOwnQuery());
//                        this._add(r);
//                    }
//
//                }
//                
//            }
//            catch (myException ex)
//            {
//            	oRS.Close();
//                if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
//            	throw ex;
//            }
//            catch (Exception e)
//            {
//            	oRS.Close();
//                if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
//            	e.printStackTrace();
//            	throw new myException(e.getLocalizedMessage());
//            }
// 		}
//		else
//		{
//        	DEBUG.System_println("--------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
//        	DEBUG.System_println("Rec20: Error SQL  ---------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
//        	DEBUG.System_println(sql,DEBUG.DEBUG_FLAG_SQL_ERROR);
//        	DEBUG.System_println("--------------------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
//            if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
//          	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT,"Rec20:__build_Hash: Cannnot open resultset !"+sql);
//		}
//
//        oRS.Close();
//        if (toolbox_created) { bibALL.destroy_myDBToolBox(oDB); }
//
//	}
	
	
	
//	public Rec20 get(int index) throws myException{
//		if (index>=this.size()) {
//			throw new myException(this,"Index out of range !!");
//		}
//		return this.get(new Vector<String>(this.keySet()).get(index));
//	}


	@Override
	public Iterator<Rec20> iterator() {
		return this.values().iterator();
	}

	
	public RecList20 get_filtered_list(IF_RecListFilter filter) throws myException {
		RecList20 rl = new RecList20(this.tab);
		for (Rec20 rec: this) {
			if (filter.isInFilter(rec)) {
				rl._add(rec);
			}
		}
		return rl;
	}
	
	
	
//	public VectorNN<String> get_sqls_2_delete() throws myException {
//		VectorNN<String> v = new VectorNN<>();
//		
//		for (Rec20 r: this) {
//			v.add(r.get_sql_2_delete());
//		}
//		
//		return v;
//	}
	
	
//	
//	public LinkedHashMap<String,MyBigDecimal> get_hm_db_MyBigDecimals(IF_Field field) throws myException {
//		LinkedHashMap<String,MyBigDecimal> hmrueck = new LinkedHashMap<String,MyBigDecimal>();
//		for (Rec20 r: this) {
//			hmrueck.put(r.get_key_value(),r.get_myBigDecimal_dbVal(field));
//		}
//		return hmrueck;
//	}
//	
//	public LinkedHashMap<String,MyLong> get_hm_db_MyLong(IF_Field field) throws myException {
//		LinkedHashMap<String,MyLong> hmrueck = new LinkedHashMap<String,MyLong>();
//		for (Rec20 r: this) {
//			hmrueck.put(r.get_key_value(),r.get_myLong_dbVal(field));
//		}
//		return hmrueck;
//	}
//	
//
//	
//	public LinkedHashMap<String,MyDate> get_hm_db_MyDate(IF_Field field) throws myException {
//		LinkedHashMap<String,MyDate> hmrueck = new LinkedHashMap<String,MyDate>();
//		for (Rec20 r: this) {
//			hmrueck.put(r.get_key_value(),r.get_myDate_dbVal(field));
//		}
//		return hmrueck;
//	}
//	


	
	/**
	 * summiert nach der uebergebenen summationsregel
	 * @param summation
	 * @return
	 * @throws myException
	 */
	public BigDecimal  get_sum_bigDecimal(IF_RecListSumBigDecimal summation) throws myException {
		BigDecimal bd_ret = new BigDecimal(0);
		
		for (Rec20 r: this) {
			bd_ret=bd_ret.add(summation.bd_value(r));
		}
		
		return bd_ret;
	}
	
	

	/**
	 * 
	 * @param com
	 * @return  sorted vector of rec20-objects
	 */
	@Override
	public VEK<Rec20>  get_sorted_vector(Comparator<Rec20>  com) {
		
		VEK<Rec20>  v = new VEK<>();
		
		for (Rec20 r: this) {
			v._a(r);
		}
		
		v.sort(com);
		
		return v;
	}
	
	
	
	@Override
	public VEK<Rec20> getVEK() {
		VEK<Rec20> v = new VEK<Rec20>();
		for (Rec20 r: this) {
			v._a(r);
		}
		return v;
	}
	
	
	/**
	 * 
	 * @param field
	 * @param valWhenNull
	 * @return returns vektor with unformated values of field
	 * @throws myException
	 */
	@Override
	public VEK<String> getVEK_uf(IF_Field field, String valWhenNull) throws myException {
		VEK<String>  vr = new VEK<String>();
		for (Rec20 r: this) {
			vr._a(r.get_ufs_dbVal(field, valWhenNull));
		}
		return vr;
	}



//	/**
//	 * 
//	 * @param field
//	 * @return returns vektor with unformated values of field
//	 * @throws myException
//	 */
//	public VEK<String> getVEK_uf(IF_Field field) throws myException {
//		return this.getVEK_uf(field, "");
//	}
//	
//	/**
//	 * 
//	 * @param field
//	 * @param valWhenNull
//	 * @return returns vektor with formated values of field
//	 * @throws myException
//	 */
//	public VEK<String> getVEK_f(IF_Field field, String valWhenNull) throws myException {
//		VEK<String>  vr = new VEK<String>();
//		for (Rec20 r: this) {
//			vr._a(r.get_fs_dbVal(field, valWhenNull));
//		}
//		return vr;
//	}
//	
//	/**
//	 * 
//	 * @param field
//	 * @return returns vektor with formated values of field
//	 * @throws myException
//	 */
//	public VEK<String> getVEK_f(IF_Field field) throws myException {
//		return this.getVEK_f(field, "");
//	}
//	
	
	
//	/**
//	 * 20171204: dbtoolbox setter vergessen
//	 */
//	public RecList20 _set_dbtoolbox(MyDBToolBox db) {
//		this.oDB=db;
//		return this;
//	}
//	

//	
//	public VEK<Long> getVEKLong(IF_Extract<Long> extractor) throws myException {
//		VEK<Long> v = new VEK<Long>();
//		
//		for (Rec20 r: this) {
//			Long l = extractor.getValue(r);
//			if (l!=null) {
//				v.add(l);
//			}
//		}
//		return v;
//	}
//	
//	public VEK<String> getVEKString(IF_Extract<String> extractor) throws myException{
//		VEK<String> v = new VEK<String>();
//		
//		for (Rec20 r: this) {
//			String s = extractor.getValue(r);
//			if (s!=null) {
//				v.add(s);
//			}
//		}
//		return v;
//	}
//	

}
