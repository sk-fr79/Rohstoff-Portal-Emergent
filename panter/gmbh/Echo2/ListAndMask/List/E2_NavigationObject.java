/*
 *
 */
package panter.gmbh.Echo2.ListAndMask.List;

import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import echopointng.ContainerEx;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.Echo2.staticStyles.Style_Row_Normal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.myVectors.vectorForSegmentation;

/**
 * navigationsklasse mit eigenem actionlistener,
 * es existieren navigationsbuttons anfang, zurueck, seite weiter ende
 * 
 * Die navigation beginnt bei 0, angezeigt wird aber immer die 1 !!!
 * 
 * 
 */
public abstract class E2_NavigationObject extends MyE2_Column 
{
    
    private MyE2_Button		buttStart 				= null;
    private MyE2_Button 	buttEnd 				= null;
    private MyE2_Button 	buttBack 				= null;
    private MyE2_Button 	buttForeward 			= null;
    private MyE2_Button		buttGoPos				= null;
    
    private MyE2_Button     buttReload				= null;
    
//    private MyE2_Button     buttToggleReducedView   = null;
    
    /*
     * 2018-02-08: neuer button, der immer angezeigt wird, wenn ein NavilistStatus gespeichert wird
     */
    private E2_NaviListStatusSaverBtRestoreSettings       buttRestoreSaveSetting = new E2_NaviListStatusSaverBtRestoreSettings(this);
    
    
    /*
     * 2018-08-08: eigene ActionAgents, um eine eigene RefreshPageInfo-aktion auszuloesen
     */
    private Vector<XX_ActionAgent>    ownActionsRefreshingPage = new Vector<XX_ActionAgent>();
    public void addActionAfterRefreshPageInfo(XX_ActionAgent a) {
    	this.ownActionsRefreshingPage.add(a);
    }
    public void clearActionAfterRefreshPageInfo() {
    	this.ownActionsRefreshingPage.clear();
    }
	/**
	 * Feuern der angemeldeten ActionAgents
	 * @throws myException
	 */
	public void fireActionAgentsAfterRefreshPageInfo() throws myException{
		for (int i = 0; i < ownActionsRefreshingPage.size() ; i++){
			XX_ActionAgent o = ownActionsRefreshingPage.get(i);
			o.executeAgentCode(null);
		}
	}
    /*
     * 2018-08-08: eigene ActionAgents, um eine eigene RefreshPageInfo-aktion auszuloesen
     */
	

    
    
    
    public E2_Button getButtRestoreSaveSetting() {
		return buttRestoreSaveSetting;
	}


	private MyE2_TextField	oTextPageNumber			= null;
    
    /*
     * row fuer die eigene Steuerung
     */
    private MyE2_Row 		rowForContentNav		= null;    

    private ContainerEx     containerForContent			= null;
    
    private Color 			oColorKomponentBorder;
    private Color 			oColorBackgroundApplication;
    private Color 			oColorComponentContent;
    private int				iActualPage				= -1;

    private MyE2_Label		labelPositionsInfo  	= null;
    private MyE2_Label		labelZeilenInfo			= null;
    
    private E2_ComponentGroupHorizontal rowNavigationButtons = null;
    
    /*
     * ein select-field zum waehlen der darstellunglaenge der liste
     */
    private MyE2_SelectField  oSelectPageSize	=  null;
    

    /*
     * segmentierungsvector, enthaelt IDs
     */
    private vectorForSegmentation vectorSegmentation 	=	new vectorForSegmentation(); 
    
	//2011-03-17: Container der navilist uebergeben
    //2012-02-02: verschoben in dieses modul
	private E2_BasicModuleContainer   oContainer_NaviList_BelongsTo = null;

    
//	/*
//	 * definition, ob eine reduzierte ansicht dargestellt wird 
//	 */
//	private  boolean 				bReducedView = false;

