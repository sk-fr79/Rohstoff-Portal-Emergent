package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;

import java.util.Vector;

import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;


//kann den klassen MyE2_MaskSearchField und MyE2_DB_MaskSearchField uebergeben werden und 
// liefert eigenen Vector<MyE2_Button[]> statt des XX_SearchBlock
public abstract class XX_Alternative_Result_Button_Generator
{

	public abstract Vector<XX_Button4SearchResultList[]> get_vResultButtons() throws myException;
	
	public abstract boolean get_bUseAlternative() throws myException;
	
}
