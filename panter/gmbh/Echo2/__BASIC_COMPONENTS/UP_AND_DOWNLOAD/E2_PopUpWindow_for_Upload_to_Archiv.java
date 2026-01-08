package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.WindowPane;
import nextapp.echo2.app.event.WindowPaneEvent;
import nextapp.echo2.app.event.WindowPaneListener;
import nextapp.echo2.app.filetransfer.UploadEvent;
import nextapp.echo2.app.filetransfer.UploadListener;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.Container.E2_FillImageBorder;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorWindowPaneContent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorWindowPaneTitel;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.E2_MessageHandler;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWrap;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionInFileHandling;

public class E2_PopUpWindow_for_Upload_to_Archiv extends MyE2_WindowPane implements UploadListener, WindowPaneListener
{
	public static MutableStyle oStyleWindowPane = null;
	static
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( WindowPane.PROPERTY_BACKGROUND, new E2_ColorWindowPaneContent()); 
		try
		{
			oStyle.setProperty( WindowPane.PROPERTY_TITLE_BACKGROUND, new E2_ColorWindowPaneTitel());
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
		 
		
		oStyle.setProperty( WindowPane.PROPERTY_CLOSE_ICON, E2_ResourceIcon.get_RI("windowpaneclose_small.png"));
		oStyle.setProperty( WindowPane.PROPERTY_BORDER, new E2_FillImageBorder(Color.BLACK,new Insets(1,1,1,1),new Insets(1,1,1,1)));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_HEIGHT,new Extent(20));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_INSETS,new Insets(7,3,0,3));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FONT, new E2_FontPlain(-2));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FOREGROUND, Color.WHITE);
		
		oStyleWindowPane = oStyle;
		
	}

	
    private boolean 			bOverWrite	= 		false;
	private String		   		cUeberschrift = 	"";
	private String		   		cInfoText = 		"";
	private String 				cTABELLE = 			null;
	private String 				cID_ZU_Tabelle	= 	null;
    
    private Component			oZusatzComponent = null;
    
	
	private E2_UploadSelect 	oUploadSelect	= 	null;
    private Archiver 			oArchiver	= 		null;
	
    private E2_ContentPane		oContentPanePopUP = new E2_ContentPane(true);
	
    /*
     * das basis-grid, wo alles reinkommt
     */
    private E2_BasicModuleContainer   	oCol = new E2_BasicModuleContainer();
    
    
    /*
     * wird eine navigtionslist uebergeben, wird nach dem upload ein refresh der seite ausgefuehrt
     */
    private E2_NavigationList	oNaviList_Files = null;
    
    
    //2011-12-06: neues feld programm_kenner
    private String     			cPROGRAMM_KENNER = null;
    
    
	/**
	 * @param tabelle
	 * @param cid_tabelle
	 * @param overWrite
	 * @param ueberschrift
	 * @param infoText
	 * @param oNavigation_FileList 
	 * @throws myException
	 */
	public E2_PopUpWindow_for_Upload_to_Archiv(		String 				tabelle,
													String 				cid_tabelle, 
													boolean 			overWrite, 
													String 				ueberschrift, 
													String 				infoText, 
													E2_NavigationList 	oNavigation_FileList)  throws myException
	{
		super(true);
		
		this.define__(tabelle, cid_tabelle, overWrite, ueberschrift, infoText, oNavigation_FileList,null,null);
	}



	
	/**
	 * 
	 * @param tabelle
	 * @param cid_tabelle
	 * @param overWrite
	 * @param ueberschrift
	 * @param infoText
	 * @param oNavigation_FileList
	 * @param v_erlaubte_endungen
	 * @param Programm_kenner
	 * @throws myException
	 */
	public E2_PopUpWindow_for_Upload_to_Archiv(		String 				tabelle,
													String 				cid_tabelle, 
													boolean 			overWrite, 
													String 				ueberschrift, 
													String 				infoText, 
													E2_NavigationList 	oNavigation_FileList,
													Vector<String>     v_erlaubte_endungen,
													String     			Programm_kenner)  throws myException
	{
		super(true);
		
		this.define__(tabelle, cid_tabelle, overWrite, ueberschrift, infoText, oNavigation_FileList,v_erlaubte_endungen, Programm_kenner);
	
	}

	
	
	private void define__(		String 				tabelle,
								String 				cid_tabelle, 
								boolean 			overWrite, 
								String 				ueberschrift, 
								String 				infoText, 
								E2_NavigationList 	oNavigation_FileList,
								Vector<String>      v_ErlaubteEndungen,
								String    			cProgrammKenner)  throws myException
	{
		
		
		this.oNaviList_Files = oNavigation_FileList;
		
		this.setDefaultCloseOperation(WindowPane.DISPOSE_ON_CLOSE);
		this.addWindowPaneListener(this);
		
		
		this.setStyle(E2_PopUpWindow_for_Upload_to_Archiv.oStyleWindowPane);
		this.add(this.oContentPanePopUP);
		this.oContentPanePopUP.add(this.oCol);
		
		this.bOverWrite = 			overWrite;
		this.cUeberschrift = 		ueberschrift;
		this.cInfoText = 			infoText;
		this.cTABELLE = 			tabelle;
		this.cID_ZU_Tabelle = 		cid_tabelle;
		
		this.cPROGRAMM_KENNER = 	cProgrammKenner;
		
		this.setWidth(new Extent(750));
		this.setHeight(new Extent(400));
		
		/*
		* nur der "kern" des tabellennamens
		*/
		if (this.cTABELLE.toUpperCase().startsWith("JT_")) this.cTABELLE = this.cTABELLE.substring(3);
		if (this.cTABELLE.toUpperCase().startsWith("JD_")) this.cTABELLE = this.cTABELLE.substring(3);
		
		this.oArchiver = new Archiver(this.cTABELLE);
		
		this.oUploadSelect = new E2_UploadSelect(oArchiver.get_cCompleteArchivePath(), bOverWrite);
		try
		{
			this.oUploadSelect.addUploadListener(this);
		}
		catch (Exception ex)
		{
			throw new myException("GridForFileUpload:Error creating uploader !"+ex.getLocalizedMessage());
		}
		
		/*
		* jetzt checken, welche endungen erlaubt sind !!!
		*/
		String cInfoStringErlaubteDateisorten = bibALL.get_TRANSLATOR().translate("\nErlaubte Dateien zum Upload: ") + "\n";

		Vector<String> 		vEndungen	= new Vector<String>();
		
		if (v_ErlaubteEndungen == null || v_ErlaubteEndungen.size()==0)   //dann erfolgt abfrage
		{
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			String[][] cDefinitionen = oDB.EinzelAbfrageInArray("SELECT DATEIENDUNG,BESCHREIBUNG FROM " + bibALL.get_TABLEOWNER() + ".JT_MEDIENTYP order by DATEIENDUNG", "@@@");
			bibALL.destroy_myDBToolBox(oDB);
			
			if ((cDefinitionen == null) || (cDefinitionen.length == 0))
			{
				throw new myException(this,"There are no allowed Filetypes defined !!");
			}
			
			for (int i = 0; i < cDefinitionen.length; i++)
			{
				vEndungen.add(cDefinitionen [i] [0]);
			}
		}
		else
		{
			vEndungen.addAll(v_ErlaubteEndungen);
		}
		
		for (int i=0;i<vEndungen.size();i++)
		{
			cInfoStringErlaubteDateisorten += ("*." + vEndungen.get(i));
			if (i < (vEndungen.size() - 1))
			{
				cInfoStringErlaubteDateisorten += " / ";
			}

		}
		
		
		this.oUploadSelect.set_vErlaubtEndungen(vEndungen);

		
		/*
		* jetzt die column fuellen
		*/
		if (!cUeberschrift.trim().equals(""))
		{
			oCol.add(new MyE2_Label(new MyE2_String(this.cUeberschrift), MyE2_Label.STYLE_TITEL_BIG()),new Insets(2,2,2,10));
		}
		
		if (!cInfoText.trim().equals(""))
		{
			oCol.add(new MyE2_Label(new MyE2_String(this.cInfoText,false)),new Insets(2,2,2,10));
		}
		
		oCol.add(new MyE2_LabelWrap(new MyE2_String(cInfoStringErlaubteDateisorten,false)),new Insets(2,2,2,10));
		oCol.add(this.oUploadSelect,new Insets(2,2,2,10));
		
		if (this.oZusatzComponent != null)
		{
			oCol.add(this.oZusatzComponent,new Insets(2,2,2,10));
		}
		
		
	}
 
	
	
	

	public void fileUpload(UploadEvent e)
	{
		MyE2_MessageVector oMV = new  MyE2_MessageVector();
		
		
    	MyString cInfo = null;
    	String   cInfoAddon = "";
    	try
    	{
    		this.oUploadSelect.doSaveFile(e,this.cTABELLE+"_ID_"+this.cID_ZU_Tabelle+"_",true);
    	}
    	catch (myException ex)
    	{
    		if (ex instanceof myExceptionInFileHandling)
    		{
    			cInfo = 		((myExceptionInFileHandling)ex).get_oMessage();
    			cInfoAddon = 	((myExceptionInFileHandling)ex).get_cAdditionalInfo();
    		}
    		else
    			cInfoAddon = ex.get_ErrorMessage().get_cMessage().CTrans();
    		
    	}
    	
    	
    	/*
    	 * information einblenden
    	 */
		if (! bibALL.isEmpty(cInfoAddon) ||   cInfo!=null)
		{
			String cMessage = "";
			
			if (cInfo != null)					cMessage += cInfo.CTrans()+": ";
			if (!bibALL.isEmpty(cInfoAddon))	cMessage += cInfoAddon;
			
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cMessage,false)));
		}
		else
		{
			String cMessage = "Upload OK!";
			
			
			try
			{
	            this.oArchiver.WriteFileInfoToArchiveTable(this.oArchiver.get_cSUB_DIR_IN_Archiv(),
	                            oUploadSelect.get_cStoringFileNameWithEndung_NoPath(),
	                            oUploadSelect.get_cUploadFileNameWithEndung_NoPath(),
	    						"Upload-File",
	    						null,
	    						null,
	    						this.cTABELLE,
	    						this.cID_ZU_Tabelle,
	    						oUploadSelect.get_cEndung(),
	    						Archiver_CONST.MEDIENKENNER.UPLOAD.get_DB_WERT(),
	    						"",
	    						"",
	    						"",
	    						this.cPROGRAMM_KENNER);
	            
	            // die letzten ids in der liste eintragen
	            if (this.oNaviList_Files != null)
	            {
	            	if (this.oArchiver.get_cLastNew_SEQ_ARCHIVMEDIEN() != null)
	            		this.oNaviList_Files.ADD_NEW_ID_TO_ALL_VECTORS(this.oArchiver.get_cLastNew_SEQ_ARCHIVMEDIEN());
	            }
	            
	            
				cMessage +=  " //   Archiveintrag OK!";
				oMV.add_MESSAGE(new MyE2_Info_Message(cMessage));
				
				//2011-07-08: moegliche reaktion, wenn upload ok war
				this.do_actionWhenUpload_was_ok(e);
				
			}
			catch (myException ex)
			{
				cMessage +=  " //   Fehler beim Schreiben des Archiveintrags!";
				oMV.add_MESSAGE(new MyE2_Alarm_Message(cMessage));
				oMV.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
		}
		
		bibMSG.add_MESSAGE(oMV);
		
		new E2_MessageHandler(null).showMessages();
		
	}


	public void invalidFileUpload(UploadEvent e)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		oMV.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Upload ..."));

		bibMSG.add_MESSAGE(oMV);
		
		new E2_MessageHandler(null).showMessages();
	}


	public void windowPaneClosing(WindowPaneEvent arg0)
	{
		
	}

	
	//2011-07-08: methode, zum reagieren, wenn upload geklappt hat
	//ZUM UEBERSCHREIBEN
	public void do_actionWhenUpload_was_ok(UploadEvent e) throws myException
	{
		
	}
	
}
