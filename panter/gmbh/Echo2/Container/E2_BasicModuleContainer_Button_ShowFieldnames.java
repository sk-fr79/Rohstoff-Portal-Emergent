package panter.gmbh.Echo2.Container;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.SelectField;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea_WithSelektor;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class E2_BasicModuleContainer_Button_ShowFieldnames extends MyE2_Button {

	public E2_BasicModuleContainer_Button_ShowFieldnames() 
	{
		super(new MyE2_String("Tooltips mit Entwicklerinfos füllen"));
		this.setToolTipText(new MyE2_String("Tooltips aller Felder mit Entwicklerinfos (z.B. Feldnamen usw) füllen").CTrans());
		this.add_oActionAgent(new ownActionAgent());
	}
	
	
	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_RecursiveSearch_Component oSearch = new E2_RecursiveSearch_Component(
					bibE2.GET_FIRST_CONTENTPANE_IN_SESSION(),bibALL.get_Vector(	MyE2IF__DB_Component.class.getName()
																				,IF_RB_Component.class.getName()
																				,IF_RB_Component_Savable.class.getName()
																				),null);
			
			for (Component oComp: oSearch.get_vAllComponents()) {
				if (oComp instanceof IF_RB_Component || oComp instanceof IF_RB_Component_Savable) {
					String cNameTabelle = 		"<>";
					String cFieldname =  		"<>";
					String cHashKey =  			"<>";
					String cMaskName =   		"<>";
					String cMaskKey = 			"<>";
					
					IF_RB_Component rb_Comp = (IF_RB_Component)oComp;
					if (rb_Comp.EXT().get_oComponentThisBelongsTo()!=null && rb_Comp.rb_get_belongs_to() instanceof RB_ComponentMap) {
						RB_ComponentMap  mask = rb_Comp.rb_ComponentMap_this_belongsTo();
						try {
							if (rb_Comp.rb_KF()!=null) {
								cFieldname = rb_Comp.rb_KF().FIELDNAME();
								cHashKey =  rb_Comp.rb_KF().HASHKEY();
							} else {
								DEBUG.System_println("kein key: "+rb_Comp.toString());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (mask !=null) {
							cNameTabelle = mask.rb_TABLENAME();
							RB_KM maskKey = mask.getOwnMaskKey();
							if (maskKey!=null) {
								cMaskName = maskKey.get_REALNAME();
								cMaskKey = maskKey.get_HASHKEY();
							}
						}
						
						String cToolTipText = 		"DESCRIPTION: "+"<tabelle>"+cNameTabelle+":"+"<feldname>"+cFieldname+":"+"<fieldkey>"+cHashKey+":"+"<maskname>"+cMaskName+":"+"<maskkey>"+cMaskKey;
						this.set_tooltips(oComp, cToolTipText);
					} else if (oComp instanceof MyE2IF__DB_Component) {   //wenn rbComponents auf alten masken eingesetzt werden
						tagMyE2IF__DB_Component(oComp);
					}
				} else if (oComp instanceof MyE2IF__DB_Component) {
					tagMyE2IF__DB_Component(oComp);
//					MyE2IF__DB_Component oDB_Comp = (MyE2IF__DB_Component)oComp;
//					
//					if (oDB_Comp.EXT_DB().get_oSQLField() != null) {
//						String cNameTabelle = S.NN(oDB_Comp.EXT_DB().get_oSQLField().get_cTableName(),"<table>");
//						String cFieldname =  S.NN(oDB_Comp.EXT_DB().get_oSQLField().get_cFieldLabel(),"<label>");
//						String cFieldausdruck =  S.NN(oDB_Comp.EXT_DB().get_oSQLField().get_cFieldAusdruck(),"<ausdruck>");
//						String cToolTipText = "DESCRIPTION: "+cNameTabelle+":"+cFieldname+":"+cFieldausdruck;
//						
//						this.set_tooltips(oComp, cToolTipText);
//						
//					}

				}
			}
		}
		
		
		private void tagMyE2IF__DB_Component(Component oComp) {
			try {
				MyE2IF__DB_Component oDB_Comp = (MyE2IF__DB_Component)oComp;
				
				if (oDB_Comp.EXT_DB().get_oSQLField() != null) {
					String cNameTabelle = S.NN(oDB_Comp.EXT_DB().get_oSQLField().get_cTableName(),"<table>");
					String cFieldname =  S.NN(oDB_Comp.EXT_DB().get_oSQLField().get_cFieldLabel(),"<label>");
					String cFieldausdruck =  S.NN(oDB_Comp.EXT_DB().get_oSQLField().get_cFieldAusdruck(),"<ausdruck>");
					String cToolTipText = "DESCRIPTION: "+cNameTabelle+":"+cFieldname+":"+cFieldausdruck;
					
					this.set_tooltips(oComp, cToolTipText);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private void set_tooltips(Component  oComp, String cToolTipText) {

			//von speziell nach allgemein
			
			
			if (oComp instanceof RB_date_selektor) {
				((RB_date_selektor)oComp).get_oTextField()._ttt_ut_if_longer(cToolTipText);
				return;
			}
			
			if (oComp instanceof RB_SearchField) {
				((RB_SearchField)oComp).get_tf_search_input()._ttt_ut_if_longer(cToolTipText);
				return;
			}

			if (oComp instanceof MyE2_DB_ComboBoxErsatz) {
				((MyE2_DB_ComboBoxErsatz)oComp).get_oTextField()._ttt_ut_if_longer(cToolTipText);
				return;
			}
			
			if (oComp instanceof MyE2_DB_TextArea_WithSelektor) {
				((MyE2_DB_TextArea_WithSelektor)oComp).get_oTextArea()._ttt_ut_if_longer(cToolTipText);
				return;
			} 

//			if (oComp instanceof MyE2IF__Component) {
//				((MyE2IF__Component)oComp)._ttt_ut_if_longer(cToolTipText);
//				return;
//			}
			
			if (oComp instanceof TextField) {
				if (S.NN(((TextField)oComp).getToolTipText()).length()<S.NN(cToolTipText).length()) {  
					((TextField)oComp).setToolTipText(cToolTipText);
				}
				return;
			}
			
			if (oComp instanceof TextArea) {
				if (S.NN(((TextArea)oComp).getToolTipText()).length()<S.NN(cToolTipText).length()) {  
					((TextArea)oComp).setToolTipText(cToolTipText);
				}
				return;
			} 
			
			if (oComp instanceof Label) {
				if (S.NN(((Label)oComp).getToolTipText()).length()<S.NN(cToolTipText).length()) {  
					((Label)oComp).setToolTipText(cToolTipText);
				}
				return;
			} 
			if (oComp instanceof CheckBox) {
				if (S.NN(((CheckBox)oComp).getToolTipText()).length()<S.NN(cToolTipText).length()) {  
					((CheckBox)oComp).setToolTipText(cToolTipText);
				}
				return;
			}
			
			if (oComp instanceof SelectField) {
				if (S.NN(((SelectField)oComp).getToolTipText()).length()<S.NN(cToolTipText).length()) {  
					((SelectField)oComp).setToolTipText(cToolTipText);
				}
				return;
			}

			
			if (oComp instanceof Component) {
				E2_RecursiveSearch_Component searcher = new E2_RecursiveSearch_Component((Component)oComp,null,null); 
				for (Component c: searcher.get_vAllComponents()) {
					for (Method m: c.getClass().getMethods()) {
						if (m.getName().equals("setToolTipText")) {
							try {
								m.invoke(c, cToolTipText);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
				}
				return;
			}
			
			
			
		}
		
		
		
	}
	
}
