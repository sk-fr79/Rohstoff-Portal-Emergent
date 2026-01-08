package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv;

import java.util.HashMap;

public abstract class DatevCSVLine extends HashMap<Integer, DatevCSVColumn> {
	public String toCSVString() throws DatevCSVException {
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= this.size(); i++) {
			if (i > 1) {
				sb.append(";");
			}
			try {
				sb.append(this.get(i).toCSVString());
			} catch (DatevCSVException e) {
				throw new DatevCSVException("Index "+i+ " in CSV line has an error: "+e.getMessage(), e);
			}
		}
		return sb.toString();
	}
	
	
	public String getHeader() {
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= this.size(); i++) {
			if (i > 1) {
				sb.append(";");
			}
			sb.append("\""+this.get(i).getDescription()+"\"");
		}
		return sb.toString();
	}
	
	public DatevCSVLine setValue(Integer i, Object val) {
		this.get(i).setValue(val);
		return this;
	}

	// For using "builder-style"
	private int indexCounter = 1;
	public DatevCSVLine addValue(Object val) {
		this.get(indexCounter++).setValue(val);
		return this;
	}
	
}
