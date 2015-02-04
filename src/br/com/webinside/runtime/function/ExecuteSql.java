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
public class ExecuteSql extends AbstractConnector {
    /**
     * Creates a new ExecuteSql object.
     */
    public ExecuteSql() {
    }

    /**
     * DOCUMENT ME!
     *
     * @param wiMap DOCUMENT ME!
     * @param databases DOCUMENT ME!
     * @param headers DOCUMENT ME!
     */
    public void execute(WIMap wiMap, DatabaseAliases databases,
        InterfaceHeaders headers) {
        String dbid = wiMap.get("tmp.dbid");
        String update = wiMap.get("tmp.update");
        DatabaseHandler dbgen = databases.get(dbid).cloneMe();
        new ExecuteSqlThread(dbgen, update, wiMap.cloneMe()).start();
    }
}
