package panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER;


import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldSetter_AND_Validator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import echopointng.able.Scrollable;


public class MyE2_DBC_DaughterTable extends MyE2_Column implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy
{
    
    
    private E2_NavigationList 				oNavigationList = null;
    private E2_ComponentMAP 				oComponentMAP_From_Mask = null;             // componentmap der maske
    private E2_ComponentMAP 				oOwnComponentMAP_ForList = null;            // componentMap der liste (tochtermap)
    
	private MyE2EXT__DB_Component 			oEXTDB=new MyE2EXT__DB_Component(this);
    
	
	/*
	 * diese komponente merkt sich den status beim aufbau der maske, damit beim speichern
	 * unterschieden werden kann
	 */
	private String 							cActual_STATUS_MAP = null;
	
 	
	/*
	 * eine Vector mit komponenten, die das Interface MyE2IF__Component erfuellen muss und 
	 * im set_enabled_for_edit mitberuecksichtigt wird (z.B. neueingabe-Button) 
	 */
	private Vector<MyE2IF__Component> 	     vComponentForDifferentTasks = new Vector<MyE2IF__Component>();
	
	/*
	 * eine row oberhalb/unterhalb der liste anzeigen (fuer komponenten von aussen)
	 */
	private MyE2_Row						oRow_In_TOP = new MyE2_Row();
	private MyE2_Row						oRow_In_BOTTOM = new MyE2_Row();

	
	/*
	 * eine hoehe, die die hoehe der containerEx - komponente, die die navigationlist umfasst,
	 * definiert
	 * 
	 */
	private Extent							oContainerExScrollHeight = new Extent(300);
	private Extent							oContainerExScrollWidth =null;
	
	
	private MyE2_ContainerEx 				oContainerEx = new MyE2_ContainerEx();
	
	
	/*
	 *	falls die tochtertabelle nur zur anzeige eingeblendet wird, aber nie bearbeitet wird
	 *	kann sie passiv gesetzt werden. dann werden keine updates bei speichern der uebergeordneten
	 *  maske erzeugt 
	 */
	private boolean    						bDaughterIsPassive = false;
	
	
	
	
	/*
	 * fuer benutzung in ungebundenen zustaenden existieren die methoden UB_***
	 * hierfuer ist immer bedingung, dass eine ID aus der Mutter-Tabelle vorliegt.
	 * Dazu das feld UB_MOTHER_ID
	 */
	private String  						UB_MOTHER_ID = null; 
	
	
	
	
 

	public MyE2_DBC_DaughterTable() 
    {
        super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
        this.oRow_In_TOP.setVisible(false);    //wenn nix drin ist, dann leer
    }


