package panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider;

import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.MESSAGE_PROVIDER;
import panter.gmbh.indep.exceptions.myException;


public class MES_MASK_MaskGrid extends E2_Grid {
    public MES_MASK_MaskGrid(MES_MASK_ComponentMapCollector  mapColl) throws myException {
        super();
        this._setSize(200,600)._bo_no();
        
        MES_MASK_ComponentMap  map1 = (MES_MASK_ComponentMap) mapColl.get(new MES_KEY());
        
        this._a(new RB_lab("ID") ,new RB_gld()._ins(2)._left_mid());
        this._a(map1.getComp(MESSAGE_PROVIDER.id_message_provider), new RB_gld()._ins(2,2,2,4)._al(E2_ALIGN.LEFT_TOP));
        this._a(new RB_lab("Empfänger der Nachricht") ,new RB_gld()._ins(2,2,2,4)._left_mid());
        this._a(map1.getComp(MESSAGE_PROVIDER.id_user), new RB_gld()._ins(2,2,2,4)._al(E2_ALIGN.LEFT_TOP));
        this._a(new RB_lab("Programmschlüssel") ,new RB_gld()._ins(2,2,2,4)._left_mid());
        this._a(map1.getComp(MESSAGE_PROVIDER.messagekey), new RB_gld()._ins(2,2,2,4)._al(E2_ALIGN.LEFT_TOP));
        this._a(new RB_lab("Nachricht senden") ,new RB_gld()._ins(2,2,2,4)._left_mid());
        this._a(map1.getComp(MESSAGE_PROVIDER.send_message), new RB_gld()._ins(2,2,2,4)._al(E2_ALIGN.LEFT_TOP));
        this._a(new RB_lab("eMail senden") ,new RB_gld()._ins(2,2,2,4)._left_mid());
        this._a(map1.getComp(MESSAGE_PROVIDER.send_email), new RB_gld()._ins(2,2,2,4)._al(E2_ALIGN.LEFT_TOP));
    
    }
  
    
}
 
