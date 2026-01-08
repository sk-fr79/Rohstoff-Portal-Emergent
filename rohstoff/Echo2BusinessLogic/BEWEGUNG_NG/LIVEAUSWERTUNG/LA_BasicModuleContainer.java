package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LIVEAUSWERTUNG;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class LA_BasicModuleContainer extends Project_BasicModuleContainer
{
	
	/*
	 * grid, in das die wechselnden Sichten eingeblendet werden (einspaltig !!)
	 */
	private MyE2_Grid oGridInnen = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	
	public LA_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_ATOM_LIVELISTE);

		this.add(new LA_Select_Auswertung(this),E2_INSETS.I_0_0_0_0);
		this.add(this.oGridInnen,E2_INSETS.I_0_0_0_0);
		
	}

	
	public void __fuelle_Ansicht(XX_ClassSammler4AuswerteZentrale oDefObject) throws myException
	{
		this.ClearModulToBasic();
		
		if (oDefObject == null)
		{
			throw new myException("Error: Module is null");
		}
		
		oDefObject.INIT_WITH_ComponentMAP(null, E2_MODULNAMES.NAME_MODUL_ATOM_LIVELISTE);
		
		if (oDefObject.get__oListSelectorContainer()!=null)
		{
			this.oGridInnen.add(oDefObject.get__oListSelectorContainer(), E2_INSETS.I_1_1_1_1);
		}
		
		if (oDefObject.get__oListBedienPanelWithButtons()!=null || oDefObject.get__oListSearch()!=null)
		{
			MyE2_Grid oGridHelp = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			int iSpalten = 0;
			if (oDefObject.get__oListBedienPanelWithButtons()!=null)  	{ iSpalten++; }
			if (oDefObject.get__oListSearch()!=null)  					{ iSpalten++; }
			
			oGridHelp.setSize(iSpalten);

			if (oDefObject.get__oListBedienPanelWithButtons()!=null)  	{ oGridHelp.add(oDefObject.get__oListBedienPanelWithButtons(), E2_INSETS.I_0_0_10_0); }
			if (oDefObject.get__oListSearch()!=null)  					{  oGridHelp.add(oDefObject.get__oListSearch(), E2_INSETS.I_0_0_10_0); }
			
			this.oGridInnen.add(oGridHelp, E2_INSETS.I_1_1_1_1);
		}		
		
		this.oGridInnen.add(oDefObject.get_oNaviList(),E2_INSETS.I_10_10_0_0);

		oDefObject.get_oNaviList()._REBUILD_COMPLETE_LIST("");
	}
	
	
	public void ClearModulToBasic()
	{
		this.oGridInnen.removeAll();
	}
}
