package panter.gmbh.Echo2.ListAndMask.ComponentExtender;

import java.util.HashMap;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.LayoutData;
import panter.gmbh.BasicInterfaces.IF_getOwnComponentMAP;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.MyE2_Row_WithComponentAndChangelogButton;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.HANDLER.COMPONENTS.LOGTRIG_BUTTON_ZeigeProtokoll;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TRIGGER_DEF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class ADDING_FieldLoggingButtons
{

	public ADDING_FieldLoggingButtons(IF_ADDING_Allowed oContainer)
	{
		super();
		this.add_Logging_ButtonsToDBComps(oContainer);
	}
	
	
	private void ADD_FieldLoggingButtons(Component[]  arrComp) 	{
		for (int i=0;i<arrComp.length;i++)	{
			if (arrComp[i] instanceof MyE2_Row_WithComponentAndChangelogButton)			{
				//dies kompoenten wird nicht mehr veraendert, das sonst doppelte LogInfo-Buttons auftauchen
			}
			else if (arrComp[i] instanceof IF_ADDING_Allowed  && (!(arrComp[i] instanceof IF_FieldContainerComponent)))		{
				add_Logging_ButtonsToDBComps((IF_ADDING_Allowed)arrComp[i]);
			} else if (arrComp[i] instanceof MyE2IF__DB_Component  && (! (arrComp[i] instanceof IF_RB_Component))) {
				
				MyE2IF__DB_Component oDB_Comp = (MyE2IF__DB_Component)arrComp[i];
				
				if (oDB_Comp.EXT_DB().get_oSQLField()!=null && !oDB_Comp.get_bIsComplexObject())
				{
					
					String cTableName = oDB_Comp.EXT_DB().get_oSQLField().get_cTableName();
					String cFieldName = oDB_Comp.EXT_DB().get_oSQLField().get_cFieldName();
					
					if (S.isFull(cTableName) && cTableName.length()>4 && S.isFull(cFieldName))
					{
						try
						{
							HashMap<String, HashMap<String,RECORD_TRIGGER_DEF>> hmLogTrigger = bibALL.get_hmLogTriggerFields();
							
							String cTableNameBase = cTableName.substring(3);
													
							HashMap<String,RECORD_TRIGGER_DEF>  vFields = hmLogTrigger.get(cTableNameBase);
							
							//veraendern nur, wenn ein feld als betroffen gefunden wird
							if (vFields!=null && vFields.containsKey(cFieldName))
							{
								MyE2_Row_WithComponentAndChangelogButton oNewComp = new MyE2_Row_WithComponentAndChangelogButton();
								LayoutData  oLayout = ((Component)oDB_Comp).getLayoutData();
								((Component)oDB_Comp).setLayoutData(null);
								oNewComp.add((Component)oDB_Comp, E2_INSETS.I_0_0_0_0);
								
								E2_ComponentMAP map = oDB_Comp.EXT().get_oComponentMAP();
								if (oDB_Comp instanceof IF_getOwnComponentMAP) {
									map = ((IF_getOwnComponentMAP)oDB_Comp).getOwnComponentMap();
								}
								
								oNewComp.add(new LOGTRIG_BUTTON_ZeigeProtokoll(vFields.get(cFieldName), cTableNameBase, map), E2_INSETS.I_1_0_0_0);
								oNewComp.setLayoutData(oLayout);
								arrComp[i]=oNewComp;
							}
							
						}
						catch (myException e)
						{
							e.printStackTrace();
						}
					}
				}
			} else if (arrComp[i] instanceof IF_RB_Component) {				
				String 				cTableName = null;
				String 				cFieldName = null;;
				RB_ComponentMap  	map = null;
				
				try {
					//versuch 1 (wenn der key aus dem IF_Field gebildet wird)
					IF_RB_Component c = (IF_RB_Component)arrComp[i];
					
					if (c.rb_KF() != null)  {   				//evtl. simple beschriftungsobjekte auf der maske, sind nicht registriert
						if (c != null && c.rb_KF().get_data_field()!=null) {
							cTableName = c.rb_KF().get_data_field().tn();
							cFieldName = c.rb_KF().get_data_field().fn();
						}
//						// versuch 2 ueber die String-Keys
//						if (!S.isAllFull(cTableName,cFieldName)) {
//							cFieldName = c.rb_KF().get_REALNAME();
//							cTableName = c.rb_get_belongs_to().getRbDataObjectActual().get_RecORD().get_TABLENAME();
//						}
						map = c.rb_get_belongs_to();
						
						if (S.isAllFull(cTableName,cFieldName) && map!=null) {
							
							String tableBase = cTableName.substring(3);
							HashMap<String, HashMap<String,RECORD_TRIGGER_DEF>> hmLogTrigger = bibALL.get_hmLogTriggerFields();
													
							HashMap<String,RECORD_TRIGGER_DEF>  vFields = hmLogTrigger.get(tableBase);
							
							//veraendern nur, wenn ein feld als betroffen gefunden wird
							if (vFields!=null && vFields.containsKey(cFieldName)) {
								LayoutData ld = arrComp[i].getLayoutData();
								RB_gld gld = new RB_gld()._copyLayoutDataSettings(ld);
								arrComp[i] = new E2_Grid()._s(2)._a(arrComp[i],new RB_gld()._center_mid())
																._a(new LOGTRIG_BUTTON_ZeigeProtokoll(vFields.get(cFieldName), tableBase, map), new RB_gld()._ins(3, 0, 0, 0)._center_mid());
								arrComp[i].setLayoutData(gld);
							}
						}
					}	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	
	private void add_Logging_ButtonsToDBComps(IF_ADDING_Allowed oContainer)
	{
		Component[]  arrComp = oContainer.getComponents();
		this.ADD_FieldLoggingButtons(arrComp);
		oContainer.removeAll();
		for (int i=0;i<arrComp.length;i++)
		{
			oContainer.add_raw(arrComp[i]);
		}
	}


}
