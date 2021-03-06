/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitysController;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import net.wildpark.wpmaps.entitys.Log;
import net.wildpark.wpmaps.entitys.User;
import net.wildpark.wpmaps.enums.UserRole;
import net.wildpark.wpmaps.facades.LogFacade;
import net.wildpark.wpmaps.facades.UserFacade;

/**
 *
 * @author Panker-RDP
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {
    
    @EJB
    private UserFacade userFacade;
    @EJB
    private LogFacade logFacade;
    private User current=new User();
    private User newUser = new User();
    private List<User> users=new ArrayList<>();
    private boolean entered=false;
    private boolean admin=false;
    
    private String new_password = "";
    private String new_login = "";
    private String new_info = "";
    
    
    public UserController() {
    }
    
    public String login(){
        if(userFacade.findAll().isEmpty()){
            User admin=new User();
            admin.setLogin("panker");
            admin.setEmail("informer@mksat.net");
            admin.setAbout("I`m first user. I`m admin");
            admin.setPassword("156456851");
            admin.getMessages().add("Created automaticly by system");
            admin.setActive(true);
            userFacade.create(admin);
            users.add(admin);
            
            
            User admin2 = new User();
            admin2.setLogin("zekart");
            admin2.setEmail("zekart22@gmail.com");
            admin2.setAbout("I`m first user. I`m admin");
            admin2.setPassword("12344321");
            admin2.getMessages().add("Created automaticly by system");
            admin2.setActive(true);
            userFacade.create(admin2);
            users.add(admin2);           
            
        }else{
            users=userFacade.findAll();
        }
        for (User user : users) {
            if(user.getLogin().equals(current.getLogin())){
                if(user.getPassword().equals(current.getPassword())){
                    current=user;
                    entered=true;
                    admin=user.getUserRole().equals(UserRole.ADMIN);
                    logFacade.create(new Log(current.getLogin()+" авторизовался в системе. IP:"+getIpRequest()));
                    return"basicPage.xhtml?faces-redirect=true";
                }else{
                    addMessage("Не верный пароль. Повторите попытку");
                    return"";
                }
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", current.getLogin());
            
        }
        addMessage("Совпадений не найдено");
        return "";
    }
    
    public String registration(){

            User new_user=new User();
            new_user.setLogin(newUser.getLogin());
            new_user.setEmail("informer@mksat.net");
            new_user.setAbout(newUser.getAbout());
            new_user.setPassword(newUser.getPassword());
            new_user.setActive(true);
            new_user.setUserRole(UserRole.USER);
            new_user.setEmail("Created automaticly by system");
            userFacade.create(newUser);
            users.add(new_user);    
            return "index_1.xhtml?faces-redirect=true";

    }
    
    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getNew_login() {
        return new_login;
    }

    public void setNew_login(String new_login) {
        this.new_login = new_login;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getNew_info() {
        return new_info;
    }

    public void setNew_info(String new_info) {
        this.new_info = new_info;
    }
    
    
    
    private String getIpRequest(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        
        return ipAddress;
    }
    
    private void addMessage(String msg){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
    }
}
