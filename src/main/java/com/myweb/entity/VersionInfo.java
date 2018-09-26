package com.myweb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Table(name="versioninfo")
public class VersionInfo implements Serializable {
    @Id
    private Integer vid;

    @Column(name = "versionType")
    private String versiontype;

    private String url;

    private String version;

    @Column(name = "releaseDate")
    private Date releasedate;

    @Column(name = "releaseName")
    private String releasename;

    @Column(name = "forceUpdate")
    private String forceupdate;

    @Column(name = "useVersion")
    private Integer useversion;

    private String temp;

    private static final long serialVersionUID = 1L;

    /**
     * @return vid
     */
    public Integer getVid() {
        return vid;
    }

    /**
     * @param vid
     */
    public void setVid(Integer vid) {
        this.vid = vid;
    }

    /**
     * @return versionType
     */
    public String getVersiontype() {
        return versiontype;
    }

    /**
     * @param versiontype
     */
    public void setVersiontype(String versiontype) {
        this.versiontype = versiontype == null ? null : versiontype.trim();
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * @return version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    /**
     * @return releaseDate
     */
    public Date getReleasedate() {
        return releasedate;
    }

    /**
     * @param releasedate
     */
    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    /**
     * @return releaseName
     */
    public String getReleasename() {
        return releasename;
    }

    /**
     * @param releasename
     */
    public void setReleasename(String releasename) {
        this.releasename = releasename == null ? null : releasename.trim();
    }

    /**
     * @return forceUpdate
     */
    public String getForceupdate() {
        return forceupdate;
    }

    /**
     * @param forceupdate
     */
    public void setForceupdate(String forceupdate) {
        this.forceupdate = forceupdate == null ? null : forceupdate.trim();
    }

    /**
     * @return useVersion
     */
    public Integer getUseversion() {
        return useversion;
    }

    /**
     * @param useversion
     */
    public void setUseversion(Integer useversion) {
        this.useversion = useversion;
    }

    /**
     * @return temp
     */
    public String getTemp() {
        return temp;
    }

    /**
     * @param temp
     */
    public void setTemp(String temp) {
        this.temp = temp == null ? null : temp.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", vid=").append(vid);
        sb.append(", versiontype=").append(versiontype);
        sb.append(", url=").append(url);
        sb.append(", version=").append(version);
        sb.append(", releasedate=").append(releasedate);
        sb.append(", releasename=").append(releasename);
        sb.append(", forceupdate=").append(forceupdate);
        sb.append(", useversion=").append(useversion);
        sb.append(", temp=").append(temp);
        sb.append("]");
        return sb.toString();
    }
}