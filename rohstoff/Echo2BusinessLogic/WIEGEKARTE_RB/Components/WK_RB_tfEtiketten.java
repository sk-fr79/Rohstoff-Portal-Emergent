package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.exceptions.myException;

public class WK_RB_tfEtiketten extends RB_TextField {


	
	public WK_RB_tfEtiketten() {
		// TODO Auto-generated constructor stub
		_al_right();
	}

	public WK_RB_tfEtiketten(int i_width) {
		super(i_width);
	}

	public WK_RB_tfEtiketten(int i_width, int i_max_input_size) {
		super(i_width, i_max_input_size);
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	}

	@Override
	public String rb_readValue_4_dataobject()  {
		return "";
	}
	

}
