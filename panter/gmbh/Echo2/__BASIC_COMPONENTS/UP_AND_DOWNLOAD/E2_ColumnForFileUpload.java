package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.filetransfer.UploadEvent;
import nextapp.echo2.app.filetransfer.UploadListener;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.E2_MessageHandler;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionInFileHandling;

/**
 * kleines grid, das als uploader benutzt werden kann, wenn zu einer liste standard-upload-buttons vorhanden sind,
 * es kann ueberall, wo noetig, eingeblendet werden
 */
public class E2_ColumnForFileUpload extends MyE2_Column implements UploadListener
{
     
    private boolean 			bOverWrite	= 		false;
	private E2_UploadSelect 	oUploadSelect	= 	null;
    private Component			oZusatzComponent = null;
    
    private XX_ActionAgent		ActionAfterUpload = 	null;
    
    public E2_ColumnForFileUpload(	String 				CompleteUploadPath_WithSeparators, 
                    				boolean 			overWrite, 
                    				Component 			ozusatzComponent,
                    				Vector<String>		vEndungen)  throws myException
    {
        super();  
        this.bOverWrite = 			overWrite;
        this.oZusatzComponent = 	ozusatzComponent;
        
		this.oUploadSelect = new E2_UploadSelect(CompleteUploadPath_WithSeparators, bOverWrite);
		try
		{
		    this.oUploadSelect.addUploadListener(this);
		}
		catch (Exception ex)
		{
		    throw new myException("GridForFileUpload:Error creating uploader !"+ex.getLocalizedMessage());
		}
		
		
		Vector<String> vvEndungen	= new Vector<String>();
		/*
		 * jetzt checken, welche endungen erlaubt sind !!!
		 */
		if (vEndungen == null)
		{
			
			String[][] cDefinitionen = bibDB.EinzelAbfrageInArray("SELECT DATEIENDUNG,BESCHREIBUNG FROM " + bibALL.get_TABLEOWNER() + ".JT_MEDIENTYP order by DATEIENDUNG", "@@@");
			
			if ((cDefinitionen == null) || (cDefinitionen.length == 0))
			{
				// dann error-vorgaben schreiben
				cDefinitionen = new String[1][2];
				cDefinitionen[0][0]="ERR";
				cDefinitionen[0][1]="Fehler->Keine Dateiendungen";
			}
			else
			{
				for (int i=0;i<cDefinitionen.length;i++)
				{
					vvEndungen.add(cDefinitionen[i][0]);
				}
			}
		}
		else
		{
			vvEndungen.addAll(vEndungen);
		}
		
		
		String cInfoStringErlaubteDateisorten = bibALL.get_TRANSLATOR().translate("\nErlaubte Dateien zum Upload: ") + "\n";

		for (int i = 0; i < vvEndungen.size(); i++)
		{
			cInfoStringErlaubteDateisorten += ("*." + (String)vvEndungen.get(i));
			if (i < (vvEndungen.size()- 1))
				cInfoStringErlaubteDateisorten += " / ";

		}
		this.oUploadSelect.set_vErlaubtEndungen(vvEndungen);

		this.add(new MyE2_Label(new MyE2_String(cInfoStringErlaubteDateisorten,false)));
		this.add(this.oUploadSelect);
		if (this.oZusatzComponent != null)
		{
			this.add(this.oZusatzComponent);
		}
    }

    
    
    public void fileUpload(UploadEvent e)
    {
    	MyE2_MessageVector oMV = new MyE2_MessageVector();
    	try
    	{
    		this.oUploadSelect.doSaveFile(e,null,false);
    		MyE2_String cInfo = new MyE2_String("Datei hochgeladen: ");
    		cInfo.addUnTranslated(e.getFileName());
    		oMV.add_MESSAGE(new MyE2_Info_Message_OT(cInfo.CTrans()));
    		
    		if (this.ActionAfterUpload != null)
    			this.ActionAfterUpload.ExecuteAgentCode(new ExecINFO_OnlyCode());
    	}

    	catch (myExceptionInFileHandling ex)
    	{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(ex.get_oMessage().CTrans(),false)));
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(ex.get_cAdditionalInfo(),false)));
    	}
    	
    	catch (myException ex)
    	{
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    		oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(ex.getLocalizedMessage(),false)));
    	}

    	bibMSG.add_MESSAGE(oMV);
    	
    	new E2_MessageHandler(null).showMessages();
    }


    public void invalidFileUpload(UploadEvent arg0)
    {
       	MyE2_MessageVector oMV = new MyE2_MessageVector();
        
       	oMV.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Upload!"));
       	oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(arg0.getFileName(),false)));
		
       	bibMSG.add_MESSAGE(oMV);
    	new E2_MessageHandler(null).showMessages();
    	       	
    }


	public XX_ActionAgent get_ActionAfterUpload()
	{
		return ActionAfterUpload;
	}

	public void set_ActionAfterUpload(XX_ActionAgent actionAfterUpload)
	{
		ActionAfterUpload = actionAfterUpload;
	}
    
	
	public String get_cStoredFileNameWithEndig()
	{
		return this.oUploadSelect.get_cStoragePathAndFileNameWithEnding();
	}

	
}
