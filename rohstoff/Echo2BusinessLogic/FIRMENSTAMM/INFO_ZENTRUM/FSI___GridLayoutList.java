package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;

public class FSI___GridLayoutList extends GridLayoutData
{
	
	/**
	 * 
	 * @param iLeftCenterRight_012
	 * @param BackColor
	 */
	public FSI___GridLayoutList(int iLeftCenterRight_012, Color  BackColor)
	{
		super();
		
		switch (iLeftCenterRight_012)
		{
			case 0:
			{
				this.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
				break;
			}
			case 1:
			{
				this.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
				break;
			}
			case 2:
			{
				this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
				break;
			}
		}
		
		this.setBackground(BackColor);
		this.setInsets(new Insets(1, 2, 2, 2));

	}

}
