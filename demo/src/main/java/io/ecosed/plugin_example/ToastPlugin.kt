package io.ecosed.plugin_example

import android.content.Context
import android.widget.Toast
import io.ecosed.plugin.EcosedPlugin
import io.ecosed.plugin.PluginBinding
import io.ecosed.plugin.PluginChannel

class ToastPlugin : EcosedPlugin, PluginChannel.MethodCallHandler {

    private lateinit var pluginChannel: PluginChannel
    private lateinit var mContext: Context

    /**
     * 插件被添加时执行
     */
    override fun onEcosedAdded(binding: PluginBinding) {
        pluginChannel = PluginChannel(binding = binding, channel = channel)
        pluginChannel.getContext()?.let {
            mContext = it
        }
        pluginChannel.setMethodCallHandler(handler = this@ToastPlugin)
    }

    /**
     * 执行代码时调用
     */
    override fun onEcosedMethodCall(call: PluginChannel.MethodCall, result: PluginChannel.Result) {
        when (call.method) {
            "toast" -> Toast.makeText(mContext, "Hello World", Toast.LENGTH_SHORT).show()
            else -> result.notImplemented()
        }
    }

    /**
     * 返回pluginChannel
     */
    override val getPluginChannel: PluginChannel
        get() = pluginChannel

    companion object {
        const val channel: String = "ToastPlugin"
    }
}