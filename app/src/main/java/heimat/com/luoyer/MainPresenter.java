package heimat.com.luoyer;

import heimat.com.luoyer.base.BasePresenter;

/**
 * Created by code5 on 2017/3/29.
 */
public class MainPresenter extends BasePresenter<TestView>  {
    public MainPresenter(TestView mvpView) {
        super(mvpView);
    }
    public void getData(String text){
        mvpView.showData(text);
    }
}
