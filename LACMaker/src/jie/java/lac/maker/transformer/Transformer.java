package jie.java.lac.maker.transformer;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Transformer {

	public static void main(String[] args) {
		Transformer trans = new Transformer();
		trans.analyse(".\\doc\\transformer_template.xml");
	}
	
	public boolean analyse(final String file) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			Document doc = factory.newDocumentBuilder().parse(file);
//			processDoc(doc);
//			
			NodeList node = doc.getElementsByTagName("dictionary");
			for (int i = 0; i < node.getLength(); ++ i) {
				Node n = node.item(i);
				System.out.println(n.getNodeType() + " - " +  n.getNodeName());
				NodeList c = n.getChildNodes();
				for (int j = 0; j < c.getLength(); ++ j) {
					Node m = c.item(j);
					System.out.println(m.getNodeType() + " - " +  m.getNodeName());
				}
			}
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	
	public static void processNode(Node node,int deep)//分析一个节点
    {
       deep++;
       String space = "";
       for(int i =  0 ; i < deep ; i++)//空白符用于控制缩进
       {
           space += "   ";
       }
       if(node instanceof Element)//如果当前节点是Element
       {
           System.out.println(space + node.getNodeName());//输出节点名字
 
           NamedNodeMap map = node.getAttributes();//先获取Element的属性
           for(int i = 0 ;i < map.getLength(); i++)//输出Element的属性
           {
              System.out.println(space + "attr name "+ map.item(i).getNodeName());
              System.out.println(space + "attr value" + map.item(i).getNodeValue());
           }
          
           NodeList nodes = node.getChildNodes();//获取子节点
           for(int i = 0 ; i < nodes.getLength() ; i++)//递归输出子节点信息
           {
              processNode(nodes.item(i), deep);
           }
                    
       }else if(node instanceof Text)//如果节点是文本
       {
           //if(!node.getNodeValue().trim().equals(""))//如果文本不是空白符，就输出文本内容
              System.out.println(space+"text: " + node.getNodeValue().trim());
       }
      
    }
   
    public static void  processDoc(Document doc)
    {
       System.out.println("start process doc");
       System.out.println("doc uri " + doc.getDocumentURI());     
       System.out.println("version " +doc.getXmlVersion());
       System.out.println("encoding "+doc.getXmlEncoding());
       //NodeList nodes = doc.getChildNodes();
       NodeList nodes = doc.getElementsByTagName("dictionary");
       //System.out.println(doc.getNodeName());
 
       for(int i = 0 ; i < nodes.getLength() ;i++)
       {
           processNode(nodes.item(i),0);
       }
      
    }	
}
