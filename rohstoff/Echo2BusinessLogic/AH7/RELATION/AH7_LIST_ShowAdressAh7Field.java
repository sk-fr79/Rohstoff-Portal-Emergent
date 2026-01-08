package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.HIGHLEVEL.E2_AdressEditWithId;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class AH7_LIST_ShowAdressAh7Field extends AH7_LIST_ShowAdress {

	
	public AH7_LIST_ShowAdressAh7Field(IF_Field p_f) {
		super();
		this._setField(p_f);
		this.EXT().set_bLineWrapListHeader(true);
	}


	@Override
	public void renderAdress(Rec21 a) throws myException {
		Rec21_adresse adress = new Rec21_adresse(a);
		this._clear()._setSize(20,20,200);
		this._a(new AH7_Marker(this.getField()), new RB_gld()._ins(2,0,2,0));

		this._a(
				new E2_AdressEditWithId(adress.get_ufs_dbVal(ADRESSE.id_adresse))
				._addActionAfterSave(this.getActionToRefreshNaviList())
						._ttt(S.ms("Adresse ").ut(adress.__get_main_adresse().getUfs(ADRESSE.id_adresse)).t(" öffnen"))
				);

		String adressVal = adress.get__Name_flexible(", ");
		this._a(new ownBt(adressVal)._lwn(), new RB_gld()._ins(2,0,2,0));
		//zeile 2
		this._a("")._a("")._a(new ownBt(adress.get__PLZ_Anschrift(", ")+(adress.isPrivat()?" (*privat*)":""))._lwn(), new RB_gld()._ins(2,0,2,0));
		
		//--zeile 3 (optional)
		if (adress.__is_liefer_adresse()) {
			this._a("")._a("")._a(new ownBt(adress.__get_main_adresse().get__FullNameAndAdress_flexible(", "))
									._lwn()
									._fo_italic()
									._fsa(-2)
									._ttt(S.ms("Firmenadresse zu: <").ut(adressVal+">")), new RB_gld()._ins(2,2,2,0));
		}
	}


	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			AH7_LIST_ShowAdressAh7Field oCheckCopy = new AH7_LIST_ShowAdressAh7Field(this.getField());
			oCheckCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oCheckCopy));
			return oCheckCopy;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy("Error copying AH7_showAH7field");
		}

	}


	/**
	 * macht die labels in der liste "aktiv" zum zeilenmarkieren
	 */
	private class ownBt extends E2_Button {
		public ownBt(String c) {
			super();
			this._t(c);
			this._aaa(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					AH7_LIST_ShowAdressAh7Field.this.EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
				}
			});
		}
	}
	
	
}
