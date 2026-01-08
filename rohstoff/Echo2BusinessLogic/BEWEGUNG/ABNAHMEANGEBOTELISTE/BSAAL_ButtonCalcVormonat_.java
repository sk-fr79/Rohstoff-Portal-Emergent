package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;


import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;


public class BSAAL_ButtonCalcVormonat_ extends MyE2_Button 
{

	private BSAAL__ModulContainerLIST	oModulContainerList = null;
	
	public BSAAL_ButtonCalcVormonat_(BSAAL__ModulContainerLIST	oModulContainer) 
	{
		super(new MyE2_String("Vormonat"));
		
		this.oModulContainerList = oModulContainer;
		this.add_IDValidator(new BSAAL_Validator_Kopf_IS_NOT_CLOSED());
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Vormonatspreise ausrechnen ...").CTrans());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oModulContainer.get_MODUL_IDENTIFIER(),BSAAL__CONST.BUTTON_CALC_VORMONATSPREIS));
		
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			BSAAL_ButtonCalcVormonat_ oThis = BSAAL_ButtonCalcVormonat_.this;
			
			// welche IDs 
			Vector<E2_ComponentMAP> vComponentMaps = oThis.oModulContainerList.get_oNaviList().get_vComponentMAPS();
			try
			{
			
				double 	dNeu = 0;
				int 	iCountDiffs = 0;
				
				
				for (int i=0;i<vComponentMaps.size();i++)
				{
					dNeu =0;
					
					E2_ComponentMAP 	oMap = (E2_ComponentMAP)vComponentMaps.get(i);
					MyE2_Label 			oLabVormonat = (MyE2_Label)oMap.get__Comp(BSAAL__CONST.HASH_KEY_ANZEIGE_VORMONAT);
					MyE2_TextField		oTF_ActualPreis = (MyE2_TextField)oMap.get__Comp("EINZELPREIS");
					if (!bibALL.isEmpty(oTF_ActualPreis.getText()))
					{
						DotFormatter oDF = new DotFormatter(oTF_ActualPreis.getText(),2,Locale.GERMAN,true,3);
						if (oDF.doFormat())
						{
							dNeu = oDF.getDoubleValue();
						}
					}
					
					BSAAL_VormonatsPreisCalkulator oVM = new BSAAL_VormonatsPreisCalkulator(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
					
					Double 			dVormonat = oVM.getDVormonatsPreis();

					oLabVormonat.setBackground(null);
					
					//2014-05-13: negative Preise auch aus dem vormonat anzeigen
//					if (dVormonat != null && dVormonat.doubleValue()>0)
					if (dVormonat != null)
					{
						DecimalFormat	dForm = new DecimalFormat("#,###,##0.00");
						String 			cVormonat = dForm.format(dVormonat.doubleValue());
						oLabVormonat.setText(cVormonat);
						
						if (Math.abs(new Double((dNeu*100d)))>=1d && Math.abs(new Double((dVormonat*100d)))>=1d)    //berechung wenn vormonatwert >0.01 absolut,
						{
							double dVerhaeltnis = 1-dNeu/dVormonat.doubleValue();
							if (Math.abs(dVerhaeltnis)>BSAAL__CONST.WarningVormonat)
							{
								oLabVormonat.setBackground(new E2_ColorAlarm());
								iCountDiffs++;
							} else if ( (dVormonat>0 && dNeu<0) || (dVormonat<0 && dNeu>0 )  ) {
								oLabVormonat.setBackground(new E2_ColorAlarm());
								iCountDiffs++;
							}
//						} else {
//							DEBUG.System_println("Neuberechnung nicht moeglich");
						}
						
					}
					else
					{
						oLabVormonat.setText("???");
					}
				}
				
				if (iCountDiffs>0)
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es wurden Preise mit Abweichungen gefunden ("+iCountDiffs+")"));
				
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error calculating former prices !!"));
			}
			
			
		}
	}
	
	
	
	private class BSAAL_VormonatsPreisCalkulator 
	{

	    private Double 		dVormonatsPreis = null;
	    

	    /**
	     * 
	     */
	    public BSAAL_VormonatsPreisCalkulator(String cID_VPOS) throws myException
	    {
	        super();
	        	
        	RECORD_VPOS_STD  recVPOS = new RECORD_VPOS_STD(cID_VPOS);
	        
            try
            {
                Integer		intMonth	= 		new Integer(recVPOS.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_VON_cUF().substring(5,7));
                Integer		intYear		= 		new Integer(recVPOS.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_VON_cUF().substring(0,4));
                Long 		intADRESSE = 		recVPOS.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_ID_ADRESSE_lValue(null);
                Long 		intARTIKEL_BEZ = 	recVPOS.get_ID_ARTIKEL_BEZ_lValue(null);
                
                /*
                 * jetzt vormonats-monat und -jahr rausfinden
                 */
                int iMonthBefore 	= intMonth.intValue()-1;
                int iYear 			= intYear.intValue();
                
                 
                if (iMonthBefore<=0)
                {
                    iMonthBefore=12;
                    iYear--;
                }
                
                String cQuery2 = "select avg( NVL(einzelpreis,0)) from " +
                				 bibE2.cTO()+".JT_VPOS_STD ,"+bibE2.cTO()+".JT_VKOPF_STD  ,"+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT  "+
                    			" where  JT_VKOPF_STD.ID_VKOPF_STD = JT_VPOS_STD.ID_VKOPF_STD " +
                    			" and    JT_VPOS_STD.ID_VPOS_STD = JT_VPOS_STD_ANGEBOT.ID_VPOS_STD " +
                    			" and    JT_VKOPF_STD.vorgang_typ = 'ABNAHMEANGEBOT'"+
                    			" AND      NVL(JT_VPOS_STD.DELETED,'N')='N' "+
                    			" and    JT_VPOS_STD.POSITION_TYP = "+bibALL.MakeSql(myCONST.VG_POSITION_TYP_ARTIKEL)+
                    			" and    TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.gueltig_von,'MM'))="+iMonthBefore+
                    			" and    TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.gueltig_von,'YYYY'))="+iYear+
			        			" and    JT_VKOPF_STD.ID_ADRESSE="+intADRESSE.intValue()+ 
			        			" and    JT_VPOS_STD.ID_ARTIKEL_BEZ="+intARTIKEL_BEZ.intValue(); 
			    
                String[][] cVormonatsWert= bibDB.EinzelAbfrageInArray(cQuery2,"");
                
                if (cVormonatsWert != null && cVormonatsWert.length > 0)
                {
               		this.dVormonatsPreis = new Double(cVormonatsWert[0][0]);
                }
                
            }
            catch (Exception ex)
            {}
	    }
	    
	    public Double getDVormonatsPreis()
	    {
	        return dVormonatsPreis;
	    }
	 }
	
}
