package kaleb.com.br.mymovies

import android.app.ProgressDialog
import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.content.DialogInterface
import android.support.v7.app.AlertDialog


/**
 * Created by kaleb on 26/07/2017.
 */
 abstract class CustomAppCompathAcitivityKot : AppCompatActivity() {
    private var dialog: ProgressDialog? = null
    protected fun openSnackBar(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    protected fun dialogMessage(msg: String, positiveClick: DialogInterface.OnClickListener?,
                                negativeClick: DialogInterface.OnClickListener?, ctx: Context) {
        val builderDialog = AlertDialog.Builder(ctx)
        builderDialog.setCancelable(positiveClick != null || negativeClick != null)
        builderDialog.setMessage(msg)
        if (positiveClick != null) {
            builderDialog.setPositiveButton("sim", positiveClick)
        }
        if (negativeClick != null) {
            builderDialog.setNegativeButton("não", negativeClick)
        }
        builderDialog.show()

    }


    protected fun startDialogLoding(ctx: Context) {
        if (dialog == null) {
            dialog = ProgressDialog(ctx)
            (dialog as ProgressDialog).setProgressStyle(ProgressDialog.STYLE_SPINNER)
            (dialog as ProgressDialog).setMessage("Carregando. Porfavor aguarde...")
            (dialog as ProgressDialog).setIndeterminate(true)
            (dialog as ProgressDialog).setCanceledOnTouchOutside(false)

        }
        (dialog as ProgressDialog).show()
    }
}