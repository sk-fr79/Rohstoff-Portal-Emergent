package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_FormaterForFoundButtons;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterPrepareContentForNew;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZOLLTARIFNUMMER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class AS_MASK_SEARCH_ZOLLNUMMER extends MyE2_DB_MaskSearchField
{

	public AS_MASK_SEARCH_ZOLLNUMMER(SQLField 		osqlField)  throws myException
	{
		super(   osqlField,	
				" ID_ZOLLTARIFNUMMER,NUMMER,TEXT1||' '||NVL(TEXT2,'')||' '||NVL(TEXT3,'')", 
				bibE2.cTO()+".JT_ZOLLTARIFNUMMER ", 
				"NUMMER ", 
				null,
				"TO_CHAR(ID_ZOLLTARIFNUMMER)='#WERT#' OR TO_CHAR(ID_ZOLLTARIFNUMMER,'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.''')='#WERT#' OR UPPER(NUMMER) LIKE UPPER('%#WERT#%') OR UPPER(TEXT1) LIKE UPPER('%#WERT#%') OR UPPER(TEXT2) LIKE UPPER('%#WERT#%') OR  UPPER(TEXT3) LIKE UPPER('%#WERT#%')",
				null, 
				null,
				"SELECT NUMMER||CASE WHEN NVL(REVERSE_CHARGE,'N')='Y' THEN ' <RC>' ELSE ' ' END||':  '||SUBSTR(TEXT1,1,250) FROM "+bibE2.cTO()+".JT_ZOLLTARIFNUMMER WHERE ID_ZOLLTARIFNUMMER=#WERT#", 
				E2_INSETS.I_0_0_2_0, 
				false);
		
		this.get_oTextFieldForSearchInput().set_iWidthPixel(100);
		this.get_oTextForAnzeige().setWidth(new Extent(300));
		
		this.set_oMaskActionAfterMaskValueIsFound(new actionAfterFound());
		this.set_oMaskActionAfterPrepareContentForNew(new ownActionAfterPrepareContentNew());
		
		this.set_FormatterForButtons(new ownFormatter4Buttons());
		
	}


	
	private class actionAfterFound extends XX_MaskActionAfterFound
	{

		@Override
		public void doMaskSettingsAfterValueWrittenInMaskField(String ID_ZOLLTARIFNUMMER, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException
		{
			if (bAfterAction)
			{
			
				AS_MASK_SEARCH_ZOLLNUMMER oSearchZoll = AS_MASK_SEARCH_ZOLLNUMMER.this;
	
				E2_ComponentMAP  oMap = oSearchZoll.EXT().get_oComponentMAP();
				
				RECORD_ZOLLTARIFNUMMER recZT = new RECORD_ZOLLTARIFNUMMER(bibALL.ReplaceTeilString(ID_ZOLLTARIFNUMMER,".",""));
				
				
				((MyE2_DB_TextField)oMap.get__Comp("ZOLLTARIFNR")).setText(recZT.get_NUMMER_cUF_NN(""));
				
				String cHelpText = recZT.get_TEXT1_cUF_NN("");
				if (S.isFull(recZT.get_TEXT2_cUF_NN("")))
				{
					cHelpText += ("\n"+recZT.get_TEXT2_cUF_NN(""));
				}
				if (S.isFull(recZT.get_TEXT3_cUF_NN("")))
				{
					cHelpText += ("\n"+recZT.get_TEXT3_cUF_NN(""));
				}
				
				//den Textblock nur fuellen, wenn noch nichts drinsteht (sonst werden evtl. uebersetzungen geloescht)
				if (S.isEmpty(((MyE2_DB_TextArea)oMap.get__Comp("ZOLLTARIFNOTIZ")).getText()))
				{
					((MyE2_DB_TextArea)oMap.get__Comp("ZOLLTARIFNOTIZ")).setText(cHelpText);
				}
				
				//dem suchfeld einen tooltip mit dem kompletten gefundenen text uebergeben
				oSearchZoll.get_oTextFieldForSearchInput().setToolTipText(new MyE2_String("Original-Wortlaut:").CTrans()+"\n"+cHelpText);
				oSearchZoll.get_oTextForAnzeige().setToolTipText(new MyE2_String("Original-Wortlaut:").CTrans()+"\n"+cHelpText);
				
			}
		}
	}
	
	
	private class ownActionAfterPrepareContentNew extends XX_MaskActionAfterPrepareContentForNew
	{

		@Override
		public void doMaskSettingsAfterPrepareContentForNew(MyE2_DB_MaskSearchField oDB_Searchfield, boolean bSetDefaults) throws myException 
		{
			E2_ComponentMAP  oMap = oDB_Searchfield.EXT().get_oComponentMAP();
			
			((MyE2_DB_TextField)oMap.get__Comp("ZOLLTARIFNR")).setText("");
			((MyE2_DB_TextArea)oMap.get__Comp("ZOLLTARIFNOTIZ")).setText("");

		}
		
	}
	
	
	
	private class ownFormatter4Buttons extends XX_FormaterForFoundButtons
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
//		public Component DO_Transform(MyE2_Button oButton) throws myException {
//			return null;
//		}

		@Override
		public void RESET_ROW(XX_Button4SearchResultList[] buttonsInRow) throws myException {
			
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