    //2011-10-18: Vector mit komponenten vor und nach den navigationselementen einzublenden
    private Vector<Component>       VectorComponentsBevorNaviElements = new Vector<Component>();
    private Vector<Component>       VectorComponentsAfterNaviElements = new Vector<Component>();
    private RowLayoutData           oLayout_Components_bevor_NaviElements = MyE2_Row.LAYOUT_LEFT(E2_INSETS.I_0_0_1_0);
    private RowLayoutData           oLayout_Components_after_NaviElements = MyE2_Row.LAYOUT_LEFT(E2_INSETS.I_1_0_1_0);
    
    
//    //2012-02-02: speicherkenner fuer veschiedene bereiche, z.B. zum speichern der seitenlaenge
    private String       			AUTOMATIC_GENERATED_KENNUNG = null;           
    
	
    
    
    public E2_NavigationObject() throws myException
    {
        super();
        this.INIT_FOR_WORK();
        
        //this.setBorder(new Border(1,Color.RED,Border.STYLE_SOLID));
    }


    private void INIT_FOR_WORK() throws myException
    {
    	
    	
        // this.oMessageAgent = omessageAgent;
        this.setStyle(new Style_Column_Normal(0, new Insets(0,0)));
        
        this.oColorKomponentBorder  		= new E2_ColorBase(30);
        this.oColorBackgroundApplication  	= new E2_ColorBase(0);
        this.oColorComponentContent 		= new E2_ColorBase(60);
        
        this.vectorSegmentation.set_iSegmentGroesse(10);

        HashMap<String,String> hmSeitenLaengen = new HashMap<String,String>();
        String cHelp = new String(""+this.vectorSegmentation.get_iSegmentGroesse());
        if (cHelp.length()<3) cHelp = ("  ").substring(0,3-cHelp.length())+cHelp;
        hmSeitenLaengen.put(cHelp,cHelp);
        hmSeitenLaengen.put("  1","  1");
        hmSeitenLaengen.put("  2","  2");
        hmSeitenLaengen.put("  3","  3");
        hmSeitenLaengen.put("  4","  4");
        hmSeitenLaengen.put("  5","  5");
        hmSeitenLaengen.put("  8","  8");
        hmSeitenLaengen.put(" 10"," 10");
        hmSeitenLaengen.put(" 20"," 20");
        hmSeitenLaengen.put(" 30"," 30");
        hmSeitenLaengen.put(" 40"," 40");
        hmSeitenLaengen.put(" 50"," 50");
        hmSeitenLaengen.put(" 70"," 70");
        hmSeitenLaengen.put("100","100");
        hmSeitenLaengen.put("150","150");
        hmSeitenLaengen.put("200","200");

        Vector<String> vSeitenLaengen = bibALL.get_vBuildKeyVectorFromHashmap(hmSeitenLaengen);
        Collections.sort(vSeitenLaengen);
        
        String[] cPageSizes = new String[vSeitenLaengen.size()];
        for (int i=0;i<vSeitenLaengen.size();cPageSizes[i]=((String)vSeitenLaengen.get(i++)).trim());
        
        this.oSelectPageSize = new MyE2_SelectField(cPageSizes,null,false);
        this.oSelectPageSize.set_ActiveInhalt_or_FirstInhalt(""+this.vectorSegmentation.get_iSegmentGroesse());
        this.oSelectPageSize.add_oActionAgent(new action_oSelectPageSize());
        // fertig seitenlaenge-operator
        
        this.buttStart 		= new MyE2_Button(E2_ResourceIcon.get_RI("left_end.png"), 	E2_ResourceIcon.get_RI("leer.png"));
        this.buttEnd 		= new MyE2_Button(E2_ResourceIcon.get_RI("right_end.png"), 	E2_ResourceIcon.get_RI("leer.png"));
        this.buttBack 		= new MyE2_Button(E2_ResourceIcon.get_RI("left.png"), 	E2_ResourceIcon.get_RI("leer.png"));
        this.buttForeward 	= new MyE2_Button(E2_ResourceIcon.get_RI("right.png"), 	E2_ResourceIcon.get_RI("leer.png"));
        this.buttGoPos 		= new MyE2_Button(E2_ResourceIcon.get_RI("position.png"), 	E2_ResourceIcon.get_RI("leer.png"));
        
        this.buttReload 	= new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"), 	E2_ResourceIcon.get_RI("leer.png"));
        
        
        
        
//        this.buttToggleReducedView = new MyE2_Button(E2_ResourceIcon.get_RI("eng_zu_weit.png"), 	E2_ResourceIcon.get_RI("leer.png"));
//        this.buttToggleReducedView.setVisible(false);
        
        this.buttStart.add_oActionAgent(new action_buttStart());
        this.buttEnd.add_oActionAgent(new action_buttEnd());
        this.buttBack.add_oActionAgent(new action_buttBack());
        this.buttForeward.add_oActionAgent(new action_buttForeward());
        this.buttGoPos.add_oActionAgent(new action_buttGoPos());
        this.buttReload.add_oActionAgent(new action_buttReload());
//        this.buttToggleReducedView.add_oActionAgent(new action_buttToggleReducedView());
//       
        
        this.oTextPageNumber = new MyE2_TextField("",50,5);
        this.oTextPageNumber.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
        
        this.buttStart.setToolTipText(new MyE2_String("Sprung zur ersten Seite").CTrans());
        this.buttEnd.setToolTipText(new MyE2_String("Sprung zur letzen Seite").CTrans());
        this.buttBack.setToolTipText(new MyE2_String("Sprung zur vorigen Seite").CTrans());
        this.buttForeward.setToolTipText(new MyE2_String("Sprung zur nächsten Seite").CTrans());
        this.buttGoPos.setToolTipText(new MyE2_String("Sprung zur eingegebenen Seite").CTrans());
        this.oTextPageNumber.setToolTipText(new MyE2_String("Sprung zur eingegebenen Seite (Pfeil nach unten)").CTrans());
        
        
        this.labelPositionsInfo = new MyE2_Label(""+this.iActualPage+" ("+this.vectorSegmentation.get_iZahlSegmente()+")");
        this.labelZeilenInfo = new MyE2_Label("" + this.vectorSegmentation.size() + "");
        
        // this.rowNavigationButtons 	= new MyE2_Row(new Style_Row_Normal(0, new Insets(2,2)));
        
        this.rowNavigationButtons	= new E2_ComponentGroupHorizontal(1,E2_INSETS.I_0_0_2_0);
        this.rowForContentNav 		= new MyE2_Row(new Style_Row_Normal(0, new Insets(0,0)));
        this.containerForContent = new ContainerEx();
        this.containerForContent.setScrollBarPolicy(ContainerEx.AUTO);

        //this.containerForContent.setHeight(new Extent(100,Extent.PERCENT));
        
        this.add(this.rowForContentNav,E2_INSETS.I_0_0_0_0);
        this.add(this.containerForContent,E2_INSETS.I_0_0_0_0);
 

        /*
         * gridForContentNav WIRD GEBRAUCHT FUER Rahmen um die navigationsbuttons
         */
        this.rowForContentNav.add(this.rowNavigationButtons, E2_INSETS.I_0_0_0_0);
        
		//2017-11-22: umstellen auf ueberschreibbare methode

		this.buildRow4Navigation(		rowNavigationButtons
										, buttStart
										, buttBack
										, oTextPageNumber
										, buttGoPos
										, buttForeward
										, buttEnd
										, buttReload
										, this.buttRestoreSaveSetting
										, labelPositionsInfo
										, labelZeilenInfo
										, oSelectPageSize
										);

        
//        rowNavigationButtons.add(this.buttStart,E2_INSETS.I(2,1,0,1));
//        rowNavigationButtons.add(this.buttBack,E2_INSETS.I(0,1,0,1));
//        rowNavigationButtons.add(this.oTextPageNumber,E2_INSETS.I(5,1,0,1));
//        rowNavigationButtons.add(this.buttGoPos,E2_INSETS.I(0,1,5,1));
//        rowNavigationButtons.add(this.buttForeward,E2_INSETS.I(0,1,0,1));
//        rowNavigationButtons.add(this.buttEnd,E2_INSETS.I(0,1,0,1));
//        rowNavigationButtons.add(this.buttReload,E2_INSETS.I(10,1,0,1));
////        rowNavigationButtons.add(this.buttToggleReducedView);
//        rowNavigationButtons.add(new MyE2_Label("Seite: "),E2_INSETS.I(10,1,3,1));
//        rowNavigationButtons.add(this.labelPositionsInfo,E2_INSETS.I(10,1,0,1));
//        
//        rowNavigationButtons.add(new MyE2_Label("Anz. Zeilen (Datensätze):"),E2_INSETS.I(5,1,3,1));
//        rowNavigationButtons.add(this.labelZeilenInfo,E2_INSETS.I(10,1,3,1));
//        
//        /*
//         * das selektionsfeld fuer die Seitenlaenge
//         */
//        rowNavigationButtons.add(new MyE2_Label("Seitenlänge: "),E2_INSETS.I(10,1,0,1));
//        rowNavigationButtons.add(this.oSelectPageSize,E2_INSETS.I(10,1,0,1));
//        //rowNavigationButtons.setCellSpacing(new Extent(2));
//        
//        RowLayoutData oRowBig =  new RowLayoutData();
//        oRowBig.setWidth(new Extent(100));
//        this.labelPositionsInfo.setLayoutData(oRowBig);
//        this.labelZeilenInfo.setLayoutData(oRowBig);

    }
    
    
    
