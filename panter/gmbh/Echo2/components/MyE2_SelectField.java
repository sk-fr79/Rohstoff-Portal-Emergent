package panter.gmbh.Echo2.components;


import java.util.EventListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.SelectField;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.event.EventListenerList;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.list.DefaultListCellRenderer;
import nextapp.echo2.app.list.DefaultListModel;
import nextapp.echo2.app.list.ListCellRenderer;
import nextapp.echo2.app.list.StyledListCell;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.XX_MessageManipulator;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.E2_InnerActionListenerForActionAgents;
import panter.gmbh.Echo2.ActionEventTools.E2_InnerActionListenerForInternalActionAgents;
import panter.gmbh.Echo2.ActionEventTools.E2_PassiveActionExecuter;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Factorys.StyleFactory_SelectField;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataToView.zuOrdnung;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


public class MyE2_SelectField extends SelectField implements MyE2IF__Component, E2_IF_Copy, E2_IF_Handles_ActionAgents, MyE2IF__CanGetStampInfo
{
	private MyE2EXT__Component oEXT = new MyE2EXT__Component(this);
    /*
     * das verhalten, wenn zu einem Wert keine anzeige gefunden wird
     */
    public static final String SHOW_ERROR_STRING_IF_VALUE_NOT_FOUND 	= "SHOW_ERROR_STRING_IF_VALUE_NOT_FOUND";
    public static final String SHOW_EMPTY_STRING_IF_VALUE_NOT_FOUND 	= "SHOW_EMPTY_STRING_IF_VALUE_NOT_FOUND";
    public static final String SHOW_EXCEPTION_IF_VALUE_NOT_FOUND 		= "SHOW_EXCEPTION_IF_VALUE_NOT_FOUND";


    private DefaultListModel oSelModel = null;

    private String  cDefineAction_IF_ValueNotFound = MyE2_SelectField.SHOW_ERROR_STRING_IF_VALUE_NOT_FOUND;
    
	/*
	 * ein vector fuer die speicherung der MyE2_ActionAgent - object.
	 * dieser werden dann nach der reihenfolge ihrer zufuegung ausgefuehrt 
	 */
	private Vector<XX_ActionAgent> 	 vActionAgents = new Vector<XX_ActionAgent>();
	private Vector<ActionListener>   vExternalActionListeners = new Vector<ActionListener>();

	/*
	 * vector fuer validatoren (je einer fuer globale und fuer ID bezogene) 
	 */
	private Vector<XX_ActionValidator>	 vGlobalValidators = 	new Vector<XX_ActionValidator>();
	private Vector<XX_ActionValidator>   vIDValidators = 		new Vector<XX_ActionValidator>();
    
	
	
	private E2_InnerActionListenerForActionAgents		oInnerActionListener = new E2_InnerActionListenerForActionAgents();

    
    
    /*
     * falls bei der suche nach einem wert ein eintrag nicht gefunden wird, dann
     * wird ein error-eintrag in die anzeige aufgenommen und aktiviert
     * der error-eintrag ist dann true;
     */
    private boolean 		bHasErrorEintrag = false;
    private String 			cDefaultValue = null;

	private dataToView 		oDataToView = null;
    
	/*
	 * fuer "toten" content (z.B. ein inaktiver mitarbeiter)
	 * wird einen schatten-liste (dataToView) mitgefuehrt.
	 * Immer wenn z.B. zum Editieren oder zum anzeigen ein eintrag
	 * aus dieser schattenliste angezeigt wird, dann wandert dieser in die echte
	 * liste. Die liste wird bei jeder neuen anzeige-aktion wieder 
	 * gesaeubert, sodass max. 1 falscher eintrag vorhanden ist
	 */
	private dataToView    	odataToViewShadow = null;
	
	
	private boolean 		Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = true;
	
	// SQL Command für das Füllen der combobx, falls als Fülloption die Variante mit dem SQL-String genutzt wurde.
	private String 			m_cSQL_Query_For_LIST = null;



	private boolean 		m_bThirdColumnIS_STANDARD_MARKER= false;
	private boolean 		m_bEmtyValueInFront = false;
	private boolean 		m_bValuesFormated = false;
	private boolean 		m_bTranslate = false;	
	
	// 2012-07-16: moeglichkeit des aufbaus eines selektors aus query mit definition grau gekennzeichneter selektoren
	private String         cSQLQuery4InactivShownMembers = null;

	
	//2013-01-04: moeglichkeit, automatische die (evtl. langen) Eintraege als tooltip anzuzeigen
	private boolean   		bSetToolTipsToActiveListValue = false;


	private String         cToolTipWhenEmptyDropDownValue = null;

	


	public boolean get_bMustSet_MILLISECONDSTAMP_TO_Session() 
	{
		return this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER;
	}


	public void set_bMustSet_MILLISECONDSTAMP_TO_Session(boolean bMustSet_MILLISECONDSTAMP) 
	{
		this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = bMustSet_MILLISECONDSTAMP;
	}



	/**
	 * 2013-12-18: neue methode um meldungen zu manipulieren
	 */
	private XX_MessageManipulator      oMessageManipulator = null;
	public XX_MessageManipulator   get_MessageManipulator() {
		return this.oMessageManipulator;
	}

	public void set_MessageManipulator(XX_MessageManipulator oManipulator) {
		this.oMessageManipulator = oManipulator;
	}

	
	
	//20170906: unterbrechungen mit benutzerinteraktion einfuegen
	private  E2_Break4PopupController break4PopupController = null;
	
	
	@Override
	public E2_Break4PopupController  getBreak4PopupController() {
		return this.break4PopupController;
	}


	/**
	 * 2018-01-16: martin: break4popup-controller setter dazugefuegt
	 * @param controller
	 * @return
	 */
	@Override
	public void setBreak4PopupController(E2_Break4PopupController controller) {
		this.break4PopupController = controller;
	}
	


	
	
