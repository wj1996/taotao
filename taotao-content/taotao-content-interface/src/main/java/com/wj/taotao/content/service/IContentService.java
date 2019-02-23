package com.wj.taotao.content.service;

import com.wj.taotao.common.TaotaoResult;
import com.wj.taotao.pojo.TbContent;

import java.util.List;

public interface IContentService {

    List<TbContent> getTbContentList(Long cid);

    TaotaoResult saveContent(TbContent tbContent);

    TaotaoResult updateTbContent(TbContent tbContent);
}
