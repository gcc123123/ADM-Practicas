package adm.practica2;

import android.R.bool;
import android.R.integer;

public class Question {
	//*//Secuencia de datos segun el xml
	String answer1, answer2, answer3, answer4; //text = Question description
	String audience, fifty1, fifty2, number, phone, right;
	public Question(String answer1, String answer2, String answer3,
			String answer4, String audience, String fifty1, String fifty2,
			String number, String phone, String right, String text) {
		super();
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.audience = audience;
		this.fifty1 = fifty1;
		this.fifty2 = fifty2;
		this.number = number;
		this.phone = phone;
		this.right = right;
		this.text = text;
	}
	String text;
	//*/
	/*//Secuencia de datos segun del generator
	String number, text, answer1, answer2, answer3, answer4,
		   right, audience, phone, fifty1, fifty2;
	//*/
	public Question() {
		super();
		this.number = null;
		this.text = null;
		this.answer1 = null;
		this.answer2 = null;
		this.answer3 = null;
		this.answer4 = null;
		this.right = null;
		this.audience = null;
		this.phone = null;
		this.fifty1 = null;
		this.fifty2 = null;
	}
	public boolean isEmpty(){ //if any value is empty return isEmpty = true
		if(number == null || 
			text == null || 
			answer1 == null || 
			answer2 == null || 
			answer3 == null || 
			answer4 == null || 
			right == null || 
			audience == null || 
			phone == null || 
			fifty1 == null || 
			fifty2 == null) 
				return true;
		else 
				return false;
	}
	//para generador question estatic
	public Question(String number, String text, String answer1, String answer2,
			String answer3, String answer4, String right, String audience,
			String phone, String fifty1, String fifty2, bool tipo) {
		super();
		this.number = number;
		this.text = text;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.right = right;
		this.audience = audience;
		this.phone = phone;
		this.fifty1 = fifty1;
		this.fifty2 = fifty2;
	}
	public String getNumber() {
		return number;
	}
	public Integer getNumberAsInteger() {
		return Integer.parseInt(number);
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	public String getRight() {
		return right;
	}
	public Integer getRightAsInteger(){
		return Integer.parseInt(right);
	}
	public void setRight(String right) {
		this.right = right;
	}
	public String getAudience() {
		return audience;
	}
	public Integer getAudienceAsInteger() {
		return Integer.parseInt(audience);
	}
	public void setAudience(String audience) {
		this.audience = audience;
	}
	public String getPhone() {
		return phone;
	}
	public Integer getPhoneAsInteger() {
		return Integer.parseInt(phone);
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFifty1() {
		return fifty1;
	}
	public Integer getFifty1AsInteger() {
		return Integer.parseInt(fifty1);
	}
	public void setFifty1(String fifty1) {
		this.fifty1 = fifty1;
	}
	public String getFifty2() {
		return fifty2;
	}
	public Integer getFifty2AsInteger() {
		return Integer.parseInt(fifty2);
	}
	public void setFifty2(String fifty2) {
		this.fifty2 = fifty2;
	}
	
	
	
}
