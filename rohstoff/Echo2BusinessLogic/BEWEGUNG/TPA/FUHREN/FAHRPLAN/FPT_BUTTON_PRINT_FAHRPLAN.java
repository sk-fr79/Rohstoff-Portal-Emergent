package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.maggie.TestingDate;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_ModulContainer;

public class FPT_BUTTON_PRINT_FAHRPLAN extends FP__BUTTON_PRINTFAHRPLAENE
{

	private FU_LIST_ModulContainer 			ModulContainer = null;
	private MyE2_SelectField  				oSelectLKW = null;
	private E2_DateBrowser  				oDateBrowser = null;


	
	public FPT_BUTTON_PRINT_FAHRPLAN(FU_LIST_ModulContainer Modulcontainer, MyE2_SelectField  SelectLKW, E2_DateBrowser  DateBrowser)
	{
		super(new MyE2_String("Fahrplan-Druck/-Mail"),"DRUCK_FAHRPLAN","MAIL_FAHRPLAN",null);
		this.ModulContainer = Modulcontainer;
		this.oDateBrowser = DateBrowser;
		this.oSelectLKW = SelectLKW;
		this.setToolTipText(new MyE2_String("Fahrauftrag drucken ...").CTrans());
	}

	@Override
	public void RefreshContainerBeforePrint() throws myException
	{
		FPT_BUTTON_PRINT_FAHRPLAN.this.ModulContainer.get_oNavList()._REBUILD_ACTUAL_SITE("");
	}

	
	@Override
	public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException
	{

		//jetzt die JasperHashMAP bauen
		FPT_BUTTON_PRINT_FAHRPLAN oThis = FPT_BUTTON_PRINT_FAHRPLAN.this;
		
		String cDateString = oThis.oDateBrowser.get_oDatumsFeld().getText();
		TestingDate oTestDate = new TestingDate(cDateString);
		if (!oTestDate.testing())
		{
			throw new myExceptionForUser("Fehler: Bitte geben Sie ein korrektes Datum ein !");
		}
		
		cDateString = oTestDate.get_ISO_DateFormat(false);
		String cID_LKW = oThis.oSelectLKW.get_ActualWert();
		
		return this.build_JasperHash(cDateString, cID_LKW);
	}

}