    /**
     * Ein/ausschalten der Navigationsbuttons
     * @param bEnabled
     */
    public void set_EnabledNavigationElements(boolean bEnabled)
    {
    	try
    	{
	        this.buttStart.set_bEnabled_For_Edit(bEnabled);
	        this.buttBack.set_bEnabled_For_Edit(bEnabled);
	        this.oTextPageNumber.set_bEnabled_For_Edit(bEnabled);
	        this.buttGoPos.set_bEnabled_For_Edit(bEnabled);
	        this.buttForeward.set_bEnabled_For_Edit(bEnabled);
	        this.buttEnd.set_bEnabled_For_Edit(bEnabled);
	        this.buttReload.set_bEnabled_For_Edit(bEnabled);
	        this.buttRestoreSaveSetting.set_bEnabled_For_Edit(bEnabled);
	        this.oSelectPageSize.set_bEnabled_For_Edit(bEnabled);
    	}
    	catch (myException ex)
    	{
    		
    	}
    }
    
    
    public void refresh_pageinfo_in_navigator(int iactualPage) throws myException
    {
        this.iActualPage = iactualPage;
        
//        this.labelPositionsInfo.setText(""+(this.iActualPage+1)+" / "+this.vectorSegmentation.get_iZahlSegmente());
        //2015-07-27: formatierte seitenzahl
        this.labelPositionsInfo.setText(""+(this.iActualPage+1)+" / "+MyResultValue.formatDez(this.vectorSegmentation.get_iZahlSegmente(),true));
        this.labelZeilenInfo.setText(MyResultValue.formatDez(this.vectorSegmentation.size(),true));
        
        this.oTextPageNumber.setText(""+(iactualPage+1));
        this.oSelectPageSize.set_ActiveValue(""+this.vectorSegmentation.get_iSegmentGroesse());
        
        //2018-08-02: code nach pagerefresh ausfuehren
        this.fireActionAgentsAfterRefreshPageInfo();
        
    }
    
    

