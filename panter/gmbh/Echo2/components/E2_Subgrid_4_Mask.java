package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/*
 * komponente erzeugt ein Grid, das in einer maske 2-zeilige anzeige mit ueberschriften ueber den feldern baut
 */
public class E2_Subgrid_4_Mask extends MyE2_Grid_InLIST
{

	/**
	 * 
	 * @param cTitelPartsFor_TranslationLabels (titelbezeichner, getrennt durch | - werden uebersetzt)
	 * @param FieldNames  (Feldbezeichner, getrennt durch | )
	 * @param oMap
	 * @param Insets4Titles
	 * @param Insets4Fields
	 * @param numCols
	 * @param style
	 */
	public E2_Subgrid_4_Mask(String cTitelPartsFor_TranslationLabels, String FieldNames, E2_ComponentMAP oMap, Insets Insets4Titles, Insets Insets4Fields) throws myException
	{
		super(MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
		
		Vector<String>  vTitles = bibALL.TrenneZeile(cTitelPartsFor_TranslationLabels, "|");
		Vector<String>  vFieldNames = bibALL.TrenneZeile(FieldNames, "|");
		
		if (vTitles.size() != vFieldNames.size())
			throw new myException(this,"Number of titles must be same as number of fields !!");
		

		this.setSize(vTitles.size());
		
		for (int i=0;i<vTitles.size();i++)
		{
			this.add(new MyE2_Label(new MyE2_String(vTitles.get(i)),MyE2_Label.STYLE_SMALL_ITALIC()), Insets4Titles==null?E2_INSETS.I_1_1_5_1:Insets4Titles);
		}
		
		
		for (int i=0;i<vFieldNames.size();i++)
		{
			if (S.isEmpty(vFieldNames.get(i)))
			{
				this.add(new MyE2_Label(""), Insets4Fields==null?E2_INSETS.I_1_1_5_1:Insets4Fields);
			}
			else
			{
				this.add(oMap.get_Comp(vFieldNames.get(i)), Insets4Fields==null?E2_INSETS.I_1_1_5_1:Insets4Fields);
			}
		}
		
	}
	

	
	
	
	/**
	 * 
	 * @param vComponentsTitel titelkomponenten
	 * @param FieldNames  (Feldbezeichner, getrennt durch | )
	 * @param oMap
	 * @param Insets4Titles
	 * @param Insets4Fields
	 * @param numCols
	 * @param style
	 */
	public E2_Subgrid_4_Mask(Vector<Component> vComponentsTitel, String FieldNames, E2_ComponentMAP oMap, Insets Insets4Titles, Insets Insets4Fields) throws myException
	{
		super(MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
		
		Vector<String>  vFieldNames = bibALL.TrenneZeile(FieldNames, "|");
		
		if (vComponentsTitel.size() != vFieldNames.size())
			throw new myException(this,"Number of titles must be same as number of fields !!");
		

		this.setSize(vComponentsTitel.size());
		
		for (int i=0;i<vComponentsTitel.size();i++)
		{
			this.add(vComponentsTitel.get(i), Insets4Titles==null?E2_INSETS.I_1_1_5_1:Insets4Titles);
		}
		
		
		for (int i=0;i<vFieldNames.size();i++)
		{
			if (S.isEmpty(vFieldNames.get(i)))
			{
				this.add(new MyE2_Label(""), Insets4Fields==null?E2_INSETS.I_1_1_5_1:Insets4Fields);
			}
			else
			{
				this.add(oMap.get_Comp(vFieldNames.get(i)), Insets4Fields==null?E2_INSETS.I_1_1_5_1:Insets4Fields);
			}
		}
		
	}
	

	public E2_Subgrid_4_Mask() throws myException
	{
		super(MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
	}

	
	
	
	
	
	/**
	 * 
	 * @param cTitelPartsFor_TranslationLabels (titelbezeichner, getrennt durch | - werden uebersetzt)
	 * @param FieldNames  (Feldbezeichner, getrennt durch | )
	 * @param oMap
	 * @param Insets4Titles
	 * @param Insets4Fields
	 * @param numCols
	 * @param style
	 */
	public E2_Subgrid_4_Mask(E2_ComponentMAP oMap, String cTitelPartsFor_TranslationLabels, String FieldNames, GridLayoutData oGL4Titles, GridLayoutData oGL4Fields) throws myException
	{
		super(MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
		
//		Vector<String>  vTitles = bibALL.TrenneZeile(cTitelPartsFor_TranslationLabels, "|");
//		Vector<String>  vFieldNames = bibALL.TrenneZeile(FieldNames, "|");
//		
//		if (vTitles.size() != vFieldNames.size())
//			throw new myException(this,"Number of titles must be same as number of fields !!");
//		
//
//		this.setSize(vTitles.size());
//		
//		for (int i=0;i<vTitles.size();i++)
//		{
//			GridLayoutData oGL = oGL4Titles;
//			if (oGL==null)
//			{
//				oGL=LayoutDataFactory.get_GridLayoutGridLeft(E2_INSETS.I_1_1_1_1);
//			}
//			
//			this.add(new MyE2_Label(new MyE2_String(vTitles.get(i)),MyE2_Label.STYLE_SMALL_ITALIC()), oGL);
//		}
//		
//		
//		for (int i=0;i<vFieldNames.size();i++)
//		{
//			GridLayoutData oGL = oGL4Fields;
//			if (oGL==null)
//			{
//				oGL=LayoutDataFactory.get_GridLayoutGridLeft(E2_INSETS.I_1_1_1_1);
//			}
//			
//			if (S.isEmpty(vFieldNames.get(i)))
//			{
//				this.add(new MyE2_Label(""), oGL);
//			}
//			else
//			{
//				this.add(oMap.get_Comp(vFieldNames.get(i)), oGL);
//			}
//		}

		this.FILL_WITH(oMap, cTitelPartsFor_TranslationLabels, FieldNames, oGL4Titles, oGL4Fields);
		
	}

	
	
	/**
	 * 
	 * @param oMap
	 * @param cTitelPartsFor_TranslationLabels
	 * @param FieldNames
	 * @param oGL4Titles
	 * @param oGL4Fields
	 * @throws myException
	 */
	public void FILL_WITH(E2_ComponentMAP oMap, String cTitelPartsFor_TranslationLabels, String FieldNames, GridLayoutData oGL4Titles, GridLayoutData oGL4Fields) throws myException {
		
		this.removeAll();
		
		Vector<String>  vTitles = bibALL.TrenneZeile(cTitelPartsFor_TranslationLabels, "|");
		Vector<String>  vFieldNames = bibALL.TrenneZeile(FieldNames, "|");
		
		if (vTitles.size() != vFieldNames.size())
			throw new myException(this,"Number of titles must be same as number of fields !!");
		

		this.setSize(vTitles.size());
		
		for (int i=0;i<vTitles.size();i++)
		{
			GridLayoutData oGL = oGL4Titles;
			if (oGL==null)
			{
				oGL=LayoutDataFactory.get_GridLayoutGridLeft(E2_INSETS.I_1_1_1_1);
			}
			
			this.add(new MyE2_Label(new MyE2_String(vTitles.get(i)),MyE2_Label.STYLE_SMALL_ITALIC()), oGL);
		}
		
		
		for (int i=0;i<vFieldNames.size();i++)
		{
			GridLayoutData oGL = oGL4Fields;
			if (oGL==null)
			{
				oGL=LayoutDataFactory.get_GridLayoutGridLeft(E2_INSETS.I_1_1_1_1);
			}
			
			if (S.isEmpty(vFieldNames.get(i)))
			{
				this.add(new MyE2_Label(""), oGL);
			}
			else
			{
				this.add(oMap.get_Comp(vFieldNames.get(i)), oGL);
			}
		}

	}
	
	

	/**
	 * 
	 * @param vTitelStrings  Vector<MyString>
	 * @param vComponents    Vector<Component>
	 * @param Insets4Titles
	 * @param Insets4Fields
	 * @param bAllowMultiElementLines
	 * @throws myException
	 */
	public E2_Subgrid_4_Mask(Vector<MyString> vTitelStrings, Vector<Component> vComponents, Insets Insets4Titles, Insets Insets4Fields, boolean bAllowMultiElementLines) throws myException
	{
		super(MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
		
		
		if ( (vTitelStrings.size() != vComponents.size()) && !bAllowMultiElementLines )
			throw new myException(this,"Number of titles must be same as number of fields !!");
		

		this.setSize(vTitelStrings.size());
		
		for (int i=0;i<vTitelStrings.size();i++)
		{
			this.add(new MyE2_Label(vTitelStrings.get(i),MyE2_Label.STYLE_SMALL_ITALIC()), Insets4Titles==null?E2_INSETS.I_1_1_5_1:Insets4Titles);
		}
		
		
		for (int i=0;i<vComponents.size();i++)
		{
			this.add(vComponents.get(i), Insets4Fields==null?E2_INSETS.I_1_1_5_1:Insets4Fields);
		}
	}


	
	/**
	 * 
	 * @param vTitelStrings  Vector<MyString>
	 * @param vComponents    Vector<Component>
	 * @param Insets4Titles
	 * @param Insets4Fields
	 * @param bAllowMultiElementLines
	 * @throws myException
	 */
	public E2_Subgrid_4_Mask(	Vector<MyString> 	vTitelStrings, 
								Vector<Component> 	vComponents, 
								GridLayoutData 		gl4Titles, 
								GridLayoutData 		gl4Fields, 
								boolean 			bAllowMultiElementLines,
								Border              oBorder4Grid,
								boolean             bLabelZeilenumbruch) throws myException
	{
		super();
		
		this.setStyle(MyE2_Grid.STYLE_GRID_BORDER(oBorder4Grid));
		
		if ( (vTitelStrings.size() != vComponents.size()) && !bAllowMultiElementLines )
			throw new myException(this,"Number of titles must be same as number of fields !!");
		
		this.setSize(vTitelStrings.size());
		
		for (int i=0;i<vTitelStrings.size();i++)
		{
			this.add(new MyE2_Label(vTitelStrings.get(i),MyE2_Label.STYLE_SMALL_ITALIC(),bLabelZeilenumbruch),gl4Titles);
		}
		
		
		for (int i=0;i<vComponents.size();i++)
		{
			this.add(vComponents.get(i), gl4Fields);
		}
		
		
		
	}
	
	

	
	

	

	/**
	 * 2015-06-08: neuer konstruktor
	 * @param vTitelStrings
	 * @param vComponents
	 * @param v_gl4Titles
	 * @param v_gl4Fields
	 * @param bAllowMultiElementLines
	 * @param oBorder4Grid
	 * @param bLabelZeilenumbruch
	 * @throws myException
	 */
	public E2_Subgrid_4_Mask(	Vector<MyString> 		vTitelStrings, 
								Vector<Component> 		vComponents, 
								Vector<GridLayoutData>	v_gl4Titles, 
								Vector<GridLayoutData> 	v_gl4Fields, 
								boolean 				bAllowMultiElementLines,
								Border              	oBorder4Grid,
								boolean             	bLabelZeilenumbruch) throws myException
	{
		super();
		
		this.setStyle(MyE2_Grid.STYLE_GRID_BORDER(oBorder4Grid));
		
		int iSizeTitel = vTitelStrings.size();
		
		if (vTitelStrings==null || vComponents==null || v_gl4Titles==null || v_gl4Fields==null) {
			throw new myException(this,"All Vectors MUST be NOT NULL !");
		}
		
		if (vComponents.size() != iSizeTitel || v_gl4Titles.size() != iSizeTitel || v_gl4Fields.size() != iSizeTitel) {
			throw new myException(this,"All Vectors MUST have the same size !");
		}
		
		this.setSize(vTitelStrings.size());
		
		for (int i=0;i<vTitelStrings.size();i++) {
			this.add(new MyE2_Label(vTitelStrings.get(i),MyE2_Label.STYLE_SMALL_ITALIC(),bLabelZeilenumbruch),v_gl4Titles.get(i));
		}
		
		for (int i=0;i<vComponents.size();i++) {
			this.add(vComponents.get(i), v_gl4Fields.get(i));
		}
	}
	
	
	
	
	
}
