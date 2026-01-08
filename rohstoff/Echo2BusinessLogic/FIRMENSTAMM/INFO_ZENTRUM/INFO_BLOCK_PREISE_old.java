package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.UserSettings.XXX_UserSetting;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField_month_year;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

public class INFO_BLOCK_PREISE_old extends MyE2_Grid 
{

	private MyE2_SelectField_month_year  o_1_SelField = null;
	
	private cbAuswahlWasZeigt 			cb_2_EK = null;
	private cbAuswahlWasZeigt 			cb_3_VK = null;

	private MyE2_TextField    			oTF_Suche = 	new MyE2_TextField("",60,30);
	private MyE2_Button       			oBT_Refresh = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png")); 

	
	private BigDecimal 	 				BD0 = new BigDecimal(0);

	private MyE2_ContainerEx  			oContainerEx = new MyE2_ContainerEx();
	
	private Vector<Component> vSelcomponents = new Vector<Component>();

	private FS__Adress_Info_Zentrum   oZentrum = null;
	
	public INFO_BLOCK_PREISE_old(FS__Adress_Info_Zentrum oInfoZentrum, boolean bAufbau) throws myException 
	{
		super(11, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
		
		this.oContainerEx.setWidth(new Extent(100, Extent.PERCENT));
		this.oContainerEx.setHeight(new Extent(350));   //anfangshoehe
		this.oContainerEx.setBorder(new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));

		oZentrum = oInfoZentrum;
		
		//ausblenden (wie in fuhrenzentrale)
		this.o_1_SelField =         new MyE2_SelectField_month_year(36,2,false);
		this.cb_2_EK=				new cbAuswahlWasZeigt("EK",				new MyE2_String("Zeigt EK-Preise an") ,	 	true, false);
		this.cb_3_VK=				new cbAuswahlWasZeigt("VK",				new MyE2_String("Zeigt VK-Preise an") ,		true, false);

		
		this.o_1_SelField.add_oActionAgent(	   	new actionNeubauFuhrenGrid());
		this.cb_2_EK.add_oActionAgent(			new actionNeubauFuhrenGrid());
		this.cb_3_VK.add_oActionAgent(			new actionNeubauFuhrenGrid());
		this.oBT_Refresh.add_oActionAgent(		new actionCheckHighlight());

		
		//dann status speichern
		this.o_1_SelField.add_oActionAgent(		new actionSaveStatus());
		this.cb_2_EK.add_oActionAgent(			new actionSaveStatus());
		this.cb_3_VK.add_oActionAgent(			new actionSaveStatus());
		this.oBT_Refresh.add_oActionAgent(		new actionSaveStatus());
		

		vSelcomponents.add(new MyE2_Label(new MyE2_String("Gueltig ab:"), new E2_FontItalic(-2)));
		vSelcomponents.add(this.o_1_SelField);
		
		vSelcomponents.add(this.cb_2_EK);
		vSelcomponents.add(this.cb_3_VK);
		
		vSelcomponents.add(this.oTF_Suche);
		vSelcomponents.add(this.oBT_Refresh);
		
		this.restore_status_der_selektionen();
		
		if (bAufbau)
		{
			this.__aufbau();
		}
		
	}
	

	
	public void __aufbau() throws myException
	{
		this.removeAll();
		
		RECORD_ADRESSE recADRESSE = this.oZentrum.get_recADRESSE();

		
		String cQueryBase = "SELECT JT_VPOS_STD.*       FROM "+bibE2.cTO()+".JT_VPOS_STD  " +
										" LEFT OUTER    JOIN "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT ON (JT_VPOS_STD.ID_VPOS_STD=JT_VPOS_STD_ANGEBOT.ID_VPOS_STD) "+
										" LEFT OUTER    JOIN "+bibE2.cTO()+".JT_VKOPF_STD ON (JT_VKOPF_STD.ID_VKOPF_STD=JT_VPOS_STD.ID_VKOPF_STD) "+
		 					" WHERE  NVL(JT_VPOS_STD.DELETED,'N')='N' AND NVL(JT_VKOPF_STD.DELETED,'N')='N' AND JT_VPOS_STD.EINZELPREIS_FW IS NOT NULL AND JT_VKOPF_STD.ID_ADRESSE="+recADRESSE.get_ID_ADRESSE_cUF();
		
		String cWhereAnteilSelDatumLieferant = 	this.o_1_SelField.get_ActualWert().trim().equals("")?"":" AND NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYY-MM'),'9999-99')>="+bibALL.MakeSql(this.o_1_SelField.get_ActualWert());
		String cWhereAnteilSelDatumAbnehmer = 	this.o_1_SelField.get_ActualWert().trim().equals("")?"":" AND NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYY-MM'),'9999-99')>="+bibALL.MakeSql(this.o_1_SelField.get_ActualWert());

		
		String cQueryEK = cQueryBase+" AND JT_VKOPF_STD.VORGANG_TYP="+bibALL.MakeSql(myCONST.VORGANGSART_ABNAHMEANGEBOT)+	cWhereAnteilSelDatumLieferant+" ORDER BY JT_VPOS_STD_ANGEBOT.GUELTIG_VON,JT_VPOS_STD.ANR1";
		String cQueryVK = cQueryBase+" AND JT_VKOPF_STD.VORGANG_TYP="+bibALL.MakeSql(myCONST.VORGANGSART_ANGEBOT)+			cWhereAnteilSelDatumAbnehmer+" 	ORDER BY JT_VPOS_STD_ANGEBOT.GUELTIG_VON,JT_VPOS_STD.ANR1";
		

		//nur die noetigen records aufbauen
		RECLIST_VPOS_STD reclistVPOS_STD_EK = this.cb_2_EK.isSelected()?new RECLIST_VPOS_STD(cQueryEK):new RECLIST_VPOS_STD();
		RECLIST_VPOS_STD reclistVPOS_STD_VK = this.cb_3_VK.isSelected()?new RECLIST_VPOS_STD(cQueryVK):new RECLIST_VPOS_STD();
		
		boolean bHasEK = false;
		boolean bHasVK = false;

		Vector<RECORD_VPOS_STD_own>  vEK_Preise = new Vector<RECORD_VPOS_STD_own>();
		Vector<RECORD_VPOS_STD_own>  vVK_Preise = new Vector<RECORD_VPOS_STD_own>();
		
		for (int i=0;i<reclistVPOS_STD_EK.get_vKeyValues().size();i++)
		{
			vEK_Preise.add(new RECORD_VPOS_STD_own(reclistVPOS_STD_EK.get(i),reclistVPOS_STD_EK.get(i).get_ANR1_cUF_NN("-")+"-"+reclistVPOS_STD_EK.get(i).get_ANR2_cUF_NN("-"),true));
			bHasEK = true;
		}
		
		for (int i=0;i<reclistVPOS_STD_VK.get_vKeyValues().size();i++)
		{
			vVK_Preise.add(new RECORD_VPOS_STD_own(reclistVPOS_STD_VK.get(i),reclistVPOS_STD_VK.get(i).get_ANR1_cUF_NN("-")+"-"+reclistVPOS_STD_VK.get(i).get_ANR2_cUF_NN("-"),true));
			bHasVK = true;
		}

		
		if (bHasEK)
		{
			//ueberschrift
			this.add(new ownLabelLeftTitel("Typ"));
			this.add(new ownLabelLeftTitel("Buch-Nr."));
			this.add(new ownLabelLeftTitel("Gültig"));
			this.add(new ownLabelRightTitel("FU-Plan"));
			this.add(new ownLabelRightTitel("FU-LadeM."));
			this.add(new ownLabelLeftTitel("ANR1-2"));
			this.add(new ownLabelLeftTitel("Artbez1"));
			this.add(new ownLabelLeftTitel("Artbez2"));
			this.add(new ownLabelRightTitel("Preis"));
			this.add(new ownLabelLeftTitel("-"));
			this.add(new ownLabelLeftTitel("-"));
			//this.add(new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE(reclistFuhrenAlsLieferant.get_vKeyValues(), this.oZentrum.get_oContainerToCloseAfterJump()));
			
			for (int i=0;i<vEK_Preise.size();i++)
			{
				RECORD_VPOS_STD_own  recVSTD = vEK_Preise.get(i);
				
				BigDecimal[] bdMengePlanEcht =  recVSTD.get_MengeGeliefertPlanEcht();   //    recVKON.get_MengeGeliefertPlanEcht();
				//BigDecimal[] bdMengePlanEcht = {BD0,BD0,BD0};
				
				this.add(new ownLabelLeft("EK",recVSTD));
				
				String cBuchungsNummer = recVSTD.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_BUCHUNGSNUMMER_cUF_NN("<ID:"+recVSTD.get_ID_VKOPF_STD_cF()+">");
				this.add(new ownLabelLeft(cBuchungsNummer,recVSTD));
				
				String cGueltig = recVSTD.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_VON_cF_NN("??.??.????").substring(0,6);
				cGueltig += (" - "+recVSTD.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_BIS_cF_NN("??.??.????").substring(0,6));
				this.add(new ownLabelLeft(cGueltig,recVSTD));
				this.add(new ownLabelRight(MyNumberFormater.formatDez( bdMengePlanEcht[0],0,true),recVSTD));
				this.add(new ownLabelRight(MyNumberFormater.formatDez( bdMengePlanEcht[1],0,true),recVSTD));
				this.add(new ownLabelLeft(recVSTD.get_ANR1_cUF_NN("")+"-"+recVSTD.get_ANR2_cUF_NN(""),recVSTD));
				this.add(new ownLabelLeft(recVSTD.get_ARTBEZ1_cF_NN("<Artbez1>"),recVSTD));
				this.add(new ownLabelLeft(recVSTD.get_ARTBEZ2_cF_NN("<Artbez2>"),recVSTD));
				this.add(new ownLabelRight(MyNumberFormater.formatDez( recVSTD.get_EINZELPREIS_FW_bdValue(BD0),2,true),recVSTD));
				this.add(new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_ANGEBOT(	
											bibALL.get_Vector(recVSTD.get_ID_VKOPF_STD_cUF()), 
											this.oZentrum.get_oContainerToCloseAfterJump(),"EK",
											this.oZentrum.is_jump_is_active()));
				
				
				Vector<String> vID_Fuhren = new Vector<String>();
				RECLIST_VPOS_TPA_FUHRE  reclistFuhren = recVSTD.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_std_ek("NVL(DELETED,'N')='N'",null,true);
				RECLIST_VPOS_TPA_FUHRE_ORT  reclistFuhrenOrt = recVSTD.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_std("NVL(DELETED,'N')='N'",null,true);
				vID_Fuhren.addAll(reclistFuhren.get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("-1").values());
				vID_Fuhren.addAll(reclistFuhrenOrt.get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("-1").values());
				this.add(new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE(	vID_Fuhren, 
																						this.oZentrum.get_oContainerToCloseAfterJump(),
																						this.oZentrum.is_jump_is_active()));
				
			}
		}

		
		if (bHasVK)
		{
			if (bHasEK )
			{
				//trenner einbauen
				this.add(new Separator(),this.getSize(),E2_INSETS.I_1_1_1_1);
			}

			
			//ueberschrift
//			this.add(new ownLabelRightTitel("Abnehmer"));
			this.add(new ownLabelLeftTitel("Typ"));
			this.add(new ownLabelLeftTitel("Buch-Nr."));
			this.add(new ownLabelLeftTitel("Gültig"));
//			this.add(new ownLabelRightTitel("Menge"));
			this.add(new ownLabelRightTitel("FU-Plan"));
			this.add(new ownLabelRightTitel("FU-LadeM."));
//			this.add(new ownLabelRightTitel("Rech."));
			this.add(new ownLabelLeftTitel("ANR1-2"));
			this.add(new ownLabelLeftTitel("Artbez1"));
			this.add(new ownLabelLeftTitel("Artbez2"));
			this.add(new ownLabelRightTitel("Preis"));
			this.add(new ownLabelLeftTitel("-"));
			this.add(new ownLabelLeftTitel("-"));
			
			for (int i=0;i<vVK_Preise.size();i++)
			{
				RECORD_VPOS_STD_own  recVSTD = vVK_Preise.get(i);
				
				BigDecimal[] bdMengePlanEcht =  recVSTD.get_MengeGeliefertPlanEcht();
//				BigDecimal   bdBerechnet     =  BD0;
				
//				this.add(new ownLabelLeft(recVSTD.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_UP_RECORD_ADRESSE_id_adresse().get___KETTE(bibALL.get_Vector("NAME1", "ORT"))));
				this.add(new ownLabelLeft("VK",recVSTD));
				
				String cBuchungsNummer = recVSTD.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_BUCHUNGSNUMMER_cUF_NN("<ID:"+recVSTD.get_ID_VKOPF_STD_cF()+">");
				this.add(new ownLabelLeft(cBuchungsNummer,recVSTD));
				
				String cGueltig = recVSTD.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_VON_cF_NN("??.??.????").substring(0,6);
				cGueltig += (" - "+recVSTD.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_BIS_cF_NN("??.??.????").substring(0,6));
				this.add(new ownLabelLeft(cGueltig,recVSTD));
//				this.add(new ownLabelRight(MyNumberFormater.formatDez( recVSTD.get_ANZAHL_bdValue(BD0),0,true),recVSTD));
				this.add(new ownLabelRight(MyNumberFormater.formatDez( bdMengePlanEcht[0],0,true),recVSTD));
				this.add(new ownLabelRight(MyNumberFormater.formatDez( bdMengePlanEcht[1],0,true),recVSTD));
//				this.add(new ownLabelRight(MyNumberFormater.formatDez( bdBerechnet,0,true),recVSTD));
				this.add(new ownLabelLeft(recVSTD.get_ANR1_cUF_NN("")+"-"+recVSTD.get_ANR2_cUF_NN(""),recVSTD));
				this.add(new ownLabelLeft(recVSTD.get_ARTBEZ1_cF_NN("<Artbez1>"),recVSTD));
				this.add(new ownLabelLeft(recVSTD.get_ARTBEZ2_cF_NN("<Artbez2>"),recVSTD));
				this.add(new ownLabelRight(MyNumberFormater.formatDez( recVSTD.get_EINZELPREIS_FW_bdValue(BD0),2,true),recVSTD));
				this.add(new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_ANGEBOT(	bibALL.get_Vector(recVSTD.get_ID_VKOPF_STD_cUF()), 
																							this.oZentrum.get_oContainerToCloseAfterJump(),
																							"VK",
																							this.oZentrum.is_jump_is_active()));

				
//				this.add(new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_KONTRAKT(bibALL.get_Vector(recVSTD.get_ID_VKOPF_STD_cUF()), this.oZentrum.get_oContainerToCloseAfterJump(),"VK"));
//				this.add(new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_KONTRAKT_Clearing(bibALL.get_Vector(recVSTD.get_ID_VPOS_STD_cUF()), this.oZentrum.get_oContainerToCloseAfterJump(),"VK"));
				
				Vector<String> vID_Fuhren = new Vector<String>();
				RECLIST_VPOS_TPA_FUHRE  reclistFuhren = recVSTD.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_std_vk("NVL(DELETED,'N')='N'",null,true);
				RECLIST_VPOS_TPA_FUHRE_ORT  reclistFuhrenOrt = recVSTD.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_std("NVL(DELETED,'N')='N'",null,true);
				vID_Fuhren.addAll(reclistFuhren.get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("-1").values());
				vID_Fuhren.addAll(reclistFuhrenOrt.get_ID_VPOS_TPA_FUHRE_hmString_UnFormated("-1").values());
				this.add(new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE(	vID_Fuhren, 
																						this.oZentrum.get_oContainerToCloseAfterJump(),
																						this.oZentrum.is_jump_is_active()));
				
			}
		}
		
		this.__CheckHighLight();
	}
	
	
	public void __CheckHighLight() throws myException
	{
	    Component[] vAllComponents = INFO_BLOCK_PREISE_old.this.getComponents();
	    
	    //einfach pruefen, ob ein label einen string enthaelt
	    Vector<RECORD_VPOS_STD_own>  vFuhrenHighlight = new Vector<INFO_BLOCK_PREISE_old.RECORD_VPOS_STD_own>();
	    
	    String cSuchText = this.oTF_Suche.getText();
	    
	    for (int i=0;i<vAllComponents.length;i++)
	    {
	    	if (vAllComponents[i] instanceof ownLabelLeft)
	    	{
	    		String cTextAufLabel =   ((ownLabelLeft)vAllComponents[i]).getText()+"@@@@@@@"+bibALL.ReplaceTeilString(((ownLabelLeft)vAllComponents[i]).getText(), ".", "");
	    		if (S.isFull(cSuchText) && cTextAufLabel.indexOf(cSuchText)>=0)
	    		{
	    			if (!vFuhrenHighlight.contains(((ownLabelLeft)vAllComponents[i]).recV_STD))
	    			{
	    				vFuhrenHighlight.add(((ownLabelLeft)vAllComponents[i]).recV_STD);
	    			}
	    		}
	    	}
	    	if (vAllComponents[i] instanceof ownLabelRight)
	    	{
	    		String cTextAufLabel =   ((ownLabelRight)vAllComponents[i]).getText()+"@@@@@@@"+bibALL.ReplaceTeilString(((ownLabelRight)vAllComponents[i]).getText(), ".", "");
	    		if (S.isFull(cSuchText) && cTextAufLabel.indexOf(cSuchText)>=0)
	    		{
	    			if (!vFuhrenHighlight.contains(((ownLabelRight)vAllComponents[i]).recV_STD))
	    			{
	    				vFuhrenHighlight.add(((ownLabelRight)vAllComponents[i]).recV_STD);
	    			}
	    		}
	    	}
	    }

	    
	    for (int i=0;i<vAllComponents.length;i++)
	    {
	    	if (vAllComponents[i] instanceof ownLabelRight)
	    	{
	    		if (vFuhrenHighlight.contains( ((ownLabelRight)vAllComponents[i]).recV_STD))
	    		{
	    			((ownLabelRight)vAllComponents[i]).setLayoutData(this.oZentrum.get_GL_Highlight_Right());
	    		}
	    		else
	    		{
	    			((ownLabelRight)vAllComponents[i]).setLayoutData(this.oZentrum.get_GL_Normal_Right());
	    		}
	    	}
	    	if (vAllComponents[i] instanceof ownLabelLeft)
	    	{
	    		if (vFuhrenHighlight.contains( ((ownLabelLeft)vAllComponents[i]).recV_STD))
	    		{
	    			((ownLabelLeft)vAllComponents[i]).setLayoutData(this.oZentrum.get_GL_Highlight_Left());
	    		}
	    		else
	    		{
	    			((ownLabelLeft)vAllComponents[i]).setLayoutData(this.oZentrum.get_GL_Normal_Left());
	    		}
	    	}
	    }
	    
	    
	    
	}

	
	

	
	public String get_Status_der_Selektoren() throws myException
	{
		
		String cRueck = "";
		cRueck += S.NN(this.o_1_SelField.get_ActualWert());
		cRueck += "|";
		cRueck += (this.cb_2_EK.isSelected()?"Y":"N");
		cRueck += "|";
		cRueck += (this.cb_3_VK.isSelected()?"Y":"N");
		
		cRueck += "|";
		cRueck += (this.oTF_Suche.getText());
		
		return cRueck;
	}

	
	
