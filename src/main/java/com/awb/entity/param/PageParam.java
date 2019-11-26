package com.awb.entity.param;

public class PageParam {
    protected Integer pageSize=500;

    protected Integer currentPage=1;


    /**
     * 排序字段  默认为id
     */
    protected String sort= "id";            //排序字段
    /**
     * 排序方式 （asc/desc）
     */
    protected String order = "DESC";           //排序方式 （asc/desc）

    /**
     * 优先排序字段 默认为数据库sort字段
     */
    protected String firstSort = "sort";

    protected String firstOrder = "DESC";
;
    public PageParam(){

    }
    public PageParam(Integer currentPage, Integer pageSize){
        this.currentPage = currentPage ;
        this.pageSize = pageSize ;

    }


    /**
     * 起启记录
     * @return
     */
    public Integer getStartRecord(){
        return currentPage>1 ? pageSize*(currentPage-1):0;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }


    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getFirstSort() {
        return firstSort;
    }

    public void setFirstSort(String firstSort) {
        this.firstSort = firstSort;
    }

    public String getFirstOrder() {
        return firstOrder;
    }

    public void setFirstOrder(String firstOrder) {
        this.firstOrder = firstOrder;
    }
}
