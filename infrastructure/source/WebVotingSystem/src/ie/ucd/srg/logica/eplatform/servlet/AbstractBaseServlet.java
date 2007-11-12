/*
 * Created on 26-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ie.ucd.srg.logica.eplatform.ticket.*;

/**
 * @author Alan E. Morkan
 */
public class AbstractBaseServlet extends HttpServlet{

	/**
	 * should this exist??
	 */
	private /*@ non_null @*/ Ticket ticket;
    
	/**
	 * 
	 */
	public static /*@ non_null @*/  HttpServletRequest commandTarget;
    
	/**
	 * @throws ServletException
	 */
    public void init()throws ServletException{
    	//@ assert false;
    	initCommandFactory();
    	initMisc();
    	initServices();
    }
    
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    //@ requires request != null;
    //@ requires response != null;
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException{
		commandTarget = request;
		performGetTask(request, response);
    }
    
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    //@ requires request != null;
    //@ requires response != null;
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    	commandTarget = request;
    	performPostTask(request, response);
    }
    
    /**
     * 
     * @param request
     * @param response
     * @param s
     */
    //@ requires request != null; 
    //@ requires response != null;
    //@ requires s != null;
    public void redirect(HttpServletRequest request, HttpServletResponse response, 
    						String s){
    	RequestDispatcher dispatcher = request.getRequestDispatcher(s);
    	try{
    		dispatcher.forward(request, response);
    	}catch(IOException ioe){
    		System.out.println(ioe);
    	}catch(ServletException se){
    		System.out.println(se);
    	}
    }
	
    /**
     * 
     * @param request
     */
    //@ requires request != null;
    //@ ensures \result != null;
    public Ticket getTicket(HttpServletRequest request){
    		//@ assert false;
    		return ticket;
	}
    
    /**
     * 
     */
    public void initCommandFactory(){
    	//@ assert false;
    }
    
    /**
     * 
     */
    public void initMisc(){
    	//@ assert false;
    }
    
    /**
     * 
     */
    public void initServices(){
    	//@ assert false;
    }
    
    /**
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    //@ requires request !=null;
    //@ requires response != null;
    public void performGetTask(HttpServletRequest request, HttpServletResponse response)
    											throws IOException, ServletException{
    	//@ assert false;
    }
    
    /**
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    //@ requires request !=null;
    //@ requires response != null;
    public void performPostTask(HttpServletRequest request, HttpServletResponse response)
    											throws IOException, ServletException{
    	//@ assert false;
    }
    
    /**
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    //@ requires request !=null;
    //@ requires response != null;
    public void noTicket(HttpServletRequest request, HttpServletResponse response)
    											throws IOException, ServletException{
    	//@ assert false;
    }
}
