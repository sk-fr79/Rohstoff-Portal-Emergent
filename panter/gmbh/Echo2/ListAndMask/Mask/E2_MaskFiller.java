package panter.gmbh.Echo2.ListAndMask.Mask;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.SelectField;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea_WithSelektor;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

/**
 * fuell-hilfe, um maskenfelder und erlaeuterungen  anzuordnen in Grid-Zeilen
 * Es werden der methode add_line entweder MyString oder String - objekte uebergeben.
 * Wenn es String sind, dann MUESSEN es HashWerte fuer die suche in einer der ComponentMAPs sein,
 * oder der string beginnt mit #, dann wird automatisch eine MyE2_Label(MyE2_String())) erzeugt
 */
public class E2_MaskFiller 
{



	private Insets 			oIN_InfoText = 		null;
	private Insets			oIN_Komponente = 	null;
	private E2_ComponentMAP oMap1= 				null;
	private E2_ComponentMAP oMap2= 				null;
	private E2_ComponentMAP oMap3= 				null;
	
	private Alignment		BasicAlignment	= 		new Alignment(Alignment.LEFT,Alignment.TOP);
	
	

	//es koennen einzelne zeilen hervorgehoben werden. dies geschieht mit der methode set_NextLineColor
	private Color 		  oActualColor = null;
	private int  		  iNumberLines = 0;
	
	/**
	 * @param Map1
	 * @param Map2
	 * @param Map3
	 * @param infoText
	 * @param komponente
	 * @param alignment
	 * @throws myException
	 */
	public E2_MaskFiller(E2_ComponentMAP Map1, 
									E2_ComponentMAP Map2,
									E2_ComponentMAP Map3,  
									Insets infoText, 
									Insets komponente, 
									Alignment alignment) throws myException 
	{
		super();
		oIN_InfoText = infoText;
		oIN_Komponente = komponente;
		this.oMap1=Map1;
		this.oMap2=Map2;
		this.oMap3=Map3;
		
		if (alignment != null)
			this.BasicAlignment=alignment;
		
		if (this.oMap1 == null)
			throw new myException(this,":Constuctor: Even 1 ComponentMAP MUST BE NOT NULL !!! ");
		
		this.__INIT();
		
	}
	
	
	
	/**
	 * @param Map1
	 * @param Map2
	 * @param Map3
	 * @throws myException
	 */
	public E2_MaskFiller(	E2_ComponentMAP Map1, 
							E2_ComponentMAP Map2,
							E2_ComponentMAP Map3  
							) throws myException 
	{
		super();
		oIN_InfoText = E2_INSETS.I_5_2_5_2;
		oIN_Komponente = E2_INSETS.I_5_2_5_2;;
		this.oMap1=Map1;
		this.oMap2=Map2;
		this.oMap3=Map3;
		
		if (this.oMap1 == null)
		throw new myException(this,":Constuctor: Even 1 ComponentMAP MUST BE NOT NULL !!! ");
		
		this.__INIT();

	}


	private void __INIT()
	{
//		oRowLayoutTop.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
//		oRowLayoutMid.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
//		oRowLayoutBottom.setAlignment(new Alignment(Alignment.LEFT,Alignment.BOTTOM));
	}
	
	
	/**
	 * 
	 * @param oColor
	 * @param NumberLines
	 */
	public void set_NextLineColor(Color oColor, int NumberLines)
	{
		this.oActualColor = oColor;
		this.iNumberLines = NumberLines;
	}
	

