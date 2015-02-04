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

import java.util.HashMap;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class ProjectElementsHashMap extends HashMap
    	implements ProjectElementsMap {

	private static final long serialVersionUID = 1L;
	private AbstractProject project;
    private String directory = "";

    /**
     * DOCUMENT ME!
     *
     * @param project DOCUMENT ME!
     */
    public void setProject(AbstractProject project) {
        this.project = project;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public AbstractProject getProject() {
        return project;
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ProjectElement getElement(String key) {
        return (ProjectElement) get(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param element DOCUMENT ME!
     */
    public void putElement(ProjectElement element) {
        if ((element == null) || element.getId().equals("")) {
            return;
        }
        element.setProject(project);
        put(element.getId(), element);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * DOCUMENT ME!
     *
     * @param dir DOCUMENT ME!
     */
    public void setDirectory(String dir) {
        if (dir == null) {
            return;
        }
        directory = dir;
    }
}
