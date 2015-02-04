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

import br.com.webinside.runtime.xml.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class RedirSql extends AbstractRedir {

	private static final long serialVersionUID = 1L;

    /**
     * Creates a new RedirSql object.
     */
    public RedirSql() {
        this.setSeq("");
    }

    /**
     * Creates a new RedirSql object.
     *
     * @param seq DOCUMENT ME!
     */
    public RedirSql(String seq) {
        this.setSeq(seq);
    }

    /**
     * Creates a new RedirSql object.
     *
     * @param element DOCUMENT ME!
     */
    public RedirSql(Element element) {
        if ((element == null) || (!element.getName().equals("REDIR"))) {
            element = new Element("REDIR");
        }
        this.redir = element;
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setWIObj(String value) {
        XMLFunction.setElemValue(this.redir, "WIOBJ", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getWIObj() {
        return XMLFunction.getElemValue(this.redir, "WIOBJ");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setDatabase(String value) {
        XMLFunction.setElemValue(this.redir, "DATABASE", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDatabase() {
        return XMLFunction.getElemValue(this.redir, "DATABASE");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setSql(String value) {
        XMLFunction.setElemValue(this.redir, "SQL", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSql() {
        return XMLFunction.getElemValue(this.redir, "SQL");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setSqlFilter(String value) {
        XMLFunction.setElemValue(this.redir, "SQLFILTER", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSqlFilter() {
        return XMLFunction.getElemValue(this.redir, "SQLFILTER");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setWhen(String value) {
        XMLFunction.setElemValue(this.redir, "WHEN", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getWhen() {
        return XMLFunction.getElemValue(this.redir, "WHEN");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public AbstractActionElement cloneMe() {
        RedirSql obj = new RedirSql((Element) redir.clone());
        return obj;
    }
}
