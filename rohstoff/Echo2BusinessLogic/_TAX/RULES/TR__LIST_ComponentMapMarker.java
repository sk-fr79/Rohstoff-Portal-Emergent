/**
 * rohstoff.Echo2BusinessLogic._TAX.RULES
 * @author martin
 * @date 21.02.2019
 * 
 */
package rohstoff.Echo2BusinessLogic._TAX.RULES;

import java.util.Collection;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.basics4project.DB_ENUMS.HANDELSDEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 21.02.2019
 *
 */
public class TR__LIST_ComponentMapMarker extends E2_ComponentMapMarker {

	/**
	 * @author martin
	 * @date 21.02.2019
	 *
	 * @param p_map
	 */
	public TR__LIST_ComponentMapMarker(E2_ComponentMAP p_map) {
		super(p_map);
	}

	@Override
	protected void innerFormat(Collection<Component> v) {
		super.innerFormat(v);
		
		E2_ComponentMAP map = this.getMap();
		
		try {
			Rec21 recHD = new Rec21(_TAB.handelsdef)._fill_id(map.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			
			if (!recHD.is_yes_db_val(HANDELSDEF.aktiv)) {
				this.setLayoutBackgroundColorInMap(v,new Color(230,230,230));
				this.setTextColorInMap(v,Color.DARKGRAY);
			}
			
		} catch (myException e) {
			e.printStackTrace();
		}
		
	}

	
	
	
	
}
