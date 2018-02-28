package com.redsparkle.foe.network;

import com.google.gson.JsonObject;
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
            case "graph":
                GFProcessor(process, ctx);
                break;
            case "sound":
                SoundProcessor(process, ctx);
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


    @SideOnly(Side.CLIENT)
    private static void SoundProcessor(JsonObject process, MessageContext ctx) {
    }

    @SideOnly(Side.CLIENT)
    private static void GFProcessor(JsonObject process, MessageContext ctx) {
    }
}
