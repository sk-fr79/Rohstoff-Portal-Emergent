package panter.gmbh.indep;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;
import java.math.BigDecimal;

import nextapp.echo2.app.AwtImageReference;
import panter.gmbh.indep.exceptions.myException;


public class LoadImageNT extends Component
{
	private String 				cImagePathAndName = null;
	private Image  				oImage = 			null;
	private int     			i_loadWidth = 0;
	private int     			i_loadHeight = 0;
	
	public LoadImageNT(String ImagePathAndName) throws myException
	{
		super();

		if (new File(ImagePathAndName).exists())
		{
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			this.oImage = toolkit.getImage(ImagePathAndName);
			
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(this.oImage, 0);
			try {
				mt.waitForAll();
				
				this.i_loadWidth = this.oImage.getWidth(null);
				this.i_loadHeight = this.oImage.getHeight(null);
				
				//DEBUG.System_println("Image groesse : "+this.i_loadWidth+" .... "+this.isLightweight());

			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new myException(this,"Error loading image "+ImagePathAndName);
			}
			
		}
		else
		{
			throw new myException("file: "+ImagePathAndName+" was not found ...");
		}
	}

	
	public AwtImageReference get_ImageRef() throws myException  {
		if (this.oImage!=null) {
			return new AwtImageReference(this.oImage);
		}
		throw new myException(this,"Error loading image (2) "+this.cImagePathAndName);
	}


	/**
	 * 
	 * @param i_width_maximum  wenn das bild groesser ist, dann wird es skaliert
	 * @return
	 * @throws myException
	 */
	public AwtImageReference get_ImageRef(int i_width_maximum) throws myException  {
		if (this.oImage!=null) {
			if (this.i_loadWidth>i_width_maximum) {
				double scaler = ((double)this.i_loadWidth)/((double)i_width_maximum);
				
				double new_hight = ((double)this.i_loadHeight)/scaler;
				
				int intNewHight = new BigDecimal(new_hight).intValue();
				
				Image imgNew = this.oImage.getScaledInstance(i_width_maximum, intNewHight, Image.SCALE_SMOOTH);
				
				return new AwtImageReference(imgNew);
			}
			
			return new AwtImageReference(this.oImage);
		}
		throw new myException(this,"Error loading image (2) "+this.cImagePathAndName);
	}
	
	public String get_cImagePathAndName() 
	{
		return cImagePathAndName;
	}
	
	
}
