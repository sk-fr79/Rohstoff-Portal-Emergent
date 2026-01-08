/**
 * 
 */
package panter.gmbh.basics4project.GEOCODIERUNG;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class GEO_CodingResultGrid extends E2_Grid {

	/**
	 * @throws myException 
	 * 
	 */
	public GEO_CodingResultGrid(ComponentGenerator generator, GEO_ErrorMap... map) throws myException {
		super();
		
		if (map.length==1) {
			this._setSize(500,100)._a(new RB_lab()._tr("Ergebniss der Geocodierung: ")._b(), new RB_gld()._span(2)._ins(5, 10, 5, 20));
			
			this._a("Bereich", new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorDark())._left_mid());
			this._a("Anzahl",  new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorDark())._right_mid());
			
			for (ENUM_GEO_Error e: ENUM_GEO_Error.values()) {
				this._a(S.ms(e.user_text()),  new RB_gld()._span(1)._ins(5, 4, 5, 5)._left_mid());
				Component c = generator.gen(map[0].get(e));
				this._a(c,  new RB_gld()._span(1)._ins(5, 4, 5, 5)._right_mid());
			}
		} else {
			//dann ein tabbed pane mit allen grids
			this._setSize(610);
			MyE2_TabbedPane tab = new MyE2_TabbedPane(300);
			this._a(tab);
			for (GEO_ErrorMap m: map) {
				E2_Grid g = new E2_Grid();
				g._setSize(500,100)._a(new RB_lab()._tr("Ergebniss der Geocodierung: ")._b(), new RB_gld()._span(2)._ins(5, 10, 5, 20));
				
				g._a("Bereich", new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorDark())._left_mid());
				g._a("Anzahl",  new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorDark())._right_mid());
				
				for (ENUM_GEO_Error e: ENUM_GEO_Error.values()) {
					g._a(S.ms(e.user_text()),  new RB_gld()._span(1)._ins(5, 4, 5, 5)._left_mid());
					Component c = generator.gen(m.get(e));
					g._a(c,  new RB_gld()._span(1)._ins(5, 4, 5, 5)._right_mid());
				}
				
				tab.add_Tabb(S.ms(m.getBeschreibung()), g);
			}
		}
	}

	
	/**
	 * interface zum generieren der componente, die ids erzeugt
	 * @author martin
	 *
	 */
	public static interface ComponentGenerator {
		public Component gen(VEK<String> vek) throws myException;
	}
	
	
}