    public MyE2_SelectField()
    {
        super();
		this.EXT().set_STYLE_FACTORY(new StyleFactory_SelectField());
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(true,true,false));

    }
    

    public MyE2_SelectField( Extent oWidth)
    {
        super();
		this.EXT().set_STYLE_FACTORY(new StyleFactory_SelectField());
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
		if (oWidth!=null)
		{
			this.setWidth(oWidth);
		}
 
    }
    

    public MyE2_SelectField(String[] aDefArray, String cdefaultValue,  boolean btranslate)  throws myException
    {
        super();
         
        if ((aDefArray == null) || (aDefArray.length == 0))
            throw new myException("mySelectField: Constructor error: null-array not allowed");

//        this.cDefaultValue = cdefaultValue;
//        
//        this.set_ListenInhalt(aDefArray,btranslate);
//        if (cdefaultValue != null)
//        	this.set_ActiveValue_OR_FirstValue(cdefaultValue);
//        
//		this.EXT().set_STYLE_FACTORY(new StyleFactory_SelectField());
//		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
        
        this.populate(aDefArray,  cdefaultValue,  btranslate,null);
        
    }

    /**
     * 2017-01-10: neuer konstruktor
     * @param aDefArray
     * @param cdefaultValue
     * @param btranslate
     * @throws myException
     */
    public MyE2_SelectField(Vector<String> aDefArray, String cdefaultValue,  boolean btranslate)  throws myException
    {
        super();
         
        if ((aDefArray == null) || (aDefArray.size() == 0))
            throw new myException("mySelectField: Constructor error: null-array not allowed");

        String[] arr = new String[aDefArray.size()];
        int i=0;
        for (String c: aDefArray) {
        	arr[i++]=c;
        }
        this.populate(arr,  cdefaultValue,  btranslate,null);
        
    }

    
    
 
    public MyE2_SelectField(String[][] aDefArray, String cdefaultValue,  boolean btranslate)  throws myException
    {
        super();
        
        if ((aDefArray == null) || (aDefArray.length == 0))
            throw new myException("mySelectField: Constructor error: null-array not allowed");

//        this.cDefaultValue = cdefaultValue;
//        
//        this.set_ListenInhalt(aDefArray,btranslate);
//        if (cdefaultValue != null)
//        	this.set_ActiveValue_OR_FirstValue(cdefaultValue);
//        
//		this.EXT().set_STYLE_FACTORY(new StyleFactory_SelectField());
//		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
        
        this.populate(aDefArray,  cdefaultValue,  btranslate,null);
    }

    /**
     * Allows the creating with a direct HashMap
     * @author nils
     * @param aDefArray
     * @param cdefaultValue
     * @param btranslate
     * @throws myException
     */
	public MyE2_SelectField(HashMap<String, String> aDefArray, String cdefaultValue,  boolean btranslate)  throws myException
	{
		// TODO Auto-generated constructor stub
        super();
        
        if ((aDefArray == null) || (aDefArray.size() == 0))
            throw new myException("mySelectField: Constructor error: null-array not allowed");

        this.populate(aDefArray,  cdefaultValue,  btranslate, null);
	}
    
    public MyE2_SelectField(String[] aDefArray, String cdefaultValue,  boolean btranslate, Extent oWidth)  throws myException
    {
        super();
         
        if ((aDefArray == null) || (aDefArray.length == 0))
            throw new myException("mySelectField: Constructor error: null-array not allowed");

        this.populate(aDefArray,  cdefaultValue,  btranslate,oWidth);
    }

 
    public MyE2_SelectField(String[][] aDefArray, String cdefaultValue,  boolean btranslate, Extent oWidth)  throws myException
    {
        super();
        
        if ((aDefArray == null) || (aDefArray.length == 0))
            throw new myException("mySelectField: Constructor error: null-array not allowed");

        this.populate(aDefArray,  cdefaultValue,  btranslate,oWidth);
    }
 
    
    

    private void populate(Object aDefArray, String cdefaultValue,  boolean btranslate, Extent oWidth)  throws myException
    {
  
        this.cDefaultValue = cdefaultValue;
        
        this.set_ListenInhalt(aDefArray,btranslate);
        if (cdefaultValue != null)
        	this.set_ActiveValue_OR_FirstValue(cdefaultValue);
        
		this.EXT().set_STYLE_FACTORY(new StyleFactory_SelectField());
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
		
		if (oWidth!=null)
		{
			this.setWidth(oWidth);
		}
    	
		
		this.setze_ToolTips();
    }
    
    /**
     * 2015-10-19: neue konstruktoren(1)
     * @param values
     * @param bEmptyInFront
     * @param bTranslate
     * @throws myException
     */
    public MyE2_SelectField (LinkedHashMap<String, String> hm_values, 
    						  boolean  						bEmptyInFront,
    						  boolean   					bTranslate) throws myException {
		String aDefArray[][] = new String[hm_values.size()+(bEmptyInFront?1:0)][2];
		int i=0;
		if (bEmptyInFront) {
			aDefArray[0][0] = "*"; aDefArray[0][1]="";
			i++;
		}
		for (Map.Entry<String, String> entry: hm_values.entrySet()) {
			aDefArray[i][0]=entry.getValue();
			aDefArray[i][1]=entry.getKey();
			i++;
		}
       this.populate(aDefArray,null,bTranslate, null);
		
    }
    
    /**
     * 2015-10-19: neue konstruktoren(2)
     * @param values
     * @param bEmptyInFront
     * @param bTranslate
     * @throws myException
     */
    public MyE2_SelectField (LinkedHashMap<String, MyE2_String> hm_values, 
    						  boolean  						bEmptyInFront) throws myException {
		String aDefArray[][] = new String[hm_values.size()+(bEmptyInFront?1:0)][2];
		int i=0;
		if (bEmptyInFront) {
			aDefArray[0][0] = "*"; aDefArray[0][1]="";
			i++;
		}
		for (Map.Entry<String, MyE2_String> entry: hm_values.entrySet()) {
			aDefArray[i][0]=entry.getValue().CTrans();
			aDefArray[i][1]=entry.getKey();
			i++;
		}
       this.populate(aDefArray,null,false, null);   //hier liegt die translation-info bereits in den MyE2_Strings
		
    }
    
    
	/**
	 * @param cSQL_Query_For_LIST : Abfrage mit zwei spalten: Textanzeige und DatenbankWert zur anzeige in der liste !!! 
	 * 								WICHTIG ! Wert-Spalte muss formatiert sein
	 * @param bThirdColumnIS_STANDARD_MARKER  (kann als dritte spalte uebergeben werden, wenn die dropdown-tabelle einen 
	 * 								IST_STANDARD - marker beinhaltet
	 * @param bEmtyValueInFront
	 * @param bValuesFormated
	 * @param btranslate
	 * @throws myException
	 */
	public MyE2_SelectField(	String 			cSQL_Query_For_LIST , 
								boolean 		bThirdColumnIS_STANDARD_MARKER,
								boolean 		bEmtyValueInFront,
								boolean 		bValuesFormated,
								boolean 		btranslate) throws myException
	{
		super();

		// übernehmen der Parameter
		this.m_cSQL_Query_For_LIST = cSQL_Query_For_LIST;
		this.cSQLQuery4InactivShownMembers = null;
		this.m_bThirdColumnIS_STANDARD_MARKER = bThirdColumnIS_STANDARD_MARKER;
		this.m_bEmtyValueInFront = bEmtyValueInFront;
		this.m_bValuesFormated = bValuesFormated;
		this.m_bTranslate = btranslate;
		
		
		//Ausführen der aktualisierung!!
		
		populateCombobox(cSQL_Query_For_LIST,this.cSQLQuery4InactivShownMembers, bThirdColumnIS_STANDARD_MARKER,
				bEmtyValueInFront, bValuesFormated, btranslate);

		
		this.setze_ToolTips();
	}


	
	/**
	 * @param cSQL_Query_For_LIST : Abfrage mit zwei spalten: Textanzeige und DatenbankWert zur anzeige in der liste !!! 
	 * 								WICHTIG ! Wert-Spalte muss formatiert sein
	 * @param bThirdColumnIS_STANDARD_MARKER  (kann als dritte spalte uebergeben werden, wenn die dropdown-tabelle einen 
	 * 								IST_STANDARD - marker beinhaltet
	 * @param bEmtyValueInFront
	 * @param bValuesFormated
	 * @param btranslate
	 * @throws myException
	 */
	public MyE2_SelectField(	String 			cSQL_Query_For_LIST , 
								boolean 		bThirdColumnIS_STANDARD_MARKER,
								boolean 		bEmtyValueInFront,
								boolean 		bValuesFormated,
								boolean 		btranslate,
								Extent          Width) throws myException
	{
		super();

		// übernehmen der Parameter
		this.m_cSQL_Query_For_LIST = cSQL_Query_For_LIST;
		
		this.cSQLQuery4InactivShownMembers = null;

		
		this.m_bThirdColumnIS_STANDARD_MARKER = bThirdColumnIS_STANDARD_MARKER;
		this.m_bEmtyValueInFront = bEmtyValueInFront;
		this.m_bValuesFormated = bValuesFormated;
		this.m_bTranslate = btranslate;
		
		
		//Ausführen der aktualisierung!!
		
		populateCombobox(cSQL_Query_For_LIST, this.cSQLQuery4InactivShownMembers, bThirdColumnIS_STANDARD_MARKER,
				bEmtyValueInFront, bValuesFormated, btranslate);

		this.setWidth(Width);
		
		this.setze_ToolTips();
	}

	
	
	/**
	 * 2012-07-16: moeglichkeit des aufbaus eines selektors aus query mit definition grau gekennzeichneter selektoren
	 * @param cSQL_Query_For_LIST : Abfrage mit zwei spalten: Textanzeige und DatenbankWert zur anzeige in der liste !!! 
	 * 								WICHTIG ! Wert-Spalte muss formatiert sein
	 * @param bThirdColumnIS_STANDARD_MARKER  (kann als dritte spalte uebergeben werden, wenn die dropdown-tabelle einen 
	 * 								IST_STANDARD - marker beinhaltet
	 * @param bEmtyValueInFront
	 * @param bValuesFormated
	 * @param btranslate
	 * @throws myException
	 */
	public MyE2_SelectField(	String 			cSQL_Query_For_LIST , 
								String          cSQL_Query_4_Inaktiv,
								boolean 		bThirdColumnIS_STANDARD_MARKER,
								boolean 		bEmtyValueInFront,
								boolean 		bValuesFormated,
								boolean 		btranslate,
								Extent          Width) throws myException
	{
		super();

		// übernehmen der Parameter
		this.m_cSQL_Query_For_LIST = cSQL_Query_For_LIST;
		this.m_bThirdColumnIS_STANDARD_MARKER = bThirdColumnIS_STANDARD_MARKER;
		this.m_bEmtyValueInFront = bEmtyValueInFront;
		this.m_bValuesFormated = bValuesFormated;
		this.m_bTranslate = btranslate;
		
		this.cSQLQuery4InactivShownMembers = cSQL_Query_4_Inaktiv;
		
		//Ausführen der aktualisierung!!
		
		populateCombobox(cSQL_Query_For_LIST, this.cSQLQuery4InactivShownMembers, bThirdColumnIS_STANDARD_MARKER,
				bEmtyValueInFront, bValuesFormated, btranslate);

		this.setWidth(Width);
		
		this.setze_ToolTips();

		
	}


	


	/**
	 * 
	 * Author: manfred
	 * 20.05.2009
	 *
	 * @throws myException
	 */
	public void RefreshComboboxFromSQL() throws myException{
		
		if (m_cSQL_Query_For_LIST != null){
		
			// alten Listeneintrag merken
			String sValue = null;
			try {
				sValue = this.get_ActualWert();
			} catch (Exception e) {
				sValue = null;
			}
						
			this.populateCombobox(	this.m_cSQL_Query_For_LIST, 
									this.cSQLQuery4InactivShownMembers,
									this.m_bThirdColumnIS_STANDARD_MARKER, 
									this.m_bEmtyValueInFront, 
									this.m_bValuesFormated, 
									this.m_bTranslate);
			
			if (sValue != null){
				this.set_ActiveValue(sValue);
			}
		} else {
			throw new myException(new MyE2_String("Die Combobox wurde falsch initialisiert.").toString()) ;
		}
		
		
		this.setze_ToolTips();

	}
	
	
	
	public void set_Fuelle_Neu(String cQuery) throws myException
	{
		this.populateCombobox(cQuery,null, this.m_bThirdColumnIS_STANDARD_MARKER, this.m_bEmtyValueInFront,this.m_bValuesFormated, this.m_bTranslate);
		
		this.setze_ToolTips();

	}
	
	
	/*
	 * 2012-07-16: inaktive members koennen hier via query uebergeben werden
	 */
	protected void populateCombobox(	String cSQL_Query_For_LIST,
										String cSQL_Query4InactiveMembers,
										boolean bThirdColumnIS_STANDARD_MARKER, boolean bEmtyValueInFront,
										boolean bValuesFormated, boolean btranslate) throws myException 
	{
		
		
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cResult = null;
		if (bValuesFormated)
			cResult = oDB.EinzelAbfrageInArrayFormatiert(cSQL_Query_For_LIST,"");
		else
			cResult = oDB.EinzelAbfrageInArray(cSQL_Query_For_LIST,"");
		
		bibALL.destroy_myDBToolBox(oDB);
		
		if (cResult == null)
			throw new myException("MyE2_SelectField:Constructor:Query has no result!:cSQL_Query_For_LIST:"+cSQL_Query_For_LIST);
		
		

		if (cResult.length>0)
			if (bThirdColumnIS_STANDARD_MARKER && cResult[0].length<2)
				throw new myException("MyE2_SelectField:Constructor:with bThirdColumnIS_STANDARD_MARKER = true there must be 3 query-cols !");
		
		if (bEmtyValueInFront)
		{
			/*
			 * der erste wert muss leer sein / ZWINGEND
			 */
			String[][] cWert = new String[cResult.length+1][2];
			cWert[0][0] = "-";		cWert[0][1] = "";
			for (int i=0;i<cResult.length;i++)
			{
				cWert[i+1][0] = cResult[i][0];		cWert[i+1][1] = cResult[i][1];
			}
			this.set_ListenInhalt(cWert,btranslate);
			/*
			 * standard einstellen, wenn noetig
			 */
			if (bThirdColumnIS_STANDARD_MARKER)
				for (int i=0;i<cResult.length;i++)
				{
					if (cResult[i][2].equals("Y"))
					{
						this.setSelectedIndex(i+1);
						break;
					}
				}
			
		}
		else
		{
			this.set_ListenInhalt(cResult,btranslate);
			/*
			 * standard einstellen, wenn noetig
			 */
			if (bThirdColumnIS_STANDARD_MARKER)
				for (int i=0;i<cResult.length;i++)
				{
					if (cResult[i][2].equals("Y"))
					{
						this.setSelectedIndex(i);
						break;
					}
					
				}

		}
		
		
		
		//2012-067-16: neuer code fuer das anzeigen inaktiver elemente
		if (S.isFull(cSQL_Query4InactiveMembers))
		{
			String[][] cInaktive = bibDB.EinzelAbfrageInArray(cSQL_Query4InactiveMembers,"");
			
			if (cInaktive!=null)
			{
				Vector<String> vInaktiveEintraege = new Vector<String>();
				for (int i=0;i<cInaktive.length;i++)
				{
					if (cInaktive[i].length>0)
					{
						vInaktiveEintraege.add(cInaktive[i][0]);
					}
				}
				
				this.setCellRenderer(new MyE2_SelectField.ownListCellRenderer(vInaktiveEintraege));
				
			}
			else
			{
				this.setCellRenderer(new DefaultListCellRenderer());
			}
		}
		// ende neuer code fuer das anzeigen inaktiver elemente 
		
		
 		this.EXT().set_STYLE_FACTORY(new StyleFactory_SelectField());
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
	}

    
    
    
	/*
	 * 2015-10-21: neue populate-methode
	 */
	protected void populateCombobox(	String[][] list_val,
										String[][] list_val_inactive,
										String  defaultValue,
										boolean bEmtyValueInFront,
										boolean btranslate) throws myException  {
		
		String[][] cResult = list_val;
		
		if (cResult == null) {
			throw new myException("MyE2_SelectField:populateCombobox:Empty list not allowed");
		}

		
		if (bEmtyValueInFront) {
			/*
			 * der erste wert muss leer sein / ZWINGEND
			 */
			String[][] cWert = new String[cResult.length+1][2];
			cWert[0][0] = "-";		cWert[0][1] = "";
			for (int i=0;i<cResult.length;i++)
			{
				cWert[i+1][0] = cResult[i][0];		cWert[i+1][1] = cResult[i][1];
			}
			this.set_ListenInhalt(cWert,btranslate);
		} else {
			this.set_ListenInhalt(cResult,btranslate);
		}
		
		if (list_val_inactive!=null && list_val_inactive.length>0) 	{
			String[][] cInaktive = list_val_inactive;
			Vector<String> vInaktiveEintraege = new Vector<String>();
			for (int i=0;i<cInaktive.length;i++) {
				if (cInaktive[i].length>0)	{
					vInaktiveEintraege.add(cInaktive[i][0]);
				}
			}
			this.setCellRenderer(new MyE2_SelectField.ownListCellRenderer(vInaktiveEintraege));
		}
		
		if (S.isFull(defaultValue)) {
			this.set_ActiveInhalt_or_FirstInhalt(defaultValue);
		}
		
 		this.EXT().set_STYLE_FACTORY(new StyleFactory_SelectField());
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
	}

    
	
    /*
     * Füllen des select-fields
     */
    public void set_ListenInhalt(Object aDefArray, boolean btranslate) throws myException
    {
    	
        if (!((aDefArray instanceof String[]) || (aDefArray instanceof String[][]) || (aDefArray instanceof HashMap)))
            throw new myException("mySelectField: set_ListenInhalt: only String[] or String[][] or HashMap allowed");
 	
        if (aDefArray instanceof String[]) {
        	this.oDataToView = new dataToView((String[])aDefArray,btranslate,bibE2.get_CurrSession());
        } else if (aDefArray instanceof HashMap) {
        	this.oDataToView = new dataToView((HashMap<String, String>)aDefArray,btranslate,bibE2.get_CurrSession());
        } else {
        	this.oDataToView = new dataToView((String[][])aDefArray,btranslate,bibE2.get_CurrSession());
        }
    	
        this.oSelModel = new DefaultListModel(this.oDataToView.get_ViewArray());
        this.setModel(this.oSelModel);
        
        /*
         * dafuer sorgen, dass bei ungeklickten selektoren der activevalue auf dem ersten steht
         */
        this.set_ActiveInhalt_or_FirstInhalt(null);
        
		this.setze_ToolTips();

    }


	
    /*
     * Füllen des select-fields
     */
    public void set_ListenInhalt(	String cSQL_Query_For_LIST,
									boolean bThirdColumnIS_STANDARD_MARKER, 
									boolean bEmtyValueInFront,
									boolean bValuesFormated, 
									boolean btranslate) throws myException
						    {
    	this.populateCombobox(cSQL_Query_For_LIST,null, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront, bValuesFormated, btranslate);
    	this.m_cSQL_Query_For_LIST=cSQL_Query_For_LIST;
    	this.cSQLQuery4InactivShownMembers = null;
    	
		this.setze_ToolTips();

    }

    
  
    /*
     * 2012-07-17: neue variante fuer inaktive angezeigte members: Füllen des select-fields 
     */
    public void set_ListenInhalt(	String cSQL_Query_For_LIST,
    								String cSQLQuery_4_InactiveMembers,
									boolean bThirdColumnIS_STANDARD_MARKER, 
									boolean bEmtyValueInFront,
									boolean bValuesFormated, 
									boolean btranslate) throws myException
    {
    	this.populateCombobox(cSQL_Query_For_LIST,cSQLQuery_4_InactiveMembers, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront, bValuesFormated, btranslate);
    	this.m_cSQL_Query_For_LIST=cSQL_Query_For_LIST;
    	this.cSQLQuery4InactivShownMembers = cSQLQuery_4_InactiveMembers;
    	
		this.setze_ToolTips();

    }

    
    

    
    
    public void set_oDataToView(dataToView odataToView)
    {
    	this.oDataToView = odataToView;
        this.oSelModel = new DefaultListModel(this.oDataToView.get_ViewArray());
        this.setModel(this.oSelModel);
        /*
         * dafuer sorgen, dass bei ungeklickten selektoren der activevalue auf dem ersten steht
         */
        this.setSelectedIndex(0);
        this.set_bHasErrorEintrag(false);  //kann keinen fehlereintrag mehr haben, das das listmodel neuaufgebaut wurde
        
        
		this.setze_ToolTips();

    }
    
    
    public dataToView get_oDataToView()
    {
    	return this.oDataToView;
    }
    
    
    /*
     * markiert einen aktuellen wert im werte-array
     */
    public int set_ActiveValue(String cWert) throws myException
    {
        this.removeErrorEintrag();
        this.cleanFromShadow();

        if (cWert == null)
            throw new myException("mySelectField:set_ActiveValue:null-value not allowed !");
        
        int iRueck=this.oDataToView.get_PositionOfData(cWert);
        
        
        if (iRueck == -1)
        {
        	//dann erst in der schattenliste nachsehen
        	if (this.odataToViewShadow != null)
        	{
        		// wenn der wert in der schattenliste vorhanden ist, dann diese zuordnung in die echte liste rueberholen und
        		// das SelectField neu bauen
        		int iPosShadow = this.odataToViewShadow.get_PositionOfData(cWert);
        		if (iPosShadow >=0 )
        		{
           			this.get_oDataToView().add((zuOrdnung)this.get_odataToViewShadow().get_zoAtPosition(iPosShadow).get_Copy(null));
        	        this.oSelModel = new DefaultListModel(this.oDataToView.get_ViewArray());
        	        this.setModel(this.oSelModel);
        	        this.setSelectedIndex(this.oDataToView.size()-1);
        	        return (this.oDataToView.size()-1);
        		}
        	}
        	
        	
            if (this.cDefineAction_IF_ValueNotFound.equals(MyE2_SelectField.SHOW_ERROR_STRING_IF_VALUE_NOT_FOUND))
            {
                this.oSelModel.add("@@@ERROR@@@"); // am ende einfügen und aktivieren           
                this.setSelectedIndex(this.oDataToView.size());
                this.bHasErrorEintrag = true;
            }
            if (this.cDefineAction_IF_ValueNotFound.equals(MyE2_SelectField.SHOW_EMPTY_STRING_IF_VALUE_NOT_FOUND))
            {
                this.oSelModel.add(""); // am ende einfügen und aktivieren           
                this.setSelectedIndex(this.oDataToView.size());
                this.bHasErrorEintrag = true;
            }
            if (this.cDefineAction_IF_ValueNotFound.equals(MyE2_SelectField.SHOW_EXCEPTION_IF_VALUE_NOT_FOUND))
            {
                throw new myException("mySelectField:set_ActiveValue:value not found !");            }

        }
        else
        {
            this.setSelectedIndex(iRueck);
        }
        
        
		this.setze_ToolTips();


        return iRueck;
    }

    
    
    
    /*
     * markiert einen aktuellen wert im werte-array, hängt aber keinen error-eintrag ran, sondern markiert den ersten eintrag
     */
    public int set_ActiveValue_OR_FirstValue(String cWert)
    {
        this.removeErrorEintrag();

        
        /*
         * 2013-10-01: aenderung des verhaltens: 
         * diese methode wird in den DB-Ablegern der klasse verwendet, um leere felder darzustellen fuer die maske. Dies hat zur folge, dass der fehlende Aufruf:
         *         this.cleanFromShadow();
         * vorher evtl. in einer maske auftauchende Shadow-werte stehen laesst und waehlbar macht.
         * Deshalb hier eingefuegt        
         */
        this.cleanFromShadow();
        
        
        
        
        if (cWert == null)
        {
        	if (this.oSelModel.size()>0)
        		this.setSelectedIndex(0);
        	
        	return 0;
        }
         
        int iRueck=this.oDataToView.get_PositionOfData(cWert);
        
        if (iRueck == -1)
            this.setSelectedIndex(0);
        else
            this.setSelectedIndex(iRueck);

        
		this.setze_ToolTips();

        return iRueck;
    }
    
    
    
    /*
     * markiert einen aktuellen wert im anzeige-array
     */
    public int set_ActiveInhalt(String cWert) throws myException
    {
        this.removeErrorEintrag();
        this.cleanFromShadow();

        if (cWert == null)
            throw new myException("mySelectField:set_ActiveValue:null-value not allowed !");
        
        int iRueck=this.oDataToView.get_PositionOfView(cWert);
        
        
        if (iRueck == -1)
        {
        	//dann erst in der schattenliste nachsehen
        	if (this.odataToViewShadow != null)
        	{
        		// wenn der wert in der schattenliste vorhanden ist, dann diese zuordnung in die echte liste rueberholen und
        		// das SelectField neu bauen
        		int iPosShadow = this.odataToViewShadow.get_PositionOfView(cWert);
        		if (iPosShadow >=0 )
        		{
           			this.get_oDataToView().add((zuOrdnung)this.get_odataToViewShadow().get_zoAtPosition(iPosShadow).get_Copy(null));
        	        this.oSelModel = new DefaultListModel(this.oDataToView.get_ViewArray());
        	        this.setModel(this.oSelModel);
        	        this.setSelectedIndex(this.oDataToView.size()-1);
        	        return (this.oDataToView.size()-1);
        		}
        	}

        	
        	
        	
            if (this.cDefineAction_IF_ValueNotFound.equals(MyE2_SelectField.SHOW_ERROR_STRING_IF_VALUE_NOT_FOUND))
            {
                this.oSelModel.add("@@@ERROR@@@"); // am ende einfügen und aktivieren           
                this.setSelectedIndex(this.oDataToView.size());
                this.bHasErrorEintrag = true;
            }
            if (this.cDefineAction_IF_ValueNotFound.equals(MyE2_SelectField.SHOW_EMPTY_STRING_IF_VALUE_NOT_FOUND))
            {
                this.oSelModel.add(""); // am ende einfügen und aktivieren           
                this.setSelectedIndex(this.oDataToView.size());
                this.bHasErrorEintrag = true;
            }
            if (this.cDefineAction_IF_ValueNotFound.equals(MyE2_SelectField.SHOW_EXCEPTION_IF_VALUE_NOT_FOUND))
            {
                throw new myException("mySelectField:set_ActiveValue:value not found !");            }

        }
        else
        {
            this.setSelectedIndex(iRueck);
        }

		this.setze_ToolTips();

        
        return iRueck;
    }

    /*
     * markiert einen aktuellen wert im anzeige-array (oder falls nicht gefunden, den ersten)
     */
    public int set_ActiveInhalt_or_FirstInhalt(String cWert)
    {
        this.removeErrorEintrag();

        /*
         * 2013-10-01: aenderung des verhaltens: 
         * diese methode wird in den DB-Ablegern der klasse verwendet, um leere felder darzustellen fuer die maske. Dies hat zur folge, dass der fehlende Aufruf:
         *         this.cleanFromShadow();
         * vorher evtl. in einer maske auftauchende Shadow-werte stehen laesst und waehlbar macht.
         * Deshalb hier eingefuegt        
         */
        this.cleanFromShadow();

        
        
        if (cWert == null)
        {
        	this.setSelectedIndex(0);
        	return 0;
        }
        
        int iRueck=this.oDataToView.get_PositionOfView(cWert);
        
        if (iRueck == -1)
            this.setSelectedIndex(0);
        else
            this.setSelectedIndex(iRueck);

        
        
		this.setze_ToolTips();

        
        return iRueck;

    }

    
    /**
     * die "lebenden" von den "zombies" befreien
     */
    public void cleanFromShadow()
    {
    	
    	if (this.odataToViewShadow != null)
    	{
        	dataToView oDWNeu = new dataToView(false,bibE2.get_CurrSession());
        	boolean bLeibtDrin = true;
        	
    		for (int i=0;i<this.oDataToView.size();i++)
    		{
    			bLeibtDrin = true;
    			for (int k=0;k<this.odataToViewShadow.size();k++)
    			{
    				if (this.oDataToView.get(i).get_cData().equals(this.odataToViewShadow.get(k).get_cData()))  // gleicher datenwert heist: raus
    				{
    					bLeibtDrin = false;
    				}
    			}
    			if (bLeibtDrin)
    			{
    				oDWNeu.add(this.oDataToView.get(i));
    			}
    		}
    		
    		this.set_oDataToView(oDWNeu);
    	}
    }


    public String get_ActualWert() throws myException
    {
        return this.oDataToView.get_cValueAtPosition(this.getSelectedIndex());
    }

    
    
    public String get_ActualView() throws myException
    {
    	return this.oDataToView.get_cViewAtPosition(this.getSelectedIndex());
    }

    
    
    
    public void removeErrorEintrag()
    {
        if (this.bHasErrorEintrag)
        {
            int iOldPos = this.getSelectedIndex();
            this.oSelModel.remove(this.oDataToView.size());

            if ((iOldPos >= 0) && (iOldPos < this.oDataToView.size()))
            {
                this.setSelectedIndex(iOldPos);
            }

            this.bHasErrorEintrag = false;
        }
        
        this.setze_ToolTips();

    }

    
    public boolean get_bErrorEintragIsSelected()
    {
        boolean bRueck = false;

        if (this.bHasErrorEintrag)
        { // sonst ist gar keiner da, der angewählt sein kann

            if (this.getSelectedIndex() == this.oDataToView.size())
            {
                // dann steht die listenauswahl auf dem error-element
                bRueck = true;
            }
        }

        return bRueck;
    }


    /**
     * @return
     */
    public boolean get_bHasErrorEintrag()
    {
        return bHasErrorEintrag;
    }



	public String get_cDefaultValue()
	{
		return cDefaultValue;
	}
    
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		if (this.oDataToView == null)
			throw new myExceptionCopy("MyE2_SelectField:get_Copy: Error: SelectField not initialized !");
		
		
		MyE2_SelectField oSelField = new MyE2_SelectField();
		
		oSelField.set_EXT((MyE2EXT__Component)this.oEXT.get_Copy(oSelField));
		oSelField.setStyle(this.getStyle());
		oSelField.set_oDataToView(this.get_oDataToView());
		oSelField.set_odataToViewShadow(this.get_odataToViewShadow());
		
		
		Vector<XX_ActionAgent> vAgents = this.get_vActionAgents();
		for (int i=0;i<vAgents.size();i++)
			oSelField.add_oActionAgent((XX_ActionAgent)vAgents.get(i));
		
		
		//2013-01-04: interne actionAgents
		Vector<XX_ActionAgent> vInternalAgents = this.get_vInternalActionAgents();
		for (int i=0;i<vInternalAgents.size();i++)
			oSelField.add_oInternalActionAgent((XX_ActionAgent)vInternalAgents.get(i));

		
		
		Vector<ActionListener> vActionListeners = this.get_vExternalActionListeners();
		for (int i=0;i<vActionListeners.size();i++)
			oSelField.addActionListener((ActionListener)vActionListeners.get(i));
		
		for (int i=0;i<this.vGlobalValidators.size();i++)
			oSelField.add_GlobalValidator((XX_ActionValidator)this.vGlobalValidators.get(i));
				
		
		for (int i=0;i<this.vIDValidators.size();i++)
			oSelField.add_IDValidator((XX_ActionValidator)this.vIDValidators.get(i));

		
		oSelField.set_bSetToolTipsToActiveListValue(this.get_bSetToolTipsToActiveListValue());
		
		
		return oSelField;
	}
	
	
	
	/**
	 * Einfache kopie des select-fields ohne alle agenten
	 * @param cSelectValue
	 * @return
	 * @throws myException
	 */
	public MyE2_SelectField  get_SimpleCopy(String cSelectValue) throws myException
	{
		MyE2_SelectField oSelFieldNeu = new MyE2_SelectField();
		oSelFieldNeu.set_oDataToView(this.get_oDataToView());
		oSelFieldNeu.setWidth(this.getWidth());
		oSelFieldNeu.set_ActiveValue_OR_FirstValue(cSelectValue);
		return oSelFieldNeu;
	}


	public void set_bEnabled_For_Edit(boolean bbEnabled) throws myException
	{
		boolean bEnabled = bbEnabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();;

		this.setEnabled(bEnabled);
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(bEnabled,true,false));
	}

	public void show_InputStatus(boolean bInputIsOK)
	{
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),true,!bInputIsOK));
	}



	public String get_cDefineAction_IF_ValueNotFound()
	{
		return cDefineAction_IF_ValueNotFound;
	}


	public void set_cDefineAction_IF_ValueNotFound(String show_IF_ValueNotFound)
	{
		cDefineAction_IF_ValueNotFound = show_IF_ValueNotFound;
	}


	
	public void add_oActionAgent(XX_ActionAgent actionAgent, boolean bInFront)
	{
		if (actionAgent != null)
		{
			if (bInFront)
			{
				this.vActionAgents.add(0,actionAgent);
			}
			else
			{
				this.vActionAgents.add(actionAgent);
			}
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==1)
		{
			super.addActionListener(this.oInnerActionListener);
		}
	}

	
	
	/*
	 * 2012-02-07: weitere methode hinzugefuegt, martin 
	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
	 */
	public void add_oActionAgent(Vector<XX_ActionAgent> vActionAgent, boolean bInFront)
	{
		if (vActionAgent != null)
		{
			if (bInFront)
			{
				this.vActionAgents.addAll(0,vActionAgent);
			}
			else
			{
				this.vActionAgents.addAll(vActionAgent);
			}
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==1)
		{
			super.addActionListener(this.oInnerActionListener);
		}
	}

	

	
	public void add_oActionAgent(XX_ActionAgent actionAgent)
	{
		if (actionAgent != null)
		{
			this.vActionAgents.add(actionAgent);
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==1)
		{
			super.addActionListener(this.oInnerActionListener);
		}
	}

	
	
	public void remove_oActionAgent(XX_ActionAgent actionAgent)
	{
		for (int i=0;i<this.vActionAgents.size();i++)
		{
			if (this.vActionAgents.get(i)==actionAgent)
			{
				this.vActionAgents.remove(i);
			}
		}
		
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==0)
		{
			super.removeActionListener(this.oInnerActionListener);
		}
	}

	
	
	public void remove_AllActionAgents() 
	{
		this.vActionAgents.removeAllElements();
		super.removeActionListener(this.oInnerActionListener);
	}


	
	public Vector<XX_ActionAgent> get_vActionAgents()
	{
		return this.vActionAgents;
	}


	public Vector<ActionListener> get_vExternalActionListeners()
	{
		return vExternalActionListeners;
	}


	public void addActionListener(ActionListener oActionListener)
	{
		this.vExternalActionListeners.add(oActionListener);
		super.addActionListener(oActionListener);
	}
	
	public void removeActionListener(ActionListener oActionListener)
	{
		for (int i=0;i<this.vExternalActionListeners.size();i++)
		{
			if (this.vExternalActionListeners.get(i) == oActionListener)
			{
				this.vExternalActionListeners.remove(i);
			}
		}
		super.removeActionListener(oActionListener);
	}

	
	public void add_GlobalValidator(XX_ActionValidator oValid)
	{
		this.vGlobalValidators.add(oValid);
	}

	public void add_IDValidator(XX_ActionValidator oValid)
	{
		this.vIDValidators.add(oValid);
	}
	public void add_GlobalValidator(Vector<XX_ActionValidator> vValid)
	{
		this.vGlobalValidators.addAll(vValid);
	}

	public void add_IDValidator(Vector<XX_ActionValidator> vValid)
	{
		this.vIDValidators.addAll(vValid);
	}


	
	/**
	 * prueft alle vorhandenen globalen validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
	public MyE2_MessageVector valid_GlobalValidation() throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vGlobalValidators.size();i++)
		{
			XX_ActionValidator oValid = (XX_ActionValidator)this.vGlobalValidators.get(i);
			vRueck.add_MESSAGE(oValid.isValid(this));
		}
		return vRueck;
	}

	
	/**
	 * prueft alle vorhandenen ID-basierten validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
	public MyE2_MessageVector valid_IDValidation(Vector<String> vID_Unformated) throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vIDValidators.size();i++)
		{
			XX_ActionValidator oValid = (XX_ActionValidator)this.vIDValidators.get(i);
			vRueck.add_MESSAGE(oValid.isValid(vID_Unformated,false));
		}
		return vRueck;
	}


	
	
	public Vector<XX_ActionValidator> 		get_vGlobalValidators()	{		return vGlobalValidators;	}
	public Vector<XX_ActionValidator> 		get_vIDValidators()		{		return vIDValidators;	}
	public DefaultListModel 				get_oSelModel() 		{		return oSelModel;	}
	public void 							set_bHasErrorEintrag(boolean hasErrorEintrag) 
	{
		this.bHasErrorEintrag = hasErrorEintrag;
	}

	
	/*
	 * schalter, der benutzt wird, um "passive", d.h. aus dem programmcode abgeleitete event-aufrufe zu starten
	 */
	private boolean bActionEventIsPassive = false;
	

	
	@Deprecated
	/**
	 * Does some thing in old style.
	 *
	 * @deprecated use {@link doActionPassivManual()} instead.  
	 */
	public void doActionPassiv() 
	{
		bActionEventIsPassive =true;
		//this.doAction();   //select-field hat kein doAction(); 
		bActionEventIsPassive =false;
	}

	
	public void doActionPassivManual() {
		bActionEventIsPassive =true;
		
		EventListenerList list =  this.getEventListenerList();
		if (list!=null && list.getListeners(ActionListener.class).length>0) {
			
			EventListener[] clickListener = list.getListeners(ActionListener.class);
			for (EventListener l: clickListener) {
				if (l instanceof E2_InnerActionListenerForActionAgents) {
					((E2_InnerActionListenerForActionAgents)l).actionPerformed(new ActionEvent(this, null));
				}
			}
		}
		bActionEventIsPassive =false;
	}
	

	public boolean get_bIsPassivAction() 
	{
		return this.bActionEventIsPassive;
	}


	public void set_bPassivAction(boolean bPassiv) 
	{
		this.bActionEventIsPassive = bPassiv;
	}


	public dataToView get_DataToView()
	{
		return oDataToView;
	}


	public dataToView get_odataToViewShadow()
	{
		return odataToViewShadow;
	}


	public void set_odataToViewShadow(dataToView odataToViewShadow)
	{
		this.odataToViewShadow = odataToViewShadow;
	}

    
	
	/*
	 * eine liste mit bestimmten werten hervorheben
	 */
	public void MarkValues(Vector<String> vValuesUnformated, String MARKER) throws myException
	{
		dataToView oDV = this.get_oDataToView();

		int iPosActual = this.getSelectedIndex();
		
		for (int i=0;i<oDV.size();i++)
		{
			String cValue = bibALL.ReplaceTeilString(oDV.get_cValueAtPosition(i),".","");     // value ist hier formatiert  
			if (vValuesUnformated.contains(cValue))
			{
				if (!oDV.get_cViewAtPosition(i).startsWith(MARKER))
				{
					oDV.set_cViewAtPosition(MARKER+oDV.get_cViewAtPosition(i),i);
				}
			}
		}
		this.set_oDataToView(oDV);

		if (iPosActual>=0)
			this.setSelectedIndex(iPosActual);

	}

	
	
	
	protected String get_SQL_Query_For_LIST() {
		return m_cSQL_Query_For_LIST;
	}


	protected void setSQL_Query_For_LIST(String mCSQLQueryForLIST) {
		m_cSQL_Query_For_LIST = mCSQLQueryForLIST;
	}
	
	
	//2011-09-30: neue methode im interface fuer das passive abarbeiten eine Button-codes innerhalb eines 
	//            anderen actionagenten
	public void do_OnlyCode_from_OtherActionAgent(MyActionEvent oActionEventOtherActionAgent) throws myException 
	{
		new E2_PassiveActionExecuter(this, oActionEventOtherActionAgent);
	}
	
	
	public String get_cSQLQuery4InactivShownMembers()
	{
		return cSQLQuery4InactivShownMembers;
	}


	public void set_cSQLQuery4InactivShownMembers(String SQLQuery4InactivShownMembers)
	{
		this.cSQLQuery4InactivShownMembers = SQLQuery4InactivShownMembers;
	}

	
	
	//2012-06-19: Formatierbare listen, werden realisiert, indem den selectfields ListCellRenderer uebergeben werden
	public static class ownListCellRenderer implements ListCellRenderer
	{
		private Vector<String> vInactivListContent = null;
		
		public ownListCellRenderer(Vector<String> v_InactivListContent)
		{
			super();
			this.vInactivListContent = v_InactivListContent;
		}


		@Override
		public Object getListCellRendererComponent(Component list, final Object value, final int index)
		{
            return new StyledListCell() 
            {
            
                public Color getForeground() 
                {
                	if (vInactivListContent.contains(value.toString()))
                	{
                		return Color.DARKGRAY;
                	}
                	else
                	{
                		return Color.BLACK;
                	}
                }
            
                public Font getFont() 
                {
                    return new E2_FontPlain();
                }
            
                public Color getBackground() 
                {
                    return new E2_ColorBase();
                }
                
                
                //das ist der bestandteil des DefaultListCellRenderers
                public String toString() 
                {
                    return value == null ? null : value.toString();
                }
            };
		}
		
	}
	
	
	
	
	public static class ListCellRendererInfo {
		public Color 	foreColor = Color.BLACK;
		public Font  	font = new E2_FontPlain();
		public Color   	backColor = new E2_ColorEditBackground();
		public ListCellRendererInfo() {
			super();
		}
		public ListCellRendererInfo _fc(Color foreCol) {
			this.foreColor = foreCol;
			return this;
		}
		public ListCellRendererInfo _bc(Color backCol) {
			this.backColor = backCol;
			return this;
		}
		public ListCellRendererInfo _f(Font f) {
			this.font = f;
			return this;
		}
		
	}
	
	
	
	//2012-06-19: Formatierbare listen, werden realisiert, indem den selectfields ListCellRenderer uebergeben werden
	public static class ownListCellRenderer2 implements ListCellRenderer
	{
		private HashMap<String,ListCellRendererInfo> hm_sonderFormatierung = null;
		
		public ownListCellRenderer2( HashMap<String,ListCellRendererInfo> sonderFormatierung)
		{
			super();
			this.hm_sonderFormatierung = sonderFormatierung;
		}


		@Override
		public Object getListCellRendererComponent(Component list, final Object value, final int index)
		{
            return new StyledListCell() {
            
                public Color getForeground()  {
                	if (hm_sonderFormatierung.containsKey(value.toString()))	{
                		return hm_sonderFormatierung.get(value.toString()).foreColor;
                	} else {
                		return Color.BLACK;
                	}
                }
            
                public Font getFont() {
                	if (hm_sonderFormatierung.containsKey(value.toString()))	{
                		return hm_sonderFormatierung.get(value.toString()).font;
                	} else {
                		return new E2_FontPlain();
                	}
                }
            
                public Color getBackground() {
                   	if (hm_sonderFormatierung.containsKey(value.toString()))	{
                		return hm_sonderFormatierung.get(value.toString()).backColor;
                	} else {
                		return new E2_ColorEditBackground();
                	}
                }
                
                //das ist der bestandteil des DefaultListCellRenderers
                public String toString() {
                    return value == null ? null : value.toString();
                }
            };
		}
		
	}
	

	
	
	
	//2013-01-04: codeblock fuer interalActionAgents
	private E2_InnerActionListenerForInternalActionAgents  oInnerActionListenerInternalAction = new E2_InnerActionListenerForInternalActionAgents();
	private Vector<XX_ActionAgent>  vInternalActionAgents = new Vector<XX_ActionAgent>();
	public Vector<XX_ActionAgent> 	get_vInternalActionAgents()
	{
		return this.vInternalActionAgents;
	}

	public void add_oInternalActionAgent(XX_ActionAgent actionAgent)
	{
		if (actionAgent != null)
		{
			this.vInternalActionAgents.add(actionAgent);
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==1)
		{
			super.addActionListener(this.oInnerActionListenerInternalAction);
		}
	}

	
	public void add_oInternalActionAgent(XX_ActionAgent actionAgent, boolean bInFront)
	{
		if (actionAgent != null)
		{
			if (bInFront)
			{
				this.vInternalActionAgents.add(0,actionAgent);
			}
			else
			{
				this.vInternalActionAgents.add(actionAgent);
			}
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==1)
		{
			super.addActionListener(this.oInnerActionListenerInternalAction);
		}
	}
	
	public void remove_oInternalActionAgent(XX_ActionAgent actionAgent)
	{
		for (int i=0;i<this.vInternalActionAgents.size();i++)
		{
			if (this.vInternalActionAgents.get(i)==actionAgent)
			{
				this.vInternalActionAgents.remove(i);
			}
		}
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==0)
		{
			super.removeActionListener(this.oInnerActionListenerInternalAction);
		}
	}

	public void remove_AllInternalActionAgents() 
	{
		this.vInternalActionAgents.removeAllElements();
		super.removeActionListener(this.oInnerActionListenerInternalAction);
	}

	//2013-01-04 -- ende codeblock internalActionAgents
	
	
	
	
	//2013-01-04: --tooltipautomatik
	public boolean 		get_bSetToolTipsToActiveListValue()
	{
		return bSetToolTipsToActiveListValue;
	}
	
	public void set_bSetToolTipsToActiveListValue(boolean SetToolTipsToActiveListValue)
	{
		this.bSetToolTipsToActiveListValue = SetToolTipsToActiveListValue;
		
		if (this.bSetToolTipsToActiveListValue)
		{
			this.add_oInternalActionAgent(new ownActionSetLongTextToToolTip());
		}
	}

	
	public String get_cToolTipWhenEmptyDropDownValue()
	{
		return cToolTipWhenEmptyDropDownValue;
	}

	public void set_cToolTipWhenEmptyDropDownValue(String cToolTipWhenEmptyListValue)
	{
		this.cToolTipWhenEmptyDropDownValue = cToolTipWhenEmptyListValue;
	}

	
	
	private class ownActionSetLongTextToToolTip extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			MyE2_SelectField oSelect = (MyE2_SelectField)oExecInfo.get_MyActionEvent().getSource();
			
			oSelect.setze_ToolTips();
		}
	}


	private void setze_ToolTips()
	{
		try
		{
			if (this.bSetToolTipsToActiveListValue)
			{
				if (this.get_oDataToView()!=null && this.getSelectedIndex()>=0)
				{
					if (S.isFull(this.get_ActualWert()))
					{
						this.setToolTipText(S.NN(this.get_ActualView()));
					}
					else
					{
						this.setToolTipText(S.NN(this.get_cToolTipWhenEmptyDropDownValue()));
					}
				}
				else
				{
					this.setToolTipText(S.NN(this.get_cToolTipWhenEmptyDropDownValue()));
				}
			}
		}
		catch (Exception e)            //sicherheithalber aller exceptions abfangen
		{
			e.printStackTrace();
		}
	}
	
	
	
	//2014-09-08: methoden um das objekt in ein grid einzuwickeln
	private Vector<MyE2IF__Component> 	vADDONS_IN_WRAP = new Vector<MyE2IF__Component>();
	private boolean 					b_OnComponentInFront = true;
	private int   						i_SpaceInPixel = 2;
	@Override
	public MyE2IF__Component ME() throws myException {
		MyE2_Grid oGridRueck = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridRueck.setSize(1+this.vADDONS_IN_WRAP.size());
		//die alte layoutdata sichern und an das aussengrid uebergeben
		LayoutData oLayoutThis = this.getLayoutData();
		GridLayoutData 	oLayoutInnen1 = new GridLayoutData();
		GridLayoutData 	oLayoutInnen2 = new GridLayoutData();
		oLayoutInnen1.setInsets(E2_INSETS.I(0,0,this.i_SpaceInPixel,0));
		oLayoutInnen1.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		oLayoutInnen2.setInsets(E2_INSETS.I(0,0,0,0));
		oLayoutInnen2.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		if (this.b_OnComponentInFront) {
			this.setLayoutData(oLayoutInnen1);
			oGridRueck.add_raw(this);
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			((Component)this.vADDONS_IN_WRAP.get(this.vADDONS_IN_WRAP.size()-1)).setLayoutData(oLayoutInnen2);
		} else {
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			this.setLayoutData(oLayoutInnen2);
			oGridRueck.add_raw(this);
		}
		
		oGridRueck.setLayoutData(oLayoutThis);
		return oGridRueck;
	}

	@Override
	public Component C_ME() throws myException {
		return (Component) this.ME();
	}

	@Override
	public Vector<MyE2IF__Component> get_vADDONS_IN_WRAP() throws myException {
		return vADDONS_IN_WRAP;
	}

	@Override
	public void set_ME_First(boolean ME_InFront) {
		this.b_OnComponentInFront = ME_InFront;
	}

	@Override
	public void set_SpaceInPX(int iSpace) {
		this.i_SpaceInPixel=iSpace;
	}


	@Override
	public String get_STAMP_INFO() throws myException {
		return S.NN(this.get_ActualView());
	}


	public boolean get_bTranslate() {
		return m_bTranslate;
	}


	public boolean get_bThirdColumnIS_STANDARD_MARKER() {
		return m_bThirdColumnIS_STANDARD_MARKER;
	}





}
