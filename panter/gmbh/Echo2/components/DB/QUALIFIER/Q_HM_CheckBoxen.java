package panter.gmbh.Echo2.components.DB.QUALIFIER;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class Q_HM_CheckBoxen extends HashMap<String, Q_DEF_CheckBox>
{

	public Q_HM_CheckBoxen()
	{
		super();
	}


	public Vector<Q_DEF_CheckBox>  get_vSortedVectorCheckboxes()
	{
		//vector zum sortieren erfinden
		Vector<Q_DEF_CheckBox>  vCheckBoxen = new Vector<Q_DEF_CheckBox>();
		
		//jetzt die hm mit den checkboxen durchsuchen und nachsehen, was sich veraendert hat
		Iterator<Q_DEF_CheckBox>  oIter = this.values().iterator();
	    while (oIter.hasNext())
	    {
	    	vCheckBoxen.add(oIter.next());
	    }
	    
	    Collections.sort(vCheckBoxen);

	    return vCheckBoxen;
	}
	
	
	/**
	 * 2014-03-10
	 * @return einen String, wo in der sortierten reihenfolge aus get_vSortedVectorCheckboxes() die schalterzustaende 
	 *         erfasst werden in der definition: "YNNYYN ...")
	 */
	public String get_StatusAnAus_4_ActualMatrix() {
		String cRueck = "";
		
		Vector<Q_DEF_CheckBox> vCB = this.get_vSortedVectorCheckboxes();
		for (int i=0;i<vCB.size();i++) {
			cRueck += (vCB.get(i).isSelected()?"Y":"N");
		}
		
		return cRueck;
	}
	
	/**
	 * 2014-03-10
	 * wiederherstellen einer gespeicherten matrix in der definition: "YNNYYN ..."
	 * @param cStatus
	 */
	public void set_StatusAnAus_4_ActualMatrix(String cStatus) { 
		Vector<Q_DEF_CheckBox> vCB = this.get_vSortedVectorCheckboxes();
		for (int i=0;i<vCB.size();i++) {
			if (cStatus.length()>i) {
				if (cStatus.substring(i, i+1).equals("Y")) {
					vCB.get(i).setSelected(true);
				} else {
					vCB.get(i).setSelected(false);
				}
			}
		}
	}
	
}
