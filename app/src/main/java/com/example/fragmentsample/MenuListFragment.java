package com.example.fragmentsample;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuListFragment extends Fragment {

    private Activity _parentActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _parentActivity = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);

        // 画面部品ListViewを取得。
        ListView lvMenu = view.findViewById(R.id.lvMenu);

        // SimpleAdapterで使用するListオブジェクトを用意。
        List<Map<String, String>> menuList = new ArrayList<>();

        // 定食のデータを格納する
        Map<String, String> menu = new HashMap<>();
        menu.put("name", "唐揚げ定食");
        menu.put("price", "800円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", "940円");
        menuList.add(menu);

        // SimpleAdapterの第4引数の用意
        String[] from = {"name", "price"};

        // SimpleAdapterの第5引数の用意
        int[] to = {android.R.id.text1, android.R.id.text2};
        // text1, text2とは一体・・・

        // SimpleAdapterの生成

        SimpleAdapter adapter = new SimpleAdapter(_parentActivity, menuList,
                android.R.layout.simple_list_item_2, from, to);

        // アダプタの登録
        lvMenu.setAdapter(adapter);

        // リスナの登録
        lvMenu.setOnItemClickListener(new ListItemClickListener());

        return view;
    }
    private class ListItemClickListener implements android.widget.AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // タップされた行のデータを取得
            Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);

            // 定食と金額を取得

            String menuName = item.get("name");
            String menuPrice = item.get("price");

            // インテントオブジェクトを生成
            Intent intent = new Intent(_parentActivity, MenuThanksActivity.class);

            // 第2画面に送るデータを格納
            intent.putExtra("menuName", menuName);
            intent.putExtra("menuPrice", menuPrice);

            startActivity(intent);
        }
    }



}
