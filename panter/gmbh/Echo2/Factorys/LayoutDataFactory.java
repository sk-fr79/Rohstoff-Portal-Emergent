package panter.gmbh.Echo2.Factorys;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.indep.exceptions.myException;

public class LayoutDataFactory
{

	public static GridLayoutData 	get_GridLayoutGridCenter(Insets oInsets, Color oColor, Alignment oAlignment)						
	{
		GridLayoutData oRueck = new GridLayoutData(); 
		if (oAlignment==null)
			oRueck.setAlignment(new Alignment(Alignment.CENTER,Alignment.DEFAULT));
		else
			oRueck.setAlignment(oAlignment);
		
		if (oInsets != null)
			oRueck.setInsets(oInsets);
		
		if (oColor != null)
			oRueck.setBackground(oColor);
		
		return oRueck;	
	}

	
	public static GridLayoutData 	get_GridLayoutGridLeft(Insets oInsets, Color oColor, Alignment oAlignment)				
	{
		GridLayoutData oRueck = new GridLayoutData(); 

		if (oAlignment==null)
			oRueck.setAlignment(new Alignment(Alignment.LEFT,Alignment.DEFAULT));
		else
			oRueck.setAlignment(oAlignment);

		
		if (oInsets != null)
			oRueck.setInsets(oInsets);
		if (oColor != null)
			oRueck.setBackground(oColor);

		return oRueck;	
	}

	
	public static GridLayoutData 	get_GridLayout(Insets oInsets, Color oColor, Alignment oAlignment, Integer iColSpan)				
	{
		GridLayoutData oRueck = new GridLayoutData(); 

		if (oAlignment==null)
		{
			oRueck.setAlignment(new Alignment(Alignment.LEFT,Alignment.DEFAULT));
		}
		else
		{
			oRueck.setAlignment(oAlignment);
		}

		
		if (oInsets != null)
		{
			oRueck.setInsets(oInsets);
		}

		if (oColor != null)
		{
			oRueck.setBackground(oColor);
		}

		if (iColSpan!=null)
		{
			oRueck.setColumnSpan(iColSpan);
		}
		
		
		return oRueck;	
	}


	
	
	public static GridLayoutData 	get_GridLayoutGridRight(Insets oInsets, Color oColor, Alignment oAlignment)						
	{
		GridLayoutData oRueck = new GridLayoutData(); 

		if (oAlignment==null)
			oRueck.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
		else
			oRueck.setAlignment(oAlignment);

		
		if (oInsets != null)
			oRueck.setInsets(oInsets);
		if (oColor != null)
			oRueck.setBackground(oColor);
		return oRueck;	
	}

	public static GridLayoutData 	get_GridLayoutGridCenter(Insets oInsets)				
	{
		return LayoutDataFactory.get_GridLayoutGridCenter(oInsets,null, null);	
	}
	

	public static GridLayoutData 	get_GridLayoutGridCenter(Insets oInsets, int iColSpan)						
	{
		GridLayoutData oGL_Rueck = LayoutDataFactory.get_GridLayoutGridCenter(oInsets,null, null);	
		oGL_Rueck.setColumnSpan(iColSpan);
		return oGL_Rueck;	
	}

	
	public static GridLayoutData 	get_GridLayoutGridCenter_TOP(Insets oInsets)				
	{
		return LayoutDataFactory.get_GridLayoutGridCenter(oInsets,null, new Alignment(Alignment.CENTER,Alignment.TOP));	
	}
	
	public static GridLayoutData 	get_GridLayoutGridCenter_DARK_TOP(Insets oInsets)						
	{
		return LayoutDataFactory.get_GridLayoutGridRight(oInsets,new E2_ColorDark(), new Alignment(Alignment.CENTER,Alignment.TOP));	
	}

	
	
	public static GridLayoutData 	get_GridLayoutGridCenter_TOP(Insets oInsets, Color oColor)				
	{
		return LayoutDataFactory.get_GridLayoutGridCenter(oInsets,oColor, new Alignment(Alignment.CENTER,Alignment.TOP));	
	}
	

