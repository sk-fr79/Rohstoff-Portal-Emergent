package panter.gmbh.Echo2.components;


import nextapp.echo2.app.Color;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorFortschrittsbalken;

public class E2_FortsschrittsBalken extends MyE2_Row
{

	private int iMAX = 0;
	private int iValue = 0;
	
	private int iAnzahlSegmente = 20;
	
	private Color  oColorSegmente = new E2_ColorFortschrittsbalken();

	private MutableStyle oStyleSegFull = null;
	private MutableStyle oStyleSegLeer = null;

	private MyE2_Row     oRowInnen = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());;          //row fuer die eigentliche anzeige
	 
	public E2_FortsschrittsBalken(int imax, int anzahlSegmente,Color colorSegmente)
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());

		if (colorSegmente!=null) this.oColorSegmente = colorSegmente;

		this.add(this.oRowInnen);
		
		this.iMAX = 				imax;
		this.iAnzahlSegmente = 		anzahlSegmente;
	
		this.oStyleSegFull = this.get_StyleSegmentFull();
		this.oStyleSegLeer = this.get_StyleSegmentEmpty();
		
		this.fuelle(false);
	}
	
	
	public void set_Wert(int iWert)
	{
		this.iValue = (iWert<=(iMAX-1)?iWert:iMAX);
		if (this.iValue<0) this.iValue = 0;
		
		this.fuelle((iValue>=iMAX));
	}


	private void fuelle(boolean bFull)
	{
		this.oRowInnen.removeAll();
		double dValue = (((double)this.iValue)/((double)this.iMAX))*this.iAnzahlSegmente;

		for (int i=0;i<this.iAnzahlSegmente;i++)
		{
			MyE2_Row oRow = new MyE2_Row();
			oRow.add(new MyE2_Label(E2_ResourceIcon.get_RI("empty10.png")),E2_INSETS.I_1_1_1_1);
			
			if (bFull)
			{
				oRow.setStyle(oStyleSegFull);
			}
			else
			{
				if (i<(int)dValue)
				{
					oRow.setStyle(oStyleSegFull);
				}
				else
				{
					oRow.setStyle(oStyleSegLeer);
				}
			}
			this.oRowInnen.add(oRow,E2_INSETS.I_1_1_1_1);
		}
	}
	
	
	
	
	private MutableStyle get_StyleSegmentFull()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Row.PROPERTY_BACKGROUND, this.oColorSegmente);
		oStyle.setProperty(Row.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		return oStyle;
	}
	
	private MutableStyle get_StyleSegmentEmpty()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty(Row.PROPERTY_BACKGROUND, null);
		oStyle.setProperty(Row.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);
		return oStyle;
	}
	
	
	
	
}
