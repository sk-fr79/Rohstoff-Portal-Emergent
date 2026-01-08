package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.SELEKTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.indep.dataTools.TERM.is_null;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_STATUS;

public class FZ_LIST_SEL_StatusVektor extends E2_ListSelektorMultiselektionStatusFeld_STD	{

	static int[] CheckBoxSelektorSpaltenBewegungStatus = {70,70};
	

	
	public FZ_LIST_SEL_StatusVektor() throws myException
	{
		super(FZ_LIST_SEL_StatusVektor.CheckBoxSelektorSpaltenBewegungStatus,true,null,new Extent(20));
		
		this.ADD_STATUS_TO_Selector(true,	new And(new vgl(BEWEGUNG_VEKTOR.status, ENUM_VEKTOR_STATUS.AKTIV.db_val())).s(),	
											new MyE2_String(ENUM_VEKTOR_STATUS.AKTIV.user_text()),   new MyE2_String(ENUM_VEKTOR_STATUS.AKTIV.user_text_lang()));

		this.ADD_STATUS_TO_Selector(false,	new And(new vgl(BEWEGUNG_VEKTOR.status, ENUM_VEKTOR_STATUS.FINAL.db_val())).s(),	
											new MyE2_String(ENUM_VEKTOR_STATUS.FINAL.user_text()),   new MyE2_String(ENUM_VEKTOR_STATUS.FINAL.user_text_lang()));
		
		this.ADD_STATUS_TO_Selector(false,	new And(new vgl(BEWEGUNG_VEKTOR.status, ENUM_VEKTOR_STATUS.STORNIERT.db_val())).s(),	
											new MyE2_String(ENUM_VEKTOR_STATUS.STORNIERT.user_text()),   new MyE2_String(ENUM_VEKTOR_STATUS.STORNIERT.user_text_lang()));

		this.ADD_STATUS_TO_Selector(false,	new And(new vgl(BEWEGUNG_VEKTOR.status, ENUM_VEKTOR_STATUS.GEPLANT.db_val())).s(),	
											new MyE2_String(ENUM_VEKTOR_STATUS.GEPLANT.user_text()),   new MyE2_String(ENUM_VEKTOR_STATUS.GEPLANT.user_text_lang()));

		this.ADD_STATUS_TO_Selector(false,	new And(new is_null(BEWEGUNG_VEKTOR.status)).s(),	
											new MyE2_String("undefiniert"),   new MyE2_String("Undefinierter Status des Vektors"));
		
		this.set_cConditionWhenAllIsSelected("1=1");

		
	}
}