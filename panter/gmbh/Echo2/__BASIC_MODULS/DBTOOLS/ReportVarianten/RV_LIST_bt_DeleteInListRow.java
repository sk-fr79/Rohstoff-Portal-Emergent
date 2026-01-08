 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten;
  
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_Delete;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
  
public class RV_LIST_bt_DeleteInListRow extends RB_BtV4_Delete  {
    
	
	public RV_LIST_bt_DeleteInListRow(RB_TransportHashMap p_tpHashMap) {
        super();
        
        this._setShapeDeleteButton();
        this._setTransportHashMap(p_tpHashMap);
        
        this.setToolTipText(new MyE2_String("Listenfeld-Definition in dieser Zeile loeschen").CTrans());
        
        this.add_GlobalValidator(RV_VALIDATORS.DELETE.getValidator());
        
    }
 
	@Override
	public MyE2_String get_message_text_mindestens_eine_irgendwas_markieren() {
		return S.ms("Bitte mindestens eine Zeile zum Loeschen markieren");
	}
 
	@Override
	public MyE2_String get_warnung_achtung_es_wird_ein_irgendwas_geloescht() {
		return S.ms("Soll diese Listenfeld-Definition geloescht werden ?");	}
 
	@Override
	public String get_warnung_achtung_es_werden_n_irgendwas_geloescht_mit_platzhalter_fuer_zahl() {
		
		return "Sollen diese #WERT# Listenfeld-Definitionen geloescht werden ?";
	}
 
	@Override
	public Vector<String> get_delete_sql_statements(String id_to_delete, MyE2_MessageVector mv) throws myException {
		
		Vector<String> v = new Vector<>();
		
		MyLong lid = new MyLong(id_to_delete);
		if (lid.isOK()) {
			Rec21 rec = new Rec21(_TAB.rep_varianten)._fill_id(lid.get_oLong());
			v.add(rec.get_DELETE_STATEMENT());
		} else {
			mv._addAlarm(S.ms("Fehler beim erstellen der Delete-Statements !"));
		}
		
		return v;
	}
   
	@Override
	public Vector<Long> getIdsToDelete(MyE2_MessageVector mv) throws myException {
		//in der navilist als listenelement die id aus der aktuellen E2_ComponentMap holen
		
		if (this.EXT().get_oComponentMAP()!=null) {
			MyLong  idToDel = new MyLong(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			
			if (idToDel.isOK()) {
				return new VEK<Long>()._a(idToDel.get_oLong());
			} else {
				throw new myException("Error finding id to delete");
			}
		} else {
			throw new myException("Error:  no containing E2_ComponentMAP");
   
		}
	}
   
	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		RV_LIST_bt_DeleteInListRow copy= new RV_LIST_bt_DeleteInListRow(this.getTransportHashMap());
		copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
		return copy;
	}
  
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}
	
	
	/**
	 * spezielle methode zum ein permanent enables object doch noch disablen zu koennen
	 * @param enabled
	 * @throws myException
	 */
	public void setEnabledForEditOfSuperClass(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(enabled);
	}
	
  
}
 
 
