package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN_NG;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibTEXT;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class FK_Button_Eingabe_Kosten_NG_Validators extends Vector<XX_ActionValidator>{

	private E2_NavigationList oNaviList;

	public FK_Button_Eingabe_Kosten_NG_Validators(E2_NavigationList oNavigationList) {
		super();

		this.oNaviList = oNavigationList;

		this.add(new selection_validator());
		this.add(new spedition_validator());
		//		this.add(new transportAufträgen_validator());
	}

	public E2_NavigationList getNavilist() {
		return oNaviList;
	}




	public class spedition_validator extends XX_ActionValidator{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector omv =  new MyE2_MessageVector();

			FK_MASK_bt_MULTI_FUHREN_KOSTEN comp = (FK_MASK_bt_MULTI_FUHREN_KOSTEN)oComponentWhichIsValidated;

			Vector<String> selectedIds = comp.getSelectedFuhreId();

			StringBuffer q2 = new StringBuffer("select NVL(NVL(tk.id_adresse, NVL(fu.ID_ADRESSE_SPEDITION,(SELECT EIGENE_ADRESS_ID FROM JD_MANDANT WHERE ID_MANDANT=FU.ID_MANDANT))),0)  AS id_adresse_spedition, fu.ID_VPOS_TPA_FUHRE ");
			q2.append("from jt_vpos_tpa_fuhre fu ");
			q2.append("left outer join jt_vpos_tpa tp on ( fu.id_vpos_tpa= tp.id_vpos_tpa)");
			q2.append("left outer join jt_vkopf_tpa tk on ( tk.id_vkopf_tpa= tp.id_vkopf_tpa)");
			q2.append("where FU.id_vpos_tpa_Fuhre in (");
			q2.append(bibTEXT.concat(selectedIds, ",", ""));
			q2.append(")");

			if(selectedIds.size()>0){

				String[][] ergebnisArray = bibDB.EinzelAbfrageInArray(q2.toString());

				if(ergebnisArray.length>0){
					String firstId = ergebnisArray[0][0];

					for(String[] item: ergebnisArray){
						if(!item[0].equals(firstId)){
							omv.add(new MyE2_Alarm_Message("Es können nur Fuhren zusammen bepreist werden, die von der gleichen Spedition gefahren wurden !"));
						}
					}

				}
			}
			return omv;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {return null;}

	}

	public class selection_validator extends XX_ActionValidator{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector omv =  new MyE2_MessageVector();

			FK_MASK_bt_MULTI_FUHREN_KOSTEN comp = (FK_MASK_bt_MULTI_FUHREN_KOSTEN)oComponentWhichIsValidated;
			Vector<String> selectionList = comp.getSelectedFuhreId();
			if(selectionList.size()==0){
				omv.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Bitte wählen Sie mindestens eine Fuhre zur Preiseingabe an !")));
			}

			return omv;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}

	}
}
