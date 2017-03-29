package heimat.com.luoyer.interfaces;

/**
 * Created by code5 on 2017/3/28.
 */
public interface InitInterface {
    void initBefore();
    int getLayoutId();
    void initView();
    void initListener();
    void initData();
}
