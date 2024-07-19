package io.github.falon.villagerhuts;

import io.github.falon.villagerhuts.init.ModItems;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VillagerHuts implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Villager Huts");
	public static final String MOD_ID = "villagerhuts";

    @Override
    public void onInitialize(ModContainer mod) {
        LOGGER.info("Hello Quilt world from {}! Stay fresh!", mod.metadata().name());
		ModItems.initialize();
    }
}
