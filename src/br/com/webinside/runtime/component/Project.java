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

import org.jdom.Element;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class Project extends AbstractProject {

	private static final long serialVersionUID = 1L;
	private ProjectElementsMap pages;
    private ProjectElementsMap combos;
    private ProjectElementsMap grids;
    private ProjectElementsMap events;
    private ProjectElementsMap downloads;
    private ProjectElementsMap uploads;
    private ProjectElementsMap webservices;

    /**
     * Creates a new Project object.
     *
     * @param name DOCUMENT ME!
     */
    public Project(String name) {
        super(name);
    }

    /**
     * Creates a new Project object.
     *
     * @param id DOCUMENT ME!
     * @param element DOCUMENT ME!
     */
    public Project(String id, Element element) {
        super(id, element);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Databases getDatabases() {
        Databases dbs = new Databases(project, project.getChild("DATABASES"));
        dbs.setProject(this);
        return dbs;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Hosts getHosts() {
        Hosts hts = new Hosts(project, project.getChild("HOSTS"));
        hts.setProject(this);
        return hts;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ProjectElementsMap getCombos() {
        if (combos == null) {
            combos = new ProjectElementsHashMap();
            combos.setDirectory(Combo.DIRECTORY);
            combos.setProject(this);
        }
        return combos;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ProjectElementsMap getGrids() {
        if (grids == null) {
            grids = new ProjectElementsHashMap();
            grids.setDirectory(AbstractGrid.DIRECTORY);
            grids.setProject(this);
        }
        return grids;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ProjectElementsMap getEvents() {
        if (events == null) {
            events = new ProjectElementsHashMap();
            events.setDirectory(AbstractEvent.DIRECTORY);
            events.setProject(this);
        }
        return events;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ProjectElementsMap getPages() {
        if (pages == null) {
            pages = new ProjectElementsHashMap();
            pages.setDirectory(Page.DIRECTORY);
            pages.setProject(this);
        }
        return pages;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ProjectElementsMap getDownloads() {
        if (downloads == null) {
            downloads = new ProjectElementsHashMap();
            downloads.setDirectory(AbstractDownload.DIRECTORY);
            downloads.setProject(this);
        }
        return downloads;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ProjectElementsMap getUploads() {
        if (uploads == null) {
            uploads = new ProjectElementsHashMap();
            uploads.setDirectory(AbstractUpload.DIRECTORY);
            uploads.setProject(this);
        }
        return uploads;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ProjectElementsMap getWebServices() {
        if (webservices == null) {
            webservices = new ProjectElementsHashMap();
            webservices.setDirectory(WebService.DIRECTORY);
            webservices.setProject(this);
        }
        return webservices;
    }
}
