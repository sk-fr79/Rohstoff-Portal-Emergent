/**
 * panter.gmbh.indep
 * @author martin
 * @date 30.07.2020
 * 
 */
package panter.gmbh.indep;

import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 30.07.2020
 *
 */
public class ResultValue<T> {

	private T object = null;
	
	private VEK<String> errors= new VEK<>();
	private VEK<String> infos = new VEK<>();

	/**
	 * @author martin
	 * @date 30.07.2020
	 *
	 */
	public ResultValue() {
		super();
	}


    public T get()  {
        return object;
    }

    public ResultValue<T> _set(T t) {
        this.object = t;
        return this;
    }

    /**
     *
     * @return true, when errorstack is empty and object != null;
     */
    public boolean isOk() {
        return this.errors.size()==0 && object!=null;
    }

    /**
     *
     * @return true, when errorstack is full or object == null;
     */
    public boolean isFail() {
        return !isOk();
    }

    public String getError() {
        if (this.isOk()) {
            return null;
        } else {
            if (errors.size()>0) {
                return errors.concatenante("\n");
            } else {
                return "Unbekannter-Fehler !";
            }
        }
    }

    public boolean hasMessage() {
        return this.infos.size()!=0;
    }
    public boolean hasErrorMessage() {
        return this.errors.size()!=0;
    }

    public String getMessage() {
        if (this.isOk()) {
            return infos.concatenante("\n");
        } else {
            return null;
        }
    }


    public ResultValue<T> _addError(String error) {
        if (S.isFull(error)) {
            errors._a(error);
        }
        return this;
    }

    public ResultValue<T> _addInfo(String info) {
        if (S.isFull(info)) {
            infos._a(info);
        }
        return this;
    }


	
}
