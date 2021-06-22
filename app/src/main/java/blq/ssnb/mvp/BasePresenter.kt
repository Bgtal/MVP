package blq.ssnb.mvp

import androidx.lifecycle.LifecycleOwner
import java.lang.ref.WeakReference

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期: 2018/7/12
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * MVP 中 P 的抽象类,实现 M和V的绑定和解绑功能
 * ================================================
</pre> *
 */
abstract class BasePresenter<V : IView?, M : IModel?> : IPresenter<V, M> {

    private var mViewWeakReference: WeakReference<V>? = null
    private var mModel: M? = null
    override val model: M
        get() {
            if (mModel == null) {
                mModel = initModel()
            }
            return mModel!!
        }
    override val view: V?
        get() = mViewWeakReference?.get()

    override fun attach(view: V) {
        mViewWeakReference?.apply {
            val oldView = get()
            if (oldView is IAttachOrDetachListener) {
                oldView.onMvpDetach()
            }
            clear()
        }
        mViewWeakReference = WeakReference(view)

        if (view is LifecycleOwner) {
            view.lifecycle.addObserver(this)
        }
        if (view is IAttachOrDetachListener) {
            view.onMvpAttach()
        }
        if (model is IAttachOrDetachListener) {
            (model as IAttachOrDetachListener).onMvpAttach()
        }
    }

    override fun detach() {
        mViewWeakReference?.let {
            val view: V? = it.get()
            if (view is IAttachOrDetachListener) {
                view.onMvpDetach()
            }
        }
        mViewWeakReference = null

        mModel?.let {
            if (it is IAttachOrDetachListener) {
                it.onMvpDetach()
            }
        }
        mModel = null
    }
}