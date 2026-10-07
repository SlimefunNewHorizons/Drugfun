package tsp.drugfun.implementation.item;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import io.github.bakedlibs.dough.items.ItemUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.potion.PotionEffect;
import tsp.drugfun.Drugfun;
import tsp.drugfun.implementation.DrugGroup;
import tsp.drugfun.implementation.data.DrugData;
import tsp.drugfun.util.Utils;

import java.util.Map;

public class Drug extends SlimefunItem {

    public static final NamespacedKey DRUG_COUNTER_KEY = new NamespacedKey(Drugfun.getInstance(), "drugs_active");
    private final DrugData data;

    public Drug(DrugData data) {
        super(DrugGroup.GROUP,
                Utils.asSlimefunStack(data),
                Utils.asRecipeType(data.getRecipeData()),
                Utils.asIngredients(data.getRecipeData())
        );
        this.data = data;
    }

    public void onRightClick(PlayerRightClickEvent event) {
        int counter = PersistentDataAPI.getInt(event.getPlayer(), DRUG_COUNTER_KEY, 0); // overdose counter

        event.getPlayer().addPotionEffects(Utils.asEffects(data.getTriggerData()));
        ItemUtils.consumeItem(event.getItem(), true);
        PersistentDataAPI.setInt(event.getPlayer(), DRUG_COUNTER_KEY, counter + data.getTriggerData().getAmount());

        Map<PotionEffect, Integer> effects = Drugfun.getInstance().getCfg().getOverdoseEffects();
        for (Map.Entry<PotionEffect, Integer> entry : effects.entrySet()) {
            if (entry.getValue() >= counter) {
                event.getPlayer().addPotionEffect(entry.getKey());
            }
        }
    }

    public DrugData getData() {
        return data;
    }

    @Override
    public void preRegister() {
        addItemHandler((ItemUseHandler) this::onRightClick);
    }

}
