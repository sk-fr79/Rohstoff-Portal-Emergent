/**
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List.ExportSql;

import java.util.Vector;

import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_HelpPopUp;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.TERM.INSERT.INSERT;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class EXP_portSqlFromList {
	
	private _TAB tab = null;

	private Vector<EXP_portDef>   vExportDef = new Vector<EXP_portDef>();
	
	
	/**
	 * @param p_tab
	 * @throws myException 
	 */
	public EXP_portSqlFromList(_TAB p_tab) throws myException {
		super();
		this.tab = p_tab;
		
		for (IF_Field f: this.tab.get_fields()) {
			vExportDef.add(new EXP_portDef(EXP_ENUM_EVALTYP.FIELDVAL, f, "#"+f.fn()+"#"));
		}
		
	}
	
	
	
	public E2_Grid getExpDefGrid() throws myException {
		
		E2_Grid g = new E2_Grid();
		
		g._setSize(20,200,100,600);
		
		g._a(new RB_lab()._tr("Exportprofil für die Tabelle: "+tab.fullTableName())._fo_s_plus(2)._b(), new RB_gld()._span(4)._ins(2, 2, 2, 20));
		
		g	._a(new RB_lab(S.ms("Aktiv"))._i(), 	new RB_gld()._ins(2)._colD())
			._a(new RB_lab(S.ms("Feldname"))._i(), 	new RB_gld()._ins(2)._colD())
			._a(new E2_Grid()	._a(new RB_lab(S.ms("Typ"))._i())
								._a(new ownHelp4Type())
								,new RB_gld()._ins(2)._colD())
			._a(new E2_Grid()	._a(new RB_lab(S.ms("Inhalt"))._i())
										
								, 	new RB_gld()._ins(2)._colD());
		
		for (EXP_portDef  def: vExportDef) {
			
			g	._a(def.getCbAktiv(), 		new RB_gld()._ins(2))
				._a(def.getFieldLabel(), 	new RB_gld()._ins(2))
				._a(def.getSelType(), 		new RB_gld()._ins(2))
				._a(def.getDefText(), 		new RB_gld()._ins(2))
				;
		}
		return g;
	}

	
	

	public String generateExportString(Long id) throws myException {
		
		Rec20  r = new Rec20(this.tab)._fill_id(id);
		
		INSERT ins = new INSERT(this.tab);
		
		for (EXP_portDef def: this.vExportDef) {
			if (def.isAktiv()) {
				ins._addRawPair (def.getField(), def.interpretText(r));
			}
		}
		
		return ins.s();
	}
	
	
	
	
	public EXP_portSqlFromList _saveUserSettings() throws myException {
		
		for (EXP_portDef def: this.vExportDef) {
			def.saveUserSettings();
		}

		
		return this;
	}
	
	
	
	private class ownHelp4Type extends E2_HelpPopUp {

		public ownHelp4Type() {
			super();
			this._setWidth(600)
			._addTextLine("Infos zu Export-Feld-Typen:")
			._addTextAbschnitt(-2, "Feldwert:", "Dabei wird ein einfacher Feldwert aus der Datenbank gelesen und in das INSERT-Statements eingefügt werden")
			._addTextAbschnitt(-2, "Abfrage:", "Eine Abfrage, deren Ergebnis als String in den INSERT-Statement eingefügt wird (kann #WERT# - Felder aus der Quell-Tabelle enthalten")
			._addTextAbschnitt(-2, "Konstante:", "Eine simple Konstante, die in die INSERT-Statements eingebettet wird (muss korrekt formatiert sein: Bsp: 10.23 oder 'Meier' oder TO_DATE('2017-03-01','YYYY-MM-DD')")
			;
			
		}
		
	}
	
	
}
