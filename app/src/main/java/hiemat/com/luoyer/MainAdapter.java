package hiemat.com.luoyer;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by code5 on 2017/3/22.
 */
public class MainAdapter extends BaseQuickAdapter {

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
        helper.setImageResource(R.id.Img, (Integer) item);
    }

}
