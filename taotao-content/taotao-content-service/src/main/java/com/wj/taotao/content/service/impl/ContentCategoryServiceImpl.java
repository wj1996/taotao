package com.wj.taotao.content.service.impl;

import com.wj.taotao.common.EasyUITreeNode;
import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.content.service.IContentCategoryService;
import com.wj.taotao.mapper.TbContentCategoryMapper;
import com.wj.taotao.pojo.TbContentCategory;
import com.wj.taotao.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements IContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCategoryList(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for(TbContentCategory tbContentCategory : list){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public TaotaoResult addContentCategory(long parentId, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setIsParent(false);
        tbContentCategory.setName(name);
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());

        contentCategoryMapper.insert(tbContentCategory);
        //判断父节点的isParent是否为true，不是true需要改为true
        TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
        if(! parentNode.getIsParent()){
            parentNode.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentNode);
        }

        return TaotaoResult.ok(tbContentCategory);
    }
}
