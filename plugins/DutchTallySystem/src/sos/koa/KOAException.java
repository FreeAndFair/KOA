/*
 * JML specification Copyright 2004 SoS Group, University of Nijmegen
 */

package sos.koa;

/**
 * General KOA exception.
 *
 * @version $Id$
 *
 * @author Martijn Oostdijk (martijno@cs.kun.nl)
 */
public /*@ nullable_by_default @*/ class KOAException extends Exception {

   /**
    * Constructs a new exception with error message <code>s</code>.
    *
    * @param s the error message.
    */
   /*@ pure @*/ public KOAException(String s) {
      super(s);
   }

   /**
    * Constructs a new exception by wrapping the exception <code>e</code>.
    *
    * @param e any exception.
    */
   /*@ pure @*/ public KOAException(Exception e) {
      super(e);
   }
}

