package panter.gmbh.indep;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import panter.gmbh.indep.exceptions.myException;

import nextapp.echo2.app.AwtImageReference;


public class LoadImage
{
	private String 				cImagePathAndName = null;
	private AwtImageReference   oImageRef = 		null;  
	
	public LoadImage(String ImagePathAndName) throws myException
	{
		super();

		if (new File(ImagePathAndName).exists())
		{
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image   oImage = toolkit.getImage(ImagePathAndName);
			this.oImageRef = new AwtImageReference(oImage);
		}
		else
		{
			throw new myException("file: "+ImagePathAndName+" was not found ...");
		}
	}

	
	public AwtImageReference get_ImageRef() 
	{
		return oImageRef;
	}

	
	public String get_cImagePathAndName() 
	{
		return cImagePathAndName;
	}
}
