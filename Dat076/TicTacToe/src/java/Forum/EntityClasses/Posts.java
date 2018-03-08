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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arvid
 */
@Named
@RequestScoped
@Entity
@Table(name = "POSTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p")
    , @NamedQuery(name = "Posts.findByThread", query = "SELECT p FROM Posts p WHERE p.postsPK.thread = :thread")
    , @NamedQuery(name = "Posts.findByPostNr", query = "SELECT p FROM Posts p WHERE p.postsPK.postNr = :postNr")
    , @NamedQuery(name = "Posts.findByText", query = "SELECT p FROM Posts p WHERE p.text = :text")
    , @NamedQuery(name = "Posts.findByDate", query = "SELECT p FROM Posts p WHERE p.date = :date")
    , @NamedQuery(name = "Posts.findByCreater", query = "SELECT p FROM Posts p WHERE p.creater = :creater")})
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PostsPK postsPK;
    @Size(max = 250)
    @Column(name = "TEXT")
    private String text;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 45)
    @Column(name = "CREATER")
    private String creater;
    @Column(name = "MODIFIED")
    private boolean modified;

    public boolean isModified() {
        return modified;
    }

    public void setModified() {
        this.modified = true;
    }

    public Posts() {
    }

    public Posts(PostsPK postsPK) {
        this.postsPK = postsPK;
    }

    public Posts(String thread, int postNr, String text, String creater) {
        this.postsPK = new PostsPK(thread, postNr);
        this.text = text;
        this.date = new Date();
        this.creater = creater;
        this.modified = false;
    }

    public PostsPK getPostsPK() {
        return postsPK;
    }

    public void setPostsPK(PostsPK postsPK) {
        this.postsPK = postsPK;
    }

    public String getText() {
        return text;
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

    public void setDate() {
        this.date = new Date();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postsPK != null ? postsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.postsPK == null && other.postsPK != null) || (this.postsPK != null && !this.postsPK.equals(other.postsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Forum.EntityClasses.Posts[ postsPK=" + postsPK + " ]";
    }
    
}
