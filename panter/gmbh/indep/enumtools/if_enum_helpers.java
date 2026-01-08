package panter.gmbh.indep.enumtools;

public interface if_enum_helpers<T extends Enum<T>> {
	
	@SuppressWarnings("unchecked")
	public default String S() {
		return ((Enum<T>)this).name(); 
	}

	@SuppressWarnings("unchecked")
	public default String key() {
		return ((Enum<T>)this).name(); 
	}
	
	
}
