package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN_NG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLAGENTUR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class FK_MASK_SelectField_Zollagentur extends RB_SelectField implements IF_RB_Component_Savable{
	
	public FK_MASK_SelectField_Zollagentur(IF_Field field) throws myException {
		super(field, new Extent(200));
		String zollQuery = new SEL(ZOLLAGENTUR.kurzbezeichnung).ADDFIELD(ZOLLAGENTUR.id_zollagentur)
				.FROM(_TAB.zollagentur)
				.WHERE(new vgl(ZOLLAGENTUR.id_mandant, bibALL.get_ID_MANDANT())).s();
		
		populateCombobox(zollQuery, null, false, true, false, true);
	}

}
