package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;

public interface MyE2IF_IsMarkable
{
	public void make_Look_Deleted(boolean bIsDeleted);
	
	public void setForeColorActive(Color ForeColor);
	public void setFontActive(Font Font);
	
	//public Color get_ForeColor_of_markableComponent();
	
	public Color get_Unmarked_ForeColor();
	public Font get_Unmarked_Font();
	
}

