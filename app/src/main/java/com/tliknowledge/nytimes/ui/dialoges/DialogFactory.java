package com.tliknowledge.nytimes.ui.dialoges;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.tliknowledge.nytimes.R;

import java.util.ArrayList;

/**
 * This DialogFactory class is used return objects of different Custom Dialogs
 * Created by shahid.
 */
public class DialogFactory {

    /**
     * This method returns AlertDialog with a neutral button
     *
     * @param context                    - Context of the current activity or fragment
     * @param titleText                  - Text to be displayed in the title
     * @param messageText                - Text to be displayed in the dialog
     * @param neutralButtonText          - Text to be displayed on Neutral button
     * @param neutralButtonClickListener - Listener to be fired when neutral button is clicked
     * @return - Alert Dialog instance
     */
    public static AlertDialog createAlertDialogWithNeutralButton(Context context, String titleText,
                                                                 String messageText, String neutralButtonText,
                                                                 DialogInterface.OnClickListener neutralButtonClickListener) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);

        // set dialog message
        alertDialogBuilder
                .setTitle(titleText)
                .setMessage(messageText)
                .setCancelable(false)
                .setPositiveButton(neutralButtonText, neutralButtonClickListener);

        return alertDialogBuilder.create();
    }


    /**
     * This method returns default AlertDialog with a Positive and Negative buttons
     *
     * @param context               - Context of the current activity or fragment
     * @param titleText             - Text to be displayed in the title
     * @param messageText           - Text to be displayed in the dialog
     * @param positiveText          - Text to be displayed on the positive button in alert
     * @param negativeText          - Text to be displayed on the negative button in alert
     * @param positiveClickListener - Listener to be fired when positive button is clicked
     * @param negativeClickListener - Listener to be fired when negative button is clicked
     * @return - Alert Dialog instance
     */
    public static AlertDialog createAlertDialogWithButtons(Context context, String titleText, String messageText,
                                                           String positiveText, String negativeText,
                                                           DialogInterface.OnClickListener positiveClickListener,
                                                           DialogInterface.OnClickListener negativeClickListener) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);

        // set dialog message
        alertDialogBuilder
                .setTitle(titleText)
                .setMessage(messageText)
                .setPositiveButton(positiveText, positiveClickListener)
                .setNegativeButton(negativeText, negativeClickListener)
                .setCancelable(false);

        return alertDialogBuilder.create();
    }

    /**
     * This method sets a customView to the AlertDialog with positive and negative buttons
     *
     * @param context                      - Context of the current activity or fragment
     * @param customView                   - View to be set in the Alert Dialog body
     * @param positiveButtonText           - Text to be displayed on the positive button
     * @param negativeButtonText           - Text to be displayed on the negative button
     * @param onPositiveButtonClickListner - Listener to be set to the positive button
     * @param onNegativeButtonClickListner - Listener to be set to the negative button
     * @return - Alert Dialog instance
     */
    public static AlertDialog createAlertDialogWithCustomView(Context context, String titleText, View customView,
                                                              String positiveButtonText, String negativeButtonText,
                                                              DialogInterface.OnClickListener onPositiveButtonClickListner,
                                                              DialogInterface.OnClickListener onNegativeButtonClickListner) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);

        // set dialog message
        alertDialogBuilder
                .setTitle(titleText)
                .setView(customView)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, onPositiveButtonClickListner)
                .setNegativeButton(negativeButtonText, onNegativeButtonClickListner);

        return alertDialogBuilder.create();
    }

    /**
     * This method sets a customView to the AlertDialog with positive and negative buttons
     *
     * @param context         - Context of the current activity or fragment
     * @param customView      - View to be set in the Alert Dialog body
     * @param buttonText      - Text to be displayed on the positive button
     * @param onButtonClicked - Listener to be set to the positive button
     * @return - Alert Dialog instance
     */
    public static AlertDialog createAlertDialogWithCustomViewSingleButton(Context context, String titleText, View customView,
                                                                          String buttonText,
                                                                          DialogInterface.OnClickListener onButtonClicked) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);

        // set dialog message
        alertDialogBuilder
                .setTitle(titleText)
                .setView(customView)
                .setCancelable(false)
                .setPositiveButton(buttonText, onButtonClicked);

        return alertDialogBuilder.create();
    }

    /**
     * This method sets a customView to the AlertDialog with positive and negative buttons
     *
     * @param context                      - Context of the current activity or fragment
     * @param customView                   - View to be set in the Alert Dialog body
     * @param positiveButtonText           - Text to be displayed on the positive button
     * @param negativeButtonText           - Text to be displayed on the negative button
     * @param onPositiveButtonClickListner - Listener to be set to the positive button
     * @param onNegativeButtonClickListner - Listener to be set to the negative button
     * @return - Alert Dialog instance
     */
    public static AlertDialog createAlertDialogWithCustomViewNoTitle(Context context, View customView,
                                                                     String positiveButtonText, String negativeButtonText,
                                                                     DialogInterface.OnClickListener onPositiveButtonClickListner,
                                                                     DialogInterface.OnClickListener onNegativeButtonClickListner) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);

        // set dialog message
        alertDialogBuilder
                .setView(customView)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, onPositiveButtonClickListner)
                .setNegativeButton(negativeButtonText, onNegativeButtonClickListner);

        return alertDialogBuilder.create();
    }

    /**
     * This method sets a customView to the AlertDialog with positive and negative buttons
     *
     * @param context    - Context of the current activity or fragment
     * @param customView - View to be set in the Alert Dialog body
     * @return - Alert Dialog instance
     */
    public static AlertDialog createDialogWithCustomViewNoTitleNoBtn(Context context, View customView) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);

        // set dialog message
        alertDialogBuilder
                .setView(customView)
                .setCancelable(true);

        return alertDialogBuilder.create();
    }

    public static AlertDialog createDialogWithCustomViewNoBtn(Context context, int stringId, View customView) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);

        // set dialog message
        alertDialogBuilder
                .setView(customView)
                .setCancelable(true)
                .setTitle(stringId);

        return alertDialogBuilder.create();
    }


    /**
     * This method sets a customView to the AlertDialog without positive and negative buttons
     *
     * @param context    - Context of the current activity or fragment
     * @param customView - View to be set in the Alert Dialog body
     * @return - Alert Dialog instance
     */
    public static AlertDialog createAlertDialogWithCustomViewNoButtons(Context context, String title, View customView) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);

        // set dialog message
        alertDialogBuilder
                .setTitle(title)
                .setView(customView)
                .setCancelable(false);

        return alertDialogBuilder.create();
    }


    /**
     * This method creates a Single choice alert dialog in material theme
     *
     * @param context        - Context of the current activity or fragment
     * @param itemList       - List containing the contents of the single choice dialog
     * @param selectedItem   - position of the last selected item
     * @param alertTitle     - Title to be displayed on the dialog
     * @param dialogListener - Listener that will fire when the single choice is selected in the dialog
     * @return - Alert Dialog instance
     */
    public static AlertDialog createSingleChoiceAlertDialog(Context context, ArrayList<String> itemList, int selectedItem,
                                                            String alertTitle,
                                                            DialogInterface.OnClickListener dialogListener) {

        String[] list = new String[itemList.size()];
        list = itemList.toArray(list);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);

        // set dialog message
        alertDialogBuilder
                .setTitle(alertTitle)
                .setSingleChoiceItems(list, selectedItem, dialogListener);

        return alertDialogBuilder.create();
    }
}
