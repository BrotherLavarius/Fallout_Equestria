package com.redsparkle.foe.network;

import com.google.gson.JsonObject;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
            case "gui_advInv":
                GUI_advInv_Processor(process, ctx, side);
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
            case "sync_adv_inv":
                Adv_Inv_Sync(process, ctx, side);
                break;
            case "sync_adv_inv_op":
                Adv_Inv_Sync_op(process, ctx, side);
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

    private static void GUI_advInv_Processor(JsonObject process, MessageContext ctx, String side) {
        if (side.equalsIgnoreCase("client")) {

        } else if (side.equalsIgnoreCase("server")) {
            DedicatedServerProxy.handleAdv(process, ctx);
        }
    }


    private static void ammo_holder_update(JsonObject process, MessageContext ctx, String side) {
        if (side.equalsIgnoreCase("client")) {
            ClientOnlyProxy.handleSync_AmmoItems(process);
        } else if (side.equalsIgnoreCase("server")) {
        }
    }

    private static void Adv_Inv_Sync(JsonObject process, MessageContext ctx, String side) {
        if (side.equalsIgnoreCase("client")) {
            ClientOnlyProxy.handleAdv_SYNC(process);
        } else if (side.equalsIgnoreCase("server")) {
            DedicatedServerProxy.handleAdv_SYNC(process, ctx);
        }
    }

    private static void Adv_Inv_Sync_op(JsonObject process, MessageContext ctx, String side) {
        if (side.equalsIgnoreCase("client")) {
            ClientOnlyProxy.handleAdv_SYNC_op(process);
        } else if (side.equalsIgnoreCase("server")) {

        }
    }
}
