package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_PRIO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_LIST_EXPANDER_HIERARCHY;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_LIST_EXPANDER_HIERARCHY_Settings;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_LIST_EXPANDER_ROW_OBJECT;
import echopointng.Separator;

/**
 * hilfsmethode zum schnellen ueberblick ueber adressen-informationen,
 * auch mehrere adressen gleichzeitig
 */

public class WF_HEAD_WINDOW_UEBERSICHT extends E2_BasicModuleContainer
{

     
	
	
	
	
	private Vector<String> 		vWorkflowIDs 	= null;
	
	
	private String 				cTO = bibE2.cTO();
	
	/*
	 * diese column enthaelt alles
	 */
	private MyE2_Column			oColBasic	= new MyE2_Column();

	private WF_LIST_EXPANDER_HIERARCHY_Settings oSettings = new WF_LIST_EXPANDER_HIERARCHY_Settings();

	
    public WF_HEAD_WINDOW_UEBERSICHT(Vector<String> v_WorkflowIDs,
    								WF_LIST_EXPANDER_HIERARCHY_Settings HierarchySettings
    								) throws myException
    {
        super();
        this.oSettings = HierarchySettings;
        
        this.vWorkflowIDs = v_WorkflowIDs;
        if (vWorkflowIDs == null || vWorkflowIDs.size()==0)
        {
            throw new myException("WF_HEAD_WINDOW_UEBERSCIHT: Null-values in Constructor not allowed !");
        }

        this.InitStyles();
        
        
        String cInList = "(";
        for (int i=0;i<vWorkflowIDs.size()-1;i++)
        {
            cInList += (String)vWorkflowIDs.get(i)+",";
        }
        cInList += (String)vWorkflowIDs.get(vWorkflowIDs.size()-1)+")";

        
        // alle Laufzettel lesen
        RECLIST_LAUFZETTEL recsLaufzettel = new RECLIST_LAUFZETTEL("SELECT * FROM " + cTO + ".JT_LAUFZETTEL WHERE ID_LAUFZETTEL IN " + cInList );
        
        int iAnzahl = recsLaufzettel.size();
        
        
        /*
         * jetzt die listen aufbauen
         */
        for (int i=0; i<iAnzahl; i++)
        {
        	// der Laufzettel
        	RECORD_LAUFZETTEL oLaufzettel = recsLaufzettel.get(i);
        	
        	// jetzt die Einträge ins Fenster einblenden
            this.oColBasic.add(this.addTitleRow(oLaufzettel),oInsetsStandard);   // baut die adress-ueberschrifts-zeile auf
            
            this.Add_Entries(oLaufzettel.get_ID_LAUFZETTEL_cUF());
            
            /*
             * leerzeile einfuegen
             */
        	this.oColBasic.add(new Separator(),new Insets(2));
        }

        
        this.add(oColBasic);
        this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(740),new MyE2_String("Laufzettel-Übersicht"));
        
    }

    
    
    
    
    /*
     * Styles
     * 
     */
	private  MutableStyle	oStyleGridEntry = new MutableStyle();
	private  MutableStyle	oStyleGridHeadingWFEntry = new MutableStyle();
	private  MutableStyle	oStyleGridHeadingWF = new MutableStyle();
	
	
	private  MutableStyle	oStyleLabelHeadingWF = new MutableStyle();
	private  MutableStyle	oStyleLabelHeadingWFSmall = new MutableStyle();
	
	private  MutableStyle	oStyleLabelHeadingWFEntry = new MutableStyle();
	private  MutableStyle	oStyleLabelHeadingWFEntrySmall = new MutableStyle();
	
	
