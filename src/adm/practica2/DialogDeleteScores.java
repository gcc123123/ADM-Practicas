package adm.practica2;

import android.app.Dialog;
import android.view.View;

public class DialogDeleteScores {

	private static Dialog myDialog;
	private ScoresActivity activity;
	
    protected DialogDeleteScores(ScoresActivity activity_) {
    	activity = activity_;
    	myDialog = new Dialog(activity);
    	myDialog.setContentView(R.layout.dialog_delete_scores);
    	myDialog.setTitle(R.string.dialog_delete_scores);
    }
	
    public void goDeleteScores(View view) {
    	activity.deleteScores();
    	dismissDialog();
    }
    
    public void cancelDelete(View view) {
    	dismissDialog();
    }
    
    public void openDialog() {
    	myDialog.show();
    }
    
    public void dismissDialog() {
    	myDialog.dismiss();
    }
	
}
