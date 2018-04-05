package com.qbao.store.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.qbao.store.entity.BUEntity;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月28日 下午5:30:47 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月28日 下午5:30:47 	修改人：nz
 *  	描述	:
 ***********************************************************
 */
public class BUParser
{
	public static List<BUEntity> getNodes(InputSource inputSource) throws Exception
	{
		List<BUEntity> list = new ArrayList<BUEntity>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(inputSource);
		Element element = document.getDocumentElement();
		NodeList menuNode = element.getElementsByTagName("buId");
		int nodeNumber = menuNode.getLength();
		
		for (int i = 0; i < nodeNumber; i++)
		{
			BUEntity buEntity = new BUEntity();
			
			Element nodeElement = (Element) menuNode.item(i);
			
			NodeList childNodes = nodeElement.getChildNodes();
		
			for (int j = 0; j < childNodes.getLength(); j++)
			{
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE && "name".equals(childNodes.item(j).getNodeName()))
				{
					 buEntity.setBuName(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE && "id".equals(childNodes.item(j).getNodeName()))
				{
					buEntity.setBuId(childNodes.item(j).getTextContent());
				}
			}
			list.add(buEntity);
		}
		return list;
	}
	
}