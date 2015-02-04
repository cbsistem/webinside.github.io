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

package br.com.webinside.modules.search;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import br.com.webinside.modules.Util;
import br.com.webinside.runtime.integration.DatabaseAliases;
import br.com.webinside.runtime.integration.InterfaceGrid;
import br.com.webinside.runtime.integration.InterfaceParameters;
import br.com.webinside.runtime.integration.JavaParameter;
import br.com.webinside.runtime.util.WIMap;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class SearchGrid extends WISearcher implements InterfaceGrid,
    InterfaceParameters {
    /**
     * DOCUMENT ME!
     *
     * @param context DOCUMENT ME!
     * @param databases DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Map[] execute(WIMap context, DatabaseAliases databases) {
        Hashtable[] ht = null;
        try {
            ht = getResultsIntoHashtable(context);
        } catch (Exception e) {
            context.put(WISearcher.ERROR_VAR, Util.stackTraceToString(e));
        }
        return ht;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public JavaParameter[] getInputParameters() {
        List l = getInputJavaParameters();
        JavaParameter[] jp = new JavaParameter[l.size()];
        l.toArray(jp);
        return jp;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public JavaParameter[] getOutputParameters() {
        List l = getOutputJavaParameters();
        JavaParameter[] jp = new JavaParameter[l.size()];
        l.toArray(jp);
        return jp;
    }

    /**
     * DOCUMENT ME!
     *
     * @param context DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Hashtable[] executeOld(WIMap context) {
        Hashtable[] ht = null;
        try {
            // realiza a busca...
            // no contexto do usu�rio deve haver uma vari�vel chamada
            // "tmp.indexName" ou "idx.indexName" indicando qual o �ndice
            // a ser usado para pesquisa e uma vari�vel chamada "idx.query"
            // ou "tmp.search.query"  contendo a express�o de busca
            String in = context.get("idx.indexName");
            if (in.equals("")) {
                in = context.get("tmp.indexName");
            }
            String indexName = context.get("wi.proj.root") + "/index/" + in;
            WISearcher ms = new WISearcher(indexName);

            String query = context.get("idx.query");
            if (query.equals("")) {
                query = context.get("tmp.search.query");
            }
            ms.setQuery(WISearchUtil.limparConteudo(query));
            // coloca no contexto a vari�vel "tmp.search.query" que indica a
            // express�o de busca utilizada
            context.put("tmp.search.query", query);

            long inicio = System.currentTimeMillis();
            ms.doIt();
            long tempo = System.currentTimeMillis() - inicio;

            // coloca no contexto a vari�vel "tmp.search.time" que indica a
            // dura��o da realiza��o da pesquisa
            context.put("tmp.search.time", String.valueOf(tempo));

            int count = ms.getHitsCount();

            // deprecated - usar tmp.searchResult.count por ser coloquialmente mais l�gica
            context.put("tmp.search.count", String.valueOf(count));
            // coloca no contexto a vari�vel "tmp.searchResult.count" que retorna
            // a quantidade de documentos encontrada pela busca
            context.put("tmp.searchResult.count", String.valueOf(count));

            // propriedades que esse grid possui...
            ht = ms.getResults();

            //			ht = new Hashtable[count];
            //
            //			for (int i = 0; i < count; i++) {
            //				ht[i] = new Hashtable();
            //
            //				// adiciona a propriedade "id"
            //				ht[i].put("id", String.valueOf(i));
            //
            //				// recupera as propriedades de cada um dos documentos encontrados
            //				// pela pesquisa, exceto o seu conte�do pois ele apenas � pesquis�vel,
            //				// e disponibiliza-as no contexto do WI para poderem ser
            //				// referenciadas na p�gina de modelo de um grid Java
            //				Enumeration e = ms.getHitInfo(i).getFields();
            //				while (e.hasMoreElements()) {
            //					Field field = (Field) e.nextElement();
            //					if (!field.name().equalsIgnoreCase("contents")) {
            //						ht[i].put(field.name(), field.stringValue());
            //					}
            //				}
            //			}
            // a variavel "tmp.searchResult.orderBy" deve conter o nome de uma das propriedades
            // do documento que ser� usada como base para ordena��o do resultado da busca
            String orderBy = context.get("tmp.searchResult.orderBy");

            // a variavel "tmp.searchResult.reverse" indica se a ordena��o do resultado
            // da busca ser� feita de maneira descendente (reversa). O valor default para
            // essa vari�vel � false, sendo assim os dados a princ�pio v�m em ordem crescente.
            // Essa vari�vel s� tem fun��o quando em uso conjunto com "tmp.searchResult.orderBy".
            boolean reverse =
                context.get("tmp.searchResult.reverse").equalsIgnoreCase("true");

            // a variavel "tmp.searchResult.caseSensitive" indica se a ordena��o do resultado
            // da busca ser� "sens�vel ao caso". O valor default para essa vari�vel � false.
            // Essa vari�vel s� tem fun��o quando em uso conjunto com "tmp.searchResult.orderBy".
            boolean caseSensitive =
                context.get("tmp.searchResult.caseSensitive").equalsIgnoreCase("true");
            if (!orderBy.equals("")) {
                Arrays.sort(ht,
                    new WIComparator(orderBy, reverse, caseSensitive));
            }
        } catch (Exception e) {
            context.put("tmp.search.error", Util.stackTraceToString(e));
        }
        return ht;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int returnType() {
        return InterfaceGrid.COMPLETE;
    }
}
