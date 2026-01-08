package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;

public class KFIX_K_M_Selection_Validator extends XX_ActionValidator_NG {

	private MyE2_MessageVector 	oMv = null;
	private E2_NavigationList 	naviList= null;

	public KFIX_K_M_Selection_Validator(E2_NavigationList navilist) {
		super();
		this.naviList = navilist;
		this.oMv = new MyE2_MessageVector();
	}
	

	@Override
	public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
		KFIX_K_M_Selection_Validator.this.oMv.clear();

		Vector<String> ids_to_pruefen = KFIX_K_M_Selection_Validator.this.naviList.get_vSelectedIDs_Unformated();
		if(ids_to_pruefen.size()==0){
			KFIX_K_M_Selection_Validator.this.oMv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens einen Kontrakt auswählen!")));
		}else{
			String previousValue = new Rec20_VKOPF_KON(_TAB.vkopf_kon)._fill_id(ids_to_pruefen.get(0)).get_fs_dbVal(VKOPF_KON.ist_fixierung, "N");
			for(int i=1;i<ids_to_pruefen.size();i++){
				String actualValue = new Rec20_VKOPF_KON(_TAB.vkopf_kon)._fill_id(ids_to_pruefen.get(i)).get_fs_dbVal(VKOPF_KON.ist_fixierung, "N");
				if(! (actualValue.equals(previousValue))){
					KFIX_K_M_Selection_Validator.this.oMv.add_MESSAGE(new MyE2_Alarm_Message("Es dürfen nur Kontrakte des gleichen Typs zur Bearbeitung ausgewählt werden."));
					break;
				}
			}
		}
		return KFIX_K_M_Selection_Validator.this.oMv;
	}

}