//	private  MutableStyle	oStyleLabelEntryDark = new MutableStyle();
	private  MutableStyle	oStyleLabelEntry = new MutableStyle();
	private  MutableStyle   oStyleLabelEntryItalic = new MutableStyle();
	private  MutableStyle   oStyleLabelEntrySmall = new MutableStyle();
	
	private  MutableStyle	oStyleTextArea_HeadingWF = new MutableStyle();
	private  MutableStyle   oStyleTextArea_HeadingWFEntry = new MutableStyle();
	private  MutableStyle   oStyleTextArea_Entry = new MutableStyle();
	
	private  MutableStyle	oStyleLabelNormal = new MutableStyle();
	
	private  Color			oColorHeadingWF = new E2_ColorDDark();
	private  Color			oColorHeadingWFEntry = new E2_ColorDark();
	private  Color			oColorEntry = new E2_ColorBase();
	
	private  Insets		    oInsetsStandard = new Insets(10,3,10,3);

	
    private void InitStyles(){

    	// Gridstyles
    	oStyleGridHeadingWF.setProperty(Grid.PROPERTY_BACKGROUND,oColorHeadingWF);
    	oStyleGridHeadingWF.setProperty(Grid.PROPERTY_BORDER,new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		
		oStyleGridHeadingWFEntry.setProperty(Grid.PROPERTY_BACKGROUND,oColorHeadingWFEntry);
		oStyleGridHeadingWFEntry.setProperty(Grid.PROPERTY_BORDER,new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		
		oStyleGridEntry.setProperty(Grid.PROPERTY_BACKGROUND,oColorEntry);
		oStyleGridEntry.setProperty(Grid.PROPERTY_BORDER,new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		

		
		// Labels
		oStyleLabelHeadingWF.setProperty(Label.PROPERTY_FONT,new E2_FontBold(1));
		oStyleLabelHeadingWF.setProperty(Label.PROPERTY_BACKGROUND,oColorHeadingWF);
		
		oStyleLabelHeadingWFSmall.setProperty(Label.PROPERTY_FONT,new E2_FontBold(-2));
		oStyleLabelHeadingWFSmall.setProperty(Label.PROPERTY_BACKGROUND, oColorHeadingWF);
		
		oStyleLabelHeadingWFEntry.setProperty(Label.PROPERTY_FONT,new E2_FontBold(0));
		oStyleLabelHeadingWFEntry.setProperty(Label.PROPERTY_BACKGROUND, oColorHeadingWFEntry);
		
		oStyleLabelHeadingWFEntrySmall.setProperty(Label.PROPERTY_FONT,new E2_FontBold(-2));
		oStyleLabelHeadingWFEntrySmall.setProperty(Label.PROPERTY_BACKGROUND, oColorHeadingWFEntry);
		
	   	oStyleLabelEntry.setProperty(Label.PROPERTY_FONT,new E2_FontPlain());
	   	oStyleLabelEntry.setProperty(Label.PROPERTY_BACKGROUND, oColorEntry);
		
		oStyleLabelEntryItalic.setProperty(Label.PROPERTY_FONT,new E2_FontItalic(1));
		oStyleLabelEntryItalic.setProperty(Label.PROPERTY_BACKGROUND, oColorEntry);
		
		oStyleLabelEntrySmall.setProperty(Label.PROPERTY_FONT,new E2_FontPlain(-2));
		oStyleLabelEntrySmall.setProperty(Label.PROPERTY_BACKGROUND, oColorEntry);
		
		// Textarea
		oStyleTextArea_Entry.setProperty(TextArea.PROPERTY_BACKGROUND, oColorEntry);
		oStyleTextArea_HeadingWF.setProperty(TextArea.PROPERTY_BACKGROUND, oColorHeadingWF);
		oStyleTextArea_HeadingWFEntry.setProperty(TextArea.PROPERTY_BACKGROUND, oColorHeadingWFEntry);

		oStyleLabelNormal.setProperty(Label.PROPERTY_FONT,new E2_FontPlain(0));
		
		
		GridLayoutData oGL = new GridLayoutData();
		oGL.setAlignment(new Alignment(Alignment.LEADING,Alignment.TOP));
		oGL.setInsets(new Insets(2,2,2,2));
//		oGridLayout=oGL;
		
		ColumnLayoutData oCL = new ColumnLayoutData();
		oCL.setAlignment(new Alignment(Alignment.LEADING,Alignment.TOP));
		oCL.setInsets(new Insets(2,0,2,2));
//		oColLayout=oCL;

    }
 
    
    /**
     * Ermitttelt den Benutzernamen aus der ID
     * @param sID
     * @return
     */
    private String getUserNameFromID(String sID){
    	return getUserNameFromID(sID, "");
    }
    
    /**
     * 
     * @param sID
     * @param ValueWhenNull
     * @return
     */
    private String getUserNameFromID(String sID, String ValueWhenNull){
    	String sRet = ValueWhenNull;
    	RECORD_USER oRec = null;
    	if (!bibALL.isEmpty(sID) ){
    		try {
				oRec = new RECORD_USER(sID);
				if (oRec != null){
					sRet = oRec.get_VORNAME_cUF_NN("") + " " + oRec.get_NAME1_cUF_NN("");
				}
    		} catch (myException e) {
    			sRet = "ID nicht bekannt. (" + sID + ")" ;
    		}
    	}
    	return sRet;
    }
    
    
    
    /**
     * schreibt die adresszeile direkt in die haupttabelle rein und gibt die anzahl der hier benutzen titelspalten zurueck
     */
    private MyE2_Grid addTitleRow(RECORD_LAUFZETTEL oRec) throws myException
    {
    	
    	String sText;
    	String sIDUserBesitzer;
    	String sIDUserSupervisor;
    	String sAngelegtVon;
    	String sAngelegtAm;
    	String sIDUserAbgeschlossenVon;
    	String sAbgeschlossenAm;
    	String sPrivat;
    	String sGeloescht;

    	
    	MyE2_Grid oGrid = new MyE2_Grid(6);
    	oGrid.setWidth(new Extent(100,Extent.PERCENT));
    	
        oGrid.setStyle(oStyleGridHeadingWF);
		
        sText = oRec.get_TEXT_cUF_NN("");
        sIDUserBesitzer = oRec.get_ID_USER_BESITZER_cUF_NN("");
        sIDUserSupervisor = oRec.get_ID_USER_SUPERVISOR_cUF_NN("");
        sIDUserAbgeschlossenVon = oRec.get_ID_USER_ABGESCHLOSSEN_VON_cUF_NN("");
        sAbgeschlossenAm = oRec.get_ABGESCHLOSSEN_AM_cF_NN("");
        sGeloescht = oRec.get_GELOESCHT_cUF_NN("");
        sAngelegtVon = oRec.get_ERZEUGT_VON_cUF_NN("");
        sAngelegtAm = oRec.get_ANGELEGT_AM_cF_NN("");
        sPrivat = oRec.get_PRIVAT_cUF_NN("N");
        
        
        // Zeile
        oGrid.add(new MyE2_Label(new MyE2_String("Besitzer: ").CTrans() + getUserNameFromID(sIDUserBesitzer) ,oStyleLabelHeadingWFSmall),2, E2_INSETS.I_1_1_1_1);
        oGrid.add(new MyE2_Label(new MyE2_String("Supervisor: ").CTrans() + getUserNameFromID(sIDUserSupervisor) ,oStyleLabelHeadingWFSmall),2, E2_INSETS.I_1_1_1_1);
        oGrid.add(new MyE2_Label(new MyE2_String("Angelegt am ").CTrans() + sAngelegtAm, oStyleLabelHeadingWFSmall),2, E2_INSETS.I_1_1_1_1);
        
        
        // Zeile
        MyE2_TextArea oText =  new MyE2_TextArea();
        String[] rows = sText.split("\n");
    	oText.setWidth(new Extent(99, Extent.PERCENT));
		oText.setHeight(new Extent((int) (rows.length ) ,Extent.PC));
		oText.set_bEnabled_For_Edit(false);
		oText.setBackground(oColorHeadingWF);
		oText.setFont(new E2_FontBold());
		oText.setText(sText);

		oGrid.add(oText,6,E2_INSETS.I_1_1_1_1);

		
        // Zeile
        if (sPrivat.equals("Y")){
        	oGrid.add(new MyE2_Label(( sPrivat.equals("Y") ? "Der Laufzettel ist Privat!" : ""),oStyleLabelHeadingWF), 6, E2_INSETS.I_1_1_1_1);
        }
        
        // Zeile
        if (sGeloescht.equals("Y")){
        	oGrid.add(new MyE2_Label(( sGeloescht.equals("Y") ? "** Eintrag wurde gelöscht **" : ""),oStyleLabelHeadingWF), 6, E2_INSETS.I_1_1_1_1);
        }
        

        // Zeile
        if (!bibALL.isEmpty(sIDUserAbgeschlossenVon)){
	        oGrid.add(new MyE2_Label(new MyE2_String("Abgeschlossen von ").CTrans() + getUserNameFromID(sIDUserAbgeschlossenVon) ,oStyleLabelHeadingWFSmall),2, E2_INSETS.I_1_1_1_1);
	        oGrid.add(new MyE2_Label(new MyE2_String("am ").CTrans() + sAbgeschlossenAm ,oStyleLabelHeadingWFSmall),4, E2_INSETS.I_1_1_1_1);
        }
          
        return oGrid;
    }
    

    
    
    
    
    
    
    private void Add_Entries(String sIDWorkflow) throws myException{
    	

		// liste der einzelnen Laufzettel-Einträge
		RECLIST_LAUFZETTEL_EINTRAG oLE = 
			new RECLIST_LAUFZETTEL_EINTRAG("SELECT * FROM  " + cTO + ".JT_LAUFZETTEL_EINTRAG WHERE id_laufzettel = " + sIDWorkflow + " ORDER BY ID_LAUFZETTEL_EINTRAG " );
		
		WF_LIST_EXPANDER_HIERARCHY oH = new WF_LIST_EXPANDER_HIERARCHY(oLE);
		oH.set_Settings(oSettings);
		
		oH.BuildEntryList();
		Vector<WF_LIST_EXPANDER_ROW_OBJECT> oEntries = oH.getVDisplayRows();
		

		if (!oEntries.isEmpty())
		{
			// die Displayrows in die Tabelle aufnehmen
			for (WF_LIST_EXPANDER_ROW_OBJECT oRow :oEntries)
			{
				Add_Entry(oLE.get(oRow.getId_Laufzettel_Eintrag()), oRow.getDepth());
			}
		}

    }
    
    
    
    
    /*
     * gridblock fuer eine adresse, enthält alle untergeordneten angaben
     */
    private void Add_Entry(RECORD_LAUFZETTEL_EINTRAG Entry, int Stufe) throws myException
    {

    	RECORD_LAUFZETTEL_EINTRAG oRec = Entry;
    
    	// grid für die Überschrift
    	MyE2_Grid oGridHeading = new MyE2_Grid(4);
    	oGridHeading.setWidth(new Extent(100,Extent.PERCENT));
    	oGridHeading.setStyle(oStyleGridHeadingWFEntry);
    	

    	// Zeile
        oGridHeading.add(new MyE2_Label(new MyE2_String("Für: ").CTrans() + getUserNameFromID(oRec.get_ID_USER_BEARBEITER_cUF(),"-")  ,oStyleLabelHeadingWFEntrySmall),1, E2_INSETS.I_1_1_1_1);
        oGridHeading.add(new MyE2_Label(new MyE2_String("Von: ").CTrans() + getUserNameFromID(oRec.get_ID_USER_BESITZER_cUF(),"-") ,oStyleLabelHeadingWFEntrySmall),1, E2_INSETS.I_1_1_1_1);
        oGridHeading.add(new MyE2_Label(new MyE2_String("Zuletzt geändert von ").CTrans() + oRec.get_GEAENDERT_VON_cUF_NN("") + new MyE2_String(" am ").CTrans() + oRec.get_LETZTE_AENDERUNG_cF() ,oStyleLabelHeadingWFEntrySmall),2, E2_INSETS.I_1_1_1_1);
        
    	
    	// Zeile
    	//oGridHeading.add(new MyE2_LabelWrap(oRec.get_AUFGABE_cUF_NN(""),oStyleLabelEntryDark) ,1,E2_INSETS.I_1_1_1_1);
    	String sText = oRec.get_AUFGABE_cUF_NN("");
    	
    	MyE2_TextArea oText =  new MyE2_TextArea();
        String[] rows = sText.split("\n");
    	oText.setWidth(new Extent(99, Extent.PERCENT));
		oText.setHeight(new Extent((int) (rows.length ) ,Extent.PC));
		oText.set_bEnabled_For_Edit(false);
		oText.setBackground(oColorHeadingWFEntry);
		oText.setText(sText);

		oGridHeading.add(oText,4,E2_INSETS.I_1_1_1_1);

    	// restliches Grid
    	MyE2_Grid oGrid = new MyE2_Grid(6);
    	oGrid.setWidth(new Extent(100,Extent.PERCENT));
        oGrid.setStyle(oStyleGridEntry);
             
        
        // Zeile
        if (!bibALL.isEmpty(oRec.get_BERICHT_cUF_NN(""))){
        	
        	String sBericht = oRec.get_BERICHT_cUF_NN("");
        	
        	MyE2_TextArea oBericht =  new MyE2_TextArea();
            rows = sBericht.split("\n");
            oBericht.setWidth(new Extent(99, Extent.PERCENT));
            oBericht.setHeight(new Extent((int) (rows.length ) ,Extent.PC));
            oBericht.set_bEnabled_For_Edit(false);
            oBericht.setBackground(oColorEntry);
            oBericht.setFont(new E2_FontItalic());
            oBericht.setText(sBericht);
            

    		oGrid.add(oBericht,6,E2_INSETS.I_1_1_1_1);
	        
        }
        

        // Zeile
        if (!oRec.get_FAELLIG_AM_cF_NN("").equals("")){
        	oGrid.add(new MyE2_Label("Zu eledigen bis " + oRec.get_FAELLIG_AM_cF(),oStyleLabelEntrySmall), 6, E2_INSETS.I_1_1_1_1);
        }
        
        // Zeile
        String sIDStatus = oRec.get_ID_LAUFZETTEL_STATUS_cUF();
        if (sIDStatus != null){
        	RECORD_LAUFZETTEL_STATUS oStatus = new RECORD_LAUFZETTEL_STATUS(sIDStatus);
        	oGrid.add(new MyE2_Label("Staus: " + oStatus.get_STATUS_cUF(),oStyleLabelEntrySmall), 6, E2_INSETS.I_1_1_1_1);
        }
        
        String sIDPrio = oRec.get_ID_LAUFZETTEL_PRIO_cUF();
        if (sIDPrio != null){
        	RECORD_LAUFZETTEL_PRIO oPrio = new RECORD_LAUFZETTEL_PRIO(sIDPrio);
        	oGrid.add(new MyE2_Label("Prio: " + oPrio.get_PRIO_cUF(),oStyleLabelEntrySmall), 6, E2_INSETS.I_1_1_1_1);
       }

        
        // Zeile
        if (oRec.get_PRIVAT_cUF_NN("").equals("Y")){
        	oGrid.add(new MyE2_Label("Eintrag ist Privat",oStyleLabelEntry), 6, E2_INSETS.I_1_1_1_1);
        }
        
        // Zeile
        if (oRec.get_GELOESCHT_cUF_NN("").equals("Y")){
        	oGrid.add(new MyE2_Label("** EINTRAG GELÖSCHT **",oStyleLabelEntry), 6, E2_INSETS.I_1_1_1_1);
        }
        
        
        // Zeile
        if (!bibALL.isEmpty(oRec.get_ID_USER_ABGESCHLOSSEN_VON_cUF_NN(""))){
	        oGrid.add(new MyE2_Label(new MyE2_String("Abgeschlossen von ").CTrans() + getUserNameFromID(oRec.get_ID_USER_ABGESCHLOSSEN_VON_cUF_NN("")) ,oStyleGridEntry),2, E2_INSETS.I_1_1_1_1);
	        oGrid.add(new MyE2_Label(new MyE2_String("am ").CTrans() + oRec.get_ABGESCHLOSSEN_AM_cF() ,oStyleGridEntry),4, E2_INSETS.I_1_1_1_1);
        }
        
        Insets oInsetsEntry = new Insets(Stufe * 20, 0,0,0);
        
        this.oColBasic.add(oGridHeading,oInsetsEntry);
        this.oColBasic.add(oGrid, oInsetsEntry);
        this.oColBasic.add(new Separator(),oInsetsEntry);
            
     }
    
}
