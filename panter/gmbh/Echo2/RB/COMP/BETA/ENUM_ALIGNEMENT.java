package panter.gmbh.Echo2.RB.COMP.BETA;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_ALIGNEMENT implements IF_enum_4_db{
	LEFT_TOP		("LEFT_TOP", 		E2_ALIGN.LEFT_TOP),
	LEFT_MID 		("LEFT_MID", 		E2_ALIGN.LEFT_MID),
	LEFT_BOTTOM 	("LEFT_BOTTOM", 	E2_ALIGN.LEFT_BOTTOM),
	CENTER_TOP 		("CENTER_TOP", 		E2_ALIGN.CENTER_TOP),
	CENTER_MID 		("CENTER_MID", 		E2_ALIGN.CENTER_MID),
	CENTER_BOTTOM 	("CENTER_BOTTOM",	E2_ALIGN.CENTER_BOTTOM),
	RIGHT_TOP 		("RIGHT_TOP", 		E2_ALIGN.RIGHT_TOP),
	RIGHT_MID 		("RIGHT_MID", 		E2_ALIGN.RIGHT_MID),
	RIGHT_BOTTOM	("RIGHT_BOTTOM", 	E2_ALIGN.RIGHT_BOTTOM)
	;

	private String 	value = "";
	private Alignment align;
	
	private ENUM_ALIGNEMENT(String val_4_db, Alignment p_align) {
		this.align = p_align;
		this.value = val_4_db;
	}
	
	@Override
	public String db_val() {
		return value;
	}

	@Override
	public String user_text() {
		return value;
	}

	@Override
	public String user_text_lang() {
		return value;
	}

	public Alignment get_alignment() throws myException{
		return align;
	}
	
	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_MASKNAME.values(), emptyPairInFront);
	}
}
