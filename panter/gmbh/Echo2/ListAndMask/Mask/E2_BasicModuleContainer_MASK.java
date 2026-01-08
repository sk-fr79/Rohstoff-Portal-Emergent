package panter.gmbh.Echo2.ListAndMask.Mask;

import java.util.Map;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.ADDING_FieldLoggingButtons;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF.MMC_ERSETZUNGS_HASH_STD;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF.MMC_OWN_RECLIST_MAIL_AUS_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF.MMC_StartMail_PopUP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_RowAutomaticVisibleWheFilled;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.specialValidation.DynamicRuleSetter;
import panter.gmbh.Echo2.staticStyles.Style_Row_Normal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

public class E2_BasicModuleContainer_MASK extends E2_BasicModuleContainer 
{
	
	/*
	 * Vector enthaelt E2_ComponentMAPs der maske
	 */
	private E2_vCombinedComponentMAPs 		vComponentMAPs = new E2_vCombinedComponentMAPs();
	
	
	/*
	 * komponente, in der die maskenelemente angeordnet sind, die blaupause mit den Inhalten
	 * aus der E2_ComponentMAP zu einer maske angeordnet
	 */
	private IF_BaseComponent4Mask			oComponentWithMaskElements = null;



	/*
	 * oRowForButtons: in diese row werden die gewuenschten steuerkomponenten der maske eingefuegt
	 * und das element oComponentWithMask;
	 */
	private MyE2_Row						oRowForHeadline = new MyE2_Row(new Style_Row_Normal(0,E2_INSETS.I_0_0_2_0));

	
	/*
	 * oRowForButtons: in diese row werden die gewuenschten steuerkomponenten der maske eingefuegt
	 * und das element oComponentWithMask;
	 */
	private MyE2_Row						oRowForButtons = new MyE2_Row(new Style_Row_Normal(0,E2_INSETS.I_0_0_2_0));
	
	
	
	/*
	 * oRowForAutomaticTools: in diese row werden automatische Steuerkomponenten eingefuegt
	 * und das element oComponentWithMask;
	 */
	private MyE2_RowAutomaticVisibleWheFilled	oRowForAutomaticTools = new MyE2_RowAutomaticVisibleWheFilled(new Style_Row_Normal(0,E2_INSETS.I_0_0_2_0));
	
	
	
	
	
	/*
	 * contentPane
	 */
	private E2_ContentPane					oContentPane = null;
	

	
	/*
	 * falls die Maske mittels eines XX_ButtonActionAgent_FromListToMask aufgerufen wird,
	 * wird dieses feld mit der rufenden E2_NavigationList besetzt
	 */
	private E2_NavigationList              oNavigationListWhichBelongsToTheMask = null;
	
	
	
	/*
	 * leere constructor, muss initialisiert werden 
	 */
	public E2_BasicModuleContainer_MASK()
	{
		super();
	}
	
	
	

	/**
	 * @param maskMAP
	 * @param ocomponentWithMaskElements
	 * @param oextWidth
	 * @param oextHeight
	 * initialisierung muss vorgenommen werden
	 * @throws myException 
	 */
	public void INIT(	E2_ComponentMAP 		maskMAP, 
						IF_BaseComponent4Mask 	ocomponentWithMaskElements, 
						Extent					oextWidth,
						Extent					oextHeight) throws myException
			
	{
		
		this.INIT(maskMAP, ocomponentWithMaskElements, oextWidth, oextHeight,false);
		
	}


	
	/**
	 * @param maskMAP
	 * @param ocomponentWithMaskElements
	 * @param oextWidth
	 * @param oextHeight
	 * @param showSeparator
	 * initialisierung muss vorgenommen werden
	 * @throws myException 
	 */
	public void INIT(	E2_ComponentMAP 		maskMAP, 
						IF_BaseComponent4Mask 	ocomponentWithMaskElements, 
						Extent					oextWidth,
						Extent					oextHeight,
						boolean             	showSeparator) throws myException
			
