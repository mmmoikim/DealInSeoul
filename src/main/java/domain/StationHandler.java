package domain;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.dis.dis.vo.StationVO;
@Component
public class StationHandler extends DefaultHandler{
	String position = "";
	StationVO svo;  
	int flag;//flag=0, 1=arsId, 2=stationNu, flag=-1

	public StationVO getParsedData() {
		return this.svo;
	}

	@Override
	public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException {
		System.out.println(namespaceURI);
		System.out.println(localName);
		System.out.println(qName);
		if(flag==-1) return;
		if (qName.equals("arsId")) {
			svo = new StationVO();
			flag=1;
		} 
		else if (qName.equals("stationNm")) {
			flag=2;
		}
	}
	@Override
	public void characters(char ch[], int start, int length) {
		switch(flag){
		case 1:
			svo.setArsId(new String(ch, start, length));
			flag=0;
			break;
		case 2:
			svo.setStationNm(new String(ch, start, length));
			flag=-1;
			break;
			default:
				break;
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("svo"+ svo);
	}
	
}
