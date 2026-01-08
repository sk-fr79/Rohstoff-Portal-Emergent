package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.specialViews.build_flat_atom;
import panter.gmbh.basics4project.specialViews.build_table_JD_DATUM;
import panter.gmbh.basics4project.specialViews.build_view_DIVERSE;
import panter.gmbh.basics4project.specialViews.build_view_FIRMENADRESSEN_FLACH;
import panter.gmbh.basics4project.specialViews.build_view_FuhrenEinfacherBewegungssatz;
import panter.gmbh.basics4project.specialViews.build_view_FuhrenKlassifizierung;
import panter.gmbh.basics4project.specialViews.build_view_FuhrenPreisInfos;
import panter.gmbh.basics4project.specialViews.build_view_Fuhren_und_Orte;
import panter.gmbh.basics4project.specialViews.build_view_Fuhren_und_Orte_REALMENGEN;
import panter.gmbh.basics4project.specialViews.build_view_MATSPEZ_4_SELECTION;
import panter.gmbh.basics4project.specialViews.build_view_Real_GUTSCHRIFT_RECHNUNG_POS;
import panter.gmbh.basics4project.specialViews.build_view_Sonderlager;
import panter.gmbh.basics4project.specialViews.build_view_Sonderlager_2;
import panter.gmbh.basics4project.specialViews.build_view_Sonderlager_Rechnungen;
import panter.gmbh.basics4project.specialViews.build_view_Sonderlager_Rechnungen_2;
import panter.gmbh.basics4project.specialViews.build_view_Transportkosten;
import panter.gmbh.basics4project.specialViews.build_view_lager_in_out_mengen_preis_vergleich;
import panter.gmbh.basics4project.specialViews.Views_4_tempTables.build_view_ListDefined_With_Globale_TempTables;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class GeneratorAction_BuildAllSpecialViews extends XX_ActionAgent implements GeneratorAction__IF {

	private Vector<String>  v_meldungen_ok = new Vector<String>();
	private Vector<String>  v_meldungen_fehler = new Vector<String>();
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		try {
			
			// Tabelle mit den Datumswerten neu aufbauen
			new build_table_JD_DATUM().build_View_forAll_Mandants();
			
			new build_view_Fuhren_und_Orte().build_View_forAll_Mandants();
			new build_view_Fuhren_und_Orte_REALMENGEN().build_View_forAll_Mandants();
			new build_view_Transportkosten().build_View_forAll_Mandants();
			new build_view_FuhrenKlassifizierung().build_View_forAll_Mandants();
			new build_view_FuhrenPreisInfos().build_View_forAll_Mandants();
			new build_view_FuhrenEinfacherBewegungssatz().build_View_forAll_Mandants();
			
			//2012-10-19: zwei weitere views: alle rechnungspositionen eines mandanten ohne DELETED und stornierte
			new build_view_Real_GUTSCHRIFT_RECHNUNG_POS().build_View_forAll_Mandants();
			
			//2011-08-15: neuer view lager-in-out-vergleich
			new build_view_lager_in_out_mengen_preis_vergleich().build_View_forAll_Mandants();
			
			// 2011-10-26  mandantenübergreifende Views für die Sonderlager und Rechnungen
			new build_view_Sonderlager().build_View_forAll_Mandants();
			new build_view_Sonderlager_Rechnungen().build_View_forAll_Mandants();
			
			// 2019-01-14 mandanteübergreifende Views für die Sonderlager und Rechnengen V2
			new build_view_Sonderlager_2().build_View_forAll_Mandants();
			new build_view_Sonderlager_Rechnungen_2().build_View_forAll_Mandants();
			
			
			//2012-03-06 mandantenübergreifende View für flache Firmenadressen 
			new build_view_FIRMENADRESSEN_FLACH().build_View_forAll_Mandants();
			
			//2015-04-16: weitere mandanten-views 
			new build_view_DIVERSE().build_View_forAll_Mandants();
			
			
			// 2012-12-12: Views für die Bewegungssätze generieren
//		new build_view_BEWEGUNG_FLACH().build_View_forAll_Mandants();
			
			//2013-04-12: materialspezifikations-View fuer die selektion
			new build_view_MATSPEZ_4_SELECTION().build_View_forAll_Mandants();
			
			//2017-05-05: spezielle flache tabelle der atom mit allen bewegungstabellen nach oben bauen
			new build_flat_atom().build_View_forAll_Mandants();
			
			//neue views fuer die handlichere benutzung der neuen bewegungs-struktur (Stichwort: atom)
			Vector<String> vViewFiles = bibALL.get_Vector(	"S0_WE_ATOM",
															"S0_WE_MONATS_SUM",
															"S0_WA_ATOM",
															"S0_WA_MONATS_SUM",
															"S0_WE_RECHPOS_MONAT_SUM",
															"S0_WA_RECHPOS_MONAT_SUM");
			vViewFiles.add("S0_KOSTEN_AUS_FUHREN");
			vViewFiles.add("S0_STATISTIK_LIEFERBED");
			
			
			HashMap<String, String> hmViewFiles = new HashMap<String, String>();
			hmViewFiles.put("S0_WE_ATOM", 			"S#MANDANT#_WE_ATOM");
			hmViewFiles.put("S0_WE_MONATS_SUM", 	"S#MANDANT#_WE_MONATS_SUM");
			hmViewFiles.put("S0_WA_ATOM", 			"S#MANDANT#_WA_ATOM");
			hmViewFiles.put("S0_WA_MONATS_SUM", 	"S#MANDANT#_WA_MONATS_SUM");
			hmViewFiles.put("S0_WE_RECHPOS_MONAT_SUM", "S#MANDANT#_WE_RECHPOS_MONAT_SUM");
			hmViewFiles.put("S0_WA_RECHPOS_MONAT_SUM", "S#MANDANT#_WA_RECHPOS_MONAT_SUM");
			
			hmViewFiles.put("S0_KOSTEN_AUS_FUHREN", "S#MANDANT#_KOSTEN_AUS_FUHREN");
			hmViewFiles.put("S0_STATISTIK_LIEFERBED", "S#MANDANT#_STATISTIK_LIEFERBED");

			new build_view_ListDefined_With_Globale_TempTables(hmViewFiles,vViewFiles).build_View_forAll_Mandants();
			
		} catch (myException mex) {
			mex.printStackTrace();
			 
			bibMSG.add_MESSAGE(mex);
		}
		
		MyE2_MessageVector MV_Infos = bibMSG.MV().get_InfoMessages();
		MyE2_MessageVector MV_Alarm = bibMSG.MV().get_AlarmMessages();
		MyE2_MessageVector MV_Warning = bibMSG.MV().get_WarnMessages();

		for (MyE2_Message m: MV_Infos) {
			this.v_meldungen_ok.add(m.get_cMessage().CTrans());
		}
		for (MyE2_Message m: MV_Warning) {
			this.v_meldungen_ok.add(m.get_cMessage().CTrans());
		}
		for (MyE2_Message m: MV_Alarm) {
			this.v_meldungen_fehler.add(m.get_cMessage().CTrans());
		}

		bibMSG.MV().clear();
		
	}


	@Override
	public Vector<String> get_v_meldungen_ok() {
		return v_meldungen_ok;
	}

	@Override
	public Vector<String> get_v_meldungen_fehler() {
		return v_meldungen_fehler;
	}


	@Override
	public MyE2_String get_description() {
		return new MyE2_String("Spezielle Views ...");
	}

	
}
