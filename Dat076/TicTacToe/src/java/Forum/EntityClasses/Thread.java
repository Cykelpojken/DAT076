/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.EntityClasses;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arvid
 */
@Named
@RequestScoped
@Entity
@Table(name = "THREAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Thread.findAll", query = "SELECT t FROM Thread t")
    , @NamedQuery(name = "Thread.findByTitle", query = "SELECT t FROM Thread t WHERE t.title = :title")
    , @NamedQuery(name = "Thread.findByText", query = "SELECT t FROM Thread t WHERE t.text = :text")
    , @NamedQuery(name = "Thread.findByDate", query = "SELECT t FROM Thread t WHERE t.date = :date")
    , @NamedQuery(name = "Thread.findByPosts", query = "SELECT t FROM Thread t WHERE t.posts = :posts")})
public class Thread implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 250)
    @Column(name = "TEXT")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POSTS")
    private int posts;

    public Thread() {
    }

    public Thread(String title) {
        this.title = title;
    }

    public Thread(String title, String text) {
        this.title = title;
        this.text = text;
        this.date = new Date();
        this.posts = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }
    
    public String getThreadViewText() {
        String s = text.substring(0, Math.min(text.length(), 40));
        if(text.length() > 40)
        {
            s += "...";
        }
        return s;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }
    
    public String getSimpleDate() {
        SimpleDateFormat ft = new SimpleDateFormat("MM-dd-YYYY");
        return ft.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (title != null ? title.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Thread)) {
            return false;
        }
        Thread other = (Thread) object;
        if ((this.title == null && other.title != null) || (this.title != null && !this.title.equals(other.title))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Forum.Thread[ title=" + title + "text=" + text + " ]";
    }
    
}
