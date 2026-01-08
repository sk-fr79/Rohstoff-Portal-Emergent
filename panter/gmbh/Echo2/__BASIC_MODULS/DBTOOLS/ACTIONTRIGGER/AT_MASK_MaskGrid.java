package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.BasicInterfaces.IF_wrappedMulticomponentsInGrid;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.TOOLS.RB_grid4masks;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class AT_MASK_MaskGrid extends RB_grid4masks {

	public AT_MASK_MaskGrid(AT_MASK_ComponentMapCollector  mapColl) throws myException {
		super();
		
        AT_MASK_ComponentMap  map1 = (AT_MASK_ComponentMap) mapColl.get(new RB_KM(_TAB.trigger_action_def));
        
        RB_gld gld1 = new RB_gld()._ins(2, 2, 2, 2);
        RB_gld gld2 = new RB_gld()._ins(2, 2, 2, 2);
        
        IF_wrappedMulticomponentsInGrid wrap = (Component... comps)-> {E2_Grid g = new E2_Grid(); for (Component c: comps) {g._a(c, new RB_gld()._ins(0, 0, 5, 0));} return g._s(comps.length); };
        
        this._a(new RB_lab()._tr("ID"), gld1 ).						_a(map1.getComp(TRIGGER_ACTION_DEF.id_trigger_action_def),gld2);
        this._a(new RB_lab()._tr("Trigger-Name"), gld1 ).			_a(map1.getComp(TRIGGER_ACTION_DEF.trigger_name),gld2);
        this._a(new RB_lab()._tr("Tabelle"), gld1 ).				_a(map1.getComp(TRIGGER_ACTION_DEF.table_basename),gld2);
        this._a(new RB_lab()._tr("Tabellen-ID"), gld1 ).			_a(map1.getComp(TRIGGER_ACTION_DEF.table_id),gld2);
        this._a(new RB_lab()._tr("Feldname"), gld1 ).				_a(map1.getComp(TRIGGER_ACTION_DEF.field_name),gld2);
        this._a(new RB_lab()._tr("Validierungs-Klasse"), gld1 ).	_a(wrap.grid(map1.getComp(TRIGGER_ACTION_DEF.validation_class),map1.getComp(AT_CONST.key_InfoButtonValidation())),gld2);
        this._a(new RB_lab()._tr("Validierungs-Code"), gld1 ).		_a(map1.getComp(TRIGGER_ACTION_DEF.execution_valid),gld2);
        this._a(new RB_lab()._tr("Ausführungs-Klasse"), gld1 ).		_a(wrap.grid(map1.getComp(TRIGGER_ACTION_DEF.execution_class),map1.getComp(AT_CONST.key_InfoButtonExecution())),gld2);
        this._a(new RB_lab()._tr("Ausführungs-Code"), gld1 ).		_a(map1.getComp(TRIGGER_ACTION_DEF.execution_code),gld2);
        
        this._setSize(200,600);
    }
  
    
}
 
