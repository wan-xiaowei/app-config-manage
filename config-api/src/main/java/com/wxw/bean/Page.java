package com.wxw.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Created by heweiming on 2017/5/17.
 */
public class Page<T extends Serializable>  implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 页码(当前页)
     */
    private int page;

    /**
     * 总页数
     */
    private int total;

    /**
     * 每页显示记录大小
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private long records;

    public Page() {
        
    }

    public Page(int page, int pageSize, long records, List<T> rows) {
        super();
        this.page = page;
        this.pageSize = pageSize;
        this.records = records;
        this.rows = rows;
    }

    /**
     * 当前页记录数据
     */
    private List<T> rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        try {
            total = Long.valueOf(records / pageSize + ((records % pageSize) == 0 ? 0 : 1)).intValue();
        } catch (Exception e) {
            throw new RuntimeException("records或pageSize参数不正确");
        }
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
