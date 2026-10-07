package tsp.drugfun.implementation.item;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.bakedlibs.dough.items.ItemUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import tsp.drugfun.implementation.DrugGroup;

public class Morphine extends SlimefunItem {

    public Morphine() {
        super(DrugGroup.GROUP, new SlimefunItemStack(
                "DRUG_MORPHINE",
                Material.COCOA_BEANS,
                "&bMorfina médica",
                " ",
                "&7Quita todos los efectos negativos"
        ), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.SPLINT
        }); // TODO: Add recipe
    }
    
    private final PotionEffectType[] effects = new PotionEffectType[]{
            PotionEffectType.NAUSEA, PotionEffectType.BLINDNESS, PotionEffectType.WEAKNESS,
            PotionEffectType.HUNGER, PotionEffectType.SLOWNESS, PotionEffectType.MINING_FATIGUE,
    };

    public void onRightClick(PlayerRightClickEvent event) {
        Player player = event.getPlayer();
        ItemUtils.consumeItem(event.getItem(), true);
        for (PotionEffectType effect : effects) {
            player.removePotionEffect(effect);
        }
    }
    
    @Override
    public void preRegister() {
        addItemHandler((ItemUseHandler) this::onRightClick);
    }
    
}
