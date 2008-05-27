/*
 * Created on 26-Oct-2004
 */
package ie.ucd.srg.logica.eplatform.error;

/**
 * @author Alan E. Morkan
 */
public class EPlatformException extends Exception{

	/**
	 * @param errorcode
	 */
	//@ requires errorcode != null;
	public EPlatformException(String errorcode){
		//@ assert false;
		super(errorcode);
	    errorCode = errorcode;
	    System.out.println(errorcode);
	}

	/**
	 * 
	 * @param errorcode
	 * @param params
	 */
	//@ requires errorcode != null && params != null;
	public EPlatformException(String errorcode, String[] params){
		//@ assert false;
		super();
		errorCode = errorcode;
		parameters = params;
		System.out.println(errorcode);
	}
	
	/**
	 * 
	 * @param errorcode
	 * @param params
	 * @param wrappedException
	 */
	//@ requires errorcode != null && params != null && wrappedException != null;
	public EPlatformException(String errorcode, String[] params, Exception wrappedException){
		//@ assert false;
		super();
		errorCode = errorcode;
		parameters = params;
		System.out.println(errorcode);
		//should probably do something with the wrappedException
	}
	
	/**
	 * 
	 * @param errorcode
	 * @param params
	 * @param wrappedException
	 * @param debugMessage
	 */
	/*@ requires errorcode != null && params != null &&
				 wrappedException != null && debugMessage != null; @*/
	public EPlatformException(String errorcode, String[] params, 
								Exception wrappedException, String debugMessage){
		//@ assert false;
		super();
		errorCode = errorcode;
		parameters = params;
		System.out.println(debugMessage);
		System.out.println(errorcode);
		//should probably do something with the wrappedException
	}
	
	/**
	 * 
	 * @param errorcode
	 * @param wrappedException
	 */
	//@ requires errorcode != null && wrappedException != null;
	public EPlatformException(String errorcode, Exception wrappedException){
		//@ assert false;
		super();
		errorCode = errorcode;
		System.out.println(errorcode);
		//should probably do something with the wrappedException
	}
	
   /**
    * 
    *
    */
	public EPlatformException() {
      super();
   }

   /**
	 * 
	 * @return
	 */
	//@ ensures \result = errorCode;
	public String getErrorCode(){
		return errorCode;
	}
	
	/**
	 * 
	 * @return
	 */
	//@ ensures \result = parameters;
	public String[] getParameters(){
		return parameters;
	}
	
	/**
	 * 
	 */
	private String errorCode;
	
	/**
	 * 
	 */
	private String[] parameters;
}