    /*
     * damit kann der naviationsbutton ein- und ausgeschaltet werden
     */
    public void show_NavigationsElements()
    {
        this.removeAll();
        this.add(this.rowForContentNav);
        this.add(this.containerForContent);
    }
    

    /*
     * damit kann der naviationsbutton ein- und ausgeschaltet werden
     */
    public void hide_NavigationsElements()
    {
        this.removeAll();
        this.add(this.containerForContent);
    }
    
    
    
	public E2_BasicModuleContainer get_oContainer_NaviList_BelongsTo() 
	{
		return this.oContainer_NaviList_BelongsTo;
	}

	public void set_oContainer_NaviList_BelongsTo(E2_BasicModuleContainer oContainer_NaviList_BelongsTo) 
	{
		this.oContainer_NaviList_BelongsTo = oContainer_NaviList_BelongsTo;
	}

  
    
    
    public void set_NEW_remove_OLD_Content(Component oComponent)
    {
        this.containerForContent.removeAll();
        this.containerForContent.add(oComponent);
    }
    
    
    
    private class action_oSelectPageSize extends XX_ActionAgent
    {
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_NavigationObject oThis = E2_NavigationObject.this;
            String cActualLength = oThis.oSelectPageSize.get_ActualWert();
            if (cActualLength != null)
            {
                Integer intNewSize = new Integer((String)oThis.oSelectPageSize.getSelectedItem());
                oThis.vectorSegmentation.set_iSegmentGroesse(intNewSize.intValue());
                
                //2012-02-02: jetzt pruefen, ob ein save_kenner hinterlegt ist, und wenn ja, dann speichern der gewaehlten laenge
                if (S.isFull(E2_NavigationObject.this.get_AUTOMATIC_GENERATED_KENNUNG()))
                {
                	new E2_Usersetting_SiteLength(E2_NavigationObject.this.get_AUTOMATIC_GENERATED_KENNUNG()).STORE(
                			 oThis.oSelectPageSize.get_ActualWert());
                	
                }
            }
            /*
             * jetzt die abstrakte methode ausfuehren, um die einstellungen zu definieren
             */
            oThis.changeSettingsForNewSegmentSize();
		}
    }
    

    private class action_buttStart extends XX_ActionAgent
    {
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_NavigationObject oThis = E2_NavigationObject.this;
			oThis.goToPage(oThis,0);
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Sie sind am Anfang der Liste"));
		}
    }

    private class action_buttEnd extends XX_ActionAgent
    {
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_NavigationObject oThis = E2_NavigationObject.this;
			oThis.goToPage(oThis,oThis.vectorSegmentation.get_iZahlSegmente()-1);
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Sie sind am Ende der Liste"));
		}
    }
  

    private class action_buttForeward extends XX_ActionAgent
    {
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_NavigationObject oThis = E2_NavigationObject.this;
	        int iNav = oThis.iActualPage+1;
	        if (iNav>(oThis.vectorSegmentation.get_iZahlSegmente()-1)) iNav = (oThis.vectorSegmentation.get_iZahlSegmente()-1);
	        oThis.goToPage(oThis,iNav);
	        
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Sie sind auf Seite ... "+(iNav+1)));
		}
    }
  

    private class action_buttBack extends XX_ActionAgent
    {
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_NavigationObject oThis = E2_NavigationObject.this;
	        int iNav = oThis.iActualPage-1;
	        if (iNav<0) iNav = 0;
	        oThis.goToPage(oThis,iNav);

	        bibMSG.add_MESSAGE(new MyE2_Info_Message("Sie sind auf Seite ... "+(iNav+1)));
		}
    }
  
    private class action_buttReload extends XX_ActionAgent
    {
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_NavigationObject oThis = E2_NavigationObject.this;
        	oThis.RefreshList();
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Liste wurde neu aufgebaut"));
		}
    }
  
    
