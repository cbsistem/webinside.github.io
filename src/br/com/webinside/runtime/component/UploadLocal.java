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
public class UploadLocal extends AbstractUpload {

	private static final long serialVersionUID = 1L;

    private static Document template;

    /**
     * Creates a new UploadLocal object.
     *
     * @param id DOCUMENT ME!
     */
    public UploadLocal(String id) {
        super(id);
        this.setType("LOCAL");
    }

    /**
     * Creates a new UploadLocal object.
     *
     * @param id DOCUMENT ME!
     * @param element DOCUMENT ME!
     */
    public UploadLocal(String id, Element element) {
        super(id, element);
        this.setType("LOCAL");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setDirectory(String value) {
        XMLFunction.setElemValue(this.upload, "DIRECTORY", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDirectory() {
        return XMLFunction.getElemValue(this.upload, "DIRECTORY");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setCreateDir(String value) {
        XMLFunction.setElemValue(this.upload, "CREATEDIR", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getCreateDir() {
        return XMLFunction.getElemValue(this.upload, "CREATEDIR");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setFile(String value) {
        XMLFunction.setElemValue(this.upload, "FILE", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getFile() {
        return XMLFunction.getElemValue(this.upload, "FILE");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Document getTemplate() {
        if (template == null) {
            template = CompFunction.getTemplate("upload_local.xml");
        }
        return template;
    }
}
