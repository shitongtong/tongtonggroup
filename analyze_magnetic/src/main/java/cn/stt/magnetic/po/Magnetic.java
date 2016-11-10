package cn.stt.magnetic.po;

import java.io.Serializable;

public class Magnetic implements Serializable{
    private static final long serialVersionUID = 6495627062268068586L;
    private String hash;

    private String sha1;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1 == null ? null : sha1.trim();
    }

    @Override
    public String toString() {
        return "Magnetic{" +
                "hash='" + hash + '\'' +
                ", sha1='" + sha1 + '\'' +
                ", status=" + status +
                '}';
    }
}