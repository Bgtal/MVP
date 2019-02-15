package blq.ssnb.mvp;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期: 2018/7/12
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * MVP 中的 P 父类 继承 {@link LifecycleObserver}
 * ================================================
 * </pre>
 */
public interface IPresenter<V extends IView,M extends IModel> extends LifecycleObserver {

    /**
     * 初始化 model
     * @return 返回model类
     */
    @NonNull
    M initModel();
    /**
     * 获取model对象
     * @return Model 对象
     */
    @NonNull
    M getModel();

    /**
     * 获得 绑定的View
     * @return 绑定的view
     */
    @Nullable
    V getView();

    /**
     * 绑定view
     * @param view P 需要绑定的view
     */
    void attach(@NonNull V view);

    /**
     * 解绑
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void detach();
}
