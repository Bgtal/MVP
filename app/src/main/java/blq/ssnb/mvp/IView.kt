package blq.ssnb.mvp

import androidx.lifecycle.LifecycleOwner

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期: 2018/7/12
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * MVP 中的 V 父类
 * ================================================
</pre> *
 */
interface IView

interface ILifecycleView : IView, LifecycleOwner, IAttachOrDetachListener