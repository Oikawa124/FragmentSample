package com.example.fragmentsample;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuThanksFragment extends Fragment {

    private Activity _parentActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 所属するアクティビティオブジェクトを取得
        _parentActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_thanks, container, false);

        Intent intent = _parentActivity.getIntent();

        Bundle extras = intent.getExtras();

        String menuName = "";
        String menuPrice = "";

        // 引き継ぎデータが存在すれば・・・
        if (extras != null) {
            menuName = extras.getString("menuName");
            menuPrice = extras.getString("menuPrice");
        }

        // 定食名と金額を表示させるTextViewを取得
        TextView tvMenuName = view.findViewById(R.id.tvmenuName);
        TextView tvMenuPrice = view.findViewById(R.id.tvMenuPrice);

        // TextViewに定食名と金額を表示

        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);

        // 戻るボタンを取得
        Button btBackButton = view.findViewById(R.id.btBackButton);

        // 戻るボタンにリスナを登録
        btBackButton.setOnClickListener(new ButtonClickListener());

        return view;
    }


    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            _parentActivity.finish();
        }
    }

}