	private void restore_status_der_selektionen() throws myException
	{
		//hier wird die einstellung gleich in dieser methode gemacht, rueckgabe ist unnoetig
		String cDatabaseSetting = (String)new E2_UserSettings_Checkbox_und_Selektoren().get_Settings(FS__Adress_Info_Zentrum.STORE_MODUL_KENNER);
		
		if (cDatabaseSetting!=null)
		{
			Vector<String> vWerte= bibALL.TrenneZeile(cDatabaseSetting, "|");
			
			if (vWerte.size()>0) o_1_SelField.set_ActiveValue_OR_FirstValue(vWerte.get(0));
			if (vWerte.size()>1) cb_2_EK.setSelected(vWerte.get(1).equals("Y"));
			if (vWerte.size()>2) cb_3_VK.setSelected(vWerte.get(2).equals("Y"));
			if (vWerte.size()>3) this.oTF_Suche.setText(vWerte.get(3));
		}
	}

	
	
	private E2_ComponentGroupHorizontal get_BedienzeileFuerFuhrenliste() 
	{
		return new E2_ComponentGroupHorizontal(0,vSelcomponents,new Insets(2,0,5,0));
	}
	
	//gibt ein zweizeiliges grid zurueck mit bedienzeile und eigentlicher liste in containerEx
	public MyE2_Grid get_ContainerGridMitBedienZeile() throws myException
	{
		MyE2_Grid gridRueck = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.oContainerEx.removeAll();
		this.oContainerEx.add(this);
		
		gridRueck.add(this.get_BedienzeileFuerFuhrenliste());
		gridRueck.add(oContainerEx);
		
		return gridRueck;
	}

	
	
