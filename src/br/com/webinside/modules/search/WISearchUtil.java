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

import java.io.*;
import java.util.Hashtable;
import org.apache.lucene.index.IndexReader;

import br.com.webinside.runtime.util.WIMap;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class WISearchUtil {
    /** DOCUMENT ME! */
    private static final boolean DEBUG = false;
    /** DOCUMENT ME! */
    private static final String DELIMITER = "|";
    
    private static Hashtable extraField;

    /**
     * DOCUMENT ME!
     *
     * @param context DOCUMENT ME!
     * @param content DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String processContent(WIMap context, String content) {
        StringBuffer sb = new StringBuffer();

        int iniDelim = -1;
        int endDelim = -1;
        int offset = 0;
        while ((iniDelim = content.indexOf(WISearchUtil.DELIMITER, offset)) != -1) {
            sb.append(content.substring(offset, iniDelim));
            if ((
                            endDelim =
                                content.indexOf(WISearchUtil.DELIMITER,
                                    iniDelim + 1)
                        ) != -1) {
                String var = content.substring(iniDelim + 1, endDelim);
                sb.append(context.get(var));
            }
            offset = endDelim + 1;
        }
        sb.append(content.substring(offset, content.length()));

        return sb.toString();
    }

    /**
     * DOCUMENT ME!
     *
     * @param txt DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String limparConteudo(String txt) {
        txt = txt.replace('�', 'a');
        txt = txt.replace('�', 'A');
        txt = txt.replace('�', 'e');
        txt = txt.replace('�', 'E');
        txt = txt.replace('�', 'i');
        txt = txt.replace('�', 'I');
        txt = txt.replace('�', 'o');
        txt = txt.replace('�', 'O');
        txt = txt.replace('�', 'u');
        txt = txt.replace('�', 'U');
        txt = txt.replace('�', 'a');
        txt = txt.replace('�', 'A');
        txt = txt.replace('�', 'e');
        txt = txt.replace('�', 'E');
        txt = txt.replace('�', 'i');
        txt = txt.replace('�', 'I');
        txt = txt.replace('�', 'o');
        txt = txt.replace('�', 'O');
        txt = txt.replace('�', 'u');
        txt = txt.replace('�', 'U');
        txt = txt.replace('�', 'a');
        txt = txt.replace('�', 'A');
        txt = txt.replace('�', 'e');
        txt = txt.replace('�', 'E');
        txt = txt.replace('�', 'i');
        txt = txt.replace('�', 'I');
        txt = txt.replace('�', 'o');
        txt = txt.replace('�', 'O');
        txt = txt.replace('�', 'u');
        txt = txt.replace('�', 'U');
        txt = txt.replace('�', 'a');
        txt = txt.replace('�', 'A');
        txt = txt.replace('�', 'e');
        txt = txt.replace('�', 'E');
        txt = txt.replace('�', 'i');
        txt = txt.replace('I', 'I');
        txt = txt.replace('�', 'o');
        txt = txt.replace('�', 'O');
        txt = txt.replace('�', 'u');
        txt = txt.replace('�', 'U');
        txt = txt.replace('�', 'a');
        txt = txt.replace('�', 'A');
        txt = txt.replace('�', 'o');
        txt = txt.replace('�', 'O');
        txt = txt.replace('�', 'c');
        txt = txt.replace('�', 'C');
        return txt;
    }

    /**
     * DOCUMENT ME!
     *
     * @param reader DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws IOException DOCUMENT ME!
     */
    public static Reader limparConteudo(Reader reader)
        throws IOException {
        char[] c = new char[1];
        StringWriter sw = new StringWriter();

        while (reader.read(c) != -1) {
            sw.write(c);
        }
        reader.close();
        sw.close();

        return new StringReader(WISearchUtil.limparConteudo(sw.toString()));
    }

    /**
     * DOCUMENT ME!
     *
     * @param msg DOCUMENT ME!
     */
    public static void debug(Object msg) {
        System.out.println(msg);
    }

    /**
     * M�todo que retorna um identificador �nico para um documento a ser
     * inserido no �ndice. A informa��o sobre o contador est� armazenado no
     * arquivo "counter.dat" que cont�m o pr�ximo identificador a ser
     * associado a um documento. Esse identificador sera �til quando se quiser
     * que um documento espec�fico seja removido do �ndice pois a numera��o
     * gerada pelo pr�prio �ndice n�o garante que o mesmo documento sempre
     * ter� o mesmo n�mero (chaves ef�meras).
     *
     * @param path diret�rio onde se encontra o arquivo "counter.dat"
     *
     * @return um identificador �nico para um documento a ser inserido no
     *         �ndice.
     *
     * @throws IOException DOCUMENT ME!
     * @throws FileNotFoundException DOCUMENT ME!
     */
    public static synchronized String getIDDoc(String path)
        throws IOException, FileNotFoundException {
        int count = 0;
        File file = new File(path, "counter.dat");

        if (!file.exists()) {
            //se o arquivo que armazena o contador n�o existir, crie-o
            file.createNewFile();
        } else {
            //se o arquivo j� existir, recupere o valor do contador
            ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(file));
            count = ois.readInt();
            ois.close();
        }

        //atualize o contador incrementando-o de 1
        ObjectOutputStream oos =
            new ObjectOutputStream(new FileOutputStream(file));
        oos.writeInt(count + 1);
        oos.close();

        return String.valueOf(count);
    }

    /**
     * Metodo que verifica se o �ndice j� foi aberto para pesquisa e armazenado
     * em cache. Se sim, fecha-o para que possa ser feita a atualiza��o.
     *
     * @param indexName DOCUMENT ME!
     *
     * @throws IOException DOCUMENT ME!
     */
    public static synchronized void closeIndexReader(String indexName)
        throws IOException {
        Hashtable indexCache = CachedIndex.indexCache;
        CachedIndex ci = null;
        if (indexCache != null) {
            ci = (CachedIndex) indexCache.get(indexName);
            if (ci != null) {
                ci.close();
                //testando...
                //um codigo abaixo foi comentado em virtude disso
                // remove do cache o IndexReader que est� instanciado
                indexCache.remove(indexName);
                if (DEBUG) {
                    WISearchUtil.debug(
                        "Indice aberto para leitura, removendo-o!");
                }
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param name DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws IOException DOCUMENT ME!
     */
    public static IndexReader getIndexReader(String name)
        throws IOException {
        CachedIndex index = (CachedIndex) CachedIndex.indexCache.get(name);

        // look in cache
        if ((index != null)
                    && (index.modified == IndexReader.getCurrentVersion(name))) {
            return index.reader; // cache hit
        } else {
            index = new CachedIndex(name); // cache miss
        }
        CachedIndex.indexCache.put(name, index); // add to cache
        return index.reader;
    }

    /**
     * M�todo que armazena as informa��es adicionais de um documento em um
     * arquivo. O conte�do do arquivo � um objeto Hashtable serializado onde a
     * chave dos elementos � o nome de um arquivo e o valor e um outro objeto
     * Hashtable onde est�o armazenadas as propriedades adicinais do documento
     * representado por este arquivo.
     *
     * @param indexName DOCUMENT ME!
     * @param fileName DOCUMENT ME!
     * @param ht DOCUMENT ME!
     *
     * @throws IOException DOCUMENT ME!
     * @throws ClassNotFoundException DOCUMENT ME!
     * @throws OptionalDataException DOCUMENT ME!
     * @throws FileNotFoundException DOCUMENT ME!
     */
    public static synchronized void appendExtraFieldsInfo(String indexName,
        String fileName, Hashtable ht)
        throws IOException, ClassNotFoundException, OptionalDataException, 
            FileNotFoundException {
        String indexPath = new File(indexName).getAbsolutePath();
        File file = new File(indexPath, "index.dat");

        if (!file.exists()) {
            extraField = new Hashtable();
            file.createNewFile();
        } else if (extraField == null) {
            ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(file));
            extraField = (Hashtable) ois.readObject();
            ois.close();
        }

        ObjectOutputStream oos =
            new ObjectOutputStream(new FileOutputStream(file));
        extraField.put(fileName, ht);
        oos.writeObject(extraField);
        oos.close();
    }
}
