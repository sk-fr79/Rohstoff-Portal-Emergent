package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


public class FZ_LIST_btDeleteVektor extends E2_Button {

	private E2_NavigationList  f_navilist = null;
	
	public FZ_LIST_btDeleteVektor(E2_NavigationList  navilist) throws myException {
		super();
		this._image(E2_ResourceIcon.get_RI("delete.png"));
		this.f_navilist=navilist;
		this._aaa(new ownActionDelete())._ttt("Den selektierten Vektor löschmarkieren !");
		this.add_GlobalValidator(new ownValidatorOnlyAtom());

	}

	
	
	private class ownActionDelete extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FZ_LIST_btDeleteVektor oThis = FZ_LIST_btDeleteVektor.this;
			
			Vector<String>  v_id = oThis.f_navilist.get_vSelectedIDs_Unformated();
			
			if (v_id.size()==0) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens eine Zeile zum Löschen wählen !")));
			} else {
				
				for (String id: v_id) {
					bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(oThis.delete_row(id, bibMSG.MV()),true));
				}
			}
			
			oThis.f_navilist._REBUILD_ACTUAL_SITE("");
		}
		
	}

	
	
	private Vector<String> delete_row(String id_bewegung_vektor, MyE2_MessageVector  mv_r) throws myException {
		Vector<String> v_sql = new Vector<>();
		
		RECORD_BEWEGUNG_VEKTOR  		v = new RECORD_BEWEGUNG_VEKTOR(id_bewegung_vektor);
		RECORD_BEWEGUNG  				b = v.get_UP_RECORD_BEWEGUNG_id_bewegung();
		
		
		RECLIST_BEWEGUNG_VEKTOR_POS  	l_p = v.get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor();
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		for (RECORD_BEWEGUNG_VEKTOR_POS p: l_p) {
			
			RECLIST_BEWEGUNG_ATOM  l_a = p.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor_pos();
			
			for (RECORD_BEWEGUNG_ATOM  a: l_a) {
				a.nv(BEWEGUNG_ATOM.deleted, "Y", mv).nv(BEWEGUNG_ATOM.del_kuerzel, bibALL.get_KUERZEL(), mv).nv(BEWEGUNG_ATOM.del_date, bibALL.get_cDateNOW(), mv);
				v_sql.add(a.get_SQL_UPDATE_STD());
			}
			
		}
	
		v.nv(BEWEGUNG_VEKTOR.deleted, "Y", mv).nv(BEWEGUNG_VEKTOR.del_kuerzel, bibALL.get_KUERZEL(), mv).nv(BEWEGUNG_VEKTOR.del_date, bibALL.get_cDateNOW(), mv);
		
		v_sql.add(v.get_SQL_UPDATE_STD());
		
		//wenn dieser vektor der einzige in der bewegung ist, dann die bewegung auch loeschen
		if (b.get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_bewegung().size()==1) {
			v.nv(BEWEGUNG.deleted, "Y", mv).nv(BEWEGUNG.del_kuerzel, bibALL.get_KUERZEL(), mv).nv(BEWEGUNG.del_date, bibALL.get_cDateNOW(), mv);
			v_sql.add(v.get_SQL_UPDATE_STD());
			mv_r.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Zugehörige Bewegung ebenfalls gelöscht !  ID-Vektor: "+id_bewegung_vektor)));
		}
		mv_r.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("ID-Vektor wurde gelöscht! "+id_bewegung_vektor)));
		
		return v_sql;
	}
	
	
	
	
	
	private class ownValidatorOnlyAtom extends XX_ActionValidator_NG {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			Vector<String> v_ids = FZ_LIST_btDeleteVektor.this.f_navilist.get_vSelectedIDs_Unformated();
			
			if (v_ids.size()>0) {
				for (String id: v_ids) {
					RECORD_BEWEGUNG_VEKTOR v = new RECORD_BEWEGUNG_VEKTOR(id);
					if (S.isFull(v.get_UP_RECORD_BEWEGUNG_id_bewegung().ufs(BEWEGUNG.id_vpos_tpa_fuhre,""))) {
						mv._addAlarm("Nur Bewegungssätze auf Basis Bewegungsatom können gelöscht werden !");
						
					}
				}
				
				
			}
			
			
			
			return mv;
		}
		
	}

}
