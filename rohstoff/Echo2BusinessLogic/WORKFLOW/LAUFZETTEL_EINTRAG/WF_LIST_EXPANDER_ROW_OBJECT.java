package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

/***
 * Klasse wird verwendet um die Laufzettel-Einträge und deren Hierarchiestufe abzubilden, die einem Laufzettel zugeordnet sind.
 * 
 * @author manfred
 * @date 11.12.2008
 */
public class WF_LIST_EXPANDER_ROW_OBJECT
{
	String id_Laufzettel_Eintrag = "";
	int depth = 0;
	
	/**
	 * @return the id_Laufzettel_Eintrag
	 */
	public String getId_Laufzettel_Eintrag()
	{
		return id_Laufzettel_Eintrag;
	}
	/**
	 * @param id_Laufzettel_Eintrag the id_Laufzettel_Eintrag to set
	 */
	public void setId_Laufzettel_Eintrag(String id_Laufzettel_Eintrag)
	{
		this.id_Laufzettel_Eintrag = id_Laufzettel_Eintrag;
	}
	/**
	 * @return the depth
	 */
	public int getDepth()
	{
		return depth;
	}
	/**
	 * @param depth the depth to set
	 */
	public void setDepth(int depth)
	{
		this.depth = depth;
	}
	
	
	
}
