package panter.gmbh.Echo2.__BASIC_MODULS.ADMINTOOLS;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.MyAdress;
import echopointng.Separator;


/*
 * Werkzeug, um alle nicht besetzten felder der Adressen zu fuellen, die als infos
 * fuer die EU-Beiblaetter benoetigt werden ( 
 * EU_BEIBLATT_EMAIL
 * EU_BEIBLATT_ANSPRECH
 * EU_BEIBLATT_TEL
 * EU_BEIBLATT_FAX
 */
public class Tool_Fill_EU_BEIBLATT_INFOFELDER extends MyE2_Column 
{

	private MyE2_CheckBox oCBUeberschreibeAlle = new MyE2_CheckBox(new MyE2_String("Alle, auch bereits besetzt neu schreiben ..."));
	
	public Tool_Fill_EU_BEIBLATT_INFOFELDER() 
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
	
		this.add(new MyE2_Label(new MyE2_String("Ausfüllhilfe für die Felder EU_BEIBLATT_TEL/EU_BEIBLATT_FAX/EU_BEIBLATT_EMAIL/EU_BEIBLATT_ANSPRECH")), E2_INSETS.I_10_2_10_2);
		this.add(new MyE2_Label(new MyE2_String("für alle Adresse im Adress-Stamm.")), E2_INSETS.I_10_2_10_2);
		this.add(new MyE2_Label(new MyE2_String("Lieferadressen werden mit den Infos aus den Basis-Adressen gefuellt .")), E2_INSETS.I_10_2_10_2);
		
		this.add(new Separator(), E2_INSETS.I_10_2_10_2);
		
		this.oCBUeberschreibeAlle.setSelected(false);
		MyE2_Button oButtonStart = new MyE2_Button("Start !");
		
		this.add(new E2_ComponentGroupHorizontal(0,
				this.oCBUeberschreibeAlle,
				oButtonStart, E2_INSETS.I_10_2_10_2));
		
		oButtonStart.add_oActionAgent(new ownActionAgent());
		oButtonStart.add_GlobalValidator(new E2_ButtonAUTH_ONLY_ADMIN());
		
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		
		private int iFehler = 0;
		private int iZuAendernHauptAdresse =  0;
		private int iZuAendernLieferAdresse =  0;
		
		private int iGeaendert = 0;
		private int iUebersprungen = 0;

		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			
			String cQuery = "SELECT ID_ADRESSE,ADRESSTYP,EU_BEIBLATT_TEL, EU_BEIBLATT_FAX, EU_BEIBLATT_EMAIL, EU_BEIBLATT_ANSPRECH  FROM "+bibE2.cTO()+".JT_ADRESSE " +
					" WHERE ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO+
					" OR    ADRESSTYP="+myCONST.ADRESSTYP_LIEFERADRESSE;
			

			String cAntwort[][] = bibDB.EinzelAbfrageInArray(cQuery, "");
			
			if (cAntwort == null || cAntwort.length==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Adress-Abfrage !"));
			}
			else
			{
				for (int i=0;i<cAntwort.length;i++)
				{
					if (cAntwort[i][1].equals(""+myCONST.ADRESSTYP_FIRMENINFO))
					{
						iZuAendernHauptAdresse++;
						try
						{
							this.SetAdress(cAntwort[i][0], cAntwort[i][0], cAntwort[i][2], cAntwort[i][3], cAntwort[i][4], cAntwort[i][5]);
						}
						catch (myException ex)
						{
							iFehler++;
						}
					}
					if (cAntwort[i][1].equals(""+myCONST.ADRESSTYP_LIEFERADRESSE))
					{
						iZuAendernLieferAdresse++;
						// dann zuerst die BASIS-Adresse beschaffen
						
						String cQuery2 = "SELECT ID_ADRESSE_BASIS FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_LIEFER="+cAntwort[i][0];
						
						String cID_ADRESSE_BASIS = bibDB.EinzelAbfrage(cQuery2);
						
						if (!bibALL.isLong(cID_ADRESSE_BASIS))
						{
							DEBUG.System_println(" ######### Fehler beim Lesen der Basis-Adresse zu Lieferadresse "+cAntwort[i][0], "");
							iFehler++;
						}
						else
						{
							try
							{
								this.SetAdress(cAntwort[i][0], cID_ADRESSE_BASIS, cAntwort[i][2], cAntwort[i][3], cAntwort[i][4], cAntwort[i][5]);
							}
							catch (myException ex)
							{
								iFehler++;
							}
						}
					}
				}
			}
			
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Gesamte Adressen: "+(cAntwort!=null?cAntwort.length:0)));
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Haupt-Adressen: "+iZuAendernHauptAdresse));
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Liefer-Adressen: "+iZuAendernLieferAdresse));

