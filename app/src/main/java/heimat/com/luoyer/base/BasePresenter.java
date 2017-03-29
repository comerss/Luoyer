package heimat.com.luoyer.base;

/**
 * Created by code5 on 2017/3/28.
 */
public class BasePresenter<V extends BaseView> implements Presenter<V> {
    protected V mvpView;
    public BasePresenter(V mvpView){
        attachView(mvpView);
    }
    @Override
    public void attachView(V view) {
        mvpView=view;
    }

    @Override
    public void detachView() {
        mvpView=null;
    }
}
