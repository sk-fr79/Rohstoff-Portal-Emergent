package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_GridWithLabel extends MyE2_Grid implements MyE2IF__CanGetStampInfo
{

	private   int        iPixelHeightWhenEmpty = 18;
	
	private   MyE2_Label oLabel = new MyE2_Label(true);
	
	private   MutableStyle oStyleNormal = 		null;
	private   MutableStyle oStyleWithoutText =  null;    //falls border, dann wird mit leerem Text ein Strich angezeigt
	
	
	public MyE2_GridWithLabel(int iWidth)
	{
		super(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add(oLabel,E2_INSETS.I_2_0_2_0);
		this.setWidth(new Extent(iWidth));
		this.oStyleNormal = MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS();
		this.oStyleWithoutText =  MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS();

		//text setzten, damit der Style gezogen wird
		this.set_Text(this.oLabel.getText());
	}
	
	public MyE2_GridWithLabel(int iWidth, MutableStyle oStyle)
	{
		super(1,oStyle);
		this.add(oLabel,E2_INSETS.I_2_0_2_0);
		this.setWidth(new Extent(iWidth));
		this.oStyleNormal=oStyle;
		this.oStyleWithoutText=oStyle;
		
		//text setzten, damit der Style gezogen wird
		this.set_Text(this.oLabel.getText());
	}

	public MyE2_GridWithLabel(int iWidth, MutableStyle oStyleFull, MutableStyle oStyleEmpty, int iHeightWhenEmpty)
	{
		super(1,oStyleFull);
		this.add(oLabel,E2_INSETS.I_2_0_2_0);
		this.setWidth(new Extent(iWidth));
		this.oStyleNormal=oStyleFull;
		this.oStyleWithoutText=oStyleEmpty;
		this.iPixelHeightWhenEmpty = iHeightWhenEmpty;

		//text setzten, damit der Style gezogen wird
		this.set_Text(this.oLabel.getText());
	}

	
	public MyE2_Label get_oLabel()
	{
		return this.oLabel;
	}

	public void set_iWidth(int iWidth)
	{
		this.setWidth(new Extent(iWidth));
	}
	
	public void set_Text(String cText)
	{
		this.oLabel.set_Text(cText);
		if (S.isEmpty(cText))
		{
			this.setStyle(this.oStyleNormal);
			this.setHeight(new Extent(this.iPixelHeightWhenEmpty));
		}
		else
		{
			this.setStyle(this.oStyleNormal);
			this.setHeight(null);
		}
	}
	
	
	public void set_Text(MyString cText)
	{
		this.oLabel.set_Text(cText);
		if (S.isEmpty(cText))
		{
			this.setStyle(this.oStyleNormal);
			this.setHeight(new Extent(this.iPixelHeightWhenEmpty));
		}
		else
		{
			this.setStyle(this.oStyleNormal);
			this.setHeight(null);
		}
	}

	
	public int get_iPixelHeightWhenEmpty()
	{
		return iPixelHeightWhenEmpty;
	}

	public void set_iPixelHeightWhenEmpty(int iPixelHeightWhenEmpty)
	{
		this.iPixelHeightWhenEmpty = iPixelHeightWhenEmpty;
		
		//text setzten, damit der Style gezogen wird
		this.set_Text(this.oLabel.getText());
	}

	public MutableStyle get_oStyleNormal()
	{
		return oStyleNormal;
	}

	public void set_oStyleNormal(MutableStyle oStyleNormal)
	{
		this.oStyleNormal = oStyleNormal;
	}

	public MutableStyle get_oStyleWithoutText()
	{
		return oStyleWithoutText;
	}

	public void set_oStyleWithoutText(MutableStyle oStyleWithoutText)
	{
		this.oStyleWithoutText = oStyleWithoutText;
	}

	@Override
	public String get_STAMP_INFO() throws myException {
		return S.NN(this.oLabel.getText());
	}


	
}