	//ein user-setting-objekt
	private class E2_UserSettings_Checkbox_und_Selektoren extends XXX_UserSetting 
	{

		private String cSessionHash = "SESSION_HASH_FIRMENSTAMM_JUMPBOX_SPEICHERE_SELECTOR_PREISE";
		
		public E2_UserSettings_Checkbox_und_Selektoren() 
		{
			super();
		}

		@Override
		public String get_SessionHash() 
		{
			return this.cSessionHash;
		}

		@Override
		protected String get_OBJECT_TO_STRING(Object oSetting) throws myException 
		{
			return (String)oSetting;
		}

		@Override
		protected Object get_STRING_TO_OBJECT(String cDatabaseSetting)	throws myException 
		{
			return cDatabaseSetting;
		}
		
		
	}
	
	private class actionSaveStatus extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			new E2_UserSettings_Checkbox_und_Selektoren().STORE(FS__Adress_Info_Zentrum.STORE_MODUL_KENNER, INFO_BLOCK_PREISE_old.this.get_Status_der_Selektoren());
		}
	}

	
	private class cbAuswahlWasZeigt extends MyE2_CheckBox
	{
		public cbAuswahlWasZeigt(Object cText, MyE2_String cToolTipText,
				boolean bIsSelected, boolean bSetDisabledFromBasic) {
			super(cText, cToolTipText, bIsSelected, bSetDisabledFromBasic);
		}
	}

	
	private class ownLabelLeft extends MyE2_Label 
	{
		public RECORD_VPOS_STD_own  recV_STD = null;
		
		public ownLabelLeft(String cWert,RECORD_VPOS_STD_own  recVSTD ) 
		{
			super(S.NN(cWert), MyE2_Label.STYLE_SMALL_PLAIN());
			this.recV_STD=recVSTD;
			this.setLayoutData(INFO_BLOCK_PREISE_old.this.oZentrum.get_GL_Normal_Left());
		}
	}
	
	private class ownLabelRight extends MyE2_Label 
	{
		public RECORD_VPOS_STD_own recV_STD = null;

		public ownLabelRight(String cWert,RECORD_VPOS_STD_own  recVSTD) 
		{
			super(S.NN(cWert), MyE2_Label.STYLE_SMALL_PLAIN());
			this.recV_STD=recVSTD;
			this.setLayoutData(INFO_BLOCK_PREISE_old.this.oZentrum.get_GL_Normal_Right());
		}
	}
	

	private class ownLabelLeftTitel extends MyE2_Label 
	{

		public ownLabelLeftTitel(String cWert) 
		{
			super(S.NN(cWert), MyE2_Label.STYLE_SMALL_PLAIN());
			this.setLayoutData(INFO_BLOCK_PREISE_old.this.oZentrum.get_GL_Titel_Left());
		}
		
	}
	
	private class ownLabelRightTitel extends MyE2_Label 
	{

		public ownLabelRightTitel(String cWert) 
		{
			super(S.NN(cWert), MyE2_Label.STYLE_SMALL_PLAIN());
			GridLayoutData Gl = MyE2_Grid.LAYOUT_RIGHT_TOP(E2_INSETS.I_4_2_4_0);
			Gl.setBackground(new E2_ColorDDark());
			this.setLayoutData(INFO_BLOCK_PREISE_old.this.oZentrum.get_GL_Titel_Right());
		}
		
	}

	public class actionNeubauFuhrenGrid extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			INFO_BLOCK_PREISE_old.this.__aufbau();
		}
	}


	
	private class RECORD_VPOS_STD_own extends RECORD_VPOS_STD
	{
		public String   					ANR1_2 = "";
		public Boolean   					bEK = false;
		
		private BigDecimal[]  				arrayBDMengePlanEcht = null;
		private BigDecimal    				bdMengeBerechnet = null;


		public RECORD_VPOS_STD_own(RECORD_VPOS_STD rec_vposSTD, String _ANR1_2, boolean EK) throws myException 
		{
			super(rec_vposSTD);
			this.ANR1_2 = _ANR1_2;
			this.bEK = EK;
		}
		
		/**
		 * 
		 * @return s Bigdecimal[2]: (EK:) plan oder lade / lade    (VK:) plan oder ablade / ablade
		 *           [0] = plan
		 *           [1] = lade
		 *           [2] = realer Abzug
		 * @throws myException
		 */
		public BigDecimal[]  get_MengeGeliefertPlanEcht() throws myException
		{
			
			if (this.arrayBDMengePlanEcht!=null)
			{
				return this.arrayBDMengePlanEcht;        //nur einmal berechnen
			}
			else
			{
			
				this.arrayBDMengePlanEcht = new BigDecimal[3];
				
				this.arrayBDMengePlanEcht[0] = new BigDecimal(0);  //plan
				this.arrayBDMengePlanEcht[1] = new BigDecimal(0);  //echt
				this.arrayBDMengePlanEcht[2] = new BigDecimal(0);  //abzug (nicht inhalt)
				
		
				if (this.bEK)
				{
					RECLIST_VPOS_TPA_FUHRE   			reclistFuhre = 		this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_std_ek("NVL(DELETED,'N')='N'",null,true);
					RECLIST_VPOS_TPA_FUHRE_ORT   		reclistFuhreOrt = 	this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_std("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='EK'",null,true);
					
					Iterator<RECORD_VPOS_TPA_FUHRE> 	iteratorFuhre =  	reclistFuhre.values().iterator();
					Iterator<RECORD_VPOS_TPA_FUHRE_ORT> iteratorFuhreOrt =  reclistFuhreOrt.values().iterator();
		
					while (iteratorFuhre.hasNext())
					{
						RECORD_VPOS_TPA_FUHRE recFuhre = iteratorFuhre.next();
						if (!(recFuhre.is_DELETED_YES() || recFuhre.is_IST_STORNIERT_YES()))
						{
							this.arrayBDMengePlanEcht[0]=this.arrayBDMengePlanEcht[0].add(recFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(recFuhre.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(recFuhre.get_ANTEIL_PLANMENGE_LIEF_bdValue(new BigDecimal(0)))));
		
							this.arrayBDMengePlanEcht[1]=this.arrayBDMengePlanEcht[1].add(recFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(recFuhre.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(new BigDecimal(0))));
							
							this.arrayBDMengePlanEcht[2]=this.arrayBDMengePlanEcht[2].add(recFuhre.get_ABZUG_LADEMENGE_LIEF_bdValue(new BigDecimal(0)));
						}
					}
					
					while (iteratorFuhreOrt.hasNext())
					{
						RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt = iteratorFuhreOrt.next();
						if (!(recFuhreOrt.is_DELETED_YES() || recFuhreOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().is_IST_STORNIERT_YES()))
						{
							this.arrayBDMengePlanEcht[0]=this.arrayBDMengePlanEcht[0].add(recFuhreOrt.get_ANTEIL_LADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_ABLADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_PLANMENGE_bdValue(new BigDecimal(0)))));
		
							this.arrayBDMengePlanEcht[1]=this.arrayBDMengePlanEcht[1].add(recFuhreOrt.get_ANTEIL_LADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_ABLADEMENGE_bdValue(new BigDecimal(0))));
							
							this.arrayBDMengePlanEcht[2]=this.arrayBDMengePlanEcht[2].add(recFuhreOrt.get_ABZUG_MENGE_bdValue(new BigDecimal(0)));
						}
					}
				}
				else
				{
					RECLIST_VPOS_TPA_FUHRE   			reclistFuhre = 		this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_std_ek("NVL(DELETED,'N')='N'",null,true);
					RECLIST_VPOS_TPA_FUHRE_ORT   		reclistFuhreOrt = 	this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_std("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='VK'",null,true);
					
					Iterator<RECORD_VPOS_TPA_FUHRE> 	iteratorFuhre =  	reclistFuhre.values().iterator();
					Iterator<RECORD_VPOS_TPA_FUHRE_ORT> iteratorFuhreOrt =  reclistFuhreOrt.values().iterator();
		
					while (iteratorFuhre.hasNext())
					{
						RECORD_VPOS_TPA_FUHRE recFuhre = iteratorFuhre.next();
						if (!(recFuhre.is_DELETED_YES() || recFuhre.is_IST_STORNIERT_YES()))
						{
							this.arrayBDMengePlanEcht[0]=this.arrayBDMengePlanEcht[0].add(recFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(recFuhre.get_ANTEIL_LADEMENGE_ABN_bdValue(recFuhre.get_ANTEIL_PLANMENGE_ABN_bdValue(new BigDecimal(0)))));
		
							this.arrayBDMengePlanEcht[1]=this.arrayBDMengePlanEcht[1].add(recFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(recFuhre.get_ANTEIL_LADEMENGE_ABN_bdValue(new BigDecimal(0))));
							
							this.arrayBDMengePlanEcht[2]=this.arrayBDMengePlanEcht[2].add(recFuhre.get_ABZUG_ABLADEMENGE_ABN_bdValue(new BigDecimal(0)));
						}
					}
					
					while (iteratorFuhreOrt.hasNext())
					{
						RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt = iteratorFuhreOrt.next();
						if (!(recFuhreOrt.is_DELETED_YES() || recFuhreOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().is_IST_STORNIERT_YES()))
						{
							this.arrayBDMengePlanEcht[0]=this.arrayBDMengePlanEcht[0].add(recFuhreOrt.get_ANTEIL_ABLADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_LADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_PLANMENGE_bdValue(new BigDecimal(0)))));
		
							this.arrayBDMengePlanEcht[1]=this.arrayBDMengePlanEcht[1].add(recFuhreOrt.get_ANTEIL_ABLADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_LADEMENGE_bdValue(new BigDecimal(0))));
							
							this.arrayBDMengePlanEcht[2]=this.arrayBDMengePlanEcht[2].add(recFuhreOrt.get_ABZUG_MENGE_bdValue(new BigDecimal(0)));
						}
					}
				}
				return this.arrayBDMengePlanEcht;
			}
		}
		

		


		
	}
	
	
	
	public class actionCheckHighlight extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			INFO_BLOCK_PREISE_old.this.__CheckHighLight();
		}
	}



	public MyE2_ContainerEx get_oContainerEx()
	{
		return oContainerEx;
	}

	


}
