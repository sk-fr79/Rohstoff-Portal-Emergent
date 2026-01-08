package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.indep.bibALL;

public enum FZ_TEXT_SIZE {
	SMALL(-2)
	,NORMAL(0)
	,BIG(2)
	;
	private int size_index = 0;

	private FZ_TEXT_SIZE(int i_size_index) {
		this.size_index = i_size_index;
	}

	public E2_Font get_font(){
		return new E2_FontPlain(this.size_index);
	}
	
	public int getSize(){
		return bibALL.get_FONT_SIZE()+size_index;
	}

}
