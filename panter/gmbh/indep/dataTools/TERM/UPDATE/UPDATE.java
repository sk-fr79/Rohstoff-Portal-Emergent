/**
 * 
 */
package panter.gmbh.indep.dataTools.TERM.UPDATE;

import java.util.HashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class UPDATE implements Term {

	private _TAB tab = null;
	private Long idToUpdate = null;
	
	private HashMap<IF_Field, String>  hmNewValues = new HashMap<>();
	
	
	/**
	 * @param p_tab
	 * @param p_idToUpdate
	 */
	public UPDATE(_TAB p_tab, Long p_idToUpdate) {
		super();
		this.tab = p_tab;
		this.idToUpdate = p_idToUpdate;
	}

	
	/**
	 * 
	 * @param f
	 * @param valFormated  (wert, z.B "test" oder 1.010,23)
	 * @return
	 * @throws myException
	 */
	public UPDATE _addPair(IF_Field f, String valFormated) throws myException {
		if (f==f._t().key()) {
			throw new myException(this,"key cannot be updated !!!");
		}
		
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
	public UPDATE _addRawPair(IF_Field f, String valUnFormated) throws myException {
		if (f==f._t().key()) {
			throw new myException(this,"key cannot be updated !!!");
		}
		
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
		if (this.tab==null || this.idToUpdate==null || this.idToUpdate<=0 || this.hmNewValues.size()==0) {
			throw new myException(this,"Not all informations are present !!");
		}
		
		String s = "UPDATE "+bibE2.cTO()+"."+this.tab.fullTableName()+" SET ";
		
		int fieldnum = this.hmNewValues.values().size();
		int zaehler = 0;

		for (IF_Field f: this.hmNewValues.keySet()) {
			s=s+(f.fieldName()+"="+this.hmNewValues.get(f));
			zaehler++;
			if (zaehler<fieldnum) {
				s=s+",";
			}
		}
		
		s = s + " WHERE "+ this.tab.key().fieldName()+"="+this.idToUpdate;
		return s;
	}

}
