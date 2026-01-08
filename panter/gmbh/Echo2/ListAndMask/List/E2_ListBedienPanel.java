package panter.gmbh.Echo2.ListAndMask.List;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.components.MyE2_Grid;

public class E2_ListBedienPanel extends MyE2_Grid {



	private E2_ListSelectorContainer  	oSelectorBlock = null;
	private MyE2_Grid 			 		oGridButtonLeiste = null;
	
	
	public E2_ListBedienPanel() {
		super(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	}


	/**
	 * Zeigt das bedienpanel in verschiedenen zusammensetzungen
	 * @param bShowListSelector
	 * @param bShowButtonLeiste
	 */
	public void showParts(boolean bShowListSelector, boolean bShowButtonLeiste) {
		this.removeAll();
		
		if (this.oSelectorBlock!=null  && bShowListSelector) {
			this.add(this.oSelectorBlock, E2_INSETS.I_0_0_0_0);
		}
		
		if (this.oGridButtonLeiste!=null  && bShowButtonLeiste) {
			this.add(this.oGridButtonLeiste, E2_INSETS.I_0_0_0_0);
		}
		
	}
	
	
	public E2_ListSelectorContainer get_oListSelectorContainer() {
		return oSelectorBlock;
	}

	public void set_oListSelectorContainer(E2_ListSelectorContainer oSelectorBlock) {
		this.oSelectorBlock = oSelectorBlock;
	}

	public MyE2_Grid get_oGridButtonLeiste() {
		return oGridButtonLeiste;
	}

	public void set_oGridButtonLeiste(MyE2_Grid oGridButtonLeiste) {
		this.oGridButtonLeiste = oGridButtonLeiste;
	}

}
