package io.github.falon.villagerhuts.init;

import io.github.falon.villagerhuts.VillagerHuts;
import io.github.falon.villagerhuts.item.VillagerSackItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ModItems {
	Map<Item, Identifier> items = new LinkedHashMap<>();

	Item VILLAGER_SACK = makeItem("villager_sack", new VillagerSackItem(new QuiltItemSettings()));

	private static <T extends Item> T makeItem(String name, T item) {
		items.put(item, new Identifier(VillagerHuts.MOD_ID, name));
		return item;
	}

	static void initialize() {
		items.keySet().forEach(item -> {
			Registry.register(Registries.ITEM, items.get(item), item);
		});
	}
}
