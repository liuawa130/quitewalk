package com.quietwalk.mod;

import com.mojang.logging.LogUtils;
import com.quietwalk.mod.config.QuietWalkConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

@Mod(QuietWalkMod.MOD_ID)
public class QuietWalkMod {
    public static final String MOD_ID = "quietwalk";
    public static final Logger LOGGER = LogUtils.getLogger();

    public QuietWalkMod() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener((FMLCommonSetupEvent e) -> e.enqueueWork(() ->
            LOGGER.info("[Quiet Walk] v1.5.1 loaded - sprint disabled, walk=CS2 run, Ctrl=CS2 silent walk, Shift=CS2 crouch, gun pose kept straight (TaCZ: {})",
                ModList.get().isLoaded("tacz") ? "found" : "not found")));
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, QuietWalkConfig.SPEC);
        if (FMLEnvironment.dist == Dist.CLIENT)
            MinecraftForge.EVENT_BUS.register(new QuietWalkHandler());
    }
}
