package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.FIBU;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAHNUNG_POS;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.FIBU.RECORD_FIBU_ext;
import rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN2.FIBU_LIST_BT_Sachbearbeiter_Validator;

public class FIBU_MAHNUNG_Button extends MyE2_Button {

	private E2_NavigationList oNaviList;

	private MyE2_MessageVector omv = new MyE2_MessageVector();


	
	public FIBU_MAHNUNG_Button(E2_NavigationList   naviList) throws myException {
		super(E2_ResourceIcon.get_RI("mahnung_new.png"), true);

		this.oNaviList = naviList;

		this._ttt(new MyE2_String("Mahnung-Neu, freie Belegauswahl für die Mahnungen"));

		this.add_GlobalAUTHValidator_AUTO("MAHNUNG_ERSTELLEN");

		this.add_GlobalValidator(new SelectionValidation());

		this.add_GlobalValidator(new FIBU_LIST_BT_Sachbearbeiter_Validator());

		this.add_GlobalValidator(new AdresseValidation());

		this.add_GlobalValidator(new WaehrungValidator());

		this.add_GlobalValidator(new BuchungsTypValidation());

		this.add_GlobalValidator(new ownSummeValidator());

		this.add_oActionAgent(new ownActionAgent());

	}


	private class SelectionValidation extends XX_ActionValidator_NG{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			omv.clear();
			if(oNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only().size()==0){
				omv.add(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie an mindestens einen Beleg/eine Zeile aus der Fibu-Liste")));
			}
			return omv;
		}

	}

	private class AdresseValidation extends XX_ActionValidator_NG{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			VectorSingle idaddressen = new VectorSingle();

			for(String id: oNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only()){
				idaddressen.add(new RECORD_FIBU(id).get_ID_ADRESSE_cUF_NN(""));
			}

			if(idaddressen.size()>1){
				omv.add(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie Belege von genau einer Firma aus!")));
			}
			return omv;
		}		
	}

	private class BuchungsTypValidation extends XX_ActionValidator_NG{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {

			boolean typValid = false;

