package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.activeReport.E2_ActiveReport;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._INFO_NT.AI__Container;

public class INFO_BLOCK_ADRESS_UEBERSICHT_NT extends E2_ActiveReport
{

	private RECORD_ADRESSE  recAdresse = null;
	private AI__Container 	container = new AI__Container(new Vector<String>());
	
	public INFO_BLOCK_ADRESS_UEBERSICHT_NT() throws myException {
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	}	
	
	public MyE2_Grid get_ContainerGridMitBedienZeile() throws myException {
		return this.container;
	}
	
	public void set_recAdresse(RECORD_ADRESSE recAd) {
		this.recAdresse = recAd;
	}
	
	
	public MyE2_ContainerEx get_oContainerEx() {
		return this.container.get_Container_EX();
	}
 
	
	//wird hier ueberschrieben, damit der neue adressinfo-block funktioniert
	public void fill_Report() throws myException 
	{
		this.container.get_v_id_adressen().removeAllElements();
		this.container.get_v_id_adressen().add(this.recAdresse.get_ID_ADRESSE_cUF());
		this.container._build();
	}

}
