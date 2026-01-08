
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_Delete;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE_BOX;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;

public class LH_P_LIST_bt_DeleteInListRow extends RB_BtV4_Delete  {


	public LH_P_LIST_bt_DeleteInListRow(RB_TransportHashMap p_tpHashMap) throws myException {
		super();

		this._setShapeDeleteButton();
		this._setTransportHashMap(p_tpHashMap);

		this.setToolTipText(S.ms("Palette in dieser Zeile loeschen").CTrans());
		this._addGlobalValidator(new ownIsOk4Delete());

		this.add_GlobalValidator(LH_P_VALIDATORS.DELETE.getValidator());
	}

	private class ownIsOk4Delete extends XX_ActionValidator_NG{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();

			VEK<Long> vIds_palette = new VEK<Long>()._a(getIdsToDelete(mv));
			
			String mvt_query = new SEL().FROM(_TAB.lager_palette_box)
					.WHERE(new vgl(LAGER_PALETTE_BOX.id_lager_palette, ""+vIds_palette.get(0)))
					.s();
			
			Rec21 oRecPalette = new Rec21(_TAB.lager_palette)._fill_id(vIds_palette.get(0));
			
			int sze = new RecList21(_TAB.lager_palette_box)._fill(mvt_query).size();
			if(oRecPalette.is_yes_db_val(LAGER_PALETTE.ausbuchung_hand) || S.isFull(oRecPalette.getUfs(LAGER_PALETTE.id_vpos_tpa_fuhre_aus))) {
				mv._addAlarm("Die Palette ist schon ausgebucht, sie kann nicht gelöscht werden.");
			}else if(sze>1) {
				mv._addAlarm("Die Palette kann nicht gelöscht werden.");
			}
			
			
			return mv;
		}

	}

	@Override
	public MyE2_String get_message_text_mindestens_eine_irgendwas_markieren() {
		return S.ms("Bitte mindestens eine Zeile zum Loeschen markieren");
	}

	@Override
	public MyE2_String get_warnung_achtung_es_wird_ein_irgendwas_geloescht() {
		return S.ms("Soll diese Palette geloescht werden ?");	}

	@Override
	public String get_warnung_achtung_es_werden_n_irgendwas_geloescht_mit_platzhalter_fuer_zahl() {

		return "Sollen diese #WERT# Palette geloescht werden ?";
	}

	@Override
	public Vector<String> get_delete_sql_statements(String id_to_delete, MyE2_MessageVector mv) throws myException {

		Vector<String> v = new Vector<>();

		MyLong lid = new MyLong(id_to_delete);
		if (lid.isOK()) {
			Rec21 rec = new Rec21(_TAB.lager_palette)._fill_id(lid.get_oLong());
			
			RecList21 rec_lager_palette_box = rec.get_down_reclist21(LAGER_PALETTE_BOX.id_lager_palette, "", "");
			for(Rec21 recdel : rec_lager_palette_box) {
				v.add(recdel.get_DELETE_STATEMENT());
			}
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
		LH_P_LIST_bt_DeleteInListRow copy;
		try {
			copy = new LH_P_LIST_bt_DeleteInListRow(this.getTransportHashMap());
			copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
			return copy;
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
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


