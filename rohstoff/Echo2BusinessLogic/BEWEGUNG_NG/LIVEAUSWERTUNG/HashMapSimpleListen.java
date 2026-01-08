package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LIVEAUSWERTUNG;

import java.util.HashMap;
import java.util.Vector;

public class HashMapSimpleListen extends HashMap<String, SimpleListenSpalte>
{
	private Vector<String>  vSorter = new Vector<String>();
	
	public HashMapSimpleListen()
	{
		super();
	}
	public void ADD(String cFieldName, String BeschreibungsText, int SpaltenBreite)
	{
		this.put(cFieldName, new SimpleListenSpalte(cFieldName, BeschreibungsText, SpaltenBreite, null));
		this.vSorter.add(cFieldName);
	}

	public void ADD(String cFieldName, String BeschreibungsText, int SpaltenBreite, String ToolTips)
	{
		this.put(cFieldName, new SimpleListenSpalte(cFieldName, BeschreibungsText, SpaltenBreite, ToolTips));
		this.vSorter.add(cFieldName);
	}

	public Vector<String> get_vSorter()
	{
		return vSorter;
	}

}
