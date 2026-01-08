package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.maggie.TestingDate;

public class FPT_BUTTON_PRINT_FAHRPLANUEBERSICHT extends FP__BUTTON_PRINT_TAGESUEBERSICHTEN
{

	private E2_DateBrowser  				oDateBrowser = null;

	
	public FPT_BUTTON_PRINT_FAHRPLANUEBERSICHT(E2_DateBrowser  DateBrowser)
	{
		super(new MyE2_String("Fahrplanübersichten-Druck/-Mail"),"DRUCK_FAHRPLAN_UEBERSICHT","MAIL_FAHRPLAN_UEBERSICHT",null);
		this.oDateBrowser = DateBrowser;
		this.setToolTipText(new MyE2_String("Fahrplanuebersicht drucken ...").CTrans());
	}

	
	@Override
	public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException
	{

		//jetzt die JasperHashMAP bauen
		FPT_BUTTON_PRINT_FAHRPLANUEBERSICHT oThis = FPT_BUTTON_PRINT_FAHRPLANUEBERSICHT.this;
		
		String cDateString = oThis.oDateBrowser.get_oDatumsFeld().getText();
		TestingDate oTestDate = new TestingDate(cDateString);
		if (!oTestDate.testing())
		{
			throw new myExceptionForUser("Fehler: Bitte geben Sie ein korrektes Datum ein !");
		}
		
		cDateString = oTestDate.get_ISO_DateFormat(false);
		return this.build_JasperHash(cDateString, null);
	}

}