//    private class action_buttToggleReducedView extends XX_ActionAgent
//    {
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
//		{
//			E2_NavigationObject oThis = E2_NavigationObject.this;
//			oThis.bReducedView = !oThis.bReducedView;
//			oThis._REBUILD_ACTUAL_SITE("");
//
//		}
//    }


    
    private class action_buttGoPos extends XX_ActionAgent
    {
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_NavigationObject oThis = E2_NavigationObject.this;
			try
            {
                Integer intZahl = new Integer(oThis.oTextPageNumber.getText());
                int 	iPos	= intZahl.intValue() -1;
                
                int iNav=-1;
                if (iPos<0) 
                    iNav = 0;
                else if (intZahl.intValue()>(oThis.vectorSegmentation.get_iZahlSegmente()-1))
                    iNav = (oThis.vectorSegmentation.get_iZahlSegmente()-1);
                else
                    iNav = iPos;
                
                oThis.goToPage(oThis,iNav);
    			bibMSG.add_MESSAGE(new MyE2_Info_Message("Sie sind auf Seite ... "+(iNav+1)));
                
            }
            catch (NumberFormatException ex)
            {
                throw new myExceptionForUser("Bitte eine Ganzzahl eingeben !");
            }
			
		}
    }
    
    

    /**
     * Methode wird aufgerufen in set_newContentVector und ist der Einsprungpunkt in der NavigationList nachdem die
     * IDs neu aufgebaut wurden
     * @throws myException
     */
    protected void fireActionAgentsAfterContentsIsSet(){
    }
    
    
    
    /**
     * @param object
     * @param i
     */
    public abstract boolean goToPage(Object object, int iZielseite) throws myException;
    
 

    /*
     * methode, um den aufbau der liste zu regeln, wenn die seitenlaenge veraendert wurde
     */
    public abstract void  changeSettingsForNewSegmentSize()  throws myException;
    
 
    
    /*
     * methode, einen neuaufbau zu erzwingen
     */
    public abstract void  RefreshList()  throws myException;
 
    
    
    public abstract void _REBUILD_ACTUAL_SITE(String MarkOnlyID) throws myException;
    
    
    /*
     * neuen content uebergeben
     */
    public void set_newContentVector(Vector<String> vContent)
    {
        this.vectorSegmentation.do_Replace_content(vContent);
        
        this.fireActionAgentsAfterContentsIsSet();
		
        //aenderung: Automatisches ausblenden der navi-element raus 
//        if (this.vectorSegmentation.get_iZahlSegmente()<=1)
//        {
//            this.hide_NavigationsElements();
//        }
//        else
//        {
//            this.show_NavigationsElements();
//        }
    }
    

    public Color get_oColorBackgroundApplication()
    {
        return oColorBackgroundApplication;
    }
    public void set_oColorBackgroundApplication(Color colorBackgroundApplication)
    {
        oColorBackgroundApplication = colorBackgroundApplication;
    }
    public Color get_oColorComponentContent()
    {
        return oColorComponentContent;
    }
    public void set_oColorComponentContent(Color colorComponentContent)
    {
        oColorComponentContent = colorComponentContent;
    }
    public Color get_oColorKomponentBorder()
    {
        return oColorKomponentBorder;
    }
    public void set_oColorKomponentBorder(Color colorKomponentBorder)
    {
        oColorKomponentBorder = colorKomponentBorder;
    }

    public int get_iActualPage()
    {
        return iActualPage;
    }
     
    public MyE2_SelectField get_oSelectPageSize()
    {
        return oSelectPageSize;
    }

    public vectorForSegmentation get_vectorSegmentation()
    {
        return vectorSegmentation;
    }

