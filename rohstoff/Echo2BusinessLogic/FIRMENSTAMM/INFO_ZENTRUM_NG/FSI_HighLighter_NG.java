package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.indep.S;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.layout.GridLayoutData;

public class FSI_HighLighter_NG
{
	private Component[] arrComponentsInLine = null;
	private TextField   oTFSuchText = null;
	
	public FSI_HighLighter_NG(TextField  TFSuchText, Component[] arrayComponentsInLine)
	{
		super();
		this.arrComponentsInLine=arrayComponentsInLine;
		this.oTFSuchText = TFSuchText;
		
		if (this.bCheckObHighlight())
		{
			for (int i=0;i<this.arrComponentsInLine.length;i++)
			{
				if (this.arrComponentsInLine[i].getLayoutData()!=null && this.arrComponentsInLine[i].getLayoutData() instanceof GridLayoutData)
				{
					GridLayoutData  oGL =((GridLayoutData)this.arrComponentsInLine[i].getLayoutData());
					oGL.setBackground(new E2_ColorLLight());
					this.arrComponentsInLine[i].setLayoutData(oGL);
				}
			}
		}
		else
		{
			for (int i=0;i<this.arrComponentsInLine.length;i++)
			{
				if (this.arrComponentsInLine[i].getLayoutData()!=null && this.arrComponentsInLine[i].getLayoutData() instanceof GridLayoutData)
				{
					GridLayoutData  oGL =((GridLayoutData)this.arrComponentsInLine[i].getLayoutData());
					oGL.setBackground(new E2_ColorBase());
					this.arrComponentsInLine[i].setLayoutData(oGL);
				}
			}
			
		}
		
	}
	
	private boolean bCheckObHighlight()
	{
		boolean bRueck = false;
		
		if (this.oTFSuchText!=null && S.isFull(this.oTFSuchText.getText()))
		{
			for (int i=0;i<this.arrComponentsInLine.length;i++)
			{
				if (this.arrComponentsInLine[i] instanceof Label)
				{
					if (S.isFull(((Label)this.arrComponentsInLine[i]).getText()))
					{
						if (((Label)this.arrComponentsInLine[i]).getText().toUpperCase().indexOf(this.oTFSuchText.getText().trim().toUpperCase())>=0)
						{
							bRueck = true;
							break;
						}
					}
				}
				else if (this.arrComponentsInLine[i] instanceof Button)
				{
					if (S.isFull(((Button)this.arrComponentsInLine[i]).getText()))
					{
						if (((Button)this.arrComponentsInLine[i]).getText().toUpperCase().indexOf(this.oTFSuchText.getText().trim().toUpperCase())>=0)
						{
							bRueck = true;
							break;
						}
					}
				}

			}
		}
		return bRueck;
	}
	
}
