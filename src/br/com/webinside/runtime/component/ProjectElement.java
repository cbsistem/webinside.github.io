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

import java.io.Serializable;

import org.jdom.Document;
import org.jdom.Element;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface ProjectElement extends Serializable {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getId();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String toJSP();

    /**
     * DOCUMENT ME!
     *
     * @param project DOCUMENT ME!
     */
    public void setProject(AbstractProject project);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public AbstractProject getProject();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Element getElement();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Document getTemplate();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getType();
}
