package rohstoff.utils.SQL_DAEMONS;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.XX_SQL_STACK_DAEMON;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON_TRAKT;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox_LOG_INFO;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_EK_VK_BEZUG;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_KON;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_KON_LAGER;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE_ORT;


public class BEWEGUNGSDATEN_PRUEF_Daemon extends XX_SQL_STACK_DAEMON {

	
	/*
	 * ein DBToolBox-validator, der kontrakte ueberwacht, wenn in einem speichervorgang ein SQL-statement (INSERT oder UPDATE)
	 * vorkommt, in dem ein speichervorgang einer tabelle aus den folgenden vorkommt: 
	 * JT_VPOS_TPA_FUHRE
	 * JT_VPOS_TPA_FUHRE_ORT
	 * JT_VPOS_KON 
	 * JT_VPOS_KON_LAGER
	 * JT_EK_VK_BEZUG
	 * JT_VPOS_KON_LAGER 
	 * und alle kontraktmengen-relevanten daten prueft und validiert
	 */
	

	private Vector<String>  vTablesToCheck = new Vector<String>();
	
	
	public BEWEGUNGSDATEN_PRUEF_Daemon() throws myException 
	{
		super();
		this.vTablesToCheck.add("JT_VPOS_TPA_FUHRE");
		this.vTablesToCheck.add("JT_VPOS_TPA_FUHRE_ORT");
		this.vTablesToCheck.add("JT_VPOS_KON");
		this.vTablesToCheck.add("JT_VPOS_KON_TRAKT");
		this.vTablesToCheck.add("JT_VPOS_KON_LAGER");
		this.vTablesToCheck.add("JT_EK_VK_BEZUG");
	}


	
	protected void finalize()
	{
	}

	
	
	// wird unmittelbar nach jedem einzelnen sql-statement ausgefuehrt
	public MyE2_MessageVector collect_LOG_INFOs(Vector<MyDBToolBox_LOG_INFO> vLogInfos, MyConnection oConn) throws myException 
	{
		
		//die betroffenen tabellen-LogInfos sammeln
		for (int i=0;i<vLogInfos.size();i++)
		{
			if (this.vTablesToCheck.contains(vLogInfos.get(i).get_cTABLENAME()))
			{
				if (!vLogInfos.get(i).get_IS_DELETE())           //nur insert und update werden beruecksichtigt !
				{
					this.get_vLogInfos().add(vLogInfos.get(i));
				}
			}
		}
		
		return new MyE2_MessageVector();
	}


	
	public MyE2_MessageVector doValidate(MyConnection oConn) throws myException 
	{
		
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		
//		if (this.oDB==null)
//			this.oDB =bibALL.get_myDBToolBox(oConn);
//		
		
		VectorSingle vIDs_VPOS_KON = 			new VectorSingle();
		VectorSingle vIDs_VPOS_KON_TRAKT =		new VectorSingle();
		VectorSingle vIDs_VPOS_KON_LAGER = 		new VectorSingle();
		VectorSingle vIDs_VPOS_TPA_FUHRE = 		new VectorSingle();
		VectorSingle vIDs_VPOS_TPA_FUHRE_ORT = 	new VectorSingle();
		VectorSingle vIDs_EK_VK_BEZUG = 		new VectorSingle();
		
		
		// ein Vector mit tabellennamen der beteiligten Tables wird dem ____Check() mit uebergeben, damit dort entschieden werden kann welche pruefungen sinnvoll sind
		VectorSingle vActionTables = new VectorSingle();
		
		
		for (int i=0;i<this.get_vLogInfos().size();i++)
		{
			String cTableName = this.get_vLogInfos().get(i).get_cTABLENAME();
			String cTableID   = this.get_vLogInfos().get(i).get_cID_TABLE();

			vActionTables.add(cTableName);
			
			if (cTableName.equals("JT_VPOS_KON"))
			{
				vIDs_VPOS_KON.add(cTableID);
			}

			if (cTableName.equals("JT_VPOS_KON_TRAKT"))
			{
				vIDs_VPOS_KON_TRAKT.add(cTableID);
			}

			if (cTableName.equals("JT_VPOS_KON_LAGER"))
			{
				vIDs_VPOS_KON_LAGER.add(cTableID);
			}
			
			if (cTableName.equals("JT_VPOS_TPA_FUHRE"))
			{
				vIDs_VPOS_TPA_FUHRE.add(cTableID);
			}
			
			if (cTableName.equals("JT_VPOS_TPA_FUHRE_ORT"))
			{
				vIDs_VPOS_TPA_FUHRE_ORT.add(cTableID);
			}
			
			if (cTableName.equals("JT_EK_VK_BEZUG"))
			{
				if (!this.get_vLogInfos().get(i).get_IS_DELETE())         //EK-VK-Bezug kann geloescht werden, darf dann nicht geprueft werden
				{
					vIDs_EK_VK_BEZUG.add(cTableID);
				}
			}

		}

		//jetzt die vectoren abarbeiten
		VectorSingle_PRUEF_Interface vSammler = new VectorSingle_PRUEF_Interface();
		
		for (String cID: vIDs_VPOS_KON)
		{
			vSammler.add(new PRUEF_RECORD_VPOS_KON(cID,oConn,true,"JT_VPOS_KON"));
		}
		for (String cID: vIDs_VPOS_KON_TRAKT)
		{
			vSammler.add(new PRUEF_RECORD_VPOS_KON(new RECORD_VPOS_KON_TRAKT(cID,oConn).get_ID_VPOS_KON_cUF(),oConn,true,"JT_VPOS_KON"));
		}
		for (String cID: vIDs_VPOS_KON_LAGER)
		{
			vSammler.add(new PRUEF_RECORD_VPOS_KON_LAGER(cID,oConn,true,"JT_VPOS_KON_LAGER"));
		}
		for (String cID: vIDs_VPOS_TPA_FUHRE)
		{
			vSammler.add(new PRUEF_RECORD_VPOS_TPA_FUHRE(cID,oConn,true,"JT_VPOS_TPA_FUHRE"));
		}
		for (String cID: vIDs_VPOS_TPA_FUHRE_ORT)
		{
			vSammler.add(new PRUEF_RECORD_VPOS_TPA_FUHRE_ORT(cID,oConn,true,"JT_VPOS_TPA_FUHRE_ORT"));
		}
		for (String cID: vIDs_EK_VK_BEZUG)
		{
			vSammler.add(new PRUEF_RECORD_EK_VK_BEZUG(cID,oConn,true,"JT_EK_VK_BEZUG"));
		}

		//jetzt den sammler recursiv abarbeiten, da in den pruefungen neue PRUEF_RECORDS dazukommen koennen
		
		while (vSammler.has_SomethingToDo())
		{
			for (int i=0;i<vSammler.size();i++)
			{
				if (!vSammler.get(i).IsDone())
				{
//					System.out.println("CHECK      ---->"+vSammler.get(i).get_TABLENAME()+" --> "+vSammler.get(i).get_ID());
					
					
					oMV.add_MESSAGE(vSammler.get(i).__Check(vSammler,vActionTables));
					break;   //neu anfangen, da evtl. was dazukommt
				}
			}
		}
		
		return oMV;
	}

	
	

	public Vector<String> getTriggerStatementsAfterSQL( MyConnection oConn, MyE2_MessageVector oMV) throws myException 
	{
		return new Vector<String>();
	}


}
