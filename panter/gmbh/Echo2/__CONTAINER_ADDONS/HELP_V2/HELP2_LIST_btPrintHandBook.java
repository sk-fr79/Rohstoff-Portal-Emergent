package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.BtMailAndReport_V2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class HELP2_LIST_btPrintHandBook extends BtMailAndReport_V2 {

	private E2_NavigationList  naviList = null;
	
	public HELP2_LIST_btPrintHandBook() throws myException {
		super();
		
		this._init(S.ms("Handbuchdruck"), null, null, null, 
					HELP2_CONST.PRINT_KENNERS.BETREFF_HANDBUCHDRUCK.db_val(), 
					HELP2_CONST.PRINT_KENNERS.MAILTEXT_HANDBUCHDRUCK.db_val(), 
					HELP2_CONST.PRINT_KENNERS.ARCHIV_HANDBUCHDRUCK.db_val());
		
		this.setToolTipText(new MyE2_String("Drucken der momentan gewählten Hilfetexte").CTrans());
		
		this.add_GlobalValidator(new ownValidatorValidNoEmptySelection());
		
	}

	
	public HELP2_LIST_btPrintHandBook _setNaviList(E2_NavigationList naviList) {
		this.naviList = naviList;
		return this;
	}
	

	
	
	@Override
	public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException {
		V_JasperHASH  vRueck = new V_JasperHASH();
		vRueck.add(new ownJASPER_HASH());
		return vRueck;
	}

	private Vector<String> get_vIDs_Hilfetext() throws myException {
		
		//zuerst nachsehen, ob eine checkbox (nur fuer developer-user) angekreuzt ist
		VEK<String>  v_positiv_ausgewaehlt = new VEK<String>();
		v_positiv_ausgewaehlt._a(naviList.get_vSelectedIDs_Unformated());
		
		VEK<String>  v_allesInSegment = new VEK<String>();
		v_allesInSegment._a(naviList.get_vectorSegmentation());
		
		VEK<String>  v_iis_to_print = new VEK<String>();

		
		if (naviList==null) {
			throw new myException(this,"Design-Error: no naviList is set !");
		}
		
		if (v_positiv_ausgewaehlt.size()==0) {
			if (v_allesInSegment.size()==0) {
				bibMSG.MV()._addAlarm("Es ist nichts zum Drucken ausgewählt / vorhanden ");
			} else {
				bibMSG.MV()._addInfo(S.ms("Es wird alles in der monmentanen Filtereinstellung gedruckt: ").ut(" ("+v_allesInSegment.size()+")"));
				v_iis_to_print._a(v_allesInSegment);
			}
		} else {
			bibMSG.MV()._addInfo(S.ms("Es werden die ausgewählten Einträge gedruckt: ").ut(" ("+v_positiv_ausgewaehlt.size()+")"));
			v_iis_to_print._a(v_positiv_ausgewaehlt);
		}

		
		return v_iis_to_print;
	}
	
	

	
	
	
	private class ownValidatorValidNoEmptySelection extends XX_ActionValidator {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			if (HELP2_LIST_btPrintHandBook.this.get_vIDs_Hilfetext().size()==0) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es gibt nichts zu Drucken !")));
			}
			
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}
		
	}

	
	
	
	private class ownJASPER_HASH extends E2_JasperHASH_STD {

		public ownJASPER_HASH() throws myException {
			super("handbuch.jasper", new JasperFileDef_PDF());
			
			//jetzt die spezifischen felder fuer den report uebergeben
			String cWhere = HILFETEXT.id_hilfetext.fn()+" IN ("+
			bibALL.Concatenate(HELP2_LIST_btPrintHandBook.this.get_vIDs_Hilfetext(), ",","-1")+")";
			
			this.put(HELP2_CONST.parameter_handbuchdruck.WHEREBLOCK.name(), cWhere);
			this.put(HELP2_CONST.parameter_handbuchdruck.HASH_TYP.name(), HELP2_CONST.get_hm_ticketTyp());
			this.put(HELP2_CONST.parameter_handbuchdruck.HASH_STATUS.name(), HELP2_CONST.get_hm_status());
			this.put(HELP2_CONST.parameter_handbuchdruck.HASH_MODULE.name(), E2_MODULNAME_ENUM.get_hm_MODULNAMES());
			this.put(HELP2_CONST.parameter_handbuchdruck.ORDERBLOCK.name(), naviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_actualOrderBlock());
		}
		
		
		
	}
	
	
	


}
