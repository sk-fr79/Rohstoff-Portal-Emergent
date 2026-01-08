package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.io.File;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.myTempFile;

public class E2_FileDeleter 
{

	private Vector<myTempFile> vVectorFilenamesToDelete = new Vector<myTempFile>();
	
	public E2_FileDeleter(Vector<myTempFile> vVectorMitKomplettenDateipfaden) 
	{
		super();
		this.vVectorFilenamesToDelete.addAll(vVectorMitKomplettenDateipfaden);
	}

	
	public MyE2_MessageVector execute_DELETE()
	{
		MyE2_MessageVector  oMVRueck = new MyE2_MessageVector();
		
	    for (myTempFile oTempfile: vVectorFilenamesToDelete)
	    {
	        try
	        {
                File oDelFile = new File(oTempfile.getFileName());
                if (oDelFile.exists())  oDelFile.delete();
	                
            }
	        catch (Exception ex)
	        {
	        	ex.printStackTrace();
	        	oMVRueck.add_MESSAGE(new MyE2_Alarm_Message("Es konnten eine temporäre Datei nicht gelöscht werden !!!"));
	        }
	    }

		
		return oMVRueck;
	}
	
}
