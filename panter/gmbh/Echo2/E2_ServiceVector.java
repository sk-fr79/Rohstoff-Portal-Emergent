package panter.gmbh.Echo2;

import java.util.Collection;
import java.util.Vector;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.components.MyE2IF__Component;



public class E2_ServiceVector extends Vector<MyE2IF__Component>
{

	public E2_ServiceVector()
	{
		super();
	}

	public E2_ServiceVector(Collection<? extends MyE2IF__Component> c)
	{
		super(c);
	}

	
	public void set_Font(Font oFont)
	{
		for (MyE2IF__Component oComp: this)
		{
			if (oComp instanceof Label)
			{
				((Label)oComp).setFont(oFont);
			}
			if  (oComp instanceof TextField)
			{
				((TextField)oComp).setFont(oFont);
			}
			if  (oComp instanceof TextArea)
			{
				((TextArea)oComp).setFont(oFont);
			}
			if  (oComp instanceof Button)
			{
				((Button)oComp).setFont(oFont);
			}
			
		}
	}

	
	
	public void set_Width(Extent oExt)
	{
		for (MyE2IF__Component oComp: this)
		{
			if (oComp instanceof Label)
			{
				//bei labe nicht moeglich
			}
			if  (oComp instanceof TextField)
			{
				((TextField)oComp).setWidth(oExt);
			}
			if  (oComp instanceof TextArea)
			{
				((TextArea)oComp).setWidth(oExt);
			}
			if  (oComp instanceof Button)
			{
				((Button)oComp).setWidth(oExt);
			}
			
		}
	}

	
	public void set_Height(Extent oExt)
	{
		for (MyE2IF__Component oComp: this)
		{
			if (oComp instanceof Label)
			{
				//bei labe nicht moeglich
			}
			if  (oComp instanceof TextField)
			{
				((TextField)oComp).setHeight(oExt);
			}
			if  (oComp instanceof TextArea)
			{
				((TextArea)oComp).setHeight(oExt);
			}
			if  (oComp instanceof Button)
			{
				((Button)oComp).setHeight(oExt);
			}
			
		}
	}


	
	
	
	
}
