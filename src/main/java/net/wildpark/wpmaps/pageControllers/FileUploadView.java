package net.wildpark.wpmaps.pageControllers;

import javax.faces.bean.ManagedBean;
import org.primefaces.event.FileUploadEvent; 
import org.primefaces.model.UploadedFile;
 
@ManagedBean


public class FileUploadView {
     
    private UploadedFile file;
     
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public byte[] upload() {
        byte[] imgByte = file.getContents();
        return imgByte;
    }
//    public void upload(FileUploadEvent event) {
//        UploadedFile uploadedFile = event.getFile();
//        String fileName = uploadedFile.getFileName();
//        String contentType = uploadedFile.getContentType();
//        byte[] contents = uploadedFile.getContents(); // Or getInputStream()
//         ... Save it, now!
//    }

    public void fileUploadListener(FileUploadEvent e){
            // Get uploaded file from the FileUploadEvent

            this.file = e.getFile();

            // Print out the information of the file
            System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize() + "Some" + file.getContents());
    }



}