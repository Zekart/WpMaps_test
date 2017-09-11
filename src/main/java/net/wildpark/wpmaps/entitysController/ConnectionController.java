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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import net.wildpark.wpmaps.entitys.ConnectPoint;
import net.wildpark.wpmaps.facades.ConnectFacade;
import net.wildpark.wpmaps.facades.PointFacade;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author zekart
 */
@Named(value = "connectController")
@SessionScoped
public class ConnectionController implements Serializable {

    @EJB
    private ConnectFacade connectFacade;
    @EJB
    private PointFacade mapFacade;
    
    
    private ConnectPoint cp = new ConnectPoint();
    

    private int fromPoint;

    private int toPoint;
    
    private TreeNode root;
    
    List<ConnectPoint> connect_point_list = new ArrayList<>();
    
//    @PostConstruct
//    public void init() {
//        root = new DefaultTreeNode("Точка", null);
//        cp = (ConnectPoint)connectFacade.findAll().;
//        TreeNode node0 = new DefaultTreeNode(cp.getFromPoint(), root);
//        TreeNode node1 = new DefaultTreeNode(cp.getToPoint(), root);
//        
//        TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
//        TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);
//    }
    
    

    
    public void deleteConnection(int id){
        
        cp = connectFacade.find(id);
        connectFacade.remove(cp);
        
    }
    
    
    
    public List<ConnectPoint> getConnectionWiev(){
        
        cp = (ConnectPoint)connectFacade.find(1);
        connect_point_list.add(cp);
//        if (cp.getFromPoint() == id || cp.getToPoint() == id) {
//            return this.connect_point_list;
//        }else{
//            return null;
//        }
        return this.connect_point_list;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    public List<ConnectPoint> getConnect_point_list() {
        return connect_point_list;
    }

    public void setConnect_point_list(List<ConnectPoint> connect_point_list) {
        this.connect_point_list = connect_point_list;
    }
    
    public ConnectFacade getConnectFacade() {
        return connectFacade;
    }

    public void setConnectFacade(ConnectFacade connectFacade) {
        this.connectFacade = connectFacade;
    }

    public ConnectPoint getCp() {
        return cp;
    }

    public void setCp(ConnectPoint cp) {
        this.cp = cp;
    }

    public int getFromPoint() {
        return fromPoint;
    }

    public void setFromPoint(int fromPoint) {
        this.fromPoint = fromPoint;
    }

    public int getToPoint() {
        return toPoint;
    }

    public void setToPoint(int toPoint) {
        this.toPoint = toPoint;
    }

    
       

    
}
