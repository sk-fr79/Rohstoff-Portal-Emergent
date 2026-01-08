package panter.gmbh.indep.dataTools;

import java.util.HashMap;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.exceptions.myException;

public class HASHMAP_MyMetaFieldDef extends HashMap<String,MyMetaFieldDEF> {
	
	private String c_TABLENAME = null;


	
	public HASHMAP_MyMetaFieldDef(String cTABLENAME) throws myException {
		super();
		this.init_(cTABLENAME);
	}
	
	
	
	public HASHMAP_MyMetaFieldDef(String cTABLENAME, boolean queryWithEmtyMyRecord) throws myException {
		super();
		if (queryWithEmtyMyRecord) {
			this.putAll(new MyRECORD_NEW(cTABLENAME));
		} else {
			this.init_(cTABLENAME);
		}
	}
	
	
	
	private void init_(String cTABLENAME) throws myException {
		this.c_TABLENAME = cTABLENAME;
		
		DB_META_RECORDLIST rlMETA = new DB_META_RECORDLIST(this.c_TABLENAME);
		
		for (String cFIELD: rlMETA.keySet()) {
			
			this.put(cFIELD, new MyMetaFieldDEF(	rlMETA.get_TABLENAME(), 
													cFIELD, 
													cFIELD, 
													rlMETA.get_DATA_TYPE(cFIELD), 
													rlMETA.get_CHAR_LENGTH(cFIELD), 
													rlMETA.get_DATA_PRECISION(cFIELD), 
													rlMETA.get_DATA_SCALE(cFIELD), 
													rlMETA.get_bNULLABLE(cFIELD)));
			
			
//			DEBUG.System_println(		rlMETA.get_TABLENAME()+" - "+cFIELD+" - "+rlMETA.get_DATA_TYPE(cFIELD)+" - "+rlMETA.get_CHAR_LENGTH(cFIELD)+" - "+
//										rlMETA.get_DATA_PRECISION(cFIELD)+" - "+rlMETA.get_DATA_SCALE(cFIELD)	+" - "+rlMETA.get_bNULLABLE(cFIELD));
			
		}
	}


	/**
	 * 2015-02-13: privater konstruktor fuer die erstellung der clones
	 * @throws myException
	 */
	private HASHMAP_MyMetaFieldDef() throws myException {
		super();
	}
	

	/**
	 * 2015-02-13: kopiert eine HASHMAP_MyMetaFieldDef
	 * @return
	 * @throws myException
	 */
	public HASHMAP_MyMetaFieldDef get_CLONE() throws myException {
		HASHMAP_MyMetaFieldDef  hmRueck = new HASHMAP_MyMetaFieldDef();
		
		for (String cFIELD: this.keySet()) {
			MyMetaFieldDEF old = this.get(cFIELD);
			hmRueck.put(cFIELD, new MyMetaFieldDEF(	this.c_TABLENAME, 
													cFIELD, 
													cFIELD, 
													old.get_FieldType(), 
													old.get_FieldTextLENGTH(), 
													old.get_FieldNumberLENGTH(), 
													old.get_FieldDecimals(), 
													old.get_bFieldNullableBasic()));
			
		}
		
		return hmRueck;
	}
	
	
}
