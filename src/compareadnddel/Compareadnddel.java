/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compareadnddel;

import java.io.File;
import java.util.Arrays;

/**
 *
 * @author Luc
 */
public class Compareadnddel {

    /**
     * @param args the command line arguments
     * @throws compareadnddel.QueueException
     */
    public static void main(String[] args) throws QueueException {
        File sideToDelete = new File(args[0]);
        File sideToKeep = new File(args[1]);
        File[] sidea = sideToDelete.listFiles();
        String[] sideb = sideToKeep.list();
        ArrayQueue<File> n = new ArrayQueue<>();
        ArrayQueue<String> n1 = new ArrayQueue<>(200000);

        for (File file : sidea) {
            n.enqueue(file);
        }
        for (String string : sideb) {
            n1.enqueue(string);
        }
        while (!n1.isEmpty() && !n.isEmpty()) {
            try {
                String src = "";
                if (n.front().getName().endsWith("mp4")) {
                    src = n.front().getName().split(".mp4")[0];
                } else {
                    src = n.front().getName().split(".dav")[0];
                }
                String dst = n1.front().split(".mp")[0];
                if (src.equals(dst)) {
                    n1.dequeue();

                    n.dequeue().delete();
                } else if (src.compareToIgnoreCase(dst) < 0) {
                    n.dequeue();
                } else {
                    n1.dequeue();
                }
            } catch (Exception f) {
                System.err.println(f.getMessage());
                n1.dequeue();
            }
        }
        System.out.println(Arrays.toString(sidea));

    }

}
