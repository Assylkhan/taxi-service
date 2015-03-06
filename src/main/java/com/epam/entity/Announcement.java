package com.epam.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Announcement extends BaseEntity implements Serializable {
    private String titleEn;
    private String bodyEn;
    private String titleRu;
    private String bodyRu;
    private Timestamp postDate;

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getBodyEn() {
        return bodyEn;
    }

    public void setBodyEn(String bodyEn) {
        this.bodyEn = bodyEn;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getBodyRu() {
        return bodyRu;
    }

    public void setBodyRu(String bodyRu) {
        this.bodyRu = bodyRu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Announcement)) return false;
        if (!super.equals(o)) return false;

        Announcement that = (Announcement) o;

        if (bodyEn != null ? !bodyEn.equals(that.bodyEn) : that.bodyEn != null) return false;
        if (bodyRu != null ? !bodyRu.equals(that.bodyRu) : that.bodyRu != null) return false;
        if (postDate != null ? !postDate.equals(that.postDate) : that.postDate != null) return false;
        if (titleEn != null ? !titleEn.equals(that.titleEn) : that.titleEn != null) return false;
        if (titleRu != null ? !titleRu.equals(that.titleRu) : that.titleRu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (titleEn != null ? titleEn.hashCode() : 0);
        result = 31 * result + (bodyEn != null ? bodyEn.hashCode() : 0);
        result = 31 * result + (titleRu != null ? titleRu.hashCode() : 0);
        result = 31 * result + (bodyRu != null ? bodyRu.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        return result;
    }
}
