package net.wildpark.wpmaps.pageControllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
 
@ManagedBean
@SessionScoped
public class FileUploadView implements Serializable{
    
    
    private Part image;

//    public StreamedContent getImage() throws IOException {
////        if(point.getPic() != null){
////            image = point.getPic();
////        }else{
//            image = getBytesFile();
////        }
//        return new DefaultStreamedContent(new ByteArrayInputStream(image), "image/png/jpg");
//    }
//    
//    public byte[] getBytesFile() throws IOException{
//        
//        InputStream iStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/No-image-found.jpg");
//        Path path = Paths.get(iStream.toString());
//        byte[] data = Files.readAllBytes(path);
//        return data;
//    }
    public void setFile(){
        try{
            InputStream in = image.getInputStream();
            File f = new File("/Users/Documents/" + image.getSubmittedFileName());
            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int length;
            
            while ((length = in.read(buffer))>0) {
                out.write(buffer,0,length);
            }
            out.close();
            in.close();
            
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }
    


}