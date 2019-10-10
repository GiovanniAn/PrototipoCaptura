package balam.app.protocaptura.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import balam.app.protocaptura.R;
import balam.app.protocaptura.Utils.Utils;

public class CapturaCamaraFragment extends Fragment implements View.OnClickListener {
    private static View view;
    public static int REQUEST_CAMERA=100;
    private Button scanBtn,vozBtn;
    private static FragmentManager fragmentManager;
    private CodeScanner mCodeScanner;
    private  TextView textContent;
    private FragmentActivity myContext;
    private CodeScannerView scannerView;
    public CapturaCamaraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Activity activity = getActivity();
        view = inflater.inflate(R.layout.fragment_captura_camara, container, false);
        init();

        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textContent.setText(result.getText());

                    }
                });
            }
        });
        // Inflate the layout for this fragment
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    private void init(){
        fragmentManager = getActivity().getSupportFragmentManager();
        textContent = view.findViewById(R.id.text_content);
        scanBtn = view.findViewById(R.id.btn_scan);
        scanBtn.setOnClickListener(this);
        vozBtn =view.findViewById(R.id.btn_voz);
        vozBtn.setOnClickListener(this);
        scannerView = view.findViewById(R.id.scanner_view);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_scan:

                checkPermission();
                break;
            case R.id.btn_voz:

                // Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new CaputuraPorVozFragment(),
                                Utils.SignUp_Fragment).commit();
                break;
        }
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        } else {
            mCodeScanner.startPreview();
            scannerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }



}
