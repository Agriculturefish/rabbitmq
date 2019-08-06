package com.yuhao.business.conditional.imple;

import com.yuhao.business.conditional.inter.ListService;

public class WindowsListService implements ListService {

    @Override
    public String showListCmd() {
        return "dir";
    }
}

