package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;

public class FUS_Mask_SHOW_SUM extends MyE2_Grid
{

	private MyE2_Label  oLabelInfo = new MyE2_Label("0,000",MyE2_Label.STYLE_TITEL_BIG());
	
	public FUS_Mask_SHOW_SUM()
	{
		super(1,MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		
		MyE2_Grid oGridInnen = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.add(oGridInnen,E2_INSETS.I_0_0_0_0);
		
		oGridInnen.add(new MyE2_Label(new MyE2_String("Summe aller Positionen")),  MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_10_0));
		oGridInnen.add(this.oLabelInfo,  MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_0_0));
	}

	
	public void set_Text(Object cText)
	{
		
		
		if (cText == null)
		{
			this.oLabelInfo.setText(null);
		}
		else if (cText instanceof String)
		{
			this.oLabelInfo.setText((String)cText);
		} 
		else if (cText instanceof MyString)
		{
			this.oLabelInfo.setText(((MyString)cText).CTrans());
		}
		else
		{
			this.oLabelInfo.setText("@@@ERROR@@@");
		}
		
	}

	
	
}
