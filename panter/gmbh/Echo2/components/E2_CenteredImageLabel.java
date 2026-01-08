package panter.gmbh.Echo2.components;



import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.layout.GridLayoutData;

public class E2_CenteredImageLabel extends MyE2_Grid_100_percent 
{

	private MyE2_Label   oLabel = new MyE2_Label();
	
	public E2_CenteredImageLabel() 
	{
		super();
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.setStyle(MyE2_Grid.STYLE_GRID_DDARK_BORDER());

		this.setSize(1);
		
		GridLayoutData oGridLayoutCenter = new GridLayoutData();
		oGridLayoutCenter.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));

		oLabel.setLayoutData(oGridLayoutCenter);
		
		this.add(oLabel);
	}

	
	public E2_CenteredImageLabel(ImageReference oImage) 
	{
		super();
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		this.setSize(1);
		
		GridLayoutData oGridLayoutCenter = new GridLayoutData();
		oGridLayoutCenter.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));

		oLabel.setLayoutData(oGridLayoutCenter);
		
		this.add(oLabel);
		this.set_Image(oImage);
	}


	public E2_CenteredImageLabel(MyE2_Label Label) 
	{
		super();
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		this.oLabel = Label;
		
		this.setSize(1);
		
		GridLayoutData oGridLayoutCenter = new GridLayoutData();
		oGridLayoutCenter.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));

		oLabel.setLayoutData(oGridLayoutCenter);
		
		this.add(oLabel);
	}

	
	
	public void set_Image(ImageReference  oImage)
	{
		this.oLabel.setIcon(oImage);
	}
	
}
