package panter.gmbh.Echo2.components;

import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;

public class E2_ComponentGroupHorizontal_NG extends MyE2_Grid
{
	private Insets oIN_Basic = new Insets(0, 0, 3, 0);
	private GridLayoutData oGL_Basic = LayoutDataFactory.get_GridLayoutGridLeft(oIN_Basic, null, new Alignment(Alignment.LEFT, Alignment.TOP));

	boolean bFirstElement = true;

	public E2_ComponentGroupHorizontal_NG()
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	}

	public E2_ComponentGroupHorizontal_NG(GridLayoutData GL_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oGL_Basic = GL_Basic;
	}

	public E2_ComponentGroupHorizontal_NG(Insets IN_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oIN_Basic = IN_Basic;
		this.oGL_Basic  = LayoutDataFactory.get_GridLayoutGridLeft(oIN_Basic, null, new Alignment(Alignment.LEFT, Alignment.TOP));
	}


	public E2_ComponentGroupHorizontal_NG(Component oC1)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add_(oC1);
	}

	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add_(oC1);
		this.add_(oC2);
	}

	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, int[] iWidth)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add_(oC1);
		this.add_(oC2);
		
		for (int i=0;i<iWidth.length;i++)
		{
			if (i<this.getSize() && iWidth[i]>0)
			{
				this.setColumnWidth(i,new Extent(iWidth[i]));
			}
		}
		
		
	}

	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, int[] iWidth)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		
		for (int i=0;i<iWidth.length;i++)
		{
			if (i<this.getSize() && iWidth[i]>0)
			{
				this.setColumnWidth(i,new Extent(iWidth[i]));
			}
		}
		
		
	}

	
	
	public E2_ComponentGroupHorizontal_NG(	Component oC1, int iSpalte1, Insets Inset1,
											Component oC2, int iSpalte2, Insets Inset2,
											Component oC3, int iSpalte3, Insets Inset3)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add_(oC1,iSpalte1,Inset1);
		this.add_(oC2,iSpalte2,Inset2);
		this.add_(oC3,iSpalte3,Inset3);
	}

	
	
	
	
	
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
	}

	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, Component oC6)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
		this.add_(oC6);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, Component oC6, Component oC7)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
		this.add_(oC6);
		this.add_(oC7);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, Component oC6, Component oC7, Component oC8)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
		this.add_(oC6);
		this.add_(oC7);
		this.add_(oC8);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, Component oC6, Component oC7, Component oC8, Component oC9)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
		this.add_(oC6);
		this.add_(oC7);
		this.add_(oC8);
		this.add_(oC9);
	}
	
	

	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Insets IN_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oIN_Basic = IN_Basic;
		this.oGL_Basic  = LayoutDataFactory.get_GridLayoutGridLeft(oIN_Basic, null, new Alignment(Alignment.LEFT, Alignment.TOP));
		this.add_(oC1);
	}

	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Insets IN_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oIN_Basic = IN_Basic;
		this.oGL_Basic  = LayoutDataFactory.get_GridLayoutGridLeft(oIN_Basic, null, new Alignment(Alignment.LEFT, Alignment.TOP));
		this.add_(oC1);
		this.add_(oC2);
	}

	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Insets IN_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oIN_Basic = IN_Basic;
		this.oGL_Basic  = LayoutDataFactory.get_GridLayoutGridLeft(oIN_Basic, null, new Alignment(Alignment.LEFT, Alignment.TOP));
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
	}

	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Insets IN_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oIN_Basic = IN_Basic;
		this.oGL_Basic  = LayoutDataFactory.get_GridLayoutGridLeft(oIN_Basic, null, new Alignment(Alignment.LEFT, Alignment.TOP));
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, Insets IN_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oIN_Basic = IN_Basic;
		this.oGL_Basic  = LayoutDataFactory.get_GridLayoutGridLeft(oIN_Basic, null, new Alignment(Alignment.LEFT, Alignment.TOP));
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, Component oC6, Insets IN_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oIN_Basic = IN_Basic;
		this.oGL_Basic  = LayoutDataFactory.get_GridLayoutGridLeft(oIN_Basic, null, new Alignment(Alignment.LEFT, Alignment.TOP));
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
		this.add_(oC6);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, Component oC6, Component oC7, Insets IN_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oIN_Basic = IN_Basic;
		this.oGL_Basic  = LayoutDataFactory.get_GridLayoutGridLeft(oIN_Basic, null, new Alignment(Alignment.LEFT, Alignment.TOP));
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
		this.add_(oC6);
		this.add_(oC7);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, Component oC6, Component oC7, Component oC8, Insets IN_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oIN_Basic = IN_Basic;
		this.oGL_Basic  = LayoutDataFactory.get_GridLayoutGridLeft(oIN_Basic, null, new Alignment(Alignment.LEFT, Alignment.TOP));
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
		this.add_(oC6);
		this.add_(oC7);
		this.add_(oC8);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, Component oC6, Component oC7, Component oC8, Component oC9, Insets IN_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oIN_Basic = IN_Basic;
		this.oGL_Basic  = LayoutDataFactory.get_GridLayoutGridLeft(oIN_Basic, null, new Alignment(Alignment.LEFT, Alignment.TOP));
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
		this.add_(oC6);
		this.add_(oC7);
		this.add_(oC8);
		this.add_(oC9);
	}
	
	
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, GridLayoutData GL_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oGL_Basic = GL_Basic;
		this.add_(oC1);
	}

	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, GridLayoutData GL_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oGL_Basic = GL_Basic;
		this.add_(oC1);
		this.add_(oC2);
	}

	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, GridLayoutData GL_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oGL_Basic = GL_Basic;
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
	}

	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, GridLayoutData GL_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oGL_Basic = GL_Basic;
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, GridLayoutData GL_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oGL_Basic = GL_Basic;
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, Component oC6, GridLayoutData GL_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oGL_Basic = GL_Basic;
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
		this.add_(oC6);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, Component oC6, Component oC7, GridLayoutData GL_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oGL_Basic = GL_Basic;
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
		this.add_(oC6);
		this.add_(oC7);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, Component oC6, Component oC7, Component oC8, GridLayoutData GL_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oGL_Basic = GL_Basic;
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
		this.add_(oC6);
		this.add_(oC7);
		this.add_(oC8);
	}
	
	public E2_ComponentGroupHorizontal_NG(Component oC1, Component oC2, Component oC3, Component oC4, Component oC5, Component oC6, Component oC7, Component oC8, Component oC9, GridLayoutData GL_Basic)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.oGL_Basic = GL_Basic;
		this.add_(oC1);
		this.add_(oC2);
		this.add_(oC3);
		this.add_(oC4);
		this.add_(oC5);
		this.add_(oC6);
		this.add_(oC7);
		this.add_(oC8);
		this.add_(oC9);
	}
	
	
	
	
	
	public void add_(Component oComp)
	{
		if (oComp==null)
		{
			return;
		}
		
		if (!this.bFirstElement)   //beim erstenmal bleibt die size auf 1 
		{
			this.setSize(this.getSize()+1);
		}
		
		this.bFirstElement=false;
		
		if (oComp.getLayoutData()==null || (!(oComp.getLayoutData() instanceof GridLayoutData)))
		{
			oComp.setLayoutData(this.oGL_Basic);
		}
				
		this.add(oComp);
	}

	
	
	
	public void add_(Component oComp, Integer iBreite, Insets oInset)
	{
		if (oComp==null)
		{
			return;
		}
		
		if (!this.bFirstElement)   //beim erstenmal bleibt die size auf 1 
		{
			this.setSize(this.getSize()+1);
		}
		
		this.bFirstElement=false;
		
				
		this.add(oComp);
		if (iBreite !=null) {
			this.setColumnWidth(this.getSize()-1, new Extent(iBreite));
		}
		
		if (oInset!=null) {
			oComp.setLayoutData(LayoutDataFactory.get_GridLayoutGridLeft(oInset, null, new Alignment(Alignment.LEFT, Alignment.CENTER)));
		} else {
			oComp.setLayoutData(LayoutDataFactory.get_GridLayoutGridLeft(this.oIN_Basic, null, new Alignment(Alignment.LEFT, Alignment.CENTER)));
		}
		
	}

	
	
	

	
	
}
