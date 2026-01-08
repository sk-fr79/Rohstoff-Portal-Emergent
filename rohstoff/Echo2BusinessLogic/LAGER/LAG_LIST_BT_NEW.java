package rohstoff.Echo2BusinessLogic.LAGER;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class LAG_LIST_BT_NEW extends MyE2_Button
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1563265202576531967L;
	private E2_NavigationList m_navigationList = null;
	
	
	public LAG_LIST_BT_NEW(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"));
		m_navigationList = onavigationList;
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_LAG"));
	}

	
	
	//private class ownActionAgent extends ButtonActionAgentNEW
	private class ownActionAgent extends XX_ActionAgent
	{

		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			//	super(new MyE2_String("Lagerbuchung... "), onavigationList, omaskContainer, oownButton, null);
			
			super();
			
		}

		
		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ListAndMask.List.ActionAgents.XX_ButtonActionAgent_FromListToMask#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			// TODO Auto-generated method stub
			//	super.executeAgentCode(execInfo);
			
			new LAG_LagerBuchung_Erfassung(m_navigationList,EnumDisplayOptions.SHOW_BUCHUNG_EINFACH);
			
			
			
			
//			// DEBUG
//			
//			  //variante fuhre 
//            //Preis 1 (gut):
//            new RECORD_VPOS_TPA_FUHRE("1").get_UP_RECORD_VPOS_KON_id_vpos_kon_ek().get_EINZELPREIS_dValue(new Double(0));
//            new RECORD_VPOS_TPA_FUHRE("1").get_UP_RECORD_VPOS_KON_id_vpos_kon_vk().get_EINZELPREIS_dValue(new Double(0));
//       
//            //Preis 2 (besser):   !!! wichtig! den RECORD_VPOS_RG suchen, der keine ID_VPOS_TPA_FUHRE_ORT hat und nicht gelöscht 
//            // ungerade anzahl von Datensätzen ->letzter Satz ist der gültige Preis
//            // gerade Anzahl von Datensätzen -> kein gültiger Satz
//            // isdeleted no
//            new RECORD_VPOS_TPA_FUHRE("1").get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord().get(0).get_EINZELPREIS_dValue(new Double(0)); 
//            
//            
//            //variante ort
//            //Preis 1 (gut):
//            new RECORD_VPOS_TPA_FUHRE_ORT("2").get_UP_RECORD_VPOS_KON_id_vpos_kon().get_EINZELPREIS_dValue(new Double(0));
//            //Preis 2 (besser):   !!! wichtig! den RECORD_VPOS_RG suchen, der keine ID_VPOS_TPA_FUHRE_ORT hat
//            new RECORD_VPOS_TPA_FUHRE_ORT("2").get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord().get(0).get_EINZELPREIS_dValue(new Double(0)); 
//            

			
			
			
			// DEBUG:
//			LAG_LagerBuchungsSatz l; //= new LAG_LagerBuchungsSatz("1","2104","1",new BigDecimal("12.2"),new BigDecimal("5800"),"'2009-03-18'","WE",null,null);
//			LAG_LagerHandler lHandler = new LAG_LagerHandler();
//			Vector<String> vSql;

//			// WE
//			l = new LAG_LagerBuchungsSatz("1","2104","1",new BigDecimal("11.50"),new BigDecimal("11000"),"SYSDATE","WE","10","10");
//			lHandler.LagerBuchung(l,bStorno	);
//			
//			l = new LAG_LagerBuchungsSatz("1","2104","2",new BigDecimal("99.95"),new BigDecimal("100"),"SYSDATE","WE","10","20");
//			lHandler.LagerBuchung(l,bStorno);
//				
//			l= new LAG_LagerBuchungsSatz("1","2104","3",new BigDecimal("199.95"),new BigDecimal("11100"),"SYSDATE","WE","10","30");
//			lHandler.LagerBuchung(l,bStorno);
//
//			l = new LAG_LagerBuchungsSatz("1","2104","1",new BigDecimal("12.25"),new BigDecimal("12500"),"SYSDATE","WE","10",null);
//			lHandler.LagerBuchung(l,bStorno);
//
//			//WA
//			l = new LAG_LagerBuchungsSatz("1","2104","1",new BigDecimal("12.5"),new BigDecimal("-10000"),"SYSDATE","WA","10","10");
//			lHandler.LagerBuchung(l,bStorno);
//			
//			l = new LAG_LagerBuchungsSatz("1","2104","2",new BigDecimal("99.95"),new BigDecimal("-200"),"SYSDATE","WA","10","20");
//			lHandler.LagerBuchung(l,bStorno);
//				
//			l= new LAG_LagerBuchungsSatz("1","2104","3",new BigDecimal("199.95"),new BigDecimal("-1100"),"SYSDATE","WA","10","30");
//			lHandler.LagerBuchung(l,bStorno);
//
//			l = new LAG_LagerBuchungsSatz("1","2104","1",new BigDecimal("12.25"),new BigDecimal("-11500"),"SYSDATE","WA","10",null);
//			lHandler.LagerBuchung(l,bStorno);
//
//			// WE
//			l = new LAG_LagerBuchungsSatz("1","2104","1",new BigDecimal("11.50"),new BigDecimal("11000"),"SYSDATE","WE","10","10");
//			lHandler.LagerBuchung(l,bStorno);
//			
//			l = new LAG_LagerBuchungsSatz("1","2104","2",new BigDecimal("99.95"),new BigDecimal("100"),"SYSDATE","WE","10","20");
//			lHandler.LagerBuchung(l,bStorno);
//				
//			l= new LAG_LagerBuchungsSatz("1","2104","3",new BigDecimal("199.95"),new BigDecimal("11100"),"SYSDATE","WE","10","30");
//			lHandler.LagerBuchung(l,bStorno);
//
//			l = new LAG_LagerBuchungsSatz("1","2104","1",new BigDecimal("12.25"),new BigDecimal("12500"),"SYSDATE","WE","10",null);
//			lHandler.LagerBuchung(l,bStorno);

			
			
