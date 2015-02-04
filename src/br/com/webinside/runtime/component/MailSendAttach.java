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
public class MailSendAttach {
    /** DOCUMENT ME! */
    protected Element attach;

    /**
     * Creates a new SendAttach object.
     */
    public MailSendAttach() {
        attach = new Element("SENDATTACH");
    }

    /**
     * Creates a new SendAttach object.
     *
     * @param element DOCUMENT ME!
     */
    public MailSendAttach(Element element) {
        if ((element == null) || (!element.getName().equals("SENDATTACH"))) {
            element = new Element("SENDATTACH");
        }
        this.attach = element;
    }

    /**
     * DOCUMENT ME!
     *
     * @param element DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected int insertInto(Element element) {
        if (element == null) {
            return ErrorCode.NULL;
        }
        element.addContent(attach);
        return ErrorCode.NOERROR;
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setDownload(String value) {
        XMLFunction.setElemValue(attach, "DOWNLOAD", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDownload() {
        return XMLFunction.getElemValue(attach, "DOWNLOAD");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setMime(String value) {
        XMLFunction.setElemValue(attach, "MIME", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMime() {
        return XMLFunction.getElemValue(attach, "MIME");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setBinary(String value) {
        XMLFunction.setElemValue(attach, "BINARY", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getBinary() {
        return XMLFunction.getElemValue(attach, "BINARY");
    }
}
