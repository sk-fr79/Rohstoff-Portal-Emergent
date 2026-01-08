package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import java.util.Vector;

/***
 * Eintrag in den Baum der die Hierarchie der Laufzettel abbilden soll.
 * Es ist aufgebaut aus
 * - ID 
 * - Tiefe
 * - Vector der nachfolgenden child-TreeEntry-Objekte 
 * @author manfred
 *
 */
public class WF_Tree_Entry
{	
	
	/***
	 * Die ID des aktuellen Knotens
	 */
	String ID ;
	
	/**
	 * die Tiefe des aktuellen Knotens
	 */
	int    Depth ;
	
	/**
	 * Der Vektor der Kinder-Knoten
	 */
	Vector<WF_Tree_Entry> Childs = new Vector<WF_Tree_Entry>();

	/**
	 * @param id
	 * @param depth
	 */
	public WF_Tree_Entry(String id, int depth)
	{
		ID = id;
		Depth = depth;
	}

	public String getID()
	{
		return ID;
	}

	public void setID(String id)
	{
		ID = id;
	}

	public int getDepth()
	{
		return Depth;
	}

	public void setDepth(int depth)
	{
		Depth = depth;
	}

	public Vector<WF_Tree_Entry> getChilds()
	{
		return Childs;
	}

	
	/**
	 * Fügt ein Kind in den Vector ein mit den gegebenen Daten ein.
	 * Der Child-Vektor des neuen Kindes ist leer!
	 * 
	 * @param id
	 * @param depth
	 * @return Das neue TreeEntry-Objekt
	 */
	public WF_Tree_Entry addChild(String id)
	{
		int depth = this.Depth + 1;
		WF_Tree_Entry o = new WF_Tree_Entry(id,depth);
		this.Childs.add(o);
		return o;
	}

	
	/**
	 * Gibt ein Child dieses Knotens zurück, wenn die ID vorahnden ist
	 * @param id
	 * @return
	 */
	public WF_Tree_Entry getNode(String id){

		WF_Tree_Entry oRet = null;
		for (WF_Tree_Entry o: this.getChilds())
		{
			if (o.getID().equals(id))
			{
				oRet = o;
				break;
			}
			else 
			{
				if (o.getChilds().size() > 0)
				{
					oRet = o.getNode(id);
					if(oRet != null)
					{
						break;
					}
				}
			}
		}
		
		return oRet;
	}

	
	/***
	 * löscht rekursiv alle childs aus dem Baum raus.
	 */
	public void removeChilds()
	{
		for (WF_Tree_Entry o: this.getChilds())
		{
			if (o.getChilds().size() > 0)
			{
				o.removeChilds();
			}
			
			o.getChilds().clear();
		}
		
		Childs.clear();
	}


	
}



