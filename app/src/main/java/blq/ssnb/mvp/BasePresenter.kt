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
            return mModel
        }
    override val view: V?
        get() = if (mViewWeakReference != null) {
            mViewWeakReference!!.get()
        } else null

    override fun attach(view: V) {
        mViewWeakReference = if (mViewWeakReference == null) {
            WeakReference(view)
        } else {
            if (view != null && view is IAttachOrDetachListener) {
                (view as IAttachOrDetachListener).onMvpDetach()
            }
            mViewWeakReference!!.clear()
            WeakReference(view)
        }
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
        }
        if (view is IAttachOrDetachListener) {
            (view as IAttachOrDetachListener).onMvpAttach()
        }
        if (model is IAttachOrDetachListener) {
            (model as IAttachOrDetachListener).onMvpAttach()
        }
    }

    override fun detach() {
        if (mViewWeakReference != null) {
            if (view != null && view is IAttachOrDetachListener) {
                (view as IAttachOrDetachListener?)!!.onMvpDetach()
            }
            mViewWeakReference!!.clear()
            mViewWeakReference = null
        }
        if (mModel != null) {
            if (mModel is IAttachOrDetachListener) {
                (mModel as IAttachOrDetachListener).onMvpDetach()
            }
            mModel = null
        }
    }
}