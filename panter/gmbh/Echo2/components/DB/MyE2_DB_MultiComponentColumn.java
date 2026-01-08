package panter.gmbh.Echo2.components.DB;

import java.util.HashMap;
import java.util.Vector;

import echopointng.PopUp;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_ComponentHelper;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2IF__IndirectHELPER;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibServer;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_DB_MultiComponentColumn extends MyE2_Column  implements MyE2IF__Component, E2_IF_Copy,MyE2IF__ComponentContainer
{

	private Vector<MyE2IF__Component>   		vComponents = new Vector<MyE2IF__Component>();
	private HashMap<String,MyE2IF__Component>  	hmComponents = new HashMap<String, MyE2IF__Component>();

	public MyE2_DB_MultiComponentColumn()
	{
		super();
		this.setStyle(new Style_Column_Normal(0, new Insets(0,0,0,2)));
		
		//this.setBorder(new Border(1,Color.BLUE,Border.STYLE_SOLID));

		
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

	public MyE2IF__Component add_Component(MyE2IF__Component oComp, MyString cTitleForMaskOrList, String cHASH_KEY_FOR_NON_DB_FIELDS, LayoutData oLayoutList,  LayoutData oLayoutTitel) throws myException
	{
		oComp.EXT().set_oLayout_ListElement(oLayoutList);
		oComp.EXT().set_oLayout_ListTitelElement(oLayoutTitel);
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
			
			E2_NavigationList_ComponentHelper.set_COLUMN_LayoutData_InList((Component)oComp);
			
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
					throw new myException("MyE2_DB_MultiComponentColumn:add_Component: MyE2IF__Component-Types must get a separate HASHKEY");
				
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
		
		
		//
		//2014-01-13: tooltips aus datenbank auch in subgrids implementieren
		this.check_if_toolTipsArePossible(oComp, cTitleForMaskOrList);
		
	}


	
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_MultiComponentColumn oRueck = new MyE2_DB_MultiComponentColumn();
		
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
					E2_NavigationList_ComponentHelper.set_COLUMN_LayoutData_InList(oRueck);
					
					oRueck.add_Component((MyE2IF__Component)oCompCopy, ((MyE2IF__Component)oCompCopy).EXT().get_cList_or_Mask_Titel(), ((MyE2IF__Component)oCompCopy).EXT().get_C_HASHKEY());

					oCompCopy.setVisible(oCom.isVisible());
				}
				catch (myException ex)
				{
					throw new myExceptionCopy(myExceptionCopy.ERROR_COPYING+" ->> "+ex.get_ErrorMessage().get_cMessage().COrig());
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
	//uebergibt eine komponente von sich selbst, damit die navilist der header-komponente die richtige formatierung geben kann
	public MyE2IF__Component get_ListHeaderComponent(E2_NavigationList oList) throws myException
	{
		
		MyE2_DB_MultiComponentColumn oColumnRueck = new MyE2_DB_MultiComponentColumn();
		oColumnRueck.setStyle(new Style_Column_Normal(0, new Insets(0,0,0,2)));
		
		for (int i=0;i<vComponents.size();i++)
		{
			Component compHelp = (Component)vComponents.get(i);
			
			Component oCompHeader = this.build_HeaderComponent(compHelp, oList);
			
			E2_NavigationList_ComponentHelper.set_ToolTipsToHeaderComponent(compHelp, oCompHeader);

			//alt: 			oColumnRueck.add(oCompHeader);

			//2014-09-05: neuer wrapper um die titelkomponente
			oColumnRueck.add(E2_NavigationList_ComponentHelper.wrapHeaderWithAddonKomponents((MyE2IF__Component)compHelp,oCompHeader));

			//oColumnRueck.add(oCompHeader);
			
		}
		return oColumnRueck;
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

			E2_NavigationList_ComponentHelper.set_COLUMN_LayoutData_Title(oCompRueck,oComp);
			
			return (oCompRueck);
		}


		
		if (oComp instanceof MyE2IF__DB_Component)
		{
			Component oRueck = E2_NavigationList_ComponentHelper.build_HeaderComponent_helper(oComp,oList);
			
			E2_NavigationList_ComponentHelper.set_COLUMN_LayoutData_Title(oRueck,oComp);

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
			
			E2_NavigationList_ComponentHelper.set_COLUMN_LayoutData_Title(oRueck,oComp);

			((MyE2IF__Component)oComp).EXT().set_oCompTitleInList(oRueck);
			return oRueck;
		}

		
	}


	
	
	
	//2014-01-13: tooltips aus datenbank auch in subgrids implementieren
	/*
	 * aenderung 2013-07-26: wenn in der datenbank ein @USER:  - Tooltip gefunden wird, dann wird diese angezeigt UND ERSETZT ALLE ALTEN!!! (wird 
	 * nur in original-E2_ComponentMAPs durchgefuehrt, in kopien nicht (wegen listen-laufzeiten)
	 */
	private void check_if_toolTipsArePossible(MyE2IF__Component oComponent, MyString cTitleForMaskOrList)
	{
		String c_TooltipFromDB = bibServer.get_cTooltipInfosFromDBDescription(oComponent);
		//String c_TooltipFromDB = null;
		
		MyE2_String cTooltipFromDB = S.isEmpty(c_TooltipFromDB)?null:new MyE2_String(c_TooltipFromDB);
		
		
		//2013-01-04: selectField muss auch tooltips bekommen
		if (oComponent instanceof MyE2_SelectField)
		{
			if (cTooltipFromDB != null) {
				((MyE2_SelectField)oComponent).setToolTipText(cTooltipFromDB.CTrans());
				((MyE2_SelectField)oComponent).set_cToolTipWhenEmptyDropDownValue(cTooltipFromDB.CTrans());
			} else {
			
				if (S.isFull(((MyE2_SelectField)oComponent).getToolTipText()))
				{
					((MyE2_SelectField)oComponent).set_cToolTipWhenEmptyDropDownValue(((MyE2_SelectField)oComponent).getToolTipText());
				}
				else if (S.isEmpty(((MyE2_SelectField)oComponent).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					((MyE2_SelectField)oComponent).setToolTipText(cTitleForMaskOrList.CTrans());
					((MyE2_SelectField)oComponent).set_cToolTipWhenEmptyDropDownValue(cTitleForMaskOrList.CTrans());
				}
				
			}
		}
		else if (oComponent instanceof TextField)
		{
			if (cTooltipFromDB != null) {
				((TextField)oComponent).setToolTipText(cTooltipFromDB.CTrans());
			}
			else {
			
				if (S.isEmpty(((TextField)oComponent).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					((TextField)oComponent).setToolTipText(cTitleForMaskOrList.CTrans());
				}
				
			}
		}
		else if (oComponent instanceof TextArea)
		{
			if (cTooltipFromDB != null) {
				((TextArea)oComponent).setToolTipText(cTooltipFromDB.CTrans());
			}
			else {
				if (S.isEmpty(((TextArea)oComponent).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					((TextArea)oComponent).setToolTipText(cTitleForMaskOrList.CTrans());
				}
			}
		}
		else if (oComponent instanceof Button)
		{
			if (cTooltipFromDB != null) {
				((Button)oComponent).setToolTipText(cTooltipFromDB.CTrans());
			}
			else {
				
				if (S.isEmpty(((Button)oComponent).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					((Button)oComponent).setToolTipText(cTitleForMaskOrList.CTrans());
				}
				
			}
		}
		else if (oComponent instanceof Label)
		{
			if (cTooltipFromDB != null) {
				((Label)oComponent).setToolTipText(cTooltipFromDB.CTrans());
			}
			else {
				
				if (S.isEmpty(((Label)oComponent).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					((Label)oComponent).setToolTipText(cTitleForMaskOrList.CTrans());
				}
				
			}
		}
		else if (oComponent instanceof CheckBox)
		{
			if (cTooltipFromDB != null) {
				((CheckBox)oComponent).setToolTipText(cTooltipFromDB.CTrans());
			}
			else {
				if (S.isEmpty(((CheckBox)oComponent).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					((CheckBox)oComponent).setToolTipText(cTitleForMaskOrList.CTrans());
				}
			}
		}
		else if (oComponent instanceof MyE2_DB_TextField_WithSelektor)
		{
			MyE2_DB_TextField_WithSelektor oHelp = (MyE2_DB_TextField_WithSelektor)oComponent;
			
			TextField 	oTFHelp = oHelp.get_oTextField();
			PopUp 		oPopUp = oHelp.get_oPopUp();
			
			if (cTooltipFromDB != null) {
				oTFHelp.setToolTipText(cTooltipFromDB.CTrans());
				oPopUp.setToolTipText(cTooltipFromDB.CTrans());
			}
			else {

				if (S.isEmpty(oTFHelp.getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					oTFHelp.setToolTipText(cTitleForMaskOrList.CTrans());
				}
				if (S.isEmpty(oPopUp.getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
				{
					oPopUp.setToolTipText(cTitleForMaskOrList.CTrans());
				}
			}
		}
		else if (oComponent instanceof MyE2_Row)
		{
			Vector<String> vSearchList = bibALL.get_Vector(Button.class.getName(), Label.class.getName(),TextField.class.getName(),TextArea.class.getName());
			E2_RecursiveSearch_Component oRecSearch = new E2_RecursiveSearch_Component((MyE2_Row)oComponent,vSearchList,null);

			for (Component oComp: oRecSearch.get_vAllComponents())
			{
				
				if (oComp instanceof TextField)
				{
					if (S.isEmpty(((TextField)oComp).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
					{
						((TextField)oComp).setToolTipText(cTitleForMaskOrList.CTrans());
					}
				}
				else if (oComp instanceof TextArea)
				{
					if (S.isEmpty(((TextArea)oComp).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
					{
						((TextArea)oComp).setToolTipText(cTitleForMaskOrList.CTrans());
					}
				}
				else if (oComp instanceof Button)
				{
					if (S.isEmpty(((Button)oComp).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
					{
						((Button)oComp).setToolTipText(cTitleForMaskOrList.CTrans());
					}
				}
				else if (oComp instanceof Label)
				{
					if (S.isEmpty(((Label)oComp).getToolTipText()) && cTitleForMaskOrList!=null && S.isFull(cTitleForMaskOrList))
					{
						((Label)oComp).setToolTipText(cTitleForMaskOrList.CTrans());
					}
				}
			}
		} 

	}

	

}
