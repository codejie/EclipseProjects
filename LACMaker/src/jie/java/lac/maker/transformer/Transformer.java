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
	
	
	public static void processNode(Node node,int deep)//����һ���ڵ�
    {
       deep++;
       String space = "";
       for(int i =  0 ; i < deep ; i++)//�հ׷����ڿ�������
       {
           space += "   ";
       }
       if(node instanceof Element)//�����ǰ�ڵ���Element
       {
           System.out.println(space + node.getNodeName());//����ڵ�����
 
           NamedNodeMap map = node.getAttributes();//�Ȼ�ȡElement������
           for(int i = 0 ;i < map.getLength(); i++)//���Element������
           {
              System.out.println(space + "attr name "+ map.item(i).getNodeName());
              System.out.println(space + "attr value" + map.item(i).getNodeValue());
           }
          
           NodeList nodes = node.getChildNodes();//��ȡ�ӽڵ�
           for(int i = 0 ; i < nodes.getLength() ; i++)//�ݹ�����ӽڵ���Ϣ
           {
              processNode(nodes.item(i), deep);
           }
                    
       }else if(node instanceof Text)//����ڵ����ı�
       {
           //if(!node.getNodeValue().trim().equals(""))//����ı����ǿհ׷���������ı�����
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
