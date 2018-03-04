/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.EntityClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Arvid
 */
@Embeddable
public class PostsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "THREAD")
    private String thread;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POST_NR")
    private int postNr;

    public PostsPK() {
    }

    public PostsPK(String thread, int postNr) {
        this.thread = thread;
        this.postNr = postNr;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public int getPostNr() {
        return postNr;
    }

    public void setPostNr(int postNr) {
        this.postNr = postNr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (thread != null ? thread.hashCode() : 0);
        hash += (int) postNr;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostsPK)) {
            return false;
        }
        PostsPK other = (PostsPK) object;
        if ((this.thread == null && other.thread != null) || (this.thread != null && !this.thread.equals(other.thread))) {
            return false;
        }
        if (this.postNr != other.postNr) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Forum.EntityClasses.PostsPK[ thread=" + thread + ", postNr=" + postNr + " ]";
    }
    
}
