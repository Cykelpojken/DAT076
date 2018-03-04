/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import javax.ejb.Stateless;
import javax.inject.Named;
import Forum.EntityClasses.Posts;
import Forum.EntityClasses.PostsPK;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author Arvid
 */
@Stateless
@Named
public class PostBean {

    private static final Logger LOG = Logger.getLogger(PostBean.class.getName());
    @PersistenceContext
    EntityManager em;
    
    @Inject Posts posts;

    public List getList(){
        return em.createNamedQuery("Posts.findAll").getResultList();
    }
    
    public List getThreadList(String thread) {
        List<Posts> l = getList();
        
        List<Posts> threadPosts = new ArrayList<>();
        
        for(Posts p : l)
        {
            if(thread.equals(p.getPostsPK().getThread()))
            {
                threadPosts.add(p);
            }
        }
        return threadPosts;
    }
    
    public Posts getPost(String name, int post_nr){
        return (Posts)em.createNamedQuery("Posts.findByPK").setParameter("postsPK", new PostsPK(name, post_nr)).getSingleResult();
    }
    
    public void create(){
        String thread = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("threadId"));
        posts.setCreater(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("creator"));
        List<Posts> l = getList();
        
        System.out.println(posts.getCreater());
        
        List<Posts> threadPosts = new ArrayList<>();
        
        for(Posts p : l)
        {
            if(thread.equals(p.getPostsPK().getThread()))
            {
                threadPosts.add(p);
            }
        }
        int post_nr = threadPosts.size() + 1;
        Posts dummy = new Posts(thread, post_nr, posts.getText(), posts.getCreater());
        for(Posts p : threadPosts){
            if(dummy.equals(p))
                return;
        }
        System.out.println(dummy.toString() + dummy.getCreater() + dummy.getText());
        try{
            em.persist(dummy);
        }
        catch(EntityExistsException e){
        e.printStackTrace();}
        
    }
}
