package com.tliknowledge.nytimes.ui.dialoges;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.tliknowledge.nytimes.R;
import com.tliknowledge.nytimes.listeners.CustomSingleChoiceDialogListener;
import com.tliknowledge.nytimes.modules.logger.Logger;

import java.util.ArrayList;

/**
 * This Util class is used for displaying dialogs
 * Created by shahid.
 */
public class DialogUtil {
    private Activity mActivity;
    private Logger logger = new Logger(DialogUtil.class);

    protected DialogUtil() {
    }

    public DialogUtil(Activity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * This listener is for dismissing the dialog for Ok button
     *
     * @return - DialogInterface.OnClickListener which dismissed the dialog in onClick
     */
    public static DialogInterface.OnClickListener getOkDialogOnClickListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
    }

    public void selectPeriodValue(String selectedDaysValue,
                                       final CustomSingleChoiceDialogListener singleChoiceDialogListener) {
        if (mActivity != null) {
            int selectedItemPosition = -1;
            final ArrayList<String> periodValueList = new ArrayList<>();
            String[] periodValueArray = mActivity.getResources().getStringArray(R.array.period_values);
            for (int i = 0; i < periodValueArray.length; i++) {
                periodValueList.add(periodValueArray[i]);
                if (selectedDaysValue.equalsIgnoreCase(periodValueArray[i])) {
                    selectedItemPosition = i;
                }
            }
            DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    singleChoiceDialogListener.onItemSelected(periodValueList.get(which));
                    dialog.dismiss();
                }
            };
            AlertDialog alertDialog = DialogFactory.createSingleChoiceAlertDialog(mActivity,
                    periodValueList, selectedItemPosition, mActivity.getString(R.string.select_days), dialogListener);
            alertDialog.setCancelable(true);
            alertDialog.show();
        }
    }

}
