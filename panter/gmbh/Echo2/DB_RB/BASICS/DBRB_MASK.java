package panter.gmbh.Echo2.DB_RB.BASICS;

import java.util.HashMap;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.exceptions.myException;

public abstract class DBRB_MASK {

	//hashmap mit Paaren aus Feldnamen und Komponenten, die zum laden und speichern gefuellt/registriert werden muessen
	private HashMap<String, IF_DBRB_Component>     hmDB_Components = new HashMap<String, IF_DBRB_Component>();

	private String    								cTABLENAME = null;
	
	/*
	 * hashmap mit den tablenames und den metadefs fuer die benutzung "alter" DB_Components
	 * wird generiert mit dem Tabellennamen
	 */
	private HashMap<String, MyMetaFieldDEF>			hmMetaFieldDEF = new HashMap<String, MyMetaFieldDEF>();
	
	
	

	private MyRECORD_IF_RECORDS 			record4Mask = null;
	private MyRECORD_NEW  					recordNEW4Mask = null;
	
	public abstract MyE2_MessageVector  	resetAllMaskSettings() 								throws myException;
	public abstract MyE2_MessageVector  	clearMask() 										throws myException;
	
	public abstract MyE2_MessageVector  	doMaskSettings_Before_Load()  						throws myException;
	public abstract MyE2_MessageVector  	fillMask()  										throws myException;
	public abstract MyE2_MessageVector  	doMaskSettings_After_Load()  						throws myException;
	
	
	public abstract MyE2_MessageVector  	validateMask_Before_Save()  						throws myException;
	public abstract MyE2_MessageVector  	setMaskValuesToRecord()  							throws myException;
	public abstract String  				getSqlStatementSaveMask(MyE2_MessageVector oMV)  	throws myException;

	
	public DBRB_MASK() {
		super();
	}

	public DBRB_MASK(String tablename) throws myException {
		super();
		this.set_cTABLENAME(tablename, true);
	}

	
	public DBRB_MASK(MyRECORD_IF_RECORDS record) throws myException {
		super();
		this.setActualRecord4Mask(record,true);
	}
	

	
	/**
	 * 
	 * @param FIELDNAME
	 * @param oComp
	 * @throws myException
	 */
	public void registerDB_Component(String FIELDNAME, IF_DBRB_Component oComp) throws myException {
		if (this.hmDB_Components.containsKey(FIELDNAME.toUpperCase())) {
			throw new myException(this,"Fieldname "+FIELDNAME+" is already used !");
		}
		if (S.isEmpty(this.cTABLENAME)) {
			throw new myException(this,"There is no Tablename registered yet !");
		}
		this.hmDB_Components.put(FIELDNAME.toUpperCase(), oComp);
	}

	
	/**
	 * methode liefert ein "pseudo"-SQLField, damit die noetigen strukturen fuer die db-components vorhanden sind.
	 * dies kann erst nach uebergabe des feldnamens erfolgen (dann ist die hashmap hmTableDefs vorhanden)
	 */
	public SQLField_4_DBRB get_SQLFieldDummy(String cFIELDNAME) throws myException {
		if (S.isEmpty(this.cTABLENAME)) {
			throw new myException(this,"get_SQLFieldDummy: There is no Tablename registered yet !");
		}
	
		if (this.hmMetaFieldDEF.containsKey(cFIELDNAME.toUpperCase())) {
			return new SQLField_4_DBRB(this.cTABLENAME, cFIELDNAME.toUpperCase(),this.hmMetaFieldDEF.get(cFIELDNAME.toUpperCase()));
		} else {
			throw new myException(this,"get_SQLFieldDummy: field  "+cFIELDNAME+" is not in this DBRB_MASK-TableDef");
		}
		
	}
	
	
	/**
	 * 
	 * @param record
	 * @throws myException 
	 */
	public void setActualRecord4Mask(MyRECORD_IF_RECORDS  record, boolean bCreate_RECORD_NEW) throws myException {
		this.record4Mask = record;
		if (S.isFull(this.cTABLENAME) && !(this.cTABLENAME.toUpperCase().equals(this.record4Mask.get_TABLENAME().toUpperCase()))) {
			throw new myException(this,"setActualRecord4Mask: Not the same table !!! Old:"+this.cTABLENAME+"  ->  new : "+record.get_TABLENAME());
		}
		this.cTABLENAME = this.record4Mask.get_TABLENAME().toUpperCase();
		
		if (bCreate_RECORD_NEW) {
			this.hmMetaFieldDEF.putAll(record.get_RECORD_NEW());
			this.recordNEW4Mask =  new MyRECORD_NEW(this.get_cTABLENAME(), this.hmMetaFieldDEF);
		}

	}
	
	
	public String get_cTABLENAME() {
		return cTABLENAME;
	}
	
	public void set_cTABLENAME(String tablename, boolean bCreate_RECORD_NEW) throws myException {
		if (S.isFull(this.cTABLENAME) && !(this.cTABLENAME.toUpperCase().equals(tablename.toUpperCase()))) {
			throw new myException(this,"You try to overwrite the tablename "+this.cTABLENAME+" with "+tablename+"!!!");
		}
	
		this.cTABLENAME = tablename.toUpperCase();
		
		if (bCreate_RECORD_NEW) {
			this.recordNEW4Mask = new MyRECORD_NEW(this.cTABLENAME);
			this.hmMetaFieldDEF.putAll(this.recordNEW4Mask);
		}
		
	}

	
	public MyRECORD   getActualRecord4Mask() {
		return (MyRECORD)this.record4Mask;
	}
	
	public MyRECORD_IF_RECORDS   get_IF_RECORDS_ActualRecord4Mask() {
		return this.record4Mask;
	}

	

	public HashMap<String, IF_DBRB_Component> get_hmDB_Components() {
		return hmDB_Components;
	}


	public void setAllDBComponentsEnabled() throws myException {
		for (MyE2IF__Component oCOMP: this.hmDB_Components.values()) {
			oCOMP.set_bEnabled_For_Edit(true);
		}
	}
	
	public void setAllDBComponentsDisable() throws myException {
		for (MyE2IF__Component oCOMP: this.hmDB_Components.values()) {
			oCOMP.set_bEnabled_For_Edit(false);
		}
	}
	
	
	public MyRECORD_NEW getRecordNEW4Mask() {
		return recordNEW4Mask;
	}
	
	
	
}