//			// einfache "Luft- oder Korrekturbuchungen"
//			l = new LAG_LagerBuchungsSatz("3112","1",new BigDecimal("120"),new BigDecimal("20300"),"SYSDATE","WE",null,null);
//			lHandler.LagerBuchung(l,true);
//			
//			vSql = lHandler.getSqlStatements();
//			bibALL.System_println(vSql.toString());
//			lHandler.executeSqlStatements(true);
////			
//			l = new LAG_LagerBuchungsSatz("3112","1",new BigDecimal("9.57"),new BigDecimal("20500"),"SYSDATE","WE",null,null);
//			lHandler.LagerBuchung(l,bStorno);
//			
//			
//			l = new LAG_LagerBuchungsSatz("3112","1",new BigDecimal("33.33"),new BigDecimal("-1500"),"SYSDATE","WA","10","10");
//			lHandler.LagerBuchung(l,true);
//			vSql = lHandler.getSqlStatements();
//			bibALL.System_println(vSql.toString());
//			lHandler.executeSqlStatements(true);
//			
			
//			lHandler.LagerBuchung(l,bStorno);
//		
//			l = new LAG_LagerBuchungsSatz("3112","1",new BigDecimal("25.99"),new BigDecimal("-800"),"SYSDATE","WA","10",null);
//			lHandler.LagerBuchung(l,bStorno);
////
//			
//			l = new LAG_LagerBuchungsSatz("3112","1",new BigDecimal("10.57"),new BigDecimal("2000"),"SYSDATE","WE",null,null);
//			lHandler.LagerBuchung(l,bStorno);
//
//			
//			l = new LAG_LagerBuchungsSatz("3112","1",new BigDecimal("33.33"),new BigDecimal("-800"),"SYSDATE","WA","10","10");
//			lHandler.LagerBuchung(l,bStorno);
//			
//			l = new LAG_LagerBuchungsSatz("3112","1",new BigDecimal("44.33"),new BigDecimal("-8000"),"SYSDATE","WA",null,null);
//			lHandler.LagerBuchung(l,bStorno);
//
//			
//			
//			
//			// STornoSatz
//			l = new LAG_LagerBuchungsSatz("3112","1",new BigDecimal("0"),new BigDecimal("0"),"SYSDATE","WA","10","10");
//			lHandler.LagerBuchung(l,true);
//			
//			LAG_LagerBewegungHandler oBewegung = new LAG_LagerBewegungHandler();
//			oBewegung.ReorganiseLagerEntries("1", "3112", "1");
			
//			lHandler.LagerBuchung("24374");
//			
//			lHandler.LagerbuchungOrt("1034");
//			lHandler.LagerbuchungOrt("1035");
//			lHandler.LagerbuchungOrt("1036");
//			lHandler.LagerbuchungOrt("1037");
//			lHandler.LagerbuchungOrt("1038");
//			lHandler.LagerbuchungOrt("1039");
//			lHandler.LagerbuchungOrt("1040");
//			lHandler.LagerbuchungOrt("1041");
//			lHandler.LagerbuchungOrt("1042");
//			lHandler.LagerbuchungOrt("1043");

			
			
		}

		
		
	}
	
}
