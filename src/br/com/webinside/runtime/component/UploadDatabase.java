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
public class UploadDatabase extends AbstractUpload {

	private static final long serialVersionUID = 1L;

    private static Document template;

    /**
     * Creates a new UploadDatabase object.
     *
     * @param id DOCUMENT ME!
     */
    public UploadDatabase(String id) {
        super(id);
        this.setType("DATABASE");
    }

    /**
     * Creates a new UploadDatabase object.
     *
     * @param id DOCUMENT ME!
     * @param element DOCUMENT ME!
     */
    public UploadDatabase(String id, Element element) {
        super(id, element);
        this.setType("DATABASE");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setDatabase(String value) {
        XMLFunction.setElemValue(this.upload, "DATABASE", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDatabase() {
        return XMLFunction.getElemValue(this.upload, "DATABASE");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setSql(String value) {
        XMLFunction.setElemValue(this.upload, "SQL", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSql() {
        return XMLFunction.getElemValue(this.upload, "SQL");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setSqlFilter(String value) {
        XMLFunction.setElemValue(this.upload, "SQLFILTER", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSqlFilter() {
        return XMLFunction.getElemValue(this.upload, "SQLFILTER");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Document getTemplate() {
        if (template == null) {
            template = CompFunction.getTemplate("upload_database.xml");
        }
        return template;
    }
    
    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setZip(boolean value) {
        String msg = (value == true) ? "ON" : "";
        XMLFunction.setElemValue(this.upload, "ZIP", msg);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean isZip() {
        return XMLFunction.getElemValue(this.upload, "ZIP").equals("ON");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setBase64(boolean value) {
        String msg = (value == true) ? "ON" : "";
        XMLFunction.setElemValue(this.upload, "BASE64", msg);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean isBase64() {
        return XMLFunction.getElemValue(this.upload, "BASE64").equals("ON");
    }

}
