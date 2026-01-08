/**
 * rohstoff.businesslogic.bewegung2.detail
 * @author sebastien
 * @date 26.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.detail;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.EAK_BRANCHE;
import panter.gmbh.basics4project.DB_ENUMS.EAK_CODE;
import panter.gmbh.basics4project.DB_ENUMS.EAK_GRUPPE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author sebastien
 * @date 26.03.2019
 *
 */
public class B2_fieldInfoComp_AVVCode extends E2_Grid implements IF_FieldInfo_Component {

	private IF_Field field = null;

	public B2_fieldInfoComp_AVVCode(IF_Field p_field) {
		super();
		this.field = p_field;
	}

	@Override
	public Component get_component(Rec21 r) throws myException {
		this._clear()._bo_no()._s(2);

		long id_eak_code = r.get_myLong_dbVal(field, new MyLong(0)).get_lValue();
		
		if(id_eak_code>0) {
			Rec21 rEakCode =new Rec21(_TAB.eak_code)._fill_id(id_eak_code);
			Rec21 rEakGruppe = rEakCode.get_up_Rec21(EAK_CODE.id_eak_gruppe, EAK_GRUPPE.id_eak_gruppe, false);
			Rec21 rEakBranche = rEakGruppe.get_up_Rec21(EAK_GRUPPE.id_eak_gruppe, EAK_BRANCHE.id_eak_branche, false);

			String key_branche = rEakBranche.get_ufs_dbVal(EAK_BRANCHE.key_branche,"");
			String key_gruppe = rEakGruppe.get_ufs_dbVal(EAK_GRUPPE.key_gruppe,"");
			String key_code = rEakCode.get_ufs_dbVal(EAK_CODE.key_code,"");

			boolean ist_gefaehrlich = rEakCode.get_ufs_dbVal(EAK_CODE.gefaehrlich,"N").equals("Y")?true:false; 

			String AVV_Code = "";

			if(S.isFull(key_branche)) {
				AVV_Code = key_branche+"-";
			}
			if(S.isFull(key_gruppe)) {
				AVV_Code = AVV_Code + key_gruppe+"-";
			}
			AVV_Code = AVV_Code + key_code;

			this._a(new RB_lab()._t(AVV_Code+ (ist_gefaehrlich?"(*)":"") )._lwn() , 			new RB_gld()._ins(2)._left_mid());
			this._a(new RB_lab()._t(rEakCode.get_ufs_dbVal(EAK_CODE.code,"<-->"))._lwn()._i(), 	new RB_gld()._ins(2)._left_mid());
		}
		return this.c();
	}

}
