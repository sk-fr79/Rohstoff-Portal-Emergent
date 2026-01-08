 
package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER;
  
import java.util.Vector;
  
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_Delete;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
  
public class ZT_LIST_bt_multiDelete extends RB_BtV4_Delete {
	
    
    public ZT_LIST_bt_multiDelete(RB_TransportHashMap  p_tpHashMap)    {
        super();
        this._image(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
        
        this._setTransportHashMap(p_tpHashMap);
        
        this.add_GlobalValidator(ZT_VALIDATORS.DELETE.getValidator());
        
    }
   
	@Override
	public MyE2_String get_message_text_mindestens_eine_irgendwas_markieren() {
		return S.ms("Bitte mindestens eine Zeile zum Loeschen markieren");
	}
   
	@Override
	public MyE2_String get_warnung_achtung_es_wird_ein_irgendwas_geloescht() {
		return S.ms("Soll diese Zolltarifnummer-Definition geloescht werden ?");	
    }
	@Override
	public String get_warnung_achtung_es_werden_n_irgendwas_geloescht_mit_platzhalter_fuer_zahl() {
		return "Sollen diese #WERT# Zolltarifnummer-Definitionen geloescht werden ?";
	}
   
	
	@Override
	public Vector<String> get_delete_sql_statements(String id_to_delete, MyE2_MessageVector mv) throws myException {
		
		Vector<String> v = new Vector<>();
		
		MyLong lid = new MyLong(id_to_delete);
		if (lid.isOK()) {
			Rec22 rec = new Rec22(_TAB.zolltarifnummer)._fill_id(lid.get_oLong());
			v.add(rec.get_DELETE_STATEMENT());
		} else {
			mv._addAlarm(S.ms("Fehler beim Erstellen der Delete-Statements !"));
		}
		
		return v;
	}
  
    
    
}
 
 
