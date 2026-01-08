package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import nextapp.echo2.app.Font;
import nextapp.echo2.app.LayoutData;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZOLLTARIFNUMMER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class BS__DB_TextFieldZolltarifNummer extends MyE2_DB_TextField
{

	public BS__DB_TextFieldZolltarifNummer(SQLField osqlField, boolean bAutoAlign, int iWidthInPixel, int iMaxInputSize, boolean bDisabledFromBasic, Font oFont) throws myException
	{
		super(osqlField, bAutoAlign, iWidthInPixel, iMaxInputSize, bDisabledFromBasic, oFont);
	}

	public BS__DB_TextFieldZolltarifNummer(SQLField osqlField, boolean bAutoAlign, int iWidthInPixel, int iMaxInputSize, boolean bDisabledFromBasic) throws myException
	{
		super(osqlField, bAutoAlign, iWidthInPixel, iMaxInputSize, bDisabledFromBasic);
	}

	public BS__DB_TextFieldZolltarifNummer(SQLField osqlField, boolean bAutoAlign, int iWidthInPixel, LayoutData oLayout, Font oFont) throws myException
	{
		super(osqlField, bAutoAlign, iWidthInPixel, oLayout, oFont);
	}

	public BS__DB_TextFieldZolltarifNummer(SQLField osqlField, boolean bAutoAlign, int iWidthInPixel, LayoutData oLayout) throws myException
	{
		super(osqlField, bAutoAlign, iWidthInPixel, oLayout);
	}

	public BS__DB_TextFieldZolltarifNummer(SQLField osqlField, boolean bAutoAlign, int iWidthInPixel) throws myException
	{
		super(osqlField, bAutoAlign, iWidthInPixel);
	}

	public BS__DB_TextFieldZolltarifNummer(SQLField osqlField) throws myException
	{
		super(osqlField);
	}

	
	public void setText(String cText)
	{
		super.setText(cText);
		
		this.setToolTipText("");    //reset
		
		try
		{
			if (S.isFull(cText))
			{
				RECORD_ZOLLTARIFNUMMER  recZollTarif = new RECORD_ZOLLTARIFNUMMER("NUMMER="+bibALL.MakeSql(cText));
				if (recZollTarif!=null)
				{
					String cToolText = recZollTarif.get_TEXT1_cUF_NN("")+"\n"+recZollTarif.get_TEXT2_cUF_NN("")+"\n"+recZollTarif.get_TEXT3_cUF_NN("")+
					(recZollTarif.is_REVERSE_CHARGE_YES()?"\n <REVERSE CHARGE>":"");
					this.setToolTipText(cToolText);
				}
			}
		}
		catch (myException e)
		{
			this.setToolTipText("<Zolltariftext not found>");
			e.printStackTrace();
		}
		
		
	}
	
	
}