	public static GridLayoutData 	get_GridLayoutGridCenter_TOP(Insets oInsets, int iColSpan)						
	{
		GridLayoutData oGL_Rueck = LayoutDataFactory.get_GridLayoutGridCenter(oInsets,null, new Alignment(Alignment.CENTER,Alignment.TOP));	
		oGL_Rueck.setColumnSpan(iColSpan);
		return oGL_Rueck;	
	}


	public static GridLayoutData 	get_GridLayoutGridLeft(Insets oInsets)					
	{
		return LayoutDataFactory.get_GridLayoutGridLeft(oInsets,null, null);	
	}

	
	public static GridLayoutData 	get_GridLayoutGridLeftTOP(Insets oInsets)					
	{
		return LayoutDataFactory.get_GridLayoutGridLeft(oInsets,null, new Alignment(Alignment.LEFT,Alignment.TOP));	
	}


	
	public static GridLayoutData 	get_GridLayoutGridLeftMID(Insets oInsets)					
	{
		return LayoutDataFactory.get_GridLayoutGridLeft(oInsets,null, new Alignment(Alignment.LEFT,Alignment.CENTER));	
	}
	
	public static GridLayoutData 	get_GridLayoutGridLeftMID(Insets oInsets, int iColSpan)					
	{
		GridLayoutData oGL_Rueck = LayoutDataFactory.get_GridLayoutGridLeft(oInsets,null, new Alignment(Alignment.LEFT,Alignment.CENTER));
		oGL_Rueck.setColumnSpan(iColSpan);
		return oGL_Rueck;	
	}

	
	public static GridLayoutData 	get_GridLayoutGridLeftTOP(Insets oInsets, int iColSpan)						
	{
		GridLayoutData oGL_Rueck = LayoutDataFactory.get_GridLayoutGridLeft(oInsets,null, new Alignment(Alignment.LEFT,Alignment.TOP));
		oGL_Rueck.setColumnSpan(iColSpan);
		return oGL_Rueck;	
	}

	
	public static GridLayoutData 	get_GridLayoutGridRight(Insets oInsets)						
	{
		return LayoutDataFactory.get_GridLayoutGridRight(oInsets,null, null);	
	}

	public static GridLayoutData 	get_GridLayoutGridRight_TOP(Insets oInsets)						
	{
		return LayoutDataFactory.get_GridLayoutGridRight(oInsets,null, new Alignment(Alignment.RIGHT,Alignment.TOP));	
	}

	
	public static GridLayoutData 	get_GridLayoutGridRight_TOP(Insets oInsets, Color oColorBackground)						
	{
		return LayoutDataFactory.get_GridLayoutGridRight(oInsets,oColorBackground, new Alignment(Alignment.RIGHT,Alignment.TOP));	
	}

	
	
	public static GridLayoutData 	get_GridLayoutGridRight_BOTTOM(Insets oInsets)						
	{
		return LayoutDataFactory.get_GridLayoutGridRight(oInsets,null, new Alignment(Alignment.RIGHT,Alignment.BOTTOM));	
	}

	public static GridLayoutData 	get_GridLayoutGridRight_MID(Insets oInsets)						
	{
		return LayoutDataFactory.get_GridLayoutGridRight(oInsets,null, new Alignment(Alignment.RIGHT,Alignment.CENTER));	
	}

	
	
	public static GridLayoutData 	get_GridLayoutGridRight_TOP(Insets oInsets, int iColSpan)						
	{
		GridLayoutData oGL_Rueck = LayoutDataFactory.get_GridLayoutGridRight(oInsets,null, new Alignment(Alignment.RIGHT,Alignment.TOP));
		oGL_Rueck.setColumnSpan(iColSpan);
		return oGL_Rueck;	
	}

	
	
	public static GridLayoutData 	get_GridLayoutGridCenter_DARK(Insets oInsets)				
	{
		return LayoutDataFactory.get_GridLayoutGridCenter(oInsets,new E2_ColorDark(), null);	
	}
	
	public static GridLayoutData 	get_GridLayoutGridLeft_DARK(Insets oInsets)					
	{
		return LayoutDataFactory.get_GridLayoutGridLeft(oInsets,new E2_ColorDark(), null);	
	}

