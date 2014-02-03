package adm.practica2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import adm.practica2.R.color;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.lang.Math;
@SuppressLint("ResourceAsColor")
public class PlayActivity extends Activity {
	//List<Question> question;
	Integer question_num;
	Integer score;
	QuestionManager qManage ;
	Integer current_question_number;
	Integer award[] = {0, 100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000, 32000,
			   64000, 125000, 250000, 500000, 1000000};
	Integer correct_answer_num;
	Integer no_minus_wrong_answer_num;
	Integer wrong_answer_num;
	
    @SuppressWarnings("static-access")
	
    public void reset(){
    		question_num = 1;
    		current_question_number = 0;
        score = 0;
        wrong_answer_num = 0;
        correct_answer_num = 0;
        no_minus_wrong_answer_num = 0;
        updateGame();
    }
    
    public Integer getCurrentReward(){
    		Integer reward_position = correct_answer_num + no_minus_wrong_answer_num - wrong_answer_num;
    		return award[ Math.min( Math.max(reward_position, 0), award.length - 1) ];
    		
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        //qManage.changeLanguage(this, "ES");
        qManage = new QuestionManager(this , "EN");
        reset();
        //qManage.question.isEmpty();
        
    }

    public void play_phone(View view){
    		suggest_answer(qManage.question.get(current_question_number).getPhoneAsInteger());
    }
    public void play_audience(View view){
		suggest_answer(qManage.question.get(current_question_number).getAudienceAsInteger());
    }
    @SuppressLint("ResourceAsColor")
	public void fifty(){
    	 	Button answer1 = (Button) findViewById(R.id.play_button_answer1);
        Button answer2 = (Button) findViewById(R.id.play_button_answer2);
        Button answer3 = (Button) findViewById(R.id.play_button_answer3);
        Button answer4 = (Button) findViewById(R.id.play_button_answer4);
        if(qManage.question.get(current_question_number).getFifty1AsInteger() == 1 ||
        		qManage.question.get(current_question_number).getFifty2AsInteger() == 1 ){
				//answer1.setEnabled(false);
				answer1.setTextColor(getResources().getColor(color.play_answer_button_disactive_text));
				answer1.setBackgroundColor(getResources().getColor(color.play_answer_button_disactive_background));
        			//answer1.setText( qManage.question.get(current_question_number).getAnswer1() );
        }
        if(qManage.question.get(current_question_number).getFifty1AsInteger() == 2 ||
        		qManage.question.get(current_question_number).getFifty2AsInteger() == 2 ){
        			//answer2.setEnabled(false);
    				answer2.setTextColor(getResources().getColor(color.play_answer_button_disactive_text));
    				answer2.setBackgroundColor(getResources().getColor(color.play_answer_button_disactive_background));
        			//answer2.setText( qManage.question.get(current_question_number).getAnswer2() );
    		}
        if(qManage.question.get(current_question_number).getFifty1AsInteger() == 3 ||
        		qManage.question.get(current_question_number).getFifty2AsInteger() == 3 ){
        			//answer3.setEnabled(false);
    				answer3.setTextColor(getResources().getColor(color.play_answer_button_disactive_text));
    				answer3.setBackgroundColor(getResources().getColor(color.play_answer_button_disactive_background));
        }
        if(qManage.question.get(current_question_number).getFifty1AsInteger() == 4 ||
        		qManage.question.get(current_question_number).getFifty2AsInteger() == 4 ){
				//answer4.setEnabled(false);
				answer4.setTextColor(getResources().getColor(color.play_answer_button_disactive_text));
				answer4.setBackgroundColor(getResources().getColor(color.play_answer_button_disactive_background));
        			//answer4.setText( qManage.question.get(current_question_number).getAnswer4() );
        }
    }
    public void suggest_answer(Integer suggestion){
	 	Button answer1 = (Button) findViewById(R.id.play_button_answer1);
	    Button answer2 = (Button) findViewById(R.id.play_button_answer2);
	    Button answer3 = (Button) findViewById(R.id.play_button_answer3);
	    Button answer4 = (Button) findViewById(R.id.play_button_answer4);
	    switch(suggestion){
	    case 1:
	    		answer1.setTextColor(getResources().getColor(color.play_answer_button_suggest_text));
			answer1.setBackgroundColor(getResources().getColor(color.play_answer_button_suggest_background));
    			break;
	    case 2:
	    		answer2.setTextColor(getResources().getColor(color.play_answer_button_suggest_text));
			answer2.setBackgroundColor(getResources().getColor(color.play_answer_button_suggest_background));
			break;
	    case 3:
	    		answer3.setTextColor(getResources().getColor(color.play_answer_button_suggest_text));
			answer3.setBackgroundColor(getResources().getColor(color.play_answer_button_suggest_background));
			break;
	    case 4:
	    		answer4.setTextColor(getResources().getColor(color.play_answer_button_suggest_text));
			answer4.setBackgroundColor(getResources().getColor(color.play_answer_button_suggest_background));
			break;
	    	default:
	    		break;
	    }
    }
    public void play_fifty(View view){
    		fifty();
    }
    public void play_end_game(View view){
    		finish();
    };
    public void answer(Integer answer){
    		//update answer state
    		if(current_question_number < (qManage.question.size() - 1)){
	    		if(answer == qManage.question.get(question_num).getRightAsInteger()){
	    			correct_answer_num += 1;
	    		}else{
	    			//if current answer number is 5 or 10, the incorrect answer doesn't minus the reward
	    			if(current_question_number == 5 || current_question_number == 10){
	    				no_minus_wrong_answer_num += 1;
	    			}
				wrong_answer_num += 1;
	    		}
	    		current_question_number = current_question_number + 1;
	    		updateGame();
    		}
    }
    public void updateGame(){
    		//Update reward and current question number 
    		TextView play_reward_view = (TextView) findViewById(R.id.play_reward);
    		play_reward_view.setText( String.valueOf(getCurrentReward())+"$" );
    		
    		TextView play_question_number_view = (TextView) findViewById(R.id.play_question_number);
    		play_question_number_view.setText( String.valueOf(current_question_number + 1) );
    		
/*
	        Button answer1 = (Button) findViewById(R.id.play_button_answer1);
	        Button answer2 = (Button) findViewById(R.id.play_button_answer2);
	        Button answer3 = (Button) findViewById(R.id.play_button_answer3);
	        Button answer4 = (Button) findViewById(R.id.play_button_answer4);
			//answer1.setTextColor(R.color.play_answer_button_text);
			//answer1.setBackgroundColor(R.color.play_answer_button_background);
			//answer2.setTextColor(R.color.play_answer_button_text);
			//answer2.setBackgroundColor(R.color.play_answer_button_background);
			//answer3.setTextColor(R.color.play_answer_button_text);
			answer3.setBackgroundColor(R.color.play_answer_button_background);
			//answer4.setTextColor(R.color.play_answer_button_text);
			answer4.setBackgroundColor(R.color.play_answer_button_background);
			*/
			
    		//Update question view
    		updateQuestion();
    }
    @SuppressLint("ResourceAsColor")
	public void updateQuestion(){
        if( !qManage.question.isEmpty() ){
			if(qManage.question.get(question_num).getRight() != null){
				//update question
		        TextView question_text_view = (TextView) findViewById(R.id.question_text);
		        question_text_view.setText( qManage.question.get(current_question_number).getText());
		        //Update button text and reset button state
		        Button answer1 = (Button) findViewById(R.id.play_button_answer1);
		        Button answer2 = (Button) findViewById(R.id.play_button_answer2);
		        Button answer3 = (Button) findViewById(R.id.play_button_answer3);
		        Button answer4 = (Button) findViewById(R.id.play_button_answer4);
		        answer1.setText( qManage.question.get(current_question_number).getAnswer1() );
		        answer2.setText( qManage.question.get(current_question_number).getAnswer2() );
		        answer3.setText( qManage.question.get(current_question_number).getAnswer3() );
		        answer4.setText( qManage.question.get(current_question_number).getAnswer4() );
		        //Color.WHITE;
		        //color.play_answer_button_disactive_background
		        
		        /*
		        // If you're in an activity:
		        Button11.setBackgroundColor(getResources().getColor(R.color.red));
		        // OR, if you're not: 
		        Button11.setBackgroundColor(Button11.getContext().getResources().getColor(R.color.red));
		        Or, alternatively:
		        */
		        answer1.setTextColor(getResources().getColor(color.play_answer_button_text));
		        answer1.setBackgroundColor(getResources().getColor(color.play_answer_button_background));
		        answer2.setTextColor(getResources().getColor(color.play_answer_button_text));
		        answer2.setBackgroundColor(getResources().getColor(color.play_answer_button_background));
		        answer3.setTextColor(getResources().getColor(color.play_answer_button_text));
		        answer3.setBackgroundColor(getResources().getColor(color.play_answer_button_background));
		        answer4.setTextColor(getResources().getColor(color.play_answer_button_text));
		        answer4.setBackgroundColor(getResources().getColor(color.play_answer_button_background));
				//answer1.setTextColor(R.color.play_answer_button_text);
				//answer1.setBackgroundColor(R.color.play_answer_button_background);
		        /*
		        Button answer1 = (Button) findViewById(R.id.play_button_answer1);
		        Button answer2 = (Button) findViewById(R.id.play_button_answer2);
		        Button answer3 = (Button) findViewById(R.id.play_button_answer3);
		        Button answer4 = (Button) findViewById(R.id.play_button_answer4);
				answer1.setTextColor(R.color.play_answer_button_text);
				answer1.setBackgroundColor(R.color.play_answer_button_background);
				answer2.setTextColor(R.color.play_answer_button_text);
				answer2.setBackgroundColor(R.color.play_answer_button_background);
				answer3.setTextColor(R.color.play_answer_button_text);
				answer3.setBackgroundColor(R.color.play_answer_button_background);
				answer4.setTextColor(R.color.play_answer_button_text);
				answer4.setBackgroundColor(R.color.play_answer_button_background);
				*/
			}
        }
    }
    public void play_answer1(View view){
    		answer(1);
    }
    public void play_answer2(View view){
    		answer(2);
    }
    public void play_answer3(View view){
    		answer(3);
    }
    public void play_answer4(View view){
    		answer(4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public List<Question> generateQuestionList() {

    		List<Question> list = new ArrayList<Question>();
    		Question q = null;
    		FileInputStream inputStream = null;

    		Resources res = this.getResources();
    		/*
			try {
				//inputStream = openFileInput("questions0001_es.xml");
				inputStream =openFileInput("questions0001_es.xml");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			*/
    	    XmlPullParser parser = null;
    	    parser = res.getXml(R.xml.questions0001_es);
			try {
				parser = XmlPullParserFactory.newInstance().newPullParser();
			} catch (XmlPullParserException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} try {
				parser.setInput(inputStream, null);
			} catch (XmlPullParserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
    	    int eventType = XmlPullParser.START_DOCUMENT;
	    	    while (eventType != XmlPullParser.END_DOCUMENT) { 
	    	    		if (eventType == XmlPullParser.START_TAG) {
	    				q = new Question(
		    					    parser.getAttributeValue(null, "answer1"),
		    					    parser.getAttributeValue(null, "answer2"),
		    					    parser.getAttributeValue(null, "answer3"),
		    					    parser.getAttributeValue(null, "answer4"),
    	    					    		parser.getAttributeValue(null, "audience"),
	    	    					    parser.getAttributeValue(null, "number"), 
	    	    					    parser.getAttributeValue(null, "text"),
	    	    					    parser.getAttributeValue(null, "right"),
	    	    					    parser.getAttributeValue(null, "phone"),
	    	    					    parser.getAttributeValue(null, "fifty1"),
	    	    					    parser.getAttributeValue(null, "fifty2")
	    	    					 	);
					list.add(q);
					try {
						eventType = parser.next();
					} catch (XmlPullParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	    		}
	    	    }
    	    try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
    		return list;
    }
    
    
    
    public List<Question> generateQuestionListStatic() {
		List<Question> list = new ArrayList<Question>();
		Question q = null;
		
		q = new Question(
				"1", 
				"Which is the Sunshine State of the US?",
				"North Carolina",
		        "Florida",
		        "Texas",
		        "Arizona",
				"2", 
				"2", 
				"2", 
				"1", 
				"4"
				);
		list.add(q);

		q = new Question(
				"2", 
				"Which of these is not a U.S. state?",
		        "New Hampshire",
		        "Washington",
		        "Wyoming",
		        "Manitoba",
				"4", 
				"4", 
				"4", 
				"2", 
				"3"
				);
		list.add(q);

		q = new Question(
				"3", 
				"What is Book 3 in the Pokemon book series?",
		        "Charizard",
		        "Island of the Giant Pokemon",
		        "Attack of the Prehistoric Pokemon",
		        "I Choose You!",
				"3", 
				"2", 
				"3", 
				"1", 
				"4"
				);
		list.add(q);

		q = new Question(
				"4", 
				"Who was forced to sign the Magna Carta?",
		        "King John",
		        "King Henry VIII",
		        "King Richard the Lion-Hearted",
		        "King George III",
				"1", 
				"3", 
				"1", 
				"2", 
				"3"
				);
		list.add(q);

		q = new Question(
				"5", 
				"Which ship was sunk in 1912 on its first voyage, although people said it would never sink?",
		        "Monitor",
		        "Royal Caribean",
		        "Queen Elizabeth",
		        "Titanic",
				"4", 
				"4", 
				"4", 
				"1", 
				"2"
				);
		list.add(q);

		q = new Question(
				"6", 
				"Who was the third James Bond actor in the MGM films? (Do not include &apos;Casino Royale&apos;.)",
		        "Roger Moore",
		        "Pierce Brosnan",
		        "Timothy Dalton",
		        "Sean Connery",
				"1", 
				"3", 
				"3", 
				"2", 
				"3"
				);
		list.add(q);

		q = new Question(
				"7", 
				"Which is the largest toothed whale?",
		        "Humpback Whale",
		        "Blue Whale",
		        "Killer Whale",
		        "Sperm Whale",
				"4", 
				"2", 
				"2", 
				"2", 
				"3"
				);
		list.add(q);

		q = new Question(
				"8", 
				"In what year was George Washington born?",
		        "1728",
		        "1732",
		        "1713",
		        "1776",
				"2", 
				"2", 
				"2", 
				"1", 
				"4"
				);
		list.add(q);

		q = new Question(
				"9", 
				"Which of these rooms is in the second floor of the White House?",
		        "Red Room",
		        "China Room",
		        "State Dining Room",
		        "East Room",
				"2", 
				"2", 
				"2", 
				"3", 
				"4"
				);
		list.add(q);

		q = new Question(
				"10", 
				"Which Pope began his reign in 963?",
		        "Innocent III",
		        "Leo VIII",
		        "Gregory VII",
		        "Gregory I",
				"2", 
				"1", 
				"2", 
				"3", 
				"4"
				);
		list.add(q);

		q = new Question(
				"11", 
				"What is the second longest river in South America?",
		        "Parana River",
		        "Xingu River",
		        "Amazon River",
		        "Rio Orinoco",
				"1", 
				"1", 
				"1", 
				"2", 
				"3"
				);
		list.add(q);

		q = new Question(
				"12", 
				"What Ford replaced the Model T?",
		        "Model U",
		        "Model A",
		        "Edsel",
		        "Mustang",
				"2", 
				"4", 
				"4", 
				"1", 
				"3"
				);
		list.add(q);

		q = new Question(
				"13", 
				"When was the first picture taken?",
		        "1860",
		        "1793",
		        "1912",
		        "1826",
				"4", 
				"4", 
				"4", 
				"1", 
				"3"
				);
		list.add(q);

		q = new Question(
				"14", 
				"Where were the first Winter Olympics held?",
		        "St. Moritz, Switzerland",
		        "Stockholm, Sweden",
		        "Oslo, Norway",
		        "Chamonix, France",
				"4", 
				"1", 
				"4", 
				"2", 
				"3"
				);
		list.add(q);

		q = new Question(
				"15", 
				"Which of these is not the name of a New York tunnel?",
		        "Brooklyn-Battery",
		        "Lincoln",
		        "Queens Midtown",
		        "Manhattan",
				"4", 
				"4", 
				"4", 
				"1", 
				"3"
				);
		list.add(q);
		
		return list;
	}
}
