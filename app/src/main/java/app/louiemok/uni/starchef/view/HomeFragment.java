package app.louiemok.uni.starchef.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;

import app.louiemok.uni.starchef.R;
import app.louiemok.uni.starchef.adapter.ReadRecommandOutSideAdapter;
import app.louiemok.uni.starchef.adapter.SkillRecommandOutSideAdapter;
import app.louiemok.uni.starchef.seledefine.ListViewInScrollView;
import app.louiemok.uni.starchef.seledefine.YsxNavigationBar;


public class HomeFragment extends Fragment implements View.OnClickListener{

    YsxNavigationBar ysxNavigationBar;
    ListView lv_skillrecommand;
    ListViewInScrollView lv_readrecommand;
    View view;
    SkillRecommandOutSideAdapter skillRecommandOutSideAdapter;
    ReadRecommandOutSideAdapter readRecommandOutSideAdapter;
    ImageView iv_dailyfood;
    ImageView iv_classicalmenu;
    ImageView iv_skill;
    ImageView iv_videoarea;
    RelativeLayout rl_toreadrecommand;
    RelativeLayout rl_toskillrecommand;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated ( Bundle savedInstanceState ) {
        super.onActivityCreated(savedInstanceState);
        initYsxNavigationBar();
        initSkillRecommandLstView();
        initReadRecommandLstView();
        initElements();
    }

    private void initYsxNavigationBar () {
        ysxNavigationBar = view.findViewById(R.id.nav);
        ysxNavigationBar.hideLeftSrc();
        ysxNavigationBar.setTitle("首页");
        ysxNavigationBar.hideRightSrc();
        ysxNavigationBar.showRightSrc(R.drawable.magnifer);
        ysxNavigationBar.setClickCallback(new YsxNavigationBar.ClickCallback() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightBtnClick() {

            }

            @Override
            public void onRightTextClick() {

            }
        });
    }

    public static HomeFragment newInstance ( String title ) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    private void initElements () {
        iv_dailyfood = view.findViewById(R.id.iv_dailyfood);
        iv_classicalmenu = view.findViewById(R.id.iv_classicalmenu);
        iv_skill = view.findViewById(R.id.iv_skill);
        iv_videoarea = view.findViewById(R.id.iv_videoarea);
        rl_toskillrecommand = view.findViewById(R.id.rl_toskillrecommand);
        rl_toreadrecommand = view.findViewById(R.id.rl_toreadrecommand);
        iv_dailyfood.setOnClickListener(this);
        iv_classicalmenu.setOnClickListener(this);
        iv_skill.setOnClickListener(this);
        iv_videoarea.setOnClickListener(this);
        rl_toskillrecommand.setOnClickListener(this);
        rl_toreadrecommand.setOnClickListener(this);
    }

    private void initSkillRecommandLstView () {
        lv_skillrecommand = view.findViewById(R.id.lv_skillrecommand);
        skillRecommandOutSideAdapter = new SkillRecommandOutSideAdapter(getActivity(), new
                ArrayList<HashMap<String, String>>());
        lv_skillrecommand.setAdapter(skillRecommandOutSideAdapter);
        setListViewHeightBasedOnChild(lv_skillrecommand, false);
    }

    private void initReadRecommandLstView () {
        lv_readrecommand = view.findViewById(R.id.lv_readrecommand);
        readRecommandOutSideAdapter = new ReadRecommandOutSideAdapter(getActivity(), new
                ArrayList<HashMap<String, String>>());
        lv_readrecommand.setAdapter(readRecommandOutSideAdapter);
//        setListViewHeightBasedOnChild(lv_readrecommand, true);
    }

    private void setListViewHeightBasedOnChild ( ListView ls, boolean isbottom) {
        ListAdapter listAdapter = ls.getAdapter();
        if ( listAdapter == null ) {
            return;
        }

        int totalHeight = 0;
        for ( int i = 0 ; i < listAdapter.getCount() ; i++ ) {
            View listItem = listAdapter.getView(i, null, ls);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = ls.getLayoutParams();
        if ( isbottom ) {
            params.height = totalHeight+dip2px(getActivity(), 56);
        }
        else {
            params.height = totalHeight + (ls.getDividerHeight() * (listAdapter.getCount() - 1));
        }
        ls.setLayoutParams(params);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void onClick ( View v ) {
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_dailyfood:
                intent = new Intent(getActivity(), DailyFoodActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.iv_classicalmenu:
                break;
            case R.id.iv_skill:
                break;
            case R.id.iv_videoarea:
                break;
            case R.id.rl_toreadrecommand:
                intent = new Intent(getActivity(), ReadRecommandActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.rl_toskillrecommand:
                intent = new Intent(getActivity(), SkillRecommandActivity.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }

}
