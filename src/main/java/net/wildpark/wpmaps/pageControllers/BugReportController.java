/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.pageControllers;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import net.wildpark.wpmaps.entitys.BugsReport;
import net.wildpark.wpmaps.facades.BugsReportFacade;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Zekart
 */
@Named(value = "bugsReportController")
@SessionScoped
public class BugReportController implements Serializable{
    @EJB
    private BugsReportFacade bugsFacade;
    


    private String text;
    private String data;
    
    private List<BugsReport> list_bug = new ArrayList<>();

    public BugReportController() {
    }

    
    @PostConstruct
    public void init() {

        list_bug = bugsFacade.findAll();
        System.out.println(text);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        data = dateFormat.format(date);

    }   

    
    public void addBugsReport(){
        BugsReport bugs = new BugsReport() ;
        bugs.setText(text);
        bugs.setAdded(data);
        bugs.setStatus("new");
        bugsFacade.create(bugs);
        
        RequestContext.getCurrentInstance().closeDialog("bugReportPage");
        reset();

    } 
    
    
    public void getListBug(){

    }
    
    public void reset(){
        text = null;
    }

    public String getText() {
        return text;
    }
 
    public void setText(String text) {
        this.text = text;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<BugsReport> getList_bug() {
        return list_bug;
    }

    public void setList_bug(List<BugsReport> list_bug) {
        this.list_bug = list_bug;
    }




    
}
