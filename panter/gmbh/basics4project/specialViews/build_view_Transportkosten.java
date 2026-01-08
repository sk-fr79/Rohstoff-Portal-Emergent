package panter.gmbh.basics4project.specialViews;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.XXX_ViewBuilder;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class build_view_Transportkosten extends XXX_ViewBuilder
{
	// String Array: [Spalte] [Alias]
	private String[][] cDefArray = 
			{
			{"JT_VPOS_TPA.ID_VPOS_TPA",						   "ID_TPA_KOSTEN"},
			{"JT_VPOS_TPA.ID_MANDANT",							"ID_MANDANT"},
			{"JT_VPOS_TPA.ID_ARTIKEL",							"ID_ARTIKEL"},
			{"JT_VPOS_TPA.ARTBEZ1",								"ARTBEZ1"},
			{"JT_VPOS_TPA.EINHEITKURZ",							"EINHEITKURZ"},
			{"JT_VPOS_TPA.ANR1",								"ANR1"},
			{"JT_VPOS_TPA.ANR2",								"ANR2"},
			{"JT_VPOS_TPA.ANZAHL",								"ANZAHL"},
			{"JT_VPOS_TPA.EINHEIT_PREIS_KURZ",					"EINHEIT_PREIS_KURZ"},
			{"JT_VPOS_TPA.EINZELPREIS",							"EINZELPREIS"},
			{"JT_VPOS_TPA.GESAMTPREIS",							"GESAMTPREIS"},
			{"JT_VPOS_TPA.ZAHLUNGSBEDINGUNGEN",					"ZAHLUNGSBEDINGUNGEN"},
			{"JT_VPOS_TPA.LIEFERBEDINGUNGEN",					"LIEFERBEDINGUNGEN"},
			{"JT_VKOPF_TPA.NAME1 || JT_VKOPF_TPA.NAME2",		"SPED_NAME"},
			{"JT_VKOPF_TPA.STRASSE",							"SPED_STRASSE"},
			{"JT_VKOPF_TPA.HAUSNUMMER",							"SPED_HAUSNUMMER"},
			{"JT_VPOS_TPA_FUHRE.TRANSPORTMITTEL",				"TRANSPORTMITTEL"},
			{"JT_VPOS_TPA_FUHRE.DATUM_ABHOLUNG",				"DATUM_ABHOLUNG"},
			{"JT_VPOS_TPA_FUHRE.ID_ARTIKEL",					"ID_SORTE"},
			{"JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_START",		"ID_ADRESSE_LAGER_START"},
			{"JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_ZIEL",			"ID_ADRESSE_LAGER_ZIEL"},
			{"ADR_START.PLZ",									"ADR_START_PLZ"},
			{"ADR_START.ORT",									"ADR_START_ORT"},
			{"ADR_START.ID_LAND",								"ADR_START_ID_LAND"},
			{"ADR_ZIEL.PLZ",									"ADR_ZIEL_PLZ"},
			{"ADR_ZIEL.ORT",									"ADR_ZIEL_ORT"},
			{"ADR_ZIEL.ID_LAND",								"ADR_ZIEL_ID_LAND"},
		};
	
	
	
	
	
	@Override
	public boolean build_View_forAll_Mandants() throws myException
	{
		
		RECLIST_MANDANT  oRecList = new RECLIST_MANDANT("SELECT * FROM "+bibE2.cTO()+".JD_MANDANT");
		
		String c_SQL =  	 " CREATE OR REPLACE VIEW V#MANDANT#_TPA_KOSTEN AS "+	
									"  (SELECT DISTINCT " + 
									this.CreateBaseFieldList()+
									" FROM " + bibE2.cTO()+".JT_VPOS_TPA " +
									" INNER JOIN  " + bibE2.cTO()+".JT_VPOS_TPA_FUHRE  ON  (  " + bibE2.cTO()+".JT_VPOS_TPA.ID_VPOS_TPA =  " + bibE2.cTO()+".JT_VPOS_TPA_FUHRE.ID_VPOS_TPA ) " +
									" INNER JOIN  " + bibE2.cTO()+".JT_VKOPF_TPA ON (  " + bibE2.cTO()+".JT_VPOS_TPA.ID_VKOPF_TPA =  " + bibE2.cTO()+".JT_VKOPF_TPA.ID_VKOPF_TPA ) " +
									" INNER JOIN  " + bibE2.cTO()+".JT_ADRESSE ADR_START ON (  " + bibE2.cTO()+".JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_START = ADR_START.ID_ADRESSE ) " +
									" INNER JOIN  " + bibE2.cTO()+".JT_ADRESSE ADR_ZIEL ON (  " + bibE2.cTO()+".JT_VPOS_TPA_FUHRE.ID_ADRESSE_LAGER_ZIEL = ADR_ZIEL.ID_ADRESSE ) " +
									" WHERE  " + bibE2.cTO()+".JT_VPOS_TPA.ID_MANDANT =#MANDANT#) ";
									

		for (int i=0;i<oRecList.get_vKeyValues().size();i++)
		{
			
			String cSQL = bibALL.ReplaceTeilString(c_SQL,"#MANDANT#",oRecList.get(i).get_ID_MANDANT_cUF());
			
			
			if (bibDB.ExecSQL(cSQL, true))
			{
				MyE2_String cInfo = new MyE2_String("Transportkosten-View fuer Mandant: <",true,oRecList.get(i).get_NAME1_cF_NN(""),false, "> erfolgreich erstellt !",true);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(cInfo));
			}
			else
			{
				MyE2_String cInfo = new MyE2_String("Transportkosten-View fuer Mandant: <",true,oRecList.get(i).get_NAME1_cF_NN(""),false, "> KONNTE NICHT ERSTELLT WERDEN !",true);
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cInfo));
			}
		}
		
		
		return false;
	}

	
	
	private String CreateBaseFieldList()
	{
		StringBuffer  SQLListBase = new StringBuffer();
		
		for (int i=0;i<cDefArray.length;i++)
		{
			if (i > 0) SQLListBase.append(",");
			SQLListBase.append( cDefArray[i][0]+" AS "+cDefArray[i][1] );
		}
		
		return SQLListBase.toString();
	}
	
	

	
	
	
	
}
