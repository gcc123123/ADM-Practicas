package adm.practica2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ScoresActivity extends Activity {

	private DialogDeleteScores myDialog;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        myDialog = new DialogDeleteScores(this);
    }
    
    public void closeScoresActivity(View view) {
    	finish();
    }
    
    public void deleteScores() {
    	//
    }
    
    public void tryToDeleteScores(View view) {
    	myDialog.openDialog();
    }
}
