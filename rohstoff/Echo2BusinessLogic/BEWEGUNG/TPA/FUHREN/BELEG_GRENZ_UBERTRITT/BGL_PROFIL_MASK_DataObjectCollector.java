
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MessageTranslator;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS.PROFIL_GRENZUBERTRITT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class BGL_PROFIL_MASK_DataObjectCollector extends RB_DataobjectsCollector_V2 {
	public BGL_PROFIL_MASK_DataObjectCollector() throws myException {
		super();
		this.registerComponent(_TAB.profil_grenzubertritt.rb_km(), new BGL_PROFIL_MASK_DataObject());

		this._addMessageTranslator(new RB_MessageTranslator(
				new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));

	}


	public BGL_PROFIL_MASK_DataObjectCollector(String id_profil_grenzubertritt, MASK_STATUS status) throws myException {
		super();
		this.registerComponent(_TAB.profil_grenzubertritt.rb_km(), new BGL_PROFIL_MASK_DataObject(new Rec20(_TAB.profil_grenzubertritt)._fill_id(id_profil_grenzubertritt),status));

		this._addMessageTranslator(new RB_MessageTranslator(
				new VEK<String>()._a("unique","constraint"),"Es wurde eine Datensatzdublette erkannt."));

	}
	@Override
	public void database_to_dataobject(Object startPoint) throws myException {


	}

	@Override
	public RB_DataobjectsCollector_V2 get_copy() throws myException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv)
			throws myException {

		RB_ComponentMap oMap = this.rb_ComponentMapCollector_ThisBelongsTo().rb_get_ComponentMAP(_TAB.profil_grenzubertritt.rb_km());
		if(((RB_CheckBox)oMap.getRbComponentSavable(PROFIL_GRENZUBERTRITT.std)).rb_readValue_4_dataobject().equals("Y")){

			String get_std_profil_abfrage = new SEL().FROM(_TAB.profil_grenzubertritt)
					.WHERE(new vgl(PROFIL_GRENZUBERTRITT.id_mandant, bibALL.get_ID_MANDANT()))
					.AND(new vgl_YN(PROFIL_GRENZUBERTRITT.std, true)).s();

			RecList21 std_profile_recList = new RecList21(_TAB.profil_grenzubertritt)._fill(get_std_profil_abfrage);

			for(Rec21 std_profile_rec: std_profile_recList) {
				std_profile_rec._nv(PROFIL_GRENZUBERTRITT.std, "N", mv);
				do_collector._addSqlAtEnd(std_profile_rec.get_sql_4_save(true, mv));
			}
		}
	}

	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V2 do_collector,
			MyE2_MessageVector mv) throws myException {

	}
}

