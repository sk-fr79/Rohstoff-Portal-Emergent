package panter.gmbh.Echo2.decisions;

import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class FingerPrint_Record extends FingerPrint {

	private VectorSingle  v_FieldsToExclude = new VectorSingle();
	
	
	public FingerPrint_Record() {
		super();
	}

	public FingerPrint_Record(MyRECORD  oRec, String cTableName, String cTableID, VectorSingle  vFieldsToExclude) throws myException {
		super();
		this.v_FieldsToExclude.addAll(vFieldsToExclude);
		
		for (String cField: oRec.keySet()) {
			if (!vFieldsToExclude.contains(cField)) {
				this.put(cTableName+"@"+cTableID+"@"+cField, oRec.get_UnFormatedValue(cField, "-"));
			}
		}
		
	}

	public VectorSingle get_vFieldsToExclude() {
		return v_FieldsToExclude;
	}

	public void set_vFieldsToExclude(VectorSingle vFieldsToExclude) {
		this.v_FieldsToExclude.addAll(vFieldsToExclude);
	}

	
	
}
