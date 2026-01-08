package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ATOMBUILD;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.Basic_PluginColumn;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DEBUG.DEBUG_FLAGS;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.KORRE_ABZUEGE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.Nvl;
import panter.gmbh.indep.dataTools.TERM.Plus;
import panter.gmbh.indep.dataTools.TERM.SetList;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class AA_korrigiere_fehlbuchungen extends XX_ActionAgent {

	/**
	 * @author manfred
	 * @date 07.10.2016
	 *
	 */
	public AA_korrigiere_fehlbuchungen(Basic_PluginColumn container) {
		_container = container; 
	}
	private Basic_PluginColumn _container;
	
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		
		
		new E2_ServerPushMessageContainer(new Extent(500),new Extent(200),new MyE2_String("Korrektur ..."),true,false,true,1000) {
			@Override
			public void Run_Loop() throws myException 	{
				
				SetList pairs = new SetList();
				pairs	._addPair(BEWEGUNG_ATOM.menge_netto.t("ba"), 				new Plus(new Nvl(BEWEGUNG_ATOM.menge_netto.t("ba"),"0"), new Nvl(BEWEGUNG_ATOM.abzug_menge.t("ba"),"0")).in_brackets())
						._addPair(BEWEGUNG_ATOM.abzug_menge.t("ba"), 				"0")
						._addPair(BEWEGUNG_ATOM.gpreis_abz_mge.t("ba"), 			"0")
						._addPair(BEWEGUNG_ATOM.gpreis_abz_auf_nettomge.t("ba"), 	new Plus(	new Nvl(BEWEGUNG_ATOM.gpreis_abz_auf_nettomge.t("ba"), 	"0"),
																								new Nvl(BEWEGUNG_ATOM.gpreis_abz_mge.t("ba"), 			"0")))
						._addPair(BEWEGUNG_ATOM.e_preis_result_netto_mge.t("ba"), 	
								new TermSimple("( (nvl(ba.GESAMTPREIS,0)-nvl(ba.GPREIS_ABZ_AUF_NETTOMGE,0)-nvl(ba.GPREIS_ABZ_MGE,0)) / (  (nvl(ba.menge_netto,0)+nvl(ba.abzug_menge,0)) /  (select a.mengendivisor from jt_artikel a where a.id_artikel=ba.id_artikel) ))"))
						._addPair(BEWEGUNG_ATOM.e_preis_result_brutto_mge.t("ba"), 	
								new TermSimple("( (nvl(ba.GESAMTPREIS,0)-nvl(ba.GPREIS_ABZ_AUF_NETTOMGE,0)-nvl(ba.GPREIS_ABZ_MGE,0)) / (  (nvl(ba.menge_netto,0)+nvl(ba.abzug_menge,0)) /  (select a.mengendivisor from jt_artikel a where a.id_artikel=ba.id_artikel) ))"))
						;
		
				//DEBUG.System_println("----"+pairs.s());
				
				String update = "UPDATE "+BEWEGUNG_ATOM.T("ba").s()+ 
						" SET "+
						pairs.s()+
						" WHERE "+
						new  And(BEWEGUNG_ATOM.abzug_menge.t("ba"),COMP.GT, new TermSimple("0"))
							.and(new Plus(new Nvl(BEWEGUNG_ATOM.menge_netto.t("ba"),"0"),new Nvl(BEWEGUNG_ATOM.abzug_menge.t("ba"), "0")).in_brackets(),COMP.GT, "0").s();
				
				
				DEBUG.System_println(update,true);
				
				
				int iCountKorreSorten = 0;

				SEL sorten = new SEL(ARTIKEL.anr1,KORRE_ABZUEGE.id_artikel,KORRE_ABZUEGE.korre_aelter_als).FROM(_TAB.korre_abzuege).INNERJOIN(_TAB.artikel, ARTIKEL.id_artikel, KORRE_ABZUEGE.id_artikel).ADD_Distinct().ORDERUP(ARTIKEL.anr1);
				
				String[][] zu_korrigeren = bibDB.EinzelAbfrageInArray(sorten.s(),"");
				
				if (zu_korrigeren==null) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei Abfrage der Korrekturliste")));
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(sorten.s()));
				} else {

					iCountKorreSorten = zu_korrigeren.length;
					
					MyE2_MessageVector mv = new MyE2_MessageVector();
					
					int iCount=0;
					int iCount_updates = 0;
					for (String[] zeile: zu_korrigeren) {
						String anr1=zeile[0];
						String id_artikel=zeile[1];
						String bis_wann=zeile[2];
						
						String sql = update+" AND "+new And(BEWEGUNG_ATOM.id_artikel.t("ba"),COMP.EQ, new TermSimple(id_artikel))
														.and(new TermSimple("TO_CHAR("+BEWEGUNG_ATOM.leistungsdatum.fn("ba")+",'YYYY-MM-DD')"),COMP.LE,new TermSimple("'"+bis_wann+"'")).s();
						
					
						Vector<Integer> v_zaehler = new Vector<>();
						
						mv.add_MESSAGE(bibDB.ExecMultiSQLVector(bibVECTOR.get_Vector(sql), v_zaehler, true));
						
						if (v_zaehler.size()>0) {
							iCount_updates+=v_zaehler.get(0).intValue();
						}
						
						DEBUG.System_println(sql, DEBUG_FLAGS.MARTINS_EIGENER.name());
						
						
						if (iCount % 10 == 0) {  
							//fortschrittsmeldungen
							MyE2_Grid oG = this.get_oGridBaseForMessages();
							oG.removeAll();
							oG.setSize(6);
							oG.setStyle(MyE2_Grid.STYLE_GRID_DDARK_BORDER());
							oG.add(new MyE2_Label("Ich bearbeite ANR1: "), E2_INSETS.I(5,2,5,2));
							oG.add(new MyE2_Label(anr1), E2_INSETS.I(5,2,5,2));
							oG.add(new MyE2_Label("Bereits upgedated:"), E2_INSETS.I(15,2,5,2));
							oG.add(new MyE2_Label(""+iCount_updates), E2_INSETS.I(5,2,5,2));
							oG.add(new MyE2_Label("Sorte "), E2_INSETS.I(15,2,5,2));
							oG.add(new MyE2_Label(""+iCount+" / "+iCountKorreSorten), E2_INSETS.I(5,2,5,2));
	
						}
						

						if (this.get_bIsInterupted()) {
							break;
						}
						iCount++;
					}
					
				}
				
			}
				

			@Override
			public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane)	throws myException {
						
			}
			
		};	
	}
}