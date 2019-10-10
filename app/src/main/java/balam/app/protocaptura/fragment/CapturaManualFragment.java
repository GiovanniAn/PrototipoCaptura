package balam.app.protocaptura.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import balam.app.protocaptura.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CapturaManualFragment extends Fragment {


    public CapturaManualFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_captura_manual, container, false);
        return view;
    }

}
