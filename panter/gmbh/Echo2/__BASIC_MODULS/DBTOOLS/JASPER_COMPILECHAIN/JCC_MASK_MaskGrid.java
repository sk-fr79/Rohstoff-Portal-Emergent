 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.JASPER_COMPILECHAIN;
  
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.JASPER_COMPILE_CHAIN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class JCC_MASK_MaskGrid extends E2_Grid {
    public JCC_MASK_MaskGrid(JCC_MASK_ComponentMapCollector  mapColl) throws myException {
        super();
        this._setSize(250,300)._bo_no();
        
        JCC_MASK_ComponentMap  map1 = (JCC_MASK_ComponentMap) mapColl.get(_TAB.jasper_compile_chain.rb_km());

        
        this._a(new RB_lab("ID") ,											new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._a(map1.getComp(JASPER_COMPILE_CHAIN.id_jasper_compile_chain), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

        this._a(new RB_lab("Aufgerufener Report: ")._b()._i() ,				new RB_gld()._span(2)._ins(2,10,2,2)._al(E2_ALIGN.LEFT_TOP)._col_back_d());
        
//        this._a(new RB_lab("Basis/Mandantenpfad") ,							new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._a(map1.getComp(JASPER_COMPILE_CHAIN.basedir), 				new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._a(new RB_lab("Reportname") ,									new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._a(map1.getComp(JASPER_COMPILE_CHAIN.reportname), 				new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

        this._a(new RB_lab("Zugehöriger Pfad zum Kompilieren: ")._b()._i(),	new RB_gld()._span(2)._ins(2,10,2,2)._al(E2_ALIGN.LEFT_TOP)._col_back_d());
        
        this._a(new RB_lab("Basis/Mandantenpfad zu Subreport-Verzeichnis") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._a(map1.getComp(JASPER_COMPILE_CHAIN.compilebasedir), 			new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        
        this._a(new RB_lab("Compiler-Ziel (Report-jrxml oder Ordner)") ,	new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
        this._a(map1.getComp(JASPER_COMPILE_CHAIN.compiletarget), 			new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
    }
  
    
}
 
