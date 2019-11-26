package com.awb.entity.vo;

import java.io.Serializable;
import java.util.List;

/**
 * * description:
 * * auth.:yu.li
 * * created:2018/1/22 0022
 **/
public class PageResult<T> implements Serializable {
    //每页多少行
    private long pageSize = 10;
    //总行数
    private long total = 0;
    protected Integer currentPage=1;
    //当前页记录
    private List<T> list;
    /**
     * 生成分页
     * @param count 总记录数
     * @param list 当前页记录
     */
    public PageResult(long count, List<T> list, Integer currentPage)
    {
        this.total=count;
        this.list=list;
        this.currentPage=currentPage;
    }

    /**
     * 生成分页
     * @param count 总记录数
     * @param list 当前页记录
     * @param pageSize 每页大小
     */
    public PageResult(long count, List<T> list, long pageSize)
    {
        this.total=count;
        this.pageSize=pageSize;
        this.list=list;
    }



    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
