package blq.ssnb.mvp

/**
 * <pre>
 * ================================================
 * 作者: SSNB
 * 日期: 2018/6/1
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 2
 * 描述:
 * 在MVP中 用于 P 调用 M 后的回调
 * ================================================
</pre> *
 */
interface MvpCallBack<T> {
    /**
     * 回调成功
     * @param backData 回调成功的对象
     */
    fun onSuccess(backData: T)

    /**
     * 回调失败
     * @param errorCode 错误code
     * @param msg 失败信息
     */
    fun onFail(errorCode: Int, msg: String)
}