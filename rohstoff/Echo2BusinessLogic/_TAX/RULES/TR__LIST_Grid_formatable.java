package rohstoff.Echo2BusinessLogic._TAX.RULES;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_ComponentHelper;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentGrid;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


public class TR__LIST_Grid_formatable extends MyE2_DB_MultiComponentGrid  {

	private int  		f_cols = 0;
	private Integer	  	f_colWidth = null;
	
	public TR__LIST_Grid_formatable(int icols, Integer iColWith0) {
		super(icols, iColWith0);
		this.f_cols = icols;
		this.f_colWidth = iColWith0;
	}

	

	
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		TR__LIST_Grid_formatable oRueck = new TR__LIST_Grid_formatable(this.f_cols, this.f_colWidth);
		
		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		oRueck.setStyle(this.getStyle());
		
		for (int i=0;i<this.get_vComponents().size();i++)
		{
			if (this.get_vComponents().get(i) instanceof E2_IF_Copy)
			{
				
				Component oCom = (Component)this.get_vComponents().get(i);
				Component oCompCopy = (Component)((E2_IF_Copy)oCom).get_Copy(null);
				
				try
				{

					//darf erst hier zugefuegt werden, da im falle eines MyE2IF__Indirect-komponente hier die Layout-Datas umdefiniert werden
					E2_NavigationList_ComponentHelper.set_GRID_LayoutData_InList(oRueck);
					oRueck.add_Component((MyE2IF__Component)oCompCopy, ((MyE2IF__Component)oCompCopy).EXT().get_cList_or_Mask_Titel(), ((MyE2IF__Component)oCompCopy).EXT().get_C_HASHKEY());

					oCompCopy.setVisible(oCom.isVisible());

				}
				catch (myException ex)
				{
					ex.printStackTrace();
					throw new myExceptionCopy(myExceptionCopy.ERROR_COPYING+" ->> "+ex.get_ErrorMessage().get_cMessage().COrig());
				}
				
			}
		}
		

		//aenderung 20101020: 
		//jetzt die columnwidth anpassen, damit die ueberschriften und die spalteninhalte einigermassen gleich sind
		for (int i=0;i<this.getSize();i++)
		{
			if (this.getColumnWidth(i)!=null)
			{
				oRueck.setColumnWidth(i, this.getColumnWidth(i));
			}
		}
		
		
		oRueck.set_bComponentIsPassive(this.get_bComponentIsPassive());
		
		
		return oRueck;
	}
}
