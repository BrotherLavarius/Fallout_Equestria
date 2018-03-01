package com.redsparkle.foe.network;

import com.google.gson.JsonObject;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class UnifiedProcessor {

    public static void GP(JsonObject process, MessageContext ctx, @Nullable String side) {
        String type = process.get("type").getAsString();
        switch (type) {

            case "gun_fire":
                GUNFire(process, ctx, side);
                break;
            case "gun_reload":
                GUNReload(process, ctx, side);
                break;
            case "capa":
                CAPAProcessor(process, ctx, side);
                break;
            case "gui":
                GUIProcessor(process, ctx, side);
                break;
            case "sound":
                SoundProcessor(process, ctx, side);
                break;
            case "lvl_update_request":
                Lvl_update_request(process, ctx, side);
                break;
            case "lvl_update_reply":
                Lvl_update_request(process, ctx, side);
                break;
            case "ammo_holder_udpate":
                ammo_holder_update(process, ctx, side);
                break;

        }


    }


    private static void GUNFire(JsonObject process, MessageContext ctx, String side) {
        if (side.equalsIgnoreCase("client")) {

        } else if (side.equalsIgnoreCase("server")) {
            DedicatedServerProxy.MessageGunFire_handler(process, ctx);
        }


    }

    private static void GUNReload(JsonObject process, MessageContext ctx, String side) {
        if (side.equalsIgnoreCase("client")) {

        } else if (side.equalsIgnoreCase("server")) {
            DedicatedServerProxy.MessageGunReload_handler(process, ctx);
        }


    }

    private static void CAPAProcessor(JsonObject process, MessageContext ctx, String side) {
    }

    private static void Lvl_update_request(JsonObject process, MessageContext ctx, String side) {
        if (side.equalsIgnoreCase("client")) {
            ClientOnlyProxy.handleLevelMessageOnDemand(process);

        } else if (side.equalsIgnoreCase("server")) {
            DedicatedServerProxy.handleSLSOnDemand(ctx);
        }
    }


    @SideOnly(Side.CLIENT)
    private static void SoundProcessor(JsonObject process, MessageContext ctx, String side) {


        if (side.equalsIgnoreCase("client")) {
            ClientOnlyProxy.MessageClientPlaySound_handler(process, ctx);

        } else if (side.equalsIgnoreCase("server")) {
        }
    }

    private static void GUIProcessor(JsonObject process, MessageContext ctx, String side) {
        if (side.equalsIgnoreCase("client")) {
            ClientOnlyProxy.handleOpenGui(process);

        } else if (side.equalsIgnoreCase("server")) {
            DedicatedServerProxy.handleOpenGuiMessage(process, ctx);
        }
    }

    private static void ammo_holder_update(JsonObject process, MessageContext ctx, String side) {
        if (side.equalsIgnoreCase("client")) {
            ClientOnlyProxy.handleSync_AmmoItems(process);
        } else if (side.equalsIgnoreCase("server")) {
        }
    }
}
