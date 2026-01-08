package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.UEBERSICHT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.maggie.TestingDate;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP__BUTTON_PRINTFAHRPLAENE;

public class FPU_BUTTON_PRINT_FAHRPLAN extends FP__BUTTON_PRINTFAHRPLAENE
{

	private FPU_BasicModuleContainer 		ModulContainer = null;
	private E2_DateBrowser  				oDateBrowser = null;


	
	public FPU_BUTTON_PRINT_FAHRPLAN(FPU_BasicModuleContainer Modulcontainer)
	{
		super(new MyE2_String("Fahrplan-Druck/-Mail"),"DRUCK_FAHRPLAN","MAIL_FAHRPLAN",null);
		this.ModulContainer = Modulcontainer;
		this.oDateBrowser = Modulcontainer.get_oDateForFahrplan();
		this.setToolTipText(new MyE2_String("Fahraufträge drucken ...").CTrans());
	}

	@Override
	public void RefreshContainerBeforePrint() throws myException
	{
	}

	
	@Override
	public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException
	{
		V_JasperHASH  vRueck = new V_JasperHASH();
		
		//jetzt die JasperHashMAP bauen
		FPU_BUTTON_PRINT_FAHRPLAN oThis = FPU_BUTTON_PRINT_FAHRPLAN.this;
		
		String cDateString = oThis.oDateBrowser.get_oDatumsFeld().getText();
		TestingDate oTestDate = new TestingDate(cDateString);
		if (!oTestDate.testing())
		{
			throw new myExceptionForUser("Fehler: Bitte geben Sie ein korrektes Datum ein !");
		}
		
		cDateString = oTestDate.get_ISO_DateFormat(false);
		
		for (int i=0;i<this.ModulContainer.get_vLKW_CheckBox().size();i++)
		{
			if (this.ModulContainer.get_vLKW_CheckBox().get(i).isSelected())
			{
				vRueck.addAll(this.build_JasperHash(cDateString, this.ModulContainer.get_vLKW_CheckBox().get(i).get_recLKW().get_ID_MASCHINEN_cUF()));
			}
		}
		return vRueck;
	}

}
