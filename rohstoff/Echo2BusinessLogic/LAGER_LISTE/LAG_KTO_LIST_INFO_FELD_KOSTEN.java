package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN.FUK__INFO_Grid;

public class LAG_KTO_LIST_INFO_FELD_KOSTEN extends FUK__INFO_Grid
{

	public LAG_KTO_LIST_INFO_FELD_KOSTEN(SQLField osqlField)throws myException
	{
		super(osqlField, MyE2_Grid.STYLE_GRID_BORDER(new Border(1,new E2_ColorDDark(),Border.STYLE_SOLID)));
	}

	@Override
	public String get_cID_VPOS_TPA_FUHRE_UF() throws myException
	{
		String cID_VPOS_TPA_FUHRE = null;
		
		String cID_LAGER_KTO = this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
		
		RECORD_LAGER_KONTO  recLagerKto = new RECORD_LAGER_KONTO(cID_LAGER_KTO);
		
		if (recLagerKto.get_ID_VPOS_TPA_FUHRE_lValue(new Long(-1))>0)
		{
			cID_VPOS_TPA_FUHRE = recLagerKto.get_ID_VPOS_TPA_FUHRE_cUF();
		}
		return cID_VPOS_TPA_FUHRE;
	}

	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		LAG_KTO_LIST_INFO_FELD_KOSTEN oRueck = null;
		
		try
		{
			oRueck = new LAG_KTO_LIST_INFO_FELD_KOSTEN(this.EXT_DB().get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("LAG_KTO_LIST_INFO_FELD_KOSTEN:get_Copy:copy-error!");
		}

		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		oRueck.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oRueck));
		
		return oRueck;
	}

	
}
