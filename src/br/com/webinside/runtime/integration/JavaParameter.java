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

package br.com.webinside.runtime.integration;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class JavaParameter {
    private String varId;
    private String description;
    private String hint = "";
    private String value = "";

    /**
     * Creates a new JavaParameter object.
     *
     * @param varID DOCUMENT ME!
     * @param description DOCUMENT ME!
     * @param hint DOCUMENT ME!
     */
    public JavaParameter(String varID, String description, String hint) {
        this.varId = varID;
        this.description = description;
        this.hint = hint;
    }

    /**
     * Creates a new JavaParameter object.
     *
     * @param varID DOCUMENT ME!
     * @param description DOCUMENT ME!
     */
    public JavaParameter(String varID, String description) {
        this.varId = varID;
        this.description = description;
    }

    /**
     * Creates a new JavaParameter object.
     *
     * @param varID DOCUMENT ME!
     */
    public JavaParameter(String varID) {
        this.varId = varID;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getVarId() {
        return varId;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDescription() {
        return description;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getHint() {
        return hint;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String toString() {
        return "{varId=[" + varId + "], description=[" + description
        + "], hint=[" + hint + "], value=[" + value + "]}";
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getValue() {
        return value;
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setValue(String value) {
        this.value = value;
    }
}
