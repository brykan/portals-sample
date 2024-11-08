package com.ionic.io.portalssample

import android.app.Application
import com.capacitorjs.plugins.camera.CameraPlugin
import com.ionic.io.portalssample.data.ShoppingCart
import com.ionic.io.portalssample.plugins.ShopAPIPlugin
import io.ionic.portals.PortalManager
import io.ionic.portals.PortalManager.newPortal

class App : Application() {

    val shoppingCart: ShoppingCart = ShoppingCart()

    override fun onCreate() {
        instance = this
        super.onCreate()

        PortalManager.register(BuildConfig.IONIC_API_KEY)

        // Checkout Portal
        newPortal("checkout")
            .setStartDir("webapp")
            .setPlugins(
                mutableListOf(
                    ShopAPIPlugin::class.java,
                    CameraPlugin::class.java
                )
            )
            .create()

        // Help Portal
        val initialContext = HashMap<String, String>()
        initialContext["startingRoute"] = "/help"
        newPortal("help")
            .setStartDir("webapp")
            .setInitialContext(initialContext)
            .setPlugins(mutableListOf(ShopAPIPlugin::class.java))
            .create()

        // Profile Portal
        val initialContextProfile = HashMap<String, String>()
        initialContextProfile["startingRoute"] = "/user"
        newPortal("profile")
            .setStartDir("webapp")
            .addPlugin(ShopAPIPlugin::class.java)
            .addPlugin(CameraPlugin::class.java)
            .setInitialContext(initialContextProfile)
            .create()
    }

    companion object {
        private lateinit var instance: App

        fun getInstance(): App {
            return instance
        }
    }
}
