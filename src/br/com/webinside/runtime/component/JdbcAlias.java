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

package br.com.webinside.runtime.component;

import org.jdom.*;

import br.com.webinside.runtime.database.DatabaseDrivers;
import br.com.webinside.runtime.xml.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.2 $
 */
public class JdbcAlias {
		
    private Element alias;

    /**
     * Creates a new JdbcAlias object.
     */
    public JdbcAlias() {
        alias = new Element("JDBC");
    }

    /**
     * Creates a new JdbcAlias object.
     *
     * @param ele DOCUMENT ME!
     */
    public JdbcAlias(Element ele) {
        if ((ele == null) || !ele.getName().equals("JDBC")) {
            alias = new Element("JDBC");
        } else {
            alias = ele;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getID() {
        return XMLFunction.getAttrValue(alias, "ID");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDescription() {
        return XMLFunction.getElemValue(alias, "DESCRIPTION");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getClassName() {
        return XMLFunction.getElemValue(alias, "CLASS");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getUrl() {
        return XMLFunction.getElemValue(alias, "URL");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getTemplate() {
        return XMLFunction.getElemValue(alias, "TEMPLATE");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getClassType() {
        return XMLFunction.getAttrValue(alias, "CLASS", "TYPE").toUpperCase();
    }
    
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean usePreparedStatement() {
    	String aux = XMLFunction.getElemValue(alias, "PREPAREDSTATEMENT"); 
        return !aux.equalsIgnoreCase("false");
    }
    
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getValidationQuery() {
        return XMLFunction.getElemValue(alias, "VALIDATIONQUERY");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getVersion() {
        return DatabaseDrivers.getVersion(this);
    }
}
