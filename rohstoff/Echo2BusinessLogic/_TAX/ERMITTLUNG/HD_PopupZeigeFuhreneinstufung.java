package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class HD_PopupZeigeFuhreneinstufung extends E2_BasicModuleContainer
{
	public HD_PopupZeigeFuhreneinstufung(	HD_WarenBewegungEinstufungen vSingulaereFuhrenEinstufungen, 
											MyE2_String oStringTitel, 
											boolean bSchreibeFuhrenInfo) throws myException
	{
		super();


		MyE2_Grid oGridBase = vSingulaereFuhrenEinstufungen.get(0).get_GridMitFuhrenEinstufung(oStringTitel,bSchreibeFuhrenInfo, null);
		
		
		if (vSingulaereFuhrenEinstufungen.size()>1)
		{
			for (int i=1; i<vSingulaereFuhrenEinstufungen.size();i++)
			{
				oGridBase.append_Grid(vSingulaereFuhrenEinstufungen.get(i).get_GridMitFuhrenEinstufung(null,bSchreibeFuhrenInfo, null), 
						null, MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2));
			}
		}
		this.add(oGridBase, E2_INSETS.I_2_2_2_2);

		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(400), new MyE2_String("Fuhreneinstufungen:"));
	}

}
