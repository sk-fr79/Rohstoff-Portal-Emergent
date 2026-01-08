package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.E2_Refreshable;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_BASIC_InfoMessageWithAddonComponent;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_LOG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class TRIGGER_Operator implements E2_Refreshable {

	@Override
	public void RefreshInfo()  {
		
		//message-stack muss hier separat behandelt werden, damit eine Error-Message nicht zu Systemstillstand fuehrt
		MyE2_MessageVector mv_save = new MyE2_MessageVector();
		mv_save.add_MESSAGE(bibMSG.MV());
		bibMSG.MV().clear();
		
		try {
			//ab hier sind alle errormessages im bibMV von der triggerbehandlung
			
			SEL sel = new SEL().ADDFIELD(TRIGGER_ACTION_LOG.id_trigger_action_log)
			 				   .ADDFIELD(TRIGGER_ACTION_LOG.id_trigger_action_def)
			 				   .FROM(_TAB.trigger_action_log)
			 				   .WHERE(TRIGGER_ACTION_LOG.user_id,bibALL.get_ID_USER());
			
			SEL sel2 = new SEL().ADDFIELD(TRIGGER_ACTION_LOG.id_trigger_action_log)
			 				   .FROM(_TAB.trigger_action_log)
			 				   .WHERE(TRIGGER_ACTION_LOG.user_id,bibALL.get_ID_USER());
			
			String[][] ids = bibDB.EinzelAbfrageInArray(sel.s(),"");
			
			if (ids.length>0) {
				for (int i=0;i<ids.length;i++) {
					Rec20_trigger_action_log rt = (Rec20_trigger_action_log)new Rec20_trigger_action_log()._fill_id(ids[i][0]);
					rt.validate_and_execute_trigger(bibMSG.MV());
				}
				
				String sql_delete="DELETE FROM "+bibE2.cTO()+"."+_TAB.trigger_action_log.n()+" WHERE "+TRIGGER_ACTION_LOG.id_trigger_action_log+" IN ("+sel2.s()+")";
				if (!bibDB.ExecSQL(sql_delete, true)) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error deleting message-log ..."+sql_delete));
				}
			}
			
		} catch (Exception e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("TRIGGER_Operator.RefreshInfo()"+e.getMessage()));
			e.printStackTrace();
		}

		//jetzt den messagevektor auf errormessages untersuchen und diese zu einer info-message in ein popup sperren
		MyE2_MessageVector  mv_aus_trigger = new MyE2_MessageVector();
		mv_aus_trigger.add_MESSAGE(bibMSG.MV());
		bibMSG.MV().clear();
		bibMSG.MV().add_MESSAGE(mv_save);     //jetzt ist wieder alles wie vor der triggerbehandlung
		
		
		if (mv_aus_trigger.size()>0) {
			DEBUG.System_print(mv_aus_trigger);
			MyE2_BASIC_InfoMessageWithAddonComponent msg = new MyE2_BASIC_InfoMessageWithAddonComponent(new MyE2_String("Es sind Hintergrundmeldungen vorhanden ..."), new ownInfoButtonToShowTriggerMessages(mv_aus_trigger)); 
			bibMSG.add_MESSAGE(msg, true);
		}
	}
	

	
	/**
	 * infobutton mit Actionlistener an der internen actionAgent-Schleife vorbei (damit nicht dort auch die trigger ausgelesen werden) 
	 * @author martin
	 *
	 */
	private class ownInfoButtonToShowTriggerMessages extends E2_Button {

		private MyE2_MessageVector mv_from_triggers = null;
		
		public ownInfoButtonToShowTriggerMessages(MyE2_MessageVector p_mv_from_triggers) {
			super();
			this.mv_from_triggers = p_mv_from_triggers;
			this._t(S.mt("Hintergrund-Fehler"))._b()._bord_black()._bc(Color.RED);
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						new ownContainer();
					} catch (myException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		private class ownContainer extends E2_BasicModuleContainer {

			public ownContainer() throws myException {
				super();
				
				E2_Grid  grid = new E2_Grid()._s(1)._w100();
				
				for (MyE2_Message  m: ownInfoButtonToShowTriggerMessages.this.mv_from_triggers) {
					if (m.get_iType()==MyE2_Message.TYP_INFO) {
						grid._a(new RB_lab()._t(m.get_cMessage().CTrans()),new RB_gld()._ins(2));
					} else if (m.get_iType()==MyE2_Message.TYP_WARNING) {
						grid._a(new RB_lab()._t(m.get_cMessage().CTrans()),new RB_gld()._ins(2)._col(new E2_ColorHelpBackground()));
					} else {
						grid._a(new RB_lab()._t(m.get_cMessage().CTrans()),new RB_gld()._ins(2)._col(new E2_ColorAlarm()));
					}
				}
				
				this.add(grid, E2_INSETS.I(5));
				
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(600), new MyE2_String("Hintergrund-Meldungen"));
			}
		}
	}
	
	
}
