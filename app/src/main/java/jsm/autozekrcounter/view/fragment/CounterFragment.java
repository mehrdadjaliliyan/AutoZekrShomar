package jsm.autozekrcounter.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import jsm.autozekrcounter.R;
import jsm.autozekrcounter.model.RequestZekrPackage;
import jsm.autozekrcounter.model.ZekrModel;

public class CounterFragment extends Fragment implements View.OnClickListener {
    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM1_ID = "param2";
    private String perfsName = "zekr";
    private RequestZekrPackage mParam1;
    private int zekrId;
    private Button btnNext;
    private TextView tvCurCount;
    private TextView tvText;
    private int curPosition;
    private int index = 0;
    private int zekrArraySize;
    private ZekrModel zekrModel;
    private View mRoot;
    private Vibrator vibrator;
    private FloatingActionMenu fabMenu;
    private FloatingActionButton buttonSave;
    private FloatingActionButton buttonClear;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            if (getArguments().containsKey(ARG_PARAM1)) {
                mParam1 = getArguments().getParcelable(ARG_PARAM1);
            }

            if (getArguments().containsKey(ARG_PARAM1_ID)) {
                zekrId = getArguments().getInt(ARG_PARAM1_ID);
                perfsName = perfsName + zekrId;
                load(perfsName);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_counter, container, false);
        mRoot = rootView;
        initUi(rootView);

        if (mParam1 == null) {
            return rootView;
        }
        if (mParam1.getZekrModelArrayList().size() == 0) {
            return rootView;
        }

        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        zekrModel = mParam1.getZekrModelArrayList().get(index);
        tvText.setText(zekrModel.getText());
        tvCurCount.setText(String.valueOf(curPosition));

        zekrArraySize = mParam1.getZekrModelArrayList().size();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextZekr();

            }
        });

        return rootView;
    }

    private void initUi(View rootView) {
        Typeface tfNazanin = Typeface.createFromAsset(getActivity().getAssets(), "fonts/b_nazanin.ttf");
        Typeface tfNazaninBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/b_nazanin_bold.ttf");
        tvText = (TextView) rootView.findViewById(R.id.textViewText);
        tvCurCount = (TextView) rootView.findViewById(R.id.TextViewCount);
        btnNext = (Button) rootView.findViewById(R.id.buttonNext);
        TextView tv1 = (TextView) rootView.findViewById(R.id.textViewText);
        TextView tv2 = (TextView) rootView.findViewById(R.id.textViewText);
        fabMenu = (FloatingActionMenu) rootView.findViewById(R.id.menu_yellow);
        buttonSave = (FloatingActionButton) rootView.findViewById(R.id.fabSave);
        buttonClear = (FloatingActionButton) rootView.findViewById(R.id.fabClear);
        buttonSave.setOnClickListener(this);
        buttonClear.setOnClickListener(this);

        if (tfNazanin != null && tfNazaninBold != null) {
            tvText.setTypeface(tfNazaninBold);
            tvCurCount.setTypeface(tfNazanin);
            tv1.setTypeface(tfNazanin);
            tv2.setTypeface(tfNazanin);
        }
    }

    private void nextZekr() {
        try {
            curPosition++;
            if (curPosition == zekrModel.getCount() && zekrModel.getCount() != ZekrModel.END_LESS) {
                Snackbar.make(mRoot, "end " + zekrModel.getText(), Snackbar.LENGTH_LONG).show();
                vibrator.vibrate(300);
                if (index < zekrArraySize) {
                    index++;
                    if (index == zekrArraySize) {
                        Snackbar.make(mRoot, "final end" + zekrModel.getText(), Snackbar.LENGTH_LONG).show();
                        reset();
                    } else {
                        zekrModel = mParam1.getZekrModelArrayList().get(index);
                        curPosition = 0;
                        tvText.setText(zekrModel.getText());
                    }
                }
            }
            tvCurCount.setText(String.valueOf(curPosition));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reset() {
        curPosition = 0;
        index = 0;
        zekrModel = mParam1.getZekrModelArrayList().get(index);
        tvText.setText(zekrModel.getText());
        tvCurCount.setText(String.valueOf(curPosition));
    }

    private void save(String name) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        editor.putInt("curPosition", curPosition);
        editor.putInt("index", index);
        editor.commit();
    }

    private void load(String name) {
        SharedPreferences prefs = getActivity().getSharedPreferences(name, Context.MODE_PRIVATE);
        if (prefs != null) {
            curPosition = prefs.getInt("curPosition", 0);
            index = prefs.getInt("index", 0);
        }
    }

    private void clearPreferences(String name) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fabMenu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fabSave:
                save(perfsName);
                fabMenu.close(true);
                Snackbar.make(view, "save", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;

            case R.id.fabClear:
                clearPreferences(perfsName);
                reset();
                fabMenu.close(true);
                Snackbar.make(view, "clear", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;

        }
    }
}
