
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;


import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO.ADI_CONST.TRANSLATOR;



public class ADI_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {

	private RECORD_ADRESSE rec_adresse = null;

	public ADI_MASK_MaskModulContainer(RECORD_ADRESSE  p_rec_adresse) throws myException {
		super();
		
		this.rec_adresse = p_rec_adresse;
		
		ADI_MASK_ComponentMapCollector compMapCollector = new ADI_MASK_ComponentMapCollector(this.rec_adresse);
		this.registerComponent(new RB_KM(_TAB.adresse_info), compMapCollector);

		this.rb_INIT(TRANSLATOR.MASK.get_modul(), new ADI_MASK_MaskGrid(compMapCollector), true);

	}
}
