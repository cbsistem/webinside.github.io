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

import java.io.File;
import java.util.Hashtable;

import br.com.webinside.modules.Util;
import br.com.webinside.runtime.integration.*;
import br.com.webinside.runtime.util.WIMap;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class WIPublisher extends AbstractConnector
    implements InterfaceParameters {
    //par�metros de sa�da
    private static final String ERROR_VAR = "tmp.publisher.error";
    private static final String STATUS_VAR = "tmp.publisher.status";
    private static final String IDDOC_VAR = "tmp.publisher.iddoc";

    //par�metros de entrada
    private static final String FILE_VAR = "tmp.arquivo";
    private static final String INDEX_NAME_VAR = "tmp.indexName";
    private static final String PUBLISHING_DIR_VAR = "tmp.publishingDir";
    private static final String CONTENT = "tmp.publishContent";
    
    private static Object sync = new Object();

    /**
     * DOCUMENT ME!
     *
     * @param context DOCUMENT ME!
     * @param databases DOCUMENT ME!
     * @param headers DOCUMENT ME!
     */
    public void execute(WIMap context, DatabaseAliases databases,
        InterfaceHeaders headers) {
        //by LRBS 2.1.5b21
        Indexer ind = null;

        //-by LRBS 2.1.5b21
        try {
            synchronized (sync) {
                // o �ndice a ser utilizado
                String indexName =
                    context.get("wi.proj.path") + "/WEB-INF/index/"
                    + context.get(WIPublisher.INDEX_NAME_VAR);

                // verifica se o �ndice j� se encontra aberto para pesquisa e armazenado em cache.
                // Se sim, fecha-o para que possa ser feita a atualiza��o.
                Hashtable indexCache = CachedIndex.indexCache;
                CachedIndex ci = null;
                if (indexCache != null) {
                    ci = (CachedIndex) indexCache.get(indexName);
                    if (ci != null) {
                        ci.close();
                        //testando...
                        // um codigo abaixo foi comentado em virtude disso
                        // remove do cache o IndexReader que est� instanciado
                        indexCache.remove(indexName);
                        //Utilitario.debug("Indice aberto para leitura, removendo-o!");
                    }
                }

                // DEBUG
                //context.put("tmp.res", "Fechei o cache de leitura!");
                // abre o indice para alteracao (instancia o gravador de indice)
                //by LRBS 2.1.5b21
                //Indexer ind = new Indexer(indexName, Indexer.UPDATE);
                //-by LRBS 2.1.5b21
                //by LRBS 2.1.5b21
                String tmpArquivo = context.get(WIPublisher.FILE_VAR);

                if (!tmpArquivo.equals("")) {
                    ind = new Indexer(indexName, Indexer.UPDATE);

                    // instancia o arquivo a ser incluido no �ndice
                    //1a					File file = new File(Utilitario.processContent(context, ind.getDataSource()), tmpArquivo);
                    //2a          File file = ind.getFile(tmpArquivo);
                    //recupere o diret�rio onde o arquivo foi publicado
                    String dataSource =
                        context.get(WIPublisher.PUBLISHING_DIR_VAR);

                    //se o diret�rio n�o foi informado recupere o reposit�rio configurado para o �ndice
                    if (dataSource.equals("")) {
                        dataSource = ind.getDataSource();
                    }

                    //-by LRBS 2.1.5b21
                    //cria uma tabela hash com as propriedades adicionais que os documentos desse �ndice podem ter
                    Hashtable ht = new Hashtable();

                    //-by LRBS 2.1.5b36
                    String iddoc = WISearchUtil.getIDDoc(indexName);
                    context.put(WIPublisher.IDDOC_VAR, iddoc);
                    //by LRBS
                    //ht.put("iddoc", getIDDoc(indexName));
                    ht.put("iddoc", iddoc);
                    String[] extraFields =
                        Util.split(ind.getExtraFields(), ", ");
                    if (extraFields != null) {
                        for (int i = 0; i < extraFields.length; i++) {
                            String campo = extraFields[i];

                            //ht.put(campo, Utilitario.limparConteudo(context.get("tmp." + campo)));
                            ht.put(campo, context.get("tmp." + campo));
                        }
                    }
                    ind.getWIDocument().setProperties(ht);

                    //instancia o arquivo a ser publicado no �ndice
                    File file = new File(dataSource, tmpArquivo);

                    if ((file != null) && file.exists() && file.isFile()
                                && file.canRead()) {
                        //by LRBS 2.1.5b21
                        //Indexer ind = new Indexer(indexName, Indexer.UPDATE);
                        //-by LRBS 2.1.5b21
                        WISearchUtil.appendExtraFieldsInfo(indexName,
                            file.getAbsolutePath(), ht);
                        //adiciona as propriedades ao documento a ser indexado
                        //indexa o documento...
                        if (ind.indexDocs(file)) {
                            ind.getWriter().mergeFactor = 20;
                            ind.getWriter().optimize();
                        }
                    } else {
                        ind.indexDocs(context.get(WIPublisher.CONTENT));
                    }
                    context.put("tmp.res", ind.getDataSource()
                            + context.get(WIPublisher.FILE_VAR));
                        context.put(WIPublisher.STATUS_VAR,
                            "OK;" + context.get(WIPublisher.FILE_VAR));
                } else {
                    context.put(WIPublisher.ERROR_VAR,
                        "Nenhum arquivo publicado.");
                }
            }
        } catch (Exception e) {
            context.put("tmp.res", e.getMessage());
            //by LRBS 2.1.5b21
            context.put(WIPublisher.ERROR_VAR, Util.stackTraceToString(e));
            //-by LRBS 2.1.5b21
        } finally {
            //by LRBS 2.1.5b21
            try {
                if (ind != null) {
                    ind.getWriter().close();
                }
            } catch (java.io.IOException ioe) {
                context.put(WIPublisher.ERROR_VAR,
                    "�ndice n�o pode ser fechado: " + ioe.getMessage());
            }

            //-by LRBS 2.1.5b21
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public JavaParameter[] getInputParameters() {
        JavaParameter[] jp = new JavaParameter[4];
        jp[0] =
            new JavaParameter(WIPublisher.INDEX_NAME_VAR,
                "Nome do �ndice a ser usado");
        jp[1] =
            new JavaParameter(WIPublisher.FILE_VAR,
                "Nome do arquivo publicado",
                "Se o arquivo foi publicado atrav�s de um upload utilize a vari�vel wi.upl.filename.");
        jp[2] =
            new JavaParameter(WIPublisher.PUBLISHING_DIR_VAR,
                "Diret�rio onde o arquivo foi publicado",
                "Se vazio, ir� publicar o conte�do informado no pr�ximo campo. Se o arquivo foi publicado atrav�s de um upload utilize a vari�vel wi.upl.path.");
        jp[3] =
            new JavaParameter(WIPublisher.CONTENT,
                "Conte�do a ser indexado",
                "Se o diret�rio n�o for informado, esse conte�do ser� anexado.");
        return jp;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public JavaParameter[] getOutputParameters() {
        JavaParameter[] jp = new JavaParameter[3];
        jp[0] =
            new JavaParameter(WIPublisher.ERROR_VAR,
                "Vari�vel que conter� o stack trace se ocorrer algum erro "
                + "durante a publica��o de um documento.");
        jp[1] =
            new JavaParameter(WIPublisher.IDDOC_VAR,
                "Vari�vel que conter� o identificador �nico do documento publicado.");
        jp[2] =
            new JavaParameter(WIPublisher.STATUS_VAR,
                "Vari�vel que indica que a publica��o foi efetuada com sucesso.");
        return jp;
    }
}
