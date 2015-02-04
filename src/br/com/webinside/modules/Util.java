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

package br.com.webinside.modules;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.StringTokenizer;

/**
 * DOCUMENT ME!
 *
 * @author Luiz Ricardo To change the template for this generated type comment
 *         go to Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Util {
    /**
     * DOCUMENT ME!
     *
     * @param str DOCUMENT ME!
     * @param delim DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String[] split(String str, String delim) {
        StringTokenizer st = new StringTokenizer(str, delim);
        String[] tokens = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            tokens[i++] = st.nextToken();
        }
        return tokens;
    }

    /**
     * DOCUMENT ME!
     *
     * @param t DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String stackTraceToString(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    /**
     * DOCUMENT ME!
     *
     * @param f DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getAbsolutePath(File f) {
        return (f.isFile()) ? f.getParentFile().getAbsolutePath()
                            : f.getAbsolutePath();
    }

    /**
     * Retorna o caminho do diret�rio do arquivo <code>file</code> relativo ao
     * diret�rio <code>dir</code>.
     *
     * @param file o arquivo.
     * @param dir o diret�rio.
     *
     * @return o caminho do diret�rio do arquivo <code>file</code> relativo ao
     *         diret�rio <code>dir</code>.
     */
    public static String getRelativePath(File file, File dir) {
        String path = getAbsolutePath(file);
        int i = path.compareTo(dir.getAbsolutePath());
        return (i > 0) ? path.substring(i + 1)
                       : "";
    }
}
