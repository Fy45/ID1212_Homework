/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrmi;

/**
 *
 * @author user
 */
public class file {
        private static String filename;
        private static String fileowner;
        private static String fileprivacy;
        public file(String filename, String fileowner,String fileprivacy){
          this.filename=filename;
          this.fileowner=fileowner;
          this.fileprivacy=fileprivacy;
        }
        public static String getfileName() {
            return filename;
        }

        public void setfileName(String fileName) {
            this.filename = fileName;
        }
         public static String getfileowner() {
            return fileowner;
        }

        public void setfileowner(String fileowner) {
            this.fileowner = fileowner;
        }

        public static String getfileprivacy() {
            return fileprivacy;
        }

        public void setUserPassword(String fileprivacy) {
            this.fileprivacy = fileprivacy;
}
}