	private void end_Line(MyE2_Grid oGrid)
	{
		oGrid.NewLine();
		this.iNumberLines--;
		if (this.iNumberLines<=0)
		{
			this.oActualColor = null;	
		}
		
	}
	

	

//	/**
//	 * 
//	 * @param oGrid
//	 * @param oComp1
//	 * @param iColspan1
//	 * @param oComp2
//	 * @param iColspan2
//	 * @param oComp3
//	 * @param iColspan3
//	 * @param oComp4
//	 * @param iColspan4
//	 */
//	public void add_Line_Components(MyE2_Grid oGrid,Component oComp1, int iColspan1,Component oComp2, int iColspan2,Component oComp3, int iColspan3,Component oComp4, int iColspan4)
//	{
//		oGrid.add(oComp1,new ownGridLayoutNormal(iColspan1,this.oIN_InfoText));
//		if (oComp2!=null) oGrid.add(oComp2,new ownGridLayoutNormal(iColspan2,this.oIN_Komponente));
//		if (oComp3!=null) oGrid.add(oComp3,new ownGridLayoutNormal(iColspan3,this.oIN_Komponente));
//		if (oComp4!=null) oGrid.add(oComp4,new ownGridLayoutNormal(iColspan4,this.oIN_Komponente));
//		this.end_Line(oGrid);
//	}
//
	
	
	/**
	 * 
	 * @param oGrid
	 * @param oLabTitle
	 * @param oComp1
	 * @param iColspan1
	 * @param Insets1
	 * @param oComp2
	 * @param iColspan2
	 * @param Insets2
	 * @param oComp3
	 * @param iColspan3
	 * @param Insets3
	 */
	public void add_Line_WithComponents(MyE2_Grid oGrid,MyE2_Label oLabTitle, Component oComp1,int iColspan1, Insets Insets1,Component oComp2,int iColspan2, Insets Insets2, Component oComp3,int iColspan3, Insets Insets3)
	{
		oGrid.add(oLabTitle,new ownGridLayoutNormal(1,this.oIN_InfoText,null));
		oGrid.add(oComp1,new ownGridLayoutNormal(iColspan1,Insets1,null));
		if (oComp2!=null) oGrid.add(oComp2,new ownGridLayoutNormal(iColspan2,Insets2,null));
		if (oComp3!=null) oGrid.add(oComp3,new ownGridLayoutNormal(iColspan3,Insets3,null));
		this.end_Line(oGrid);
	}
	

	
	/**
	 * 
	 * @param oGrid
	 * @param oLabTitle
	 * @param oAlignTitle
	 * @param oComp1
	 * @param iColspan1
	 * @param Insets1
	 * @param oAlign1
	 * @param oComp2
	 * @param iColspan2
	 * @param Insets2
	 * @param oAlign2
	 * @param oComp3
	 * @param iColspan3
	 * @param Insets3
	 * @param oAlign3
	 */
	public void add_Line_WithComponents(		MyE2_Grid oGrid,
												MyE2_Label oLabTitle, 
												Insets InsetsTitle,
												Alignment  oAlignTitle,
												Component oComp1,
												int iColspan1, 
												Insets Insets1,
												Alignment  oAlign1,
												Component oComp2,
												int iColspan2, 
												Insets Insets2, 
												Alignment  oAlign2,
												Component oComp3,
												int iColspan3, 
												Insets Insets3,
												Alignment  oAlign3
												)
	{
		oGrid.add(oLabTitle,new ownGridLayoutNormal(1,InsetsTitle,oAlignTitle));
		oGrid.add(oComp1,new ownGridLayoutNormal(iColspan1,Insets1,oAlign1));
		if (oComp2!=null) oGrid.add(oComp2,new ownGridLayoutNormal(iColspan2,Insets2,oAlign2));
		if (oComp3!=null) oGrid.add(oComp3,new ownGridLayoutNormal(iColspan3,Insets3,oAlign3));
		this.end_Line(oGrid);
	}

	
	/**
	 * 
	 * @param oGrid
	 * @param oLabTitle
	 * @param oAlignTitle
	 * @param oComp1
	 * @param iColspan1
	 * @param Insets1
	 * @param oAlign1
	 * @param oComp2
	 * @param iColspan2
	 * @param Insets2
	 * @param oAlign2
	 * @param oComp3
	 * @param iColspan3
	 * @param Insets3
	 * @param oAlign3
	 * @param oComp4
	 * @param iColspan44
	 * @param Insets4
	 * @param oAlign4
	 */
	public void add_Line_WithComponents(		MyE2_Grid oGrid,
												MyE2_Label oLabTitle, 
												Insets InsetsTitle,
												Alignment  oAlignTitle,
												Component oComp1,
												int iColspan1, 
												Insets Insets1,
												Alignment  oAlign1,
												Component oComp2,
												int iColspan2, 
												Insets Insets2, 
												Alignment  oAlign2,
												Component oComp3,
												int iColspan3, 
												Insets Insets3,
												Alignment  oAlign3,
												Component oComp4,
												int iColspan4, 
												Insets Insets4,
												Alignment  oAlign4
												)
	{
		oGrid.add(oLabTitle,new ownGridLayoutNormal(1,InsetsTitle,oAlignTitle));
		oGrid.add(oComp1,new ownGridLayoutNormal(iColspan1,Insets1,oAlign1));
		if (oComp2!=null) oGrid.add(oComp2,new ownGridLayoutNormal(iColspan2,Insets2,oAlign2));
		if (oComp3!=null) oGrid.add(oComp3,new ownGridLayoutNormal(iColspan3,Insets3,oAlign3));
		if (oComp4!=null) oGrid.add(oComp4,new ownGridLayoutNormal(iColspan4,Insets4,oAlign4));
		this.end_Line(oGrid);
	}

	
	/**
	 * 
	 * @param oGrid
	 * @param oLabTitle
	 * @param oComp1
	 * @param iColspan1
	 * @param Insets1
	 * @param oComp2
	 * @param iColspan2
	 * @param Insets2
	 * @param oComp3
	 * @param iColspan3
	 * @param Insets3
	 */
	public void add_Line_WithComponents(MyE2_Grid oGrid,MyE2_Label oLabTitle, 
										Component oComp1,int iColspan1, Insets Insets1,
										Component oComp2,int iColspan2, Insets Insets2, 
										Component oComp3,int iColspan3, Insets Insets3,
										Alignment oAlign)
	{
		oGrid.add(oLabTitle,new ownGridLayoutNormal(1,this.oIN_InfoText,oAlign));
		oGrid.add(oComp1,new ownGridLayoutNormal(iColspan1,Insets1,oAlign));
		if (oComp2!=null) oGrid.add(oComp2,new ownGridLayoutNormal(iColspan2,Insets2,oAlign));
		if (oComp3!=null) oGrid.add(oComp3,new ownGridLayoutNormal(iColspan3,Insets3,oAlign));
		this.end_Line(oGrid);
	}

	
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, BasicAlignment, false);
		this.end_Line(oGrid);
	}
	

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, false);
		this.end_Line(oGrid);
	}

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, false);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, false);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME5,iColSpan5, this.BasicAlignment, false);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Object cNAME6,int iColSpan6) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME5,iColSpan5, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME6,iColSpan6, this.BasicAlignment, false);
		this.end_Line(oGrid);
	}

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Object cNAME6,int iColSpan6,Object cNAME7,int iColSpan7) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME5,iColSpan5, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME6,iColSpan6, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME7,iColSpan7, this.BasicAlignment, false);
		this.end_Line(oGrid);
	}


	public void add_Line(MyE2_Grid oGrid,	
							Object cNAME1,int iColSpan1,
							Object cNAME2,int iColSpan2,
							Object cNAME3,int iColSpan3,
							Object cNAME4,int iColSpan4,
							Object cNAME5,int iColSpan5,
							Object cNAME6,int iColSpan6,
							Object cNAME7,int iColSpan7,
							Object cNAME8,int iColSpan8
							) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME5,iColSpan5, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME6,iColSpan6, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME7,iColSpan7, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME8,iColSpan8, this.BasicAlignment, false);
		this.end_Line(oGrid);
	}

	
	
	public void add_Line(MyE2_Grid oGrid,	
						Object cNAME1,int iColSpan1,
						Object cNAME2,int iColSpan2,
						Object cNAME3,int iColSpan3,
						Object cNAME4,int iColSpan4,
						Object cNAME5,int iColSpan5,
						Object cNAME6,int iColSpan6,
						Object cNAME7,int iColSpan7,
						Object cNAME8,int iColSpan8,
						Object cNAME9,int iColSpan9
						) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME5,iColSpan5, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME6,iColSpan6, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME7,iColSpan7, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME8,iColSpan8, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME9,iColSpan9, this.BasicAlignment, false);
		this.end_Line(oGrid);
	}


	public void add_Line(MyE2_Grid oGrid,	
			Object cNAME1,int iColSpan1,
			Object cNAME2,int iColSpan2,
			Object cNAME3,int iColSpan3,
			Object cNAME4,int iColSpan4,
			Object cNAME5,int iColSpan5,
			Object cNAME6,int iColSpan6,
			Object cNAME7,int iColSpan7,
			Object cNAME8,int iColSpan8,
			Object cNAME9,int iColSpan9,
			Object cNAME10,int iColSpan10
			) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME5,iColSpan5, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME6,iColSpan6, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME7,iColSpan7, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME8,iColSpan8, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME9,iColSpan9, this.BasicAlignment, false);
		this.add_field(oGrid,cNAME10,iColSpan10, this.BasicAlignment, false);
		this.end_Line(oGrid);
	}


	
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,Object cNAME2,Object cNAME3,Object cNAME4,Object cNAME5,Object cNAME6,Object cNAME7) throws myException 
	{
		this.add_field(oGrid,cNAME1,1, this.BasicAlignment, false);
		if (cNAME2 != null) this.add_field(oGrid,cNAME2,1, this.BasicAlignment, false);
		if (cNAME3 != null) this.add_field(oGrid,cNAME3,1, this.BasicAlignment, false);
		if (cNAME4 != null) this.add_field(oGrid,cNAME4,1, this.BasicAlignment, false);
		if (cNAME5 != null) this.add_field(oGrid,cNAME5,1, this.BasicAlignment, false);
		if (cNAME6 != null) this.add_field(oGrid,cNAME6,1, this.BasicAlignment, false);
		if (cNAME7 != null) this.add_field(oGrid,cNAME7,1, this.BasicAlignment, false);
		this.end_Line(oGrid);
	}

	
	
	///
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Alignment oAlignment) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignment, false);
		this.end_Line(oGrid);
	}
	

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Alignment oAlignment) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignment, false);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignment, false);
		this.end_Line(oGrid);
	}

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Alignment oAlignment) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignment, false);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignment, false);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignment, false);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Alignment oAlignment) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignment, false);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignment, false);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignment, false);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignment, false);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Alignment oAlignment) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignment, false);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignment, false);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignment, false);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignment, false);
		this.add_field(oGrid,cNAME5,iColSpan5, oAlignment, false);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Object cNAME6,int iColSpan6,Alignment oAlignemtnForMultiComponents) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents, false);
		this.end_Line(oGrid);
	}

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Object cNAME6,int iColSpan6,Object cNAME7,int iColSpan7,Alignment oAlignemtnForMultiComponents) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME7,iColSpan7, oAlignemtnForMultiComponents, false);
		this.end_Line(oGrid);
	}

	
	
	public void add_Line(MyE2_Grid oGrid, 
					Object cNAME1,int iColSpan1,
					Object cNAME2,int iColSpan2,
					Object cNAME3,int iColSpan3,
					Object cNAME4,int iColSpan4,
					Object cNAME5,int iColSpan5,
					Object cNAME6,int iColSpan6,
					Object cNAME7,int iColSpan7,
					Object cNAME8,int iColSpan8,
					Alignment oAlignemtnForMultiComponents) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME7,iColSpan7, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME8,iColSpan8, oAlignemtnForMultiComponents, false);
		this.end_Line(oGrid);
	}

	
	public void add_Line(MyE2_Grid oGrid, 
			Object cNAME1,int iColSpan1,
			Object cNAME2,int iColSpan2,
			Object cNAME3,int iColSpan3,
			Object cNAME4,int iColSpan4,
			Object cNAME5,int iColSpan5,
			Object cNAME6,int iColSpan6,
			Object cNAME7,int iColSpan7,
			Object cNAME8,int iColSpan8,
			Object cNAME9,int iColSpan9,
			Alignment oAlignemtnForMultiComponents) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME7,iColSpan7, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME8,iColSpan8, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME9,iColSpan9, oAlignemtnForMultiComponents, false);
		this.end_Line(oGrid);
	}



	public void add_Line(MyE2_Grid oGrid, 
			Object cNAME1,int iColSpan1,
			Object cNAME2,int iColSpan2,
			Object cNAME3,int iColSpan3,
			Object cNAME4,int iColSpan4,
			Object cNAME5,int iColSpan5,
			Object cNAME6,int iColSpan6,
			Object cNAME7,int iColSpan7,
			Object cNAME8,int iColSpan8,
			Object cNAME9,int iColSpan9,
			Object cNAME10,int iColSpan10,
			Alignment oAlignemtnForMultiComponents) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME7,iColSpan7, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME8,iColSpan8, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME9,iColSpan9, oAlignemtnForMultiComponents, false);
		this.add_field(oGrid,cNAME10,iColSpan10, oAlignemtnForMultiComponents, false);
		this.end_Line(oGrid);
	}


	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,Object cNAME2,Object cNAME3,Object cNAME4,Object cNAME5,Object cNAME6,Object cNAME7,Alignment oAlignemtnForMultiComponents) throws myException 
	{
		this.add_field(oGrid,cNAME1,1, oAlignemtnForMultiComponents, false);
		if (cNAME2 != null) this.add_field(oGrid,cNAME2,1, oAlignemtnForMultiComponents, false);
		if (cNAME3 != null) this.add_field(oGrid,cNAME3,1, oAlignemtnForMultiComponents, false);
		if (cNAME4 != null) this.add_field(oGrid,cNAME4,1, oAlignemtnForMultiComponents, false);
		if (cNAME5 != null) this.add_field(oGrid,cNAME5,1, oAlignemtnForMultiComponents, false);
		if (cNAME6 != null) this.add_field(oGrid,cNAME6,1, oAlignemtnForMultiComponents, false);
		if (cNAME7 != null) this.add_field(oGrid,cNAME7,1, oAlignemtnForMultiComponents, false);
		this.end_Line(oGrid);
	}

	
	
	//2014-07-16: neue methoden mit der moeglichkeit, die labels mit linewrap zu definieren
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, BasicAlignment, bLineWrap);
		this.end_Line(oGrid);
	}
	

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, bLineWrap);
		this.end_Line(oGrid);
	}

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, bLineWrap);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, bLineWrap);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME5,iColSpan5, this.BasicAlignment, bLineWrap);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Object cNAME6,int iColSpan6, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME5,iColSpan5, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME6,iColSpan6, this.BasicAlignment, bLineWrap);
		this.end_Line(oGrid);
	}

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Object cNAME6,int iColSpan6,Object cNAME7,int iColSpan7, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME5,iColSpan5, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME6,iColSpan6, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME7,iColSpan7, this.BasicAlignment, bLineWrap);
		this.end_Line(oGrid);
	}


	public void add_Line(MyE2_Grid oGrid,	
							Object cNAME1,int iColSpan1,
							Object cNAME2,int iColSpan2,
							Object cNAME3,int iColSpan3,
							Object cNAME4,int iColSpan4,
							Object cNAME5,int iColSpan5,
							Object cNAME6,int iColSpan6,
							Object cNAME7,int iColSpan7,
							Object cNAME8,int iColSpan8,
							boolean bLineWrap
							) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME5,iColSpan5, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME6,iColSpan6, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME7,iColSpan7, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME8,iColSpan8, this.BasicAlignment, bLineWrap);
		this.end_Line(oGrid);
	}

	
	
	public void add_Line(MyE2_Grid oGrid,	
						Object cNAME1,int iColSpan1,
						Object cNAME2,int iColSpan2,
						Object cNAME3,int iColSpan3,
						Object cNAME4,int iColSpan4,
						Object cNAME5,int iColSpan5,
						Object cNAME6,int iColSpan6,
						Object cNAME7,int iColSpan7,
						Object cNAME8,int iColSpan8,
						Object cNAME9,int iColSpan9,
						boolean bLineWrap
						) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME5,iColSpan5, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME6,iColSpan6, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME7,iColSpan7, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME8,iColSpan8, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME9,iColSpan9, this.BasicAlignment, bLineWrap);
		this.end_Line(oGrid);
	}


	public void add_Line(MyE2_Grid oGrid,	
			Object cNAME1,int iColSpan1,
			Object cNAME2,int iColSpan2,
			Object cNAME3,int iColSpan3,
			Object cNAME4,int iColSpan4,
			Object cNAME5,int iColSpan5,
			Object cNAME6,int iColSpan6,
			Object cNAME7,int iColSpan7,
			Object cNAME8,int iColSpan8,
			Object cNAME9,int iColSpan9,
			Object cNAME10,int iColSpan10,
			boolean bLineWrap
			) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME5,iColSpan5, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME6,iColSpan6, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME7,iColSpan7, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME8,iColSpan8, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME9,iColSpan9, this.BasicAlignment, bLineWrap);
		this.add_field(oGrid,cNAME10,iColSpan10, this.BasicAlignment, bLineWrap);
		this.end_Line(oGrid);
	}


	
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,Object cNAME2,Object cNAME3,Object cNAME4,Object cNAME5,Object cNAME6,Object cNAME7, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,1, this.BasicAlignment, false);
		if (cNAME2 != null) this.add_field(oGrid,cNAME2,1, this.BasicAlignment, bLineWrap);
		if (cNAME3 != null) this.add_field(oGrid,cNAME3,1, this.BasicAlignment, bLineWrap);
		if (cNAME4 != null) this.add_field(oGrid,cNAME4,1, this.BasicAlignment, bLineWrap);
		if (cNAME5 != null) this.add_field(oGrid,cNAME5,1, this.BasicAlignment, bLineWrap);
		if (cNAME6 != null) this.add_field(oGrid,cNAME6,1, this.BasicAlignment, bLineWrap);
		if (cNAME7 != null) this.add_field(oGrid,cNAME7,1, this.BasicAlignment, bLineWrap);
		this.end_Line(oGrid);
	}

	
	
	///
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Alignment oAlignment, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignment, bLineWrap);
		this.end_Line(oGrid);
	}
	

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Alignment oAlignment, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignment, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignment, bLineWrap);
		this.end_Line(oGrid);
	}

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Alignment oAlignment, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignment, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignment, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignment, bLineWrap);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Alignment oAlignment, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignment, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignment, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignment, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignment, bLineWrap);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Alignment oAlignment, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignment, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignment, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignment, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignment, bLineWrap);
		this.add_field(oGrid,cNAME5,iColSpan5, oAlignment, bLineWrap);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Object cNAME6,int iColSpan6,Alignment oAlignemtnForMultiComponents, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents, bLineWrap);
		this.end_Line(oGrid);
	}

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Object cNAME6,int iColSpan6,Object cNAME7,int iColSpan7,Alignment oAlignemtnForMultiComponents, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME7,iColSpan7, oAlignemtnForMultiComponents, bLineWrap);
		this.end_Line(oGrid);
	}

	
	
	public void add_Line(MyE2_Grid oGrid, 
					Object cNAME1,int iColSpan1,
					Object cNAME2,int iColSpan2,
					Object cNAME3,int iColSpan3,
					Object cNAME4,int iColSpan4,
					Object cNAME5,int iColSpan5,
					Object cNAME6,int iColSpan6,
					Object cNAME7,int iColSpan7,
					Object cNAME8,int iColSpan8,
					Alignment oAlignemtnForMultiComponents,
					boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME7,iColSpan7, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME8,iColSpan8, oAlignemtnForMultiComponents, bLineWrap);
		this.end_Line(oGrid);
	}

	
	public void add_Line(MyE2_Grid oGrid, 
			Object cNAME1,int iColSpan1,
			Object cNAME2,int iColSpan2,
			Object cNAME3,int iColSpan3,
			Object cNAME4,int iColSpan4,
			Object cNAME5,int iColSpan5,
			Object cNAME6,int iColSpan6,
			Object cNAME7,int iColSpan7,
			Object cNAME8,int iColSpan8,
			Object cNAME9,int iColSpan9,
			Alignment oAlignemtnForMultiComponents,
			boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME7,iColSpan7, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME8,iColSpan8, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME9,iColSpan9, oAlignemtnForMultiComponents, bLineWrap);
		this.end_Line(oGrid);
	}



	public void add_Line(MyE2_Grid oGrid, 
			Object cNAME1,int iColSpan1,
			Object cNAME2,int iColSpan2,
			Object cNAME3,int iColSpan3,
			Object cNAME4,int iColSpan4,
			Object cNAME5,int iColSpan5,
			Object cNAME6,int iColSpan6,
			Object cNAME7,int iColSpan7,
			Object cNAME8,int iColSpan8,
			Object cNAME9,int iColSpan9,
			Object cNAME10,int iColSpan10,
			Alignment oAlignemtnForMultiComponents,
			boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME7,iColSpan7, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME8,iColSpan8, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME9,iColSpan9, oAlignemtnForMultiComponents, bLineWrap);
		this.add_field(oGrid,cNAME10,iColSpan10, oAlignemtnForMultiComponents, bLineWrap);
		this.end_Line(oGrid);
	}


	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,Object cNAME2,Object cNAME3,Object cNAME4,Object cNAME5,Object cNAME6,Object cNAME7,Alignment oAlignemtnForMultiComponents, boolean bLineWrap) throws myException 
	{
		this.add_field(oGrid,cNAME1,1, oAlignemtnForMultiComponents, bLineWrap);
		if (cNAME2 != null) this.add_field(oGrid,cNAME2,1, oAlignemtnForMultiComponents, bLineWrap);
		if (cNAME3 != null) this.add_field(oGrid,cNAME3,1, oAlignemtnForMultiComponents, bLineWrap);
		if (cNAME4 != null) this.add_field(oGrid,cNAME4,1, oAlignemtnForMultiComponents, bLineWrap);
		if (cNAME5 != null) this.add_field(oGrid,cNAME5,1, oAlignemtnForMultiComponents, bLineWrap);
		if (cNAME6 != null) this.add_field(oGrid,cNAME6,1, oAlignemtnForMultiComponents, bLineWrap);
		if (cNAME7 != null) this.add_field(oGrid,cNAME7,1, oAlignemtnForMultiComponents, bLineWrap);
		this.end_Line(oGrid);
	}

	
	//---------------------------------ENDE 2014-07-16
	
	
	public void add_Trenner(MyE2_Grid oGrid, Insets oInsets)
	{
		this.add_Separator(oGrid, oInsets);
	}
	
	public void add_Separator(MyE2_Grid oGrid, Insets oInsets)
	{
		oGrid.add(new Separator(),new ownGridLayoutNormal(oGrid.getSize(),oInsets,null));
		this.end_Line(oGrid);
	}
	


	
	public MyE2_Grid  get_oGridWithComponents(Object cNAME1,Alignment oAlignemtnForMultiComponents) throws myException
	{
		int iSpalten = 0;
		if (cNAME1 != null) iSpalten++;

		MyE2_Grid_InLIST  oGridInList = new MyE2_Grid_InLIST(iSpalten);
		if (cNAME1 != null) this.add_field(oGridInList,cNAME1,1, oAlignemtnForMultiComponents, false);
		return oGridInList;
	}


	public MyE2_Grid  get_oGridWithComponents(Object cNAME1,Object cNAME2 ,Alignment oAlignemtnForMultiComponents) throws myException
	{
		int iSpalten = 0;
		if (cNAME1 != null) iSpalten++;
		if (cNAME2 != null) iSpalten++;

		MyE2_Grid_InLIST  oGridInList = new MyE2_Grid_InLIST(iSpalten);
		
		if (cNAME1 != null) this.add_field(oGridInList,cNAME1,1, oAlignemtnForMultiComponents, false);
		if (cNAME2 != null) this.add_field(oGridInList,cNAME2,1, oAlignemtnForMultiComponents, false);
		
		return oGridInList;
	}
	
	public MyE2_Grid  get_oGridWithComponents(Object cNAME1,Object cNAME2,Object cNAME3,Alignment oAlignemtnForMultiComponents) throws myException
	{
		int iSpalten = 0;
		if (cNAME1 != null) iSpalten++;
		if (cNAME2 != null) iSpalten++;
		if (cNAME3 != null) iSpalten++;

		MyE2_Grid_InLIST  oGridInList = new MyE2_Grid_InLIST(iSpalten);
		
		if (cNAME1 != null) this.add_field(oGridInList,cNAME1,1, oAlignemtnForMultiComponents, false);
		if (cNAME2 != null) this.add_field(oGridInList,cNAME2,1, oAlignemtnForMultiComponents, false);
		if (cNAME3 != null) this.add_field(oGridInList,cNAME3,1, oAlignemtnForMultiComponents, false);
		
		return oGridInList;
	}
	
	public MyE2_Grid  get_oGridWithComponents(Object cNAME1,Object cNAME2,Object cNAME3,Object cNAME4 ,Alignment oAlignemtnForMultiComponents) throws myException
	{
		int iSpalten = 0;
		if (cNAME1 != null) iSpalten++;
		if (cNAME2 != null) iSpalten++;
		if (cNAME3 != null) iSpalten++;
		if (cNAME4 != null) iSpalten++;

		MyE2_Grid_InLIST  oGridInList = new MyE2_Grid_InLIST(iSpalten);
		
		if (cNAME1 != null) this.add_field(oGridInList,cNAME1,1, oAlignemtnForMultiComponents, false);
		if (cNAME2 != null) this.add_field(oGridInList,cNAME2,1, oAlignemtnForMultiComponents, false);
		if (cNAME3 != null) this.add_field(oGridInList,cNAME3,1, oAlignemtnForMultiComponents, false);
		if (cNAME4 != null) this.add_field(oGridInList,cNAME4,1, oAlignemtnForMultiComponents, false);
		
		return oGridInList;
	}
	public MyE2_Grid  get_oGridWithComponents(Object cNAME1,Object cNAME2,Object cNAME3,Object cNAME4,Object cNAME5, Alignment oAlignemtnForMultiComponents) throws myException
	{
		int iSpalten = 0;
		if (cNAME1 != null) iSpalten++;
		if (cNAME2 != null) iSpalten++;
		if (cNAME3 != null) iSpalten++;
		if (cNAME4 != null) iSpalten++;
		if (cNAME5 != null) iSpalten++;

		MyE2_Grid_InLIST  oGridInList = new MyE2_Grid_InLIST(iSpalten);
		
		if (cNAME1 != null) this.add_field(oGridInList,cNAME1,1, oAlignemtnForMultiComponents, false);
		if (cNAME2 != null) this.add_field(oGridInList,cNAME2,1, oAlignemtnForMultiComponents, false);
		if (cNAME3 != null) this.add_field(oGridInList,cNAME3,1, oAlignemtnForMultiComponents, false);
		if (cNAME4 != null) this.add_field(oGridInList,cNAME4,1, oAlignemtnForMultiComponents, false);
		if (cNAME5 != null) this.add_field(oGridInList,cNAME5,1, oAlignemtnForMultiComponents, false);
		
		return oGridInList;
	}
	public MyE2_Grid  get_oGridWithComponents(Object cNAME1,Object cNAME2,Object cNAME3,Object cNAME4,Object cNAME5,Object cNAME6,Alignment oAlignemtnForMultiComponents) throws myException
	{
		int iSpalten = 0;
		if (cNAME1 != null) iSpalten++;
		if (cNAME2 != null) iSpalten++;
		if (cNAME3 != null) iSpalten++;
		if (cNAME4 != null) iSpalten++;
		if (cNAME5 != null) iSpalten++;
		if (cNAME6 != null) iSpalten++;

		MyE2_Grid_InLIST  oGridInList = new MyE2_Grid_InLIST(iSpalten);
		
		if (cNAME1 != null) this.add_field(oGridInList,cNAME1,1, oAlignemtnForMultiComponents, false);
		if (cNAME2 != null) this.add_field(oGridInList,cNAME2,1, oAlignemtnForMultiComponents, false);
		if (cNAME3 != null) this.add_field(oGridInList,cNAME3,1, oAlignemtnForMultiComponents, false);
		if (cNAME4 != null) this.add_field(oGridInList,cNAME4,1, oAlignemtnForMultiComponents, false);
		if (cNAME5 != null) this.add_field(oGridInList,cNAME5,1, oAlignemtnForMultiComponents, false);
		if (cNAME6 != null) this.add_field(oGridInList,cNAME6,1, oAlignemtnForMultiComponents, false);
		
		return oGridInList;
	}

	
	public MyE2_Grid  get_oGridWithComponents(Object cNAME1,Object cNAME2,Object cNAME3,Object cNAME4,Object cNAME5,Object cNAME6,Object cNAME7,Alignment oAlignemtnForMultiComponents) throws myException
	{
		int iSpalten = 0;
		if (cNAME1 != null) iSpalten++;
		if (cNAME2 != null) iSpalten++;
		if (cNAME3 != null) iSpalten++;
		if (cNAME4 != null) iSpalten++;
		if (cNAME5 != null) iSpalten++;
		if (cNAME6 != null) iSpalten++;
		if (cNAME7 != null) iSpalten++;

		MyE2_Grid_InLIST  oGridInList = new MyE2_Grid_InLIST(iSpalten);
		
		if (cNAME1 != null) this.add_field(oGridInList,cNAME1,1, oAlignemtnForMultiComponents, false);
		if (cNAME2 != null) this.add_field(oGridInList,cNAME2,1, oAlignemtnForMultiComponents, false);
		if (cNAME3 != null) this.add_field(oGridInList,cNAME3,1, oAlignemtnForMultiComponents, false);
		if (cNAME4 != null) this.add_field(oGridInList,cNAME4,1, oAlignemtnForMultiComponents, false);
		if (cNAME5 != null) this.add_field(oGridInList,cNAME5,1, oAlignemtnForMultiComponents, false);
		if (cNAME6 != null) this.add_field(oGridInList,cNAME6,1, oAlignemtnForMultiComponents, false);
		if (cNAME7 != null) this.add_field(oGridInList,cNAME7,1, oAlignemtnForMultiComponents, false);
		
		return oGridInList;
	}


	
	
	
	private void add_field(MyE2_Grid oGrid,Object cNAME, int iColSpan, Alignment oAlignemtnForMultiComponents, boolean bLineWrapLables) throws myException 
	{
		if (cNAME == null)
			throw new myException(this,":add_field: null-value not allowed !");
		
		if 		(cNAME instanceof MyString)
		{
			oGrid.add(new MyE2_Label(cNAME,bLineWrapLables),new ownGridLayoutNormal(iColSpan,this.oIN_InfoText,oAlignemtnForMultiComponents));
		}
		else if (cNAME instanceof Component)
		{
			oGrid.add((Component)cNAME,new ownGridLayoutNormal(iColSpan,this.oIN_Komponente,oAlignemtnForMultiComponents));
		}
		else if	(cNAME instanceof String)
		{
			String ccNAME = (String)cNAME;
			if (ccNAME.indexOf("|")>=0)           // dann mehrere komponenten in einem feld unterbringen
			{
				Vector<String> vParts = bibALL.TrenneZeile(ccNAME,"|");
				
				//2013-01-14: umstellung auf maskenelemente, die immer enabled sin
				MyE2_Row_EveryTimeEnabled oRow = new MyE2_Row_EveryTimeEnabled(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
				
				
				for (int i=0;i<vParts.size();i++)
				{
					String cPart=(String)vParts.get(i);

					RowLayoutData oRowLayout = new RowLayoutData();
					oRowLayout.setAlignment(oAlignemtnForMultiComponents);
					
					if (cPart.trim().equals("#"))               // platzhalter ist sowas wie |#   |, fuer jedes leerzeichen wird ein abstand von 10 pixeln eingezogen
					{
						oRowLayout.setInsets(new Insets(0,0,cPart.length()*10,0));
						oRow.add(new Label(" "), oRowLayout);
					}
					else
					{
						StringBuffer strHelp = new StringBuffer(cPart);

						analyseTags an= new analyseTags(strHelp);
				
						strHelp = an.cCleanFeldName;
						
						/*
						 * 20101007: aenderung in maske: flexible einrueckung mit <L122L>  <R344R> 
						 */
						Insets iIn = new Insets(an.hmINT.get("L"),0,an.hmINT.get("R")==0?5:an.hmINT.get("R"),0);

						//jetzt fuer die moeglichen typen noch die breite auf der maske definieren
						Component oComp = this.take_component(strHelp.toString(), bLineWrapLables);
						
						Integer iWidth = an.hmINT.get("W");
						if (iWidth!=null && iWidth>0)
						{
							this.check_component_and_format_width(oComp, iWidth);

						}
								
								
						Integer iHeight = an.hmINT.get("H");
						if (iHeight!=null && iHeight>0)
						{
							this.check_component_and_format_height(oComp, iHeight);

						}
						
						oRowLayout.setInsets(iIn);
						oRow.add(oComp,oRowLayout);
					}
					
				}
				oGrid.add(oRow,new ownGridLayoutNormal(iColSpan,this.oIN_Komponente,oAlignemtnForMultiComponents));
			}
			else
			{
				StringBuffer cHelpName = new StringBuffer((String)cNAME);
				
				analyseTags an= new analyseTags(cHelpName);
				
				Component oComp = this.take_component(an.cCleanFeldName.toString(), bLineWrapLables);
				
				Integer iWidth = an.hmINT.get("W");
				if (iWidth!=null && iWidth>0)
				{
					this.check_component_and_format_width(oComp, iWidth);
				}
				
				Integer iHeight = an.hmINT.get("H");
				if (iHeight!=null && iHeight>0)
				{
					this.check_component_and_format_height(oComp, iHeight);
				}

				oGrid.add(oComp,new ownGridLayoutNormal(iColSpan,this.oIN_Komponente,oAlignemtnForMultiComponents));
			}
			
		}
		else
			throw new myException(this,":add_field:only Types MyString and String are allowed ");
		
		
	}

	
	

	
	
	
	
	
	
	private void check_component_and_format_width(Component oComp, Integer iWidth)
	{
		if (oComp == null || iWidth ==null || iWidth.intValue()==0) 
		{
			return;
		}
		
		if (oComp instanceof TextField) 		{((TextField)oComp).setWidth(new Extent(iWidth));} 
		if (oComp instanceof SelectField) 		{((SelectField)oComp).setWidth(new Extent(iWidth));} 
		if (oComp instanceof TextArea) 			{((TextArea)oComp).setWidth(new Extent(iWidth));} 
		if (oComp  instanceof  MyE2_DB_TextArea_WithSelektor) 	{((MyE2_DB_TextArea_WithSelektor)oComp).get_oTextArea().setWidth(new Extent(iWidth));}

	}

	
	private void check_component_and_format_height(Component oComp, Integer iHeight)
	{
		if (oComp == null || iHeight ==null || iHeight.intValue()==0) 
		{
			return;
		}
		if (oComp instanceof MyE2_TextArea) {((MyE2_TextArea)oComp).set_iRows(iHeight);};
		if (oComp  instanceof  MyE2_DB_TextArea_WithSelektor) 	{((MyE2_DB_TextArea_WithSelektor)oComp).get_oTextArea().set_iRows(iHeight);}
	}

	
	
	private class analyseTags
	{

		public StringBuffer 	cCleanFeldName = null;
		public HashMap<String,Integer>  hmINT = new HashMap<String, Integer>();
		
		public analyseTags(StringBuffer cOriginalFeldName) throws myException 
		{
			super();
			
			StringBuffer strHelp = new StringBuffer(cOriginalFeldName);
			
			//jetzt die tags durchgehen
			strHelp = this.readValue(strHelp, "L");
			strHelp = this.readValue(strHelp, "R");
			strHelp = this.readValue(strHelp, "W");
			strHelp = this.readValue(strHelp, "H");
			

			this.cCleanFeldName = strHelp;
		}
		
		
		private StringBuffer readValue(StringBuffer cTempString, String cHash) throws myException
		{
			Integer iLeft = S.get_InWertInStringCode(cTempString, cHash);
			if (iLeft!=null)
			{
				this.hmINT.put(cHash, iLeft);
			}
			else
			{
				this.hmINT.put(cHash, 0);
			}

			return cTempString;
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,GridLayoutData oGridLayout1) throws myException 
	{
		this.add_field(oGrid,cNAME1,oGridLayout1);
		this.end_Line(oGrid);
	}
	

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,GridLayoutData oGridLayout1,Object cNAME2,GridLayoutData oGridLayout2) throws myException 
	{
		this.add_field(oGrid,cNAME1,oGridLayout1);
		this.add_field(oGrid,cNAME2,oGridLayout2);
		this.end_Line(oGrid);
	}

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,GridLayoutData oGridLayout1,Object cNAME2,GridLayoutData oGridLayout2,Object cNAME3,GridLayoutData oGridLayout3) throws myException 
	{
		this.add_field(oGrid,cNAME1,oGridLayout1);
		this.add_field(oGrid,cNAME2,oGridLayout2);
		this.add_field(oGrid,cNAME3,oGridLayout3);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,GridLayoutData oGridLayout1,Object cNAME2,GridLayoutData oGridLayout2,Object cNAME3,GridLayoutData oGridLayout3,Object cNAME4,GridLayoutData oGridLayout4) throws myException 
	{
		this.add_field(oGrid,cNAME1,oGridLayout1);
		this.add_field(oGrid,cNAME2,oGridLayout2);
		this.add_field(oGrid,cNAME3,oGridLayout3);
		this.add_field(oGrid,cNAME4,oGridLayout4);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,GridLayoutData oGridLayout1,Object cNAME2,GridLayoutData oGridLayout2,Object cNAME3,GridLayoutData oGridLayout3,Object cNAME4,GridLayoutData oGridLayout4,Object cNAME5,GridLayoutData oGridLayout5) throws myException 
	{
		this.add_field(oGrid,cNAME1,oGridLayout1);
		this.add_field(oGrid,cNAME2,oGridLayout2);
		this.add_field(oGrid,cNAME3,oGridLayout3);
		this.add_field(oGrid,cNAME4,oGridLayout4);
		this.add_field(oGrid,cNAME5,oGridLayout5);
		this.end_Line(oGrid);
	}
	
	public void add_Line(MyE2_Grid oGrid,Object cNAME1,GridLayoutData oGridLayout1,Object cNAME2,GridLayoutData oGridLayout2,Object cNAME3,GridLayoutData oGridLayout3,Object cNAME4,GridLayoutData oGridLayout4,Object cNAME5,GridLayoutData oGridLayout5,Object cNAME6,GridLayoutData oGridLayout6) throws myException 
	{
		this.add_field(oGrid,cNAME1,oGridLayout1);
		this.add_field(oGrid,cNAME2,oGridLayout2);
		this.add_field(oGrid,cNAME3,oGridLayout3);
		this.add_field(oGrid,cNAME4,oGridLayout4);
		this.add_field(oGrid,cNAME5,oGridLayout5);
		this.add_field(oGrid,cNAME6,oGridLayout6);
		this.end_Line(oGrid);
	}

	public void add_Line(MyE2_Grid oGrid,Object cNAME1,GridLayoutData oGridLayout1,Object cNAME2,GridLayoutData oGridLayout2,Object cNAME3,GridLayoutData oGridLayout3,Object cNAME4,GridLayoutData oGridLayout4,Object cNAME5,GridLayoutData oGridLayout5,Object cNAME6,GridLayoutData oGridLayout6,Object cNAME7,GridLayoutData oGridLayout7) throws myException 
	{
		this.add_field(oGrid,cNAME1,oGridLayout1);
		this.add_field(oGrid,cNAME2,oGridLayout2);
		this.add_field(oGrid,cNAME3,oGridLayout3);
		this.add_field(oGrid,cNAME4,oGridLayout4);
		this.add_field(oGrid,cNAME5,oGridLayout5);
		this.add_field(oGrid,cNAME6,oGridLayout6);
		this.add_field(oGrid,cNAME7,oGridLayout7);
		this.end_Line(oGrid);
	}

	
	
	
	
	
	
	
	
	
	private void add_field(MyE2_Grid oGrid,Object cNAME, GridLayoutData o_Layout) throws myException 
	{
		if (cNAME == null)
			throw new myException(this,":add_field: null-value not allowed !");
		
		
		//eigenes GridLayout rausziehen
		GridLayoutData oLayout = new GridLayoutData();
		oLayout.setAlignment(o_Layout.getAlignment());
		oLayout.setInsets(o_Layout.getInsets());
		oLayout.setBackground(o_Layout.getBackground());
		oLayout.setBackgroundImage(o_Layout.getBackgroundImage());
		oLayout.setRowSpan(o_Layout.getRowSpan());
		oLayout.setColumnSpan(o_Layout.getColumnSpan());
		
		
		
		if 		(cNAME instanceof MyString)
		{
			oGrid.add(new MyE2_Label(cNAME),oLayout);
		}
		else if (cNAME instanceof Component)
		{
			oGrid.add((Component)cNAME,oLayout);
		}
		else if	(cNAME instanceof String)
		{
			String ccNAME = (String)cNAME;
			if (ccNAME.indexOf("|")>=0)           // dann mehrere komponenten in einem feld unterbringen
			{
				Vector<String> vParts = bibALL.TrenneZeile(ccNAME,"|");
				MyE2_Row oRow = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
				for (int i=0;i<vParts.size();i++)
				{
					String cPart=(String)vParts.get(i);

					RowLayoutData oRowLayout = new RowLayoutData();
					oRowLayout.setAlignment(oLayout.getAlignment());
					oRowLayout.setInsets(oLayout.getInsets());
					oRowLayout.setBackground(oLayout.getBackground());
					oRowLayout.setBackgroundImage(oLayout.getBackgroundImage());
					
					if (cPart.trim().equals("#"))               // platzhalter ist sowas wie |#   |, fuer jedes leerzeichen wird ein abstand von 10 pixeln eingezogen
					{
						oRowLayout.setInsets(new Insets(0,0,cPart.length()*10,0));
						oRow.add(new Label(" "), oRowLayout);
					}
					else
					{
						StringBuffer strHelp = new StringBuffer(cPart);
						Integer iLeft = S.get_InWertInStringCode(strHelp, "L");
						Integer iRight = S.get_InWertInStringCode(strHelp, "R");
						
						Integer iWidth = S.get_InWertInStringCode(strHelp, "W");
						
						/*
						 * 20101007: aenderung in maske: flexible einrueckung mit <L122L>  <R344R> 
						 */
						Insets iIn = new Insets(iLeft==null?0:iLeft.intValue(),0,iRight==null?5:iRight.intValue(),0);
						
						// oRowLayout.setInsets(E2_INSETS.I_0_0_5_0);
						oRowLayout.setInsets(iIn);
						
						//jetzt fuer die moeglichen typen noch die breite auf der maske definieren
						Component oComp = this.take_component(strHelp.toString(), false);
						if (oComp instanceof TextField && iWidth!=null && iWidth>0) {((TextField)oComp).setWidth(new Extent(iWidth));} 
						if (oComp instanceof SelectField && iWidth!=null && iWidth>0) {((SelectField)oComp).setWidth(new Extent(iWidth));} 
						if (oComp instanceof TextArea && iWidth!=null && iWidth>0) {((TextArea)oComp).setWidth(new Extent(iWidth));} 
						
						
						oRow.add(oComp,oRowLayout);
					}
					
				}
				oGrid.add(oRow,oLayout);
			}
			else
			{
				Component oComp = this.take_component((String)cNAME, false);
				oGrid.add(oComp,oLayout);
			}
			
		}
		else
			throw new myException(this,":add_field:only Types MyString and String are allowed ");
		
		
	}

	
	
	
	
	
	
	
	private Component take_component(String cNAME, boolean bLineWrapLabel) throws myException
	{
		if (cNAME.startsWith("#"))
			return new MyE2_Label(new MyE2_String(cNAME.substring(1)),bLineWrapLabel);
		
		
		Component ocomp1 = null;
		Component ocomp2 = null;
		Component ocomp3 = null;

		try
		{
			ocomp1 = this.oMap1.get_Comp(cNAME);
		}
		catch (myException ex ){}
		
		if(this.oMap2 != null)
		{
			try
			{
				ocomp2 = this.oMap2.get_Comp(cNAME);
			}
			catch (myException ex ){}
		}
		
		if(this.oMap3 != null)
		{
			try
			{
				ocomp3 = this.oMap3.get_Comp(cNAME);
			}
			catch (myException ex ){}
		}
			
		int iCountFound = 0;
		
		if (ocomp1 != null) iCountFound++;
		if (ocomp2 != null) iCountFound++;
		if (ocomp3 != null) iCountFound++;
		
		if (iCountFound > 1)
			throw new myException(this,":add_field:the key is not singular: "+(String)cNAME);
		
		if (iCountFound == 0)
			throw new myException(this,":add_field:the key is not found: "+(String)cNAME);
		
		if 		(ocomp1 != null)
			return ocomp1;
		else if (ocomp2 != null)
			return ocomp2;
		else if (ocomp3 != null)
			return ocomp3;
		else 
			return new Label("");
		
	}
	

	
	private Component take_componentNG(String cNAME, boolean bLineWrapLabel) throws myException
	{
	
		//zuerst testen, ob es ein key ist
		if (	 this.oMap1.containsKey(cNAME) || 
				(this.oMap2!=null && this.oMap2.containsKey(cNAME)) ||
				(this.oMap3!=null && this.oMap3.containsKey(cNAME)) 
			)
		{

			Component ocomp1 = null;
			Component ocomp2 = null;
			Component ocomp3 = null;
	
			try
			{
				ocomp1 = this.oMap1.get_Comp(cNAME);
			}
			catch (myException ex ){}
			
			if(this.oMap2 != null)
			{
				try
				{
					ocomp2 = this.oMap2.get_Comp(cNAME);
				}
				catch (myException ex ){}
			}
			
			if(this.oMap3 != null)
			{
				try
				{
					ocomp3 = this.oMap3.get_Comp(cNAME);
				}
				catch (myException ex ){}
			}
				
			int iCountFound = 0;
			
			if (ocomp1 != null) iCountFound++;
			if (ocomp2 != null) iCountFound++;
			if (ocomp3 != null) iCountFound++;
			
			if (iCountFound > 1)
				throw new myException(this,":add_field:the key is not singular: "+(String)cNAME);
			
			if (iCountFound == 0)
				throw new myException(this,":add_field:the key is not found: "+(String)cNAME);
			
			if 		(ocomp1 != null)
				return ocomp1;
			else if (ocomp2 != null)
				return ocomp2;
			else if (ocomp3 != null)
				return ocomp3;
			else 
				return new Label("");
		}
		else
		{
			return new MyE2_Label(new MyE2_String(cNAME),bLineWrapLabel);
		}
		
	}

	
	
	public GridLayoutData get_ownGridLayoutNormal(int iColSpan, Insets oInsets, Alignment oAlignment)
	{
		return new ownGridLayoutNormal(iColSpan, oInsets, oAlignment);
	}
	
	
	private class ownGridLayoutNormal extends GridLayoutData
	{
		public ownGridLayoutNormal(int iColSpan, Insets oInsets, Alignment oAlignment)
		{
			super();
			if (oInsets != null)
				this.setInsets(oInsets);
			
			if (iColSpan>1)
				this.setColumnSpan(iColSpan);
			
			if (oAlignment==null)
			{
				this.setAlignment(E2_MaskFiller.this.BasicAlignment);
			}
			else
			{
				this.setAlignment(oAlignment);
			}
			
			if (E2_MaskFiller.this.oActualColor != null)
			{
				this.setBackground(E2_MaskFiller.this.oActualColor);
			}
			
			
		}
		
	}


	
	
	/**
	 * 
	 * @param oGrid
	 * @param gl4Block  (can be NULL, then standard)
	 * @param vFieldDefs  (besteht aus String mit nummern-Tags wie "NAME<B100B><W50>", d.h. name mit 100 pixel-breite im Rahmen und Textfeldbreite 50)
	 *                    Moegliche Tags: B = Breite des unsichbaren Rahmens um das Objekt
	 *                                    W = Breite bei Feldern, wo das moeglich ist
	 *                                    H = Hoehe bei Feldern, wo das moeglich ist
	 * @param iColSpan4Block
	 * @throws myException
	 */
	public void BlockAdd(MyE2_Grid oGrid, GridLayoutData gl4Block, Vector<String> vFieldDefs,  int iColSpan4Block) throws myException
	{

		if (oGrid == null ||vFieldDefs == null)
		{
			throw new myException(this,"Null-Value is not allowed !!");
		}

		GridLayoutData oLayout4GanzenBlock = gl4Block;
		if (gl4Block==null)
		{
			oLayout4GanzenBlock = new ownGridLayoutNormal(iColSpan4Block,this.oIN_Komponente,this.BasicAlignment);
		}
		else
		{
			oLayout4GanzenBlock = LayoutDataFactory.get_GL_Copy(gl4Block);
			oLayout4GanzenBlock.setColumnSpan(iColSpan4Block);
		}
		
		
		

		MyE2_Grid oGridBlock = new MyE2_Grid(vFieldDefs.size(), MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		for (int i=0;i<vFieldDefs.size();i++)
		{
			String cField = vFieldDefs.get(i);
			
			//jetzt die tags pruefen 
			S.Tags  oTags = new S.Tags(cField,  bibALL.get_Vector("B","W","H"));

			
			int 	iBreite = 		oTags.getHmINT().get("B")==null?100:oTags.getHmINT().get("B");              //Breite des umhuellenden Rahmens / immer was da
			Integer iFeldBreite = 	oTags.getHmINT().get("W")==null?null:oTags.getHmINT().get("W");              //Breite des inneren Feld  / kann null sein
			Integer iFeldHoehe = 	oTags.getHmINT().get("H")==null?null:oTags.getHmINT().get("H");              //Hoehe des inneren Feld   / kann null sein
			
			Component  oComp = this.take_componentNG(oTags.get_cCleanFeldName(), false);
			
			if (iFeldBreite != null) this.check_component_and_format_width(oComp, iFeldBreite);
			if (iFeldHoehe != null) this.check_component_and_format_height(oComp, iFeldHoehe);
			
			MutableStyle  oStyle = new MutableStyle();
			oStyle.setProperty(Grid.PROPERTY_BORDER, new Border(0,Color.BLACK,Border.STYLE_NONE));
			oStyle.setProperty(Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
			oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(iBreite));
			
			
			
			MyE2_Grid oGridInnen = new MyE2_Grid(1,oStyle);
			oGridInnen.add(oComp);
			oGridBlock.add(oGridInnen,E2_INSETS.I_0_0_0_0);
		}
		
		oGrid.add(oGridBlock, oLayout4GanzenBlock);
		
	}
		
	
	
	public Insets get_oINSETS_InfoText() {
		return oIN_InfoText;
	}



	public void set_oINSETS_InfoText(Insets IN_InfoText) {
		this.oIN_InfoText = IN_InfoText;
	}



	public Insets get_oINSETS_Komponente() {
		return oIN_Komponente;
	}



	public void set_oINSETS_Komponente(Insets IN_Komponente) {
		this.oIN_Komponente = IN_Komponente;
	}


	
	
	//2013-11-15: add-line jeweils ohne zeilenabschluss
	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, BasicAlignment);
	}
	

	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, this.BasicAlignment);
	}

	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, this.BasicAlignment);
	}
	
	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, this.BasicAlignment);
	}
	
	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME5,iColSpan5, this.BasicAlignment);
	}
	
	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Object cNAME6,int iColSpan6) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME5,iColSpan5, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME6,iColSpan6, this.BasicAlignment);
	}

	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Object cNAME6,int iColSpan6,Object cNAME7,int iColSpan7) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME5,iColSpan5, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME6,iColSpan6, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME7,iColSpan7, this.BasicAlignment);
	}


	public void add_Line_OA(MyE2_Grid oGrid,	
							Object cNAME1,int iColSpan1,
							Object cNAME2,int iColSpan2,
							Object cNAME3,int iColSpan3,
							Object cNAME4,int iColSpan4,
							Object cNAME5,int iColSpan5,
							Object cNAME6,int iColSpan6,
							Object cNAME7,int iColSpan7,
							Object cNAME8,int iColSpan8
							) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME5,iColSpan5, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME6,iColSpan6, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME7,iColSpan7, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME8,iColSpan8, this.BasicAlignment);
	}

	
	
	public void add_Line_OA(MyE2_Grid oGrid,	
						Object cNAME1,int iColSpan1,
						Object cNAME2,int iColSpan2,
						Object cNAME3,int iColSpan3,
						Object cNAME4,int iColSpan4,
						Object cNAME5,int iColSpan5,
						Object cNAME6,int iColSpan6,
						Object cNAME7,int iColSpan7,
						Object cNAME8,int iColSpan8,
						Object cNAME9,int iColSpan9
						) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME5,iColSpan5, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME6,iColSpan6, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME7,iColSpan7, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME8,iColSpan8, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME9,iColSpan9, this.BasicAlignment);
	}


	public void add_Line_OA(MyE2_Grid oGrid,	
			Object cNAME1,int iColSpan1,
			Object cNAME2,int iColSpan2,
			Object cNAME3,int iColSpan3,
			Object cNAME4,int iColSpan4,
			Object cNAME5,int iColSpan5,
			Object cNAME6,int iColSpan6,
			Object cNAME7,int iColSpan7,
			Object cNAME8,int iColSpan8,
			Object cNAME9,int iColSpan9,
			Object cNAME10,int iColSpan10
			) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME5,iColSpan5, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME6,iColSpan6, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME7,iColSpan7, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME8,iColSpan8, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME9,iColSpan9, this.BasicAlignment);
		this.add_field_RAW(oGrid,cNAME10,iColSpan10, this.BasicAlignment);
	}


	
	
	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,Object cNAME2,Object cNAME3,Object cNAME4,Object cNAME5,Object cNAME6,Object cNAME7) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,1, this.BasicAlignment);
		if (cNAME2 != null) this.add_field_RAW(oGrid,cNAME2,1, this.BasicAlignment);
		if (cNAME3 != null) this.add_field_RAW(oGrid,cNAME3,1, this.BasicAlignment);
		if (cNAME4 != null) this.add_field_RAW(oGrid,cNAME4,1, this.BasicAlignment);
		if (cNAME5 != null) this.add_field_RAW(oGrid,cNAME5,1, this.BasicAlignment);
		if (cNAME6 != null) this.add_field_RAW(oGrid,cNAME6,1, this.BasicAlignment);
		if (cNAME7 != null) this.add_field_RAW(oGrid,cNAME7,1, this.BasicAlignment);
	}

	
	
	///
	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Alignment oAlignment) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, oAlignment);
	}
	

	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Alignment oAlignment) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, oAlignment);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, oAlignment);
	}

	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Alignment oAlignment) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, oAlignment);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, oAlignment);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, oAlignment);
	}
	
	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Alignment oAlignment) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, oAlignment);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, oAlignment);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, oAlignment);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, oAlignment);
	}
	
	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Alignment oAlignment) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, oAlignment);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, oAlignment);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, oAlignment);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, oAlignment);
		this.add_field_RAW(oGrid,cNAME5,iColSpan5, oAlignment);
	}
	
	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Object cNAME6,int iColSpan6,Alignment oAlignemtnForMultiComponents) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents);
	}

	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,int iColSpan1,Object cNAME2,int iColSpan2,Object cNAME3,int iColSpan3,Object cNAME4,int iColSpan4,Object cNAME5,int iColSpan5,Object cNAME6,int iColSpan6,Object cNAME7,int iColSpan7,Alignment oAlignemtnForMultiComponents) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME7,iColSpan7, oAlignemtnForMultiComponents);
	}

	
	
	public void add_Line_OA(MyE2_Grid oGrid, 
					Object cNAME1,int iColSpan1,
					Object cNAME2,int iColSpan2,
					Object cNAME3,int iColSpan3,
					Object cNAME4,int iColSpan4,
					Object cNAME5,int iColSpan5,
					Object cNAME6,int iColSpan6,
					Object cNAME7,int iColSpan7,
					Object cNAME8,int iColSpan8,
					Alignment oAlignemtnForMultiComponents) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME7,iColSpan7, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME8,iColSpan8, oAlignemtnForMultiComponents);
	}

	
	public void add_Line_OA(MyE2_Grid oGrid, 
			Object cNAME1,int iColSpan1,
			Object cNAME2,int iColSpan2,
			Object cNAME3,int iColSpan3,
			Object cNAME4,int iColSpan4,
			Object cNAME5,int iColSpan5,
			Object cNAME6,int iColSpan6,
			Object cNAME7,int iColSpan7,
			Object cNAME8,int iColSpan8,
			Object cNAME9,int iColSpan9,
			Alignment oAlignemtnForMultiComponents) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME7,iColSpan7, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME8,iColSpan8, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME9,iColSpan9, oAlignemtnForMultiComponents);
	}



	public void add_Line_OA(MyE2_Grid oGrid, 
			Object cNAME1,int iColSpan1,
			Object cNAME2,int iColSpan2,
			Object cNAME3,int iColSpan3,
			Object cNAME4,int iColSpan4,
			Object cNAME5,int iColSpan5,
			Object cNAME6,int iColSpan6,
			Object cNAME7,int iColSpan7,
			Object cNAME8,int iColSpan8,
			Object cNAME9,int iColSpan9,
			Object cNAME10,int iColSpan10,
			Alignment oAlignemtnForMultiComponents) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,iColSpan1, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME2,iColSpan2, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME3,iColSpan3, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME4,iColSpan4, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME5,iColSpan5, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME6,iColSpan6, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME7,iColSpan7, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME8,iColSpan8, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME9,iColSpan9, oAlignemtnForMultiComponents);
		this.add_field_RAW(oGrid,cNAME10,iColSpan10, oAlignemtnForMultiComponents);
	}


	
	public void add_Line_OA(MyE2_Grid oGrid,Object cNAME1,Object cNAME2,Object cNAME3,Object cNAME4,Object cNAME5,Object cNAME6,Object cNAME7,Alignment oAlignemtnForMultiComponents) throws myException 
	{
		this.add_field_RAW(oGrid,cNAME1,1, oAlignemtnForMultiComponents);
		if (cNAME2 != null) this.add_field_RAW(oGrid,cNAME2,1, oAlignemtnForMultiComponents);
		if (cNAME3 != null) this.add_field_RAW(oGrid,cNAME3,1, oAlignemtnForMultiComponents);
		if (cNAME4 != null) this.add_field_RAW(oGrid,cNAME4,1, oAlignemtnForMultiComponents);
		if (cNAME5 != null) this.add_field_RAW(oGrid,cNAME5,1, oAlignemtnForMultiComponents);
		if (cNAME6 != null) this.add_field_RAW(oGrid,cNAME6,1, oAlignemtnForMultiComponents);
		if (cNAME7 != null) this.add_field_RAW(oGrid,cNAME7,1, oAlignemtnForMultiComponents);
	}


	private void add_field_RAW(MyE2_Grid oGrid,Object cNAME, int iColSpan, Alignment oAlignemtnForMultiComponents) throws myException 
	{
		if (cNAME == null)
			throw new myException(this,":add_field: null-value not allowed !");
		
		if 		(cNAME instanceof MyString)
		{
			oGrid.add_RAW(new MyE2_Label(cNAME),new ownGridLayoutNormal(iColSpan,this.oIN_InfoText,oAlignemtnForMultiComponents));
		}
		else if (cNAME instanceof Component)
		{
			oGrid.add_RAW((Component)cNAME,new ownGridLayoutNormal(iColSpan,this.oIN_Komponente,oAlignemtnForMultiComponents));
		}
		else if	(cNAME instanceof String)
		{
			String ccNAME = (String)cNAME;
			if (ccNAME.indexOf("|")>=0)           // dann mehrere komponenten in einem feld unterbringen
			{
				Vector<String> vParts = bibALL.TrenneZeile(ccNAME,"|");
				
				//2013-01-14: umstellung auf maskenelemente, die immer enabled sin
				MyE2_Row_EveryTimeEnabled oRow = new MyE2_Row_EveryTimeEnabled(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
				
				
				for (int i=0;i<vParts.size();i++)
				{
					String cPart=(String)vParts.get(i);

					RowLayoutData oRowLayout = new RowLayoutData();
					oRowLayout.setAlignment(oAlignemtnForMultiComponents);
					
					if (cPart.trim().equals("#"))               // platzhalter ist sowas wie |#   |, fuer jedes leerzeichen wird ein abstand von 10 pixeln eingezogen
					{
						oRowLayout.setInsets(new Insets(0,0,cPart.length()*10,0));
						oRow.add(new Label(" "), oRowLayout);
					}
					else
					{
						StringBuffer strHelp = new StringBuffer(cPart);

						analyseTags an= new analyseTags(strHelp);
				
						strHelp = an.cCleanFeldName;
						
						/*
						 * 20101007: aenderung in maske: flexible einrueckung mit <L122L>  <R344R> 
						 */
						Insets iIn = new Insets(an.hmINT.get("L"),0,an.hmINT.get("R")==0?5:an.hmINT.get("R"),0);

						//jetzt fuer die moeglichen typen noch die breite auf der maske definieren
						Component oComp = this.take_component(strHelp.toString(), false);
						
						Integer iWidth = an.hmINT.get("W");
						if (iWidth!=null && iWidth>0)
						{
							this.check_component_and_format_width(oComp, iWidth);

						}
								
								
						Integer iHeight = an.hmINT.get("H");
						if (iHeight!=null && iHeight>0)
						{
							this.check_component_and_format_height(oComp, iHeight);

						}
						
						oRowLayout.setInsets(iIn);
						oRow.add(oComp,oRowLayout);
					}
					
				}
				oGrid.add_RAW(oRow,new ownGridLayoutNormal(iColSpan,this.oIN_Komponente,oAlignemtnForMultiComponents));
			}
			else
			{
				StringBuffer cHelpName = new StringBuffer((String)cNAME);
				
				analyseTags an= new analyseTags(cHelpName);
				
				Component oComp = this.take_component(an.cCleanFeldName.toString(), false);
				
				Integer iWidth = an.hmINT.get("W");
				if (iWidth!=null && iWidth>0)
				{
//					if (oComp instanceof TextField) 						{((TextField)oComp).setWidth(new Extent(iWidth));} 
//					if (oComp instanceof SelectField) 						{((SelectField)oComp).setWidth(new Extent(iWidth));} 
//					if (oComp instanceof TextArea) 							{((TextArea)oComp).setWidth(new Extent(iWidth));}
//					if (oComp  instanceof  MyE2_DB_TextArea_WithSelektor) 	{((MyE2_DB_TextArea_WithSelektor)oComp).get_oTextArea().setWidth(new Extent(iWidth));}
					this.check_component_and_format_width(oComp, iWidth);
				}
				
				Integer iHeight = an.hmINT.get("H");
				if (iHeight!=null && iHeight>0)
				{
//					if (oComp instanceof MyE2_TextArea) {((MyE2_TextArea)oComp).set_iRows(iHeight);};
//					if (oComp  instanceof  MyE2_DB_TextArea_WithSelektor) 	{((MyE2_DB_TextArea_WithSelektor)oComp).get_oTextArea().set_iRows(iHeight);}
					
					this.check_component_and_format_height(oComp, iHeight);
				}

				oGrid.add_RAW(oComp,new ownGridLayoutNormal(iColSpan,this.oIN_Komponente,oAlignemtnForMultiComponents));
			}
			
		}
		else
			throw new myException(this,":add_field:only Types MyString and String are allowed ");
		
		
	}

	

	
	
	
}
