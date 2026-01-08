package panter.gmbh.Echo2.RB.DATA;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.IF.IF_RB_Collector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * hashmap enthaelt einen satz von RB_MASK_DATA korrelieren mit der RB_MASK_HM_BASE
 * 
 * container-klasse um LinkedHashMap<RB_K,RB_Dataobject>
 * @author martin
 *
 */
public interface RB_DataobjectsCollector extends Iterable<RB_Dataobject>, IF_RB_Collector<RB_Dataobject>  {

	public RB_Dataobject get(RB_KM ob); 

	/*
	 * in dieser methode muss der zusammenhang der RECORD - objekte aufgebaut werden (je nach zusammenstellung der maske und  verbindung der teile)
	 */
	public void database_to_dataobject(Object startPoint) throws myException;
	
//	/*
//	 * hier wird beim speichern festgelegt, wie die StatementBuilder zusammenhaengen
//	 */
//	public void dataobject_to_database_connect_RB_MASK_DATA(RB_StatementBuilderCollector Statemenbuilder_Collector) throws myException;
	
	public MyE2_MessageVector rb_Dataobjects_to_Database(boolean forceSave) throws myException;

	public RB_ComponentMapCollector rb_ComponentMapCollector_ThisBelongsTo();

	public void set_rb_ComponentMapCollector_ThisBelongsTo(RB_ComponentMapCollector mask_ContainerThisBelongsTo);

//	public void rb_Clear_ALL_RECNEW() throws myException;
	
	/**
	 * @deprecated
	 * @param bOnlyWhenExists
	 * @throws myException
	 */
	public void rb_Rebuild_ALL_RECORD(boolean bOnlyWhenExists) throws myException;
	
	/**
	 * 
	 * @param bOnlyWhenExists
	 * @throws myException
	 */
	public void rb_RebuildAllRecords() throws myException;


	/**
	 * beim speichern eines neuen datensatzes wird der letzte currval-eintrag in der datenbank mitgeschrieben
	 * @param cTablename
	 * @return
	 */
	public String get_LastWrittenNewID(String cTablename) throws myException;
	
	
	/**
	 * 2016-04-27: martin
	 * beim speichern eines neuen datensatzes wird der letzte currval-eintrag in der datenbank mitgeschrieben
	 * @param table
	 * @return
	 */
	public String get_LastWrittenNewID(_TAB table) throws myException;
	
	
	
	/**
	 * 2015-05-06: das holen der DBToolBox hier ausgelagert in eine eigene Methode, die ueberschrieben werden kann
	 * @return
	 * @throws myException
	 */
	public MyDBToolBox  generate_DBToolBox(MyConnection conn) throws myException;


	public MyDBToolBox_FAB get_DBToolBox_FAB();


	public void set_DBToolBox_FAB(MyDBToolBox_FAB dBToolBox_FAB);
	
	/**
	 * 2015-05-06: ExecMultiSQLVector ausgelagert in eine eigene Methode, damit die eigene DBToolBox verwendet wird
	 * @param vSQLStack
	 * @param bCommit
	 * @return
	 * @throws myException 
	 */
	public MyE2_MessageVector ExecMultiSQLVector(Vector<String> vSQLStack, boolean bCommit) throws myException;


	//get und set Extender
	public RB_DataobjectsCollector_EXT EXT_DO_Collector();
	
	
	public LinkedHashMap<RB_KM, RB_Dataobject> rb_hm_DataObjects();
	
	@Override
	public default Iterator<RB_Dataobject> iterator() {
		return this.rb_hm_DataObjects().values().iterator();
	}

	
	/**
	 * 20180105: martin
	 * @return s vector of all dataobjects
	 */
	public default VEK<RB_Dataobject> getVectorDataobjects() throws myException {
		VEK<RB_Dataobject> v = new VEK<>();
		
		v.addAll(this.rb_hm_DataObjects().values());

		return v;
	}
	
	
	
}
