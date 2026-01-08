package panter.gmbh.indep;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.basics4project.DEBUG;


public class DebugPrinter
{

	public DebugPrinter(HashMap hmCheck)
	{
		super();
	
		Iterator oIt = hmCheck.entrySet().iterator();
		
		DEBUG.System_println("------------------------------------------------------------------------------------------------------------", "");
		DEBUG.System_println("-----Debug-Printer-Ausgabe --START !!-----------------------------------------------------------------------", "");

		while (oIt.hasNext())
		{
			Map.Entry oEntry = (Map.Entry)oIt.next();
			
			if (oEntry.getKey() instanceof String)
			{
				if (oEntry.getValue() instanceof String)
				{
					DEBUG.System_println((String)oEntry.getKey()+" -> "+(String)oEntry.getValue(), "");
				}
				else
				{
					DEBUG.System_println((String)oEntry.getKey(), "");
				}
			}
			
		}
		DEBUG.System_println("------------------------------------------------------------------------------------------------------------", "");
		DEBUG.System_println("-----Debug-Printer-Ausgabe --ENDE !!------------------------------------------------------------------------", "");
		
		
		
		
	}
	
	
	public DebugPrinter(Vector<String> vCheck)
	{
		super();
	
		Iterator<String> oIt = vCheck.iterator();
		
		DEBUG.System_println("------------------------------------------------------------------------------------------------------------", "");
		DEBUG.System_println("-----Debug-Printer-Ausgabe --START !!-----------------------------------------------------------------------", "");

		while (oIt.hasNext())
		{
			DEBUG.System_println(oIt.next(), "");
		}
		DEBUG.System_println("------------------------------------------------------------------------------------------------------------", "");
		DEBUG.System_println("-----Debug-Printer-Ausgabe --ENDE !!------------------------------------------------------------------------", "");
		
	}

	
}