    /**
     * @param osqlField (primaerschluessel der hauptmaskentabelle)
     * @param ocomponentMAP_From_Mother
     * @param oownComponentMAP_ForDaughterTable (muss eine sqlFieldMAP haben mit einem SQLFieldJoinOutside, das das gleiche feld als 
     * 									aussenverbindungsfeld hat, wie das hier uebergebene sqlField)
     * @param StyleForList
     * @throws myException
     */
    public void INIT_DAUGHTER(	SQLFieldForPrimaryKey 	osqlField, 
								E2_ComponentMAP 		ocomponentMAP_From_Mother,
								E2_ComponentMAP 		oownComponentMAP_ForDaughterTable, 
								MutableStyle 			StyleForList) throws myException
	{
 
    	/*
    	 * aenderung (2006-11-07): die mutter-componentmap kann in ihrer maske auch eine untergeordnete tabelle sein,
    	 * d.h. die bedingung, dass die ocomponentMAP_From_Mother eine sqlfieldmap mit der eigenschaft get_bIsSQLMapLEADINGMAP() sein muss.
    	 *      kann entfallen
    	 */
    	if (oownComponentMAP_ForDaughterTable.get_oSQLFieldMAP().get_bIsSQLMapLEADINGMAP())
    		throw new myException("MyE2_DBC_DaughterTable:Constructor: oownComponentMAP_ForList CANNOT BE leading map !");
    	
        if(	oownComponentMAP_ForDaughterTable.get_oSQLFieldMAP().get_oSQLFieldJoinOutside().get_oFieldFromConnectedTable()!=	osqlField)
        	throw new myException("MyE2_DBC_DaughterTable:Constructor: The SQLField from this Component MUST be the Outside - Field from the ListComponentMAP !");
        
        this.oComponentMAP_From_Mask = ocomponentMAP_From_Mother;
        this.oOwnComponentMAP_ForList = oownComponentMAP_ForDaughterTable;
        
		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(osqlField);

		this.oNavigationList = new E2_NavigationList();
		this.oNavigationList.INIT_WITH_ComponentMAP(this.oOwnComponentMAP_ForList, StyleForList, null);
        
		/*
		 * hier gibts keine navigatoren, es wird immer alles angezeigt
		 */
		this.oNavigationList.get_vectorSegmentation().set_bOnlyOneSegment(true);
		this.oNavigationList.hide_NavigationsElements();
		
		
		oContainerEx.setHeight(this.oContainerExScrollHeight);
		oContainerEx.setScrollBarPolicy(Scrollable.AUTO);
		oContainerEx.add(this.oNavigationList);
		
		this.add(this.oRow_In_TOP);
		this.add(oContainerEx,new Insets(0,0,0,0));
		this.add(this.oRow_In_BOTTOM);
		
		
		/*
		 * jetzt alle EXT_DB()-komponenten unsortierbar schalten, da eine sortierung einen
		 * undefinierbaren effekt beim neuaufbau der liste haette
		 */
		this.oOwnComponentMAP_ForList.set_allDBComponents_Sortable(false);
		
		/*
		 * eigene fieldvalidator dem feld hinzufuegen, dieser prueft die eingaben und evtl. map-validatoren
		 */
		this.EXT().add_FieldSetters_AND_Validator__AfterReadInputMAP(new MyE2_DBC_DaughterTable.ownFieldValidator_AfterReadInputMAP(this));

	}
    
	
	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{

		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(true) && this.EXT().get_bCanBeEnabled();

		if (this.vComponentForDifferentTasks.size()>0)
		{
			for (int i=0;i<this.vComponentForDifferentTasks.size();i++)
			{
				if (this.vComponentForDifferentTasks.get(i) instanceof MyE2IF__Component)
					((MyE2IF__Component)this.vComponentForDifferentTasks.get(i)).set_bEnabled_For_Edit(bVoraussetzung);
				else
					throw new myException("MyE2_DBC_DaughterTable:set_bEnabled_For_Edit: Component MUST implement the interface MyE2IF__Component!");
				
			}
			
		}
		
		Vector<E2_ComponentMAP>  vE2_ComponentMAPs =	this.oNavigationList.get_vComponentMAPS();
		
		for (int i=0;i<vE2_ComponentMAPs.size();i++)
		{
			((E2_ComponentMAP)vE2_ComponentMAPs.get(i)).do_MapSettings_BEFORE(E2_ComponentMAP.STATUS_UNDEFINED);
			((E2_ComponentMAP)vE2_ComponentMAPs.get(i)).set_AllComponentsEnabled_For_Edit(bVoraussetzung,E2_ComponentMAP.STATUS_UNDEFINED);
		}
		
	}


	
	public void prepare_ContentForNew(boolean bSetDefaults) throws myException
	{
		/*
		 * 2017-02-09: hier wird ein interface ueberprueft
		 */
		if (this instanceof IF_DBC_DaughterTable_do_something_before_filling) {
			((IF_DBC_DaughterTable_do_something_before_filling)this).prepare4NewMask(this);
		}
		
		
		/*
		 * tabelle wird leer
		 */
		this.oNavigationList.get_vComponentMAPS_NEW().removeAllElements();
		this.oNavigationList.Show_Empty_NavigationList();
		
		 /*
		  * der sqlfieldmap wird eine bedingung mit leerer antwort uebergeben, damit ein wvtl.
		  * vorhandener sortierknopf keine falschen listeneintraege zurueckgibt beim neuaufbau.
		  * Im Zustand "Neueingabe" kann noch keine beschraenkung der liste vorhanden sein
 		  */
		 SQLFieldJoinOutside oFieldJoin = this.oOwnComponentMAP_ForList.get_oSQLFieldMAP().get_oSQLFieldJoinOutside();
		 String cZusatzWhere = oFieldJoin.get_cTableName()+"."+oFieldJoin.get_cFieldName()+"=-1";
		 this.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_BEDINGUNG_STATIC();
		 this.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().add_BEDINGUNG_STATIC(cZusatzWhere);
		
		this.cActual_STATUS_MAP = E2_ComponentMAP.STATUS_NEW_EMPTY;
		
	}


    