//	public boolean get_bReducedView()
//	{
//		return bReducedView;
//	}
//
//
//	public void set_bReducedView(boolean reducedView)
//	{
//		bReducedView = reducedView;
//	}


	public MyE2_Button get_ButtonReload()
	{
		return buttReload;
	}


//	public MyE2_Button get_ButtonToggleReducedView()
//	{
//		return buttToggleReducedView;
//	}


	/**
	 * @return s Row with navigation-elements
	 * Hier koennen eigene kompoenten eingeblendet werden
	 */
	public MyE2_Row get_RowForNavigationElements() 
	{
		return rowForContentNav;
	}
	
	
	//2011-10-18: neue methode um komponenten in die listentitelzeile einblenden zu koennen
	public void clean_Components_bevor_NaviElements()
    {
    	this.VectorComponentsBevorNaviElements.removeAllElements();
    	this.baue_RowWithNavElements();
    }
    public void clean_Components_after_NaviElements()
    {
    	this.VectorComponentsAfterNaviElements.removeAllElements();
    	this.baue_RowWithNavElements();
    }
    
    public void add_ComponentBevorNaviElements(Component oComp)
    {
    	this.VectorComponentsBevorNaviElements.add(oComp);
    	this.baue_RowWithNavElements();
    }
    public void add_ComponentAfterNaviElements(Component oComp)
    {
    	this.VectorComponentsAfterNaviElements.add(oComp);
    	this.baue_RowWithNavElements();
    }

	public RowLayoutData get_oLayout_Components_bevor_NaviElements()
	{
		return oLayout_Components_bevor_NaviElements;
	}
	public RowLayoutData get_oLayout_Components_after_NaviElements()
	{
		return oLayout_Components_after_NaviElements;
	}
	public void set_oLayout_Components_bevor_NaviElements(RowLayoutData oLayout_Components_bevor_NaviElements)
	{
		this.oLayout_Components_bevor_NaviElements = oLayout_Components_bevor_NaviElements;
    	this.baue_RowWithNavElements();
	}
	public void set_oLayout_Components_after_NaviElements(RowLayoutData oLayout_Components_after_NaviElements)
	{
		this.oLayout_Components_after_NaviElements = oLayout_Components_after_NaviElements;
    	this.baue_RowWithNavElements();
	}
	
	//2011-10-18: neue methode um komponenten in die listentitelzeile einblenden zu koennen
    private void baue_RowWithNavElements()
    {
    	this.rowForContentNav.removeAll();
    	if (this.VectorComponentsBevorNaviElements!=null && this.VectorComponentsBevorNaviElements.size()>0)
    	{
    		for (int i=0;i<this.VectorComponentsBevorNaviElements.size();i++)
    		{
    			this.rowForContentNav.add(this.VectorComponentsBevorNaviElements.get(i), this.oLayout_Components_bevor_NaviElements);
    		}
    	}
    	
    	this.rowForContentNav.add(this.rowNavigationButtons, E2_INSETS.I_0_0_0_0);
    	
    	if (this.VectorComponentsAfterNaviElements!=null && this.VectorComponentsAfterNaviElements.size()>0)
    	{
    		for (int i=0;i<this.VectorComponentsAfterNaviElements.size();i++)
    		{
    			this.rowForContentNav.add(this.VectorComponentsAfterNaviElements.get(i), this.oLayout_Components_after_NaviElements);
    		}
    	}
    }


	public String get_AUTOMATIC_GENERATED_KENNUNG()
	{
		return AUTOMATIC_GENERATED_KENNUNG;
	}


	public void set_AUTOMATIC_GENERATED_KENNUNG(String cKENNUNG)
	{
		AUTOMATIC_GENERATED_KENNUNG = cKENNUNG;
	}


	// 20171122: rowNavigationButtons aufbauen, kann ueberschrieben werden
	public void buildRow4Navigation(	E2_ComponentGroupHorizontal _rowNavigationButtons
    									, MyE2_Button _buttStart
    									, MyE2_Button _buttBack
    									, MyE2_TextField _oTextPageNumber
    									, MyE2_Button _buttGoPos
    									, MyE2_Button _buttForeward
    									, MyE2_Button _buttEnd
    									, MyE2_Button _buttReload
    									, E2_Button   _buttRestore
    									, MyE2_Label  _labelPosInfo
    									, MyE2_Label  _labelZeilenInfo
    									, MyE2_SelectField selZeilen
    									) {
		
		_rowNavigationButtons.add(_buttStart, E2_INSETS.I(2, 1, 0, 1));
		_rowNavigationButtons.add(_buttBack, E2_INSETS.I(0, 1, 0, 1));
		_rowNavigationButtons.add(_oTextPageNumber, E2_INSETS.I(5, 1, 0, 1));
		_rowNavigationButtons.add(_buttGoPos, E2_INSETS.I(0, 1, 5, 1));
		_rowNavigationButtons.add(_buttForeward, E2_INSETS.I(0, 1, 0, 1));
		_rowNavigationButtons.add(_buttEnd, E2_INSETS.I(0, 1, 0, 1));
		_rowNavigationButtons.add(_buttReload, E2_INSETS.I(10, 1, 0, 1));
		_rowNavigationButtons.add(_buttRestore, E2_INSETS.I(10, 1, 0, 1));    //20180208: restore-button
		
		// rowNavigationButtons.add(this.buttToggleReducedView);
		_rowNavigationButtons.add(new MyE2_Label("Seite: "), E2_INSETS.I(10, 1, 3, 1));
		_rowNavigationButtons.add(_labelPosInfo, E2_INSETS.I(10, 1, 0, 1));

		_rowNavigationButtons.add(new MyE2_Label("Anz. Zeilen (Datensätze):"), E2_INSETS.I(5, 1, 3, 1));
		_rowNavigationButtons.add(_labelZeilenInfo, E2_INSETS.I(10, 1, 3, 1));

		/*
		 * das selektionsfeld fuer die Seitenlaenge
		 */
		_rowNavigationButtons.add(new MyE2_Label("Seitenlänge: "), E2_INSETS.I(10, 1, 0, 1));
		_rowNavigationButtons.add(selZeilen, E2_INSETS.I(10, 1, 0, 1));
		
		RowLayoutData oRowBig = new RowLayoutData();
		oRowBig.setWidth(new Extent(100));
		_labelPosInfo.setLayoutData(oRowBig);
		_labelZeilenInfo.setLayoutData(oRowBig);
    }
	
	
	/**
	 * 
	 * @author martin
	 * @date 18.02.2019
	 *
	 * @return s inner container for list
	 */
	public ContainerEx getContainerForContent() {
		return containerForContent;
	}

}
 
    
    
    
    
    
    