	{
		this.vComponentMAPs.add( maskMAP);                                    // die hauptmap ...
		oComponentWithMaskElements = ocomponentWithMaskElements;
		
		this.oRowForHeadline.setVisible(false);            // wird erst true, wenn eine headline eingeblendet wird
		
		MyE2_Row oRowButtonsUndHeadline = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		
		oRowButtonsUndHeadline.add(this.oRowForButtons, E2_INSETS.I_0_0_10_0);
		oRowButtonsUndHeadline.add(this.oRowForAutomaticTools, E2_INSETS.I_0_0_10_0);
		oRowButtonsUndHeadline.add(this.oRowForHeadline, E2_INSETS.I_0_0_0_0);
		
		this.add(oRowButtonsUndHeadline, new Insets(4,0,2,2));
		
		if (showSeparator)
		{
			this.add(new Separator(),E2_INSETS.I_0_0_0_0);
		}
		
		this.add((Component)this.oComponentWithMaskElements, new Insets(4,0,2,2));

		
		/*
		 * zusatzeinblendungen in der maske (z.B. LOGGING-Info-Button) einfuegen
		 */
		if (this.oComponentWithMaskElements.get_Basic_Mask_Container_Components()!=null)
		{
			for (IF_ADDING_Allowed oContainer: this.oComponentWithMaskElements.get_Basic_Mask_Container_Components())
			{
				new ADDING_FieldLoggingButtons(oContainer);
			}
		}
		//ende der zusatzeinblendungen
		
		
		
		if (oextWidth != null) this.set_oExtWidth(oextWidth);
		if (oextHeight != null) this.set_oExtHeight(oextHeight);
		
		maskMAP.set_oModulContainerMASK_This_BelongsTo(this);
		
		
		
		//2012-02-27:  maskenvalidierung aus der datenbank einschalten, wenn ein modulkenner gesetzt ist
		if (S.isFull(this.get_MODUL_IDENTIFIER()))
		{
			new DynamicRuleSetter(maskMAP, this.get_MODUL_IDENTIFIER());
		}
		
		
		
		//2014-05-09:  eMail-Versende-Makros einblenden, wenn vorhanden
		if (S.isFull(this.get_MODUL_IDENTIFIER()))
		{
			MMC_OWN_RECLIST_MAIL_AUS_MASK  oMMR = new MMC_OWN_RECLIST_MAIL_AUS_MASK(this.get_MODUL_IDENTIFIER());
			
			if (oMMR.get_vKeyValues().size()>0) {
				RowLayoutData  oRL = new RowLayoutData();
				oRL.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
				this.oRowForAutomaticTools.add(new MMC_StartMail_PopUP(oMMR, new MMC_ERSETZUNGS_HASH_STD(this)),oRL);
			}
		}
	
		//2015-07-03: hochlade-button der bezogen auf die Tabellen der ComponentMAPS einblenden 	
		this.pruefe_UploadArchivButton(maskMAP);
		
		
	}

	
	
	
	public void add_SubTableComponentMAP(E2_ComponentMAP oSubMap) throws myException
	{
		if (oSubMap.get_oSQLFieldMAP().get_bIsSQLMapLEADINGMAP())
			throw new myException("E2_MaskContainer:add_SubTableComponentMAP: Added E2_ComponentMAP MUST have an SQLFieldMAP which ist not a leading MAP!");
		
		this.vComponentMAPs.add(oSubMap);
		
		oSubMap.set_oModulContainerMASK_This_BelongsTo(this);
		
		
		//2012-02-27:  maskenvalidierung aus der datenbank einschalten, wenn ein modulkenner gesetzt ist
		if (S.isFull(this.get_MODUL_IDENTIFIER())) 	{
			new DynamicRuleSetter(oSubMap, this.get_MODUL_IDENTIFIER());
		}
		
		//2015-07-03: hochlade-button der bezogen auf die Tabellen der ComponentMAPS einblenden 	
		this.pruefe_UploadArchivButton(oSubMap);
	
	}
	

	
	//2015-07-03: hochlade-button der bezogen auf die Tabellen der ComponentMAPS einblenden 
	private void pruefe_UploadArchivButton(E2_ComponentMAP map) throws myException {
		if (map instanceof E2_BasicModulContainer_MASK_ADDON_IN_MASK) {
			RowLayoutData  oRL = new RowLayoutData();
			oRL.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
			Vector<Component> v_addon_buttons = ((E2_BasicModulContainer_MASK_ADDON_IN_MASK)map).generate_MaskComponents(map);
			
			if (v_addon_buttons != null && v_addon_buttons.size()>0) {
				for (Component  comp: v_addon_buttons) {
					this.oRowForAutomaticTools.add(comp,oRL);
				}
			}
		}

	}
	
	
	public void add_Headline(Component oCompHeadline)
	{
		this.oRowForHeadline.removeAll();
		this.oRowForHeadline.add(oCompHeadline);
		this.oRowForHeadline.setVisible(true);
	}
	

