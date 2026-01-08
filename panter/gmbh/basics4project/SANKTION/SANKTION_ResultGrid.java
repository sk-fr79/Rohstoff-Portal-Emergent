/**
 * 
 */
package panter.gmbh.basics4project.SANKTION;

import java.util.HashMap;
import java.util.Map;

import echopointng.Separator;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class SANKTION_ResultGrid extends E2_Grid {

	public SANKTION_ResultGrid(HashMap<String, SANKTION_Ergebnisse> hmErg) throws myException {

		super();

		MyE2_TabbedPane tab = new MyE2_TabbedPane(300);

		this._setSize(610);
		this._a(tab);

		HashMap<ENUM_SANKTION_Ergebnis_typ, Integer> global_counter_map 	= new HashMap<>() ;	
//		HashMap<ENUM_SANKTION_Ergebnis_typ, Integer> hauptadresse_counter_map 	= new HashMap<>() ;	
//		HashMap<ENUM_SANKTION_Ergebnis_typ, Integer> mitarbeiter_counter_map	= new HashMap<>();
//		HashMap<ENUM_SANKTION_Ergebnis_typ, Integer> lieferadresse_counter_map	= new HashMap<>();

		initializeMap(global_counter_map);
//		initializeMap(hauptadresse_counter_map);
//		initializeMap(mitarbeiter_counter_map);
//		initializeMap(lieferadresse_counter_map);

		for(String id_adresse: hmErg.keySet()) {
			SANKTION_Ergebnisse search_result = hmErg.get(id_adresse);

//			Rec21 rec_adresse = new Rec21(_TAB.adresse)._fill_id(id_adresse);
//			int i_adresse_typ = rec_adresse.get_myLong_dbVal(ADRESSE.adresstyp).get_iValue();
			if(search_result.size()>0) {
				for(Map.Entry<String,VEK<SANKTION_Treffer>> ergebnis_entry: search_result.entrySet()) {
					for(SANKTION_Treffer problem_item:ergebnis_entry.getValue()) {

						global_counter_map.put(problem_item.getTreffer_typ(), global_counter_map.get(problem_item.getTreffer_typ())+1);

//						if( i_adresse_typ == myCONST.ADRESSTYP_LIEFERADRESSE) {
//							lieferadresse_counter_map.put(problem_item.getTreffer_typ(), lieferadresse_counter_map.get(problem_item.getTreffer_typ()) + 1);
//						}else if(i_adresse_typ == myCONST.ADRESSTYP_MITARBEITER) {
//							mitarbeiter_counter_map.put(problem_item.getTreffer_typ(), mitarbeiter_counter_map.get(problem_item.getTreffer_typ()) + 1);
//
//						}else if(i_adresse_typ == myCONST.ADRESSTYP_FIRMENINFO) {
//							hauptadresse_counter_map.put(problem_item.getTreffer_typ(), hauptadresse_counter_map.get(problem_item.getTreffer_typ()) + 1);
//						}
					}
				}
			}
		}
		tab.add_Tabb(S.ms("Zusammenfassung"),  fill_grid(global_counter_map));

//		tab.add_Tabb(S.ms("Hauptadresse"),	fill_grid( hauptadresse_counter_map));
//
//		tab.add_Tabb(S.ms("Mitarbeiter"), 	fill_grid( mitarbeiter_counter_map));
//
//		tab.add_Tabb(S.ms("Lieferadresse"), fill_grid( lieferadresse_counter_map));

	}

	private E2_Grid fill_grid(HashMap<ENUM_SANKTION_Ergebnis_typ, Integer> hauptadresse_counter_map) {
		E2_Grid g = new E2_Grid();
		g._setSize(500,100)._a(new RB_lab()._tr("Ergebnis der Adress-Prüfung auf Sanktionseinträge:")._b(), new RB_gld()._span(2)._ins(5, 10, 5, 20));

		g._a("Treffer In", new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorDark())._left_mid());
		g._a("Anzahl",  new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorDark())._right_mid());

		g
		._a(ENUM_SANKTION_Ergebnis_typ.TREFFER_AUSWEIS.user_text()										, new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorBase())._left_mid())
		._a(""+hauptadresse_counter_map.get(ENUM_SANKTION_Ergebnis_typ.TREFFER_AUSWEIS).intValue()		, new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorBase())._right_mid())
		._a(ENUM_SANKTION_Ergebnis_typ.TREFFER_ADRESSE.user_text()										, new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorBase())._left_mid())
		._a(""+hauptadresse_counter_map.get(ENUM_SANKTION_Ergebnis_typ.TREFFER_ADRESSE).intValue()		, new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorBase())._right_mid())
		._a(ENUM_SANKTION_Ergebnis_typ.TREFFER_NAME.user_text()											, new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorBase())._left_mid())
		._a(""+hauptadresse_counter_map.get(ENUM_SANKTION_Ergebnis_typ.TREFFER_NAME).intValue()			, new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorBase())._right_mid())
		._a(new Separator(), new RB_gld()._ins(0,2,0,2)._span(2))
		._a(ENUM_SANKTION_Ergebnis_typ.FEHLER_BEI_PRUEFUNG.user_text()									, new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorBase())._left_mid())
		._a(""+hauptadresse_counter_map.get(ENUM_SANKTION_Ergebnis_typ.FEHLER_BEI_PRUEFUNG).intValue()	, new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorBase())._right_mid())
		._a(ENUM_SANKTION_Ergebnis_typ.OK.user_text()													, new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorBase())._left_mid())
		._a(""+hauptadresse_counter_map.get(ENUM_SANKTION_Ergebnis_typ.OK).intValue()					, new RB_gld()._span(1)._ins(5, 4, 5, 5)._col(new E2_ColorBase())._right_mid())
		;

		return g;
	}

	private void initializeMap(HashMap<ENUM_SANKTION_Ergebnis_typ, Integer> oMap) throws myException{
		for(ENUM_SANKTION_Ergebnis_typ e: ENUM_SANKTION_Ergebnis_typ.values()) {
			oMap.put(e, 0);
		}
	}
	/**
	 * interface zum generieren der componente, die ids erzeugt
	 * @author martin
	 *
	 */
	public static interface SanktionComponentGenerator {
		public Component gen(VEK<String> vek) throws myException;
	}
}
