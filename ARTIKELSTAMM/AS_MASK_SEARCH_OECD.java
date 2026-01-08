package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.Echo2.components.MaskSearchField.XX_FormaterForFoundButtonsNonDB;
import panter.gmbh.Echo2.components.MaskSearchField.XX_MaskActionAfterFoundNonDB;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_OECD_CODE;
import panter.gmbh.indep.exceptions.myException;

public class AS_MASK_SEARCH_OECD extends MyE2_MaskSearchField
{

	public AS_MASK_SEARCH_OECD()  throws myException
	{
		super(	"ID_OECD_CODE,OECD_CODE,OECD_TEXT ", 
				bibE2.cTO()+".JT_OECD_CODE ", 
				"OECD_CODE", 
				null,
				"UPPER(OECD_CODE) LIKE UPPER('%#WERT#%') OR UPPER(OECD_TEXT) LIKE UPPER('%#WERT#%')",
				null, 
				null,
				"SELECT ID_OECD_CODE,OECD_CODE,SUBSTR(OECD_TEXT,1,250) FROM "+bibE2.cTO()+".JT_OECD_CODE WHERE ID_OECD_CODE=#WERT#", 
				E2_INSETS.I_0_0_2_0, false,250);
		
		this.get_oTextFieldForSearchInput().set_iWidthPixel(100);
		this.get_oTextForAnzeige().setWidth(new Extent(300));
		
		this.set_oMaskActionAfterMaskValueIsFound(new actionAfterFound());

		this.set_FormatterForButtons(new ownFormatter4Buttons());

	}


	
	private class actionAfterFound extends XX_MaskActionAfterFoundNonDB
	{

		@Override
		public void doMaskSettingsAfterValueWrittenInMaskField(String ID_OECD_CODE,  MyE2_MaskSearchField searchField, boolean afterAction) throws myException
		{
			AS_MASK_SEARCH_OECD oSearch_OECD = AS_MASK_SEARCH_OECD.this;

			E2_ComponentMAP  oMap = oSearch_OECD.EXT().get_oComponentMAP();
			
			RECORD_OECD_CODE recZT = new RECORD_OECD_CODE(ID_OECD_CODE);
			
			
			((MyE2_DB_TextField)oMap.get__Comp("EUCODE")).setText(recZT.get_OECD_CODE_cUF_NN(""));
			
			String cHelpText = recZT.get_OECD_TEXT_cUF_NN("");
			((MyE2_DB_TextArea)oMap.get__Comp("EUNOTIZ")).setText(cHelpText);
			
			oSearch_OECD.clean();
		}
		
	}
	
	
	private class ownFormatter4Buttons extends XX_FormaterForFoundButtonsNonDB
	{

		@Override
		public void DO_Format(XX_Button4SearchResultList oButton) 
		{
			String cTextAufButton = oButton.getText();
			if (cTextAufButton.length()>150)
			{
				oButton.setLineWrap(true);
			}
			
			if (cTextAufButton.length()>300)
			{
				oButton.setText(cTextAufButton.substring(0,300)+"...");
				oButton.setToolTipText(cTextAufButton);
			}
			
		}

		@Override
		public void RESET() 
		{
			
		}

//		@Override
//		public Component DO_Transform(MyE2_Button oButton) throws myException
//		{
//			return null;
//		}

		@Override
		public void RESET_ROW(XX_Button4SearchResultList[] buttonsInRow) throws myException
		{
		}
		
	}
	
	
	@Override
	public E2_BasicModuleContainer get_ownContainer() throws myException
	{
		return new ownE2_Container();
	}

	private class ownE2_Container extends E2_BasicModuleContainer
	{
		public ownE2_Container()
		{
			super();
		}
	}
	

}
