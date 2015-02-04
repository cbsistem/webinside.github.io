/*
 * WEBINSIDE - Ferramenta de produtividade Java
 * Copyright (c) 2011-2012 LINEWEB Solu��es Tecnol�gicas Ltda.
 * Copyright (c) 2009-2010 Inc�gnita Intelig�ncia Digital Ltda.
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou modific�-lo 
 * sob os termos da GNU LESSER GENERAL PUBLIC LICENSE (LGPL) conforme publicada 
 * pela Free Software Foundation; vers�o 2.1 da Licen�a.
 * Este programa � distribu�do na expectativa de que seja �til, por�m, SEM 
 * NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU 
 * ADEQUA��O A UMA FINALIDADE ESPEC�FICA.
 * 
 * Consulte a GNU LGPL para mais detalhes.
 * Voc� deve ter recebido uma c�pia da GNU LGPL junto com este programa; se n�o, 
 * veja em http://www.gnu.org/licenses/ 
 */

package br.com.webinside.modules.parser;

import java.io.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
class ParserThread extends Thread {
    /** DOCUMENT ME! */
    HTMLParser parser;

    /**
     * Creates a new ParserThread object.
     *
     * @param p DOCUMENT ME!
     */
    public ParserThread(HTMLParser p) {
		super("WI-ParserThread");
        parser = p;
    }

    /**
     * DOCUMENT ME!
     */
    public void run() { // convert pipeOut to pipeIn
        try {
            try { // parse document to pipeOut
                parser.HTMLDocument();
            } catch (ParseException e) {
                System.err.println("Parse Aborted: " + e.getMessage());
            } catch (TokenMgrError e) {
                System.err.println("Parse Aborted: " + e.getMessage());
            } finally {
                parser.pipeOut.close();
                synchronized (parser) {
                    parser.summary.setLength(HTMLParser.SUMMARY_LENGTH);
                    parser.titleComplete = true;
                    parser.notifyAll();
                }
            }
        } catch (IOException e) {
        }
    }
}
