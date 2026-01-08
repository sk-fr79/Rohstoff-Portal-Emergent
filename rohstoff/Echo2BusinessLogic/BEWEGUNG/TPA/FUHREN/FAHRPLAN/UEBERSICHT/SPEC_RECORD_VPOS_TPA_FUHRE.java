package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.UEBERSICHT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;

public class SPEC_RECORD_VPOS_TPA_FUHRE extends RECORD_VPOS_TPA_FUHRE
{

	private MyE2_CheckBox  oCB_DruckeFahrt = new MyE2_CheckBox();
	
	
	public SPEC_RECORD_VPOS_TPA_FUHRE(RECORD_VPOS_TPA_FUHRE recordOrig) throws myException
	{
		super(recordOrig);
		this.oCB_DruckeFahrt.setSelected(true);
		
		this.oCB_DruckeFahrt.setToolTipText(new MyE2_String("Fahrt zum Druck der Belege Lieferschein/Ladeschein/Abholavis/Lieferavis auswählen ...",true," (ID-Fuhre: "+this.get_ID_VPOS_TPA_FUHRE_cUF_NN("")+")",false).CTrans());
	}


	public MyE2_CheckBox get_CB_DruckeFahrt()
	{
		return oCB_DruckeFahrt;
	}

}
