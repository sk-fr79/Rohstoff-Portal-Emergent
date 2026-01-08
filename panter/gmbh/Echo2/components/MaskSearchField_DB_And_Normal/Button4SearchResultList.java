package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;

import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class Button4SearchResultList extends XX_Button4SearchResultList
{

	
	
	
	@Override
	public String get_SortText() throws myException
	{
		if (this.get_DefSpalteLayout()!=null && this.get_DefSpalteLayout().get_Translator()!=null)
		{
			return this.get_DefSpalteLayout().get_Translator().TRANSLATE(this.getText());
		}
		
		return S.NN(this.getText());
	}

	
	public Button4SearchResultList(String cText)
	{
		super(cText);
	}

	public Button4SearchResultList(MyString cText)
	{
		super(cText);
	}
	
	public Button4SearchResultList(String cText, E2_MutableStyle oStyle)
	{
		super(cText,oStyle);
	}
	
	public Button4SearchResultList(MyString cText, E2_MutableStyle oStyle)
	{
		super(cText,oStyle);
	}




}
