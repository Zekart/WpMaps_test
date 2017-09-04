package net.wildpark.wpmaps.pageControllers;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Base64;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent; 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
 
@ManagedBean

@SessionScoped
public class FileUploadView implements Serializable{
    
    private String value;
    private String val;
    private int valType = 3;
    private String selectType;
    private int m_Index = 0;
     
    private UploadedFile uploadedFile;
    private byte[] fileContents;
    
    @PostConstruct
    public void init() {selectType = "select";}
    
    public void change_mIndex(){
        RequestContext context = RequestContext.getCurrentInstance();

        switch (valType) {
            case 1:  selectType = "PILLAR";
                    context.execute("PF('addPillarDialog').show();");
                    break;
            case 2:  selectType = "HOUSE";
                    context.execute("PF('addHouseDialog').show();");
                    break;
            case 3:  selectType = "DRAW_WELL";
                    context.execute("PF('addDrawwWellDialog').show();");
                     break;
            default: selectType = "Не знаем такого";
                     break;
        }
    }    
    
    public void change(){
        val = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public byte[] getFileContents() {
        return fileContents;
    }

    public void setFileContents(byte[] fileContents) {
        this.fileContents = fileContents;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public int getM_Index() {
        return m_Index;
    }

    public void setM_Index(int m_Index) {
        this.m_Index = m_Index;
    }
    
    
 


}