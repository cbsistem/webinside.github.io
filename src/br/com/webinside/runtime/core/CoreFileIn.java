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

package br.com.webinside.runtime.core;

import br.com.webinside.runtime.component.FileIn;
import br.com.webinside.runtime.integration.ProducerParam;
import br.com.webinside.runtime.util.FileIO;
import br.com.webinside.runtime.util.StringA;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class CoreFileIn extends CoreCommon {
    private FileIn fileIn;

    /**
     * Creates a new CoreFileIn object.
     *
     * @param wiParams DOCUMENT ME!
     * @param fileIn DOCUMENT ME!
     */
    public CoreFileIn(ExecuteParams wiParams, FileIn fileIn) {
        this.wiParams = wiParams;
        this.fileIn = fileIn;
        element = fileIn;
    }

    /**
     * DOCUMENT ME!
     */
    public void execute() {
        if (fileIn == null || isDisabledCondition()) {
            return;
        }
        String file = "";
        if (isValidCondition()) {
            file = fileIn.getSourceIfTrue().trim();
        } else {
            file = fileIn.getSourceIfFalse().trim();
        }
        if (file.equals("")) {
            return;
        }
        ProducerParam prod = new ProducerParam();
        prod.setWIMap(wiMap);
        prod.setInput(file);
        wiParams.getProducer().setParam(prod);
        wiParams.getProducer().execute();
        file = prod.getOutput();
        file = StringA.change(file, "../", "/");
        FileIO fl = new FileIO(file, 'r');
        String content = fl.readText();
        fl.close();
        String wiobj = fileIn.getWIObj().trim();
        if (fileIn.getActivePage().equals("ON")) {
            prod.setInput(content);
            wiParams.getProducer().setParam(prod);
            wiParams.getProducer().execute();
            content = prod.getOutput();
        }
        if (fileIn.getDecodeXML().equals("ON")) {
            new CoreXmlImport(wiMap, wiobj).execute(content);
        } else {
            wiMap.put(wiobj, content);
        }
        writeLog();
    }
}
