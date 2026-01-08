package panter.gmbh.Echo2.components.DB;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_ComponentHelper;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2IF__IndirectHELPER;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.Style_Grid_Normal;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_DB_MultiComponentGrid extends MyE2_Grid  implements MyE2IF__Component, E2_IF_Copy,MyE2IF__ComponentContainer
{

	private Vector<MyE2IF__Component>  vComponents = new Vector<MyE2IF__Component>();
	private HashMap<String,MyE2IF__Component>  hmComponents = new HashMap<String, MyE2IF__Component>();

	private int      iCols = 2;
	
	
	/**
	 * 2011-03-02: bestimmt, ob die komponente in listen passiv bleibt, d.h. ob das set_enabled(false) ignoriert wird,
	 *             damit evtl. vorhandene aktive elemente, die drunterliegen erreichbar sind.
	 *             ist bComponentIsPassive = false, dann verhaelt sich die komponente wie das MyE2_Grid
	 */
	private boolean  bComponentIsPassive = false;                  
	                                                               
	
	

	public MyE2_DB_MultiComponentGrid(int icols)
	{
		super(icols,MyE2_Grid.STYLE_GRID_NO_BORDER());
		this.iCols = icols;
	}
	

	public MyE2_DB_MultiComponentGrid(int icols, Integer iColWith0)
	{
		super(icols,MyE2_Grid.STYLE_GRID_NO_BORDER());
		this.iCols = icols;
		
		if (this.iCols>0 && iColWith0!=null) {this.setColumnWidth(0, new Extent(iColWith0.intValue())); }
	}


	public MyE2_DB_MultiComponentGrid(int icols, MutableStyle oStyle4Grid)
	{
		super(icols,oStyle4Grid);
		this.iCols = icols;
	}


	
	
	public MyE2_DB_MultiComponentGrid(int icols, Integer iColWith0, Integer iColWith1)
	{
		super(icols,MyE2_Grid.STYLE_GRID_NO_BORDER());
		this.iCols = icols;
		
		if (this.iCols>0 && iColWith0!=null) {this.setColumnWidth(0, new Extent(iColWith0.intValue())); }
		if (this.iCols>1 && iColWith1!=null) {this.setColumnWidth(1, new Extent(iColWith1.intValue())); }
	}
	public MyE2_DB_MultiComponentGrid(int icols, Integer iColWith0, Integer iColWith1, Integer iColWith2)
	{
		super(icols,MyE2_Grid.STYLE_GRID_NO_BORDER());
		this.iCols = icols;
		
		if (this.iCols>0 && iColWith0!=null) {this.setColumnWidth(0, new Extent(iColWith0.intValue())); }
		if (this.iCols>1 && iColWith1!=null) {this.setColumnWidth(1, new Extent(iColWith1.intValue())); }
		if (this.iCols>2 && iColWith2!=null) {this.setColumnWidth(2, new Extent(iColWith2.intValue())); }
	}

	public MyE2_DB_MultiComponentGrid(int icols, Integer iColWith0, Integer iColWith1, Integer iColWith2, Integer iColWith3)
	{
		super(icols,MyE2_Grid.STYLE_GRID_NO_BORDER());
		this.iCols = icols;
		
		if (this.iCols>0 && iColWith0!=null) {this.setColumnWidth(0, new Extent(iColWith0.intValue())); }
		if (this.iCols>1 && iColWith1!=null) {this.setColumnWidth(1, new Extent(iColWith1.intValue())); }
		if (this.iCols>2 && iColWith2!=null) {this.setColumnWidth(2, new Extent(iColWith2.intValue())); }
		if (this.iCols>3 && iColWith3!=null) {this.setColumnWidth(3, new Extent(iColWith3.intValue())); }
	}
	
	public MyE2_DB_MultiComponentGrid(int icols, Integer iColWith0, Integer iColWith1, Integer iColWith2, Integer iColWith3, Integer iColWith4)
	{
		super(icols,MyE2_Grid.STYLE_GRID_NO_BORDER());
		this.iCols = icols;
		
		if (this.iCols>0 && iColWith0!=null) {this.setColumnWidth(0, new Extent(iColWith0.intValue())); }
		if (this.iCols>1 && iColWith1!=null) {this.setColumnWidth(1, new Extent(iColWith1.intValue())); }
		if (this.iCols>2 && iColWith2!=null) {this.setColumnWidth(2, new Extent(iColWith2.intValue())); }
		if (this.iCols>3 && iColWith3!=null) {this.setColumnWidth(3, new Extent(iColWith3.intValue())); }
		if (this.iCols>4 && iColWith4!=null) {this.setColumnWidth(4, new Extent(iColWith4.intValue())); }
	}
	
	public MyE2_DB_MultiComponentGrid(int icols, Integer iColWith0, Integer iColWith1, Integer iColWith2, Integer iColWith3, Integer iColWith4, Integer iColWith5)
	{
		super(icols,MyE2_Grid.STYLE_GRID_NO_BORDER());
		this.iCols = icols;
		
		if (this.iCols>0 && iColWith0!=null) {this.setColumnWidth(0, new Extent(iColWith0.intValue())); }
		if (this.iCols>1 && iColWith1!=null) {this.setColumnWidth(1, new Extent(iColWith1.intValue())); }
		if (this.iCols>2 && iColWith2!=null) {this.setColumnWidth(2, new Extent(iColWith2.intValue())); }
		if (this.iCols>3 && iColWith3!=null) {this.setColumnWidth(3, new Extent(iColWith3.intValue())); }
		if (this.iCols>4 && iColWith4!=null) {this.setColumnWidth(4, new Extent(iColWith4.intValue())); }
		if (this.iCols>5 && iColWith5!=null) {this.setColumnWidth(5, new Extent(iColWith5.intValue())); }
	}

	
	public MyE2IF__Component add_Component(MyE2IF__Component oComp, MyString cTitleForMaskOrList, String cHASH_KEY_FOR_NON_DB_FIELDS) throws myException
	{
		this.__add_Component(oComp,cTitleForMaskOrList,cHASH_KEY_FOR_NON_DB_FIELDS);
		return oComp;
	}

	public MyE2IF__Component add_Component(MyE2IF__Component oComp, MyString cTitleForMaskOrList, String cHASH_KEY_FOR_NON_DB_FIELDS, LayoutData oLayout) throws myException
	{
		oComp.EXT().set_oLayout_ListElement(oLayout);
		this.__add_Component(oComp,cTitleForMaskOrList,cHASH_KEY_FOR_NON_DB_FIELDS);
		return oComp;

	}

	public MyE2IF__Component add_Component(MyE2IF__Component oComp, MyString cTitleForMaskOrList, String cHASH_KEY_FOR_NON_DB_FIELDS, LayoutData oLayoutElement, LayoutData oLayoutTitle) throws myException
	{
		oComp.EXT().set_oLayout_ListElement(oLayoutElement);
		oComp.EXT().set_oLayout_ListTitelElement(oLayoutTitle);
		this.__add_Component(oComp,cTitleForMaskOrList,cHASH_KEY_FOR_NON_DB_FIELDS);
		return oComp;

	}

	
	public void add_Component(MyE2IF__Component oComp, MyString cTitleForMaskOrList, String cHASH_KEY_FOR_NON_DB_FIELDS,boolean bIsSortableInList) throws myException
	{
		this.__add_Component(oComp,cTitleForMaskOrList,cHASH_KEY_FOR_NON_DB_FIELDS);
		if (oComp instanceof MyE2IF__DB_Component)
		{
			((MyE2IF__DB_Component)oComp).EXT_DB().set_bIsSortable(bIsSortableInList);
		}
	}

	private void __add_Component(MyE2IF__Component oComp, MyString cTitleForMaskOrList, String cHASH_KEY_FOR_NON_DB_FIELDS) throws myException
	{
		
		if (!(oComp instanceof E2_IF_Copy))
			throw new myException("MyE2_DB_MultiComponentColumn:add_Component:only E2_IF_Copy-types alowed");

		/*
		 * 2011-06-06: weiteres feld, dass definiert, ob das objekt zu 
		 * einem MyE2IF__ComponentContainer
		 * gehoert
		 */
		oComp.EXT().set_oColumnComponentContainerThisBelongsTo(this);

		
		if (oComp instanceof MyE2IF__Component || oComp instanceof MyE2IF__DB_Component )
		{
			((MyE2IF__Component)oComp).EXT().set_cList_or_Mask_Titel(cTitleForMaskOrList);

			E2_NavigationList_ComponentHelper.set_GRID_LayoutData_InList((Component)oComp);

			this.vComponents.add(oComp);
			
			//20190220: auch in den sub-containern die gerenderte komponente in den EXT schreiben 
			Component cReal = new MyE2IF__IndirectHELPER((Component)oComp).get_oCompRueck();
			oComp.EXT().set_real_rendered_component_in_list(cReal);
		    this.add(cReal);
			
			
			/*
			 * falls eine db-component, dann den hash-key in den extender reinschreiben
			 */
			if (oComp instanceof MyE2IF__DB_Component)
			{
				String cHashKey = ((MyE2IF__DB_Component)oComp).EXT_DB().get_oSQLField().get_cFieldLabel();
				((MyE2IF__Component)oComp).EXT().set_C_HASHKEY(cHashKey);
				
				MyE2IF__DB_Component oDBComponent = (MyE2IF__DB_Component)oComp;
				/*
				 * wenn der feldbezeichner im sqlfield noch dem label entspricht, dann wird dieser
				 * auch ueberschrieben
				 */
				if (oDBComponent.EXT_DB().get_oSQLField().get_cFieldLabelForUser().COrig().equals(oDBComponent.EXT_DB().get_oSQLField().get_cFieldLabel()))
					 oDBComponent.EXT_DB().get_oSQLField().set_cFieldLabelForUser(cTitleForMaskOrList);
				
				
			}
			else
			{
				if (bibALL.isEmpty(cHASH_KEY_FOR_NON_DB_FIELDS))
					throw new myException("MyE2_DB_MultiComponentGrid:add_Component: MyE2IF__Component-Types must get a separate HASHKEY");
				
				((MyE2IF__Component)oComp).EXT().set_C_HASHKEY(cHASH_KEY_FOR_NON_DB_FIELDS);
			}
			
			/*
			 * auch die componentmap mitteilen, wenn diese schon vergeben wurde
			 */
			if (this.EXT().get_oComponentMAP() != null)
				((MyE2IF__Component)oComp).EXT().set_oComponentMAP(this.EXT().get_oComponentMAP());
			
			
			this.hmComponents.put(((MyE2IF__Component)oComp).EXT().get_C_HASHKEY(),oComp);
		}
		else 
			throw new myException("MyE2_DB_MultiComponentColumn:add_Component:only types MyE2IF__Component and MyE2IF__DB_Component allowed");
		
		
	}


	
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_MultiComponentGrid oRueck = new MyE2_DB_MultiComponentGrid(this.iCols);
		
		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		oRueck.setStyle(this.getStyle());
		
		for (int i=0;i<this.get_vComponents().size();i++)
		{
			if (this.get_vComponents().get(i) instanceof E2_IF_Copy)
			{
				
				Component oCom = (Component)this.vComponents.get(i);
				Component oCompCopy = (Component)((E2_IF_Copy)oCom).get_Copy(null);
				
				try
				{

					//darf erst hier zugefuegt werden, da im falle eines MyE2IF__Indirect-komponente hier die Layout-Datas umdefiniert werden
					E2_NavigationList_ComponentHelper.set_GRID_LayoutData_InList(oRueck);
					oRueck.add_Component((MyE2IF__Component)oCompCopy, ((MyE2IF__Component)oCompCopy).EXT().get_cList_or_Mask_Titel(), ((MyE2IF__Component)oCompCopy).EXT().get_C_HASHKEY());

					oCompCopy.setVisible(oCom.isVisible());

				}
				catch (myException ex)
				{
					ex.printStackTrace();
					throw new myExceptionCopy(myExceptionCopy.ERROR_COPYING+" ->> "+ex.get_ErrorMessage().get_cMessage().COrig());
				}
				
			}
		}
		

		//aenderung 20101020: 
		//jetzt die columnwidth anpassen, damit die ueberschriften und die spalteninhalte einigermassen gleich sind
		for (int i=0;i<this.getSize();i++)
		{
			if (this.getColumnWidth(i)!=null)
			{
				oRueck.setColumnWidth(i, this.getColumnWidth(i));
			}
		}
		
		
		oRueck.set_bComponentIsPassive(this.get_bComponentIsPassive());
		
		
		return oRueck;
	}




	public Vector<MyE2IF__Component> get_vComponents()
	{
		return this.vComponents;
	}



	public Vector<MyE2IF__DB_Component> get_vDB_Components()
	{
		Vector<MyE2IF__DB_Component> vRueck = new Vector<MyE2IF__DB_Component>();
		for (int i=0;i<this.vComponents.size();i++)
		{
			if (this.vComponents.get(i) instanceof MyE2IF__DB_Component)
			{
				vRueck.add((MyE2IF__DB_Component)this.vComponents.get(i));
			}
		}
		
		return vRueck;
	}


	/*
	 * methode uebergibt allen componenten vom typ label einen
	 * wert fuer leere datenfelder, damit diese zeilen auch sichtsind
	 * und nicht auf hoehe null schrumpfen
	 */
	public void set_all_labels_EmtpyValue(String cEmpty)
	{
		for (int i=0;i<this.vComponents.size();i++)
		{
			if (vComponents.get(i) instanceof MyE2_Label)
			{
				((MyE2_Label)vComponents.get(i)).set_cErsatzFuerLeeranzeige(cEmpty);
			}
		}
		
	}
	
	


	public HashMap<String,MyE2IF__Component> get_hmComponents()
	{
		return this.hmComponents;
	}


	public MyE2IF__Component get_ReducedComponent()
	{
		return (MyE2IF__Component)this.vComponents.get(0);
	}


	
	
	@Override
	public MyE2IF__Component get_ListHeaderComponent(E2_NavigationList oList) throws myException
	{

		//uebergibt eine komponente von sich selbst, damit die navilist der header-komponente die richtige formatierung geben kann
		MyE2_DB_MultiComponentGrid oGridRueck = new MyE2_DB_MultiComponentGrid(this.iCols);
		oGridRueck.setStyle(new Style_Grid_Normal(0, new Insets(0,0,0,2)));
		
		for (int i=0;i<vComponents.size();i++)
		{
			
			Component compHelp = (Component)vComponents.get(i);
			
			Component oCompHeader = this.build_HeaderComponent(compHelp, oList);
			
			E2_NavigationList_ComponentHelper.set_ToolTipsToHeaderComponent(compHelp, oCompHeader);
			
			//2014-09-05: neuer wrapper um die titelkomponente
			oGridRueck.add(E2_NavigationList_ComponentHelper.wrapHeaderWithAddonKomponents((MyE2IF__Component)compHelp,oCompHeader));

			//alt: oGridRueck.add(oCompHeader);

		}
		
		//aenderung 20101020: 
		//jetzt die columnwidth anpassen, damit die ueberschriften und die spalteninhalte einigermassen gleich sind
		for (int i=0;i<this.getSize();i++)
		{
			if (this.getColumnWidth(i)!=null)
			{
				oGridRueck.setColumnWidth(i, this.getColumnWidth(i));
			}
		}

		
		return oGridRueck;
	}

	
	
	private Component build_HeaderComponent(Component oComp,E2_NavigationList oList) throws myException
	{
		/*
		 * falls schon eine titelkomponente vorhanden ist, dann diese zurueckgeben, damit werden 
		 * die ueberschriften/sortbuttons nur einmal erzeugt. Auch ist es damit moeglich, einer komponenten aus der
		 * rufenden einheit bereits eine komponente fuer die titelzeile mitzugeben 
		 */
		
		if (((MyE2IF__Component)oComp).EXT().get_oCompTitleInList() != null)
		{
			Component oCompRueck = ((MyE2IF__Component)oComp).EXT().get_oCompTitleInList();
			
			E2_NavigationList_ComponentHelper.set_GRID_LayoutData_Title(oCompRueck,oComp);
			
			return (oCompRueck);
		}

		if (oComp instanceof MyE2IF__DB_Component)
		{
			Component oRueck = E2_NavigationList_ComponentHelper.build_HeaderComponent_helper(oComp,oList);
			
			E2_NavigationList_ComponentHelper.set_GRID_LayoutData_Title(oRueck,oComp);

			((MyE2IF__Component)oComp).EXT().set_oCompTitleInList(oRueck);
			return oRueck;
		}
		else
		{
			MyString cHelp = null;
			
			if (((MyE2IF__Component) oComp).EXT().get_cList_or_Mask_Titel() != null)
			{
				cHelp = ((MyE2IF__Component) oComp).EXT().get_cList_or_Mask_Titel();
			}
			else
			{
				cHelp = new MyString(" ");
			}

			Component oRueck = new MyE2_Label(cHelp);
			
			E2_NavigationList_ComponentHelper.set_GRID_LayoutData_Title(oRueck,oComp);

			((MyE2IF__Component)oComp).EXT().set_oCompTitleInList(oRueck);
			return oRueck;
		}

		
	}


	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		if (!this.bComponentIsPassive)
		{
			this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
		}
	}

	public boolean get_bComponentIsPassive() 
	{
		return bComponentIsPassive;
	}


	public void set_bComponentIsPassive(boolean ComponentIsPassive) 
	{
		this.bComponentIsPassive = ComponentIsPassive;
	}


}
