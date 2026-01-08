package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;


public class CAL_COMPONENT_GridVerteiler extends MyE2_Grid 
{
	
	private Vector<MyE2_CheckBox> vCBUser = new Vector<MyE2_CheckBox>();
	
	
	public CAL_COMPONENT_GridVerteiler() 
	{
		super(6, MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
		
	}

	public void build_Grid_4_Edit(String cID_TERMIN_USER_Unformated)
	{
	
		// jetzt rauskriegen, wie die verteilung ist
		String cSQLQuery2 = "SELECT ID_TERMIN FROM "+bibE2.cTO()+".JT_TERMIN_USER WHERE  ID_TERMIN_USER="+cID_TERMIN_USER_Unformated;
		String cAntwort = bibDB.EinzelAbfrage(cSQLQuery2,true,true);
		
		// alle termin-user-eintraege, die zu dem termin gehoeren
		String cSQLQuery3 = "SELECT   NVL(IS_OWNER,'N'), ID_USER  FROM "+bibE2.cTO()+".JT_TERMIN_USER WHERE ID_TERMIN="+cAntwort;
		
		String[][] cTerminUser = bibDB.EinzelAbfrageInArray(cSQLQuery3,"");

		/*
		 * rauskriegen, ob der User der besitzer ist
		 */
		String cID_USER_OWNER = null;
		Vector<String> vID_USER_IN_VERTEILER = new Vector<String>();
		for (int i=0;i<cTerminUser.length;i++)
		{
			vID_USER_IN_VERTEILER.add( cTerminUser[i][1]);
			if (cTerminUser[i][0].toUpperCase().equals("Y"))
			{
				cID_USER_OWNER = cTerminUser[i][1];
			}
		}
		
		
		// zuerst alle Benutzer ausser dem eigenen abfragen und in das grid eintragen
		String cSQLQuery = "SELECT   VORNAME, NAME1, NAME,ID_USER FROM "+
							bibE2.cTO()+".JD_USER WHERE NAME IS NOT NULL AND ID_USER<>"+cID_USER_OWNER+" AND ID_MANDANT="+bibALL.get_ID_MANDANT()+
							" ORDER BY NAME";
		
		String[][] cBenutzer = bibDB.EinzelAbfrageInArray(cSQLQuery,"");

		
		
		boolean bEigen = (cID_USER_OWNER.equals(bibALL.get_ID_USER()));

		
		
		this.vCBUser.removeAllElements();
		
		if (cBenutzer != null)
		{
			if (cBenutzer.length != 0)
			{
				for (int i=0;i<cBenutzer.length;i++)
				{
					MyE2_CheckBox  oCBUser = new MyE2_CheckBox(cBenutzer[i][2], MyE2_CheckBox.STYLE_ITALIC());
					oCBUser.setFont(new E2_FontItalic(-4));
					oCBUser.EXT().set_C_MERKMAL(cBenutzer[i][3]);   // als merkmal wird id_user uebergeben
					
					this.vCBUser.add(oCBUser);

					if (vID_USER_IN_VERTEILER.contains(cBenutzer[i][3]))
					{
						oCBUser.setSelected(true);
						oCBUser.EXT().set_C_MERKMAL2("Y");    // speichert den anfangszustand
					}
					else
					{
						oCBUser.setSelected(false);
						oCBUser.EXT().set_C_MERKMAL2("N");
					}
					
					if (! bEigen)
						oCBUser.setEnabled(false);
					else
						oCBUser.setEnabled(true);
					
					this.add(oCBUser,E2_INSETS.I_3_3_3_3);
				}
			}
		}
	}
	

	
	public void build_Grid_4_NEW()
	{
	
		
		// zuerst alle Benutzer ausser dem eigenen abfragen und in das grid eintragen
		String cSQLQuery = "SELECT   VORNAME,  NAME1,  NAME,ID_USER FROM "+
							bibE2.cTO()+".JD_USER WHERE NAME IS NOT NULL AND ID_USER<>"+bibALL.get_ID_USER()+
							" AND ID_MANDANT="+bibALL.get_ID_MANDANT()+
							" ORDER BY NAME";
		
		String[][] cBenutzer = bibDB.EinzelAbfrageInArray(cSQLQuery,"");

		this.vCBUser.removeAllElements();
		
		if (cBenutzer != null)
		{
			if (cBenutzer.length != 0)
			{
				for (int i=0;i<cBenutzer.length;i++)
				{
					MyE2_CheckBox  oCBUser = new MyE2_CheckBox(cBenutzer[i][2], MyE2_CheckBox.STYLE_SMALL_ITALIC());
					oCBUser.EXT().set_C_MERKMAL(cBenutzer[i][3]);   // als merkmal wird id_user uebergeben
					this.vCBUser.add(oCBUser);
					oCBUser.setSelected(false);
					oCBUser.setEnabled(true);
					this.add(oCBUser,E2_INSETS.I_3_3_3_3);
				}
			}
		}
	}
	
	
	/*
	 * baut einen Vector mit statements fuer den speichervorgang nach neueingabe
	 */
	public Vector<String> get_vInsertStatements_NEW()
	{
		Vector<String> vRueck = new Vector<String>();
		
		for (int i=0;i<this.vCBUser.size();i++)
		{
			MyE2_CheckBox oCB = (MyE2_CheckBox)this.vCBUser.get(i);
			
			if (oCB.isSelected())
			{
				vRueck.add("INSERT INTO JT_TERMIN_USER (ID_TERMIN_USER,IS_OWNER,ID_USER,ID_TERMIN) "+
						                     " VALUES(SEQ_TERMIN_USER.NEXTVAL,'N',"+oCB.EXT().get_C_MERKMAL() +",SEQ_TERMIN.CURRVAL)");
			}
		}
		
		return vRueck;
	}
	
	
	
	/*
	 * baut einen Vector mit statements fuer den speichervorgang nach neueingabe
	 */
	public Vector<String> get_vUpdateStatements_EDIT(String cID_TERMIN_EDIT)
	{
		Vector<String> vRueck = new Vector<String>();
		
		for (int i=0;i<this.vCBUser.size();i++)
		{
			MyE2_CheckBox oCB = (MyE2_CheckBox)this.vCBUser.get(i);

			// neu dazugekommen
			if 		(oCB.isSelected() && oCB.EXT().get_C_MERKMAL2().equals("N"))
			{
				vRueck.add("INSERT INTO "+bibE2.cTO()+".JT_TERMIN_USER (ID_TERMIN_USER,IS_OWNER,ID_USER,ID_TERMIN) "+
						                     " VALUES(SEQ_TERMIN_USER.NEXTVAL,'N',"+oCB.EXT().get_C_MERKMAL() +","+cID_TERMIN_EDIT+")");
			}
			else if (!oCB.isSelected() && oCB.EXT().get_C_MERKMAL2().equals("Y"))
			{
				vRueck.add("DELETE FROM "+bibE2.cTO()+".JT_TERMIN_USER WHERE ID_TERMIN="+cID_TERMIN_EDIT+" AND ID_USER="+oCB.EXT().get_C_MERKMAL());
			}
		}
		
		return vRueck;
	}
	
	

	
	
	
}
