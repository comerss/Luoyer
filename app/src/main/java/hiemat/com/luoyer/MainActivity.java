package hiemat.com.luoyer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    int[] images=new int[]{R.drawable.a6k,R.drawable.a4r,
            R.drawable.af8,R.drawable.a8_,
            R.drawable.a8a,R.drawable.a8c};
    private MainAdapter mAdapter;
    ArrayList imagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }

    private void initData() {

    }

    private void initListener() {
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }
        });
    }

    private void initView() {
        imagesList=new ArrayList();
        for(int i=0;i<images.length;i++){
            imagesList.add(images[i]);
        }
        Log.i("size","---------->"+imagesList.size());
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mAdapter = new MainAdapter(this, imagesList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
