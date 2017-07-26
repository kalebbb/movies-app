package kaleb.com.br.mymovies.Util;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by kaleb on 26/07/2017.
 */

public abstract class CustomAppCompatActivity extends AppCompatActivity {
    /*
    * Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
    *                   .setAction("Action", null).show();
    * */

    private ProgressDialog dialog;


    protected void startDialogLoding(Context ctx){
        if(dialog==null){
            dialog = new ProgressDialog(ctx);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Carregando. Porfavor aguarde...");
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);

        }
        dialog.show();
    }
    protected void openSnackBar(View view,String text){
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                                   .setAction("Action", null).show();
    }

}
