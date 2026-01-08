package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import nextapp.echo2.app.Extent;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.PROFIL.AH7P_CONST.TRANSLATOR;


public class AH7P_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public AH7P_MASK_MaskModulContainer() throws myException {
        super();
        AH7P_MASK_ComponentMapCollector compMapCollector = new AH7P_MASK_ComponentMapCollector() ; 
        this.registerComponent(new  AH7P_KEY(), compMapCollector );
        this.rb_INIT(TRANSLATOR.MASK.get_modul(), new AH7P_MASK_MaskGrid(compMapCollector), true);
        this.set_oExtWidth(new Extent(1350));
        this.set_oExtHeight(new Extent(800));
    }
}
 
