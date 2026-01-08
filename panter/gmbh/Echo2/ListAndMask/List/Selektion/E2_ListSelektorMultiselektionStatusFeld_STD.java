package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;

public class E2_ListSelektorMultiselektionStatusFeld_STD extends  E2_ListSelektorMultiselektionStatusFeld
{

	// Manfred 2015-05-15: von private->protected um in der abgeleiteten Klasse darauf zugreifen zu können.
	protected int[]   intSpaltenBreitenSelectors = null; 
	
	/**
	 * 
	 * @param CountColumnsInGrid
	 * @param ZeigeBeschriftungAn
	 * @param cBeschriftung
	 * @param oExtBeschriftung
	 */
	public E2_ListSelektorMultiselektionStatusFeld_STD(int CountColumnsInGrid, boolean ZeigeBeschriftungAn, MyE2_String cBeschriftung, Extent oExtBeschriftung )
	{
		super(CountColumnsInGrid,ZeigeBeschriftungAn,cBeschriftung,oExtBeschriftung);
	}

	
	/**
	 * 
	 * @param intColWidths
	 * @param ZeigeBeschriftungAn
	 * @param cBeschriftung
	 * @param oExtBeschriftung
	 */
	public E2_ListSelektorMultiselektionStatusFeld_STD(int[] intColWidths, boolean ZeigeBeschriftungAn, MyE2_String cBeschriftung , Extent oExtBeschriftung )
	{
		super(intColWidths.length,ZeigeBeschriftungAn,cBeschriftung,oExtBeschriftung);
		this.intSpaltenBreitenSelectors = intColWidths;
	}

	
	@Override
	public Component get_oComponentForSelection()
	{
		MyE2_Grid oGridAussen = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		//MyE2_Grid oGridAussen = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		
		MyE2_Grid oGridSelektors = new MyE2_Grid(this.get_iCountColsInGrid(), MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		if (this.get_oExtBeschriftung()==null)
		{
			oGridSelektors.setColumnWidth(0,this.get_oExtBeschriftung());
		}
		
		Insets  oIN = new Insets(0, 1, 3, 1);
		Insets  oIN2 = new Insets(0, 0, 3, 1);
		
		oGridAussen.add(new MyE2_Label(this.get_cBeschriftung()!=null?this.get_cBeschriftung():new MyE2_String("")), MyE2_Grid.LAYOUT_LEFT_TOP(oIN));
		oGridAussen.add(oGridSelektors, MyE2_Grid.LAYOUT_LEFT_TOP(oIN));
		
		if (this.get_oExtBeschriftung()!=null)
		{
			oGridAussen.setColumnWidth(0, this.get_oExtBeschriftung());
		}
		
		for (int i=0;i<this.get_vCheckBoxTypen().size();i++)
		{
			oGridSelektors.add(this.get_vCheckBoxTypen().get(i), MyE2_Grid.LAYOUT_LEFT_TOP(oIN2));
		}

		if (this.intSpaltenBreitenSelectors!=null)
		{
			for (int i=0;i<this.intSpaltenBreitenSelectors.length;i++)
			{
				oGridSelektors.setColumnWidth(i,new Extent(this.intSpaltenBreitenSelectors[i]));
			}
		}
		
		if (this.get_bZeigeBeschriftungAn())
		{
			return oGridAussen;
		}
		else
		{
			return oGridSelektors;
		}
	}

	
	
}
