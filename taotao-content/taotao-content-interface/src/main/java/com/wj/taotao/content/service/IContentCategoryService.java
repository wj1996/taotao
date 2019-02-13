package com.wj.taotao.content.service;

import com.wj.taotao.common.EasyUITreeNode;
import com.wj.taotao.common.TaotaoResult;

import java.util.List;

public interface IContentCategoryService {

    List<EasyUITreeNode> getContentCategoryList(long parentId);

    TaotaoResult addContentCategory(long parentId,String name);
}