	public E2_vCombinedComponentMAPs		 	get_vCombinedComponentMAPs()			{		return this.vComponentMAPs;	}
//	public Component 							get_oComponentWithMask()				{		return oComponentWithMaskElements;	}
	public MyE2_Row 							get_oRowForButtons()					{		return oRowForButtons;	}
	public E2_ContentPane 						get_oContentPane()						{		return oContentPane;	}

	
	
	
	/**
	 * Sucht recursiv eine TabbedPane innerhalb des Containers
	 * Falls er welche findet, dann liefert er die erste zurueck, sonst null
	 * @return
	 */
	public MyE2_TabbedPane get_oTabbedPane()
	{
		MyE2_TabbedPane oPane = null;
		
		E2_RecursiveSearch_Component oSearch = new E2_RecursiveSearch_Component(this,bibALL.get_Vector(MyE2_TabbedPane.class.getName()),null);
	
		if (oSearch.get_vAllComponents().size()>0)
		{
			oPane = (MyE2_TabbedPane)oSearch.get_vAllComponents().get(0);
		}
		return oPane;
	}
	


	
	/**
	 * 
	 * @return s kombinierte Inputmap mit keys, die sich zusammensetzen aus:
	 *           <TableName>.<Fieldname> 
	 * @throws myException
	 */
	public SQLMaskInputMAP  get_CombinedMaskInputMAP() throws myException
	{
		SQLMaskInputMAP oMaskInputMap = new SQLMaskInputMAP();
		
		for (int i=0;i<this.get_vCombinedComponentMAPs().size();i++)
		{
			E2_ComponentMAP oMap = this.get_vCombinedComponentMAPs().get(i);
			String cTableName = oMap.get_oSQLFieldMAP().get_cMAIN_TABLE();
			
			SQLMaskInputMAP oTeilInput = new SQLMaskInputMAP(oMap,false);
			
			for (Map.Entry<String,String> oEntry: oTeilInput.entrySet())
			{
				oMaskInputMap.put((cTableName+"."+oEntry.getKey()).toUpperCase(), oEntry.getValue());
			}
		}
		
		return oMaskInputMap;
	}
	
	
	

	/**
	 * 
	 * @param cTableName_plus_FieldName
	 * @return s Sucht MyE2IF__Component mit der kombination Name der Tabelle der ComponentMAP und HashName/Feldname der ComponentMAP
	 *           TableName.Fieldname aus der Maske
	 * @throws myException
	 */
	public MyE2IF__Component  get_Component(String cTableName_plus_FieldName) throws myException
	{
		MyE2IF__Component ocompRueck = null;
		String cUpperVergleich = cTableName_plus_FieldName.toUpperCase();
		
		for (int i=0;i<this.get_vCombinedComponentMAPs().size();i++)
		{
			E2_ComponentMAP oMap = this.get_vCombinedComponentMAPs().get(i);

			for (Map.Entry<String,MyE2IF__Component> oEntry: oMap.entrySet())
			{
				String cTestString = (oMap.get_oSQLFieldMAP().get_cMAIN_TABLE()+"."+oEntry.getKey()).toUpperCase();
				
				if (cUpperVergleich.equals(cTestString))
				{
					if (ocompRueck != null)
					{
						throw new myException(this,cTableName_plus_FieldName+" was found more than one time in Mask !" );
					}
					ocompRueck = oEntry.getValue();
				}
			}
		}
		return ocompRueck;
	}
	
	
	
