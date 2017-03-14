
package com.rzice.lynley.police.nohttp;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

/**
 * Created in Oct 23, 2015 1:19:04 PM
 *
 * @author YOLANDA
 */
public class WaitDialog extends ProgressDialog {

    public WaitDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setProgressStyle(STYLE_SPINNER);
        setMessage("正在请求，请稍候…");

    }

}
