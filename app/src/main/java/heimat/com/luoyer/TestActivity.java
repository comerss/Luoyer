package heimat.com.luoyer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by code5 on 2017/3/29.
 */
public class TestActivity extends AppCompatActivity{
    @BindView(R.id.textview)
    TextView mTextview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        mTextview.setText("画的覅");
    }
}
