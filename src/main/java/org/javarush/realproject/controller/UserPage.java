package org.javarush.realproject.controller;

import org.javarush.realproject.model.User;

import java.util.List;

/**
 * Created by APopov on 16.11.2015.
 */

public class UserPage {
    private List<User> pageList;
    private int pagesCount;
    //для варианта на Angular:
    private boolean needUpdate = false;

    public List<User> getPageList() {
        return pageList;
    }

    public void setPageList(List<User> pageList) {
        this.pageList = pageList;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public boolean isNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(boolean needUpdate) {
        this.needUpdate = needUpdate;
    }
}
