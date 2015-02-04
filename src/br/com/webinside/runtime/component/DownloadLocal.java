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

import org.jdom.Document;
import org.jdom.Element;

import br.com.webinside.runtime.xml.XMLFunction;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.2 $
 */
public class DownloadLocal extends AbstractDownload {

	private static final long serialVersionUID = 1L;

    private static Document template;

    /**
     * Creates a new DownloadLocal object.
     *
     * @param id DOCUMENT ME!
     */
    public DownloadLocal(String id) {
        super(id);
        this.setType("LOCAL");
    }

    /**
     * Creates a new DownloadLocal object.
     *
     * @param id DOCUMENT ME!
     * @param element DOCUMENT ME!
     */
    public DownloadLocal(String id, Element element) {
        super(id, element);
        this.setType("LOCAL");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setDirectory(String value) {
        XMLFunction.setElemValue(this.download, "DIRECTORY", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDirectory() {
        return XMLFunction.getElemValue(this.download, "DIRECTORY");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setFile(String value) {
        XMLFunction.setElemValue(this.download, "FILE", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getFile() {
        return XMLFunction.getElemValue(this.download, "FILE");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public void setRealname(String realname) {
        XMLFunction.setElemValue(this.download, "REALNAME", realname);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getRealname() {
        return XMLFunction.getElemValue(this.download, "REALNAME");
    }
    
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Document getTemplate() {
        if (template == null) {
            template = CompFunction.getTemplate("download_local.xml");
        }
        return template;
    }
}
