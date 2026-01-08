package panter.gmbh.Echo2.ListAndMask.List;

import java.util.Vector;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.calculation.CALC_ZUSATZKOMPONENTE_IF;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2IF__ComponentContainer;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class E2_NavigationList_ComponentHelper
{
	
	// ausgelagert, baut fuer die MyE2IF__ComponentContainer-komponenten die header-buttons/labels
	public static Component build_HeaderComponent_helper(Component oComp, E2_NavigationList oList)
	{
		Component oCompRueck = null;
		
		SQLField   oField =  null;
		if (oComp instanceof MyE2IF__DB_Component)
		{
			oField=((MyE2IF__DB_Component)oComp).EXT_DB().get_oSQLField();
		}
		MyString cHelp = ((MyE2IF__Component)oComp).EXT().get_cList_or_Mask_Titel();
		
		if (cHelp == null)  cHelp = new MyE2_String("--"); 

//		DEBUG.System_println(cHelp.COrig());
		
		
//		//2014-08-29: pruefen, ob die komponente weitere buttons fuer den listentitel enthaelt
//		Vector<E2IF__BelongsToNavigationList> 	vADDON_ListenTitel = 	((MyE2IF__Component)oComp).EXT().get_vZusatzKomponentenInListenTitel();
		
		
		if (oField != null && ((MyE2IF__DB_Component)oComp).EXT_DB().get_bIsSortable())
		{
			/*
			 * das sort-statement ist bei einfachen feldern immer tabellenname.feldname,
			 * bei zusammengesetzten feldern ist es der field-alias
			 */
			String cSortStatementUP = ((MyE2IF__DB_Component)oComp).EXT_DB().get_cSortAusdruckFuerSortbuttonUP();
			String cSortStatementDOWN = ((MyE2IF__DB_Component)oComp).EXT_DB().get_cSortAusdruckFuerSortbuttonDOWN();
			
			if (S.isEmpty(cSortStatementUP))
			{
				if (oField.get_cTableName() == null)
				{
					cSortStatementUP = oField.get_cFieldAusdruck()+" ASC ";
				}
				else
				{
					cSortStatementUP = oField.get_cTableName()+"."+oField.get_cFieldName()+" ASC ";
				}
			}
			if (S.isEmpty(cSortStatementDOWN))
			{
				if (oField.get_cTableName() == null)
				{
					cSortStatementDOWN = oField.get_cFieldAusdruck()+" DESC ";
				}
				else
				{
					cSortStatementDOWN = oField.get_cTableName()+"."+oField.get_cFieldName()+" DESC ";
				}
			}
			
			E2_ButtonListSorter oButton = new E2_ButtonListSorter(	cHelp,
																	cSortStatementUP,
																	cSortStatementDOWN,
																	oList, 
																	oList.get_bStandardSortButtonsLineWrap());
			
			/**
			 * 20190218: die breite der titelbuttons auslesen
			 */
			if (oComp instanceof MyE2IF__Component && (((MyE2IF__Component)oComp).EXT().getWidthOfTitelSortButton()!=null)) {
				oButton.get_oButtonSort().setWidth(new Extent( ((MyE2IF__Component)oComp).EXT().getWidthOfTitelSortButton()));
				
				if (((MyE2IF__Component)oComp).EXT().getStyleOfAutoTitelButton()!=null) {
//					oButton.get_oButtonSort().setAlignment(Alignment.ALIGN_RIGHT);
//					oButton.get_oButtonSort().setForeground(Color.RED);
					oButton.get_oButtonSort().setStyle(((MyE2IF__Component)oComp).EXT().getStyleOfAutoTitelButton());
				}
			}
			
			oButton.EXT().set_C_MERKMAL(oField.get_cFieldLabel());

			//2012-12-06: linewrap in titelkomponenten
			if ( ((MyE2IF__Component)oComp).EXT().get_bLineWrapListHeader())
			{
				oButton.get_oButtonSort().setLineWrap(true);
			}
			
			
			//2013-10-10: wenn sortierungen gespeichert wurden, dann hier schauen, ob der sortup- oder down-string in der sortliste ist und dann anzeigen
			if (oList.get_bSaveSortStatus()) {
				if (oList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_vOrderFields().contains(cSortStatementUP)) {
					oButton.set_SortedUP();
				} else if (oList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_vOrderFields().contains(cSortStatementDOWN)) {
					oButton.set_SortedDown();
				}
			}
			
			
			oCompRueck = oButton;
		}
		else
		{
			oCompRueck = new MyE2_Label(cHelp, ((MyE2IF__Component)oComp).EXT().get_bLineWrapListHeader());
		}
		
		//jetzt nachsehen, ob die komponente, fuer die ein Header erzeugt worden ist, auch einen mutablestyle dafuer enthaelt
		E2_NavigationList_ComponentHelper.pruefe_und_setze_Header_style_aus_EXT(oComp,oCompRueck);
		
		return oCompRueck;
		
	}


	
	public static void set_ToolTipsToHeaderComponent(Component oCompBaseMap, Component oComponentHeader)
	{
		if (oCompBaseMap instanceof MyE2IF__Component)
		{
			if (S.isFull(((MyE2IF__Component)oCompBaseMap).EXT().get_ToolTipStringForListHeader()))
			{
				if (oComponentHeader instanceof Button)
				{
					((Button)oComponentHeader).setToolTipText(((MyE2IF__Component)oCompBaseMap).EXT().get_ToolTipStringForListHeader().CTrans());
				}
				if (oComponentHeader instanceof E2_ButtonListSorter)
				{
					((E2_ButtonListSorter)oComponentHeader).get_oButtonSort().setToolTipText(((MyE2IF__Component)oCompBaseMap).EXT().get_ToolTipStringForListHeader().CTrans());
				}
				if (oComponentHeader instanceof Label)
				{
					((Label)oComponentHeader).setToolTipText(((MyE2IF__Component)oCompBaseMap).EXT().get_ToolTipStringForListHeader().CTrans());
				}
				
			}
		}

	}
	
	
	
	// setzt zu dem definierten titel-layoutelement  das passenden layout-data
	public static void set_GRID_LayoutData_Title(Component oTitleComponent, Component oOriginalComponent) throws myException
	{
		if ( oOriginalComponent instanceof MyE2IF__Component &&  
		 	((MyE2IF__Component)oOriginalComponent).EXT().get_oLayout_ListTitelElement()!=null && 
		 	 ((MyE2IF__Component)oOriginalComponent).EXT().get_oLayout_ListTitelElement() instanceof GridLayoutData)
		{
			oTitleComponent.setLayoutData(((MyE2IF__Component)oOriginalComponent).EXT().get_oLayout_ListTitelElement());
		}
		else
		{
			//hier noch unterscheiden zwischen component-groups und normalen componenten
			if (oTitleComponent instanceof MyE2IF__ComponentContainer)
			{
				oTitleComponent.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_GRID_Layout4ComponentGroupsHeader());
			}
			else
			{

			
				if (E2_NavigationList_ComponentHelper.get_bMUST_BE_RIGHT(oOriginalComponent))
				{
					oTitleComponent.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader());
				}
				else
				{
					oTitleComponent.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutLeftTOPHeader());
				}
			}
		}
	}
	
	
	
	// setzt zu dem definierten titel-layoutelement  das passenden layout-data
	public static void set_COLUMN_LayoutData_Title(Component oTitleComponent, Component oOriginalComponent) throws myException
	{
		if ( oOriginalComponent instanceof MyE2IF__Component &&  
		 	((MyE2IF__Component)oOriginalComponent).EXT().get_oLayout_ListTitelElement()!=null && 
		 	 ((MyE2IF__Component)oOriginalComponent).EXT().get_oLayout_ListTitelElement() instanceof ColumnLayoutData)
		{
			oTitleComponent.setLayoutData(((MyE2IF__Component)oOriginalComponent).EXT().get_oLayout_ListTitelElement());
		}
		else
		{
			//hier noch unterscheiden zwischen component-groups und normalen componenten
			if (oTitleComponent instanceof MyE2IF__ComponentContainer)
			{
				oTitleComponent.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_GRID_Layout4ComponentGroupsHeader());
			}
			else
			{
				if (E2_NavigationList_ComponentHelper.get_bMUST_BE_RIGHT(oOriginalComponent))
				{
					oTitleComponent.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_COLUMN_LayoutRightTOPHeader());
				}
				else
				{
					oTitleComponent.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_COLUMN_LayoutLeftTOPHeader());
				}
			}
		}
	}
	
	

	// setzt zu dem definierten titel-layoutelement  das passenden layout-data
	public static void set_ROW_LayoutData_Title(Component oTitleComponent, Component oOriginalComponent) throws myException
	{
		if ( oOriginalComponent instanceof MyE2IF__Component &&  
		 	((MyE2IF__Component)oOriginalComponent).EXT().get_oLayout_ListTitelElement()!=null && 
		 	 ((MyE2IF__Component)oOriginalComponent).EXT().get_oLayout_ListTitelElement() instanceof RowLayoutData)
		{
			oTitleComponent.setLayoutData(((MyE2IF__Component)oOriginalComponent).EXT().get_oLayout_ListTitelElement());
		}
		else
		{
			
			//hier noch unterscheiden zwischen component-groups und normalen componenten
			if (oTitleComponent instanceof MyE2IF__ComponentContainer)
			{
				oTitleComponent.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_GRID_Layout4ComponentGroupsHeader());
			}
			else
			{
				if (E2_NavigationList_ComponentHelper.get_bMUST_BE_RIGHT(oOriginalComponent))
				{
					oTitleComponent.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_ROW_LayoutRightTOPHeader());
				}
				else
				{
					oTitleComponent.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_ROW_LayoutLeftTOPHeader());
				}
			}
		}
	}
	
	


	// setzt zu dem definierten titel-layoutelement  das passenden layout-data
	public static void set_GRID_LayoutData_InList(Component oCompNew) throws myException
	{
		if ( oCompNew instanceof MyE2IF__Component && 
			((MyE2IF__Component)oCompNew).EXT().get_oLayout_ListElement()!=null &&
			((MyE2IF__Component)oCompNew).EXT().get_oLayout_ListElement() instanceof GridLayoutData)
		{
			oCompNew.setLayoutData(((MyE2IF__Component)oCompNew).EXT().get_oLayout_ListElement());
		}
		else
		{
			//hier noch unterscheiden zwischen component-groups und normalen componenten
			if (oCompNew instanceof MyE2IF__ComponentContainer)
			{
				oCompNew.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_GRID_Layout4ComponentGroups());
			}
			else
			{
				
				if (E2_NavigationList_ComponentHelper.get_bMUST_BE_RIGHT(oCompNew))
				{
					oCompNew.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());
				}
				else
				{
					oCompNew.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutLeftTOP());
				}
			}
		}
	}




	// setzt zu dem definierten titel-layoutelement  das passenden layout-data
	public static void set_COLUMN_LayoutData_InList(Component oCompNew) throws myException
	{
		if ( oCompNew instanceof MyE2IF__Component && 
			((MyE2IF__Component)oCompNew).EXT().get_oLayout_ListElement()!=null &&
			((MyE2IF__Component)oCompNew).EXT().get_oLayout_ListElement() instanceof ColumnLayoutData)
		{
			oCompNew.setLayoutData(((MyE2IF__Component)oCompNew).EXT().get_oLayout_ListElement());
		}
		else
		{
			
			if (E2_NavigationList_ComponentHelper.get_bMUST_BE_RIGHT(oCompNew))
			{
				oCompNew.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_COLUMN_LayoutRightTOP());
			}
			else
			{
				oCompNew.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_COLUMN_LayoutLeftTOP());
			}
		}
	}


	

	// setzt zu dem definierten titel-layoutelement  das passenden layout-data
	public static void set_ROW_LayoutData_InList(Component oCompNew) throws myException
	{
		if ( oCompNew instanceof MyE2IF__Component && 
			((MyE2IF__Component)oCompNew).EXT().get_oLayout_ListElement()!=null &&
			((MyE2IF__Component)oCompNew).EXT().get_oLayout_ListElement() instanceof RowLayoutData)
		{
			oCompNew.setLayoutData(((MyE2IF__Component)oCompNew).EXT().get_oLayout_ListElement());
		}
		else
		{
			if (E2_NavigationList_ComponentHelper.get_bMUST_BE_RIGHT(oCompNew))
			{
				oCompNew.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_ROW_LayoutRightTOP());
			}
			else
			{
				oCompNew.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_ROW_LayoutLeftTOP());
			}
		}
	}


	
	
	public static boolean get_bMUST_BE_RIGHT(Component oCompInList) throws myException
	{
		boolean bMustBeRight = false;
		
		if (oCompInList instanceof MyE2IF__DB_Component)
		{
			if (((MyE2IF__DB_Component)oCompInList).EXT_DB().get_oSQLField().get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
			{
				if (oCompInList instanceof MyE2_Label || oCompInList instanceof MyE2_Button)
				{
					bMustBeRight = true;
				}
			}
		}
		
		return bMustBeRight;
	}
	
	
	
	
	/*
	 * methode schaut in dem EXT() einer Listenkomponente, ob fuer den Header dieser komponente ein
	 * Style vorhanden ist und weisst (falls vorhanden) diesen zu
	 */
	public static void pruefe_und_setze_Header_style_aus_EXT(Component oComp, Component oCompGeneratedHeader)
	{
		//jetzt nachsehen, ob die komponente, fuer die ein Header erzeugt worden ist, auch einen mutablestyle dafuer enthaelt
		if (oComp instanceof MyE2IF__Component)
		{
			if (((MyE2IF__Component) oComp).EXT().get_oStyle_4_ListHeaderComponent()!=null)
			{
				oCompGeneratedHeader.setStyle(((MyE2IF__Component) oComp).EXT().get_oStyle_4_ListHeaderComponent());
				if (oCompGeneratedHeader instanceof E2_ButtonListSorter) {
					((E2_ButtonListSorter)oCompGeneratedHeader).get_oButtonSort().setStyle(((MyE2IF__Component) oComp).EXT().get_oStyle_4_ListHeaderComponent());
				}
			}
		}

	} 
	
	

	/**
	 * 	/*
	 * 2014-09-04: wrapper, der die erzeugten header-components in ein Grid "einwickelt", das evtl. vorhandene summen-komponenten enhaelt.
	 * Dieser aufruf ergolt nach der kompletten definition der komponenten, einschliesslich Layoutdata usw. diese wird von der Komponente
	 * auf die neue Grid-komponente uebertragen
	 *
	 *
	 * @param oCompFromListMAP
	 * @param oGeneratedTitelComponent
	 * @return
	 */
	public static Component wrapHeaderWithAddonKomponents(MyE2IF__Component oCompFromListMAP,  Component oGeneratedTitelComponent) {
		
		Component oRueck = oGeneratedTitelComponent;
		
		LayoutData  oLayoutGeneratedComponent = oGeneratedTitelComponent.getLayoutData();   //bereits vordefiniertes layoutdata fuer diese komponente, je nachdem
		 																					//ob das element direkt in die Liste oder in eines der multiComponents eingefuegt wird
		
		GridLayoutData  oLayout4NewWrapperComponent = MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,0,1,0));
		
		//2014-08-29: pruefen, ob die komponente weitere buttons fuer den listentitel enthaelt
		Vector<E2IF__BelongsToNavigationList> 	vADDON_ListenTitel = 	((MyE2IF__Component)oCompFromListMAP).EXT().get_vZusatzKomponentenInListenTitel();
		
		
		if (vADDON_ListenTitel!=null && vADDON_ListenTitel.size()>0) {
			
			int iSpalten = 1+vADDON_ListenTitel.size();
			MyE2_Grid  oGridWrapper = new MyE2_Grid(1+vADDON_ListenTitel.size(), MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			oGridWrapper.add( oGeneratedTitelComponent,oLayout4NewWrapperComponent);
			for (E2IF__BelongsToNavigationList oCompADDON: vADDON_ListenTitel) {
				if (oCompADDON instanceof Component) {
					oGridWrapper.add((Component)oCompADDON, oLayout4NewWrapperComponent);
				}
			}
			
//			oGridWrapper.add(new MyE2_Label("test"), iSpalten, E2_INSETS.I(0,0,0,0));
			//jetzt noch verdeckte ergebnisspalten hinterlegen
			
			GridLayoutData  oLayout4NewWrapperComponentZusatz = MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,0,1,0));
			oLayout4NewWrapperComponentZusatz.setColumnSpan(iSpalten);
			for (E2IF__BelongsToNavigationList oCompADDON: vADDON_ListenTitel) {
				if (oCompADDON instanceof CALC_ZUSATZKOMPONENTE_IF && (((CALC_ZUSATZKOMPONENTE_IF)oCompADDON)._GET_ZUSATZ_Komponente()!=null)) {
					((CALC_ZUSATZKOMPONENTE_IF)oCompADDON)._RESET_ZUSATZ_Komponente();
					oGridWrapper.add(((CALC_ZUSATZKOMPONENTE_IF)oCompADDON)._GET_ZUSATZ_Komponente(), oLayout4NewWrapperComponentZusatz);
				}
			}
			
			
			
			oRueck = oGridWrapper;
			oRueck.setLayoutData(oLayoutGeneratedComponent);
		}
		
		return oRueck;
	}
	
	
	
	
	
	
}
