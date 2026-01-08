package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;


public class AH7P_MASK_DataObjectCollector extends RB_DataobjectsCollector_V2 {
    public AH7P_MASK_DataObjectCollector() throws myException {
        super();
        this.registerComponent(new AH7P_KEY(), new AH7P_MASK_DataObject());
        this._addMessageTranslator(new RB_MessageTranslator(
        		new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt. Die Eigenschaftsrelationen des Datensatzes existieren schon!"));
    }
    
   
    public AH7P_MASK_DataObjectCollector(String id_ah7_profil, MASK_STATUS status) throws myException {
        super();
        this.registerComponent(new  AH7P_KEY(), new AH7P_MASK_DataObject(new Rec20(_TAB.ah7_profil)._fill_id(id_ah7_profil),status));
        this._addMessageTranslator(new RB_MessageTranslator(
        		new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt. Die Eigenschaftsrelationen des Datensatzes existieren schon!"));
    }

    @Override
	public void database_to_dataobject(Object startPoint) throws myException {
	}
  
	@Override
	public RB_DataobjectsCollector_V2 get_copy() throws myException {
		return null;
	}
	
	
	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv)	throws myException {
		
	}
	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V2 do_collector,MyE2_MessageVector mv) throws myException {
		
		SEL s = new SEL("count(*)").FROM(_TAB.ah7_profil).WHERE(new vgl_YN(AH7_PROFIL.profile4allothers,true));
		
		MyLong l = new MyLong(bibDB.EinzelAbfrage(s.s()));
		
		if (l.isOK()) {
			if (l.get_iValue()>1) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es darf nur ein Profil geben, das den Status: Sonderfall für alle Relationen, die nicht eingeordnet werden können, trägt !")));
			}
		} else {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("AH7P_MASK_DataObjectCollector: Fehler bei Feststellung der Anzahl Standard-Sätze !")));
		}
	}
}
 
