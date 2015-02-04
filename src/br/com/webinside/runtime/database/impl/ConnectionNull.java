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

package br.com.webinside.runtime.database.impl;

import java.util.HashMap;
import java.util.Map;

import br.com.webinside.runtime.database.DatabaseConnection;
import br.com.webinside.runtime.database.ErrorCode;
import br.com.webinside.runtime.database.ResultSet;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class ConnectionNull extends DatabaseConnection {
    /**
     * Creates a new ConnectionNull object.
     */
    public ConnectionNull() {
        super("", "", "", "");
        setMaxRows(0);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public DatabaseConnection cloneMe() {
        return new ConnectionNull();
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean isPooled() {
        return false;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean isConnected() {
        return false;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getVersion() {
        return "";
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean isSql() {
        return false;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int connect() {
        return ErrorCode.UNKNOWN;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String[] listMetas() {
        return new String[0];
    }

    /**
     * DOCUMENT ME!
     *
     * @param meta DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ResultSet listMeta(String meta) {
        return new ResultSetNoSql();
    }

    /**
     * DOCUMENT ME!
     *
     * @param metalist DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ResultSet listMeta(String[] metalist) {
        return new ResultSetNoSql();
    }

    /**
     * DOCUMENT ME!
     *
     * @param table DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ResultSet listMetaStruct(String table) {
        return new ResultSetNoSql();
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Map getDataTypes() {
        return new HashMap();
    }

    /**
     * DOCUMENT ME!
     */
    public void commit() {
    }

    /**
     * DOCUMENT ME!
     */
    public void rollback() {
    }

    /**
     * DOCUMENT ME!
     *
     * @param status DOCUMENT ME!
     */
    public void autocommit(boolean status) {
    }

    /**
     * DOCUMENT ME!
     *
     * @param status DOCUMENT ME!
     */
    public boolean isAutocommit() {
    	return false;
    }

    /**
     * DOCUMENT ME!
     */
    public void clear() {
    }

    /**
     * DOCUMENT ME!
     */
    public void close() {
    }
}
