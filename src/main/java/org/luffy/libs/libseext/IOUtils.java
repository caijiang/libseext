/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.libs.libseext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author luffy
 */
public class IOUtils {

    public static void processString(InputStream in, String code, StringProcesser sp) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, code));
            String str = reader.readLine();
            while (str != null) {
                if (sp.processString(str)) {
                    break;
                }
                str = reader.readLine();
            }
        } finally {
            in.close();
        }
    }

    public static void processString(InputStream in, String code) throws IOException {
        StringProcesser sp = (x) -> {
            System.out.println(x);
            return false;
        };
        processString(in, code, sp);
    }

    public static void processString(InputStream in) throws IOException {
        processString(in, "UTF-8");
    }

    public static void stream2StreamWithClose(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[1024];
        int readed = 0;
        try {
            while (true) {
                int s = in.read(buf, readed, buf.length);
                if (s == -1) {
                    break;
                } else {
                    out.write(buf, 0, s);
                    out.flush();
                    readed += s;
                }
            }
            out.flush();
        } finally {
            in.close();
            out.close();
        }

    }

    public static String toString(InputStream in) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String str = reader.readLine();
            return str;
        } finally {
            in.close();
        }
    }

}
