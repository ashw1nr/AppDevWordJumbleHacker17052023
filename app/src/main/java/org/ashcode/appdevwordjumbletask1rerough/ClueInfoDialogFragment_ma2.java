package org.ashcode.appdevwordjumbletask1rerough;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class ClueInfoDialogFragment_ma2 extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.cluedialoglayout_ma2, null));
        builder.setMessage("dialog_start_game")
                .setPositiveButton("start", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity().getApplicationContext(), "Clicked positive", Toast.LENGTH_SHORT).show();

                    }
                } )
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        Toast.makeText(getActivity().getApplicationContext(), "Clicked negative", Toast.LENGTH_SHORT).show();

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}