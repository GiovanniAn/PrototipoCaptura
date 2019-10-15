package balam.app.protocaptura.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import balam.app.protocaptura.activity.MainActivity;
import balam.app.protocaptura.R;
import balam.app.protocaptura.Utils.Utils;
import balam.app.protocaptura.interfaces.CallbackVozListener;
import balam.app.protocaptura.interfaces.SendData;

public class CaputuraPorVozFragment extends Fragment implements SendData, View.OnClickListener {

    private  TextView setTexto;
    private CallbackVozListener listener;
    private FragmentManager fragmentManager;

    public CaputuraPorVozFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_caputura_por_voz, container, false);
        bindUI(view);
        return view;
    }

    private void bindUI(View view) {
        fragmentManager = getActivity().getSupportFragmentManager();
        ImageView grabarVoz=view.findViewById(R.id.imgGrabarVoz);
        Button capturaManual=view.findViewById(R.id.btnManual);
        grabarVoz.setOnClickListener(this);
        capturaManual.setOnClickListener(this);
        setTexto=view.findViewById(R.id.txtMostrarTexto);
    }


    @Override
    public void mostrarTexto(String porVoz) {
        setTexto.setText(porVoz);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgGrabarVoz:
                listener.onClickListener();
                break;
            case R.id.btnManual:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new CapturaManualFragment(),
                                Utils.SignUp_Fragment).commit();
                break;

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            listener = (CallbackVozListener) context;
        } else {
            throw new RuntimeException( "CallbackVozListener must implement FragmentEvent");
        }
    }
}
