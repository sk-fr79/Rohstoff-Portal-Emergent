package panter.gmbh.indep.dataTools;

import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public interface IF_Enum4JoinDef {
	public String 					getSchema();
	public String 					getLeftTableAlias();
	public String 					getJoinTableAlias();
	public String 					getJoinTableFieldPrefix();
	public _TermCONST.JOINTYPES 	getJoinTyp();
	public IF_Field                 getLeftField() throws myException;
	public IF_Field                 getJoinField() throws myException;
	public VEK<IF_Field>		    getFields()  throws myException;
	
	public default String getFieldListSeparated(String sepKey) throws myException {
		if (this.getFields()==null || this.getFields().size()==0) {
			return "";
		}
		
		String cRet = sepKey;
		
		for (IF_Field f: this.getFields()) {
			cRet = cRet+f.fieldName()+sepKey;
		}
		
		return cRet;
	}
	
}