	public static GridLayoutData 	get_GridLayoutGridRight_DARK(Insets oInsets)						
	{
		return LayoutDataFactory.get_GridLayoutGridRight(oInsets,new E2_ColorDark(), null);	
	}

	public static GridLayoutData 	get_GridLayoutGridRight_DARK_TOP(Insets oInsets)						
	{
		return LayoutDataFactory.get_GridLayoutGridRight(oInsets,new E2_ColorDark(), new Alignment(Alignment.RIGHT,Alignment.TOP));	
	}
	
	public static GridLayoutData 	get_GridLayoutGridLeft_DARK_TOP(Insets oInsets)						
	{
		return LayoutDataFactory.get_GridLayoutGridRight(oInsets,new E2_ColorDark(), new Alignment(Alignment.LEFT,Alignment.TOP));	
	}

	
	public static GridLayoutData 	get_GridLayoutGridCenter_DDARK(Insets oInsets)				
	{
		return LayoutDataFactory.get_GridLayoutGridCenter(oInsets,new E2_ColorDDark(), null);	
	}
	
	public static GridLayoutData 	get_GridLayoutGridLeft_DDARK(Insets oInsets)					
	{
		return LayoutDataFactory.get_GridLayoutGridLeft(oInsets,new E2_ColorDDark(), null);	
	}

	public static GridLayoutData 	get_GridLayoutGridRight_DDARK(Insets oInsets)						
	{
		return LayoutDataFactory.get_GridLayoutGridRight(oInsets,new E2_ColorDDark(), null);	
	}

	
	
	
	public static ColumnLayoutData 	get_ColLayoutGridCenter(Insets oInsets)					
	{
		ColumnLayoutData oRueck = new ColumnLayoutData(); 
		oRueck.setAlignment(new Alignment(Alignment.CENTER,Alignment.DEFAULT));
		if (oInsets != null)
			oRueck.setInsets(oInsets);
		return oRueck;	
	}

	
	public static ColumnLayoutData 	get_ColLayoutGridLeft(Insets oInsets)						
	{
		ColumnLayoutData oRueck = new ColumnLayoutData(); 
		oRueck.setAlignment(new Alignment(Alignment.LEFT,Alignment.DEFAULT));
		if (oInsets != null)
			oRueck.setInsets(oInsets);
		return oRueck;	
	}

	
	public static ColumnLayoutData 	get_ColLayoutGridRight(Insets oInsets)						
	{
		ColumnLayoutData oRueck = new ColumnLayoutData(); 
		oRueck.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
		if (oInsets != null)
			oRueck.setInsets(oInsets);
		return oRueck;	
	}

	public static ColumnLayoutData 	get_ColLayoutGridRight_DARK(Insets oInsets)						
	{
		ColumnLayoutData oRueck = new ColumnLayoutData(); 
		oRueck.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
		if (oInsets != null)
			oRueck.setInsets(oInsets);
		
		oRueck.setBackground(new E2_ColorDark());
		
		return oRueck;	
	}

	
	//2011-01-24: kopiermethode fuer layoutdata-objekte
	public static GridLayoutData   get_GL_Copy(GridLayoutData  oGL_Orig)
	{
		if (oGL_Orig==null) return null;
		
		GridLayoutData  oGLRueck = new GridLayoutData();
		
		oGLRueck.setAlignment(			oGL_Orig.getAlignment());
		oGLRueck.setBackground(			oGL_Orig.getBackground());
		oGLRueck.setBackgroundImage(	oGL_Orig.getBackgroundImage());
		oGLRueck.setColumnSpan(			oGL_Orig.getColumnSpan());
		oGLRueck.setInsets(				oGL_Orig.getInsets());
		oGLRueck.setRowSpan(			oGL_Orig.getRowSpan());
		
		return oGLRueck;
	}

	public static GridLayoutData   get_GL_Copy(GridLayoutData  oGL_Orig, Color oNewBackground)
	{
		if (oGL_Orig==null) return null;

		GridLayoutData  oGLRueck = LayoutDataFactory.get_GL_Copy(oGL_Orig);
		oGLRueck.setBackground(oNewBackground);
		return oGLRueck;
	}

