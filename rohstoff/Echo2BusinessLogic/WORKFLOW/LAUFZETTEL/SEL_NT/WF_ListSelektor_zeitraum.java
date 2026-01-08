package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_SelectField_Zeitraum;


public class WF_ListSelektor_zeitraum extends XX_ListSelektor {
	
	protected 	String[][] 	  						arrZeitraum = null;
	private 	WF_HEAD_LIST_SelectField_Zeitraum  	oSelZeitraum = null;
	
	public WF_ListSelektor_zeitraum() throws myException
	{
		super();
		// Zeitraum in die Zukunft
		arrZeitraum = new String[][]{
				{new MyE2_String("--").CTrans(),""},
				{new MyE2_String("1 Tag").CTrans(),"1"},
				{new MyE2_String("2 Tage").CTrans(),"2"},
				{new MyE2_String("3 Tage").CTrans(),"3"},
				{new MyE2_String("4 Tage").CTrans(),"4"},
				{new MyE2_String("5 Tage").CTrans(),"5"},
				{new MyE2_String("6 Tage").CTrans(),"6"},
				{new MyE2_String("7 Tage").CTrans(),"7"},
				{new MyE2_String("2 Wochen").CTrans(),"14"},
				{new MyE2_String("3 Wochen").CTrans(),"21"},
				{new MyE2_String("4 Wochen").CTrans(),"28"},
				{new MyE2_String("5 Wochen").CTrans(),"35"},
				{new MyE2_String("10 Wochen").CTrans(),"70"},
				{new MyE2_String("20 Wochen").CTrans(),"140"},
				{new MyE2_String("30 Wochen").CTrans(),"210"},
				{new MyE2_String("1 Jahr").CTrans(),"365"},
				{new MyE2_String("unbegrenzt").CTrans(),"9999999"},
				};
		this.oSelZeitraum = new WF_HEAD_LIST_SelectField_Zeitraum(arrZeitraum, null, false);
		this.oSelZeitraum.set_ActiveInhalt_or_FirstInhalt( new MyE2_String("2 Wochen").CTrans() );
		
		this.oSelZeitraum.setWidth(new Extent(250));
		this.oSelZeitraum.setToolTipText(new MyE2_String("Beschränkt die Auswahl auf Laufzettel mit Aufgaben, deren Fälligkeitsdatum <= HEUTE + gewählter Zeitraum in der Zukunft ist!"
														+ " Laufzettel ohne Aufgaben werden nicht angezeigt,"
														+ " Laufzettel, deren Aufgaben alle geschlossen sind, werden nicht angezeigt").CTrans());
	}

	@Override
	public String get_WhereBlock() throws myException
	{
		String cWert = this.oSelZeitraum.get_ActualWert();
		
		if (S.isEmpty(cWert)) {
			return "";                    //selektor verhaelt sich neutral
		} else {
			MyLong l = new MyLong(cWert);
			if (l.get_bOK()) {
				SEL  sel_lz = new SEL().ADDFIELD(LAUFZETTEL_EINTRAG.id_laufzettel).FROM(_TAB.laufzettel_eintrag)
																				  .WHERE(new TermSimple(LAUFZETTEL_EINTRAG.abgeschlossen_am.tnfn()+" IS NULL"))
																				  .AND(new vgl_YN(LAUFZETTEL_EINTRAG.geloescht, false));
				if (l.get_lValue(0l)<=400) {
				   sel_lz.AND(new TermSimple(" NVL(JT_LAUFZETTEL_EINTRAG.FAELLIG_AM,SYSDATE + " + l.get_cUF_LongString() + ") <= (SYSDATE + " + l.get_cUF_LongString() + ")"));
				}
				return " JT_LAUFZETTEL.ID_LAUFZETTEL in (" +sel_lz.s()+")";
			} else {
				throw new myException("Unexpected Selector-Error !!");
			}
		}
	}

	
	@Override
	public Component get_oComponentForSelection()
	{
		MyE2_Grid oGridRueck = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGridRueck.add(this.oSelZeitraum);
		return oGridRueck;
	}

	@Override
	public Component get_oComponentWithoutText()
	{
		return this.get_oComponentForSelection();
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent)
	{
		this.oSelZeitraum.add_oActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck()
	{
		
	}

	
	public WF_HEAD_LIST_SelectField_Zeitraum get_selZeitraum() {
	
		return oSelZeitraum;
	}

}
