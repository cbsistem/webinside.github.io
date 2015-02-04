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
public class FileRemoveFtp extends AbstractFileRemove {

	private static final long serialVersionUID = 1L;

    /**
     * Creates a new FileRemoveFtp object.
     */
    public FileRemoveFtp() {
        this.setSeq("");
    }

    /**
     * Creates a new FileRemoveFtp object.
     *
     * @param element DOCUMENT ME!
     */
    public FileRemoveFtp(Element element) {
        if ((element == null) || (!element.getName().equals("REMOVEFILE"))) {
            element = new Element("REMOVEFILE");
        }
        this.removeFile = element;
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setHostId(String value) {
        XMLFunction.setElemValue(this.removeFile, "HOSTID", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getHostId() {
        return XMLFunction.getElemValue(this.removeFile, "HOSTID");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public AbstractActionElement cloneMe() {
        FileRemoveFtp obj = new FileRemoveFtp((Element) removeFile.clone());
        return obj;
    }
}
