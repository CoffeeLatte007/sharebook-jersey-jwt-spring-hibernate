package com.lclizhao.sharebook.vojo;/**
 * Created by lizhaoz on 2015/12/4.
 */

import com.lclizhao.sharebook.daomain.User_Book;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 */
@XmlRootElement
public class Collections implements Serializable {
    @XmlElement(name = "start")
    private int start;
    @XmlElement(name = "count")
    private int count;
    @XmlElement(name="total")
    private int total;
    @XmlElement(name = "collections")
    @XmlElementWrapper()
    private List<Collection> collections;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }
}
