/**
 * 
 */
package panter.gmbh.indep.dataTools.TERM.INSERT;

import java.util.LinkedHashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class INSERT implements Term {

	private _TAB tab = null;
	
	private LinkedHashMap<IF_Field, String>  hmNewValues = new LinkedHashMap<>();
	
	
	/**
	 * @param p_tab
	 * @param p_idToUpdate
	 */
	public INSERT(_TAB p_tab) {
		super();
		this.tab = p_tab;
	}

	
	/**
	 * 
	 * @param f
	 * @param valFormated  (wert, z.B "test" oder 1.010,23)
	 * @return
	 * @throws myException
	 */
	public INSERT _addPair(IF_Field f, String valFormated) throws myException {
		
		if (f._t()!=this.tab) {
			throw new myException(this,"field is not from this table !!!");
		}
		
		this.hmNewValues.put(f, f.generate_MetaFieldDef().get_cStringForDataBase(valFormated, true, false));
		return this;
	}

	
	/**
	 * 
	 * @param f
	 * @param valUnFormated  (wert, z.B "'test'" oder 1010.23)
	 * @return
	 * @throws myException
	 */
	public INSERT _addRawPair(IF_Field f, String valUnFormated) throws myException {
		
		if (f._t()!=this.tab) {
			throw new myException(this,"field is not from this table !!!");
		}
		
		this.hmNewValues.put(f, valUnFormated);
		return this;
	}

	

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.dataTools.TERM.Term#s()
	 */
	@Override
	public String s() throws myException {
		if (this.tab==null || this.hmNewValues.size()==0) {
			throw new myException(this,"Not all informations are present !!");
		}
		
		if (this.hmNewValues.get(this.tab.key())== null) {
			this._addRawPair(this.tab.key(), this.tab.sql_nextval());
		}
		
		VEK<IF_Field>  vSort = new VEK<IF_Field>()._a(this.hmNewValues.keySet());
		
		String s = "INSERT INTO "+bibE2.cTO()+"."+this.tab.fullTableName()+" ";
		
		int fieldnum = this.hmNewValues.values().size();

		int zaehler = 0;
		String fieldBlock = "";
		for (IF_Field f: vSort) {
			fieldBlock=fieldBlock+(f.fieldName());
			zaehler++;
			if (zaehler<fieldnum) {
				fieldBlock=fieldBlock+",";
			}
		}
		
		zaehler = 0;
		String valBlock = "";
		for (IF_Field f: vSort) {
			valBlock=valBlock+(this.hmNewValues.get(f));
			zaehler++;
			if (zaehler<fieldnum) {
				valBlock=valBlock+",";
			}
		}
		
		
		
		s = s + "("+ fieldBlock+") VALUES ("+valBlock+")";
		return s;
	}

}
