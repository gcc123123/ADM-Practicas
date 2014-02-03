package adm.practica2;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.res.Resources;
public class QuestionManager {
	List<Question> question;
	String language;
	static final Language language_map = new Language();
	
	public QuestionManager(Activity activity){
		question = null;
		language = null;
		language = "DEFAULT";
		try {
			read_questions(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public QuestionManager(Activity activity, String language){ //flag == language
		this.language = language;
		try {
			question = read_questions_(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changeLanguage(Activity activity, String language){
		this.language = language;
		try {
			question = read_questions(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//get xmlPullParser for leer questions
	@SuppressWarnings("static-access")
	public XmlPullParser getXmlPullParser(Activity activity) throws XmlPullParserException{
		Resources res = activity.getResources();
		XmlPullParser parser = null;
		int lang=language_map.Language(language);
		if(language_map.Language("ES") == lang){
			//********    para xml en directorio res/xml/   *******//
			//parser = res.getXml(R.xml.questions0001_es);
			
			//********    para xml en directorio res/raw/   *******//
			InputStreamReader isr = new InputStreamReader( res.openRawResource(R.raw.questions0001_es));
			parser = XmlPullParserFactory.newInstance().newPullParser();
			parser.setInput(isr);
		}else{ //default language = EN
			//********    para xml en directorio res/xml/   *******//
			//parser = res.getXml(R.xml.questions0001);
			
			//********    para xml en directorio res/raw/   *******//
			InputStreamReader isr = new InputStreamReader( res.openRawResource(R.raw.questions0001));
			parser = XmlPullParserFactory.newInstance().newPullParser();
			parser.setInput(isr);
		}
		return parser;
	}
	
	public List<Question> read_questions_(Activity activity) throws Exception {
		List<Question> list = new ArrayList<Question>();
		XmlPullParser parser = null;
		//Resources res = activity.getResources();
		//parser = res.getXml(R.xml.questions0001);
		parser = getXmlPullParser(activity);
		Question question = null;
		Integer event;
		event = parser.getEventType();
		String name = null;
		while (event != XmlPullParser.END_DOCUMENT) {
			if (event == XmlPullParser.START_TAG) {
				name = parser.getName();
				if (name.equals("question")) {
					question = new Question(parser.getAttributeValue(null,
							"answer1"), parser.getAttributeValue(null,
							"answer2"), parser.getAttributeValue(null,
							"answer3"), parser.getAttributeValue(null,
							"answer4"), parser.getAttributeValue(null,
							"audience"), parser.getAttributeValue(null,
							"fifty1"),
							parser.getAttributeValue(null, "fifty2"),
							parser.getAttributeValue(null, "number"),
							parser.getAttributeValue(null, "phone"),
							parser.getAttributeValue(null, "right"),
							parser.getAttributeValue(null, "text"));
					if (!question.isEmpty()) {
						list.add(question);
					}
					question = null;
				}
			}
			event = parser.next();
		}
		return list;
	}

	public List<Question> read_questions(Activity activity) throws Exception {
		List<Question> list = new ArrayList<Question>();
		XmlPullParser parser = null;
		//Resources res = activity.getResources();
		//parser = res.getXml(R.xml.questions0001);
		parser = getXmlPullParser(activity);
		Question question = null;
		Integer event;
		event = XmlPullParser.START_DOCUMENT;
		String name = null;
		event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			name = parser.getName();
			switch (event) {
			case XmlPullParser.START_TAG:
				question = new Question();
				name = parser.getName();
				if ("question".equals(name)) {
					question = new Question();
					question.setAnswer1(parser.getAttributeValue(null,"answer1"));
					question.setAnswer2(parser.getAttributeValue(null,"answer2"));
					question.setAnswer3(parser.getAttributeValue(null,"answer3"));
					question.setAnswer4(parser.getAttributeValue(null,"answer4"));
					question.setAudience(parser.getAttributeValue(null,"audience"));
					question.setFifty1(parser.getAttributeValue(null, "fifty1"));
					question.setFifty2(parser.getAttributeValue(null, "fifty2"));
					question.setNumber(parser.getAttributeValue(null, "number"));
					question.setPhone(parser.getAttributeValue(null, "phone"));
					question.setRight(parser.getAttributeValue(null, "right"));
					question.setText(parser.getAttributeValue(null, "text"));
				}
				break;
			case XmlPullParser.END_TAG:
				name = parser.getName();
				if (name.equals("question") && question != null) {
					list.add(question);
				}
				question = null;
				break;
			}
			event = parser.next();
		}
		return list;
	}
    
    
    
    
    
    
    
    
    
    /*
    public  List<Question> leer_xml_example(Activity activity) throws Exception {
        List<Question> list = new ArrayList<Question>();
	    XmlPullParser parser = null;
		Resources res = activity.getResources();
	    parser = res.getXml(R.xml.questions0001);
		Question question = null;
	    Integer event;
	    event = XmlPullParser.START_DOCUMENT;

		String name = null;
		event = parser.getEventType();
	    //event = parser.nextTag();
	    while(event != XmlPullParser.END_DOCUMENT){
	    		
	    		name = parser.getName();
		    		//if("answer1".equals(parser.getAttributeNamespace(0))){
		    		switch(event){	
		    		case XmlPullParser.START_DOCUMENT:
		    			list = new ArrayList<Question>();
		    			break;
	    			case XmlPullParser.START_TAG:
		    			name = parser.getName();
		    			if ("question".equals(name)) {
							question = new Question();
						} else if ("answer1".equals(name)) {
							question.setAnswer1( parser.nextText() );
						} else if ("answer2".equals(name)) {
							question.setAnswer2( parser.nextText() );
						} else if ("answer3".equals(name)) {
							question.setAnswer3( parser.nextText() );
						} else if ("answer4".equals(name)) {
							question.setAnswer4( parser.nextText() );
						} else if ("audience".equals(name)) {
							question.setAudience( parser.nextText() );
						} else if ("fifty1".equals(name)) {
							question.setFifty1( parser.nextText() );
						} else if ("fifty2".equals(name)) {
							question.setFifty2( parser.nextText() );
						} else if ("number".equals(name)) {
							question.setNumber( parser.nextText() );
						} else if ("phone".equals(name)) {
							question.setPhone( parser.nextText() );
						} else if ("right".equals(name)) {
							question.setRight( parser.nextText() );
						} else if ("text".equals(name)) {
							question.setText( parser.nextText() );
						}
		    				break;
	    			case XmlPullParser.END_TAG:
		    			name = parser.getName();
		    			
		    			if(name.equals("question") && question != null){
		    				list.add(question);
		    			}
	    				question = null;
		    			break;
		    		}
			event = parser.next();
	    }
	    return list;
    }
    */
	
	
}