	//2011-01-24: kopiermethode fuer layoutdata-objekte
	public static GridLayoutData   get_GL_Copy_NN(GridLayoutData  oGL_Orig)
	{
		if (oGL_Orig==null) return new GridLayoutData();
		
		GridLayoutData  oGLRueck = new GridLayoutData();
		
		oGLRueck.setAlignment(			oGL_Orig.getAlignment());
		oGLRueck.setBackground(			oGL_Orig.getBackground());
		oGLRueck.setBackgroundImage(	oGL_Orig.getBackgroundImage());
		oGLRueck.setColumnSpan(			oGL_Orig.getColumnSpan());
		oGLRueck.setInsets(				oGL_Orig.getInsets());
		oGLRueck.setRowSpan(			oGL_Orig.getRowSpan());
		
		return oGLRueck;
	}
	
	public static GridLayoutData   get_GL_Copy_NN(GridLayoutData  oGL_Orig, Color oNewBackground)
	{
		GridLayoutData  oGLRueck = LayoutDataFactory.get_GL_Copy_NN(oGL_Orig);
		oGLRueck.setBackground(oNewBackground);
		return oGLRueck;
	}

	
	
	
	
	
	//2013-06-28: kopiermethode fuer Columnlayoutdata-objekte
	public static ColumnLayoutData   get_CL_Copy(ColumnLayoutData  oGL_Orig)
	{
		if (oGL_Orig==null) return null;
		
		ColumnLayoutData  oCLRueck = new ColumnLayoutData();
		
		oCLRueck.setAlignment(			oGL_Orig.getAlignment());
		oCLRueck.setBackground(			oGL_Orig.getBackground());
		oCLRueck.setBackgroundImage(	oGL_Orig.getBackgroundImage());
		oCLRueck.setInsets(				oGL_Orig.getInsets());
		
		return oCLRueck;
	}

	public static ColumnLayoutData   get_CL_Copy(ColumnLayoutData  oCL_Orig, Color oNewBackground)
	{
		if (oCL_Orig==null) return null;

		ColumnLayoutData  oGLRueck = LayoutDataFactory.get_CL_Copy(oCL_Orig);
		oGLRueck.setBackground(oNewBackground);
		return oGLRueck;
	}

	//2011-01-24: kopiermethode fuer layoutdata-objekte
	public static ColumnLayoutData   get_CL_Copy_NN(ColumnLayoutData  oCL_Orig)
	{
		if (oCL_Orig==null) return new ColumnLayoutData();
		
		ColumnLayoutData  oCLRueck = new ColumnLayoutData();
		
		oCLRueck.setAlignment(			oCL_Orig.getAlignment());
		oCLRueck.setBackground(			oCL_Orig.getBackground());
		oCLRueck.setBackgroundImage(	oCL_Orig.getBackgroundImage());
		oCLRueck.setInsets(				oCL_Orig.getInsets());
		
		return oCLRueck;
	}
	
	public static ColumnLayoutData   get_CL_Copy_NN(ColumnLayoutData  oGL_Orig, Color oNewBackground)
	{
		ColumnLayoutData  oCLRueck = LayoutDataFactory.get_CL_Copy_NN(oGL_Orig);
		oCLRueck.setBackground(oNewBackground);
		return oCLRueck;
	}

	
	
	/**
	 * ersetzt das gridLayoutData einer componente durch eine kopie mit anderem hintergrund,
	 * hat die componente ein layoutData== null oder ist das layoutData kein GridLayoutData, dann passiert nichts 
	 * @param comp
	 * @param colBack
	 */
	public static void change_gridLayoutData(Component comp, Color colBack) throws myException {
		if (comp.getLayoutData()!=null && comp.getLayoutData() instanceof GridLayoutData)  {
			GridLayoutData gl_new = LayoutDataFactory.get_GL_Copy((GridLayoutData)comp.getLayoutData());
			gl_new.setBackground(colBack);
			comp.setLayoutData(gl_new);
		}
	}

	
}
