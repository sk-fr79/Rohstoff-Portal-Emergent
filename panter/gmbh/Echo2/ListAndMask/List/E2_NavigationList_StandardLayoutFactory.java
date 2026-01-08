package panter.gmbh.Echo2.ListAndMask.List;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.indep.bibALL;

public class E2_NavigationList_StandardLayoutFactory
{
	
	
	public static GridLayoutData 	get_GRID_LayoutCenterTOP()						
	{
		GridLayoutData oLayout = new GridLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.CENTER,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorBase());
		return oLayout;	
	}
	
	
	public static GridLayoutData 	get_GRID_LayoutCenterTOPHeader()				
	{	
		GridLayoutData oLayout = new GridLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.CENTER,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorDark());
		return oLayout;	
	}
	
	public static GridLayoutData 	get_GRID_LayoutLeftTOP()						
	{	
		GridLayoutData oLayout = new GridLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.LEFT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorBase());
		return oLayout;	
	}

	
	public static GridLayoutData 	get_GRID_LayoutLeftTOPHeader()					
	{	
		GridLayoutData oLayout = new GridLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.LEFT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorDark());
		return oLayout;	
	}
	

	public static GridLayoutData get_GRID_LayoutRightTOP()							
	{	
		GridLayoutData oLayout = new GridLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.RIGHT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorBase());
		return oLayout;	
	}
	
	
	public static GridLayoutData get_GRID_LayoutRightTOPHeader()					
	{	
		GridLayoutData oLayout = new GridLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.RIGHT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorDark());
		return oLayout;	
	}


	public static ColumnLayoutData 	get_COLUMN_LayoutCenterTOP()						
	{	
		ColumnLayoutData oLayout = new ColumnLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.CENTER,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorBase());
		return oLayout;	
	}
	
	
	public static ColumnLayoutData 	get_COLUMN_LayoutCenterTOPHeader()				
	{	
		ColumnLayoutData oLayout = new ColumnLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.CENTER,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorDark());
		return oLayout;	
	}
	
	
	public static ColumnLayoutData 	get_COLUMN_LayoutLeftTOP()						
	{	
		ColumnLayoutData oLayout = new ColumnLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.LEFT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorBase());
		return oLayout;	
	}
	
	
	public static ColumnLayoutData 	get_COLUMN_LayoutLeftTOPHeader()					
	{	
		ColumnLayoutData oLayout = new ColumnLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.LEFT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorDark());

		return oLayout;	
	}
	
	
	public static ColumnLayoutData get_COLUMN_LayoutRightTOP()							
	{	
		ColumnLayoutData oLayout = new ColumnLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.RIGHT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorBase());
		return oLayout;	
	}
	
	public static ColumnLayoutData get_COLUMN_LayoutRightTOPHeader()					
	{	
		ColumnLayoutData oLayout = new ColumnLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.RIGHT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorDark());

		return oLayout;	
	}

	public static RowLayoutData 	get_ROW_LayoutCenterTOP()						
	{	
		RowLayoutData oLayout = new RowLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.CENTER,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorBase());
		return oLayout;	
	}

	public static RowLayoutData 	get_ROW_LayoutCenterTOPHeader()				
	{	
		RowLayoutData oLayout = new RowLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.CENTER,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorDark());
		return oLayout;	
	}
	
	public static RowLayoutData 	get_ROW_LayoutLeftTOP()						
	{	
		RowLayoutData oLayout = new RowLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.LEFT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorBase());
		return oLayout;	
	}

	
	
	public static RowLayoutData 	get_ROW_LayoutLeftTOPHeader()					
	{	
		RowLayoutData oLayout = new RowLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.LEFT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorDark());
		return oLayout;	
	}

	public static RowLayoutData get_ROW_LayoutRightTOP()							
	{	
		RowLayoutData oLayout = new RowLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.RIGHT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorBase());
		return oLayout;	
	}
	
	
	public static RowLayoutData get_ROW_LayoutRightTOPHeader()					
	{	
		RowLayoutData oLayout = new RowLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.RIGHT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorDark());
		return oLayout;	
	}

	
	
	/*
	 * spezielle layoutData-definitionen fuer component-groups.
	 * die in diesen groups enthaltenen componenten wurde bereits mit layouts versehen,
	 * do dass die gruppe links oben ohne insets eingebunden werden muss
	 */
	public static GridLayoutData 	get_GRID_Layout4ComponentGroups()						
	{	
		GridLayoutData oLayout = new GridLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.LEFT,Alignment.TOP), E2_INSETS.I_0_0_0_0, new E2_ColorBase());
		return oLayout;	
	}

	
	public static GridLayoutData 	get_GRID_Layout4ComponentGroupsHeader()					
	{	
		GridLayoutData oLayout = new GridLayoutData();

		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.LEFT,Alignment.TOP), E2_INSETS.I_0_0_0_0, new E2_ColorDark());
		return oLayout;	
	}
	
	
	//2012-08-30: kopiermethode fuer layoutdata-objekte
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

		GridLayoutData  oGLRueck = E2_NavigationList_StandardLayoutFactory.get_GL_Copy(oGL_Orig);
		oGLRueck.setBackground(oNewBackground);
		return oGLRueck;
	}
	
	
	//2012-08-30: kopiermethode fuer layoutdata-objekte
	public static ColumnLayoutData   get_CL_Copy(ColumnLayoutData  oCL_Orig)
	{
		if (oCL_Orig==null) return null;
		
		ColumnLayoutData  oCLRueck = new ColumnLayoutData();
		
		oCLRueck.setAlignment(			oCL_Orig.getAlignment());
		oCLRueck.setBackground(			oCL_Orig.getBackground());
		oCLRueck.setBackgroundImage(	oCL_Orig.getBackgroundImage());
		oCLRueck.setInsets(				oCL_Orig.getInsets());
		
		return oCLRueck;
	}

	
	public static ColumnLayoutData   get_CL_Copy(ColumnLayoutData  oCL_Orig, Color oNewBackground)
	{
		if (oCL_Orig==null) return null;

		ColumnLayoutData  oCLRueck = E2_NavigationList_StandardLayoutFactory.get_CL_Copy(oCL_Orig);
		oCLRueck.setBackground(oNewBackground);
		return oCLRueck;
	}
	
	
	//2012-08-30: kopiermethode fuer layoutdata-objekte
	public static RowLayoutData   get_RL_Copy(RowLayoutData  oCL_Orig)
	{
		if (oCL_Orig==null) return null;
		
		RowLayoutData  oRLRueck = new RowLayoutData();
		
		oRLRueck.setAlignment(			oCL_Orig.getAlignment());
		oRLRueck.setBackground(			oCL_Orig.getBackground());
		oRLRueck.setBackgroundImage(	oCL_Orig.getBackgroundImage());
		oRLRueck.setInsets(				oCL_Orig.getInsets());
		
		return oRLRueck;
	}

	
	public static RowLayoutData   get_RL_Copy(RowLayoutData  oCL_Orig, Color oNewBackground)
	{
		if (oCL_Orig==null) return null;

		RowLayoutData  oRLRueck = E2_NavigationList_StandardLayoutFactory.get_RL_Copy(oCL_Orig);
		oRLRueck.setBackground(oNewBackground);
		return oRLRueck;
	}
	

	
	//2012-08-30: kopiermethode fuer layoutdata-objekte
	public static LayoutData   get_LayoutData_Copy(LayoutData  oL_Orig)
	{
		if (oL_Orig==null) return null;
		
		if 		(oL_Orig instanceof GridLayoutData)
		{
			return E2_NavigationList_StandardLayoutFactory.get_GL_Copy( (GridLayoutData)oL_Orig);
		}
		else if (oL_Orig instanceof ColumnLayoutData)
		{
			return E2_NavigationList_StandardLayoutFactory.get_CL_Copy( (ColumnLayoutData)oL_Orig);
		}
		else if (oL_Orig instanceof RowLayoutData)
		{
			return E2_NavigationList_StandardLayoutFactory.get_RL_Copy( (RowLayoutData)oL_Orig);
		}
		else
		{
			return null;
		}
	}


	
	public static LayoutData   get_LayoutData_Copy(LayoutData  oL_Orig, Color oNewBackground)
	{
		if (oL_Orig==null) return null;

		if 		(oL_Orig instanceof GridLayoutData)
		{
			return E2_NavigationList_StandardLayoutFactory.get_GL_Copy( (GridLayoutData)oL_Orig,oNewBackground);
		}
		else if (oL_Orig instanceof ColumnLayoutData)
		{
			return E2_NavigationList_StandardLayoutFactory.get_CL_Copy( (ColumnLayoutData)oL_Orig,oNewBackground);
		}
		else if (oL_Orig instanceof RowLayoutData)
		{
			return E2_NavigationList_StandardLayoutFactory.get_RL_Copy( (RowLayoutData)oL_Orig,oNewBackground);
		}
		else
		{
			return null;
		}

	}
	
	public static Color  get_ColorBackground(LayoutData  oL_Orig)
	{
		if (oL_Orig==null) return null;

		if 		(oL_Orig instanceof GridLayoutData)
		{
			return ((GridLayoutData)oL_Orig).getBackground();
		}
		else if (oL_Orig instanceof ColumnLayoutData)
		{
			return ((ColumnLayoutData)oL_Orig).getBackground();
		}
		else if (oL_Orig instanceof RowLayoutData)
		{
			return ((RowLayoutData)oL_Orig).getBackground();
		}
		else
		{
			return null;
		}
		
	}

	
	
	public static void  set_BackgroundColor(LayoutData oLayout, Color ocol)
	{
		if (oLayout==null) return ;

		if 		(oLayout instanceof GridLayoutData)
		{
			((GridLayoutData)oLayout).setBackground(ocol);
		}
		else if (oLayout instanceof ColumnLayoutData)
		{
			 ((ColumnLayoutData)oLayout).setBackground(ocol);
		}
		else if (oLayout instanceof RowLayoutData)
		{
			 ((RowLayoutData)oLayout).setBackground(ocol);
		}
		else
		{
			return;
		}
	}


	
	
	
	
}
