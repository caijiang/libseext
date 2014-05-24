/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.libs.libseext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author luffy
 */
public class IOUtils {

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

}
