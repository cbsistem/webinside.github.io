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

package br.com.webinside.runtime.util;

import javax.crypto.*;

import java.math.BigInteger;
import java.security.*;

/**
 * Classe utilizada para criptogratia de textos.
 *
 * @author Geraldo Moraes
 * @version $Revision: 1.2 $
 *
 * @since 3.0
 */
public class Crypto {
    private String strKey;
    private SecretKey desKey;

    /**
     * Cria um novo Crypto.
     *
     * @param key a chave que ser� usada no DES.
     */
    public Crypto(String key) {
        if (key == null) {
            key = new String();
        }
        strKey = key;
        try {
            KeyGenerator kg = KeyGenerator.getInstance("DES", "SunJCE");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(strKey.getBytes());            
            kg.init(sr);
            desKey = kg.generateKey();
        } catch (Exception err) {
        	err.printStackTrace(System.err);
            // Nunca deve ocorrer.
        }
    }

    /**
     * Codifica um texto para o formato DES.
     *
     * @param text o texto a ser codificado.
     *
     * @return o texto no formato DES.
     */
    public String encodeDES(String text) {
        StringBuffer resp = new StringBuffer();
        if (text != null) {
            try {
                Cipher cipher = Cipher.getInstance("DES", "SunJCE");
                cipher.init(Cipher.ENCRYPT_MODE, desKey);
                byte[] enc = cipher.doFinal(text.getBytes());
                for (int i = 0; i < enc.length; i++) {
                    if (((int) enc[i] & 0xff) < 0x10) {
                        resp.append("0");
                    }
                    resp.append(Long.toString((int) enc[i] & 0xff, 16));
                }
            } catch (Exception err) {
                err.printStackTrace(System.err);
                // Nunca deve ocorrer.
            }
        }
        return resp.toString();
    }

    /**
     * Decodifica um texto no formato DES.
     *
     * @param text o texto no formato DES.
     *
     * @return o texto decodificado.
     */
    public String decodeDES(String text) {
        if (text == null) {
            return "";
        }
        try {
            Cipher cipher = Cipher.getInstance("DES", "SunJCE");
            AlgorithmParameters algParams = cipher.getParameters();
            cipher.init(Cipher.DECRYPT_MODE, desKey, algParams);
            int size = text.length() / 2;
            byte[] msg = new byte[size];
            for (int i = 0; i < (size * 2); i = i + 2) {
                String hex = StringA.mid(text, i, i + 1);
                msg[i / 2] = (byte) (Integer.parseInt(hex, 16));
            }
            byte[] dec = cipher.doFinal(msg);
            return new String(dec).trim();
        } catch (Exception err) {
        	System.err.println(err.getMessage() + " in password \"" + text + "\"");
        }
        return "";
    }

    public static String encodeMD5(String text) {
        if (text != null) {
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.update(text.getBytes());
                return new BigInteger(1, digest.digest()).toString(16);
            } catch (NoSuchAlgorithmException err) {
            	err.printStackTrace(System.err);
                // Nunca deve ocorrer.
            }
        }
        return "";
    }
    
    public static String encodeSHA1(String text) {
        if (text != null) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA1");
                digest.update(text.getBytes());
                return new BigInteger(1, digest.digest()).toString(16);
            } catch (NoSuchAlgorithmException err) {
            	err.printStackTrace(System.err);
                // Nunca deve ocorrer.
            }
        }
        return "";
    }
    
    public static void main(String[] args) {
    	System.out.println(Crypto.encodeSHA1("geraldo"));
    }
    
}
