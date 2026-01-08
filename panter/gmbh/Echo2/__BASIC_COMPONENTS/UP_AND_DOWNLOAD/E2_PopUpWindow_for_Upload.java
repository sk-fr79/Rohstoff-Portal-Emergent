package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.io.File;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.event.WindowPaneEvent;
import nextapp.echo2.app.filetransfer.UploadEvent;
import nextapp.echo2.app.filetransfer.UploadListener;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWrap;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionInFileHandling;


/*
 * popupfenster zum beliebigen fileupload in ein definiertes verzeichnis 
 */
public abstract class E2_PopUpWindow_for_Upload extends E2_BasicModuleContainer implements UploadListener
{


	public static Insets		INSETSBASIC = new Insets(10,2,2,10);
	
	
    private boolean 			bOverWrite	= 		false;
	private MyString		   	cUeberschrift = 	null;
	private MyString		   	cInfoText = 		null;
	private String 				cPathToStoringDirectory = 			null;
    
    private Component			oZusatzComponent = null;
	
	private E2_UploadSelect 	oUploadSelect	= 	null;
	
    private MyE2_Column         oCol = new MyE2_Column();
    
    private String 				cInfoStringErlaubteDateisorten = "";
     

	/**
	 * @param cPathToStoringdirectory
	 * @param overWrite
	 * @param cAllowedEndings String[n][2] Endung/Beschreibung  [xml,*.xml]
	 * @param ueberschrift
	 * @param infoText
	 * @throws myException
	 */
	public E2_PopUpWindow_for_Upload(	String 				cPathToStoringdirectory,
										boolean 			overWrite, 
										String[][]			cAllowedEndings,
										MyString 			ueberschrift, 
										MyString 			infoText)  throws myException
	{
		super();
		
		this.cPathToStoringDirectory = cPathToStoringdirectory;
		
		if (bibALL.isEmpty(this.cPathToStoringDirectory))
			throw new myException("E2_PopUpWindow_for_Upload:Constructor: Empty Storing-path not allowed !!");

		if (cAllowedEndings == null || cAllowedEndings.length==0)
			throw new myException("E2_PopUpWindow_for_Upload:Constructor: you MUST give an array with correct endings !!");

		
		if (!(new File(cPathToStoringdirectory).exists()))
			throw new myException("E2_PopUpWindow_for_Upload:Constructor: Storing-path is not existing !!");

		
        this.bOverWrite = 			overWrite;
        this.cUeberschrift = 		ueberschrift;
        this.cInfoText = 			infoText;

		this.oUploadSelect = new E2_UploadSelect(this.cPathToStoringDirectory, bOverWrite);
		try
		{
		    this.oUploadSelect.addUploadListener(this);
		}
		catch (Exception ex)
		{
		    throw new myException("GridForFileUpload:Error creating uploader !"+ex.getLocalizedMessage());
		}

		Vector<String> 		vEndungen	= new Vector<String>();

		this.cInfoStringErlaubteDateisorten = bibALL.get_TRANSLATOR().translate("\nErlaubte Dateien zum Upload: ") + "\n";

		for (int i = 0; i < cAllowedEndings.length; i++)
		{
			vEndungen.add(cAllowedEndings [i] [0]);
			cInfoStringErlaubteDateisorten += ("*." + cAllowedEndings [i] [0]);
			if (i < (cAllowedEndings.length - 1))
				cInfoStringErlaubteDateisorten += " / ";

		}
		this.oUploadSelect.set_vErlaubtEndungen(vEndungen);
		
		this.add(this.oCol);
		
		this.buildMask();

		
	}

	
	private void buildMask()
	{
		this.oCol.removeAll();
		
		/*
		 * jetzt die column fuellen
		 */
		if (cUeberschrift != null)
		    oCol.add(new MyE2_Label(this.cUeberschrift, MyE2_Label.STYLE_TITEL_BIG()),E2_PopUpWindow_for_Upload.INSETSBASIC);
		
		if (cInfoText != null)
		    oCol.add(new MyE2_Label(this.cInfoText),E2_PopUpWindow_for_Upload.INSETSBASIC);
		
		oCol.add(new MyE2_LabelWrap(new MyE2_String(cInfoStringErlaubteDateisorten,false)),E2_PopUpWindow_for_Upload.INSETSBASIC);

		if (this.oZusatzComponent != null)
			oCol.add(this.oZusatzComponent,E2_PopUpWindow_for_Upload.INSETSBASIC);

		oCol.add(this.oUploadSelect,E2_PopUpWindow_for_Upload.INSETSBASIC);
		
	}
	
	

	public void fileUpload(UploadEvent e)
	{
		MyE2_MessageVector oMV = new  MyE2_MessageVector();

    	MyString cInfo = null;
    	String   cInfoAddon = "";
    	try
    	{
    		if (this.actionBeforeStoringFile(this.oUploadSelect,e))
    			this.oUploadSelect.doSaveFile(e,null,false);
    		
    		oMV.add_MESSAGE(new MyE2_Info_Message("Upload OK!"));
    		
    		this.actionAfterStoringFile(this.oUploadSelect,e);
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
		
		bibMSG.add_MESSAGE(oMV);

		this.showActualMessages();
		
	}


	public void invalidFileUpload(UploadEvent e)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();

		oMV.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Upload ..."));

		bibMSG.add_MESSAGE(oMV);
		
		this.showActualMessages();

	}


	public void windowPaneClosing(WindowPaneEvent arg0)
	{
		
	}


	// methoden zum ueberschreiben falls noetig
	public abstract boolean actionBeforeStoringFile(E2_UploadSelect UploadSelect, UploadEvent oUploadEvent) throws myException;
	public abstract boolean actionAfterStoringFile(E2_UploadSelect UploadSelect, UploadEvent oUploadEvent) throws myException;

	
	
	public Component get_oZusatzComponent()
	{
		return this.oZusatzComponent;
	}


	public void set_oZusatzComponent(Component zusatzComponent)
	{
		this.oZusatzComponent = zusatzComponent;
		this.buildMask();
	}
	
	
	
}
