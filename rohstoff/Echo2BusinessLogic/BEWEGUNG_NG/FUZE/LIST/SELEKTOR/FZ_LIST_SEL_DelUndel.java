package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.SELEKTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;

class FZ_LIST_SEL_DelUndel extends E2_ListSelektorMultiselektionStatusFeld_STD	{
	
	private static int[] CheckBoxSelektorSpaltenDelStatus = {85,85};

	public FZ_LIST_SEL_DelUndel() throws myException
	{
		super(FZ_LIST_SEL_DelUndel.CheckBoxSelektorSpaltenDelStatus,true,new MyE2_String(""),new Extent(20));
		
		this.ADD_STATUS_TO_Selector(true,	new And(new vgl(BEWEGUNG_VEKTOR.deleted, "N")).s(),	
											new MyE2_String("Lebende"),   new MyE2_String("Zeige Vektoren die NICHT löschmarkiert sind"));
		
		this.ADD_STATUS_TO_Selector(false,	new And(new vgl(BEWEGUNG_VEKTOR.deleted, "Y")).s(),	
											new MyE2_String("Gelöschte"),   new MyE2_String("Zeige Vektoren die löschmarkiert sind"));
		
		this.set_cConditionWhenAllIsSelected("1=1");

	}
}