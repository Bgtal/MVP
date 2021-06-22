package blq.ssnb.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期: 2018/7/12
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * MVP 中的 P 父类 继承 [LifecycleObserver]
 * ================================================
</pre> *
 */
interface IPresenter<V : IView?, M : IModel?> : LifecycleObserver {
    /**
     * 初始化 model
     * @return 返回model类
     */
    fun initModel(): M

    /**
     * 获取model对象
     * @return Model 对象
     */
    val model: M

    /**
     * 获得 绑定的View
     * @return 绑定的view
     */
    val view: V?

    /**
     * 绑定view
     * @param view P 需要绑定的view
     */
    fun attach(view: V)

    /**
     * 解绑
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detach()
}