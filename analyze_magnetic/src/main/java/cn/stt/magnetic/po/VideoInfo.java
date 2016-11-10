package cn.stt.magnetic.po;

import java.io.Serializable;

public class VideoInfo implements Serializable {
    private static final long serialVersionUID = 6495627062268068586L;
    private Integer id;

    private String torrentHash;

    private Integer torrentIndex;

    private String hash;

    private String url;

    private Long createTime;

    private String name;

    private String size;

    private String cookie;

    private Integer valid;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTorrentHash() {
        return torrentHash;
    }

    public void setTorrentHash(String torrentHash) {
        this.torrentHash = torrentHash == null ? null : torrentHash.trim();
    }

    public Integer getTorrentIndex() {
        return torrentIndex;
    }

    public void setTorrentIndex(Integer torrentIndex) {
        this.torrentIndex = torrentIndex;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "id=" + id +
                ", torrentHash='" + torrentHash + '\'' +
                ", torrentIndex=" + torrentIndex +
                ", hash='" + hash + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", cookie='" + cookie + '\'' +
                ", valid='" + valid + '\'' +
                '}';
    }
}