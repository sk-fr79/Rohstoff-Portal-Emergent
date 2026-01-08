package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST;


import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.QUERYAGENT_MarkiereRowsNG;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class FZ_LIST_FORMATING_Agent_showStorno extends QUERYAGENT_MarkiereRowsNG  {

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 	{
	}


	@Override
	public boolean check_if_rowIsMarked(E2_ComponentMAP oMAP,	SQLResultMAP oUsedResultMAP) throws myException {
		RECORD_BEWEGUNG_VEKTOR_SPEC recVekt = (RECORD_BEWEGUNG_VEKTOR_SPEC)oMAP.get_Record4MainTable();
		
		if (recVekt.is_DELETED_NO() && recVekt.is_Storniert()) {
			return true;
		}
		
		return false;
	}

	@Override
	public Color get_colorUnmarked() throws myException {
		return Color.BLACK;
	}

	@Override
	public Color get_colorMarked() throws myException {
		return new E2_ColorGray(80);
	}

}