			bibMSG.add_MESSAGE(new MyE2_Info_Message("geändert: "+iGeaendert));
			bibMSG.add_MESSAGE(new MyE2_Info_Message("übersprungen: "+iUebersprungen));
			
			if (iFehler>0)
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler: "+iFehler));
			
		}
		
		
		
		private void SetAdress(	String cID_ZielAdresse,
								String cID_BasisAdresse,
								String cOLD_TEL,
								String cOLD_FAX,
								String cOLD_MAIL,
								String cOLD_Ansprech
								) throws myException
		{
			Tool_Fill_EU_BEIBLATT_INFOFELDER oThis = Tool_Fill_EU_BEIBLATT_INFOFELDER.this;
			
			if ((iGeaendert+iUebersprungen)%50==0)
				DEBUG.System_println("Bearbeitet: "+(iGeaendert+iUebersprungen), "");

			
			
			if ( ( ! bibALL.isEmpty(cOLD_TEL) ||
				   ! bibALL.isEmpty(cOLD_FAX) || 
				   ! bibALL.isEmpty(cOLD_MAIL) || 
				   ! bibALL.isEmpty(cOLD_Ansprech) ) && !oThis.oCBUeberschreibeAlle.isSelected())
			{
				iUebersprungen++;
				return;
			}

			MyAdress oAdressBase = new MyAdress(cID_BasisAdresse,true);
			
			String cStandardTel = oAdressBase.get_StandardTelefonNumber();
			String cStandardFax = oAdressBase.get_StandardFaxNumber();
			String cStandardEmail = oAdressBase.get_EmailForALL_Or_FirstEmail(myCONST.EMAIL_TYPE_VALUE_ALLTYPES);
			String cStandardAnsprech = oAdressBase.get_StandardAnsprechpartner(true, false, true, true, false, false);
			
			if (cStandardTel.length()>50) cStandardTel = cStandardTel.substring(0,50);
			if (cStandardFax.length()>50) cStandardFax = cStandardFax.substring(0,50);
			if (cStandardEmail.length()>50) cStandardEmail = cStandardEmail.substring(0,50);
			if (cStandardAnsprech.length()>50) cStandardAnsprech = cStandardAnsprech.substring(0,50);
			
			String cUpdateTel = "UPDATE "+bibE2.cTO()+".JT_ADRESSE SET EU_BEIBLATT_TEL="+bibALL.MakeSql(cStandardTel)+" WHERE ID_ADRESSE="+cID_ZielAdresse;
			String cUpdateFax = "UPDATE "+bibE2.cTO()+".JT_ADRESSE SET EU_BEIBLATT_FAX="+bibALL.MakeSql(cStandardFax)+" WHERE ID_ADRESSE="+cID_ZielAdresse;
			String cUpdateEMail = "UPDATE "+bibE2.cTO()+".JT_ADRESSE SET EU_BEIBLATT_EMAIL="+bibALL.MakeSql(cStandardEmail)+" WHERE ID_ADRESSE="+cID_ZielAdresse;
			String cUpdateAnsprech = "UPDATE "+bibE2.cTO()+".JT_ADRESSE SET EU_BEIBLATT_ANSPRECH="+bibALL.MakeSql(cStandardAnsprech)+" WHERE ID_ADRESSE="+cID_ZielAdresse;

			
			
			
			Vector<String> vSQL = new Vector<String>();
			if (oThis.oCBUeberschreibeAlle.isSelected())
			{
				vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cUpdateTel);
				vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cUpdateFax);
				vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cUpdateEMail);
				vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cUpdateAnsprech);
			}
			else
			{
				if (bibALL.isEmpty(cOLD_TEL))vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cUpdateTel);
				if (bibALL.isEmpty(cOLD_FAX))vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cUpdateFax);
				if (bibALL.isEmpty(cOLD_MAIL))vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cUpdateEMail);
				if (bibALL.isEmpty(cOLD_Ansprech))vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cUpdateAnsprech);
			}
			
			if (vSQL.size()>0)
			{
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true)); 
				if (bibMSG.get_bIsOK())
				{
					this.iGeaendert++;
				}
				else
				{
					bibMSG.add_ALARMMESSAGE_Vector_Untranslated(vSQL);
					throw new myException("Error Writing ...");
				}
			}
			else
			{
				this.iUebersprungen ++;
			}
		}
		
	}
	
	
	
}
