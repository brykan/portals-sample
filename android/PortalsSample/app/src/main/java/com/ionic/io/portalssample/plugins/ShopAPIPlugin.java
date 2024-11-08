package com.ionic.io.portalssample.plugins;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.gson.Gson;
import com.ionic.io.portalssample.App;
import com.ionic.io.portalssample.data.DataService;
import com.ionic.io.portalssample.data.model.Cart;
import com.ionic.io.portalssample.data.model.User;

import org.json.JSONException;
import org.json.JSONObject;

@CapacitorPlugin(name = "ShopAPI")
public class ShopAPIPlugin extends Plugin {

    DataService dataService;

    public ShopAPIPlugin() {
        dataService = DataService.getInstance(App.Companion.getInstance());
    }

    @PluginMethod
    public void getCart(PluginCall call) {
        try {
            Cart cart = App.Companion.getInstance().getShoppingCart().getCart();
            String cartJson = new Gson().toJson(cart);
            JSObject cartJSObject = JSObject.fromJSONObject(new JSONObject(cartJson));
            call.resolve(cartJSObject);
        } catch (JSONException e) {
            call.reject("error decoding cart object");
        }
    }

    @PluginMethod
    public void getUserDetails(PluginCall call) {
        try {
            User user = dataService.getUser();
            String userJson = new Gson().toJson(user);
            JSObject userJSObject = JSObject.fromJSONObject(new JSONObject(userJson));
            call.resolve(userJSObject);
        } catch (JSONException e) {
            call.reject("error decoding user object");
        }
    }

    @PluginMethod
    public void updateUserDetails(PluginCall call) {
        try {
            JSObject userJSObject = call.getData();
            User user = new Gson().fromJson(userJSObject.toString(), User.class);
            dataService.setUser(user);
            call.resolve(userJSObject);
        } catch (Exception e) {
            call.reject("error updating user details");
        }
    }

    @PluginMethod
    public void checkoutResult(PluginCall call) {
        String result = call.getString("result");

        // Update cart
//        CartViewModel cartViewModel = new ViewModelProvider(this.getActivity()).get(CartViewModel.class);
//        ShoppingCart cart = cartViewModel.getShoppingCart().getValue();
//        cart.checkout(result);
//        cartViewModel.getShoppingCart().postValue(cart);

        call.resolve();
    }

    @PluginMethod
    public void getUserPicture(PluginCall call) {
        User user = dataService.getUser();
        if (user.image != null && !user.image.isEmpty()) {
            String picture = user.getImageBase64(getContext());
            if (picture != null) {
                JSObject returnPicture = new JSObject();
                returnPicture.put("picture", picture);
                call.resolve(returnPicture);
                return;
            }
        }

        call.reject("No picture available");
    }

    @PluginMethod
    public void setUserPicture(PluginCall call) {
        String picture = call.getString("picture");
        if (picture != null && !picture.isEmpty()) {
            User user = dataService.getUser();
            dataService.storeUserImage(getContext(), user, picture);
            dataService.setUser(user);
        }
        call.resolve();
    }
}

