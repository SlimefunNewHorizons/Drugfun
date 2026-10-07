package tsp.drugfun.util;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tsp.drugfun.Drugfun;
import tsp.drugfun.implementation.data.DrugData;
import tsp.drugfun.implementation.data.PotionData;
import tsp.drugfun.implementation.data.RecipeData;
import tsp.drugfun.implementation.data.ResearchData;
import tsp.drugfun.implementation.data.TriggerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static SlimefunItemStack asSlimefunStack(DrugData data) {
        return new SlimefunItemStack(
                data.getId().toUpperCase(), // Slimefun requires upper case ids
                new ItemStack(Material.matchMaterial(data.getMaterial()), data.getRecipeData().getOutput()),
                data.getName(),
                data.getLore().toArray(new String[0])
        );
    }

    public static RecipeType asRecipeType(RecipeData data) {
        String type = data.getType();
        RecipeType result = RecipeType.NULL;
        if (type.equalsIgnoreCase("ENHANCED_CRAFTING_TABLE") || type.equalsIgnoreCase("ECT")) {
            result = RecipeType.ENHANCED_CRAFTING_TABLE;
        } else if (type.equalsIgnoreCase("FOOD_FABRICATOR") || type.equalsIgnoreCase("FF")) {
            result = RecipeType.FOOD_FABRICATOR;
        }
        // TODO: finish

        return result;
    }

    public static ItemStack[] asIngredients(RecipeData data) {
        List<String> raw = data.getIngredients();
        List<ItemStack> result = new ArrayList<>();
        for (String entry : raw) {
            String[] args = entry.split(":");
            ItemStack item = new ItemStack(Material.AIR, Integer.parseInt(args[1]));
            String material = args[0];
            if (material.startsWith("#")) {
                Slimefun.getRegistry().getAllSlimefunItems().stream()
                        .filter(sfItem -> sfItem.getId().equalsIgnoreCase(args[0]))
                        .findFirst()
                        .ifPresent(sfItem -> result.add(sfItem.getItem()));
            } else {
                item.setType(Material.matchMaterial(material));
                result.add(item);
            }
        }

        return result.toArray(new ItemStack[0]);
    }

    public static Research asResearch(ResearchData data) {
        return new Research(
                new NamespacedKey(Drugfun.getInstance(), data.getId()),
                data.getLegacyId(),
                data.getName(),
                data.getCost()
        );
    }

    public static List<PotionEffect> asEffects(TriggerData data) {
        List<String> raw = data.getEffects();
        List<PotionEffect> result = new ArrayList<>();
        for (String entry : raw) {
            String[] args = entry.split(":");
            result.add(
                    new PotionEffect(PotionEffectType.getByName(args[0]),
                            Integer.parseInt(args[1]) * 20,
                            Integer.parseInt(args[2]) - 1
                    ));
        }

        return result;
    }

    public static Map<PotionEffect, Integer> asOverdoseEffects(List<PotionData> data) {
        Map<PotionEffect, Integer> result = new HashMap<>();
        for (PotionData entry : data) {
            PotionEffect effect = new PotionEffect(
                    PotionEffectType.getByName(entry.getType()),
                    entry.getDuration(),
                    entry.getAmplifier()
            );
            result.put(effect, entry.getRequired());
        }

        return result;
    }

}
