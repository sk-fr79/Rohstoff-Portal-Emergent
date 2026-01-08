package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN.FUK__INFO_Grid;

public class FU_LIST_INFO_FELD_Kosten extends FUK__INFO_Grid
{

	public FU_LIST_INFO_FELD_Kosten(SQLField osqlField) 	throws myException
	{
		super(osqlField, MyE2_Grid.STYLE_GRID_BORDER(new Border(1,new E2_ColorDDark(),Border.STYLE_SOLID)));
		
		
		
	}

	@Override
	public String get_cID_VPOS_TPA_FUHRE_UF() throws myException
	{
		return this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();	
	}

	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		FU_LIST_INFO_FELD_Kosten oRueck = null;
		
		try
		{
			oRueck = new FU_LIST_INFO_FELD_Kosten(this.EXT_DB().get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("FU_LIST_INFO_FELD_Kosten:get_Copy:copy-error!");
		}

		
		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		oRueck.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oRueck));
		
		return oRueck;
	}

	
}
