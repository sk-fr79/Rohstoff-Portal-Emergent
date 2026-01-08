package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.UEBERSICHT;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.maggie.TestingDate;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP__BUTTON_PRINT_TAGESUEBERSICHTEN;

public class FPU_BUTTON_PRINT_FAHRPLANUEBERSICHT extends FP__BUTTON_PRINT_TAGESUEBERSICHTEN
{

	private FPU_BasicModuleContainer 		ModulContainer = null;
	private E2_DateBrowser  				oDateBrowser = null;

	
	public FPU_BUTTON_PRINT_FAHRPLANUEBERSICHT(FPU_BasicModuleContainer Modulcontainer)
	{
		super(new MyE2_String("Fahrplanübersichten-Druck/-Mail"),"DRUCK_FAHRPLAN_UEBERSICHT","MAIL_FAHRPLAN_UEBERSICHT",null);
		this.ModulContainer = Modulcontainer;
		this.oDateBrowser = Modulcontainer.get_oDateForFahrplan();
		this.setToolTipText(new MyE2_String("Fahrplanuebersicht drucken ...").CTrans());
	}

	
	@Override
	public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException
	{

		//jetzt die JasperHashMAP bauen
		FPU_BUTTON_PRINT_FAHRPLANUEBERSICHT oThis = FPU_BUTTON_PRINT_FAHRPLANUEBERSICHT.this;
		
		String cDateString = oThis.oDateBrowser.get_oDatumsFeld().getText();
		TestingDate oTestDate = new TestingDate(cDateString);
		if (!oTestDate.testing())
		{
			throw new myExceptionForUser("Fehler: Bitte geben Sie ein korrektes Datum ein !");
		}
		
		cDateString = oTestDate.get_ISO_DateFormat(false);
		
		Vector<String> vHelp = new Vector<String>(); 
		for (int i=0;i<this.ModulContainer.get_vLKW_CheckBox().size();i++)
		{
			if (this.ModulContainer.get_vLKW_CheckBox().get(i).isSelected())
			{
				vHelp.add(this.ModulContainer.get_vLKW_CheckBox().get(i).get_recLKW().get_ID_MASCHINEN_cUF());
			}
		}
		
		if (vHelp.size()==0)
			throw new myExceptionForUser("Es sind keine LKWs in der Übersicht !");
		
		return this.build_JasperHash(cDateString, "JT_VPOS_TPA_FUHRE.ID_MASCHINEN_LKW_FP IN ("+bibALL.ConcatenateWithoutException(vHelp, ",", "-1")+")");
	}

}
