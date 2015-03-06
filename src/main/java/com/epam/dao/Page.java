package com.epam.dao;

import java.util.List;

public class Page<T> {
    private List<T> items;
    private Boolean last;
    private Boolean first;
    private int pageNumber;
    private int perPageCount;

    public Page(){}

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getCountPerPage() {
        return perPageCount;
    }

    public void setCountPerPage(int perPageCount) {
        this.perPageCount = perPageCount;
    }

    private Page(Builder builder) {
        this.items = builder.items;
        this.last = builder.last;
        this.first = builder.first;
        this.pageNumber = builder.pageNumber;
        this.perPageCount = builder.perPageCount;
    }

    public static class Builder<T> {
        private List<T> items;
        private boolean last;
        private boolean first;
        private int pageNumber;
        private int perPageCount;


        public Builder items(List<T> items){
            this.items = items;
            return this;
        }

        public Builder last(boolean last){
            this.last = last;
            return this;
        }

        public Builder first(boolean first){
            this.first = first;
            return this;
        }

        public Builder pageNumber(int pageNumber){
            this.pageNumber = pageNumber;
            return this;
        }

        public Builder perPage(int perPageCount){
            this.perPageCount = perPageCount;
            return this;
        }

        public Page<T> build(){
            return new Page<T>(this);
        }
    }

    @Override
    public String toString() {
        return "Page{" +
                "items=" + items +
                ", last=" + last +
                ", first=" + first +
                ", pageNumber=" + pageNumber +
                ", perPageCount=" + perPageCount +
                '}';
    }
}