	public String get_cActualMaskValue() throws myException
	{
		return null;
	}


	public String get_cActualDBValueFormated() throws myException
	{
		return null;
	}


	public void set_cActualMaskValue(String cText) throws myException
	{
	}


	public void set_cActual_Formated_DBContent_To_Mask(String cID_MASTERTABLE, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DBC_DaughterTable:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");

		
		/*
		 * 2017-02-09: hier wird ein interface ueberprueft
		 */
		if (this instanceof IF_DBC_DaughterTable_do_something_before_filling) {
			((IF_DBC_DaughterTable_do_something_before_filling)this).prepare4Filling(this);
		}

		
		
		this.cActual_STATUS_MAP = cMASK_STATUS;
		
		 String cUnformated = this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_cStringForDataBase(cID_MASTERTABLE, true, false);
		 
		 /*
		  * maske fuellen mit der bedingung, die die verbindung zur muttertabelle liefert
		  */
		 SQLFieldJoinOutside oFieldJoin = this.oOwnComponentMAP_ForList.get_oSQLFieldMAP().get_oSQLFieldJoinOutside();
		 String cZusatzWhere = oFieldJoin.get_cTableName()+"."+oFieldJoin.get_cFieldName()+"="+cUnformated;

		 this.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_BEDINGUNG_STATIC();
		 this.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().add_BEDINGUNG_STATIC(cZusatzWhere);
		 
		 this.oNavigationList.get_vComponentMAPS_NEW().removeAllElements();
		 this.oNavigationList.Fill_NavigationList("");
		 
		 /*
		  * falls der status COPY vorliegt, dann muss in den componentMAPs der navigationlist 
		  * das actuelle resultmap auf null gesetzt werden
		  */
		 if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY))
		 {
			 Vector<E2_ComponentMAP> vE2_ComponentMAPS = this.oNavigationList.get_vComponentMAPS();
			 for (int i=0;i<vE2_ComponentMAPS.size();i++)
			 {
				 ((E2_ComponentMAP)vE2_ComponentMAPS.get(i)).set_InternResultMAP_TO_NULL();
			 }
		 }
		 
		 
		 
 	}


	public void set_bIsComplexObject(boolean bisComplex)
	{
	}


	public boolean get_bIsComplexObject()
	{
		return true;
	}


	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAPFromMother, SQLMaskInputMAP oMaskInputMap) throws myException
	{
	    
	    Vector<String> vRueck = new Vector<String>();
	    

	    if (this.bDaughterIsPassive)
	    	return vRueck;                   // ende

	    
	    /*
	     * bei neueingabe koennen die mitglieder von vE2_ComponentMAPs nur aus einer kopie kommen
	     */
		Vector<E2_ComponentMAP> 	vE2_ComponentMAPs = 		this.oNavigationList.get_vComponentMAPS();
		Vector<E2_ComponentMAP> 	vE2_ComponentMAPs_NEW = 	this.oNavigationList.get_vComponentMAPS_NEW();
		
		for (int i=0;i<vE2_ComponentMAPs.size();i++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)vE2_ComponentMAPs.get(i);
			if (!this.get_bMapIsMarkedToDelete(oMap))
			{
				SQLMaskInputMAP oInputMap = oMap.get_ActualInputMAP_And_MarkFalseInput();		// inputmap
				vRueck.addAll(oMap.get_oSQLFieldMAP().get_SQL_INSERTSTACK(oInputMap));
				vRueck.addAll(oMap.get_InsertStackFromComplexFields(oMap,oInputMap));
			}
		}
		for (int i=0;i<vE2_ComponentMAPs_NEW.size();i++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)vE2_ComponentMAPs_NEW.get(i);
			if (!this.get_bMapIsMarkedToDelete(oMap))
			{
				SQLMaskInputMAP oInputMap = oMap.get_ActualInputMAP_And_MarkFalseInput();		// inputmap
				vRueck.addAll(oMap.get_oSQLFieldMAP().get_SQL_INSERTSTACK(oInputMap));
				vRueck.addAll(oMap.get_InsertStackFromComplexFields(oMap,oInputMap));
			}
		}
	    
	    return vRueck;
	}


	
	
	
	
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAPFromMother, SQLMaskInputMAP oMaskInputMap) throws myException
	{
	   
        Vector<String> vRueck = new Vector<String>();
        

	    if (this.bDaughterIsPassive)
	    	return vRueck;                   // ende

        
        /*
         * fuer tochterzeilen, dei beim update neu eingetragen wurden, muss der aktuelle wert des
         * Connect-feldes gefunden werden
         */
        String cNAME_OF_OutsideConnector = this.oOwnComponentMAP_ForList.get_oSQLFieldMAP().get_oSQLFieldJoinOutside().get_oFieldFromConnectedTable().get_cFieldLabel();
        String cACTUAL_FORMATED_VALUE_JoinOutside = oE2_ComponentMAPFromMother.get_oInternalSQLResultMAP().get_FormatedValue(cNAME_OF_OutsideConnector);
        
        
		Vector<E2_ComponentMAP> 	vE2_ComponentMAPs = 		this.oNavigationList.get_vComponentMAPS();
		Vector<E2_ComponentMAP>		vE2_ComponentMAPs_NEW = 	this.oNavigationList.get_vComponentMAPS_NEW();
		
		for (int i=0;i<vE2_ComponentMAPs.size();i++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)vE2_ComponentMAPs.get(i);
			if (!this.get_bMapIsMarkedToDelete(oMap))
			{
				SQLMaskInputMAP oInputMap = oMap.get_ActualInputMAP_And_MarkFalseInput();		// inputmap
				vRueck.addAll(oMap.get_oSQLFieldMAP().get_SQL_UPDATESTACK(oMap.get_oInternalSQLResultMAP(),oInputMap));
				vRueck.addAll(oMap.get_UpdateStackFromComplexFields(oMap,oInputMap));
			}
			else
			{
				vRueck.addAll(oMap.get_oSQLFieldMAP().get_SQL_DELETESTACK_From_FORMATED_KEY(oMap.get_oInternalSQLResultMAP().get_cFormatedROW_ID()));
			}
		}
		for (int i=0;i<vE2_ComponentMAPs_NEW.size();i++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)vE2_ComponentMAPs_NEW.get(i);
			if (!this.get_bMapIsMarkedToDelete(oMap))
			{
				SQLMaskInputMAP oInputMap = oMap.get_ActualInputMAP_And_MarkFalseInput();		// inputmap
				vRueck.addAll(oMap.get_oSQLFieldMAP().get_SQL_INSERTSTACK(oInputMap,cACTUAL_FORMATED_VALUE_JoinOutside));
				vRueck.addAll(oMap.get_InsertStackFromComplexFields(oMap,oInputMap));
			}
		}
        
        return vRueck;
	}


	
	
	
	
	
	
	/**
	 * @return Alle ComponentMAPs die nicht zum loeschen markiert sind
	 */
	public Vector<E2_ComponentMAP> get_vE2_ComponentMAPs_NewAndEdit_WithoutDeleteMarker()
	{
		Vector<E2_ComponentMAP> vRueck = 					new Vector<E2_ComponentMAP>();
		Vector<E2_ComponentMAP> vE2_ComponentMAPs = 		this.oNavigationList.get_vComponentMAPS();
		Vector<E2_ComponentMAP> vE2_ComponentMAPs_NEW = 	this.oNavigationList.get_vComponentMAPS_NEW();
		
		for (E2_ComponentMAP oMap: vE2_ComponentMAPs)
		{
			if (!this.get_bMapIsMarkedToDelete(oMap))
			{
				vRueck.add(oMap);
			}
		}
		for (E2_ComponentMAP oMap: vE2_ComponentMAPs_NEW)
		{
			if (!this.get_bMapIsMarkedToDelete(oMap))
			{
				vRueck.add(oMap);
			}
		}
		return vRueck;
	}
	
	
	
	
	

	/**
	 * 
	 * @param bOnlyNotMarked4Delete
	 * @return
	 */
	public Vector<E2_ComponentMAP> get_vE2_ComponentMAPs_New(boolean bOnlyNotMarked4Delete)
	{
		Vector<E2_ComponentMAP> vRueck = 					new Vector<E2_ComponentMAP>();
		Vector<E2_ComponentMAP> vE2_ComponentMAPs_NEW = 	this.oNavigationList.get_vComponentMAPS_NEW();
		
		boolean returnAll = !bOnlyNotMarked4Delete;
		
		for (E2_ComponentMAP oMap: vE2_ComponentMAPs_NEW) {
			if (returnAll || !this.get_bMapIsMarkedToDelete(oMap))
			if (!this.get_bMapIsMarkedToDelete(oMap))	{
				vRueck.add(oMap);
			}
		}
		return vRueck;
	}
	
	

	/**
	 * 
	 * @param bOnlyNotMarked4Delete
	 * @return
	 */
	public Vector<E2_ComponentMAP> get_vE2_ComponentMAPs_Edit(boolean bOnlyNotMarked4Delete)
	{
		Vector<E2_ComponentMAP> vRueck = 					new Vector<E2_ComponentMAP>();
		Vector<E2_ComponentMAP> vE2_ComponentMAPs = 		this.oNavigationList.get_vComponentMAPS();

		boolean returnAll = !bOnlyNotMarked4Delete;

		for (E2_ComponentMAP oMap: vE2_ComponentMAPs) {
			if (returnAll || !this.get_bMapIsMarkedToDelete(oMap)) {
				vRueck.add(oMap);
			}
		}
		return vRueck;
	}
	
	

	
	
	public boolean get_bMapIsMarkedToDelete(E2_ComponentMAP oMap)
	{
		boolean bDelete = false;
		Vector<MyE2IF__Component> vAllKomponents = oMap.get_REAL_ComponentVector();
		for (int i=0;i<vAllKomponents.size();i++)
		{
			if (vAllKomponents.get(i) instanceof MyE2_ButtonMarkForDelete)
			{
				bDelete = ((MyE2_ButtonMarkForDelete)vAllKomponents.get(i)).get_bMarkedToDelete();
				return bDelete;
			}
		}
		return bDelete;
	}

	
	
	public boolean set_bMapIsMarkedToDelete(E2_ComponentMAP oMap, boolean bDeleted)
	{
		Vector<MyE2IF__Component> vAllKomponents = oMap.get_REAL_ComponentVector();
		for (int i=0;i<vAllKomponents.size();i++)
		{
			if (vAllKomponents.get(i) instanceof MyE2_ButtonMarkForDelete)
			{
				((MyE2_ButtonMarkForDelete)vAllKomponents.get(i)).set_bMarkToDelete(bDeleted);
			}
		}
		return bDeleted;
	}

	
	
	
	public MyE2EXT__DB_Component EXT_DB()							{		return this.oEXTDB;		}
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)			{		this.oEXTDB = oEXT_DB;	}


	public String 							get_cActual_STATUS_MAP()			{		return cActual_STATUS_MAP;	}
	public E2_ComponentMAP 					get_oComponentMAP_From_Mask()		{		return oComponentMAP_From_Mask;	}
	public E2_NavigationList 				get_oNavigationList()				{		return oNavigationList;	}
	public E2_ComponentMAP 					get_oOwnComponentMAP_ForList()		{		return oOwnComponentMAP_ForList;	}
	public Vector<MyE2IF__Component>	 	get_vComponentForDifferentTasks()	{		return this.vComponentForDifferentTasks;	}


	public void 				set_cActual_STATUS_MAP(String cStatus)			{		this.cActual_STATUS_MAP=cStatus;	}
	

	/**
	 * @return titelRow fuer alles, was noch zum dem Tochterelement dazugehoert
	 */
	public void  add_to_Row_In_TOP(Component oComponent)
	{
		this.oRow_In_TOP.add(oComponent);
		this.oRow_In_TOP.setVisible(true);
	}


	/**
	 * @return Abschluss-Row fuer alles, was noch zum dem Tochterelement dazugehoert
	 */
	public MyE2_Row get_oRow_In_BOTTOM()
	{
		return oRow_In_BOTTOM;
	}

	
	/**
	 * @return s Height of scroll-area of list
	 */
	public Extent get_oContainerExScrollHeight() 
	{
		return oContainerExScrollHeight;
	}


	/**
	 * @param containerExScrollHeight
	 * Height of scroll-area of list
	 */
	public void set_oContainerExScrollHeight(Extent containerExScrollHeight) 
	{
		this.oContainerExScrollHeight = containerExScrollHeight;
		this.oContainerEx.setHeight(this.oContainerExScrollHeight);
	}


	/**
	 * @return s Height of scroll-area of list
	 */
	public Extent get_oContainerExScrollWidth() 
	{
		return oContainerExScrollWidth;
	}


	/**
	 * @param containerExScrollHeight
	 * Height of scroll-area of list
	 */
	public void set_oContainerExScrollWidth(Extent containerExScrollWidth) 
	{
		this.oContainerExScrollWidth = containerExScrollWidth;
		if (containerExScrollWidth!=null) this.oContainerEx.setWidth(this.oContainerExScrollWidth);
	}

	
	
	public void set_to_100_percent()
	{
		if (this.oContainerExScrollWidth!=null)
		{
			this.oContainerEx.setWidth(new Extent(100,Extent.PERCENT));
			this.oContainerEx.setHeight(new Extent(100,Extent.PERCENT));
		}
	}
	
	/*
	 * eigene field-validator-class, die die eingaben in die einzelnen E2_ComponentMAPs durchprueft
	 */
	public static class ownFieldValidator_AfterReadInputMAP extends XX_FieldSetter_AND_Validator
	{
		
		private MyE2_DBC_DaughterTable oFieldDaughterTabler = null;


		public ownFieldValidator_AfterReadInputMAP(MyE2_DBC_DaughterTable odaughter)
		{
			super();
			oFieldDaughterTabler = odaughter;
		}
		
		
		public MyE2_MessageVector isValid( String cSTATUS_MAP, MyE2EXT__Component EXT_own) throws myException
		{
			MyE2_MessageVector 			oMV = new MyE2_MessageVector();
			E2_NavigationList 			oList = this.oFieldDaughterTabler.get_oNavigationList();
			Vector<E2_ComponentMAP> 	vE2_ComponentMAPs = oList.get_vComponentMAPS();
			Vector<E2_ComponentMAP> 	vE2_ComponentMAPs_NEW = oList.get_vComponentMAPS_NEW();
			
			for (int i=0;i<vE2_ComponentMAPs.size();i++)
			{
				if (! oFieldDaughterTabler.get_bMapIsMarkedToDelete((E2_ComponentMAP)vE2_ComponentMAPs.get(i)))
				{
					((E2_ComponentMAP)vE2_ComponentMAPs.get(i)).MakeCompleteCycle_of_Validation_After_Input(null,oMV,cSTATUS_MAP);
				}
			}
			for (int i=0;i<vE2_ComponentMAPs_NEW.size();i++)
			{
				if (! oFieldDaughterTabler.get_bMapIsMarkedToDelete((E2_ComponentMAP)vE2_ComponentMAPs_NEW.get(i)))
				{
					((E2_ComponentMAP)vE2_ComponentMAPs_NEW.get(i)).MakeCompleteCycle_of_Validation_After_Input(null,oMV,cSTATUS_MAP);
				}
			}
			
			return oMV;
		}
		
	}




	
	

	public boolean get_bDaughterIsPassive() 
	{
		return bDaughterIsPassive;
	}


	public void set_bDaughterIsPassive(boolean daughterIsPassive) 
	{
		bDaughterIsPassive = daughterIsPassive;
	}






	/*
	 * ab hier eine reihe von methoden fuer die nutzung in unbound masken
	 */
	/**
	 * Weitere methode, um diese daughter-table auch in nicht gebundenen Masken verwenden zu koennen
	 * 
	 * @param cID_VALUE_FROM_MOTHER_TABLE_Formated 
	 * @return
	 * @throws myException
	 */
	public Vector<String> UB_get_vSQLStack() throws myException
	{
	   
		
		if (S.isEmpty(this.UB_MOTHER_ID))
		{
			throw new Exception_MotherID_Not_Set();
		}
		
		
        Vector<String> vRueck = new Vector<String>();
        

	    if (this.bDaughterIsPassive)
	    	return vRueck;                   // ende

        
        /*
         * fuer tochterzeilen, dei beim update neu eingetragen wurden, muss der aktuelle wert des
         * Connect-feldes gefunden werden
         */
//        String cNAME_OF_OutsideConnector = this.oOwnComponentMAP_ForList.get_oSQLFieldMAP().get_oSQLFieldJoinOutside().get_oFieldFromConnectedTable().get_cFieldLabel();
//        String cACTUAL_FORMATED_VALUE_JoinOutside = oE2_ComponentMAPFromMother.get_oInternalSQLResultMAP().get_FormatedValue(cNAME_OF_OutsideConnector);
//        
        
		Vector<E2_ComponentMAP> 	vE2_ComponentMAPs = 		this.oNavigationList.get_vComponentMAPS();
		Vector<E2_ComponentMAP>		vE2_ComponentMAPs_NEW = 	this.oNavigationList.get_vComponentMAPS_NEW();
		
		for (int i=0;i<vE2_ComponentMAPs.size();i++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)vE2_ComponentMAPs.get(i);
			if (!this.get_bMapIsMarkedToDelete(oMap))
			{
				SQLMaskInputMAP oInputMap = oMap.get_ActualInputMAP_And_MarkFalseInput();		// inputmap
				vRueck.addAll(oMap.get_oSQLFieldMAP().get_SQL_UPDATESTACK(oMap.get_oInternalSQLResultMAP(),oInputMap));
				vRueck.addAll(oMap.get_UpdateStackFromComplexFields(oMap,oInputMap));
			}
			else
			{
				vRueck.addAll(oMap.get_oSQLFieldMAP().get_SQL_DELETESTACK_From_FORMATED_KEY(oMap.get_oInternalSQLResultMAP().get_cFormatedROW_ID()));
			}
		}
		for (int i=0;i<vE2_ComponentMAPs_NEW.size();i++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)vE2_ComponentMAPs_NEW.get(i);
			if (!this.get_bMapIsMarkedToDelete(oMap))
			{
				SQLMaskInputMAP oInputMap = oMap.get_ActualInputMAP_And_MarkFalseInput();		// inputmap
				vRueck.addAll(oMap.get_oSQLFieldMAP().get_SQL_INSERTSTACK(oInputMap,this.UB_MOTHER_ID));
				vRueck.addAll(oMap.get_InsertStackFromComplexFields(oMap,oInputMap));
			}
		}
        
        return vRueck;
	}


	
	public MyE2_MessageVector  UB_CHECK_INPUT_IN_LIST() throws myException
	{
		if (S.isEmpty(this.UB_MOTHER_ID))
		{
			throw new Exception_MotherID_Not_Set();
		}

		
		MyE2_MessageVector  oMV = this.EXT().make_Setting_AND_Validation__AfterReadInputMAP(E2_ComponentMAP.STATUS_EDIT);
		
		return oMV;
	}
	
	
	public void UB_PopulateDaughterWithMother_4_EDIT() throws myException
	{
		if (S.isEmpty(this.UB_MOTHER_ID))
		{
			throw new Exception_MotherID_Not_Set();
		}

		
		this.set_cActual_Formated_DBContent_To_Mask(this.UB_MOTHER_ID, E2_ComponentMAP.STATUS_EDIT, null);
		this.set_bEnabled_For_Edit(true);
	}
	
	public void UB_PopulateDaughterWithMother_4_VIEW() throws myException
	{
		if (S.isEmpty(this.UB_MOTHER_ID))
		{
			throw new Exception_MotherID_Not_Set();
		}

		
		this.set_cActual_Formated_DBContent_To_Mask(this.UB_MOTHER_ID, E2_ComponentMAP.STATUS_VIEW, null);
		this.set_bEnabled_For_Edit(false);
	}
	
	
	public void UB_PREPARE_4_NEW(boolean bEnable4Edit) throws myException
	{
		this.prepare_ContentForNew(true);
		this.set_bEnabled_For_Edit(bEnable4Edit);
	}
	
	public String get_UB_MOTHER_ID() 
	{
		return UB_MOTHER_ID;
	}


	public void set_UB_MOTHER_ID(String uB_MOTHER_ID) 
	{
		UB_MOTHER_ID = uB_MOTHER_ID;
	}


	public class Exception_MotherID_Not_Set extends myException
	{

		public Exception_MotherID_Not_Set() 
		{
			super("MyE2_DBC_Daughter_Table: UB_MOTHER_ID is not SET !!");
		}
		
	}







	/**
	 * 2015-02-19: neue setter und getter
	 * @param o_ComponentMAP_From_Mask
	 */
	public void set_oComponentMAP_From_Mask(E2_ComponentMAP o_ComponentMAP_From_Mask) {
		this.oComponentMAP_From_Mask = o_ComponentMAP_From_Mask;
	}
	
	public void set_oOwnComponentMAP_ForList(E2_ComponentMAP o_OwnComponentMAP_ForList) {
		this.oOwnComponentMAP_ForList = o_OwnComponentMAP_ForList;
	}
	
	public void set_oNavigationList(E2_NavigationList navigationList) {
		this.oNavigationList = navigationList;
	}

	public MyE2_ContainerEx get_oContainerEx() {
		return this.oContainerEx;
	}

	public MyE2_Row get_oRow_In_TOP() {
		return oRow_In_TOP;
	}

	
	
	
	
	
}
