package hiemat.com.luoyer;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by code5 on 2017/3/22.
 */
public class MainAdapter extends BaseQuickAdapter {
    int[] images=new int[]{R.drawable.a6k,R.drawable.a4r,R.drawable.af8,R.drawable.a8_,R.drawable.a8a,R.drawable.a8c};
    private Context mContext;
    public MainAdapter(List data) {
        super(R.layout.adapter_main,data);
    }
    public MainAdapter(Context context,List data) {
        super(R.layout.adapter_main,data);
        mContext=context;
    }
    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        helper.setImageResource(R.id.Img, (int) item);
    }

}
