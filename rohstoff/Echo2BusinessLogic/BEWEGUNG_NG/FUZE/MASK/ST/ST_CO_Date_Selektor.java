package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.XX_ActionAfterCreateTempFiles;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;

public class ST_CO_Date_Selektor extends RB_date_selektor {
	
	private ENUM_VEKTORPOS_TYP pos_typ;
	
	public ST_CO_Date_Selektor(int i_width_in_px,ENUM_VEKTORPOS_TYP pos_typ) throws myException {
		super(i_width_in_px, true);
		
		this.pos_typ = pos_typ;
		
		this.get_vActionAgentsZusatz().add(new ownActionAgent());
	}

	private class ownActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			ST_CO_Date_Selektor oThis = ST_CO_Date_Selektor.this;
	
			__ST_MASTER_KEY 	mKey = (__ST_MASTER_KEY)((KEY_ATOM)oThis.rb_ComponentMap_this_belongsTo().getOwnMaskKey()).get_root_key();
			oThis.get_oDateFromTextField().get_cDateStandardFormat();
			if(oThis.pos_typ == ENUM_VEKTORPOS_TYP.ST_MAIN_LEFT){
				oThis.rb_ComponentMap_this_belongsTo()
				._find_component_in_neighborhood(mKey.k_vektor_pos_left__atom_right(), BEWEGUNG_ATOM.leistungsdatum.fk())
				.rb_set_db_value_manual(oThis.get_oDateFromTextField().get_cDateStandardFormat());
			}else{
				oThis.rb_ComponentMap_this_belongsTo()
				._find_component_in_neighborhood(mKey.k_vektor_pos_right__atom_left(), BEWEGUNG_ATOM.leistungsdatum.fk())
				.rb_set_db_value_manual(oThis.get_oDateFromTextField().get_cDateStandardFormat());

			}
		}
		
	}

}
