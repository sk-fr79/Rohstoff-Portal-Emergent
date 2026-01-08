package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

//standard-uebersetzer der Button-Texte in Sort-Texte
public class TransSTD extends XX_Translate_Buttontext_to_SortText
{

	@Override
	public String TRANSLATE(String cText) throws myException
	{
		return bibALL.get_TRANSLATOR().translate(S.NN(cText));
	}

}
