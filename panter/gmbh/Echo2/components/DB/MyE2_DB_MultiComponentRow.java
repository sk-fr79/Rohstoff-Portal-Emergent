package panter.gmbh.Echo2.components.DB;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_ComponentHelper;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2IF__IndirectHELPER;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.staticStyles.Style_Row_Normal;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_DB_MultiComponentRow extends MyE2_Row  implements MyE2IF__Component, E2_IF_Copy,MyE2IF__ComponentContainer
{

	private Vector<MyE2IF__Component>  vComponents = new Vector<MyE2IF__Component>();
	private HashMap<String,MyE2IF__Component>  hmComponents = new HashMap<String, MyE2IF__Component>();
	
	public MyE2_DB_MultiComponentRow()
	{
		super();
		this.setStyle(new Style_Row_Normal(0, new Insets(0,0,2,0)));
	}
	
	
	public MyE2IF__Component add_Component(MyE2IF__Component oComp, MyString cTitleForMaskOrList, String cHASH_KEY_FOR_NON_DB_FIELDS) throws myException
	{
		
		if (!(oComp instanceof E2_IF_Copy))
			throw new myException("MyE2_DB_MultiComponentRow:add_Component:only E2_IF_Copy-types alowed");

		
		if (oComp instanceof MyE2IF__Component || oComp instanceof MyE2IF__DB_Component )
		{
			((MyE2IF__Component)oComp).EXT().set_cList_or_Mask_Titel(cTitleForMaskOrList);

			/*
			 * 2011-06-06: weiteres feld, dass definiert, ob das objekt zu 
			 * einem MyE2IF__ComponentContainer
			 * gehoert
			 */
			oComp.EXT().set_oColumnComponentContainerThisBelongsTo(this);

			
			E2_NavigationList_ComponentHelper.set_ROW_LayoutData_InList((Component)oComp);
			
			this.vComponents.add(oComp);
			
			//20190220: auch in den sub-containern die gerenderte komponente in den EXT schreiben 
			Component cReal = new MyE2IF__IndirectHELPER((Component)oComp).get_oCompRueck();
			oComp.EXT().set_real_rendered_component_in_list(cReal);
		    this.add(cReal);

			
		   // this.add(new MyE2IF__IndirectHELPER((Component)oComp).get_oCompRueck());

			
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
					throw new myException("MyE2_DB_MultiComponentColumn:add_Component: MyE2IF__Component-Types must get as separate HASHKEY");
				
				((MyE2IF__Component)oComp).EXT().set_C_HASHKEY(cHASH_KEY_FOR_NON_DB_FIELDS);
			}
		
			/*
			 * auch die componentmap mitteilen, wenn diese schon vergeben wurde
			 */
			if (this.EXT().get_oComponentMAP() != null)
				((MyE2IF__Component)oComp).EXT().set_oComponentMAP(this.EXT().get_oComponentMAP());

			
			this.hmComponents.put(((MyE2IF__Component)oComp).EXT().get_C_HASHKEY(),oComp);
		}
		else {
			throw new myException("MyE2_DB_MultiComponentRow:add_Component:only types MyE2IF__Component and MyE2IF__DB_Component allowed");
		}
		
		return oComp;
	}
	
	


	
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_MultiComponentRow oRueck = new MyE2_DB_MultiComponentRow();
		
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
					E2_NavigationList_ComponentHelper.set_ROW_LayoutData_InList(oRueck);
					oRueck.add_Component((MyE2IF__Component)oCompCopy, ((MyE2IF__Component)oCompCopy).EXT().get_cList_or_Mask_Titel(), ((MyE2IF__Component)oCompCopy).EXT().get_C_HASHKEY());
					
					oCompCopy.setVisible(oCom.isVisible());

				}
				catch (myException ex)
				{
					throw new myExceptionCopy(myExceptionCopy.ERROR_COPYING);
				}
				
			}
		}
		
		
		
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
		MyE2_DB_MultiComponentRow oRowRueck = new MyE2_DB_MultiComponentRow();
		oRowRueck.setStyle(new Style_Row_Normal(0, new Insets(0,0,0,2)));
		
		for (int i=0;i<vComponents.size();i++)
		{
			
			Component compHelp = (Component)vComponents.get(i);
			
			Component oCompHeader = this.build_HeaderComponent(compHelp, oList);


			
			
			E2_NavigationList_ComponentHelper.set_ToolTipsToHeaderComponent(compHelp, oCompHeader);

			
			//2014-09-05: neuer wrapper um die titelkomponente
			oRowRueck.add(E2_NavigationList_ComponentHelper.wrapHeaderWithAddonKomponents((MyE2IF__Component)compHelp,oCompHeader));

			
			//alt: oRowRueck.add(oCompHeader);

		}

		return oRowRueck;
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
			
			E2_NavigationList_ComponentHelper.set_ROW_LayoutData_Title(oCompRueck,oComp);
			
			return (oCompRueck);
		}

		if (oComp instanceof MyE2IF__DB_Component)
		{
			Component oRueck = E2_NavigationList_ComponentHelper.build_HeaderComponent_helper(oComp,oList);
			
			E2_NavigationList_ComponentHelper.set_ROW_LayoutData_Title(oRueck,oComp);

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

			E2_NavigationList_ComponentHelper.set_ROW_LayoutData_Title(oRueck,oComp);

			((MyE2IF__Component)oComp).EXT().set_oCompTitleInList(oRueck);
			return oRueck;
		}

		
	}

	
	
	
}