			for(String id:oNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only()){
				RECORD_FIBU recordFibu = new RECORD_FIBU(id);
				if(recordFibu.is_BUCHUNG_GESCHLOSSEN_NO()){
					if(recordFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_GUTSCHRIFT)){
						typValid = true;
					}else if(recordFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_DRUCK_RECHNUNG)){
						typValid = true;
					}else if(recordFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_ZAHLUNGSEINGANG)){
						typValid = true;
					}else if(recordFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_ZAHLUNGSAUSGANG)){
						typValid = true;
					}else if(recordFibu.get_BUCHUNGSTYP_cUF_NN("").equals(myCONST.BT_SCHECKDRUCK)){
						typValid = true;
					}
				}
				if(! typValid){
					omv.add(new MyE2_Alarm_Message(new MyE2_String("Gemahnt werden können nur NICHT GESCHLOSSENE und nicht AUSGEGLICHENE Belege vom Typ RECHNUNG mit POSITIVEM Wert oder GUTSCHRIFT mit negativem Wert!")));
				}
			}
			return omv;
		}
	}

	private class WaehrungValidator extends XX_ActionValidator_NG{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {

			VectorSingle idaddressen = new VectorSingle();

			for(String id:oNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only()){
				RECORD_FIBU recordFibu = new RECORD_FIBU(id);
				idaddressen.add(recordFibu.get_WAEHRUNG_FREMD_cUF_NN(""));
			}
			return omv;
		}

	}

	private class ownSummeValidator extends XX_ActionValidator_NG{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			VectorSingle buchungsBlockList = new VectorSingle();


			for(String id:oNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only()){
				RECORD_FIBU recordFibu = new RECORD_FIBU(id);
				buchungsBlockList.add(recordFibu.get_BUCHUNGSBLOCK_NR_cUF_NN(""));
			}

			if(buchungsBlockList.size()>1){

			}else{

				BigDecimal summe = new BigDecimal(0.00);
				BigDecimal BD0 = new BigDecimal(0);

				for(String id:oNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only()){
					RECORD_FIBU recordFibu = new RECORD_FIBU(id);
					BigDecimal faktor = recordFibu.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BD0);
					summe = summe.add(faktor.multiply(recordFibu.get_ENDBETRAG_FREMD_WAEHRUNG_bdValue(BD0)));
				}

				if(summe.signum()==-1){
					omv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es kann nur gemahnt werden, wenn die Summe des Buchungsblocks eine Forderung ergibt !")));
				}
			}

			return omv;
		}

	}

	private class ownBasicModuleContainerWaehleMahnungAus extends E2_BasicModuleContainer
	{
		public ownBasicModuleContainerWaehleMahnungAus() throws myException 
		{
			super();
			
			RECLIST_FIBU recListFibuMahnbar = new RECLIST_FIBU();
			
			int iMahnStuffe = 0;
			
			for(String idFibu: oNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only()){
				RECORD_FIBU_ext recFibu = new RECORD_FIBU_ext(idFibu);
				if(recFibu.is_BUCHUNG_GESCHLOSSEN_YES()){
					omv.add_MESSAGE(new MyE2_Alarm_Message("Die Buchung ist bereits geschlossen !"));
				}else if(recFibu.get_DOWN_RECORD_LIST_MAHNUNG_POS_id_fibu().size()>0){
					for(RECORD_MAHNUNG_POS recMahPos: recFibu.get_DOWN_RECORD_LIST_MAHNUNG_POS_id_fibu()){
						if(recMahPos.get_UP_RECORD_MAHNUNG_id_mahnung().get_MAHNSTUFE_lValue(0l)>0){
							iMahnStuffe = Integer.parseInt(recMahPos.get_UP_RECORD_MAHNUNG_id_mahnung().get_MAHNSTUFE_cUF_NN("0"));
							break;
						}
					}
				}
				
				recListFibuMahnbar.ADD(new RECORD_FIBU(idFibu),false);

			}
			if(omv.get_bIsOK()){
				Mahnstuffe_selektion_popup int_pop_up = new Mahnstuffe_selektion_popup(recListFibuMahnbar, iMahnStuffe+1);
				int_pop_up.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(280), new MyE2_String("Mahnstufe"));
			}else{
				bibMSG.add_MESSAGE(omv);
			}

		}
	}

	private class ownActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(omv.get_bIsOK()){
				new ownBasicModuleContainerWaehleMahnungAus();
			}
		}

	}

	private class Mahnstuffe_selektion_popup extends E2_BasicModuleContainer{

		private MyE2_SelectField sel=null;


		private RECLIST_FIBU recListFibuMahnbar 		= null;


		public Mahnstuffe_selektion_popup(RECLIST_FIBU oRecListFibuMahnbar, int iDefaultMahnstuffe) throws myException {
			super();
			if(iDefaultMahnstuffe>3){
				iDefaultMahnstuffe = 3;
			}

			
			
			this.recListFibuMahnbar 		= oRecListFibuMahnbar;

			sel = new MyE2_SelectField(new String[]{"1", "2", "3"}, Integer.toString(iDefaultMahnstuffe), false, new Extent(75));
			
			E2_Button   btn_ok = 		new E2_Button()._t(new MyE2_String("OK"))._s_BorderText()._center()._aaa( new okActionAgent(this.recListFibuMahnbar , this));
			E2_Button   btn_cancel = 	new E2_Button()._t(new MyE2_String("Abbrechen"))._s_BorderText()._center()._aaa( new cancelActionAgent( this));;
			E2_Grid  grd = new E2_Grid()._setSize(100,100,100)
										._a(new RB_lab("Gewünschte Mahnstufe ?")._b(), new RB_gld()._ins(8)._span(2))
										._a(sel									 , new RB_gld()._ins(8));
			
			
			if(! isBuchungsBlock_complete()){
				E2_Grid grid_warn = new E2_Grid()	._s(1)
													._a(new RB_lab("Bitte beachten! Sie haben mindestens einen Buchungsblock ausgewählt,"
															+ " der nicht vollständig ist!")._b(), new RB_gld()._al(E2_ALIGN.CENTER_MID)._ins(2, 4, 2, 4))
													._bo(new Border(2, new E2_ColorAlarm(),Border.STYLE_SOLID ));
				
				grd._a(grid_warn,new RB_gld()._span(3));
			}							
										
			grd							._a(btn_ok.in_grid(80,20)				 , new RB_gld()._ins(8,20,8,2))
										._a(btn_cancel.in_grid(80,20)			 , new RB_gld()._ins(8,20,8,2));
			
			this.add(grd);
			
		}

		public Integer getSelectedMahnstuffe() throws myException{
			return Integer.parseInt(sel.get_ActualWert());
		}
	}

	private boolean isBuchungsBlock_complete() throws myException{
		Vector<String> selectedIdList = this.oNaviList.get_vSelectedIDs_Unformated();
		
		String BuchungsBlockQuery = new SEL("distinct("+FIBU.buchungsblock_nr.fn() + ")")
				.FROM(_TAB.fibu)
				.WHERE(new vgl(FIBU.id_mandant, bibALL.get_ID_MANDANT()))
				.AND(FIBU.id_fibu.fn(), "IN (", bibALL.ConcatenateWithoutException(selectedIdList, ",", "") + ")").s();
		
		//select jt_fibu.BUCHUNGSBLOCK_NR, count(id_fibu) from jt_fibu where jt_fibu.BUCHUNGSBLOCK_NR in (18640,28074) group by jt_fibu.BUCHUNGSBLOCK_NR

		String Selectedid_Buchungsblocklength = new SEL(FIBU.buchungsblock_nr).ADDFIELD("count("+FIBU.id_fibu.fn()+")")
				.FROM(_TAB.fibu)
				.WHERE(new vgl(FIBU.id_mandant, bibALL.get_ID_MANDANT()))
				.AND(FIBU.id_fibu.fn(), "IN (", bibALL.ConcatenateWithoutException(selectedIdList, ",", "") + ")").s().concat(" GROUP BY " + FIBU.buchungsblock_nr.fn());
		
		HashMap<String, String> map1 = new HashMap<>();
		
		String [][] erg1 = bibDB.EinzelAbfrageInArray(Selectedid_Buchungsblocklength);
		for(String[] row : erg1){
			map1.put(row[0],row[1]);
		}
		
		HashMap<String, String> map2 = new HashMap<>();
		
		Vector<String> erg2 = bibVECTOR.get_VectorFromArray(bibDB.EinzelAbfrageInArray(BuchungsBlockQuery));
		for(String bbNr: erg2){
			map2.put(bbNr, bibDB.EinzelAbfrage(new SEL("count("+FIBU.id_fibu.fn()+")").FROM(_TAB.fibu).WHERE(new vgl(FIBU.id_mandant, bibALL.get_ID_MANDANT())).AND(new vgl(FIBU.buchungsblock_nr, bbNr)).s()));
			
		}
		boolean ret = false;
		for(String bbNr: map1.keySet()){
			if(Integer.parseInt(map2.get(bbNr))==Integer.parseInt(map1.get(bbNr))){
				ret=true;
			}else ret = false;
			
		}
		
		return ret;
	
	}
	
	
	private class okActionAgent extends XX_ActionAgent{

		private RECLIST_FIBU recListFibuMahnbar = null;

		private Mahnstuffe_selektion_popup parent;

		public okActionAgent(RECLIST_FIBU oRecListFibuMahnbar,Mahnstuffe_selektion_popup oParent) {
			this.parent = oParent;
			this.recListFibuMahnbar = oRecListFibuMahnbar;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			Vector<String> idList = oNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only();
			
			String orderedIdListQuery = new SEL(FIBU.id_fibu)
					.FROM(_TAB.fibu)
					.WHERE(FIBU.id_fibu.fieldName(), "IN ", "("+bibALL.Concatenate(idList, ",", "")+")")
					.AND(new vgl(FIBU.buchungstyp,myCONST.BT_DRUCK_RECHNUNG))
					.ORDERDOWN(FIBU.endbetrag_fremd_waehrung).s();

			String[][] orderedIdList = bibDB.EinzelAbfrageInArray(orderedIdListQuery);
		
			Vector<String> finalIdList = bibVECTOR.get_VectorFromArray(orderedIdList);
			
			for(String id: idList){
				if(!finalIdList.contains(id)){
					finalIdList.add(id);
				}
			}
			
			new FIBU_MAHNUNG_Container(recListFibuMahnbar,parent.getSelectedMahnstuffe(),oNaviList, finalIdList);
			this.parent.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}

	}

	private class cancelActionAgent extends XX_ActionAgent{
		private Mahnstuffe_selektion_popup parent;
		public cancelActionAgent(Mahnstuffe_selektion_popup oParent) {
			this.parent = oParent;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			parent.CLOSE_AND_DESTROY_POPUPWINDOW(false);	
		}

	}
}
