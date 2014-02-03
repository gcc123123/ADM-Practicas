package adm.practica2;

import android.R.integer;
import android.widget.Switch;

public class Language {
	public static final int es = 0034;
	public static final int en = 0044;
	public static final int fr = 0045;
	public static final int novuale = 0044; //equal default language
	
	public static int getEs() {
		return es;
	}

	public static int getEn() {
		return en;
	}

	public static int getFr() {
		return fr;
	}

	public static final void Language() {}
	
	
	public static enum Languages
	{
	ES, EN, FR, DEFAULT,NOVALUE;
		public static final Languages toLanguages(String str)
		{
			try {
				return valueOf(str);
			}catch (Exception ex) {
				return NOVALUE;
			}
		}
	}
	
	
	public static final int Language(String flag) {
		// TODO Auto-generated constructor stub
		switch(Languages.toLanguages(flag)){
		case ES:
			return es;
		case EN:
			return en;
		case FR:
			return fr;
		case NOVALUE:
		case DEFAULT:
		default:
			return novuale;
		}
	}
	
	public static final String Language(int flag) {
		// TODO Auto-generated constructor stub
		switch(flag){
		case es:
			return "ES";
		case en:
			return "EN";
		case fr:
			return "FR";
		default:
			return "EN";
		}
	}
	
	public static final String getPlayQuestionsXmlPullParser(int flag) {
		// TODO Auto-generated constructor stub
		switch(flag){
		case es:
			return "ES";
		case en:
			return "EN";
		case fr:
			return "FR";
		default:
			return "EN";
		}
	}
	
}
