package com.github.handioq.diberapp.base.pagination;

public interface PaginationListener {

    void onPaginationLoad(boolean state, int totalItemCount, int limit);

}