	/**
	 * 
	 * @param HashkeyFromComponentMAP
	 * @return s Sucht Vector<MyE2IF__Component> mit komponenten mit HashkeyFromComponentMAP aus allen get_vCombinedComponentMAPs
	 * @throws myException
	 */
	public Vector<MyE2IF__Component>  get_vComponents(String HashkeyFromComponentMAP)
	{
		Vector<MyE2IF__Component> vCompRueck = new Vector<MyE2IF__Component>();
		
		String cUpperVergleich = HashkeyFromComponentMAP.toUpperCase();
		
		for (int i=0;i<this.get_vCombinedComponentMAPs().size();i++)
		{
			E2_ComponentMAP oMap = this.get_vCombinedComponentMAPs().get(i);

			for (Map.Entry<String,MyE2IF__Component> oEntry: oMap.entrySet())
			{
				if (cUpperVergleich.equals(oEntry.getKey().toUpperCase()))
				{
					vCompRueck.add(oEntry.getValue());
				}
			}
		}
		return vCompRueck;
	}

	
	

	/**
	 * 
	 * @param cTableName_plus_FieldName
	 * @return s Sucht MyE2IF__Component mit der kombination Name der Tabelle der ComponentMAP und HashName/Feldname der ComponentMAP
	 *           TableName.Fieldname aus der Maske
	 * @throws myException
	 */
	public MyE2IF__DB_Component  get_DBComponent(String cTableName_plus_FieldName) throws myException
	{
		MyE2IF__Component ocompRueck = this.get_Component(cTableName_plus_FieldName);
		if (ocompRueck != null && (!(ocompRueck instanceof MyE2IF__DB_Component)))
		{
			throw new myException(this,cTableName_plus_FieldName+" is no MyE2IF__DB_Component" );
		}
		return (MyE2IF__DB_Component)ocompRueck;
	}
	

	

	
	/*
	 * sucht die E2_ComponentMAP einer tabelle aus einer Maske heraus
	 */
	public E2_ComponentMAP get_ComponentMAP_FROM_TABLE(String cTableNAME) throws myException
	{
		for (int i=0;i<this.vComponentMAPs.size();i++)
		{
			if (this.vComponentMAPs.get(i).get_oSQLFieldMAP().get_cMAIN_TABLE().toUpperCase().equals(cTableNAME.toUpperCase()))
			{
				return this.vComponentMAPs.get(i);
			}
		}
		
		return null;
	}


	public E2_NavigationList get_oNavigationListWhichBelongsToTheMask()
	{
		return oNavigationListWhichBelongsToTheMask;
	}


	public void set_oNavigationListWhichBelongsToTheMask(E2_NavigationList navigationListWhichBelongsToTheMask)
	{
		oNavigationListWhichBelongsToTheMask = navigationListWhichBelongsToTheMask;
	}


	/*
	 * 2014-05-09
	 */
	public MyE2_Row get_oRowForAutomaticTools() {
		return oRowForAutomaticTools;
	}


	/*
	 * 2014-05-09
	 */
	public MyE2_Row get_oRowForHeadline() {
		return oRowForHeadline;
	}




	/**
	 * liefert die maskenkomponente (meist Grid, das die editierelemente enthaelt)
	 * @return
	 */
	public IF_BaseComponent4Mask get_oComponentWithMaskElements() {
		return oComponentWithMaskElements;
	}
	
	//2015-02-04: neuer setter
	public void set_IF_BaseComponent4Mask(IF_BaseComponent4Mask oComponentContainer) {
		this.oComponentWithMaskElements=oComponentContainer;
	}

	public MyE2IF__Component get_ComponentWithMaskElements() throws myException {
		if (this.oComponentWithMaskElements instanceof MyE2IF__Component) {
			return (MyE2IF__Component)oComponentWithMaskElements;
		} else {
			throw new myException(this, "ComponentWithMaskElements is not typeof MyE2IF__Component");
		}
	}
	
	
	
}
