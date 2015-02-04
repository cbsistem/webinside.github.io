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

package br.com.webinside.runtime.function;

import br.com.webinside.runtime.integration.*;
import br.com.webinside.runtime.util.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class ExecuteSqlThread extends Thread {
    private DatabaseHandler dbgen;
    private String query;
    private WIMap wiMap;

    /**
     * Creates a new ExecuteSqlThread object.
     *
     * @param dbgen DOCUMENT ME!
     * @param query DOCUMENT ME!
     * @param wiMap DOCUMENT ME!
     */
    public ExecuteSqlThread(DatabaseHandler dbgen, String query, WIMap wiMap) {
		super("WI-ExecuteSQLThread");
        this.dbgen = dbgen;
        this.query = query;
        this.wiMap = wiMap;
    }

    /**
     * DOCUMENT ME!
     */
    public void run() {
        if (dbgen == null) {
            return;
        }
        dbgen.connect();
        try {
            dbgen.executeUpdate(query, wiMap);
        } catch (Exception err) {
            System.err.println(getClass().getName() + ": " + err);
        }
        dbgen.close();
    }
}
