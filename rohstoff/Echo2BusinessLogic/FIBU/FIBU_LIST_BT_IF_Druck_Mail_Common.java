package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.Vector;

import panter.gmbh.Echo2.components.MyE2_Button;

public interface FIBU_LIST_BT_IF_Druck_Mail_Common {

	public boolean mussRechnungAnhaegen();
	
	public Vector<String> getSelectedFibuList();
	
	public Vector<String> getSeletedBuchungNummer();

	public MyE2_Button get_maskContainer();
	
}
