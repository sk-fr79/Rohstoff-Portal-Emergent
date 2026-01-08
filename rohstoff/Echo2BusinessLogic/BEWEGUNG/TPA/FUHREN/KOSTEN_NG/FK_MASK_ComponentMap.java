
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN_NG;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_Label;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FUHREN_KOSTEN_TYP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class FK_MASK_ComponentMap extends RB_ComponentMap {

	public FK_MASK_ComponentMap() throws myException {
		super();
		
		FK_MASK_SelectField_Kostentyp selectKostenTyp = new FK_MASK_SelectField_Kostentyp(VPOS_TPA_FUHRE_KOSTEN.id_fuhren_kosten_typ);
		selectKostenTyp.add_oActionAgent(new actionSelectKostenTyp());	
				
		this.registerComponent(VPOS_TPA_FUHRE_KOSTEN.id_vpos_tpa_fuhre_kosten, 	new RB_Label(VPOS_TPA_FUHRE_KOSTEN.id_vpos_tpa_fuhre_kosten));
		this.registerComponent(VPOS_TPA_FUHRE_KOSTEN.id_vpos_tpa_fuhre, 			new RB_Label(VPOS_TPA_FUHRE_KOSTEN.id_vpos_tpa_fuhre));

		this.registerComponent(VPOS_TPA_FUHRE_KOSTEN.id_fuhren_kosten_typ, 		selectKostenTyp);
		this.registerComponent(VPOS_TPA_FUHRE_KOSTEN.id_zollagentur, 				new FK_MASK_SelectField_Zollagentur(VPOS_TPA_FUHRE_KOSTEN.id_zollagentur));

//		this.rb_register(VPOS_TPA_FUHRE_KOSTEN.ist_preis_pro_tonne, 		new RB_CheckBox(VPOS_TPA_FUHRE_KOSTEN.ist_preis_pro_tonne));

		this.registerComponent(VPOS_TPA_FUHRE_KOSTEN.betrag_kosten, 				new RB_TextField(200));
		//this.rb_register(VPOS_TPA_FUHRE_KOSTEN.anzahl, 						new RB_TextField());
		//this.rb_register(VPOS_TPA_FUHRE_KOSTEN.einzelpreis, 				new RB_TextField());

		this.registerComponent(VPOS_TPA_FUHRE_KOSTEN.datum_beleg, 				new RB_date_selektor(200, true));
		this.registerComponent(VPOS_TPA_FUHRE_KOSTEN.fremdbeleg_nummer, 			new RB_TextField(400));
		this.registerComponent(VPOS_TPA_FUHRE_KOSTEN.name_spedition,				new RB_TextArea(400,5));
		this.registerComponent(VPOS_TPA_FUHRE_KOSTEN.info_kosten, 				new RB_TextArea(400,5));
		this.registerComponent(VPOS_TPA_FUHRE_KOSTEN.beleg_vorhanden, 			new RB_CheckBox(VPOS_TPA_FUHRE_KOSTEN.beleg_vorhanden));


		/*this.rb_register(VPOS_TPA_FUHRE_KOSTEN.deleted, 					new RB_CheckBox(VPOS_TPA_FUHRE_KOSTEN.deleted));
		this.rb_register(VPOS_TPA_FUHRE_KOSTEN.del_date, 					new RB_TextField(100));
		this.rb_register(VPOS_TPA_FUHRE_KOSTEN.del_grund, 					new RB_TextField(400));
		this.rb_register(VPOS_TPA_FUHRE_KOSTEN.del_kuerzel, 				new RB_TextField(100));
		this.rb_register(VPOS_TPA_FUHRE_KOSTEN.id_adresse_spedition, 		new RB_TextField());*/
	}

	@Override
	public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {

		return null;
	}

	@Override
	public MyE2_MessageVector maskSettings_do_After_Load() throws myException {

		return null;
	}

	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {

	}

	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer, MASK_STATUS status) throws myException {

		MyE2_MessageVector omv = new MyE2_MessageVector();
		
		preSettingsContainer.rb_get(VPOS_TPA_FUHRE_KOSTEN.betrag_kosten).set_MustField(true);
		preSettingsContainer.rb_get(VPOS_TPA_FUHRE_KOSTEN.info_kosten).set_MustField(true);
		
		return omv;
	}
	
	private class actionSelectKostenTyp extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FK_MASK_SelectField_Kostentyp 	oSelectTyp = 	(FK_MASK_SelectField_Kostentyp)oExecInfo.get_MyActionEvent().getSource();
			FK_MASK_ComponentMap       		oMAP = 			(FK_MASK_ComponentMap)oSelectTyp.EXT().get_oComponentMAP();

			RECORD_FUHREN_KOSTEN_TYP  recKostenTyp = null;
			if (S.isFull(oSelectTyp.get_ActualWert()))
			{
				recKostenTyp = new RECORD_FUHREN_KOSTEN_TYP(bibALL.ReplaceTeilString(oSelectTyp.get_ActualWert(),".",""));
			}
			
			if (recKostenTyp==null || recKostenTyp.is_BETRIFFT_ZOLL_NO())
			{
				oMAP.set_ActiveADHOC(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ID_ZOLLAGENTUR, 	false, true);
			}
			else
			{
				oMAP.set_ActiveADHOC(RECORD_VPOS_TPA_FUHRE_KOSTEN.FIELD__ID_ZOLLAGENTUR, 	true, true);
			}
			
		}
	}
}
