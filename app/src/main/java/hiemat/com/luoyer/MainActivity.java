package hiemat.com.luoyer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    int[] images=new int[]{R.drawable.a6k,R.drawable.a4r,R.drawable.af8,R.drawable.a8_,R.drawable.a8a,R.drawable.a8c};
    private MainAdapter mAdapter;

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

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mAdapter = new MainAdapter(this, Arrays.asList(images));
        mRecyclerView.setAdapter(mAdapter);
    }
}
