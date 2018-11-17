package com.dsman.uaapp.Professor.Professor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsman.uaapp.Comunities.CardView_Comunity_Data;
import com.dsman.uaapp.Comunities.RecyclerAdapter_Comunity_Data;
import com.dsman.uaapp.R;

import java.util.Objects;

public class Professor_FragmentDialog extends AppCompatDialogFragment {

    private ImageView professorImg;
    private TextView professorDescription;
    private TextView professorName;
    private TextView professorSurname;
    private TextView professorDescriptionTwo;
    private DrawerLayout drawer;
    private RecyclerView recycle;
    private RecyclerView.Adapter mAdapterProfessor;
    private RecyclerView.LayoutManager mLayoutManagerProfessor;
    private FloatingActionButton my_fab;

    public Dialog onCreateDialog(Bundle savedInstaceState){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_professor__fragment_dialog, null);
        CardView_Comunity_Data asignatura1 = new CardView_Comunity_Data(ResourcesCompat.getDrawable(getResources(), R.drawable.u_logo, null),"Test");
        CardView_Comunity_Data asignatura2 = new CardView_Comunity_Data(ResourcesCompat.getDrawable(getResources(), R.drawable.u_logo, null),"Test 2");
        CardView_Comunity_Data asignatura3 = new CardView_Comunity_Data(ResourcesCompat.getDrawable(getResources(), R.drawable.u_logo, null),"Test 3");
        CardView_Comunity_Data asignatura4 = new CardView_Comunity_Data(ResourcesCompat.getDrawable(getResources(), R.drawable.u_logo, null),"Test 4");
        CardView_Comunity_Data[] elementos = {asignatura1,asignatura2,asignatura3,asignatura4};
        builder.setView(view)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                })
                .setPositiveButton("acept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
        professorName = view.findViewById(R.id.name_professor);
        professorSurname = view.findViewById(R.id.surname_professor);
        professorImg = view.findViewById(R.id.img_professor);
        drawer = view.findViewById(R.id.drawer_layout_professor);
        recycle = view.findViewById(R.id.recyclerViewProfessor);
        professorDescription = view.findViewById(R.id.description);
        professorDescriptionTwo = view.findViewById(R.id.text_desc);
        mLayoutManagerProfessor = new GridLayoutManager(view.getContext(), 1);
        recycle.setLayoutManager(mLayoutManagerProfessor);
        mAdapterProfessor = new RecyclerAdapter_Comunity_Data(elementos);
        recycle.setAdapter(mAdapterProfessor);
        my_fab = view.findViewById(R.id.my_fab);

    return builder.create();
    }

    public void onclick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("prueba@gmail.com"));
        intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        startActivity(intent.createChooser(intent, "Send email via..."));

    }
}
