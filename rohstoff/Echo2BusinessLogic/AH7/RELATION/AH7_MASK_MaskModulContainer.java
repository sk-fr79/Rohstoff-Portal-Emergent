 
package rohstoff.Echo2BusinessLogic.AH7.RELATION;
import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7__CONST.TRANSLATOR;

public class AH7_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
	
	private AH7_MASK_ComponentMapCollector compMapCollector=null;
	
    public AH7_MASK_MaskModulContainer() throws myException {
        super();
        this.compMapCollector = new AH7_MASK_ComponentMapCollector() ; 
        this.registerComponent(new  AH7_KEY(), compMapCollector );
        
        this.set_oExtWidth(new Extent(1100));
        this.set_oExtHeight(new Extent(750));
        
        this.rb_INIT(TRANSLATOR.MASK.get_modul(), new AH7_MASK_MaskGrid(compMapCollector), true);
        
    }

	public AH7_MASK_ComponentMapCollector getCompMapCollector() {
		return compMapCollector;
	}
}
 
