package io.ecosed.plugin_example

import io.ecosed.plugin.EcosedPlugin
import io.ecosed.plugin.PluginChannel

class ExamplePlugin : EcosedPlugin, PluginChannel.MethodCallHandler {

    private lateinit var pluginChannel: PluginChannel

    /**
     * 插件被添加时执行
     */
    override fun onEcosedAdded(binding: EcosedPlugin.EcosedPluginBinding) {
        pluginChannel = PluginChannel(binding = binding, channel = channel)
        pluginChannel.setMethodCallHandler(callBack = this@ExamplePlugin)
    }

    /**
     * 插件被移除时执行
     */
    override fun onEcosedRemoved(binding: EcosedPlugin.EcosedPluginBinding) {
        pluginChannel.setMethodCallHandler(callBack = null)
    }

    /**
     * 执行代码时调用
     */
    override fun onEcosedMethodCall(call: PluginChannel.MethodCall, result: PluginChannel.Result) {
        when (call.method) {
            "getText" -> result.success("Hello World")
            else -> result.notImplemented()
        }
    }

    /**
     * 返回EcosedPluginMethod
     */
    override val getPluginChannel: PluginChannel
        get() = pluginChannel

    companion object {
        const val channel: String = "ExamplePlugin"
    }
}